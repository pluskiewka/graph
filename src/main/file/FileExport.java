package main.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.gui.model.Graph;
import main.gui.model.object.Line;
import main.gui.model.object.Point;

public class FileExport {
	private String path;
	private FileWriter file;
	private BufferedWriter out;
	
	public FileExport(String path) throws IOException {
		this.path = path;
		this.file = new FileWriter(this.path);
		this.out = new BufferedWriter(file);
	}
	
	public void writeln(String str) throws IOException {
		out.write(str+"\n");
	}
	
	public void close() throws IOException {
		out.close();
		file.close();
	}
	
	public static void exportGraph(Graph model, String path) throws IOException {
		FileExport exp = new FileExport(path);
		
		exp.writeln("P");
		for(Point point : model.getPoints()) {
			exp.writeln(point.getId()+" "+point.getX()+" "+point.getY());
		}
		
		exp.writeln("L");
		for(Line line : model.getLines()) {
			exp.writeln(line.getLeft().getId()+" "+line.getRight().getId()+" "+line.getLevel());
		}
		
		exp.close();
	}
}
