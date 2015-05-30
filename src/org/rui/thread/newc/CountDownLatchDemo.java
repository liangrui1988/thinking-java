package org.rui.thread.newc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Latch 锁存器 
 * 新类库中的构件 countDownLatch
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

	// 处理业务代码
	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + " 完成");
	}

	public String toString() {
		return String.format("%1$-3d", id);
	}

}

// waits on the countDownLatch
class WaitingTask implements Runnable {
	private static int counter = 0;// 计数
	private final int id = counter++;
	private static Random rand = new Random(47);

	private final CountDownLatch latch;

	WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			// 调用countDown()的任务在产生调用时并没有被阻塞，只有对await的调用会被阻塞，直至计数值到达0
			// 等待问题被解决的任务在这个锁存器上调用await(),将它们自已拦住，直至锁存器计数结束
			latch.await();
			System.out.println("latch 障碍被认为 " + this);
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
	}

	public String toString() {
		return String.format("waitingTask %1$-3d", id);
	}
}

/**
 * TaskPortio将随机地休眠一段时间，以模拟这部分工作的完成，而WaitingTask表示系统中等待的部分，它要等待到问题的初始部分成完为止，
 * 所有的任务都使用了在main中定义同一个单一的counDownLacth
 * 
 * @author lenovo
 * 
 */
public class CountDownLatchDemo {
	static final int SIZE = 100;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);

		// 都必须共享一个countDownLatch对象
		for (int i = 0; i < 10; i++) {
			exec.execute(new WaitingTask(latch));// 这个要等待 latch上面的为0时才会执行
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
43  完成
95  完成
99  完成
36  完成
94  完成
11  完成
....
12  完成
1   完成
27  完成
98  完成
13  完成
72  完成
71  完成
2   完成
45  完成
92  完成
31  完成
14  完成
17  完成
6   完成
97  完成
....
80  完成
....
56  完成
85  完成
61  完成
30  完成
....
3   完成
93  完成
81  完成
78  完成
73  完成
44  完成
82  完成
49  完成
64  完成
83  完成
16  完成
latch 障碍被认为 waitingTask 2  
latch 障碍被认为 waitingTask 0  
latch 障碍被认为 waitingTask 4  
latch 障碍被认为 waitingTask 1  
latch 障碍被认为 waitingTask 5  
latch 障碍被认为 waitingTask 3  
latch 障碍被认为 waitingTask 7  
latch 障碍被认为 waitingTask 6  
latch 障碍被认为 waitingTask 9  
latch 障碍被认为 waitingTask 8  
*/