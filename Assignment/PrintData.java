package Assignment;

import org.w3c.dom.Node;

public class PrintData {

	// class called parameter returnes parametervalue
			public static void printData() {
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------EnergyConsumerList----------------");	
				for (int i=0;i<EnergyConsumerList.size();i++) { // Use enhance looping									
					String x = EnergyConsumerList.get(i).getRdfID();
					String n = EnergyConsumerList.get(i).getName();
					String R = EnergyConsumerList.get(i).getP();
					String S = EnergyConsumerList.get(i).getQ();
					String T = EnergyConsumerList.get(i).getEq_con_rdfID();
					
					int q = EnergyConsumerList.size();						
					System.out.println("rdfID: " + x + "; Name: " + n + "; P: " + R + "; Q: " + S + "; Eq_con_rdfID: " + T + "; Quantity: " + q);		
				}	
				
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------PowerTransformerList----------------");	
				for (int i=0;i<PowerTransformerList.size();i++) { // Use enhance looping									
					String x = PowerTransformerList.get(i).getRdfID();
					String n = PowerTransformerList.get(i).getName();
					String R = PowerTransformerList.get(i).getEq_con_rdfID();					
					
					int q = PowerTransformerList.size();						
					System.out.println("rdfID: " + x + "; Name: " + n + "; Eq_con_rdfID: " + R + "; Quantity: " + q);		
				}	
				
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------RegulatingControlList----------------");	
				for (int i=0;i<RegulatingControlList.size();i++) { // Use enhance looping									
					String x = RegulatingControlList.get(i).getRdfID();
					String n = RegulatingControlList.get(i).getName();
					String R = RegulatingControlList.get(i).getTargetValue();					
					
					int q = RegulatingControlList.size();						
					System.out.println("rdfID: " + x + "; Name: " + n + "; TargetValue: " + R + "; Quantity: " + q);		
				}				
					
				
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------SynchronousMachineList----------------");	
				for (int i=0;i<SynchronousMachineList.size();i++) { // Use enhance looping									
					String x = SynchronousMachineList.get(i).getRdfID();
					String n = SynchronousMachineList.get(i).getName();
					String R = SynchronousMachineList.get(i).getP();
					String S = SynchronousMachineList.get(i).getQ();
					String T = SynchronousMachineList.get(i).getEq_con_rdfID();
					
					int q = SynchronousMachineList.size();						
					System.out.println("rdfID: " + x + "; Name: " + n + "; P: " + R + "; Q: " + S + "; Eq_con_rdfID: " + T + "; Quantity: " + q);		
				}	
							
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------GeneratingUnitList----------------");	
				for (int i=0;i<GeneratingUnitList.size();i++) { // Use enhance looping									
					String x = GeneratingUnitList.get(i).getRdfID();
					String n = GeneratingUnitList.get(i).getName();
					String R = GeneratingUnitList.get(i).getMaxP();
					String S = GeneratingUnitList.get(i).getMinP();
					String T = GeneratingUnitList.get(i).getEq_con_rdfID();
					
					int q = GeneratingUnitList.size();					
					System.out.println("rdfID: " + x + "; Name: " + n + "; MaxP: " + R + "; MinP: " + S + "; Eq_con_rdfID: " + T + "; Quantity: " + q);		
				}	
				
					
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------Voltage_levelList----------------");	
				for (int i=0;i<Voltage_levelList.size();i++) { // Use enhance looping									
					String x = Voltage_levelList.get(i).getRdfID();
					String n = Voltage_levelList.get(i).getName();
					String R = Voltage_levelList.get(i).getSub_rdfID();
					String S = Voltage_levelList.get(i).getBase_volt_rdfID();
					
					int q = Voltage_levelList.size();					
					System.out.println("rdfID: " + x + "; Name: " + n + "; Sub_rdfID: " + R + "; Base_volt_rdfID: " + S + "; Quantity: " + q);		
				}	
				
				
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------SubstationList----------------");	
				for (int i=0;i<SubstationList.size();i++) { // Use enhance looping									
					String x = SubstationList.get(i).getRdfID();
					String n = SubstationList.get(i).getName();
					String R = SubstationList.get(i).getReg_rdfID();
					int q = SubstationList.size();					
					System.out.println("rdfID: " + x + "; Name: " + n + "; regRdfID: " + R + "; Quantity: " + q);				
					}			
				
							
				//for-loop using method to print values to see if it is correct
				System.out.println("-------------Base_voltageList----------------");	
				for (int i=0;i<Base_voltageList.size();i++) { // Use enhance looping									
					String x1 = Base_voltageList.get(i).getRdfID();
					double n1 = Base_voltageList.get(i).getNom_val();
					int q = Base_voltageList.size();					
					System.out.println("rdfID: " + Base_voltageList.get(i).getRdfID() + "; nomVal: " + i + "; Quantity: " + q);				
					}
				
			}
}
