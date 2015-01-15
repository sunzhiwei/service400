package com.yixin.service400.bean;

/**
 * TService400OverflowGroup entity. @author MyEclipse Persistence Tools
 */

public class TService400OverflowGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long overflowGroupId;
	private Long overflowId;
	private Long workgroupId;
	private Integer flag;

	// Constructors

	/** default constructor */
	public TService400OverflowGroup() {
	}

	/** minimal constructor */
	public TService400OverflowGroup(Long overflowGroupId) {
		this.overflowGroupId = overflowGroupId;
	}

	/** full constructor */
	public TService400OverflowGroup(Long overflowGroupId, Long overflowId,
			Long workgroupId, Integer flag) {
		this.overflowGroupId = overflowGroupId;
		this.overflowId = overflowId;
		this.workgroupId = workgroupId;
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

	public Long getWorkgroupId() {
		return this.workgroupId;
	}

	public void setWorkgroupId(Long workgroupId) {
		this.workgroupId = workgroupId;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}