package main.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.model.Graph;
import main.model.object.Point;

/**
 * Klasa której obiekt wykorzystywany jest to odtwarzania grafu z podanego pliku.
 * Plik ten jest wynikiem zapisu grafu prz pomocy obiektu klasy FileExport.
 */
public class FileImport {
	private String path;
	private FileReader file;
	private BufferedReader in;
	
	/**
	 * Zapisujemy informację o ścieżce dostępu do pliku, orz otwieramy plik,
	 * a następnie otwieramy strumień odczytu znakowego pliku.
	 * @param path
	 * @throws FileNotFoundException
	 */
	public FileImport(String path) throws FileNotFoundException {
		this.path = path;
		this.file = new FileReader(this.path);
		this.in = new BufferedReader(this.file);
	}
	
	/**
	 * Odczytujemy kolejną linię w pliku.
	 * @return
	 * @throws IOException
	 */
	public String readln() throws IOException {
		return in.readLine();
	}
	
	/**
	 * Zamykamy strumień z którego czytaliśmy, jak plik.
	 * @throws IOException
	 */
	public void close() throws IOException {
		in.close();
		file.close();
	}
	
	/**
	 * Statyczna metoda służąca do parsowania lini zwróconej
	 * przez readln(). 
	 * @param ind
	 * @param line
	 * @return
	 */
	private static int parse(int ind, String line) {
		return Integer.parseInt(line.split(" ")[ind]);
	}
	
	/**
	 * Statyczna metoda służąca do importu grafu, pod wskazany
	 * przez parametr model, z podanego pliku poprzez ścieżkę.
	 * @param model
	 * @param path
	 * @throws IOException
	 */
	public static void importGraph(Graph model, String path) throws IOException {
		FileImport imp = new FileImport(path);
		String line;
		Point p1 = null, p2 = null;
		
		if("P".equals(imp.readln())) {
			line = imp.readln();
			while(!("L".equals(line))) {
				int id = parse(0,line);
				if(Graph.getNext() < id)
					Graph.setNext(id+1);
				model.addPoint(id, parse(1, line), parse(2, line));
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
