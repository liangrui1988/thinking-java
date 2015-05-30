package org.rui.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程 是指在程序运行的时候在后台提供一种通用服务的线程
 * 
 * @author lenovo
 * 
 */
public class SimpleDeamons implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {

				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " >>> " + this);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDeamons());
			daemon.setDaemon(true);// 设为后台 线程
			daemon.start();
		}
		System.out.println("all daemons started------");
		try {
			// 试试调整休眠时间 以观察这个行为
			TimeUnit.MILLISECONDS.sleep(175);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
/**
 * output: all daemons started------ Thread[Thread-8,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@1e4f7c2 Thread[Thread-9,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@10dc6b5 Thread[Thread-4,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@1d0fafc Thread[Thread-6,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@c9d92c Thread[Thread-0,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@145f0e3 Thread[Thread-2,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@1e4f7c2 Thread[Thread-5,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@b8f8eb Thread[Thread-1,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@d0af9b Thread[Thread-7,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@f47396 Thread[Thread-3,5,main] >>>
 * org.rui.thread.basic.SimpleDeamons@170bea5
 */
