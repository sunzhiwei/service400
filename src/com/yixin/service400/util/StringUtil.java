package com.yixin.service400.util;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * aa
 * @author ygq
 *
 */
public class StringUtil {
	/**
	 * 
	 * 描述：字符串转换成日期
	 * 2012-12-31 下午04:42:56 by ygq
	 * @version
	 * @param obj
	 * @return
	 */
	public static Date strToDate(String obj){
		if (obj == null || isNull(obj.toString()))
			return null;
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = new Date();
		try {
			date1 = clsFormat.parse(obj.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}
	/**
	 * 描述：字符串转化为日期
	 * 2012-12-7 下午04:01:22 by ygq
	 * @version
	 * @param src
	 * @param format
	 * @return
	 */
	public static Date strToDate(String src,String format){
		Date date = null;
		if(null== src || "".equals(src)||"null".equals(src)){
			return null;
		}
		try{
			if(null == format || "".equals(format)){
				format = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(src);
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串是否为空
	 * 
	 * @param strValue
	 * @return
	 */
	public static boolean isNull(String strValue) {

		if ((strValue == null)
				|| (strValue.trim().equalsIgnoreCase("null") || (strValue
						.trim().equals("")))) {
			return true;
		}

		return false;
	}
	/**
	 * 描述：日期转化为字符串
	 * 2012-12-7 下午04:01:22 by ygq
	 * @version
	 * @param src
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date){
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date==null){
			return null;
		}
		str = sdf.format(date);
		return str;
	}
	/**
	 * 
	 * 描述：字符串转化为整数类型
	 * 2012-12-7 下午04:02:46 by ygq
	 * @version
	 * @param src
	 * @return
	 */
	public static int strToInt(String src){
		if(null== src || "".equals(src)){
			return 0;
		}
		try{
			return Integer.parseInt(src);
		}catch(Exception e){
			return 0;
		}
	}
	/**
	 * 
	 * 描述：字符串转化为double类型
	 * 2012-12-7 下午04:02:46 by ygq
	 * @version
	 * @param src
	 * @return
	 */
	public static double strToDouble(String src){
		if(null== src || "".equals(src)){
			return 0;
		}
		try{
			return Double.parseDouble(src);
		}catch(Exception e){
			return 0;
		}
	}
	/**
	 *
	 * 描述：将double类型转换为小数点后两位的double
	 * 2012-12-8 下午02:08:49 by ygq
	 * @version
	 * @return
	 */
	public static double doubleToNumber2(double pDouble){
		BigDecimal bd = new BigDecimal(pDouble);
		BigDecimal bd1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		pDouble = bd1.doubleValue();
		return pDouble;
	}
	/**
	 * 
	 * 描述：模糊查询的时候，添加百分号
	 * 2012-12-31 下午04:45:23 by ygq
	 * @version
	 * @param str
	 * @return
	 */
	public static String addLikeOperPre(String str){
		if(null != str && !"".equals(str)){
			str = "%" + str;
		}
		return str;
	}
	/**
	 * 
	 * 描述：模糊查询的时候，添加百分号
	 * 2012-12-31 下午04:45:23 by ygq
	 * @version
	 * @param str
	 * @return
	 */
	public static String addLikeOperEnd(String str){
		if(null != str && !"".equals(str)){
			str = str + "%";
		}
		return str;
	}
	/**
	 * 
	 * 描述：模糊查询的时候，添加百分号
	 * 2012-12-31 下午04:45:23 by ygq
	 * @version
	 * @param str
	 * @return
	 */
	public static String addLikeOperBoth(String str){
		if(null != str && !"".equals(str)){
			str = "%" + str + "%";
		}
		return str;
	}
//	/**
//	 * 
//	 * 描述：获得超链接信息的一个方法
//	 * 2013-1-8 上午11:04:12 by ygq
//	 * @version
//	 * @return
//	 */
//	public String getExcelExp(){
//		String vartitle = "<pre>在职员工列表\t\t\t\t\t\t\t\t\t\t\t\t\t"
//			+"<a href='<%=basePath %>upload/upload!exportExcel.action?begin=${param.begin}"
//			+"&end=${param.end}&receiver=${param.receiver}&departname=${param.departname}"
//			+"&postname=${param.postname}&theme=${param.theme}&typeid=${param.typeid}&wayid=${param.wayid}'>"
//			+"生成excel文件</a></pre>";
//		return vartitle;
//	}
	/**
	 * 将Object的字符串转换成字符串(null的转换成空字符串)
	 */
	public static String objToString(Object obj){
		if(obj == null){
			return "";
		}else if(obj.equals("")){
			return "";
		}else{
			return obj.toString().trim();//去掉空格
		}
	}
	/**
	 * 将Object的字符串转换成字符串(null的就是返回null)
	 */
	public static String objToString2(Object obj){
		if(obj == null){
			return null;
		}else{
			return obj.toString();//去掉空格
		}
	}
	/**
	 * 将Object的字符串转换成Long
	 */
	public static Long objToLong(Object obj){
		if(obj == null){
			return null;
		}else{
			return Long.parseLong(obj.toString());//去掉空格
		}
	}
	/**
	 * 将Object的字符串转换成整数
	 */
	public static Integer objToInteger(Object obj){
		try{
			if(obj == null||"null".equals(obj)||"".equals(obj)){
				return null;
			}else{
				return Integer.parseInt(obj.toString());//去掉空格
			}
		}catch(Exception ex){
			return null;
		}
	}
	/***
	 * 
	 * 描述：将字符串转换成json格式
	 * 为null或者list的为空的情况，json的包已经做处理的了，不用再另行处理了
	 * 2013-10-14 下午03:50:38 by ygq
	 * @version
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String convertListToGson(List list){
		Gson g = new GsonBuilder().create();
		String json = g.toJson(list);
		return json;
	}
	/***
	 * 
	 * 描述：如何把Json转换成Map
	 * 为null或者list的为空的情况，json的包已经做处理的了，不用再另行处理了
	 * 2013-10-14 下午03:50:38 by ygq
	 * @version
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map convertJsonToMap(String json){
	    Gson gson = new Gson();        
	    Map map = gson.fromJson(json, HashMap.class);
	    return map;
	}
	/***
	 * 获得数组中某个元素的信息
	 * @param obj
	 * @return
	 */
	public static String getArrayInfo(String[] obj,int index){
		try{
			if(obj == null||"null".equals(obj)||"".equals(obj)){
				return null;
			}else if(index <obj.length){
				return obj[index];//去掉空格
			}else{
				return null;
			}
		}catch(Exception ex){
			return null;
		}
	}
	/***
	 * html传递中文到后台乱码问题解决方案
	 * @param encode
	 * @return
	 */
	public static String encode(String encode){
		if(null == encode || "".equals(encode)){
			return encode;
		}
		String decode = "";
		try{
			 CharsetEncoder ch = Charset.forName("GBK").newEncoder();   
			 if(ch.canEncode(encode)){   
				 decode = encode;   
			 }else{   
				 if(encode != null && !"".equals(encode)){   
					 decode = new String(encode.getBytes("ISO-8859-1"),"GBK");   
				  }   
			 }   
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return decode;
	}
	
	public static void main(String[] args) {
//		List list = null;
//		Gson g = new GsonBuilder().create();
//		String json = g.toJson(list);
//		System.out.println(json);
//		String str = StringUtil.encode("杨高权");
//		System.out.println(str);
//		String json = "{'data1':100,'data2':'hello','id':1}";
//		Map map = convertJsonToMap(json);
//		System.out.println(map.toString()); 
	}
}
