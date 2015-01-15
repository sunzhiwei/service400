package com.yixin.service400.bean;


/**
 * TService400Notes entity. @author MyEclipse Persistence Tools
 */

public class TService400Notes implements java.io.Serializable {

	// Fields

	/**
	 */
	private static final long serialVersionUID = 5964875678569443590L;
	private Long id;
	private String opentime;
	private String inserttime;
	private String content;
	private String usermail;

	// Constructors

	/** default constructor */
	public TService400Notes() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpentime() {
		return this.opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getContent() {
		return this.content;
	}

	public String getContentStr() {
		return this.content.toString();
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsermail() {
		return this.usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

}