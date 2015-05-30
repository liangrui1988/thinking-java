package org.rui.classts;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {

	private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

	private static String[] typeNames = { "org.rui.classts.chilnd.Cat",
			"org.rui.classts.chilnd.Dog", "org.rui.classts.chilnd.Manx",
			"org.rui.classts.chilnd.Mutt", "org.rui.classts.chilnd.Pug",
			"org.rui.classts.chilnd.EgyptianMan"

	};

	//
	private static void loader() {
		for (String name : typeNames) {
			try {
				types.add((Class<? extends Pet>) Class.forName(name));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	static {
		loader();
	}

	@Override
	public List<Class<? extends Pet>> types() {
		return types;
	}

}
