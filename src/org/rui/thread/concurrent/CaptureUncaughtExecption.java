package org.rui.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 捕获异常
 * 
 * 为了解决这个问题，我们要修改executor产生线程的方式。thread.UncaughtExceptionHandler是javaSE5中的新接口，
 * 它允许你在每个Thread对象上都附着一个异常处理器......
 * 
 * @author lenovo
 * 
 */
class ExceptionThread2 implements Runnable {
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run by : " + t);
		System.out.println(t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}

}

// ////////////////无知的Exception
class MyUncaughtExecptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {

		System.out.println("caught " + e);

	}

}

// //////////////
class handlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		System.out.println("创建新的线程");
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExecptionHandler());
		System.out.println("eh= " + t.getUncaughtExceptionHandler());
		return t;
	}

}

public class CaptureUncaughtExecption {
	public static void main(String[] args) {
		ExecutorService exec = Executors
				.newCachedThreadPool(new handlerThreadFactory());
		exec.execute(new ExceptionThread2());

	}

}

/**
 * output: 创建新的线程 eh=
 * org.rui.thread.concurrent.MyUncaughtExecptionHandler@192c8d9 run by :
 * Thread[Thread-0,5,main]
 * org.rui.thread.concurrent.MyUncaughtExecptionHandler@192c8d9 创建新的线程 eh=
 * org.rui.thread.concurrent.MyUncaughtExecptionHandler@16f144c caught
 * java.lang.RuntimeException
 */
