package org.rui.thread.res;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ʹ����ʾ��Lock����
 * 
 * @author lenovo
 * 
 */
public class MutexEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	Lock lock = new ReentrantLock();

	@Override
	public int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();// ����ʧ�ܵĸ��� ��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
			++currentEvenValue;
			return currentEvenValue;
		} finally {
			lock.unlock();
		}
	}

	// ////////////////////////////
	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());

	}

}

/**
 * output: press control-c to exit
 */
