package org.rui.annotation.database;

import java.lang.annotation.*;

/**
 * ����һ�����ݿ��
 * 
 * @author lenovo
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
	public String name() default "";

}
