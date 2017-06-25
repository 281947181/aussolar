package com.zc.aussolar.bean;

import java.util.Date;

public class AdditionalComment {
	private Integer vid;
	private String project_id;
	private Date comment_date;
	private String comment_person;
	private String comment;
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
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_person() {
		return comment_person;
	}
	public void setComment_person(String comment_person) {
		this.comment_person = comment_person;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment==null?null:comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
}
