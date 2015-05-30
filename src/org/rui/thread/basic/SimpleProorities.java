package org.rui.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �߳����ȼ�
 * 
 * @author lenovo
 * 
 */
public class SimpleProorities implements Runnable {

	private int countDown = 5;
	private volatile double d; // No optimization
	private int priority;

	public SimpleProorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return Thread.currentThread() + " : " + countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);// ��ͷ�������ȼ�
		while (true) {
			for (int i = 1; i < 100000; i++) {
				// ���κ�����ֵ�����ӽ� pi����Բ���ܳ���ֱ��֮�ȣ��� double ֵ��
				// �κ�����ֵ�����ӽ� e������Ȼ�����ĵ������� double ֵ��
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}

			System.out.println(this);
			if (--countDown == 0)
				return;
		}

	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new SimpleProorities(Thread.MIN_PRIORITY));
		exec.execute(new SimpleProorities(Thread.MAX_PRIORITY));// ���ȼ���

		exec.shutdown();

	}

}
