package org.rui.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 编写注解处理器
 * 
 * @author lenovo
 * 
 */
public class UseCases {
	public static void trackUseCases(List<Integer> usCease, Class<?> clz) {
		for (Method m : clz.getDeclaredMethods()) {
			UseCase use = m.getAnnotation(UseCase.class);
			if (use != null) {
				System.out.println("找到 Use Case:" + use.id() + " "
						+ use.description());
				usCease.remove(new Integer(use.id()));

			}

		}

		for (int i : usCease) {
			System.out.println("warning: 缺少用例- " + i);
		}

	}

	public static void main(String[] args) {
		List<Integer> useCase = new ArrayList<Integer>();
		Collections.addAll(useCase, 47, 48, 49, 50);
		trackUseCases(useCase, PasswordUtils.class);
	}

}
/**
 * 找到 Use Case:47 passwords must contain at least one numeric 找到 Use Case:48 no
 * description 找到 Use Case:49 new passwords can 't equal previously used ones
 * warning: 缺少用例- 50
 */
