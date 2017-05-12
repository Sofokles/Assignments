package Assignment;

public class Main_file {

	public static void main(String[] args) {		
				
		
				//Base Voltage								
				Base_voltage.base_voltage();	//calling the method			
				
				//Substation									
				Substation.substation();	//calling the method
				
				//Voltage Level									
				Voltage_level.voltage_level();	//calling the method
				
				//Generating Unit								
				GeneratingUnit.generatingUnit();	//calling the method
				
				//Regulating Control								
				RegulatingControl.regulatingControl();	//calling the method
				
				//SynchronousMachine						
				SynchronousMachine.synchronousMachine();	//calling the method				
				
				//Power Transformer								
				PowerTransformer.powerTransformer();	//calling the method
				
				//Energy Consumer								
				EnergyConsumer.energyConsumer();	//calling the method
				
				//PowerTransformerEnd
				PowerTransformerEnd.powerTransformerEnd();	//calling the method
				
				//Breaker								
				Breaker.breaker();	//calling the method
				
				//RatioTapChanger
				RatioTapChanger.ratioTapChanger();	//calling the method
				
				//ACLineSegment 
				ACLineSegment.aCLineSegment ();	//calling the method
				
							
				//try{
				//	Connection conn1 = (Connection) Connectingdatabase.makeConnection();
				//}
				//catch(Exception e){System.out.println(e);}
				//try{
				//Connection conn1 = (Connection) Connectingdatabase.makeConnection();
				
				//String query = "insert into synchronousMachine values (?)";
				//PreparedStatement preparedStmt = conn1.prepareStatement(query);
				//preparedStmt.setString(1, "erttre");						
				//preparedStmt.execute();
				//}
				//catch(Exception e){System.out.println(e);}
				
				
				try{
					FindData.datamethod();
				}
				catch(Exception e){System.out.println(e);}			
				
				
	
				
	}

}
