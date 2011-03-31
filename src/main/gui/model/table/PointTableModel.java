package main.gui.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.gui.model.Graph;
import main.gui.model.object.Point;

public class PointTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6577263667054304933L;

	private Graph model;
	private List<Point> points;
	
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
		case 0: return points.get(rowIndex).getId();
		case 1: return points.get(rowIndex).getX();
		case 2: return points.get(rowIndex).getY();
		}
		return null;
	}
	
	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
		points = new ArrayList<Point>(model.getPoints());
	}

}
