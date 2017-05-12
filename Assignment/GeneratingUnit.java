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

public class GeneratingUnit extends Base_constructor {
				
			private static String name;				
			private static String maxP;
			private static String minP;
			private static String eq_con_rdfID;
			
			//method used to load data and store the data into an arraylist of objects
			public static void generatingUnit(){		
					
				try {
					//read EQ file
					File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(XmlFile);
					doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
					
					//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
					NodeList generatingUnitList = doc.getElementsByTagName("cim:GeneratingUnit"); 
								
					//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
					System.out.println("-------------GeneratingUnitList----------------");	
					for (int i = 0; i < generatingUnitList.getLength(); i++) {				
						Node theNode = generatingUnitList.item(i);				
						extractMethod(theNode);					
						}							
					}
				catch(Exception e){
					e.printStackTrace();
				}
			}	
			
			//method to extract data and store it into an new base_voltage object
			public static void extractMethod (Node node){				
				
				//Searching for values with the method parameter in the class ReadNode		
				String rdfID = ReadNode.parameter(node,"rdf:ID");
				String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
				String maxP = ReadNode.parameter(node,"cim:GeneratingUnit.maxOperatingP");
				String minP = ReadNode.parameter(node,"cim:GeneratingUnit.minOperatingP");
				String eq_con_rdfID = ReadNode.parameter(node,"cim:Equipment.EquipmentContainer").substring(1);			
				
				//print
				System.out.println("rdfID: " + rdfID + "; Name: " + name + "; MaxP: " + maxP + "; MinP: " + minP + "; Eq_con_rdfID: " + eq_con_rdfID );	
					
				//save data in SQL database
				try{
					Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
					String query = "insert into GeneratingUnit values (?,?,?,?,?)";
					PreparedStatement preparedStmt = conn1.prepareStatement(query);
					preparedStmt.setString(1, rdfID);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, maxP);
					preparedStmt.setString(4, minP);
					preparedStmt.setString(5, eq_con_rdfID);
					preparedStmt.execute();
					}
				catch(Exception e){
					System.out.println(e);
					}		
			}			
			
			
				
		}
	


