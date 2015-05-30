package org.rui.thread.critical;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �㻹����ʹ����ʾ��lock�����������ٽ���
 * 
 * ����������CriticalSection.java�ľ��󲿷֣� ���������µ�ʹ����ʽ��lock�����PairManger���͡�
 * expliciPariManger2չʾ�����ʹ��Lock�����������ٽ���������store()�ĵ�����������ٽ������ⲿ
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
