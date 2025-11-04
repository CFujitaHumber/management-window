/**
 * @author Carson Fujita 
 */
package worker;

import java.util.Iterator;

/**
 * Due to time constrants and requirement sepecifications this is limited
 * The contract specifies the CRUD of person building in a data structure
 */
public interface Registry {

	void addPerson(PersonBuilder builder);
	
	Iterator<Person> getIterator();
}
