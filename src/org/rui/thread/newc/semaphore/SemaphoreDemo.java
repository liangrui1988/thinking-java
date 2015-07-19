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
			System.out.println(this + "checked out 已签出:" + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println("checked in 释放:" + item);
			pool.checkIn(item);// 将用完的对像释放
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
		// 开始操练pool
		for (int i = 0; i < Size; i++)
		{
			// // 持有一段时间后再将它们签入

			exec.execute(new CheckoutTask<Fat>(pool, i));
		}
		System.out.println("all checkoutTasks created");

		// main 开始签出pool中的Fat对象 -1,但并不签入他们。
		// 一但池中的所有的对象都被签出后，semaphore将不在执行任何签出操作，
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < Size; i++)
		{
			try
			{
				Fat f = pool.checkOut();
				System.out.println(i + "   >>main() thrad checked out 签出");

				f.operation();
				list.add(f);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		// 无对象可签出 run 会阻塞
		Future<?> blocked = exec.submit(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					// semaphore prevents additional checkout 信号量防止额外的校验
					// so call is blocked 因此调用被阻塞
					pool.checkOut();
				} catch (InterruptedException e)
				{
					System.err.print("checkOut() interrupted");
					// e.printStackTrace();
				}

			}

		});
		TimeUnit.SECONDS.sleep(2);
		// 以此来挣脱future，取消,冗余的签入将被pool忽略
		System.out.println("任务是否完成：" + blocked.isDone());
		blocked.cancel(true);// break out of blocked call 中断呼叫中断 试图取消对此任务的执行
		System.out.println();

		// ///////////////都签入之后 再可以继续使用pool
		for (Fat f : list)
		{
			pool.checkIn(f);
		}
		// 这里其实可以再次使用pool了，
		for (Fat f : list)
		{
			pool.checkIn(f);// second checkin ignored 二签忽略
		}
		// exec.shutdown();
	}

}

/**
 * output:
 * checkoutTask 0 checked out 已签出:Fat>>id:0
checkoutTask 2 checked out 已签出:Fat>>id:2
checkoutTask 1 checked out 已签出:Fat>>id:1
checkoutTask 4 checked out 已签出:Fat>>id:3
checkoutTask 3 checked out 已签出:Fat>>id:4
checkoutTask 5 checked out 已签出:Fat>>id:5
checkoutTask 6 checked out 已签出:Fat>>id:6
checkoutTask 7 checked out 已签出:Fat>>id:7
checkoutTask 8 checked out 已签出:Fat>>id:8
checkoutTask 9 checked out 已签出:Fat>>id:9
checkoutTask 10 checked out 已签出:Fat>>id:10
checkoutTask 11 checked out 已签出:Fat>>id:11
checkoutTask 12 checked out 已签出:Fat>>id:12
checkoutTask 13 checked out 已签出:Fat>>id:13
checkoutTask 14 checked out 已签出:Fat>>id:14
checkoutTask 15 checked out 已签出:Fat>>id:15
checkoutTask 16 checked out 已签出:Fat>>id:16
checkoutTask 18 checked out 已签出:Fat>>id:18
checkoutTask 17 checked out 已签出:Fat>>id:17
checkoutTask 19 checked out 已签出:Fat>>id:19
checkoutTask 20 checked out 已签出:Fat>>id:20
checkoutTask 21 checked out 已签出:Fat>>id:21
checkoutTask 22 checked out 已签出:Fat>>id:22
checkoutTask 23 checked out 已签出:Fat>>id:23
all checkoutTasks created
0   >>main() thrad checked out 签出
operation>>  Fat>>id:24
checked in 释放:Fat>>id:1
checkoutTask 24 checked out 已签出:Fat>>id:1
checked in 释放:Fat>>id:2
1   >>main() thrad checked out 签出
operation>>  Fat>>id:2
checked in 释放:Fat>>id:0
2   >>main() thrad checked out 签出
operation>>  Fat>>id:0
checked in 释放:Fat>>id:3
3   >>main() thrad checked out 签出
operation>>  Fat>>id:3
checked in 释放:Fat>>id:4
4   >>main() thrad checked out 签出
operation>>  Fat>>id:4
checked in 释放:Fat>>id:5
5   >>main() thrad checked out 签出
operation>>  Fat>>id:5
checked in 释放:Fat>>id:7
6   >>main() thrad checked out 签出
operation>>  Fat>>id:7
checked in 释放:Fat>>id:9
7   >>main() thrad checked out 签出
operation>>  Fat>>id:9
checked in 释放:Fat>>id:11
8   >>main() thrad checked out 签出
operation>>  Fat>>id:11
checked in 释放:Fat>>id:13
9   >>main() thrad checked out 签出
operation>>  Fat>>id:13
checked in 释放:Fat>>id:15
10   >>main() thrad checked out 签出
operation>>  Fat>>id:15
checked in 释放:Fat>>id:17
11   >>main() thrad checked out 签出
operation>>  Fat>>id:17
checked in 释放:Fat>>id:21
12   >>main() thrad checked out 签出
operation>>  Fat>>id:21
checked in 释放:Fat>>id:19
13   >>main() thrad checked out 签出
operation>>  Fat>>id:19
checked in 释放:Fat>>id:23
14   >>main() thrad checked out 签出
operation>>  Fat>>id:23
checked in 释放:Fat>>id:8
checked in 释放:Fat>>id:6
15   >>main() thrad checked out 签出
operation>>  Fat>>id:6
16   >>main() thrad checked out 签出
operation>>  Fat>>id:8
checked in 释放:Fat>>id:10
17   >>main() thrad checked out 签出
checked in 释放:Fat>>id:14
checked in 释放:Fat>>id:16
checked in 释放:Fat>>id:18
checked in 释放:Fat>>id:20
checked in 释放:Fat>>id:22
checked in 释放:Fat>>id:12
operation>>  Fat>>id:10
18   >>main() thrad checked out 签出
operation>>  Fat>>id:12
19   >>main() thrad checked out 签出
operation>>  Fat>>id:14
20   >>main() thrad checked out 签出
operation>>  Fat>>id:16
21   >>main() thrad checked out 签出
operation>>  Fat>>id:18
22   >>main() thrad checked out 签出
operation>>  Fat>>id:20
23   >>main() thrad checked out 签出
operation>>  Fat>>id:22
checked in 释放:Fat>>id:1
24   >>main() thrad checked out 签出
operation>>  Fat>>id:1
任务是否完成：false

checkOut() interrupted
*/
