package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.gui.MainFrame;
import main.model.Graph;

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
	
	public Main() {
		new MainFrame(new Graph());
	}
	
	/**
	 * Jedyny taki public static void main() w tym programie.
	 * Czyli na tym aplikacja startuje.
	 */
	public static void main(String []args) {
		new Main();
	}
}
