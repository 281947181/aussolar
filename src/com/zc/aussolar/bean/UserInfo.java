package com.zc.aussolar.bean;

public class UserInfo {
	private Integer vid;
	private String username;
	private String salt;
	private String password;
	private String role;
	private String real_name;
	public StringBuffer toJsonString(){
		StringBuffer stringBuffer = new StringBuffer("{")
				.append("\"vid\":").append(vid).append(",")
				.append("\"username\":\"").append(username).append("\",")
				.append("\"real_name\":\"").append(real_name).append("\",")
				.append("\"role\":\"").append(role).append("\"")
				.append("}");
		return stringBuffer;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
