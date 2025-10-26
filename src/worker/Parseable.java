/**
 * @author Carson Fujita
 */
package worker;

import java.text.ParseException;

/**
 * Denotes an contract that can be parsed from string and back.
 */
public interface Parseable {
	/**
	 * Parses the text into a {@link Parseable} instance.
	 * @param text the text to be parsed. 
	 * It's the direct opposite of {@link Parseable#toString() toString()}
	 * @return a {@link Parseable} instance
	 * @see Parseable#toString()
	 */
	Parseable parse(String text) throws ParseException;	
	
	/**
	 * Turns a {@link Parseable} instance
	 * into a string
	 * @return string that can be parsed
	 * @see Parseable#parse(String)
	 */
	String toString();
}
