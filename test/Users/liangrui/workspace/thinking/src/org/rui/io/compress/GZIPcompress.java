package org.rui.io.compress;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * (GZIPÛL€U‹)
 * @author lenovo
 *
 */
public class GZIPcompress {
	static String path="D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\compress\\";
	static String [] arg=new String[]{path+"GZIPcompress.java"};
	public static void main(String[] args) throws Exception {
		//in
		BufferedReader in=new BufferedReader(new FileReader(arg[0]));
		//out
		BufferedOutputStream out=new BufferedOutputStream(new GZIPOutputStream(
				new FileOutputStream("test.gz")
				));
		System.out.println("writing file");
		int c;
		while((c=in.read())!=-1)
		{
			out.write(c);
		}
		//close
		in.close();
		out.close();
		System.out.println("reading file================");
		BufferedReader br=new BufferedReader(
				new InputStreamReader(
						new GZIPInputStream(
								new FileInputStream("test.gz")
								)
						)
				);
		
		String s;
		while((s=br.readLine())!=null)
			System.out.println(s);
	}

}
