package org.rui.generics;

public class Tuple {
	public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}

	public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}

	public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
		return new FourTuple<A, B, C, D>(a, b, c, d);
	}

	public static <A, B, C, D, F> FiveTuple<A, B, C, D, F> tuple(A a, B b, C c,
			D d, F f) {
		return new FiveTuple<A, B, C, D, F>(a, b, c, d, f);
	}

}
