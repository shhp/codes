import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class Test1{
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
			//System.out.println(getN(4));
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				int X = scanner.nextInt();
				int Y = scanner.nextInt();
				int NforX = getN(X);
				int NforY = getN(Y);
				
				
				//output.append("Case #").append(i).append(": ").append(nValue).append("\n");
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
	
	static int getN(int distance){
		double abs = Math.abs(distance);
		return (int)(Math.floor((-1 + Math.sqrt(1 + 8*abs)) / 2));
	}
	
	static String getToX(int X, int NforX){
		StringBuilder route = new StringBuilder();
		for(int i = 1; i <= NforX; i++)
			route.append(X < 0 ? "W" : "E");
		int remainDistance = ;
	}
}

