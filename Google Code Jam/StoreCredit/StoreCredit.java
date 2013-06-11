import java.io.*;
import java.util.Arrays;

class StoreCredit{
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
		try{
			File inputData = new File("A-large-practice.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				int credit = Integer.valueOf(br.readLine());
				int itemNumber = Integer.valueOf(br.readLine());
				int[] items = new int[itemNumber+1];
				getItems(br.readLine(), items);
				int[] itemsClone = Arrays.copyOf(items, items.length);
				Arrays.sort(itemsClone);
				int low = 1;
				int high = itemsClone.length - 1;
				while(low < high){
					int sum = itemsClone[low] + itemsClone[high];
					if(sum == credit)
						break;
					else if(sum < credit)
						low++;
					else
						high--;
				}
				boolean sameValue = itemsClone[low] == itemsClone[high];
				low = indexOf(items, itemsClone[low], false);
				high = indexOf(items, itemsClone[high], sameValue);
				output += "Case #"+i+": ";
				output += (low < high ? low+" "+high : high+" "+low);
				output += "\n";				
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
				if(br != null)
					br.close();
				if(outputFile != null)
					outputFile.close();
			}catch(Exception e){
			e.printStackTrace();
			}
			
		}
	}
	
	static void getItems(String line, int[] items){
		String[] itemString = line.split(" ");
		for(int i = 0; i < itemString.length; i++)
			items[i+1] = Integer.valueOf(itemString[i]);
	}
	
	static int indexOf(int[] array, int value, boolean sameValue){
		int valueFoundCount = 0;
		for(int i = 1; i < array.length; i++)
			if(array[i] == value){
				valueFoundCount++;
				if(!sameValue)
					return i;
				else if(valueFoundCount == 2)
					return i;
			}
				
			
		return 0;
	}
}