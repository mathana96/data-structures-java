/**
 * GUAVA I/O: http://www.baeldung.com/guava-write-to-file-read-from-file
 */

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Files;

import org.junit.Test;



public class WriteToByteFile
{




	//	@Test
	public void test() throws IOException
	{
		String expectedValue = "Hello world";
		File file = new File("test.txt");
		Files.write(expectedValue, file, Charsets.UTF_8);
		String result = Files.toString(file, Charsets.UTF_8);
		assertEquals(expectedValue, result);
	}

	//	@Test
	public void test2() throws IOException
	{
		String test;

		File file = new File("testoutput.dat");

		FileOutputStream outputStream = new FileOutputStream(file);

		//		FileWriter writer = new FileWriter(file); 

		Integer out = 12345678;


		byte[] bytes = new byte[100];

		//		bytes[0] = out;

		outputStream.write(bytes);

		outputStream.close();

		//		writer.write(bytes);
		//		for (int i=0; i<test.length()-1; i++)
		//		{
		//			out = Integer.parseInt(test.substring(i, i+1));
		//			writer.write(out); 
		//		}
		//		Integer out = Integer.parseInt(test, 10);

		//		writer.flush();
		//		writer.close();
		System.out.println("Write to file complete");
	}

	//	@Test
	public void test3() throws IOException
	{
		FileOutputStream fout = new FileOutputStream("testoutput.dat");    
		FileInputStream in = new FileInputStream("testoutput.dat");

		String b = "1111111";

		byte[] bytes = new BigInteger(b, 2).toByteArray();

		fout.write(bytes);

		//		System.out.println(bytes[0]);
		System.out.println(in.read());

	}

	//	@Test
	public void test4() throws Exception
	{
		FileOutputStream fout = new FileOutputStream("testoutput.dat"); 
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
	public void test5() throws Exception
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
		while ( (b.length() - i) > 7 ) 
		{
			j = i + 7; 
			String chunk = b.substring(i, j);
			System.out.println("Chunk " + chunk);
			Byte byteChuck = Byte.parseByte(chunk, 2);
			System.out.println("Byte Chuck " + byteChuck);
			System.out.println("Byte to String " + Integer.toBinaryString(byteChuck));
			array[k] = byteChuck;
//			System.out.println("Array " + array[k]);
			//			System.out.println(array[1]);
			i = j;
			k++;
		}
		System.out.println(i + " " + j);


		fout.write(array);
		fout.close();

		Path path = Paths.get("testoutput.dat");
		byte[] data = java.nio.file.Files.readAllBytes(path);
		System.out.println(data[0]);
	}

}
