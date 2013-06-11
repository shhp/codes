import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class Osmos{
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
				int A = scanner.nextInt();
				int N = scanner.nextInt();
				ArrayList<Long> motes = new ArrayList<Long>();
				for(int j = 1; j <= N; j++)
					motes.add(scanner.nextLong());
				Collections.sort(motes);
				int operationTime = 0;
				long currentMoteSize = A;
								
				output.append("Case #").append(i).append(": ").append(minOperationTime(A, motes)).append("\n");
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
	
	static int minOperationTime(long currentMoteSize, ArrayList<Long> motes){
		if(motes.size() == 0)
			return 0;
				
		while(motes.size() > 0 && motes.get(0) < currentMoteSize){
			currentMoteSize += motes.get(0);
			motes.remove(0);
		}
		
		if(currentMoteSize - 1 == 0)
			return motes.size();
		
		if(motes.size() == 0)
			return 0;
		
		ArrayList<Long> removeMotes = (ArrayList<Long>)(motes.clone());
		removeMotes.remove(0);
		int operationTimeRemove = minOperationTime(currentMoteSize, removeMotes);
		
		int operationTimeAdd = 0;
		while(currentMoteSize <= motes.get(0)){
			currentMoteSize += currentMoteSize-1;
			operationTimeAdd++;
		}
		ArrayList<Long> addMotes = (ArrayList<Long>)(motes.clone());		
		//addMotes.add(0, currentMoteSize-1);
		//System.out.println("size:" + addMotes.size() + " mote:"+(2*currentMoteSize-1));
		operationTimeAdd += minOperationTime(currentMoteSize, addMotes);
		
		
		return operationTimeAdd < operationTimeRemove+1 ? operationTimeAdd : operationTimeRemove+1;
	}
}

