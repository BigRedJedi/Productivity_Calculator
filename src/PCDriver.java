// Name: Kyle Hampton
// Class: CSCI1620-003
// Assignment #: 8
// Due Date: 1 May 2017
//
// Honor Pledge: On my honor as a student of the University of
// Nebraska at Omaha, I have neither given nor received unauthorized 
// help on this programming assignment.
//
// NAME: Kyle Hampton
// NU ID: 571
// EMAIL: kthampton@unomaha.edu
//
// Partners: NONE
// 
// This program creates and implements the driver file of the 
// implemented productivity calculator

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PCDriver
{
	   // Method name: Main
	   // Parameters: None
	   // Return value(s): None
	   // Description: Create an instance of the productivity
	   //              calculator frame to implement
	public static void main(String[] args) 
	{
        // Create instance of frame called myFrame and set the close 
		// operation, the size, and set the frame to visible
        PCJFrame myFrame = new PCJFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(450, 350);
        myFrame.setVisible(true);
        
        } // end of main method
} // end of class ProductivityCalculatorDriver