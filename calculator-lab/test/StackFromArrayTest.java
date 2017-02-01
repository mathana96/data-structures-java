/**
 * @author Mathana Sreedaran
 * 
 * Test class for my stack implementation using the various interface methods
 */
import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackFromArrayTest
{

	MyOwnStack myStack = new MyOwnStack();
	Stack<Object> javaStack = new Stack<>();


	@Before
	public void setUp()
	{


		String name1 = "First";
		String name2 = "Second";
		javaStack.push(name1);
		javaStack.push(name2);
	}

	@After
	public void tearDown()
	{
		javaStack.clear();
		myStack.clear();
	}

	@Test
	public void testPush()
	{
		String name3 = "First";
		String name4 = "Second";

		myStack.push(name3);
		myStack.push(name4);

		assertEquals(2, myStack.length());
		assertEquals(javaStack.toString(), myStack.toString());
		//	System.out.println(javaStack);
		//	System.out.println(myStack);
	}

	@Test
	public void testPeek()
	{
		String name3 = "First";
		String name4 = "Second";

		myStack.push(name3);
		myStack.push(name4);

		System.out.println(javaStack.peek());
		System.out.println(myStack.peek());
		assertEquals(javaStack.peek(), myStack.peek());
	}

	@Test
	public void testPop()
	{
		String name3 = "First";
		String name4 = "Second";

		myStack.push(name3);
		myStack.push(name4);

		assertEquals(2, myStack.length());
		System.out.println(myStack);
		assertEquals("Second", myStack.pop());

		assertEquals(1, myStack.length());

	}

	//@Test
	public void testEmpty()
	{
		assertTrue(myStack.empty());

		String name3 = "First";
		String name4 = "Second";

		myStack.push(name3);
		myStack.push(name4);

		assertFalse(myStack.empty());
	}

	//@Test
	public void testSearch()
	{
		System.out.println(javaStack.search("First"));
	}
}
