/****合规调用需要用到的方法****/
function fun_isCustomer(){
	var isCustomerValue = $('#fiscustomer').val();
	if(isCustomerValue==1){
		document.getElementById('cusSourceDiv').style.display = "";
		$('.cusRelation').hide();
		$("#fcusrelation").hide();
		$("#fidcard").show();
		$("fservicenames").show();
		$("#fcusname").attr("readonly",true);
		var fcomplainanter = $("#fcomplainanter").val();
	    $("#fcusname").val(fcomplainanter);
	}else{
		document.getElementById('cusSourceDiv').style.display = "none";
		$('.cusRelation').show();
		$("#fcusrelation").show();
		$("#fidcard").hide();
		$("#fcusname").val("");
		$("fservicenames").show();
		$("#fcusname").attr("readonly",false);
	}
}
//服务类型
function fun_serviceType(fservicetypeid,fcusstatusid){
	var serviceType = $('#'+fservicetypeid).val();
	var $sel = $("#"+fcusstatusid);
	$sel.empty().append( $("<option value='0'>----请选择----</option>") );//js 长度置空，并设置默认值
	//加载客户状态
	var url = "caseinfo/caseinfo!findCusstatus?complainInfo.fservicetypeid=" + serviceType;
	var json = sendAjax(url);
	for(var i=0;i<json.length;i++){
		var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
		$sel.append($opt);
	}
	$('.otherInfo0,.otherInfo1,.otherInfo2,.otherInfo3').hide();
	//获得选中的项
//	var selectedServiceType = $('#'+fservicetype+' option:selected').text();//选中的文本
//	if(selectedServiceType == '财富管理'){
//		document.getElementById('otherInfo2').style.display = "block";
//	}else{
//		document.getElementById('otherInfo2').style.display = "none";
//	}
}
function keyUpFun(){
	var fviolate=$('#fviolate').val();
	if(fviolate){
		$("#lxwysj").show();//违约时间
	}else{
		$("#lxwysj").hide();//违约时间
	}
}

//客户状态
function fun_cusStatus(fcusstatusid){
	$('.otherInfo0,.otherInfo1,.otherInfo2,.otherInfo3,.otherInfo4').hide();
	var cusStatus = $('#'+fcusstatusid);
	//清除动态绑定
	$("#fcontractnumber").validatebox({required: false,missingMessage: '该输入项为必填项'});//合同编号
	$("#famount").validatebox({required: false,missingMessage: '该输入项为必填项'});//金额
	$("#fdeadline").validatebox({required: false,missingMessage: '该输入项为必填项'});//期限
	//如果是普惠金融
	if(cusStatus.val() == 10){//完成放款，则将合同相关的信息都显示出来
		$('.otherInfo0').hide();	
		$('.otherInfo1').show();
		$('.otherInfo2').show();
		$('.otherInfo3').show();
		$('#lcfa').hide();//隐藏理财方案
		$("#fviolateCountHistory").show();//历史违约次数
		
		//是否违约
		var fisviolate = $("#fisviolate").val();
		if(fisviolate ==1){
			$("#fviolatedaystr").show();//违约天数
		}else{
			$("#fviolatedaystr").hide();//违约天数			
		}
		
		//历史违约次数
		var fviolate=$('#fviolate').val();
		if(fviolate){
			$("#lxwysj").show();//违约时间
		}else{
			$("#lxwysj").hide();//违约时间
		}
		
		$("#fcontractnumber").validatebox({required: true,missingMessage: '该输入项为必填项'});//合同编号
		$("#famount").validatebox({required: true,missingMessage: '该输入项为必填项'});//金额
		$("#fdeadline").validatebox({required: true,missingMessage: '该输入项为必填项'});//期限
		
		$("#fsalesmanname").validatebox({validType:'charLength[1,20]'});//销售人员姓名
		$("#fservicename").validatebox({validType:'charLength[1,20]'});//客服姓名
		$("#ffirsttrial").validatebox({validType:'charLength[1,20]'});//初审人员
		$("#flasttrial").validatebox({validType:'charLength[1,20]'});//终审人员
	}else if(cusStatus.val() == 16){//完成出借
		$('.otherInfo2').show();
		$("#fservicetype").show();
		$("#fservicetype2").show();
		$("#fstartorendtime1").show();
		$("#fstartorendtime2").show();
		
		$("#fext5time1").hide();
		$("#fext5time2").hide();
	}else if(cusStatus.val() == 1 || cusStatus.val() == 2){//尚未完成面审和完成面审
		$('.otherInfo3').show();//显示销售人员
		$('.otherInfo0,.otherInfo1,.otherInfo2').hide();
		
		$("#fsalesmanname").validatebox({validType:'charLength[1,20]'});//销售人员姓名
		$("#fservicename").validatebox({validType:'charLength[1,20]'});//客服姓名
		$("#ffirsttrial").validatebox({validType:'charLength[1,20]'});//初审人员
		$("#flasttrial").validatebox({validType:'charLength[1,20]'});//终审人员
	}else if(cusStatus.val() == 11){//拒贷
		$('.otherInfo0').show();
	}
	
}
/***
 * 案件来源调用
 * @param cussourceid
 * @param cusSourceOtherDiv
 * @return
 */
function addOtherCus(cussourceid,cusSourceOtherDiv){
	if($("#"+cussourceid).val() == 4){
		$("#"+cusSourceOtherDiv).css({display:"block"});
	}else{
		$("#"+cusSourceOtherDiv).css({display:"none"});
	} 
}
/***
 * 公用的方法
 * @param cussourceid
 * @param cusSourceOtherDiv
 * @return
 */
function addOtherCommon(selectedId,selectedValue,divId){
	if($("#"+selectedId).val() == selectedValue){
		$("#"+divId).show();
	}else{
		$("#"+divId).hide();
	} 
}
/**
 * 表单提交
 * @return
 */
function dosubmitCompliance(editForm){
	var result = $('#editForm').form('validate');
	var cusStatus = $('#fcusstatusid');
	if(cusStatus.find("option:selected").text() == '完成放款'){
			var freimbstrattime = $("#freimbstrattime").val();
			if(freimbstrattime == null||freimbstrattime == ''){
				$("#freimbstrattime")[0].focus();
				result = false;
			}
			var freimbendtime = $("#freimbendtime").val();
			if(freimbendtime == null||freimbendtime == ''){
				$("#freimbendtime")[0].focus();
				result = false;
			}
	}
	if(result){
		$('#editForm')[0].submit();
	}
}
/**
 * 表单提交:稍后再研究
 * @return
 */
function dosubmitComplianceNew(editForm){
	//var  edit = $('#'+editForm)[0];
	//alert(edit.action);
	//document.getElementById("editForm").onSubmit();
	//if($(this).form('validate') == true){
		//$('#editForm')[0].submit();
	//}
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			if(result){
			  $('#editForm')[0].submit();
			}
//			alert(result);
//			if(result == true){
//				$("#editForm").ajaxSubmit({
//		 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
//						if(json!=null && json!=''){
//							$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
//									toJson(json).success == "true"?"info":"error",function(){
//									alert(json);
//									//var url = '${contextPath}/pages/compliance/caseinfo/casList.jsp';
//									//window.location.href = url;
//							});
//						}else{
//							$.messager.alert("提示","操作成功！","info",function(){
//								window.location.href = '/pages/compliance/caseinfo/casList.jsp';
//							})
//						}
//		 			}
//		 		});	
//			}
//			return false;
		}
	})
}

/***
 * 是否违约
 * @return
 */
function fun_fisviolate(){
	var fisviolate = $("#fisviolate").val();
	if(fisviolate ==1){
		$("#fviolatedaystr").css("display","block");
		$("#fviolateCountHistory").css("display","none");
	}else{
		$("#fviolatedaystr").css("display","none");
		$("#fviolateCountHistory").css("display","block");
	}
}
/*******************************addCase***************************************************************************************************/
/***
 * 证据类型、案件初步分类时调用
 */
function fun_otherCheck(id,divId){
	if($("#"+id).attr("checked")=='checked'){
		$("#"+divId).show();
	}else{
		$("#"+divId).hide();
	}
}
var num = 0;
//添加内部员工
function fun_addInnerOut(){
	num += 1;//每添加一条记录数量加1
    $("#aa").tabs('add',{
    	title: '内部人员'+num,
    	content:"<input type='hidden' name='fisinner' value='1'/>"+
				"<input type='hidden' name='fcompanyname' value=''/>"+
			    "<input type='hidden' name='fbycompliantadd' value=''/>"+
			    "<input type='hidden' name='fisagent' value=''/>"+
		    	"<table>"+
	    		"<tr>"+
					"<td class=\"t-title\" width=\"100\">被投诉人姓名：</td>"+
					"<td><input type=\"text\" name=\"fname\" id=\"fname"+num+"\" class=\"t-text easyui-validatebox\" " +
					"onkeyup='fun_loadCompInfo(\"fname"+num+"\",\"fcomplaintcount"+num+"\",\"fcaseInfo"+num+"\",\"frefercase"+num+"\",\"fext1"+num+"\",\"${requestScope.contextPath}\");'/><span class=\"required\">*</span></td>"+
					"<td class=\"t-title\" width=\"100\">手机号码：</td>"+
					"<td><input type=\"text\" name=\"fmobile\" value=\"\" class=\"t-text\"/></td>"+
	    		"</tr>"+
	    		"<tr>"+
		    		"<td class=\"t-title\">座机：</td>"+
		    		"<td><input type=\"text\" name=\"fofficephone\" value=\"\" class=\"t-text\"></td>"+
					"<td class=\"t-title\">QQ：</td>"+
					"<td><input type=\"text\" name=\"fqq\" value=\"\" class=\"t-text\"></td>"+
	    		"</tr>"+
	    		"<tr>"+
		    		"<td class=\"t-title\">邮箱：</td>"+
		    		"<td><input type=\"text\" name=\"femail\" value=\"\" class=\"t-text\"></td>"+
		    		"<td class=\"t-title\">职位名称：</td>"+
		    		"<td><input type=\"text\" name=\"flevel\" class=\"t-text\"></td>"+
	    		"</tr>"+
	    		"<tr>"+
					"<td class=\"t-title\">组织信息：</td>"+    
					"<td colspan=\"3\">"+
					"<input type=\"text\" id=\"showFdeptname"+num+"\" onclick=openWinComp(\"部门信息\",\"win\","+num+") value=\"\" readonly=\"readonly\" class=\"t-text\"/>"+
					"<label onclick=openWinComp(\"部门信息\",\"win\","+num+")><font color=\"blue\">选择</font></label>"+
					"<input type='hidden' name='fdeptname' id='fdeptname"+num+"'/>"+
					"<input type='hidden' name='fcityname' id='fcityname"+num+"'/>"+
					"<input type='hidden' name='fsaledepart' id='fsaledepart"+num+"'/>"+
					"</td>"+
				"</tr>" +
				"<tr>" +
					"<td class=\"t-title\">入职时间：</td>"+
					"<td>"+
    				"<input type=\"text\" name=\"fentrytime\" id=\"fentrytime\" readonly=\"readonly\" "+ 
					"onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" class=\"t-text Wdate\"/>"+
					"</td>"+
					"<td class=\"t-title\">上家工作单位：</td>"+
					"<td><input type=\"text\" name=\"flastworkunit\" class=\"t-text\"/></td>"+
				"</tr>"+
				"<tr>"+
					"<td class=\"t-title\">学历：</td>"+
					"<td><input type=\"text\" name=\"feducation\" class=\"t-text\"/></td>" +
					"<td class=\"t-title\">推荐人：</td>"+
					"<td>"+
					"<input type=\"text\" name=\"freferrer\" value=\"\" class=\"t-text\"/>"+
					"</td>"+
				"</tr>"+
				"<tr>"+
					"<td class=\"t-title\">被投诉的次数：</td>"+
					"<td><input type=\"text\" name=\"fcomplaintcount\" id=\"fcomplaintcount"+num+"\" value='0' readonly=\"readonly\" class=\"t-text\"/></td>"+
					"<td class=\"t-title\">涉及到的案子：<input type='hidden' name='frefercase' id='frefercase0"+num+"'/><input type='hidden' name='fext1' id='fext1"+num+"'/></td>"+
					"<td id='fcaseInfo"+num+"'>无"+
					"</td>"+
				 "</tr>"+
		    	"</table>",
		closable:true
    });
}
var numOut = 0;
/***
 * 添加外部人员
 * @return
 */
function fun_addOut(){
	numOut += 1;
    $("#aa1").tabs('add',{
    	title: '外部人员'+numOut,
    	content:"<input type='hidden' name='fisinner' value='0'/>"+
		    	"<table>"+
	    		"<tr>"+
					"<td class=\"t-title\" width=\"100\">被投诉人姓名：</td>"+
					"<td><input type=\"text\" name=\"fname\" class=\"t-text\"/><span class=\"required\">*</span></td>"+
					"<td class=\"t-title\" width=\"100\">手机号码：</td>"+
					"<td><input type=\"text\" name=\"fmobile\" value=\"\" class=\"t-text\"/></td>"+
	    		"</tr>"+
	    		"<tr>"+
		    		"<td class=\"t-title\">座机：</td>"+
		    		"<td><input type=\"text\" name=\"fofficephone\" value=\"\" class=\"t-text\"/></td>"+
					"<td class=\"t-title\">QQ：</td>"+
					"<td><input type=\"text\" name=\"fqq\" value=\"\" class=\"t-text\"/></td>"+
	    		"</tr>"+
	    		"<tr>"+
	    		"<td class=\"t-title\">邮箱：</td>"+
	    		"<td><input type=\"text\" name=\"femail\" value=\"\" class=\"t-text\"/></td>"+
	    		"<td class=\"t-title\">公司名称：</td>"+
	    		"<td><input type=\"text\" name=\"fcompanyname\" class=\"t-text\"/></td>"+
	    		"</tr>"+
	    		"<tr>"+
				"<td class=\"t-title\">城市：</td>"+
				"<td><input type=\"text\" name=\"fcityname\" class=\"t-text\"/></td>"+
				"<td class=\"t-title\">被投诉人地址：</td>"+
				"<td><input type=\"text\" name=\"fbycompliantadd\" class=\"t-text\"/></td>"+
				"</tr>"+
				"<tr>"+
	    		"<td class=\"t-title\">是否为中介：</td>"+
	    		"<td colspan=\"3\">" +
	    		"<select id=\"fisagent\" name=\"fisagent\" onchange=\"zhongJieSelectFun();\">"+
	    		"<option value='1'>是</option>"+
	    		"<option value='0'>否</option>"+
	    		"</select></td>"+
	    		"</tr>"+
	    		"<tr style=\"display: none\" id=\"shiFouZhongJie\">"+
					"<td class=\"t-title\">备注：</td><td colspan=\"3\">" +
					"<textarea name=\"fnotagentRemark\" rows=\"3\" style=\"width:580;\"></textarea></td>"+
				"</tr>"+
		    "</table>",
		closable:true
    });
}
/**
 * 添加被投诉部门
 */
var numDept=0;
function fun_addDept(){
	numDept += 1;
	$("#dept").tabs('add',{
    	title: '被投诉部门'+numDept,
    	content: "<table>"+
		    		"<tr><td class=\"t-title\" width=\"100\">部门名称：</td>"+
						"<td><input type=\"text\" name=\"dfdeptname\" "+
							"value=\"\" class=\"t-text easyui-validatebox\"/></td>"+
						"<td class=\"t-title\" width=\"100\">城市名称：</td>"+
						"<td><input type=\"text\" name=\"dfcityname\" value=\"\" class=\"t-text easyui-validatebox\"/></td>"+
						"<td class=\"t-title\" width=\"100\">营业部名称：</td>"+
						"<td><input type=\"text\" name=\"dfsaledepart\" value=\"\" class=\"t-text easyui-validatebox\"/></td>"+
		    		"</tr>"+
	    		"</table>",
		closable:true
    });
	input_autocoplete();
}

function addOther(){
	if($("#cusStatusZhengMing").val() == 3){
		$("#zhengMingOther").css({display:"block"});
	}else{
		$("#zhengMingOther").css({display:"none"});
	} 
}
	/***
	 * 判断是否为中介
	 * @return
	 */
	function zhongJieSelectFun(){
		if($("#fisagent").val()==0){
			$("#shiFouZhongJie").show();
		}else{
			$("#shiFouZhongJie").hide();
		}
	}
	/***
	 * 投诉渠道的js
	 * @return
	 */
	function qiuDaoSelectFun(){
		if($("#fcompchannelid").find("option:selected").text() == '个人邮箱'){
			$("#qiuDaoGeRenDiv").show();
		}else{
			$("#qiuDaoGeRenDiv").hide();
		}
	}
	
	/**
	 * 打开新窗口（easyUI的模态窗口）
	 * @param type（类型，view——详细信息；edit——编辑页面）
	 * @param id （记录id）
	 * @return
	 */
	function openWinComp(title,type,id){
		var url,title;
		switch (type) {
			case 'win':
				url = $("#winURL").val();
				break;
		}
		//模态窗口自适应宽度，高度
		$('#iframeWin').window({
			title:title,
			top:(document.body.scrollTop+document.body.offsetHeight-200)/2,
			left:($(window).width()-1000)/2,
			onClose : function(){ //点击关闭按钮时清除iframe的src
				$("#iframeSource").attr("src",""); 
			} 
		})
		$("#iframeSource").attr("src",url+"?id="+id);
		$('#iframeWin').window('open');
	}

	//根据姓名加载案件相关的信息
	function fun_loadCompInfo(fname,fcomplaintcount,fcaseInfo,frefercase,fext1,contextPath){
		var fnameObj = $("#"+fname).val();
		//被投诉的次数
		var jsonCount = sendAjax(contextPath + '/caseinfo/caseinfo!findPersonCompCount?fname='+fnameObj);
		//alert(contextPath + "\t" + jsonCount);
		if(jsonCount != null && jsonCount != ""){
			$("#"+fcomplaintcount).val(jsonCount);
		}else{
			$("#"+fcomplaintcount).val('0');
		}
		//涉及到的案子
		var jsonComp = sendAjax(contextPath + '/caseinfo/caseinfo!findPersonRelComplian?fname='+fnameObj);
		$("#" + fcaseInfo).html("无");
		if(jsonComp != null && jsonComp != ''){
			var str = '';
			var saveInfo = '';
			var saveComplianId = '';
			for(i=0;i<jsonComp.length;i++){
				if(i==0){
					var url = contextPath + "/caseinfo/caseinfo!viewCaseInfo?fid="+jsonComp[i].FID;
					str += "<a href='javascript:void(0);' onclick=showModalDialogCaseInfo(this,'"+url+"','案件信息')>"+jsonComp[i].FNUMBER+"</a>";
					saveInfo += jsonComp[i].FNUMBER;
					saveComplianId +=jsonComp[i].FID;
				}else{
					var url = contextPath + "/caseinfo/caseinfo!viewCaseInfo?fid="+jsonComp[i].FID;
					str += "|<a href='javascript:void(0);' onclick=showModalDialogCaseInfo(this,'"+url+"','案件信息')>"+jsonComp[i].FNUMBER+"</a>";
					saveInfo +=  ","+jsonComp[i].FNUMBER;
					saveComplianId += ","+jsonComp[i].FID;
				}
			}
			$("#" + fcaseInfo).html(str);
			$("#"+frefercase).val(saveInfo);
			$("#"+fext1).val(saveComplianId);
		}else{
			$("#"+frefercase).val("");
			$("#"+fext1).val("");
		}
	}
	/**
	 * 查看关联的案件信息
	 * @param url
	 * @return
	 */
	function showModalDialogCaseInfo(btn,url,title){
		if(url){
			addTab("案件_详细信息", url);
		}else{
			addTab("案件_详细信息", btn);
		}
	}
	
	function input_autocoplete(){
		$("input[name='dfsaledepart']").each(function(i,n){
			$(n).autocomplete('caseinfo/caseinfo!getOrgData', {
				width: 216,
				dataType:'text',
				parse: function(data) {  //重写格式化得到的json集合               
					var data = toJson(data);
					var len = data.length;
					var rows = [];
					for(var i=0;i<len;i++){
						rows.push({data:data[i],fdeptname:data[i].FDEPTNAME,fcityname:data[i].FCITYNAME,fsaledepart:data[i].FSALEDEPART});
					} 
                    return rows;
                },
				formatItem: function(row, i, max) {   //格式化提示内容  
					return row.FSALEDEPART+"["+row.FCITYNAME+"]";//数据格式为： x/x "数据A" [数据B]  
				}
			}).result(function(event,row,formatted){
				$(this).val(row.FSALEDEPART).parents(".panel :visible").find("input[name='dfdeptname']").val(row.FDEPTNAME)
				.parents(".panel :visible").find("input[name='dfcityname']").val(row.FCITYNAME);
			});
		});
	}