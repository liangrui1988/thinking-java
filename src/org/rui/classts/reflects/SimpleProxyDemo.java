package org.rui.classts.reflects;

/**
 * �򵥶�̬���� ʵ��
 * 
 * @author lenovo
 * 
 */
interface Interface {
	void doSomething();

	void somethingElse(String arg);
}

// class------------------
class RealObject implements Interface {
	public void doSomething() {
		System.out.println("RealObject doSomething");
	}

	public void somethingElse(String arg) {
		System.out.println("RealObject somethingElse:" + arg);
	}
}

// class-SimpleProxy-----------------
class SimpleProxy implements Interface {
	private Interface proxied;

	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}

	// method
	public void doSomething() {
		System.out.println("SimpleProxy doSomething");
		proxied.doSomething();
	}

	public void somethingElse(String arg) {
		System.out.println("SimpleProxy somethingElse:" + arg);
		proxied.somethingElse(arg);
	}
}

public class SimpleProxyDemo {

	public static void consumer(Interface inta) {
		inta.doSomething();
		inta.somethingElse("bonobo");
	}

	// main----------
	public static void main(String[] args) {
		// Interface o=new RealObject();
		// ��ʵ����ȥʵ��
		consumer(new RealObject());
		// ����ȥʵ��
		consumer(new SimpleProxy(new RealObject()));

	}

}
