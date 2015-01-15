<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>send email status</title>
</head>
<body>
<fieldset>
	<legend>申请信息</legend>
	<c:if test="${flag==-1}">
		申请成功，申请邮件已经发送，请耐心等待。
		<a href="${path}/apply400Action_apply400.action">继续申请</a>
	</c:if>
	<c:if test="${flag==1}">
		申请不成功，请重试或联系管理员。
		<a href="${path}/apply400Action_apply400.action">继续申请</a>
	</c:if>
</fieldset>
</body>
</html>