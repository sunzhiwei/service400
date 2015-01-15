package com.yixin.service400.bean;

/**
 * TService400Role entity. @author MyEclipse Persistence Tools
 */

public class TService400Role implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

	// Constructors

	/** default constructor */
	public TService400Role() {
	}

	/** minimal constructor */
	public TService400Role(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TService400Role(Long id, String name) {
		this.id = id;
		this.name = name;
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

}