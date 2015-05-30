package org.rui.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueDemo {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Random random = new Random(47);
		for (int i = 0; i < 10; i++) {
			pq.offer(random.nextInt(i + 10));
		}
		QueueDemo.printQ(pq);

		List<Integer> list = Arrays.asList(25, 22, 20, 1, 25, 3, 30);
		pq = new PriorityQueue<Integer>(list);
		QueueDemo.printQ(pq);

		String fact = "HELLO world abc ABC";

		List<String> list2 = Arrays.asList(fact.split(" "));
		PriorityQueue<String> pqStr = new PriorityQueue<String>(list2);
		QueueDemo.printQ(pqStr);
		// ======
		pqStr = new PriorityQueue<String>(list2.size(),
				Collections.reverseOrder());
		pqStr.addAll(list2);
		QueueDemo.printQ(pqStr);

	}

}
