package com.zc.aussolar.bean;

import java.util.Date;

public class LeadsModifyRecord {
	private Integer vid;
	private String project_id;//yyyyddmm#####
	private String status;
	//leads
	private Date create_date;
	private String create_person;
	private String leads_type;
	private String leads_comment;
	private String leads_from;
	private String leads_knocker;
	private String customer_name;
	private String customer_phone;
	private String customer_address;
	private String customer_address_lat;
	private String customer_address_lng;
	private String customer_subburb;
	private String customer_postcode;
	private String customer_state;
	private String customer_email;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public String getLeads_type() {
		return leads_type;
	}
	public void setLeads_type(String leads_type) {
		this.leads_type = leads_type;
	}
	public String getLeads_comment() {
		return leads_comment;
	}
	public void setLeads_comment(String leads_comment) {
		this.leads_comment = leads_comment==null?null:leads_comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
	public String getLeads_from() {
		return leads_from;
	}
	public void setLeads_from(String leads_from) {
		this.leads_from = leads_from;
	}
	public String getLeads_knocker() {
		return leads_knocker;
	}
	public void setLeads_knocker(String leads_knocker) {
		this.leads_knocker = leads_knocker;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_subburb() {
		return customer_subburb;
	}
	public void setCustomer_subburb(String customer_subburb) {
		this.customer_subburb = customer_subburb;
	}
	public String getCustomer_postcode() {
		return customer_postcode;
	}
	public void setCustomer_postcode(String customer_postcode) {
		this.customer_postcode = customer_postcode;
	}
	public String getCustomer_state() {
		return customer_state;
	}
	public void setCustomer_state(String customer_state) {
		this.customer_state = customer_state;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_address_lat() {
		return customer_address_lat;
	}
	public void setCustomer_address_lat(String customer_address_lat) {
		this.customer_address_lat = customer_address_lat;
	}
	public String getCustomer_address_lng() {
		return customer_address_lng;
	}
	public void setCustomer_address_lng(String customer_address_lng) {
		this.customer_address_lng = customer_address_lng;
	}
}
