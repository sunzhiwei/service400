package com.yixin.service400.test;

import java.util.regex.Pattern;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String ip="slect aa union all select aa union all select aa union all select aa union all    ";
//		ip = ip.substring(0,ip.length()-(ip.length()-ip.lastIndexOf("union all")));
//		System.out.println(ip);
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
//		SimpleDateFormat format1=new SimpleDateFormat("yyyy.MM.dd");
//		SimpleDateFormat format2=new SimpleDateFormat("MM.dd");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
//		String years = dateFormat.format(new Date());
//		int years_value = Integer.parseInt(years);
//		years_value--;
//		String presentYearYesterday=format1.format(cal.getTime());
//		String previousYearYesterday=years_value+"."+format2.format(cal.getTime());
//		String yesterday=format2.format(cal.getTime());
//		System.out.println(presentYearYesterday);
//		System.out.println(previousYearYesterday);
//		System.out.println(yesterday);
		String str="12";
		Pattern pattern = Pattern.compile("[-+]?[0-9]+(\\.[0-9]+)?"); //判断是否为double类型
		System.out.println(pattern.matcher(str).matches());
	}

}
