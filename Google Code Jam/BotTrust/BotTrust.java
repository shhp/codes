import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;


public class BotTrust{

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
			ArrayList<Button> buttons = new ArrayList<Button>();
			ArrayList<Button> buttonsOfO = new ArrayList<Button>();
			ArrayList<Button> buttonsOfB = new ArrayList<Button>();
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");		
				buttons.clear();
				buttonsOfO.clear();
				buttonsOfB.clear();
				
				int N = scanner.nextInt();
				for(int j = 1; j <= N; ++j){
					String robot = scanner.next();
					int index = scanner.nextInt();
					Button button = new Button(robot, index);
					buttons.add(button);
					if(robot.equals("O"))
						buttonsOfO.add(button);
					else
						buttonsOfB.add(button);
					
				}
				
				int currentPositionOfO = 1;
				int currentPositionOfB = 1;
				int time = 0;
				Button nextButton;
				while(buttons.size() > 0){
					nextButton = buttons.remove(0);
					int needTime = 0;
					if(nextButton.robot.equals("O")){
						needTime = Math.abs(nextButton.index - currentPositionOfO) + 1;						
						currentPositionOfO = nextButton.index;
						if(buttonsOfB.size() > 0){
							int nextIndexOfB = buttonsOfB.get(0).index;
							currentPositionOfB = nextIndexOfB >= currentPositionOfB 
							                     ? Math.min(nextIndexOfB, currentPositionOfB + needTime)
								                 : Math.max(nextIndexOfB, currentPositionOfB - needTime);
						}
						buttonsOfO.remove(0);
					}
					else{
						needTime = Math.abs(nextButton.index - currentPositionOfB) + 1;						
						currentPositionOfB = nextButton.index;
						if(buttonsOfO.size() > 0){
							int nextIndexOfO = buttonsOfO.get(0).index;
							currentPositionOfO = nextIndexOfO >= currentPositionOfO 
							                     ? Math.min(nextIndexOfO, currentPositionOfO + needTime)
								                 : Math.max(nextIndexOfO, currentPositionOfO - needTime);
						}
						buttonsOfB.remove(0);
					}
					time += needTime;
				}
				//System.out.println(time);			
				//System.out.println(Arrays.toString(buttonsOfO.toArray()));			
				//System.out.println(Arrays.toString(buttonsOfB.toArray()));			
				output.append("Case #").append(i).append(": ");							
				output.append(time).append("\n");
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

class Button{
	String robot;
	int index;
	
	public Button(String robot, int index){
		this.robot = robot;
		this.index = index;
	}
	
	public String toString(){
		return robot + " " + index;
	}
}


