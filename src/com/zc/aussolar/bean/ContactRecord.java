package com.zc.aussolar.bean;

import java.util.Date;

public class ContactRecord {
	private Integer vid;
	private String project_id;
	private Date contact_date;
	private String contact_person;
	private String contact_comment;
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public Date getContact_date() {
		return contact_date;
	}
	public void setContact_date(Date contact_date) {
		this.contact_date = contact_date;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getContact_comment() {
		return contact_comment;
	}
	public void setContact_comment(String contact_comment) {
		this.contact_comment = contact_comment==null?null:contact_comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
}
