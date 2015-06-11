package org.rui.thread.newc;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/***
 * ����һ��������ѡ�����У������п������Ķ�ȡ������
 * ������һ��ʾ�����������ż������еĶ����ǰ����ż���˳��Ӷ����г��ֵ�����
 * prioritizedTask��������һ�����ȼ����֣� �Դ����ṩ����˳��
 * @author lenovo
 *
 */

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>
{
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();

	public PrioritizedTask(int priority)
	{
		this.priority = priority;
		sequence.add(this);

	}

	@Override
	public int compareTo(PrioritizedTask o)
	{
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);

	}

	@Override
	public void run()
	{
		try
		{
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e)
		{
			// Acceptable way to exit �ɽ��ܵķ�ʽ�˳�

		}
		System.out.println("PrioritizedTask run: " + this);
	}

	@Override
	public String toString()
	{
		return String.format("[%1$-3d]", priority) + "  =Task >>id: " + id;

	}

	public String summary()
	{
		return "(id:" + id + "  priority:" + priority + ")";
	}

	// //
	public static class endSentinel extends PrioritizedTask
	{
		private ExecutorService exec;

		public endSentinel(ExecutorService e)
		{
			super(-1);// lowest priority in this program ������ȼ�
			exec = e;
		}

		@Override
		public void run()
		{
			int count = 0;
			for (PrioritizedTask pt : sequence)
			{
				System.out.println("summary����: "+pt.summary());
				if (++count % 5 == 0)
				{
					System.out.println();
				}
			}
			System.out.println();
			System.out.println(this + " calling shutdownNow");
			exec.shutdownNow();
		}
	}
}

// //////////////������////////
class prioritizedTaskProducer implements Runnable
{
	private Random rand = new Random(47);
	private Queue<Runnable> queue;

	private ExecutorService exec;

	public prioritizedTaskProducer(Queue<Runnable> q, ExecutorService e)
	{
		queue = q;
		exec = e;// used for end sentinel ���ڽ����ڱ�

	}

	@Override
	public void run()
	{
		// unbounded queue ; never blocks �޽����;�Ӳ�����
		// fill it up fast with random priorities ������ƽ������������ȼ�
		for (int i = 0; i < 20; i++)
		{
			int temp=0;
			System.out.println("�����>>���20>>10>inner:"+(temp=rand.nextInt(10)));
			queue.add(new PrioritizedTask(temp));
			Thread.yield();// �õ�ǰ�̻߳ص���ִ��״̬���Ա��þ�����ͬ���ȼ����߳̽���ִ��״̬�������Ǿ��Եġ���Ϊ��������ܻ��ø��߳����½���ִ��״̬��
		}

		// trickle in highest-priority jobs ϸ����������ȼ��Ĺ���
		try
		{

			//����10 ��10
			for (int i = 0; i < 10; i++)
			{
				System.out.println("�����>>ָ��10>>10��:"+10);
				TimeUnit.MILLISECONDS.sleep(2500);
				queue.add(new PrioritizedTask(10));
			}

			for (int i = 0; i < 10; i++)
			{
				System.out.println("�����>>����>>10>0-10:"+i);
				
				// add jobs lowest priority first: ��ӹ��� ������ȼ�:
				queue.add(new PrioritizedTask(i));
			}

			// a sentinel to stop all the tasks �ڱ�ֹͣ���е�����
			queue.add(new PrioritizedTask.endSentinel(exec));
		} catch (InterruptedException e)
		{
			// �ɽ��ܵķ�ʽ�˳�
			System.out.println("InterruptedException >>143");
		}
		System.out.println("���  prioritizedTaskProducer");
	}

}

// //������////PriorityBlockingQueue/////////
class prioritizedTaskConsumer implements Runnable
{
	private PriorityBlockingQueue<Runnable> q;

	public prioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q)
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
				// use current thread to run the task ʹ�õ�ǰ�߳����е�����
				q.take().run();

			}
		} catch (InterruptedException e)
		{
			// acceptable way to exit
		}

		System.out.println("��� prioritizedTaskConsumer");

	}
}

/***
 * main
 * @author lenovo
 *
 */
public class PriorityBlockingQueueDemo
{

	public static void main(String[] args) throws InterruptedException
	{
		Random randx = new Random(47);
		int c = randx.nextInt(250);
		System.out.println(c);

		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
		exec.execute(new prioritizedTaskProducer(queue, exec));// ������
	
   //	Thread.sleep(8000);
		exec.execute(new prioritizedTaskConsumer(queue));// ������
	}

}

/**
output:

sle---
�����>>���20>>10>inner:8
�����>>���20>>10>inner:5
�����>>���20>>10>inner:3
�����>>���20>>10>inner:1
�����>>���20>>10>inner:1
�����>>���20>>10>inner:9
�����>>���20>>10>inner:8
�����>>���20>>10>inner:0
�����>>���20>>10>inner:2
�����>>���20>>10>inner:7
�����>>���20>>10>inner:8
�����>>���20>>10>inner:8
�����>>���20>>10>inner:1
�����>>���20>>10>inner:9
�����>>���20>>10>inner:9
�����>>���20>>10>inner:8
�����>>���20>>10>inner:8
�����>>���20>>10>inner:1
�����>>���20>>10>inner:0
�����>>���20>>10>inner:8
�����>>ָ��10>>10��:10
�����>>ָ��10>>10��:10
�����>>ָ��10>>10��:10
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 20
PrioritizedTask run: [10 ]  =Task >>id: 21
PrioritizedTask run: [10 ]  =Task >>id: 22
PrioritizedTask run: [9  ]  =Task >>id: 5
PrioritizedTask run: [9  ]  =Task >>id: 13
PrioritizedTask run: [9  ]  =Task >>id: 14
PrioritizedTask run: [8  ]  =Task >>id: 15
PrioritizedTask run: [8  ]  =Task >>id: 16
PrioritizedTask run: [8  ]  =Task >>id: 19
PrioritizedTask run: [8  ]  =Task >>id: 0
PrioritizedTask run: [8  ]  =Task >>id: 10
PrioritizedTask run: [8  ]  =Task >>id: 11
PrioritizedTask run: [8  ]  =Task >>id: 6
PrioritizedTask run: [7  ]  =Task >>id: 9
PrioritizedTask run: [5  ]  =Task >>id: 1
PrioritizedTask run: [3  ]  =Task >>id: 2
PrioritizedTask run: [2  ]  =Task >>id: 8
PrioritizedTask run: [1  ]  =Task >>id: 12
PrioritizedTask run: [1  ]  =Task >>id: 17
PrioritizedTask run: [1  ]  =Task >>id: 4
PrioritizedTask run: [1  ]  =Task >>id: 3
PrioritizedTask run: [0  ]  =Task >>id: 7
PrioritizedTask run: [0  ]  =Task >>id: 18
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 23
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 24
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 25
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 26
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 27
�����>>ָ��10>>10��:10
PrioritizedTask run: [10 ]  =Task >>id: 28
�����>>����>>10>0-10:0
�����>>����>>10>0-10:1
�����>>����>>10>0-10:2
�����>>����>>10>0-10:3
�����>>����>>10>0-10:4
�����>>����>>10>0-10:5
�����>>����>>10>0-10:6
�����>>����>>10>0-10:7
�����>>����>>10>0-10:8
�����>>����>>10>0-10:9
���  prioritizedTaskProducer
PrioritizedTask run: [10 ]  =Task >>id: 29
PrioritizedTask run: [9  ]  =Task >>id: 39
PrioritizedTask run: [8  ]  =Task >>id: 38
PrioritizedTask run: [7  ]  =Task >>id: 37
PrioritizedTask run: [6  ]  =Task >>id: 36
PrioritizedTask run: [5  ]  =Task >>id: 35
PrioritizedTask run: [4  ]  =Task >>id: 34
PrioritizedTask run: [3  ]  =Task >>id: 33
PrioritizedTask run: [2  ]  =Task >>id: 32
PrioritizedTask run: [1  ]  =Task >>id: 31
PrioritizedTask run: [0  ]  =Task >>id: 30
summary����: (id:0  priority:8)
summary����: (id:1  priority:5)
summary����: (id:2  priority:3)
summary����: (id:3  priority:1)
summary����: (id:4  priority:1)

summary����: (id:5  priority:9)
summary����: (id:6  priority:8)
summary����: (id:7  priority:0)
summary����: (id:8  priority:2)
summary����: (id:9  priority:7)

summary����: (id:10  priority:8)
summary����: (id:11  priority:8)
summary����: (id:12  priority:1)
summary����: (id:13  priority:9)
summary����: (id:14  priority:9)

summary����: (id:15  priority:8)
summary����: (id:16  priority:8)
summary����: (id:17  priority:1)
summary����: (id:18  priority:0)
summary����: (id:19  priority:8)

summary����: (id:20  priority:10)
summary����: (id:21  priority:10)
summary����: (id:22  priority:10)
summary����: (id:23  priority:10)
summary����: (id:24  priority:10)

summary����: (id:25  priority:10)
summary����: (id:26  priority:10)
summary����: (id:27  priority:10)
summary����: (id:28  priority:10)
summary����: (id:29  priority:10)

summary����: (id:30  priority:0)
summary����: (id:31  priority:1)
summary����: (id:32  priority:2)
summary����: (id:33  priority:3)
summary����: (id:34  priority:4)

summary����: (id:35  priority:5)
summary����: (id:36  priority:6)
summary����: (id:37  priority:7)
summary����: (id:38  priority:8)
summary����: (id:39  priority:9)

summary����: (id:40  priority:-1)

[-1 ]  =Task >>id: 40 calling shutdownNow
��� prioritizedTaskConsumer
*/

/**
*��ǰһ��ʾ����ͬ��prioritizedTask����Ĵ������б���¼��sequence list�У����ں�ʵ�ʵ�ִ��˳��Ƚϡ�
*run()����������һС�������ʱ�� ��Ȼ���ӡ������Ϣ����EndSentinel�ṩ�˺�ǰ����ͬ�Ĺ��ܣ�
*Ҫȷ�����Ƕ��������һ������
*
*PrioritizedTaskProducer��PrioritizedTaskComsumerͨ��PriorityBlockingQueue�˴����ӡ�
*��Ϊ���ֶ��е����������ṩ�����б����ͬ����������Ӧ��ע�⵽�ˣ����ﲻ��Ҫ�κ���ʽ��ͬ��--���ؿ���������ֶ����ж�ȡʱ
*�������Ƿ������أ���Ϊ���������û��Ԫ��ʱ����ֱ��������ȡ�ߡ�
*
*
*/