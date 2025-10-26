/**
 * @author Carson Fujita
 */
package worker;

import java.text.ParseException;

/**
 * Denotes a contract that defines the duty of a {@link Person}
 */
public enum Role implements Parseable {
	// Just to be extra safe going to override toString
	// feels stupid.
	Professor {
		@Override
		public String toString() {
			return "professor";
		}
	},
	Student {
		@Override
		public String toString() {
			return "student";
		}
	},
	TA {
		@Override
		public String toString() {
			return "ta";
		}
	},;

	/**
	 * It parses the text into Role enumeration. It might be best to call {@linkplain String.lower()} 
	 * before passing to parameter. 
	 * @param text text to parse.
	 * @throws ParseException when there is no matching Role
	 * @returns Role
	 */
	@Override
	public Parseable parse(String text) throws ParseException {
		// simple switch
		switch(text) {
		case "professor":
			return Role.Professor;
		case "student":
			return Role.Student;
		case "ta":
			return Role.TA;
		default:
			// setting the errorOffset to zero because we are using a switch case to parse.
			// yes, this feels stupid.
			throw new ParseException(String.format("text \"%s\" does not contain valid Role parse ", text), 0);
		}
	}
}
