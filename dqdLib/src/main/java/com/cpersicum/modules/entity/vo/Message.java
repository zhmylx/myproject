package com.cpersicum.modules.entity.vo;

import java.io.Serializable;

public class Message implements Serializable {
	private boolean result;
	private String message;

	public Message(boolean result, String message) {
		this.result = result;
		this.message = message;
	}

	public boolean getResult() {
		return this.result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
