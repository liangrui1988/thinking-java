package org.rui.thread.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 中断
 * 
 * @author lenovo
 * 
 */
class SleepBlocked implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

// ////
class IOBlocked implements Runnable {

	private InputStream in;

	public IOBlocked(InputStream is) {
		in = is;
	}

	@Override
	public void run() {
		System.out.println("waiting for read();");
		try {
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("interrupted from blocked I/O");
			} else {

			}
			e.printStackTrace();
		}

	}

}

// ////////////

class SynchronizedBlocked implements Runnable {

	public synchronized void f() {
		while (true) {//不释放锁
			Thread.yield();
		}
	}

	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f();// 这个线程锁了
			}
		}.start();
	}

	@Override
	public void run() {
		System.out.println("试图调用f()");
		f();
		System.out.println("exiting synchroniedBlocked .run()");
	}
}

// -------------------------------------------
public class Interrupting {
	private static ExecutorService exe = Executors.newCachedThreadPool();

	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exe.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("interrupting:" + r.getClass().getName());
		f.cancel(true);// intrrupts if running
		System.out.println("interrupt sent to " + r.getClass().getName());
	}
	
	public static void main(String[] args ) throws InterruptedException{
		test(new SleepBlocked());
		System.out.println("-----------------------");
		test(new IOBlocked(System.in));
		System.out.println("-----------------------");
		test(new SynchronizedBlocked());
		System.out.println("-----------------------");
		TimeUnit.MILLISECONDS.sleep(3);
		System.out.println("aborting with system.exit(0)");
		System.exit(0);//since last 2 interrupts failed
	}

}
/**
 * output:
interrupting:org.rui.thread.concurrency.SleepBlocked
interrupt sent to org.rui.thread.concurrency.SleepBlocked
InterruptedException
-----------------------
Exiting SleepBlocked.run()
waiting for read();
interrupting:org.rui.thread.concurrency.IOBlocked
interrupt sent to org.rui.thread.concurrency.IOBlocked
-----------------------
试图调用f()
interrupting:org.rui.thread.concurrency.SynchronizedBlocked
interrupt sent to org.rui.thread.concurrency.SynchronizedBlocked
-----------------------
aborting with system.exit(0)

 */
