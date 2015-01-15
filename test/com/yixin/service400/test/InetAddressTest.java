package com.yixin.service400.test;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class InetAddressTest {
	public static void main(String[] args) throws Exception {

		// InetAddress ip=InetAddress.getByName("www.baidu.com");
		// System.out.println(ip.getHostAddress()+":"+ip.getHostName()+":"+ip.getAddress()+":"+ip.getCanonicalHostName());
		// System.out.println(ip.isReachable(2000));
		//
		// InetAddress ip2=InetAddress.getLocalHost();
		// System.out.println(ip2.getHostAddress());
		//
		// InetAddress ip3=InetAddress.getByAddress(new byte[]{127,0,0,1});
		// System.out.println(ip3.getHostAddress()+":"+ip3.getHostName()+":"+ip3.getAddress()+":"+ip3.getCanonicalHostName());
		//
		// InetAddress ip4=InetAddress.getByAddress(new byte[]{61,(byte)
		// 135,(byte) 169,125});
		// System.out.println(ip4.getHostAddress()+":"+ip4.getHostName()+":"+ip4.getAddress()+":"+ip4.getCanonicalHostName());

		InetAddressTest urldemo = new InetAddressTest();
		try {
			InetAddress ip=InetAddress.getByName("www.sina.com.cn");
			System.out.println(ip.getHostAddress()+":"+ip.getHostName()+":"+ip.getAddress()+":"+ip.getCanonicalHostName());
			URL hp = new URL("http://www.sina.com.cn/");
			System.out.println(hp.getContent().toString());
			System.out.println(hp.getDefaultPort());
			System.out.println("Protocol: " + hp.getProtocol());
			System.out.println("Port: " + hp.getPort());
			System.out.println("Host: " + hp.getHost());
			System.out.println("File: " + hp.getFile());
			System.out.println("Ext: " + hp.toExternalForm());
		} catch (MalformedURLException ex) {
			System.out.println(ex.toString());
		}
	}
}
