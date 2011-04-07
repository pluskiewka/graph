package main.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.gui.model.Graph;
import main.gui.model.object.Point;

public class FileImport {
	private String path;
	private FileReader file;
	private BufferedReader in;
	
	public FileImport(String path) throws FileNotFoundException {
		this.path = path;
		this.file = new FileReader(this.path);
		this.in = new BufferedReader(this.file);
	}
	
	public String readln() throws IOException {
		return in.readLine();
	}
	
	public void close() throws IOException {
		in.close();
		file.close();
	}
	
	private static int parse(int ind, String line) {
		return Integer.parseInt(line.split(" ")[ind]);
	}
	
	public static void importGraph(Graph model, String path) throws IOException {
		FileImport imp = new FileImport(path);
		String line;
		Point p1 = null, p2 = null;
		
		if("P".equals(imp.readln())) {
			line = imp.readln();
			while(!("L".equals(line))) {
				model.addPoint(new Point(parse(0, line), parse(1, line), parse(2, line)));
				line = imp.readln();
			}
			line = imp.readln();
			while(line != null) {
				int pid1 = parse(0, line), pid2 = parse(1, line);
				for(Point p : model.getPoints()) {
					if(p.getId() == pid1)
						p1 = p;
					else if(p.getId() == pid2)
						p2 = p;
				}
				if(p1 != null && p2 != null) {
					model.addLine(p1, p2, parse(2, line));
				}
				line = imp.readln();
			}
		}
		imp.close();
	}
}
