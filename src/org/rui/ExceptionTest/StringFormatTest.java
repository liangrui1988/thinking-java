package org.rui.ExceptionTest;

import java.util.Date;

/**
 * �ַ�����ʽ�� ʾ��
 * 
 * ����float������double���͵�����,����String.format()������˵,ȫ��ʾΪ������,��ʹ�õĸ�ʽ��������:
 * String.format("%a,  %e,  %f,  %g",floatType,floatType,floatType,floatType);
 * ���� %a ��ʾ��ʮ�����Ʊ�ʾ %e ��ʾ�ÿ�ѧ��������ʾ %f ��ʾ����ͨ��10���Ʒ�ʽ��ʾ %g
 * ��ʾ����ʵ�ʵ����͵�ֵ�Ĵ�С,�����%e�ķ�ʽ,�����%f�ķ�ʽ
 * 
 * �����������͵�: ��: String dataStr = String.format("%1$tm-%1$te-%1$tY",dateType);
 * ����1$��ʾ����������ж��dateType��ôȡ�ĸ�dateType�е�ֵ, t��ʾ���ڻ�ʱ���ʽ, m��ʾ��,e��ʾ��,Y��ʾ��.
 */
public class StringFormatTest {

	public static void main(String[] args) {
		float floatType = 1000.00f;
		double doubleTyep = 11111111111111111.00d;
		Date dateType = new Date();
		String floatStr = String.format("%a,  %e,  %f,  %g", floatType,
				floatType, floatType, floatType);
		String doubleStr = String.format("%a,  %e,  %f,  %g", doubleTyep,
				doubleTyep, doubleTyep, doubleTyep);
		String dataStr = String.format("%1$tm-%1$te-%1$tY", dateType);
		System.out.println(floatStr);
		System.out.println(doubleStr);
		System.out.println(dataStr);
	}

}

/**
 * output: 0x1.f4p9, 1.000000e+03, 1000.000000, 1000.00 0x1.3bcbf936b38e4p53,
 * 1.111111e+16, 11111111111111112.000000, 1.11111e+16 06-26-2014
 */
