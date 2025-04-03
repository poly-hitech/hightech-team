package com.example.test.Model;

//회원 권한
public class UsersRole {
	public static final Long ADMIN = 99L;
	public static final Long PARTNER = 72L;
	public static final Long NORMAL = 1L;
	
	private Long roleId;
	private String roleName;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public static String getRoleName(int roleId) {
		if(roleId == UsersRole.ADMIN) return "admin";
		else if(roleId == UsersRole.PARTNER) return "partner";
		else if(roleId == UsersRole.NORMAL) return "normal";
		else
			return String.valueOf(roleId);
	}
}
