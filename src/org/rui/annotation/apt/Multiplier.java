package org.rui.annotation.apt;

@ExtractInterface(value = "IMultiplier")
public class Multiplier {
	
	public int multiply(int x,int y){
		int total=0;
		for(int i=0;i<x;i++){
			total=add(total,y);
		}
		return total;
	}
	
	public int add(int x,int y){
		return x+y;
	}
	
	public static void main(String[] args) {
		Multiplier m=new Multiplier();
		System.out.println("10*16="+m.multiply(10, 16));
	}

}
