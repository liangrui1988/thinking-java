package org.rui.thread.res;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized关键字不能尝试着获取锁且最终获取锁会失败 或都尝试获取一段时间 ，然后放弃它，要实现这些，你必须使用concurrent类库：
 * 
 * 
 * ReentrantLock允许你尝试着获取但最终末获取锁，这样如果其他人已经获取了这个锁，
 * 那么你就可以决定离开去执行其他一些事件，而不是等待直至这个锁被释放，就像在untimed()方法中所看到的。 在timed中
 * 做出尝试去获取锁，该尝试可以在2秒之后失败
 * 
 * @author lenovo
 * 
 */
public class AttemptLocking {
	// 可重入的互斥锁
	private ReentrantLock lock = new ReentrantLock();

	public void untimed()// 不计时的
	{
		// 仅在调用时锁未被另一个线程保持的情况下，才获取该锁。
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}

	// ///////////
	public void timed()// 计时
	{
		boolean captured = false;
		try {
			// 如果锁在给定等待时间内没有被另一个线程保持，且当前线程未被中断，则获取该锁。
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
		al.untimed();// true -- lock is available 锁可用
		al.timed();// true -- lock is available
		// 现在创建一个单独的任务获取锁 使下面的线程调用产生竞争
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
		// 暂停当前正在执行的线程对象，并执行其他线程。
		// Thread.yield();//give the 2nd task a chance 给第二个任务一个机会
		al.untimed();// false--lock grabbed by task 锁了的任务
		al.timed();// false--lock grabbed by task
	}

}
/**
 * output: tryLock(): true tryLock(2,TimeUnit.SECONDS) : true acquired
 * tryLock(): false tryLock(2,TimeUnit.SECONDS) : false
 */
