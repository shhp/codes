
//#include "BinaryTreeNode.h"

/**
* Class for queue
*/

#ifndef QUEUE_H
#define QUEUE_H

template <class T>
class Queue
{
public:
	Queue(int size);
	~Queue();
	bool isEmpty() const;
	bool isFull() const;
	T removeHead();
	void addElement(T value);

private:
	int size;
	int maxSize;
	T* dataPtr; // The pointer points to the storage position of the queue's data
	int head;// Points to the head of the queue
	//int tail;//Points to the tail of the queue
};

#endif