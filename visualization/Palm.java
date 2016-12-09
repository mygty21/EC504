package visualization;
/**
 * Palm.java - a model for the palm of a hand
 */


import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

/**
 * A model for the palm of a hand as a sphere scaled in one direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Palm extends Circular implements Displayable {

  /**
   * The OpenGL handle to the display list which contains all the components
   * which comprise this cylinder.
   */
  private int callListHandle;
  private double X, Y, Z;
  private GLU glu = new GLU();
  
  private Visualizable obj;
  
  /**
   * Instantiates this object with the specified radius and OpenGL utility
   * toolkit object for drawing the sphere.
   * 
   * @param radius
   *          The radius of this object.
   * @param glut
   *          The OpenGL utility toolkit object for drawing the sphere.
   */
  public Palm(GLUT glut){
	  super(glut);
	  this.radius = 0.2f;
	    X = 1.0f;
	    Y= 1.0f;
	    Z = 1.0f;
  }
  
  
  public Palm(ComponentNode node) {
    super(node);
    
    X = node.x_scale;
    Y= node.y_scale;
    Z = node.z_scale;

  }
  
  public void setVisualizaObject(Visualizable s){
	  this.obj = s;
  }

  
  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(GL2 gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * Defines the OpenGL call list which draws a scaled sphere.
   * 
   * @param gl
   *          {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL2 gl) {
	   this.callListHandle = gl.glGenLists(1);

	    // create an ellipsoid for the palm by scaling a sphere
	    gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
	    gl.glPushMatrix();
	    // position this so that the sphere is drawn above the x-y plane, not at
	    // the origin
	    gl.glTranslated(0, 0, this.radius());
	    
	    gl.glRasterPos3d(0, 0, this.radius*1.1);
	    glut.glutBitmapString(7, obj.getLabel());
	    
	    gl.glRasterPos3d(0, 0, -this.radius*1.1);
	    glut.glutBitmapString(7, obj.getLabel());
	    
	    if(obj.isRoot()){
	    	gl.glRasterPos3d(0, this.radius*0.5, this.radius);
	    	glut.glutBitmapString(7, "Hmin");
	    }
	    
	    if(obj.isMarked()){
	    	gl.glColor3f(1.0f, 0, 0);
	    }
	    
	    
	    this.glut().glutSolidSphere(this.radius(), 36, 18);
		
	    gl.glPopMatrix();
	    gl.glEndList();
  }

}
