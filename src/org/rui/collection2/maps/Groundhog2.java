package org.rui.collection2.maps;

/**
 * ���Ҫʹ�����ѵ�����ΪHashMap�Ľ�������ͬʱ����hashCode��equlas ʾ��
 * 
 * @author lenovo
 * 
 */
// ������2
public class Groundhog2 extends Groundhog {
	// public int number;
	public Groundhog2(int n) {
		super(n);
	}

	@Override
	public int hashCode() {
		return number;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Groundhog2
				&& (number == ((Groundhog2) obj).number);
	}

}
