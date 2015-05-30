package org.rui.collection2.maps;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * 为速度而散列
 * 
 * 散列的价值在于速度，解决方案之一就是保持健的排序状态，然后使用Collections.binarySearch()查询
 * 数组并不保存健本身。而是通过健对象生成一个数字，将其作为数组的下标。这个数字就是散列码， 注意：这个
 * 实现并不意味着对性能进行了调化，它只是想要展示散列映射表执行的各种操作，
 * 
 * @author lenovo
 * 
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

	static final int SIZE = 997;
	// 你不可能拥有一个物理geerics数组
	// you can't have a physical array of geerics
	// but you can upcast to one 但是你可以向上抛
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];// bucket桶 位

	/***
	 * 对于put方法 hasCode将针对健而被调用，并且其结果被强制转换为正数。 为了使产生的数字适合bucket数组的大小
	 * 取模操作符将按照该数组的尺寸取模， 如果数组的某个位置是null,这表示还没有元素被散列至此，所以，为了保存刚散列到该定位的对象
	 * 需要创建一个新的LinkedList
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
	 * get 按照put相同的方式计算在buckets数组中的索引， 这个很重要，因为这样可以保证两个方法可以计算相同的位置
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
