package org.rui.thread.basic;

import java.util.concurrent.TimeUnit;

/**
 * ��͸���ʶ����̨�����ڲ�ִ��finally�Ӿ������¾ͻ���ֹ��run()����
 * 
 * @author lenovo
 * 
 */

class ADeamon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("starting adaemon");
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("�����˳� ���� InterruptedException");
		} finally {
			// ��Ӧ�� run?
			System.out.println("this should always run?");
		}

	}

}

public class DaemonDontRunFinally {
	// �㽫����finally�Ӿ䲻��ִ�� ���ע��t.setDaemon(true); �ͻῴ��
	public static void main(String[] args) {
		Thread t = new Thread(new ADeamon());
		t.setDaemon(true);
		t.start();
		// һ��main�˳� jvm�ͻ������رպ�̨�߳�
	}
}
/**
 * output: starting adaemon
 */
