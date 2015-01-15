<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<title>IVR展示</title>
<!--[if IE 9]>
		<style type="text/css">
		#noner{ margin-top:-18px;}
		#anjian{ padding-left:15px;}
		</style>
<![endif]-->
<style type="text/css">
		.buf{ margin:10px;}
</style>
  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/new_z/new_z.css">
<style class="csscreations">
/*Now the CSS*/
* {
	margin: 0;
	padding: 0;
}

.newtree ul {
	padding-top: 20px;
	position: relative;
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

.newtree li {
	float: left;
	text-align: center;
	list-style-type: none;
	position: relative;
	padding: 20px 5px 0px 5px;
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*We will use ::before and ::after to draw the connectors*/
.newtree li::before,.newtree li::after {
	content: '';
	position: absolute;
	top: 0;
	right: 50%;
	border-top: 1px solid #ccc;
	width: 50%;
	height: 20px;
}

.newtree li::after {
	right: auto;
	left: 50%;
	border-left: 1px solid #ccc;
}

/*We need to remove left-right connectors from elements without 
any siblings*/
.newtree li:only-child::after,.newtree li:only-child::before {
	display: none;
}

/*Remove space from the top of single children*/
.newtree li:only-child {
	padding-top: 0;
}

/*Remove left connector from first child and 
right connector from last child*/
.newtree li:first-child::before,.newtree li:last-child::after {
	border: 0 none;
}
/*Adding back the vertical connector to the last nodes*/
.newtree li:last-child::before {
	border-right: 1px solid #ccc;
	border-radius: 0 5px 0 0;
	-webkit-border-radius: 0 5px 0 0;
	-moz-border-radius: 0 5px 0 0;
}

.newtree li:first-child::after {
	border-radius: 5px 0 0 0;
	-webkit-border-radius: 5px 0 0 0;
	-moz-border-radius: 5px 0 0 0;
}

/*Time to add downward connectors from parents*/
.newtree ul ul::before {
	content: '';
	position: absolute;
	top: 0;
	left: 50%;
	border-left: 1px solid #ccc;
	width: 0;
	height: 20px;
}

.newtree li a {
	border: 1px solid #ccc;
	padding: 5px 10px;
	text-decoration: none;
	color: #666;
	font-family: arial, verdana, tahoma;
	font-size: 11px;
	display: inline-block;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*Time for some hover effects*/
/*We will apply the hover effect the the lineage of the element also*/
.newtree li a:hover,.newtree li a:hover+ul li a {
	background: #c8e4f8;
	color: #000;
	border: 1px solid #94a0b4;
}
/*Connector styles on hover*/
.newtree li a:hover+ul li::after,.newtree li a:hover+ul li::before,.newtree li a:hover+ul::before,.newtree li a:hover+ul ul::before
	{
	border-color: #94a0b4;
}
.show{
	display: none;
	position: absolute;
    top: 50px;
    float: left;
    position: absolute;
    width:354px;
    height:254px;
    background: url('images/table_bgtwo.png') 0 0 no-repeat;
    left:100px;
}

</style>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function addFirst(id){
	$("#addKey").css("display","block");
	$("#menu").css("display","none");
	$("#update").css("display","none");
	$("#group").css("display","none");
	$("#addPid").val(id);
	$("#addName").html("<option value='11' selected='selected'>首问语</option>");
	$("#add").css("display","block");
}
function noAddKey(){
	$("#addKey").css("display","none");
}
function addKey(){
	$("#addKey").css("display","block");
	$("#menu").css("display","block");
	$("#update").css("display","none");
	$("#add").css("display","block");
	$("#group").css("display","none");
}
function updateKey(){
	$("#addKey").css("display","block");
	$("#menu").css("display","block");
	$("#add").css("display","none");
	$("#group").css("display","none");
	var id= $("#updateId").val();
	$.post("<%=request.getContextPath()%>/ivr400Action_keyNameSelf.action?key.id="+id, function(data){
		 var dataArr = data.split("-");
		  $("#updateName").append(dataArr[0]);
		  $("#updateContent").val(dataArr[1]);
	});
	$("#update").css("display","block");
}
function clickNode(id){
	$("#addKey").css("display","block");
	$("#menu").css("display","block");
	$("#update").css("display","none");
	$("#group").css("display","none");
	$("#addPid").val(id);
	$("#updateId").val(id);
	$("#deleteId").val(id);
	$("#ivrid").val(id);
	$.post("<%=request.getContextPath()%>/ivr400Action_keyName.action?key.id="+id, function(data){
		var dataArr = data.split("-");
		  var da = dataArr[0].split(";");
		  if(da==null||""==da){
			  updateKey();
			  $("#addButtion").css("display","none");
		  }else{
			  $("#addButtion").css("display","inline");
			  $("#addName").html("");
			  for(var i=0;i<da.length;i++){
				  if(da[i]!=""&&da[i]!=null){
					$("#addName").append("<option value='"+da[i]+"' selected='selected'>"+da[i]+"</option>");
				  }
			  }
		  }
		  if(dataArr[1]=="true"){
			  $("#groupButton").css("display","inline");
		  }else{
			  $("#groupButton").css("display","none");
		  }
	});
	$("#add").css("display","block");
}
function addGroup(){
	$("#add").css("display","none");
	$("#menu").css("display","block");
	$("#update").css("display","none");
	$("#group").css("display","block");
	$.post("<%=request.getContextPath()%>/workGroupAction_groupMessage.action", function(data){
		$("#groupid").append(data);
	});
	
}
function deleteKey(){
	$.messager.confirm("操作提醒","您确认要删除此节点吗？",function(data){
		if(data){
			var id = $("#deleteId").val();
			window.location.href="<%=request.getContextPath()%>/ivr400Action_deleteKey.action?key.id="+id;
		}
	});
}
 // 设置ajax同步
    $.ajaxSetup({  
        async : false  
    });
function submitIVR(){
	$.messager.confirm("操作提醒","您确认要提交IVR修改申请发送邮件吗？",function(message){
		if(message){
			var phonenum=$("#phonenum").val();
			$.post("<%=request.getContextPath()%>/ivr400Action_sendIVRMailajax.action?phonenum="+phonenum,
							function(data){
			  	               if(data==1){
			  	            	 $.messager.alert("提示","IVR提交修改申请邮件发送成功！","info",function(){
		             		    	 window.location.href="<%=request.getContextPath()%>/ivr400Action_show.action";
		  							});
		  						}else{
		  						 $.messager.alert("提示","你未对IVR进行任何修改等操作，邮件未发送，请您对IVR操作后再提交修改申请！","info",function(){
		             		    	 window.location.href="<%=request.getContextPath()%>/ivr400Action_show.action";
		  					});}}
				 );
		}
	});
}
function clickGroup(groupid,id){
	$.messager.confirm("操作提醒","您确认删除此工作组吗？",function(data){
		if(data){
			window.location.href="<%=request.getContextPath()%>/ivr400Action_deleteGroup.action?groupid="+groupid+"&key.id="+id;
		}
	});
}
function showGroupDiv(id){
	$("table[name='divTable']").css("display","none");
	$("#"+id).css("display","block");
}
function showGroup(obj){
	var temp = $("#temp").val();
	if(temp!=""){
		$("#id_"+temp).css("display","none");
	}
	$("#temp").val(obj.value);
	if(obj.value!=""){
		$("#id_"+obj.value).css("display","block");
	}
}
</script>
</head>
<body>
	<input id="temp" value="" type="hidden">
	<input  type="hidden"  value="${ts_phonenum }" id="phonenum"/>
	<div style="float: left;width: 70%;height: 480px; overflow:scroll; background:#FFF; " id="tree">
		<div style="width:7200px;">
			<legend class="two_tij" style="cursor: pointer; margin:0">
			<div style="width:720px; background:#FAFAFA; height:34px; line-height:34px; border:1px solid #EBEBEB; text-align:right">
				<c:if test="${is11}">
					<input value="开始IVR维护" type="button" style="font-size:12px; color:#517335; line-height:24px; font-weight:bold; padding:5px; cursor: pointer;" onclick="addFirst('${ts_id}')">
					<label title="无首问语时点击可添加" style="font-size:12px; color:#517335; line-height:24px; font-weight:bold; padding:5px; cursor: pointer;">号码：${ts_phonenum }</label>
				</c:if>
				<c:if test="${!is11}">
					<label style="line-height:34px; padding:5px; font-size: 12px; color:#66666">号码：${ts_phonenum }</label>
				</c:if>
				<a class="tjsq" onclick="submitIVR()" href="javascript:void(0);">提交修改申请</a>
			</div>
			</legend>
			<div class="newtree">
				${tree}
			</div>
		</div>
	</div>
	
	<div style="position: fixed; overflow-y:scroll; height:480px; width:29%; float: right;right: 0px;z-index: 9999" id="groupList">
		<select style="width: 100%; height: 30px; line-height: 30px; border-top: 3px solid #538ACA;" onchange="showGroup(this)">
			<option value="">---工作组---</option>
			<c:forEach items="${wgList}" var="wg">
			<option value="${wg.id }">${wg.name}</option>
			</c:forEach>		
		</select>
		<c:forEach items="${wgList }" var="wg">
			<div id="id_${wg.id}" style="display: none;">
				<table cellspacing="0" name="divTable" style="background-color:#E1F9FF;border:1px #22CAFB solid;width:100%; font-size:12px;" id="${wg.id}">
					<tr style="height:24px;">
						<td style="background:#C2F0FE; text-align:right; width:35%; padding-right:7px">工作组名：</td>
						<td style="padding-left: 15px;">${wg.name}</td>
					</tr>
					<tr style="height:24px;">
						<td style="background:#C2F0FE; text-align:right; width:35%; padding-right:7px">工作时间：</td>
						<td style="padding-left: 15px;">${wg.starttime}-${wg.endtime }</td>
					</tr>
					<tr style="height:24px;">
						<td style="background:#C2F0FE;padding-right:7px; text-align:right">透传号码：</td>
						<td style="padding-left: 15px;">${wg.adapternum}</td>
					</tr>
					<c:if test="${wg.workisoverflow==7 }">
					<tr style="height:24px;">
						<td  style="background:#C2F0FE;padding-right:7px; text-align:right">工作时是否溢出：</td>
						<td style="padding-left: 15px;">是</td>
					</tr>
					<tr style="height:24px;">
						<td  style="background:#C2F0FE;padding-right:7px; text-align:right">工作溢出组：</td>
						<td style="padding-left: 15px;">${wg.workoverflowGroup}</td>
					</tr>
					</c:if>
					<c:if test="${wg.workisoverflow==8 }">
					<tr style="height:24px;">
						<td  style="background:#C2F0FE;padding-right:7px; text-align:right">工作时是否溢出：</td>
						<td style="padding-left: 15px;">否</td>
					</tr>
					</c:if>
					<tr style="height:24px;">
						<td style="background:#C2F0FE; padding-right:7px; text-align:right">工作时忙音：</td>
						<td style="padding-left: 15px;">${wg.busyTone}</td>
					</tr>
					<c:if test="${wg.noworkisoverflow==7}">
					<tr style="height:24px;">
						<td  style="background:#C2F0FE;padding-right:7px; text-align:right">非工作时是否溢出：</td>
						<td style="padding-left: 15px;">是</td>
					</tr>
					<tr style="height:24px;">
						<td style="background:#C2F0FE; padding-right:7px; text-align:right">非工作溢出组：</td>
						<td style="padding-left: 15px;">${wg.noworkoverflowGroup}</td>
					</tr>
					</c:if>
					<c:if test="${wg.noworkisoverflow==8}">
					<tr style="height:24px;">
						<td  style="background:#C2F0FE;padding-right:7px; text-align:right">非工作时是否溢出：</td>
						<td style="padding-left: 15px;">否</td>
					</tr>
					<tr style="height:24px;">
						<td style="background:#C2F0FE; padding-right:7px; text-align:right">非工作时语音：</td>
						<td style="padding-left: 15px;">${wg.noworkvoice}</td>
					</tr>
					</c:if>
				</table>
			</div>
		</c:forEach>
	</div>
	
	<div style="clear:both"></div>
	<div class="show" onblur="noAddKey()" id="addKey">
		<table style="width:354px; height:224px;">
				<tr style="height:24px">
					<td>
						<input style="float:right;margin-top: 5px;" type="image" src="<%=request.getContextPath()%>/images/t_7.png" onclick="noAddKey()" value="关闭" />
					</td>
				</tr>
			<tr style="height:40px">
				<td style="padding-left:15px;" id="menu">
					<input id="addButtion" value="增加" type="image" src="<%=request.getContextPath()%>/images/t_1.png" onclick="addKey()"/>
					<input value="修改" type="image" src="<%=request.getContextPath()%>/images/t_2.png" onclick="updateKey()"/>
					<input value="删除" type="image" src="<%=request.getContextPath()%>/images/t_3.png" onclick="deleteKey()" />
					<input id="groupButton" value="添加工作组" src="<%=request.getContextPath()%>/images/t_4.png" type="image" onclick="addGroup()" />
				</td>
				
			</tr>
			<tr id="add">
				<td colspan="4">
					<form style="width:354px;margin:0 auto" method="post" action="<%=request.getContextPath()%>/ivr400Action_addKey.action?">
					<input type="hidden"  value="${ts_phonenum }" name="phonenum"/>
						<table  width=354  style="margin:0px auto; height:auto">
							<tr>
								<td colspan="2" style="line-height:34px" class="some" id="anjian">按建名：</td>
								<td><input type="hidden" value="" id="addPid" name="key.pid">
									<select id="addName" name="key.name"></select>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="some" id="anjian">语音内容：</td>
								<td>
									<textarea style="height:60px" rows="4" id="addContent" cols="24" name="key.content"></textarea>
								</td>
							</tr>
							<tr>
	                         
								<td colspan="4" style="text-align:center">
								<div style="font-size:0" class="line">&nbsp;</div>
									<img class="czsp" onclick="javascript:$('#addContent').val('');" alt="重置" src="<%=request.getContextPath()%>/images/t_6.png">
									<input class="czsp" type="image" src="<%=request.getContextPath()%>/images/t_5.png" value="添加" />
								</td>
								
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr style="display: none" id="update">
				<td colspan="4">
					<form style="width:346px;" method="post" action="<%=request.getContextPath()%>/ivr400Action_updateKey.action">
					<input  type="hidden"  value="${ts_phonenum }" name="phonenum"/>
						<table  width=354 style="margin:0px auto">
							<tr>
								<td  style="line-height:34px" class="some" id="anjian">按建名：</td>
								<td><input type="hidden" value="" id="updateId" name="key.id">
									<select id="updateName" name="key.name"></select>
								</td>
							</tr>
							<tr>
								<td  class="some" id="anjian">语音内容：</td>
								<td>
									<textarea rows="4" cols="24" id="updateContent" name="key.content"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:center">
								<div style="font-size:0" class="line">&nbsp;</div>
									<img class="czsp" onclick="javascript:$('#updateContent').val('');" alt="重置" src="<%=request.getContextPath()%>/images/t_6.png">
									<input class="czsp" type="image" src="<%=request.getContextPath()%>/images/t_5.png" value="添加" />
								</td>
							</tr>
							
						</table>
						
					</form>
				</td>
			</tr>
			<tr style="display: none" id="group">
				<td colspan="4">
					<form style="width:346px;" method="post" action="<%=request.getContextPath()%>/ivr400Action_addGroupKey.action">
						<table width=300 style="margin:0px auto;">
							<tr>
								<td style="text-align: right; font-size:14px;">选择工作组:</td>
								<td style="line-height:34px; text-align: left;" class="some" id="anjian">
									<input type="hidden" value="" id="ivrid" name="key.id">
									<select id="groupid" name="groupid"></select>
								</td>
							</tr>
							<tr>
								<td colspan="2"><input style="margin: 30px 0 0 81px;" type="image" src="<%=request.getContextPath()%>/images/t_8.png" value="确定" /></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="float: right;">
					<input type="hidden" value="" id="deleteId" >
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>