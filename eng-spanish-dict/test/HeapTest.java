import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;


import models.Pair;
import utils.ParseData;

public class HeapTest
{

	ParseData parseData = new ParseData();
	String path = "././data/spa-to-eng.txt";
	ArrayList<Pair> pairsHeap = new ArrayList<>();
	ArrayList<Pair> pairsSort = new ArrayList<>();
	
	@Before
	public void setup() throws Exception
	{
		pairsHeap = parseData.readFile(path);
		
	}
	@Test
	public void testMaxHeap() throws Exception
	{
		pairsSort =  parseData.readFile(path);
		Collections.sort(pairsSort);
		
		assertEquals(pairsSort.get(0), pairsHeap.get(0));
		
	}

}
