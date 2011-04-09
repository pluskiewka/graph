package main.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import main.file.FileExport;
import main.file.FileImport;
import main.gui.MainFrame;
import main.gui.model.Graph;
import main.gui.model.table.LineTableModel;
import main.gui.model.table.PointTableModel;

public class MainController {
	private PointTableModel pointeTableModel;
	private LineTableModel lineTableModel;
	private MainFrame frame;
	private Graph model;
	
	public MainController(Graph model, PointTableModel pointTableModel,
			LineTableModel lineTableModel, MainFrame frame) {
		this.model = model;
		this.frame = frame;
		this.pointeTableModel = pointTableModel;
		this.lineTableModel = lineTableModel;
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
				try {
					FileImport.importGraph(model,fc.getSelectedFile().getPath());
					pointeTableModel.fireTableDataChanged();
					lineTableModel.fireTableDataChanged();
					frame.getCanvas().repaint();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, e1.toString());
				}
			}
		}
		
	}
	
	class SaveButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int ret = fc.showSaveDialog(frame);
			if(ret == JFileChooser.APPROVE_OPTION) {
				try {
					FileExport.exportGraph(model, fc.getSelectedFile().getPath());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, e1.toString());
				}
			}
			
		}
	}

}
