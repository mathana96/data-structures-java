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
