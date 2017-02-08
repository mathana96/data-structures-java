import static org.junit.Assert.*;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.junit.Test;

import models.Pair;

public class ParserTest
{
	
	String dictPath = "././data/spa-to-eng.txt";
	PriorityQueue<Pair> pairs = new PriorityQueue<>();

	@Test
	public void test() throws Exception
	{
		readFile(dictPath);
		System.out.println(pairs);	
//		System.out.println(pairs.peek());
	}

	
	public void readFile(String path) throws Exception
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
			}
			else
			{
	      rawPairs.close();
	      throw new Exception("Invalid token length: "+ tokens.length);
			}
			
		}
		rawPairs.close();	
	}
}
