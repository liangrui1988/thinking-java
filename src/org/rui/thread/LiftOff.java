package org.rui.thread;

/**
 * 定义任务
 * 
 * @author lenovo
 * 
 */
public class LiftOff implements Runnable {

	protected int countDown = 10;//计时
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "liftOff!") + "),";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			// 机制的一部分，可以将cpu从一个线程转移给另一个线程 的一种建议
			// 它在声明： 我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机
			// 为完全是先择性的，
			Thread.yield();// 线程调度
		}

	}

}
