package org.rui.thread.volatiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 为了测试SerialNumberGenerator 我们需要不会耗尽内存的集（SET） ，
 * 以防需要花费很长的时间来探测问题。这里所示的circularSet重用了存储int数值的内存，
 * 并假设在你生成序列数时，产生数值覆盖冲突的可能性极小。add 和contains 方法都 是synchronized,以防示线程冲突
 * 
 * @author lenovo
 * 
 */
// 重用存储所以我们不耗尽内存
// reuses storage so we don't run out of memory
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		// 初始化一个值而不是生产
		// 这个初始全是-1和serialNumberGenerator 不相同，其后取serialNumberGenerator.next...存入
		// by the serialNumberGenerator;
		for (int i = 0; i < size; i++)
			array[i] = -1;
	}

	// add
	public synchronized void add(int i) {
		array[index] = i;
		// wrap index and write over old elements; 将指数和写在旧元素;
		index = ++index % len;
		// System.out.println(index+"  :  len :"+len);

	}

	// contains
	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++) {
			System.out.println(array[i] + " == " + val);
			if (array[i] == val)
				return true;

		}
		return false;
	}

}

// /////////////////////////////////////////////
public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();

	static class SerialChecker implements Runnable {
		@Override
		public void run() {
			while (true) {
				// 自增长
				int serial = SerialNumberGenerator.nextSerialNumber();
				if (serials.contains(serial)) {
					// 重复
					System.out.println("Duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}

	// //
	public static void main(String[] args) throws NumberFormatException,
			InterruptedException {
		String[] arg = new String[] { "10000" };

		for (int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
			// stop after n seconds if there 's an argument 停止在n秒后如果有一个论点
			if (arg.length > 0) {
				TimeUnit.SECONDS.sleep(new Integer(arg[0]));
				System.out.println("没有重复检测");
				System.exit(0);
			}
		}
	}
}

/**
 * 通过创建多个任务来竞争序列数，你将发现这些任务最终会得到重复的序列数，如果你运行的时间足够长的话 为了解决这个问题，nextSerialNumber
 * 前面添加 了synchronized关健字
 */
