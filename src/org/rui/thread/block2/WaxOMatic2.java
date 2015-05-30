package org.rui.thread.block2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用顯示的lock 和conditon， 
 * 只有在更加困難的多線程問題中才是必須使用的
 * 
 * @author lenovo
 * 
 */
// wax蜡 电气自动方式
public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();// 中断所有任务
		// shutdownNow 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表
	}
}

class Car {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();//管理任務的通信，不包含任何有關處理狀態信息
	// 表示 抛光 、上蜡的处理状态
	private boolean waxOn = false;

	// 上蜡
	public/* synchronized */void waxed() {
		lock.lock();
		try {
			waxOn = true;// ready to buff
			// notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	// 抛光
	public/* synchronized */void buffed() {

		lock.lock();
		try {
			waxOn = false;// ready to another coat of wax
			// notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	// wait 上蜡
	public/* synchronized */void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == false) {
				// wait();// 挂起这个任务
				condition.await();
			}
		} finally {
			lock.unlock();
		}

	}

	// wait 抛光
	public /*synchronized*/ void waitForBuffing() throws InterruptedException {

		lock.lock();
		try {
			while (waxOn == true) {
				// wait();// 挂起这个任务
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();// 上蜡
				car.waitForBuffing();// 等 抛光
			}
		} catch (InterruptedException e) {
			System.out.println("通过中断退出");
			// e.printStackTrace();
		}
		System.out.println("ending Wax on task");
	}

}

// /////////////////////

class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();// 等 吐蜡
				System.out.println("wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();// 抛光

			}
		} catch (InterruptedException e) {
			System.out.println("通过中断退出");
			// e.printStackTrace();
		}
		System.out.println("ending Wax Off task");
	}

}

/*
 * output:(95% match) 
wax off!
wax on!
...
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
通过中断退出
通过中断退出
ending Wax Off task
ending Wax on task

 */
