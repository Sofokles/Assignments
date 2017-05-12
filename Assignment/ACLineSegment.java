package Assignment;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ACLineSegment {

	private static String name;		
	private static String reg_rdfID;	
	
	//method used to load data and store the data into an arraylist of objects
	public static void aCLineSegment(){		
			
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList aCLineSegmentList = doc.getElementsByTagName("cim:ACLineSegment"); 
						
			//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
			System.out.println("-------------ACLineSegmentList----------------");	
			for (int i = 0; i < aCLineSegmentList.getLength(); i++) {				
				Node theNode = aCLineSegmentList.item(i);				
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
		double r = Double.parseDouble(ReadNode.parameter(node,"cim:ACLineSegment.r"));
		double x = Double.parseDouble(ReadNode.parameter(node,"cim:ACLineSegment.x"));
					
		//print
		System.out.println("rdfID: " + rdfID + "; Name: " + name + "; r: " + r + "; x: " + x);
			
		//save data in SQL database
		try{
			Connection conn1 = (Connection) Connectingdatabase.makeConnection();			
			String query = "insert into ACLine values (?,?,?,?)";
			PreparedStatement preparedStmt = conn1.prepareStatement(query);
			preparedStmt.setString(1, rdfID);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, r);
			preparedStmt.setDouble(4, x);
			preparedStmt.execute();
			}
		catch(Exception e){
			System.out.println(e);
			}	
	}

		
}




