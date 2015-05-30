package org.rui.classts;

import java.util.HashMap;
import org.rui.classts.chilnd.*;

public class PetCount {
	static class Petcounter extends HashMap<String, Integer> {
		public void count(String type) {
			Integer quantity = get(type);
			if (quantity == null)
				put(type, 1);
			else
				put(type, quantity + 1);
		}
	}

	//
	public static void countPets(PetCreator creator) {
		Petcounter counter = new Petcounter();
		for (Pet p : creator.createArray(20)) {
			System.out.println(p.getClass().getSimpleName() + "====");
			if (p instanceof Pet)
				counter.count("Pet");

			if (p instanceof Dog)
				counter.count("Dog");

			if (p instanceof Cat)
				counter.count("Cat");

			if (p instanceof Mutt)
				counter.count("Mutt");

			if (p instanceof Pug)
				counter.count("Pug");

			if (p instanceof EgyptianMan)
				counter.count("EgyptianMan.java");

			if (p instanceof Manx)
				counter.count("Manx.java");

			System.out.println(counter);
		}
	}

	public static void main(String[] args) {
		countPets(new ForNameCreator());
	}

}
