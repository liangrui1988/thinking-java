package org.rui.thread.res;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任何IntGenerator都可以用下面的EvenCherker类来测试
 * 
 * @author lenovo
 * 
 */
public class EvenChecker implements Runnable {

	private IntGenerator generator;
	private final int id;

	public EvenChecker(IntGenerator g, int ident) {
		this.generator = g;
		this.id = ident;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cancel();// cancels all evencheckers
			}
		}

	}

	// /test any type of intgenerator
	public static void test(IntGenerator gp, int count) {
		System.out.println("press control-c to exit");
		ExecutorService ex = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			ex.execute(new EvenChecker(gp, i));
		}
		ex.shutdown();

		/*
		 * for(int i=0;i<count;i++) { Thread t=new Thread(new
		 * EvenChecker(gp,i)); t.start(); }
		 */

	}

	// /
	public static void test(IntGenerator gp) {
		test(gp, 10);
	}

}
