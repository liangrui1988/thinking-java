package org.rui.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * ����ͨ������isDaemon������ȷ���߳��Ƿ���һ����̨�̡߳������һ����̨�̣߳� ��ô�������κ��߳̽����Զ����óɺ�̨�̣߳���������ʾ
 * 
 * @author lenovo
 * 
 */
class Daemon implements Runnable {

	private Thread[] t = new Thread[10];

	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("daemonSpawn " + i + " started, ");
		}
		for (int i = 0; i < t.length; i++) {
			System.out.println("t[" + i + "].isDaemon()=" + t[i].isDaemon()
					+ " ,");
		}
		/**
		 * ���̵߳����� java�̻߳��Ƶ�һ���֣����Խ�cpu��һ���߳�ת�Ƹ���һ���߳� ��һ�ֽ��� ����������
		 * ���Ѿ�ִ������������������Ҫ�Ĳ����ˣ��˿������л�����������ִ��һ��ʱ��Ĵ��ʱ�� ��ȫ�������Եģ�
		 * 
		 */
		while (true)
			Thread.yield();// �ѿ���Ȩ���������߳�

	}

}

class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true)
			Thread.yield();
	}
}

// //////////////////////////////////
public class Daemons {
	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.out.println("d.isDaemon()=" + d.isDaemon() + "  , ");
		TimeUnit.MILLISECONDS.sleep(1);
	}

}
/**
 * d.isDaemon()=true , daemonSpawn 0 started, daemonSpawn 1 started, daemonSpawn
 * 2 started, daemonSpawn 3 started, daemonSpawn 4 started, daemonSpawn 5
 * started, daemonSpawn 6 started, daemonSpawn 7 started, daemonSpawn 8 started,
 * daemonSpawn 9 started, t[0].isDaemon()=true , t[1].isDaemon()=true ,
 * t[2].isDaemon()=true , t[3].isDaemon()=true , t[4].isDaemon()=true ,
 * t[5].isDaemon()=true , t[6].isDaemon()=true , t[7].isDaemon()=true ,
 * t[8].isDaemon()=true , t[9].isDaemon()=true ,
 */
