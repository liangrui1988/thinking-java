package org.rui.annotation.database;

/**
 * ���Ҫ��Ƕ���@Constraintsע���е�unique()Ԫ��Ϊtrue ���Դ���Ϊconstraints()Ԫ�ص�Ĭ��ֵ ����Ҫ���¶����Ԫ��
 * 
 * @author lenovo
 * 
 */
public @interface Uniqueness {
	Constraints constraints() default @Constraints(unique = true);
}
