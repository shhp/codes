import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class GoodLuck{
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("small.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			String output = "Case #1:\n";
			int R = scanner.nextInt();
			int N = scanner.nextInt();
			int M = scanner.nextInt();
			int K = scanner.nextInt();
			for(int i = 1; i <= R; i++){
				int[] products = new int[K];
				for(int k = 0; k < K; k++)
					products[k] = scanner.nextInt();
				Arrays.sort(products);
				//System.out.println(Arrays.toString(products));
				output += guessOnce(products) + "\n";
			}
				
			
//			outputFile = new FileOutputStream(new File("output_small.txt"));
//			outputFile.write(output.getBytes(),0,output.length());
			System.out.println(output);
						
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
	
	static String guessOnce(int[] products){
		ArrayList<Integer> haveGuessed = new ArrayList<Integer>();
		int index = 0;
		String result = "";
		while(index < products.length && products[index] == 1)
			index++;
		if(index == products.length)
			return "222";
		int greaterThanOneIndex = index;
		while(index < products.length && haveGuessed.size() < 3){		
			if(products[index] % 27 == 0)
				return "333";
			if(products[index] % 64 == 0)
				return "444";
			if(products[index] % 125 == 0)
				return "555";
			index++;
		}
		index = greaterThanOneIndex;		
		while(index < products.length && haveGuessed.size() < 3){			
			if(products[index] % 3 == 0 && !haveGuessed.contains(3)){
				haveGuessed.add(3);
			}			
			if(products[index] % 5 == 0 && !haveGuessed.contains(3)){
				haveGuessed.add(5);
			}
			index++;
		}
		if(haveGuessed.size() > 0){
			int productOfGuessed = 1;
			for(Integer i : haveGuessed)
				productOfGuessed *= i;
			for(int i = 0; i < products.length; i++){
				if(products[i] % productOfGuessed == 0)
					products[i] /= productOfGuessed;
				else if()
			}
		}
		
		while(haveGuessed.size() < 3){
			haveGuessed.add(2);
		}
		for(Integer i : haveGuessed)
			result += i;
		
		return result;
	}
	
}