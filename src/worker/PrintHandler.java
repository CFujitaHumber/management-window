package worker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Handles the printing of {@link Person} instances
 */
public class PrintHandler {
	private Collection<Person> items;
	private File file;

	public PrintHandler(File file, Collection<Person> list) {
		this.file = file;
		this.items = list;
	}
	
	public void load() throws NumberFormatException, ParseException, FileNotFoundException {
		Scanner input = new Scanner(file);
		
		while(input.hasNextLine()) {
			items.add(Person.parseString(input.nextLine()));
		}
		
		input.close();
	}

	public void save() throws FileNotFoundException {
		try(PrintWriter writer = new PrintWriter(file)){
			Iterator<Person> iterator = items.iterator();
			while(iterator.hasNext()) {				
				writer.println(iterator.next().toString());
			}
		}
	}

}
