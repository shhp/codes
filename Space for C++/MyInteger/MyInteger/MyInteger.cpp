/*
*  Test operator overload for []. For any given int variable v, v[i] returns the ith digit of v if i is equal 
*  or less than the length of v. Otherwise return -1.
*/


#include "stdafx.h"
#include "../../structureAndtools/widgets.h"

#include <iostream>
#include <cmath>

class MyInteger{
public:
	friend ostream& operator << (ostream& ostream, const MyInteger& mi);
	MyInteger(int value);
	int operator [] (int index) const;
private:
	int value;
	int get_length() const;/* return the length of this integer. Generally, the maximum length is 10. */
	int powerNForTen(int n) const {int result = 1;for(int i = 1; i<=n; i++) result *= 10; return result;};
};

ostream& operator << (ostream& ostream, const MyInteger& mi)
{
	ostream << "Length of the integer " << mi.value << " is : " << mi.get_length() << endl;
	for(int i = 1; i <= mi.get_length() + 1; i++)
		ostream << "	[" << i << "]: " << mi[i] << endl; 
	return ostream;
}

MyInteger::MyInteger(int value):value(value)
{}

int MyInteger::operator[] (int index) const
{	
	if(index == 1)
		return value % 10;
	else if(index <= get_length())
		return (value % powerNForTen(index) )/ (powerNForTen(index - 1));
	else
		return -1;
}

int MyInteger::get_length() const
{
	int i = 1;
	while(this->value / (powerNForTen(i)) > 0)
		i++;

	return i;
}


int _tmain(int argc, _TCHAR* argv[])
{
	MyInteger i1(1065443678);
	MyInteger i2(4);

	cout << i1 << i2;

	int a;
	cin >> a;
	return 0;
}

