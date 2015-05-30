package org.rui.generics.erasure;

/**
 * �߽� <T extneds Hasf>����T�����������HasF���ߴ�Hasf���������͡� ������ȷʵ��ˣ���ô�Ϳ��԰�ȫ����obj�ϵ���f()��
 * T������ HasF
 * 
 * @author lenovo
 * 
 * @param <T>
 */
// class Manipulator<T> Error: ���ܵ���obj.f()
class Manipulator<T extends HasF> {
	private T obj;

	public Manipulator(T x) {
		obj = x;
	}

	public void manipulate() {
		obj.f();
	}

	// ��ȡ��������
	public T getGenType() {
		return obj;
	}

}

public class Manipulation<T> {
	public static void main(String[] args) {
		HasF h = new HasF();
		Manipulator<HasF> man = new Manipulator<HasF>(h);
		man.manipulate();
		System.out.println("genType:" + man.getGenType().getClass());
	}
}
/**
 * output: hasf.f genType:class org.rui.generics.erasure.HasF
 */
