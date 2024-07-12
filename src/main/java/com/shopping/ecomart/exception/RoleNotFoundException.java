package com.shopping.ecomart.exception;

public class RoleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String roleName;

	public RoleNotFoundException() {
	}

	public RoleNotFoundException(String roleName) {
		super(String.format("No %s Role found", roleName));
		this.roleName = roleName;
	}

}
