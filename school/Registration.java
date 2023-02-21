package school;
import myDataStructures.*;
import java.util.Scanner;

public class Registration {
	static BinarySearchTree studentMaster;
	static SortedLinkedList[] studentMasterByID;
	static Course[] courseRoster = new Course[4];
	
	public static void main (String[] args) {
		intializeMaster();
		
		System.out.println("Welcome to School Manager!");
		
		while (true) {
			System.out.println("What would you like to do?\n(Type \"/help\" for a list of commands)\n");
			
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			
			if (s.equals("/help")) {
				printHelp();
			}
			else if (s.equals("s")) {
				System.out.println("Search by ID (1) or by name (2)?\n");
				s = in.nextLine();
				
				if(s.equals("1"))
					searchList(in, studentMasterByID);
				else if (s.equals("2"))
					searchList(in, studentMaster);
				else
					System.out.println("Invalid Command.");
			}
			else if (s.equals("c")) {
				searchRoster(in, courseRoster);
			}
			else if (s.equals("a")) {
				addFunc(in);
			}
			else if (s.equals("r")) {
				remFunc(in);
			}
			else if (s.equals("q")) {
				in.close();
				break;
			}
			else {
				System.out.println("Invalid command");
			}
		}
		
	}
	
	private static void intializeMaster () {
		StudentRecord lfl = new StudentRecord("Luiz Felipe", "Lemos", "24264859");
		StudentRecord jz = new StudentRecord("Jon", "Ziegler", "24257856");
		StudentRecord rs = new StudentRecord("Rishi", "Sunak", "23571234");
		StudentRecord gb = new StudentRecord("Gabriel", "Boric", "12489345");
		StudentRecord tc = new StudentRecord("Tim", "Cook", "10178340");
		
		studentMaster = new BinarySearchTree();
		studentMaster.insert(lfl);
		studentMaster.insert(jz);
		studentMaster.insert(rs);
		studentMaster.insert(gb);
		studentMaster.insert(tc);
		
		studentMasterByID = new SortedLinkedList[5];
		for (int i = 0; i < 5; i++)
			 studentMasterByID[i] = new SortedLinkedList();
		int h = hashFunc(Integer.parseInt(lfl.getID()));
		studentMasterByID[h].add(lfl);
		h = hashFunc(Integer.parseInt(jz.getID()));
		studentMasterByID[h].add(jz);
		h = hashFunc(Integer.parseInt(rs.getID()));
		studentMasterByID[h].add(rs);
		h = hashFunc(Integer.parseInt(gb.getID()));
		studentMasterByID[h].add(gb);
		h = hashFunc(Integer.parseInt(tc.getID()));
		studentMasterByID[h].add(tc);
		
		courseRoster[0] = new Course("Data Structures", "CS313");
		courseRoster[1] = new Course("Assembly Language", "CS240");
		courseRoster[2] = new Course("Theory of Computation", "CS320");
		courseRoster[3] = new Course("Computer Architecture", "CS343");
	}
	
	private static void printHelp() {
		System.out.println("(s) student; view student info");
		System.out.println("(c) class; view class info");
		System.out.println("(a) insert; insert student to a class");
		System.out.println("(r) remove; remove student from a class");
		System.out.println("(q) quit; terminate program\n");
	}
	
	private static void searchList(Scanner sc, Object l) {
		StudentRecord foundStudent = searchListHelper(sc, l);
		
		if (foundStudent != null) {
			System.out.println(foundStudent);
		}
		else {
			System.out.println("Student not found.");
		}
	}
	
	private static StudentRecord searchListHelper(Scanner sc, Object l) {
		StudentRecord foundStudent = null;
		
		if (l instanceof BinarySearchTree) {
			System.out.println("First Name: ");
			String tempFirst = sc.nextLine();
			System.out.println("Last Name: ");
			String tempLast = sc.nextLine();
			
			Student tempStudent = new Student(tempFirst, tempLast);
			foundStudent = (StudentRecord)(((BinarySearchTree)l).search(tempStudent));
		}
		else if (l instanceof SortedLinkedList[]) {
			System.out.println("Student ID:");
			String tempID = sc.nextLine();
			
			StudentRecord tempStudent = new StudentRecord(tempID);
			int h = hashFunc(Integer.parseInt(tempID));
			foundStudent = (StudentRecord)(studentMasterByID[h].search(tempStudent));
		}
		else
			throw new IllegalArgumentException();
		
		
		return foundStudent;
	}
	
	private static void searchRoster(Scanner sc, Course[] ca) {
		Course tempCourse = searchRosterHelper(sc, ca);
		
		if (tempCourse == null)
			System.out.println("Course not found");
		else
			System.out.println(tempCourse);
	}
	
	private static Course searchRosterHelper(Scanner sc, Course[] ca) {
		System.out.println("Course Number:");
		String tempNumber = sc.nextLine();
		
		Course tempCourse = new Course(tempNumber);
		
		for (Course c : ca) {
			try {
				if (tempCourse.equals(c)) {
					return c;
				}
			}
			catch(NullPointerException npe) {
				continue;
			}
		}
		
		return null;
	}
	
	private static void addFunc(Scanner sc) {
		StudentRecord foundStudent = searchListHelper(sc, studentMaster);
		
		if (foundStudent == null) {
			System.out.println("Student not found");
		}
		else {
			System.out.println("Course Number:");
			String tempNumber = sc.nextLine();
			
			Course tempCourse = new Course(tempNumber);
			Course[] foundRoster = foundStudent.getRegister();
			
			/*
			 * Checks if tempCourse is in Student's registered classes
			 * Exits function if it is 
			 */
			for (int i = 0; i < foundRoster.length; i++) {
				if (tempCourse.equals(foundRoster[i])) {
					System.out.println("The student is already registered for this class");
					return;
				}
			}
			
			int i = 0;
			Course foundCourse = courseRoster[i];
			while (!(tempCourse.equals(courseRoster[i])) && i < courseRoster.length) {
				if (tempCourse.equals(courseRoster[i]))
					foundCourse = courseRoster[i];
				else
					i++;
			}
			
			foundStudent.addCourse(foundCourse);
		}
	}
	
	private static void remFunc(Scanner sc) {
		Course foundCourse = searchRosterHelper(sc, courseRoster);
		
		if (foundCourse == null) {
			System.out.println("Course not found");
		}
		else {
			System.out.println("Student ID:");
			String tempID = sc.nextLine();
			
			StudentRecord tempStudent = new StudentRecord(tempID);
			BinarySearchTree foundList = foundCourse.getList();
			
			/*
			 * Checks if tempStudent is in Course's list
			 * Exits function if it isn't
			 */
			StudentRecord foundStudent = (StudentRecord)foundList.search(tempStudent);
			if (foundStudent == null) {
				System.out.println("This student is not registered in the course");
				return;
			}
			else {
				foundStudent.delCourse(foundCourse);
			}
		}
	}
	
	private static int hashFunc (int i) {
		int rem1 = i % 10;
		int rem2 = (i/10) % 10;
		int lastTwo = rem2 * 10 + rem1;
		return lastTwo % 5;
	}
}
