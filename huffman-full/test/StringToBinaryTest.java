

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToBinaryTest
{

	//	@Test
	public void test()
	{
		char a = '.';
		Integer ascii = (int) a; 
		String binary = Integer.toBinaryString(ascii);
		System.out.println(binary);
	}

	//	@Test
	public void test2()
	{
		String s = "10101";
		while (s.length() < 7)
		{
			s = "0" + s;
		}

		System.out.println(s);
	}

//	@Test
	public void test3()
	{
		int identifier = 0xCADD099;
		String identifier2 = "CADD099";
		System.out.println(Integer.parseInt(identifier2, 16));
		System.out.println(Integer.toBinaryString(identifier));

	}
	
//	@Test
	public void test4()
	{
		assertEquals(0.0, 14%7.0, 0.01);
		assertNotEquals(0.0, 15%7.0, 0.01);
	}
	
//	take 6 bits and Append 1. when reading out, subtract 64. 
	
//	@Test
	public void test5()
	{
		System.out.println("HI");
		String binary = "10011110001111";
		byte[] byteArray = new byte[10];
		int i = 0;
		int j = 0;
		int k = 0;
		while ( (binary.length() - i) >= 7 )
		{
			j = i + 6;
			
			String chunk = binary.substring(i, j);
			chunk = "1" + chunk;
			
			if (chunk.length() < 7)
				chunk = padWithZeros(chunk);
			
			byteArray[k] = Byte.parseByte(chunk, 2);

			String decimal = Byte.toString(byteArray[k]);
			
			Integer decimalInt = Byte.toUnsignedInt(byteArray[k]);
			
			System.out.println(Integer.toString(decimalInt, 2));
			
			i = j;
			k++;
			
			System.out.println("YO");
		}

		
		
	}
	
//	@Test
	public void test6()
	{
		Integer ascii = (int) 'a';
		String asciiBinary = Integer.toBinaryString(ascii);
		System.out.println(asciiBinary);
	}
	
	@Test
	public void test7()
	{
		String teString = "1010101";
		System.out.println(teString.charAt(1));
	}
	
	public String padWithZeros(String s)
	{
		while (s.length() < 7)
		{
			s += "0";
		}
		return s;
	}

}
