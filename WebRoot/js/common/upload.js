/**
 * 导入excel
 */
function importExcelFun(){
	var file = document.getElementById('excelFile');
	var aa = getPath(file);
	if(aa==''){
		$.messager.alert("提示","请选择文件","info");	
 	}else if(!isExcel(file.value)){
		$.messager.alert("提示","请选择excel类型文件","info");	
 	}else{
 		$("#nn").val(file.value);
 		mask("正在导入数据，请稍候...");
 		$("#myform").ajaxSubmit({
 			type: $("#myform").method,
 			url:$("#myform").action,
 			resetForm: true,
 			success:function(json){//文件上传成功后执行,json
 				$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
	 				unmask(); 
	 				$("#tt").datagrid("reload");  			
 				});
 			}
 		});	
 		return false;//防止刷新
 	}
}
/**
 * 获取文件路径
 * @param obj
 * @return
 */
function getPath(obj)  {
	if(obj){
	  if (window.navigator.userAgent.indexOf("MSIE")>=1){
	       obj.select();
	      return document.selection.createRange().text;
      }else if(window.navigator.userAgent.indexOf("Firefox")>=1){        
	      if(obj.files && obj.files.length>0){          
	           return window.URL.createObjectURL(obj.files[0]);       
	      }        
	      return obj.value;        
      }  
         return obj.value;   
	}  
} 
/**
 * 判断文件类型
 * @param file
 * @return
 */
function isExcel(file){
	var strFilter=".xlsx|xls"
	if(file.indexOf(".")>-1){
		var p = file.lastIndexOf(".");
		var strPostfix=file.substring(p,file.length);
		strPostfix = strPostfix.toLowerCase();
		if(strFilter.indexOf(strPostfix)>-1){
			return true;
		}
	}        
	return false;            
} 