package org.rui.classts.reflects;

/**
 * 简单动态代理 实现
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
		// 真实对象去实现
		consumer(new RealObject());
		// 代理去实现
		consumer(new SimpleProxy(new RealObject()));

	}

}
