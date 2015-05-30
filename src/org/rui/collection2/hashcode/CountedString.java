package org.rui.collection2.hashcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 覆盖hashcode
 * 
 * 在Effective java programming language guide ....这本书 为怎样写出一份像样的hashcode给出了基本指导
 * 1，int变量result 赋予某个非零值 常 量， 2，为对象内每个有意义的域f（即每个可以做equlas操作的域） 计算一个int散列码c
 * -----------------------------------------
 * ----------------------------------------- 域类型 计算
 * -----------------------------------------
 * ----------------------------------------- boolean c=(f?0:1)
 * byte,char,shor或int c=(int)f long c=(int)(f^(f>>>32)) float
 * c=Float.floatToIntBits(f); 根据 IEEE 754 浮点“单一格式”位布局，返回指定浮点值的表示形式 double
 * c=Double.doubleToLongBits(f); Object 其equals调用这个域的equlas c=f.hashCode(); 数组
 * 对每个元素应用上述规则
 * 
 * 3,合并计算得到的散列码 4，返加result 5检查hashcode最后生的结果。确保相同的地象有相同的散列码
 * -----------------------------------------
 * -----------------------------------------
 * 
 * 
 * CountedString是由一个String和id组成，此id代表包含相同String的CountedString对象的编号
 * 所有的String被存储在ArrayList中，在构造器中通过迭代遍历此ArrayList完成对Id的计算
 * 
 * hashcode and equals都是基于CountedString的两 个域来生成的结果，如果它们只是基于String或只基于Id
 * 不同的对象就可能产生相同的值
 * 
 * 在Main中使用了相同的String，创建了多个CountedString对象，这说明，虽然String相同 ，
 * 但由于Id不同，所以使得它们的散列码并不相同，
 * 
 * @author lenovo
 * 
 */
public class CountedString {
	private static List<String> created = new ArrayList<String>();
	private String s;
	private int id = 0;

	public CountedString(String str) {
		s = str;
		created.add(s);
		// id is the total numbe of instances
		// of this string in by CountedString
		for (String s2 : created) {
			if (s2.equals(s))
				id++;
		}
	}

	public String toString() {
		return "String:" + s + "  id:" + id + " hashCode:" + hashCode();
	}

	public int hashCode() {
		// the very simple approach
		// return s.hashCode()*id;
		// using joshua bloch's recipe//使用joshua bloch的配方
		int result = 17;
		// 合并计算得到散列码
		result = 37 * result + s.hashCode();
		result = 37 * result + id;
		return result;
	}

	public boolean equals(Object o) {
		return o instanceof CountedString && s.equals(((CountedString) o).s)
				&& id == ((CountedString) o).id;

	}

	public static void main(String[] args) {
		Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
		CountedString[] cs = new CountedString[5];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);// autobox int->Integer
		}
		System.out.println(map);
		for (CountedString cstring : cs) {
			System.out.println("Looking up" + cstring);
			System.out.println("map.get(cstring):" + map.get(cstring));
		}
	}

}
