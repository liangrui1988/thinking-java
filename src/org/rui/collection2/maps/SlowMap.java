package org.rui.collection2.maps;

import java.util.*;

/**
 * 理解hashCode 使用散列的目地在于 :你要使用一个对象来查找另一个对象. 不过使用TreeMap或者你自已实现的Map也可以达到此目地 下面的示例用
 * 一对ArrayList实现了一个Map
 * 
 * @author lenovo
 * 
 */
public class SlowMap<K, V> extends AbstractMap<K, V> {
	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();

	public V put(K key, V value) {
		V oldValue = get(key);
		if (!keys.contains(key)) {
			keys.add(key);
			values.add(value);// 添加新的
		} else
			// 它将被用来查找表示它在keys列表中的位置的数值型索引 并且这个数字被用作索引来产生与values列表相关联的值
			values.set(keys.indexOf(key), value);
		return oldValue;
	}

	public V get(Object key) {
		if (!keys.contains(key))
			return null;
		return values.get(keys.indexOf(key));
	}

	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		while (ki.hasNext()) {
			set.add(new MapEntry<K, V>(ki.next(), vi.next()));

		}
		return set;
	}

	public static void main(String[] args) {
		// 慢的
		SlowMap<String, String> map = new SlowMap<String, String>();
		map.put("CAMEROON", "yaounde");
		map.put("A", "aa");
		map.put("B", "bb");
		map.put("C", "cc");
		System.out.println(map);
		System.out.println(map.get("A"));
		System.out.println(map.entrySet());
	}

}
/**
 * output: {CAMEROON=yaounde, C=cc, B=bb, A=aa} aa [CAMEROON=yaounde, C=cc,
 * B=bb, A=aa]
 */
