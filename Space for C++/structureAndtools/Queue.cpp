#include "Queue.h"

template<class T>
Queue<T>::Queue(int size)
{
	this->dataPtr = new T[size];
	this->head = 0;
	this->maxSize = size;
	this->size = 0;
}

template<class T>
Queue<T>::~Queue()
{
	delete[] this->dataPtr;
}

template<class T>
bool Queue<T>::isEmpty() const
{
	return size == 0;
}

template<class T>
bool Queue<T>::isFull() const
{
	return size == maxSize;
}

template<class T>
T Queue<T>::removeHead()
{
	if(!isEmpty())
	{
		T headValue = this->dataPtr[head]; 
		--size == 0 ? head = 0 : head = (head + 1) % maxSize;
		return headValue;
	}
	else
	{
		return NULL;
	}
}

template<class T>
void Queue<T>::addElement(T value)
{
	if(!isFull())
	{
		this->dataPtr[(head + size) % maxSize] = value;
		size++;
	}
}
