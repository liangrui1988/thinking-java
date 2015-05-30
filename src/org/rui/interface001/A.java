package org.rui.interface001;

public class A {
	interface B {
		void f();
	}

	public class Bimp implements B {
		public void f() {
		}
	}

	public class Bimp2 implements B {
		public void f() {
		}
	}

	interface C {
		public void f();
	}

	public class Cimp2 implements C {
		public void f() {
		}
	}

	private interface D {
		public void f();
	}

	private class Dimp implements D {
		public void f() {
		}
	}

	private class Dimp2 implements D {
		public void f() {
			System.out.println("Dimp2:" + Dimp2.class + new Dimp2());
		}
	}

	public D getD() {
		return new Dimp2();
	}

	private D dRef;

	public void receiveD(D d) {
		dRef = d;
		dRef.f();
	}

	// ======
	interface E {
		interface G {
			void f();
		}

		void g();
	}

	public class NestingInterfaces {
		public class Bimp implements A.B {
			public void f() {
			}
		}
	}

	class Cimp implements A.C {
		public void f() {
		}
	}

	// cannot implements a private inteface except

	class Eimp implements E {
		public void g() {
			// TODO Auto-generated method stub
		}

	}

	class Eimp2 implements E.G {
		public void f() {

		}
	}

	public static void main(String[] args) {
		System.out.println("start------");
		A a = new A();

		// can 't access
		// !A.D ad=a.getD();

		// doesn 't return anything but A.D
		// !A.Dimp2 di2=a.getD();
		// cannot access a member of the interface
		// !a.getD().f();
		// only another A can do anything with getD();
		A a2 = new A();
		a2.receiveD(a.getD());

	}
}
