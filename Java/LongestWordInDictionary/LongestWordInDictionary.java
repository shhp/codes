/*
* Problem description: 
* You are given a dictionary, in the form of a file that contains one word per line. E.g., 
* abacus 
* deltoid 
* gaff 
* giraffe 
* microphone 
* reef 
* qar 
* You are also given a collection of letters. E.g., {a, e, f, f, g, i, r, q}. 
* The task is to find the longest word in the dictionary that can be spelled with the collection of 
* letters. For example, the correct answer for the example values above is ¡°giraffe¡±. (Note that 
* ¡°reef¡± is not a possible answer, because the set of letters contains only one ¡°e¡±.)
*/

import java.io.*;

public class LongestWordInDictionary{
	public static void main(String[] args){
		char[] LETTERS_COLLECTION = {'a','e','f','f','g','i','r','q'};
		int[] letterCollection = new int[26]; //records how many times a character appears in the letters collection
		for(int i = 0; i < LETTERS_COLLECTION.length; i++)
			letterCollection[LETTERS_COLLECTION[i] - 'a']++; 
			
		/* For each word, construct a vector = new int[26] where vector[i] records how many times the (i+1)th alphabetical letter 
		*  appears in this word. A word in the dictionary that can be spelled with the collection of letters should satisfy:
		*  				vector[c - 'a'] <= letterCollection[c - 'a'] for each character c in the word.
		*  Find the longest word which satisfies the above inequation.
		*/
		int maxLength = 0;
		String longestWord = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
			String word;
			while((word = br.readLine()) != null){
				//System.out.print(line);
				int[] vector = new int[26];
				for(int i = 0; i < word.length(); i++)
					vector[word.charAt(i) - 'a']++;
					
				/* Check whether this word satisfies */		
				int i;
				for(i = 0; i < vector.length; i++){
					if(vector[i] > letterCollection[i])
						break;					
				}
				/* The word satisfies */
				if(i == vector.length && word.length() > maxLength){
					maxLength = word.length();
					longestWord = word;
				}
			}
			
			System.out.println("The longest word is: " + longestWord);
		}catch(Exception e){}
		
	}
}