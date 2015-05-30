package org.rui.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * 可以通过调用isDaemon方法来确定线程是否是一个后台线程。如果是一个后台线程， 那么创建的任何线程将被自动设置成后台线程，如下例所示
 * 
 * @author lenovo
 * 
 */
class Daemon implements Runnable {

	private Thread[] t = new Thread[10];

	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("daemonSpawn " + i + " started, ");
		}
		for (int i = 0; i < t.length; i++) {
			System.out.println("t[" + i + "].isDaemon()=" + t[i].isDaemon()
					+ " ,");
		}
		/**
		 * 对线程调度器 java线程机制的一部分，可以将cpu从一个线程转移给另一个线程 的一种建议 它在声明：
		 * 我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机 完全是先择性的，
		 * 
		 */
		while (true)
			Thread.yield();// 把控制权交给其它线程

	}

}

class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true)
			Thread.yield();
	}
}

// //////////////////////////////////
public class Daemons {
	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.out.println("d.isDaemon()=" + d.isDaemon() + "  , ");
		TimeUnit.MILLISECONDS.sleep(1);
	}

}
/**
 * d.isDaemon()=true , daemonSpawn 0 started, daemonSpawn 1 started, daemonSpawn
 * 2 started, daemonSpawn 3 started, daemonSpawn 4 started, daemonSpawn 5
 * started, daemonSpawn 6 started, daemonSpawn 7 started, daemonSpawn 8 started,
 * daemonSpawn 9 started, t[0].isDaemon()=true , t[1].isDaemon()=true ,
 * t[2].isDaemon()=true , t[3].isDaemon()=true , t[4].isDaemon()=true ,
 * t[5].isDaemon()=true , t[6].isDaemon()=true , t[7].isDaemon()=true ,
 * t[8].isDaemon()=true , t[9].isDaemon()=true ,
 */
