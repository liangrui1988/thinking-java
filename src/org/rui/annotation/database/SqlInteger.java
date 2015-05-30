package org.rui.annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * sql¿‡–Õ
 * 
 * @author lenovo
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlInteger {
	int value() default 0;

	String name() default "";

	Constraints constraints() default @Constraints;

}
