/**
 * @author Mathana Sreedaran
 * 
 * This class holds the main method and the user interface methods
 * 
 * Tutorial from http://alvinalexander.com/java/joptionpane-showinputdialog-examples
 */
package controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dictionary
{
	public static final String[] options = {"Search/Translate", "Add new pair"};
	public static void main(String[] args) throws Exception
	{
		DictEngine engine = new DictEngine();
		JFrame frame = new JFrame();

		String option = (String) JOptionPane.showInputDialog(frame, "Welcome to the Spanish-English Dictionary.\nSelect an option", "MNS" ,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (option != null)
		{
			switch(option)
			{
			case "Search/Translate":
				String spanish = JOptionPane.showInputDialog(frame, "Enter spanish word", "chica"); 
				engine.searchWord(spanish, 0);
				String english = engine.getSearchedWord();
				JOptionPane.showMessageDialog(frame, "English word for " + "'" + spanish + "'" + " is " + "'" + english + "'");
				break;

			case "Add new pair":
				String spaEntry = JOptionPane.showInputDialog(frame, "Enter spanish word"); 
				String engEntry = JOptionPane.showInputDialog(frame, "Enter english definition"); 
				String addOutput = engine.addPair(spaEntry, engEntry);
				JOptionPane.showMessageDialog(frame, addOutput);
				break;		
			}
		}
		System.exit(0);
	}





}