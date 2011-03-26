package main.gui.model.object;

public class Line {
	private Point left, right; 
	private int level;
	
	public Line(Point left, Point right, int level) {
		this.left = left;
		this.right = right;
		this.level = level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}
	
	public int getLevel() {
		return level;
	}
	
	public Point getLeft() {
		return left;
	}
	
	public Point getRight() {
		return right;
	}
}
