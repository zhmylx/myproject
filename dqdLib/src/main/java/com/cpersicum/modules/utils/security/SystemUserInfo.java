package com.cpersicum.modules.utils.security;

import com.cpersicum.modules.entity.vo.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SystemUserInfo {
	public static String getloingName() {
		ShiroUser shiroUser = getCurrnetUser();
		return shiroUser.getLoginName();
	}

	public static ShiroUser getCurrnetUser() {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		return shiroUser;
	}
}
