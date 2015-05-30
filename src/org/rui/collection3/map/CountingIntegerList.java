package org.rui.collection3.map;

import java.util.AbstractList;

/***
 * 它可以具有任意尺寸，并且用Integer数据 有效的 进行了预初始化
 * @author lenovo
 *
 */
public class CountingIntegerList extends AbstractList<Integer>
{
	private int size;
	
	public CountingIntegerList(int size){
		this.size=size<0?0:size;
	}
	

	@Override
	public Integer get(int index)
	{
		return Integer.valueOf(index);
	}

	@Override
	public int size()
	{
		return size;
	}
	
	public static void main(String[] args)
	{
		System.out.println(new CountingIntegerList(30));
	}

}
/**output:
 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
 *  16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]

 * */
