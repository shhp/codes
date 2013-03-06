#pragma once

#include "../../structureAndtools/BinaryTreeNode.h"

#ifndef HEAP_H
#define HEAP_H

//class BinaryTreeNode<T>;
/**
* Heap implemented by binary tree.
*/
template<class T>
class Heap
{
public:
	Heap(void);
	Heap(T values[],int size);
	void printHeap(BinaryTreeNode<T>* currentNode);
	BinaryTreeNode<T>* getRoot() const;
	T removeHead();
	BinaryTreeNode<T>* getLastLeaf() const;
	~Heap(void);

private:
	BinaryTreeNode<T>* root;
	int size;
	void releaseNode(BinaryTreeNode<T>*);
	void deleteNode(BinaryTreeNode<T>*);
	void siftDown(BinaryTreeNode<T>*);
};

#endif
