public class Fheap {
	FNode min;
	int n;
	
	public Fheap(){
		this.min = null;
		this.n = 0;
	}
	
	public void insert(int num){
		FNode node = new FNode(num);
		this.n++;
		if (this.min == null){
			this.min = node;
		} else {
			this.min.insertRight(node);
			if (node.key < this.min.key){
				this.min = node;
			}
		}
	}
	
	public void union(Fheap h){
		if (this.min!=null && h.min!=null){
			FNode cur = h.min.right;
			this.min.insertRight(h.min);
			while(cur != h.min){
				FNode temp = cur.right;
				this.min.insertRight(cur);
				cur = temp;
			}
			if (h.min.key < this.min.key){
				this.min = h.min;
			}
			this.n += h.n;
		} else {
			if (this.min==null){
				this.min = h.min;
				this.n = h.n;
			}
		}
	}
	
	//for test
	public void printlist(FNode node){
		FNode cur = node;
		do{
			System.out.print(cur.key+"-");
			cur = cur.right;
		}while(cur != node);
		
		System.out.println();
	}
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