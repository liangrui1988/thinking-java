package org.rui.thread.newc.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class CheckoutTask<T> implements Runnable
{

	private static int counter = 0;
	private final int id = counter++;
	private int index;

	private Pool<T> pool;

	public CheckoutTask(Pool<T> pool, int size)
	{
		this.pool = pool;
		this.index = size;
	}

	@Override
	public void run()
	{

		try
		{
			T item = pool.checkOut();
			System.out.println(this + "checked out ��ǩ��:" + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println("checked in �ͷ�:" + item);
			pool.checkIn(item);// ������Ķ����ͷ�
			// System.out.println(index+"  counter="+counter+"  id=="+id);
			// if(index==counter-1){
			// TimeUnit.SECONDS.sleep(1);
			// System.out.println("=======all checkoutTasks created");
			//
			// }

		} catch (InterruptedException e)
		{
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	@Override
	public String toString()
	{
		return "checkoutTask " + id + " ";
	}

}

// //////////////////////////
public class SemaphoreDemo
{
	final static int Size = 25;

	public static void main(String[] args) throws InterruptedException
	{
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, Size);

		ExecutorService exec = Executors.newCachedThreadPool();
		// ��ʼ����pool
		for (int i = 0; i < Size; i++)
		{
			// // ����һ��ʱ����ٽ�����ǩ��

			exec.execute(new CheckoutTask<Fat>(pool, i));
		}
		System.out.println("all checkoutTasks created");

		// main ��ʼǩ��pool�е�Fat���� -1,������ǩ�����ǡ�
		// һ�����е����еĶ��󶼱�ǩ����semaphore������ִ���κ�ǩ��������
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < Size; i++)
		{
			try
			{
				Fat f = pool.checkOut();
				System.out.println(i + "   >>main() thrad checked out ǩ��");

				f.operation();
				list.add(f);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		// �޶����ǩ�� run ������
		Future<?> blocked = exec.submit(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					// semaphore prevents additional checkout �ź�����ֹ�����У��
					// so call is blocked ��˵��ñ�����
					pool.checkOut();
				} catch (InterruptedException e)
				{
					System.err.print("checkOut() interrupted");
					// e.printStackTrace();
				}

			}

		});
		TimeUnit.SECONDS.sleep(2);
		// �Դ�������future��ȡ��,�����ǩ�뽫��pool����
		System.out.println("�����Ƿ���ɣ�" + blocked.isDone());
		blocked.cancel(true);// break out of blocked call �жϺ����ж� ��ͼȡ���Դ������ִ��
		System.out.println();

		// ///////////////��ǩ��֮�� �ٿ��Լ���ʹ��pool
		for (Fat f : list)
		{
			pool.checkIn(f);
		}
		// ������ʵ�����ٴ�ʹ��pool�ˣ�
		for (Fat f : list)
		{
			pool.checkIn(f);// second checkin ignored ��ǩ����
		}
		// exec.shutdown();
	}

}

/**
 * output:
 * checkoutTask 0 checked out ��ǩ��:Fat>>id:0
checkoutTask 2 checked out ��ǩ��:Fat>>id:2
checkoutTask 1 checked out ��ǩ��:Fat>>id:1
checkoutTask 4 checked out ��ǩ��:Fat>>id:3
checkoutTask 3 checked out ��ǩ��:Fat>>id:4
checkoutTask 5 checked out ��ǩ��:Fat>>id:5
checkoutTask 6 checked out ��ǩ��:Fat>>id:6
checkoutTask 7 checked out ��ǩ��:Fat>>id:7
checkoutTask 8 checked out ��ǩ��:Fat>>id:8
checkoutTask 9 checked out ��ǩ��:Fat>>id:9
checkoutTask 10 checked out ��ǩ��:Fat>>id:10
checkoutTask 11 checked out ��ǩ��:Fat>>id:11
checkoutTask 12 checked out ��ǩ��:Fat>>id:12
checkoutTask 13 checked out ��ǩ��:Fat>>id:13
checkoutTask 14 checked out ��ǩ��:Fat>>id:14
checkoutTask 15 checked out ��ǩ��:Fat>>id:15
checkoutTask 16 checked out ��ǩ��:Fat>>id:16
checkoutTask 18 checked out ��ǩ��:Fat>>id:18
checkoutTask 17 checked out ��ǩ��:Fat>>id:17
checkoutTask 19 checked out ��ǩ��:Fat>>id:19
checkoutTask 20 checked out ��ǩ��:Fat>>id:20
checkoutTask 21 checked out ��ǩ��:Fat>>id:21
checkoutTask 22 checked out ��ǩ��:Fat>>id:22
checkoutTask 23 checked out ��ǩ��:Fat>>id:23
all checkoutTasks created
0   >>main() thrad checked out ǩ��
operation>>  Fat>>id:24
checked in �ͷ�:Fat>>id:1
checkoutTask 24 checked out ��ǩ��:Fat>>id:1
checked in �ͷ�:Fat>>id:2
1   >>main() thrad checked out ǩ��
operation>>  Fat>>id:2
checked in �ͷ�:Fat>>id:0
2   >>main() thrad checked out ǩ��
operation>>  Fat>>id:0
checked in �ͷ�:Fat>>id:3
3   >>main() thrad checked out ǩ��
operation>>  Fat>>id:3
checked in �ͷ�:Fat>>id:4
4   >>main() thrad checked out ǩ��
operation>>  Fat>>id:4
checked in �ͷ�:Fat>>id:5
5   >>main() thrad checked out ǩ��
operation>>  Fat>>id:5
checked in �ͷ�:Fat>>id:7
6   >>main() thrad checked out ǩ��
operation>>  Fat>>id:7
checked in �ͷ�:Fat>>id:9
7   >>main() thrad checked out ǩ��
operation>>  Fat>>id:9
checked in �ͷ�:Fat>>id:11
8   >>main() thrad checked out ǩ��
operation>>  Fat>>id:11
checked in �ͷ�:Fat>>id:13
9   >>main() thrad checked out ǩ��
operation>>  Fat>>id:13
checked in �ͷ�:Fat>>id:15
10   >>main() thrad checked out ǩ��
operation>>  Fat>>id:15
checked in �ͷ�:Fat>>id:17
11   >>main() thrad checked out ǩ��
operation>>  Fat>>id:17
checked in �ͷ�:Fat>>id:21
12   >>main() thrad checked out ǩ��
operation>>  Fat>>id:21
checked in �ͷ�:Fat>>id:19
13   >>main() thrad checked out ǩ��
operation>>  Fat>>id:19
checked in �ͷ�:Fat>>id:23
14   >>main() thrad checked out ǩ��
operation>>  Fat>>id:23
checked in �ͷ�:Fat>>id:8
checked in �ͷ�:Fat>>id:6
15   >>main() thrad checked out ǩ��
operation>>  Fat>>id:6
16   >>main() thrad checked out ǩ��
operation>>  Fat>>id:8
checked in �ͷ�:Fat>>id:10
17   >>main() thrad checked out ǩ��
checked in �ͷ�:Fat>>id:14
checked in �ͷ�:Fat>>id:16
checked in �ͷ�:Fat>>id:18
checked in �ͷ�:Fat>>id:20
checked in �ͷ�:Fat>>id:22
checked in �ͷ�:Fat>>id:12
operation>>  Fat>>id:10
18   >>main() thrad checked out ǩ��
operation>>  Fat>>id:12
19   >>main() thrad checked out ǩ��
operation>>  Fat>>id:14
20   >>main() thrad checked out ǩ��
operation>>  Fat>>id:16
21   >>main() thrad checked out ǩ��
operation>>  Fat>>id:18
22   >>main() thrad checked out ǩ��
operation>>  Fat>>id:20
23   >>main() thrad checked out ǩ��
operation>>  Fat>>id:22
checked in �ͷ�:Fat>>id:1
24   >>main() thrad checked out ǩ��
operation>>  Fat>>id:1
�����Ƿ���ɣ�false

checkOut() interrupted
*/
