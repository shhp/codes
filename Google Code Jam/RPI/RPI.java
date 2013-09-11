import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


public class RPI{

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
			int[] wins, totalMatches;		
			double[] WP, OWP, OOWP, RPI;
			double[][] OWPHelper;
			int[][] playRecord;
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				int teamNumber = scanner.nextInt();
				wins = new int[teamNumber];
				totalMatches = new int[teamNumber];
				WP = new double[teamNumber];
				OWP = new double[teamNumber];
				OOWP = new double[teamNumber];
				RPI = new double[teamNumber];
				OWPHelper = new double[teamNumber][teamNumber];
				playRecord = new int[teamNumber][teamNumber];
				String line = scanner.nextLine();
				for(int j = 0; j < teamNumber; ++j){
					line = scanner.nextLine();
					for(int k = 0; k < teamNumber; ++k){
						if(line.charAt(k) != '.')
							totalMatches[j]++;
							
						if(line.charAt(k) == '1'){
							wins[j]++;
							playRecord[j][k] = 1;
						}
						else if(line.charAt(k) == '0'){
							playRecord[j][k] = 0;
						}
						else{
							playRecord[j][k] = -1;
						}
					}
				}
				
				for(int j = 0; j < teamNumber; ++j)
					WP[j] = wins[j] / (totalMatches[j] * 1.0);
					
				for(int j = 0; j < teamNumber; ++j)
					for(int k = 0; k < teamNumber; ++k){
						if(playRecord[j][k] == 0)
							OWPHelper[j][k] = (wins[k] - 1) / ((totalMatches[k] - 1) * 1.0);
						else if(playRecord[j][k] == 1)
							OWPHelper[j][k] = (wins[k]) / ((totalMatches[k] - 1) * 1.0);
						else
							OWPHelper[j][k] = WP[k];
					}
					
				for(int j = 0; j < teamNumber; ++j){
					double OWPSum = 0;
					for(int k = 0; k < teamNumber; ++k){
						if(playRecord[j][k] >= 0)
							OWPSum += OWPHelper[j][k];
					}
					
					OWP[j] = OWPSum / totalMatches[j];
				}
				
				for(int j = 0; j < teamNumber; ++j){
					double OWPSum = 0;
					for(int k = 0; k < teamNumber; ++k){
						if(playRecord[j][k] >= 0)
							OWPSum += OWP[k];
					}
					
					OOWP[j] = OWPSum / totalMatches[j];
				}
				
				for(int j = 0; j < teamNumber; ++j){
					RPI[j] = 0.25 * WP[j] + 0.50 * OWP[j] + 0.25 * OOWP[j];
				}
					
				//System.out.println(Arrays.toString(RPI));			
				output.append("Case #").append(i).append(":\n");	
				for(int j = 0; j < teamNumber; ++j)				
					output.append(RPI[j]).append("\n");
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


