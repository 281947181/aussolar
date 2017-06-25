package com.zc.aussolar.bean;

import java.util.HashMap;
import java.util.Map;

public class ProjectStatusInfo {
	static public Integer LEADS_REJECT = -2;
	static public Integer CANCELED = -1;
	static public Integer TO_ASIGN = 0;
	static public Integer TO_CONTACT = 1;
	static public Integer TO_QUOTE = 2;
	static public Integer TO_CONFIRM = 3;
	static public Integer TO_CHECK = 4;
	static public Integer TO_INSTALL = 5;
	static public Integer INSTALLATION_ARRANGED = 6;
	static public Integer TO_FINISH = 7;
	static public Integer COMPLETE = 8;
	private Map<String, Integer> statusMap;
	public Map<String, Integer> getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(Map<String, Integer> statusMap) {
		this.statusMap = statusMap;
	}
	public ProjectStatusInfo() {
		statusMap = new HashMap<>();
		statusMap.put("to asign", 0);
		statusMap.put("to contact", 1);
		statusMap.put("to quote", 2);
		statusMap.put("to confirm", 3);
		statusMap.put("to check", 4);
		statusMap.put("to install", 5);
		statusMap.put("installation arranged", 6);
		statusMap.put("to finish", 7);
		statusMap.put("complete", 8);
		statusMap.put("canceled", -1);
		statusMap.put("leads reject", -2);
	}
}
