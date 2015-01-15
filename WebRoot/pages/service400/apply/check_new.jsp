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
	
   		$(function() {
   			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  // 向服务器传递的参数
					applicationperson:$("#applicationperson").val(),
					phonenum:$('#phonenum').val(),
					company:$('#company').val(),
					department:$("#department").val(),
					startapplicationtime:$("#startapplicationtime").val(),
					endapplicationtime:$("#endapplicationtime").val()
			        }); 
			})

			$("#resetBtn").click(function(){
				applicationperson:$("#applicationperson").val("");
				phonenum:$('#phonenum').val("");
				company:$('#company').val("");
				department:$("#department").val("");
			    startapplicationtime:$("#startapplicationtime").val("");
			    endapplicationtime:$("#endapplicationtime").val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>apply400Action_queryPageJson.action',
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
					  {field:'applicationperson',title:'申请人',width:fixColumnWidth(0.0700)},
					  {field:'phonenum',title:'申请号码',width:fixColumnWidth(0.0500)},
					  {field:'opentime',title:'预计开通时间',width:fixColumnWidth(0.0500)},
					  {field:'applicationtime',title:'申请时间',width:fixColumnWidth(0.0500)},
					  {field:'company',title:'公司',width:fixColumnWidth(0.0500)},
					  {field:'department',title:'部门',width:fixColumnWidth(0.0500)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node){
						           if(node.status==1)
						        	   return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"号码审核\",\"edit\","
					   		           +node.id+")' >补充申请</a>&nbsp;</span>"
					   		           +"&nbsp;<a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"号码审核\",\"view\","
					   		           +node.id+")' >确认完成</a>"
						           else if(node.status==0)
						    	        return "<a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"号码审核\",\"edit\","
						   		           +node.id+")' >确认申请</a>"
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
	<div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
		    <td class="t-title">申请人:</td>
            <td>
            	<input class="t-text" id="applicationperson" name="applicationperson" type="text" value="">
            </td>
		  	<td class="t-title">申请号码:</td>
            <td>
            	<input class="t-text" id="phonenum" name="phonenum" type="text" value="">
            </td>
            <td class="t-title">申请时间:</td>
            <td>
            	<input class="t-text" id="startapplicationtime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="startapplicationtime" type="text" value="">
            	—
            	<input class="t-text" id="endapplicationtime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="startapplicationtime" type="text" value="">
            </td>
            </tr>
            <tr>
            <td class="t-title">公司:</td>
            <td>
            	<input class="t-text" id="company" name="company" type="text" value="">
            </td>
            <td class="t-title">部门:</td>
            <td>
            	<input class="t-text" id="department" name="department" type="text" value="">
            </td>
            <td>
               <a href="javascript:void(0)" id="queryBtn" class="but-search"></a>
            </td>
            <td>
		      	<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
		    </td>
            </tr>
            </table>
  </div>
		<div class="search-list">
        	<span class="list-title">400号码申请审核列表</span>
            <table id="tt" toolbar="#toolbar"></table></div>
        <input type="hidden" id="editURL" value="<%=request.getContextPath()%>/apply400Action_conApp.action?ts.id="/>
		<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/apply400Action_clsLoop.action?ts.id="/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</body>
</html>
