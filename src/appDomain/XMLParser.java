/**
 * XMLParser.java
 * 
 * @description An XML document parser that reads an XML file 
 * supplied via the command line and validates its structural 
 * correctness. Uses a MyStack and MyQueue to detect and report 
 * improperly nested or mismatched tags according to Kitty's 
 * parsing algorithm.
 * 
 * @author Team Sidon - Aaron Reid, Cielo Pacot, Joshua Couto, Ryan Burns
 * Course: CPRG-304 (Object-Oriented Programming 3)
 * Institution: Southern Alberta Institute of Technology 
 * Date: March 28th, 2026
 */

package appDomain;

import java.util.Scanner;

import implementations.MyArrayList;
import implementations.MyStack;
import implementations.MyQueue;
import exceptions.EmptyQueueException;

import java.io.File;
import java.io.FileNotFoundException;

public class XMLParser {

	public static void main(String[] args) {
		// Load XML File
		MyArrayList<String> xmlData = loadFile(args[0]);
		
		// Process XML File
		parseXML(xmlData);		
	}
	
	public static MyArrayList<String> loadFile(String fileName) {
		MyArrayList<String> data = new MyArrayList<>();
		
		
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
		
		return data;
	}
	
	public static void parseXML(MyArrayList<String> data) {
		MyStack<String> tags = new MyStack<>();
		MyQueue<String> errors = new MyQueue<>();
		
		for (int i = 0; i < data.size(); i++) {
			String line = data.get(i).trim();
			int lineNum = i + 1;
			
			// Check for self-closing tag
			if (line.endsWith("/>")) {
				continue;
			}
			
			// Check for processing instructions
			if (line.endsWith("?>")) {
				continue;
			}
			
			// Find start tags and add to stack
			if (!line.startsWith("</")) {
				int start = 0;
				int end = 0;
				
				while (start != -1) {
					start = line.indexOf("<", start);
					end = line.indexOf(" ", start);
					String startTag = line.substring(start + 1, end);
					tags.push(startTag);
				}
			}
			
			// Find end tags and remove from stack
			if (line.startsWith("</")) {
				String endTag = line.substring(2, line.length() - 1);
				
				// Check end tag matches top of stack
				if (tags.peek().equals(endTag)) {
					tags.pop();
				}
				// ** Add else statement for mismatching tags here **
			}
		}
	}
}
