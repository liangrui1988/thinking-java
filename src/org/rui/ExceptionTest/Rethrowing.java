package org.rui.ExceptionTest;

/**
 * 重新抛出异常
 * 
 * 在某些情况下，我们想重新掷出刚才产生过的违例，特别是在用Exception 捕获所有可能的违例时。由于我
 * 们已拥有当前违例的句柄，所以只需简单地重新掷出那个句柄即可。下面是一个例子： catch(Exception e) {
 * System.out.println("一个违例已经产生"); throw e; }
 * 重新“掷”出一个违例导致违例进入更高一级环境的违例控制器中。用于同一个try 块的任何更进一步的 catch
 * 从句仍然会被忽略。此外，与违例对象有关的所有东西都会得到保留，所以用于捕获特定违例类型的 更高一级的控制器可以从那个对象里提取出所有信息。
 * 若只是简单地重新掷出当前违例，我们打印出来的、与printStackTrace()内的那个违例有关的信息会与违
 * 例的起源地对应，而不是与重新掷出它的地点对应。若想安装新的堆栈跟踪信息，可调用
 * fillInStackTrace()，它会返回一个特殊的违例对象。这个违例的创建过程如下：将当前堆栈的信息填充到 原来的违例对象里。下面列出它的形式：
 * 
 * @author lenovo
 * 
 */
public class Rethrowing {
	public static void f() throws Exception {
		System.out.println("f() 方法执行");
		throw new Exception("thrown form f()");
	}

	public static void g() throws Exception {
		try {
			f();
		} catch (Exception e) {
			System.out.println("inside g() ,e...");
			e.printStackTrace(System.out);
			throw e;// 把补获的异常 重新抛出异常
		}
	}

	public static void main(String[] args) {
		try {
			g();
		} catch (Exception e) {
			System.out.println("main 处理异常 ");
			e.printStackTrace(System.out);
		}

	}

}
/**
 * f() 方法执行 inside g() ,e... java.lang.Exception: thrown form f() at
 * org.rui.ExceptionTest.Rethrowing.f(Rethrowing.java:7) at
 * org.rui.ExceptionTest.Rethrowing.g(Rethrowing.java:13) at
 * org.rui.ExceptionTest.Rethrowing.main(Rethrowing.java:25) main 处理异常
 * java.lang.Exception: thrown form f() at
 * org.rui.ExceptionTest.Rethrowing.f(Rethrowing.java:7) at
 * org.rui.ExceptionTest.Rethrowing.g(Rethrowing.java:13) at
 * org.rui.ExceptionTest.Rethrowing.main(Rethrowing.java:25)
 */
