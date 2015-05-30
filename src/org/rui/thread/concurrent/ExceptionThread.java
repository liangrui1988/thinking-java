package org.rui.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 捕获异常
 * 
 * 下面的任务总是会抛出一个异常，该异常会传播到其run方法的外部， 并且main展示了当你运行它时，所发生的事情
 * 
 * @author lenovo
 * 
 */
public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();

	}

	public static void main(String[] args) {
		/*
		 * ExecutorService exec=Executors.newCachedThreadPool();
		 * exec.execute(new ExceptionThread());
		 */
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("eeeeeeeeeeeeeeee 该语句将不执行!");
		}

	}

}
/**
 * output: 以上输出结果一样: Exception in thread "pool-1-thread-1"
 * java.lang.RuntimeException at
 * org.rui.thread.concurrent.ExceptionThread.run(ExceptionThread.java:15) at
 * java
 * .util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1110)
 * at
 * java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:
 * 603) at java.lang.Thread.run(Thread.java:722)
 */
