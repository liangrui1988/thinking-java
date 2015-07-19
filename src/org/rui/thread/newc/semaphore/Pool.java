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
//				Object o=classObject.newInstance();
//				Fat f=(Fat) classObject.newInstance();
				items.add(classObject.newInstance());
			} catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	//�������Ҫһ���¶�����ô����Ե���checkOut(),��ʹ�����ݽ���checkIn()
	public T checkOut() throws InterruptedException
	{
		//���û���κε�Semaphore���֤����ζ�ų���û�ж��������
		available.acquire();//��ȡһ����ɣ�����ṩ��һ�������������أ������õ�������� 1��
		return getItems();
	}

	public void checkIn(T x)
	{
		if (releaseItem(x))
			//�����ǩ��Ķ�����Ч��������ź�������һ�����֤
			available.release();//�ͷ�һ����ɣ����䷵�ظ��ź�����

	}

	private synchronized T getItems()
	{
		for (int i = 0; i < size; ++i)
		{
			//System.out.println(checkedOut[i]);
			if (!checkedOut[i])//���Ϊfalse  ˵����releaseItem �����ǩ��
			{
				checkedOut[i] = true;
				//System.out.println("xxxxxx===="+items.get(i));
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
		if (checkedOut[index])//���Ϊtrue ��˵����ǩ�� ������ͷ�
		{
			//System.out.println("releaseItem:"+item);
			checkedOut[index] = false;
			return true;
		}
		return false;// wasn't checked out û��ǩ��
	}
}
