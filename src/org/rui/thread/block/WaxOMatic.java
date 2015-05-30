package org.rui.thread.block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



// wax��    �����Զ���ʽ
public class WaxOMatic {

	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();// �ж���������
		// shutdownNow ��ͼֹͣ��������ִ�еĻ������ͣ�������ڵȴ������񣬲����صȴ�ִ�е������б�
	}
}

class Car {
	//��ʾ �׹� �������Ĵ���״̬
	private boolean waxOn = false;

	// ����
	public synchronized void waxed() {
		waxOn = true;// ready to buff
		notifyAll();
	}

	// �׹�
	public synchronized void buffed() {
		waxOn = false;// ready to another coat of wax
		notifyAll();
	}

	// wait ����
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();//�����������
		}
	}

	// wait �׹�
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true) {
			wait();//�����������
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();// ����  
				car.waitForBuffing();//�� �׹�
			}
		} catch (InterruptedException e) {
			System.out.println("ͨ���ж��˳�");
			// e.printStackTrace();
		}
		System.out.println("ending Wax on task");
	}

}

// /////////////////////

class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();//�� ����
				System.out.println("wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();//�׹�

			}
		} catch (InterruptedException e) {
			System.out.println("ͨ���ж��˳�");
			// e.printStackTrace();
		}
		System.out.println("ending Wax Off task");
	}

}

/*output:(95% match) 
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
wax off!
wax on!
ͨ���ж��˳�
ending Wax on task
ͨ���ж��˳�
ending Wax Off task
*/
