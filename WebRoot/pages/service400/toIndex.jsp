<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
<head>
	<title>400号码申请及IVR维护系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/index.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript"> 
		$(function() {
			tabCloseEven();
			$('.cs-navi-tab,#changePwdBtn').click(function() {
				var $this = $(this);
				var href = $this.attr('src');
				var title = $this.text();
				addTab(title, href);
			});
		});
	</script>
</head>
	<body class="easyui-layout" >
		<div region='north' border="true" class="cs-north">
        	<div class="cs-north-bg">
                <div class="cs-north-logo"></div>
                <ul class="ui-skin-nav">				
                    <li class="li-skinitem">${user.username }&nbsp;&nbsp;您好，感谢登陆使用！</li>
                    <li class="li-skinitem"><a id="changePwdBtn" href="javascript:void(0)" src="<%=basePath%>user400Action_updatePWDUI.action" 
						 plain="true"><span class="icon alterpwd">修改密码</span></a></li>
                    <li class="li-skinitem" style="margin-left:20px;"><a href="#" plain="true" onclick="outLogon();"><span class="icon exit">安全退出</span></a></li>
                </ul>	
            </div>
		</div>  
	    <div region='west' title='  ' expand=false split=true style="width:210px; background:#434649">
	    	<div class="easyui-accordion" fit="true" border="false">
		    	<c:forEach items="${firstLelelMenus}" var="firstLelelMenu" varStatus="status">
		    		<div title="${firstLelelMenu.name }" <c:if test='status==0'>selected="true"</c:if>>
			    		<div class="cs-div"><c:forEach items="${secondLelelMenus}" var="secondLelelMenu">
			    			<c:if test="${secondLelelMenu.pid == firstLelelMenu.id }">
		          				<a src="<%=basePath%>${secondLelelMenu.url==null?'javascript:void(0)': secondLelelMenu.url}" 
	          						class="cs-navi-tab" href="javascript:void(0);">${secondLelelMenu.name }</a><br/>
			    			</c:if>
			    		</c:forEach></div>
			   		</div>
		   		</c:forEach>
   			</div>
	    </div>  
	    <div id="mainPanle" region="center" border="false">
	 		<div id="tabs" class="easyui-tabs"  fit="true" border="false" style="height: 700px;">
	            <div title="主页">
					<div class="cs-home-remark">
						<img src="<%=request.getContextPath() %>/images/welcome.jpg">
					</div>
				</div>
        	</div>
		</div>
		<div region='south' style="height:20px;" class="cs-south">
	    	@版权所有;2014-2015 Vision_1.0_07022014, guyezi123@163.com.
    	</div> 
		<div id="mm" class="easyui-menu cs-tab-menu">
			<div id="mm-tabupdate">刷新</div>
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseother">关闭其他</div>
			<div id="mm-tabcloseall">关闭全部</div>
		</div>
	</body>
	<script type="text/javascript">
	function outLogon() {
		location.replace("${pageContext.request.contextPath}/userAction_exitLogin.action"); //outLogon即是你所要转的退出登录的Action地址
	}
	</script>
</html>
