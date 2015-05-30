package org.rui.ExceptionTest;

import java.util.Date;

/**
 * 字符串格式化 示例
 * 
 * 其中float类型与double类型的数据,对于String.format()方法来说,全表示为浮点数,可使用的格式化参数如:
 * String.format("%a,  %e,  %f,  %g",floatType,floatType,floatType,floatType);
 * 其中 %a 表示用十六进制表示 %e 表示用科学记数法表示 %f 表示用普通的10进制方式表示 %g
 * 表示根据实际的类型的值的大小,或采用%e的方式,或采用%f的方式
 * 
 * 对于日期类型的: 如: String dataStr = String.format("%1$tm-%1$te-%1$tY",dateType);
 * 其中1$表示如果参数中有多个dateType那么取哪个dateType中的值, t表示日期或时间格式, m表示月,e表示日,Y表示年.
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
