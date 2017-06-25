package com.zc.aussolar.bean;

public class ContractConfirmInfo {
	private Integer vid;
	private String project_id;
	private Integer quote_record_id;
	private String quote_info_confirm;
	private String contract_info_confirm;
	private String fee_info_confirm;
	private String phase_info_confirm;
	private String nearmap_info_confirm;
	private String comment_info_confirm;
	private String deposit_confirm;
	private String dd_info_confirm;
	private String certegy_confirm;
	private String asm_confirm;
	private String other_finance_confirm;
	private String status;
	private String admin_comment;
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
	public Integer getQuote_record_id() {
		return quote_record_id;
	}
	public void setQuote_record_id(Integer quote_record_id) {
		this.quote_record_id = quote_record_id;
	}
	public String getContract_info_confirm() {
		return contract_info_confirm;
	}
	public void setContract_info_confirm(String contract_info_confirm) {
		this.contract_info_confirm = contract_info_confirm;
	}
	public String getFee_info_confirm() {
		return fee_info_confirm;
	}
	public void setFee_info_confirm(String fee_info_confirm) {
		this.fee_info_confirm = fee_info_confirm;
	}
	public String getPhase_info_confirm() {
		return phase_info_confirm;
	}
	public void setPhase_info_confirm(String phase_info_confirm) {
		this.phase_info_confirm = phase_info_confirm;
	}
	public String getNearmap_info_confirm() {
		return nearmap_info_confirm;
	}
	public void setNearmap_info_confirm(String nearmap_info_confirm) {
		this.nearmap_info_confirm = nearmap_info_confirm;
	}
	public String getComment_info_confirm() {
		return comment_info_confirm;
	}
	public void setComment_info_confirm(String comment_info_confirm) {
		this.comment_info_confirm = comment_info_confirm;
	}
	public String getDd_info_confirm() {
		return dd_info_confirm;
	}
	public void setDd_info_confirm(String dd_info_confirm) {
		this.dd_info_confirm = dd_info_confirm;
	}
	public String getCertegy_confirm() {
		return certegy_confirm;
	}
	public void setCertegy_confirm(String certegy_confirm) {
		this.certegy_confirm = certegy_confirm;
	}
	public String getAsm_confirm() {
		return asm_confirm;
	}
	public void setAsm_confirm(String asm_confirm) {
		this.asm_confirm = asm_confirm;
	}
	public String getOther_finance_confirm() {
		return other_finance_confirm;
	}
	public void setOther_finance_confirm(String other_finance_confirm) {
		this.other_finance_confirm = other_finance_confirm;
	}
	public String getQuote_info_confirm() {
		return quote_info_confirm;
	}
	public void setQuote_info_confirm(String quote_info_confirm) {
		this.quote_info_confirm = quote_info_confirm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeposit_confirm() {
		return deposit_confirm;
	}
	public void setDeposit_confirm(String deposit_confirm) {
		this.deposit_confirm = deposit_confirm;
	}
	public String getAdmin_comment() {
		return admin_comment;
	}
	public void setAdmin_comment(String admin_comment) {
		this.admin_comment = admin_comment==null?null:admin_comment.replace("\r", " ").replace("\n", " ").replace("\"", " ").replace("\'", " ");
	}
}
