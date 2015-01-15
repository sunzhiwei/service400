package com.yixin.service400.bean;

/**
 * TService400OverflowPhone entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TService400OverflowPhone implements java.io.Serializable {

	// Fields

	private Long overflowGroupId;
	private Long overflowId;
	private String phonenum;
	private Integer flag;

	// Constructors

	/** default constructor */
	public TService400OverflowPhone() {
	}

	/** minimal constructor */
	public TService400OverflowPhone(Long overflowGroupId) {
		this.overflowGroupId = overflowGroupId;
	}

	/** full constructor */
	public TService400OverflowPhone(Long overflowGroupId, Long overflowId,
			String phonenum, Integer flag) {
		this.overflowGroupId = overflowGroupId;
		this.overflowId = overflowId;
		this.phonenum = phonenum;
		this.flag = flag;
	}

	// Property accessors

	public Long getOverflowGroupId() {
		return this.overflowGroupId;
	}

	public void setOverflowGroupId(Long overflowGroupId) {
		this.overflowGroupId = overflowGroupId;
	}

	public Long getOverflowId() {
		return this.overflowId;
	}

	public void setOverflowId(Long overflowId) {
		this.overflowId = overflowId;
	}

	public String getPhonenum() {
		return this.phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}