import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

class Treasure{

	static boolean problemSolved = false;
	static String solution = "";
	static HashMap<Integer,ArrayList<Integer>> chestToContainingKeys = new HashMap<Integer,ArrayList<Integer>>();
	static HashMap<Integer,ArrayList<Integer>> keyToChestsCanOpen = new HashMap<Integer,ArrayList<Integer>>();	
	static HashMap<Integer,Integer> chestToOpenKeys;
	public static void main(String[] args){
		BufferedReader br = null;
		FileOutputStream outputFile = null;
		
		try{
			File inputData = new File("D-small.in");
			br = new BufferedReader(new FileReader(inputData));
			/* Firstly, read the case counts N */
			int caseCount = Integer.valueOf(br.readLine());
			/* Solve cases one by one */			
			String output = "";
			for(int i = 1; i <= caseCount; i++){
				problemSolved = false;
				solution = "";
				System.out.println("--------Case #" + i + "---------");
				String[] KandN = br.readLine().split(" ");
				int keyNumber = Integer.valueOf(KandN[0]);
				int chestNumber = Integer.valueOf(KandN[1]);
				ArrayList<Integer> keys = new ArrayList<Integer>();
				chestToOpenKeys = new HashMap<Integer,Integer>();
				chestToContainingKeys = new HashMap<Integer,ArrayList<Integer>>();
				keyToChestsCanOpen = new HashMap<Integer,ArrayList<Integer>>();	
			    HashMap<Integer,Integer> chestInLinked = new HashMap<Integer,Integer>();
				HashMap<Integer,ArrayList<Integer>> chestOutLink = new HashMap<Integer,ArrayList<Integer>>();
				HashMap<Integer,Integer> keyNeeded = new HashMap<Integer,Integer>();
				getKeys(br.readLine(), keys);
				for(int j = 1; j <= chestNumber; j++){
					String[] numbers = br.readLine().split(" ");
					int openKey = Integer.valueOf(numbers[0]);
					chestToOpenKeys.put(j, openKey);
					if(!keyToChestsCanOpen.containsKey(openKey)){
						ArrayList<Integer> chestsCanOpen = new ArrayList<Integer>();
						chestsCanOpen.add(j);
						keyToChestsCanOpen.put(openKey, chestsCanOpen);
					}
					else{
						keyToChestsCanOpen.get(openKey).add(j);
					}
					if(!keyNeeded.containsKey(openKey)){
						keyNeeded.put(openKey, 1);
					}
					else{
						int need = keyNeeded.get(openKey);
						keyNeeded.remove(openKey);
						keyNeeded.put(openKey, need+1);
					}
					ArrayList<Integer> containingKeys = new ArrayList<Integer>();
					for(int k = 2; k < numbers.length; k++)
						containingKeys.add(Integer.valueOf(numbers[k]));
					chestToContainingKeys.put(j, containingKeys);
				}
				
				/*for(Integer key : keys){
					if(keyNeeded.containsKey(key)){
					int need = keyNeeded.get(key);
					keyNeeded.remove(key);
					keyNeeded.put(key, need-1);
					}
					
				}*/
				
				for(int j = 1; j <= chestNumber; j++){
					ArrayList<Integer> containingKeys = chestToContainingKeys.get(j);
					if(!chestOutLink.containsKey(j)){
						chestOutLink.put(j, new ArrayList<Integer>());
					}
					for(Integer key : containingKeys)
						if(keyToChestsCanOpen.containsKey(key)){
							for(Integer chest : keyToChestsCanOpen.get(key)){
							if(chest != j && !(chest < j && chestOutLink.get(chest).contains(j))){
								if(!chestInLinked.containsKey(chest)){
								chestInLinked.put(chest, 1);
								}
								else{
									int link = chestInLinked.get(chest);
									chestInLinked.remove(chest);
									chestInLinked.put(chest, link+1);
								}
								chestOutLink.get(j).add(chest);
							}
							
						}
						}
						
				}
				
				//for(Integer key : keyNeeded.keySet())
					//System.out.println("key "+key+" "+keyNeeded.get(key)); 
				//for(Integer chest : chestInLinked.keySet())
					//System.out.println("chest "+chest+" "+chestInLinked.get(chest) + " outlink: " + Arrays.toString(chestOutLink.get(chest).toArray()));
				//openChests(keys, new ArrayList<Integer>(), chestToOpenKeys, chestToContainingKeys, keyToChestsCanOpen);
				ArrayList<Integer> chestOpened = new ArrayList<Integer>();
				while(keys.size() > 0 && chestOpened.size() < chestNumber){
					int j;
					for(j = 1; j <= chestNumber; j++)
						if(!chestOpened.contains(j) && !chestInLinked.containsKey(j))
							break;
					Integer nextChest = 0;
					Integer minChestCanOpen = getMinChestsCanBeOpened(keys, keyToChestsCanOpen, chestOpened);
					if(minChestCanOpen < 0)
						break;
					else if(j == chestNumber+1)
						nextChest = minChestCanOpen;
					else{
						int keyType = chestToOpenKeys.get(minChestCanOpen);
						nextChest = (canChooseMinChest(keyType, currentKeyTypeNumber(keys, keyType), keyNeeded.get(keyType), keys, minChestCanOpen)  ? minChestCanOpen : j);
					}
					System.out.println("nextChest "+nextChest);
					keys.remove(chestToOpenKeys.get(nextChest));
					keys.addAll(chestToContainingKeys.get(nextChest));
					for(Integer key : chestToContainingKeys.get(nextChest))
						if(keyToChestsCanOpen.containsKey(key)){
							for(Integer chest : keyToChestsCanOpen.get(key)){
								if(chestInLinked.containsKey(chest)){
									int link = chestInLinked.get(chest);
									if(link - 1 == 0)
										chestInLinked.remove(chest);
									else{
										chestInLinked.remove(chest);
										chestInLinked.put(chest, link-1);		
									}
								}
								
						}
						}
						
					chestOpened.add(nextChest);
					int keyType = chestToOpenKeys.get(nextChest);
					int need = keyNeeded.get(keyType);
					keyNeeded.remove(keyType);
					keyNeeded.put(keyType, need-1);
				}
				output += "Case #"+i+": ";
				if(chestOpened.size() == chestNumber)
					for(Integer chest : chestOpened)
						output += chest + " ";
				else
					output += "IMPOSSIBLE";
				output += "\n";				
			}
			
			outputFile = new FileOutputStream(new File("output_small.txt"));
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
	
	static boolean canChooseMinChest(int keyType, int currentKeyNumber, int keyNeeded, ArrayList<Integer> keys, Integer minCanOpenChest){
		if(currentKeyNumber >= keyNeeded)
			return true;
		
		int number = 0;
		for(Integer key : keys){
			if(key == chestToOpenKeys.get(minCanOpenChest))
				number += currentKeyTypeNumber(chestToContainingKeys.get(minCanOpenChest), keyType);
			else
				number += minKey(keyType, key);
		}
		
		if(number >= keyNeeded-1)
			return true;
		else 
			return false;
	}
	
	static int minKey(int keyType, Integer key){
		int result = Integer.MAX_VALUE;
		for(Integer chest : keyToChestsCanOpen.get(key)){
			int number = currentKeyTypeNumber(chestToContainingKeys.get(chest), keyType);
			if(number < result)
				result = number;
		}
		return result;
	}
	
	static void getKeys(String line, ArrayList<Integer> keys){
		String[] keysString = line.split(" ");
		for(String key : keysString)
			keys.add(Integer.valueOf(key));
	}
	
	static int currentKeyTypeNumber(ArrayList<Integer> keys, int keyType){
		int number = 0;
		for(int key : keys)
			if(key == keyType)
				number++;
		return number;
	}
	
	static Integer getMinChestsCanBeOpened(ArrayList<Integer> keys, HashMap<Integer,ArrayList<Integer>> keyToChestsCanOpen,ArrayList<Integer> chestsOpened){
		ArrayList<Integer> result = new ArrayList<Integer>();		
		ArrayList<Integer> keyAlreadyCount = new ArrayList<Integer>();		
		for(Integer key : keys)
			if(!keyAlreadyCount.contains(key)){
				result = merge(result, keyToChestsCanOpen.get(key));
				keyAlreadyCount.add(key);
			}
		for(Integer chest : chestsOpened)
			result.remove(chest);
		
		if(result.size() > 0)
			return result.get(0);
		else
			return -1;
	}
	
	static ArrayList<Integer> getChestsCanBeOpened(ArrayList<Integer> keys, HashMap<Integer,ArrayList<Integer>> keyToChestsCanOpen,ArrayList<Integer> chestsOpened){
		ArrayList<Integer> result = new ArrayList<Integer>();		
		ArrayList<Integer> keyAlreadyCount = new ArrayList<Integer>();		
		for(Integer key : keys)
			if(!keyAlreadyCount.contains(key)){
				result = merge(result, keyToChestsCanOpen.get(key));
				keyAlreadyCount.add(key);
			}
		for(Integer chest : chestsOpened)
			result.remove(chest);
		return result;
	}
	
	static ArrayList<Integer> merge(ArrayList<Integer> a1, ArrayList<Integer> a2){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(a2 == null) return result;
 		
		int index1 = 0;
		int index2 = 0;		
		while(index1 < a1.size() && index2 < a2.size()){
			int value1 = a1.get(index1);
			int value2 = a2.get(index2);
			if(value1 < value2){
				result.add(value1);
				index1++;
			}
			else if(value1 == value2){
				result.add(value1);
				index1++;
				index2++;
			}
			else{
				result.add(value2);
				index2++;
			}
		}
		
		for(int i = index1; i < a1.size(); i++)
			result.add(a1.get(i));
		for(int i = index2; i < a2.size(); i++)
			result.add(a2.get(i));
			
		return result;
	}
	
	/* Using backtracking to solve the problem */
	static void openChests(ArrayList<Integer> keys, ArrayList<Integer> chestsOpened, HashMap<Integer,Integer> chestToOpenKeys, 
	                       HashMap<Integer,ArrayList<Integer>> chestToContainingKeys, HashMap<Integer,ArrayList<Integer>> keyToChestsCanOpen){
		int chestNumber = chestToOpenKeys.keySet().size();
		ArrayList<Integer> chestsCanBeOpened = getChestsCanBeOpened(keys, keyToChestsCanOpen, chestsOpened);
		if(keys.size() >= chestsCanBeOpened.size() && chestsOpened.size() + chestsCanBeOpened.size() == chestNumber){//chestsOpened.size() == chestNumber
			//System.out.println(Arrays.toString(chestsOpened.toArray()));
			problemSolved = true;
			for(Integer chest : chestsOpened)
				solution += chest + " ";
			for(Integer chest : chestsCanBeOpened)
				solution += chest + " ";
			return;
		}
		
		
		for(Integer chest : chestsCanBeOpened){
			ArrayList<Integer> keysClone = (ArrayList<Integer>)(keys.clone());
			ArrayList<Integer> chestsOpenedClone = (ArrayList<Integer>)(chestsOpened.clone());
			chestsOpenedClone.add(chest);
			keysClone.remove(keysClone.indexOf(chestToOpenKeys.get(chest)));
			keysClone.addAll(chestToContainingKeys.get(chest));
			if(keysClone.size() > 0)
				openChests(keysClone, chestsOpenedClone, chestToOpenKeys, chestToContainingKeys, keyToChestsCanOpen);
			if(problemSolved)
				break;
		}
	}
	
	static void printCase(ArrayList<Integer> keys, HashMap<Integer,Integer> chestToOpenKeys, 
	                      HashMap<Integer,ArrayList<Integer>> chestToContainingKeys, HashMap<Integer,ArrayList<Integer>> keyToChestsCanOpen){
		System.out.print("keys: ");
		System.out.println(Arrays.toString(keys.toArray()));
		for(Integer chest : chestToOpenKeys.keySet())
			System.out.println("chest #" + chest + " need key " + chestToOpenKeys.get(chest) + "  containing:" + Arrays.toString(chestToContainingKeys.get(chest).toArray()));
		for(Integer key : keyToChestsCanOpen.keySet())
			System.out.println("key #" + key + " can open " +  Arrays.toString(keyToChestsCanOpen.get(key).toArray()));
	}
}