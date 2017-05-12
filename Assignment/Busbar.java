package Assignment;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Busbar extends Base_constructor {	
	
	//method used to load data and store the data into an arraylist of objects
	public static void busbar(ArrayList<Busbar> BusbarList){		
		
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();//normalize EQ file ( which returns root element of the file and then normalize your XML object.)			
			
			//reads all "cim:BaseVoltage" to a list called basvoltList from the DOM-doc
			NodeList busbarList = doc.getElementsByTagName("cim:BusbarSection"); 
						
			//for-loop to to store the loaded XML-data "basvoltList.doc" as objects in the object array 
			//System.out.println("-------------BusbarList----------------");	
			for (int i = 0; i < busbarList.getLength(); i++) {				
				Node theNode = busbarList.item(i);		
				BusbarList.add(extractMethod(theNode));									
				}							
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	//method to extract data and store it into an new base_voltage object
	public static Busbar extractMethod (Node node){				
		
		//Searching for values with the method parameter in the class ReadNode		
		String rdfID = ReadNode.parameter(node,"rdf:ID");
		String name = ReadNode.parameter(node,"cim:IdentifiedObject.name");	
		String eq_con_rdfID = ReadNode.parameter(node,"cim:Equipment.EquipmentContainer").substring(1);	
					
		//print
		//System.out.println("rdfID: " + rdfID + "; Name: " + name + "; Eq_con_rdfID: " + eq_con_rdfID );		
		
		//create an object and set values
		Busbar obj = new Busbar();		
		obj.setRdfID(rdfID);
		obj.setName(name);				
		obj.setEq_con_rdfID(eq_con_rdfID);		
		
		return obj;
	}
}
