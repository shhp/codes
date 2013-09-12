import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


public class HotDogs{
	static double shouldBePositioned;
	
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
			ArrayList<Vendor> vendors = new ArrayList<Vendor>();
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				int C = scanner.nextInt();
				int D = scanner.nextInt();
				int totalVendors = 0;
				vendors.clear();
				for(int j = 0; j < C; ++j){
					int P = scanner.nextInt();
					int V = scanner.nextInt();
					vendors.add(new Vendor(P, V));
					totalVendors += V;
				}	

				double lowerBound = 0;
				double upperBound = 1000000000000.0;
				double time = 0;
				while(upperBound - lowerBound > 0.0000001 * upperBound){
					time = (lowerBound + upperBound) / 2;
					shouldBePositioned = vendors.get(0).position - time + D;
					if(timeIsEnough(time, vendors, totalVendors, D))
						upperBound = time;
					else
						lowerBound = time;
				}
				//System.out.println(lowerBound);			
				output.append("Case #").append(i).append(": ");							
				output.append(lowerBound).append("\n");
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
	
	static boolean timeIsEnough(double time, ArrayList<Vendor> vendors, int totalVendors, int D){
		for(int k = 2; k <= totalVendors; ++k){
			int currentPosition = getVendorPosition(k, vendors);
			if(shouldBePositioned <= currentPosition){
				shouldBePositioned = Math.max(shouldBePositioned, currentPosition-time) + D;
			}
			else{
				if(shouldBePositioned - currentPosition > time){
					return false;
				}
				else{
					shouldBePositioned += D;
				}
			}
		}
		
		return true;
	}
		
	static int getVendorPosition(int index, ArrayList<Vendor> vendors){
		int vendorCounter = 0;
		int vendorIndex = -1;
		while(vendorCounter < index){
			vendorIndex++;
			vendorCounter += vendors.get(vendorIndex).number;			
		}
		
		return vendors.get(vendorIndex).position;
	}
}

class Vendor{
	int position;
	int number;
	
	public Vendor(int position, int number){
		this.position = position;
		this.number = number;
	}
	
	public String toString(){
		return "P:" + position + " V:" + number;
	}
}


