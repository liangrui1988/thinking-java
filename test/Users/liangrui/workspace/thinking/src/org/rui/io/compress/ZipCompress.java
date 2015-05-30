package org.rui.io.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * (ZipÛL‡öÝX
 * @author lenovo
 *
 */
public class ZipCompress {
	//static String path="D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\compress\\";
	static String path=new File("").getAbsolutePath()+"\\src\\org\\rui\\io\\compress\\";
	static String [] arg=new String[]{path+"GZIPcompress.java",path+"ZipCompress.java"};
	
	public static void main(String[] args) throws Exception {
		//out
		FileOutputStream f=new FileOutputStream("test.zip");
		CheckedOutputStream cos=new CheckedOutputStream(f,new Adler32());
		ZipOutputStream zos=new ZipOutputStream(cos);
		//out
		BufferedOutputStream out=new BufferedOutputStream(zos);
		zos.setComment("A test of java zipping");
		
		for(String s:arg)
		{
			System.out.println("writing file "+s);
			BufferedReader in =new BufferedReader(new FileReader(s));
			//
			zos.putNextEntry(new ZipEntry(s));
			int c;
			while((c=in.read())!=-1){
				out.write(c);
			}
			in.close();
			out.flush();
		}
		
		out.close();
		//checksum valid only after the file has been closed!
		System.out.println("reading file================");
		FileInputStream fi=new FileInputStream("test.zip");
		CheckedInputStream csumi=new CheckedInputStream(fi,new Adler32());
		ZipInputStream zis=new ZipInputStream(csumi);
		BufferedInputStream bis=new BufferedInputStream(zis);
		ZipEntry ze;
		while((ze=zis.getNextEntry())!=null)
		{
			System.out.println("Reading file "+ze);
			int x;
			while((x=bis.read())!=-1)
			{
				System.out.println(x);
			}
		}
		
		//
		if(arg.length==1)
			System.out.println("checksum:"+csumi.getChecksum().getValue());
		bis.close();
		//alternative way to open and read zip files
		ZipFile zf=new ZipFile("test.zip");
		
		Enumeration e=zf.entries();
		while(e.hasMoreElements())
		{
			ZipEntry ze2=(ZipEntry) e.nextElement();
			System.out.println("file:"+ze2);
			//...and extract the data as before
		}
		/*if(arg.length==1)*/
		
	}

}
