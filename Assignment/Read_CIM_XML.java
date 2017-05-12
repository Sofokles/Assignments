package Assignment;

//import IO
import java.io.File;

//import parser
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

//import DOM
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Read_CIM_XML {
	public static void main(String[] args) {
	
		try {
			//read EQ file
			File XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			// normalize CIM XML file
			doc.getDocumentElement().normalize();//gör så att vi kan finna errors..? ( which returns root element of the file and then normalize your XML
			//object.)
			
			//read cim:BaseVoltage
			NodeList basvoltList = doc.getElementsByTagName("cim:BaseVoltage"); //lista med substations, är detta då en lista med objekt..?			
			for (int i = 0; i < basvoltList.getLength(); i++) {
				// … use extractNode method
				Node theNode = basvoltList.item(i);			
				extractNode (theNode);
				}	
			//read Substations
			NodeList subList = doc.getElementsByTagName("cim:Substation"); //lista med substations, är detta då en lista med objekt..?			
			for (int i = 0; i < subList.getLength(); i++) {
				// … use extractNode method
				Node theNode = subList.item(i);			
				extractNode (theNode);
				}			
			//read Voltage and save to a NodeList
			NodeList voltList = doc.getElementsByTagName("cim:VoltageLevel");
			for (int i = 0; i < voltList.getLength(); i++) {
				// … use extractNode method
				Node theNode = voltList.item(i);			
				extractNode (theNode);
				}
			
			NodeList genList = doc.getElementsByTagName("cim:SynchronousMachine");
			for (int i = 0; i < genList.getLength(); i++) {
				// … use extractNode method
				Node theNode = genList.item(i);			
				extractNode (theNode);	
				}
			
			int t0 = basvoltList.getLength();
			int t1 = subList.getLength();
			int t2 = voltList.getLength();
			int t3 = genList.getLength();
			System.out.println("Print:" + t0 + t1 + t2 + t3);		
			
			
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void extractNode (Node node){
	// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;		
		element.getElementsByTagName("cim:IdentifiedObject.name");//.item(0).getTextContent;
		String rdfID = element.getAttribute("rdf:ID");
		String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
		String nom_val = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent();
		
		//System.out.println(element.getElementsByTagName("cim:IdentifiedObject.name"));
		System.out.println(rdfID);
		System.out.println(name);
		System.out.println(nom_val);
		//can be used to read specific attribute of XML node.
	}
}