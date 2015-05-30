//Watercolors.java
package org.rui.generics.set;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 我们可以从输出中看到各种关系运算的结果
 * 
 * @author lenovo
 * 
 */
public class ContainerMethodDifferences {
	static Set<String> methodSet(Class<?> type) {
		Set<String> result = new TreeSet<String>();
		for (Method m : type.getMethods())
			result.add(m.getName());

		return result;
	}

	static void interfaces(Class<?> type) {
		System.out.println("interfaces in:" + type.getSimpleName());
		List<String> result = new ArrayList<String>();
		for (Class<?> c : type.getInterfaces())
			result.add(c.getSimpleName());
		System.out.println("result:" + result);
	}

	static Set<String> object = methodSet(Object.class);
	static {
		object.add("clone");
	}

	// difference
	static void difference(Class<?> superset, Class<?> subset) {
		System.out.println(superset.getSimpleName() + " extends:"
				+ subset.getSimpleName());

		Set<String> comp = Sets.difference(methodSet(superset),
				methodSet(subset));

		comp.removeAll(object);
		System.out.println("object:" + comp);
		interfaces(superset);
	}

	// mian
	public static void main(String[] args) {
		/*
		 * System.out.println("collection:"+ methodSet(Collection.class));
		 * 
		 * interfaces(Collections.class);
		 */
		System.out.println("----Set-----------------------------");
		difference(Set.class, Collections.class);
		System.out.println("----HashSet-----------------------------");
		difference(HashSet.class, Set.class);
		System.out.println("----LinkedHashSet-----------------------------");
		difference(LinkedHashSet.class, HashSet.class);
		System.out.println("----TreeSet-----------------------------");
		difference(TreeSet.class, Set.class);
		System.out.println("-----List----------------------------");
		difference(List.class, Collection.class);
		System.out.println("------ArrayList---------------------------");
		difference(ArrayList.class, List.class);
		System.out.println("------LinkedList---------------------------");
		difference(LinkedList.class, List.class);
		System.out.println("------Queue---------------------------");
		difference(Queue.class, Collection.class);
		System.out.println("------PriorityQueue---------------------------");
		difference(PriorityQueue.class, Queue.class);

		System.out.println("Map:" + methodSet(Map.class));
		System.out.println("------HashMap---------------------------");
		difference(HashMap.class, Map.class);
		System.out.println("------LinkedHashMap---------------------------");
		difference(LinkedHashMap.class, HashMap.class);
		System.out.println("------TreeMap---------------------------");
		difference(TreeMap.class, Map.class);
		// 分类
		System.out.println("------SortedMap---------------------------");
		difference(SortedMap.class, Map.class);
	}

}/*
 * output: ----Set----------------------------- Set extends:Collections
 * object:[toArray, iterator, remove, containsAll, contains, add, size, clear,
 * isEmpty, retainAll, removeAll] interfaces in:Set result:[Collection]
 * ----HashSet----------------------------- HashSet extends:Set object:[]
 * interfaces in:HashSet result:[Set, Cloneable, Serializable]
 * ----LinkedHashSet----------------------------- LinkedHashSet extends:HashSet
 * object:[] interfaces in:LinkedHashSet result:[Set, Cloneable, Serializable]
 * ----TreeSet----------------------------- TreeSet extends:Set object:[lower,
 * last, higher, descendingIterator, subSet, pollLast, comparator, pollFirst,
 * floor, headSet, ceiling, tailSet, first, descendingSet] interfaces in:TreeSet
 * result:[NavigableSet, Cloneable, Serializable]
 * -----List---------------------------- List extends:Collection object:[get,
 * set, listIterator, lastIndexOf, indexOf, subList] interfaces in:List
 * result:[Collection] ------ArrayList--------------------------- ArrayList
 * extends:List object:[trimToSize, ensureCapacity] interfaces in:ArrayList
 * result:[List, RandomAccess, Cloneable, Serializable]
 * ------LinkedList--------------------------- LinkedList extends:List
 * object:[offerFirst, removeFirstOccurrence, pop, peekLast, push,
 * descendingIterator, poll, peek, removeFirst, pollLast, getFirst, offerLast,
 * element, removeLast, offer, pollFirst, addLast, addFirst, peekFirst, getLast,
 * removeLastOccurrence] interfaces in:LinkedList result:[List, Deque,
 * Cloneable, Serializable] ------Queue--------------------------- Queue
 * extends:Collection object:[element, offer, poll, peek] interfaces in:Queue
 * result:[Collection] ------PriorityQueue---------------------------
 * PriorityQueue extends:Queue object:[comparator] interfaces in:PriorityQueue
 * result:[Serializable] Map:[clear, containsKey, containsValue, entrySet,
 * equals, get, hashCode, isEmpty, keySet, put, putAll, remove, size, values]
 * ------HashMap--------------------------- HashMap extends:Map object:[]
 * interfaces in:HashMap result:[Map, Cloneable, Serializable]
 * ------LinkedHashMap--------------------------- LinkedHashMap extends:HashMap
 * object:[] interfaces in:LinkedHashMap result:[Map]
 * ------TreeMap--------------------------- TreeMap extends:Map
 * object:[pollLastEntry, firstKey, floorEntry, ceilingEntry, lowerEntry,
 * lastEntry, subMap, tailMap, navigableKeySet, higherEntry, lowerKey, headMap,
 * firstEntry, comparator, descendingKeySet, descendingMap, pollFirstEntry,
 * lastKey, higherKey, floorKey, ceilingKey] interfaces in:TreeMap
 * result:[NavigableMap, Cloneable, Serializable]
 * ------SortedMap--------------------------- SortedMap extends:Map
 * object:[tailMap, firstKey, headMap, comparator, lastKey, subMap] interfaces
 * in:SortedMap result:[Map]
 */
