package org.rui.ExceptionTest;

/**
 * 可能在捕获异常之后抛出另一种异常， 有关原来的异常发生点的信息会丢失， 剩下的是与新的抛出点有关的信息
 * 
 * @author lenovo
 * 
 */

// 自定义异常
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
		System.out.println("执行f方法  f()");
		throw new OneException("thrown from f()");//
	}

	public static void main(String[] args) {

		try {
			try {
				f();
			} catch (OneException e) {
				System.out.println("内部异常处理, e.printStackTrace()");
				e.printStackTrace();
				throw new TwoException("抛出异常 inner try");
			}

		} catch (TwoException e) {
			System.out.println("outer try ---外部的异常处理");
			e.printStackTrace(System.out);
		}
	}
}
/**
 * 最后那个异常仅知道自已来自main 而对f()一无所知
 * 
 * output: 执行f方法 f() 内部异常处理, e.printStackTrace()
 * org.rui.ExceptionTest.OneException: thrown from f() at
 * org.rui.ExceptionTest.RethrowNew.f(RethrowNew.java:18) at
 * org.rui.ExceptionTest.RethrowNew.main(RethrowNew.java:26) outer try
 * ---外部的异常处理 org.rui.ExceptionTest.TwoException: 抛出异常 inner try at
 * org.rui.ExceptionTest.RethrowNew.main(RethrowNew.java:31)
 */

// /:~