/**
* 问题：m个珠子围成一圈，每一个珠子具有一种颜色，共n种。找出其中的一段，使其包含所有n种颜色的珠子并且长度最短。
*
* 我的算法时间复杂度为O(n^2)
*/

public class ShortestFullColor{

	public static void shortestFullColor(int[] bubbles, int colorNum){
	    int bubbleNum = bubbles.length;
	    int head;
	    int windowSize = colorNum;
	    int[] colorCounter;
	    int currentColorNum;
	    
	    while(windowSize <= bubbleNum){
	        head = currentColorNum = 0;
	        colorCounter = new int[colorNum];
	        for(int i = 0; i < windowSize; ++i){
	            if(++colorCounter[bubbles[i]] == 1)
	                currentColorNum++;
	        }
	        
	        while(head < bubbleNum){
	            if(currentColorNum == colorNum){
	                System.out.println("begin:"+head + " length:"+windowSize);
	                return;
	            }
	            
	            if(--colorCounter[bubbles[head]] == 0)
	                currentColorNum--;
	            if(colorCounter[bubbles[(head+windowSize)%bubbleNum]]++ == 0)
                    currentColorNum++;
	            
	            head++;
	        }
	        
	        windowSize++;
	    }
	    
	}

}