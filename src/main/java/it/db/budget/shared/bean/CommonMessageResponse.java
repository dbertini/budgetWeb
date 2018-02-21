package it.db.budget.shared.bean;

import java.io.Serializable;

public class CommonMessageResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3793573881203143709L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
