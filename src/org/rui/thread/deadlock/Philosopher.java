package org.rui.thread.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * �܌W��
 * @author lenovo
 *
 */
public class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;// ˼��
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
				System.out.println(this + " " + "thinking ˼��");
				pause();// ��ͣ

				// philosopher becomes hungry
				System.out.println(this + " " + "grabbing ץ  right");
				right.take();
				System.out.println(this + " " + "grabbing ץ left");
				left.take();
				System.out.println(this + "" + "eating �Է�");
				pause();// ��ͣ
				right.drop();//��ֹ
				left.drop();
			}

		} catch (InterruptedException e) {
			System.out.println(this+" "+"ͨ���ж��˳�");
		}

	}

	@Override
	public String toString() {
		return " philosopher " + id;
	}

}
