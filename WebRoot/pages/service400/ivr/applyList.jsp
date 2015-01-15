<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<title>可配置IVR号码列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_z/new_z.css">
</head>
<body style="text-align:center;">
	<div style="width: 60%;text-align: center; margin: 70px auto;">
		<div style="text-align:left" class="ivr_table_top">可配置IVR的400号码列表</div>
		<div class="sq_box">
			<table cellspacing="0">
				<tr class="table_head">
					<th>400号码</th>
					<th>400号码名称</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${tsList}" var="ts">
				<tr>
					<td>${ts.phonenum }</td>
					<td class="green">${ts.numcategory}</td>
					<td><a style="border:none;" href="<%=request.getContextPath()%>/ivr400Action_showIVR.action?key.path=${ts.phonenum}&key.pid=${ts.id}"><img style="border:none;" src=<%=request.getContextPath()%>/images/caozuo.png></a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>