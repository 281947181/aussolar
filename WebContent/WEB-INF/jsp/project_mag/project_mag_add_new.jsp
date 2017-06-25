<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" fit="true">
	<div class="easyui-layout" data-options="region:'west',width:330" style="border:none;">
	    <div region="center">
	    	<form id="projectMag_addNew_form" method="post" style="margin-top:7px;">
	    		<input type="hidden" id="projectMag_addNew_form_customerAddressLatInput" name="customer_address_lat"/>
	    		<input type="hidden" id="projectMag_addNew_form_customerAddressLngInput" name="customer_address_lng"/>
				<input class="easyui-textbox" id="projectMag_addNew_form_customerNameInput" name="customer_name" data-options="width:300,label:'Customer Name:',labelWidth:100,labelAlign:'right',required:true">
				<input class="easyui-textbox" id="projectMag_addNew_form_customerPhoneInput" name="customer_phone" data-options="width:300,label:'Phone:',labelWidth:100,labelAlign:'right',required:true">
				<input class="easyui-textbox" id="projectMag_addNew_form_customerSubburbInput" name="customer_subburb" data-options="width:300,label:'Suburb:',labelWidth:100,labelAlign:'right',required:true">
				<input class="easyui-textbox" id="projectMag_addNew_form_customerAddressInput" name="customer_address" data-options="width:300,label:'Address:',labelWidth:100,labelAlign:'right',required:true,buttonText:'check',
					onClickButton:function(){
						var value = $.trim($(this).textbox('getValue'));
						deleteMarkers();
						projectMag_addNew_codeAddressByAddress(value);
					}
				">
				<input class="easyui-textbox" id="projectMag_addNew_form_customerPostcodeInput" name="customer_postcode" data-options="width:300,label:'Postcode:',labelWidth:100,labelAlign:'right',required:true">
				<input class="easyui-textbox" id="projectMag_addNew_form_customerStateInput" name="customer_state" data-options="width:300,label:'State:',labelWidth:100,labelAlign:'right',required:true">
				<input class="easyui-textbox" id="projectMag_addNew_form_customerEmailInput" name="customer_email" data-options="width:300,label:'Email:',labelWidth:100,labelAlign:'right',validType:'email'">
				<select class="easyui-combobox" id="projectMag_addNew_form_leadsTypeCombobox" name="leads_type" data-options="width:300,label:'Leads Type:',labelWidth:100,labelAlign:'right',editable:false,required:true">
				    <option value="Residential">Residential</option>
				    <option value="Commercial">Commercial</option>
				    <option value="Off Grid">Off Grid</option>
				</select>
				<select class="easyui-combobox" id="projectMag_addNew_form_leadsFromCombobox" name="leads_from" data-options="width:300,label:'Leads From:',labelWidth:100,labelAlign:'right',editable:false,required:true,
					onChange:function(newValue,oldValue){
						if(newValue!='Door to door')
							$('#projectMag_addNew_form_leadsKnockerCombobox').combobox('disable');
						else
							$('#projectMag_addNew_form_leadsKnockerCombobox').combobox('enable');
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
				<select class="easyui-combobox" id="projectMag_addNew_form_leadsKnockerCombobox" name="leads_knocker" data-options="width:300,label:'Door Knocker:',labelWidth:100,labelAlign:'right',editable:false,required:true">
					<c:forEach var="name" items="${names}" varStatus="status"> 
						<option value="${name}" <c:if test="${name==sessionScope.name}">selected="selected"</c:if>>${name}</option>  
					</c:forEach> 
				</select>
				<input class="easyui-textbox" id="projectMag_addNew_form_leadsCommentInput" name="leads_comment" data-options="width:300,label:'Comment:',labelWidth:100,labelAlign:'right',multiline:true,height:100">
				
			</form>
	    </div>
	    <div region="south" border="false" style="text-align:right;height:30px;line-height:30px;">
	        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_addNew_submit()">Submit</a>
	        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_addNew_cancel()">Cancel</a>
	    </div>
    </div>
    <div id="projectMag_addNew_map" data-options="region:'center'">
    </div>
</div>
