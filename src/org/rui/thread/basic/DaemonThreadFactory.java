package org.rui.thread.basic;

import java.util.concurrent.ThreadFactory;

/**
 * ͨ����д���Ƶ�threadFactory���Զ�����Executor�������̵߳����� ����̨�����ȼ������ƣ�
 * 
 * @author lenovo
 * 
 */
public class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}

}
