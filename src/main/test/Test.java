package main.test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.model.Graph;
import main.model.object.Point;
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
		int id, n;
		
		for(int k=0; k<20; k++) {
			Graph model = new Graph();
			n = (int) Math.pow(2, k);
			for(int i=0; i<n; i++)  {
				id = Graph.getAndSetNext();
				// deg = Random.nextInt(360);
				// model.addPoint(id, (int)(200*Math.cos(deg)+300), (int)(200*Math.sin(deg)+250));
				model.addPoint(id, 0, 0);
			}
			
			long p1 = System.nanoTime();
			for(Point point : model.getPoints()) {
				for(Point toLine : model.getPoints()) {
					model.addLine(point, toLine, Random.nextInt(40)-20);
				}
			}
			long p2 = System.nanoTime();
			
			System.err.println("Total time for " + n + ": " + Double.toString((double)(p2-p1)/1000000000.0));
		}
	}
}
