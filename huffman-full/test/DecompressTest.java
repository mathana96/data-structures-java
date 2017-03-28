import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import models.Node;

public class DecompressTest
{

	private static final int identifier = 0xCADD099;
	private static final int identifierBits = 28;
	private static final int trieLengthBits = 14;

	//	Integer valIdent = Integer.parseInt(identifier, 16);

	String dirtyPayload = "";
	String cleanPayload = "";
	String textOutput = "";
	String trieCode = "";
	int treeIndex = -1;
	int codeIndex = -1;
	boolean EOF = false;
	//	int trieLength = 0;

	@Test
	public void test1() throws IOException
	{

		Path path = Paths.get("././data/compressed.dat");
		byte[] data = Files.readAllBytes(path);


		for (int i=0; i<data.length; i++)
		{
			Integer integerForm = Byte.toUnsignedInt(data[i]);
			String stringForm = Integer.toBinaryString(integerForm);
			dirtyPayload += stringForm;

		}

		cleanPayload = cleanPayload(dirtyPayload);

		System.out.println("Dirty :" + dirtyPayload);
		System.out.println("Clean : " + cleanPayload);

		boolean EOF = false; 

		String ident = cleanPayload.substring(0, identifierBits);

		if (checkIdent(ident))
		{
			int trieLength = getTrieLength(cleanPayload.substring(identifierBits, identifierBits + trieLengthBits));
			int trieStart = identifierBits + trieLengthBits;
			System.out.println("Triestart index: " + trieStart + " trieend: " + (trieStart+trieLength));
			trieCode = cleanPayload.substring(trieStart, trieStart + trieLength);
			Node root = buildTrie();

			codeIndex += trieStart + trieLength;

			while (!EOF)
			{
				decoderMF(root);
			}
			//			System.out.println("freq" + root.freq);
			//			System.out.println(trieLength);
		}

	}



	public void decoderMF(Node node)
	{

		if (node.data != '#')
		{
			if (isLeaf(node))
			{

				System.out.println(node.data);

			} else
			{
				if (getNextCharCode() == '0')
					decoderMF(node.left);
				else
					decoderMF(node.right);
			} 
		}
		else
		{
			EOF = true;
		}

	}

	public char getNextCharCode()
	{
		codeIndex++;
		//		System.out.println(trieCode.charAt(treeIndex));
		return cleanPayload.charAt(codeIndex);
	}
	public boolean isLeaf(Node node)
	{
		if ( (node.left == null) && (node.right == null) )
			return true;
		else
			return false;
	}
	public String cleanPayload(String payload)
	{
		StringBuilder sb = new StringBuilder(payload);

		for (int i=0; (i*7)<payload.length(); i++) 
		{
			sb.deleteCharAt((i * 7)-i);
		}

		return sb.toString();
	}

	public Node buildTrie()
	{
		if (getNextCharTrie() == '1')
		{
			char data = getAscii();
			return new Node(data, 0, null, null);
		}
		else
		{
			Node x = buildTrie();
			Node y = buildTrie();
			return new Node('\0', 0, x, y);
		}
	}

	public char getNextCharTrie()
	{
		treeIndex++;
		System.out.println(trieCode.charAt(treeIndex));
		return trieCode.charAt(treeIndex);
	}

	public char getAscii()
	{
		treeIndex++;
		String asciiStr = trieCode.substring(treeIndex, treeIndex + 7);
		int asciiInt = Integer.parseInt(asciiStr, 2);
		char ascii = (char) asciiInt;
		treeIndex = treeIndex + 6; //has to be 6 to counter the increment at getNextCHar()
		return ascii;
	}

	public boolean checkIdent(String ident)
	{
		String identValid = Integer.toBinaryString(identifier);

		//		System.out.println("In text: " + ident);
		//		System.out.println("real: " + identValid);

		if (ident.matches(identValid))
			return true;

		return false;
	}

	public int getTrieLength(String s)
	{
		return Integer.parseInt(s, 2);
	}


}