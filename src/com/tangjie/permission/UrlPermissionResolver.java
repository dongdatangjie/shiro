package com.tangjie.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class UrlPermissionResolver implements PermissionResolver{

	@Override
	public Permission resolvePermission(String stringPermission) {
		// TODO Auto-generated method stub
		if(stringPermission.startsWith("/")){
			return new UrlPermission(stringPermission);
		}
		return new WildcardPermission(stringPermission);
	}

}
