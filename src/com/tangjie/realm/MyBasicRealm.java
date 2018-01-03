package com.tangjie.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.tangjie.init.InitServlet;
import com.tangjie.model.Resource;
import com.tangjie.model.Role;
import com.tangjie.model.User;
import com.tangjie.service.RoleService;
import com.tangjie.service.UserService;

public class MyBasicRealm extends AuthorizingRealm{

//	@Override
//	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
//		// TODO Auto-generated method stub
//		Cache c=this.getAuthenticationCache();
//		Set<Object> keys=c.keys();
//		super.clearCachedAuthorizationInfo(principals);
//	}
//
//	@Override
//	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
//		// TODO Auto-generated method stub
//		Cache c=this.getAuthenticationCache();
//		Set<Object> keys=c.keys();
//		User user=(User)principals.getPrimaryPrincipal();
//		SimplePrincipalCollection spc=new SimplePrincipalCollection(user.getUsername(),this.getName());
//		super.clearCachedAuthenticationInfo(principals);
//	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user=(User)principals.getPrimaryPrincipal();
		int uid=user.getId();
		System.out.println(user.getId()+"--------"+user.getNickname());
		UserService userService=(UserService) InitServlet.getWc().getBean("userService");
		List<String> roles=userService.listSnByUser(uid);
		List<Resource> reses=userService.listAllResource(uid);
		List<String> permissions=new ArrayList<String>();
		for(Resource r:reses){
			permissions.add(r.getUrl());
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setRoles(new HashSet<String>(roles));
		info.setStringPermissions(new HashSet<String>(permissions));
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UserService userService=(UserService) InitServlet.getWc().getBean("userService");
		String username=token.getPrincipal().toString();
		String password=new String((char[])token.getCredentials());
		User user= userService.login(username, password);
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user, user.getPassword(),getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
		return info;
	}

}
