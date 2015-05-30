package org.rui.thread.volatiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Ϊ�˲���SerialNumberGenerator ������Ҫ����ľ��ڴ�ļ���SET�� ��
 * �Է���Ҫ���Ѻܳ���ʱ����̽�����⡣������ʾ��circularSet�����˴洢int��ֵ���ڴ棬
 * ��������������������ʱ��������ֵ���ǳ�ͻ�Ŀ����Լ�С��add ��contains ������ ��synchronized,�Է�ʾ�̳߳�ͻ
 * 
 * @author lenovo
 * 
 */
// ���ô洢�������ǲ��ľ��ڴ�
// reuses storage so we don't run out of memory
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		// ��ʼ��һ��ֵ����������
		// �����ʼȫ��-1��serialNumberGenerator ����ͬ�����ȡserialNumberGenerator.next...����
		// by the serialNumberGenerator;
		for (int i = 0; i < size; i++)
			array[i] = -1;
	}

	// add
	public synchronized void add(int i) {
		array[index] = i;
		// wrap index and write over old elements; ��ָ����д�ھ�Ԫ��;
		index = ++index % len;
		// System.out.println(index+"  :  len :"+len);

	}

	// contains
	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++) {
			System.out.println(array[i] + " == " + val);
			if (array[i] == val)
				return true;

		}
		return false;
	}

}

// /////////////////////////////////////////////
public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();

	static class SerialChecker implements Runnable {
		@Override
		public void run() {
			while (true) {
				// ������
				int serial = SerialNumberGenerator.nextSerialNumber();
				if (serials.contains(serial)) {
					// �ظ�
					System.out.println("Duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}

	// //
	public static void main(String[] args) throws NumberFormatException,
			InterruptedException {
		String[] arg = new String[] { "10000" };

		for (int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
			// stop after n seconds if there 's an argument ֹͣ��n��������һ���۵�
			if (arg.length > 0) {
				TimeUnit.SECONDS.sleep(new Integer(arg[0]));
				System.out.println("û���ظ����");
				System.exit(0);
			}
		}
	}
}

/**
 * ͨ����������������������������㽫������Щ�������ջ�õ��ظ�������������������е�ʱ���㹻���Ļ� Ϊ�˽��������⣬nextSerialNumber
 * ǰ����� ��synchronized�ؽ���
 */
