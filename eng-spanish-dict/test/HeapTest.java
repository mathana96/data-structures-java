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
	public void teardown()
	{
		pairsHeap.clear();
		pairsSort.clear();
	}
	@Test
	public void testMaxHeap() throws Exception
	{
		System.out.println(pairsHeap.get(0));
		pairsSort =  parseData.readFile(path);
		Collections.sort(pairsSort);

		assertEquals(pairsSort.get(0), pairsHeap.get(0));

		
	}

}
