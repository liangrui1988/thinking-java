package org.rui.thread.deadlock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 破壞第四個條件 防止死鎖
 * @author lenovo
 *
 */
public class FixedDiningPhilosophers {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		String[] arg = { "5", "5", "timeout" };

		int ponder = 5;
		if (arg.length > 0) {
			ponder = Integer.parseInt(arg[0]);
		}
		int size = 5;
		if (arg.length > 1) {
			size = Integer.parseInt(arg[1]);
		}

		ExecutorService exec = Executors.newCachedThreadPool();

		Chopstick[] sticks = new Chopstick[size];

		for (int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		}

		for (int i = 0; i < size; i++) {
			// 通过确保最后一个philosopher先拿起和放下左边的Chopstick，
			// 我们可以移除死锁，从而使这个程序平滑的运行。
			if (i < (size - 1)) {
				exec.execute(new Philosopher(sticks[i], sticks[i + 1], i,
						ponder));
			} else {
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
			}
		}

		if (arg.length == 3 && arg[2].equals("timeout")) {
			TimeUnit.SECONDS.sleep(5);
		} else {
			System.out.println("press enter to quit");
			System.in.read();
		}
		exec.shutdownNow();

	}

}
