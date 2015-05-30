package org.rui.thread.critical;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 你还可以使用显示的lock对象来创建临界区
 * 
 * 这里利用了CriticalSection.java的绝大部分， 并创建了新的使用显式的lock对象的PairManger类型。
 * expliciPariManger2展示了如何使用Lock对象来创建临界区，而对store()的调用则在这个临界区的外部
 * 
 * @author lenovo
 * 
 */

// ////////////////////////

class ExplicitPairManager1 extends PairManager {

	private Lock lock = new ReentrantLock();

	@Override
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}

}

// use a critical section
class ExplicitPairManager2 extends PairManager {
	private Lock lock = new ReentrantLock();

	@Override
	public void increment() {
		Pair temp = null;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}

		store(temp);
	}
}

// ///////////////
public class ExplicitCriticalSection {

	public static void main(String[] args) {
		PairManager pman1 = new PairManager1(), pman2 = new PairManager2();

		CriticalSection.testApproaches(pman1, pman2);
	}
}
/**
 * output: pm1:Pair: x: 10 , y:10 checkCounter= 195142 pm2:Pair: x: 11 , y:11
 * checkCounter= 4129459
 */
