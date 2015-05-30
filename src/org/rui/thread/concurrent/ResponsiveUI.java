package org.rui.thread.concurrent;

/**
 * 创建有响应的用户界面 一个关注于运算，所以不能读取控制台输入，另一个把运算放在任务里单独运行, 此时就可以在进行运算的同时监听控制台输入
 * 
 * @author lenovo
 * 
 */

// 无反应的
class UnresponsiveUI {
	private volatile double d = 1;

	public UnresponsiveUI() throws Exception {
		while (d > 0)
			d = d + (Math.PI + Math.E);
		System.in.read();// 永远不会在这里
	}

}

public class ResponsiveUI extends Thread {
	private volatile static double d = 1;

	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		// 无限循环，显然程序 不可能到达读取控制台输入的那一行（编译器被欺骗了，相信while的条件使得程序能到达读取控制台输入的那一行）。
		// 如果把建立UnresponsiveUI的那一行的注释解除掉再运行程序，那么要终止它的话，就只能杀死这个进程
		while (true)
			d = d + (Math.PI + Math.E) / d;
	}

	// //////////////////
	public static void main(String[] args) throws Exception {
		// 4new UnresponsiveUI();// 必须杀了这个线程
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);// 显示上传进度
		/**
		 * 要想让程序有响应，就得把计算程序放在run方法中，这样它就能让出处理器给别的程序。
		 * 当你按下‘回车’健的时候，可以看到计算确实在作为后台程序运行，同时还在等待用户输入。
		 */
	}

}
/**
 * input: hello output: 根据停留时间输出： 68130.17629894095
 */
