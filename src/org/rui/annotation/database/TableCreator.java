package org.rui.annotation.database;

import java.io.ObjectInputStream.GetField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ǰ������ο� thinking in java ��4�� 20��
 * 
 * @author lenovo
 * 
 */
public class TableCreator {

	// ����ע�� �����Ӧ��ֵ
	public static String getConstraints(Constraints con) {
		String constraints = "";
		if (con.allowNull())
			constraints += "NOT NULL";
		if (con.primaryKey())
			constraints += "PRIMARY KEY";
		if (con.unique())
			constraints += "UNIQUE";
		return constraints;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		String[] arr = new String[] { "org.rui.annotation.database.Member" };
		if (arr.length < 1) {
			System.out.println("0000");
			System.exit(0);
		}

		for (String className : arr) {
			Class<?> clzz = Class.forName(className);
			DBTable table = clzz.getAnnotation(DBTable.class);
			if (table == null) {
				System.out.println("dbtable annotaions in class:" + className);
				continue;
			}

			String tableName = table.name();
			if (tableName.length() < 1)
				tableName = clzz.getName().toUpperCase();

			// �������
			List<String> columnDefs = new ArrayList<String>();

			// ��ȡ�����ֶ�
			for (Field f : clzz.getDeclaredFields()) {
				String columnName = null;
				// ��ȡ�ֶ������ע��
				Annotation[] annot = f.getAnnotations();
				if (annot.length < 1)
					continue;
				// �����SqlInteger
				if (annot[0] instanceof SqlInteger) {
					SqlInteger sInt = (SqlInteger) annot[0];
					// ���û��ָ������ ʹ���ֶ�����
					if (sInt.name().length() < 1) {
						columnName = f.getName().toUpperCase();
					} else {
						columnName = sInt.name();
					}

					columnDefs.add(columnName + " INT"
							+ getConstraints(sInt.constraints()));
				}
				// �����string ����
				if (annot[0] instanceof SQLString) {
					SQLString sStr = (SQLString) annot[0];
					if (sStr.name().length() < 1) {
						columnName = f.getName().toUpperCase();
					} else {
						columnName = sStr.name();
					}
					columnDefs.add(columnName + " VARCHAR(" + sStr.value()
							+ ")" + getConstraints(sStr.constraints()));
				}

				StringBuilder sql = new StringBuilder("CREATE TABLE "
						+ tableName + "( ");
				for (String c : columnDefs) {
					sql.append("\n   " + c + ",");// ƴ���ֶ�
					// remove trailing comma
					String tableCreate = sql.substring(0, sql.length() - 1)
							+ ");";
					System.out.println("table ceration sql fro " + className
							+ " is :\n " + tableCreate);
				}
			}
		}

	}

}
/**
 * output: table ceration sql fro org.rui.annotation.database.Member is : CREATE
 * TABLE MEMBER( FIRSTNAME VARCHAR(30)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL, LASTNAME VARCHAR(50)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL, LASTNAME VARCHAR(50)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL, LASTNAME VARCHAR(50)NOT NULL, AGE INTNOT NULL); table
 * ceration sql fro org.rui.annotation.database.Member is : CREATE TABLE MEMBER(
 * FIRSTNAME VARCHAR(30)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL, LASTNAME VARCHAR(50)NOT NULL); table ceration sql fro
 * org.rui.annotation.database.Member is : CREATE TABLE MEMBER( FIRSTNAME
 * VARCHAR(30)NOT NULL, LASTNAME VARCHAR(50)NOT NULL, AGE INTNOT NULL); table
 * ceration sql fro org.rui.annotation.database.Member is : CREATE TABLE MEMBER(
 * FIRSTNAME VARCHAR(30)NOT NULL, LASTNAME VARCHAR(50)NOT NULL, AGE INTNOT NULL,
 * HANDLE VARCHAR(30)NOT NULLPRIMARY KEY);
 */
