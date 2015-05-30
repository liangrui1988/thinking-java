package org.rui.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程优先级
 * 
 * @author lenovo
 * 
 */
public class SimpleProorities implements Runnable {

	private int countDown = 5;
	private volatile double d; // No optimization
	private int priority;

	public SimpleProorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return Thread.currentThread() + " : " + countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);// 开头设置优先级
		while (true) {
			for (int i = 1; i < 100000; i++) {
				// 比任何其他值都更接近 pi（即圆的周长与直径之比）的 double 值。
				// 任何其他值都更接近 e（即自然对数的底数）的 double 值。
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}

			System.out.println(this);
			if (--countDown == 0)
				return;
		}

	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new SimpleProorities(Thread.MIN_PRIORITY));
		exec.execute(new SimpleProorities(Thread.MAX_PRIORITY));// 优先级高

		exec.shutdown();

	}

}
