package visualization;


import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jogamp.opengl.util.gl2.GLUT;


public class ComponentNode {
	
	protected String label;
	protected Point3D pos;
	protected String color;
	protected ArrayList<ComponentNode> children;
	protected final GLUT glut;
	public String type;
	public double radius;
	public double height;
	public Component _component;
	
	public boolean textureEnable;
	public String texturePath;
	
	public double x_scale, y_scale, z_scale;
	
	protected String activeColor = "ff0000";
	
	  protected double x_range_min, x_range_max;
	  protected double y_range_min, y_range_max;
	  protected double z_range_min, z_range_max;
	  protected double x_init, y_init, z_init;
	
	public ComponentNode(final GLUT glut){
		children = new ArrayList<>();
		this.glut = glut;
	}
	
	public static ComponentNode buildComponent(Element node, final GLUT glut){
		
		final ComponentNode root = new ComponentNode(glut);
		root.label = node.getAttribute("label");
		root.type = node.getAttribute("type");
		//System.out.println(root.label);
		String tmp = node.getAttribute("radius");
		root.radius = tmp == "" ? 0.0 : Double.valueOf(tmp);
		
		tmp = node.getAttribute("x-scale");
	    root.x_scale = tmp == ""? 1: Double.valueOf(tmp);
	    
	    tmp = node.getAttribute("y-scale");
	    root.y_scale = tmp == ""? 1: Double.valueOf(tmp);
	    
	    tmp = node.getAttribute("z-scale");
	    root.z_scale = tmp == ""? 1: Double.valueOf(tmp);
	    
	    tmp = node.getAttribute("texture-enable");
	    root.textureEnable = tmp == ""? false: Boolean.valueOf(tmp);
			    
	    if(root.textureEnable){
	    	root.texturePath = node.getAttribute("texture");
	    }
	    else{
	    	root.texturePath = "";
	    }
	    
		
		tmp = node.getAttribute("height");
		root.height = tmp == "" ? 0.0: Double.valueOf(tmp);
		root.pos = root.readPosition(node.getAttribute("position"));
		
		root.color = node.getAttribute("color");
								
		root.loadPositionInfo(node);
		
		
		NodeList nodeList = node.getChildNodes();
		int length = nodeList.getLength();
		for(int i = 0; i < length; i++){
			Node nNode = nodeList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) nNode;
				root.addChild(buildComponent(e, glut));
			}
		}
		//map.put(root.label, root);
		return root;
	}
	

	private void loadPositionInfo(Element node) {
		
		String direct_string = node.getAttribute("direct");
		String xrange = node.getAttribute("x-rotate-range");
		String yrange = node.getAttribute("y-rotate-range");
		String zrange = node.getAttribute("z-rotate-range");
		
		String[] directs = direct_string.split(",");
		x_init = Double.valueOf(directs[0]);
		y_init = Double.valueOf(directs[1]);
		z_init = Double.valueOf(directs[2]);
		
		String[] xrange_str = xrange.split(",");
		x_range_min = Double.valueOf(xrange_str[0]);
		x_range_max = Double.valueOf(xrange_str[1]);
		
		String[] yrange_str = yrange.split(",");
		y_range_min = Double.valueOf(yrange_str[0]);
		y_range_max = Double.valueOf(yrange_str[1]);
		
		String[] zrange_str = zrange.split(",");
		z_range_min = Double.valueOf(zrange_str[0]);
		z_range_max = Double.valueOf(zrange_str[1]);
		
	}

	public void addChild(ComponentNode node){
		this.children.add(node);
	}

	private Point3D readPosition(String attribute) {
		// TODO Auto-generated method stub
		
		String[] attrs = attribute.split(",");
		double x = Double.valueOf(attrs[0]);
		double y = Double.valueOf(attrs[1]);
		double z = Double.valueOf(attrs[2]);
		
		return new Point3D(x, y, z);
	}

	public void setActive(boolean b) {
		// TODO Auto-generated method stub
		if(b){
			this._component.setColor(getColor(this.activeColor));
		}
		else{
			this._component.setColor(getColor(this.color));
		}
	}
	
	public final Component convert(HashMap<String , ComponentNode> resource){
		ComponentNode node = this;
		Displayable shape = CylinderFactory.createCylinder(node, node.glut);
		
		final Component p = new Component(node.pos, shape, node.label);
		
		if(!node.textureEnable){
			p.setColor(getColor(node.color));
		}
		
		
		p.rotate(Axis.X, x_init);
		p.rotate(Axis.Y, y_init);
		p.rotate(Axis.Z, z_init);
		
		p.setXNegativeExtent(x_range_min);
		p.setXPositiveExtent(x_range_max);
		
		p.setYNegativeExtent(y_range_min);
		p.setYPositiveExtent(y_range_max);
		
		p.setZNegativeExtent(z_range_min);
		p.setZPositiveExtent(z_range_max);
		
		int length = node.children.size();
		//System.out.println("node "+this.label+ " "+ length);
		
		for(ComponentNode n : node.children){
			final Component child = n.convert(resource);
			p.addChild(child);
		}
		this._component = p;
		resource.put(node.label, this);
		
		return p;
		
	}
	
	 private FloatColor getColor(String color){
		  char[] s = color.toCharArray();
		  final float r = evaluateColor(s[0], s[1]);
		  final float g = evaluateColor(s[2], s[3]);
		  final float b = evaluateColor(s[4], s[5]);
		  return new FloatColor(r,g, b); 
	  }
	 
	  private float evaluateColor(char h, char l){
		  int val = 0;
		  if(h >= '0' && h <= '9'){
			  val += (h-'0')*16;
		  }
		  else{
			  val += (h - 'a' + 10) * 16;
		  }
		  
		  if(l >= '0' && l <= '9'){
			  val += (l-'0');
		  }
		  else{
			  val += (l - 'a' + 10) ;
		  }
		  
		  return val/255.0f;
	  }
	
}
