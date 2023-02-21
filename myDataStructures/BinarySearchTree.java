package myDataStructures;

public class BinarySearchTree {
	private BinarySearchTree left;
	private BinarySearchTree right;
	private Comparable root;
	
	public BinarySearchTree () {
		root = null;
		left = null;
		right = null;
	}
	
	public BinarySearchTree (Comparable root) {
		this.root = root;
		left = null;
		right = null;
	}
	
	public Comparable getRoot() {
		return root;
	}
	
	public void setRoot (Comparable root) {
		this.root = root;
	}
	
	public void insert(Comparable val) {
		if (root == null)
			root = val;
		insertHelper(this, val);
	}
	
	private static void insertHelper (BinarySearchTree t, Comparable val) {
		if (t == null) {
			t = new BinarySearchTree(val);
			return;
		}
		if (val.compareTo(t.root) <= 0) {
			insertHelper(t.left , val);
		}
		else {
			insertHelper(t.right, val);
		}
	}
	
	public BinarySearchTree delete (Comparable val) {
		try {
			return deleteHelper(this, val);
		}
		catch (Exception e) {
			System.out.println("The queried value was not found");
			return null;
		}
	}
	
	private static BinarySearchTree deleteHelper (BinarySearchTree t, Comparable val) throws Exception {
		if (t == null) throw new Exception();
		int c = val.compareTo(t.root);
		if (c < 0) 
			t.left = deleteHelper(t.left, val);
		else if (c > 0)
			t.right = deleteHelper(t.right, val);
		else {
			if (t.left == null)
				t = t.right;
			else if (t.right == null)
				t = t.left;
			else {
				BinarySearchTree temp = t.right;
				while (temp.left != null)
					temp = temp.left;
				t.root = temp.root;
				t.right = deleteHelper(t.right, t.root);
			}
		}
		
		return t;
	}
	
	public Comparable search (Comparable val) {
		return searchHelper(this, val);
	}
	
	private static Comparable searchHelper (BinarySearchTree t, Comparable val) {
		if (t == null)
			return null;
		else if (val.compareTo(t.root) == 0)
			return t.root;
		else
			if (val.compareTo(t.root) < 0)
				return searchHelper(t.left, val);
			else
				return searchHelper(t.right, val);
	}
	
	public static boolean isEmpty(BinarySearchTree t) {
		return t == null;
	}
	
	public String toString() {
		return toStringHelper(this);
	}
	
	private static String toStringHelper(BinarySearchTree t) {
		String str = "";
		toStringHelper(t.left);
		str += t.root.toString();
		toStringHelper(t.right);
		
		return str;
	}
}
