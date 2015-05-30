package org.rui.thread.deadlock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * ß@‚€³ÌÐòŒ¢®aÉúËÀæi
 * @author lenovo
 *
 */
public class DeadlockingDiningPhilosophers {

	public static void main(String[] args) throws InterruptedException, IOException {
		String arg[] = { "0", "5","timeout"};
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
			exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i,
					ponder));
			if(arg.length==3&&arg[2].equals("timeout")){
				TimeUnit.SECONDS.sleep(5);
			}
			else{
				System.out.println("press enter to quit");
			    System.in.read();
			}
			exec.shutdownNow();
		}
	}

}
