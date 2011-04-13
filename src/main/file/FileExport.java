package main.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.model.Graph;
import main.model.object.Line;
import main.model.object.Point;

/**
 * Klasa, której obiekt jest wykorzystywany do otwarcia wskazanego pliku,
 * poprzez podanie ścieżki bezwzględnej, zapisanie modelu wg określonego 
 * schematu, narzuconego przez program, oraz zamknięcie pliku po 
 * zakończeniu zapisywania.  
 */
public class FileExport {
	private String path;
	private FileWriter file;
	private BufferedWriter out;
	
	/**
	 * Zachowuje informację o ścieżce do pliku, otwiera plik, a następnie
	 * otwiera strumień zapisywania znakowego do pliku. 
	 * @param path
	 * @throws IOException
	 */
	public FileExport(String path) throws IOException {
		this.path = path;
		this.file = new FileWriter(this.path);
		this.out = new BufferedWriter(file);
	}
	
	/**
	 * Zapisujemy kolejną linię w pliku, kończąc ją zaleznym od systemu,
	 * końcem zanku linii.
	 * @param str
	 * @throws IOException
	 */
	public void writeln(String str) throws IOException {
		out.write(str+"\n");
	}
	
	/**
	 * Zamknięcie strumienia zapisywania danych znakowych oraz pliku.
	 * @throws IOException
	 */
	public void close() throws IOException {
		out.close();
		file.close();
	}
	
	/**
	 * Metoda statyczna, która otrzymując jako parametry, model oraz 
	 * ścieżkę do pliku, tworzy obiekt tej klasy, otwierając pliku poprzez tworzenie
	 * lub opróżnianie istniejącego pliku, zapisanie struktury grafu, poprzez zapisanie 
	 * wymaganych do otworzenia informacji o wierzchołkach grafu, a następnie
	 * zapisując informacje o krawędziach grafu. Następnie zamyka plik.
	 * @param model
	 * @param path
	 * @throws IOException
	 */
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
