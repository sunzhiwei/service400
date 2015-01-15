<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>IVR维护申请信息查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
		function closeLoop(id){
			window.location.href='${path}/query400Action_closeLoopByID.action?tsf.id='+id;
		}
	</script>
  </head>
  <body>
    <fieldset>
    	<legend>IVR维护申请信息</legend>
    	<form method="post" action="${path}/query400Action_queryHis.action">
    	<table>
    		<tr>
    			<td>发送申请时间</td>
    			<td>
    				<input name="time1" value="${time1}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px">
    				-
    				<input name="time2" value="${time2}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px">
    			</td>
    			<td>完成时间</td>
    			<td>
    				<input  value="${time3}" name="time3" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px">
    				-
    				<input name="time4" value="${time4}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px">
    			</td>
    			<td><input type="submit" value="查询" /></td>
    			<td><input type="reset" value="清空" /></td>
    		</tr>
    	</table>
    	</form>
    	<table>
    		<tr>
    			<td>申请人</td>
    			<td>申请时间</td>
    			<td>查看信息</td>
    		</tr>
    		<c:forEach items="${list}" var="tsfile">
    		<tr>
    			<td>${tsfile.sendperson }</td>
    			<td>${tsfile.sendtime }</td>
    			<td><a href="${tsfile.filepathURL }">申请信息</a></td>
    		</tr>
    		</c:forEach>
    	</table>
    </fieldset>
  </body>
</html>
