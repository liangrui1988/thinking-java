package org.rui.collection2.maps;

import java.util.Random;

//预测
public class Prediction {
	private static Random rand = new Random(47);
	private boolean shadow = rand.nextDouble() > 0.5;

	@Override
	public String toString() {
		if (shadow)
			return "六周后是冬天";// 六个周的冬天six more weeks of winter
		else
			return "早春";// 早春Early spring!
	}
}
