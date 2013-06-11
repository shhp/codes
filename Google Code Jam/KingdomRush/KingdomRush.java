import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

class KingdomRush{
	public static void main(String[] args){
		Scanner scanner = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("large.in");
			scanner = new Scanner(inputData);
			/* Firstly, read the case counts N */
			int caseNumber = scanner.nextInt();
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseNumber; i++){
				System.out.println("--------Case #" + i + "---------");
				ArrayList<OneRatingLevel> oneRatingLevelList = new ArrayList<OneRatingLevel>();
				ArrayList<TwoRatingLevel> twoRatingLevelList = new ArrayList<TwoRatingLevel>();
				int levelNumber = scanner.nextInt();
				for(int j = 1; j <= levelNumber; j++){
					int oneRationgStars = scanner.nextInt();
					int twoRationgStars = scanner.nextInt();
					//System.out.println("Level #" + j + ": 1-rating:" + oneRationgStars  + " 2-rating:" + twoRationgStars);
					oneRatingLevelList.add(new OneRatingLevel(j, oneRationgStars));
					twoRatingLevelList.add(new TwoRatingLevel(j, twoRationgStars));
				}
				
				Object[] oneRatingListSort = oneRatingLevelList.toArray();
				Object[] twoRatingListSort = twoRatingLevelList.toArray();
				Arrays.sort(oneRatingListSort);
				Arrays.sort(twoRatingListSort);
				oneRatingLevelList.clear();
				for(Object o : oneRatingListSort){
					OneRatingLevel l = (OneRatingLevel)o;
					oneRatingLevelList.add(l);
					//System.out.println("one rating level #" + l.level + " stars:" + l.needStars);
				}
				twoRatingLevelList.clear();
				for(Object o : twoRatingListSort){
					TwoRatingLevel l = (TwoRatingLevel)o;
					twoRatingLevelList.add(l);
					//System.out.println("two rating level #" + l.level + " stars:" + l.needStars);
				}
				
				int currentStars = 0;
				int playTimes = 0;
				while(twoRatingLevelList.size() > 0){
					TwoRatingLevel tRatingLevel = twoRatingLevelList.get(0);
					if(tRatingLevel.needStars <= currentStars){
						playTimes++;
						OneRatingLevel l = new OneRatingLevel(tRatingLevel.level, 0);
						if(oneRatingLevelList.contains(l)){
							currentStars += 2;
							oneRatingLevelList.remove(l);
						}
						else
							currentStars += 1;
							
						twoRatingLevelList.remove(0);						
					}
					else{
						if(oneRatingLevelList.size() > 0){
							OneRatingLevel oRatingLevel = oneRatingLevelList.get(0);							
							if(oRatingLevel.needStars <= currentStars){
								int maxNeedStars = twoRatingLevelList.get(twoRatingLevelList.indexOf(new TwoRatingLevel(oRatingLevel.level, 0))).needStars;
								int maxIndex = 0;
								int index = 1;
								while(index < oneRatingLevelList.size()){
									OneRatingLevel l = oneRatingLevelList.get(index);
									if(l.needStars <= currentStars){
										TwoRatingLevel twoL =  twoRatingLevelList.get(twoRatingLevelList.indexOf(new TwoRatingLevel(l.level, 0)));
										if(twoL.needStars > maxNeedStars){
											maxNeedStars = twoL.needStars;
											maxIndex = index;
										}
										index++;
									}
									else
										break;
								}
								playTimes++;
								currentStars += 1;
								oneRatingLevelList.remove(maxIndex);
							}
							else
								break;
						}
						else
							break;
					}
				}
				output += "Case #"+i+": ";			
				if(twoRatingLevelList.size() == 0)
					output += playTimes;
				else
					output += "Too Bad";
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

class OneRatingLevel implements Comparable{
	int level;
	int needStars;
	
	public OneRatingLevel(int level, int needStars){
		this.level = level;
		this.needStars = needStars;
	}
	
	public int compareTo(Object o){
		OneRatingLevel another = (OneRatingLevel)o;
		if(this.needStars < another.needStars)
			return -1;
		if(this.needStars == another.needStars)
			return 0;
		if(this.needStars > another.needStars)
			return 1;
		
		return 0;
	}
	
	public boolean equals(Object o){
		OneRatingLevel another = (OneRatingLevel)o;
		return this.level == another.level;
	}
}

class TwoRatingLevel implements Comparable{
	int level;
	int needStars;
	
	public TwoRatingLevel(int level, int needStars){
		this.level = level;
		this.needStars = needStars;
	}
	
	public int compareTo(Object o){
		TwoRatingLevel another = (TwoRatingLevel)o;
		if(this.needStars < another.needStars)
			return -1;
		if(this.needStars == another.needStars)
			return 0;
		if(this.needStars > another.needStars)
			return 1;
		return 0;
	}
	
	public boolean equals(Object o){
		TwoRatingLevel another = (TwoRatingLevel)o;
		return this.level == another.level;
	}
}