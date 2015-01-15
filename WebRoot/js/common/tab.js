/* FireFox resize workaround start begin */  
var ffStartWidth = $(window).width();  
var frameWidth = window.screen.width-250;
/**
 * 火狐添加自适应监听（window.onresize不支持）
 * @return
 */
function doFirefoxResize() {  
    var resizeWidth = $(window).width();  
    if (resizeWidth != ffStartWidth) {  
        ffStartWidth = resizeWidth;  
        fixWidth();  
    }  
    setTimeout("doFirefoxResize();", 800);  
}  
/* FireFox resize workaround end */ 
/**
 * 页面加载时选项卡页面自适应宽度
 */
$(function(){
//	var href = getTopWin().$("#easyuiTheme").attr('href');
//	$("#easyuiTheme").attr('href', href)
//	$("#queryTab,#viewTab").css({width:frameWidth});
	if(document.all){//ie
		$(window).resize(function(){
			fixWidth();
		})
	}else{//火狐和其他浏览器
		doFirefoxResize();
	}
})
/**
 * 设置列表页列宽度
 * @param percent 百分比
 * @return
 */
function fixColumnWidth(percent){  
	return document.body.clientWidth * percent ;  
} 
/**
 * 选项卡面主要元素自适应
 * @return
 */
function fixWidth(){
//	$("#queryTab,#viewTab").css({
//		width:frameWidth<$(window).width()?$(window).width()-40:frameWidth});
	$('#tt').datagrid('resize', {
		width:frameWidth<$(window).width()?$(window).width()-40:frameWidth
	});
}
/**
 * 找到顶层window
 * @return
 */
function getTopWin(){
	var topWindow = window;
	while(topWindow != topWindow.parent){
		topWindow = topWindow.parent;
	}
	return topWindow;
}
/**
 * 添加新的选项卡
 * @param title选项卡标题
 * @param url选项卡url
 * @return
 */
function addTab(title, url){
	if (getTopWin().$('#tabs').tabs('exists', title)){
		getTopWin().$('#tabs').tabs('select', title);//选中并刷新
		var currTab = getTopWin().$('#tabs').tabs('getSelected');
		getTopWin().$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame((url!=undefined&&currTab.panel('options').title!='主页')
					?url:$(currTab.panel('options').content).attr('src'))
			}
		})
	} else {
		var content = createFrame(url);
		getTopWin().$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
/**
 * tab选项卡页面内容
 * @param url 选项卡url
 * @return
 */
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%;overflow:auto;"></iframe>';
	return s;
}

/**
 * 关闭选项卡
 * @return
 */
function tabClose() {
	/*双击关闭TAB选项卡*/
	getTopWin().$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		getTopWin().$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	getTopWin().$(".tabs-inner").bind('contextmenu',function(e){
		getTopWin().$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
 
		var subtitle =$(this).children(".tabs-closable").text();
 
		getTopWin().$('#mm').data("currtab",subtitle);
		getTopWin().$('#tabs').tabs('select',subtitle);
		return false;
	});
}
/**
 * 绑定右键菜单事件
 * @return
 */
function tabCloseEven() {
	//刷新
	getTopWin().$('#mm-tabupdate').click(function(){
		var currTab = getTopWin().$('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '主页') {
			getTopWin().$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	getTopWin().$('#mm-tabclose').click(function(){
		var currtab_title = getTopWin().$('#mm').data("currtab");
		getTopWin().$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	getTopWin().$('#mm-tabcloseall').click(function(){
		getTopWin().$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != '主页') {
				getTopWin().$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	getTopWin().$('#mm-tabcloseother').click(function(){
		var prevall = getTopWin().$('.tabs-selected').prevAll();
		var nextall = getTopWin().$('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=getTopWin().$('a:eq(0) span',$(n)).text();
				if(t != '主页') {
					getTopWin().$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=getTopWin().$('a:eq(0) span',$(n)).text();
				if(t != '主页') {
					getTopWin().$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	getTopWin().$('#mm-tabcloseright').click(function(){
		var nextall = getTopWin().$('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=getTopWin().$('a:eq(0) span',$(n)).text();
			getTopWin().$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	getTopWin().$('#mm-tabcloseleft').click(function(){
		var prevall = getTopWin().$('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=getTopWin().$('a:eq(0) span',$(n)).text();
			getTopWin().$('#tabs').tabs('close',t);
		});
		return false;
	});
 
	//退出
	getTopWin().$("#mm-exit").click(function(){
		getTopWin().$('#mm').menu('hide');
	})
}
/**
 * 点击取消操作时关闭选项卡
 * @return
 */
function closeTab(){
	getTopWin().$('#tabs').tabs('close',getTopWin().$('#tabs').tabs('getSelected').panel('options').title);
}
/**
 * 表单提交
 * @param id 必选项		提交表单时点击对象的id属性
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function submitTab(id,title){
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			if(result == true){
				if(id){
					$("#"+id).attr("disabled","disabled");
					$("#"+id).removeAttr('onclick');
				} 
				$("#editForm").ajaxSubmit({
					dataType:"text",
		 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
								toJson(json).success == "true"?"info":"error",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								if(refrTab){
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
											})
								}
								
							}
							closeTab();
						});
		 			}
		 		});	
			}else{
				if(id) $("#"+id).removeAttr("disabled");
			}
			return false;
		}
	})
}
/**
 * 打开新的选项卡（easyUI的tab）
 * @param btn 必选项 调用此函数的对象，传入this
 * @param parentTitle 必选项 当关闭编辑或者详细信息页面时需要刷新页面的title
 * @param type 必选项 类型，view——详细信息；edit——编辑页面
 * @param id 可选项（记录id） 不传时默认详细信息页面，传值时打开新增或者编辑页面
 * @return
 */
function openTab(btn,parentTitle,type,id){
	var me = $(btn);
	var url,title=parentTitle;
	switch (type) {
		case 'view':
			url = $("#detailURL").val()+id;	title+="_详细信息";
			break;
		case 'edit':
			url = $("#editURL").val()+id;	title+="_编辑信息";
			if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
			break;
	}
	
	addTab(title, url);
	
}

