package com.yixin.service400.bean;

/**
 * TService400Workgroup entity. @author MyEclipse Persistence Tools
 */

public class TService400Workgroup implements java.io.Serializable {
	private static final long serialVersionUID = -4463999090970248157L;

	// Fields
	private Integer ivrStatus;
	private String path;

	private Long id;
	private String name;
	private String starttime;
	private String endtime;
	private Long adapternum;
	private String noworkvoice;
	private Integer noworkisoverflow;
	private Integer status;
	private String workoverflowGroup;
	private String noworkoverflowGroup;
	private String username;
	private String mailtime;
	private String closelooptime;
	private String busyTone;
	private Integer workisoverflow;
	private String startweek;
	private String endweek;

	// Constructors

	/** default constructor */
	public TService400Workgroup() {
	}

	/** minimal constructor */
	public TService400Workgroup(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TService400Workgroup(Long id, String name, String starttime,
			String endtime, Long adapternum, String noworkvoice,
			Integer noworkisoverflow, Integer status, String workoverflowGroup,
			String noworkoverflowGroup, String username) {
		this.id = id;
		this.name = name;
		this.starttime = starttime;
		this.endtime = endtime;
		this.adapternum = adapternum;
		this.noworkvoice = noworkvoice;
		this.noworkisoverflow = noworkisoverflow;
		this.status = status;
		this.workoverflowGroup = workoverflowGroup;
		this.noworkoverflowGroup = noworkoverflowGroup;
		this.username = username;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Long getAdapternum() {
		return this.adapternum;
	}

	public void setAdapternum(Long adapternum) {
		this.adapternum = adapternum;
	}

	public String getNoworkvoice() {
		return this.noworkvoice;
	}

	public void setNoworkvoice(String noworkvoice) {
		this.noworkvoice = noworkvoice;
	}

	public Integer getNoworkisoverflow() {
		return this.noworkisoverflow;
	}

	public void setNoworkisoverflow(Integer noworkisoverflow) {
		this.noworkisoverflow = noworkisoverflow;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWorkoverflowGroup() {
		return this.workoverflowGroup;
	}

	public void setWorkoverflowGroup(String workoverflowGroup) {
		this.workoverflowGroup = workoverflowGroup;
	}

	public String getNoworkoverflowGroup() {
		return this.noworkoverflowGroup;
	}

	public void setNoworkoverflowGroup(String noworkoverflowGroup) {
		this.noworkoverflowGroup = noworkoverflowGroup;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getIvrStatus() {
		return ivrStatus;
	}

	public void setIvrStatus(Integer ivrStatus) {
		this.ivrStatus = ivrStatus;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMailtime() {
		return mailtime;
	}

	public void setMailtime(String mailtime) {
		this.mailtime = mailtime;
	}

	public String getCloselooptime() {
		return closelooptime;
	}

	public void setCloselooptime(String closelooptime) {
		this.closelooptime = closelooptime;
	}

	public String getBusyTone() {
		return busyTone;
	}

	public void setBusyTone(String busyTone) {
		this.busyTone = busyTone;
	}

	public Integer getWorkisoverflow() {
		return workisoverflow;
	}

	public void setWorkisoverflow(Integer workisoverflow) {
		this.workisoverflow = workisoverflow;
	}

	public String getStartweek() {
		return startweek;
	}

	public void setStartweek(String startweek) {
		this.startweek = startweek;
	}

	public String getEndweek() {
		return endweek;
	}

	public void setEndweek(String endweek) {
		this.endweek = endweek;
	}
	
}