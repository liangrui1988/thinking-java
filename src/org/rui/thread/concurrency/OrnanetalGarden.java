package org.rui.thread.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 终结任务
 * 
 * 装饰性花园
 * 
 * @author lenovo
 * 
 */
class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) {// 收益率时间
			Thread.yield();// 暂停当前正在执行的线程对象，并执行其他线程。
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

	// 取消
	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		// keep this task in a list . also prevents 把这个任务列表中。也可以防止
		// garbage collection of dead tasks:垃圾收集的死任务:
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

	// 总和
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
		// 运行一段时间然后停止并收集数据:
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
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
