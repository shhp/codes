import java.io.*;

class ReverseWords{
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
		try{
			File inputData = new File("B-large-practice.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Reverse words line by line */
			String line = null;
			String[] words = null;
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				line = br.readLine();
				output += "Case #"+i+": ";
				if(line != null){
					words = line.split(" ");
					for(int j = words.length - 1; j >=0; j--)
						output += words[j] + " ";
					output += "\n";
				}
			}
			
			outputFile = new FileOutputStream(new File("output.txt"));
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
}