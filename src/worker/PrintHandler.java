package worker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Handles the printing of {@link Person} instances
 */
public class PrintHandler<T extends Registry> {
	
	/**
	 * File to print and read from
	 */
	private File file;
	
	/**
	 * Builder for parsing and writing
	 */
	private PersonBuilder builder;

	/**
	 * Instantiates a new instance with a specified file
	 * @param file the specified file
	 */
	public PrintHandler(File file) { 
		this.file = file;
		this.builder = new PersonBuilder();
	}

	/**
	 * Loads all file data to specified registry
	 * prints to stacktrace on parsing errors.
	 * @param registry specified
	 * @throws FileNotFoundException file doesn't exist
	 */
	public void loadTo(T registry) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			String text = input.nextLine();
			//split by delimiter
			String[] tokens = text.split(PersonParser.DELIMITER);
			
			// parse the first half.
			try {
				builder.setRole(tokens[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {
				builder.setHours(tokens[tokens.length-1]);
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// everything between is the name, name should be split by the delimiter
			builder.setName(String.join(PersonParser.DELIMITER, Arrays.copyOfRange(tokens, 1, tokens.length-1)));

			builder.reset();
		}
		input.close();
	}

	/**
	 * saves to {@link PrintHandler#file file} the specified registry
	 * @param registry
	 * @throws FileNotFoundException
	 */
	public void saveFrom(T registry) throws FileNotFoundException {
		try(PrintWriter writer = new PrintWriter(file)){
			Iterator<Person> iterator = registry.getIterator();
			while(iterator.hasNext()) {				
				builder.editPerson(iterator.next());
				//using this instead of toString since parsing is different
				writer.println(builder.toParsableString());
			}
		}
	}
}