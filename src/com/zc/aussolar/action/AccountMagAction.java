package com.zc.aussolar.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zc.aussolar.bean.UserInfo;
import com.zc.aussolar.dao.BaseDao;
import com.zc.aussolar.util.JsonUtils;
import com.zc.aussolar.util.SHAUtils;

public class AccountMagAction extends ActionSupport {
	private static final long serialVersionUID = -2296518018029301420L;
	private BaseDao baseDao;
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String login(){
		ActionContext actionContext = ActionContext.getContext();
		String[] username = (String[])actionContext.getParameters().get("username");
		String[] password = (String[])actionContext.getParameters().get("password");
		if ( username == null || password == null ) {
			Map<String,String> request = (Map<String,String>) actionContext.get("request"); 
			request.put("errorMsg", "Please login first!");
			return "loginFail";
		}
		String hqlForUserInfo = "from UserInfo where username = ?";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo, username[0]);
		if ( userInfos.isEmpty() ) {
			Map<String,String> request = (Map<String,String>) actionContext.get("request"); 
			request.put("errorMsg", "Wrong username or password!");
			return "loginFail";
		}
		else{
			String inputPwd = SHAUtils.SHA256(userInfos.get(0).getSalt()+password[0]);
			if ( inputPwd.equals(userInfos.get(0).getPassword()) ){
				actionContext.getSession().put("name", userInfos.get(0).getReal_name());
				actionContext.getSession().put("username", userInfos.get(0).getUsername());
				actionContext.getSession().put("role", userInfos.get(0).getRole());
				String hqlForUserInfoAsignToName = "from UserInfo where role = 'sales'";
				List<UserInfo> userInfosNames = baseDao.queryBySql(hqlForUserInfoAsignToName);
				List<String> names = new ArrayList<>();
				for (int i = 0; i < userInfosNames.size(); i++) {
					names.add(userInfosNames.get(i).getReal_name());
				}
//				Map<String, List<String>> request = (Map<String, List<String>>)ActionContext.getContext().get("request");
				Map request = (Map)ActionContext.getContext().get("request");
				request.put("names", names);
				
//				String hqlForJobStatus = "select project_status from ProjectInfo group by project_status";
//				List<String> jobStatus = baseDao.queryBySql(hqlForJobStatus);
//				request.put("job_status", jobStatus);
				
				String hqlForSalesRep = "select asign_to from ProjectInfo group by asign_to";
				List<String> salesRep = baseDao.queryBySql(hqlForSalesRep);
				request.put("sales_rep", salesRep);
				
				String hqlForInstaller = "select install_person from ProjectInfo group by install_person";
				List<String> installer = baseDao.queryBySql(hqlForInstaller);
				request.put("installer", installer);
				
				String hqlForDoorKnocker = "select leads_knocker from ProjectInfo group by leads_knocker";
				List<String> leads_knocker = baseDao.queryBySql(hqlForDoorKnocker);
				request.put("leads_knocker", leads_knocker);
				
				String hqlForPanelBrand = "select panel_brand from QuoteRecord group by panel_brand";
				List<String> panel_brand = baseDao.queryBySql(hqlForPanelBrand);
				request.put("panel_brand", panel_brand);
				
				String hqlForInverterBrand = "select inverter_brand from QuoteRecord group by inverter_brand";
				List<String> inverter_brand = baseDao.queryBySql(hqlForInverterBrand);
				request.put("inverter_brand", inverter_brand);
				return "loginSuccess";
			}
			else{
				Map<String,String> request = (Map<String,String>) actionContext.get("request"); 
				request.put("errorMsg", "Wrong username or password!");
				return "loginFail";
			}
		}
	}
	public String logout(){
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getSession().remove("name");
		actionContext.getSession().remove("username");
		actionContext.getSession().remove("role");
		return "logoutSuccess";
	}
	public void getNavi(){
		//根据权限生成导航菜单内容
		new JsonUtils().writeJson("[{\"id\":0,\"text\":\"用户管理\"},{\"id\":1,\"text\":\"leads\"},{\"id\":2,\"text\":\"contract\"},{\"id\":3,\"text\":\"paper work\"},{\"id\":4,\"text\":\"installation\"},{\"id\":5,\"text\":\"归档\"}]");
	}
	public String accountMagInit(){
		return "accountMagInitSuccess";
	}
	public void queryUserInfo(){
		//需添加权限检查
		ActionContext actionContext = ActionContext.getContext();
		String[] name = (String[])actionContext.getParameters().get("name");
		String[] role = (String[])actionContext.getParameters().get("role");
		String[] page = (String[])actionContext.getParameters().get("page");
		String[] rows = (String[])actionContext.getParameters().get("rows");
		String[] sort = (String[])actionContext.getParameters().get("sort");
		String[] order = (String[])actionContext.getParameters().get("order");
		int pageInt = Integer.parseInt(page[0]);
		int rowsInt = Integer.parseInt(rows[0]);
		
		String hqlForUserInfo = "from UserInfo where 1 = 1";
		List<String> params = new ArrayList<>();
		if ( name != null ) {
			hqlForUserInfo += " and ( username like ? or real_name like ? )";
			params.add("%"+name[0]+"%");
			params.add("%"+name[0]+"%");
		}
		if ( role != null && !"all".equals(role[0]) ) {
			hqlForUserInfo += " and role = ?";
			params.add(role[0]);
		}
		if ( sort != null ) {
			String[] sortArray = sort[0].split(",");
			String[] orderArray = order[0].split(",");
			hqlForUserInfo += " order by ";
			for (int i = 0; i < sortArray.length; i++) {
				hqlForUserInfo += ((i==0?"":", ") + sortArray[i] + " " + orderArray[i]);
			}
		}
		long total = baseDao.queryTotalCount("select count(vid) "+hqlForUserInfo, params.toArray());
		List<UserInfo> userInfos = baseDao.queryBySqlLimit(hqlForUserInfo, params.toArray(),(pageInt-1)*rowsInt,rowsInt);
		StringBuffer stringBuffer = new StringBuffer("{\"total\":").append(total).append(",\"rows\":[");
		for (int i = 0; i < userInfos.size(); i++) {
			stringBuffer.append(i==0?"":",")
			.append(userInfos.get(i).toJsonString());
		}
		stringBuffer.append("]}");
		new JsonUtils().writeJson(stringBuffer.toString());
	}
	public String generateUserInfoEditor(){
		return "generateUserInfoEditorSuccess";
	}
	public void saveUserInfo(){
		ActionContext actionContext = ActionContext.getContext();
		String[] real_name = (String[])actionContext.getParameters().get("real_name");
		String[] username = (String[])actionContext.getParameters().get("username");
		String[] role = (String[])actionContext.getParameters().get("role");
		String hqlForUserInfo = "from UserInfo where username = ?";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo, username[0].trim());
		if ( userInfos.isEmpty() ) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(username[0]);
			userInfo.setRole(role[0]);
			userInfo.setReal_name(real_name[0]);
			userInfo.setSalt(SHAUtils.genSalt());
			userInfo.setPassword(SHAUtils.genDefaultPwd(userInfo.getSalt()));
			baseDao.save(userInfo);
			new JsonUtils().writeJson("{\"success\":true,\"userInfo\":"+userInfo.toJsonString().toString()+"}");
		}
		else{
			new JsonUtils().writeJson("{\"success\":false,\"msg\":\"Username already exists!\"}");
		}
		
	}
	public void removeUserInfo(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String hqlForUserInfo = "from UserInfo where vid = ?";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo, Integer.parseInt(vid[0]));
		if ( !userInfos.isEmpty() ) {
			baseDao.delete(userInfos.get(0));
		}
		new JsonUtils().writeJson("{\"success\":true}");
	}
	public void updateUserInfo(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String[] real_name = (String[])actionContext.getParameters().get("real_name");
		String[] username = (String[])actionContext.getParameters().get("username");
		String[] role = (String[])actionContext.getParameters().get("role");
		String hqlForUserInfo = "from UserInfo where vid = ?";
		String hqlForUserInfo2 = "from UserInfo where username = ?";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo, Integer.parseInt(vid[0]));
		if ( !userInfos.isEmpty() ) {
			List<UserInfo> userInfos2 = baseDao.queryBySql(hqlForUserInfo2,username[0]);
			if ( userInfos2.isEmpty() ) {
				UserInfo userInfo = userInfos.get(0);
				userInfo.setUsername(username[0]);
				userInfo.setReal_name(real_name[0]);
				userInfo.setRole(role[0]);
				baseDao.update(userInfo);
				new JsonUtils().writeJson("{\"success\":true,\"userInfo\":"+userInfo.toJsonString().toString()+"}");
			}
			else{
				new JsonUtils().writeJson("{\"success\":false,\"msg\":\"Username already exists!\"}");
			}
		}
		else{
			new JsonUtils().writeJson("{\"success\":false,\"msg\":\"No such user!\"}");
		}
	}
	public void resetPassword(){
		ActionContext actionContext = ActionContext.getContext();
		String[] vid = (String[])actionContext.getParameters().get("vid");
		String hqlForUserInfo = "from UserInfo where vid = ?";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo, Integer.parseInt(vid[0]));
		if ( !userInfos.isEmpty() ) {
			userInfos.get(0).setSalt(SHAUtils.genSalt());
			userInfos.get(0).setPassword(SHAUtils.genDefaultPwd(userInfos.get(0).getSalt()));
			baseDao.update(userInfos.get(0));
		}
		new JsonUtils().writeJson("{\"success\":true,\"msg\":\"Password has been changed to '123456789'!\"}");
	}
	public void changePasswordSubmit(){
		ActionContext actionContext = ActionContext.getContext();
		String[] old_password = (String[])actionContext.getParameters().get("old_password");
		String[] new_password = (String[])actionContext.getParameters().get("new_password");
		String username = (String) actionContext.getSession().get("username");
		String hqlForUserInfo = "from UserInfo where username = ?";
		List<UserInfo> userInfos = baseDao.queryBySql(hqlForUserInfo, username);
		if ( userInfos.isEmpty() ) {
			new JsonUtils().writeJson("{\"success\":false,\"msg\":\"No such user!\"}");
		}
		else{
			String inputPwd = SHAUtils.SHA256(userInfos.get(0).getSalt()+old_password[0]);
			if ( inputPwd.equals(userInfos.get(0).getPassword()) ){
				userInfos.get(0).setSalt(SHAUtils.genSalt());
				userInfos.get(0).setPassword(SHAUtils.SHA256(userInfos.get(0).getSalt()+new_password[0]));
				baseDao.update(userInfos.get(0));
				new JsonUtils().writeJson("{\"success\":true}");
			}
			else{
				new JsonUtils().writeJson("{\"success\":false,\"msg\":\"Wrong old password!\"}");
			}
		}
	}
}
