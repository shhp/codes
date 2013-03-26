/*
* A binary search tree
*/
public class BinarySearchTree{

	public static void main(String[] args){
		BinarySearchTree tree = new BinarySearchTree();
		tree.addNode(50);
		tree.addNode(25);
		tree.addNode(65);
		tree.addNode(9);
		tree.addNode(41);
		tree.addNode(4);
		tree.addNode(95);
		tree.addNode(10);
		
		//tree.deleteNode(25);
		//System.out.println("root is: " + tree.root.value);
		//tree.printTree(tree.root);
		tree.convertToDoubleList(tree.root);
		Node node = tree.doubleListHead;
		System.out.println("tail: " + tree.doubleListTail.value);
		while(node != null){
			System.out.print(node.value + ",");
			node = node.right;
		}
	}

	/* A tree node */
	public class Node{
		int value;
		Node left;
		Node right;
		
		public Node(int value, Node left, Node right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
	public Node root = null;
	public Node doubleListHead = null;
	public Node doubleListTail = null;
	
	public void addNode(int value){
		if(this.root == null){
			this.root = new Node(value, null, null);
		}
		else{
			Node parent = null, node = root;
			while(node != null){
				parent = node;
				if(value == node.value){
					System.out.println("Node "+value+" already exists!");
					return;
				}
				else if(value < node.value)
					node = node.left;
				else
					node = node.right;
			}
			
			/* Have found the new node's position. Insert it into the tree. */
			if(value < parent.value)
				parent.left = new Node(value, null, null);
			else
				parent.right = new Node(value, null, null);
		}
	}
	
	public void deleteNode(int value){
		/* Firstly search for the node */
		Node parent = null, node = root;
		while(node != null){
			if(node.value == value)
				break;
			else if(value < node.value){
				parent = node;
				node = node.left;
			}
			else{
				parent = node;
				node = node.right;
			}
		}
		
		if(node == null){
			System.out.println("Node " + value + " dosen't exist!");
			return;
		}
		
		if(node.left == null){
			if(parent != null){
				if(node.value < parent.value)
					parent.left = node.right;
				else
					parent.right = node.right;
			}
			/* The node to be deleted is root */
			else{
				root = root.right;
			}
		}
		else{
			Node replaceNodeParent = node;
			Node replaceNode = node.left;
			while(replaceNode.right != null){
				replaceNodeParent = replaceNode;
				replaceNode = replaceNode.right;
			}
			if(replaceNode.value < replaceNodeParent.value)
				replaceNodeParent.left = replaceNode.left;
			else
				replaceNodeParent.right = replaceNode.left;
				
			node.value = replaceNode.value;
		}
	}
	
	/* Print the tree by inorder traversal */
	public void printTree(Node node){
		if(node == null) return;
		printTree(node.left);
		System.out.print(node.value + ",");
		printTree(node.right);
	}
	
	/* Convert the tree to a double linked list in ascending order */
	public void convertToDoubleList(Node node){
		if(node == null) return;
		
		convertToDoubleList(node.left);
		if(doubleListTail == null){
			doubleListHead = node;			
		}
		else{
			doubleListTail.right = node;
		}
		node.left = doubleListTail;
		doubleListTail = node;
		convertToDoubleList(node.right);
	}
}