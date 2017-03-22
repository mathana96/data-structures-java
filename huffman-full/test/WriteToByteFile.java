/**
 * GUAVA I/O: http://www.baeldung.com/guava-write-to-file-read-from-file
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;


import org.junit.Test;



public class WriteToByteFile
{

	@Test
	public void testByteArray() throws Exception
	{
		FileOutputStream fout = new FileOutputStream("testoutputbyte.dat"); 
		String b = "1000111";
		String b2 = "1000111";

		byte a = Byte.parseByte(b, 2);
		byte a2 = Byte.parseByte(b2, 2);

		byte[] array = { a, a2 };
		System.out.println(array[0]);
		fout.write(array);
		fout.close();
	}

	@Test
	public void testWritingToFile() throws Exception
	{
		FileOutputStream fout = new FileOutputStream("testoutput.dat");
		FileInputStream fin = new FileInputStream("testoutput.dat");
		//		"0101110 0111110 1111011 0110111 1100010 0001111 0001000 01"
		//		"10111 11111 111101 11011 1100010 0001111 0001000 01"
		String b = "010111001111101111011011011111000100001111000100001";

		byte[] array = new byte[(b.length()/7)+1];

		//		System.out.println(b.length());

		int i = 0;
		int j = 0;
		int k = 0;
		while ( (b.length() - i) >= 7 ) 
		{
			j = i + 7; 
			String chunk = b.substring(i, j);
			System.out.println("Chunk " + chunk);
			Byte byteChuck = Byte.parseByte(chunk, 2);
			System.out.println("Byte Chuck " + byteChuck);

			//			Need to add leading zeros to lone below
			System.out.println("Byte to String " + Integer.toBinaryString(byteChuck));
			array[k] = byteChuck;
			System.out.println("Array " + array[k]);
			//			System.out.println(array[1]);
			i = j;
			k++;
		}

		//		int remainingNumOfBits = b.length() - i;

		String remainingChunk = b.substring( i, b.length() );
		String chunk = "0";
		System.out.println(remainingChunk);
		while (chunk.length() < (7 - remainingChunk.length()) )
		{
			chunk += "0";
		}
		chunk += remainingChunk;

		System.out.println("Remaining chunk " + chunk);
		Byte byteChuck = Byte.parseByte(chunk, 2);
		array[k] = byteChuck;


		System.out.println(i + " " + j);


		fout.write(array);
		fout.close();

	}

	@Test
	public void testReadingFromFile() throws IOException
	{
		Path path = Paths.get("testoutput.dat");
		byte[] data = java.nio.file.Files.readAllBytes(path);


		for (int i=0; i<data.length; i++)
		{
			String theByte = Integer.toBinaryString(data[i]);
			String correctedByte = "0";
			if (theByte.length() < 7)
			{
				while (correctedByte.length() < (7 - theByte.length()))
				{
					correctedByte += "0";	
				}
				correctedByte += theByte;
				System.out.println(correctedByte);
			}
			else
			{
				System.out.println(theByte);
			}

		}
	}

}
