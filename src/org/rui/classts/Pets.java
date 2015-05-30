package org.rui.classts;

import java.util.List;

public class Pets {

	public static final PetCreator creator = new LiteralPetCreator();

	public static Pet reandomPet() {
		return creator.randomPet();
	}

	//
	public static Pet[] creatorArray(int size) {
		return creator.createArray(size);
	}

	//
	public static List<Pet> ArrayList(int size) {
		return creator.arrayList(size);
	}

}
