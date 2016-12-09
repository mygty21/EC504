import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import visualization.Visualizable;

public class Fheap implements Heap{
	FNode min;
	int n;
	Map<Integer, FNode> map;
	
	public Fheap(){
		this.min = null;
		this.n = 0;
		this.map = new HashMap<Integer, FNode>();
	}
	
	public boolean isEmpty(){
		return this.min == null;
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
	
	public int min(){
		return this.min.key;
	}
	
	public int extractMin() {
		int res = this.min.key;
		FNode extract = this.min;

		if (this.min == null){
			return Integer.MAX_VALUE;
		}
		
		while(this.min.child != null){
			FNode temp = this.min.child;
			this.min.child = removeNode(temp);
			temp.parent = null;
			this.min.insertRight(temp);
		}
		
		this.min = removeNode(extract);
		consolidate();
		this.n--;
		return res;
	}
	
	private void consolidate(){
		if (this.min == null) {
			return;
		}
		FNode[] A = new FNode[D()];
		FNode end = this.min, cur = this.min;
		List<FNode> rootList = new ArrayList<FNode>();
		do {
			rootList.add(cur);
			cur = cur.right;
		}while (cur != end);
		
		
		for(int i = 0; i < rootList.size(); i++){
			FNode node = rootList.get(i);
			while(A[node.degree] != null){
				if(node.key < A[node.degree].key){
					if(A[node.degree] == end){
						end = end.right;
					}
					fibHeapLink(node, A[node.degree]);
				} else {
					if(node == end){
						end = end.right;
					}
					fibHeapLink(A[node.degree], node);
					node = A[node.degree];
				}
                A[node.degree] = null;
                node.degree++;
			}
			A[node.degree] = node;
        }
		
		
		this.min = null;
		for(int i = 0; i < A.length; i++){
			if (A[i] != null){
				if (this.min == null){
					this.min = A[i];
				} else {
					if (A[i].key < this.min.key)
						this.min = A[i];
				}
			}
		}
	}
	
	private void fibHeapLink(FNode parent, FNode child){
		removeNode(child);
		child.parent = parent;
		child.mark = false;
		if(parent.child==null){
			parent.child = child;
			child.left = child;
			child.right = child;
		} else {
			parent.child.insertRight(child);
		}
	}
	
	private int D(){
		return (int)(Math.ceil(Math.log((double)n)/Math.log(2.0)))+1;
	}
	
	/**
	 * Remove a node from a node list. Input the node that needs to be removed,
	 * if it is the only node in the list, return null;
	 * otherwise connect node.left and node.right, then return node.right.
	 * 
	 * @param node to be removed.
	 * @return node.right if the node list is not empty or null if node list is empty.
	 */
	private FNode removeNode(FNode node){
		if (node.right == node){
			return null;
		}
		
		node.left.right = node.right;
		node.right.left = node.left;
		return node.right;
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

class FNode implements Visualizable{
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
	
	/**
	 * Input a FNode and insert the input node on the right side of this FNode
	 * 
	 * @param node to be inserted
	 */
	void insertRight(FNode node){
		FNode r = this.right;
		this.right = node;
		r.left = node;
		node.left = this;
		node.right = r;
	}
	
	public String toString(){
		return "(left="+left.key+"  this="+key+",degree="+degree+"  right="+right.key+")";
	}

	@Override
	public Visualizable getLeftNeighbor() {
		return this.left;
	}

	@Override
	public Visualizable getRightNeightbor() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public List<Visualizable> getChildren() {
		// TODO Auto-generated method stub
		if(this.child==null){
			return new ArrayList<Visualizable>();
		}
		
		List<Visualizable> list = new ArrayList<Visualizable>();
		FNode child = this.child;
		list.add(child);
		FNode cur = child.right;
		while(cur != child){
			list.add(cur);
			cur = cur.right;
		}
		return list;
	}

	@Override
	public boolean isMarked() {
		// TODO Auto-generated method stub
		return this.mark;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.key+"";
	}

	@Override
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}
}