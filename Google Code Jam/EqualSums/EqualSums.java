import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


class EqualSums{

	static boolean found = false;
	
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
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");							
				int N = scanner.nextInt();
				long[] numbers = new long[20];
				for(int j = 0; j < 20; ++j)		
					numbers[j] = scanner.nextLong();
				Arrays.sort(numbers);
				
				ArrayList<Long> subset = new ArrayList<Long>();
				found = false;
				getSubsetWithSum(0, numbers, 12270, subset);
				if(found)
					System.out.println(Arrays.toString(subset.toArray()));	
				
				//output.append("Case #").append(i).append(": ");				
				
			}
			//outputFile = new FileOutputStream(new File("output_large.txt"));
			//outputFile.write(output.toString().getBytes(),0,output.toString().length());
			//System.out.println(output.toString());
						
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
	
	static void getSubsetWithSum(int startIndex, long[] numbers, long sum, ArrayList<Long> result){
		if(sum == 0){
			found = true;
			return;
		}
		
		for(int i = startIndex; i < numbers.length; ++i){
			if(numbers[i] > sum)
				return;
			
			result.add(numbers[i]);
			getSubsetWithSum(i+1, numbers, sum-numbers[i], result);
			if(found)
				return;
			else
				result.remove(result.indexOf(numbers[i]));
		}
	}
	
}

