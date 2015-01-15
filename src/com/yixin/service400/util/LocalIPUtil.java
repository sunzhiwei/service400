package com.yixin.service400.util;

import java.net.InetAddress;

public class LocalIPUtil {

	public static String getLocalIP() throws Exception{
		  InetAddress addr = InetAddress.getLocalHost();   
	      String ip=addr.getHostAddress();
	      return ip;
	}
}
