package org.rui.thread.concurrency;

/**
 * ������������ͬ�� synchronized ��������һ�������Ͻ���ͬ���Ķ��󣬲��������ķ�ʽ�ǣ�ʹ���䷽�����ڱ����õĵ�ǰ����
 * ��synchronized(this), �� ���ַ�ʽ�У���������synchronized���ϵ�����
 * ��ô�ö���������synchronized�������ٽ����Ͳ��ܱ������ˡ� ��ˣ������this��ͬ��,�ٽ�����Ч���ͻ�ֱ����С��ͬ���ķ�Χ��.
 * 
 * ��ʱ��������һ��������ͬ�������������Ҫ��ô�����ͱ���ȷ�����������������ͬһ��������ͬ���ġ� �����ʾ����ʾ�������������ͬʱ����ͬһ������
 * ֻҪ��������ϵķ������ڲ�ͬ�е�����ͬ���ļ���
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
		// ��һ�� �߳�
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
 * DualSyn.f()ͨ��ͬ���������� ��this��ͬ������g()��һ����syncObject��ͬ����synchronized��
 * ��ˣ�������ͬ���ǻ�������ġ�ͨ����main()�д�������f()��thread����һ���������ʾ�� ��ˣ�������ͬ���ǻ��������.������п��Կ�����
 * ������������ͬʱ���У�����κ�һ��������û����Ϊ����һ��������ͬ����������
 * 
 * 
 * output(sample) g() f() g() f() f() f() f() g() g() g()
 */
