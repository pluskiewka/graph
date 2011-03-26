package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.gui.MainFrame;
import main.gui.controller.MainController;
import main.gui.model.Graph;

/**
 * Ustawienie PLAF'a, utworzenie modelu grafu, utworzenie głównego okna i głównego kontrolera.
 */
public class Main {
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
	
	private Graph model;
	private MainFrame frame;
	
	public Main() {
		model = new Graph();
		frame = new MainFrame(model);
		new MainController(model, frame);
	}
	
	/**
	 * Jedyny taki public static void main() w tym programie.
	 * Czyli na tym aplikacja startuje.
	 */
	public static void main(String []args) {
		new Main();
	}
}
