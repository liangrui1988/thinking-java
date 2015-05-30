package org.rui.thread.block;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		Timer timer = new Timer();
		// 安排指定的任务在指定的延迟后开始进行重复的固定速率执行。
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;

			public void run() {
				if (prod) {
					System.out.println("\n notify() ");
					Task.blocker.prod();//
					prod = false;
				} else {
					System.out.println("\n notifyAll()");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		TimeUnit.SECONDS.sleep(5);// run for a while...
		timer.cancel();// 终止此计时器，
		System.out.println("\n Timer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task.blocker.prodAll()");
		Task2.blocker.prodAll();//唤醒task2 不包括任何在Task.blocker中的锁上等待的任务
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\nshutting down");
		exec.shutdownNow();

	}
}

class Blocker {
	synchronized void waitingCall() {
		try {
			while (!Thread.interrupted()) {
				wait();
				System.out.println(Thread.currentThread() + " ");
			}

		} catch (InterruptedException e) {
			// ok to exit this way
		}
	}

	synchronized void prod() {
		notify();//在众多等候同一个锁的任务中，只有一个会被唤醒，因此如果你希望使用nofify ，就必须保证被唤醒的是恰当的任务
	}

	synchronized void prodAll() {
		notifyAll();//只有等待这个锁的任务才会被唤醒
	}
}

// ////////
class Task implements Runnable {
	static Blocker blocker = new Blocker();

	public void run() {
		blocker.waitingCall();
	}

}

// ////////
class Task2 implements Runnable {
	// a separate blocker object 一个单独的拦截器对象
	static Blocker blocker = new Blocker();

	public void run() {
		blocker.waitingCall();
		//System.out.println(Thread.currentThread()+" : Task 不会被换醒Task2");
	}

}
/**
 * output:
 * 
 notify() 
Thread[pool-1-thread-2,5,main] 

 notifyAll()
Thread[pool-1-thread-2,5,main] 
Thread[pool-1-thread-5,5,main] 
Thread[pool-1-thread-4,5,main] 
Thread[pool-1-thread-3,5,main] 
Thread[pool-1-thread-1,5,main] 

 notify() 
Thread[pool-1-thread-2,5,main] 

 notifyAll()
Thread[pool-1-thread-2,5,main] 
Thread[pool-1-thread-1,5,main] 
Thread[pool-1-thread-3,5,main] 
Thread[pool-1-thread-4,5,main] 
Thread[pool-1-thread-5,5,main] 

 notify() 
Thread[pool-1-thread-2,5,main] 

 notifyAll()
Thread[pool-1-thread-2,5,main] 
Thread[pool-1-thread-5,5,main] 
Thread[pool-1-thread-4,5,main] 
Thread[pool-1-thread-3,5,main] 
Thread[pool-1-thread-1,5,main] 

 notify() 
Thread[pool-1-thread-2,5,main] 

 notifyAll()
Thread[pool-1-thread-2,5,main] 
Thread[pool-1-thread-1,5,main] 
Thread[pool-1-thread-3,5,main] 
Thread[pool-1-thread-4,5,main] 
Thread[pool-1-thread-5,5,main] 

 notify() 
Thread[pool-1-thread-2,5,main] 

 notifyAll()
Thread[pool-1-thread-2,5,main] 
Thread[pool-1-thread-5,5,main] 
Thread[pool-1-thread-4,5,main] 
Thread[pool-1-thread-3,5,main] 
Thread[pool-1-thread-1,5,main] 

 notify() 
Thread[pool-1-thread-2,5,main] 

 notifyAll()
Thread[pool-1-thread-2,5,main] 
Thread[pool-1-thread-1,5,main] 
Thread[pool-1-thread-3,5,main] 
Thread[pool-1-thread-4,5,main] 
Thread[pool-1-thread-5,5,main] 

 Timer canceled
Task.blocker.prodAll()
Thread[pool-1-thread-6,5,main] 

shutting down
*/
