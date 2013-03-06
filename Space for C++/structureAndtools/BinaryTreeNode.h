/**
* A node in the binary tree.
*/

#ifndef BINARYTREENODE_H
#define BINARYTREENODE_H

template<class T>
class BinaryTreeNode
{
public:
	T value;
	BinaryTreeNode* parent;
	BinaryTreeNode* leftChild;
	BinaryTreeNode* rightChild;

	BinaryTreeNode();

	BinaryTreeNode(T value, BinaryTreeNode* parent = NULL, BinaryTreeNode* leftChild= NULL, BinaryTreeNode* rightChild= NULL)
	{
		this->value = value;
		this->parent = parent;
		this->leftChild = leftChild;
		this->rightChild = rightChild;
	}
};

#endif