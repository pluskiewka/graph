package main.gui.view.object;

import java.awt.Color;
import java.awt.Graphics;

import main.gui.model.object.Line;

public class JLine {
	private Line line;
	private JPoint left, right;
	
	public JLine(Line line, JPoint left, JPoint right) {
		this.line = line;
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
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
		JLine other = (JLine) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	public JPoint getLeft() {
		return left;
	}
	
	public JPoint getRight() {
		return right;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(left.getX(), left.getY(), right.getX(), right.getY());
	}
}
