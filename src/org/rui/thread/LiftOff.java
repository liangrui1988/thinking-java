package org.rui.thread;

/**
 * ��������
 * 
 * @author lenovo
 * 
 */
public class LiftOff implements Runnable {

	protected int countDown = 10;//��ʱ
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "liftOff!") + "),";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			// ���Ƶ�һ���֣����Խ�cpu��һ���߳�ת�Ƹ���һ���߳� ��һ�ֽ���
			// ���������� ���Ѿ�ִ������������������Ҫ�Ĳ����ˣ��˿������л�����������ִ��һ��ʱ��Ĵ��ʱ��
			// Ϊ��ȫ�������Եģ�
			Thread.yield();// �̵߳���
		}

	}

}
