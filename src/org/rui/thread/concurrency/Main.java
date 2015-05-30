package org.rui.thread.concurrency;

import java.util.Random;

public class Main {

	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);

		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}

	};

	public static void main(String[] args) {
		System.out.println(value.get());

	}
}
