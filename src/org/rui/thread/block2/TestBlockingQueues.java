package org.rui.thread.block2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.rui.thread.LiftOff;

/**
 * ������-�����������
 * 
 * @author lenovo
 * 
 */

class LiftOffRunner implements Runnable {

	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> b) {
		rockets = b;
	}

	//���һ�����񵽶���
	public void add(LiftOff lo) {
		//��ָ��Ԫ�ز���˶����У�������������Ҳ���Υ���������ƣ���
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				// ��ȡ���Ƴ��˶��е�ͷ������Ԫ�ر�ÿ���֮ǰһֱ�ȴ�������б�Ҫ����
				LiftOff rocket = rockets.take();
				rocket.run();
			}

		} catch (InterruptedException e) {
			System.out.println("�ж��˳�");
		}
		System.out.println("x exiting liftOffRunner");

	}
}

public class TestBlockingQueues {
	
	static void getkey() {
		try {
			// compensate for windows/linux difference in the
			// �س��������Ľ��
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void getkey(String message) {
		System.out.println(message);
		getkey();
	}

	static void tets(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		
		//����һ���߳�
		Thread t = new Thread(runner);
		t.start();
		
		for (int i = 0; i < 5; i++) {
			//��������LiftOffRunner������
			runner.add(new LiftOff(5));
		}
		
		//�������̨
		getkey("press 'enter' (" + msg + ")");
		t.interrupt();
		System.out.println(" ���� " + msg + "test");

	}

	public static void main(String[] args) {
		tets("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());// unlimited																		// size
		tets("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));// fied																		// size
		tets("SynchronousQueue", new SynchronousQueue<LiftOff>());// size of 1

	}

}
