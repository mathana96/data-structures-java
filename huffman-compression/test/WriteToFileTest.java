import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import utils.ReadFromFile;
import utils.WriteToFile;

public class WriteToFileTest
{

	@Test
	public void test() throws Exception
	{
		String huffman = "0110001101011111000101110111100001101";
		WriteToFile writer = new WriteToFile(huffman);
		
		ReadFromFile reader = new ReadFromFile("././data/huffman-output.txt");
		assertEquals(huffman, reader.getFileContent());
	}

}
