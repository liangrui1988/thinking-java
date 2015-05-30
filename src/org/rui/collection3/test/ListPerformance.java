package org.rui.collection3.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * ¶ÔListµÄÑ¡Ôñ
 * 
 * @author lenovo
 * 
 */
public class ListPerformance {

	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qTests = new ArrayList<Test<LinkedList<Integer>>>();
	static {
		tests.add(new Test<List<Integer>>("get") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for (int i = 0; i < loops; i++) {
					list.clear();
					for (int j = 0; j < listSize; j++) {
						list.add(j);
					}
				}
				return loops * listSize;

			}
		});
		// /-------------------------------------------
		tests.add(new Test<List<Integer>>("get") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for (int i = 0; i < loops; i++) {
					list.get(rand.nextInt(listSize));
				}
				return loops;
			}
		});
		// /-------------------------------------------
		tests.add(new Test<List<Integer>>("set") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for (int i = 0; i < loops; i++) {
					list.set(rand.nextInt(listSize), 47);
				}
				return loops;
			}
		});
		// /-------------------------------------------
		tests.add(new Test<List<Integer>>("iteradd") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size() / 2;
				ListIterator<Integer> it = list.listIterator(half);

				for (int i = 0; i < LOOPS; i++) {
					it.add(47);
				}
				return LOOPS;
			}
		});

		// /-------------------------------------------
		tests.add(new Test<List<Integer>>("insert") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = list.size();
				for (int i = 0; i < loops; i++) {
					list.add(5,47);// minimize random -access costt
				}
				return loops;
			}
		});
		
		// /-------------------------------------------
				tests.add(new Test<List<Integer>>("remove") {
					@Override
					int test(List<Integer> list, TestParam tp) {
						int loops = tp.loops;
						int size = list.size();
						for (int i = 0; i < loops; i++) {
							list.clear();
							//list.addAll(new CountingIntegerList(size));// minimize random -access costt
						}
						return loops;
					}
				});

	}
}
