package org.rui.test1;

class Book {
	boolean checkedout = false;

	Book(boolean b) {
		this.checkedout = b;
	}

	void cehckIn() {
		checkedout = false;
	}

	protected void finalize() {
		 System.out.println("finalize----"+checkedout);
		if (checkedout) {
			System.out.println("error checked out");
		}
	}

}

public class Finalize {
	public static void main(String[] args) {

		// System.out.println("h");
		Book b = new Book(true);
		 b.cehckIn();
		new Book(true);

		System.gc();

	}
}
