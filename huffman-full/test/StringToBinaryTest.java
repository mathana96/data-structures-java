

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

	@Test
	public void test3()
	{
		int identifier = 0xCADD099;
		
		System.out.println(Integer.toBinaryString(identifier));

	}
	
//	@Test
	public void test4()
	{
		assertEquals(0.0, 14%7.0, 0.01);
		assertNotEquals(0.0, 15%7.0, 0.01);
	}

}
