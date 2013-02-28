/*
* Author: Wang Chong
*
* A simple console memory mathch game
*/

// MemoryMatchGame.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"

#include "../../structureAndtools/widgets.h"

#include <iostream>
#include <Windows.h>

using namespace std;

void displayCard(int [][4],int,bool [][4]);
void shuffleCard(int [][4],int);
void playGame(int [][4],int,bool [][4]);


int _tmain(int argc, _TCHAR* argv[])
{
	int card[4][4] = {{1,2,3,4},{5,6,7,8},{1,2,3,4},{5,6,7,8}};
	bool cardFaceup[4][4] = {{false,false,false,false},{false,false,false,false},{false,false,false,false},{false,false,false,false}};
	bool cardFaceupTest[4][4] = {{true,true,true,true},{true,true,true,true},{true,true,true,true},{true,true,true,true}};
	
	shuffleCard(card,4);
	displayCard(card,4,cardFaceup);
	playGame(card,4,cardFaceup);

	int a;
	cin >> a;
	return 0;
}

void displayCard(int card[][4],int cardCount,bool cardFaceup[][4])
{
	for(int i=0; i<cardCount;i++)
	{
		for(int j=0;j < 4; j++)
			if(cardFaceup[i][j])
				cout << card[i][j] << "	";
			else
				cout << "*	";
		cout << endl;
	}
}

void shuffleCard(int card[][4],int cardCount)
{
	int swapTimes = 100;
	srand( time( 0 ) );
	for(int i=0;i< swapTimes;i++)
	{		
		int row1 = generateRandomNumber(cardCount), column1=generateRandomNumber(cardCount);
		int row2 = generateRandomNumber(cardCount), column2=generateRandomNumber(cardCount);
		//cout << row1 << "  " << column1 << "  " << row2 << "  " << column2 << endl;
		swap(card[row1][column1],card[row2][column2]);
	}
}

void playGame(int card[][4],int cardCount,bool cardFaceup[][4])
{
	int matchedPairs = 0;
	int totalPairs = cardCount * cardCount / 2;

	int row1, column1, row2, column2;
	while(matchedPairs < totalPairs)
	{
		cout << "\nPlease input two cards' indexes (e.g. 2 2 3 1): ";
		cin >> row1 >> column1 >> row2 >> column2;

		cardFaceup[row1][column1] = cardFaceup[row2][column2] = true;
		displayCard(card,4,cardFaceup);

		Sleep(2000);

		if(card[row1][column1] == card[row2][column2])
		{
			matchedPairs++;
		}
		else
		{
			for(int i=0; i<50; i++)
				cout << endl;

			cardFaceup[row1][column1] = cardFaceup[row2][column2] = false;
			displayCard(card,4,cardFaceup);
		}
	}
}