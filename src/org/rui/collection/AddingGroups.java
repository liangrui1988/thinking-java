package org.rui.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddingGroups {
	public static void main(String[] args) {
		Collection<Integer> coll = new ArrayList<Integer>(Arrays.asList(1, 2,
				3, 4, 5));
		Integer[] moreInts = { 6, 7, 8, 9 };
		Collections.addAll(coll, moreInts);
		Collections.addAll(coll, 10, 11, 12, 13);
		// coll.iterator();
		System.out.println("coll:" + coll);
		List<Integer> list = Arrays.asList(14, 15, 16, 17, 18);
		list.set(1, 99);
		System.out.println("list:" + list);
		// iteraotr
		System.out.println("ListIterator===============");
		java.util.ListIterator<Integer> it = list.listIterator();
		while (it.hasNext())
			System.out.println(it.next() + "," + it.nextIndex() + ","
					+ it.previousIndex());

		while (it.hasPrevious()) {
			System.out.println(" previous: " + it.previous());
		}
		System.out.println(list);
		it = list.listIterator(3);
		while (it.hasNext()) {
			it.next();
			it.set(88);
		}
		System.out.println(list);
		System.out.println();

	}
}
