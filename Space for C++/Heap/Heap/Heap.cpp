// Heap.cpp : realize the data structure: Heap
//

#include "stdafx.h"
#include "Heap.h"
#include "../../structureAndtools/Queue.cpp"
#include<iostream>

using namespace std;


template<class T>
void swapValue(T& variable1, T& variable2)
{
	T temp = variable1;
	variable1 = variable2;
	variable2 = temp;
}

int _tmain(int argc, _TCHAR* argv[])
{
	int values[] = {0,50,4,3,2};
	Heap<int> heap(values,4);
    //heap.removeHead();
	//heap.removeHead();	
	//heap.insert(2);
	//heap.insert(3);
	//heap.insert(50);
	//heap.insert(4);
	heap.insert(52);
	cout << "After operating, heap is: ";
	heap.printHeap(heap.getRoot());
	//cout << heap.getLastLeaf()->value;

	int a;
	cin >> a;
	return 0;
}


template<class T>
Heap<T>::Heap(void)
{
}

template<class T>
Heap<T>::Heap(T values[],int size)
{
	BinaryTreeNode<T>** nodes = new BinaryTreeNode<T>*[size+1];
	nodes[0] = NULL;

	for(int i = 1; i <= size; i++)
	{
		nodes[i] = new BinaryTreeNode<T>(values[i]);
	}
	for(int i = 1; i <= size; i++)
	{
		nodes[i]->parent = nodes[i/2];
		if(2 * i <= size)
			nodes[i]->leftChild = nodes[2*i];
		if(2 * i + 1 <= size)
			nodes[i]->rightChild = nodes[2*i+1];
	}

	this->size = size;
	this->root = size > 0 ? nodes[1] : NULL;

	delete[] nodes;
}

template<class T>
BinaryTreeNode<T>* Heap<T>::getRoot() const
{
	return this->root;
}

template<class T>
void Heap<T>::printHeap(BinaryTreeNode<T>* currentNode)
{
	if(currentNode != NULL)
	{
		Queue<BinaryTreeNode<T>*> queue(this->size);
		queue.addElement(currentNode);
		while(!queue.isEmpty())
		{
			BinaryTreeNode<T>* node = queue.removeHead();
			cout << node->value << ",";
			if(node->leftChild != NULL) queue.addElement(node->leftChild);
			if(node->rightChild != NULL) queue.addElement(node->rightChild);
		}
		
	}
}

template<class T>
T Heap<T>::removeHead()
{
	if(root != NULL)
	{
		int head = root->value;		
		BinaryTreeNode<T>* lastLeaf = getLastLeaf();		
		root->value = lastLeaf->value;
		deleteNode(lastLeaf);
		if(--size == 0) root = NULL;
		siftDown(root);		
		return head;
	}
	else
	{
		cout << "Heap is empty" << endl;
		return NULL;
	}
}

template<class T>
void Heap<T>::insert(T value)
{
	BinaryTreeNode<T>* insertNodeParent = root;// insertNodeParent is the parent of the new node

	/* Find the insertNodeParent whose index should be (size+1)/2 in breadth first search */
	Queue<BinaryTreeNode<T>*> queue(this->size);
	queue.addElement(root);
	int count = 0;
	while(!queue.isEmpty())
	{
		BinaryTreeNode<T>* node = queue.removeHead();
		count++;
		if(count == (size+1)/2)
		{
			insertNodeParent = node;
			break;
		}

		if(node->leftChild != NULL) queue.addElement(node->leftChild);
		if(node->rightChild != NULL) queue.addElement(node->rightChild);
	}

	BinaryTreeNode<T>* newNode = new BinaryTreeNode<T>(value,insertNodeParent);
	if(insertNodeParent == NULL)
	{
		root = newNode;
	}
	else
	{
		insertNodeParent->leftChild == NULL ? insertNodeParent->leftChild = newNode : insertNodeParent->rightChild = newNode;
	}
	
	floatUp(newNode);
	size++;
}

template<class T>
void Heap<T>::siftDown(BinaryTreeNode<T>* node)
{
	if(node != NULL)
	{
		BinaryTreeNode<T>* parent = node;
		BinaryTreeNode<T>* child = node->leftChild;
		while(child != NULL)
		{
			if(parent->rightChild != NULL && parent->rightChild->value > child->value)
				child = parent->rightChild;

			if(child->value > parent->value)
			{
				/*int temp = parent->value;
				parent->value = child->value;
				child->value = temp;*/
				swapValue(parent->value, child->value);

				parent = child;
				child = parent->leftChild;
			}
			else
				break;
		}

	}
}

template<class T>
void Heap<T>::floatUp(BinaryTreeNode<T>* node)
{
	if(node != NULL)
	{
		BinaryTreeNode<T>* parent = node->parent;
		BinaryTreeNode<T>* child = node;
		while(parent != NULL)
		{			
			if(child->value > parent->value)
			{				
				swapValue(parent->value, child->value);

				child = parent;
				parent = parent->parent;				
			}
			else
				break;
		}

	}
}

template<class T>
void Heap<T>::deleteNode(BinaryTreeNode<T>* node)
{
	BinaryTreeNode<T>* parent = node->parent;
	if(parent != NULL)
	{
		if(parent->leftChild->value == node->value)
			parent->leftChild = NULL;
		else
			parent->rightChild = NULL;
	}
	
	delete node;
}

template<class T>
BinaryTreeNode<T>* Heap<T>::getLastLeaf() const
{
	if(root != NULL)
	{
		Queue<BinaryTreeNode<T>*> queue(this->size);
		queue.addElement(root);
		int count = 0;
		while(!queue.isEmpty())
		{
			BinaryTreeNode<T>* node = queue.removeHead();
			count++;
			if(count == size) return node;

			if(node->leftChild != NULL) queue.addElement(node->leftChild);
			if(node->rightChild != NULL) queue.addElement(node->rightChild);
		}
	}
	else
		return NULL;
}

template<class T>
Heap<T>::~Heap(void)
{
	releaseNodes(root);
}

template<class T>
void Heap<T>::releaseNodes(BinaryTreeNode<T>* currentNode)
{
	if(currentNode != NULL)
	{		
		releaseNodes(currentNode->leftChild);
		releaseNodes(currentNode->rightChild);
		delete currentNode;
	}
}
