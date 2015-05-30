package org.rui.ExceptionTest;

public class ExtraFeature {
	// -------使用------
	public static void f() throws MyException {
		System.out.println("MyException from f()");
		throw new MyException();
	}

	public static void l() throws MyException {
		System.out.println("MyException from l()");
		throw new MyException("Originated in l()");
	}

	public static void r() throws MyException {
		System.out.println("MyException from r()");
		throw new MyException("originated(起源) in r()");
	}

	// -------main---------
	public static void main(String[] args) {
		try {
			f();
		} catch (MyException e) {
			e.printStackTrace(System.out);
		}
		try {
			l();
		} catch (MyException e) {
			e.printStackTrace(System.err);
		}
		try {
			r();
		} catch (MyException e) {
			e.printStackTrace(System.out);
			System.out.println("getLocalizedMessage: "
					+ e.getLocalizedMessage());
			// 栈轨迹
			for (StackTraceElement ste : e.getStackTrace())
				System.out.println("methodName:" + ste.getMethodName());
		}
	}

}

// 自定义异常---
class MyException extends Exception {
	private int x;

	public MyException() {
	}

	public MyException(String msg) {
		super(msg);
	}

	public MyException(String msg, int x) {
		super(msg);
		this.x = x;
	}

	public int val() {
		return x;
	}

	public String getMessge() {
		return "Detail Message: " + x + "super.getmessage()";
	}
}
