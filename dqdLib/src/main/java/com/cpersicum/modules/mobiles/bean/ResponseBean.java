package com.cpersicum.modules.mobiles.bean;

public class ResponseBean {
	private Head head;

	public Head getHead() {
		return this.head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setHeadData(boolean success, String errorCode, String message) {
		this.head = new Head();
		this.head.setSuccess(success);
		this.head.setErrorCode(errorCode);
		this.head.setMessage(message);
	}
}
