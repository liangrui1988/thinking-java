package org.rui.thread.block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Mutex  互斥      Reentrant :可重入
class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		// Acquire it reght away, to demonstrate interruption 获取它心中,来演示中断
		// of a task blocked on a ReentrantLock reentrantLock的任务了
		lock.lock();
	}

	public void f() {
		try {
			// this will nerer be available to a second task 这将纵然是可用的第二个任务
			lock.lockInterruptibly();// 如果当前线程未被中断，则获取锁     special call 
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			System.out.println("interrupted from lock acuisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	@Override
	public void run() {
		System.out.println("Waiting for f()  in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");//爆发的阻塞调用

	}

}

public class Interruptiing2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		//t.interrupt();//中断线程
	}
}
/**
 * output:
Waiting for f()  in BlockedMutex
Issuing t.interrupt()
interrupted from lock acuisition in f()
Broken out of blocked call
 */
