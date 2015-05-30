package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * CyclicBarrier Ӧ��
 * @author lenovo
 *
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		final CyclicBarrier cb = new CyclicBarrier(3); // �����߳�ͬʱ����
		
		for (int i = 0; i < 3; i++) {
			
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�1����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���ѵ���"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�2����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���ѵ���"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�3����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���ѵ���"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
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
�߳�pool-1-thread-3�������Ｏ�ϵص�1����ǰ����1���ѵ������ڵȺ�
�߳�pool-1-thread-1�������Ｏ�ϵص�1����ǰ����2���ѵ������ڵȺ�
�߳�pool-1-thread-2�������Ｏ�ϵص�1����ǰ����3���ѵ��ﶼ�����ˣ������߰�
�߳�pool-1-thread-2�������Ｏ�ϵص�2����ǰ����1���ѵ������ڵȺ�
�߳�pool-1-thread-1�������Ｏ�ϵص�2����ǰ����2���ѵ������ڵȺ�
�߳�pool-1-thread-3�������Ｏ�ϵص�2����ǰ����3���ѵ��ﶼ�����ˣ������߰�
�߳�pool-1-thread-3�������Ｏ�ϵص�3����ǰ����1���ѵ������ڵȺ�
�߳�pool-1-thread-2�������Ｏ�ϵص�3����ǰ����2���ѵ������ڵȺ�
�߳�pool-1-thread-1�������Ｏ�ϵص�3����ǰ����3���ѵ��ﶼ�����ˣ������߰�
 */
