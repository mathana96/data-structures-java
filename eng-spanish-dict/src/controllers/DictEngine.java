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
	String path = "././data/spa-to-eng.txt";
	ArrayList<Pair> pairs = new ArrayList<>();
	ParseData parseData = new ParseData();
	
	
	public DictEngine() throws Exception
	{
		pairs = parseData.readFile(path);
	}
	public String searchWord(String spanish)
	{
		for (Pair pair: pairs)
		{
			if (pair.getSpanish().equals(spanish))
				return pair.getEnglish();
			
		}
		return "not found";
	}
	
	public String addPair(String spanish, String english) throws Exception
	{
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
