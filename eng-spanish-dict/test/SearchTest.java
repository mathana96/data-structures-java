/**
 * @author Mathana Sreedaran
 * 
 * Test class for searching the english definition of a spanish key/entry
 */
import static org.junit.Assert.*;

import org.junit.Test;

import controllers.DictEngine;

public class SearchTest
{

	DictEngine engine;
	
	@Test
	public void testSearch() throws Exception
	{
		engine = new DictEngine();
		assertEquals("girl", engine.searchWord("chica"));
		assertEquals("not found", engine.searchWord("BUNL"));
		assertEquals("not found", engine.searchWord(""));
		assertEquals("not found", engine.searchWord(null));
		assertEquals("not found", engine.searchWord("$*&("));
		assertEquals("not found", engine.searchWord("98897"));
	}

}
