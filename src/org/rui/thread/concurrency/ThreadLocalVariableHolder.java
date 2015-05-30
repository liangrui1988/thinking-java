package org.rui.thread.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程本地存存储
 * 
 * 防步任务共享资源上产生冲突的第二种方式是根除对变量共享。 线程本地存储是一种自动化机制，可以为使用相同变量的每个不同的线程都创建不同的存储。因此，
 * 如果你有5个线程都要使用变量X所表示的对象，那线程本地存储就会生成5个用于x的不同的存储块。主要是， 它们使得你可以将状态与线程关联起来。
 * 
 * @author lenovo
 * 
 */
class Accessor implements Runnable {
	private final int id;

	public Accessor(int idn) {
		id = idn;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.incrment();
			System.out.println(this);
			Thread.yield();
		}

	}

	// toString()
	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}

}

// /////////////////////////////
public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);

		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}

	};

	// //
	public static void incrment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Accessor(i));
		TimeUnit.SECONDS.sleep(3);// run for a while
		exec.shutdownNow();// all accessors will quit

	}
}
/**
 * ThreadLocal对象通常当作静态域存储。在创建ThreadLocal时，你只能通过get() set()方法来访问该对象的内容，
 * 其中，get()方法将返回与其线程相关联的对象副本，而set会将参数插入到为其线程存储的对象中，并返回存储中原有的对象。
 * increment()和get()方法在这里演示了这点， ThreadLocal保证不会出现竞争条件.
 * 
 * 
 * 
 * output(sample) #1: 39807 #1: 39808 #1: 39809 #1: 39810 #1: 39811 #1: 39812
 * #0: 44495 #4: 41633 #4: 41634 #4: 41635 #4: 41636 #4: 41637 #4: 41638 #4:
 * 41639 #4: 41640
 */
