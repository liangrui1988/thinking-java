package org.rui.collection2.maps;

/**
 * 散列与散列码 将土拔鼠对象与预报对象联系起来，
 * 
 * @author lenovo
 * 
 */
// 土拨鼠
public class Groundhog {
	protected int number;

	public Groundhog(int n) {
		number = n;
	}

	@Override
	public String toString() {
		return "Groundhog #" + number;
	}
}
