package org.rui.collection3.test;

public class TestParam {
	public final int size;
	public final int loops;

	public TestParam(int size, int loops) {
		this.size = size;
		this.loops = loops;
	}

	// create an array of testparam from a varargs sequence
	// 创建一个数组的testparam可变参数序列
	public static TestParam[] array(int... values) {
		int size = values.length / 2;
		TestParam[] result = new TestParam[size];
		int n = 0;
		for (int i = 0; i < size; i++) {
			result[i] = new TestParam(values[n++], values[n++]);
		}
		return result;
	}

	// convert a string array to a testparam array
	//字符串数组转换为testparam数组
	public static TestParam[] array(String[] values) {
		int[] vals = new int[values.length];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = Integer.decode(values[i]);
		}
		return array(vals);

	}
	
	public static void main(String[] args) {
		
		Integer s=Integer.decode("0xA");
		System.out.println(s);
		//System.out.println(Integer.decode("40"));
	}
}
