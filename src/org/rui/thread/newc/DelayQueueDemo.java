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
 * ���ڶ��� ���ȼ����е�һ�ֱ���
 * 
 * @author lenovo
 * 
 */

class DelayedTask implements Runnable, Delayed
{

	private static int counter = 0;
	private final int id = counter++;// id ��0++
	private final int delta;// ���ٺ���ֹͣ
	private final long trigger;// ����ֹͣ

	// ���������񱻴�����˳��
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
		// 1��id��Ӧһ������ �ᰲ˳����ִ������
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public String summary()
	{
		return "(" + id + ":" + delta + ")";
	}

	// ���򴥷�
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
	 * ��֪�ӳٵ����ж೤ʱ�� �������ӳ��ڶ೤ʱ��֮ǰ�Ѿ�����
	 */
	@Override
	public long getDelay(TimeUnit unit)
	{
		// ������ʱ�� -����ʱ�� =ִ��ʱ��
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	// �ر����������;�� ��������ȥ �ǽ������Ϊ���е����һ��Ԫ�ء�
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

// ������һ������
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
				q.take().run();// run task with the current thread �뵱ǰ�߳����е�����
			}
		} catch (InterruptedException e)
		{
			System.out.println("�ɽ��ܵķ�ʽ�˳�=");
			// �ɽ��ܵķ�ʽ�˳�
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
		// fill with tasks that have random delays:��������ӳٵ�����
		for (int i = 0; i < 20; i++)
		{
			queue.put(new DelayedTask(rand.nextInt(5000)));
		}
		// ����ֹͣ��
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