package Assignment;

import java.util.ArrayList;

public class Main_file {

	public static void main(String[] args) {		
		
		
		
		//----------------------------------------------------------------------------------------------------
		//TASK 1 and 2, IMPORT THE XML-CIM DATA AND STORE IT IN SQL
		//-------------------------------------------------------------------------------
		//I ALSO STORE THE DATA IN OBJECTS TO BE USED IN THE YBUS CALCULATION IN TASK 3
		//MOREOVER, I STORE TERMINAL, BUSBAR and AC-LINE DATA IN OBJECTS TO PERFORM TASK 3
			//Base Voltage
			ArrayList<Base_voltage> Base_voltageList = new ArrayList<Base_voltage>(); //creating an arraylist of objects					
			Base_voltage.base_voltage(Base_voltageList);	//calling the method							
			
			//Substation
	 		ArrayList<Substation> SubstationList = new ArrayList<Substation>(); //creating an arraylist of objects					
			Substation.substation(SubstationList);	//calling the method
			
			//Voltage Level
			ArrayList<Voltage_level> Voltage_levelList = new ArrayList<Voltage_level>(); //creating an arraylist of objects					
			Voltage_level.voltage_level(Voltage_levelList);	//calling the method				
			
			//Generating Unit
			ArrayList<GeneratingUnit> GeneratingUnitList = new ArrayList<GeneratingUnit>(); //creating an arraylist of objects					
			GeneratingUnit.generatingUnit(GeneratingUnitList);	//calling the method				
			
			//Regulating Control
			ArrayList<RegulatingControl> RegulatingControlList = new ArrayList<RegulatingControl>(); //creating an arraylist of objects					
			RegulatingControl.regulatingControl(RegulatingControlList);	//calling the method
			
			//SynchronousMachine
			ArrayList<SynchronousMachine> SynchronousMachineList = new ArrayList<SynchronousMachine>(); //creating an arraylist of objects					
			SynchronousMachine.synchronousMachine(SynchronousMachineList);	//calling the method				
							
			//Power Transformer
			ArrayList<PowerTransformer> PowerTransformerList = new ArrayList<PowerTransformer>(); //creating an arraylist of objects					
			PowerTransformerList = PowerTransformer.powerTransformer(PowerTransformerList);	//calling the method
							
			//Energy Consumer
			ArrayList<EnergyConsumer> EnergyConsumerList = new ArrayList<EnergyConsumer>(); //creating an arraylist of objects					
			EnergyConsumer.energyConsumer(EnergyConsumerList);	//calling the method				
			
			//PowerTransformerEnd
			ArrayList<PowerTransformerEnd> PowerTransformerEndList = new ArrayList<PowerTransformerEnd>(); //creating an arraylist of objects					
			PowerTransformerEnd.powerTransformerEnd(PowerTransformerEndList);	//calling the method				
			
			//Breaker
			ArrayList<Breaker> BreakerList = new ArrayList<Breaker>(); //creating an arraylist of objects					
			Breaker.breaker(BreakerList);	//calling the method				
					
			//RatioTapChangerList
			ArrayList<RatioTapChanger> RatioTapChangerList = new ArrayList<RatioTapChanger>(); //creating an arraylist of objects					
			RatioTapChanger.ratioTapChanger(RatioTapChangerList);	//calling the method	
			
			//EXTRA TO PERFORM TASK 3
			//ACLineSegment
			ArrayList<ACLineSegment> ACLineSegmentList = new ArrayList<ACLineSegment>(); //creating an arraylist of objects					
			ACLineSegment.aCLineSegment(ACLineSegmentList);	//calling the method
		
			//Busbar
			ArrayList<Busbar> BusbarList = new ArrayList<Busbar>(); //creating an arraylist of objects					
			Busbar.busbar(BusbarList);	//calling the method
											
			//Terminal
			ArrayList<Terminal> TerminalList = new ArrayList<Terminal>(); //creating an arraylist of objects					
			Terminal.terminal(TerminalList);	//calling the method
		//---------------------------------------------------------------------------------------------------------
		
		//----------------------------------------------------------------------------------------------------
		//TASK 3, CALCULATE THE YBUS
		//Creating a Bus2Bus arraylist
			//ConnectedBus2BusList
			ArrayList<ConnectedBus2Bus> ConnectedBus2BusList = new ArrayList<ConnectedBus2Bus>(); //creating an arraylist of objects
			
			//Call the method and recive ConnectedBus2BusList with all connections
			ConnectedBus2BusList = findConnections(TerminalList, BreakerList, BusbarList, PowerTransformerList, PowerTransformerEndList, ACLineSegmentList,
					SynchronousMachineList, Base_voltageList);
			
			//Ybus calculation using the ConnectedBus2BusList and print teh bus
			Ybus.ybus(ConnectedBus2BusList);
	}
	
	
	//This method creates three arrays with objects of connections. The method needs some lists that are input as you can see. 
	//1. ConnectedBus2TrafoList is the connection between busses and trafos
	//2. ConnectedBus2LineList is the connection between busses and Lines
	//3. The two lists above are used to create the ConnectedBus2BusList with all conenctions between bussus end with the corresponding trafos/lines connected to them
	public static ArrayList<ConnectedBus2Bus> findConnections(ArrayList<Terminal>TerminalList, ArrayList<Breaker>BreakerList, ArrayList<Busbar>BusbarList, 
			ArrayList<PowerTransformer>PowerTransformerList, ArrayList<PowerTransformerEnd>PowerTransformerEndList, ArrayList<ACLineSegment>ACLineSegmentList,
			ArrayList<SynchronousMachine>SynchronousMachineList, ArrayList<Base_voltage>Base_voltageList) {
		
		//ConnectionBus2Trafo
		ArrayList<ConnectedBus2Trafo> ConnectedBus2TrafoList = new ArrayList<ConnectedBus2Trafo>(); //creating an arraylist of objects
		
		//ConnectionBus2Line
		ArrayList<ConnectedBus2Line> ConnectedBus2LineList = new ArrayList<ConnectedBus2Line>(); //creating an arraylist of objects
				
		//find connections between Bus2Trafo, Bus2Bus or Bus2Line
		for (int i = 0; i <BusbarList.size()		; i++) {	//BusbarList.size()										
			
			String Rdf_bus = BusbarList.get(i).getRdfID();
			String Eq_con_rdfID_bus = BusbarList.get(i).getEq_con_rdfID();		
			
			//find the terminal corresponding to the bus
			for (int i2 = 0; i2 <TerminalList.size(); i2++) {											
				
				String Terminal_ConductingEquipment_t1 = TerminalList.get(i2).getTerminal_ConductingEquipment();
				
				//find the terminals con_eq that is the same as for the bus
				if (Terminal_ConductingEquipment_t1.equals(Rdf_bus)){
					String ConnectivityNode_t1 = TerminalList.get(i2).getConnectivityNode();
					
					//-----------Direct connection to a trafo, //find connections between Bus2Trafo
					//Find the terminal that has the same connectivity node but not the same conducting equipment. 
					for (int i3 = 0; i3 <TerminalList.size(); i3++) {								
						
						String ConnectivityNode_t3 = TerminalList.get(i3).getConnectivityNode();	
						String Terminal_ConductingEquipment_t3 = TerminalList.get(i3).getTerminal_ConductingEquipment();	
						
						//if eq_con is not the same as the breaker but it is the same connectivity node
						if (ConnectivityNode_t1.equals(ConnectivityNode_t3) && !Terminal_ConductingEquipment_t3.equals(Terminal_ConductingEquipment_t1)) {
						
							//Store these busnumbers and connecting trafonumbers in an arraylist as bus2trafo objects 
							for (int i4 = 0; i4 <PowerTransformerList.size(); i4++) {								
								
								String RfdID_p1 = PowerTransformerList.get(i4).getRdfID();	
								
								//if the found con_eq is a transformer
								if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
									
									//create an object
									ConnectedBus2Trafo obj = new ConnectedBus2Trafo();
									obj.setBusNum(i);
									obj.setTrafoNum(i4);
									
									//store object in list
									ConnectedBus2TrafoList.add(obj);
								}
							}
						}
					}
					
					//----------------Directly connected to a breaker
					//Find the breaker connected to the bus and later, //find connections between Bus2Trafo, Bus2Bus or Bus2Line
					for (int i3 = 0; i3 <BreakerList.size(); i3++) {
						String Eq_con_rdfID_br = BreakerList.get(i3).getEq_con_rdfID();						
													
						//if eq_con is the same for breaker and bus	
						if (Eq_con_rdfID_br.equals(Eq_con_rdfID_bus)){
																	
							Boolean state_br = BreakerList.get(i3).getState();
							
							//if breaker is closed
							if (state_br.equals(false)){
								String RdfID_br = BreakerList.get(i3).getRdfID();
								
								//find the terminal that corresponding to this breaker get next connection
								for (int i4 = 0; i4 <TerminalList.size(); i4++) {								
									
									String Terminal_ConductingEquipment_t = TerminalList.get(i4).getTerminal_ConductingEquipment();	
									
									//find the terminals with the same eq_con as the breaker 
									if (Terminal_ConductingEquipment_t.equals(RdfID_br)){
										
										String ConnectivityNode_t2 = TerminalList.get(i4).getConnectivityNode();
										
										//Choose the breaker side terminal that is not connected to the investigated bus by its connectivity node.									
										if (!ConnectivityNode_t2.equals(ConnectivityNode_t1)){
																																						
											//Find the terminal that has the same connectivity node but not the same conducting equipment. 
											for (int i5 = 0; i5 <TerminalList.size(); i5++) {								
												
												String ConnectivityNode_t3 = TerminalList.get(i5).getConnectivityNode();	
												String Terminal_ConductingEquipment_t3 = TerminalList.get(i5).getTerminal_ConductingEquipment();	
												
												//if eq_con is not the same as the breaker but it is the same connectivity node
												if (!Eq_con_rdfID_br.equals(Terminal_ConductingEquipment_t3) && ConnectivityNode_t2.equals(ConnectivityNode_t3) && !Terminal_ConductingEquipment_t3.equals(Terminal_ConductingEquipment_t)) {
												
													//Store these busnumbers and connecting trafonumbers in an arraylist as bus2trafo objects 
													for (int i6 = 0; i6 <PowerTransformerList.size(); i6++) {								
														
														String RfdID_p1 = PowerTransformerList.get(i6).getRdfID();	
													
														//only if the con_eq is a transformer
														if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
															
															//create an object
															ConnectedBus2Trafo obj = new ConnectedBus2Trafo();
															obj.setBusNum(i);
															obj.setTrafoNum(i6);
															
															//store object in list
															ConnectedBus2TrafoList.add(obj);
														}
													}
													
													//Store these busnumbers and connecting lines in an arraylist as bus2line objects
													for (int i6 = 0; i6 <ACLineSegmentList.size(); i6++) {								
														
														String RfdID_p1 = ACLineSegmentList.get(i6).getRdfID();	
														
														//if the founded con_eq is a line
														if (Terminal_ConductingEquipment_t3.equals(RfdID_p1)){
															
															//create an object
															ConnectedBus2Line obj = new ConnectedBus2Line();
															obj.setBusNum(i);
															obj.setLineNum(i6);
															
															//store object in list
															ConnectedBus2LineList.add(obj);
														}
													}
												}
											}													
										}
									}
								}	
							}		
						}	
					}
				}	
			}
		}
		//DO YOU WANT TO PRINT TRAFO CONNECTIONS?
		int p1=0;//if p1=1 it prints...
		if (p1==1){
			//print ConnectedBus2TrafoList
			for (int i = 0; i <ConnectedBus2TrafoList.size(); i++) {								
				
				int busNum = ConnectedBus2TrafoList.get(i).getBusNum();	
				int trafoNum = ConnectedBus2TrafoList.get(i).getTrafoNum();
				int pp = ConnectedBus2TrafoList.size();
				
				System.out.println("busNum: " + busNum + "; trafoNum: " + trafoNum + "; size" + pp);
			}
		
			//print ConnectedBus2LineList
			for (int i = 0; i <ConnectedBus2LineList.size(); i++) {								
			
				int busNum = ConnectedBus2LineList.get(i).getBusNum();	
				int lineNum = ConnectedBus2LineList.get(i).getLineNum();
				int pp = ConnectedBus2LineList.size();
				
				String name = BusbarList.get(busNum).getName();
				String name2 = ACLineSegmentList.get(lineNum).getName();
				
				System.out.println("busNum: " + busNum + name + "; lineNum: " + lineNum + name2 + "; size" + pp);	
			}
		}
	
	//ConnectedBus2BusList
	ArrayList<ConnectedBus2Bus> ConnectedBus2BusList = new ArrayList<ConnectedBus2Bus>(); //creating an arraylist of objects
			
	//creating a list of objects bus2bus with lines and trafo connections and also their data.
	for (int i = 0; i <5-1; i++) {//all busses -1
		for (int i2 = i+1; i2 <5; i2++) {//all busses starts +1
			//create an object
			ConnectedBus2Bus obj = new ConnectedBus2Bus();
			ConnectedBus2BusList.add(obj);
			
			//numbers
			obj.setBusNum(i);
			obj.setBus2Num(i2);
			
			//base Power
			double ratedS = SynchronousMachineList.get(0).getRatedS();
			obj.setBase_S(ratedS);	
			//bus2line						
			for (int i3 = 0; i3 <ConnectedBus2LineList.size()-1; i3++) {
				for (int i4 = i3+1; i4 <ConnectedBus2LineList.size(); i4++) {
					int conBusL1 = ConnectedBus2LineList.get(i3).getBusNum();	
					int conBusL2 = ConnectedBus2LineList.get(i4).getBusNum();
					int conLineL1 = ConnectedBus2LineList.get(i3).getLineNum();	
					int conLineL2 = ConnectedBus2LineList.get(i4).getLineNum();
						
					//Test if busses are correct and same Line connection
					if (conBusL1==i && conBusL2==i2 && conLineL1==conLineL2){									
						int conLine = ConnectedBus2LineList.get(i3).getLineNum();									
						int testLine = obj.getLineNum();
						
						//set Line data r and x
						double r = ACLineSegmentList.get(conLine).getR();
						double x = ACLineSegmentList.get(conLine).getX();
						
						//set base_volt, finds the base_volt value and stores it
						String BaseVolt_rdfID = ACLineSegmentList.get(conLine).getBase_volt_rdfID();
						for (int i5 = 0; i5 <Base_voltageList.size(); i5++) {	
							String BaseVolt_rdfID_2 = Base_voltageList.get(i5).getRdfID();	
							if (BaseVolt_rdfID_2.equals(BaseVolt_rdfID)){
								double BaseVolt_nom = Base_voltageList.get(i5).getNom_val();	
								obj.setBase_volt(BaseVolt_nom);
							}
						}			
						if (testLine == -1){//if no line-connection all-ready exists
							obj.setLineNum(conLine);
							obj.setR_line1(r);
							obj.setX_line1(x);
							//System.out.println("conBusL1: " + conBusL1 + "; conBusL2: " + conBusL2 + "; conLineL1: " +conLineL1 + "; conLineL2 : " + conLineL2 );	
							testLine=conLine;
						}	else{//if there all-ready is a line-connection
							obj.setLine2Num(conLine);
							obj.setR_line2(r);
							obj.setX_line2(x);
						}
					}
				}
			}
			
			//bus2trafo					
			for (int i3 = 0; i3 <ConnectedBus2TrafoList.size()-1; i3++) {
				for (int i4 = i3+1; i4 <ConnectedBus2TrafoList.size(); i4++) {
					int conBusT1 = ConnectedBus2TrafoList.get(i3).getBusNum();	
					int conBusT2 = ConnectedBus2TrafoList.get(i4).getBusNum();
					int conTrafoT1 = ConnectedBus2TrafoList.get(i3).getTrafoNum();	
					int conTrafoT2 = ConnectedBus2TrafoList.get(i4).getTrafoNum();
					//Test if busses arer correct and same Line connection
					if (conBusT1==i && conBusT2==i2 && conTrafoT1==conTrafoT2){
						int conTrafo = ConnectedBus2TrafoList.get(i3).getTrafoNum();	
						obj.setTrafoNum(conTrafo);
						//System.out.println("conBusT1: " + conBusT1 + "; conBusT2: " + conBusT2 + "; conTrafoT1: " +conTrafoT1 + "; conTrafoT2: " + conTrafoT2 );	
						
						//set base_volt, finds the base_volt value and stores it
						String Trafo_name = PowerTransformerList.get(conTrafo).getName();
						for (int i5 = 0; i5 <PowerTransformerEndList.size(); i5++) {	
							String PowerTransformerEnd_name = PowerTransformerEndList.get(i5).getName();	
							if (PowerTransformerEnd_name.equals(Trafo_name)){
								double r = PowerTransformerEndList.get(i5).getTransformer_r();	
								double x = PowerTransformerEndList.get(i5).getTransformer_x();	
								if (r>0){
									obj.setR_trafo(r);
									obj.setX_trafo(x);
									String BaseVolt_rdfID = PowerTransformerEndList.get(i5).getBase_volt_rdfID();
									
									for (int i6 = 0; i6 <Base_voltageList.size(); i6++) {	
										String BaseVolt_rdfID_2 = Base_voltageList.get(i6).getRdfID();	
										if (BaseVolt_rdfID_2.equals(BaseVolt_rdfID)){
											double BaseVolt_nom = Base_voltageList.get(i6).getNom_val();	
											obj.setBase_volt(BaseVolt_nom);
										}
									}	
								}
							}
						}	
					}
				}
			}
		}
		
		
	}
		
	//print
	System.out.println("------------Connections Between Busses--------------------");	
	System.out.println("	Bus1	Bus2	Line1	Line2	Trafo	RatedS	RatedU");	//	R_trafo	    	X_trafo		R_Line1	X_Line1		R_Line2	X_Line2 
	for (int i = 0; i <ConnectedBus2BusList.size(); i++) {								
		
		int busNum = ConnectedBus2BusList.get(i).getBusNum();	
		int bus2Num = ConnectedBus2BusList.get(i).getBus2Num();
		int conLine = ConnectedBus2BusList.get(i).getLineNum();
		int conLine2= ConnectedBus2BusList.get(i).getLine2Num();
		int conTrafo= ConnectedBus2BusList.get(i).getTrafoNum();	
		double Base_S = ConnectedBus2BusList.get(i).getBase_S();
		double Base_V = ConnectedBus2BusList.get(i).getBase_volt();	
		
		//only print the the connections..
		int sum = conLine + conLine2 + conTrafo;
		if (sum>-3){
		
			System.out.println("	" + busNum + "	" + bus2Num + "	" + conLine + "	" + conLine2 + "	" + conTrafo + "	" + Base_S + "	" + Base_V );	
		}
	}
	return ConnectedBus2BusList;
	}
}
