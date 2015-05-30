package org.rui.collection2.maps;

/**
 * 如果要使用自已的类作为HashMap的健，必须同时重载hashCode和equlas 示列
 * 
 * @author lenovo
 * 
 */
// 土拨鼠2
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
