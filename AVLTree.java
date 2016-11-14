import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AVLTree implements Heap{
	AVLNode root;
	
	public AVLTree(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void insert(int num) {
		if (root==null) {
			root = new AVLNode(num);
		}
		
		root = insertHelper(num, root);
	}
	
	public void remove(int num) {
		Stack<AVLNode> path = new Stack<AVLNode>();
		AVLNode target = null, cur = root;
		while(cur != null && cur.val != num){
			path.push(cur);
			if (num > cur.val) {
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		
		if (cur == null) {
			return;
		}
		
		if (cur.left != null && cur.right != null) {
			target = cur;
			path.push(cur);
			cur = cur.left;
			while(cur.right != null) {
				path.push(cur);
				cur = cur.right;
			}
			target.val = cur.val;
		}
		
		if(path.isEmpty()){
			this.root = this.root.right;
			return;
		}else if(path.peek().left == cur) {
			if(cur.left != null){
				path.peek().left = cur.left;
			} else {
				path.peek().left = cur.right;
			}
		}else{
			if(cur.left != null){
				path.peek().right = cur.left;
			} else {
				path.peek().right = cur.right;
			}
		}
		
		while(!path.isEmpty()){
			AVLNode temp = path.pop();
			cur = updateNode(temp);
			if(!path.isEmpty()){
				if(path.peek().left == temp){
					path.peek().left = cur;
				} else {
					path.peek().right = cur;
				}
			}
		}
		
		root = cur;
	}
	
	public int min(){
		AVLNode node = root;
		while(node.left != null)
			node = node.left;
		return node.val;
	}
	
	public int extractMin(){
		int min = min();
		remove(min);
		return min;
	}
	
	public int predecessor(int num) {
		return predecessorNode(num).val;

	}
	
	public int successor(int num) {
		return successorNode(num).val;

	}
	
	public int height(){
		return 1 + root.height;
		
	}

	public boolean is_balanced(){
		return balanced(root);

	}


	private boolean balanced(AVLNode node){
		if(node == null)
			return true;
		else{
			if(node.left==null && node.right==null){
				return true;
			}else if(node.left==null){
				return (node.right.height) == 0 && balanced(node.left) && balanced(node.right);
			}else if(node.right==null){
				return (node.left.height) == 0 && balanced(node.left) && balanced(node.right);
			}else{
				return Math.abs(node.left.height - node.right.height) <= 1 && balanced(node.left) && balanced(node.right);
			}

		}
	}
	private AVLNode insertHelper(int num, AVLNode node) {
		if(node == null) {
			return new AVLNode(num);
		}
		if(node.val == num){
			return node;
		}
		
		if(num > node.val) {
			node.right = insertHelper(num, node.right);
		} else {
			node.left = insertHelper(num, node.left);
		}
		return updateNode(node);
	}
	
	private AVLNode updateNode(AVLNode node) {
		node.weight = (node.left==null?-1:node.left.height)-(node.right==null?-1:node.right.height);
		if(node.weight >= -1 && node.weight <= 1) {
			node.height = Math.max(node.left==null?-1:node.left.height, node.right==null?-1:node.right.height)+1;
			return node;
		}
		
		if(node.weight <= -2) {
			if(node.right.weight > 0) { //RL
				node.right = rightRotate(node.right);
			}
			return leftRotate(node);
		} else {
			if(node.left.weight < 0) { //LL
				node.left = leftRotate(node.left);
			}
			return rightRotate(node);
		}
	}
	
	private AVLNode predecessorNode(int num) {
		Stack<AVLNode> path = pathTo(num);
		if(path.isEmpty()) {
			return null;
		}
		
		AVLNode node = path.pop();
		if(node.left != null) {
			node = node.left;
		} else {
			while(!path.isEmpty() && node.val >= num){
				node = path.pop();
			}
			if (node.val >= num) {
				return null;
			}
			
			if (node.left == null) {
				return node;
			}
			node = node.left;
		}
		
		while(node.right != null) {
			node = node.right;
		}
		
		return node;
	}
	
	private AVLNode successorNode(int num) {
		Stack<AVLNode> path = pathTo(num);
		if(path.isEmpty()) {
			return null;
		}
		
		AVLNode node = path.pop();
		if (node.right != null) {
			node = node.right;
		} else {
			while(!path.isEmpty() && node.val <= num) {
				node = path.pop();
			}
			if (node.val <= num) {
				return null;
			}
			
			if (node.right == null) {
				return node;
			}
			node = node.right;
		}
		
		while(node.left != null) {
			node = node.right;
		}
		
		return node;
	}
	
	private Stack<AVLNode> pathTo(int num) {
		Stack<AVLNode> s = new Stack<AVLNode>();
		AVLNode temp = root;
		while(temp != null && temp.val != num) {
			s.push(temp);
			if (num > temp.val){
				temp = temp.right;
			} else {
				temp = temp.left;
			}
		}
		
		if (temp == null) {
			return new Stack<AVLNode>();
		} else {
			s.push(temp);
			return s;
		}
	}
	
	private AVLNode leftRotate(AVLNode node) {
		if (node.right == null) {
			return node;
		}
		
		AVLNode r = node.right;
		node.right = r.left;
		r.left = node;
		
		node.height = Math.max(node.left==null?-1:node.left.height, node.right==null?-1:node.right.height) + 1;
		node.weight = (node.left==null?-1:node.left.height)-(node.right==null?-1:node.right.height);
		
		r.height = Math.max(node.height, r.right==null?-1:r.right.height)+1;
		r.weight = node.height-(r.right==null?-1:r.right.height);

		return r;
	} 
	
	private AVLNode rightRotate(AVLNode node) {
		if (node.left == null) {
			return node;
		}
		
		AVLNode l = node.left;
		node.left = l.right;
		l.right = node;
		
		node.height = Math.max(node.left==null?-1:node.left.height, node.right==null?-1:node.right.height) + 1;
		node.weight = (node.left==null?-1:node.left.height)-(node.right==null?-1:node.right.height);
		
		l.height = Math.max(l.left==null?-1:l.left.height, node.height)+1;
		l.weight = (l.left==null?-1:l.left.height)-node.height;
		
		return l;
	}
 	
 	//For test
 	public void print(){
 		BTreePrinter.printNode(root);
 	}
}

class AVLNode {
	AVLNode left;
	AVLNode right;
	int val;
	int height;
	int weight;
	
	AVLNode(int num) {
		val = num;
		left = null;
		right = null;
		height = 0;
		weight = 0;
	}
}

//Helper for print pretty tree
class BTreePrinter {

    public static void printNode(AVLNode root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<AVLNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<AVLNode> newNodes = new ArrayList<AVLNode>();
        for (AVLNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(AVLNode node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}