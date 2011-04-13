package main.gui.model.table;

import javax.swing.table.AbstractTableModel;

import main.model.Graph;

public class PointTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6577263667054304933L;

	private Graph model;
	
	public PointTableModel(Graph model) {
		this.model = model;
	}

	public String getColumnName(int column) {
		switch(column) {
		case 0: return "ID";
		case 1: return "x";
		case 2: return "y";
		}
		return null;
	}
	
	@Override
	public int getRowCount() {
		return model.getPoints().size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0: return model.getPoints().get(rowIndex).getId();
			case 1: return model.getPoints().get(rowIndex).getX();
			case 2: return model.getPoints().get(rowIndex).getY();
		}
		return null;
	}

}
