import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

class Spelling{
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
		HashMap<String,String> letterCode = new HashMap<String,String>();
		HashMap<String,Integer> letterKey = new HashMap<String,Integer>();
		init(letterCode, letterKey);
		try{
			File inputData = new File("C-large-practice.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				char[] letters = br.readLine().toCharArray();
				output += "Case #"+i+": ";
				for(int j = 0; j < letters.length; j++){					
					if(j > 0 && letterKey.get(""+letters[j-1]) == letterKey.get(""+letters[j]))
						output += " ";
					output += letterCode.get(""+letters[j]);
				}
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
	
	static void init(HashMap<String,String> letterCode, HashMap<String,Integer> letterKey){
		letterCode.put("a","2");
		letterCode.put("b","22");
		letterCode.put("c","222");
		letterCode.put("d","3");
		letterCode.put("e","33");
		letterCode.put("f","333");
		letterCode.put("g","4");
		letterCode.put("h","44");
		letterCode.put("i","444");
		letterCode.put("j","5");
		letterCode.put("k","55");
		letterCode.put("l","555");
		letterCode.put("m","6");
		letterCode.put("n","66");
		letterCode.put("o","666");
		letterCode.put("p","7");
		letterCode.put("q","77");
		letterCode.put("r","777");
		letterCode.put("s","7777");
		letterCode.put("t","8");
		letterCode.put("u","88");
		letterCode.put("v","888");
		letterCode.put("w","9");
		letterCode.put("x","99");
		letterCode.put("y","999");
		letterCode.put("z","9999");
		letterCode.put(" ","0");
		
		letterKey.put("a",2);
		letterKey.put("b",2);
		letterKey.put("c",2);
		letterKey.put("d",3);
		letterKey.put("e",3);
		letterKey.put("f",3);
		letterKey.put("g",4);
		letterKey.put("h",4);
		letterKey.put("i",4);
		letterKey.put("j",5);
		letterKey.put("k",5);
		letterKey.put("l",5);
		letterKey.put("m",6);
		letterKey.put("n",6);
		letterKey.put("o",6);
		letterKey.put("p",7);
		letterKey.put("q",7);
		letterKey.put("r",7);
		letterKey.put("s",7);
		letterKey.put("t",8);
		letterKey.put("u",8);
		letterKey.put("v",8);
		letterKey.put("w",9);
		letterKey.put("x",9);
		letterKey.put("y",9);
		letterKey.put("z",9);
		letterKey.put(" ",0);
	}
	
}