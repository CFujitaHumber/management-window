/**
 * @author Carson Fujita
 */
package worker;

/**
 * Defines a contract of a person with their name, hours of work, and role within a company.
 */
public class Person{

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
	 * Instantiates a new empty {@link Person}
	 */
	Person() {}

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
