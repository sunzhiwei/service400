package com.yixin.service400.bean;

/**
 * TService400RolePrivilege entity. @author MyEclipse Persistence Tools
 */

public class TService400RolePrivilege implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rolePrivilegeId;
	private Long roleid;
	private Long privilegeid;

	// Constructors

	/** default constructor */
	public TService400RolePrivilege() {
	}

	/** minimal constructor */
	public TService400RolePrivilege(Long rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}

	/** full constructor */
	public TService400RolePrivilege(Long rolePrivilegeId, Long roleid,
			Long privilegeid) {
		this.rolePrivilegeId = rolePrivilegeId;
		this.roleid = roleid;
		this.privilegeid = privilegeid;
	}

	// Property accessors

	public Long getRolePrivilegeId() {
		return this.rolePrivilegeId;
	}

	public void setRolePrivilegeId(Long rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getPrivilegeid() {
		return this.privilegeid;
	}

	public void setPrivilegeid(Long privilegeid) {
		this.privilegeid = privilegeid;
	}

}