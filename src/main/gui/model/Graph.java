package main.gui.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import main.gui.model.object.Line;
import main.gui.model.object.Point;

public class Graph {
	private Set<Line> lines;
	private Set<Point> points;
	private Integer min, max;
	
	public Graph() {
		lines = new HashSet<Line>();
		points = new HashSet<Point>();
		min = 0;
		max = 0;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
	
	public Line addLine(Point left, Point right, int level) {
		if(!points.contains(left))
			points.add(left);
		if(!points.contains(right))
			points.add(right);
		if(level<min)
			min = level;
		else if(level>max)
			max = level;
		Line line = new Line(left, right, level, this);
		lines.add(line);
		return line;
	}
	
	public Collection<Point> getPoints() {
		return points;
	}
	
	public Collection<Line> getLines() {
		return lines;
	}
	
	public synchronized void setMinMax() {
		int t = min;
		min = max;
		max = t;
		for(Line line : lines) {
			if(line.getLevel() < min)
				min = line.getLevel();
			else if(line.getLevel() > max)
				max = line.getLevel();
		}
	}
	
	public int getMax() {
		return max;
	}
	
	public int getMin() {
		return min;
	}
	
}
