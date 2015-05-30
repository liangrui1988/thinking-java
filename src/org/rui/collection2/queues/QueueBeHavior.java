package org.rui.collection2.queues;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.rui.generics.anonymity.Generator;

/**
 * 下面涉及Queue实现的大部分操作的基本示例 可以看到除了优先队列，Queue将精确地按照元素被置于Queue中的顺序产生它们
 * 
 * @author lenovo
 * 
 */
public class QueueBeHavior {
	private static int count = 10;

	static <T> void test(Queue<T> queue, Generator<T> gen) {
		for (int i = 0; i < count; i++) {
			// T temp=gen.next();
			// System.out.println(temp);
			queue.offer(gen.next());
		}

		while (queue.peek() != null)
			System.out.print(queue.remove() + "  ");
		System.out.println();
	}

	static class Gen implements Generator<String> {
		String[] s = ("one tow three four five six seven eight nine ten"
				.split(" "));
		int i;

		public String next() {
			return s[i++];
		}
	}

	public static void main(String[] args) {
		test(new LinkedList<String>(), new Gen());
		test(new PriorityQueue<String>(), new Gen());
		test(new ArrayBlockingQueue<String>(count), new Gen());
		test(new ConcurrentLinkedQueue<String>(), new Gen());
		test(new LinkedBlockingQueue<String>(), new Gen());
		test(new PriorityBlockingQueue<String>(), new Gen());
	}
}
/**
 * output: one tow three four five six seven eight nine ten eight five four nine
 * one seven six ten three tow one tow three four five six seven eight nine ten
 * one tow three four five six seven eight nine ten one tow three four five six
 * seven eight nine ten eight five four nine one seven six ten three tow
 */
