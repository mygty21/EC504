package visualization;
/**
 * Circular.java - a circular object
 */


import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl

/**
 * A circular object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Circular {
  /** The OpenGL utility toolkit object to use to draw this object. */
  protected final GLUT glut;
  /** The radius of this object. */
  protected double radius;

  public Circular(ComponentNode node){
	  this.radius = node.radius;
	  this.glut = node.glut;
  }
  
  public Circular(GLUT glut){
	  this.glut = glut;
	  radius = 30.0;
  }
  
  /**
   * Gets the OpenGL utility toolkit object.
   * 
   * @return The OpenGL utility toolkit object.
   */
  protected GLUT glut() {
    return this.glut;
  }

  /**
   * Gets the radius of this object.
   * 
   * @return The radius of this object.
   */
  protected double radius() {
    return this.radius;
  }
}
