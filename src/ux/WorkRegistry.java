/**
 * @author Carson Fujita
 */
package ux;

import java.util.Collection;
import java.util.Iterator;

import worker.Person;
import worker.PersonBuilder;
import worker.Registry;

/**
 * Defines a contract of a collection of Person instances created from a builder.
 */
public class WorkRegistry implements Registry{

	private Collection<Person> registry;
	
	/**
	 * Creates a Registry Instance that has a collection of Persons.
	 * @param registry the specified collection
	 */
	public WorkRegistry(Collection<Person> registry) {
		this.registry = registry;
	}
	
	@Override
	public void addPerson(PersonBuilder builder) {
		registry.add(builder.getResult());
	}

	@Override
	public Iterator<Person> getIterator() {
		return registry.iterator();
	}
}