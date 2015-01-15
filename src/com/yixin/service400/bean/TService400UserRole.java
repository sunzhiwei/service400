package com.yixin.service400.bean;

/**
 * TService400UserRole entity. @author MyEclipse Persistence Tools
 */

public class TService400UserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userRoleId;
	private Long userid;
	private Long roleid;

	// Constructors

	/** default constructor */
	public TService400UserRole() {
	}

	/** minimal constructor */
	public TService400UserRole(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/** full constructor */
	public TService400UserRole(Long userRoleId, Long userid, Long roleid) {
		this.userRoleId = userRoleId;
		this.userid = userid;
		this.roleid = roleid;
	}

	// Property accessors

	public Long getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

}