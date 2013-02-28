/*
* Author: Wang Chong
*
* Some usefule pieces of codes including:
* - A random number generator
* - A swap function
*/

#include <ctime>
#include <cstdlib> 

using namespace std;

int generateRandomNumber(int maxNumber)
{
	return rand() % maxNumber;
}

void swap(int &a,int &b)
{
	int temp = a;
	a = b;
	b = temp;
}