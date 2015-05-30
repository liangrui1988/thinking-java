package org.rui.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ���������ֻ���ڲ������߳�ר�е�ĩ�����쳣������������²Żᱻ���á� ϵͳ�����߳�ר�а�
 * �������û�з��֣������߳����Ƿ�����ר�е�uncaughtException()������
 * ���Ҳû�У��ٵ���defaultUncaughtExceptionHandler
 * 
 * @author lenovo
 * 
 */
public class SettingDefaultHandler {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExecptionHandler());

		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}

}
/**
 * output: caught java.lang.RuntimeException
 */
