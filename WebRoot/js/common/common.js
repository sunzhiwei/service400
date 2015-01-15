/**
 * 页面加载时的公用方法
 */
//============================================================================================//

/**
 * 省略列表页面单元格的长数据（超过21个字符时截取数据（中文算两个字符））
 */
function omitLongData(){
	return function(){ 
		var panel = $(this).datagrid('getPanel');   
		var cell = panel.find('div.datagrid-body tr .datagrid-cell');//获取dataGrid单元格   
		cell.each(function(){
			var me = $(this);
			var objString = $.trim(me.text());
			if(objString == null || objString == "") me.text("-");
			var objLength=0,showStr = [];
			for(var i=0;i<objString.length;i++){
				if(objString.charCodeAt(i)>255) objLength+=2;//中文算两个字符
				else objLength++;
				if(objLength<21) showStr.push(objString.charAt(i));
			}
			if(objLength>21) me.text(showStr.join("") + "...");
			me.attr("title",objString);
		});   
	}
}
//============================================================================================//
//项目信息列表去掉省略号
function omitLong(){
	return function(){ 
		var panel = $(this).datagrid('getPanel');   
		var cell = panel.find('div.datagrid-body tr .datagrid-cell');//获取dataGrid单元格   
		cell.each(function(){
			var me = $(this);
			var objString = $.trim(me.text());
			if(objString == null || objString == "") me.text("-");
			var objLength=0,showStr = [];
			for(var i=0;i<objString.length;i++){
				if(objString.charCodeAt(i)>255) objLength+=2;//中文算两个字符
				else objLength++;
				if(objLength<21) showStr.push(objString.charAt(i));
			}
			if(objLength>21)
			me.attr("title",objString);
		});   
	}
}
/**
 * 房屋合同操作栏权限处理
 * @param btn 必选项 点击事件对象
 * @param parentTitle 必选项 需要刷新的选项卡页title
 * @param id	当前记录id
 * @param typeid登陆用户typeid
 * @param orgDiffer当前记录部门区分1：普惠职能端，2：财富端
 * @param menuName可选项为空时没有编辑权限
 */
function handleOperationPurview(btn,parentTitle,id,typeid,orgDiffer,menuName){
	var result = "<span><a id=\"operation\" href='javascript:void(0)'"+" class=\"operation-a\""+
		" onclick='openTab(this,\""+parentTitle+"\",\"view\","+id+")' >查看</a>&nbsp;</span>";
	//5普惠职能端行政部门负责人（曹莹）//typeid==9财富端行政部门负责人（徐广宁）
	if(!((typeid==5 && orgDiffer==2) || (typeid==9 && orgDiffer==1))){
		if(!(menuName=='portInfo' && typeid==10)){
			if(typeid!=3){
				result += "&nbsp;<a id=\"operation\" href='javascript:void(0)'"+" class=\"operation-a\""+
				" onclick='openTab(this,\""+parentTitle+"\",\"edit\","+id+")' >编辑</a></span>";
	//				if(typeid!=10){//!财务端
	//					result += "&nbsp;<a href='javascript:void(0)' onclick='deleteRecord("+id+")' >删除</a>";
	//				}		
			}
		}
	}
	return result;
}

/**
 * 回车绑定查询事件
 */
function bindQuery(event){
	//firefox下没有window.event全局域对象
	var evt = window.event || arguments.callee.caller.arguments[0]; 
	if(evt.keyCode == 13){
		$("#queryBtn").click();
	}
}

/**
 * 
 * @return 查询下拉框绑定查询事件
 */
function changeQyery(){
	$("#queryBtn").click();
}
/**
 * 打开新窗口（easyUI的模态窗口）
 * @param type（类型，view——详细信息；edit——编辑页面）
 * @param id （记录id）
 * @return
 */
function openWin(type,id){
	var url,title;
	switch (type) {
		case 'view':
			url = $("#detailURL").val()+id;	title="详细信息";
			break;
		case 'edit':
			url = $("#editURL").val()+id;	title="编辑信息";
			if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
			break;
	}
	//模态窗口自适应宽度，高度
	$('#iframeWin').window({
		title:title,
//		left:88+$(window).scrollLeft(),
//		top:65+$(window).scrollTop(),
//		width:($(window).width()-176),height:($(window).height()-100),//原来-120
		onClose : function(){ //点击关闭按钮时清除iframe的src
			$("#iframeSource").attr("src",""); 
		} 
	})
	$("#iframeSource").attr("src",url);
	$('#iframeWin').window('open');
}



/**
 * 打新窗口（路径可以是jsp页面）
 * @param {} type
 */
function openSan(type){
	var url,title;
	switch (type) {
		case 'win':
			url = $("#winURL").val();	title="成本中心列表";
			break;
		case 'edit':
			url = $("#addURL").val();	title="编辑信息";
			break;
	}
	//模态窗口自适应宽度，高度
	$('#iframeWin').window({
		title:title,
		left:8+$(window).scrollLeft(),
		top:6+$(window).scrollTop(),
		width:($(window).width()-20),height:($(window).height()-20),//原来-120
		onClose : function(){ //点击关闭按钮时清除iframe的src
			$("#iframeSource").attr("src",""); 
		} 
	})
	$("#iframeSource").attr("src",url);
	$('#iframeWin').window('open');

}
/**
 * 添加新的表单
 * @param url
 * @return
 */
function openWinNew(url){
	$("#iframeSource").attr("src",url);
	$('#iframeWin').window('open');
}
/**
 * 表单提交
 * @return
 */
function dosubmit(id){
	if(id) $("#"+id).linkbutton('disable');
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			if(result == true){
				$("#editForm").ajaxSubmit({
					dataType:"text",
		 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
						if(json!=null && json!=''){
							$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
									toJson(json).success == "true"?"info":"error",function(){
								parent.$('#iframeSource').removeAttr('src');
								parent.$('#iframeWin').window('close');
								parent.$("#tt").datagrid("reload");
							});
						}else{
							$.messager.alert("提示","操作成功！","info",function(){
								parent.$('#iframeSource').removeAttr('src');
								parent.$('#iframeWin').window('close');
								parent.$("#tt").datagrid("reload");
							})
						}
		 			}
		 		});	
			}else{
				if(id) $("#"+id).linkbutton('enable');
			}
			return false;
		}
	})
}

/**
 * 取消操作
 * @return
 */
function doCancel(){
	parent.$('#iframeWin').window('close');parent.$('#iframeSource').removeAttr('src');
}
/**
 * 根据id删除单条记录数据
 * @param id记录id
 * @return
 */
function deleteRecord(id){
	var url = $("#deleteURL").val() + id;
	$.messager.confirm('警告', '确定需要删除此信息吗？', function(r){
		if(r){
			$.ajax({
				 url: url,
				 async: false,
				 success:function(){
					$.messager.alert("提示","操作成功！","info",function(){
						$("#tt").datagrid("reload");
					})
 				}
			});
		}
	});
}
// 有限合伙系统变更
function change_deleteRecord(id){
	var url = $("#deleteURL").val() + id;
	var detailurl=$("#detailURL").val();
	$.messager.confirm('警告', '确定需要删除此信息吗？', function(r){
		if(r){
			$.ajax({
				 url: url,
				 async: false,
				 success:function(){
					$.messager.alert("提示","操作成功！","info",function(){
						window.location.href=detailurl;
					})
 				}
			});
		}
	});
}

/**
 * 根据员工编码发送短信
 * @return
 */
function sendMessage(fnumber){
	var url = $("#sendmessage").val()+fnumber;
	$.messager.confirm('提示', '确定需要发送短信息吗？', function(r){
		if(r){
			$.ajax({
				 url: url,
				 async: false,
				 success:function(){
					$.messager.alert("提示","操作成功！","info",function(){
					})
 				}
			});
		}
	});
	
}

/**
 * 添加页面easyUI摭罩消息
 * @param message
 * @return
 */
function mask(message){
	var obj = $("<div id=\"maskDiv\" class=\"datagrid-mask\"></div>")
				.css({display:"block",width:"100%",height:$(window).height()})
				.appendTo("body");
			  $("<div id=\"maskMsg\" class=\"datagrid-mask-msg\"></div>")
			  	.html(message||"正在处理，请稍候...")
			  	.appendTo("body")
			  	.css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
    return obj;
}

/**
 * 销毁页面摭罩层消息
 * @return
 */
function unmask(){
	$("#maskDiv").remove();$("#maskMsg").remove();
}

/**
 * json字符串转换成json对象
 * @param jsonStr
 * @return
 */
function toJson(jsonStr){
//	return eval("("+(jsonStr||"")+")");
	return (new Function("","return "+jsonStr||""))();
}

/**
 *  系统提示消息
 * @param date日期
 * @param title消息框title
 * @param msg消息内容
 * @param timeout消息消失时间（毫秒）
 * @param showType消息显示方式
 * @return
 */
function showMessage(date,title,msg,timeout,showType){
	if(new Date().getTime()<date.getTime()){
		$.messager.show({
			title:title,
			msg:msg,
			timeout:timeout,
			showType:showType
		});
	}
}
/***
 * 
//动态的取后台的值
 * @param url
 * @return
 */
function sendAjax(url){
	var json = {};
	$.ajax({
		   type: "POST",
		   url: url,
		   async:false,//发送同步请求
		   dataType: "json",
		   success: function(data){
		   		json = data;
		   }
	});
	return json;
}
/**
 * 加载页面上的下拉项
 * 对于初始值，则赋值为0
 * 这种方法可能不适用于所有的需要，使用的时候需要注意
 * @param id
 * @param url
 * @return
 */
function loadSelectInfo(id,url){
		var $id = $("#"+id);
		$id.empty().append( $("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
		var json = sendAjax(url);
		for(var i=0;i<json.length;i++){
			var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
			$id.append($opt);
		}
}
/**
 * 加载页面上的下拉项,用于修改
 * @param id
 * @param url
 * @return
 */
function loadSelectInfo(id,url,selectedValue){
		var $id = $("#"+id);
		$id.empty().append( $("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
		var json = sendAjax(url);
		for(var i=0;i<json.length;i++){
			var opt = '';
			if(selectedValue == json[i].key){
				$opt = $("<option value="+json[i].key +" selected>"+json[i].value +"</option>");
			}else{
				$opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
			}
			$id.append($opt);
		}
}
/*******************************新封装了一个方法:动态加验证条件的时候用********************************/
function validRequired(id){
	$("#"+id).validatebox({required: true,missingMessage: '该输入项为必填项'});
}

