package main.model.object;

import java.awt.Color;
import java.awt.Graphics;

public class Point {
	private final int id;
	private int x, y;
	private Color color;
	
	public Point(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.color = Color.BLACK;
	}
	
	public boolean equals(Object obj) {
		return (obj instanceof Point &&
				((Point)obj).id == this.id);
	}
	
	public int hashCode() {
		return id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(id), x-3, y-1);
		g.setColor(color);
		g.fillOval(x, y, 10, 10);
		g.setColor(color);
		g.drawOval(x, y, 10, 10);
	}
}
