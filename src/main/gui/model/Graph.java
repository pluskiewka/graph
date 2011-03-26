package main.gui.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import main.gui.model.object.Line;
import main.gui.model.object.Point;

public class Graph {
	private Set<Line> lines;
	private Set<Point> points;
	
	public Graph() {
		lines = new HashSet<Line>();
		points = new HashSet<Point>();
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
	
	public void addLine(Line line) {
		lines.add(line);
	}
	
	public void addLine(Point left, Point right, int level) {
		if(!points.contains(left))
			points.add(left);
		if(!points.contains(right))
			points.add(right);
		lines.add(new Line(left, right, level));
	}
	
	public Collection<Point> getPoints() {
		return points;
	}
	
	public Collection<Line> getLines() {
		return lines;
	}
	
}
