package Assignment;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SynchronousMachineTest extends Base_constructor {
						
				private static String P;
				private static String Q;
				
				//method used to load data and store the data into an arraylist of objects
				public static void synchronousMachine(ArrayList<SynchronousMachine>SynchronousMachineList){		
						
					try {
						//read EQ file
						File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc = dBuilder.parse(XmlFile);
						doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
						
						//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
						NodeList synchronousMachineList = doc.getElementsByTagName("cim:SynchronousMachine"); 
									
						//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
						for (int i = 0; i <synchronousMachineList.getLength(); i++) {				
							Node theNode = synchronousMachineList.item(i);				
							SynchronousMachineList.add(extractMethod(theNode));					
							}							
						}
					catch(Exception e){
						e.printStackTrace();
					}
				}	
				
				//method to extract data and store it into an new object
				public static SynchronousMachine extractMethod (Node node){				
					
					//Searching for values with the method parameter in the class ReadNode		
					String rdfID = ReadNode.parameter(node,"rdf:ID");
					String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
					String ratedS = ReadNode.parameter(node,"cim:RotatingMachine.ratedS");					
					String genUnit_rdfID = ReadNode.parameter(node,"cim:RotatingMachine.GeneratingUnit");
					String regControl_rdfID = ReadNode.parameter(node,"cim:RegulatingCondEq.RegulatingControl");
					String eq_con_rdfID = ReadNode.parameter(node,"cim:Equipment.EquipmentContainer");
					
					try {
						//read SSH file
						File XmlFile = new File("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						Document doc2 = dBuilder.parse(XmlFile);
						doc2.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
						
						//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
						NodeList synchronousMachineList2 = doc2.getElementsByTagName("cim:SynchronousMachine"); 
									
						//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
						for (int i = 0; i <synchronousMachineList2.getLength(); i++) {				
							Node theNode2 = synchronousMachineList2.item(i);				
							String rdfID2 = ReadNode.parameter(theNode2,"rdf:about");							
							String rdfID1 = 	"#" + rdfID;	
							//System.out.println("rdfID2: " + rdfID2 + " rdfID1: " + rdfID1);							
							
							if (rdfID2.equals(rdfID1)){
								//System.out.println("rdfID: " + rdfID2);
								P = ReadNode.parameter(theNode2,"cim:RotatingMachine.p");
								Q = ReadNode.parameter(theNode2,"cim:RotatingMachine.q");
							}
							
							}							
						}
					catch(Exception e){
						e.printStackTrace();
					}
					
					//create an object and set values
					SynchronousMachine obj = new SynchronousMachine();		
					obj.setRdfID(rdfID);
					obj.setName(name);		
					obj.setRatedS(ratedS);		
					obj.setP(P);
					obj.setQ(Q);
					obj.setGenUnit_rdfID(genUnit_rdfID);
					obj.setRegControl_rdfID(regControl_rdfID);
					obj.setEq_con_rdfID(eq_con_rdfID);
					
					//return the object
					return obj;		
				}
				
				
				//set-methods	
				public void setP(String P_1){		
					P = P_1;				
				}
				public void setQ(String Q_1){		
					Q = Q_1;				
				}				
				
				//get-methods
				public static String getP(){		 
					return P;		
				}
				public static String getQ(){		 
					return Q;		
				}
}
