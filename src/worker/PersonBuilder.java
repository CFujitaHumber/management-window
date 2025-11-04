/**
 * @author Carson Fujita
 */
package worker;

import java.text.ParseException;

/**
 * Describes a contract that builds a {@link worker.Person} instance
 */
public class PersonBuilder implements PersonParser {

	/**
	 * Person instance being built.
	 */
	private Person person;

	/**
	 * empty builder
	 */
	public PersonBuilder() {
		this.person = new Person();
	}

	@Override
	public void setName(String name) {
		person.setName(name);
	}

	/**
	 * @throws NumberFormatException if there isn't a valid number
	 * @throws ParseExeception if the number is below zero
	 */
	@Override
	public void setHours(String hours) throws NumberFormatException, ParseException {
		//need a buffer incase of errors
		String buffer = hours;

		// remove all instances of "HRS"
		buffer = buffer.replaceAll("[Hh][Rr][Ss]", "");
		//trimming
		buffer = buffer.trim();
		//this may throw number format exception
		int parsed = Integer.parseInt(hours);

		if(parsed < 0) {
			int negativePosition = hours.indexOf('-'); //find the negative number
			throw new ParseException("Numbers below 0 are invalid",negativePosition);
		}
		person.setHours(parsed);
	}

	@Override
	public void setRole(String role) throws ParseException {
		person.setRole(Role.parseString(role));
	}

	/**
	 * Prints the resulting person
	 * @return the resulting person built
	 */
	public Person getResult() {
		return person;
	}

	/**
	 * resets the builder
	 */
	public void reset() {
		person = new Person();
	}
	
	@Override
	public String toParsableString() {
		return person.getRole().toString() + Parseable.DELIMITER 
				+ person.getName().toString() + Parseable.DELIMITER
				+ person.getHours();
	}
}
