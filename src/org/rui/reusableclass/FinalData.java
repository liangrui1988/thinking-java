package org.rui.reusableclass;

import java.util.Random;

class Value {
	
	int i;
	public Value(int i) {
		this.i = i;
	}
}

public class FinalData {

	private static Random rand=new Random(47);
	private String id;
	

	
	public FinalData(String id) {
		this.id=id;
		
	}
	//����complie-time����
	private final int valueOne=9;
	private static final int VALUE_TWO=99;
	//���͵Ĺ�������
	private static final int VALUE_THREE=39;
	
	//���ܱ���ʱ����:
	private final int i4=rand.nextInt(20);
	static final int INT_5=rand.nextInt(20);
	private Value v1=new Value(11);
	private final Value v2=new Value(22);
	private static final Value VAL_3=new Value(33);
	
	//arrays
	private final int[] a={1,2,3,4,5};
	@Override
	public String toString() {
		return id+": i4="+i4+",INT_5:"+INT_5;
	}
	
	
	public void fs(final FinalData f){
		//!f=new FinalData("f");
	}
	public int fs(final int f){
	    //! f++;
		return f+5;//ok
	}
	
	public static void main(String[] args) {
	
		FinalData fd1=new FinalData("fd1");
		//! fd1.valueOne++;
		fd1.v2.i++;
		fd1.v1=new Value(9);
		for(int i=0;i<fd1.a.length;i++){
			fd1.a[i]++;//object isn't constant!�����ǳ���!
			//! fd1.v2=new Value(0);
			//! fd1.VAL_3=new Value(0);
			//! fd1.a=new int[3];
		}
		
		System.out.println(fd1);
		System.out.println("create new FinalData");
		FinalData fd2=new FinalData("fd2");
		System.out.println(fd1);
		System.out.println(fd2);
	}

}
/**
output:
fd1: i4=15,INT_5:18
create new FinalData
fd1: i4=15,INT_5:18
fd2: i4=13,INT_5:18
*/