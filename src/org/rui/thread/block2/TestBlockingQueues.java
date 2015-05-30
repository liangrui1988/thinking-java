package org.rui.thread.block2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.rui.thread.LiftOff;

/**
 * 生产者-消费者与队列
 * 
 * @author lenovo
 * 
 */

class LiftOffRunner implements Runnable {

	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> b) {
		rockets = b;
	}

	//添加一个任务到队列
	public void add(LiftOff lo) {
		//将指定元素插入此队列中（如果立即可行且不会违反容量限制），
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				// 获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
				LiftOff rocket = rockets.take();
				rocket.run();
			}

		} catch (InterruptedException e) {
			System.out.println("中断退出");
		}
		System.out.println("x exiting liftOffRunner");

	}
}

public class TestBlockingQueues {
	
	static void getkey() {
		try {
			// compensate for windows/linux difference in the
			// 回车键产生的结果
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void getkey(String message) {
		System.out.println(message);
		getkey();
	}

	static void tets(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		
		//启动一个线程
		Thread t = new Thread(runner);
		t.start();
		
		for (int i = 0; i < 5; i++) {
			//加入任务到LiftOffRunner队列中
			runner.add(new LiftOff(5));
		}
		
		//输入控制台
		getkey("press 'enter' (" + msg + ")");
		t.interrupt();
		System.out.println(" 完了 " + msg + "test");

	}

	public static void main(String[] args) {
		tets("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());// unlimited																		// size
		tets("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));// fied																		// size
		tets("SynchronousQueue", new SynchronousQueue<LiftOff>());// size of 1

	}

}
