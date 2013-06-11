import java.io.*;
import java.util.Arrays;

class FairAndSquare{
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("C-large1.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				String[] AandB = br.readLine().split(" ");
				long A = Long.valueOf(AandB[0]);
				long B = Long.valueOf(AandB[1]);
				long fairAndSquareCount = 0;
				for(long j = A; j <= B; j++){
					long squareRoot = isSquareNumber(j);
					if(squareRoot > 0 && isPalindromeNumber(j) && isPalindromeNumber(squareRoot))
						fairAndSquareCount++;
				}
				output += "Case #"+i+": "+fairAndSquareCount;			
				output += "\n";				
			}
			
			outputFile = new FileOutputStream(new File("output_large1.txt"));
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
	
	
	static long isSquareNumber(long number){
		long squareRoot = (long)Math.sqrt(number);
		return squareRoot * squareRoot == number ? squareRoot : -1;
	}
	
	static boolean isPalindromeNumber(long number){
		String numberInString = String.valueOf(number);
		char[] numberChars = numberInString.toCharArray();
		int low = 0;
		int high = numberChars.length - 1;
		while(low < high)
			if(numberChars[low] != numberChars[high])
				return false;
			else{
				low++;
				high--;
			}
		
		return true;
	}
	
}