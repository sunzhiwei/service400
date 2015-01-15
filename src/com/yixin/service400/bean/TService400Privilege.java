package com.yixin.service400.bean;

import java.sql.Timestamp;

/**
 * TService400Privilege entity. @author MyEclipse Persistence Tools
 */

public class TService400Privilege implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long leaf;
	private String name;
	private String target;
	private String title;
	private String url;
	private Long pid;
	private Long subsequence;
	private Timestamp createtime;
	private Long levelid;

	// Constructors

	/** default constructor */
	public TService400Privilege() {
	}

	/** minimal constructor */
	public TService400Privilege(Long id, Long leaf) {
		this.id = id;
		this.leaf = leaf;
	}

	/** full constructor */
	public TService400Privilege(Long id, Long leaf, String name, String target,
			String title, String url, Long pid, Long subsequence,
			Timestamp createtime, Long levelid) {
		this.id = id;
		this.leaf = leaf;
		this.name = name;
		this.target = target;
		this.title = title;
		this.url = url;
		this.pid = pid;
		this.subsequence = subsequence;
		this.createtime = createtime;
		this.levelid = levelid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Long leaf) {
		this.leaf = leaf;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getSubsequence() {
		return this.subsequence;
	}

	public void setSubsequence(Long subsequence) {
		this.subsequence = subsequence;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Long getLevelid() {
		return this.levelid;
	}

	public void setLevelid(Long levelid) {
		this.levelid = levelid;
	}

}