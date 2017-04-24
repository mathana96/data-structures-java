import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import models.Rule;
import utils.Parser;

public class TestParser
{

	private static String path = "././data/input.txt"; //Path to data
	
	Map<Integer, Rule> rules = new HashMap<>();
	
	@Test
	public void test() throws Exception
	{
		Parser parser = new Parser();
		rules = parser.parseRuleData(path);
		
		Rule rule = rules.get(1);
		System.out.println(rule.question);
		System.out.println(rule.yes.question);
		System.out.println(rule.no.question);
		
		Rule rule1 = rules.get(20);
		System.out.println(rule1.question);
		System.out.println(rule1.yes);
		System.out.println(rule1.no);

	}
	
	

	@Test
	public void test2() throws Exception
	{
		Parser parser = new Parser();
		rules = parser.parseRuleData(path);
		
		
		Rule rule2 = rules.get(6);
		System.out.println(rule2.question);
		System.out.println(rule2.yes.question);
		System.out.println(rule2.no.question);
	}
	
}
