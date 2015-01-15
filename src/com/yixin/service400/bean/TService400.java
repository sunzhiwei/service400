package com.yixin.service400.bean;

/**
 * TService400 entity. @author MyEclipse Persistence Tools
 */

public class TService400 implements java.io.Serializable {
	private static final long serialVersionUID = -6635496191419681853L;
	/**
	 * 未使用
	 */
	public static final Integer WSY = 0;
	/**
	 * 已使用
	 */
	public static final Integer YSY = 1;
	private Long id;
	private String phonenum;
	private Double price;
	private String operationCompany;
	private Integer status;

	private String YD = "YD";
	private String LT = "LT";
	private String DX = "DX";

	public String getOCString() {
		if (this.getOperationCompany().equals(YD)) {
			return "中国移动";
		}
		if (this.getOperationCompany().equals(LT)) {
			return "中国联通";
		}
		if (this.getOperationCompany().equals(DX)) {
			return "中国电信";
		}
		return this.getOperationCompany();
	}

	// Constructors

	/** default constructor */
	public TService400() {
	}

	/** minimal constructor */
	public TService400(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TService400(Long id, String phonenum, Double price,
			String operationCompany, Integer status) {
		this.id = id;
		this.phonenum = phonenum;
		this.price = price;
		this.operationCompany = operationCompany;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhonenum() {
		return this.phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOperationCompany() {
		return this.operationCompany;
	}

	public void setOperationCompany(String operationCompany) {
		this.operationCompany = operationCompany;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}