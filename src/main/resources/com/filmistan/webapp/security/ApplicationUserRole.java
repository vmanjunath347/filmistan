package com.filmistan.webapp.security;

import com.google.common.collect.Sets;

import java.util.Set;

import com.filmistan.webapp.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
	
	USER(Sets.newHashSet(ApplicationUserPermission.USER_READ,ApplicationUserPermission.USER_WRITE,ApplicationUserPermission.USER_UPDATE)),
	ADMIN(Sets.newHashSet(ApplicationUserPermission.USER_READ,ApplicationUserPermission.USER_WRITE,ApplicationUserPermission.USER_UPDATE));
	
	private final Set<ApplicationUserPermission> permission;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permission) {
		this.permission = permission;
	}
	
	public Set<ApplicationUserPermission> getPermission(){
		return permission;
	}
}
