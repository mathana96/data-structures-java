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

}
