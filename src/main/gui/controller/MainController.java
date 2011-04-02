package main.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import main.gui.MainFrame;
import main.gui.model.Graph;
import main.gui.model.table.LineTableModel;
import main.gui.model.table.PointTableModel;

public class MainController {
	private JFrame frame;
	@SuppressWarnings("unused")
	private Graph model;
	
	public MainController(Graph model, PointTableModel pointTableModel,
			LineTableModel lineTableModel, MainFrame frame) {
		this.model = model;
		this.frame = frame;
		frame.addLoadButtonListener(new LoadButtonListener());
		frame.addSaveButtonListener(new SaveButtonListener());
		new CanvasController(model, pointTableModel, lineTableModel, frame.getCanvas());
	}
	
	class LoadButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int ret = fc.showOpenDialog(frame);
			if(ret == JFileChooser.APPROVE_OPTION) {
				/*
				 * TODO parsowanie wybranego pliku i umieszczenie go w modelu.
				 * Dostęp do modelu poprzez interfejs Graph'u czyli addPoint, addLine
				 * oraz new Point(x, y) oraz new Line(point1, point2);
				 */
				System.out.println(fc.getSelectedFile().getName());
			}
		}
		
	}
	
	class SaveButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int ret = fc.showSaveDialog(frame);
			if(ret == JFileChooser.APPROVE_OPTION) {
				/*
				 * TODO zapisać do pliku aktualny stan modelu graphu.
				 */
				System.out.println(fc.getSelectedFile().getPath());
			}
			
		}
	}

}
