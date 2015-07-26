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
 * ����������֮���л�դ��������Щդ����������ʱ�����Ǻ���ӵ��һ�����󣬵������뿪ʱ�����Ƕ�ӵ��֮ǰ�ɶ�����еĶ���
 * 
 * ==
 * �����и���Ķ����ڱ�������ͬʱ�����ѡ�
 * 
 */

/**
 * ������
 */
class ExchangerProduer<T> implements Runnable
{

	private Generator<T> generator;// ������
	private Exchanger<List<T>> exchanger;
	private List<T> holder;// ���󼯺�

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
					// �Ѽӹ��õĶ�����뼯����
					T t = generator.next();
					System.err.println(Thread.currentThread().getName()
							+ ">>��������:" + t);
					holder.add(generator.next());

					/**
					 * exchange(V v)
					 * �ȴ���һ���̵߳���˽����㣨���ǵ�ǰ�̱߳��жϣ���Ȼ�󽫸����Ķ����͸����̣߳������ո��̵߳Ķ���
					 */
					// exchange full for empty: ȫ��Ϊ��
					holder = exchanger.exchange(holder);
				}
			}

		} catch (InterruptedException e)
		{
			// ok to terminate this way ���������ַ�ʽ��ֹ
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
				// ������ȡ������ ���д���
				exchanger.exchange(holder);
				for (T x : holder)
				{
					value = x;// fetch out value
					System.out.println(Thread.currentThread().getName()
							+ "���Ѷ���:" + x);
					holder.remove(x);// ok for copyOmWriteArrayList ��������Ƴ�
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

		// ----------------------������
		// CopyOnWriteArrayList >>>ArrayList ��һ���̰߳�ȫ�ı��壬
		// �������пɱ������add��set �ȵȣ�����ͨ���Եײ��������һ���µĸ�����ʵ�ֵ�
		List<Fat> producerList = new CopyOnWriteArrayList<Fat>();
		ExchangerProduer produer = new ExchangerProduer<Fat>(xc,
				BasicGenerator.create(Fat.class), producerList);
		//������ exchange(), ��������ֱ���Է�������������ѵ�exchange()��������ʱ������exchange()������ȫ����ɣ���List<T>�򱻻�ת��
		exec.execute(produer);
		TimeUnit.SECONDS.sleep(delay);

		// ---------------------- ����  
		List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();// ������
		exec.execute(new ExchangerConsumer<Fat>(xc, consumerList));

		TimeUnit.SECONDS.sleep(delay);

		// ��ͼֹͣ��������ִ�еĻ������ͣ�������ڵȴ������񣬲����صȴ�ִ�е������б�
		exec.shutdownNow();

	}

}

/**
 * output:
 * Final value:Fat>>id:89024
 */
