package org.rui.annotation.database;

import java.lang.annotation.*;

/**
 * 生成一个数据库表
 * 
 * @author lenovo
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
	public String name() default "";

}
