package org.rui.collection3.map;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/***
 * 集合
 * 使用Abstract类  
 * 
 * @author lenovo
 *
 */
public class Countries
{
	public static final String[][] DATA =
	{
			// Africa
			{ "A", "a" },
			{ "B", "b" },
			{ "C", "c" },
			{ "D", "d" },
			{ "E", "e" },
			{ "F", "f" },
			{ "G", "g" },
			{ "H", "h" },
			{ "I", "i" },
			{ "J", "j" },
			{ "K", "k" },
			{ "L", "l" },
			{ "M", "m" },
			{ "N", "n" },
			{ "O", "o" },
			{ "P", "p" },
			{ "Q", "q" } };

	private static class FlyweightMap extends AbstractMap<String, String>
	{

		private static class Entry implements Map.Entry<String, String>
		{
			int index;

			Entry(int index)
			{
				this.index = index;
			}

			@Override
			public String getKey()
			{
				return DATA[index][0];
			}

			@Override
			public String getValue()
			{
				return DATA[index][1];
			}

			@Override
			public String setValue(String value)
			{
				throw new UnsupportedOperationException();
			}

			public int hashCode()
			{
				return index;
			}

			public boolean equals(Object o)
			{
				return DATA[index][0].equals(o);
			}
		}

		// use abstractset by implementing size & iterator 通过实现尺寸& iterato使用套抽象
		static class EnterSet extends AbstractSet<Map.Entry<String, String>>
		{
			private int size;

			EnterSet(int size) // 享元部分 只存储它的索引 而不是实际的健和值
			{
				if (size < 0)
				{
					this.size = 0;
				} else if (size > DATA.length)
				{
					this.size = DATA.length;
				} else
				{
					this.size = size;
				}
			}

			@Override
			public int size()
			{
				return size;
			}

			private class Iter implements Iterator<Map.Entry<String, String>>
			{
				// 每个迭代器只有一个条目对象
				private Entry entry = new Entry(-1);

				@Override
				public boolean hasNext()
				{
					return entry.index < size - 1;
				}

				@Override
				public java.util.Map.Entry<String, String> next()
				{
					entry.index++;
					return entry;
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();

				}
			}

			@Override
			public Iterator<java.util.Map.Entry<String, String>> iterator()
			{

				return new Iter();
			}

		}

		//
		public static Set<java.util.Map.Entry<String, String>> entries = new EnterSet(
				DATA.length);

		@Override
		public Set<java.util.Map.Entry<String, String>> entrySet()
		{

			return entries;
		}

	}

	// create a partial map of size countries 创建一个部分大小国家的地图
	static Map<String, String> select(final int size)
	{
		return new FlyweightMap()
		{
			@Override
			public Set<java.util.Map.Entry<String, String>> entrySet()
			{

				return new FlyweightMap.EnterSet(size);
			}
		};
	}

	static Map<String, String> map = new FlyweightMap();

	public static Map<String, String> capitals()
	{
		return map;
	}

	public static Map<String, String> capitals(int size)
	{
		return select(size);// a partial map
	}

	static List<String> names = new ArrayList<String>(map.keySet());

	// all the names;
	public static List<String> names()
	{
		return names;
	}

	// A partial list
	public static List<String> names(int size)
	{
		return new ArrayList<String>(select(size).keySet());
	}

	/********************main******************************/
	public static void main(String[] args)
	{
		System.out.println(capitals(10));
		System.out.println(names(10));
		System.out.println(new HashMap<String, String>(capitals(3)));
		System.out.println(new TreeMap<String, String>(capitals(3)));
		System.out.println(new LinkedHashMap<String, String>(capitals(3)));
		System.out.println(new Hashtable<String, String>(capitals(3)));

		System.out.println(new HashSet<String>(names(6)));
		System.out.println(new TreeSet<String>(capitals(6).keySet()));
		System.out.println(new LinkedHashSet<String>(names(6)));
		// System.out.println(new Hashtable<String, String>(capitals(3)));

		System.out.println(new ArrayList<String>(names(6)));
		System.out.println(new LinkedList<String>(names(6)));

		System.out.println(capitals().get("F"));

	}

}
/**
output:
{A=a, B=b, C=c, D=d, E=e, F=f, G=g, H=h, I=i, J=j}
[A, B, C, D, E, F, G, H, I, J]
{A=a, B=b, C=c}
{A=a, B=b, C=c}
{A=a, B=b, C=c}
{A=a, C=c, B=b}
[D, E, F, A, B, C]
[A, B, C, D, E, F]
[A, B, C, D, E, F]
[A, B, C, D, E, F]
[A, B, C, D, E, F]
f
*/