package org.rui.thread.res;

/**
 * ����������ջ�ʧ�ܣ���ΪevenChecker������evenGenerator���� ��ǡ���� ״̬ʱ ���ܹ�����������Ϣ
 * �����ϣ������ط���ʧ�ܣ����Գ����Ž�yield() �ĵ��÷��õ���һ���͵ڶ�����������֮�䡣 ��ֻ�ǲ�������Ĳ��ֲ���
 * 
 * @author lenovo
 * 
 */
public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public int next() {
		++currentEvenValue;// Σ����Ŀ���ĳ�����! danger point here
		++currentEvenValue;
		return currentEvenValue;

	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
/**
 * output: press control-c to exit 13103419 not even!
 */
