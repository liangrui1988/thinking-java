package org.rui.generics.bounds;

import java.awt.Color;

/**
 * 边界 下面展示了边界基本要素。
 * 
 * @author lenovo
 * 
 */
interface HasColor {
	java.awt.Color getColor();
}

class Colored<T extends HasColor> {
	T item;

	Colored(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}

	java.awt.Color color() {
		return item.getColor();
	}
}

// /////////////////////////////////////////////////
class Dimensio {
	public int x, y, z;
}// 尺码

// !class ColoredDimension<T extends HasColor & Dimensio>
class ColoredDimension<T extends Dimensio & HasColor> {
	T item;

	ColoredDimension(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}

	java.awt.Color color() {
		return item.getColor();
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
interface Weight {
	int weight();
}

class Solid<T extends Dimensio & HasColor & Weight> {
	T item;

	Solid(T item) {
		this.item = item;
	}

	T getItem() {
		return item;
	}

	java.awt.Color color() {
		return item.getColor();
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

	//
	int getWeight() {
		return item.weight();
	}
}

// ////////////////////////////////////////////////////////////////////
class Bounded extends Dimensio implements HasColor, Weight {
	public int weight() {
		return 0;
	}

	public Color getColor() {
		return null;
	}
}

// ////////////////////////////////////////////////////////////////////////
public class BasicBounds {

	public static void main(String[] args) {
		Solid<Bounded> solid = new Solid<Bounded>(new Bounded());
		solid.color();
		solid.getY();
		solid.getWeight();
	}
}
