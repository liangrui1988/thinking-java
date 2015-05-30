package org.rui.thread.concurrent;

/**
 * ��������Ӧ���û����� һ����ע�����㣬���Բ��ܶ�ȡ����̨���룬��һ����������������ﵥ������, ��ʱ�Ϳ����ڽ��������ͬʱ��������̨����
 * 
 * @author lenovo
 * 
 */

// �޷�Ӧ��
class UnresponsiveUI {
	private volatile double d = 1;

	public UnresponsiveUI() throws Exception {
		while (d > 0)
			d = d + (Math.PI + Math.E);
		System.in.read();// ��Զ����������
	}

}

public class ResponsiveUI extends Thread {
	private volatile static double d = 1;

	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		// ����ѭ������Ȼ���� �����ܵ����ȡ����̨�������һ�У�����������ƭ�ˣ�����while������ʹ�ó����ܵ����ȡ����̨�������һ�У���
		// ����ѽ���UnresponsiveUI����һ�е�ע�ͽ���������г�����ôҪ��ֹ���Ļ�����ֻ��ɱ���������
		while (true)
			d = d + (Math.PI + Math.E) / d;
	}

	// //////////////////
	public static void main(String[] args) throws Exception {
		// 4new UnresponsiveUI();// ����ɱ������߳�
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);// ��ʾ�ϴ�����
		/**
		 * Ҫ���ó�������Ӧ���͵ðѼ���������run�����У������������ó�����������ĳ���
		 * ���㰴�¡��س�������ʱ�򣬿��Կ�������ȷʵ����Ϊ��̨�������У�ͬʱ���ڵȴ��û����롣
		 */
	}

}
/**
 * input: hello output: ����ͣ��ʱ������� 68130.17629894095
 */
