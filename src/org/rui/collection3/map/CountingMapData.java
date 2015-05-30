package org.rui.collection3.map;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
/**
 * 这里使用的是LinkedHashSet,而不是定制的Set类，因此享元未完全实现
 * @author lenovo
 *
 */
public class CountingMapData extends AbstractMap<Integer, String>
{

	private int size;
	private static String[] chars = "A B C D E F G".split(" ");

	public CountingMapData(int size)
	{
		if (size < 0)
			this.size = 0;
		this.size = size;
	}

	private static class Entry implements Map.Entry<Integer, String>
	{

		private int index;

		Entry(int index)
		{
			this.index = index;
		}

		@Override
		public Integer getKey()
		{
			return index;
		}

		@Override
		public String getValue()
		{
			return chars[index % chars.length]
					+ Integer.toString(index / chars.length);
		}

		@Override
		public String setValue(String value)
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean equals(Object obj)
		{
			return Integer.valueOf(index).equals(obj);
		}

		@Override
		public int hashCode()
		{

			return Integer.valueOf(index).hashCode();
		}
	}

	@Override
	public Set<java.util.Map.Entry<Integer, String>> entrySet()
	{
		// linedhashset 保留初始化顺序
		Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<Map.Entry<Integer, String>>();
		for (int i = 0; i < size; i++)
		{
			entries.add(new Entry(i));
		}

		return entries;
	}

	public static void main(String[] args)
	{
		System.out.println(new CountingMapData(60));
	}

}
/**
 * {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=A1, 
 * 8=B1, 9=C1, 10=D1, 11=E1, 12=F1, 13=G1, 14=A2, 15=B2, 
 * 16=C2, 17=D2, 18=E2, 19=F2, 20=G2, 21=A3, 22=B3, 23=C3,
 *  24=D3, 25=E3, 26=F3, 27=G3, 28=A4, 29=B4, 30=C4, 31=D4, 
 *  32=E4, 33=F4, 34=G4, 35=A5, 36=B5, 37=C5, 38=D5, 39=E5,
 *   40=F5, 41=G5, 42=A6, 43=B6, 44=C6, 45=D6, 46=E6, 47=F6, 
 *   48=G6, 49=A7, 50=B7, 51=C7, 52=D7, 53=E7, 54=F7, 55=G7, 
 *   56=A8, 57=B8, 58=C8, 59=D8}

 * */
