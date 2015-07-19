package org.rui.thread.newc.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
/**
 * 允许N个任务同时访问这个资源
 * @author lenovo
 *
 * @param <T>
 */
public class Pool<T>
{
	private int size;
	private List<T> items = new ArrayList<T>();

	private volatile boolean[] checkedOut;

	private Semaphore available;

	public Pool(Class<T> classObject, int size)
	{
		this.size = size;
		checkedOut = new boolean[size];
		available = new Semaphore(size, true);
		// load pool with objects that can be checked out:可以检查的对象的负载池
		for (int i = 0; i < size; i++)
		{
			// assumes a default constructor自己的默认构造函数
			try
			{
//				Object o=classObject.newInstance();
//				Fat f=(Fat) classObject.newInstance();
				items.add(classObject.newInstance());
			} catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	//如果你需要一个新对象，那么你可以调用checkOut(),在使用完后递交给checkIn()
	public T checkOut() throws InterruptedException
	{
		//如果没有任何的Semaphore许何证，意味着池中没有对象可用了
		available.acquire();//获取一个许可（如果提供了一个）并立即返回，将可用的许可数减 1。
		return getItems();
	}

	public void checkIn(T x)
	{
		if (releaseItem(x))
			//如果被签入的对象有效，则会向信号亮返回一个许可证
			available.release();//释放一个许可，将其返回给信号量。

	}

	private synchronized T getItems()
	{
		for (int i = 0; i < size; ++i)
		{
			//System.out.println(checkedOut[i]);
			if (!checkedOut[i])//如果为false  说明是releaseItem 则可以签出
			{
				checkedOut[i] = true;
				//System.out.println("xxxxxx===="+items.get(i));
				return items.get(i);
			}

		}
		return null;// semaphore prevents reaching here防止信号到达这里
	}

	private synchronized boolean releaseItem(T item)
	{
		int index = items.indexOf(item);
		if (index == -1)
			return false;// not in the list
		if (checkedOut[index])//如果为true 则说明已签出 则可以释放
		{
			//System.out.println("releaseItem:"+item);
			checkedOut[index] = false;
			return true;
		}
		return false;// wasn't checked out 没有签出
	}
}
