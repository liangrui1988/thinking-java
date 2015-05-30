package org.rui.thread.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * �ս�����
 * 
 * װ���Ի�԰
 * 
 * @author lenovo
 * 
 */
class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) {// ������ʱ��
			Thread.yield();// ��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
		}
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

// //
class Entrance implements Runnable {
	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;
	// doesn 't need synchronization to read:
	private final int id;
	private static volatile boolean canceled = false;

	// ȡ��
	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		// keep this task in a list . also prevents ����������б��С�Ҳ���Է�ֹ
		// garbage collection of dead tasks:�����ռ���������:
		entrances.add(this);
	}

	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + "total:" + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("stopping:" + this);
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Entrance " + id + ":" + getValue() + "  ";
	}

	public static int getTotalCount() {
		return count.value();
	}

	// �ܺ�
	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}

}

// //////////////////////////////////
public class OrnanetalGarden {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Entrance(i));
		// run for a while then stop and collect the data:
		// ����һ��ʱ��Ȼ��ֹͣ���ռ�����:
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();// ����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances:" + Entrance.sumEntrances());
	}

}
/**
 * output: Entrance 2:1 total:2 Entrance 0:1 total:1 Entrance 1:1 total:3
 * Entrance 4:1 total:5 Entrance 3:1 total:4 Entrance 4:2 total:6 Entrance 2:2
 * total:7 ..... Entrance 0:28 total:136 Entrance 1:28 total:140 Entrance 3:28
 * total:139 Entrance 2:28 total:138 Entrance 4:28 total:137 stopping:Entrance
 * 4:28 stopping:Entrance 2:28 stopping:Entrance 3:28 stopping:Entrance 1:28
 * stopping:Entrance 0:28 Total: 140 Sum of Entrances:140
 */
