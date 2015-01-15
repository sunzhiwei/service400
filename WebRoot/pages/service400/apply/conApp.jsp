<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>400号码申请表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${path}/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/icon.css">
    <link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/new_z/new_z.css">
 	<link rel="stylesheet" href="${path}/inc/style.css" />
	<script type="text/javascript" src="${path}/js/jquery-1.7.1.min.js"></script>
	 	<script type="text/javascript" src="${path}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	var result1=true;
	var result2=true;
	var result3=true;
    function check(){
        var costcentre=$("#costcentre").val();
        var langingnum=$("#langingnum").val();
        var langingnum2=$("#langingnum2").val();
        var langingnum3=$("#langingnum3").val();
       // var opentime=$("#opentime").val();
    	var reg=/^\d{11,12}$/;
        if(costcentre==""){
        	  $.messager.alert("提示", "请您输入成本中心，成本中心不能为空！");
    		  $("#costcentre").focus();
    		  result1=false;
           }
        if(langingnum==""&&langingnum!=null){
    		  $.messager.alert("提示", "请您输入落地号1，落地号不能为空！");
    		  $("#langingnum").focus();
    		  result2=false;
    	    }
        if(reg.test(langingnum)==false){
        	 $.messager.alert("提示", "落地号1只能为数字，且为11-12位数字！");
     		  $("#langingnum").focus();
     		 result2=false;
        }
        if(langingnum2!=""&&langingnum2!=null && reg.test(langingnum2)==false){
        	 $.messager.alert("提示", "落地号2只能为数字，且为11-12位数字！");
     		  $("#langingnum2").focus();
     		 result2=false;
        }
        if(langingnum3!=""&&langingnum3!=null && reg.test(langingnum3)==false){
        	 $.messager.alert("提示", "落地号3只能为数字，且为11-12位数字！");
     		  $("#langingnum3").focus();
     		 result2=false;
        }
        //if(opentime==""){
  		 // $.messager.alert("提示", "请您输入实际开通时间，实际开通时间不能为空！");
  		 // $("#opentime").focus();
  		 // result3=false;
  	    //}
    }
	function addFile(){
		$("#files").append("<input style='display:block;' type=\"file\" name=\"tsfile\" />");
	}
	function save(){
		check();
		if(result1==true && result2==true && result3==true){
			$("#flag").val("0");
			document.getElementById("form").submit();
		}else{
			result1=true;
			result2=true;
			result3=true;
		}
	}
	function closeLoop(){
		check();
		if(result1==true && result2==true && result3==true){
			$("#flag").val("1");
			document.getElementById("form").submit();
		}else{
			result1=true;
			result2=true;
			result3=true;
		}
	}
	function setLangingnum(obj){
		if(obj==0){
			var a = $("#langingnum_a").val();
			var n = $("#langingnum_n").val();
			$("#langingnum").val(a+n);
		}
		if(obj==2){
			var a = $("#langingnum2_a").val();
			var n = $("#langingnum2_n").val();
			$("#langingnum2").val(a+n);
		}
		if(obj==3){
			var a = $("#langingnum3_a").val();
			var n = $("#langingnum3_n").val();
			$("#langingnum3").val(a+n);
		}
	}
	
	</script>
<style type="text/css">
	.xiahua td{ border-bottom:1px solid #548BCB;}
	.xiahua tr{ line-height:30px; font-size:14px;}
	.other{text-align:right; padding-right: 35px; background:#ECF9FF; width:30%;}
	.other_two{ padding-left:35px;}
	.bc_two{background: url('images/list_boxbg.png') 0 0 repeat-x; border:1px solid #E8E8E8}
	.two_td{ text-align:right; font-size:14px; width:30%;}
	.bc_two tr{ line-height:30px;}
</style>
  </head>
  <body style="background:#FFFFFF; text-align:center; color:#333333">
    <div id="ts" style="margin: 20px auto;width: 60%">
    	<div style="text-align:left;margin-left: 15px;"><img src="<%=request.getContextPath()%>/images/list_top.png"></div>
	    	<table class="xiahua" cellspacing="0" style="margin:0 auto; border: 1px solid #548BCB; margin-top:10px; width:600px; border-left:3px solid #FF6600">
	    		<tr>
	    			<td class="other">申请人姓名:</td>
	    			<td class="other_two">${ts.applicationperson }</td>
	    		</tr>
	    		<tr>
	    			<td class="other">400号码:</td>
	    			<td class="other_two">${ts.phonenum}</td>
	    		</tr>
	    		<tr>
	    			<td class="other">号码名称:</td>
	    			<td class="other_two">${ts.numcategory}</td>
	    		</tr>
	    		<tr>
	    			<td class="other">预计开通时间:</td>
	    			<td class="other_two">
	    				${ts.opentime}
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="other">用途说明:</td>
	    			<td class="other_two">
	    				<textarea readonly="readonly" rows="3" cols="25">${ts.useinstructions}</textarea>
					</td>
	    		</tr>
	    		<tr>
	    			<td class="other">400号码价格:</td>
	    			<td class="other_two">${ts.phonenumPrice }</td>
	    		</tr>
	    		<tr>
	    			<td class="other">申请人邮箱:</td>
	    			<td class="other_two">${ts.applicationpersonEmail}</td>
	    		</tr>
	    		<tr>
	    			<td class="other">部门负责人邮箱:</td>
	    			<td class="other_two">${ts.departmentheaderEmail }</td>
	    		</tr>
	    		<tr>
	    			<td class="other">抄送人邮箱:</td>
	    			<td class="other_two">
	    				${ts.copypersonEmail }
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="other">手机:</td>
	    			<td class="other_two">${ts.applicationpersonPhone }</td>
	    		</tr>
	    		<tr>
	    			<td class="other">公司:</td>
	    			<td class="other_two">${ts.company }</td>
	    		</tr>
	    		<tr>
	    			<td class="other">部门:</td>
	    			<td class="other_two">${ts.department }</td>
	    		</tr>
	    		<tr>
	    			<td class="other" style="border:none">是否需要配置IVR:</td>
	    			<td style="border:none" class="other_two">
	    				<c:if test="${ts.isivr==1 }">是</c:if>
	    				<c:if test="${ts.isivr==0 }">否</c:if>
	    			</td>
	    		</tr>
	    	</table>
    </div>
	<div class="bc_two" style="margin: 20px auto;width: 57%">
		<div style="text-align:left"><img src="<%=request.getContextPath()%>/images/bcxx.png"></div>
		<form style="padding-bottom:20px;" id="form" method="post" enctype="multipart/form-data"  action="${path}/apply400Action_conAppSup.action">
			<input id="service400Id" name="ts.id" style="display: none;"
				value="${ts.id}">
			<input id="flag" style="display: none;" />
			<table style="margin:0 auto">
				<tr>
					<td class="two_td">成本中心<font color="red">*</font>：</td>
					<td><input id="costcentre" name="ts.costcentre" value="${ts.costcentre}" /></td>
					<td><div id="costcentreErr"></div></td>
				</tr>
				<tr>
	    		<td></td><td>(可联系部门财务接口人获知，如不确认可不填)</td>
	    		</tr>
				<tr>
					<td class="two_td">落地号1<font color="red">*</font>：</td>
					<td>
						<input type="hidden" id="langingnum" name="ts.langingnum" value="${ts.langingnum }" />
						<input style="width: 20%" maxlength="4" id="langingnum_a" onblur="setLangingnum(0)" value="${ts.langingnum_a }" />-
						<input style="width: 40%" maxlength="8" id="langingnum_n" onblur="setLangingnum(0)" value="${ts.langingnum_n }" />
					</td>
					<td><div id="langingnumErr"></div></td>
				</tr>
				<tr>
					<td class="two_td">落地号2：</td>
					<td>
						<input type="hidden" id="langingnum2" name="ts.langingnum2" value="${ts.langingnum2 }" />
						<input style="width: 20%" maxlength="4" id="langingnum2_a" onblur="setLangingnum(2)" value="${ts.langingnum2_a }" />-
						<input style="width: 40%" maxlength="8" id="langingnum2_n" onblur="setLangingnum(2)" value="${ts.langingnum2_n }" />
					</td>
					<td><div id="langingnum2Err"></div></td>
				</tr>
				<tr>
					<td class="two_td">落地号3：</td>
					<td>
						<input type="hidden" id="langingnum3" name="ts.langingnum3" value="${ts.langingnum3 }" />
						<input style="width: 20%" maxlength="4" id="langingnum3_a" onblur="setLangingnum(3)" value="${ts.langingnum3_a }" />-
						<input style="width: 40%" maxlength="8" id="langingnum3_n" onblur="setLangingnum(3)" value="${ts.langingnum3_n }" />
					</td>
					<td><div id="langingnum3Err"></div></td>
				</tr>
				<tr>
					<td class="two_td">实际开通时间：</td>
					<td>
					<input readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="opentime" style="width: 65%" name="ts.factopentime" value="${ts.factopentime }">
					</td>
					<td><div id="factopentimeErr"></div></td>
				</tr>
				<tr>
					<td class="two_td">相关文件上传：</td>
					<td><div id="files"><input style="display:block" type="file" name="tsfile" /></div></td>
					<td><div onclick="addFile()" style="cursor: pointer;width:20px;height:20px;background: url('${path}/images/tabicons.png') 376px 516px;"></div></td>
				</tr>
				<c:if test="${ts.status==1||ts.status==0}">
				<tr>
					<td class="two_td">已经上传的文件：</td>					
					<td colspan="2">
						<c:forEach var="na" items="${ts.filepathArr}">
							${na}&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="3" style="text-align:center;">
						<img style="margin:20px 10px 0; cursor:pointer" onclick="javascript:history.go(-1);" src="<%=request.getContextPath()%>/images/button_a.png">
						<img style="margin:20px 10px 0; cursor:pointer" onclick="save()" src="<%=request.getContextPath()%>/images/button_d.png">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
