package com.example.test.Model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

//회원정보
public class Users {
	private Long userId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	private String phone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date signupDay = new Date();
	
	private String gender;
	private String profileImage;
	private String nickname;
	private Long agreeStatus = 1L;
	private String username;
	private String password;
	private Long roleId;
	private String name;
	private List<RegexDetail> regexDetail;
	private List<NewRegex> newRegex;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getSignupDay() {
		return signupDay;
	}
	public void setSignupDay(Date signupDay) {
		this.signupDay = signupDay;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getAgreeStatus() {
		return agreeStatus;
	}
	public void setAgreeStatus(Long agreeStatus) {
		this.agreeStatus = agreeStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<RegexDetail> getRegexDetail() {
		return regexDetail;
	}
	public void setRegexDetail(List<RegexDetail> regexDetail) {
		this.regexDetail = regexDetail;
	}
	public List<NewRegex> getNewRegex() {
		return newRegex;
	}
	public void setNewRegex(List<NewRegex> newRegex) {
		this.newRegex = newRegex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
