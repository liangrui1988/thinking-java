package org.rui.thread.volatiles;

/**
 * 考虑一些更简单的事情，一个产生序列数字的类， 每当nextSerial-Number被调用时，它必须向调用者返回唯一的值
 * 
 * @author lenovo
 * 
 */
public class SerialNumberGenerator {

	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++;// 非线程安全
	}
}
