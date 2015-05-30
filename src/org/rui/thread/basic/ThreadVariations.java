package org.rui.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * 有时通过使用内部类来将线程 代码隐藏在类中 将会很有用，就像下面这样: -----
 * InnerThread1创建了一个扩展自thread的匿名内部类，并且在构造器中创建了这个内部类的一个实例。
 * 如果内部类具有你在其他方法中需要访问的特殊能力 （新方法）
 * 那这么做将会很有意义。但是，在大多数时候，创建线程的原因只是为了使用thread的能力，因此不必创建匿名内部类 并且将其向上转型为thread引用 t。
 * 如果类中的其他方法需要访问t 那它们可以通过thread接口来实现. 并且不需要了解该对象的确切类型。 该示例的第三个和第四个
 * 类重复了前面的两个类,但是它们使用的是runnable接口 而不是Thread类
 * 
 * @author lenovo
 * 
 */
// //////////InnerThread1///////////////////////////////
class InnerThread1 {
	private int countDown = 5;
	private Inner inner;

	public InnerThread1(String name) {
		inner = new Inner(name);
	}

	// 内部类
	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0)
						return;
					sleep(100);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}

		public String toString() {
			return getName() + " : " + countDown;
		}
	}

}

// //////////InnerThread2///////////////////////////////
class InnerThread2 {
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0)
							return;
						sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			}

			public String toString() {
				return getName() + " : " + countDown;
			}
		};
		t.start();
	}
}

// //////////InnerRunnable1///////////////////////////////
class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;

	public InnerRunnable1(String name) {
		inner = new Inner(name);// 实例内部类
	}

	// 内部类实现线程
	private class Inner implements Runnable {
		Thread t;

		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (Exception e) {
				System.out.println("interrupted");
			}
		}

		@Override
		public String toString() {
			return t.getName() + " : " + countDown;
		}
	}
}

// //////////InnerRunnable1///////////////////////////////
class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;

	// 构造器实现线程
	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0) {
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (Exception e) {
					System.out.println("interrupted");
				}
			}

			@Override
			public String toString() {
				return Thread.currentThread().getName() + " : " + countDown;
			}

		});

		t.start();
	}
}

// ///////////////////////////////a separate method to run some code as a
// task///////////////////////////////////////////////////
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}

	public void runTask() {
		if (t == null) {
			t = new Thread(name) {
				@Override
				public void run() {
					try {
						while (true) {
							System.out.println(this);
							if (--countDown == 0)
								return;
							sleep(10);
						}
					} catch (Exception e) {
						System.out.println("interrupted");
					}
				}

				@Override
				public String toString() {
					return getName() + " : " + countDown;
				}
			};
			t.start();
		}
	}
}

// ///////////////////////////////main///////////////////////////////////////////////////
public class ThreadVariations {

	public static void main(String[] args) {
		new InnerThread1("InnerTherad1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
		;
	}

}
