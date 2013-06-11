import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;

class SaftyInNumbers{
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
				int contestantNumber = scanner.nextInt();
				int[] points = new int[contestantNumber];
				int[] pointsClone = new int[contestantNumber];
				for(int j = 0; j < contestantNumber; ++j){
					points[j] = scanner.nextInt();
					pointsClone[j] = points[j];
				}
					
				int totalPoints = 0;
				for(int point : points)
					totalPoints += point;				
				
				double sharePoints = 2 * totalPoints;
				int shareNumber = contestantNumber;
				double samePointForEveryContestant = sharePoints / shareNumber;
				Arrays.sort(pointsClone);
				for(int j = pointsClone.length-1; j >=0; --j){
					int point = pointsClone[j];
					if(point > samePointForEveryContestant){
						sharePoints -= point;
						shareNumber--;
						samePointForEveryContestant = sharePoints / shareNumber;
					}
				}
				BigDecimal sharePointBig = new BigDecimal(sharePoints);
				BigDecimal samePointForNotEliminated = sharePointBig.divide(new BigDecimal(shareNumber),7, RoundingMode.HALF_UP);
								
				
				output.append("Case #").append(i).append(": ");
				for(int point : points)
					if(point >= samePointForNotEliminated.doubleValue())
						output.append(0.000000).append(" ");
					else
						output.append((samePointForNotEliminated.subtract(new BigDecimal(point))).multiply(new BigDecimal(100)).divide(new BigDecimal(totalPoints),7, RoundingMode.HALF_UP).doubleValue()).append(" ");
					
			    output.append("\n");
				
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

