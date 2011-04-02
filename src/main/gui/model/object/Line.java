package main.gui.model.object;

import java.awt.Color;
import java.awt.Graphics;

import main.gui.model.Graph;

public class Line {
	private Point left, right; 
	private int level, rgb;
	private Graph model;
	
	public Line(Point left, Point right, int level, Graph model) {
		this.left = left;
		this.right = right;
		this.level = level;
		this.model = model;
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
	
	public void setLevel(int level) {
		this.level = level;
		model.setMinMax();
		double min = model.getMin(), max = model.getMax();
		this.rgb = (int)((255.0)*((level - min)/(max - min)));
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(rgb, rgb, rgb));
		g.drawLine(left.getX()+5, left.getY()+5, right.getX()+5, right.getY()+5);
	}
}
