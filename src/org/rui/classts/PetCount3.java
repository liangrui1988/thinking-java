package org.rui.classts;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PetCount3 {

	// List<Class<? extends Pet>> l=LiteralPetCreator.allTypes;
	static LinkedHashMap mm = new LinkedHashMap();

	static {
		for (Class<? extends Pet> l : LiteralPetCreator.allTypes) {
			mm.put(l.getClass().getSimpleName(), l);
		}
	}

	static class PetConter extends LinkedHashMap<Class<? extends Pet>, Integer> {
		public PetConter() {
			super(mm);
		}

		//

		public void count(Pet p) {
			for (java.util.Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
				if (pair.getKey().isInstance(p)) {
					put(pair.getKey(), pair.getValue() + 1);
				}
			}
		}

	}

}
