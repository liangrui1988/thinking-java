package org.rui.inner01;

public class DotThis {
	void f() {
		System.out.println("Dot This.f()");
	}

	public class Inner {
		public DotThis outer() {
			return DotThis.this;
		}
	}

	public Inner inner() {
		return new Inner();
	}

	public static void main(String[] argss) {
		DotThis dt = new DotThis();

		DotThis.Inner dti = dt.inner();
		dti.outer().f();

		//
		DotThis.Inner dinner = dt.new Inner();
		dinner.outer().f();

	}

}
