package com.cpersicum.modules.orm;

import java.io.Serializable;

public class QueryCondition implements Serializable {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	protected int pageNo = 1;
	protected int pageSize = 10;

	protected String orderBy = null;
	protected String order = "asc";
	private String currnetUserLoginName;
	private String currnetUserDeptNo;

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCurrnetUserLoginName() {
		return this.currnetUserLoginName;
	}

	public void setCurrnetUserLoginName(String currnetUserLoginName) {
		this.currnetUserLoginName = currnetUserLoginName;
	}

	public String getCurrnetUserDeptNo() {
		return this.currnetUserDeptNo;
	}

	public void setCurrnetUserDeptNo(String currnetUserDeptNo) {
		this.currnetUserDeptNo = currnetUserDeptNo;
	}
}

