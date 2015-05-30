package org.rui.collection;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {

	protected String[] words = ("And that is how "
			+ "we know the Earth to be banana-shaped").split(" ");

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {

			private int index = 0;

			public boolean hasNext() {
				return index < words.length;
			}

			public String next() {
				return words[index++];
			}

			public void remove() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {

		for (String s : new IterableClass())
			System.out.print(s + ", ");
	}
}
