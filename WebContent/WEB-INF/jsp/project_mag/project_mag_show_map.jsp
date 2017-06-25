<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="easyui-layout" fit="true">
    <div data-options="region:'center'">
    	<table id="project_mag_to_install_table" class="easyui-datagrid" style="width:100%;height:100%;"
    			data-options="singleSelect:true,fitColumns:true,nowrap:false,pagination:true">
		        <!-- data-options="singleSelect:true,fitColumns:true,nowrap:false,pagination:true,toolbar:'#project_mag_to_install_table_toolbar'"> -->
		</table>
		<%-- <div id="project_mag_to_install_table_toolbar" style="padding:5px;height:auto">
			<select class="easyui-combobox" id="project_mag_to_install_table_toolbar_statusCombo"data-options="width:300,label:'Job Status:',labelWidth:80,labelAlign:'right',editable:false,valueField:'value',textField:'text'">
    			<option value="all">ALL</option>
    			<option value="to asign">TO ASIGN</option>
    			<option value="to contact">TO CONTACT</option>
    			<option value="to quote">TO QUOTE</option>
    			<option value="to confirm">TO CONFIRM</option>
    			<option value="to check">TO CHECK</option>
    			<option value="to install">TO INSTALL</option>
    			<option value="installation arranged">INSTALLATION ARRANGED</option>
    			<option value="to finish">TO FINISH</option>
    			<option value="complete">COMPLETE</option>
    			<option value="canceled">CANCELED</option>
    			<option value="leads reject">LEADS REJECT</option>
			</select>
			<a id="project_mag_to_install_table_toolbar_searchProjectBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="searchProjectForMap()" data-options="width:100">Search</a>
		</div> --%>
    </div>
    <div id="project_mag_to_install_map" data-options="region:'east',width:'50%'">
    </div>
</div>
