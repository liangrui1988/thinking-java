package org.rui.collection2.map;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.rui.generics.anonymity.Generator;

/**
 * map 适配器现在可以使用各种不同的Generator,iterator和常量值的组合来填充Map初始化对象
 * 
 * @author lenovo
 * 
 * @param <K>
 * @param <V>
 */

public class MapData<K, V> extends LinkedHashMap<K, V> {

	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pair<K, V> p = gen.next();
			put(p.key, p.value);
		}
	}

	// /////////////////////////////////////////////////////////
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}

	// ////////A key Generator and a single
	// value/////////////////////////////////////////////////
	public MapData(Generator<K> genK, V genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV);
		}
	}

	// /////////an iterable and a value
	// generator////////////////////////////////////////////////
	public MapData(Iterable<K> genK, Generator<V> genV) {
		for (K k : genK) {
			put(k, genV.next());
		}
	}

	// /////////an iterable and a single
	// value////////////////////////////////////////////////
	public MapData(Iterable<K> genK, V v) {
		for (K k : genK) {
			// System.out.println(k);
			put(k, v);
		}
	}

	// ///////////generic convenience
	// methods///////////////////////////////////////////

	public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen,
			int quantity) {
		return new MapData<K, V>(gen, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> gen, Generator<V> genV,
			int quantity) {
		return new MapData<K, V>(gen, genV, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> gen, V v, int quantity) {
		return new MapData<K, V>(gen, v, quantity);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> k, Generator<V> v) {
		return new MapData<K, V>(k, v);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> k, V v) {
		return new MapData<K, V>(k, v);
	}
}
