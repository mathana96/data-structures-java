import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import models.Pair;
import utils.ParseData;

public class ParserTest
{
	
	String path = "././data/spa-to-eng.txt";
	ArrayList<Pair> pairs = new ArrayList<>();

	@Test
	public void test() throws Exception
	{
		ParseData parseData = new ParseData();
		pairs = parseData.readFile(path);
		System.out.println(pairs);	
//		System.out.println(pairs.peek());
	}

}
