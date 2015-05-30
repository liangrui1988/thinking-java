package org.rui.classts.reflects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬�������ĳЩ���� ����ʾ
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
		// �����������⴦��
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
		// �������
		SomeMethod proxy = (SomeMethod) Proxy.newProxyInstance(
				SomeMethod.class.getClassLoader(), // �����������������
				new Class[] { SomeMethod.class },// ������Ҫʵ�ֵĽӿ��б�
				new MethodSelector(new Implementation())// ָ�ɷ������õĵ��ô������
				);

		proxy.boring1();
		proxy.boring2();
		proxy.interesting("bonobo");
		proxy.boring3();

	}

}
