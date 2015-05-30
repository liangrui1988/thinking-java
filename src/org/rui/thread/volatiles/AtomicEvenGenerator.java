package org.rui.thread.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

import org.rui.thread.res.EvenChecker;
import org.rui.thread.res.IntGenerator;

/**
 * 下面是用AtomicInteger重写MutexEvenGenerator.java: 所有其他形式的同步再次通过使用AtomicInteger得到了根除
 * 
 * 
 * 
 * 应该强调的是，atomic类被设计用来构建java.util.concureent中的类，
 * 因此只有在特殊情况下才在自己的代码中使用它们，即便使用了也需要确保不存在其他可能出现的问题。
 * 通常依赖于锁要更安全一些（要么是synchronized关键字，要么是显式的lock对象）
 * 
 * @author lenovo
 * 
 */
public class AtomicEvenGenerator extends IntGenerator {

	private AtomicInteger currentEvenValue = new AtomicInteger(0);

	@Override
	public int next() {
		return currentEvenValue.addAndGet(2);
	}

	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}

}
