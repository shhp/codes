fileInput = open("large.in")
caseNumber = int(fileInput.readline().strip())
output = ""
for i in range(1, caseNumber+1):
    NAndM = fileInput.readline().strip().split()
    N = int(NAndM[0])
    M = int(NAndM[1])
    directoryAlreadyExist = ["/"]
    for j in range(N):
        path = fileInput.readline().strip().split("/")
        directoryIdentifier = ""
        for directory in path[1:]:
            directoryIdentifier = directoryIdentifier + "/" + directory
            if not (directoryIdentifier in directoryAlreadyExist):
                directoryAlreadyExist.append(directoryIdentifier)
    #print(directoryAlreadyExist)
    countOfMkdir = 0
    for j in range(M):
        path = fileInput.readline().strip().split("/")
        directoryIdentifier = ""
        for directory in path[1:]: 
            directoryIdentifier = directoryIdentifier + "/" + directory
            if not (directoryIdentifier in directoryAlreadyExist):
                directoryAlreadyExist.append(directoryIdentifier)
                countOfMkdir = countOfMkdir + 1
    output = output + "Case #" + str(i) + ": " + str(countOfMkdir) + "\n"

fileOutput = open("large.txt","w")
fileOutput.write(output)
fileOutput.close()
fileInput.close()
        
            
