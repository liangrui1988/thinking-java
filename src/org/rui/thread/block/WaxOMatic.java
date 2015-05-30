package org.rui.thread.block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



// wax蜡    电气自动方式
public class WaxOMatic {

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
	//表示 抛光 、上蜡的处理状态
	private boolean waxOn = false;

	// 上蜡
	public synchronized void waxed() {
		waxOn = true;// ready to buff
		notifyAll();
	}

	// 抛光
	public synchronized void buffed() {
		waxOn = false;// ready to another coat of wax
		notifyAll();
	}

	// wait 上蜡
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();//挂起这个任务
		}
	}

	// wait 抛光
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true) {
			wait();//挂起这个任务
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
				car.waitForBuffing();//等 抛光
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
				car.waitForWaxing();//等 吐蜡
				System.out.println("wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();//抛光

			}
		} catch (InterruptedException e) {
			System.out.println("通过中断退出");
			// e.printStackTrace();
		}
		System.out.println("ending Wax Off task");
	}

}

/*output:(95% match) 
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
wax on!
wax off!
wax on!
通过中断退出
ending Wax on task
通过中断退出
ending Wax Off task
*/
