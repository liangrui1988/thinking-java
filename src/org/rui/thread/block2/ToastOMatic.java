package org.rui.thread.block2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 吐司BlockingQueue
 * @author lenovo
 *
 */

class Toast {
	public enum Status {
		DRY/* 干的 */, BUTTERED/* 涂黄油 */, JAMMED// 果酱
	}

	private Status status = Status.DRY;
	private final int id;

	public Toast(int idn) {
		id = idn;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ":" + status;
	}
}

/**
 * 吐司队列
 * 
 * @author lenovo
 * 
 */
class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);

	public Toaster(ToastQueue tq) {
		toastQueue = tq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				// 制作 toast
				Toast t = new Toast(count++);
				System.out.println(t);
				// insert into queue
				toastQueue.put(t);

			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("toaster off");
	}
}

// apply butter to toast
class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}

	@Override
	public void run() {
		try {

			while (!Thread.interrupted()) {
				// blocks until next piece of toast is available 块,直到下一块面包
				Toast t = dryQueue.take();
				t.butter();
				System.out.println(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("涂黄油 interrupted");
		}
		System.out.println("涂黄油 off");
	}

}

// apply jam to buttered toast
class Jammer implements Runnable {
	private ToastQueue butteredQueue, finishedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {

			while (!Thread.interrupted()) {
				// blocks until next piece of toast is available 块,直到下一块面包
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println(t);
				finishedQueue.put(t);

			}
		} catch (InterruptedException e) {
			System.out.println("涂果酱 interrupted");
		}
		System.out.println("涂果酱 off");
	}

}

// ////使用烤面包 consume the toast
class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;

	public Eater(ToastQueue finished) {
		finishedQueue = finished;
	}

	@Override
	public void run() {
		try {

			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				// verify that the toast is coming in order 确认面包来了
				// and that all pieces are getting jammed ,所有碎片越来越挤
				if (t.getId() != counter++
						|| t.getStatus() != Toast.Status.JAMMED) {
					System.out.println("===>>>>error" + t);
					System.exit(1);

				} else {
					System.out.println("吃!" + t);
				}

			}
		} catch (InterruptedException e) {
			System.out.println("食者 interrupted");
		}
		System.out.println(" 食者 off");
	}
}

/**
 * main
 * 
 * @author lenovo
 * 
 */
public class ToastOMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue();
		ToastQueue butteredQueue = new ToastQueue();
		ToastQueue finishedQueue = new ToastQueue();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));//烤面包
		exec.execute(new Butterer(dryQueue, butteredQueue));//涂黄油
		exec.execute(new Jammer(butteredQueue, finishedQueue));//上果酱
		exec.execute(new Eater(finishedQueue));//吃
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();

	}
}
/**output:
 Toast 0:DRY
Toast 0:BUTTERED
Toast 0:JAMMED
吃!Toast 0:JAMMED
Toast 1:DRY
Toast 1:BUTTERED
Toast 1:JAMMED
吃!Toast 1:JAMMED
Toast 2:DRY
Toast 2:BUTTERED
Toast 2:JAMMED
吃!Toast 2:JAMMED
...
...
Toast 10:DRY
Toast 10:BUTTERED
Toast 10:JAMMED
吃!Toast 10:JAMMED
Toast 11:DRY
Toast 11:BUTTERED
Toast 11:JAMMED
吃!Toast 11:JAMMED
Toast 12:DRY
Toast 12:BUTTERED
Toast 12:JAMMED
吃!Toast 12:JAMMED
Toast 13:DRY
Toast 13:BUTTERED
Toast 13:JAMMED
吃!Toast 13:JAMMED
Toast 14:DRY
Toast 14:BUTTERED
Toast 14:JAMMED
吃!Toast 14:JAMMED
食者 interrupted
Toaster interrupted
 食者 off
涂果酱 interrupted
涂果酱 off
涂黄油 interrupted
涂黄油 off
toaster off

 */
