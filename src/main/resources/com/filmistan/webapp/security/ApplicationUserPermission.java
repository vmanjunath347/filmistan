package com.filmistan.webapp.security;

public enum ApplicationUserPermission {
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	USER_UPDATE("user:update"),
	ADMIN_READ("user:read"),
	ADMIN_WRITE("user:write"),
	ADMIN_UPDATE("user:update");
	
	private final String permission;
	
	ApplicationUserPermission(String permission){
		this.permission = permission;
		
	}
	
	public String getPermission() {
		return permission;
	}
}
