<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>工作组查看明细</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
<style type="text/css">
.blue_line tr td{border-bottom:1px dotted #8BDFF8; text-align:left;}
.ge_color{ background:#F5F8FD;}
</style>
</head>
	<body style="text-align:center;">
	<input type="hidden" id="workOverFlow" value="${workOverFlow }"/>
    <input type="hidden" id="noworkOverFlow" value="${noworkOverFlow }"/>
	<div class="tableForm">
	<div class="title" style="text-align:left;">工作组明细预览</div>
	<table class="blue_line" width='552' height="334" style="margin:20px auto; border:1px solid #8BDFF8; font-size:14px;">
		<tr>
			 <td width=31% class="t-title">组名:</td>
			 <td>${workgroup.name }</td>
		 </tr>
		 <tr class="ge_color">
			 <td class="t-title">透传号码:</td>
			 <td>
			  ${workgroup.adapternum }
			 </td>
		</tr>
		<tr>
				<td class="t-title">工作周:</td>
				<td>
				${workgroup.startweek }
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
			    ${workgroup.endweek }   
			    </td>
			</tr>
			<tr class="ge_color">
				<td class="t-title">工作时间:</td>
				<td>
				${workgroup.starttime }
				&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
			    ${workgroup.endtime }   
			    </td>
			</tr>
		<tr>
			<td class="t-title">工作时溢出组:</td>
		    <td>${workgroup.workoverflowGroup }</td>
	    </tr>
		    <tr class="ge_color">
			    <td class="t-title">工作时忙音:</td>
			    <td>
			    ${workgroup.busyTone }
			    </td>
		    </tr>
	<tr class="blue_line">
		<td class="t-title">非工作时溢出组:</td>
	    <td> 
	    ${workgroup.noworkoverflowGroup }
	    </td>
    </tr>
	<tr>
	    <td class="t-title">非工作时语音:</td>
	    <td>${workgroup.noworkvoice }</td>
	</tr>
	
</table>
<div class="t-but" style="text-align: center;">
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">返回</a>
            </div>

	</div>
	</body>
	</html>
