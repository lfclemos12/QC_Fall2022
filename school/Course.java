package school;

import myDataStructures.*;

public class Course {
	private String courseName;
	private String courseNumber;
	private int limit;
	private int count;
	private BinarySearchTree studentRoster;
	private Queue waitingList;
	
	public Course(String name, String number) {
		courseName = name;
		courseNumber = number;
		limit = 10;
		count = 0;
	}
	
	public Course(String name, String number, int l) {
		courseName = name;
		courseNumber = number;
		limit = l;
		count = 0;
	}
	
	public Course(String number) {
		courseNumber = number;
		limit = 10;
		count = 0;
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
	
	public BinarySearchTree getList() {
		return studentRoster;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Course) {
			return courseNumber.equals(((Course)other).courseNumber);
		}
		else {
			return false;
		}
	}
	
	public void addStudent (Student s) {
		if (BinarySearchTree.isEmpty(studentRoster))
			studentRoster = new BinarySearchTree(s);
		else if (count <= limit) {
			studentRoster.insert(s);
			count++;
		}
		else {
			System.out.println(courseNumber + " - " + courseName + " is full.\n"
								+ "Adding to waiting list.");
			waitingList.enqueue(s);
		}
	}
	
	public void delStudent (Student s) {
		if (BinarySearchTree.isEmpty(studentRoster))
			return;
		else {
			studentRoster.delete(s);
			if (!(waitingList.isEmpty()))
				studentRoster.insert(waitingList.dequeue());
			count--;
		}
	}
	
	public boolean isFull() {
		return count == limit;
	}
	
	public String toString() {
		return courseNumber + " - " + courseName + "\n" + "Students:\n" + studentRoster.toString();
	}
}
