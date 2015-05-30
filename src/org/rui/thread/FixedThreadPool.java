package org.rui.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用executor
 * 
 * 在任何线程池中 现有线程在可能的情况下，都会自动复用
 * 
 * @author lenovo
 * 
 */
public class FixedThreadPool {
	public static void main(String[] args) {

		/**
		 * 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
		 */
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
/**
 * OUTPUT: #0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(liftoff!),
 * #1(9),#3(9),#1(8),#3(8),#1(7),#3(7),#1(6),#3(6),#1(5),#3(5),#1(4),
 * #3(4),#1(3),#3(3),#1(2),#3(2),#1(1),#3(1),#1(liftoff!),#3(liftoff!),
 * #2(9),#2(8),#2(7),#2(6),#2(5),#2(4),#2(3),#2(2),
 * #2(1),#2(liftoff!),#4(9),#4(8
 * ),#4(7),#4(6),#4(5),#4(4),#4(3),#4(2),#4(1),#4(liftoff!),
 */
