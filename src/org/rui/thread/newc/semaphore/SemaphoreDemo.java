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
	private Pool<T> pool;

	public CheckoutTask(Pool<T> pool)
	{
		this.pool = pool;
	}

	@Override
	public void run()
	{

		try
		{
			T item = pool.checkOut();
			System.out.println(this + "checked out:" + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println("checked in:" + item);
			pool.checkIn(item);

		} catch (InterruptedException e)
		{
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
////////////////////////////
public class SemaphoreDemo
{
	final static int Size=25;
	public static void main(String[] args) throws InterruptedException
	{
		final Pool<Fat> pool=new Pool<Fat>(Fat.class,Size);
		
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<Size;i++){
			exec.execute(new CheckoutTask<Fat>(pool));
		}
		System.out.println("all checkoutTasks created");
		List<Fat> list=new ArrayList<Fat>();
		for(int i=0;i<Size;i++){
			try
			{
				Fat f=pool.checkOut();
				System.out.println(i+">>main() thrad checked out");

				f.operation();
				list.add(f);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Future<?> blocked=exec.submit(new Runnable(){

			@Override
			public void run()
			{
				try
				{
					//semaphore prevents additional checkout 信号量防止额外的校验
					//so call is blocked 因此调用被阻塞
					pool.checkOut();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);//break out of blocked call 中断呼叫中断
		System.out.println();
		for(Fat f:list){
			pool.checkIn(f);
		}
		for(Fat f:list){
			pool.checkIn(f);//second checkin ignored 二签忽略
		}
		exec.shutdown();
	}

}
