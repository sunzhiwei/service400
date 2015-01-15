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
	    function closeLoop(id){
		      window.location.href='<%=basePath%>query400Action_closeLoopByID.action?tsf.id='+id;
	    }
   		$(function() {
   			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  // 向服务器传递的参数
					type:$("#type").val(),
					sendstarttime:$("#sendstarttime").val(),
					sendendtime:$('#sendendtime').val(),
			        }); 
			})

			$("#resetBtn").click(function(){
				sendstarttime:$("#sendstarttime").val("");
				sendendtime:$('#sendendtime').val("");
			    type:$("#type").val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>query400Action_queryCLPageJson.action',
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
					  {field:'sendperson',title:'申请人',width:fixColumnWidth(0.0700)},
					  {field:'sendtime',title:'申请时间',width:fixColumnWidth(0.0500)},
					  {field:'type',title:'申请类型',width:fixColumnWidth(0.0500)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node){
						              return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='closeLoop("
						                     +node.id+")' >确认完成</a>&nbsp;</span>"
		   		                             +"&nbsp;<a class=\"operation-a\" id=\"operation\" href='${path}/query400Action_download.action?tsf.id="+node.id+"'>申请信息</a>"
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
		  <td>申请类型:</td>
             <td>
             	<select id="type">
	             <option value="">全部</option>
	             <option value="B">IVR申请</option>
	             <option value="C">工作组申请</option>
            	</select>
            </td>
		    <td class="t-title">发送申请时间:</td>
            <td>
            	<input class="t-text" id="sendstarttime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="sendstarttime" type="text" value="">
            	—
            	<input class="t-text" id="sendendtime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="sendendtime" type="text" value="">
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
        	<span class="list-title">IVR及工作组维护申请记录列表</span>
            <table id="tt" toolbar="#toolbar"></table></div>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</body>
</html>
