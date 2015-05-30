//WatercolorSets.java
package org.rui.generics.set;

import java.util.EnumSet;
import java.util.Set;

/**
 * EnumSet Ê¹ÓÃÊ¾Àý
 * 
 * @author lenovo
 * 
 */
public class WatercolorSets {

	public static void main(String[] args) {
		Set<Watercolors> set1 = EnumSet.range(Watercolors.LEMON_TYLLOW,
				Watercolors.ORANGE);

		Set<Watercolors> set2 = EnumSet.range(Watercolors.ZINC,
				Watercolors.MEDIUM_YELLOW);
		System.out.println(set1);
		System.out.println(set2);
		// union
		System.out.println(" union 1 2:" + Sets.union(set1, set2));
		// intersection
		System.out.println("intersection:" + Sets.intersection(set1, set2));
		// difference ²îÒì
		System.out.println("difference1:" + Sets.difference(set1, set2));
		System.out.println("difference2:" + Sets.difference(set2, set1));

		// complement²¹×ã
		System.out.println("complement:" + Sets.complement(set1, set2));

	}

}
