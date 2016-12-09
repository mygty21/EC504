package visualization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode implements Visualizable{
	
	public int val;
	public TreeNode left;
	public TreeNode right;
	public boolean isMarked;
	public boolean isRoot;
	
	public List<TreeNode> children;
	
	public TreeNode(int val){
		this.val = val;
		children = new ArrayList<>();
	}

	
	@Override
	public Visualizable getLeftNeighbor() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public Visualizable getRightNeightbor() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public List<Visualizable> getChildren() {
		// TODO Auto-generated method stub
		return (List)children;
	}

	@Override
	public boolean isMarked() {
		// TODO Auto-generated method stub
		return isMarked;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return String.valueOf(val);
	}

	@Override
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return isRoot;
	}

}
