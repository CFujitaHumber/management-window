/**
 * @author Carson Fujita
 */
package worker;

import java.text.ParseException;
import java.util.Arrays;

/**
 * Defines a contract of a person with their name, hours of work, and role within a company.
 */
public class Person {

	/**
	 * The name of this {@link Person}; full name.
	 * UTF-16
	 * length limit would be 2^31-1... 
	 * No one would have a name that long. 
	 * Hopefully string pool optimization will not affect this... 
	 * hopefully...
	 */
	private String name;
	

	/**
	 * The hours this {@link Person} worked either annually, bi-weekly...
	 * The requirements document doesn't specify.
	 * Shouldn't be possible to exceed the 32-bit size of integer.
	 */
	private int hours;
	
	/**
	 * The role of the person. This allows for modularity. 
	 * Will I ever use this code again? Probably not. 
	 */
	private Role role;

	/**
	 * Only for the static factory 
	 * @see Person#parse(String)
	 */
	private Person(){};

	/**
	 * Instantiates a new {@link Person}
	 * @param role the specified role
	 * @param name the specified name
	 * @param hours the specified hours
	 */
	public Person(Role role, String name, int hours) {
		this.role = role;
		this.name = name;
		this.hours = hours;
	}

	/**
	 * Parses the name by reading the first string token as the {@link Person#role} the last token 
	 * as the {@link Person#hours hours} and everything in-between as the {@link Name}
	 * @param text the text to parse
	 * @return a new person object
	 * @throws ParseException parsing failed
	 * @throws NumberFormatException hour format is wrong
	 */
	public static Person parse(String text) throws ParseException, NumberFormatException {
		Person export = new Person();
		//split by delimter
		String[] tokens = text.split(Parseable.DELIMITER);
		
		// parse the first half.
		export.role = Role.parse(tokens[0]);
		
		// hours must be last
		export.hours = Integer.parseInt(tokens[tokens.length-1]);
		
		// everything between is the name, name should be split by the delimiter
		export.name = String.join(Parseable.DELIMITER, Arrays.copyOfRange(tokens, 1, tokens.length-1));
		return export;
	}

	@Override
	public String toString() {
		return role.toString() + Parseable.DELIMITER 
				+ name.toString() + Parseable.DELIMITER
				+ Integer.toString(hours);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
