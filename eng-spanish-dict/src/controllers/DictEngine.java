/**
 * @author Mathana Sreedarab
 * 
 * The engine class is where the main methods are located. The methods
 * are called upon from the Dictionary class to carry out the two basic 
 * functions of the dictionary, which are search for the English definition of
 * a Spanish word and add a new Spanish-English pair. 
 * 
 * Some of the code references used;
 * 
 * For JSwing: http://alvinalexander.com/java/joptionpane-showinputdialog-examples
 * For writing to file: http://stackoverflow.com/questions/9961292/write-to-text-file-without-overwriting-in-java
 */
package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.base.Preconditions;

import models.Pair;
import utils.ParseData;

public class DictEngine
{
	ParseData parseData = new ParseData();
	String path = "././data/spa-to-eng.txt";
	ArrayList<Pair> pairs = new ArrayList<>();
	
	int index = 0;
	String english = "not found";

	public DictEngine() throws Exception
	{
		pairs = parseData.readFile(path);
//		System.out.println(pairs);
	}
	
	/**
	 * Method to search the english definition of a spaish word
	 * @param spanish
	 * @param index
	 */
	public void searchWord(String spanish, int index)
	{
		int leftChild = 2 * (index) + 1;
		Pair pair = pairs.get(index);

		if (pair.getSpanish().equals(spanish))
		{
			english = pair.getEnglish();
		}
		else
		{
			if ( (pair.getSpanish().compareTo(spanish) > 0) && (leftChild < pairs.size()) )//if worth going down
			{
				index = leftChild;
				searchWord(spanish, index);

				//				Crossing to the right node
				int parent = (leftChild - 1) / 2;
				int rightChild = 2 * (parent) + 2;
				if (rightChild < pairs.size())
				{
					index = rightChild;
					searchWord(spanish, index);
				}
			}

		}
	}
	
	/**
	 * 
	 * @return searched English word
	 */
	public String getSearchedWord()
	{
		String word = english;
		english = "not found";
		return word;
	}
//	public String searchWord(String spanish)
//	{
//		
//		System.out.println("Total items: " + pairs.size());
//		for (int i=0; i<pairs.size(); i++)
//		{
//			Pair pair = pairs.get(i);
//			if (pair.getSpanish().equals(spanish))
//			{
//				System.out.println("Number of travels " + Integer.toString(i));
//				return pair.getEnglish();
//			}
//
//		}
//
//		return "not found";
//	}
	
	/**
	 * Add a new spanihs-english pair to the dictionary
	 * @param spanish
	 * @param english
	 * @return
	 * @throws Exception
	 */
	public String addPair(String spanish, String english) throws Exception
	{
				
		if ( (english.equals("") || spanish.equals("")) )
			return "Null/Empty values not allowed";
		
		Pair pair = new Pair(spanish, english);
		
		
		if (pairs.contains(pair))
		{
			return "The word " + "'" + spanish + "'" + " and it's definition already exists";
		}
		else
		{
			pairs.add(pair);
			siftUp();
			
			writeToFile(spanish, english);
			return "Entry added.\nEnglish word for " + "'" + spanish + "'" + " is " + "'" + english + "'";
		}

	}
	
	public void siftUp()
	{
		
		int k = pairs.size() - 1;
		boolean greater = true;
		
		while (k > 0 && greater)
		{

			int p = (k-1)/2;
			Pair child = pairs.get(k);
			Pair parent = pairs.get(p);
			
			if (child.compareTo(parent) > 0)
			{
				pairs.set(p, child);
				pairs.set(k, parent);
				
				k = p;
			}
			else
				greater = false;	
		}
	}

	public void writeToFile(String spanish, String english) throws IOException
	{
		 File file = new File("././data/spa-to-eng.txt");
		 
		 FileWriter writer = new FileWriter(file, true); 
		 
		 
		 writer.write("\n" + spanish + "\t" + english); 
     writer.flush();
     writer.close();
     
	}
}
