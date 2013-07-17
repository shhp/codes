#find the longest substring without duplicate letters

def longestSubString(string):
        maxLength = 0;
        longestSubstringStartIndex = 0;
        start = 0;
        letterLastIndex = {};

        for i in range(0, len(string)):
                if(string[i] in letterLastIndex):
                        if(letterLastIndex[string[i]] >= start):
                                curLength = i - start;
                                if(curLength > maxLength):
                                	maxLength = curLength;
                                	longestSubstringStartIndex = start;
                                start = letterLastIndex[string[i]] + 1;
                                letterLastIndex[string[i]] = i;
                        else:
                        	letterLastIndex[string[i]] = i;
                else:
                	letterLastIndex[string[i]] = i;
        curLength = i - start + 1;
        if(curLength > maxLength):
        	maxLength = curLength;
        	longestSubstringStartIndex = start;
        return {"start":longestSubstringStartIndex, "length":maxLength};

print (longestSubString("abacdsgetvhaqw"));
