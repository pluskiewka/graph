package main.test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.gui.MainFrame;
import main.gui.model.Graph;
import main.gui.model.object.Point;
import main.util.Random;

public class Test {
	static {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException e) {
			System.err.println(e.toString());
		} catch (InstantiationException e) {
			System.err.println(e.toString());
		} catch (IllegalAccessException e) {
			System.err.println(e.toString());
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println(e.toString());
		}
	}
	
	public static void main(String []args) {
		Graph model = new Graph();
		MainFrame frame = new MainFrame(model);
		int id, deg;
		
		for(int i=0; i<20; i++)  {
			id = Graph.getAndSetNext();
			deg = Random.nextInt(360);
			model.addPoint(id, (int)(200*Math.cos(deg)+300), (int)(200*Math.sin(deg)+250));
			frame.getCanvas().repaint();
		}
		
		for(Point point : model.getPoints()) {
			if(Random.nextInt(100)>50) {
				for(Point toLine : model.getPoints()) {
					if(Random.nextInt(100)>25) {
						model.addLine(point, toLine, Random.nextInt(40)-20);
						frame.getCanvas().repaint();
					}
				}
			}
		}
	}
}
