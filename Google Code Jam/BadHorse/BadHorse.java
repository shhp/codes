import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


public class BadHorse{

	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;	
		
		try{
			File inputData = new File("small.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			StringBuilder output = new StringBuilder();
			HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>();						
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				pairs.clear();
				int M = scanner.nextInt();
				scanner.nextLine();
				for(int j = 1; j <= M; ++j){
					String pair = scanner.nextLine();
					String[] pairMembers = pair.split(" ");
					String member1 = pairMembers[0];
					String member2 = pairMembers[1];
					
					if(pairs.containsKey(member1))
						pairs.get(member1).add(member2);						
					else{
						ArrayList<String> opponent = new ArrayList<String>();
						opponent.add(member2);
						pairs.put(member1, opponent);
					}
					if(pairs.containsKey(member2))
						pairs.get(member2).add(member1);
					else{
						ArrayList<String> opponent = new ArrayList<String>();
						opponent.add(member1);
						pairs.put(member2, opponent);
					}
				}
								
								
				//System.out.println(isBipartiteGraph(pairs));			
				//System.out.println(Arrays.toString(pairs.keySet().toArray()));			
				output.append("Case #").append(i).append(": ");							
				output.append(isBipartiteGraph(pairs) ? "Yes" : "No").append("\n");
			}
			outputFile = new FileOutputStream(new File("output_small.txt"));
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
	
	/* Breadth first search for determining bipartite graph*/
	static boolean isBipartiteGraph(HashMap<String, ArrayList<String>> pairs){
		HashMap<String, Integer> colors = new HashMap<String, Integer>();
		ArrayList<String> BFSQueue = new ArrayList<String>();
		
		String firstMember = (String)(pairs.keySet().toArray()[0]);
		BFSQueue.add(firstMember);
		colors.put(firstMember, 0);
		while(BFSQueue.size() > 0){
			String member = BFSQueue.remove(0);
			//System.out.println(Arrays.toString(pairs.keySet().toArray()));
			if(pairs.containsKey(member)){
				for(String opponent : pairs.get(member)){
					/* Already colored*/
					if(colors.containsKey(opponent) && colors.get(opponent).equals(colors.get(member)))
						return false;
					
					if(!colors.containsKey(opponent)){
						colors.put(opponent, 1 - colors.get(member));
						BFSQueue.add(opponent);
					}
										
				}
			}			
			
			//BFSQueue.addAll(pairs.get(member));
			
		}
		return true;
	}
}


