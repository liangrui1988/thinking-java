package org.rui.classts;

class Building {
}

class House extends Building {
}

public class ClassCasts {

	public static void main(String[] args) {
		Building b = new House();
		Class<House> clszz = House.class;
		House ho = clszz.cast(b);
		// ho=(House) b;
	}

}
