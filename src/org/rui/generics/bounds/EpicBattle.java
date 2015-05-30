package org.rui.generics.bounds;

import java.awt.Color;
import java.util.List;

/**
 * 边界 多层次的示例
 * 
 * @author lenovo
 * 
 */

interface SuperPower {
}

// 视觉
interface XRayVision extends SuperPower {
	void seeThroughWalls();
}

// 听力
interface SuperHearing extends SuperPower {
	void hearSubtleNoises();
}

// 闻
interface SuperSmell extends SuperPower {
	void trackBysmell();
}

// ////////英雄；男主角////////////////////////////////
class SuperHero<POWER extends SuperPower> {
	POWER power;

	SuperHero(POWER power) {
		this.power = power;
	}

	POWER getPower() {
		return power;
	}
}

// ////////侦查////////////////////////////////
class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {
	SuperSleuth(POWER power) {
		super(power);
	}

	void see() {
		power.seeThroughWalls();
	}
}

// //////////犬//////////////////////////////
class CanineHero<POWER extends SuperHearing & SuperSmell> extends
		SuperHero<POWER> {
	CanineHero(POWER power) {
		super(power);
	}

	void hear() {
		power.hearSubtleNoises();
	}

	void smell() {
		power.trackBysmell();
	}
}

// /////////////////////////////////////
class SuperHearSmell implements SuperHearing, SuperSmell {
	@Override
	public void trackBysmell() {
	}

	@Override
	public void hearSubtleNoises() {
	}
}

// /////////////////////////////////////
class DogBoy extends CanineHero<SuperHearSmell> {
	DogBoy() {
		super(new SuperHearSmell());
	}
}

// ////////////////////////////////////////////////////////////////////////
public class EpicBattle {
	// bounds in generic methods
	static <POWER extends SuperHearing> void userSuperHearing(
			SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
	}

	static <POWER extends SuperHearing & SuperSmell> void sperFind(
			SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
		hero.getPower().trackBysmell();
	}

	public static void main(String[] args) {
		DogBoy dogboy = new DogBoy();
		userSuperHearing(dogboy);
		sperFind(dogboy);
		// YOU can do this
		List<? extends SuperHearing> audioBoys;
		// but you can 't do this:
		// List<? extends SuperHearing & SuperSemll> audioBoys;
	}
}
