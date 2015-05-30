package org.rui.collection2.maps;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * ɢ����ɢ���� �������������Ԥ��������ϵ������ ÿ��Groundhog�� ��һ����ʶ ���֣����ǿ�����hasmmap����������Prediction��
 * ������Groundhog #3��ص�prediction prediction�����һ��boolean ��toString ����
 * ֵ��random����ʼ������Tostring��������ͽ����
 * detecSpring����ʹ�÷��������ʵ��������Groundhog����κδ�GroundHog������������.
 * 
 * @author lenovo
 * 
 */
public class SpringDetector {
	// ����
	public static <T extends Groundhog> void detectSpring(Class<T> type)
			throws Exception {
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<Groundhog, Prediction> map = new HashMap<Groundhog, Prediction>();

		// ��ʼ��map
		for (int i = 0; i < 10; i++) {
			map.put(ghog.newInstance(i), new Prediction());
		}
		System.out.println("map:=" + map);

		// ����һ��number=3��ʿ����
		Groundhog gh = ghog.newInstance(3);
		// ����Ԥ��
		System.out.println("looking up prediction for:" + gh);

		// �ڳ�ʼ���е�map�в���
		if (map.containsKey(gh))
			System.out.println(map.get(gh));
		else
			System.out.println("key not found " + gh);
		// �������ܼ򵥣����������������޷��ҵ� ��Ҫʵ��hacode ��equals ���½���
	}

	public static void main(String[] args) throws Exception {
		detectSpring(Groundhog.class);
	}
}
/**
 * output: map:={Groundhog #5=�紺, Groundhog #7=�紺, Groundhog #8=���ܺ��Ƕ���,
 * Groundhog #0=���ܺ��Ƕ���, Groundhog #9=���ܺ��Ƕ���, Groundhog #2=�紺, Groundhog
 * #1=���ܺ��Ƕ���, Groundhog #4=���ܺ��Ƕ���, Groundhog #3=�紺, Groundhog #6=�紺} looking up
 * prediction for:Groundhog #3 key not found Groundhog #3
 */
