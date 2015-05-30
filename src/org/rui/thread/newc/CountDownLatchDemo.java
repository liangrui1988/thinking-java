package org.rui.thread.newc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Latch ������ 
 * ������еĹ��� countDownLatch
 * 
 * @author lenovo
 * 
 */
class TaskPortion implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);

	private final CountDownLatch latch;

	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			// acceptable way to exit
		}

	}

	// ����ҵ�����
	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + " ���");
	}

	public String toString() {
		return String.format("%1$-3d", id);
	}

}

// waits on the countDownLatch
class WaitingTask implements Runnable {
	private static int counter = 0;// ����
	private final int id = counter++;
	private static Random rand = new Random(47);

	private final CountDownLatch latch;

	WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			// ����countDown()�������ڲ�������ʱ��û�б�������ֻ�ж�await�ĵ��ûᱻ������ֱ������ֵ����0
			// �ȴ����ⱻ���������������������ϵ���await(),������������ס��ֱ����������������
			latch.await();
			System.out.println("latch �ϰ�����Ϊ " + this);
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
	}

	public String toString() {
		return String.format("waitingTask %1$-3d", id);
	}
}

/**
 * TaskPortio�����������һ��ʱ�䣬��ģ���ⲿ�ֹ�������ɣ���WaitingTask��ʾϵͳ�еȴ��Ĳ��֣���Ҫ�ȴ�������ĳ�ʼ���ֳ���Ϊֹ��
 * ���е�����ʹ������main�ж���ͬһ����һ��counDownLacth
 * 
 * @author lenovo
 * 
 */
public class CountDownLatchDemo {
	static final int SIZE = 100;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);

		// �����빲��һ��countDownLatch����
		for (int i = 0; i < 10; i++) {
			exec.execute(new WaitingTask(latch));// ���Ҫ�ȴ� latch�����Ϊ0ʱ�Ż�ִ��
		}
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new TaskPortion(latch));
		}

		// latch.await();
		System.out.println("launched all tasks");
		exec.shutdown();// quit when all task complete
	}

}
/**
output:
launched all tasks
43  ���
95  ���
99  ���
36  ���
94  ���
11  ���
....
12  ���
1   ���
27  ���
98  ���
13  ���
72  ���
71  ���
2   ���
45  ���
92  ���
31  ���
14  ���
17  ���
6   ���
97  ���
....
80  ���
....
56  ���
85  ���
61  ���
30  ���
....
3   ���
93  ���
81  ���
78  ���
73  ���
44  ���
82  ���
49  ���
64  ���
83  ���
16  ���
latch �ϰ�����Ϊ waitingTask 2  
latch �ϰ�����Ϊ waitingTask 0  
latch �ϰ�����Ϊ waitingTask 4  
latch �ϰ�����Ϊ waitingTask 1  
latch �ϰ�����Ϊ waitingTask 5  
latch �ϰ�����Ϊ waitingTask 3  
latch �ϰ�����Ϊ waitingTask 7  
latch �ϰ�����Ϊ waitingTask 6  
latch �ϰ�����Ϊ waitingTask 9  
latch �ϰ�����Ϊ waitingTask 8  
*/