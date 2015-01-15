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
	<script type="text/javascript">

	function addTr(){
		$("#add").css("display","block");
	}
	function getTsInfo(id){
		$.get("${path }/num400Action_update400page.action?id="+id, function(data){
			  $("#id").val(id);
			  var da = data.split(":");
			  $("#phonenum").val(da[0]);
			  $("#price").val(da[1]);
			  $("#update").css("display","block");
		});
	}
	function checkAddNum(){
		var flag = true;
		var phonenum = $("#phonenum");
		var tit = $("#tit").val();
		var num = $("#num").val();
		var pn =tit+""+num; 
		var re = /^\d{10}$/;
		if(!re.test(pn)){
			$.messager.alert("提示", "您输入的400号码不符合格式！");
			flag = false;
		}else{
			phonenum.val(pn);
		}
		
		var price = $("#price").val();
		re = /^\d{2,4}(\.\d{1,2})?$/;
		if(!re.test(price)){
			$.messager.alert("提示", "月最低消费额请填写数字且最多两位小数！");
			flag = false;
		}
		if(flag){
			document.getElementById("addNum").submit();
		}
		
	}
	
   		$(function() {
   			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  // 向服务器传递的参数
					phonenum:$('#ser400_phonenum').val(),
					company:$('#company').val()
			        }); 
			})

			$("#resetBtn").click(function(){
				phonenum:$('#phonenum').val("");
				company:$('#company').val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>num400Action_queryPageJson.action',
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
					  {field:'phonenum',title:'400号码',width:fixColumnWidth(0.0700)},
					  {field:'price',title:'月最低消费',width:fixColumnWidth(0.0700)},
					  {field:'operation_company',title:'运营商',width:fixColumnWidth(0.0500)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node){
						    	   return "<a class=\"operation-a\" href='javascript:void(0)' onclick='deleteRecord("+node.id+")' >删除</a>";
						   }
			   		  }
				]],
				//下面 定义 分页配置 ：
				pageSize:20,
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
	<div class="search" style="display: none" id="add">
	<form id="addNum" action="<%=basePath%>num400Action_add400.action">
			<table>
				<tr>
					<td class="t-title">400号码：<input id="tit" value="400" maxlength="4" style="width: 30px" />-
								<input class="t-text" id="num">
						<input type="hidden" id="phonenum" name="ts.phonenum" maxlength="10" value="">
					</td>
					<td>月最低消费额(元/月)：<input class="t-text" id="price" name="ts.price" value=""></td>
					<td >运营商：
						<select name="ts.operationCompany">
							<option selected="selected" value="YD">移动</option>
							<option value="DX">电信</option>
							<option value="LT">联通</option>
						</select>
					</td>
					<td><div class="t-but"><a href="javascript:void(0)" class="but-change" onclick="checkAddNum()" plain="true">添加</a></div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
		  	<td class="t-title">400号码:</td>
            <td>
            	<input class="t-text" id="ser400_phonenum" name="phonenum" type="text" value="">
            </td>
            <td>运营商:</td>
            <td>
            <select id="company" name="company">
				 <option value="">全部</option>
	             <option value="YD">移动</option>
	             <option value="DX">电信</option>
	             <option value="LT">联通</option>
            	</select>
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
        	<span class="list-title">400号码列表</span>
            <table id="tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/num400Action_delete400.action?id="/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		<div id="toolbar">
		<a href='javascript:void(0)' onclick="addTr()" class='easyui-linkbutton' iconCls='icon-add' plain='true'>添加</a>
		</div>
	</body>
</html>
