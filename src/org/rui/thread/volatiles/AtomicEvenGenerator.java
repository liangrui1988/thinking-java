package org.rui.thread.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

import org.rui.thread.res.EvenChecker;
import org.rui.thread.res.IntGenerator;

/**
 * ��������AtomicInteger��дMutexEvenGenerator.java: ����������ʽ��ͬ���ٴ�ͨ��ʹ��AtomicInteger�õ��˸���
 * 
 * 
 * 
 * Ӧ��ǿ�����ǣ�atomic�౻�����������java.util.concureent�е��࣬
 * ���ֻ������������²����Լ��Ĵ�����ʹ�����ǣ�����ʹ����Ҳ��Ҫȷ���������������ܳ��ֵ����⡣
 * ͨ����������Ҫ����ȫһЩ��Ҫô��synchronized�ؼ��֣�Ҫô����ʽ��lock����
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
