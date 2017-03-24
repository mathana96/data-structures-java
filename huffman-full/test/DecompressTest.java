import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class DecompressTest
{

	private static final String identifier = "CADD099";
	Integer valIdent = Integer.parseInt(identifier, 16);
	
	String dirtyPayload = "";

	@Test
	public void test1() throws IOException
	{

		Path path = Paths.get("././data/compressed.dat");
		byte[] data = Files.readAllBytes(path);
		
	
		for (int i=0; i<data.length; i++)
		{
			Integer integerForm = Byte.toUnsignedInt(data[i]);
			String stringForm = Integer.toBinaryString(integerForm);
			dirtyPayload += stringForm;
			
		}
		
		
		System.out.println(dirtyPayload);
		System.out.println(cleanPayload(dirtyPayload));
	}
	
	public String cleanPayload(String payload)
	{
		StringBuilder sb = new StringBuilder(payload);
		
		for (int i=0; (i*7)<payload.length(); i++) 
		{
			sb.deleteCharAt((i * 7)-i);
		}
		
		return sb.toString();
	}
	
//	public String cleanPayload(String dirtyPayload)
//	{
//		StringBuilder sb = new StringBuilder();
//		
//		int i = 1;
//		int j = 0;
//		while ((dirtyPayload.length() - i) < 7)
//		{
//			j = i + 7;
//			String sub = dirtyPayload.substring(i, j);
//			sb.append(sub);
//			i = j + 2;
//		}
//		
//		return sb.toString();
//		
//	}
//	public boolean confirmHeader()
//	{
//		
//	}
	//	@Test
	//	public void test() throws IOException
	//	{
	//		String output = "";
//			File file = new File("././data/compressed.dat");
	//		FileInputStream fin = new FileInputStream(file);
	//
	//		byte[] identarray = new byte[4];
	//
	//		fin.read(identarray);
	//
	//		if (confirmIdentifier(identarray))
	//		{
	//			int partOfIdent2 = Byte.toUnsignedInt((byte) fin.read());
	//			int partOfIdent = Byte.toUnsignedInt((byte) fin.read());
	//			System.out.println(partOfIdent);
	//			String string = Integer.toBinaryString(partOfIdent);
	//			if (string.length() < 7)
	//			{
	//				string = correctLeadingZeros(string);
	//			}
	//			Integer isValidIdent = Integer.parseInt(string, 2);
	//
	//			System.out.println(isValidIdent);
	//
	//			//			while (fin.read() != -1)
	//			//			{
	//			//
	//			//			}
	//		}
	//
	//	}
	//
	//	public boolean confirmIdentifier(byte[] ident)
	//	{		
	//		String identifier = "";
	//
	//		byte[] identarray = ident;
	//
	//		//		while (fin.read() != -1)
	//		//		{
	//
	//		for (int i=0; i<4; i++)
	//		{
	//			int partOfIdent = Byte.toUnsignedInt(identarray[i]);
	//			String string = Integer.toBinaryString(partOfIdent);
	//			if (string.length() < 7)
	//			{
	//				string = correctLeadingZeros(string);
	//			}
	//			identifier += string;
	//			//				identifier += identarray[i];
	//			//				System.out.println(identifier);
	//
	//		}
	//
	//		Integer isValidIdent = Integer.parseInt(identifier, 2);
	//
	//		if (isValidIdent.equals(valIdent))
	//			return true;
	//		else
	//			return false;
	//
	//	}
	//	public String correctLeadingZeros(String s)
	//	{
	//		while (s.length() < 7)
	//		{
	//			s = "0" + s;
	//		}
	//		return s;
	//	}

}