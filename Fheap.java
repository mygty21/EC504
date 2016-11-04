public class Fheap {
	FNode min;
	int n;
}

class FNode {
	int key;
	int degree;
	boolean mark;
	FNode parent;
	FNode child;
	FNode left;
	FNode right;
	
	FNode(int num){
		key = num;
		degree = 0;
		mark = false;
		parent = null;
		child = null;
		left = this;
		right = this;
	}
	
	void insertRight(FNode node){
		FNode r = this.right;
		this.right = node;
		r.left = node;
		node.left = this;
		node.right = r;
	}
}