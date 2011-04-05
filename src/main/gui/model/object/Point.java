package main.gui.model.object;

import java.awt.Color;
import java.awt.Graphics;

public class Point {
	private static int next = 0;
	private final int id;
	private int x, y;
	private Color color;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = next++;
		this.color = Color.BLUE;
	}
	
	public Point(int id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id = id;
		if(id >next)
			next = id;
		this.color = Color.BLUE;
	}
	
	public Point(int id) {
		this.id = id;
		if(id > next)
			next = id;
	}
	
	public boolean equals(Object obj) {
		return (obj instanceof Point &&
				((Point)obj).id == this.id);
	}
	
	public int hashCode() {
		return id;
	}
	
	public String toString() {
		return "Point "+id;
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
		g.setColor(color);
		g.fillOval(x, y, 10, 10);
		g.setColor(color);
		g.drawOval(x, y, 10, 10);
	}
}
