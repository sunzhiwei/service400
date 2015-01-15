package com.yixin.service400.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TService400Applicationform entity. @author MyEclipse Persistence Tools
 */

public class TService400Applicationform implements java.io.Serializable {

	/**
	 * 申请状态：未审核
	 */
	public static final Integer STATUS_WSH = 0;
	/**
	 * 申请状态：已审核
	 */
	public static final Integer STATUS_YSH = 1;
	/**
	 * 申请状态：闭环
	 */
	public static final Integer STATUS_BH = 3;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6973512404454563180L;
	private Long id;
	private String applicationperson;
	private String opentime;
	private String applicationtime;
	private Long phonenum;
	private Double phonenumPrice;
	private String applicationpersonEmail;
	private Long applicationpersonPhone;
	private String company;
	private String department;
	private String costcentre;
	private String langingnum;
	private String langingnum2;
	private String langingnum3;
	private Long service400Id;
	private Integer status;
	private String factopentime;
	private String useinstructions;
	private String departmentheaderEmail;
	private String copypersonEmail;
	private Integer isivr;
	private String checktime;
	private String closetime;
	private String numcategory;
	private String filepath;

	// Constructors

	/** default constructor */
	public TService400Applicationform() {
	}

	/** minimal constructor */
	public TService400Applicationform(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationperson() {
		return this.applicationperson;
	}

	public void setApplicationperson(String applicationperson) {
		this.applicationperson = applicationperson;
	}

	public String getOpentime() {
		return this.opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getApplicationtime() {
		return this.applicationtime;
	}

	public void setApplicationtime(String applicationtime) {
		this.applicationtime = applicationtime;
	}

	public Long getPhonenum() {
		return this.phonenum;
	}

	public void setPhonenum(Long phonenum) {
		this.phonenum = phonenum;
	}

	public Double getPhonenumPrice() {
		return this.phonenumPrice;
	}

	public void setPhonenumPrice(Double phonenumPrice) {
		this.phonenumPrice = phonenumPrice;
	}

	public String getApplicationpersonEmail() {
		return this.applicationpersonEmail;
	}

	public void setApplicationpersonEmail(String applicationpersonEmail) {
		this.applicationpersonEmail = applicationpersonEmail;
	}

	public Long getApplicationpersonPhone() {
		return this.applicationpersonPhone;
	}

	public void setApplicationpersonPhone(Long applicationpersonPhone) {
		this.applicationpersonPhone = applicationpersonPhone;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCostcentre() {
		return this.costcentre;
	}

	public void setCostcentre(String costcentre) {
		this.costcentre = costcentre;
	}

	public String getLangingnum() {
		return this.langingnum;
	}

	public void setLangingnum(String langingnum) {
		this.langingnum = langingnum;
	}

	public Long getService400Id() {
		return this.service400Id;
	}

	public void setService400Id(Long service400Id) {
		this.service400Id = service400Id;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFactopentime() {
		return this.factopentime;
	}

	public void setFactopentime(String factopentime) {
		this.factopentime = factopentime;
	}

	public String getUseinstructions() {
		return this.useinstructions;
	}

	public void setUseinstructions(String useinstructions) {
		this.useinstructions = useinstructions;
	}

	public String getDepartmentheaderEmail() {
		return this.departmentheaderEmail;
	}

	public void setDepartmentheaderEmail(String departmentheaderEmail) {
		this.departmentheaderEmail = departmentheaderEmail;
	}

	public String getCopypersonEmail() {
		return this.copypersonEmail;
	}

	public void setCopypersonEmail(String copypersonEmail) {
		this.copypersonEmail = copypersonEmail;
	}

	public Integer getIsivr() {
		return this.isivr;
	}

	public void setIsivr(Integer isivr) {
		this.isivr = isivr;
	}

	public String getChecktime() {
		return checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public String getClosetime() {
		return closetime;
	}

	public void setClosetime(String closetime) {
		this.closetime = closetime;
	}

	public String getNumcategory() {
		return numcategory;
	}

	public void setNumcategory(String numcategory) {
		this.numcategory = numcategory;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getLangingnum2() {
		return langingnum2;
	}

	public void setLangingnum2(String langingnum2) {
		this.langingnum2 = langingnum2;
	}

	public String getLangingnum3() {
		return langingnum3;
	}

	public void setLangingnum3(String langingnum3) {
		this.langingnum3 = langingnum3;
	}

	public String getLangingnum_a(){
		String langingnum = this.getLangingnum();
		if(langingnum==null){
			return "";
		}
		int length = langingnum.length();
		if(length==11){
			return this.getLangingnum().substring(0, 3);
		}else if(length==12){
			return this.getLangingnum().substring(0,4);
		}
		return this.getLangingnum();
	}
	public String getLangingnum_n(){
		String langingnum = this.getLangingnum();
		if(langingnum==null){
			return "";
		}
		int length = langingnum.length();
		if(length==11){
			return this.getLangingnum().substring(3);
		}else if(length==12){
			return this.getLangingnum().substring(4);
		}
		return this.getLangingnum();
	}
	public String getLangingnum2_a(){
		String langingnum2 = this.getLangingnum2();
		if(langingnum2==null){
			return "";
		}
		int length = langingnum2.length();
		if(length==11){
			return this.getLangingnum2().substring(0, 3);
		}else if(length==12){
			return this.getLangingnum2().substring(0,4);
		}
		return this.getLangingnum2();
	}
	public String getLangingnum2_n(){
		String langingnum2 = this.getLangingnum2();
		if(langingnum2==null){
			return "";
		}
		int length = langingnum2.length();
		if(length==11){
			return this.getLangingnum2().substring(3);
		}else if(length==12){
			return this.getLangingnum2().substring(4);
		}
		return this.getLangingnum2();
	}
	public String getLangingnum3_a(){
		String langingnum3 = this.getLangingnum3();
		if(langingnum3==null){
			return "";
		}
		int length = langingnum3.length();
		if(length==11){
			return this.getLangingnum3().substring(0, 3);
		}else if(length==12){
			return this.getLangingnum3().substring(0,4);
		}
		return this.getLangingnum3();
	}
	public String getLangingnum3_n(){
		String langingnum3 = this.getLangingnum3();
		if(langingnum3==null){
			return "";
		}
		int length = langingnum3.length();
		if(length==11){
			return this.getLangingnum3().substring(3);
		}else if(length==12){
			return this.getLangingnum3().substring(4);
		}
		return this.getLangingnum3();
	}
	
	public Object[] getFilepathArr() {
		String[] sp = filepath.split(";");
		List<String> list = new ArrayList<String>();
		for (String s : sp) {
			if (s == null || s.equals("")||"null".equals(s)) {
				continue;
			}
			int i = s.lastIndexOf(File.separator);
			String sub = s.substring(i+1);
			list.add(sub);
		}
		return list.toArray();
	}

	@Override
	public String toString() {
		return "TService400Applicationform [id=" + id + ", applicationperson="
				+ applicationperson + ", opentime=" + opentime
				+ ", applicationtime=" + applicationtime + ", phonenum="
				+ phonenum + ", phonenumPrice=" + phonenumPrice
				+ ", applicationpersonEmail=" + applicationpersonEmail
				+ ", applicationpersonPhone=" + applicationpersonPhone
				+ ", company=" + company + ", department=" + department
				+ ", costcentre=" + costcentre + ", langingnum=" + langingnum
				+ ", service400Id=" + service400Id + ", status=" + status
				+ ", factopentime=" + factopentime + ", useinstructions="
				+ useinstructions + ", departmentheaderEmail="
				+ departmentheaderEmail + ", copypersonEmail="
				+ copypersonEmail + ", isivr=" + isivr + ", checktime="
				+ checktime + ", closetime=" + closetime + ", numcategory="
				+ numcategory + ",filepath=" + filepath + "]";
	}
}