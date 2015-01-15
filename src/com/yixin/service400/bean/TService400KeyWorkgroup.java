package com.yixin.service400.bean;

/**
 * TService400KeyWorkgroup entity. @author MyEclipse Persistence Tools
 */

public class TService400KeyWorkgroup implements java.io.Serializable {

	private static final long serialVersionUID = 7182902828767131994L;
	public static final int STATUS_ADD = 4;
	public static final int STATUS_DELETE = 6;
	public static final int STATUS_SENDMAIL = 9;
	public static final int STATUS_CLOSELOOP = 10;
	public static final int STATUS_VOID = 11;
	private Long keyWorkgroupId;
	private Long keyId;
	private Long workgroupId;
	private Integer ivr_workgroup_status;
	private String path;
	private String username;
	private String mailtime;

	// Constructors

	/** default constructor */
	public TService400KeyWorkgroup() {
	}

	/** minimal constructor */
	public TService400KeyWorkgroup(Long keyWorkgroupId) {
		this.keyWorkgroupId = keyWorkgroupId;
	}

	/** full constructor */
	public TService400KeyWorkgroup(Long keyWorkgroupId, Long keyId,
			Long workgroupId,Integer ivr_workgroup_status,String path) {
		this.keyWorkgroupId = keyWorkgroupId;
		this.keyId = keyId;
		this.workgroupId = workgroupId;
		this.ivr_workgroup_status=ivr_workgroup_status;
		this.path=path;
	}

	// Property accessors

	public Long getKeyWorkgroupId() {
		return this.keyWorkgroupId;
	}

	public void setKeyWorkgroupId(Long keyWorkgroupId) {
		this.keyWorkgroupId = keyWorkgroupId;
	}

	public Long getKeyId() {
		return this.keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public Long getWorkgroupId() {
		return this.workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public Integer getIvr_workgroup_status() {
		return ivr_workgroup_status;
	}

	public void setIvr_workgroup_status(Integer ivrWorkgroupStatus) {
		ivr_workgroup_status = ivrWorkgroupStatus;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailtime() {
		return mailtime;
	}

	public void setMailtime(String mailtime) {
		this.mailtime = mailtime;
	}

}