package org.rui.thread.basic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 每个静态的executorService创建方法都被重载为接受一个threadfactory对象， 而这个对象将被用来创建新的线程
 * 
 * @author lenovo
 * 
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new DaemonThreadFactory());

	}

}
