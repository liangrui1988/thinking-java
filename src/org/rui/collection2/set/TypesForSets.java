package org.rui.collection2.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

//TypesForSets.java
/**
 * ������ʾ��Ϊ�˳ɹ���ʹ���ض���Set ʵ�����Ͷ����붨��ķ���
 * 
 * ================= Ϊ��֤����Щ��������ĳ���ض���Set�Ǳ�����ģ�����ͬʱ��Ҫ��������ظ���
 * ���Ǵ����������ࡣ����SetTypeֻ�洢һ��int������ͨ��toString()������������ֵ��
 * ��Ϊ������Set�д洢���඼�������equals()����������ڻ�����Ҳ�и÷����� ��ȼ����ǻ������int���͵�i��ֵ��ȷ����.
 * ================ HashType�̳���SetType,���������haseCode�������� ��ȼ����ǻ������int���͵�i��ֵ��ȷ����
 * ======================= TreeTypeʵ����Comparable�ӿڣ�����һ�����������κ���������������У� ����
 * SortedSet(TreeSet����Ψһʵ��)�� ��ô������ʵ������ӿڣ� ע�⣬��compareTo()��,��û��ʹ�� "�������"
 * ����ʽreturn i-i2,��Ϊ����һ���� ���ı�̴��� ��ֻ����i��i2�����޷��ŵ�int������������������������������
 * ��ͨ��ϣ��compareTo()�������Բ�����equals��������һ�µ���Ȼ�������equals()����
 * ĳ���ض��Ƚϲ���true����ôcompareTo()���ڸñȽ�Ӧ�÷���0 ���equals()����
 * ĳ���ض��Ƚϲ���false����ôcompareTo()���ڸñȽ�Ӧ�÷��ط�0ֵ ===================================
 * ��TypesForSets�У�fill() ��test()������ ���÷��Ͷ���ģ�����Ϊ�˱�������ظ��� Ϊ����֤ĳ��set����Ϊ ,
 * test()���ڱ���Set�ϵ���fill()���Σ� �����������������ظ�����fill()�������Խ����κ����͵�Set,
 * �Լ���ͬ����Class������ʹ��Class���������ֲ�����int�����Ĺ�����,Ȼ����ù��������set��
 * ========================== ������п��Կ��� HashSet��ĳ�����ص�˳�򱣴����е�����
 * LinkedHashSet����Ԫ�ز����˳�� TreeSet ���� ���򡣡������ﰴ�� compareTo()��ʽ ά�����ǽ���
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
		// ������Ȳ��� iֵ���
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
				// ͨ������������һ������ʵ����
				type.getConstructor(int.class).newInstance(i));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		// set���10�����󲢷���
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
		// things that don't work ������������
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
