package org.rui.thread;

/**
 * Run ���ǵ������߳�������������main��ֱ�ӵ��õ� �����Ծ�ʹ���̣߳������Ƿ����main���Ǹ��߳�
 * 
 * @author lenovo
 * 
 */
public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}

}
/**
 * output: #0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(liftoff!),
 */
