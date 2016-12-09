package visualization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jogamp.opengl.util.gl2.GLUT;

public class HeapRender {

	protected GLUT glut;
	protected CanvasRender render;
	
	public static double OFFSET_DEPTH = 0.9f;
	public static double OFFSET_WIDTH =1f;

	public static double OPEN_ANGLE = Math.PI/5;
	
	public HeapRender(Visualizable root){
		
		glut = new GLUT();
		
		Component cp = createComponent(root);
		render = new CanvasRender(cp, glut);
	}
	
	public void show(){
		render.animator.start();
	}
	
	public void stop(){
		render.animator.stop();
	}
	
	public Component createComponent(Visualizable node){
		if(node == null){
			return null;
		}
		
		ArrayList<Visualizable> rootList = new ArrayList<>();
		rootList.add(node);
		Visualizable nodeIter = node.getRightNeightbor();
		while(nodeIter != null && nodeIter != node){
			rootList.add(nodeIter);
			nodeIter = nodeIter.getRightNeightbor();
		}
		
		ArrayList<Component> components = new ArrayList<>();
		
		
		
		int i = 0;
		for(Visualizable v : rootList){
			Component c = createComponentForHeap(v, new Point3D(OFFSET_WIDTH, 0, 0));
			if(c == null){
				continue;
			}
			i += 1;
			components.add(c);
		}
		
		Component result = new Component(new Point3D(-4, 1.5, 0), "result");
		//result.addChild(components.get(0));
		Component first = components.get(0);
		result.addChild(first);
		for(i = 1; i < rootList.size(); i++){
			Component pre = components.get(i-1);
			Component cur = components.get(i);
			pre.addChild(cur);
		}
		return result;
		
	}


	private Component createComponentForHeap(Visualizable v, Point3D position) {
		if(v == null){
			return null;
		}
		
		Palm body = new Palm(glut);
		body.setVisualizaObject(v);
		Component cp = new Component(position, body, "body");
		List<Visualizable> children = v.getChildren();
		int count = children.size();
		if(count == 0){
			return cp;
		}
		
		// theta >=0 and theta <= 2pi
		double delta = (2*Math.PI)/ count;
		double r = Math.tan(OPEN_ANGLE) * OFFSET_DEPTH;
		double theta = 0;
		double nextY = 0;//position.y() - OFFSET_DEPTH;
		double nextX = 0;//Math.cos(theta) * r;
		double nextZ = 0;//Math.sin(theta) * r;
		Component tmp = null;
		
		
		
		for(int i = 0; i < count; i++){
			nextX = r*Math.cos(theta);
			nextY = - OFFSET_DEPTH;
			nextZ = r*Math.sin(theta);
			theta += delta;
			tmp = createComponentForHeap(children.get(i), new Point3D(nextX, nextY, nextZ));
			if(tmp == null){
				continue;
			}
			
		//	connectComponent(cp, tmp, new Point3D(nextX, nextY, nextZ));
			
			cp.addChild(tmp);
		}
		
		return cp;
	}
	
	public static void main(String[] args){
		
		TreeNode root = new TreeNode(0);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		TreeNode other = new TreeNode(4);
		TreeNode test = new TreeNode(100);
		test.isMarked = true;
		
		root.children.add(left);
		root.children.add(right);
		root.children.add(other);
		root.children.add(test);
		
		TreeNode test2 = new TreeNode(200);
		test.children.add(test2);
		
		TreeNode root2 = new TreeNode(0);
		TreeNode left2 = new TreeNode(1);
		TreeNode right2 = new TreeNode(2);
		
		TreeNode th = new TreeNode(12);
		
		root2.children.add(left2);
		root2.children.add(right2);
		
		root2.left = th;
		root2.right = root;
		
		root.left = root2;
		root.right = th;
		
		th.left = root;
		th.right = root2;
		
		root.isRoot = true;
		root2.isMarked = true;
		
		
		
		HeapRender render = new HeapRender(th);
		render.show();
		
		
	}
	
	
}
