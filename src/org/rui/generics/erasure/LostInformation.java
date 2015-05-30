package org.rui.generics.erasure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Frob {
}

class Fnorkle {
}

class Quark<Q> {
}

class Particle<POSITION, MOMENTUM> {
};

/**
 * java 泛型是使用擦除来实现的 在泛型代码内部，无法获得任何有关泛型 参数类型信息
 * 
 * @author lenovo
 * 
 */

public class LostInformation {
	public static void main(String[] args) {
		List<Frob> list = new ArrayList<Frob>();
		Map<Frob, Fnorkle> map = new HashMap<Frob, Fnorkle>();
		Quark<Fnorkle> quark = new Quark<Fnorkle>();
		Particle<Long, Double> p = new Particle<Long, Double>();

		System.out
				.println(Arrays.toString(list.getClass().getTypeParameters()));

		System.out.println(Arrays.toString(map.getClass().getTypeParameters()));

		System.out.println(Arrays
				.toString(quark.getClass().getTypeParameters()));

		System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
	}
}
/*
 * output: [E] [K, V] [Q] [POSITION, MOMENTUM]
 */
