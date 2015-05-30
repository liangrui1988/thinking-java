package org.rui.thread.res;

/**
 * ͬ������ EvenGenerator
 * 
 * @author lenovo
 * 
 */
public class SynchronizedEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();// ����ʧ�ܵĸ��� ��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
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
