package com.yixin.service400.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLConnectionDemo {
	public static void main(String[] args) throws Exception {
		URL hp = new URL(
				"http://localhost:8080/SerManage/homeAction_index.action");
		// URL urll=new URL("http://www.cqwu.edu.cn/");
		// URL lib=new URL(urll , "library/library.asp");
		URLConnection hpCon = hp.openConnection();
		hpCon.setDoInput(true);
		hpCon.setDoOutput(true);
		hpCon.connect();
		
		OutputStream outputStream = hpCon.getOutputStream();
		outputStream.write("username=kobe".getBytes());
		System.out.println(hpCon.getURL());
		System.out.println("Date: " + new Date(hpCon.getDate()));
		System.out.println("Content-Type: " + hpCon.getContentType());
		System.out.println("Expires: " + hpCon.getExpiration());
		System.out.println("Last-Modified: "
				+ new Date(hpCon.getLastModified()));
		int len = hpCon.getContentLength();
		InputStream in = hpCon.getInputStream();
		StringBuffer sb = new StringBuffer();
		byte[] arr = new byte[10240];
		while ((len = in.read(arr)) > 0) {
			sb.append(new String(arr, 0, len));
		}

		System.out.println(sb.toString());
	}

}
