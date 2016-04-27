package com.cpersicum.modules.mobiles.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "success", "errorCode", "message" })
public class Head {
	private boolean success;
	private String errorCode;
	private String message;

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@XmlElement(name = "errorCode")
	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String resutlCode) {
		this.errorCode = resutlCode;
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
