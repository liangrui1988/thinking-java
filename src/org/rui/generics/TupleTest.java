package org.rui.generics;

//import org.rui.generics.Tuple.*;

public class TupleTest {

	static TwoTuple<String, Integer> f() {
		return Tuple.tuple("hi", 47);
	}

	static TwoTuple f2() {
		return Tuple.tuple("hi", 47);
	}

	//
	static ThreeTuple<Bean1, String, Integer> g() {
		return Tuple.tuple(new Bean1(), "hi", 47);
	}

	//
	public static FourTuple<Bean2, Bean1, String, Integer> h() {
		return Tuple.tuple(new Bean2(), new Bean1(), "hi", 47);
	}

	//
	static FiveTuple<Bean2, Bean1, String, Integer, Double> k() {
		return Tuple.tuple(new Bean2(), new Bean1(), "hi", 47, 50.8);
	}

	// //////////////////////////////////////////////////////

	public static void main(String[] args) {
		TwoTuple<String, Integer> ttso = f();
		System.out.println(ttso);
		System.out.println(f2());
		System.out.println(g());
		System.out.println(h());
		System.out.println(k());

	}

}
