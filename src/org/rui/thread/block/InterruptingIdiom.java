package org.rui.thread.block;

import java.util.concurrent.TimeUnit;
/**
 * ����ж�
 * @author lenovo
 *
 */
class NeedsCleanup {//��Ҫ���
	private final int id;

	public NeedsCleanup(int ident) {
		id = ident;
		System.out.println("NeedsCleanup " + id);
	}

	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				// start try-finally immediately after definition
				// of n1 , to auarantee proper cleanup of n1
				try {
					System.out.println("sleeping");
					TimeUnit.SECONDS.sleep(1);
					// point 2
					NeedsCleanup n2 = new NeedsCleanup(2);
					// guarantee proper cleanup of n2 ��֤�ʵ�������n2
					try {
						System.out.println("���㵥Ԫ");
						// A time-consuming,non-blocking operation:  ��ʱ,����������
						for (int i = 1; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("��ɺ�ʱ�Ĳ���");
					} finally {
						n2.cleanup();
					}

				} finally {
					n1.cleanup();
					//throw new InterruptedException();
				}
			}
			System.out.println("exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("exiting via inerruptedExecption");
		}
	}
}

// /////////////////////////////////////

public class InterruptingIdiom {

	public static void main(String[] args) throws Exception {
		String[] arg = { "1100" };
		if (arg.length != 1) {
			System.exit(1);
		}
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(arg[0]));
		t.interrupt();
	}
}
/**
output:

NeedsCleanup 1
sleeping
NeedsCleanup 2
���㵥Ԫ
��ɺ�ʱ�Ĳ���
Cleaning up 2
Cleaning up 1
NeedsCleanup 1
sleeping
Cleaning up 1
exiting via inerruptedExecption
*/