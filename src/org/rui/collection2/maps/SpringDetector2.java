package org.rui.collection2.maps;

/**
 * Groundhog2.hashCode返回Groundhog的标识数字（编号）作为散列码。
 * 在此例中，程序员负责确保不同的groundhog具有不同的编号。hashCode并不需要总是能够返回唯一的标识 码 但equals必须严格判断对象是否相同
 * 
 * @author lenovo
 * 
 */
public class SpringDetector2 {
	public static void main(String[] args) throws Exception {
		SpringDetector.detectSpring(Groundhog2.class);
	}

}
/**
 * map:={Groundhog #0=六周后是冬天, Groundhog #1=六周后是冬天, Groundhog #2=早春, Groundhog
 * #3=早春, Groundhog #4=六周后是冬天, Groundhog #5=早春, Groundhog #6=早春, Groundhog
 * #7=早春, Groundhog #8=六周后是冬天, Groundhog #9=六周后是冬天} looking up prediction
 * for:Groundhog #3 早春
 */
