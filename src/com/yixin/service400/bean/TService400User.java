package com.yixin.service400.bean;

/**
 * TService400User entity. @author MyEclipse Persistence Tools
 */

public class TService400User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Long telphone;
	private String email;
	private String company;
	private String uuid;

	// Constructors

	/** default constructor */
	public TService400User() {
	}

	/** minimal constructor */
	public TService400User(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TService400User(Long id, String username, String password,
			Long telphone, String email, String company, String uuid) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.email = email;
		this.company = company;
		this.uuid = uuid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTelphone() {
		return this.telphone;
	}

	public void setTelphone(Long telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}