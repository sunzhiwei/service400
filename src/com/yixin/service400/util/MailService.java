package com.yixin.service400.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.junit.Test;

public class MailService {
	/**
	 * 申请400号码邮件类型
	 */
	public static String MAIL_TYPE_APPLY = "apply";
	/**
	 * 申请按键变更邮件类型
	 */
	public static String MAIL_TYPE_KEY = "key";
	/**
	 * 工作组变更邮件类型
	 */
	public static String MAIL_TYPE_GROUP = "group";
	public static String MAIL_STATUS_SUESSCE = "success";
	public static String MAIL_STATUS_FAIL = "fail";

	@Test
	public static boolean sendMail(String type, Map<String, Object> map)
			throws IOException {
		if (type == null) {
			return false;
		}
		String link = Conf.getValue("mail.service") + "?type=" + type;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			link += "&" + entry.getKey() + "=" + entry.getValue().toString();
		}
		System.out.println(link);
		URL url = new URL(link);
		URLConnection openConnection = url.openConnection();
		InputStream in = openConnection.getInputStream();
		byte[] arr = new byte[10240];
		int length = 0;
		StringBuffer sb = new StringBuffer();
		while ((length = in.read(arr)) > 0) {
			sb.append(new String(arr, 0, length));
		}
		return sb.toString().trim().equals(MAIL_STATUS_SUESSCE);
	}

}
