package org.rui.thread.newc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 延期队列 优先级队列的一种变体
 * 
 * @author lenovo
 * 
 */

class DelayedTask implements Runnable, Delayed
{

	private static int counter = 0;
	private final int id = counter++;// id 从0++
	private final int delta;// 多少毫秒停止
	private final long trigger;// 纳秒停止

	// 保存了任务被创建的顺序
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMilliseconds)
	{
		delta = delayInMilliseconds;
		trigger = System.nanoTime()
				+ TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);

		sequence.add(this);
	}

	@Override
	public void run()
	{
		System.out.println(this + " run");
	}

	public String toString()
	{
		// 1个id对应一项任务 会安顺序来执行任务
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public String summary()
	{
		return "(" + id + ":" + delta + ")";
	}

	// 排序触发
	@Override
	public int compareTo(Delayed o)
	{
		DelayedTask that = (DelayedTask) o;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	/**
	 * 告知延迟到期有多长时间 ，或者延迟在多长时间之前已经到期
	 */
	@Override
	public long getDelay(TimeUnit unit)
	{
		// 对象到期时间 -现在时间 =执行时间
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	// 关闭所有事物的途径 具体做剥去 是将其放置为队列的最后一个元素。
	public static class EndSentinel extends DelayedTask
	{

		private ExecutorService exec;

		public EndSentinel(int delayInMilliseconds, ExecutorService c)
		{
			super(delayInMilliseconds);
			exec = c;
		}

		@Override
		public void run()
		{
			for (DelayedTask pt : sequence)
			{
				System.out.print(pt.summary() + " ");
			}
			System.out.println();
			System.out.println(this + "  calling shutdownNow");
			exec.shutdownNow();
		}
	}
}

// 自身是一个任务
class DelayedTaskConsumer implements Runnable
{
	private DelayQueue<DelayedTask> q;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> q)
	{
		this.q = q;
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				q.take().run();// run task with the current thread 与当前线程运行的任务
			}
		} catch (InterruptedException e)
		{
			System.out.println("可接受的方式退出=");
			// 可接受的方式退出
		}
		System.out.println("finished delayedTaskConsumer");
	}
}

public class DelayQueueDemo
{
	public static void main(String[] args)
	{
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		// fill with tasks that have random delays:充满随机延迟的任务
		for (int i = 0; i < 20; i++)
		{
			queue.put(new DelayedTask(rand.nextInt(5000)));
		}
		// 设置停止点
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));

	}
}
/** output:
[128 ] Task 11 run
[200 ] Task 7 run
[429 ] Task 5 run
[520 ] Task 18 run
[555 ] Task 1 run
[961 ] Task 4 run
[998 ] Task 16 run
[1207] Task 9 run
[1693] Task 2 run
[1809] Task 14 run
[1861] Task 3 run
[2278] Task 15 run
[3288] Task 10 run
[3551] Task 12 run
[4258] Task 0 run
[4258] Task 19 run
[4522] Task 8 run
[4589] Task 13 run
[4861] Task 17 run
[4868] Task 6 run
(0:4258) (1:555) (2:1693) (3:1861) (4:961) (5:429) (6:4868) (7:200) (8:4522) (9:1207) (10:3288) (11:128) (12:3551) (13:4589) (14:1809) (15:2278) (16:998) (17:4861) (18:520) (19:4258) (20:5000) 
[5000] Task 20  calling shutdownNow
finished delayedTaskConsumer
*/