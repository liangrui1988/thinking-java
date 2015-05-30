package org.rui.io.xml;

import java.util.prefs.Preferences;

/**
 * Preferences API �ṩһ��ϵͳ�ķ����������û���ƫ��������Ϣ,����.�����û�����, ��סĳ���ı�������һ��ֵ��. Preferences
 * ��һ������Ϊ�������ֵļ�/ֵ��. ֵ����Ϊ������,�ַ���, �����򵥵��������ͣ� ��int. Preferences
 * ͨ��get��set����ȡ������ƫ����Ϣ����get�ķ���������һ��Ĭ��ֵ����Ҫ��ȡ�ļ�δ������ֵʱ���ͷ��ش�Ĭ��ֵ. 1.2. ���ݵ�ʵ�ʴ洢
 * ���ݵ�ʵ�ʴ洢�������ڲ���ϵͳƽ̨��, ����.��Windows ������ʹ��ע�����������Щ��Ϣ��
 * ����Linux������ʹ���û���homeĿ¼�����һ�������ļ����洢��. 2. APIʹ�� java.util.prefs.Preferences
 * ������ʹ��. �㲻�ò�����һ���ڵ����洢����. �������Ϳ���ʹ��get��set�ķ���. �ڶ���������Ĭ��ֵ�������Ҳ���ֵʱ���õ��ľ������Ĭ��ֵ��,
 * ����. ���preference��ֵ��δ����, ��ô���᷵�����Ĭ��ֵ.
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