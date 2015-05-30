package org.rui.generics.erasure;

/**
 * 创建类型实例
 * 
 * @author lenovo
 * 
 */

class ClassAsFactory<T> {
	T x;

	public ClassAsFactory(Class<T> kind) {
		try {
			x = kind.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// //////////////////////////////////
class Employee {
}

public class InstantiateGenericType {
	public static void main(String[] args) {
		ClassAsFactory<Employee> caf = new ClassAsFactory<Employee>(
				Employee.class);
		System.out.println("caf:" + caf.x);

		/*
		 * try { //Integer 没有默认的构造器 ClassAsFactory<Integer> cafInt= new
		 * ClassAsFactory<Integer>(Integer.class); } catch (Exception e) {
		 * System.out.println("ClassAsFactory<Integer> failed"); }
		 */

	}

}
