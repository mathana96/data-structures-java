package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile
{
	Scanner input;
	File file;
	StringBuilder sb;
	
	public ReadFromFile(String path) throws Exception
	{
		List<String> lines = Files.readAllLines(Paths.get(path),StandardCharsets.UTF_8);
		sb = new StringBuilder(1024); 
		for (String line : lines) 
		{ 
			sb.append(line); 
		}

		
	}
	
	public String getFileContent()
	{
		return sb.toString();
	}

}
