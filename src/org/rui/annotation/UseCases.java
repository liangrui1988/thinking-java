package org.rui.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ��дע�⴦����
 * 
 * @author lenovo
 * 
 */
public class UseCases {
	public static void trackUseCases(List<Integer> usCease, Class<?> clz) {
		for (Method m : clz.getDeclaredMethods()) {
			UseCase use = m.getAnnotation(UseCase.class);
			if (use != null) {
				System.out.println("�ҵ� Use Case:" + use.id() + " "
						+ use.description());
				usCease.remove(new Integer(use.id()));

			}

		}

		for (int i : usCease) {
			System.out.println("warning: ȱ������- " + i);
		}

	}

	public static void main(String[] args) {
		List<Integer> useCase = new ArrayList<Integer>();
		Collections.addAll(useCase, 47, 48, 49, 50);
		trackUseCases(useCase, PasswordUtils.class);
	}

}
/**
 * �ҵ� Use Case:47 passwords must contain at least one numeric �ҵ� Use Case:48 no
 * description �ҵ� Use Case:49 new passwords can 't equal previously used ones
 * warning: ȱ������- 50
 */
