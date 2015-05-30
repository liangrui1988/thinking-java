package org.rui.collection2.maps;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 散列与散列码 将土拔鼠对象与预报对象联系起来， 每个Groundhog被 予一个标识 数字，于是可以在hasmmap中这样查找Prediction：
 * 给我与Groundhog #3相关的prediction prediction类包含一个boolean 和toString 布尔
 * 值用random来初始化；而Tostring方法则解释结果。
 * detecSpring方法使用反射机制来实例化例用Groundhog类或任何从GroundHog派生出来的类.
 * 
 * @author lenovo
 * 
 */
public class SpringDetector {
	// 发现
	public static <T extends Groundhog> void detectSpring(Class<T> type)
			throws Exception {
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<Groundhog, Prediction> map = new HashMap<Groundhog, Prediction>();

		// 初始化map
		for (int i = 0; i < 10; i++) {
			map.put(ghog.newInstance(i), new Prediction());
		}
		System.out.println("map:=" + map);

		// 生成一个number=3的士拔鼠
		Groundhog gh = ghog.newInstance(3);
		// 查找预测
		System.out.println("looking up prediction for:" + gh);

		// 在初始化中的map中查找
		if (map.containsKey(gh))
			System.out.println(map.get(gh));
		else
			System.out.println("key not found " + gh);
		// 看起来很简单，但是他不工作，无法找到 需要实现hacode 和equals 下章讲解
	}

	public static void main(String[] args) throws Exception {
		detectSpring(Groundhog.class);
	}
}
/**
 * output: map:={Groundhog #5=早春, Groundhog #7=早春, Groundhog #8=六周后是冬天,
 * Groundhog #0=六周后是冬天, Groundhog #9=六周后是冬天, Groundhog #2=早春, Groundhog
 * #1=六周后是冬天, Groundhog #4=六周后是冬天, Groundhog #3=早春, Groundhog #6=早春} looking up
 * prediction for:Groundhog #3 key not found Groundhog #3
 */
