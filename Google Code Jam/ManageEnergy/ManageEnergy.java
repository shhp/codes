import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class ManageEnergy{
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("small.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");
				int maxEnergy = scanner.nextInt();
				int currentEnergy = maxEnergy;
				int regain = scanner.nextInt();
				regain = regain > currentEnergy ? currentEnergy : regain;
				int activityNumber = scanner.nextInt();
				int[] activityValues = new int[activityNumber];
				ArrayList<Activity> activities = new ArrayList<Activity>();
				for(int j = 0; j < activityNumber; j++){
					int value = scanner.nextInt();
					activities.add(new Activity(value, j));
					activityValues[j] = value;
				}
				Collections.sort(activities);
				int index = 0;
				long gain = 0;
				while(index < activityNumber){
					Activity activityWithMostValue = activities.remove(0);
					int position = activityWithMostValue.position;
					while(index < position){
						int possibleUseEnergy = currentEnergy + regain - maxEnergy;
						int useEnergy = possibleUseEnergy < 0 ? 0 : possibleUseEnergy;
						gain += useEnergy * activityValues[index];
						currentEnergy = currentEnergy - useEnergy + regain;
						activities.remove(new Activity(0, index));
						index++;
					}
					gain += currentEnergy * activityWithMostValue.value;
					currentEnergy = regain;
					index++;
				}
				
				output += "Case #"+i+": " + gain + "\n";
			}
			outputFile = new FileOutputStream(new File("output_small.txt"));
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

class Activity implements Comparable{
	int value;
	int position;
	
	public Activity(int value, int position){
		this.value = value;
		this.position = position;
	}
	
	public int compareTo(Object o){
		Activity another = (Activity)o;
		if(this.value < another.value)
			return 1;
		if(this.value == another.value)
			return 0;
		if(this.value > another.value)
			return -1;
		return 0;
	}
	
	public boolean equals(Object o){
		Activity a = (Activity)o;
		return this.position == a.position;
	}
}
