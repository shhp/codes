import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

class TicTacToe{
	enum Symbol {T, X, O, Empty}
	static int[][] POSSIBLE_WIN_POSITIONS = {
												{0,1,2,3},
												{0,4,8,12},
												{0,5,10,15},
												{4,5,6,7},
												{8,9,10,11},
												{12,13,14,15},
												{1,5,9,13},
												{2,6,10,14},
												{3,7,11,15},
												{3,6,9,12}
											};
	static int emptyCount;
	static Symbol[] symbols;
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
				
		try{
			File inputData = new File("A-large.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				emptyCount = 0;
				String board = "";
				for(int j = 1; j <=4; j++)
					board += br.readLine();
				symbols = parseBoard(board);
				//System.out.println(checkGameState());
				output += "Case #"+i+": ";
				output += checkGameState() + "\n";
				
				br.readLine();//omit the empty line between cases
										
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
	
	static Symbol[] parseBoard(String board){
		char[] symbol = board.toCharArray();
		Symbol[] symbols = new Symbol[16];
		for(int i = 0; i< symbol.length; i++){
			if(symbol[i] == 'T')
				symbols[i] = Symbol.T;
			else if(symbol[i] == 'X')
				symbols[i] = Symbol.X;
			else if(symbol[i] == 'O')
				symbols[i] = Symbol.O;
			else{
				symbols[i] = Symbol.Empty;
				emptyCount++;
			}
		}
		
		return symbols;
	}
	
	static String checkGameState(){
		if(emptyCount >= 13) return "Game has not completed";
		
 		for(int i = 0; i < 10; i++){
			Symbol s = possibleWinner(i);
			if(s == Symbol.X)
				return "X won";
			else if(s == Symbol.O)
				return "O won";
		}
		
		if(emptyCount == 0)
			return "Draw";
		else
			return "Game has not completed";
	}
	
	static Symbol possibleWinner(int winPositionIndex){	
		int TPosition = containsT(winPositionIndex);
		if(TPosition >= 0){
			Symbol previousSymbol = null;
			for(int i = 0; i < 4; i++)
				if(POSSIBLE_WIN_POSITIONS[winPositionIndex][i] != TPosition){
					if(previousSymbol == null)
						previousSymbol = symbols[POSSIBLE_WIN_POSITIONS[winPositionIndex][i]];
					else if(previousSymbol != symbols[POSSIBLE_WIN_POSITIONS[winPositionIndex][i]])
						return null;
				}
			return previousSymbol;
		}
		else{
			for(int i = 1; i < 4; i++)
				if(symbols[POSSIBLE_WIN_POSITIONS[winPositionIndex][i]] != symbols[POSSIBLE_WIN_POSITIONS[winPositionIndex][i-1]])
					return null;
			return symbols[POSSIBLE_WIN_POSITIONS[winPositionIndex][0]];
		}
	}
	
	static int containsT(int winPositionIndex){
		for(int i = 0; i < 4; i++)
			if(symbols[POSSIBLE_WIN_POSITIONS[winPositionIndex][i]] == Symbol.T)
				return POSSIBLE_WIN_POSITIONS[winPositionIndex][i];
				
		return -1;
	}
}