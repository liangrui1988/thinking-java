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
 * 这是一个基于优选级队列，它具有可阻塞的读取操作。
 * 下面是一个示例，其中在优级级队列的对象是按照优级级顺序从队列中出现的任务。
 * prioritizedTask被赋予了一个优先级数字， 以此来提供这种顺序。
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
			// Acceptable way to exit 可接受的方式退出

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
			super(-1);// lowest priority in this program 最低优先级
			exec = e;
		}

		@Override
		public void run()
		{
			int count = 0;
			for (PrioritizedTask pt : sequence)
			{
				System.out.println("summary汇总: "+pt.summary());
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

// //////////////生产者////////
class prioritizedTaskProducer implements Runnable
{
	private Random rand = new Random(47);
	private Queue<Runnable> queue;

	private ExecutorService exec;

	public prioritizedTaskProducer(Queue<Runnable> q, ExecutorService e)
	{
		queue = q;
		exec = e;// used for end sentinel 用于结束哨兵

	}

	@Override
	public void run()
	{
		// unbounded queue ; never blocks 无界队列;从不阻塞
		// fill it up fast with random priorities 把它填平快速随机的优先级
		for (int i = 0; i < 20; i++)
		{
			int temp=0;
			System.out.println("入队列>>随机20>>10>inner:"+(temp=rand.nextInt(10)));
			queue.add(new PrioritizedTask(temp));
			Thread.yield();// 让当前线程回到可执行状态，以便让具有相同优先级的线程进入执行状态，但不是绝对的。因为虚拟机可能会让该线程重新进入执行状态。
		}

		// trickle in highest-priority jobs 细流在最高优先级的工作
		try
		{

			//放入10 个10
			for (int i = 0; i < 10; i++)
			{
				System.out.println("入队列>>指定10>>10个:"+10);
				TimeUnit.MILLISECONDS.sleep(2500);
				queue.add(new PrioritizedTask(10));
			}

			for (int i = 0; i < 10; i++)
			{
				System.out.println("入队列>>自增>>10>0-10:"+i);
				
				// add jobs lowest priority first: 添加工作 最低优先级:
				queue.add(new PrioritizedTask(i));
			}

			// a sentinel to stop all the tasks 哨兵停止所有的任务
			queue.add(new PrioritizedTask.endSentinel(exec));
		} catch (InterruptedException e)
		{
			// 可接受的方式退出
			System.out.println("InterruptedException >>143");
		}
		System.out.println("完成  prioritizedTaskProducer");
	}

}

// //消费者////PriorityBlockingQueue/////////
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
				// use current thread to run the task 使用当前线程运行的任务
				q.take().run();

			}
		} catch (InterruptedException e)
		{
			// acceptable way to exit
		}

		System.out.println("完成 prioritizedTaskConsumer");

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
		exec.execute(new prioritizedTaskProducer(queue, exec));// 生产者
	
   //	Thread.sleep(8000);
		exec.execute(new prioritizedTaskConsumer(queue));// 消费者
	}

}

/**
output:

sle---
入队列>>随机20>>10>inner:8
入队列>>随机20>>10>inner:5
入队列>>随机20>>10>inner:3
入队列>>随机20>>10>inner:1
入队列>>随机20>>10>inner:1
入队列>>随机20>>10>inner:9
入队列>>随机20>>10>inner:8
入队列>>随机20>>10>inner:0
入队列>>随机20>>10>inner:2
入队列>>随机20>>10>inner:7
入队列>>随机20>>10>inner:8
入队列>>随机20>>10>inner:8
入队列>>随机20>>10>inner:1
入队列>>随机20>>10>inner:9
入队列>>随机20>>10>inner:9
入队列>>随机20>>10>inner:8
入队列>>随机20>>10>inner:8
入队列>>随机20>>10>inner:1
入队列>>随机20>>10>inner:0
入队列>>随机20>>10>inner:8
入队列>>指定10>>10个:10
入队列>>指定10>>10个:10
入队列>>指定10>>10个:10
入队列>>指定10>>10个:10
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
入队列>>指定10>>10个:10
PrioritizedTask run: [10 ]  =Task >>id: 23
入队列>>指定10>>10个:10
PrioritizedTask run: [10 ]  =Task >>id: 24
入队列>>指定10>>10个:10
PrioritizedTask run: [10 ]  =Task >>id: 25
入队列>>指定10>>10个:10
PrioritizedTask run: [10 ]  =Task >>id: 26
入队列>>指定10>>10个:10
PrioritizedTask run: [10 ]  =Task >>id: 27
入队列>>指定10>>10个:10
PrioritizedTask run: [10 ]  =Task >>id: 28
入队列>>自增>>10>0-10:0
入队列>>自增>>10>0-10:1
入队列>>自增>>10>0-10:2
入队列>>自增>>10>0-10:3
入队列>>自增>>10>0-10:4
入队列>>自增>>10>0-10:5
入队列>>自增>>10>0-10:6
入队列>>自增>>10>0-10:7
入队列>>自增>>10>0-10:8
入队列>>自增>>10>0-10:9
完成  prioritizedTaskProducer
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
summary汇总: (id:0  priority:8)
summary汇总: (id:1  priority:5)
summary汇总: (id:2  priority:3)
summary汇总: (id:3  priority:1)
summary汇总: (id:4  priority:1)

summary汇总: (id:5  priority:9)
summary汇总: (id:6  priority:8)
summary汇总: (id:7  priority:0)
summary汇总: (id:8  priority:2)
summary汇总: (id:9  priority:7)

summary汇总: (id:10  priority:8)
summary汇总: (id:11  priority:8)
summary汇总: (id:12  priority:1)
summary汇总: (id:13  priority:9)
summary汇总: (id:14  priority:9)

summary汇总: (id:15  priority:8)
summary汇总: (id:16  priority:8)
summary汇总: (id:17  priority:1)
summary汇总: (id:18  priority:0)
summary汇总: (id:19  priority:8)

summary汇总: (id:20  priority:10)
summary汇总: (id:21  priority:10)
summary汇总: (id:22  priority:10)
summary汇总: (id:23  priority:10)
summary汇总: (id:24  priority:10)

summary汇总: (id:25  priority:10)
summary汇总: (id:26  priority:10)
summary汇总: (id:27  priority:10)
summary汇总: (id:28  priority:10)
summary汇总: (id:29  priority:10)

summary汇总: (id:30  priority:0)
summary汇总: (id:31  priority:1)
summary汇总: (id:32  priority:2)
summary汇总: (id:33  priority:3)
summary汇总: (id:34  priority:4)

summary汇总: (id:35  priority:5)
summary汇总: (id:36  priority:6)
summary汇总: (id:37  priority:7)
summary汇总: (id:38  priority:8)
summary汇总: (id:39  priority:9)

summary汇总: (id:40  priority:-1)

[-1 ]  =Task >>id: 40 calling shutdownNow
完成 prioritizedTaskConsumer
*/

/**
*与前一个示例相同，prioritizedTask对象的创建序列被记录在sequence list中，用于和实际的执行顺序比较。
*run()方法将休眠一小段随机的时间 ，然后打印对象信息，而EndSentinel提供了和前面相同的功能，
*要确保它是队列中最后一个对象。
*
*PrioritizedTaskProducer和PrioritizedTaskComsumer通过PriorityBlockingQueue彼此连接。
*因为这种队列的阻塞特性提供了所有必需的同步，所以你应该注意到了，这里不需要任何显式的同步--不必考虑你从这种队列中读取时
*，基中是否有无素，因为这个队列在没有元素时，将直接阻塞读取者。
*
*
*/