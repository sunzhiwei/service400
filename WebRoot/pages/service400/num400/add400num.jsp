<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<title>400号码新增</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">

	function clear(){
		$('#ser400_phonenum').val("");
		$('#company').val("");
	}
	// 设置ajax同步
    $.ajaxSetup({  
        async : false  
    });
    var flag; //
	isrepeatName=function(pn)
	    {
		  $.post(
  	  	            "<%=basePath%>num400Action_isrepeatnum.action",
  	  	            {phonenum:pn},
  					function(data){
      					flag=data;
  					}
  				);
			if(flag==0)
				return false;
			else
			    return true;
    };
	function checkAddNum(){
		var flag = true;
		var phonenum = $("#phonenum");
		var tit = $("#tit").val();
		var num = $("#num").val();
		var pn =tit+""+num; 
		var re = /^\d{10}$/;
		if(!re.test(pn)){
			$.messager.alert("提示", "您输入的400号码不符合格式，请输入7位数字号码！");
			flag = false;
			return flag;
		}else{
			if(isrepeatName(pn) == true)
				{
	  		      $.messager.alert("提示", "您输入的400号码与数据库中有重复，号码不允许重复，请修改！");
	  		      flag = false;
	  		      return flag;
	  		     }else
		  		 {
	  		    	phonenum.val(pn);
	  		     }
		}
		
		var price = $("#price").val();
		re = /^\d{2,4}(\.\d{1,2})?$/;
		if(!re.test(price)){
			$.messager.alert("提示", "月最低消费额请填写数字且最多两位小数！");
			flag = false;
			return flag;
		}
		var operationCompany=$("#operationCompany").val();
		if(flag){
			$.post(
  	  	            "<%=basePath%>num400Action_add400ajax.action",
  	  	            {phonenum:pn,price:price,operationCompany:operationCompany},
  					function(data){
      					//flag=data;
  					}
  				);
			
			parent.window.returnValue=$("#num").val();
			window.close();
			//document.getElementById("addNum").submit();
		}
	}
	
   </script>
</head>
	<body>
	
	
	
	<form id="addNum" action="<%=basePath%>num400Action_add400ajax.action">
			<table>
				<tr>
					<td class="t-title">400号码：<input id="tit" value="400" disabled="disabled" maxlength="4" style="width: 30px" />-
								<input class="t-text" id="num">
						<input type="hidden" id="phonenum" name="ts.phonenum" maxlength="7" value="">
					</td>
					</tr>
					<tr>
					<td class="t-title">月最低消费额(元/月)：<input class="t-text" id="price" name="ts.price" value=""></td>
					</tr>
					<tr>
					<td class="t-title">运营商：
						<select name="ts.operationCompany" id="operationCompany">
							<option selected="selected" value="YD">移动</option>
							<option value="DX">电信</option>
							<option value="LT">联通</option>
						</select>
					</td>
					</tr>
					<tr>
					<td>
					<div class="t-but">
					<a href="javascript:void(0)" class="but-change" onclick="return checkAddNum();" plain="true">添加</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="clear()" plain="true">取消</a>
					</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
