package org.rui.collection2.hashcode;

import java.util.*;

import org.rui.classts.Pet;
import org.rui.classts.chilnd.*;

/**
 * 
 * 下面的示例说明了它如何工作的；
 * 
 * 由于所有的寵物都有名字，因此它們首先按照類型排序，然后在同類型中按照 名字排序
 * 為新類編寫正確的hashCode和equals很需要技巧，Apache的jakarta commons項目中有許多工具可以人幫助你完成此事
 * 
 * @author lenovo
 * 
 */

public class IndvidualTest {

	public static void main(String[] args) {
		// Set<Individual> pets=new TreeSet<Individual>();
		Pet p = new Cat("猫");
		Pet p1 = new Dog("狗");
		Pet p2 = new EgyptianMan("EgyptianMan");
		Pet p3 = new Manx("马恩岛猫");
		Pet p4 = new Pug("巴哥犬");

		// 一个人有很多宠物
		Map<Individual, List<? extends Pet>> list2 = new HashMap<Individual, List<? extends Pet>>();

		Individual in = new Individual("Dawn");
		Individual in2 = new Individual("東方不敗");
		list2.put(in, Arrays.asList(p, p1, p2, p3, p4));
		list2.put(in2, Arrays.asList(p2, p3, p4));

		System.out.println(list2);// 輸出這個人的寵物

		// 查找Dawn的寵物
		List<? extends Pet> l = list2.get(in);
		System.out.println(l);

	}
}
/**
 * output: {Individual 東方不敗=[Pet [name=EgyptianMan], Pet [name=马恩岛猫], Pet
 * [name=巴哥犬]], Individual Dawn=[Pet [name=猫], Pet [name=狗], Pet
 * [name=EgyptianMan], Pet [name=马恩岛猫], Pet [name=巴哥犬]]} [Pet [name=猫], Pet
 * [name=狗], Pet [name=EgyptianMan], Pet [name=马恩岛猫], Pet [name=巴哥犬]]
 */
