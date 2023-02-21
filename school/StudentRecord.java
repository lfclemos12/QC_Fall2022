package school;

public class StudentRecord extends Student {
	private String studentID;
	private Course[] courseReg;
	
	public StudentRecord(String fn, String ln, String id) {
		super(fn, ln);
		studentID = id;
		courseReg = new Course[5];
	}
	
	// Temporary constructor to make current build work
	public StudentRecord(String id) {
		super(null, null);
		studentID = id;
		courseReg = new Course[5];
	}
	
	public String getID() {
		return studentID;
	}
	
	public void setID(String id) {
		studentID = id;
	}
	
	public Course[] getRegister() {
		return courseReg;
	}
	
	
	// Implement correctly later
	public boolean equals(Object other) {
		if (other instanceof StudentRecord) {
			StudentRecord otherStudent = (StudentRecord)other;
			return studentID.equals(otherStudent.studentID);
		}
		else
			return false;
	}
	
	
	public void addCourse(Course c) {
		for(int i = 0; i < courseReg.length; i++) {
			if (courseReg[i] == null) {
				courseReg[i] = c;
				break;
			}
			else
				i++;
		}
		
		c.addStudent(this);
	}
	
	public void delCourse(Course c) {
		for(int i = 0; i < courseReg.length; i++) {
			if (c.equals(courseReg[i])) {
				courseReg[i] = null;
				break;
			}
			else
				i++;
		}
		
		c.delStudent(this);
	}
	
	public int compareTo(Object other) {
		if (other instanceof StudentRecord)
			return studentID.compareTo(((StudentRecord)other).studentID);
		else
			throw new IllegalArgumentException();
	}
	
	public String toString() {
		// to be implemented
		String res = lastName + ", " + firstName + " (" + studentID + ")\nCourses:\n";
		
		for(Course c : courseReg) {
			try {
				res += (c.getCourseNumber() + " - " + c.getCourseName() + "\n");
			}
			catch (NullPointerException npe) {
				continue;
			}
		}
		
		return res;
	}
}
