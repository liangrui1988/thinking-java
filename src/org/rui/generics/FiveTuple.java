package org.rui.generics;

public class FiveTuple<A, B, C, D, F> extends FourTuple<A, B, C, D> {
	public final F fifth;

	public FiveTuple(A a, B b, C c, D d, F f) {
		super(a, b, c, d);
		fifth = f;
	}

	@Override
	public String toString() {
		return "FiveTuple [fifth=" + fifth + ", fourth=" + fourth + ", third="
				+ third + ", first=" + first + ", second=" + second + "]";
	}

}
