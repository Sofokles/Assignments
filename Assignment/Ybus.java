package Assignment;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ybus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] Zmatrix = new double[5][2]; 
		
		//resistance
		double r1 =  1;
		double r2 = 1;
		double r3  = 1;
		double r4 = 1;
		double r5 = 1;
		
		double x1 =  1;
		double x2 = 1;
		double x3  = 1;
		double x4 = 1;
		double x5 = 1;
		
					
		// creating complex impedience
		Complex Z0 = new Complex(1,0);
		Complex Z1 = Complex(r1,x1);
		Complex Z2 = new Complex(r2,x2);
		Complex Z3 = new Complex(r3,x3);
		
		// calculate admittance of each line
		Complex Y_13 = Z0.divides(Z1);
		Complex Y_12 = Z0.divides(Z2);
		Complex Y_23 = Z0.divides(Z3);
		
		// calculate the elements of Ybus matrix
		Complex Y11 = Y_13.plus(Y_12);
		Complex Y12 = zero.minus(Y_12);
		Complex Y13 = zero.minus(Y_13);
		Complex Y21 = zero.minus(Y_12);
		Complex Y22 = Y_12.plus(Y_23);
		Complex Y23 = zero.minus(Y_23);
		Complex Y31 = zero.minus(Y_13);
		Complex Y32 = zero.minus(Y_23);
		Complex Y33 = Y_13.plus(Y_23);
		
		
		

	}
	  // AcLineSigment return method which returns value of r and x.
	   public static Double[]  AcLineSigment (Node node){
		  
			Double[] R_and_X = new Double[2];
			R_and_X[0]  = Double.parseDouble(ReadData.parameter(node, "cim:ACLineSegment.r"));
			R_and_X [1] = Double.parseDouble(ReadData.parameter(node, "cim:ACLineSegment.x"));
			
			return R_and_X;
	        }
	   
	   // Ybus is non-return method which Y-matrix of 3 bus system
	   
	   public static void Ybus( Double[][] impedance ){
		   
		   // Seperate r and x of each line
		    double r1 = impedance[0][0];
			double x1 = impedance[0][1]; 
			double r2 = impedance[1][0]; 
			double x2 = impedance[1][1];
			double r3 = impedance[2][0]; 
			double x3 = impedance[2][1];
			
			// pasess the values through the Complex class and calculate line impedances
			Complex zero = new Complex(0,0);
			Complex Z0 = new Complex(1,0);
			Complex Z1 = new Complex(r1,x1);
			Complex Z2 = new Complex(r2,x2);
			Complex Z3 = new Complex(r3,x3);
			
			// calculate admittance of each line
			Complex Y_13 = Z0.divides(Z1);
			Complex Y_12 = Z0.divides(Z2);
			Complex Y_23 = Z0.divides(Z3);
			
			// calculate the elements of Ybus matrix
			Complex Y11 = Y_13.plus(Y_12);
			Complex Y12 = zero.minus(Y_12);
			Complex Y13 = zero.minus(Y_13);
			Complex Y21 = zero.minus(Y_12);
			Complex Y22 = Y_12.plus(Y_23);
			Complex Y23 = zero.minus(Y_23);
			Complex Y31 = zero.minus(Y_13);
			Complex Y32 = zero.minus(Y_23);
			Complex Y33 = Y_13.plus(Y_23);
			
			JFrame frame = new JFrame();
			
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    Object rowData[][] = { { Y11, Y12, Y13 },
		    					   { Y21, Y22, Y23 } ,
		    					   { Y31, Y32, Y33 } ,
		        };
		   
		    Object columnNames[] = { "Y_i1", "Y_i2", "Y_i3" };
		    
		    JTable table = new JTable(rowData, columnNames);
		    JScrollPane scrollPane = new JScrollPane(table);
		    frame.add(scrollPane, BorderLayout.CENTER);
		    frame.setSize(1024, 500);
		    frame.setTitle("Y-matrix");
		    frame.setVisible(true);
														   
	   }         
}