import static org.junit.Assert.*;

import org.junit.Test;

import utils.ReadFromFile;

public class ReadFromFileTest
{

	ReadFromFile readFromFile;
	String path = "././data/textdata.txt";
			
	@Test
	public void test() throws Exception
	{
		readFromFile = new ReadFromFile(path);
		System.out.println(readFromFile.getFileContent());
	}

}
