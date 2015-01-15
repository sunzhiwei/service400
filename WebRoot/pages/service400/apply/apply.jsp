<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>400号码申请表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/new_z/new_z.css">
<script type="text/javascript" src="${path}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
<script type="text/javascript"
	src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	//设置ajax同步
	$.ajaxSetup( {
		async : false
	});

	function applicationperson1() {
		var flag = true;
		if (!$("#applicationperson").val()) {
			flag = false;
			$("#applicationpersonErr").html("请填写用户名！");
		} else {
			$("#applicationpersonErr").html("");
		}
		return flag;
	}
	function opentime1() {
		var flag = true;
		if (!$("#opentime").val()) {
			flag = false;
			$("#opentimeErr").html("请选择预计开通时间！");
		} else {
			$("#opentimeErr").html("");
			var date = new Date();
			var date_arr = $("#opentime").val().split("-");
			var year = parseInt(date_arr[0]);
			var month = parseInt(date_arr[1]) - 1;
			var day = parseInt(date_arr[2]) - 7;
			var date1 = new Date(year, month, day);
			if (date.valueOf() > date1.valueOf()) {
				$("#opentimeErr").html("输入日期七天以后！");
			} else {
				$("#opentimeErr").html("");
			}
		}
		return flag;
	}
	function useinstructions1() {
		var flag = true;
		if (!$("#useinstructions").val()
				|| $("#useinstructions").val().length < 3) {
			flag = false;
			$("#useinstructionsErr").html("请填写用途说明,且输入3位以上的汉字！");
		} else {
			$("#useinstructionsErr").html("");
		}
		return flag;
	}
	function applicationpersonEmail1() {
		var flag = true;
		if (!$("#applicationpersonEmail").val().match(".+@creditease.cn")) {
			flag = false;
			$("#applicationpersonEmailErr").html(
					"请填写您的公司邮箱，以@creditease.cn结尾的邮箱！");
		} else {
			$("#applicationpersonEmailErr").html("");
		}
		return flag;
	}
	function departmentheaderEmail1() {
		var flag = true;
		if (!$("#departmentheaderEmail").val().match(".+@creditease.cn")) {
			flag = false;
			$("#departmentheaderEmailErr").html(
					"请填写部门负责人的公司邮箱，以@creditease.cn结尾的邮箱！");
		} else {
			$("#departmentheaderEmailErr").html("");
		}
		return flag;
	}
	function daEmail() {
		var flag = true;
		if ($("#departmentheaderEmail").val()==$("#applicationpersonEmail").val()) {
			flag = false;
			$("#departmentheaderEmailErr").html("部门负责人的公司邮箱不能和申请人的邮箱重复。");
		} else {
			$("#departmentheaderEmailErr").html("");
		}
		return flag;
	}
	function applicationpersonPhone1() {
		var flag = true;
		if (!$("#applicationpersonPhone").val().match("1[0-9]{10}")) {
			flag = false;
			$("#applicationpersonPhoneErr").html("请填写11位手机号码！");
		} else {
			$("#applicationpersonPhoneErr").html("");
		}
		return flag;
	}
	function company1() {
		var flag = true;
		if (!$("#company").val()) {
			flag = false;
			$("#companyErr").html("请填公司名称！");
		} else {
			$("#companyErr").html("");
		}
		return flag;
	}
	function department1() {
		var flag = true;
		if (!$("#department").val()) {
			flag = false;
			$("#departmentErr").html("请填公司部门！");
		} else {
			$("#departmentErr").html("");
		}
		return flag;
	}
	function check() {
		var flag = applicationperson1();
		if (flag) {

			flag = opentime1();
		}
		if (flag) {

			flag = useinstructions1();
		}
		if (flag) {

			flag = applicationpersonEmail1();
		}
		if (flag) {

			flag = departmentheaderEmail1();
		}
		if (flag) {

			flag = daEmail();
		}
		if (flag) {

			flag = applicationpersonPhone1();
		}
		if (flag) {

			flag = company1();
		}
		if (flag) {

			flag = department1();
		}
		return flag;

	}
	function apply(id, phonenum, price, ocs) {
		$("#tsn").css("display", "none");
		$("#service400Id").val(id);
		$("#phonenum").val(phonenum);
		$("#phonenumShow").html(phonenum);
		$("#phonenumPrice").val(price);
		$("#phonenumPriceShow").html(price);
		$("#ts").css("display", "block");
	}
	function backPhoneList() {
		$("#tsn").css("display", "block");
		$("#ts").css("display", "none");
		return false;
	}
	function addEmail() {
		var email = $("#eml");
		var show = $("#show");
		var eml = email.val();
		if (!eml.match(".+@creditease.cn")) {
			alert("请填写宜信公司邮箱（@creditease.cn）");
		} else {
			email.val("");
			show
					.append("<div class='fuchu' name='sh' onclick='remEmail(this)' style='border: 1px solid #008ED2;text-align: center;'>"
							+ eml + "</div>");
			setCpe();
		}
	}
	function remEmail(obj) {
		$(obj).remove();
		setCpe();
	}
	function setCpe() {
		var cpe = $("#cpe");
		cpe.val("");
		var shs = $("#show").find("div");
		for ( var i = 0; i < shs.length; i++) {
			cpe.val(cpe.val() + $(shs[i]).text()+ ";");
		}
	}
	function showTsn(){
		$("#tsn").css("display","block");
		$("#notes").css("display","none");
	}
</script>
<style type="text/css">
.big_hezi{width:88%; margin:10px auto; border:1px solid #CCCCCC; height:auto;}
.box_p{font-size:12px; font-weight:bold; padding-left:1%; height:30px; width:99%; background:#548CC9; color:#FFFFFF; line-height:30px;}
.gdt{font-size:14px; text-indent:2em; line-height:26px; width:90%; margin:0 auto; margin-top:20px;margin-bottom:20px; overflow: scroll; height:345px; border:1px solid #cccccc;}
</style>
</head>
<body>
	<div id="notes">
	<div class="big_hezi">
		<p class="box_p">申请须知</p>
		<div class="gdt">${notes}</div>
	</div>
		<br/>
		<div style="text-align:center; width:90%"><img style="cursor:pointer; position: absolute; bottom:10px;" alt="同意并下一步" src="<%=request.getContextPath()%>/images/agree_next.png" onclick="showTsn()" /></div>
	</div>
	<div id="tsn" style="display:none;width: 64%;margin: 70px auto;">
		<div style="text-align:left" class="table_top">可申请的400号码</div>
		<div class="sq_box">
			<table cellspacing="0">
				<tr class="table_head">
					<th width=30%>400号码</th>
					<th width=25%>月最低消费额(元/月)</th>
					<th width=25%>运营商</th>
					<th width=20%>选择</th>
				</tr>
				<c:forEach items="${list}" var="tsn">
					<tr>
						<td>${tsn.phonenum }</td>
						<td class="yellow">${tsn.price }</td>
						<td class="green">${tsn.OCString }</td>
						<td><input type="image"
							src="<%=request.getContextPath()%>/images/sq_anniu.png"
							value="申请"
							onclick="apply('${tsn.id}','${tsn.phonenum }','${tsn.price }','${tsn.OCString }')" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div id="ts" style="display:none;margin: 20px auto;width: 60%">
		<div style="text-align:left">
			<img src="<%=request.getContextPath()%>/images/list_top.png">
		</div>
		<form id="myForm" method="post" action="<%=basePath%>apply400Action_add400Apply.action">
			<input id="service400Id" name="ts.service400Id"
				style="display: none;" value="">
			<div class="sqb_bigbox">
				<table class="list_box">
					<tr>
						<td class="one"><p>申请人姓名<font color="red">*</font>:</p></td>
						<td style="width:50%"><input onblur="applicationperson1(true)" id="applicationperson" style="width: 84%" name="ts.applicationperson"></td>
	    			<td width:15%><div id="applicationpersonErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>400号码<font color="red">*</font>:</p> <input
							style="width: 84%;display: none" id="phonenum" name="ts.phonenum"></td>
						<td><div style="width: 100%" id="phonenumShow"></div></td>
						<td style="width:15%">&nbsp;</td>
	    		</tr>
					<tr>
						<td class="one"><p style="margin-top: -30px;">号码名称:</p></td>
						<td><input style="width: 84%" name="ts.numcategory"><br />
							<div class="orange" style="margin-top:-5px">例：宜信联络中心热线</div></td>
						<td style="width:15%">&nbsp;</td>
	    		</tr>
					<tr>
						<td class="one"><p>预计开通时间<font color="red">*</font>:</p></td>
						<td><input readonly="readonly" onblur="opentime1()"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="opentime"
							style="width: 84%" name="ts.opentime"></td>
						<td style="width:15%"><div id="opentimeErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>用途说明<font color="red">*</font>:</p></td>
						<td><textarea style="width: 84%;" onblur="useinstructions1()"
								id="useinstructions" rows="3" cols="17"
								name="ts.useinstructions"></textarea></td>
						<td style="width:15%"><div id="useinstructionsErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>月最低消费额(元/月)<font color="red">*</font>:</p> <input
							style="width: 84%;display: none;" id="phonenumPrice"
							name="ts.phonenumPrice"></td>
						<td><div style="width: 100%" id="phonenumPriceShow"></div></td>
						<td style="width:15%">&nbsp;</td>
	    		</tr>
					<tr>
						<td class="one"><p>申请人邮箱<font color="red">*</font>:</p></td>
						<td><input onblur="applicationpersonEmail1()"
							id="applicationpersonEmail" style="width: 84%"
							name="ts.applicationpersonEmail"></td>
						<td style="width:15%"><div id="applicationpersonEmailErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>部门负责人邮箱<font color="red">*</font>:</p></td>
						<td><input onblur="departmentheaderEmail1()"
							id="departmentheaderEmail" style="width: 84%"
							name="ts.departmentheaderEmail"></td>
						<td style="width:15%"><div id="departmentheaderEmailErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one" style="float:left;"><p>抄送人邮箱:</p></td>
						<td id="email"><input id="cpe" style="display: none;"
							name="ts.copypersonEmail"> <input
							style="width: 77%; float:left;" id="eml">
							<div onclick="addEmail()"
								style=" cursor: pointer;margin-top:8px; margin-left:5px; float:left;width:12px;height:12px;background: url('${path}/images/tianjia.png') 0px 0px no-repeat;"></div>
							<div style="display:block" id="show" style="width:15%">&nbsp;</div></td>

					</tr>
					<tr>
						<td class="one"><p>手机<font color="red">*</font>:</p></td>
						<td><input onblur="applicationpersonPhone1()"
							id="applicationpersonPhone" maxlength="11" style="width: 84%"
							name="ts.applicationpersonPhone"></td>
						<td style="width:15%"><div id="applicationpersonPhoneErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>公司<font color="red">*</font>:</p></td>
						<td><input onblur="company1()" id="company"
							style="width: 84%" name="ts.company"></td>
						<td style="width:15%"><div id="companyErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>部门<font color="red">*</font>:</p></td>
						<td><input onblur="department1()" id="department"
							style="width: 84%" name="ts.department"></td>
						<td style="width:15%"><div id="departmentErr" style="color: red;">&nbsp;</div></td>
	    		</tr>
					<tr>
						<td class="one"><p>成本中心:</p></td>
						<td><input id="costcentre" style="width: 84%" name="ts.costcentre"></td>
						<td style="width:15%"></td>
	    		</tr>
	    		<tr>
	    		<td></td><td>(可联系部门财务接口人获知，如不确认可不填)</td>
	    		</tr>
					<tr>
						<td class="one"><p>是否需要配置IVR:</p></td>
						<td><input type="radio" checked="checked" value="1"
							name="ts.isivr">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="radio" value="0" name="ts.isivr">否</td>
						<td style="width:15%"> &nbsp;</td>
	    		</tr>

				</table>
				<table style="margin:10px auto">
					<tr class="three_box">
						<td class="jianj">
							<img style="cursor: pointer;" alt="返回" src="<%=request.getContextPath()%>/images/button_a.png" onclick="backPhoneList()">
						</td>
						<td class="jianj">
							<input src="<%=request.getContextPath()%>/images/button_c.png" onclick="return check()" type="image" value="申请">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
