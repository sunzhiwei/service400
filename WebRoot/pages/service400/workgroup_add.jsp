<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
	<title>工作组添加</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	.worker{ text-align:right;}
	.worker_two{border: #d1deea solid 1px; height: 30px; line-height: 30px; width: 258px; padding: 0 8px;}
	.shouji_button {cursor:pointer; background: #FCECAB; border: 1px solid #F2C409; text-align: center; color: #333333; width:46px; height:28px; line-height:28px; float:left;}
	.zm_button{cursor:pointer; background: #ACDEFF; border: 1px solid #6AC4FF; line-height:30px; text-align: center; color: #333333; width:46px; height:30px; float:left;}
	.line_ku td{border:1px dotted #C7D3E3; padding:10px; background:#F5F8FD;}
	</style>
	</head>
	<body>
	<input type="hidden" id="allworkgroup" value="${allworkgroup }"/>
	<input type="hidden" id="allnoworkflowgroup" value="${allnoworkflowgroup }"/>
	<div class="tableForm">
	<div class="title">工作组增加</div>
	<form id="myForm" method="post">
	<table style="margin:20px auto">
		<tr class="line_ku" style="line-height:38px">
			 <td class="worker">组名:</td>
			 <td><input class="worker_two" type="text" id="name" name="workgroup.name" value=""/><span class="required">*</span></td>
		</tr>
		<tr class="line_ku" style="line-height:38px">
			 <td class="worker">透传号码:</td>
			 <td>
			  <input class="worker_two" type="text" id="adapternum" name="workgroup.adapternum" value=""/><span class="required">*</span>
			 </td>
		</tr>
		<tr class="line_ku" style="line-height:38px">
				<td class="worker">工作周:</td>
				<td>
				    <select style="width:125px;" id="startweek" name="workgroup.startweek">
				    <option value="" selected="selected">全部</option>
				    <option value="周一">周一</option>
				    <option value="周二">周二</option>
				    <option value="周三">周三</option>
				    <option value="周四">周四</option>
				    <option value="周五">周五</option>
				    <option value="周六">周六</option>
				    <option value="周日">周日</option>
				    </select>
				    至
				    <select style="width:125px;" id="endweek" name="workgroup.endweek">
				    <option value="" selected="selected">全部</option>
				    <option value="周一">周一</option>
				    <option value="周二">周二</option>
				    <option value="周三">周三</option>
				    <option value="周四">周四</option>
				    <option value="周五">周五</option>
				    <option value="周六">周六</option>
				    <option value="周日">周日</option>
				    </select>
				</td>
			</tr>
			<tr class="line_ku" style="line-height:38px">
				<td class="worker">工作时间:</td>
				<td>
				    <input style="width:110px;" class="t-text Wdate" type="text" id="starttime" name="workgroup.starttime" value="" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
				    至
				    <input style="width:110px;" class="t-text Wdate" type="text" id="endtime" name="workgroup.endtime" value="" onfocus="WdatePicker({dateFmt:'HH:mm'})"/><span class="required">*</span>
				</td>
			</tr>
	<tr class="line_ku" style="line-height:38px">
		 <td class="t-title">工作时是否溢出:</td>
	    <td>
	     <input type="radio"  name="workgroup.workisoverflow" value="7" onchange = "workChange(this);" checked="checked"/>是&nbsp;&nbsp;&nbsp;
		 <input type="radio"  name="workgroup.workisoverflow" value="8" onchange = "workChange(this);" />否
		 </td>
	</tr>
	<tr class="line_ku" id="work" style="line-height:38px">
		<td class="worker">工作时溢出组:</td>
		    <td>
		    <select id="workOverFlowSelect" name="workgroup.workoverflowGroup" style="width: 276px; padding:0 0"></select>
		    </td>
    </tr>
    <tr class="line_ku" style="line-height:38px">
	 	<td class="t-title">非工作时是否溢出:</td>
	    <td>
		     <input type="radio"  name="workgroup.noworkisoverflow" value="7" onchange = "unWorkChange(this);" checked="checked"/>是&nbsp;&nbsp;&nbsp;
			 <input type="radio"  name="workgroup.noworkisoverflow" value="8" onchange = "unWorkChange(this);" />否
		</td>
	</tr>
    <tr class="line_ku" id="unWork" style="line-height:38px">
	 	<td class="worker">非工作时间溢出组:</td>
	    <td>
		    <select id="noworkOverFlowSelect" name="workgroup.noworkoverflowGroup" style="float:left; width: 226px; padding:0 0"></select>
		    <a class="shouji_button" id="phone" value="手机" onclick="show(1)" style="display: block">手机</a>
		    <input class="t-text" type="text" id="noworkOverFlowPhone" name="workgroup.noworkoverflowGroup" style="width: 209px;display: none;float:left;">
		    <a class="zm_button" id="groupname" value="组名" onclick="show(2)" style="display: none">组名</a>
	    </td>
    </tr>
    <tr class="line_ku" id="unWorkVoice" style="display: none;">
		<td class="t-title">非工作时语音:</td>
	    <td colspan="3">
	    <textarea class="t-text" id="noworkvoice" style="width:256px;height: 60px" rows="30" cols="30" name="workgroup.noworkvoice"></textarea>
    </tr>
	<tr class="line_ku" style="line-height:38px">
		<td class="worker">忙音:</td>
	    <td>
		    <textarea style="width: 256px; height:90px"" name="workgroup.busyTone" rows="10" cols="30"></textarea>
	    </td>
	</tr>
</table>
<div class="t-but" style="text-align: center;">
		    	<a style="margin-right:12px;" href="javascript:void(0)" class="but-change" onclick="submitForm();" plain="true">添加</a>
				<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
            </div>
</form>
	</div>
	</body>
	<script type="text/javascript">
	function workChange(obj){
		var value = obj.value;
		if(value==7){
			$("#work").css("display","block");
			$("#work").css("display","table-row");
		}else{
			$("#work").css("display","none");
			$("#workOverFlowSelect").val("");
		}
	}
	    //初始化select的值
        function selectOption(typeArray)
        {
          var overflowHtmls="<option value=''>--请选择--</option>";
          for(var i=0;i<typeArray.length;i++){
        	  overflowHtmls+='<option value="'+typeArray[i]+'" >'+typeArray[i]+'</option>';
          }
          return overflowHtmls;
        };
	
        var workOverFlow=$("#allworkgroup").val();
        //var noworkOverFlow=$("#allworkgroup").val();
        var noworkOverFlow=$("#allnoworkflowgroup").val();
        var str1=new Array();
        var str2=new Array();
        if(workOverFlow!="")
           str1=workOverFlow.split(",");
        if(noworkOverFlow!="")
            str2=noworkOverFlow.split(",");

        $('#workOverFlowSelect').html(selectOption(str1)); 
        $('#noworkOverFlowSelect').html(selectOption(str2));

        function unWorkChange(obj){
        	var noworkisoverflow_new=obj.value;//$("input[name=workgroup.noworkisoverflow]:checked").val();
        	if(noworkisoverflow_new==8){
        		$("#unWork").css("display","none");
        		$("#unWorkVoice").css("display","block");
        		$("#unWorkVoice").css("display","table-row");
        		$("#noworkOverFlowSelect").val("");
        		$("#noworkOverFlowPhone").val("");
        	}else{
        		$("#unWork").css("display","block");
        		$("#unWork").css("display","table-row");
        		$("#unWorkVoice").css("display","none");
        		$("#noworkvoice").val("");
        	}
        }

        function show(type){
            if(type==1){
            	$('#noworkOverFlowSelect').css('display','none');
            	$("#noworkOverFlowSelect").attr("value",'');
            	$("#noworkOverFlowSelect").prop("disabled", true);
            	$('#phone').css('display','none');
            	$('#noworkOverFlowPhone').css('display','block');
            	$("#noworkOverFlowPhone").prop("disabled", false);
            	$('#groupname').css('display','block');
            }else{
            	$('#noworkOverFlowPhone').css('display','none');
            	$("#noworkOverFlowPhone").attr("value",'');
            	$("#noworkOverFlowPhone").prop("disabled", true);
            	$('#groupname').css('display','none');
            	$('#noworkOverFlowSelect').css('display','block');
            	$("#noworkOverFlowSelect").prop("disabled", false);
            	$('#phone').css('display','block');
            }
        }
        
        // 设置ajax同步
        $.ajaxSetup({  
            async : false  
        });
        var flag; //
  	    isrepeatName=function(name)
  	    {
  		  $.post(
      	  	            "<%=basePath%>workGroupAction_isrepeatName.action",
      	  	            {workgroupname:name},
      					function(data){
          					flag=data;
      					}
      				);
  			if(flag==0)
  				return false;
  			else
  			    return true;
        }; 

        function submitForm(){
             // 效验
        	//var reg=/^([\d]+|([\d]+[.]?|[\d]+[.]?[\d]+))$/;
        	var reg=/^\d{8,12}$/  // 8到12位号码
            
            var name=$("#name").val();
            var adapternum=$("#adapternum").val();
            var noworkvoice=$("#noworkvoice").val();
            var starttime=$("#starttime").val();
            var endtime=$("#endtime").val();
            var workOverFlowgroup=$("#workOverFlowSelect").val();
            var noworkoverflowgroup=$("#noworkOverFlowSelect").val();
            var noworkoverflowphone=$("#noworkOverFlowPhone").val();
            var workisoverflow=$("input[name='workgroup.workisoverflow']:checked").val();
            var noworkisoverflow=$("input[name='workgroup.noworkisoverflow']:checked").val();
            var allworkgroup=$("#allworkgroup").val();
            var allnoworkflowgroup=$("#allnoworkflowgroup").val();
            var startweek=$("#startweek").val();
            var endweek=$("#endweek").val();
            if(name==""){
          	  $.messager.alert("提示", "请您输入组名，组名不能为空！");
      		  $("#name").focus();
      		  return false;
                }
              if(isrepeatName(name) == true){
  		      $.messager.alert("提示", "您输入的组名与数据库中有重复，组名不允许重复，请修改！");
  		      $("#name").focus();
  		      return false;
  		     }
            if(adapternum==""){
      		  $.messager.alert("提示", "请您输入透传号码，透传号码不能为空！");
      		  $("#adapternum").focus();
      		  return false;
      	    }
            if(reg.test(adapternum)== false){
      		  $.messager.alert("提示", "透传号码只能为数字，且是8到12位以内的数字！");
      		  $("#adapternum").focus();
      		  return false;
      	  }
            if(startweek==""){
            	  $.messager.alert("提示", "请您输入工作周的开始工作周，开始工作周不能为空！");
        		  $("#startweek").focus();
        		  return false;
                  }
            if(endweek==""){
            	  $.messager.alert("提示", "请您输入工作周的结束工作周，结束工作周不能为空！");
        		  $("#endweek").focus();
        		  return false;
                  }
            if(starttime==""){
            	  $.messager.alert("提示", "请您输入开始工作时间，开始工作时间不能为空！");
        		  $("#starttime").focus();
        		  return false;
                  }
            if(endtime==""){
          	  $.messager.alert("提示", "请您输入结束工作时间，结束工作时间不能为空！");
      		  $("#endtime").focus();
      		  return false;
                }
            
            
            if(workisoverflow==7 && allworkgroup!="" && workOverFlowgroup==""){
          		$.messager.alert("提示", "请您选择工作时间溢出组，工作时间溢出组不能为空！");
     			$("#workOverFlowSelect").focus();
     			return false;
      	  }
            if(noworkisoverflow==7 && allworkgroup!=""){
            	if($('#noworkOverFlowSelect').is(":visible") && noworkoverflowgroup==""){
            		$.messager.alert("提示", "请您选择非工作时间溢出组，非工作时间溢出组不能为空！");
         			$("#noworkOverFlowSelect").focus();
         			return false;
            	}else if($('#noworkOverFlowPhone').is(":visible") && noworkoverflowphone==""){
            		$.messager.alert("提示", "请您填写非工作时间溢出的手机号，非工作时间溢出的手机号不能为空！");
         			$("#noworkOverFlowPhone").focus();
         			return false;
            	}else if($('#noworkOverFlowPhone').is(":visible") && noworkoverflowphone!="" && !(/^1\d{10}$/g).test(noworkoverflowphone)){
            		$.messager.alert("提示", "非工作时间溢出组手机号只能为11位数字,且为1开头！");
         			$("#noworkOverFlowPhone").focus();
         			return false;
            	}
            }else if(noworkvoice==""){
                  //alert("noworkisoverflow:"+noworkisoverflow);
        		  $.messager.alert("提示", "请您输入非工作时语音，非工作时语音不能为空！");
          		  $("#noworkvoice").focus();
          		  return false;
          	  }

            
            var myForm=$("#myForm");
            var option={
         		   url:"<%=basePath%>workGroupAction_add.action",
         		   success:function(data){
             		      $.messager.alert("提示","增加成功！","info",function(){
             		    	 window.location.href="<%=basePath%>workGroupAction_toList.action";
  					});
         		   }};
            $('#myForm').ajaxSubmit(option);
	 }
        
	</script>
</html>
