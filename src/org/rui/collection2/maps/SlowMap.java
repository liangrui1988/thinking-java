package org.rui.collection2.maps;

import java.util.*;

/**
 * ���hashCode ʹ��ɢ�е�Ŀ������ :��Ҫʹ��һ��������������һ������. ����ʹ��TreeMap����������ʵ�ֵ�MapҲ���Դﵽ��Ŀ�� �����ʾ����
 * һ��ArrayListʵ����һ��Map
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
			values.add(value);// ����µ�
		} else
			// �������������ұ�ʾ����keys�б��е�λ�õ���ֵ������ ����������ֱ�����������������values�б��������ֵ
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
		// ����
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
