import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class RopeIntranet{
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
				int wireNumber = scanner.nextInt();
				ArrayList<Wire> wires = new ArrayList<Wire>();
				for(int j = 1; j <= wireNumber; j++)
					wires.add(new Wire(scanner.nextInt(), scanner.nextInt()));
				Collections.sort(wires);
				int intersectionNumber = 0;
				for(int j = 0; j < wires.size()-1; j++){
					Wire currentWire = wires.get(j);
					for(int k = j+1; k < wires.size(); k++){
						Wire nextWire = wires.get(k);
						if(nextWire.rightWindow < currentWire.rightWindow)
							intersectionNumber++;
					}
				}
				output.append("Case #").append(i).append(": ").append(intersectionNumber).append("\n");
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

class Wire implements Comparable{
	int leftWindow;
	int rightWindow;
	
	public Wire(int left, int right){
		this.leftWindow = left;
		this.rightWindow = right;
	}
	
	public int compareTo(Object o){
		Wire another = (Wire)o;
		if(this.leftWindow < another.leftWindow)
			return -1;
		if(this.leftWindow == another.leftWindow)
			return 0;
		if(this.leftWindow > another.leftWindow)
			return 1;
		return 0;
	}
}
