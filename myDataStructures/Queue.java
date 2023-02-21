package myDataStructures;

public class Queue {
	private Comparable[] data;
	private int last = -1;
	private static final int MAX_SIZE = 10;
	
	public Queue(int size) {
		data = new Comparable[size];
	}
	
	public Queue() {
		data = new Comparable[MAX_SIZE];
	}
	
	public void enqueue(Comparable o) {
		if (last >= data.length)
			System.out.println("Queue is full");
		else
			data[++last] = o;
	}
	
	public Comparable dequeue() {
		if (last == -1) {
			System.out.println("Queue is empty");
			return null;
		}
		else {
			Comparable o = data[0];
			for (int i = 0; i < last-1; i++)
				data[i] = data[i+1];
			last--;
			return o;
		}
	}
	
	public boolean isEmpty() {
		return last == -1;
	}
	
	public String toString() {
		return "Queue Capacity: " + (last+1) + "/" + (data.length);
	}
}
