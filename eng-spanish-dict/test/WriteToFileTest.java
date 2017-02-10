/**
 * @author mathana
 * 
 * Test class for writing to a local file
 */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import models.Pair;
import utils.ParseData;

public class WriteToFileTest
{

	ParseData parseData;
	ArrayList<Pair> pairs = new ArrayList<>();
	
	@Test
	public void testWriteToFile() throws Exception
	{
		parseData = new ParseData();
		readFile();
		System.out.println(pairs.size());
		writeToFile();
		
	}
	
	public void writeToFile() throws IOException
	{
		 File file = new File("././test-data/test-data.txt");
		 String spanish = "spanish999";
		 String english = "english999";
		 
		 FileWriter writer = new FileWriter(file, true); 
		 
		 
		 writer.write("\n" + spanish + "\t" + english); 
     writer.flush();
     writer.close();
     
	}
	
	public void readFile() throws Exception
	{
		pairs = parseData.readFile("././test-data/test-data.txt");
	}

}
