package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * CyclicBarrier 应用
 * @author lenovo
 *
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		final CyclicBarrier cb = new CyclicBarrier(3); // 三个线程同时到达
		
		for (int i = 0; i < 3; i++) {
			
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点1，当前已有"
								+ (cb.getNumberWaiting() + 1)
								+ "个已到达"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊"
										: "正在等候"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点2，当前已有"
								+ (cb.getNumberWaiting() + 1)
								+ "个已到达"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊"
										: "正在等候"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点3，当前已有"
								+ (cb.getNumberWaiting() + 1)
								+ "个已到达"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊"
										: "正在等候"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}

/**
 * output:
线程pool-1-thread-3即将到达集合地点1，当前已有1个已到达正在等候
线程pool-1-thread-1即将到达集合地点1，当前已有2个已到达正在等候
线程pool-1-thread-2即将到达集合地点1，当前已有3个已到达都到齐了，继续走啊
线程pool-1-thread-2即将到达集合地点2，当前已有1个已到达正在等候
线程pool-1-thread-1即将到达集合地点2，当前已有2个已到达正在等候
线程pool-1-thread-3即将到达集合地点2，当前已有3个已到达都到齐了，继续走啊
线程pool-1-thread-3即将到达集合地点3，当前已有1个已到达正在等候
线程pool-1-thread-2即将到达集合地点3，当前已有2个已到达正在等候
线程pool-1-thread-1即将到达集合地点3，当前已有3个已到达都到齐了，继续走啊
 */
