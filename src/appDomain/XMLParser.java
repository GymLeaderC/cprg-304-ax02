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
import exceptions.EmptyQueueException;

import implementations.MyQueue;
import java.io.File;
import java.io.FileNotFoundException;

public class XMLParser {

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("No XML file provided");
			return;
		}
		// Load XML File
		MyArrayList<String> xmlData = loadFile(args[0]);
		
		if (xmlData.size() ==0) {
			System.out.println("No xml data");
			return;
		}
		
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
		MyQueue<String> queue = new MyQueue<>();
		
		for (int i = 0; i < data.size(); i++) {
			String line = data.get(i).trim();
			int lineNum = i +1;
			int pos = 0;
			
			while (pos < line.length()) {
				
				// Get raw tag
				int start = line.indexOf("<", pos);
				int strayClose = line.indexOf(">", pos);
				
				if (strayClose != -1 && (start == -1 || strayClose < start)) {
					System.out.println("Error: stray > found in line "+ lineNum +": " + line);
					pos = strayClose +1;
					continue;
				}
				
				if (start == -1) {
					break;
				}
				
				int end = line.indexOf(">", start);
				if (end == -1) {
					System.out.println("Error: tag missing closing > in line: "+ line);
					break;
				
				}
				
				String rawTag = line.substring(start, end + 1);
				queue.enqueue(rawTag);
										
				pos = end + 1;
			}
		}
		try {
			while (!queue.isEmpty()) {
				String rawTag = queue.dequeue();
				// Check for processing instructions
				if (rawTag.startsWith("<?")) {
					continue;
				}
							
				// Check for self-closing tag
				if (rawTag.endsWith("/>")) {
					System.out.println("Found self-closing tag: " + rawTag);
					continue;
				}
				
				// Get tag name
				String tagName = rawTag;
				tagName = tagName.replace("</", "");
				tagName = tagName.replace("/>", "");
				tagName = tagName.replace("<", "");
				tagName = tagName.replace(">", "");
				String[] tagParts = tagName.trim().split(" ");
				tagName = tagParts[0];

				if (!rawTag.startsWith("</")) {
					tags.push(tagName);
				}
				else {
					if (tags.isEmpty()) {
						System.out.println("Error: closing tag " + rawTag + " has no matching opening tag.");
						continue;
					}
					
					if (tags.peek().equals(tagName)) {
						tags.pop();
					}
					else {
						System.out.println("Error: mismatched tag. Expected </" + tags.peek() + "> but found " + rawTag) ;
						tags.pop();
						continue;
					}
					
					
				}			
			}
			
		}
		
		catch (EmptyQueueException e) {
			System.out.println("Queue error: " + e.getMessage());
			return;
		}
		
		if (tags.isEmpty()) {
			System.out.println("XML is valid.");
		}
		else {
			System.out.println("Error: unclosed tag(s) remain.");
		}
			
					
	}	
}	
