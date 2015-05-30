package org.rui.thread.newc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit.*;

import javax.xml.namespace.QName;

/**
 * 延期队列 优先级队列的一种变体
 * 
 * @author lenovo
 * 
 */

class DelayedTask implements Runnable, Delayed {

	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;

	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime()
				+ TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);

		sequence.add(this);
	}

	@Override
	public void run() {
		System.out.println(this + " ");
	}

	public String toString() {
		return String.format("[%1$-4d]", delta);
	}

	public String summary() {
		return "(" + id + ":" + delta + ")";
	}

	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask) o;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;

		public EndSentinel(int delayInMilliseconds, ExecutorService c) {
			super(delayInMilliseconds);
			exec = c;
		}

		@Override
		public void run() {
			for (DelayedTask pt : sequence) {
				System.out.print(pt.summary() + " ");
			}
			System.out.println();
			System.out.println(this + "calling shutdownNow");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		this.q = q;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();// run task whit the current thread
			}
		} catch (InterruptedException e) {
		}
		System.out.println("finished delayedTaskConsumer");
	}
}

public class DelayQueueDemo {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		// fill with tasks that have random delays:充满随机延迟的任务
		for (int i = 0; i < 20; i++) {
			queue.put(new DelayedTask(rand.nextInt(5000)));
		}
		//设置停止点
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));

	}
}