package com.yixin.service400.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.service400.bean.TService400Applicationform;
import com.yixin.service400.bean.TService400Key;
import com.yixin.service400.bean.TService400User;
import com.yixin.service400.bean.TService400Workgroup;

public class MailUtil {
	/**
	 * 
	 * @param ts
	 * 
	 */
	public static void sendApplyMail(TService400Applicationform ts,File file) {
		Map<Integer,Map<Integer,String>> table=new HashMap<Integer,Map<Integer,String>>();
		Map<Integer,String> title=new HashMap<Integer,String>();
		title.put(1, "400号码申请表");
        Map<Integer,String> map=new HashMap<Integer,String>();
        map.put(0, 1+"");
		map.put(1, ts.getApplicationperson());
		map.put(2, ts.getPhonenum()+"");
		map.put(3, ts.getPhonenumPrice()+"");
		map.put(4, ts.getNumcategory());
		map.put(5, ts.getOpentime());
		map.put(6, ts.getApplicationpersonPhone()+"");
		map.put(7, ts.getDepartment());
		map.put(8, ts.getApplicationpersonEmail());
		map.put(9, ts.getUseinstructions());
		table.put(2, map);
        
		String copypersonemail="";
		String allpersonemail=ts.getApplicationpersonEmail()+";"+ts.getDepartmentheaderEmail();
		copypersonemail=ts.getCopypersonEmail();
		String email[]=allpersonemail.split(";");
		String copyemail[]=copypersonemail.split(";");
		List<String> receiveUserList=new ArrayList<String>();
		List<String> copyreceiverList=new ArrayList<String>();
		for(int i=0;i<email.length;i++){
			  receiveUserList.add(email[i]);
		}
		for(int i=0;i<copyemail.length;i++){
			if(!"".equals(copyemail[i]))
			copyreceiverList.add(copyemail[i]);
		}
		String leader="";
		if(ts.getDepartmentheaderEmail()!=null && !("").equals(ts.getDepartmentheaderEmail()))
			leader=ts.getDepartmentheaderEmail().substring(0,ts.getDepartmentheaderEmail().indexOf("@creditease.cn"));
		else
			leader="尊敬的领导";
		String subject="400号码申请审批";
		String subjectHtml=leader+"\r\n"
		                   +"   您好，以下为 "+ts.getApplicationperson()+" 所申请的400号码相关信息，请您审批"+"\r\n"
			               +"申请人:"+ts.getApplicationperson()+"\r\n"
		                   +"申请400号码:"+ts.getPhonenum()+"\r\n"
		                   +"月最低消费 :"+ts.getPhonenumPrice()+"\r\n"
		                   +"号码类型:"+ts.getNumcategory()+"\r\n"
		                   +"预计开通时间 :"+ts.getOpentime()+"\r\n"
		                   +"申请人手机 :"+ts.getApplicationpersonPhone()+"\r\n"
		                   +"申请人公司 :"+ts.getCompany()+"\r\n"
		                   +"申请人部门 :"+ts.getDepartment()+"\r\n"
		                   +"申请人邮箱 :"+ts.getApplicationpersonEmail()+"\r\n"
		                   +"申请用途:"+ts.getUseinstructions()+"\r\n"
		                   +"\r\n"
		                   +"请回复全部"+"\r\n"
		                   +"祝好！";
		subjectHtml=subjectHtml.replaceAll("\n", "<br>");
		Template t=new Template();
        t.excel_400num(table, file.getAbsolutePath());  
		JavaMailWithAttachment mail=new JavaMailWithAttachment(true);
		List<File> affixs=new ArrayList<File>();
		affixs.add(file);
		mail.doSendHtmlEmail(subject, subjectHtml, receiveUserList, copyreceiverList, affixs);
	}
	
	public static void sendWorkGroupMail(List<TService400Workgroup> workgrouplist,TService400User user,File file){
		List<TService400Workgroup> addList=new ArrayList<TService400Workgroup>();
		List<TService400Workgroup> updateList=new ArrayList<TService400Workgroup>();
		List<TService400Workgroup> deleteList=new ArrayList<TService400Workgroup>();
		Map<Integer,Map<Integer,String>> table=new HashMap<Integer,Map<Integer,String>>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String date=format.format(new Date());
		Map<Integer,String> title=new HashMap<Integer,String>();
		Map<Integer,String> application_person=new HashMap<Integer,String>();
		Map<Integer,String> application_time=new HashMap<Integer,String>();
		title.put(2, "工作组维护申请表");
		application_person.put(0,"申请人");
		application_person.put(1,user.getUsername());
		application_time.put(0, "申请时间");
		application_time.put(1, date);
		table.put(1, application_person);
		table.put(2, application_time);
		for(int i=0;i<workgrouplist.size();i++){
			TService400Workgroup workgroup=workgrouplist.get(i);
				if(workgroup.getStatus().intValue()==StatusUtil.CONST_WORKGROUP_STATUS_ADD.intValue())
					addList.add(workgroup);
				else if(workgroup.getStatus().intValue()==StatusUtil.CONST_WORKGROUP_STATUS_UPDATE.intValue())
					updateList.add(workgroup);
				else if(workgroup.getStatus().intValue()==StatusUtil.CONST_WORKGROUP_STATUS_DELETE.intValue())
					deleteList.add(workgroup);
		    int j=4;
		    int k=1;
			
			for(TService400Workgroup wg:addList){
				Map<Integer,String> map=new HashMap<Integer,String>();
				map.put(0, k+"");
				map.put(1, wg.getName());
				map.put(2, wg.getAdapternum()+"");
				map.put(3, wg.getStartweek());
				map.put(4, wg.getEndweek());
				map.put(5, wg.getStarttime());
				map.put(6, wg.getEndtime());
				map.put(7, wg.getWorkoverflowGroup());
				map.put(8, wg.getBusyTone());
				map.put(9, wg.getNoworkoverflowGroup());
				map.put(10, wg.getNoworkvoice());
				map.put(11, "新增");
				table.put(j+1, map);
				j++;
				k++;
			}
			
			for(TService400Workgroup wg:updateList){
				Map<Integer,String> map=new HashMap<Integer,String>();
				map.put(0, k+"");
				map.put(1, wg.getName());
				map.put(2, wg.getAdapternum()+"");
				map.put(3, wg.getStartweek());
				map.put(4, wg.getEndweek());
				map.put(5, wg.getStarttime());
				map.put(6, wg.getEndtime());
				map.put(7, wg.getWorkoverflowGroup());
				map.put(8, wg.getBusyTone());
				map.put(9, wg.getNoworkoverflowGroup());
				map.put(10, wg.getNoworkvoice());
				map.put(11, "修改");
				table.put(j+1, map);
				j++;
				k++;
			}
			
			for(TService400Workgroup wg:deleteList){
				Map<Integer,String> map=new HashMap<Integer,String>();
				map.put(0, k+"");
				map.put(1, wg.getName());
				map.put(2, wg.getAdapternum()+"");
				map.put(3, wg.getStartweek());
				map.put(4, wg.getEndweek());
				map.put(5, wg.getStarttime());
				map.put(6, wg.getEndtime());
				map.put(7, wg.getWorkoverflowGroup());
				map.put(8, wg.getBusyTone());
				map.put(9, wg.getNoworkoverflowGroup());
				map.put(10, wg.getNoworkvoice());
				map.put(11, "删除");
				table.put(j+1, map);
				j++;
				k++;
			}
		}
		
		List<String> copyreceiverList=new ArrayList<String>();
		copyreceiverList.add(user.getUsername());
		Template t=new Template();
		t.excel_400num(table,file.getAbsolutePath());
		String subject="工作组维护申请审批";
		String subjectHtml="尊敬的领导"+"\r\n"
                           +"   您好，附件为所维护的工作组信息，请您审批";
		subjectHtml=subjectHtml.replaceAll("\n", "<br>");
		JavaMailWithAttachment mail=new JavaMailWithAttachment(true);
		List<File> affixs=new ArrayList<File>();
		affixs.add(file);
		mail.doSendHtmlEmail_WG_IVR(subject, subjectHtml,copyreceiverList,affixs);
	}
	
	public static void sendIVRMail(List<TService400Key> Ivrlist,TService400User user,File file,List<TService400Applicationform> appForm){
		List<TService400Key> addList=new ArrayList<TService400Key>();
		List<TService400Key> updateList=new ArrayList<TService400Key>();
		List<TService400Key> deleteList=new ArrayList<TService400Key>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String date=format.format(new Date());
		Map<Integer,Map<Integer,String>> table=new HashMap<Integer,Map<Integer,String>>();
		Map<Integer,String> title=new HashMap<Integer,String>();
		Map<Integer,String> application_person=new HashMap<Integer,String>();
		Map<Integer,String> application_time=new HashMap<Integer,String>();
		Map<Integer,String> application_phone=new HashMap<Integer,String>();
		Map<Integer,String> application_landnum1=new HashMap<Integer,String>();
		Map<Integer,String> application_landnum2=new HashMap<Integer,String>();
		Map<Integer,String> application_landnum3=new HashMap<Integer,String>();
		title.put(2, "IVR维护申请表");
		application_person.put(0,"申请人");
		application_person.put(1,user.getUsername());
		application_time.put(0, "申请时间");
		application_time.put(1, date);
		List<String> copyreceiverList=new ArrayList<String>();
		copyreceiverList.add(user.getUsername());
		if(appForm.get(0)!=null){
			application_phone.put(0, "申请号码");
			application_phone.put(1, appForm.get(0).getPhonenum()+"");
			application_landnum1.put(0, "落地号1");
			application_landnum1.put(1, appForm.get(0).getLangingnum());
			application_landnum2.put(0, "落地号2");
			application_landnum2.put(1, appForm.get(0).getLangingnum2());
			application_landnum3.put(0, "落地号3");
			application_landnum3.put(1, appForm.get(0).getLangingnum3());
            if(!user.getUsername().equals(appForm.get(0).getApplicationpersonEmail()))
            	copyreceiverList.add(appForm.get(0).getApplicationpersonEmail());
		}
		
		table.put(1, application_person);
		table.put(2, application_time);
		table.put(3, application_phone);
		table.put(4, application_landnum1);
		table.put(5, application_landnum2);
		table.put(6, application_landnum3);
		String helloword="首问语";
        for(int i=0;i<Ivrlist.size();i++){
        	TService400Key key=Ivrlist.get(i);
			if(key.getStatus().intValue()==TService400Key.STATUS_ADD.intValue())
				addList.add(key);
			else if(key.getStatus().intValue()==TService400Key.STATUS_UPDATE.intValue())
				updateList.add(key);
			else if(key.getStatus().intValue()==TService400Key.STATUS_DELETE.intValue())
				deleteList.add(key);
			
			 int j=9;
			 int k=1;
			 for(TService400Key ivr:addList){
					Map<Integer,String> map=new HashMap<Integer,String>();
					map.put(0, k+"");
					map.put(1, "新增");
					map.put(3, ivr.getContent());
					String path = ivr.getPath();
					if(path!=null && !("").equals(path)){
						String arrayPath[]=path.split("-");
						int m=1;
						for(int a=1;a<arrayPath.length;a++){
							String ivrpath=arrayPath[a];
						if (("11").equals(ivrpath)) {
							map.put(3 + m, helloword);
							if (a == arrayPath.length - 1) {
								ivr.setName(helloword);
							}
						} else if (a == arrayPath.length - 1
								&& ivr.getWorkgroup_flag() != null
								&& ivr.getWorkgroup_flag().intValue() == 1) {
							map.put(13, ivrpath);
						} else {
							map.put(3 + m, ivrpath);
						}
							m++;
						}
					}
					map.put(2, ivr.getName());
					table.put(j+1, map);
					j++;
					k++;
				}
			 for(TService400Key ivr:updateList){
					Map<Integer,String> map=new HashMap<Integer,String>();
					map.put(0, k+"");
					map.put(1, "修改");
					map.put(3, ivr.getContent());
					String path = ivr.getPath();
					if(path!=null && !("").equals(path)){
						String arrayPath[]=path.split("-");
						int m=1;
						for(int a=1;a<arrayPath.length;a++){
							String ivrpath=arrayPath[a];
							if (("11").equals(ivrpath)) {
								map.put(3 + m, helloword);
								if (a == arrayPath.length - 1) {
									ivr.setName(helloword);
								}
							} else if (a == arrayPath.length - 1
									&& ivr.getWorkgroup_flag() != null
									&& ivr.getWorkgroup_flag().intValue() == 1) {
								map.put(13, ivrpath);
							} else {
								map.put(3 + m, ivrpath);
							}
							m++;
						}
					}
					map.put(2, ivr.getName());
					table.put(j+1, map);
					j++;
					k++;
				}
			 for(TService400Key ivr:deleteList){
					Map<Integer,String> map=new HashMap<Integer,String>();
					map.put(0, k+"");
					map.put(1, "删除");
					map.put(3, ivr.getContent());
					String path = ivr.getPath();
					if(path!=null && !("").equals(path)){
						String arrayPath[]=path.split("-");
						int m=1;
						for(int a=1;a<arrayPath.length;a++){
							String ivrpath=arrayPath[a];
							if (("11").equals(ivrpath)) {
								map.put(3 + m, helloword);
								if (a == arrayPath.length - 1) {
									ivr.setName(helloword);
								}
							} else if (a == arrayPath.length - 1
									&& ivr.getWorkgroup_flag() != null
									&& ivr.getWorkgroup_flag().intValue() == 1) {
								map.put(13, ivrpath);
							} else {
								map.put(3 + m, ivrpath);
							}
							m++;
						}
					}
					map.put(2, ivr.getName());
					table.put(j+1, map);
					j++;
					k++;
				}
        }
        
        // 发送邮件变更“首问语”后改回原名称“11”
        for(TService400Key k:Ivrlist){
        	if(helloword.equals(k.getName()))
        		k.setName("11");
        }
        
        Template t=new Template();
        t.excel_400num(table, file.getAbsolutePath());
		
		String subject="IVR维护申请审批";
		String subjectHtml="尊敬的领导"+"\r\n"
                           +"   您好，附件为所维护的IVR信息，请您审批";
		subjectHtml=subjectHtml.replaceAll("\n", "<br>");
		JavaMailWithAttachment mail=new JavaMailWithAttachment(true);
		List<File> affixs=new ArrayList<File>();
	    affixs.add(file);
	    mail.doSendHtmlEmail_WG_IVR(subject, subjectHtml,copyreceiverList,affixs);
	}
	
	public static void main(String s[]){
		TService400Applicationform ts=new TService400Applicationform();
		ts.setApplicationperson("箫剑");
		ts.setPhonenum(4008001000l);
		ts.setPhonenumPrice(250d);
		ts.setNumcategory("飞机订票热线");
		ts.setOpentime("2014-08-07");
		ts.setApplicationpersonPhone(18610364019l);
		ts.setCompany("宜信公司");
		ts.setDepartment("研发六部");
		ts.setUseinstructions("开发测试用途");
		ts.setApplicationpersonEmail("867242608@qq.com");
		ts.setDepartmentheaderEmail("2427356743@qq.com");
		ts.setCopypersonEmail("867242608@qq.com;2427356743@qq.com");
//		MailUtil.sendApplyMail(ts);
	}

}
