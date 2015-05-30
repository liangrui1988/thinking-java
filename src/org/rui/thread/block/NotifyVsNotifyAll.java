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
		// ����ָ����������ָ�����ӳٺ�ʼ�����ظ��Ĺ̶�����ִ�С�
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
		timer.cancel();// ��ֹ�˼�ʱ����
		System.out.println("\n Timer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task.blocker.prodAll()");
		Task2.blocker.prodAll();//����task2 �������κ���Task.blocker�е����ϵȴ�������
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
		notify();//���ڶ�Ⱥ�ͬһ�����������У�ֻ��һ���ᱻ���ѣ���������ϣ��ʹ��nofify ���ͱ��뱣֤�����ѵ���ǡ��������
	}

	synchronized void prodAll() {
		notifyAll();//ֻ�еȴ������������Żᱻ����
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
	// a separate blocker object һ������������������
	static Blocker blocker = new Blocker();

	public void run() {
		blocker.waitingCall();
		//System.out.println(Thread.currentThread()+" : Task ���ᱻ����Task2");
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
