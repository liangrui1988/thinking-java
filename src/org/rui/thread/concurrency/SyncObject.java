package org.rui.thread.concurrency;

/**
 * 在其他对象上同步 synchronized 块必须给定一个在其上进行同步的对象，并且最合理的方式是，使用其方法正在被调用的当前对象
 * ：synchronized(this), 在 这种方式中，如果获得了synchronized块上的锁，
 * 那么该对象其他的synchronized方法和临界区就不能被调用了。 因此，如果在this上同步,临界区的效果就会直接缩小在同步的范围内.
 * 
 * 有时必须在另一个对象上同步，但是如果你要这么做，就必须确保所有相关任务都是在同一个对象上同步的。 下面的示例演示了两个任务可以同时进入同一个对象，
 * 只要这个对象上的方法是在不同中的锁上同步的即可
 * 
 * @author lenovo
 * 
 */
class DualSynch {
	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}

}

// //////////////////////////////////
public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		// 另一个 线程
		new Thread() {
			public void run() {
				ds.f();
			};
		}.start();

		// g()
		ds.g();
	}

}
/**
 * DualSyn.f()通过同步整个方法 在this上同步，面g()有一个在syncObject上同步的synchronized块
 * 因此，这两个同步是互相独立的。通过在main()中创建调用f()的thread对这一点进行了演示， 因此，这两个同步是互相独立的.从输出中可以看到，
 * 这两个方法在同时运行，因此任何一个方法都没有因为对另一个方法的同步而被阻塞
 * 
 * 
 * output(sample) g() f() g() f() f() f() f() g() g() g()
 */
