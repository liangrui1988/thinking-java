package org.rui.io.util;

/**
 * 下面展示了如何使用OSExecute的示例
 * 
 * @author lenovo
 * 
 */
public class OSExecuteDemo {
	public static void main(String[] args) {
		// javac org/rui/io/util/OSExecuteDemo.java
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\util/";
		// path="";
		String commandw = "javap " + path + "OSExecuteDemo.class";
		// System.out.println(commandw);
		OSExecuted.command(commandw);
	}

}
/************************
 * output: Compiled from "OSExecuteDemo.java" public class
 * org.rui.io.util.OSExecuteDemo { // public org.rui.io.util.OSExecuteDemo();
 * //public static void main(java.lang.String[]); }
 *******/
