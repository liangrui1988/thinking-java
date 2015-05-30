package org.rui.classts.reflects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//DynamicProxyHandler
class DynamicProxyHandler implements InvocationHandler {

	private Object proxied;

	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("proxy:==" + proxy.getClass() + "\nmethod:=="
				+ method + " \nargs:" + args);

		if (args != null)
			for (Object o : args)
				System.out.println("arg:" + o);
		// 把 实际对象给 proxied
		return method.invoke(proxied, args);

	}
}

class SimpleDynamicProxy {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("bonobo==");
	}

	public static void main(String[] args) {
		RealObject robj = new RealObject();
		consumer(robj);

		// 代理
		// 返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。
		Interface proxy = (Interface) Proxy.newProxyInstance(
				Interface.class.getClassLoader(), // 定义代理类的类加载器
				new Class[] { Interface.class },// 代理类要实现的接口列表
				new DynamicProxyHandler(robj)// 指派方法调用的调用处理程序
				);
		consumer(proxy);

	}
}
