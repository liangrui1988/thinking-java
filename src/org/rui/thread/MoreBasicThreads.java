package org.rui.thread;

/**
 * 更多的线程 驱动LiftOff
 * 
 * @author lenovo
 * 
 */
public class MoreBasicThreads {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new LiftOff());
			t.start();
			System.out.println("waiting for liftoff()");
		}

	}

}
/**
 * output: waiting for liftoff()
 * #0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(liftoff!),waiting
 * for liftoff() waiting for liftoff()
 * #2(9),#2(8),#2(7),#2(6),#2(5),#2(4),#2(3),#2(2),#2(1),#2(liftoff!),waiting
 * for liftoff() waiting for liftoff()
 * #4(9),#1(9),#3(9),#4(8),#3(8),#4(7),#3(7),
 * #4(6),#3(6),#4(5),#3(5),#4(4),#3(4),
 * #4(3),#3(3),#4(2),#3(2),#4(1),#3(1),#4(liftoff
 * !),#3(liftoff!),#1(8),#1(7),#1(6),#1(5),#1(4),#1(3),#1(2),#1(1),#1(liftoff!),
 */
