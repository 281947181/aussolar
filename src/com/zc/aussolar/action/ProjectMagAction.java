package com.zc.aussolar.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zc.aussolar.bean.AdditionalComment;
import com.zc.aussolar.bean.ContactRecord;
import com.zc.aussolar.bean.ContractConfirmInfo;
import com.zc.aussolar.bean.LeadsModifyRecord;
import com.zc.aussolar.bean.PostcodeInfo;
import com.zc.aussolar.bean.ProjectInfo;
import com.zc.aussolar.bean.ProjectStatusInfo;
import com.zc.aussolar.bean.QuoteRecord;
import com.zc.aussolar.bean.UserInfo;
import com.zc.aussolar.dao.BaseDao;
import com.zc.aussolar.util.FileDeleteUtil;
import com.zc.aussolar.util.JsonUtils;
import com.zc.aussolar.util.OfficeFileGenerateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ProjectMagAction extends ActionSupport {
	private static final long serialVersionUID = 626906445802810940L;
	private BaseDao baseDao;
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public void queryProjectInfo(){
		ActionContext actionContext = ActionContext.getContext();
		String[] page = (String[])actionContext.getParameters().get("page");
		String[] rows = (String[])actionContext.getParameters().get("rows");
//		String[] sort = (String[])actionContext.getParameters().get("sort");
//		String[] order = (String[])actionContext.getParameters().get("order");
		String[] project_status = (String[])actionContext.getParameters().get("project_status");
		String[] address = (String[])actionContext.getParameters().get("address");
		String[] customer_name = (String[])actionContext.getParameters().get("customer_name");
		String[] customer_phone = (String[])actionContext.getParameters().get("customer_phone");
		String[] postcode = (String[])actionContext.getParameters().get("postcode");
		String[] subburb = (String[])actionContext.getParameters().get("subburb");
		String[] asign_to = (String[])actionContext.getParameters().get("asign_to");
		String[] installer = (String[])actionContext.getParameters().get("installer");
		String[] door_knocker = (String[])actionContext.getParameters().get("door_knocker");
		String[] panel_brand = (String[])actionContext.getParameters().get("panel_brand");
		String[] inverter_brand = (String[])actionContext.getParameters().get("inverter_brand");
		String[] create_from = (String[])actionContext.getParameters().get("create_from");
		String[] create_to = (String[])actionContext.getParameters().get("create_to");
		String[] asign_from = (String[])actionContext.getParameters().get("asign_from");
		String[] asign_date_to = (String[])actionContext.getParameters().get("asign_date_to");
		String[] contract_from = (String[])actionContext.getParameters().get("contract_from");
		String[] contract_to = (String[])actionContext.getParameters().get("contract_to");
		String[] approved_from = (String[])actionContext.getParameters().get("approved_from");
		String[] approved_to = (String[])actionContext.getParameters().get("approved_to");
		String[] install_from = (String[])actionContext.getParameters().get("install_from");
		String[] install_to = (String[])actionContext.getParameters().get("install_to");
		
		
		
		
		String role = (String) actionContext.getSession().get("role");
		String name = (String) actionContext.getSession().get("name");
		int pageInt = Integer.parseInt(page[0]);
		int rowsInt = Integer.parseInt(rows[0]);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<Object> params = new ArrayList<>();
		String hqlForProjectInfo = "from ProjectInfo where 1 = 1";
		
		if ( project_status!=null && !"".equals(project_status[0]) && !"all".equals(project_status[0]) ) {
			hqlForProjectInfo += " and project_status = ?";
			params.add(project_status[0]);
		}
		if ( address!=null && !"".equals(address[0]) ) {
			hqlForProjectInfo += " and customer_address like ?";
			params.add("%"+address[0]+"%");
		}
		if ( customer_name!=null && !"".equals(customer_name[0]) ) {
			hqlForProjectInfo += " and customer_name like ?";
			params.add("%"+customer_name[0]+"%");
		}
		if ( customer_phone!=null && !"".equals(customer_phone[0]) ) {
			hqlForProjectInfo += " and customer_phone like ?";
			params.add("%"+customer_phone[0]+"%");
		}
		if ( postcode!=null && !"".equals(postcode[0]) ) {
			hqlForProjectInfo += " and customer_postcode like ?";
			params.add("%"+postcode[0]+"%");
		}
		if ( subburb!=null && !"".equals(subburb[0]) ) {
			hqlForProjectInfo += " and customer_subburb like ?";
			params.add("%"+subburb[0]+"%");
		}
		if ( asign_to!=null && !"".equals(asign_to[0]) ) {
			if ( "sales".equals(role) ) {
				hqlForProjectInfo += " and asign_to = ?";
				params.add(name);
			}
			else{
				hqlForProjectInfo += " and asign_to like ?";
				params.add("%"+asign_to[0]+"%");
			}
		}
		else{
			if ( "sales".equals(role) ) {
				hqlForProjectInfo += " and asign_to = ?";
				params.add(name);
			}
		}
		if ( installer!=null && !"".equals(installer[0]) ) {
			hqlForProjectInfo += " and install_person like ?";
			params.add("%"+installer[0]+"%");
		}
		if ( door_knocker!=null && !"".equals(door_knocker[0]) ) {
			if ( "door knocker".equals(role) ) {
				hqlForProjectInfo += " and leads_knocker = ?";
				params.add(name);
			}
			else{
				hqlForProjectInfo += " and leads_knocker like ?";
				params.add("%"+door_knocker[0]+"%");
			}
		}
		else{
			if ( "door knocker".equals(role) ) {
				hqlForProjectInfo += " and leads_knocker = ?";
				params.add(name);
			}
		}
		if ( panel_brand!=null && !"".equals(panel_brand[0]) ) {
			hqlForProjectInfo += " and project_id in ( select project_id from QuoteRecord where panel_brand like ? group by project_id )";
			params.add("%"+panel_brand[0]+"%");
		}
		if ( inverter_brand!=null && !"".equals(inverter_brand[0]) ) {
			hqlForProjectInfo += " and project_id in ( select project_id from QuoteRecord where inverter_brand like ? group by project_id )";
			params.add("%"+inverter_brand[0]+"%");
		}
		if ( create_from!=null && !"".equals(create_from[0]) ) {
			try {
				hqlForProjectInfo += " and create_date >= ?";
				params.add(dateFormat.parse(create_from[0]+" 00:00:00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( create_to!=null && !"".equals(create_to[0]) ) {
			try {
				hqlForProjectInfo += " and create_date <= ?";
				params.add(dateFormat.parse(create_to[0]+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( asign_from!=null && !"".equals(asign_from[0]) ) {
			try {
				hqlForProjectInfo += " and asign_date >= ?";
				params.add(dateFormat.parse(asign_from[0]+" 00:00:00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( asign_date_to!=null && !"".equals(asign_date_to[0]) ) {
			try {
				hqlForProjectInfo += " and asign_date <= ?";
				params.add(dateFormat.parse(asign_date_to[0]+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( contract_from!=null && !"".equals(contract_from[0]) ) {
			try {
				hqlForProjectInfo += " and contract_date >= ?";
				params.add(dateFormat.parse(contract_from[0]+" 00:00:00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( contract_to!=null && !"".equals(contract_to[0]) ) {
			try {
				hqlForProjectInfo += " and contract_date <= ?";
				params.add(dateFormat.parse(contract_to[0]+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( approved_from!=null && !"".equals(approved_from[0]) ) {
			try {
				hqlForProjectInfo += " and wp_appreved_date >= ?";
				params.add(dateFormat.parse(approved_from[0]+" 00:00:00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( approved_to!=null && !"".equals(approved_to[0]) ) {
			try {
				hqlForProjectInfo += " and wp_appreved_date <= ?";
				params.add(dateFormat.parse(approved_to[0]+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( install_from!=null && !"".equals(install_from[0]) ) {
			try {
				hqlForProjectInfo += " and install_date >= ?";
				params.add(dateFormat.parse(install_from[0]+" 00:00:00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ( install_to!=null && !"".equals(install_to[0]) ) {
			try {
				hqlForProjectInfo += " and install_date <= ?";
				params.add(dateFormat.parse(install_to[0]+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		hqlForProjectInfo += " order by field(project_status, 'to confirm', 'to finish', 'installation arranged', 'to contact', 'complete', 'canceled', 'leads reject'), contract_date desc, wp_approved_date desc, to_install_date desc, create_date desc, finish_date desc";
//		if ( sort!=null && !"".equals(sort[0]) ) {
//			String[] sortArray = sort[0].split(",");
//			String[] orderArray = order[0].split(",");
//			hqlForProjectInfo += " order by ";
//			for (int i = 0; i < sortArray.length; i++) {
//				hqlForProjectInfo += i==0?"":", ";
//				if ( "id".equals(sortArray[i]) ) {
//					hqlForProjectInfo += "create_date";
//				}
//				else if ( "contract_info".equals(sortArray[i]) ) {
//					hqlForProjectInfo += "contract_date";
//				}
//				hqlForProjectInfo += " " + orderArray[i];
//			}
//		}
		
		String hqlForContactRecord = "from ContactRecord where project_id = ? order by vid desc";
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ?";
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		String hqlForAdditionalComment = "from AdditionalComment where project_id = ? order by vid desc";
		
		hqlForProjectInfo += " order by vid desc";
		long total = baseDao.querySum("select count(vid) " + hqlForProjectInfo, params.toArray());
		List<ProjectInfo> projectInfos = baseDao.queryBySqlLimit(hqlForProjectInfo, params.toArray(), (pageInt-1)*rowsInt,rowsInt);
		ProjectStatusInfo projectStatusInfo = new ProjectStatusInfo();
		
		StringBuffer stringBuffer = new StringBuffer("{\"total\":").append(total).append(",\"rows\":[");
		for (int i = 0; i < projectInfos.size(); i++) {
			ProjectInfo projectInfo = projectInfos.get(i);
			stringBuffer.append(i==0?"":",")
				.append("{").append(projectInfo.toJsonStringNotClosed())
				.append(",\"status\":").append(projectStatusInfo.getStatusMap().get(projectInfo.getProject_status()));
			if ( projectInfo.getContact_date() != null ) {
				List<ContactRecord> contactRecords = baseDao.queryBySql(hqlForContactRecord, projectInfo.getProject_id());
				stringBuffer.append(",\"contact_times\":").append(contactRecords.size())
							.append(",\"latest_contact\":\"").append(contactRecords.get(0).getContact_date()).append("\"");
							
			}
			if( projectInfo.getQuoted_date() != null ){
				List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
				StringBuffer systemSize = new StringBuffer("[");
				StringBuffer price = new StringBuffer("[");
				for (int j = 0; j < quoteRecords.size(); j++) {
					if ( "to confirm".equals(quoteRecords.get(j).getStatus()) || "confirm".equals(quoteRecords.get(j).getStatus()) ){
						stringBuffer.append(",\"quote_to_confirm\":true")
						.append(",\"quote_to_confirm_panel_brand\":\"").append(quoteRecords.get(j).getPanel_brand()).append("\"")
						.append(",\"quote_to_confirm_panel_size\":\"").append(quoteRecords.get(j).getPanel_size()).append("\"")
						.append(",\"quote_to_confirm_panel_number\":\"").append(quoteRecords.get(j).getPanel_number()).append("\"")
						.append(",\"quote_to_confirm_inverter_brand\":\"").append(quoteRecords.get(j).getInverter_brand()).append("\"")
						.append(",\"quote_to_confirm_inverter_size\":\"").append(quoteRecords.get(j).getInverter_size()).append("\"")
						.append(",\"quote_to_confirm_system_size\":\"").append(quoteRecords.get(j).getSystem_size()).append("\"")
						.append(",\"quote_to_confirm_price\":\"").append(quoteRecords.get(j).getPrice()).append("\"")
						.append(",\"quote_to_confirm_payment\":\"").append(quoteRecords.get(j).getPayment()).append("\"")
						.append(",\"quote_to_confirm_deposit\":\"").append(quoteRecords.get(j).getDeposit().toString()).append("\"")
						.append(",\"quote_to_confirm_comment\":\"").append(quoteRecords.get(j).getInstall_comment()).append("\"");
					}
					systemSize.append(j==0?"":",").append("\"").append(quoteRecords.get(j).getSystem_size().toString()).append("\"");
					price.append(j==0?"":",").append("\"").append(quoteRecords.get(j).getPrice().toString()).append("\"");
					
				}
				systemSize.append("]");
				price.append("]");
				stringBuffer.append(",\"quote_system_size\":").append(systemSize)
							.append(",\"quote_price\":").append(price).append("");
			}
			
			if ( projectInfo.getContract_date() != null ) {
				List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfo.getProject_id());
				ContractConfirmInfo contractConfirmInfo = contractConfirmInfos.get(0);
				stringBuffer.append(",\"confirm_quote_info_confirm\":").append("confirm".equals(contractConfirmInfo.getQuote_info_confirm())?"true":"false")
							.append(",\"confirm_contract_info_confirm\":").append("confirm".equals(contractConfirmInfo.getContract_info_confirm())?"true":"false")
							.append(",\"confirm_electric_bill_info_confirm\":").append("confirm".equals(contractConfirmInfo.getFee_info_confirm())?"true":"false")
							.append(",\"confirm_meter_box_info_confirm\":").append("confirm".equals(contractConfirmInfo.getPhase_info_confirm())?"true":"false")
							.append(",\"confirm_nearmap_info_confirm\":").append("confirm".equals(contractConfirmInfo.getNearmap_info_confirm())?"true":"false")
							.append(",\"confirm_install_comment_confirm\":").append("confirm".equals(contractConfirmInfo.getComment_info_confirm())?"true":"false")
							.append(",\"confirm_deposit_confirm\":").append("confirm".equals(contractConfirmInfo.getDeposit_confirm())?"true":"false")
							.append(",\"confirm_dd_info_confirm\":").append("confirm".equals(contractConfirmInfo.getDd_info_confirm())?"true":"false")
							.append(",\"confirm_certegy_confirm\":").append("confirm".equals(contractConfirmInfo.getCertegy_confirm())?"true":"false")
							.append(",\"confirm_ASM_confirm\":").append("confirm".equals(contractConfirmInfo.getAsm_confirm())?"true":"false")
							.append(",\"confirm_other_finance_confirm\":").append("confirm".equals(contractConfirmInfo.getOther_finance_confirm())?"true":"false")
							.append(",\"confirm_comment\":\"").append(contractConfirmInfo.getAdmin_comment()).append("\"");
				if ( "complete".equals(contractConfirmInfo.getStatus()) ) {
				}
			}
			List<AdditionalComment> additionalComments = baseDao.queryBySqlLimit(hqlForAdditionalComment, projectInfo.getProject_id(),0,1);
			if ( additionalComments.size() > 0 ) {
				stringBuffer.append(",\"latest_additional_comment\":\"").append(additionalComments.get(0).getComment()).append("\"");
			}
			stringBuffer.append("}");
		}
		stringBuffer.append("]}");
		new JsonUtils().writeJson(stringBuffer.toString());
	}
	public void cancelProject(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] cancel_reason = (String[])actionContext.getParameters().get("cancel_reason");
		String person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setFinish_date(todayCalendar.getTime());
		projectInfo.setFinish_person(person);
		projectInfo.setFinish_remark(cancel_reason[0]);
		projectInfo.setProject_status("canceled");
		baseDao.update(projectInfo);
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void deleteProject(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		baseDao.delete(projectInfos.get(0));
		new JsonUtils().writeJson("{\"success\":true}");
	}
	@SuppressWarnings({ "unchecked" })
	public String addNewProjectInit(){
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		String hqlForUserInfo = "from UserInfo where role = 'door knocker'";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo);
		List<String> names = new ArrayList<>();
		for (int i = 0; i < userInfos.size(); i++) {
			names.add(userInfos.get(i).getReal_name());
		}
		request.put("names", names);
		return "addNewProjectInitSuccess";
	}
	public void addNewProjectSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] customer_name = (String[])actionContext.getParameters().get("customer_name");
		String[] customer_phone = (String[])actionContext.getParameters().get("customer_phone");
		String[] customer_address = (String[])actionContext.getParameters().get("customer_address");
		String[] customer_address_lat = (String[])actionContext.getParameters().get("customer_address_lat");
		String[] customer_address_lng = (String[])actionContext.getParameters().get("customer_address_lng");
		String[] customer_subburb = (String[])actionContext.getParameters().get("customer_subburb");
		String[] customer_postcode = (String[])actionContext.getParameters().get("customer_postcode");
		String[] customer_state = (String[])actionContext.getParameters().get("customer_state");
		String[] customer_email = (String[])actionContext.getParameters().get("customer_email");
		String[] leads_comment = (String[])actionContext.getParameters().get("leads_comment");
		String[] leads_type = (String[])actionContext.getParameters().get("leads_type");
		String[] leads_from = (String[])actionContext.getParameters().get("leads_from");
		String[] leads_knocker = (String[])actionContext.getParameters().get("leads_knocker");
		String create_person = (String) actionContext.getSession().get("name");
		String role = (String) actionContext.getSession().get("role");
		Calendar todayCalendar = Calendar.getInstance();
		SimpleDateFormat idFormat = new SimpleDateFormat("yyyyMMdd");
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setCustomer_name(customer_name[0]);
		projectInfo.setCustomer_phone(customer_phone[0]);
		projectInfo.setCustomer_address(customer_address[0]);
		projectInfo.setCustomer_address_lat(customer_address_lat[0]);
		projectInfo.setCustomer_address_lng(customer_address_lng[0]);
		projectInfo.setCustomer_subburb(customer_subburb[0]);
		projectInfo.setCustomer_postcode(customer_postcode[0]);
		projectInfo.setCustomer_state(customer_state[0]);
		projectInfo.setCustomer_email(customer_email[0]);
		projectInfo.setLeads_type(leads_type[0]);
		projectInfo.setLeads_from(leads_from[0]);
		projectInfo.setLeads_knocker("Door to door".equals(leads_from[0])?leads_knocker[0]:"");
		projectInfo.setLeads_comment(leads_comment[0]);
		projectInfo.setProject_status("to asign");
		projectInfo.setCreate_person(create_person);
		projectInfo.setCreate_date(todayCalendar.getTime());
		projectInfo.setCommission_status("unpaid");
		projectInfo.setCommission_value(new BigDecimal("0.00"));
		if ( "sales".equals(role) ) {
			projectInfo.setProject_status("to contact");
			projectInfo.setAsign_to(create_person);
			projectInfo.setAsign_date(todayCalendar.getTime());
		}
		
		String hqlForProjectInfo = "from ProjectInfo where project_id like ? order by vid desc";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo, idFormat.format(todayCalendar.getTime())+"%");
		String project_id = "";
		if ( projectInfos.isEmpty() ) 
			project_id = idFormat.format(todayCalendar.getTime()) + "00001";
		else
			project_id = idFormat.format(todayCalendar.getTime()) + String.format("%05d",Integer.parseInt(projectInfos.get(0).getProject_id().substring(8))+1);
		projectInfo.setProject_id(project_id);
		
		ContractConfirmInfo contractConfirmInfo = new ContractConfirmInfo();
		contractConfirmInfo.setProject_id(projectInfo.getProject_id());
		contractConfirmInfo.setStatus("incomplete");
		
		
		
		baseDao.save(contractConfirmInfo);
		
		baseDao.save(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void asignToSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] asign_to = (String[])actionContext.getParameters().get("asign_to");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		if ( !projectInfos.isEmpty() ) {
			ProjectInfo projectInfo = projectInfos.get(0);
			if ("to asign".equals(projectInfo.getProject_status())) {
				projectInfo.setProject_status("to contact");
				projectInfo.setAsign_to(asign_to[0]);
				projectInfo.setAsign_date(todayCalendar.getTime());
				baseDao.update(projectInfo);
			}
		}
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void contactSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] contact_comment = (String[])actionContext.getParameters().get("contact_comment");
		String name = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		if ( !projectInfos.isEmpty() ) {
			ProjectInfo projectInfo = projectInfos.get(0);
			if ("to contact".equals(projectInfo.getProject_status())) {
				projectInfo.setContact_person(name);
				projectInfo.setContact_date(todayCalendar.getTime());
				projectInfo.setContact_comment(contact_comment[0]);
				ContactRecord contactRecord = new ContactRecord();
				contactRecord.setProject_id(projectInfo.getProject_id());
				contactRecord.setContact_comment(contact_comment[0]);
				contactRecord.setContact_date(todayCalendar.getTime());
				contactRecord.setContact_person(name);
				baseDao.save(contactRecord);
				baseDao.update(projectInfo);
			}
		}
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void getStateByPostcode(){
		ActionContext actionContext = ActionContext.getContext();
		String[] postcode = (String[])actionContext.getParameters().get("postcode");
		String hqlForPostcodeInfo = "from PostcodeInfo where from_code <= ? and to_code >= ?";
		List<PostcodeInfo> postcodeInfos = baseDao.queryBySql(hqlForPostcodeInfo,new Object[]{Integer.parseInt(postcode[0]),Integer.parseInt(postcode[0])});
		if ( postcodeInfos.isEmpty() ) {
			new JsonUtils().writeJson("{\"success\":false}");
		}
		else{
			new JsonUtils().writeJson("{\"success\":true,\"state\":\""+postcodeInfos.get(0).getState_simple()+"\"}");
		}
	}
	@SuppressWarnings({ "unchecked" })
	public String editLeadsInfoInit(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		request.put("projectInfo", projectInfos.get(0));
		
		String hqlForUserInfo = "from UserInfo where role = 'door knocker'";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo);
		List<String> names = new ArrayList<>();
		for (int i = 0; i < userInfos.size(); i++) {
			names.add(userInfos.get(i).getReal_name());
		}
		request.put("names", names);
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfos.get(0).getProject_id());
		if ( !contractConfirmInfos.isEmpty() ) {
			request.put("contractConfirmInfo", contractConfirmInfos.get(0));
		}
		
		String hqlForContactRecord = "from ContactRecord where project_id = ?";
		List<ContactRecord> contactRecords = baseDao.queryBySql(hqlForContactRecord,projectInfos.get(0).getProject_id());
		request.put("contact_records", contactRecords);
		return "editLeadsInfoInitSuccess";
	}
	public void editLeadsInfoConfirm(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] customer_name = (String[])actionContext.getParameters().get("customer_name");
		String[] customer_phone = (String[])actionContext.getParameters().get("customer_phone");
		String[] customer_address = (String[])actionContext.getParameters().get("customer_address");
		String[] customer_address_lat = (String[])actionContext.getParameters().get("customer_address_lat");
		String[] customer_address_lng = (String[])actionContext.getParameters().get("customer_address_lng");
		String[] customer_subburb = (String[])actionContext.getParameters().get("customer_subburb");
		String[] customer_postcode = (String[])actionContext.getParameters().get("customer_postcode");
		String[] customer_state = (String[])actionContext.getParameters().get("customer_state");
		String[] customer_email = (String[])actionContext.getParameters().get("customer_email");
		String[] leads_comment = (String[])actionContext.getParameters().get("leads_comment");
		String[] leads_type = (String[])actionContext.getParameters().get("leads_type");
		String[] leads_from = (String[])actionContext.getParameters().get("leads_from");
		String[] leads_knocker = (String[])actionContext.getParameters().get("leads_knocker");
		String create_person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		if ( !customer_name[0].equals(projectInfo.getCustomer_name())
			|| !customer_phone[0].equals(projectInfo.getCustomer_phone())
			|| !customer_address[0].equals(projectInfo.getCustomer_address())
			|| !customer_subburb[0].equals(projectInfo.getCustomer_subburb())
			|| !customer_postcode[0].equals(projectInfo.getCustomer_postcode())
			|| !customer_state[0].equals(projectInfo.getCustomer_state())
			|| !customer_email[0].equals(projectInfo.getCustomer_email())
			|| !leads_comment[0].equals(projectInfo.getLeads_comment())
			|| !leads_type[0].equals(projectInfo.getLeads_type())
			|| !leads_from[0].equals(projectInfo.getLeads_from())
			|| !leads_knocker[0].equals(projectInfo.getLeads_knocker())) {
			
			LeadsModifyRecord leadsModifyRecord = new LeadsModifyRecord();
			leadsModifyRecord.setCreate_person(create_person);
			leadsModifyRecord.setCreate_date(todayCalendar.getTime());
			leadsModifyRecord.setProject_id(projectInfo.getProject_id());
			leadsModifyRecord.setStatus("confirmed");
			leadsModifyRecord.setCustomer_name(projectInfo.getCustomer_name());
			leadsModifyRecord.setCustomer_phone(projectInfo.getCustomer_phone());
			leadsModifyRecord.setCustomer_address(projectInfo.getCustomer_address());
			leadsModifyRecord.setCustomer_address_lat(projectInfo.getCustomer_address_lat());
			leadsModifyRecord.setCustomer_address_lng(projectInfo.getCustomer_address_lng());
			leadsModifyRecord.setCustomer_subburb(projectInfo.getCustomer_subburb());
			leadsModifyRecord.setCustomer_postcode(projectInfo.getCustomer_postcode());
			leadsModifyRecord.setCustomer_state(projectInfo.getCustomer_state());
			leadsModifyRecord.setCustomer_email(projectInfo.getCustomer_email());
			leadsModifyRecord.setLeads_comment(projectInfo.getLeads_comment());
			leadsModifyRecord.setLeads_type(projectInfo.getLeads_type());
			leadsModifyRecord.setLeads_from(projectInfo.getLeads_from());
			leadsModifyRecord.setLeads_knocker(projectInfo.getLeads_knocker());
			
			projectInfo.setCustomer_name(customer_name[0]);
			projectInfo.setCustomer_phone(customer_phone[0]);
			projectInfo.setCustomer_address(customer_address[0]);
			projectInfo.setCustomer_address_lat(customer_address_lat[0]);
			projectInfo.setCustomer_address_lng(customer_address_lng[0]);
			projectInfo.setCustomer_subburb(customer_subburb[0]);
			projectInfo.setCustomer_postcode(customer_postcode[0]);
			projectInfo.setCustomer_state(customer_state[0]);
			projectInfo.setCustomer_email(customer_email[0]);
			projectInfo.setLeads_comment(leads_comment[0]);
			projectInfo.setLeads_type(leads_type[0]);
			projectInfo.setLeads_from(leads_from[0]);
			projectInfo.setLeads_knocker("Door to door".equals(leads_from[0])?leads_knocker[0]:"");
			baseDao.update(projectInfo);
			baseDao.save(leadsModifyRecord);
			new JsonUtils().writeJson("{\"success\":true}");
		}
		else
			new JsonUtils().writeJson("{\"success\":false}");
	}
	public void addNewProjectFromWP(){
		ActionContext actionContext = ActionContext.getContext();
		String[] customer_name = (String[])actionContext.getParameters().get("customer_name");
		String[] customer_phone = (String[])actionContext.getParameters().get("customer_phone");
		String[] customer_address = (String[])actionContext.getParameters().get("customer_address");
		String[] customer_subburb = (String[])actionContext.getParameters().get("customer_subburb");
		String[] customer_postcode = (String[])actionContext.getParameters().get("customer_postcode");
//		String customer_state = "";
		String[] customer_email = (String[])actionContext.getParameters().get("customer_email");
		String[] jsoncallback = (String[])actionContext.getParameters().get("jsoncallback");
//		String leads_comment = "";
//		String leads_type = "Residential";
//		String leads_from = "Google ads";
//		String leads_knocker = "";
//		String create_person = "Customer from website";
		Calendar todayCalendar = Calendar.getInstance();
		SimpleDateFormat idFormat = new SimpleDateFormat("yyyyMMdd");
		
//		String hqlForPostcodeInfo = "from PostcodeInfo where from_code <= ? and to_code >= ?";
//		List<PostcodeInfo> postcodeInfos = baseDao.queryBySql(hqlForPostcodeInfo,new Object[]{Integer.parseInt(customer_postcode[0]),Integer.parseInt(customer_postcode[0])});
//		if ( postcodeInfos.isEmpty() ) {
//			customer_state = "Wrong Postcode!";
//		}
//		else{
//			customer_state = postcodeInfos.get(0).getState_simple();
//		}
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setCustomer_name(customer_name[0]);
		projectInfo.setCustomer_phone(customer_phone[0]);
		projectInfo.setCustomer_address((customer_address!=null&&"".equals(customer_address[0]))?customer_address[0]:"");
		projectInfo.setCustomer_subburb((customer_subburb!=null&&"".equals(customer_subburb[0]))?customer_subburb[0]:"");
		projectInfo.setCustomer_postcode((customer_postcode!=null&&"".equals(customer_postcode[0]))?customer_postcode[0]:"");
		projectInfo.setCustomer_email((customer_email!=null&&"".equals(customer_email[0]))?customer_email[0]:"");
		projectInfo.setCustomer_state("");
		projectInfo.setLeads_type("");
		projectInfo.setLeads_from("");
//		projectInfo.setLeads_knocker(leads_knocker);
//		projectInfo.setLeads_comment(leads_comment);
		projectInfo.setCreate_person("");
		projectInfo.setProject_status("to asign");
		projectInfo.setCommission_status("unpaid");
		projectInfo.setCommission_value(new BigDecimal("0.00"));
		projectInfo.setCreate_date(todayCalendar.getTime());
		
		String hqlForProjectInfo = "from ProjectInfo where project_id like ? order by vid desc";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo, idFormat.format(todayCalendar.getTime())+"%");
		String project_id = "";
		if ( projectInfos.isEmpty() ) 
			project_id = idFormat.format(todayCalendar.getTime()) + "00001";
		else
			project_id = idFormat.format(todayCalendar.getTime()) + String.format("%05d",Integer.parseInt(projectInfos.get(0).getProject_id().substring(8))+1);
		projectInfo.setProject_id(project_id);
		
		ContractConfirmInfo contractConfirmInfo = new ContractConfirmInfo();
		contractConfirmInfo.setProject_id(projectInfo.getProject_id());
		contractConfirmInfo.setStatus("incomplete");
		baseDao.save(contractConfirmInfo);
		
		baseDao.save(projectInfo);
		
		sendEmail("370013762@qq.com","iwrttfipjispbgfa","stanley.sun@ausuntechsolar.com",customer_name[0],customer_phone[0],projectInfo.getProject_id());
		
		new JsonUtils().writeJsonP(jsoncallback[0]+"({\"success\":true})");
	}
	private void sendEmail(String username, String password, String to, String customerName, String customerPhone, String projectId){
		Properties props = new Properties();
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
		session.setDebug(false);
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Enquiry Remind");
			message.setSentDate(new Date());
			message.setText("Enquiry Remind:" +
					"\n\n Project ID: " + projectId +
					"\n\n Customer Name: " + customerName +
					"\n\n Customer Phone: " + customerPhone +
					"\n\n Login CRM: https://ausuntechenergy.com.au"
					);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings({ "unchecked" })
	public String readyToQuote(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		request.put("projectInfo", projectInfos.get(0));
		return "readyToQuoteSuccess";
	}
	public void readyToQuoteSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] changedField = (String[])actionContext.getParameters().get("changedField");
		String[] customer_roof_type = (String[])actionContext.getParameters().get("customer_roof_type");
		String[] customer_storey = (String[])actionContext.getParameters().get("customer_storey");
		String[] customer_phase_type = (String[])actionContext.getParameters().get("customer_phase_type");
		String[] panel_brand = (String[])actionContext.getParameters().get("panel_brand");
		String[] panel_size = (String[])actionContext.getParameters().get("panel_size");
		String[] panel_number = (String[])actionContext.getParameters().get("panel_number");
		String[] inverter_brand = (String[])actionContext.getParameters().get("inverter_brand");
		String[] inverter_size = (String[])actionContext.getParameters().get("inverter_size");
		String[] system_size = (String[])actionContext.getParameters().get("system_size");
		String[] price = (String[])actionContext.getParameters().get("price");
//		String[] payment = (String[])actionContext.getParameters().get("payment");
		String quote_person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		if ( !"".equals(changedField[0]) ) {
			String[] customer_name = (String[])actionContext.getParameters().get("customer_name");
			String[] customer_phone = (String[])actionContext.getParameters().get("customer_phone");
			String[] customer_address = (String[])actionContext.getParameters().get("customer_address");
			String[] customer_address_lat = (String[])actionContext.getParameters().get("customer_address_lat");
			String[] customer_address_lng = (String[])actionContext.getParameters().get("customer_address_lng");
			String[] customer_subburb = (String[])actionContext.getParameters().get("customer_subburb");
			String[] customer_postcode = (String[])actionContext.getParameters().get("customer_postcode");
			String[] customer_state = (String[])actionContext.getParameters().get("customer_state");
			LeadsModifyRecord leadsModifyRecord = new LeadsModifyRecord();
			leadsModifyRecord.setCreate_person(quote_person);
			leadsModifyRecord.setCreate_date(todayCalendar.getTime());
			leadsModifyRecord.setProject_id(projectInfo.getProject_id());
			leadsModifyRecord.setStatus("confirmed");
			leadsModifyRecord.setCustomer_name(projectInfo.getCustomer_name());
			leadsModifyRecord.setCustomer_phone(projectInfo.getCustomer_phone());
			leadsModifyRecord.setCustomer_address(projectInfo.getCustomer_address());
			leadsModifyRecord.setCustomer_address_lat(projectInfo.getCustomer_address_lat());
			leadsModifyRecord.setCustomer_address_lng(projectInfo.getCustomer_address_lng());
			leadsModifyRecord.setCustomer_subburb(projectInfo.getCustomer_subburb());
			leadsModifyRecord.setCustomer_postcode(projectInfo.getCustomer_postcode());
			leadsModifyRecord.setCustomer_state(projectInfo.getCustomer_state());
			leadsModifyRecord.setCustomer_email(projectInfo.getCustomer_email());
			leadsModifyRecord.setLeads_comment(projectInfo.getLeads_comment());
			leadsModifyRecord.setLeads_type(projectInfo.getLeads_type());
			leadsModifyRecord.setLeads_from(projectInfo.getLeads_from());
			leadsModifyRecord.setLeads_knocker(projectInfo.getLeads_knocker());
			baseDao.save(leadsModifyRecord);
			
			projectInfo.setCustomer_name(customer_name[0]);
			projectInfo.setCustomer_phone(customer_phone[0]);
			projectInfo.setCustomer_address(customer_address[0]);
			projectInfo.setCustomer_address_lat(customer_address_lat[0]);
			projectInfo.setCustomer_address_lng(customer_address_lng[0]);
			projectInfo.setCustomer_subburb(customer_subburb[0]);
			projectInfo.setCustomer_postcode(customer_postcode[0]);
			projectInfo.setCustomer_state(customer_state[0]);
		}
		
		projectInfo.setCustomer_storey(customer_storey[0]);
		projectInfo.setCustomer_phase_type(customer_phase_type[0]);
		projectInfo.setCustomer_roof_type("Other".equals(customer_roof_type[0])?("Other:"+customer_roof_type[1]):customer_roof_type[0]);
		projectInfo.setProject_status("to quote");
		projectInfo.setQuoted_date(todayCalendar.getTime());
		projectInfo.setQuoted_person(quote_person);
		baseDao.update(projectInfo);
		
		for (int i = 0; i < price.length; i++) {
			if (!"".equals(price[i])) {
				QuoteRecord quoteRecord = new QuoteRecord();
				quoteRecord.setProject_id(projectInfo.getProject_id());
				quoteRecord.setQuote_person(quote_person);
				quoteRecord.setQuote_date(todayCalendar.getTime());
				quoteRecord.setPanel_brand(panel_brand[i]);
				quoteRecord.setPanel_size(new BigDecimal(panel_size[i]));
				quoteRecord.setPanel_number(Integer.parseInt(panel_number[i]));
				quoteRecord.setInverter_brand(inverter_brand[i]);
				quoteRecord.setInverter_size(inverter_size[i]);
				quoteRecord.setSystem_size(new BigDecimal(system_size[i]));
				quoteRecord.setPrice(new BigDecimal(price[i]));
				quoteRecord.setStatus("submit");
				baseDao.save(quoteRecord);
			}
		}
		new JsonUtils().writeJson("{\"success\":true}");
	}
	@SuppressWarnings("unchecked")
	public String showQuoteDetail(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		request.put("projectInfo", projectInfo);
		request.put("status", new ProjectStatusInfo().getStatusMap().get(projectInfo.getProject_status()));
		if ( projectInfo.getCustomer_roof_type().startsWith("Other") ) {
			request.put("customerRoofOther", true);
			request.put("customerRoofOtherValue", projectInfo.getCustomer_roof_type().substring(projectInfo.getCustomer_roof_type().indexOf(":")+1));
		}
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ?";
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
		for (int i = 0; i < quoteRecords.size(); i++) {
			request.put("quoteRecord"+(i+1), quoteRecords.get(i));
		}
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfo.getProject_id());
		if ( !contractConfirmInfos.isEmpty() ) {
			request.put("contractConfirmInfo", contractConfirmInfos.get(0));
		}
		
		return "showQuoteDetailSuccess";
	}
	public void editQuoteSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] changedField = (String[])actionContext.getParameters().get("changedField");
		String[] customer_roof_type = (String[])actionContext.getParameters().get("customer_roof_type");
		String[] customer_storey = (String[])actionContext.getParameters().get("customer_storey");
		String[] customer_phase_type = (String[])actionContext.getParameters().get("customer_phase_type");
		String[] panel_brand = (String[])actionContext.getParameters().get("panel_brand");
		String[] panel_size = (String[])actionContext.getParameters().get("panel_size");
		String[] panel_number = (String[])actionContext.getParameters().get("panel_number");
		String[] inverter_brand = (String[])actionContext.getParameters().get("inverter_brand");
		String[] inverter_size = (String[])actionContext.getParameters().get("inverter_size");
		String[] system_size = (String[])actionContext.getParameters().get("system_size");
		String[] price = (String[])actionContext.getParameters().get("price");
		String[] deposit = (String[])actionContext.getParameters().get("deposit");
		String[] payment = (String[])actionContext.getParameters().get("payment");
		String[] install_comment = (String[])actionContext.getParameters().get("install_comment");
		String[] ABN = (String[])actionContext.getParameters().get("ABN");
		String[] business_type = (String[])actionContext.getParameters().get("business_type");
		String quote_person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ?";
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		if ( !"".equals(changedField[0]) ) {
			String[] customer_name = (String[])actionContext.getParameters().get("customer_name");
			String[] customer_phone = (String[])actionContext.getParameters().get("customer_phone");
			String[] customer_address = (String[])actionContext.getParameters().get("customer_address");
			String[] customer_address_lat = (String[])actionContext.getParameters().get("customer_address_lat");
			String[] customer_address_lng = (String[])actionContext.getParameters().get("customer_address_lng");
			String[] customer_subburb = (String[])actionContext.getParameters().get("customer_subburb");
			String[] customer_postcode = (String[])actionContext.getParameters().get("customer_postcode");
			String[] customer_state = (String[])actionContext.getParameters().get("customer_state");
			LeadsModifyRecord leadsModifyRecord = new LeadsModifyRecord();
			leadsModifyRecord.setCreate_person(quote_person);
			leadsModifyRecord.setCreate_date(todayCalendar.getTime());
			leadsModifyRecord.setProject_id(projectInfo.getProject_id());
			leadsModifyRecord.setStatus("confirmed");
			leadsModifyRecord.setCustomer_name(projectInfo.getCustomer_name());
			leadsModifyRecord.setCustomer_phone(projectInfo.getCustomer_phone());
			leadsModifyRecord.setCustomer_address(projectInfo.getCustomer_address());
			leadsModifyRecord.setCustomer_address_lat(projectInfo.getCustomer_address_lat());
			leadsModifyRecord.setCustomer_address_lng(projectInfo.getCustomer_address_lng());
			leadsModifyRecord.setCustomer_subburb(projectInfo.getCustomer_subburb());
			leadsModifyRecord.setCustomer_postcode(projectInfo.getCustomer_postcode());
			leadsModifyRecord.setCustomer_state(projectInfo.getCustomer_state());
			leadsModifyRecord.setCustomer_email(projectInfo.getCustomer_email());
			leadsModifyRecord.setLeads_comment(projectInfo.getLeads_comment());
			leadsModifyRecord.setLeads_type(projectInfo.getLeads_type());
			leadsModifyRecord.setLeads_from(projectInfo.getLeads_from());
			leadsModifyRecord.setLeads_knocker(projectInfo.getLeads_knocker());
			baseDao.save(leadsModifyRecord);
			
			projectInfo.setCustomer_name(customer_name[0]);
			projectInfo.setCustomer_phone(customer_phone[0]);
			projectInfo.setCustomer_address(customer_address[0]);
			projectInfo.setCustomer_address_lat(customer_address_lat[0]);
			projectInfo.setCustomer_address_lng(customer_address_lng[0]);
			projectInfo.setCustomer_subburb(customer_subburb[0]);
			projectInfo.setCustomer_postcode(customer_postcode[0]);
			projectInfo.setCustomer_state(customer_state[0]);
		}
		
		projectInfo.setCustomer_storey(customer_storey[0]);
		projectInfo.setCustomer_phase_type(customer_phase_type[0]);
		projectInfo.setCustomer_roof_type("Other".equals(customer_roof_type[0])?("Other:"+customer_roof_type[1]):customer_roof_type[0]);
		projectInfo.setProject_status("to quote");
		projectInfo.setQuoted_date(todayCalendar.getTime());
		projectInfo.setQuoted_person(quote_person);
		baseDao.update(projectInfo);
		
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
		for (int i = 0; i < quoteRecords.size(); i++) {
			baseDao.delete(quoteRecords.get(i));
		}
		StringBuffer stringBuffer = new StringBuffer("[");
		for (int i = 0; i < price.length; i++) {
			if (!"".equals(price[i])) {
				QuoteRecord quoteRecord = new QuoteRecord();
				quoteRecord.setProject_id(projectInfo.getProject_id());
				quoteRecord.setQuote_person(quote_person);
				quoteRecord.setQuote_date(todayCalendar.getTime());
				quoteRecord.setPanel_brand(panel_brand[i]);
				quoteRecord.setPanel_size(new BigDecimal(panel_size[i]));
				quoteRecord.setPanel_number(Integer.parseInt(panel_number[i]));
				quoteRecord.setInverter_brand(inverter_brand[i]);
				quoteRecord.setInverter_size(inverter_size[i]);
				quoteRecord.setSystem_size(new BigDecimal(system_size[i]));
				quoteRecord.setPrice(new BigDecimal(price[i]));
				quoteRecord.setDeposit(new BigDecimal("".equals(deposit[i])?"0.00":deposit[i]));
				quoteRecord.setPayment(payment[i]);
				quoteRecord.setInstall_comment(install_comment[i]);
				quoteRecord.setABN(ABN[i]);
				quoteRecord.setBusiness_type(business_type[i]);
				quoteRecord.setStatus("submit");
				baseDao.save(quoteRecord);
				stringBuffer.append(i==0?"":",").append(quoteRecord.getVid());
			}
		}
		stringBuffer.append("]");
		new JsonUtils().writeJson("{\"success\":true,\"vids\":"+stringBuffer.toString()+"}");
	}
	public void readyToSign(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] quote_record_vid = (String[])actionContext.getParameters().get("quote_record_vid");
		String person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ?";
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord,projectInfo.getProject_id());
		for (int i = 0; i < quoteRecords.size(); i++) {
			if ( quoteRecords.get(i).getVid().equals(Integer.parseInt(quote_record_vid[0])) )
				quoteRecords.get(i).setStatus("to confirm");
			else
				quoteRecords.get(i).setStatus("unselected");
			baseDao.update(quoteRecords.get(i));
		}
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfo.getProject_id());
		ContractConfirmInfo contractConfirmInfo = contractConfirmInfos.get(0);
		contractConfirmInfo.setQuote_record_id(Integer.parseInt(quote_record_vid[0]));
		contractConfirmInfo.setQuote_info_confirm(null);
		contractConfirmInfo.setStatus("incomplete");
		baseDao.update(contractConfirmInfo);
		
		projectInfo.setVisit_date(todayCalendar.getTime());
		projectInfo.setVisit_person(person);
		projectInfo.setContract_date(todayCalendar.getTime());
		projectInfo.setContract_person(person);
		projectInfo.setProject_status("to confirm");
		projectInfo.setWp_status(null);
		baseDao.update(projectInfo);
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public String downloadQuotation(){
		return "downloadQuotationSuccess";
	}
	public InputStream getDownloadQuotation(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ?";
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
		
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String toSharedTempPath = path + "temp"+File.separator+"temp" + RandomStringUtils.randomAlphanumeric(10) + ".xml";
		String resultTemplatePath = path + "resources"+File.separator+"file_templates"+File.separator+"quotation_template.xlsx";
		String toExcelFilePath = path + "temp"+File.separator+"temp" + RandomStringUtils.randomAlphanumeric(10) + ".xlsx";
		
		Map<String, Object> map = new HashMap<>();
		map.put("projectInfo", projectInfo);
		map.put("quoteRecord", quoteRecords);
		map.put("today", new Date());
		
		ZipOutputStream zipout = null;
		try {
			
			Writer writer = null;
			writer = new FileWriter(new File(toSharedTempPath));
			
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(path+"resources"+File.separator+"file_templates"+File.separator));  
			Template template = configuration.getTemplate("quotation_sharedStrings.ftl");
			template.setOutputEncoding("UTF-8");
			template.process(map, writer);
			
			ZipFile zipFile = new ZipFile(new File(resultTemplatePath));
			Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
			zipout = new ZipOutputStream(new FileOutputStream(toExcelFilePath));
			int len = -1;
			byte[] buffer = new byte[1024];
			while (zipEntrys.hasMoreElements()) {
				ZipEntry next = zipEntrys.nextElement();
				InputStream is = zipFile.getInputStream(next);
				// 把输入流的文件传到输出流中 如果是word/document.xml由我们输入
				zipout.putNextEntry(new ZipEntry(next.toString()));
				if ("xl/sharedStrings.xml".equals(next.toString())) {
					InputStream in = new FileInputStream(toSharedTempPath);
					while ((len = in.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					in.close();
				}
				else {
					while ((len = is.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					is.close();
				}
			}
			zipout.close();
			zipFile.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(toExcelFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        FileDeleteUtil fileDeleteUtil = new FileDeleteUtil(toSharedTempPath);  
	        Thread t = new Thread(fileDeleteUtil);  
	        t.start();  
	        fileDeleteUtil = new FileDeleteUtil(toExcelFilePath);  
	        t = new Thread(fileDeleteUtil);  
	        t.start();  
		}
		return inputStream;
	}
	@SuppressWarnings("unchecked")
	public String quoteConfirmInit(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		request.put("projectInfo", projectInfo);
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and ( status = 'to confirm' or status = 'confirm' )";
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
		request.put("quoteRecord", quoteRecords.get(0));
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfo.getProject_id());
		request.put("contractConfirmInfo", contractConfirmInfos.get(0));
		
		return "quoteConfirmInitSuccess";
	}
	public void contractConfirmSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] type = (String[])actionContext.getParameters().get("type");
		String person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		int typeInt = Integer.parseInt(type[0]);
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfo.getProject_id());
		ContractConfirmInfo contractConfirmInfo = contractConfirmInfos.get(0);
		
		switch (typeInt) {
			case 1:{
				contractConfirmInfo.setQuote_info_confirm("confirm");
				String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and status = 'to confirm'";
				List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
				quoteRecords.get(0).setStatus("confirm");
				quoteRecords.get(0).setConfirm_date(todayCalendar.getTime());
				quoteRecords.get(0).setConfirm_person(person);
				baseDao.update(quoteRecords.get(0));
				break;
			}
			case 2:{
				contractConfirmInfo.setContract_info_confirm("confirm");
				break;
			}
			case 3:{
				contractConfirmInfo.setFee_info_confirm("confirm");
				break;
			}
			case 4:{
				contractConfirmInfo.setPhase_info_confirm("confirm");
				break;
			}
			case 5:{
				contractConfirmInfo.setNearmap_info_confirm("confirm");
				break;
			}
			case 6:{
				contractConfirmInfo.setComment_info_confirm("confirm");
				break;
			}
			case 7:{
				contractConfirmInfo.setDeposit_confirm("confirm");
				break;
			}
			case 8:{
				contractConfirmInfo.setDd_info_confirm("confirm");
				break;
			}
			case 9:{
				contractConfirmInfo.setCertegy_confirm("confirm");
				break;
			}
			case 10:{
				contractConfirmInfo.setAsm_confirm("confirm");
				break;
			}
			case 11:{
				contractConfirmInfo.setOther_finance_confirm("confirm");
				break;
			}
			default:
				break;
		}
		if( "confirm".equals(contractConfirmInfo.getQuote_info_confirm())
			&& "confirm".equals(contractConfirmInfo.getContract_info_confirm())
			&& "confirm".equals(contractConfirmInfo.getFee_info_confirm())
			&& "confirm".equals(contractConfirmInfo.getPhase_info_confirm())
			&& "confirm".equals(contractConfirmInfo.getNearmap_info_confirm())){
			if( !"complete".equals(contractConfirmInfo.getStatus()) ){
				contractConfirmInfo.setStatus("complete");
				projectInfo.setConfirm_date(todayCalendar.getTime());
				projectInfo.setConfirm_person(person);
				projectInfo.setProject_status("to check");
				projectInfo.setWp_status("WP ready to apply");
				baseDao.update(projectInfo);
			}
		}
		baseDao.update(contractConfirmInfo);
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void contractConfirmRevoke(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] type = (String[])actionContext.getParameters().get("type");
		int typeInt = Integer.parseInt(type[0]);
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where project_id = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, projectInfo.getProject_id());
		ContractConfirmInfo contractConfirmInfo = contractConfirmInfos.get(0);
		
		switch (typeInt) {
		case 1:{
			contractConfirmInfo.setQuote_info_confirm(null);
			String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and status = 'confirm'";
			List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
			quoteRecords.get(0).setStatus("to confirm");
			quoteRecords.get(0).setConfirm_date(null);
			quoteRecords.get(0).setConfirm_person(null);
			baseDao.update(quoteRecords.get(0));
			break;
		}
		case 2:{
			contractConfirmInfo.setContract_info_confirm(null);
			break;
		}
		case 3:{
			contractConfirmInfo.setFee_info_confirm(null);
			break;
		}
		case 4:{
			contractConfirmInfo.setPhase_info_confirm(null);
			break;
		}
		case 5:{
			contractConfirmInfo.setNearmap_info_confirm(null);
			break;
		}
		case 6:{
			contractConfirmInfo.setComment_info_confirm(null);
			break;
		}
		case 7:{
			contractConfirmInfo.setDeposit_confirm(null);
			break;
		}
		case 8:{
			contractConfirmInfo.setDd_info_confirm(null);
			break;
		}
		case 9:{
			contractConfirmInfo.setCertegy_confirm(null);
			break;
		}
		case 10:{
			contractConfirmInfo.setAsm_confirm(null);
			break;
		}
		case 11:{
			contractConfirmInfo.setOther_finance_confirm(null);
			break;
		}
		default:
			break;
		}
		if( contractConfirmInfo.getQuote_info_confirm() == null
			|| contractConfirmInfo.getContract_info_confirm() == null
			|| contractConfirmInfo.getFee_info_confirm() == null
			|| contractConfirmInfo.getPhase_info_confirm() == null
			|| contractConfirmInfo.getNearmap_info_confirm() == null){
			if( !"incomplete".equals(contractConfirmInfo.getStatus()) ){
				contractConfirmInfo.setStatus("incomplete");
				projectInfo.setConfirm_date(null);
				projectInfo.setConfirm_person(null);
				projectInfo.setProject_status("to confirm");
				projectInfo.setWp_status(null);
				baseDao.update(projectInfo);
			}
		}
		baseDao.update(contractConfirmInfo);
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void contractConfirmCommentSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] comment = (String[])actionContext.getParameters().get("comment");
		
		String hqlForContractConfirmInfo = "from ContractConfirmInfo where vid = ?";
		List<ContractConfirmInfo> contractConfirmInfos = baseDao.queryBySql(hqlForContractConfirmInfo, Integer.parseInt(vid[0]));
		contractConfirmInfos.get(0).setAdmin_comment(comment[0]);
		baseDao.update(contractConfirmInfos.get(0));
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void readyForWP(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setWp_status("WP applied & pending");
		projectInfo.setWp_submit_date(new Date());
		if ( projectInfo.getInstall_date() != null ) {
			projectInfo.setProject_status("to finish");
		}
		else if (projectInfo.getInstall_arrange_date() != null) {
			projectInfo.setProject_status("installation arranged");
		}
		else{
			projectInfo.setProject_status("to install");
		}
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void approvedForWP(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setWp_status("WP approved");
		projectInfo.setWp_approved_date(new Date());
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void revokeForWP(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		if ( "WP approved".equals(projectInfo.getWp_status()) ) {
			projectInfo.setWp_approved_date(null);
			projectInfo.setWp_status("WP applied & pending");
		}
		else if ( "WP applied & pending".equals(projectInfo.getWp_status()) ) {
			projectInfo.setProject_status("to check");
			projectInfo.setWp_status("WP ready to apply");
			projectInfo.setWp_submit_date(null);
		}
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public String installShowMapInit(){
		return "installShowMapInitSuccess";
	}
	public void queryProjectInfoToInstall(){
		ActionContext actionContext = ActionContext.getContext();
		String[] page = (String[])actionContext.getParameters().get("page");
		String[] rows = (String[])actionContext.getParameters().get("rows");
		String[] project_status = (String[])actionContext.getParameters().get("project_status");
		
		int pageInt = Integer.parseInt(page[0]);
		int rowsInt = Integer.parseInt(rows[0]);
		List<Object> params = new ArrayList<>();
		String hqlForProjectInfo = "from ProjectInfo where 1 = 1";
		
		if ( project_status!=null && !"".equals(project_status[0]) && !"all".equals(project_status[0]) ) {
			hqlForProjectInfo += " and project_status = ?";
			params.add(project_status[0]);
		}
		hqlForProjectInfo += " order by create_date asc, vid desc";
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and status = 'confirm'";
		long total = baseDao.querySum("select count(vid) " + hqlForProjectInfo, params.toArray());
		List<ProjectInfo> projectInfos = baseDao.queryBySqlLimit(hqlForProjectInfo, params.toArray(), (pageInt-1)*rowsInt,rowsInt);
		StringBuffer stringBuffer = new StringBuffer("{\"total\":").append(total).append(",\"rows\":[");
		for (int i = 0; i < projectInfos.size(); i++) {
			ProjectInfo projectInfo = projectInfos.get(i);
			List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
			
			stringBuffer.append(i==0?"":",").append("{")
				.append(projectInfo.toJsonStringNotClosed());
			if ( quoteRecords.size() > 0 ) {
				stringBuffer.append(",\"panel_brand\":\"").append(quoteRecords.get(0).getPanel_brand()).append("\"")
				.append(",\"panel_size\":\"").append(quoteRecords.get(0).getPanel_size()).append("\"")
				.append(",\"panel_number\":\"").append(quoteRecords.get(0).getPanel_number()).append("\"")
				.append(",\"inverter_brand\":\"").append(quoteRecords.get(0).getInverter_brand()).append("\"")
				.append(",\"inverter_size\":\"").append(quoteRecords.get(0).getInverter_size()).append("\"")
				.append(",\"system_size\":\"").append(quoteRecords.get(0).getSystem_size()).append("\"")
				.append(",\"price\":\"").append(quoteRecords.get(0).getPrice()).append("\"")
				.append(",\"deposit\":\"").append(quoteRecords.get(0).getDeposit()).append("\"")
				.append(",\"install_comment\":\"").append(quoteRecords.get(0).getInstall_comment()).append("\"");
			}
			stringBuffer.append("}");
		}
		stringBuffer.append("]}");
		new JsonUtils().writeJson(stringBuffer.toString());
	}
	@SuppressWarnings("unchecked")
	public String readyToInstallInit(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		request.put("projectInfo", projectInfo);
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and ( status = 'to confirm' or status = 'confirm' )";
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
		request.put("quoteRecord", quoteRecords.get(0));
		
		return "readyToInstallInitSuccess";
	}
	public void readyToInstallSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] to_install_date = (String[])actionContext.getParameters().get("to_install_date");
		String[] install_person = (String[])actionContext.getParameters().get("install_person");
		String[] install_comment = (String[])actionContext.getParameters().get("install_comment");
		String name = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		if ( !projectInfos.isEmpty() ) {
			ProjectInfo projectInfo = projectInfos.get(0);
			projectInfo.setInstall_person(install_person[0]);
			try {
				projectInfo.setTo_install_date(format.parse(to_install_date[0]+" 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			projectInfo.setInstall_comment(install_comment[0]);
			projectInfo.setInstall_arrange_date(todayCalendar.getTime());
			projectInfo.setInstall_arrange_person(name);
			projectInfo.setProject_status("installation arranged");
			baseDao.update(projectInfo);
		}
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void finishInstall(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setInstall_date(new Date());
		projectInfo.setProject_status("to finish");
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void revokeForInstall(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setInstall_date(null);
		projectInfo.setProject_status("installation arranged");
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	
	
	public String downloadInstallInfo(){
		return "downloadInstallInfoSuccess";
	}
	public InputStream getDownloadInstallInfo(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and status='confirm'";
		List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
		
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String toSharedTempPath = path + "temp"+File.separator+"temp" + RandomStringUtils.randomAlphanumeric(10) + ".xml";
		String resultTemplatePath = path + "resources"+File.separator+"file_templates"+File.separator+"installinfo_template.xlsx";
		String toExcelFilePath = path + "temp"+File.separator+"temp" + RandomStringUtils.randomAlphanumeric(10) + ".xlsx";
		
		Map<String, Object> map = new HashMap<>();
		projectInfo.setInstall_comment("Install Comment: "+projectInfo.getInstall_comment());
		map.put("projectInfo", projectInfo);
		quoteRecords.get(0).setInstall_comment("Sales Rep Comment: "+quoteRecords.get(0).getInstall_comment());
		map.put("quoteRecord", quoteRecords.get(0));
//		map.put("today", new Date());
		
		ZipOutputStream zipout = null;
		try {
			
			Writer writer = null;
			writer = new FileWriter(new File(toSharedTempPath));
			
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(path+"resources"+File.separator+"file_templates"+File.separator));  
			Template template = configuration.getTemplate("installinfo_sharedStrings.ftl");
			template.setOutputEncoding("UTF-8");
			template.process(map, writer);
			
			ZipFile zipFile = new ZipFile(new File(resultTemplatePath));
			Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
			zipout = new ZipOutputStream(new FileOutputStream(toExcelFilePath));
			int len = -1;
			byte[] buffer = new byte[1024];
			while (zipEntrys.hasMoreElements()) {
				ZipEntry next = zipEntrys.nextElement();
				InputStream is = zipFile.getInputStream(next);
				// 把输入流的文件传到输出流中 如果是word/document.xml由我们输入
				zipout.putNextEntry(new ZipEntry(next.toString()));
				if ("xl/sharedStrings.xml".equals(next.toString())) {
					InputStream in = new FileInputStream(toSharedTempPath);
					while ((len = in.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					in.close();
				}
				else {
					while ((len = is.read(buffer)) != -1) {
						zipout.write(buffer, 0, len);
					}
					is.close();
				}
			}
			zipout.close();
			zipFile.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(toExcelFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        FileDeleteUtil fileDeleteUtil = new FileDeleteUtil(toSharedTempPath);  
	        Thread t = new Thread(fileDeleteUtil);  
	        t.start();  
	        fileDeleteUtil = new FileDeleteUtil(toExcelFilePath);  
	        t = new Thread(fileDeleteUtil);  
	        t.start();  
		}
		return inputStream;
	}
	public void queryInstalledProjectFromWP(){
		ActionContext actionContext = ActionContext.getContext();
		String[] jsoncallback = (String[])actionContext.getParameters().get("jsoncallback");
		
		String hqlForProjectInfo = "from ProjectInfo where project_status='to finish' or project_status = 'complete' order by vid desc";
		List<ProjectInfo> projectInfos = baseDao.queryBySqlLimit(hqlForProjectInfo,0,50);
		StringBuffer stringBuffer = new StringBuffer("({\"latlng\":[");
		for (int i = 0; i < projectInfos.size(); i++) {
			stringBuffer.append(i==0?"":",")
			.append("{\"lat\":").append(projectInfos.get(i).getCustomer_address_lat())
			.append(",\"lng\":").append(projectInfos.get(i).getCustomer_address_lng()).append("}");
		}
		stringBuffer.append("]})");
		
		new JsonUtils().writeJsonP(jsoncallback[0]+stringBuffer.toString());
	}
	public void commissionSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] commission_value = (String[])actionContext.getParameters().get("commission_value");
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		if ( !projectInfos.isEmpty() ) {
			ProjectInfo projectInfo = projectInfos.get(0);
			if ("unpaid".equals(projectInfo.getCommission_status())) {
				projectInfo.setCommission_status("paid");
				projectInfo.setCommission_value(new BigDecimal(commission_value[0]));
				baseDao.update(projectInfo);
			}
		}
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void rejectLeadsSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] finish_remark = (String[])actionContext.getParameters().get("finish_remark");
		String person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setFinish_date(todayCalendar.getTime());
		projectInfo.setFinish_person(person);
		projectInfo.setFinish_remark(finish_remark[0]);
		projectInfo.setProject_status("leads reject");
		baseDao.update(projectInfo);
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public String installDownloadBat(){
		return "installDownloadBatSuccess";
	}
	public InputStream getInstallDownloadBat(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		String hqlForQuoteRecord = "from QuoteRecord where project_id = ? and status='confirm'";
		String basePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String resultDirPath = basePath + "temp" + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + RandomStringUtils.randomAlphanumeric(10);
		File dir = new File(resultDirPath);
		dir.mkdirs();
		
		for (int i = 0; i < vid.length; i++) {
			List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[i]));
			ProjectInfo projectInfo = projectInfos.get(0);
			
			List<QuoteRecord> quoteRecords = baseDao.queryBySql(hqlForQuoteRecord, projectInfo.getProject_id());
			
			String excelTemplatePath = basePath + "resources"+File.separator+"file_templates"+File.separator+"installinfo_template.xlsx";
			String resultSharedStringFileName = basePath + "temp" + File.separator + RandomStringUtils.randomAlphanumeric(10) + ".xml";
			String resultExcelFilePath = resultDirPath+File.separator + projectInfo.getProject_id()+ "-" + projectInfo.getCustomer_name() + ".xlsx";
			
			Map<String, Object> map = new HashMap<>();
			map.put("projectInfo", projectInfo);
			map.put("quoteRecord", quoteRecords.get(0));
			
			OfficeFileGenerateUtil.generateFileBySharedString(resultExcelFilePath, resultSharedStringFileName, excelTemplatePath, basePath, map);
			
			FileDeleteUtil fileDeleteUtil = new FileDeleteUtil(resultSharedStringFileName);  
	        Thread t = new Thread(fileDeleteUtil);  
	        t.start();
		}
		
		
		try {
			OfficeFileGenerateUtil officeFileGenerateUtil = new OfficeFileGenerateUtil();
			officeFileGenerateUtil.compressedFile(resultDirPath, basePath + "temp");
//			officeFileGenerateUtil.compressedFile("G:\\resource", "G:\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(resultDirPath +".zip");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        FileDeleteUtil fileDeleteUtil = new FileDeleteUtil(resultDirPath +".zip");  
	        Thread t = new Thread(fileDeleteUtil);  
	        t.start();  
	        fileDeleteUtil = new FileDeleteUtil(resultDirPath);
	        t = new Thread(fileDeleteUtil);  
	        t.start();  
		}
		return inputStream;
	}
	public void stcConfirmSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setStc_status("complete");
		projectInfo.setStc_date(new Date());
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void revokeForSTC(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setStc_status(null);
		projectInfo.setStc_date(null);
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void dueConfirmSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setMoney_status("complete");
		projectInfo.setMoney_date(new Date());
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void revokeForDue(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setMoney_status(null);
		projectInfo.setMoney_date(null);
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void issueConfirmSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setIssue_status("complete");
		projectInfo.setIssue_date(new Date());
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void revokeForIssue(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setIssue_status(null);
		projectInfo.setIssue_date(null);
		
		baseDao.update(projectInfo);
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void projectCompleteSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] finish_remark = (String[])actionContext.getParameters().get("finish_remark");
		String person = (String) actionContext.getSession().get("name");
		Calendar todayCalendar = Calendar.getInstance();
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		projectInfo.setFinish_date(todayCalendar.getTime());
		projectInfo.setFinish_person(person);
		projectInfo.setFinish_remark(finish_remark[0]);
		projectInfo.setProject_status("complete");
		baseDao.update(projectInfo);
		
		new JsonUtils().writeJson("{\"success\":true}");
	}
	@SuppressWarnings("unchecked")
	public String additionalCommentInit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		
		String hqlForProjectInfo = "from ProjectInfo where vid = ?";
		List<ProjectInfo> projectInfos = baseDao.queryBySql(hqlForProjectInfo,Integer.parseInt(vid[0]));
		ProjectInfo projectInfo = projectInfos.get(0);
		
		String hqlForAdditionalComment = "from AdditionalComment where project_id = ? order by vid desc";
		List<AdditionalComment> additionalComments = baseDao.queryBySql(hqlForAdditionalComment, projectInfo.getProject_id());
		
		Map<String, Object> request = (Map<String, Object>)ActionContext.getContext().get("request");
		request.put("additional_comments", additionalComments);
		request.put("project_id", projectInfo.getProject_id());
		return "additionalCommentInitSuccess";
	}
	public void additionalCommentSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] project_id = (String[])actionContext.getParameters().get("project_id");
		String[] comment = (String[])actionContext.getParameters().get("comment");
		String person = (String) actionContext.getSession().get("name");
		
		AdditionalComment additionalComment = new AdditionalComment();
		additionalComment.setProject_id(project_id[0]);
		additionalComment.setComment_date(new Date());
		additionalComment.setComment_person(person);
		additionalComment.setComment(comment[0]);
		baseDao.save(additionalComment);
		
		new JsonUtils().writeJson("{\"success\":true,\"row\":{\"comment_date\":\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(additionalComment.getComment_date())+"\",\"comment_person\":\""+person+"\",\"comment\":\""+comment[0]+"\"}}");
	}
	
}

