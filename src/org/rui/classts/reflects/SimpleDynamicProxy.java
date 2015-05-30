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
		// �� ʵ�ʶ���� proxied
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

		// ����
		// ����һ��ָ���ӿڵĴ�����ʵ�����ýӿڿ��Խ���������ָ�ɵ�ָ���ĵ��ô������
		Interface proxy = (Interface) Proxy.newProxyInstance(
				Interface.class.getClassLoader(), // �����������������
				new Class[] { Interface.class },// ������Ҫʵ�ֵĽӿ��б�
				new DynamicProxyHandler(robj)// ָ�ɷ������õĵ��ô������
				);
		consumer(proxy);

	}
}
