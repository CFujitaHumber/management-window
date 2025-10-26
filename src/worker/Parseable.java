/**
 * @author Carson Fujita
 */
package worker;

/**
 * Ok, so UML did not consider the fact that Java doesn't allow static inheritance... 
 * This stays here since I need a constant delimiter
 */
public interface Parseable {
	
	/**
	 * So apparently I can't use a char because string splits use solely Strings...
	 * So it's a string... Fortunately this should contain just 16-bits regardless.
	 */
	public static final String DELIMITER= " ";
	
}
