import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.junit.Test;

public class ParserTest
{
	
	String dictPath = "././data/spa-to-eng.txt";

	@Test
	public void test() throws Exception
	{
		readFile(dictPath);
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
			System.out.println("Spanish : " + tokens[0] + " | English : " + tokens[1]);			
		}
		rawPairs.close();
	}
}
