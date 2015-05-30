package org.rui.io.xml;

import java.util.prefs.Preferences;

/**
 * Preferences API 提供一种系统的方法来处理用户的偏好设置信息,例如.保存用户设置, 记住某个文本框的最后一个值等. Preferences
 * 是一个可以为任意名字的键/值对. 值可以为布尔型,字符型, 其他简单的数据类型， 如int. Preferences
 * 通过get和set来获取和设置偏好信息，且get的方法可设置一个默认值，当要获取的键未被设置值时，就返回此默认值. 1.2. 数据的实际存储
 * 数据的实际存储是依赖于操作系统平台的, 例如.在Windows 下面是使用注册表来保存这些信息，
 * 而在Linux下面是使用用户的home目录下面的一个隐藏文件来存储的. 2. API使用 java.util.prefs.Preferences
 * 很容易使用. 你不得不定义一个节点来存储数据. 接下来就可以使用get和set的方法. 第二个参数是默认值，即当找不到值时，得到的就是这个默认值了,
 * 例如. 如果preference的值还未设置, 那么将会返回这个默认值.
 * 
 * @author lenovo
 * 
 */

public class PerferencesDemo {
	public static void main(String[] args) throws Exception {

		Preferences perfs = Preferences
				.userNodeForPackage(PerferencesDemo.class);
		perfs.put("location", "0z");
		perfs.put("Footwear", "Ruby Slippers");
		perfs.putInt("Companions", 4);
		perfs.putBoolean("Are there witches?", true);
		int usageCount = perfs.getInt("UsageCount", 0);
		usageCount++;
		// System.out.println(usageCount);
		perfs.putInt("UsageCount", usageCount);
		for (String key : perfs.keys())
			System.out.println(key + ":" + perfs.get(key, null));
		System.out.println("How many companions does Dorothy have?  "
				+ perfs.getInt("Companions", 0));

	}

}/*
 * location:0z Footwear:Ruby Slippers Companions:4 Are there witches?:true
 * UsageCount:5 How many companions does Dorothy have? 4
 */