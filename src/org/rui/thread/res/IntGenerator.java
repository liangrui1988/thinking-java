package org.rui.thread.res;

/**
 * ����ȷ�ķ��� ��Դ
 * 
 * @author lenovo
 * 
 */
public abstract class IntGenerator {

	private volatile boolean canceled = false;// ȡ����

	public abstract int next();

	// �������ֱ�ȡ��
	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}

}
