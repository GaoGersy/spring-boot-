package com.piesat.project.common.utils;

import com.piesat.project.entity.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	public static User getUser() {
		return (User)getSubjct().getPrincipal();
	}
	public static String getUserId() {
		return getUser().getUserId();
	}
	public static void logout() {
		getSubjct().logout();
	}
}
