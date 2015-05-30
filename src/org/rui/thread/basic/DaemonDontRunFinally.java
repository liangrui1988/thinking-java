package org.rui.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * 你就该意识到后台进程在不执行finally子句的情况下就会终止其run()方法
 * 
 * @author lenovo
 * 
 */

class ADeamon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("starting adaemon");
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("正在退出 经过 InterruptedException");
		} finally {
			// 这应该 run?
			System.out.println("this should always run?");
		}

	}

}

public class DaemonDontRunFinally {
	// 你将看到finally子句不会执行 如果注释t.setDaemon(true); 就会看到
	public static void main(String[] args) {
		Thread t = new Thread(new ADeamon());
		t.setDaemon(true);
		t.start();
		// 一旦main退出 jvm就会立即关闭后台线程
	}
}
/**
 * output: starting adaemon
 */
