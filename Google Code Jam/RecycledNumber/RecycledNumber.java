import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;

class RecycledNumber{
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;
		
		HashMap<Integer, ArrayList<Integer>> recycledNumberPair = new HashMap<Integer, ArrayList<Integer>>();
		//generatePair(recycledNumberPair);
		/*for(Integer i : recycledNumberPair.keySet()){
			//Integer[] pair = null;
			ArrayList<Integer> thePair = recycledNumberPair.get(i);
			//thePair.toArray(pair);
			System.out.println(i + ": " + Arrays.toString(thePair.toArray()));
		}*/
		
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
				int B = scanner.nextInt();
				int count = 0;
				for(int j = A; j < B; ++j){
					
					String numberStr = String.valueOf(j);
					if(shouldShift(numberStr)){	//				
						for(int k = 1; k < numberStr.length(); ++k){
							int shiftedNumber = numberLeftShiftN(numberStr.toCharArray(), k);
							if(shiftedNumber == j)
								break;
							if(shiftedNumber > j && shiftedNumber <= B)
								++count;
						}
					}
				}
				output.append("Case #").append(i).append(": ").append(count).append("\n");
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
	
	static int solve(int A, int B) {
		int power = 1, temp = A;
		while (temp >= 10) {
			power *= 10;
			temp /= 10;
		}
		int result = 0;
		for (int n = A; n <= B; ++n) {
			temp = n;
			while (true) {
				temp = (temp / 10) + ((temp % 10) * power);
				if (temp == n)
					break;
				if (temp > n && temp <= B)
					result++;
			}
		}
		return result;
}
	
	static void generatePair(HashMap<Integer, ArrayList<Integer>> recycledNumberPair){
		for(int i = 1; i <= 2000000; ++i){
			String numberStr = String.valueOf(i);
			if(shouldShift(numberStr)){
				System.out.println(i);
				ArrayList<Integer> recycledNumber = new  ArrayList<Integer>();
				for(int j = 1; j < numberStr.length(); ++j){
					int shiftedNumber = numberLeftShiftN(numberStr.toCharArray(), j);
					if(shiftedNumber > i)
						recycledNumber.add(shiftedNumber);
				}
				recycledNumberPair.put(i, recycledNumber);
			}
			
		}
	}
	
	static boolean shouldShift(String number){
		char firstDigit = number.charAt(0);
		for(int i = 1; i < number.length(); ++i){
			if(number.charAt(i) - firstDigit >= 0)
				return true;
		}
		
		return false;
	}
	
	static int numberLeftShiftN(char[] numberInChar, int N){
		if(numberInChar[N] - numberInChar[0] < 0)
			return 0;
		
		rotate(numberInChar, 0, N-1);
		rotate(numberInChar, N, numberInChar.length-1);
		rotate(numberInChar, 0, numberInChar.length-1);
		
		return Integer.valueOf(new String(numberInChar));
	}
	
	static void rotate(char[] numberInChar, int left, int right){
		while(left < right){
			char temp = numberInChar[left];
			numberInChar[left] = numberInChar[right];
			numberInChar[right] = temp;
			
			++left;
			--right;
		}
	}
}

