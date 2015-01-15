package com.yixin.service400.bean;

/**
 * TService400User400num entity. @author MyEclipse Persistence Tools
 */

public class TService400User400num implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long user400numId;
	private Long userid;
	private Long service400num;

	// Constructors

	/** default constructor */
	public TService400User400num() {
	}

	/** minimal constructor */
	public TService400User400num(Long user400numId) {
		this.user400numId = user400numId;
	}

	/** full constructor */
	public TService400User400num(Long user400numId, Long userid,
			Long service400num) {
		this.user400numId = user400numId;
		this.userid = userid;
		this.service400num = service400num;
	}

	// Property accessors

	public Long getUser400numId() {
		return this.user400numId;
	}

	public void setUser400numId(Long user400numId) {
		this.user400numId = user400numId;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getService400num() {
		return this.service400num;
	}

	public void setService400num(Long service400num) {
		this.service400num = service400num;
	}

}