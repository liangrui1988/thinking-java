package org.rui.classts;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.rui.classts.chilnd.Cat;
import org.rui.classts.chilnd.Dog;
import org.rui.classts.chilnd.EgyptianMan;
import org.rui.classts.chilnd.Manx;
import org.rui.classts.chilnd.Mutt;
import org.rui.classts.chilnd.Pug;

public class LiteralPetCreator extends PetCreator {

	@Override
	public List<Class<? extends Pet>> types() {

		return types;
	}

	public static final List<Class<? extends Pet>> allTypes = Collections
			.unmodifiableList(Arrays.asList(Pet.class, Dog.class, Pug.class,
					Cat.class, Manx.class, Mutt.class, EgyptianMan.class));

	//
	private static final List<Class<? extends Pet>> types = allTypes.subList(
			allTypes.indexOf(Pug.class), allTypes.size());

	//
	public static void main(String[] args) {
		System.out.println(types.size() + ": " + types);
	}

}
