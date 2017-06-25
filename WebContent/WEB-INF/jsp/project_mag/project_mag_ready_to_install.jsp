<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" fit="true">
	    <div region="center">
	    	<form id="projectMag_ready_to_install_form" method="post" style="margin-top:7px;">
	    		<input type="hidden" name="vid" value="${projectInfo.vid}"/>
	    		<input class="easyui-textbox" data-options="width:350,label:'Project ID:',labelWidth:130,labelAlign:'right',readonly:true,value:'${projectInfo.project_id}'"><br/><br/>
	    		<input class="easyui-textbox" data-options="width:350,label:'WP Stauts:',labelWidth:130,labelAlign:'right',readonly:true,value:'${projectInfo.wp_status}'"><br/><br/>
				<input class="easyui-datebox" id="projectMag_ready_to_install_form_toInstallDateInput" name="to_install_date" data-options="width:350,label:'To install date:',labelWidth:130,labelAlign:'right',required:true,formatter:myformatter,parser:myparser,panelWidth:220,editable:false"><br/><br/>
				<input class="easyui-textbox" id="projectMag_to_install_form_installerInput" name="install_person" data-options="width:350,label:'Installer:',labelWidth:130,labelAlign:'right',required:true"><br/><br/>
				<input class="easyui-textbox" id="projectMag_to_install_form_installCommentInput" name="install_comment" data-options="width:350,label:'Install Comment:',labelWidth:130,labelAlign:'right',multiline:true,height:100"><br/><br/>
				<input class="easyui-textbox" data-options="width:350,label:'Comment by Sales:',labelWidth:130,labelAlign:'right',multiline:true,height:100,readonly:true,value:'${quoteRecord.install_comment}'">
			</form>
	    </div>
	    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
	        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_ready_to_install_submit()">Submit</a>
	        <!-- <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_ready_to_install_cancel()">Cancel</a> -->
	    </div>
    </div>
</div>
