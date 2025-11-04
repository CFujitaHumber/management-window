/**
 * @author Carson Fujita
 */
package worker;

import java.text.ParseException;

/**
 * Contract defines the interface of a builder that works off of parsing
 */
public interface PersonParser {
	
	/**
	 * So apparently I can't use a char because string splits use solely Strings...
	 * So it's a string... Fortunately this should contain just 16-bits regardless.
	 */
	String DELIMITER= " ";

	/**
	 * Sets the name of the {@link worker.Person}
	 */
	void setName(String name);

	/**
	 * Sets the hours of a {@link worker.Person} by parsing
	 * the specified hours String to a number.
	 * precision is determined by the subclass. 
	 * @param hours parseable string value 
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	void setHours(String hours) throws NumberFormatException, ParseException;

	/**
	 * Sets the role of a {@link worker.Person} by parsing
	 * the specified role to whatever enum or type the subclass desires.
	 * @param role parseable string value
	 * @throws ParseException 
	 */
	void setRole(String role) throws ParseException;

	/**
	 * imports a person to change builder values
	 * @param person
	 */
	void editPerson(Person person);

	/**
	 * Returns this as a parsable String
	 */
	String toParsableString();
}
