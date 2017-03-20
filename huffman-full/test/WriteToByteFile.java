/**
 * GUAVA I/O: http://www.baeldung.com/guava-write-to-file-read-from-file
 */

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

	@Test
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

}
