package org.rui.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �����쳣
 * 
 * ������������ǻ��׳�һ���쳣�����쳣�ᴫ������run�������ⲿ�� ����mainչʾ�˵���������ʱ��������������
 * 
 * @author lenovo
 * 
 */
public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();

	}

	public static void main(String[] args) {
		/*
		 * ExecutorService exec=Executors.newCachedThreadPool();
		 * exec.execute(new ExceptionThread());
		 */
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("eeeeeeeeeeeeeeee ����佫��ִ��!");
		}

	}

}
/**
 * output: ����������һ��: Exception in thread "pool-1-thread-1"
 * java.lang.RuntimeException at
 * org.rui.thread.concurrent.ExceptionThread.run(ExceptionThread.java:15) at
 * java
 * .util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1110)
 * at
 * java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:
 * 603) at java.lang.Thread.run(Thread.java:722)
 */
