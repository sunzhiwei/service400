package com.yixin.service400.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.yixin.service400.util.FileUtils;



public class FileTest {

	public static File getFile(){
		String filePath = "F:/Apache-Tomcat/apache-tomcat-6.0.32/webapps/uploadfile";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(filePath==null){
			filePath = format.format(date); // 取得服务器路径
		}else{
			filePath = filePath + "/"+format.format(date);
		}
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
	}
	
	public static void main(String args[]){
//		File file=getFile();
//		System.out.println(file);
//		getFileTest(1);
		String CRYPT_KEY = "0eee7e36eab0a04bd016685d9924201c";
		System.out.println(DigestUtils.shaHex(CRYPT_KEY.getBytes()));
//		String KEY = DigestUtils.md5DigestAsHex(CRYPT_KEY.getBytes()).toUpperCase();
	}
	
	public static void getFileTest(int type){
		String srcPath="F:/Apache-Tomcat/apache-tomcat-6.0.32/webapps/uploadfile/template.xls";
		String filePath = "F:/Apache-Tomcat/apache-tomcat-6.0.32/webapps/uploadfile";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(filePath==null){
			filePath = format.format(date); // 取得服务器路径
		}else{
			filePath = filePath + "/"+format.format(date);
		}
//		File srcFile = new File(srcPath);
//		System.out.println(srcFile.getParentFile().getAbsolutePath());
		File distFile=FileUtils.copyFile(srcPath, filePath,type);
		System.out.println(distFile.getAbsolutePath());
	}
}
