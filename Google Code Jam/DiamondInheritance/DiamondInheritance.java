import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


class DiamondInheritance{

	static boolean found = false;
	
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;	
		ArrayList<ArrayList<Integer>> classAndInheritance = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= 1000; ++i)
			classAndInheritance.add(new ArrayList<Integer>());
		try{
			File inputData = new File("large.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			StringBuilder output = new StringBuilder();
			ArrayList<Integer> inheritance = null;
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");							
				int N = scanner.nextInt();				
				for(int j = 1; j <= N; ++j){
					inheritance = classAndInheritance.get(j);
					inheritance.clear();
					int inheritanceNumber = scanner.nextInt();
					for(int k = 1; k <= inheritanceNumber; ++k)
						inheritance.add(scanner.nextInt());
				}
				//System.out.println(Arrays.toString(classAndInheritance.toArray()));
				
				output.append("Case #").append(i).append(": ");			
				ArrayList<Integer> classhaveReached = new ArrayList<Integer>();
				boolean diamondExists = false;
				for(int j = 1; j <= N; ++j){				
					ArrayDeque<Integer> classCanBeReached = new ArrayDeque<Integer>(); 
					classhaveReached.clear();
					for(Integer classNo : classAndInheritance.get(j))
						classCanBeReached.add(classNo);
						
					while(classCanBeReached.peek() != null){
						Integer classNo = classCanBeReached.remove();
						if(classhaveReached.contains(classNo)){
							diamondExists = true;
							break;
						}
						else{
							classhaveReached.add(classNo);
							for(Integer classNumber : classAndInheritance.get(classNo.intValue()))
								classCanBeReached.add(classNumber);
						}
					}
					
					if(diamondExists)	
						break;
				}
				output.append(diamondExists ? "Yes" : "No").append("\n");
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

