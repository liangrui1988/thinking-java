package org.rui.thread.volatiles;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类， java SE5引入了诸如AtomicInteger AtomicLong AtomicReference
 * 等特殊的原子性变量类，它们提供下面形式的原子性条件更新操作：
 * 
 * boolean compareAndSet(expectedValue,updateValue);
 * 
 * 这些类被调整为可以使用在某些现代处理器上的可获得的，并且是在机器级别上的原子性，
 * 因此在使用它们时，通常不需要担心。对于常规编程来说，它们很少会派上用场，但是在涉级性能调优时，
 * 它们就大有用武之地了，例如，我们可以使用AtomicInteger来重写AtomictyTest.java
 * 
 * @author lenovo
 * 
 */
public class AtomicIntegerTest implements Runnable {
	/**
	 * 这里我们通过使用AtomicInteger而消除了synchronized关键字。
	 * 因为这个程序不会失败，所以添加了一个timer,以便在5秒钟之后自动地终止
	 */
	private AtomicInteger i = new AtomicInteger(0);

	public int getValue() {
		return i.get();
	}

	private void evenIncrement() {
		i.addAndGet(2);
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		// 订时器
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest at = new AtomicIntegerTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}

	}
}
/**
 * output: Aborting
 */
