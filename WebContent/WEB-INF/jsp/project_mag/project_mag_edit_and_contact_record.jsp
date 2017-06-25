<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" fit="true">
	<div data-options="region:'center'">
	    <div region="center">
	    	<form id="projectMag_edit_leads_form" method="post" style="margin-top:7px;" data-options="ajax:true">
				<input name="vid" type="hidden" value="${projectInfo.vid}">
				<input type="hidden" id="projectMag_edit_leads_form_customerAddressLatInput" name="customer_address_lat" value="${projectInfo.customer_address_lat}"/>
	    		<input type="hidden" id="projectMag_edit_leads_form_customerAddressLngInput" name="customer_address_lng" value="${projectInfo.customer_address_lng}"/>
				<input class="easyui-textbox" id="projectMag_edit_leads_form_projectIdInput" data-options="width:300,label:'Project ID:',labelWidth:100,labelAlign:'right',required:true,readonly:true,value:'${projectInfo.project_id}'">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerNameInput" name="customer_name" data-options="width:300,label:'Customer Name:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_name}'">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerPhoneInput" name="customer_phone" data-options="width:300,label:'Phone:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_phone}'">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerSubburbInput" name="customer_subburb" data-options="width:300,label:'Suburb:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_subburb}'">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerAddressInput" name="customer_address" data-options="width:300,label:'Address:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_address}',buttonText:'check',
					onClickButton:function(){
						var value = $.trim($(this).textbox('getValue'));
						projectMag_edit_leads_codeAddressByAddress(value);
					}
				">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerPostcodeInput" name="customer_postcode" data-options="width:300,label:'Postcode:',labelWidth:100,labelAlign:'right',required:true,readonly:true,value:'${projectInfo.customer_postcode}'">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerStateInput" name="customer_state" data-options="width:300,label:'State:',labelWidth:100,labelAlign:'right',required:true,readonly:true,value:'${projectInfo.customer_state}'">
				<input class="easyui-textbox" id="projectMag_edit_leads_form_customerEmailInput" name="customer_email" data-options="width:300,label:'Email:',labelWidth:100,labelAlign:'right',validType:'email',value:'${projectInfo.customer_email}'">
				<select class="easyui-combobox" id="projectMag_edit_leads_form_leadsTypeCombobox" name="leads_type" data-options="width:300,label:'Leads Type:',labelWidth:100,labelAlign:'right',editable:false,required:true,value:'${projectInfo.leads_type}'">
				    <option value="Residential">Residential</option>
				    <option value="Commercial">Commercial</option>
				    <option value="Off Grid">Off Grid</option>
				</select>
				<select class="easyui-combobox" id="projectMag_edit_leads_form_leadsFromCombobox" name="leads_from" data-options="width:300,label:'Leads From:',labelWidth:100,labelAlign:'right',editable:false,required:true,value:'${projectInfo.leads_from}',
					onChange:function(newValue,oldValue){
						if(newValue!='Door to door'){
							$('#projectMag_edit_leads_form_leadsKnockerCombobox').combobox('setValue','');
							$('#projectMag_edit_leads_form_leadsKnockerCombobox').combobox('disable');
						}
						else
							$('#projectMag_edit_leads_form_leadsKnockerCombobox').combobox('enable');
					}">
				    <option value="Door to door">Door to door</option>
				    <option value="Online shared">Online shared</option>
				    <option value="Google ads">Google ads</option>
				    <option value="Newspaper">Newspaper</option>
				    <option value="Flyers">Flyers</option>
				    <option value="Social network">Social network</option>
				    <option value="Referral">Referral</option>
				    <option value="Call center">Call center</option>
				    <option value="Radio">Radio</option>
				    <option value="TVs">TVs</option>
				    <option value="Event">Event</option>
				    <option value="Shopping center">Shopping center</option>
				    <option value="Others">Others</option>
				</select>
				<select class="easyui-combobox" id="projectMag_edit_leads_form_leadsKnockerCombobox" name="leads_knocker" data-options="width:300,label:'Door Knocker:',labelWidth:100,labelAlign:'right',editable:false,required:true,value:'${projectInfo.leads_knocker}'<s:if test="#request.projectInfo.leads_from!='Door to door'">,disabled:true</s:if>">
					<s:iterator value="#request.names" id="name">
						<option value="<s:property value="#name"/>" <s:if test="#name==#session.name">selected="selected"</s:if>><s:property value="#name"/></option>  
					</s:iterator>
				</select>
				<input class="easyui-textbox" id="projectMag_edit_leads_form_leadsCommentInput" name="leads_comment" data-options="width:300,label:'Comment:',labelWidth:100,labelAlign:'right',multiline:true,height:100,value:'${projectInfo.leads_comment}'">
				<s:if test="#request.modified"><p style="color:red;text-align:center;">Modification to be comfirmed.</p></s:if>
			</form>
	    </div>
	    <div region="south" style="text-align:center;height:30px;line-height:30px;">
	    	<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && #request.contractConfirmInfo.quote_info_confirm!="confirm"'>
	    		<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_edit_leads_confirm()">Submit</a>
	    	</s:if>
	    </div>
    </div>
    <div data-options="region:'east'" style="width:480px;">
    	<table id="project_mag_contact_record_table" class="easyui-datagrid" style="width:100%;height:100%;"
		        data-options="title:'Contact Records',singleSelect:true,fitColumns:true,nowrap:false,
		        	rowStyler: function(index,row){
						return 'height:70px;'; // return inline style
					}
		        ">
			<thead>
		        <tr>
		            <th data-options="field:'contact_date',width:100,align:'center'">Date</th>
		            <th data-options="field:'contact_person',width:140,align:'center'">Contact Person</th>
		            <th data-options="field:'contact_comment',width:240,align:'center'">Comment</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<s:iterator value="#request.contact_records" id="record">
		    		<tr>
		    			<td><s:property value="#record.contact_date"/></td>
		    			<td><s:property value="#record.contact_person"/></td>
		    			<td><s:property value="#record.contact_comment"/></td>
		    		</tr>
				</s:iterator>
		    </tbody>
		</table>
    </div>
</div>
