package org.rui.classts.reflects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理过滤某些方法 的演示
 * 
 * @author lenovo
 * 
 */

class MethodSelector implements InvocationHandler {
	private Object obj;

	public MethodSelector(Object o) {
		this.obj = o;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// System.out.println("proxy:"+proxy.getClass().getSimpleName());
		// 可以拦截特殊处理
		if (method.getName().equals("interesting")) {
			System.out.println("proxy detected the interesting method");
		}
		return method.invoke(obj, args);
	}
}

// interface
interface SomeMethod {
	void boring1();

	void interesting(String arg);

	void boring2();

	void boring3();
}

// ------------------------------
class Implementation implements SomeMethod {
	public void boring1() {
		System.out.println("boring1");
	}

	public void interesting(String arg) {
		System.out.println("interesting:" + arg);
	}

	public void boring2() {
		System.out.println("boring2");
	}

	public void boring3() {
		System.out.println("boring3");

	}

}

//
public class SelectingMethods {
	public static void main(String[] args) {
		// 代理测试
		SomeMethod proxy = (SomeMethod) Proxy.newProxyInstance(
				SomeMethod.class.getClassLoader(), // 定义代理类的类加载器
				new Class[] { SomeMethod.class },// 代理类要实现的接口列表
				new MethodSelector(new Implementation())// 指派方法调用的调用处理程序
				);

		proxy.boring1();
		proxy.boring2();
		proxy.interesting("bonobo");
		proxy.boring3();

	}

}
