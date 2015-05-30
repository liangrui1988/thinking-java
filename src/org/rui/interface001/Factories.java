package org.rui.interface001;

//接口与工厂

//interface----------
interface Service {
	void method1();

	void method2();
}

interface ServiceFactory {
	Service getService();
}

// imp1------------
class Implementtion1 implements Service {
	public void method1() {
		System.out.println("Implementation1 method1");
	}

	public void method2() {
		System.out.println("Implementation1 method2");
	}
}

class Implementation1Fatory implements ServiceFactory {
	public Service getService() {
		return new Implementtion1();
	}
}

// imp2------------
class Implementtion2 implements Service {
	public void method1() {
		System.out.println("Implementation1 method1");
	}

	public void method2() {
		System.out.println("Implementation1 method2");
	}
}

class Implementation2Fatory implements ServiceFactory {
	public Service getService() {
		return new Implementtion2();
	}
}

// ======================

public class Factories {
	public static void servicesConsumer(ServiceFactory fact) {
		Service s = fact.getService();
		s.method1();
		s.method2();
	}

	public static void main(String[] args) {
		servicesConsumer(new Implementation1Fatory());
		// implementations are completely interchanaeable;
		servicesConsumer(new Implementation2Fatory());
	}

}
