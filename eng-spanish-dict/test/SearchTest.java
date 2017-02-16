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
		engine.searchWord("chica",0);
		assertEquals("girl", engine.getSearchedWord());
		engine.searchWord("chicdsasda",0);
		assertEquals("not found", engine.getSearchedWord());
//		assertEquals("not found", engine.searchWord("BUNL",0));
//		assertEquals("not found", engine.searchWord("",0));
//		assertEquals("not found", engine.searchWord(null,0));
//		assertEquals("not found", engine.searchWord("$*&(",0));
//		assertEquals("not found", engine.searchWord("98897",0));
	}

}
