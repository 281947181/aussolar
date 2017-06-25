<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ausuntech Solar Index</title>
<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jq_easyui.js"></script>
</head>
<body id="main_frame_layout" class="easyui-layout">
    <div id="header" data-options="region:'north'" style="height:30px;">
    	<form id="logoutForm" action="accountMag_logout.action" method="post">
    		<a id="logoutBtn" href="javascript:void(0)" class="easyui-linkbutton" style="float:right;" onclick="logout()">Logout</a>
    	</form>
    	<a id="changePwdBtn" href="javascript:void(0)" class="easyui-linkbutton" style="float:right;" onclick="changePwd()">Change Password</a>
    	<a id="accountMagBtn" href="javascript:void(0)" class="easyui-linkbutton" style="float:right;" onclick="accountMagInit()">Account Manage</a>
    	<!-- <a id="showMapBtn" href="javascript:void(0)" class="easyui-linkbutton" style="float:right;" onclick="projectMag_install_show_map()">Show Map</a> -->
    </div>
    <div id="center" data-options="region:'center'">
    	<table id="project_mag_main_table" class="easyui-datagrid" style="width:100%;height:100%;"
		        title="Project Overview" singleSelect="true" fitColumns="false" pagination="true" 
		        toolbar="#project_mag_main_table_toolbar">
		</table>
		<div id="project_mag_main_table_toolbar" data-options="selected:1" style="padding:5px;height:auto">
		    <!-- <div class="easyui-accordion" data-options="selected:1" style="margin-bottom:5px;">
		    	<div title="Filters" data-options="selected:false" style="padding:5px;"> -->
		    <div id="accordion_div" class="easyui-accordion" style="margin-bottom:5px;" data-options="selected:false,
		    	onUnselect:function(){
		    		$('#project_mag_main_table').datagrid('resize');
		    	},
		    	onSelect:function(){
		    		$('#project_mag_main_table').datagrid('resize');
		    	}
		    ">
		    	<div id="filter_accordion_div" title="Filters" data-options="selected:false" style="padding:5px;">
		    		<select class="easyui-combobox" id="project_mag_main_table_toolbar_form_statusCombo"data-options="width:200,label:'Job Status:',labelWidth:80,labelAlign:'right',editable:false,valueField:'value',textField:'text'">
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
					
					<select class="easyui-combobox" id="project_mag_main_table_toolbar_form_asignToCombo" data-options="width:200,label:'Sales Rep:',labelWidth:80,labelAlign:'right'">
		    			<option value=""></option>
		    			<s:iterator value="#request.sales_rep" id="sales_rep">
							<option value='<s:property value="#sales_rep"/>'><s:property value="#sales_rep"/></option>  
						</s:iterator>
					</select>
					
					<select class="easyui-combobox" id="project_mag_main_table_toolbar_form_installerCombo" data-options="width:200,label:'Installer:',labelWidth:80,labelAlign:'right'">
		    			<option value=""></option>
		    			<s:iterator value="#request.installer" id="installer">
							<option value='<s:property value="#installer"/>'><s:property value="#installer"/></option>  
						</s:iterator>
					</select>
					<select class="easyui-combobox" id="project_mag_main_table_toolbar_form_doorKnockerCombo" data-options="width:200,label:'Door Knocker:',labelWidth:90,labelAlign:'right'">
		    			<option value=""></option>
		    			<s:iterator value="#request.leads_knocker" id="leads_knocker">
							<option value='<s:property value="#leads_knocker"/>'><s:property value="#leads_knocker"/></option>  
						</s:iterator>
					</select>
					<select class="easyui-combobox" id="project_mag_main_table_toolbar_form_panelNameCombo" data-options="width:200,label:'Panel Name:',labelWidth:80,labelAlign:'right'">
		    			<option value=""></option>
		    			<s:iterator value="#request.panel_brand" id="panel_brand">
							<option value='<s:property value="#panel_brand"/>'><s:property value="#panel_brand"/></option>  
						</s:iterator>
					</select>
					<select class="easyui-combobox" id="project_mag_main_table_toolbar_form_inverterNameCombo" data-options="width:200,label:'Inverter Name:',labelWidth:100,labelAlign:'right'">
		    			<option value=""></option>
		    			<s:iterator value="#request.inverter_brand" id="inverter_brand">
							<option value='<s:property value="#inverter_brand"/>'><s:property value="#inverter_brand"/></option>  
						</s:iterator>
					</select>
					<br/>
					<input class="easyui-textbox" id="project_mag_main_table_toolbar_form_customerNameInput" data-options="width:250,label:'Customer Name:',labelWidth:110,labelAlign:'right'">
					<input class="easyui-textbox" id="project_mag_main_table_toolbar_form_customerPhoneInput" data-options="width:250,label:'Customer Phone:',labelWidth:110,labelAlign:'right'">
					<input class="easyui-textbox" id="project_mag_main_table_toolbar_form_postcodeInput" data-options="width:200,label:'Postcode:',labelWidth:80,labelAlign:'right'">
					<input class="easyui-textbox" id="project_mag_main_table_toolbar_form_subburbInput" data-options="width:200,label:'Suburb:',labelWidth:80,labelAlign:'right'">
					<input class="easyui-textbox" id="project_mag_main_table_toolbar_form_addressInput" data-options="width:300,label:'Addredd:',labelWidth:80,labelAlign:'right'">
					<br/>
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_createDateFromDatebox" data-options="width:230,label:'Create Date Between:',labelWidth:140,labelAlign:'right',formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_createDateToDatebox" data-options="width:90,formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_asignDateFromDatebox" data-options="width:230,label:'Asign Date Between:',labelWidth:140,labelAlign:'right',formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_asignDateToDatebox" data-options="width:90,formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_contractDateFromDatebox" data-options="width:280,label:'Contract Submit Date Between:',labelWidth:190,labelAlign:'right',formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_contractDateToDatebox" data-options="width:90,formatter:myformatter,parser:myparser,editable:false">
					<br/>
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_approvedDateFromDatebox" data-options="width:230,label:'Approved Date Between:',labelWidth:140,labelAlign:'right',formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_approvedDateToDatebox" data-options="width:90,formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_installDateFromDatebox" data-options="width:280,label:'Install Finish Date Between:',labelWidth:190,labelAlign:'right',formatter:myformatter,parser:myparser,editable:false">
					<input class="easyui-datebox" id="project_mag_main_table_toolbar_form_installDateToDatebox" data-options="width:90,formatter:myformatter,parser:myparser,editable:false">
					<a id="project_mag_main_table_toolbar_searchProjectBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="searchProject()" data-options="width:100">Search</a>
					<a id="project_mag_main_table_toolbar_showInMapBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="searchProjectAndShowInMap()" data-options="width:120">Show on Map</a>
				</div>
		    </div>
		    <div style="margin-bottom:5px">
		        <a id="project_mag_main_table_toolbar_addNewBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="projectMag_addNew()">New Project</a>
		        <a id="project_mag_main_table_toolbar_installDownloadBatBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="projectMag_installDownloadBat()">Download Selected Install Report </a>
		    </div>
		</div>
		<div id="project_mag_add_new_window"></div>
		<div id="project_mag_edit_leads_window"></div>
		<div id="project_mag_ready_to_quote_window"></div>
		<div id="project_mag_quote_detail_window"></div>
		<div id="project_mag_quote_confirm_window"></div>
		<div id="project_mag_install_show_map_window"></div>
		<div id="project_mag_ready_to_install_window"></div>
		<div id="project_mag_additional_comment_window"></div>
		<div id="account_mag_window"></div>
    </div>
<!--     <div id="footer" data-options="region:'south'" style="height:40px;">
--> <div id="footer">
		
    	<div id="map" style="visibility:hidden;"></div>
	   	<div id="change_pwd_window" class="easyui-window" title="Change Password" style="width:300px;height:160px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="change_pwd_form" method="post" style="margin-top:8px;">
		    			<input id="old_password" class="easyui-textbox" data-options="width:260,label:'Old password:',labelWidth:110,labelAlign:'right',required:true,type:'password'"><br/>
						<input id="new_password" class="easyui-textbox" data-options="width:260,label:'New password:',labelWidth:110,labelAlign:'right',required:true,type:'password'"><br/>
						<input id="re_password" class="easyui-textbox" data-options="width:260,label:'Confirm password:',labelWidth:110,labelAlign:'right',required:true,type:'password',validType:'equalTo[\'#new_password\']'">
						<input id="hidden_old_password" name="old_password" type="hidden"/>
						<input id="hidden_new_password" name="new_password" type="hidden"/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="changePasswordSubmit()">Submit</a>
			        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="changePasswordCancel()">Cancel</a>
			    </div>
		    </div>
	   	</div>
	   	<div id="asign_to_window" class="easyui-window" title="Asign To" style="width:300px;height:160px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="asign_to_form" method="post" style="margin-top:8px;">
		    			<input id="asign_to_form_projectIdInput" class="easyui-textbox" data-options="width:260,label:'Project ID:',labelWidth:90,labelAlign:'right',required:true,readonly:true"><br/>
		    			<input id="asign_to_form_customer" class="easyui-textbox" data-options="width:260,label:'Customer:',labelWidth:90,labelAlign:'right',required:true,readonly:true"><br/>
						<select class="easyui-combobox" name="asign_to" data-options="width:260,label:'Asign To:',labelWidth:90,labelAlign:'right',editable:false,required:true">
							<c:forEach var="name" items="${names}" varStatus="status"> 
								<option value="${name}">${name}</option>  
							</c:forEach> 
						</select>
						<input id="asign_to_form_vid" name="vid" type="hidden"/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_asignTo_submit()">Submit</a>
			        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_asignTo_cancel()">Cancel</a>
			    </div>
		    </div>
	   	</div>
	   	<div id="contact_window" class="easyui-window" title="Contact" style="width:300px;height:210px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="contact_form" method="post" style="margin-top:8px;">
		    			<input id="contact_form_projectIdInput" class="easyui-textbox" data-options="width:260,label:'Project ID:',labelWidth:90,labelAlign:'right',required:true,readonly:true"><br/>
		    			<input id="contact_form_contactCommentInput" name="contact_comment" class="easyui-textbox" data-options="width:260,height:100,label:'Comment:',labelWidth:90,labelAlign:'right',required:true,multiline:true"><br/>
						<input id="contact_form_vid" name="vid" type="hidden"/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contact_submit()">Submit</a>
			        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contact_cancel()">Cancel</a>
			    </div>
		    </div>
	   	</div>
	   	<div id="additional_comment_window" class="easyui-window" title="Comment" style="width:300px;height:210px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="additional_comment_form" method="post" style="margin-top:8px;">
		    			<input id="additional_comment_form_projectIdInput" name="project_id" class="easyui-textbox" data-options="width:260,label:'Project ID:',labelWidth:90,labelAlign:'right',required:true,readonly:true"><br/>
		    			<input id="additional_comment_form_commentInput" name="comment" class="easyui-textbox" data-options="width:260,height:100,label:'Comment:',labelWidth:90,labelAlign:'right',required:true,multiline:true"><br/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_add_new_additional_comment_submit()">Submit</a>
			    </div>
		    </div>
	   	</div>
	   	<div id="commission_window" class="easyui-window" title="Commission" style="width:300px;height:150px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="commission_form" method="post" style="margin-top:8px;">
		    			<input id="commission_form_projectIdInput" class="easyui-textbox" data-options="width:260,label:'Project ID:',labelWidth:90,labelAlign:'right',required:true,readonly:true"><br/>
		    			<input id="commission_form_commissionValueInput" name="commission_value" class="easyui-numberspinner" data-options="width:260,label:'Commission:',labelWidth:90,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true"><br/>
						<input id="commission_form_vid" name="vid" type="hidden"/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_commission_submit()">Submit</a>
			        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_commission_cancel()">Cancel</a>
			    </div>
		    </div>
	   	</div>
	   	<div id="rejectLeads_window" class="easyui-window" title="Reject Leads" style="width:300px;height:210px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="rejectLeads_form" method="post" style="margin-top:8px;">
		    			<input id="rejectLeads_form_projectIdInput" class="easyui-textbox" data-options="width:260,label:'Project ID:',labelWidth:90,labelAlign:'right',required:true,readonly:true"><br/>
		    			<input id="rejectLeads_form_finishRemarkInput" name="finish_remark" class="easyui-textbox" data-options="width:260,height:100,label:'Reason:',labelWidth:90,labelAlign:'right',required:true,multiline:true"><br/>
						<input id="rejectLeads_form_vid" name="vid" type="hidden"/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_rejectLeads_submit()">Submit</a>
			        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_rejectLeads_cancel()">Cancel</a>
			    </div>
		    </div>
	   	</div>
	   	<div id="cancel_window" class="easyui-window" title="Cancel Project" style="width:300px;height:210px;"
	       	data-options="modal:true,closed:true,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	       	<div class="easyui-layout" fit="true">
	   			<div region="center">
		    		<form id="cancel_form" method="post" style="margin-top:8px;">
		    			<input id="cancel_form_projectIdInput" class="easyui-textbox" data-options="width:260,label:'Project ID:',labelWidth:100,labelAlign:'right',required:true,readonly:true"><br/>
		    			<input id="cancel_form_cancelReasonInput" name="cancel_reason" class="easyui-textbox" data-options="width:260,height:100,label:'Cancel Reason:',labelWidth:100,labelAlign:'right',required:true,multiline:true"><br/>
						<input id="cancel_form_vid" name="vid" type="hidden"/>
		    		</form>
	    		</div>
			    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
			        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_cancel_project_submit()">Confirm Cancel</a>
			    </div>
		    </div>
	   	</div>
	   	<input id="hidden_roleInput" type="hidden" value="${sessionScope.role}"/>
	   	<form id="empty_form" method="post" style="visibility: hidden;"></form>
    </div>
</body>
<script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="resources/js/index.js"></script>
<!-- <script type="text/javascript" src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTwj5RaQzi___RzieB9vxR6L0VYkIxL-E"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTwj5RaQzi___RzieB9vxR6L0VYkIxL-E"></script> -->
 </html>