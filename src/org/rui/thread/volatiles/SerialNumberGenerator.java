package org.rui.thread.volatiles;

/**
 * ����һЩ���򵥵����飬һ�������������ֵ��࣬ ÿ��nextSerial-Number������ʱ��������������߷���Ψһ��ֵ
 * 
 * @author lenovo
 * 
 */
public class SerialNumberGenerator {

	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++;// ���̰߳�ȫ
	}
}
