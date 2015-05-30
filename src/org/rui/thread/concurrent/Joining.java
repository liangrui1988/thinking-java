package org.rui.thread.concurrent;

/**
 * ����һ���߳� һ���߳̿����������߳�֮�ϵ���join������������Ч���ǵȴ�һ��ʱ��ֱ���ڶ����߳̽����ż���ִ�С�
 * ���ĳ���߳�����һ���߳�T�ϵ���t.join() ���߳̽�������ֱ��Ŀ���߳�t�����Żָ����� t.isAlive()����Ϊ�٣�
 * 
 * @author lenovo
 * 
 */
class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	/**
	 * Sleeper ��Ҫ����һ��ʱ��
	 */
	@Override
	public void run() {
		try {
			sleep(duration);
		} catch (Exception e) {
			// ���� isInterrupted�ķ���ֵ��������жϣ�����һ���߳��ڸ��߳��ϵ���interrupt()ʱ,
			// �������߳��趨һ����־���������߳��Ѿ��жϣ�Ȼ�����쳣������ʱ�����������־��������catch�Ӿ��У�
			// ���쳣�������ʱ�������־����Ϊ�١����쳣֮�⣬�����־����������������������߳̿��ܻ������ж�״̬
			System.out.println(getName() + " was interrupted."
					+ "isInterrupted() " + isInterrupted());
			return;
		}
		System.out.println(getName() + "  has awakened");
	}

}

// ////////////////////////////////
class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run() {
		/**
		 * Joiner �߳̽�ͨ����sleeper�����ϵ���join�������ȴ�sleeper����.��main����
		 * ÿ��sleeper����һ��joiner,�����������з��֣����sleeper�� �жϻ���������������
		 * joiner����sleeperһͬ����
		 * 
		 */
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("interrupted");
		}
		System.out.println(getName() + " join completed");
	}

}

// ////////////////////////////////
public class Joining {
	public static void main(String[] args) {
		// �߳�1
		Sleeper sleepy = new Sleeper("Sleepy", 1500), grumpy = new Sleeper(
				"Grumpy", 1500);

		//
		Joiner Dopey = new Joiner("Dopey", sleepy), doc = new Joiner("doc",
				grumpy);

		grumpy.interrupt();
	}
}

/**
 * ע��,java SE5��java.util.concurrent����������CyclicBarrier�����Ĺ��ߡ�
 * ���ǿ��ܱ�������߳�����е�join���Ӻ��� output: Grumpywas interrupted.isInterrupted() false
 * doc join completed Sleepy has awakened Dopey join completed
 */

