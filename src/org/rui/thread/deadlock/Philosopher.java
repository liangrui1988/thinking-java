package org.rui.thread.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * 哲學家
 * @author lenovo
 *
 */
public class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;// 思考
	private Random rand = new Random(47);

	private void pause() throws InterruptedException {
		
		if (ponderFactor == 0)
			return;
		{
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
		}
		
		
		
	}

	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}

	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				System.out.println(this + " " + "thinking 思考");
				pause();// 暂停

				// philosopher becomes hungry
				System.out.println(this + " " + "grabbing 抓  right");
				right.take();
				System.out.println(this + " " + "grabbing 抓 left");
				left.take();
				System.out.println(this + "" + "eating 吃饭");
				pause();// 暂停
				right.drop();//终止
				left.drop();
			}

		} catch (InterruptedException e) {
			System.out.println(this+" "+"通过中断退出");
		}

	}

	@Override
	public String toString() {
		return " philosopher " + id;
	}

}
