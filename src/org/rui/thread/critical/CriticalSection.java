package org.rui.thread.critical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �ٽ���
 * 
 * 
 * 
 * Pair�����̰߳�ȫ�ģ���Ϊ����Լ����������Ȼ������ģ� ��Ҫ��������ά������ͬ��ֵ�� ���⣬�籾��ǰ�������������Ӳ��������̰߳�ȫ�ģ�
 * ������Ϊû���κη��������Ϊsynchronized�����Բ��ܱ�֤һ��pair�����ڶ��̳߳����в����ƻ�.
 * 
 * @author lenovo
 * 
 */
// org.rui.thread.critical.CriticalSection.java

class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public String toString() {
		return "x: " + x + " , y:" + y;
	}

	// ///////////////PariValuesNotEqualException
	public class PariValuesNotEqualException extends RuntimeException {
		public PariValuesNotEqualException() {
			super("Pair values not equal:" + Pair.this);
		}
	}

	// �����,��������������ƽ�ȵ�
	// Arbitrary invariant --both variables must be equal:
	public void checkState() {
		if (x != y) {
			throw new PariValuesNotEqualException();
		}
	}
}// Pair end

// ����һ����һ���̰߳�ȫ����
// protect a pair inside a thread -safe class:
abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections
			.synchronizedList(new ArrayList<Pair>());

	public synchronized Pair getPair() {
		// ���Ƶ�ԭʼ��ȫ:
		// Make a copy to keep the original safe:
		return new Pair(p.getX(), p.getY());
	}

	// ��������һ����ʱ�Ĳ���
	// Assume this is a time consuming operation
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//
	public abstract void increment();
}

// ����ͬ������
// synchronize the entire method
class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

// use a critical section: ʹ���ٽ���
class PairManager2 extends PairManager {
	public void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

// /������
class PairManipulator implements Runnable {
	private PairManager pm;

	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		while (true)
			pm.increment();
	}

	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter= "
				+ pm.checkCounter.get();
	}
}

// //PairChecker
class PairChecker implements Runnable {
	private PairManager pm;

	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}
}

// ///////////////////////////////////////////////////////
public class CriticalSection {
	// �������ֲ�ͬ�ķ���
	// test the two defferent approaches
	static void testApproaches(PairManager pman1, PairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator pm1 = new PairManipulator(pman1);
		PairManipulator pm2 = new PairManipulator(pman2);

		PairChecker pcheck1 = new PairChecker(pman1);
		PairChecker pcheck2 = new PairChecker(pman2);

		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);

		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}

		System.out.println("pm1:" + pm1 + "\npm2:" + pm2);
		System.exit(0);
	}

	public static void main(String[] args) {
		PairManager pman1 = new PairManager1(), pman2 = new PairManager2();

		testApproaches(pman1, pman2);
	}

}
/**
 * output:(sample) pm1:Pair: x: 8 , y:8 checkCounter= 674247 pm2:Pair: x: 8 ,
 * y:8 checkCounter= 5043736
 */