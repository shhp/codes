import java.io.*;
import java.util.Arrays;

class Lawnmover{
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("B-large.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				System.out.println("--------Case #" + i + "---------");
				String[] NandM = br.readLine().split(" ");
				int N = Integer.valueOf(NandM[0]);
				int M = Integer.valueOf(NandM[1]);
				int[][] lawnPattern = new int[N][M];
				
				for(int j = 0; j < N; j++){
					String[] line = br.readLine().split(" ");
					for(int k = 0; k < M; k++)
						lawnPattern[j][k] = Integer.valueOf(line[k]);
				}
				
				int[] minHeightForRow = new int[N];
				int[] minHeightForColumn = new int[M];
				checkMinHeight(minHeightForRow, minHeightForColumn, lawnPattern);				
				output += "Case #"+i+": ";
				//System.out.println(Arrays.toString(minHeightForRow));
			   // System.out.println(Arrays.toString(minHeightForColumn));
						
				if(isPatternPossible(minHeightForRow, minHeightForColumn, lawnPattern))
					output += "YES";
				else
					output += "NO";
				//printPattern(lawnPattern);
							
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
	
	static void printPattern(int[][] pattern){
		for(int i = 0; i < pattern.length; i++){
			for(int j = 0; j < pattern[i].length; j++)
				System.out.print(pattern[i][j] + " ");
			System.out.println();
		}
	}
	
	static void checkMinHeight(int[] row, int[] column, int[][] lawnPattern){
		for(int i = 0; i < row.length; i++){
			int max = 0;
			for(int j = 0; j < column.length; j++)
				if(lawnPattern[i][j] > max)
					max = lawnPattern[i][j];
			row[i] = max;
		}
		
		for(int j = 0; j < column.length; j++){
			int max = 0;
			for(int i = 0; i < row.length; i++)
				if(lawnPattern[i][j] > max)
					max = lawnPattern[i][j];
			column[j] = max;
		}
	}
	
	
	static boolean isPatternPossible(int[] rowHeight, int[] columnHeight, int[][] lawnPattern){
		for(int i = 0; i < rowHeight.length; i++)
			for(int j = 0; j < columnHeight.length; j++){
				if(lawnPattern[i][j] < rowHeight[i] && lawnPattern[i][j] < columnHeight[j])
					return false;
			}
			
		return true;
	}
	
}