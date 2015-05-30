package org.rui.generics.erasure;

/**
 * �����Ĳ���
 * 
 * ��������ȷ�����ͱ�ǩ����ƥ�䷺�Ͳ���
 * 
 * @author lenovo
 * 
 */

class Building {
}

class House extends Building {
}

public class ClassTypeCapture<T> {

	Class<T> kind;

	public ClassTypeCapture(Class<T> kind) {
		this.kind = kind;
	}

	public boolean f(Object obj) {
		System.out.println(kind + "   isInstance    " + obj);
		return kind.isInstance(obj);
	}

	public static void main(String[] args) {
		ClassTypeCapture<Building> ctc = new ClassTypeCapture<Building>(
				Building.class);
		System.out.println(ctc.f(new Building()));
		// ���� ���ӶԱ�
		System.out.println(ctc.f(new House()));

		ClassTypeCapture<House> ctc2 = new ClassTypeCapture<House>(House.class);
		// House is building �ӶԱȸ�=false
		System.out.println(ctc2.f(new Building()));
		System.out.println(ctc2.f(new House()));

	}

}
/**
 * output: true true false true
 */
