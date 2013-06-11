import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class Bullseye{
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("large.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");
				long r = scanner.nextLong();
				long t = scanner.nextLong();
				long blackRingNumber = 1;
				long needPaint = 2*r + 1;
				long nextRadius = 0;
				long helper = 2*r - 1;
				
				while(needPaint <= t){
//					System.out.println("needpaint: "+needPaint);
					blackRingNumber *= 2;
//					t -= needPaint;
//					nextRadius = r + 2*blackRingNumber + 1;
					
					needPaint = 2*blackRingNumber*blackRingNumber + helper*blackRingNumber;
					//System.out.println("needpaint: "+needPaint);
				}
				long low = blackRingNumber / 2;
				long high = blackRingNumber;				
				while(high - low > 1){
					blackRingNumber = (low + high) / 2;
					needPaint = 2*blackRingNumber*blackRingNumber + helper*blackRingNumber;
					if(needPaint > t)
						high = blackRingNumber;
					else if(needPaint < t)
						low = blackRingNumber;
					else
						break;
				}
				if(high - low == 1)
					output += "Case #"+i+": " +(low) + "\n";
				else
					output += "Case #"+i+": " +(blackRingNumber) + "\n";
			}
				
			
			outputFile = new FileOutputStream(new File("output_large.txt"));
			outputFile.write(output.getBytes(),0,output.length());
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
}