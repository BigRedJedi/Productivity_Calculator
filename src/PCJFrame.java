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
// This program creates and implements a productivity calculator to 
// estimate quality work hours using time and intensity of focus

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.ArrayList;

public class PCJFrame extends JFrame 
{
    private JTextField time = null;    // Declare JTextField time
    private JTextField focus = null;   // Declare JTextField focus
    private JTextField score = null;   // Declare JTextField score
    private JTextField totalScore      // Declare totalScore
                                  = null;      
    private JButton compute = null;    // Declare JButton compute
    private JButton reset = null;      // Declare JButton reset
    private JLabel label1 = null;      // Declare JLabel label
    private JLabel label2 = null;      // Declare JLabel label2
    private JLabel label3 = null;      // Declare JLabel label3
    private JLabel label4 = null;      // Declare JLabel label4
    private String[] choices = null;   // Declare array choices
    private JComboBox<String> comboBox // Declare comboBox
                                       = null; 
    private JPanel panel1 = null;      // Declare JPanel panel
    private JPanel panel2 = null;      // Declare JPanel panel2
    private JPanel panel3 = null;      // Declare JPanel panel3
    private JPanel panel4 = null;      // Declare JPanel panel4
    private JPanel panel5 = null;      // Declare JPanel panel5
    private JPanel panel6 = null;      // Declare JPanel panel6
    private String comboOutput = null; // Declare comboOutput
    private String radioOutput = null; // Declare radioOutput
    private JRadioButton button1       // Declare button1
                                 = null; 
    private JRadioButton button2       // Declare button2
                                 = null; 
    private ButtonGroup radioGroup     // Declare ButtonGroup
                                   = null;   
    private ArrayList<Integer> list    // Declare ArrayList list
                                    = null; 
    private int total = 0;             // Declare int total
    private String totalText = null;   // Declare String totalText
    
    // Method name: PCJFrame
    // Parameters: None
    // Return value(s): None
    // Description: Creates and implements the productivity 
    //              calculator
    public PCJFrame()
    {
        // Call to super constructor to set frame title, set the 
    	// flow layout of the frame, and provide introductory text
    	super("Productivity Calculator Home");
    	setLayout(new FlowLayout());
        label1 = new JLabel("Welcome to the Productivity " + 
    	                                            "Calculator!");
        add(label1);
        label2 = new JLabel("Productivity is the product of " +  
                                      "time and focus intensity.");
        add(label2);
        
        // Set choices to values of work, school, and hobby and
        // prompt the user to select one of the three values 
        // and add each to the new panel
        choices = new String[3];
        choices[0] = "Work";
        choices[1] = "School";
        choices[2] = "Hobby";
        label3 = new JLabel("Select an area of productivity:");
        
        // Set choices to comboBox with a default value of work 
        // and create a new call to ComboListener for even 
        // handling, adding each element to a new panel
        comboBox = new JComboBox<String>(choices);
        comboOutput = "Work";
        comboBox.setMaximumRowCount(3);
        comboBox.addItemListener(new ComboListener());
        panel1 = new JPanel();
        panel1.add(label3);
        panel1.add(comboBox);
        add(panel1);
        
        // Create a new label and panel for the radio buttons 
        // and set the default value to minutes
        label3 = new JLabel("Select a time measurement unit:");
        panel2 = new JPanel();
        radioOutput = "Minutes";
        panel2.add(label3);
        radioOutput = "Minutes";
        
        // Create radio buttons, set starting values so one is 
        // selected (true) and add the radio buttons to the 
        // current panel
        button1 = new JRadioButton("Minutes", true);
        button2 = new JRadioButton("Hours", false);
        panel2.add(button1);
        panel2.add(button2);
        add(panel2);

        // Create ButtonGroup and add buttons to the group,
        // this creates a logical association between buttons
        radioGroup = new ButtonGroup();
        radioGroup.add(button1);
        radioGroup.add(button2);
        
        // Add listeners for each radio button
        button1.addItemListener(new RadioListener("Minutes"));
        button2.addItemListener(new RadioListener("Hours"));        

        // Time and focus intensity inputs
        // Create a new label, panel, and text fields to receive 
        // user input on time and focus intensity
        panel3 = new JPanel();
        label4 = new JLabel("Enter Time and Focus Intensity:");
        time = new JTextField("Time", 10); 
        focus = new JTextField("Focus Intensity (1-Low - 10-High)", 17);
        
        // Add the created labels to panels and add the panel 
        // to the frame
        add(label4);
        panel3.add(time);
        panel3.add(focus);
        add(panel3);

        // Create field for output of productivity score and 
        // add the text field to a new panel
        score = new JTextField("Distinct Productivity Score",30);
        score.setEditable(false);
        panel4 = new JPanel();
        panel4.add(score);
        add(panel4);

        // Use list array list to aggregate the calculated 
        // values of productivity scores for the current session, 
        // this calculates the total productivity score, which 
        // is not editable and added to a new panel
        panel5 = new JPanel();
        list = new ArrayList<Integer>();
        totalScore = new JTextField("Total Productivity Score", 30);
        totalScore.setEditable(false);
        panel5.add(totalScore);
        add(panel5);
        
        PCFrameHandler handler = // Declare PCFrameHandler handler
        		                 new PCFrameHandler(); 

        // Create a new button calculate to perform the productivity
        // calculation and add the button to a new panel while using 
        // an action listener
        panel6 = new JPanel();
        compute = new JButton("CALCULATE");
        compute.addActionListener(handler);
        panel6.add(compute);

        // Create a new button reset the productivity calculator 
        // values and add the button to a new panel while using 
        // an action listener
        reset = new JButton("RESET");
        reset.addActionListener(handler);
        panel6.add(reset);
        add(panel6);
    }

    // Using nested inner class for comboBox event handling
    private class ComboListener implements ItemListener
    {
	   // Method name: itemStateChanged
	   // Parameters: None
	   // Return value(s): None
	   // Description: Provides event handling for state changes of
	   //              the comboBox
	   public void itemStateChanged(ItemEvent event)
    	{
    	   // Evaluate if the state of the comboBox changed and
		   // then set the comboBox output to the selected valued
		   if (event.getStateChange() == ItemEvent.SELECTED)
    		{
    			comboOutput = choices[comboBox.getSelectedIndex()];
    		}
    	}
    }
    
    //Using nested inner class for text field input event handling
    private class PCFrameHandler implements ActionListener
    {
 	   // Method name: actionPerformed
 	   // Parameters: ActionEvent event
 	   // Return value(s): None
 	   // Description: Provides event handling for the inputs of
 	   //              the text fields
    	public void actionPerformed(ActionEvent event)
        {
            String out = "";        // Declare String out     
            int testValue =         // Declare int testValue
            		        Integer.parseInt(focus.getText()); 
            
            // Check which component caused the current event 
            // whether it be the calculate or reset buttons
            if (event.getSource() == compute)
            {
                // If the user selected calculate, try the 
            	// following actions
            	try
                {
                	int factor = 0; // Declare int factor
                	
                	// If the test value of focus is outside of 1
                	// and 10, throw a new number format exception
                	if (testValue <= 0 || testValue > 10)
                    {
                    	throw new NumberFormatException();
                    }
                	// If the radio button value is minutes, set 
                	// factor to 60
                	else if (radioOutput.equals("Minutes"))
                    {
                    	factor = 60;
                    }
                    // If the radio button value is hours, set 
                	// factor to 1
                	else if (radioOutput.equals("Hours"))
                    {
                    	factor = 1;
                    }
                	
                    // Set out to the formatted string of the 
                	// calculated distinct productivity score
                	out = String.format("Distinct %s Productivity "
                	                      + "Score using %s is %d", 
                			              comboOutput, radioOutput, 
                			    (Integer.parseInt(time.getText()) * 
                	  Integer.parseInt(focus.getText())) / factor);
                    
                    // Add the current distinct productivity score 
                	// to the list array list to calculate the 
                	// total productivity score aggregating all of 
                	// the distinct productivity scores
                	list.add((Integer.parseInt(time.getText())) * 
                			  (Integer.parseInt(focus.getText())) 
                			                           / factor);
                    
                    // Reset total equal to zero
                	total = 0;
                    
                    // Iterate through the list array list to 
                	// calculate the total productivity score
                	for (int x = 0; x < list.size(); x++)
                    {
                    	total += list.get(x);
                    }
                    
                    // Set totalText to the formatted string for 
                	// total productivity score
                	totalText = String.format("Total Productivity " 
                	                       + "Score is %d", total);
                }
                // Catch and throw any number format exceptions
            	catch (NumberFormatException NFE)
                {
                    // If either input is not a valid integer
                    out = "ERROR";
                }
                
            	// Set score and totalScore fields to output 
            	// the distinct productivity and total productivity 
            	// formatted strings
                score.setText(out);
                totalScore.setText(totalText);
            }
            // If the user selects reset, reset the text field 
            // values to their default values
            else if (event.getSource() == reset)
            {
                // Set text fields to their original values
                time.setText("Time");
                focus.setText("Focus Intensity");
                score.setText("Productivity Score");
                totalScore.setText("Total Productivity Score");
                
                // Empty array list list
                list.clear();
            }
        }
    }
    
    // Using nested inner class for radio button event handling
    private class RadioListener implements ItemListener
    {
        String buttonText = null;  // Declare String buttonText

  	   // Method name: RadioListener
  	   // Parameters: String text
  	   // Return value(s): None
  	   // Description: Set the value of buttonText to the value of 
  	   //              parameter text
        public RadioListener(String text)
        {
            // Set the value of buttonText to the value of 
        	// parameter text
        	buttonText = text;
        }

  	   // Method name: itemStateChanged
  	   // Parameters: ItemEvent event
  	   // Return value(s): None
  	   // Description: Provides event handling for the inputs of
  	   //              the text fields
        public void itemStateChanged(ItemEvent event)
        {
            // Set the radio button output to the selected 
        	// button text
            radioOutput = buttonText;
        }
    }
}