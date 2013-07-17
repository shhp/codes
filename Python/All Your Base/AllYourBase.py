def symbolToNumber(string):
    global symbolToDigit

    symbolToDigit[string[0]] = 1
    symbolCount = 1
    for symbol in string[1:]:
        if not (symbol in symbolToDigit.keys()):
            symbolCount = symbolCount + 1
            if(symbolCount == 2):
                symbolToDigit[symbol] = 0
            else:
                symbolToDigit[symbol] = symbolCount - 1


fileInput = open("large.in")
caseNumber = int(fileInput.readline().strip())
output = ""
for i in range(1, caseNumber+1):
    print("-------Case #",i,"------------")
    symbolToDigit = {}
    string = fileInput.readline().strip()
    result = 0
    if(len(string) > 0):
        symbolToNumber(string)
        base = 2 if len(symbolToDigit)==1 else len(symbolToDigit)
        length = len(string)
        for j in range(1, length+1):
            result = result + pow(base, length-j)*symbolToDigit[string[j-1]]
    output = output + "Case #" + str(i) + ": " + str(result) + "\n"

fileOutput = open("large.txt","w")
fileOutput.write(output)
fileOutput.close()
#print(output)
