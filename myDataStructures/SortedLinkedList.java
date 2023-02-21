package myDataStructures;
import school.Student;

public class SortedLinkedList {
	private Node head;
	
	public SortedLinkedList() {
		head = new Node(null, null);
	}
	
	public void add(Comparable data) {
		Node currNode = head;
		
		while (currNode.next != null && data.compareTo((currNode.next).getData()) >= 0) {
			(currNode.next).setData(currNode.getData());
			currNode = currNode.next;
		}
		
		Node added = new Node(data, currNode.next);
		currNode.next = added;
	}
	
	public void delete(Comparable data) {
		if (isEmpty())
			throw new NullPointerException();
		
		Node currNode = head;
		Node nextNode = currNode.next;
		
		while (currNode != null && nextNode.next != null && !(data.equals(nextNode.getData()))) {
			currNode = currNode.next;
			nextNode = nextNode.next;
		}
		
		currNode.next = nextNode.next;
		nextNode.next = null;
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}
	
	public Comparable search(Comparable data) {
		Node currNode = head.next;
		
		while (currNode != null) {
			if (data.equals(currNode.getData()))
				return currNode.getData();
			
			currNode = currNode.next;
		}
		
		return null;
	}
	
	public String toString() {
		String res = "";
		Node currNode = head.next;
		
		while (currNode != null) {
			res += (currNode.getData()).toString() + "\n"; 
			currNode = currNode.next;
		}
		
		return res;
	}
	
	/*
	private void insertionSort() {
		Node currNode = head.next;
		while (currNode != null) {
			Node tempNode = currNode;
			Comparable thisNode = tempNode.getData();
			while (currNode.next != null && thisNode.compareTo(currNode.next) >= 0) {
				(currNode.next).setData(currNode.getData());
				currNode = currNode.next;
			}
			
		}
	} */
}
