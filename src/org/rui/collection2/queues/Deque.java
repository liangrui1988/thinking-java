package org.rui.collection2.queues;

import java.util.LinkedList;

/**
 * ˫����о���һ�����У�������������κ�һ����ӻ��Ƴ�Ԫ�أ� LinkedList�޷�ʵ�������Ľӿڣ�������ʹ�����������һ��Deque�࣬
 * 
 * @author lenovo
 * 
 * @param <T>
 */

public class Deque<T> {
	private LinkedList<T> deque = new LinkedList<T>();

	public void addFirst(T e) {
		deque.addFirst(e);
	}

	public void addLast(T e) {
		deque.addLast(e);
	}

	public T getFirst(T e) {
		return deque.getFirst();
	}

	public T getLast(T e) {
		return deque.getLast();
	}

	public T removeFirst() {
		return deque.removeFirst();
	}

	public T removeLast() {
		return deque.removeLast();
	}

	public int size() {
		return deque.size();
	}

	public String toString() {
		return deque.toString();
	}

	// and other methods as necessary............

	// //////////////////////////////////////////////
	public static void fillTest(Deque<Integer> de) {
		for (int i = 10; i < 17; i++)
			de.addFirst(i);
		for (int i = 50; i < 55; i++)
			de.addLast(i);
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		fillTest(deque);
		System.out.println(deque);
		while (deque.size() != 0)
			System.out.print(deque.removeFirst() + "  ");
		System.out.println();
		fillTest(deque);
		while (deque.size() != 0)
			System.out.print(deque.removeLast() + "  ");
		System.out.println();

	}

}
/**
 * output: [16, 15, 14, 13, 12, 11, 10, 50, 51, 52, 53, 54] 16 15 14 13 12 11 10
 * 50 51 52 53 54 54 53 52 51 50 10 11 12 13 14 15 16
 */
