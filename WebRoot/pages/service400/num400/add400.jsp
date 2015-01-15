<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
		function addTr(){
			window.open("add400.jsp");
		}
	</script>
</head>
<body>
	<div style="width: 80%;height: 80%;margin: auto;">
	<fieldset>
		<legend>可申请400号码</legend>
		<table>
			<tr>
				<td><input type="button" onclick="addTr()" value="添加"></td>
			</tr>
		</table>
		<table id="num" style="width:100%;height:auto;text-align: center;border: 1px dotted gray;">
			<thead>
				<tr style="background-image: url('');">
					<th>编号</th>
					<th>400号码</th>
					<th>最底消费</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="ts">
					<tr style="background-color: gray;">
						<td>${ts.id}</td>
						<td>${ts.id}</td>
						<td>${ts.id}</td>
						<td>${ts.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	</div>
</body>
</html>