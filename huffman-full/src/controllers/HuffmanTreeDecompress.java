package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class HuffmanTreeDecompress
{

	private static final String identifier = "CADD099";
	Integer valIdent = Integer.parseInt(identifier, 16);

	public HuffmanTreeDecompress(){}


	public void readBytesFromFile() throws IOException
	{
		String output = "";
		File file = new File("././data/compressed.dat");
		FileInputStream fin = new FileInputStream(file);

		byte[] identarray = new byte[4];

		fin.read(identarray);

		if (confirmIdentifier(identarray))
		{
			int partOfIdent = Byte.toUnsignedInt((byte) fin.read());
			String string = Integer.toBinaryString(partOfIdent);
			if (string.length() < 7)
			{
				string = correctLeadingZeros(string);
			}
			Integer isValidIdent = Integer.parseInt(string, 2);

			System.out.println(isValidIdent);
			
//			while (fin.read() != -1)
//			{
//
//			}
		}



		//		System.out.println(Integer.toString(in));

		//First 4 bits are identifier, working!


		fin.close();
	}

	public boolean confirmIdentifier(byte[] ident)
	{		
		String identifier = "";

		byte[] identarray = ident;

		//		while (fin.read() != -1)
		//		{

		for (int i=0; i<4; i++)
		{
			int partOfIdent = Byte.toUnsignedInt(identarray[i]);
			String string = Integer.toBinaryString(partOfIdent);
			if (string.length() < 7)
			{
				string = correctLeadingZeros(string);
			}
			identifier += string;
			//				identifier += identarray[i];
			//				System.out.println(identifier);

		}

		Integer isValidIdent = Integer.parseInt(identifier, 2);

		if (isValidIdent.equals(valIdent))
			return true;
		else
			return false;

	}

	public String correctLeadingZeros(String s)
	{
		while (s.length() < 7)
		{
			s = "0" + s;
		}
		return s;
	}

	public void writeDecompressedFile() throws FileNotFoundException
	{
		File file = new File("././data/decompressed.txt");
		FileOutputStream fout = new FileOutputStream(file);

		//		fout.write();
	}
}
