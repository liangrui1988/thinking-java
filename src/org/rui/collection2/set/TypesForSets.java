package org.rui.collection2.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

//TypesForSets.java
/**
 * 下面演示了为了成功地使用特定的Set 实现类型而必须定义的方法
 * 
 * ================= 为了证明哪些方法对于某种特定的Set是必须需的，并且同时还要避免代码重复，
 * 我们创建了三个类。基类SetType只存储一个int，并且通过toString()方法产生它的值。
 * 因为所有在Set中存储的类都必须具有equals()方法，因此在基类中也有该方法。 其等价性是基于这个int类型的i的值来确定的.
 * ================ HashType继承自SetType,并且添加了haseCode（）方法 其等价性是基于这个int类型的i的值来确定的
 * ======================= TreeType实现了Comparable接口，如是一个对象被用于任何种类的排序容器中， 例如
 * SortedSet(TreeSet是其唯一实现)， 那么它必须实现这个接口， 注意，在compareTo()中,我没有使用 "简洁明了"
 * 的形式return i-i2,因为这是一个常 见的编程错误， 它只有在i和i2都是无符号的int。。。。。。。。。。。。。。。
 * 你通常希望compareTo()方法可以产生与equals（）方法一致的自然排序。如果equals()对于
 * 某个特定比较产生true，那么compareTo()对于该比较应该返回0 如果equals()对于
 * 某个特定比较产生false，那么compareTo()对于该比较应该返回非0值 ===================================
 * 在TypesForSets中，fill() 和test()方法都 是用泛型定义的，这是为了避免代码重复， 为了验证某个set的行为 ,
 * test()会在被测Set上调用fill()三次， 尝试着在其中引入重复对象。fill()方法可以接受任何类型的Set,
 * 以及相同类型Class对象，它使用Class对象来发现并接受int参数的构造器,然后调用构造器添加set中
 * ========================== 从输出中可以看到 HashSet以某种神秘的顺序保存所有的无素
 * LinkedHashSet按照元素插入的顺序 TreeSet 按照 排序。。。这里按照 compareTo()方式 维护的是降序
 * 
 * @author lenovo
 * 
 */
class SetType {
	int i;

	public SetType(int n) {
		i = n;
	}

	public boolean equals(Object o) {
		// 对象相等并且 i值相等
		return o instanceof SetType && (i == ((SetType) o).i);
	}

	public String toString() {
		return Integer.toString(i);
	}

}

// //////////////////////////////////////////////
class HashType extends SetType {
	public HashType(int n) {
		super(n);
	}

	public int hashCode() {
		return i;
	}
}

// //////////////////////////////////////////////
class TreeType extends SetType implements Comparable<TreeType> {
	public TreeType(int n) {
		super(n);
	}

	public int hashCode() {
		return i;
	}

	public int compareTo(TreeType arg) {
		return (arg.i < i ? -1 : (arg.i == i ? 0 : 1));
	}

}

// //////////////////////////////////////////////
public class TypesForSets {
	static <T> Set<T> fill(Set<T> set, Class<T> type) {
		for (int i = 0; i < 10; i++) {
			try {
				set.add(
				// 通过构造器生成一个对象实例，
				type.getConstructor(int.class).newInstance(i));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		// set添加10个对象并返回
		return set;
	}

	static <T> void test(Set<T> set, Class<T> type) {
		fill(set, type);
		fill(set, type);// try to add duplicates
		fill(set, type);
		System.out.println(set);
	}

	public static void main(String[] args) {
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(), HashType.class);
		test(new TreeSet<TreeType>(), TreeType.class);
		// things that don't work 不工作的事情
		test(new HashSet<SetType>(), SetType.class);
		test(new HashSet<TreeType>(), TreeType.class);
		test(new LinkedHashSet<SetType>(), SetType.class);
		test(new LinkedHashSet<TreeType>(), TreeType.class);

		try {
			test(new TreeSet<SetType>(), SetType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			test(new TreeSet<HashType>(), HashType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
/**
 * output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] [9, 8,
 * 7, 6, 5, 4, 3, 2, 1, 0] [8, 2, 6, 3, 9, 7, 4, 6, 0, 5, 0, 3, 5, 0, 4, 1, 4,
 * 2, 9, 6, 2, 1, 8, 3, 1, 7, 5, 9, 8, 7] [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] [0, 1,
 * 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7,
 * 8, 9] [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] java.lang.ClassCastException:
 * org.rui.generics.set.SetType cannot be cast to java.lang.Comparable
 * java.lang.ClassCastException: org.rui.generics.set.HashType cannot be cast to
 * java.lang.Comparable
 */
