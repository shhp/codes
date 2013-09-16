import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


public class Moist{

	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;	
		
		try{
			File inputData = new File("small.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			StringBuilder output = new StringBuilder();
			ArrayList<String> cards = new ArrayList<String>();
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				cards.clear();
				int N = scanner.nextInt();
				scanner.nextLine();
				for(int j = 0; j < N; ++j)
					cards.add(scanner.nextLine());
					
				int startIndex = 0;
				int money = 0;
				int wrongPositionIndex = 0;
				while((wrongPositionIndex = getWrongPositionedCardIndex(cards, startIndex)) > 0){
					int rightPosition = getRightPosition(cards, wrongPositionIndex);
					cards.add(rightPosition, cards.get(wrongPositionIndex));
					cards.remove(wrongPositionIndex + 1);
					money++;
					startIndex = wrongPositionIndex < cards.size() - 1 ? wrongPositionIndex : 0;										
				}
					
				//System.out.println(money);			
				output.append("Case #").append(i).append(": ");							
				output.append(money).append("\n");
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
	
	static int getWrongPositionedCardIndex(ArrayList<String> cards, int startIndex){
		for(int i = startIndex; i < cards.size()-1; ++i){
			if(cards.get(i+1).compareTo(cards.get(i)) < 0)
				return i+1;
		}
		
		return -1;
	}
	
	static int getRightPosition(ArrayList<String> cards, int index){
		for(int i = 0; i < index; ++i){
			if(cards.get(index).compareTo(cards.get(i)) < 0)
				return i;
		}
		
		return -1;
	}
}


