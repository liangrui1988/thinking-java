package org.rui.thread.block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Mutex  ����      Reentrant :������
class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		// Acquire it reght away, to demonstrate interruption ��ȡ������,����ʾ�ж�
		// of a task blocked on a ReentrantLock reentrantLock��������
		lock.lock();
	}

	public void f() {
		try {
			// this will nerer be available to a second task �⽫��Ȼ�ǿ��õĵڶ�������
			lock.lockInterruptibly();// �����ǰ�߳�δ���жϣ����ȡ��     special call 
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
		System.out.println("Broken out of blocked call");//��������������

	}

}

public class Interruptiing2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		//t.interrupt();//�ж��߳�
	}
}
/**
 * output:
Waiting for f()  in BlockedMutex
Issuing t.interrupt()
interrupted from lock acuisition in f()
Broken out of blocked call
 */
