import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.math.BigDecimal;

class DancingGoogler{
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
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");				
				int N = scanner.nextInt();
				int S = scanner.nextInt();
				int p = scanner.nextInt();
				int[] scores = new int[N];
				for(int j = 0; j < N; ++j)
					scores[j] = scanner.nextInt();
				Arrays.sort(scores);
				//System.out.println(Arrays.toString(scores));
				int minSurprising = 3*p - 4;
				minSurprising = minSurprising < 2 ? 2 : minSurprising;
				int maxSurprising;
				if(p == 0)
					maxSurprising = -1;
				else if(p == 1)
					maxSurprising = 0;
				else
					maxSurprising = minSurprising + 1;
				int possibleSurprising = 0;
				int result = 0;
				for(int score : scores){
					if(score > maxSurprising)
						++result;
					else if(score >= minSurprising)
						++possibleSurprising;
				}
				//System.out.println(possibleSurprising + " " + S);
				result += (possibleSurprising < S ? possibleSurprising : S);
				output.append("Case #").append(i).append(": ").append(result).append("\n");
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
}

