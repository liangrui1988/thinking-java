package org.rui.generics.bounds;

import java.awt.Color;

/**
 * 边界 看上去包含可以通过继承消除沉余。 下面可以看到在继承的每个层次上添加边界的限制
 * 
 * @author lenovo
 * 
 */
class holdItem<T> {
	T item;

	holdItem(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}
}

interface HasColor2 {
	java.awt.Color getColor();
}

class Colored2<T extends HasColor2> extends holdItem<T> {

	// T item;
	Colored2(T item) {
		super(item);
	}

	// T getItem(){return item;}
	java.awt.Color color() {
		return item.getColor();
	}
}

// /////////////////////////////////////////////////
class Dimensio2 {
	public int x, y, z;
}// 尺码

class ColoredDimension2<T extends Dimensio2 & HasColor2> extends Colored2<T> {
	ColoredDimension2(T item) {
		super(item);
	}

	int getX() {
		return item.x;
	}

	int getY() {
		return item.y;
	}

	int getZ() {
		return item.z;
	}
}

// /////////////立方体///////////////////////////////////////////////
interface Weight2 {
	int weight();
}

class Solid2<T extends Dimensio2 & HasColor2 & Weight2> extends
		ColoredDimension2<T> {
	Solid2(T item) {
		super(item);
	}

	int getWeight() {
		return item.weight();
	}
}

// ////////////////////////////////////////////////////////////////////
class Bounded2 extends Dimensio2 implements HasColor2, Weight2 {
	public int weight() {
		return 0;
	}

	public Color getColor() {
		return null;
	}
}

// ////////////////////////////////////////////////////////////////////////
public class InheritBounds {

	public static void main(String[] args) {
		Solid2<Bounded2> solid = new Solid2<Bounded2>(new Bounded2());
		solid.color();
		solid.getY();
		solid.getWeight();
	}
}
