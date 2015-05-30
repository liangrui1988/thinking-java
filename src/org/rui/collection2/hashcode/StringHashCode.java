package org.rui.collection2.hashcode;

/**
 * 覆盖hashcode 设计HashCode时最重要的因素 就是：无论何时，对同一个对象调用HashCode都应该产生同样的值，
 * 如果你的HashCode方法依赖于对象中易变的数据，用户就要当心了，因为此数据发生变化 时
 * HashCode就会生成一个不同的散列码，相当于产生一个不同的健 此外
 * 也不应该使HashCode依赖于具有唯一性的对象信息，尤其是使用this的值，这只能很糟糕，
 * 因为这样做无法生成一个新的健，使这与Put中原始的健值对中的健相同，它的默认的HashCode使用的是对象的地址 所以 应该 使用对象内有意义的识别信息
 * 
 * 以下以String类为例 String对象都 映射到同一块内存域， 所以 new String("hello") 生成的两个实例 ，虽然是相互独立的，
 * 但是对它们使用HashCode应该生成同样的结果，以下示例可以看到 对String而言，HashCode明显是基于String的内容的，
 * 
 * 因此 要想使HashCode实用，它必须 速度快，并且必须有意义。也就是说，它必须基于对象的内容生成的散列码， 记得吗，散列码不必是独一无二的
 * （应该更关注生成速度，而不是唯一性） 但HashCode和Equals 必须能够完全确定对象的身份 所以散列码生成的范围并不重要，只要是int即可
 * 还有别一个影响因素，好的HashCode应该产生分布均匀的散列码
 * 
 * 
 * @author lenovo
 * 
 */

public class StringHashCode {
	public static void main(String[] args) {
		String[] hello = "Hello Hello".split(" ");
		System.out.println(hello[0].hashCode());
		System.out.println(hello[1].hashCode());
	}
}
/**
 * output: 69609650 69609650
 */
