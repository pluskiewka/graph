package main.gui.model.object;

import java.awt.Color;
import java.awt.Graphics;

public class Line {
	private Point left, right; 
	private int level;
	
	public Line(Point left, Point right, int level) {
		this.left = left;
		this.right = right;
		this.level = level;
	}
	
	public boolean equals(Object obj) {
		return (obj instanceof Line &&
				((Line)obj).left.equals(this.left) && ((Line)obj).right.equals(right));
	}
	
	public int hashCode() {
		return left.hashCode()+right.hashCode();
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
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(left.getX()+5, left.getY()+5, right.getX()+5, right.getY()+5);
	}
}
