package visualization;

import org.w3c.dom.Element;

import com.jogamp.opengl.util.gl2.GLUT;


public class CylinderFactory {
	
	public static Displayable createCylinder(ComponentNode node, GLUT glut){
		
		String type = node.type;
		
		if("palm".equals(type)){
			return new Palm(node);
		}
		else if("round".equals(type)){
			return new RoundedCylinder(node);
		}
		return null;
	}

}
