package org.rui.thread.block2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ʹ���@ʾ��lock ��conditon�� 
 * ֻ���ڸ������y�Ķྀ�̆��}�в��Ǳ��ʹ�õ�
 * 
 * @author lenovo
 * 
 */
// wax�� �����Զ���ʽ
public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();// �ж���������
		// shutdownNow ��ͼֹͣ��������ִ�еĻ������ͣ�������ڵȴ������񣬲����صȴ�ִ�е������б�
	}
}

class Car {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();//�����΄յ�ͨ�ţ��������κ����P̎���B��Ϣ
	// ��ʾ �׹� �������Ĵ���״̬
	private boolean waxOn = false;

	// ����
	public/* synchronized */void waxed() {
		lock.lock();
		try {
			waxOn = true;// ready to buff
			// notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	// �׹�
	public/* synchronized */void buffed() {

		lock.lock();
		try {
			waxOn = false;// ready to another coat of wax
			// notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	// wait ����
	public/* synchronized */void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == false) {
				// wait();// �����������
				condition.await();
			}
		} finally {
			lock.unlock();
		}

	}

	// wait �׹�
	public /*synchronized*/ void waitForBuffing() throws InterruptedException {

		lock.lock();
		try {
			while (waxOn == true) {
				// wait();// �����������
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();// ����
				car.waitForBuffing();// �� �׹�
			}
		} catch (InterruptedException e) {
			System.out.println("ͨ���ж��˳�");
			// e.printStackTrace();
		}
		System.out.println("ending Wax on task");
	}

}

// /////////////////////

class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();// �� ����
				System.out.println("wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();// �׹�

			}
		} catch (InterruptedException e) {
			System.out.println("ͨ���ж��˳�");
			// e.printStackTrace();
		}
		System.out.println("ending Wax Off task");
	}

}

/*
 * output:(95% match) 
wax off!
wax on!
...
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
ͨ���ж��˳�
ͨ���ж��˳�
ending Wax Off task
ending Wax on task

 */
