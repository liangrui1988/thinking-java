package org.rui.annotation.database;

/**
 * 如果要令嵌入的@Constraints注解中的unique()元素为true 并以此作为constraints()元素的默认值 则需要如下定义该元素
 * 
 * @author lenovo
 * 
 */
public @interface Uniqueness {
	Constraints constraints() default @Constraints(unique = true);
}
