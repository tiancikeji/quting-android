package com.example.quting.entity.guest;

import java.io.Serializable;

public class UserRegisterEntity implements Serializable{
	/**
	 * {"guest":{"created_at":"2013-06-06T11:01:14Z",
		"device_token":"1111","id":2,
		"updated_at":"2013-06-06T11:01:14Z"}}
	 */
	private static final long serialVersionUID = 1L;

	private User guest;

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}
	
}
