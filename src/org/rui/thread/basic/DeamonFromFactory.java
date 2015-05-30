package org.rui.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ������ͨ��ThreadFactory��Ψһ�������������̨״̬ȫ������Ϊ��true
 * �����ڿ�����һ���µ�DaemonThreadFactory��Ϊ�������ݸ�executory.newCachedThreadPool()
 * 
 * @author lenovo
 * 
 */
public class DeamonFromFactory implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " >> " + this);
			}
		} catch (Exception e) {

		}

	}

	// ///
	public static void main(String[] args) throws InterruptedException {
		// ÿһ��ExecutorService����������������Ϊ����һ��threadfactory����
		// ��������󽫱����������µ��߳�
		ExecutorService exec = Executors
				.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute(new DeamonFromFactory());
		}

		System.out.println("all daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
	}

}

/**
 * output: all daemons started Thread[Thread-0,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@8ab708 Thread[Thread-3,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@9934d4 Thread[Thread-5,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@f6ac0b Thread[Thread-4,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@1c0f2e5 Thread[Thread-2,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@13c7378 Thread[Thread-1,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@54c4ad Thread[Thread-6,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@5b8827 Thread[Thread-7,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@d09ad3 Thread[Thread-8,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@147c1db Thread[Thread-9,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@82d37 Thread[Thread-0,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@8ab708 Thread[Thread-5,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@f6ac0b Thread[Thread-3,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@9934d4 Thread[Thread-1,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@54c4ad Thread[Thread-4,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@1c0f2e5 Thread[Thread-2,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@13c7378 Thread[Thread-6,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@5b8827 Thread[Thread-8,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@147c1db Thread[Thread-7,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@d09ad3 Thread[Thread-9,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@82d37 Thread[Thread-5,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@f6ac0b Thread[Thread-1,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@54c4ad Thread[Thread-0,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@8ab708 Thread[Thread-3,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@9934d4 Thread[Thread-7,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@d09ad3 Thread[Thread-4,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@1c0f2e5 Thread[Thread-2,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@13c7378 Thread[Thread-6,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@5b8827 Thread[Thread-8,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@147c1db Thread[Thread-9,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@82d37 Thread[Thread-1,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@54c4ad Thread[Thread-5,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@f6ac0b Thread[Thread-3,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@9934d4 Thread[Thread-0,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@8ab708 Thread[Thread-8,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@147c1db Thread[Thread-6,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@5b8827 Thread[Thread-2,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@13c7378 Thread[Thread-4,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@1c0f2e5 Thread[Thread-7,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@d09ad3 Thread[Thread-9,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@82d37 Thread[Thread-0,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@8ab708 Thread[Thread-3,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@9934d4 Thread[Thread-1,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@54c4ad Thread[Thread-5,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@f6ac0b Thread[Thread-8,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@147c1db Thread[Thread-6,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@5b8827 Thread[Thread-2,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@13c7378 Thread[Thread-4,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@1c0f2e5 Thread[Thread-7,5,main] >>
 * org.rui.thread.basic.DeamonFromFactory@d09ad3
 */
