package org.rui.thread;

/**
 * Run 不是单独的线程驱动，它是在main中直接调用的 这里仍旧使用线程，即总是分配给main的那个线程
 * 
 * @author lenovo
 * 
 */
public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}

}
/**
 * output: #0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(liftoff!),
 */
