import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


public class CaptainHammer{

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
				int V = scanner.nextInt();
				int D = scanner.nextInt();
				double value = D * 9.8 / (V * V);
				value = value > 1.0 ? 1.0 : value;
				double theta = Math.asin(value) * 90 / Math.PI;
				//System.out.println(theta);			
				output.append("Case #").append(i).append(": ");							
				output.append(theta).append("\n");
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
	
}


