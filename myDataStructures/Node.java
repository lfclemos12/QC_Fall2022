package myDataStructures;
import school.Student;

public class Node {
	private Comparable data;
	public Node next;
	
	public Node (Comparable data, Node next) {
		this.data = data;
		this.next = next;
	}
	
	public Comparable getData() {
		return data;
	}
	
	public void setData(Comparable data) {
		this.data = data;
	}
}
