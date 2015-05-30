package org.rui.thread.block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者与消费者
 * 餐馆
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

// 餐
class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}

}

// 服务员
class WaitPerson implements Runnable {
	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		restaurant = r;
	}

	public void run() {

		try {
			while (!Thread.interrupted()) {
				
				synchronized (this) {
					while (restaurant.meal == null)//如果餐为空，服务员等待
						wait();
				}
				System.out.println("waitperson got " + restaurant.meal);
				//在厨师上同步  上完餐 并通知厨师
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();// 准备另一个
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

//厨师
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
				 * 厨师送上餐，并通知服务员，就等待 ，直至服务员收集到订单并通知厨师
				 */
				synchronized (this) {
					while (restaurant.meal != null)//就等待   while 防止错失信号的程序设计（如果if 可能会有其他线程插足拿走订单）
						wait();
				}
				if (++count == 10) {
					System.out.println("out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("order up!");
				//在服务员锁上同步
				synchronized (restaurant.waitP) {
					restaurant.meal = new Meal(count);//送上餐
					restaurant.waitP.notifyAll();//通知服务员
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