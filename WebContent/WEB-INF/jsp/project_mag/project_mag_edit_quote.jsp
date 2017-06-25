<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" fit="true">
	<form id="projectMag_edit_quote_form" method="post">
	<input type="hidden" id="project_vid" name="vid" value="${projectInfo.vid}"/>
	<input id="projectMag_edit_quote_changedHidden" type="hidden" name="changedField" value=""/>
	<input id="projectMag_edit_quote_changedFieldIdHidden" type="hidden" value=""/>
	<input type="hidden" id="projectMag_edit_quote_customerAddressLatInput" name="customer_address_lat" value="${projectInfo.customer_address_lat}"/>
 	<input type="hidden" id="projectMag_edit_quote_customerAddressLngInput" name="customer_address_lng" value="${projectInfo.customer_address_lng}"/>
	<div data-options="region:'north',height:120,title:'Basic Info ( Project ID: ${projectInfo.project_id} )',collapsible:false">
		<table style="width:100%;">
			<tr>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_edit_quote_customerNameInput" name="customer_name" data-options="width:'90%',label:'Customer Name:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_name}',
						onChange: function(newValue,oldValue){
							projectMag_editQuote_isChanged(newValue,oldValue,this);
						}">
				</td>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_edit_quote_customerPhoneInput" name="customer_phone" data-options="width:'90%',label:'Customer Phone:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_phone}',
						onChange: function(newValue,oldValue){
							projectMag_editQuote_isChanged(newValue,oldValue,this);
						}">
				</td>
				<td><input class="easyui-textbox" id="projectMag_edit_quote_customerSubburbInput" name="customer_subburb" data-options="width:'90%',label:'Suburb:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_subburb}',
					onChange: function(newValue,oldValue){
						projectMag_editQuote_isChanged(newValue,oldValue,this);
					}">
				</td>
			</tr>
			<tr>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_edit_quote_customerAddressInput" name="customer_address" data-options="width:'90%',label:'Address:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_address}',buttonText:'check',
						onChange: function(newValue,oldValue){
							projectMag_editQuote_isChanged(newValue,oldValue,this);
						},
						onClickButton:function(){
							var value = $.trim($(this).textbox('getValue'));
							projectMag_edit_quote_codeAddressByAddress(value);
						}">
				</td>
				<td><input class="easyui-textbox" id="projectMag_edit_quote_customerPostcodeInput" name="customer_postcode" data-options="width:'90%',label:'Postcode:',labelWidth:100,labelAlign:'right',readonly:true,required:true,value:'${projectInfo.customer_postcode}',
					onChange: function(newValue,oldValue){
						projectMag_editQuote_isChanged(newValue,oldValue,this);
					}">
				</td>
				<td><input class="easyui-textbox" id="projectMag_edit_quote_customerStateInput" name="customer_state" data-options="width:'90%',label:'State:',labelWidth:100,labelAlign:'right',readonly:true,required:true,value:'${projectInfo.customer_state}',
					onChange: function(newValue,oldValue){
						projectMag_editQuote_isChanged(newValue,oldValue,this);
					}">
				</td>
			</tr>
			<tr>
				<td>
					<select class="easyui-combobox" id="projectMag_edit_quote_roofTypeCombobox" name="customer_roof_type" data-options="width:'60%',label:'Roof Type:',labelWidth:100,labelAlign:'right',editable:false,required:true,value:'<s:if test="#request.customerRoofOther">Other</s:if><s:else>${projectInfo.customer_roof_type}</s:else>',
						onChange:function(newValue,oldValue){
							if( $.trim(newValue) == 'Other' )
								$('#projectMag_edit_quote_roofTypeInput').textbox('enable');
							else
								$('#projectMag_edit_quote_roofTypeInput').textbox('disable');
						}">
					    <option value=""></option>
					    <option value="Tin">Tin</option>
					    <option value="Tile">Tile</option>
					    <option value="Other">Other</option>
					</select>
					<input class="easyui-textbox" id="projectMag_edit_quote_roofTypeInput" name="customer_roof_type" data-options="width:'29%',required:true,<s:if test="#request.customerRoofOther">value:'${customerRoofOtherValue}'</s:if><s:else>disabled:true</s:else>">
				</td>
				<td>
					<select class="easyui-combobox" id="projectMag_edit_quote_storeyCombobox" name="customer_storey" data-options="width:'90%',label:'Storey:',labelWidth:100,labelAlign:'right',editable:false,required:true,value:'${projectInfo.customer_storey}'">
						<option value=""></option>
					    <option value="Single">Single</option>
					    <option value="Double">Double</option>
					    <option value="2+">2+</option>
					</select>
				</td>
				<td>
					<select class="easyui-combobox" id="projectMag_edit_quote_phaseTypeCombobox" name="customer_phase_type" data-options="width:'90%',label:'Phase Type:',labelWidth:100,labelAlign:'right',editable:false,required:true,value:'${projectInfo.customer_phase_type}'">
						<option value=""></option>
					    <option value="Single Phase">Single Phase</option>
					    <option value="Dual Phase">Dual Phase</option>
					    <option value="Three Phase">Three Phase</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',collapsible:false">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div <s:if test="#request.quoteRecord1.status=='to confirm' || #request.quoteRecord1.status=='confirm'">class="datagrid-row-selected"</s:if> data-options="region:'west',width:'33.3%',
	    		onOpen:function(){
	    			$(this).find('.easyui-textbox').textbox('disableValidation');
	    			$(this).find('.easyui-numberspinner').numberspinner('disableValidation');
	    			$(this).find('.easyui-combobox').combobox('disableValidation');
	    		}
	    	">
				<div style="height:80px;font-size:30px;line-height:80px;text-align:center;">
					<input class="easyui-switchbutton" id="projectMag_edit_quote_selectBtn1" name="select_to_sign" data-options="width:150,onText:'Selected to sign',offText:'Unselected',value:'${quoteRecord1.vid}'<s:if test='#request.quoteRecord1.status=="to confirm"||#request.quoteRecord1.status=="confirm"'>,checked:true</s:if>,
						onChange:function(checked){
							projectMag_edit_select_button_change(this,checked);
						}
					"/>
					Quote Ⅰ
				</div>
				<div>
					<input type="hidden" id="projectMag_edit_quote_quoteRecordVid1" name="quote_record_vid" value="${quoteRecord1.vid}"/>
					<input class="easyui-textbox" id="projectMag_edit_quote_panelBrandInput1" name="panel_brand" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord1.panel_brand}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_panelSizeInput1" name="panel_size" data-options="width:'88%',label:'Panel Size:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,precision:2,decimalSeparator:'.',value:'${quoteRecord1.panel_size}',
						onChange:function(newValue,oldValue){
							projectMag_edit_quote_calc_systemsize(this);
						}"> watt<br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_panelNumberInput1" name="panel_number" data-options="width:'88%',label:'Panel Number:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,value:'${quoteRecord1.panel_number}',
						onChange:function(newValue,oldValue){
							projectMag_edit_quote_calc_systemsize(this);
						}"> pcs<br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_inverterBrandInput1" name="inverter_brand" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord1.inverter_brand}'"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_inverterSizeInput1" name="inverter_size" data-options="width:'95%',label:'Inverter Size:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord1.inverter_size}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_systemSizeInput1" name="system_size" data-options="width:'88%',label:'System Size:',labelWidth:170,labelAlign:'right',readonly:true,required:true,min:0,groupSeparator:',',precision:3,decimalSeparator:'.',value:'${quoteRecord1.system_size}'"> KW<br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_priceInput1" name="price" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true,value:'${quoteRecord1.price}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_depositInput1" name="deposit" data-options="width:'95%',label:'Deposit Amount:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',value:'${quoteRecord1.deposit}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_depositInput\']').numberspinner('setValue',newValue);
							}
						}"><br/>
					<select class="easyui-combobox" id="projectMag_edit_quote_paymentCombobox1" name="payment" data-options="width:'95%',label:'Payment:',labelWidth:170,labelAlign:'right',editable:false,required:true,value:'${quoteRecord1.payment}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_paymentCombobox\']').combobox('setValue',newValue);
							}
						}
					">
						<option value=""></option>
					    <option value="Cash/Cheque">Cash/Cheque</option>
					    <option value="DD form (direct debit)">DD form (direct debit)</option>
					    <option value="Certegy">Certegy</option>
					    <option value="ASM">ASM</option>
					    <option value="Other finance">Other finance</option>
					</select><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_ABNInput1" name="ABN" data-options="width:'95%',label:'ABN:',labelWidth:170,labelAlign:'right'<s:if test='#request.projectInfo.leads_type=="Commercial"'>,required:true</s:if>,value:'${quoteRecord1.ABN}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_ABNInput\']').textbox('setValue',newValue);
							}
						}
					"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_businessTypeInput1" name="business_type" data-options="width:'95%',label:'Business Type:',labelWidth:170,labelAlign:'right'<s:if test='#request.projectInfo.leads_type=="Commercial"'>,required:true</s:if>,value:'${quoteRecord1.business_type}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_businessTypeInput\']').textbox('setValue',newValue);
							}
						}
					"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_installCommentInput1" name="install_comment"  data-options="width:'95%',height:80,label:'Install Comment:',labelWidth:170,labelAlign:'right',multiline:true,value:'${quoteRecord1.install_comment}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_installCommentInput\']').textbox('setValue',newValue);
							}
						}
					">
				</div>
	    	</div>
	    	<div <s:if test="#request.quoteRecord2.status=='to confirm' || #request.quoteRecord2.status=='confirm'">class="datagrid-row-selected"</s:if> data-options="region:'center',
	    		onOpen:function(){
	    			$(this).find('.easyui-textbox').textbox('disableValidation');
	    			$(this).find('.easyui-numberspinner').numberspinner('disableValidation');
	    			$(this).find('.easyui-combobox').combobox('disableValidation');
	    		}
	    	">
				<div style="height:80px;font-size:30px;line-height:80px;text-align:center;">
					<input class="easyui-switchbutton" id="projectMag_edit_quote_selectBtn2" name="select_to_sign" data-options="width:150,onText:'Selected to sign',offText:'Unselected',value:'${quoteRecord2.vid}'<s:if test='#request.quoteRecord2.status=="to confirm"||#request.quoteRecord2.status=="confirm"'>,checked:true</s:if>,
						onChange:function(checked){
							projectMag_edit_select_button_change(this,checked);
						}
					"/>
					Quote Ⅱ
				</div>
				<div>
					<input type="hidden" id="projectMag_edit_quote_quoteRecordVid2" name="quote_record_vid" value="${quoteRecord2.vid}"/>
					<input class="easyui-textbox" id="projectMag_edit_quote_panelBrandInput2" name="panel_brand" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord2.panel_brand}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_panelSizeInput2" name="panel_size" data-options="width:'88%',label:'Panel Size:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,precision:2,decimalSeparator:'.',value:'${quoteRecord2.panel_size}',
						onChange:function(newValue,oldValue){
							projectMag_edit_quote_calc_systemsize(this);
						}"> watt<br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_panelNumberInput2" name="panel_number" data-options="width:'88%',label:'Panel Number:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,value:'${quoteRecord2.panel_number}',
						onChange:function(newValue,oldValue){
							projectMag_edit_quote_calc_systemsize(this);
						}"> pcs<br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_inverterBrandInput2" name="inverter_brand" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord2.inverter_brand}'"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_inverterSizeInput2" name="inverter_size" data-options="width:'95%',label:'Inverter Size:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord2.inverter_size}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_systemSizeInput2" name="system_size" data-options="width:'88%',label:'System Size:',labelWidth:170,labelAlign:'right',readonly:true,required:true,min:0,groupSeparator:',',precision:3,decimalSeparator:'.',value:'${quoteRecord2.system_size}'"> KW<br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_priceInput2" name="price" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true,value:'${quoteRecord2.price}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_depositInput2" name="deposit" data-options="width:'95%',label:'Deposit Amount:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',value:'${quoteRecord2.deposit}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_depositInput\']').numberspinner('setValue',newValue);
							}
						}"><br/>
					<select class="easyui-combobox" id="projectMag_edit_quote_paymentCombobox2" name="payment" data-options="width:'95%',label:'Payment:',labelWidth:170,labelAlign:'right',editable:false,required:true,value:'${quoteRecord2.payment}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_paymentCombobox\']').combobox('setValue',newValue);
							}
						}
					">
						<option value=""></option>
					    <option value="Cash/Cheque">Cash/Cheque</option>
					    <option value="DD form (direct debit)">DD form (direct debit)</option>
					    <option value="Certegy">Certegy</option>
					    <option value="ASM">ASM</option>
					    <option value="Other finance">Other finance</option>
					</select><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_ABNInput2" name="ABN" data-options="width:'95%',label:'ABN:',labelWidth:170,labelAlign:'right'<s:if test='#request.projectInfo.leads_type=="Commercial"'>,required:true</s:if>,value:'${quoteRecord2.ABN}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_ABNInput\']').textbox('setValue',newValue);
							}
						}
					"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_businessTypeInput2" name="business_type" data-options="width:'95%',label:'Business Type:',labelWidth:170,labelAlign:'right'<s:if test='#request.projectInfo.leads_type=="Commercial"'>,required:true</s:if>,value:'${quoteRecord2.business_type}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_businessTypeInput\']').textbox('setValue',newValue);
							}
						}
					"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_installCommentInput2" name="install_comment"  data-options="width:'95%',height:80,label:'Install Comment:',labelWidth:170,labelAlign:'right',multiline:true,value:'${quoteRecord2.install_comment}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_installCommentInput\']').textbox('setValue',newValue);
							}
						}
					">
				</div>
	    	</div>
	    	<div <s:if test="#request.quoteRecord3.status=='to confirm' || #request.quoteRecord3.status=='confirm'">class="datagrid-row-selected"</s:if> data-options="region:'east',width:'33.3%',
	    		onOpen:function(){
	    			$(this).find('.easyui-textbox').textbox('disableValidation');
	    			$(this).find('.easyui-numberspinner').numberspinner('disableValidation');
	    			$(this).find('.easyui-combobox').combobox('disableValidation');
	    		}
	    	">
				<div style="height:80px;font-size:30px;line-height:80px;text-align:center;">
					<input class="easyui-switchbutton" id="projectMag_edit_quote_selectBtn3" name="select_to_sign" data-options="width:150,onText:'Selected to sign',offText:'Unselected',value:'${quoteRecord3.vid}'<s:if test='#request.quoteRecord3.status=="to confirm"||#request.quoteRecord3.status=="confirm"'>,checked:true</s:if>,
						onChange:function(checked){
							projectMag_edit_select_button_change(this,checked);
						}
					"/>
					Quote Ⅲ
				</div>
				<div>
					<input type="hidden" id="projectMag_edit_quote_quoteRecordVid3" name="quote_record_vid" value="${quoteRecord3.vid}"/>
					<input class="easyui-textbox" id="projectMag_edit_quote_panelBrandInput3" name="panel_brand" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord3.panel_brand}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_panelSizeInput3" name="panel_size" data-options="width:'88%',label:'Panel Size:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,precision:2,decimalSeparator:'.',value:'${quoteRecord3.panel_size}',
						onChange:function(newValue,oldValue){
							projectMag_edit_quote_calc_systemsize(this);
						}"> watt<br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_panelNumberInput3" name="panel_number" data-options="width:'88%',label:'Panel Number:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,value:'${quoteRecord3.panel_number}',
						onChange:function(newValue,oldValue){
							projectMag_edit_quote_calc_systemsize(this);
						}"> pcs<br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_inverterBrandInput3" name="inverter_brand" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord3.inverter_brand}'"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_inverterSizeInput3" name="inverter_size" data-options="width:'95%',label:'Inverter Size:',labelWidth:170,labelAlign:'right',required:true,value:'${quoteRecord3.inverter_size}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_systemSizeInput3" name="system_size" data-options="width:'88%',label:'System Size:',labelWidth:170,labelAlign:'right',readonly:true,required:true,min:0,groupSeparator:',',precision:3,decimalSeparator:'.',value:'${quoteRecord3.system_size}'"> KW<br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_priceInput3" name="price" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true,value:'${quoteRecord3.price}'"><br/>
					<input class="easyui-numberspinner" id="projectMag_edit_quote_depositInput3" name="deposit" data-options="width:'95%',label:'Deposit Amount:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',value:'${quoteRecord3.deposit}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_depositInput\']').numberspinner('setValue',newValue);
							}
						}
					"><br/>
					<select class="easyui-combobox" id="projectMag_edit_quote_paymentCombobox3" name="payment" data-options="width:'95%',label:'Payment:',labelWidth:170,labelAlign:'right',editable:false,required:true,value:'${quoteRecord3.payment}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_paymentCombobox\']').combobox('setValue',newValue);
							}
						}
					">
						<option value=""></option>
					    <option value="Cash/Cheque">Cash/Cheque</option>
					    <option value="DD form (direct debit)">DD form (direct debit)</option>
					    <option value="Certegy">Certegy</option>
					    <option value="ASM">ASM</option>
					    <option value="Other finance">Other finance</option>
					</select><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_ABNInput3" name="ABN" data-options="width:'95%',label:'ABN:',labelWidth:170,labelAlign:'right'<s:if test='#request.projectInfo.leads_type=="Commercial"'>,required:true</s:if>,value:'${quoteRecord3.ABN}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_ABNInput\']').textbox('setValue',newValue);
							}
						}
					"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_businessTypeInput3" name="business_type" data-options="width:'95%',label:'Business Type:',labelWidth:170,labelAlign:'right'<s:if test='#request.projectInfo.leads_type=="Commercial"'>,required:true</s:if>,value:'${quoteRecord3.business_type}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_businessTypeInput\']').textbox('setValue',newValue);
							}
						}
					"><br/>
					<input class="easyui-textbox" id="projectMag_edit_quote_installCommentInput3" name="install_comment"  data-options="width:'95%',height:80,label:'Install Comment:',labelWidth:170,labelAlign:'right',multiline:true,value:'${quoteRecord3.install_comment}',
						onChange: function(newValue, oldValue){
							if( newValue != '' ){
								$('[id^=\'projectMag_edit_quote_installCommentInput\']').textbox('setValue',newValue);
							}
						}
					">
				</div>
	    	</div>
    	</div>
    </div>
    <div data-options="region:'south',height:30" border="false" style="text-align:center;line-height:30px;">
<%--     	<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && #session.role!="door knocker" && #request.contractConfirmInfo.quote_info_confirm!="confirm"'>
 --%>    	
 		<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && #session.role!="door knocker" && #request.status <= 3'>
	        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_edit_quote_submit()">Submit Changes</a>
	        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_edit_quote_cancel()">Cancel</a>
	        <a class="easyui-linkbutton" icon="icon-edit" href="javascript:void(0)" onclick="projectMag_ready_to_sign()">Contract Submit</a>
       	</s:if>
    </div>
    </form>
</div>
