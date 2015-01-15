<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改密码</title>
<style type="text/css"> table tr td{font-size: 12px;} </style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
<script type="text/javascript">

	$(function(){
		$("#oldPwd,#newPwd,#newPwdAgain").validatebox({required: true,missingMessage: '必选'});
		$("#newPwdAgain").validatebox({validType:"same['newPwd']"});
	})
	
	
//设置ajax同步
$.ajaxSetup({  
    async : false  
});
var flag; //
checkpassword=function(oldpassword)
    {
	  $.post(
	  	            "<%=basePath%>user400Action_checkPassword.action",
	  	            {oldpassword:oldpassword},
					function(data){
  					  flag=data;
					}
				);
		if(flag==0)
			return true;
		else
		    return false;
};

function submitTab_new(id,title){
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			var oldpassword=$("#oldPwd").val();
			if(oldpassword==""){
				$.messager.alert("提示", "请您输入原密码！");
				return false;
			}
			var result1=checkpassword(oldpassword);
			if(result1== true){
				$.messager.alert("提示", "您输入的原密码不正确！");
				return false;
			}
			if(result == true){
				if(id){
					$("#"+id).attr("disabled","disabled");
					$("#"+id).removeAttr('onclick');
				} 
				$("#editForm").ajaxSubmit({
					dataType:"text",
		 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
								toJson(json).success == "true"?"info":"error",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								if(refrTab){
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
											})
								}
								
							}
							closeTab();
						});
		 			}
		 		});	
			}else{
				if(id) $("#"+id).removeAttr("disabled");
			}
			return false;
		}
	})
}
</script>
</head>
<body>
	<div class="tableForm">
	<form action="<%=basePath %>user400Action_updatePWD.action" method="post" id="editForm">
		<input type="hidden" id="userid" name="ts.id" value="${sessionScope.user.id }"/>
        <div class="title">修改密码</div>
		<table>
			<tr><td class="t-title">用户名</td>
				<td><input class="t-text required" type="text" disabled="disabled" value="${sessionScope.user.username}"/>
					<input type="hidden" name="ts.username" value="${sessionScope.user.username}"/>
				</td>
			</tr>
			<tr>
				<td class="t-title">请输入原密码</td>
				<td><input class="t-text required" type="password" name="oldpassword" id="oldPwd"/></td>
			</tr>
			<tr>
				<td class="t-title">请输入新密码</td>
				<td><input class="t-text required" type="password" name="ts.password" id="newPwd"/></td>
			</tr>
			<tr>
				<td class="t-title">请再次输入新密码</td>
				<td><input class="t-text required" type="password" id="newPwdAgain"/></td>
			</tr>
		</table>
        <div class="t-but">	
		    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab();"  plain="true">取消</a>
			<a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab_new('editBtn')" plain="true">修改</a>
		</div>
	</form>
    </div>
</body>
</html>
