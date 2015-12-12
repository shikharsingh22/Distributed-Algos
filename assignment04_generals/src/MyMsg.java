import java.awt.Color;
import teachnet.view.renderer.Shape;
import java.util.ArrayList;
import java.util.List;

public class MyMsg {

	Color color=Color.WHITE;
	Shape shape = Shape.RHOMBUS;
	//the path of the message
	int[] idPath;
	//the value carried by the message
	int value;
	//if the message is corrupt
	boolean isCorrupt;
	
	//list of all the children message (path) 
	List<MyMsg> children;
	int majority;

	public MyMsg(int[] idPath, int value, boolean isCorrupt) {
		this.idPath = idPath;
		this.value = value;
		this.isCorrupt = isCorrupt;
		this.children = new ArrayList<MyMsg>();
	}

	public void addChild(MyMsg msg) {
		children.add(msg);
	}

	public boolean containsChild(MyMsg msg) {
		return children.contains(msg);
	}

	public List<MyMsg> getChildren() {
		return children;
	}

	public int getMajority() {
		return majority;
	}

	public void setMajority(int majority) {
		this.majority = majority;
	}

	public int[] getIdPath() {
		return idPath;
	}

	public int getValue() {
		return value;
	}

	public boolean isCorrupt() {
		return isCorrupt;
	}


	@Override
	public String toString() {
		String mess = "";

		if (isCorrupt) {
			color=Color.RED;
		}else{
			color=Color.WHITE;
		}
		for (int i = 0; i < idPath.length; i++) {
			if (i != 0) {
				mess += " : ";
			}
			mess += idPath[i];
		}
		mess += " -> " + value;
		
		return mess;
	}
}
