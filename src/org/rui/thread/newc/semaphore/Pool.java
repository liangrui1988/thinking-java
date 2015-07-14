package org.rui.thread.newc.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
/**
 * ����N������ͬʱ���������Դ
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
		// load pool with objects that can be checked out:���Լ��Ķ���ĸ��س�
		for (int i = 0; i < size; i++)
		{
			// assumes a default constructor�Լ���Ĭ�Ϲ��캯��
			try
			{
				items.add(classObject.newInstance());
			} catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	public T checkOut() throws InterruptedException
	{
		available.acquire();
		return getItems();
	}

	public void checkIn(T x)
	{
		if (releaseItem(x))
			available.release();

	}

	private synchronized T getItems()
	{
		for (int i = 0; i < size; ++i)
		{
			if (checkedOut[i])
			{
				checkedOut[i] = true;
				return items.get(i);
			}

		}
		return null;// semaphore prevents reaching here��ֹ�źŵ�������
	}

	private synchronized boolean releaseItem(T item)
	{
		int index = items.indexOf(item);
		if (index == -1)
			return false;// not in the list
		if (checkedOut[index])
		{
			checkedOut[index] = false;
			return true;
		}
		return false;// wasn't checked out û��ǩ��
	}
}
