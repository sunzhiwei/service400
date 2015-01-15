package com.yixin.service400.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * TService400Key entity. @author MyEclipse Persistence Tools
 */

public class TService400Key implements java.io.Serializable {
	private static final long serialVersionUID = -3146866117544851757L;

	public static final Integer STATUS_ADD = 4;
	public static final Integer STATUS_UPDATE = 5;
	public static final Integer STATUS_DELETE = 6;
	public static List<String> list = new ArrayList<String>();
	static{
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("#");
		list.add("*");
	}
	private Long id;
	private Long pid;
	private String name;
	private String content;
	private Integer status;
	private String path;
    private String username;
    private String mailtime;
    private String closelooptime;
    private Integer workgroup_flag;
    private String phonenum;
	// Constructors

	/** default constructor */
	public TService400Key() {
	}

	/** minimal constructor */
	public TService400Key(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TService400Key(Long id, Long pid, String name, String content,
			Integer status, String path) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.content = content;
		this.status = status;
		this.path = path;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getWorkgroup_flag() {
		return workgroup_flag;
	}

	public void setWorkgroup_flag(Integer workgroupFlag) {
		workgroup_flag = workgroupFlag;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}