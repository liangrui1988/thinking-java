package org.rui.thread.volatiles;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ԭ���࣬ java SE5����������AtomicInteger AtomicLong AtomicReference
 * �������ԭ���Ա����࣬�����ṩ������ʽ��ԭ�����������²�����
 * 
 * boolean compareAndSet(expectedValue,updateValue);
 * 
 * ��Щ�౻����Ϊ����ʹ����ĳЩ�ִ��������ϵĿɻ�õģ��������ڻ��������ϵ�ԭ���ԣ�
 * �����ʹ������ʱ��ͨ������Ҫ���ġ����ڳ�������˵�����Ǻ��ٻ������ó����������漶���ܵ���ʱ��
 * ���Ǿʹ�������֮���ˣ����磬���ǿ���ʹ��AtomicInteger����дAtomictyTest.java
 * 
 * @author lenovo
 * 
 */
public class AtomicIntegerTest implements Runnable {
	/**
	 * ��������ͨ��ʹ��AtomicInteger��������synchronized�ؼ��֡�
	 * ��Ϊ������򲻻�ʧ�ܣ����������һ��timer,�Ա���5����֮���Զ�����ֹ
	 */
	private AtomicInteger i = new AtomicInteger(0);

	public int getValue() {
		return i.get();
	}

	private void evenIncrement() {
		i.addAndGet(2);
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		// ��ʱ��
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		// �̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest at = new AtomicIntegerTest();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}

	}
}
/**
 * output: Aborting
 */
