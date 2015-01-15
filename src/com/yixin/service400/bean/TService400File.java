package com.yixin.service400.bean;

import java.io.File;

import com.yixin.service400.util.Conf;
import com.yixin.service400.util.LocalIPUtil;

/**
 * TService400File entity. @author MyEclipse Persistence Tools
 */

public class TService400File implements java.io.Serializable {

	private static final long serialVersionUID = 5417656508609154268L;
	/**
	 * 闭环状态
	 */
	public static final Integer STATUS_CLOSELOOP = 10;
	/**
	 * 400号码申请
	 */
	public static final String STATUS_TYPE_SHENQING = "A";
	/**
	 * IVR申请
	 */
	public static final String STATUS_TYPE_IVR = "B";
	/**
	 * 工作组申请
	 */
	public static final String STATUS_TYPE_GROUP = "C";
	private Long id;
	private String sendperson;
	private String sendtime;
	private String filepath;
	private Integer status;
	private String evaluation;
	private String closelooptime;
	private String phonenum;
	private String type;

	// Constructors

	/** default constructor */
	public TService400File() {
	}

	/** minimal constructor */
	public TService400File(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TService400File(Long id, String sendperson, String sendtime,
			String filepath) {
		this.id = id;
		this.sendperson = sendperson;
		this.sendtime = sendtime;
		this.filepath = filepath;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSendperson() {
		return sendperson;
	}

	public void setSendperson(String sendperson) {
		this.sendperson = sendperson;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getCloselooptime() {
		return closelooptime;
	}

	public void setCloselooptime(String closelooptime) {
		this.closelooptime = closelooptime;
	}

	public String getFilepathURL() throws Exception {
		String path = this.getFilepath();
//		String value = Conf.getValue("download.file");
		String value = "http://"+LocalIPUtil.getLocalIP()+":8080"+Conf.getValue("download.file");;
		return value + path;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public String getTypeString() {
		if (STATUS_TYPE_SHENQING.equals(type)) {
			return "400号码";
		} else if (STATUS_TYPE_IVR.equals(type)) {
			return "IVR维护";
		} else if (STATUS_TYPE_GROUP.equals(type)) {
			return "工作组维护";
		}
		return type;
	}

	public String getFileName() {
		int i = this.getFilepath().lastIndexOf(File.separator);
		return this.getFilepath().substring(i+1);
	}
}