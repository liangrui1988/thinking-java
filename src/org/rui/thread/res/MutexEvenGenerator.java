package org.rui.thread.res;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示的Lock对象
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
			Thread.yield();// 导致失败的更快 暂停当前正在执行的线程对象，并执行其他线程。
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
