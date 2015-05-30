package org.rui.thread.res;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized�ؼ��ֲ��ܳ����Ż�ȡ�������ջ�ȡ����ʧ�� �򶼳��Ի�ȡһ��ʱ�� ��Ȼ���������Ҫʵ����Щ�������ʹ��concurrent��⣺
 * 
 * 
 * ReentrantLock�����㳢���Ż�ȡ������ĩ��ȡ������������������Ѿ���ȡ���������
 * ��ô��Ϳ��Ծ����뿪ȥִ������һЩ�¼��������ǵȴ�ֱ����������ͷţ�������untimed()�������������ġ� ��timed��
 * ��������ȥ��ȡ�����ó��Կ�����2��֮��ʧ��
 * 
 * @author lenovo
 * 
 */
public class AttemptLocking {
	// ������Ļ�����
	private ReentrantLock lock = new ReentrantLock();

	public void untimed()// ����ʱ��
	{
		// ���ڵ���ʱ��δ����һ���̱߳��ֵ�����£��Ż�ȡ������
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}

	// ///////////
	public void timed()// ��ʱ
	{
		boolean captured = false;
		try {
			// ������ڸ����ȴ�ʱ����û�б���һ���̱߳��֣��ҵ�ǰ�߳�δ���жϣ����ȡ������
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		try {
			System.out.println("tryLock(2,TimeUnit.SECONDS)  : " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}

	// /////////////main
	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();// true -- lock is available ������
		al.timed();// true -- lock is available
		// ���ڴ���һ�������������ȡ�� ʹ������̵߳��ò�������
		new Thread() {
			{
				setDaemon(true);
			}

			@Override
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.sleep(1000);
		// ��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
		// Thread.yield();//give the 2nd task a chance ���ڶ�������һ������
		al.untimed();// false--lock grabbed by task ���˵�����
		al.timed();// false--lock grabbed by task
	}

}
/**
 * output: tryLock(): true tryLock(2,TimeUnit.SECONDS) : true acquired
 * tryLock(): false tryLock(2,TimeUnit.SECONDS) : false
 */
