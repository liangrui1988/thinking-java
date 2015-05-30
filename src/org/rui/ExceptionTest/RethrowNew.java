package org.rui.ExceptionTest;

/**
 * �����ڲ����쳣֮���׳���һ���쳣�� �й�ԭ�����쳣���������Ϣ�ᶪʧ�� ʣ�µ������µ��׳����йص���Ϣ
 * 
 * @author lenovo
 * 
 */

// �Զ����쳣
class OneException extends Exception {
	OneException(String s) {
		super(s);
	}
}

class TwoException extends Exception {
	TwoException(String s) {
		super(s);
	}
}

// //////
public class RethrowNew {
	public static void f() throws OneException {
		System.out.println("ִ��f����  f()");
		throw new OneException("thrown from f()");//
	}

	public static void main(String[] args) {

		try {
			try {
				f();
			} catch (OneException e) {
				System.out.println("�ڲ��쳣����, e.printStackTrace()");
				e.printStackTrace();
				throw new TwoException("�׳��쳣 inner try");
			}

		} catch (TwoException e) {
			System.out.println("outer try ---�ⲿ���쳣����");
			e.printStackTrace(System.out);
		}
	}
}
/**
 * ����Ǹ��쳣��֪����������main ����f()һ����֪
 * 
 * output: ִ��f���� f() �ڲ��쳣����, e.printStackTrace()
 * org.rui.ExceptionTest.OneException: thrown from f() at
 * org.rui.ExceptionTest.RethrowNew.f(RethrowNew.java:18) at
 * org.rui.ExceptionTest.RethrowNew.main(RethrowNew.java:26) outer try
 * ---�ⲿ���쳣���� org.rui.ExceptionTest.TwoException: �׳��쳣 inner try at
 * org.rui.ExceptionTest.RethrowNew.main(RethrowNew.java:31)
 */

// /:~