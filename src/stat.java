import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

public class stat {
	{
		//stat s=new stat();
		/*Cs c=new stat.Cs();
		System.out.println("----");
		c.s();*/
	}
	
	public static void outclz(){}
	
	public static void main(String[] args) {
		
		stat cobj=new stat();
		
		Cs c=new stat.Cs();
	
		
		
		stat.Inner iner=cobj.new Inner();
		
		System.out.println("----");
		c.s();
		//c.s();
		//Cs.s();
	
	}
	
	 public  static class Cs{
		 public static void s(){
			 System.out.println("s");
		 }
	 }
	 
	 public  class Inner{
		 public  void s(){
			 System.out.println("s");
		 }
	 }

}
