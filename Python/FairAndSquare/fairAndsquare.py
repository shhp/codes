import sys

def isPalindrome(number):
    string = str(number)
    if(len(string) == 0):
        return False
    low = 0
    high = len(string) - 1
    while(low < high):
        if(string[low] != string[high]):
            return False
        else:
            low = low + 1
            high = high - 1

    return True

def generatePalindromeLessThan(value):
    length = len(str(value))
    for i in range(1, length):
        generatePalindromeWithLength(i)
    
    generatePalindromeWithLengthAndLessThanValue(length, value)

def generatePalindromeWithLength(length):
    global fairAndSquares
    minValue = int("1"+"0"*int(length/2 - 1)) if length%2 == 0 else int("1"+"0"*int(length/2))
    maxValue = int("9"*int(length/2)) if length%2 == 0 else int("9"*(int(length/2)+1))
    for i in range(minValue, maxValue+1):
        generatedPalindrome = mirror(i, int(length/2))
        square = generatedPalindrome * generatedPalindrome
        if(isPalindrome(square)):
            fairAndSquares.append(square)

def generatePalindromeWithLengthAndLessThanValue(length, value):
    global fairAndSquares
    minValue = int("1"+"0"*int(length/2 - 1)) if length%2 == 0 else int("1"+"0"*int(length/2))
    maxValue = int("9"*int(length/2)) if length%2 == 0 else int("9"*(int(length/2)+1))
    for i in range(minValue, maxValue+1):
        generatedPalindrome = mirror(i, int(length/2))        
        if generatedPalindrome < value:
            square = generatedPalindrome * generatedPalindrome
            if(isPalindrome(square)):
                fairAndSquares.append(square)
        else:
            break

def mirror(value, length):
    valueInString = str(value)
    for i in range(length):
        valueInString = valueInString + valueInString[length-1-i]
    return int(valueInString)

fairAndSquares = []
generatePalindromeLessThan(10000000)
print("fair and square number:", len(fairAndSquares))
fileInput = open("large.in")
caseNumber = int(fileInput.readline().strip())
output = ""
for i in range(1, caseNumber+1):
    #print("-------Case #",i,"------------")
    AandB = fileInput.readline().strip().split()
    A = int(AandB[0])
    B = int(AandB[1])
    indexA = 0
    while(indexA < len(fairAndSquares) and fairAndSquares[indexA] < A):
        indexA = indexA + 1
    indexB = 0
    while(indexB < len(fairAndSquares) and fairAndSquares[indexB] <= B):
        indexB = indexB + 1
    output = output + "Case #" + str(i) + ": " + str(indexB - indexA) + "\n"
#print(output)
fileOutput = open("large.txt", "w")
fileOutput.write(output)
fileOutput.close()



