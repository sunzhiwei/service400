<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
		<title>添加用户</title>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){		
			$("#username").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			var role_init=$("input[name='roleidlist']:checked").val();;
			if(role_init==1){
				$("#400ser_num").css("display","none");
			}
		});

		 // 设置ajax同步
        $.ajaxSetup({  
            async : false  
        });
		function usersubmit(title){
			var roleidlist= document.getElementsByName("roleidlist");
			var service400Idlist=document.getElementsByName("service400Idlist");
			var isSel2 =""; //判断角色项是否有选中项
			var username=$("#username").val();
			var checkuserid=$("#userid").val();
			for(var j=0;j<roleidlist.length;j++){
				if(roleidlist[j].checked==true){
					isSel2+=roleidlist[j].value+",";
					}
					}
			if(isSel2 == ""){
				$("#rolenum").val("0");
			}else{
				$("#rolenum").val("1");
			}

			$('#updateUserForm').form('submit',{
				onSubmit: function(){
					var result = $(this).form('validate');
					var result1=true;
					var result2=true;
					var result3=true;

					String.prototype.endWith=function(str){
						if(str==null||str==""||this.length==0||str.length>this.length)
						  return false;
						if(this.substring(this.length-str.length)==str)
						  return true;
						else
						  return false;
						return true;
						};
				    if(result==true && username.endWith("@creditease.cn")==false){
					        $.messager.alert("提示", "用户名需以宜信邮箱@creditease.cn结尾!","info");
						    result3=false;
						}
					
					if($("#rolenum").val()=="0"){
						 $.messager.alert("提示", "请选择角色!","info");
						result1=false;
					};
					var flag;
					checkUserExist=function(username,userid)
			  	    {
			  		  $.post(
			      	  	            "<%=basePath%>user400Action_checkUserExist.action",
			      	  	            {checkusername:username,checkuserid:checkuserid},
			      					function(data){
			          					flag=data;
			      					}
			      				);
			  			if(flag==0)
			  				return true;
			  			else
			  			    return false;
			        };

	                if(result==true){
	                	result2=checkUserExist(username,checkuserid);
	                	if(result2==false)
	                		$.messager.alert("提示", "用户名与库中重复，请修改!","error");
	                }
					
					if(result == true && result1==true && result2==true && result3==true){
						$("#updateUserForm").ajaxSubmit({
				 			success:function(){
								$.messager.alert("提示","操作成功！","info",function(){
									if(title!=undefined && title!=null && title!=''){
										var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
										var url = $(refrTab.panel('options').content).attr('src');
											getTopWin().$('#tabs').tabs('update',{
												tab:refrTab,options:{content:createFrame(url)}
										})
									}
									closeTab();
								})
				 			}
				 		});	
					}
					return false;
				}
			})
		}

		function radiochange(obj){
			var role=obj.value;
			if(role==1){
				$("#400ser_num").css("display","none");
			}else{
				$("#400ser_num").css("display","block");
			}
		}
		
		</script>
		
	</head>
	<body style="text-align: center;">
    	<div class="tableForm">
			<form action="<%=basePath %>user400Action_update.action" method="post" id="updateUserForm">
            	<div style="text-align:left" class="title">修改用户</div>
				<table style="margin: 30px auto; ">
				 <div class="t-but" style="text-align: center; border-top: #ccdae8 solid 1px; padding-top:20px;">
					<a style="margin-right: 20px;" href="javascript:void(0)" class="but-change" onclick="usersubmit('用户列表')" plain="true">修改</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
			    </div>
					<tr>
						 <input type="hidden"  id="userid" name="ts.id" value="${ts.id }"/>
						<td  style="padding-right: 0;" class="t-title" width="60">用户名：</td>
                        <td>
							<input style="width:265px" class="t-text" type="text"  id="username" name="ts.username" value="${ts.username}"/><span class="required">*</span>
						</td>
					</tr>
					<input type="hidden" id="password" name="ts.password" value="${ts.password}"/>
						<tr>
						<td style="padding-top: 14px; padding-right: 0;" class="t-title">角色：</td>
                        <td>
                          <ul>
						    <c:forEach var="role" items="${rolelist}">
                                 <li>
                                   <input style="position:relative; top:3px;" type="radio" name="roleidlist" onchange="radiochange(this)" value="${role.id}" 
                                     <c:forEach var="userrole" items="${userroleidList}">
                                        <c:if test="${role.id == userrole}">
                                         checked = "checked"
                                        </c:if >
                                     </c:forEach>
                                   >${role.name}</input>
                                   </li>
                                </c:forEach>
						    <input type="hidden" id="rolenum" />
                          </ul>
						</td>
					</tr>
					<tr id="400ser_num">
					 <td class="t-title">400号码</td>
					 <td>
					 <div>
					   <table style="border:1px solid #C7D3E2; width:600px; margin:0">
					     <tr style=" line-height:20px;">
					       <td style="border-bottom:1px solid #C7D3E2;border-right:1px dotted #C7D3E2;text-align:center; background:#EAF7FD">操作</td>
					       <td style="border-bottom:1px solid #C7D3E2;border-right:1px dotted #C7D3E2;text-align:center; background:#EAF7FD">号码</td>
					       <td style="border-bottom:1px solid #C7D3E2;border-right:1px dotted #C7D3E2;text-align:center; background:#EAF7FD">类型</td>
					     </tr>
					     <c:forEach var="appform" items="${appformlist}">
					     <tr style="line-height:40px">
					     <td width="10%" style="text-align: center; border-right:1px dotted #C7D3E2;border-bottom:1px dotted #C7D3E2" bgcolor=#EAF7FD>
						  	 <input type="checkbox" name="service400Idlist" value="${appform.service400Id}" 
						  	 <c:forEach var="userservice400" items="${user400numserviceidList}">
                                        <c:if test="${appform.service400Id == userservice400}">
                                         checked = "checked"
                                        </c:if >
                                     </c:forEach>
                             />
						 </td>
						 <td style="color:#72A04B; text-align: center; border-bottom:1px dotted #C7D3E2;border-right:1px dotted #C7D3E2;">
						   ${appform.phonenum }
						 </td>
						 <td style="text-align: center; border-bottom:1px dotted #C7D3E2">
						   ${appform.numcategory }
						 </td>
						 </tr>
						    </c:forEach>
					   </table>
					   </div>
					 </td>
					</tr>
				</table>
			</form>
         </div>
	</body>
</html>
