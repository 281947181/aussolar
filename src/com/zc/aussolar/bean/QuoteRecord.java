package com.zc.aussolar.bean;

import java.math.BigDecimal;
import java.util.Date;

public class QuoteRecord {
	private Integer vid;
	private String project_id;
	private String quote_person;
	private Date quote_date;
	private String panel_brand;
	private BigDecimal panel_size;
	private Integer panel_number;
	private String inverter_brand;
	private String inverter_size;
	private BigDecimal system_size;
	private BigDecimal price;
	private BigDecimal deposit;
	private String payment;
	private String install_comment;
	private String status;
	private Date confirm_date;
	private String confirm_person;
	private String ABN;
	private String business_type;
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
	public String getQuote_person() {
		return quote_person;
	}
	public void setQuote_person(String quote_person) {
		this.quote_person = quote_person;
	}
	public Date getQuote_date() {
		return quote_date;
	}
	public void setQuote_date(Date quote_date) {
		this.quote_date = quote_date;
	}
	public String getPanel_brand() {
		return panel_brand;
	}
	public void setPanel_brand(String panel_brand) {
		this.panel_brand = panel_brand;
	}
	public BigDecimal getPanel_size() {
		return panel_size;
	}
	public void setPanel_size(BigDecimal panel_size) {
		this.panel_size = panel_size;
	}
	public Integer getPanel_number() {
		return panel_number;
	}
	public void setPanel_number(Integer panel_number) {
		this.panel_number = panel_number;
	}
	public String getInverter_brand() {
		return inverter_brand;
	}
	public void setInverter_brand(String inverter_brand) {
		this.inverter_brand = inverter_brand;
	}
	public String getInverter_size() {
		return inverter_size;
	}
	public void setInverter_size(String inverter_size) {
		this.inverter_size = inverter_size;
	}
	public BigDecimal getSystem_size() {
		return system_size;
	}
	public void setSystem_size(BigDecimal system_size) {
		this.system_size = system_size;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getInstall_comment() {
		return install_comment;
	}
	public void setInstall_comment(String install_comment) {
		this.install_comment = install_comment==null?null:install_comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public String getABN() {
		return ABN;
	}
	public void setABN(String aBN) {
		ABN = aBN;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
}
