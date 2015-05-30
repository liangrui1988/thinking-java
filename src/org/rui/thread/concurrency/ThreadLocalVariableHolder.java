package org.rui.thread.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * �̱߳��ش�洢
 * 
 * ������������Դ�ϲ�����ͻ�ĵڶ��ַ�ʽ�Ǹ����Ա������� �̱߳��ش洢��һ���Զ������ƣ�����Ϊʹ����ͬ������ÿ����ͬ���̶߳�������ͬ�Ĵ洢����ˣ�
 * �������5���̶߳�Ҫʹ�ñ���X����ʾ�Ķ������̱߳��ش洢�ͻ�����5������x�Ĳ�ͬ�Ĵ洢�顣��Ҫ�ǣ� ����ʹ������Խ�״̬���̹߳���������
 * 
 * @author lenovo
 * 
 */
class Accessor implements Runnable {
	private final int id;

	public Accessor(int idn) {
		id = idn;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.incrment();
			System.out.println(this);
			Thread.yield();
		}

	}

	// toString()
	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}

}

// /////////////////////////////
public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);

		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}

	};

	// //
	public static void incrment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Accessor(i));
		TimeUnit.SECONDS.sleep(3);// run for a while
		exec.shutdownNow();// all accessors will quit

	}
}
/**
 * ThreadLocal����ͨ��������̬��洢���ڴ���ThreadLocalʱ����ֻ��ͨ��get() set()���������ʸö�������ݣ�
 * ���У�get()���������������߳�������Ķ��󸱱�����set�Ὣ�������뵽Ϊ���̴߳洢�Ķ����У������ش洢��ԭ�еĶ���
 * increment()��get()������������ʾ����㣬 ThreadLocal��֤������־�������.
 * 
 * 
 * 
 * output(sample) #1: 39807 #1: 39808 #1: 39809 #1: 39810 #1: 39811 #1: 39812
 * #0: 44495 #4: 41633 #4: 41634 #4: 41635 #4: 41636 #4: 41637 #4: 41638 #4:
 * 41639 #4: 41640
 */
