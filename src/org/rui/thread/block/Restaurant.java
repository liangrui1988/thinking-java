package org.rui.thread.block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ��������������
 * �͹�
 * 
 * @author lenovo
 * 
 */
public class Restaurant {

	//Restaurant r=new Restaurant();
	
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitP = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitP);
	}

	public static void main(String[] args) {
		new Restaurant();
	}

}

// ��
class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}

}

// ����Ա
class WaitPerson implements Runnable {
	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		restaurant = r;
	}

	public void run() {

		try {
			while (!Thread.interrupted()) {
				
				synchronized (this) {
					while (restaurant.meal == null)//�����Ϊ�գ�����Ա�ȴ�
						wait();
				}
				System.out.println("waitperson got " + restaurant.meal);
				//�ڳ�ʦ��ͬ��  ����� ��֪ͨ��ʦ
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();// ׼����һ��
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

//��ʦ
class Chef implements Runnable {

	Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant r) {
		restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				/**
				 * ��ʦ���ϲͣ���֪ͨ����Ա���͵ȴ� ��ֱ������Ա�ռ���������֪ͨ��ʦ
				 */
				synchronized (this) {
					while (restaurant.meal != null)//�͵ȴ�   while ��ֹ��ʧ�źŵĳ�����ƣ����if ���ܻ��������̲߳������߶�����
						wait();
				}
				if (++count == 10) {
					System.out.println("out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("order up!");
				//�ڷ���Ա����ͬ��
				synchronized (restaurant.waitP) {
					restaurant.meal = new Meal(count);//���ϲ�
					restaurant.waitP.notifyAll();//֪ͨ����Ա
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}

		} catch (InterruptedException e) {
			System.out.println("Chef interrupted");
		}

	}

}
/**
output:
order up!waitperson got Meal 1
order up!waitperson got Meal 2
order up!waitperson got Meal 3
order up!waitperson got Meal 4
order up!waitperson got Meal 5
order up!waitperson got Meal 6
order up!waitperson got Meal 7
order up!waitperson got Meal 8
order up!waitperson got Meal 9
out of food, closing
WaitPerson interrupted
order up!Chef interrupted

*/