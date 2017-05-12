package Assignment;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


//This class calculate the Ybus with the ConnectedBus2BusList2 arraylist
public class Ybus3 {
	
	//calculating Ybus using ConnectedBus2BusList2, PowerTransformerList, ACLineSegmentList
	public static void ybus3(ArrayList<ConnectedBus2Bus>ConnectedBus2BusList2){		
	
				
				//initalize complex values and store data				
				Complex zero = new Complex(0,0);
				Complex Z0 = new Complex(1,0);		
				Complex [][] data = new Complex [5][5];
				
				//calculate Y-bus--------------
				//int size = ConnectedBus2BusList2.size();
				for (int i = 0; i <ConnectedBus2BusList2.size(); i++) {	
					int bus1 = ConnectedBus2BusList2.get(i).getBusNum();
					int bus2 = ConnectedBus2BusList2.get(i).getBus2Num();
					
					//Set base values					
					double Base_V = ConnectedBus2BusList2.get(i).getBase_volt();
					double Base_S = ConnectedBus2BusList2.get(i).getBase_S();					
					double Z_base = Base_V*Base_V/Base_S;
					
					//calculate admittances Y for all one line connections
					if (ConnectedBus2BusList2.get(i).getLineNum()>-1){
						
						//get data
						double r_line1 = ConnectedBus2BusList2.get(i).getR_Line1();
						double x_line1 = ConnectedBus2BusList2.get(i).getX_Line1();						
						
						//per unit calc
						double r_line1_pu = r_line1/Z_base;
						double x_line1_pu = x_line1/Z_base;
						Complex Z_line1_pu = new Complex(r_line1_pu,x_line1_pu);
						
						//Calculating the element of Ybus matrix
						Complex Y_L1 = zero.minus(Z0.divides(Z_line1_pu));
						
						//store data
						data[bus1][bus2] = Y_L1;
						data[bus2][bus1] = Y_L1;
					}
					//calculate admittances Y for all two line connections
					if (ConnectedBus2BusList2.get(i).getLine2Num()>-1){
						//get data
						double r_line1 = ConnectedBus2BusList2.get(i).getR_Line1();
						double x_line1 = ConnectedBus2BusList2.get(i).getX_Line1();		
						double r_line2 = ConnectedBus2BusList2.get(i).getR_Line2();
						double x_line2 = ConnectedBus2BusList2.get(i).getX_Line2();						
						
						//per unit calc
						double r_line1_pu = r_line1/Z_base;
						double x_line1_pu = x_line1/Z_base;
						Complex Z_line1_pu = new Complex(r_line1_pu,x_line1_pu);
						double r_line2_pu = r_line2/Z_base;
						double x_line2_pu = x_line2/Z_base;
						Complex Z_line2_pu = new Complex(r_line2_pu,x_line2_pu);						
						
						//Calculating the element of Ybus matrix
						Complex Y_L1 = zero.minus(Z0.divides(Z_line1_pu));
						Complex Y_L2 = zero.minus(Z0.divides(Z_line2_pu));
						
						//System.out.println( Y_L1+ "	" + Y_L2 + "	" + r_line2 + "	" + x_line2 + "	" + r_line1 + "	" + x_line1);
						//store data
						data[bus1][bus2] = Y_L2.plus(Y_L1);
						data[bus2][bus1] = Y_L2.plus(Y_L1);
					}
					//calculate admittances Y for all powertransformer connections
					else if (ConnectedBus2BusList2.get(i).getTrafoNum()>-1){
						//get data
						double r_trafo = ConnectedBus2BusList2.get(i).getR_trafo();
						double x_trafo = ConnectedBus2BusList2.get(i).getX_trafo();						
						
						//per unit calc
						double r_trafo_pu = r_trafo/Z_base;
						double x_trafo_pu = x_trafo/Z_base;
						Complex Z_trafo_pu = new Complex(r_trafo_pu,x_trafo_pu);
						
						//Calculating the element of Ybus matrix
						Complex Y_T1 = zero.minus(Z0.divides(Z_trafo_pu));
						
						//store data
						data[bus1][bus2] = Y_T1;
						data[bus2][bus1] = Y_T1;
					}
					//set all others to zero.. 
					else {
						Complex Y_0 = zero;
						data[bus1][bus2] = Y_0;
						data[bus2][bus1] = Y_0;
					}			
				}
				
				//calculate the Yxx, summerize...
				for (int i = 0; i <5; i++) {	
					data[i][i] = zero;
					for (int i2 = 0; i2 <5; i2++) {	
						if (i!=i2){
							data[i][i] = data[i][i].minus(data[i][i2]);
						}
					}
				}
				
				//plott the Y-bus
				JFrame frame = new JFrame();				
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    
			    Object rowData[][] = data;
			    Object columnNames[] = { "Y_i1", "Y_i2", "Y_i3", "Y_i4", "Y_i5", };
			    
			    JTable table = new JTable(rowData, columnNames);
			    JScrollPane scrollPane = new JScrollPane(table);
			    frame.add(scrollPane, BorderLayout.CENTER);
			    frame.setSize(1024, 500);
			    frame.setTitle("Y-bus");
			    frame.setVisible(true);
			}
		      
		}