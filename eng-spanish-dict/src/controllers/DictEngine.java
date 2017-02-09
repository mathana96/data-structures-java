package controllers;

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
//		Preconditions.checkNotNull(spanish);
		for (Pair pair: pairs)
		{
			if (pair.getSpanish().equals(spanish))
				return pair.getEnglish();
		}
		return "not found";
	}
	public void addPair(String spanish, String english)
	{
		Pair pair = new Pair(spanish, english);
		pairs.add(pair);
		siftUp();
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
