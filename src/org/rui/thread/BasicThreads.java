package org.rui.thread;

/**
 * thread ÀàÇý¶¯LiftOff
 * 
 * @author lenovo
 * 
 */
public class BasicThreads {
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("waiting for liftoff()");
	}

}
/**
 * output: waiting for liftoff()
 * #0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(liftoff!),
 */
