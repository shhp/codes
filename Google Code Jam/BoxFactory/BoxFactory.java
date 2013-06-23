import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


class BoxFactory{

	static boolean found = false;
	
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;	
		
		try{
			File inputData = new File("large.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			StringBuilder output = new StringBuilder();
			ArrayList<Boxes> boxLine = new ArrayList<Boxes>();
			ArrayList<Toys> toyLine = new ArrayList<Toys>();
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				boxLine.clear();
				toyLine.clear();
				
				int N = scanner.nextInt();
				int M = scanner.nextInt();				
				for(int j = 1; j <= 2*N; j += 2){
					boxLine.add(new Boxes(scanner.nextLong(), scanner.nextInt()));
				}
				for(int j = 1; j <= 2*M; j += 2){
					toyLine.add(new Toys(scanner.nextLong(), scanner.nextInt()));
				}
				//System.out.println(Arrays.toString(toyLine.toArray()));			
				output.append("Case #").append(i).append(": ");			
				output.append(productionNumber(boxLine, toyLine, 0, 0)).append("\n");
			}
			outputFile = new FileOutputStream(new File("output_large.txt"));
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
	
	
	static long productionNumber(ArrayList<Boxes> boxLine, ArrayList<Toys> toyLine, int boxIndex, int toyIndex){
		if(boxIndex == boxLine.size() || toyIndex == toyLine.size())
			return 0;
		
		int theBoxIndex = boxIndex;
		
		long discardCurrentBoxType = productionNumber(boxLine, toyLine, boxIndex+1, toyIndex);
		//System.out.println(theBoxIndex + "- discard: " + discardCurrentBoxType);
		
		long acceptCurrentBoxType = 0;
		while(toyIndex < toyLine.size() && toyLine.get(toyIndex).type != boxLine.get(boxIndex).type)
			++toyIndex;
		int previousBoxIndex = boxIndex;
		int previousToyIndex = toyIndex;
		long boxChangedNumber = 0;
		long toyChangedNumber = 0;
		if(toyIndex < toyLine.size()){
			Toys toys = toyLine.get(toyIndex);
			Boxes boxes = boxLine.get(boxIndex);
			if(boxes.number < toys.number){
				acceptCurrentBoxType += boxes.number;
				toys.number -= boxes.number;
				++boxIndex;
				toyChangedNumber = boxes.number;
			}
			else if(boxes.number > toys.number){
				acceptCurrentBoxType += toys.number;
				boxes.number -= toys.number;
				++toyIndex;
				boxChangedNumber = toys.number;
			}
			else{
				acceptCurrentBoxType += boxes.number;
				++boxIndex;
				++toyIndex;
			}
		}
		//alreadyProduced += acceptCurrentBoxType;
		acceptCurrentBoxType += productionNumber(boxLine, toyLine, boxIndex, toyIndex);
		//System.out.println(theBoxIndex + "- accept: " + acceptCurrentBoxType);
		if(previousBoxIndex < boxLine.size())
			boxLine.get(previousBoxIndex).number += boxChangedNumber;
		if(previousToyIndex < toyLine.size())
			toyLine.get(previousToyIndex).number += toyChangedNumber;
		return discardCurrentBoxType > acceptCurrentBoxType ? discardCurrentBoxType : acceptCurrentBoxType;
	}
		
}

class Boxes{
	long number;
	int type;
	
	public Boxes(long number, int type){
		this.number = number;
		this.type = type;
	}
	
	public String toString(){
		return "Number: " + number + "  Type: " + type;
	}
}

class Toys{
	long number;
	int type;
	
	public Toys(long number, int type){
		this.number = number;
		this.type = type;
	}
	
	public String toString(){
		return "Number: " + number + "  Type: " + type;
	}
}

