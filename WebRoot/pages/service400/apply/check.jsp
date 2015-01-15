<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>400号码申请表审核</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${path}/js/jquery-1.7.1.min.js"></script>
</head>
<body>
	<fieldset>
		<legend>400号码申请审核</legend>
		<table>
			<tr>
				<th>申请人</th>
				<th>申请人邮箱</th>
				<th>申请号码</th>
				<th>号码最低消费</th>
				<th>部门负责人邮箱</th>
				<th>预计开通时间</th>
				<th>公司部门</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${tsList}" var="tsl">
			<tr>
				<td>${tsl.applicationperson }</td>
				<td>${tsl.applicationpersonEmail }</td>
				<td>${tsl.phonenum }</td>
				<td>${tsl.phonenumPrice }</td>
				<td>${tsl.departmentheaderEmail }</td>
				<td>${tsl.opentime }</td>
				<td>${tsl.company }-${tsl.department }</td>
				<td>
					<c:if test="${tsl.status==0 }">
						<a href="${path}/apply400Action_conApp.action?ts.id=${tsl.id}">确认申请</a>
					</c:if>
					<c:if test="${tsl.status==1 }">
						<a href="${path}/apply400Action_conApp.action?ts.id=${tsl.id}">补充申请</a>
						<a href="${path}/apply400Action_clsLoop.action?ts.id=${tsl.id}">确认完成</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</fieldset>
</body>
</html>
