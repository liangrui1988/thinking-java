package org.rui.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用executor
 * 
 * @author lenovo
 * 
 */
public class CachedThreadPool {
	public static void main(String[] args) {
		// 创建并返回设置有常用配置字符串的 ExecutorService 的方法。
		/**
		 * newCachedThreadPool 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		 */
		ExecutorService exec = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
		exec.shutdown();// 防止新的任务被提交到executor

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
