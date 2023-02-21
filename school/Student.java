package school;

public class Student implements Comparable<Object> {
	protected String firstName;
	protected String lastName;
	
	public Student(String fn, String ln) {
		firstName = fn;
		lastName = ln;
	}
	
	public int compareTo(Object other) {
		if (other instanceof Student) {
			int c = lastName.compareTo(((Student)other).lastName);
			if (c == 0) {
				c = firstName.compareTo(((Student)other).firstName);
			}
			
			return c;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String fn) {
		firstName = fn;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String ln) {
		lastName = ln;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Student)
			return lastName.equals(((Student)other).lastName) && firstName.equals(((Student)other).firstName);
		else
			return false;
	}
	
	public String toString() {
		// to be implemented
		return lastName + ", " + firstName;
	}
}
