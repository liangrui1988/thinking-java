package org.rui.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 适配器方法惯用法
 * 
 * @author lenovo
 * @param <T>
 */

class ReversibleArrayList<T> extends ArrayList<T> {
	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}

	public Iterable<T> reverseds() {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					int current = size() - 1;

					@Override
					public boolean hasNext() {
						return current > -1;
					}

					@Override
					public T next() {
						return get(current++);
					}

					@Override
					public void remove() {
						// TODO Auto-generated method stub
						throw new UnsupportedOperationException();
					}
				};

			}

		};

	}

}

public class AdapterMetodIdiom {

	public static void main(String[] args) {
		ReversibleArrayList<String> rl = new ReversibleArrayList<String>(
				Arrays.asList("To be or not to be".split(" ")));

		for (String s : rl) {
			System.out.print(s + "  ");
		}
		System.out.println("-----");

		for (String s : rl.reverseds()) {
			System.out.print(s + "  ");
		}

	}

}
