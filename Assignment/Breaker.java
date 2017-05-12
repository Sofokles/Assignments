package Assignment;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Breaker extends Base_constructor {	
						
			private static String state;			
			
			//method used to load data and store the data into an arraylist of objects
			public static void breaker(){		
					
				try {
					//read EQ file
					File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(XmlFile);
					doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
					
					//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
					NodeList breakerList = doc.getElementsByTagName("cim:Breaker"); 
								
					//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
					System.out.println("-------------BreakerList----------------");	
					for (int i = 0; i <breakerList.getLength(); i++) {				
						Node theNode = breakerList.item(i);				
						extractMethod(theNode);					
						}							
					}
				catch(Exception e){
					e.printStackTrace();
				}
			}	
			
			//method to extract data and store it into an new object
			public static void extractMethod (Node node){				
				
				//Searching for values with the method parameter in the class ReadNode		
				String rdfID = ReadNode.parameter(node,"rdf:ID");
				String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");			
				String eq_con_rdfID = ReadNode.parameter(node,"cim:Equipment.EquipmentContainer").substring(1);
				
				try {
					//read SSH file
					File XmlFile = new File("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc2 = dBuilder.parse(XmlFile);
					doc2.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
					
					//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
					NodeList energyConsumerList2 = doc2.getElementsByTagName("cim:Breaker"); 
								
					//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 				
					for (int i = 0; i <energyConsumerList2.getLength(); i++) {				
						Node theNode2 = energyConsumerList2.item(i);				
						String rdfID2 = ReadNode.parameter(theNode2,"rdf:about").substring(1);												
						
						if (rdfID2.equals(rdfID)){
							//System.out.println("rdfID: " + rdfID2);
							state = ReadNode.parameter(theNode2,"cim:Switch.open");							
						}
						
					}							
				}
				catch(Exception e){
					e.printStackTrace();
				}				
				//print
				System.out.println("rdfID: " + rdfID + "; Name: " + name + "; State: " + state + "; Eq_con_rdfID: " + eq_con_rdfID);	
				
				//save data in SQL database
				try{
					Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
					String query = "insert into Breaker values (?,?,?,?)";
					PreparedStatement preparedStmt = conn1.prepareStatement(query);
					preparedStmt.setString(1, rdfID);
					preparedStmt.setString(2, name);				
					preparedStmt.setString(3, state);					
					preparedStmt.setString(4, eq_con_rdfID);
					preparedStmt.execute();
				}
				catch(Exception e){
					System.out.println(e);
				}		
			}			
			
					
		}






