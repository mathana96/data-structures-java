package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile
{

	public WriteToFile(String huffman) throws IOException
	{
		 File file = new File("././data/huffman-output.txt");
		 
		 FileWriter writer = new FileWriter(file); 
		 
		 
		 writer.write(huffman); 
     writer.flush();
     writer.close();
     
	}
}
