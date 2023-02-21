package school;

import myDataStructures.*;

public class CourseOld {
	private String courseName;
	private String courseNumber;
	private int limit;
	private int count;
	private SortedLinkedList studentRoster;
	private Student[] waitingList;
	
	public CourseOld(String name, String number) {
		courseName = name;
		courseNumber = number;
		limit = 10;
		count = 0;
		studentRoster = new SortedLinkedList();
	}
	
	public CourseOld(String name, String number, int l) {
		courseName = name;
		courseNumber = number;
		limit = l;
		count = 0;
		studentRoster = new SortedLinkedList();
	}
	
	public CourseOld(String number) {
		courseNumber = number;
		limit = 10;
		count = 0;
		studentRoster = new SortedLinkedList();
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName (String name) {
		courseName = name;
	}
	
	public String getCourseNumber() {
		return courseNumber;
	}
	
	public void setCourseNumber(String number) {
		courseNumber = number;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount (int c) {
		count = c;
	}
	
	public SortedLinkedList getList() {
		return studentRoster;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Course) {
			return courseNumber.equals(((CourseOld)other).courseNumber);
		}
		else {
			return false;
		}
	}
	
	public void addStudent (Student s) {
		studentRoster.add(s);
		count++;
		// implement waiting list later
	}
	
	public void delStudent (Student s) {
		studentRoster.delete(s);
		count--;
	}
	
	public String toString() {
		// to be implemented
		return courseNumber + " - " + courseName + "\n" + "Students:\n" + studentRoster.toString();
	}
}
