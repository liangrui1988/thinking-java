package org.rui.collection2.maps;

import java.util.Random;

//Ԥ��
public class Prediction {
	private static Random rand = new Random(47);
	private boolean shadow = rand.nextDouble() > 0.5;

	@Override
	public String toString() {
		if (shadow)
			return "���ܺ��Ƕ���";// �����ܵĶ���six more weeks of winter
		else
			return "�紺";// �紺Early spring!
	}
}
