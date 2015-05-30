package org.rui.thread.res;

/**
 * 同步控制 EvenGenerator
 * 
 * @author lenovo
 * 
 */
public class SynchronizedEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();// 导致失败的更快 暂停当前正在执行的线程对象，并执行其他线程。
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
/**
 * output: press control-c to exit
 */
