package org.rui.collection2.maps;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Ϊ�ٶȶ�ɢ��
 * 
 * ɢ�еļ�ֵ�����ٶȣ��������֮һ���Ǳ��ֽ�������״̬��Ȼ��ʹ��Collections.binarySearch()��ѯ
 * ���鲢�����潡��������ͨ������������һ�����֣�������Ϊ������±ꡣ������־���ɢ���룬 ע�⣺���
 * ʵ�ֲ�����ζ�Ŷ����ܽ����˵�������ֻ����Ҫչʾɢ��ӳ���ִ�еĸ��ֲ�����
 * 
 * @author lenovo
 * 
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

	static final int SIZE = 997;
	// �㲻����ӵ��һ������geerics����
	// you can't have a physical array of geerics
	// but you can upcast to one ���������������
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];// bucketͰ λ

	/***
	 * ����put���� hasCode����Խ��������ã�����������ǿ��ת��Ϊ������ Ϊ��ʹ�����������ʺ�bucket����Ĵ�С
	 * ȡģ�����������ո�����ĳߴ�ȡģ�� ��������ĳ��λ����null,���ʾ��û��Ԫ�ر�ɢ�����ˣ����ԣ�Ϊ�˱����ɢ�е��ö�λ�Ķ���
	 * ��Ҫ����һ���µ�LinkedList
	 * 
	 */
	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if (buckets[index] == null) {
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while (it.hasNext()) {
			MapEntry<K, V> itM = it.next();
			if (itM.getKey().equals(key)) {
				oldValue = itM.getValue();
				it.set(itM);// replace old with new
				found = true;
				break;
			}
		}
		if (!found) {
			buckets[index].add(pair);
		}
		return oldValue;
	}

	/**
	 * get ����put��ͬ�ķ�ʽ������buckets�����е������� �������Ҫ����Ϊ�������Ա�֤�����������Լ�����ͬ��λ��
	 */

	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if (buckets[index] == null)
			return null;
		for (MapEntry<K, V> ipair : buckets[index])
			if (ipair.getKey().equals(key))
				return ipair.getValue();
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if (bucket == null)
				continue;
			for (MapEntry<K, V> mpair : bucket)
				set.add(mpair);
		}
		return set;
	}

	// //////////////////////////////////////////////////////
	public static void main(String[] args) {
		SimpleHashMap<String, String> simple = new SimpleHashMap<String, String>();
		simple.put("CAMEROON", "yaounde");
		simple.put("A", "aa");
		simple.put("B", "bb");
		simple.put("C", "cc");
		System.out.println(simple);
		System.out.println(simple.get("B"));
		System.out.println(simple.entrySet());
	}
}
/***
 * output: {CAMEROON=yaounde, C=cc, B=bb, A=aa} bb [CAMEROON=yaounde, C=cc,
 * B=bb, A=aa]
 */
