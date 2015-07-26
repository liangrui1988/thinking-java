package org.rui.thread.newc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.rui.generics.anonymity.BasicGenerator;
import org.rui.generics.anonymity.Generator;
import org.rui.thread.newc.semaphore.Fat;

/**
 * @author lenovo
 * 在两个任务之间切换栅栏，当这些栅栏进入任务时，它们和自拥有一个对象，当它们离开时，它们都拥有之前由对象持有的对象。
 * 
 * ==
 * 可以有更多的对象在被创建的同时被消费。
 * 
 */

/**
 * 生产者
 */
class ExchangerProduer<T> implements Runnable
{

	private Generator<T> generator;// 生成器
	private Exchanger<List<T>> exchanger;
	private List<T> holder;// 对象集合

	ExchangerProduer(Exchanger<List<T>> exchg, Generator<T> generator,
			List<T> holder)
	{
		this.generator = generator;
		this.exchanger = exchg;
		this.holder = holder;
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				for (int i = 0; i < ExchangerDemo.size; i++)
				{
					// 把加工好的对象放入集合中
					T t = generator.next();
					System.err.println(Thread.currentThread().getName()
							+ ">>生产对象:" + t);
					holder.add(generator.next());

					/**
					 * exchange(V v)
					 * 等待另一个线程到达此交换点（除非当前线程被中断），然后将给定的对象传送给该线程，并接收该线程的对象。
					 */
					// exchange full for empty: 全部为空
					holder = exchanger.exchange(holder);
				}
			}

		} catch (InterruptedException e)
		{
			// ok to terminate this way 可以以这种方式终止
		}
	}

}

/**
 * demo main
 * @author lenovo
 *
 * @param <T>
 */
class ExchangerConsumer<T> implements Runnable
{
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;

	ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder)
	{
		exchanger = ex;
		this.holder = holder;
	}

	@Override
	public void run()
	{
		try
		{
			while (!Thread.interrupted())
			{
				// 消费者取出对象 进行处理
				exchanger.exchange(holder);
				for (T x : holder)
				{
					value = x;// fetch out value
					System.out.println(Thread.currentThread().getName()
							+ "消费对象:" + x);
					holder.remove(x);// ok for copyOmWriteArrayList 消费完成移除
				}
			}
		} catch (InterruptedException e)
		{
			// ok to terminate this way
		}
		System.out.println("Final value:" + value);

	}
}

public class ExchangerDemo
{
	static int size = 10;
	static int delay = 5;

	public static void main(String[] args) throws Exception
	{
		String[] argss = new String[]
		{ "10", "5" };
		if (argss.length > 0)
		{
			size = new Integer(argss[0]);

		}
		if (argss.length > 1)
		{
			delay = new Integer(argss[0]);
		}
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();

		// ----------------------生产者
		// CopyOnWriteArrayList >>>ArrayList 的一个线程安全的变体，
		// 其中所有可变操作（add、set 等等）都是通过对底层数组进行一次新的复制来实现的
		List<Fat> producerList = new CopyOnWriteArrayList<Fat>();
		ExchangerProduer produer = new ExchangerProduer<Fat>(xc,
				BasicGenerator.create(Fat.class), producerList);
		//当调用 exchange(), 它将阻塞直至对方任务调用它自已的exchange()方法，那时，两个exchange()方法将全部完成，而List<T>则被互转：
		exec.execute(produer);
		TimeUnit.SECONDS.sleep(delay);

		// ---------------------- 消费  
		List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();// 消费者
		exec.execute(new ExchangerConsumer<Fat>(xc, consumerList));

		TimeUnit.SECONDS.sleep(delay);

		// 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
		exec.shutdownNow();

	}

}

/**
 * output:
 * Final value:Fat>>id:89024
 */
