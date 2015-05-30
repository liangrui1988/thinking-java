package org.rui.classts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PetCreator {

	public static String f = "ff";
	static Random ran = new Random(47);

	public abstract List<Class<? extends Pet>> types();

	public Pet randomPet() {
		int n = ran.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	// arrays
	public Pet[] createArray(int size) {
		Pet[] result = new Pet[size];
		for (int i = 0; i < size; i++) {
			result[i] = randomPet();
		}

		return result;
	}

	// List
	public ArrayList<Pet> arrayList(int size) {
		ArrayList<Pet> result = new ArrayList<Pet>();
		Collections.addAll(result, createArray(size));
		return result;
	}

	//

	public static void main(String[] args) {
		System.out.println(ran.nextInt(100));
		System.out.println("---");
		System.out.println(PetCreator.f);
	}

}
