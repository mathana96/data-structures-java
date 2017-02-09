package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import models.Pair;

public class ParseData
{
	ArrayList<Pair> pairs = new ArrayList<>();
//	String dictPath = "././data/spa-to-eng.txt";
	
	public ArrayList<Pair> readFile(String path) throws Exception
	{
		Scanner rawPairs = null;
		try
		{
			File file = new File(path);
			rawPairs = new Scanner(file);
			
			
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		while (rawPairs.hasNextLine())
		{
			String[] tokens = rawPairs.nextLine().replaceAll("\uFFFD", "").trim().split("\\t");
			
			if (tokens.length == 2)
			{
				Pair pair = new Pair(tokens[0], tokens[1]);
				pairs.add(pair);
				siftUp();
			}
			else
			{
	      rawPairs.close();
	      throw new Exception("Invalid token length: "+ tokens.length);
			}
			
		}
		rawPairs.close();	
		return pairs;
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

}
