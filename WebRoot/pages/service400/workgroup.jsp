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
	<title>工作组列表</title>
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	// 设置ajax同步
    $.ajaxSetup({  
        async : false  
    });
	    function sendmail(){
	    	$.messager.confirm("操作提醒","您确认要提交工作组修改申请发送邮件吗？",function(message){
	    		if(message){
	    			$.post("<%=basePath%>workGroupAction_sendMail.action",
	        				function(data){
	            				  if(data==1){
	            					  $.messager.alert("提示","工作组维护申请邮件发送成功！","info",function(){
	                      		    	$("#tt").datagrid("reload");
	           					})}else{
	           					 $.messager.alert("提示","你未对工作组进行任何修改，邮件未发送，请对工作组进行操作后再发送邮件！","info",function(){
	                  		    	$("#tt").datagrid("reload");
	       					    })}
	        				});
	    		}
	    	});
	    }
	
   		$(function() {
   			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  // 向服务器传递的参数
					workgroupname: $('#workgroupname').val(),
					starttime:$("#starttime").val(),
				    endtime:$("#endtime").val(),
				    workoverflowgroup:$("#workoverflowgroup").val(),
				    noworkoverflowgroup:$("#noworkoverflowgroup").val()
			        }); 
			})

			$("#resetBtn").click(function(){
			$("#workgroupname").val("");
			$("#starttime").val("");
		    $("#endtime").val("");
		    $("#workoverflowgroup").val("");
		    $("#noworkoverflowgroup").val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>workGroupAction_queryPageJson.action',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				columnOption: true,
				rownumbers: true,
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					  {field:'name',title:'组名',width:fixColumnWidth(0.0500)},
					  {field:'adapternum',title:'透传号码',width:fixColumnWidth(0.0600)},
					  {field:'workweek',title:'工作周',width:fixColumnWidth(0.0800)},
					  {field:'worktime',title:'工作时间',width:fixColumnWidth(0.0600)},
					  {field:'workoverflowgroup',title:'工作时溢出',width:fixColumnWidth(0.0500)},
					  {field:'busytone',title:'工作时忙音',width:fixColumnWidth(0.0500)},
					  {field:'noworkoverflowgroup',title:'非工作时溢出',width:fixColumnWidth(0.0600)},
					  {field:'noworkvoice',title:'非工作时语音',width:fixColumnWidth(0.1800)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node){
						       if(node.count>0){
						    	   return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"工作组\",\"view\","
				   		           +node.id+")' >查看明细</a>&nbsp;</span>"
				   		           +"&nbsp;<a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"工作组\",\"edit\","
				   		           +node.id+")' >编辑</a>"
						       }else{
						    	   return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"工作组\",\"view\","
				   		           +node.id+")' >查看明细</a>&nbsp;</span>"
				   		           +"&nbsp;<a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"工作组\",\"edit\","
				   		           +node.id+")' >编辑</a>&nbsp;"
				   		           +"&nbsp;<a class=\"operation-a\" href='javascript:void(0)' onclick='deleteRecord("+node.id+")' >删除</a>";
						       }
						   }
			   		  }
				]],
				//下面 定义 分页配置 ：
				pageSize:5,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
				//onLoadSuccess: omitLongData()
				onLoadSuccess: omitLong()
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
		
   </script>
</head>
	<body>
	<input type="hidden" id="allworkgroup" value="${allworkgroup }"/>
	<div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
		  	<td class="t-title">组名:</td>
            <td>
            	<input class="t-text" id="workgroupname" name="workgroupname" type="text" value="">
            </td>
            <td class="t-title">工作时间:</td>
            <td>
            	<input class="t-text" id="starttime" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})" type="text" value="">
            	—
            	<input class="t-text" id="endtime" readonly="readonly" onfocus="WdatePicker({dateFmt:'HH:mm'})"  type="text" value="">
            </td>
            </tr>
            <tr>
            <td class="t-title">工作时溢出组:</td>
            <td>
            	<input class="t-text" id="workoverflowgroup" type="text" value="">
            </td>
            <td class="t-title">非工作时溢出组:</td>
            <td>
            	<input class="t-text" id="noworkoverflowgroup" type="text" value="">
            </td>
            <td>
               <a href="javascript:void(0)" id="queryBtn" class="but-search"></a>
            </td>
            <td>
		      	<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
		      	
		      	<a class="easyui-linkbutton"  data-options="iconCls:'icon-print'"  href="<%=basePath%>workGroupAction_exportExcel.action">导出</a>
		    </td>
            </tr>
	 </table>
  </div>
		<div class="search-list">
        	<span class="list-title">工作组列表</span>
            <table id="tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/workGroupAction_edit.action?workgroup.id="/>
		<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/workGroupAction_detail.action?workgroup.id="/>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/workGroupAction_delete.action?workgroup.id="/>
		<input type="hidden" id="message" value="${message }"/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		<div id="toolbar">
		<a href='<%=basePath%>workGroupAction_addUI.action' class='easyui-linkbutton' iconCls='icon-add' plain='true'>新增</a>
		<a href="javascript:void(0)" onclick="sendmail()" class='easyui-linkbutton' iconCls='icon-add' plain='true'>邮件发送</a>
		</div>
	</body>
</html>
