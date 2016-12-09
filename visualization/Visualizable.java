package visualization;

import java.util.List;

public interface Visualizable {
	
	public Visualizable getLeftNeighbor();
	
	public Visualizable getRightNeightbor();
	
	public List<Visualizable> getChildren();
	
	public boolean isMarked();
	
	public String getLabel();
	
	public boolean isRoot();

}
