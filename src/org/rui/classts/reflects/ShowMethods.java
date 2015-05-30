package org.rui.classts.reflects;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 提取 类的 方法 构造器
 * 
 * @author lenovo
 * 
 */
// {args:ShowMethods}
// 查看一个类的所有方法和构造器
public class ShowMethods {
	private static String usage = "usage:showMethod qualified.class.mane";

	private static Pattern p = Pattern.compile("\\w+\\.");

	public static void main(String[] args) throws ClassNotFoundException {

		// System.out.println(args[0]+"  :  "+args[1]);
		args = new String[1];
		args[0] = "org.rui.classts.reflects.ShowMethods";
		// args[1]="java.awt.Color";

		if (args.length < 1) {
			System.out.println(usage);
			System.exit(0);
		}
		int lines = 0;
		Class<?> c = Class.forName(args[0]);
		Method[] m = c.getMethods();
		Constructor[] constructor = c.getConstructors();
		if (args.length == 1) {
			for (Method mt : m) {
				// System.out.println("tostring:"+mt.toString());
				// 去掉带 点和前面的字符 如 xx.ss.
				System.out.println("m1:"
						+ p.matcher(mt.toString()).replaceAll(""));
			}
			// ----------------------------------------
			for (Constructor con : constructor)
				System.out.println("c1:"
						+ p.matcher(con.toString()).replaceAll(""));
			lines = m.length + constructor.length;
		} else {
			for (Method mt : m) {
				if (mt.toString().indexOf(args[1]) != -1) {
					System.out.println("m2:"
							+ p.matcher(mt.toString()).replaceAll(""));
					lines++;
				}

			}
			// ----------------------------------------
			for (Constructor con : constructor) {
				if (con.toString().indexOf(args[1]) != -1) {
					System.out.println("c2:"
							+ p.matcher(con.toString()).replaceAll(""));
					lines++;
				}

			}
		}

	}

}
