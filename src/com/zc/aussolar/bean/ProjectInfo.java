package com.zc.aussolar.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectInfo {
	private Integer vid;
	private String project_id;//yyyyddmm#####
	//status
	private String wp_status;
	private Date wp_submit_date;
	private Date wp_approved_date;
	private String wp_comment;
	private String stc_status;	
	private Date stc_date;
	private Date money_date;
	private String money_status;
	private Date issue_date;
	private String issue_status;
	private String project_status;//project_status options: canceled/to asign/to contact/
	//														to quote/to confirm/to visit/to sign/
	//														to check/to install/to finish/complete
	
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
	private String customer_roof_type;
	private String customer_storey;
	private String customer_phase_type;
	
	//asign
	private String asign_to;
	private Date asign_date;
	
	//contact
	private Date contact_date;
	private String contact_person;
	private String contact_comment;
	
	//quote
	private Date quoted_date;
	private String quoted_person;
	
	//quote confirm
	private Date confirm_date;
	private String confirm_person;
	
	//visit
	private Date visit_date;
	private String visit_person;
	
	//contract
	private Date contract_date;
	private String contract_person;
	
	//install
	private Date install_date;
	private Date to_install_date;
	private Date install_arrange_date;
	private String install_person;
	private String install_arrange_person;
	private String install_comment;
	
	//finish
	private Date finish_date;
	private String finish_person;
	private String finish_remark;
	
	private String commission_status;
	private BigDecimal commission_value;
	
	public StringBuffer toJsonStringNotClosed(){
		StringBuffer stringBuffer = new StringBuffer("")
				.append("\"vid\":").append(vid).append(",")
				.append("\"project_id\":\"").append(project_id).append("\",")
				.append("\"stc_status\":\"").append(stc_status).append("\",")
				.append("\"money_status\":\"").append(money_status).append("\",")
				.append("\"issue_status\":\"").append(issue_status).append("\",")
				.append("\"stc_date\":\"").append(stc_date).append("\",")
				.append("\"money_date\":\"").append(money_date).append("\",")
				.append("\"issue_date\":\"").append(issue_date).append("\",")
				.append("\"project_status\":\"").append(project_status).append("\",")
				.append("\"create_date\":\"").append(create_date).append("\",")
				.append("\"create_person\":\"").append(create_person).append("\",")
				.append("\"leads_type\":\"").append(leads_type).append("\",")
				.append("\"leads_comment\":\"").append(leads_comment).append("\",")
				.append("\"leads_from\":\"").append(leads_from).append("\",")
				.append("\"leads_knocker\":\"").append(leads_knocker).append("\",")
				.append("\"customer_name\":\"").append(customer_name).append("\",")
				.append("\"customer_phone\":\"").append(customer_phone).append("\",")
				.append("\"customer_address\":\"").append(customer_address).append("\",")
				.append("\"customer_address_lat\":\"").append(customer_address_lat).append("\",")
				.append("\"customer_address_lng\":\"").append(customer_address_lng).append("\",")
				.append("\"customer_subburb\":\"").append(customer_subburb).append("\",")
				.append("\"customer_postcode\":\"").append(customer_postcode).append("\",")
				.append("\"customer_state\":\"").append(customer_state).append("\",")
				.append("\"customer_email\":\"").append(customer_email).append("\",")
				.append("\"customer_roof_type\":\"").append(customer_roof_type).append("\",")
				.append("\"customer_storey\":\"").append(customer_storey).append("\",")
				.append("\"customer_phase_type\":\"").append(customer_phase_type).append("\",")
				.append("\"asign_to\":\"").append(asign_to).append("\",")
				.append("\"asign_date\":\"").append(asign_date).append("\",")
				.append("\"contact_date\":\"").append(contact_date).append("\",")
				.append("\"contact_person\":\"").append(contact_person).append("\",")
				.append("\"contact_comment\":\"").append(contact_comment).append("\",")
				.append("\"quoted_date\":\"").append(quoted_date).append("\",")
				.append("\"quoted_person\":\"").append(quoted_person).append("\",")
				.append("\"confirm_date\":\"").append(confirm_date).append("\",")
				.append("\"confirm_person\":\"").append(confirm_person).append("\",")
				.append("\"visit_date\":\"").append(visit_date).append("\",")
				.append("\"visit_person\":\"").append(visit_person).append("\",")
				.append("\"contract_date\":\"").append(contract_date).append("\",")
				.append("\"contract_person\":\"").append(contract_person).append("\",")
				.append("\"install_date\":\"").append(install_date).append("\",")
				.append("\"install_person\":\"").append(install_person).append("\",")
				.append("\"to_install_date\":\"").append(to_install_date).append("\",")
				.append("\"install_comment\":\"").append(install_comment).append("\",")
				.append("\"install_arrange_person\":\"").append(install_arrange_person).append("\",")
				.append("\"install_arrange_date\":\"").append(install_arrange_date).append("\",")
				.append("\"finish_date\":\"").append(finish_date).append("\",")
				.append("\"finish_person\":\"").append(finish_person).append("\",")
				.append("\"wp_status\":\"").append(wp_status).append("\",")
				.append("\"wp_submit_date\":\"").append(wp_submit_date).append("\",")
				.append("\"wp_approved_date\":\"").append(wp_approved_date).append("\",")
				.append("\"wp_comment\":\"").append(wp_comment).append("\",")
				.append("\"commission_status\":\"").append(commission_status).append("\",")
				.append("\"commission_value\":\"").append(commission_value.toString()).append("\",")
				.append("\"finish_remark\":\"").append(finish_remark).append("\"");
		return stringBuffer;
	}
	public StringBuffer toJsonString(){
		return new StringBuffer("{").append(toJsonStringNotClosed()).append("}");
	}
	
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
	public String getStc_status() {
		return stc_status;
	}
	public void setStc_status(String stc_status) {
		this.stc_status = stc_status;
	}
	public String getMoney_status() {
		return money_status;
	}
	public void setMoney_status(String money_status) {
		this.money_status = money_status;
	}
	public String getProject_status() {
		return project_status;
	}
	public void setProject_status(String project_status) {
		this.project_status = project_status;
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
	public String getAsign_to() {
		return asign_to;
	}
	public void setAsign_to(String asign_to) {
		this.asign_to = asign_to;
	}
	public Date getAsign_date() {
		return asign_date;
	}
	public void setAsign_date(Date asign_date) {
		this.asign_date = asign_date;
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
	public Date getQuoted_date() {
		return quoted_date;
	}
	public void setQuoted_date(Date quoted_date) {
		this.quoted_date = quoted_date;
	}
	public String getQuoted_person() {
		return quoted_person;
	}
	public void setQuoted_person(String quoted_person) {
		this.quoted_person = quoted_person;
	}
	public Date getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}
	public String getConfirm_person() {
		return confirm_person;
	}
	public void setConfirm_person(String confirm_person) {
		this.confirm_person = confirm_person;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public String getVisit_person() {
		return visit_person;
	}
	public void setVisit_person(String visit_person) {
		this.visit_person = visit_person;
	}
	public Date getContract_date() {
		return contract_date;
	}
	public void setContract_date(Date contract_date) {
		this.contract_date = contract_date;
	}
	public String getContract_person() {
		return contract_person;
	}
	public void setContract_person(String contract_person) {
		this.contract_person = contract_person;
	}
	public Date getInstall_date() {
		return install_date;
	}
	public void setInstall_date(Date install_date) {
		this.install_date = install_date;
	}
	public String getInstall_person() {
		return install_person;
	}
	public void setInstall_person(String install_person) {
		this.install_person = install_person;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	public String getFinish_person() {
		return finish_person;
	}
	public void setFinish_person(String finish_person) {
		this.finish_person = finish_person;
	}
	public String getFinish_remark() {
		return finish_remark;
	}
	public void setFinish_remark(String finish_remark) {
		this.finish_remark = finish_remark==null?null:finish_remark.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
	public String getCustomer_roof_type() {
		return customer_roof_type;
	}
	public void setCustomer_roof_type(String customer_roof_type) {
		this.customer_roof_type = customer_roof_type;
	}
	public String getCustomer_storey() {
		return customer_storey;
	}
	public void setCustomer_storey(String customer_storey) {
		this.customer_storey = customer_storey;
	}
	public String getCustomer_phase_type() {
		return customer_phase_type;
	}
	public void setCustomer_phase_type(String customer_phase_type) {
		this.customer_phase_type = customer_phase_type;
	}
	public String getWp_status() {
		return wp_status;
	}
	public void setWp_status(String wp_status) {
		this.wp_status = wp_status;
	}
	public Date getWp_submit_date() {
		return wp_submit_date;
	}
	public void setWp_submit_date(Date wp_submit_date) {
		this.wp_submit_date = wp_submit_date;
	}
	public Date getWp_approved_date() {
		return wp_approved_date;
	}
	public void setWp_approved_date(Date wp_approved_date) {
		this.wp_approved_date = wp_approved_date;
	}
	public String getWp_comment() {
		return wp_comment;
	}
	public void setWp_comment(String wp_comment) {
		this.wp_comment = wp_comment==null?null:wp_comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
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
	public Date getTo_install_date() {
		return to_install_date;
	}
	public void setTo_install_date(Date to_install_date) {
		this.to_install_date = to_install_date;
	}
	public Date getInstall_arrange_date() {
		return install_arrange_date;
	}
	public void setInstall_arrange_date(Date install_arrange_date) {
		this.install_arrange_date = install_arrange_date;
	}
	public String getInstall_arrange_person() {
		return install_arrange_person;
	}
	public void setInstall_arrange_person(String install_arrange_person) {
		this.install_arrange_person = install_arrange_person;
	}
	public String getInstall_comment() {
		return install_comment;
	}
	public void setInstall_comment(String install_comment) {
		this.install_comment = install_comment==null?null:install_comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
	public String getCommission_status() {
		return commission_status;
	}
	public void setCommission_status(String commission_status) {
		this.commission_status = commission_status;
	}
	public BigDecimal getCommission_value() {
		return commission_value;
	}
	public void setCommission_value(BigDecimal commission_value) {
		this.commission_value = commission_value;
	}
	public Date getStc_date() {
		return stc_date;
	}
	public void setStc_date(Date stc_date) {
		this.stc_date = stc_date;
	}
	public Date getMoney_date() {
		return money_date;
	}
	public void setMoney_date(Date money_date) {
		this.money_date = money_date;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	public String getIssue_status() {
		return issue_status;
	}
	public void setIssue_status(String issue_status) {
		this.issue_status = issue_status;
	}
}
