import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;

class SpeakingInTongues{
	static HashMap<Character, Character> translation = new HashMap<Character, Character>();
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;	
		initTranslation();
		try{
			File inputData = new File("small.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			scanner.nextLine();
			/* Solve cases one by one */			
			StringBuilder output = new StringBuilder();
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");				
				String originSentence = scanner.nextLine();
				output.append("Case #").append(i).append(": ");
				for(char c : originSentence.toCharArray()){
					if(c != ' ')
						output.append(translation.get(c));
					else
						output.append(c);
				}						
				output.append("\n");
			}
			outputFile = new FileOutputStream(new File("output_small.txt"));
			outputFile.write(output.toString().getBytes(),0,output.toString().length());
			System.out.println(output.toString());
						
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(scanner != null)
					scanner.close();
				if(outputFile != null)
					outputFile.close();
			}catch(Exception e){
			e.printStackTrace();
			}
			
		}
	}
	
	static void initTranslation(){
		translation.put('a','y');
		translation.put('b','h');
		translation.put('c','e');
		translation.put('d','s');
		translation.put('e','o');
		translation.put('f','c');
		translation.put('g','v');
		translation.put('h','x');
		translation.put('i','d');
		translation.put('j','u');
		translation.put('k','i');
		translation.put('l','g');
		translation.put('m','l');
		translation.put('n','b');
		translation.put('o','k');
		translation.put('p','r');
		translation.put('q','z');
		translation.put('r','t');
		translation.put('s','n');
		translation.put('t','w');
		translation.put('u','j');
		translation.put('v','p');
		translation.put('w','f');
		translation.put('x','m');
		translation.put('y','a');
		translation.put('z','q');
	}
}

