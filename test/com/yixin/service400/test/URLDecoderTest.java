package com.yixin.service400.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
  public static void main(String[] args) throws Exception {
	   //将application/x-www-form-urlencoded字符串
	   //转换成普通字符串
	  // 将“%E6%9D%8E%E5%88%9Aj2ee” 转换成以utf-8编码的普通字符
	   // 前提是“%E6%9D%8E%E5%88%9Aj2ee” 这个特殊字符串是浏览器以utf-8 编码 进行URLEncoder.encode("李刚j2ee", "utf-8")生成的,
	  // 再转换时通过utf-8 时就不会中文乱码了
	  // 而包含中文字符的普通字符串则需要转换，转换的方法是每个中文字符占2个字节，每个字节可以转换成2个十六进制的数字，所以每个中文字符将转换成"%XX%XX"的形式
	  // 这是utf-8 编码的转换方式
	  
	  String str=URLDecoder.decode("%E6%9D%8E%E5%88%9Aj2ee", "utf-8");
	  System.out.println(str);
	    //将普通字符串
	   //转换成application/x-www-form-urlencoded字符串
	  // 将"李刚j2ee"转换成以utf-8编码的特殊字符串
	  str=URLEncoder.encode("李刚j2ee", "utf-8");
	  System.out.println(str);
}
}
