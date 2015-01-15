<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>  
<title>400号码申请及IVR维护系统</title>
<link href="<%=basePath %>images/skin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
if ( window.top != window.self ) {
	window.top.location.href = "${pageContext.request.contextPath}/index.jsp";
}
</script>
<script type="text/javascript">  
    function changeImg(){       
        $("#authCode").attr("src","<%=request.getContextPath() %>/userAction_test.action?d="+new Date().valueOf());       
    }  
 </script> 
    <script type="text/javascript"> 

    function check(){
        
		var username = document.getElementById("username").value;
		
		var password = document.getElementById("password").value;
		//var myCode = document.getElementById("myCode").value;
		if(username==""){
			   alert("请输入用户名！");
			   return false;
			   } 
	        if(password==""){
			   alert("请输入密码！");
			   return false;
			   }
			//if(myCode==""){
			//	 alert("请输入验证码！");
				//  return false;
				//}
	                               
	return true; 
		

        }
    </script> 
</head>
<body class="body">
<div class="login-box">
        <span class="login_txt_bt"></span>
        <form name="myform" action="<%=basePath %>userAction_login.action" onsubmit="return check();" method="post" id="J_StaticForm">
            <div class="field username-field">
                <input class="login-text J_UserName" id="username" name="username" value="${username }" size="20" >           	
                <span id="J_NickX1385020956995" class="nickx" href="javascript:void(0)" style="display: none;"></span>
           </div>
           <div class="field pwd-field">
                <input class="login-text J_Pwd" id="password" type="password" value="${password }" size="20" name="password" >
                <span class="error">${error2 }${msg } </span>  
           </div><%--
           <div id="l_f_code" class="field field-checkcode">
                <input class="login-text checkcode J_CheckCode" name="myCode" id="myCode" size="20">
                      
                <img src="<%=basePath %>userAction_test.action" alt="验证码" id="authCode" onclick="changeImg()" />   
       								 <a href="#" onclick="changeImg()">看不清，换一张！</a>   
 </div>
           --%><input name="Submit" type="submit" class="button" id="Submit" value="">
        </form>
</div>
<div class="login_footer"><span class="login-buttom-txt">Copyright &copy; 2013 www.creditease.cn</span></div>  

</body>
</html>
 