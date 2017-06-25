<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" fit="true">
	<form id="projectMag_ready_to_quote_form" method="post">
	<input type="hidden" name="vid" value="${projectInfo.vid}"/>
	<input id="projectMag_ready_to_quote_changedHidden" type="hidden" name="changedField" value=""/>
	<input id="projectMag_ready_to_quote_changedFieldIdHidden" type="hidden" value=""/>
	<input type="hidden" id="projectMag_ready_to_quote_customerAddressLatInput" name="customer_address_lat" value="${projectInfo.customer_address_lat}"/>
 	<input type="hidden" id="projectMag_ready_to_quote_customerAddressLngInput" name="customer_address_lng" value="${projectInfo.customer_address_lng}"/>
	<div data-options="region:'north',height:120,title:'Basic Info ( Project ID: ${projectInfo.project_id} )',collapsible:false">
		<table style="width:100%;">
			<tr>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_ready_to_quote_customerNameInput" name="customer_name" data-options="width:'90%',label:'Customer Name:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_name}',
						onChange: function(newValue,oldValue){
							projectMag_readyToQuote_isChanged(newValue,oldValue,this);
						}">
				</td>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_ready_to_quote_customerPhoneInput" name="customer_phone" data-options="width:'90%',label:'Customer Phone:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_phone}',
						onChange: function(newValue,oldValue){
							projectMag_readyToQuote_isChanged(newValue,oldValue,this);
						}">
				</td>
				<td><input class="easyui-textbox" id="projectMag_ready_to_quote_customerSubburbInput" name="customer_subburb" data-options="width:'90%',label:'Suburb:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_subburb}',
					onChange: function(newValue,oldValue){
						projectMag_readyToQuote_isChanged(newValue,oldValue,this);
					}">
				</td>
			</tr>
			<tr>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_ready_to_quote_customerAddressInput" name="customer_address" data-options="width:'90%',label:'Address:',labelWidth:100,labelAlign:'right',required:true,value:'${projectInfo.customer_address}',buttonText:'check',
						onChange:function(newValue,oldValue){
							projectMag_readyToQuote_isChanged(newValue,oldValue,this);
						},
						onClickButton:function(){
							var value = $.trim($(this).textbox('getValue'));
							projectMag_ready_to_quote_codeAddressByAddress(value);
						}">
				</td>
				<td><input class="easyui-textbox" id="projectMag_ready_to_quote_customerPostcodeInput" name="customer_postcode" data-options="width:'90%',label:'Postcode:',labelWidth:100,labelAlign:'right',readonly:true,required:true,value:'${projectInfo.customer_postcode}',
					onChange: function(newValue,oldValue){
						projectMag_readyToQuote_isChanged(newValue,oldValue,this);
					}">
				</td>
				<td><input class="easyui-textbox" id="projectMag_ready_to_quote_customerStateInput" name="customer_state" data-options="width:'90%',label:'State:',labelWidth:100,labelAlign:'right',readonly:true,required:true,value:'${projectInfo.customer_state}',
					onChange: function(newValue,oldValue){
						projectMag_readyToQuote_isChanged(newValue,oldValue,this);
					}">
				</td>
			</tr>
			<tr>
				<td>
					<select class="easyui-combobox" id="projectMag_ready_to_quote_roofTypeCombobox" name="customer_roof_type" data-options="width:'60%',label:'Roof Type:',labelWidth:100,labelAlign:'right',editable:false,required:true,
						onChange:function(newValue,oldValue){
							if( $.trim(newValue) == 'Other' )
								$('#projectMag_ready_to_quote_roofTypeInput').textbox('enable');
							else
								$('#projectMag_ready_to_quote_roofTypeInput').textbox('disable');
						}">
					    <option value=""></option>
					    <option value="Tin">Tin</option>
					    <option value="Tile">Tile</option>
					    <option value="Other">Other</option>
					</select>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_roofTypeInput" name="customer_roof_type" data-options="width:'29%',disabled:true,required:true">
				</td>
				<td>
					<select class="easyui-combobox" id="projectMag_ready_to_quote_storeyCombobox" name="customer_storey" data-options="width:'90%',label:'Storey:',labelWidth:100,labelAlign:'right',editable:false,required:true">
						<option value=""></option>
					    <option value="Single">Single</option>
					    <option value="Double">Double</option>
					    <option value="2+">2+</option>
					</select>
				</td>
				<td>
					<select class="easyui-combobox" id="projectMag_ready_to_quote_phaseTypeCombobox" name="customer_phase_type" data-options="width:'90%',label:'Phase Type:',labelWidth:100,labelAlign:'right',editable:false,required:true">
						<option value=""></option>
					    <option value="Single Phase">Single Phase</option>
					    <option value="Dual Phase">Dual Phase</option>
					    <option value="Three Phase">Three Phase</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',title:'Quote Info',collapsible:false"">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'west',width:'33.3%',
	    		onOpen:function(){
	    			$(this).find('.easyui-textbox').textbox('disableValidation');
	    			$(this).find('.easyui-numberspinner').numberspinner('disableValidation');
	    			$(this).find('.easyui-combobox').combobox('disableValidation');
	    		}
	    	">
				<div style="height:100px;font-size:35px;line-height:100px;text-align:center;">Quote Ⅰ</div>
				<div>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_panelBrandInput1" name="panel_brand" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_panelSizeInput1" name="panel_size" data-options="width:'88%',label:'Panel Size:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,precision:2,decimalSeparator:'.',
						onChange:function(newValue,oldValue){
							projectMag_ready_to_quote_calc_systemsize(this);
						}"> watt<br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_panelNumberInput1" name="panel_number" data-options="width:'88%',label:'Panel Number:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,
						onChange:function(newValue,oldValue){
							projectMag_ready_to_quote_calc_systemsize(this);
						}"> pcs<br/><br/>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_inverterBrandInput1" name="inverter_brand" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_inverterSizeInput1" name="inverter_size" data-options="width:'95%',label:'Inverter Size:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_systemSizeInput1" name="system_size" data-options="width:'88%',label:'System Size:',labelWidth:170,labelAlign:'right',readonly:true,required:true,min:0,groupSeparator:',',precision:3,decimalSeparator:'.'"> KW<br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_priceInput1" name="price" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true"><br/><br/>
				</div>
	    	</div>
	    	<div data-options="region:'center',
	    		onOpen:function(){
	    			$(this).find('.easyui-textbox').textbox('disableValidation');
	    			$(this).find('.easyui-numberspinner').numberspinner('disableValidation');
	    			$(this).find('.easyui-combobox').combobox('disableValidation');
	    		}
	    	">
				<div style="height:100px;font-size:35px;line-height:100px;text-align:center;">Quote Ⅱ</div>
				<div>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_panelBrandInput2" name="panel_brand" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_panelSizeInput2" name="panel_size" data-options="width:'88%',label:'Panel Size:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',precision:2,decimalSeparator:'.',required:true,
						onChange:function(newValue,oldValue){
							projectMag_ready_to_quote_calc_systemsize(this);
						}"> watt<br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_panelNumberInput2" name="panel_number" data-options="width:'88%',label:'Panel Number:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,
						onChange:function(newValue,oldValue){
							projectMag_ready_to_quote_calc_systemsize(this);
						}"> pcs<br/><br/>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_inverterBrandInput2" name="inverter_brand" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_inverterSizeInput2" name="inverter_size" data-options="width:'95%',label:'Inverter Size:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_systemSizeInput2" name="system_size" data-options="width:'88%',label:'System Size:',labelWidth:170,labelAlign:'right',readonly:true,min:0,groupSeparator:',',precision:3,decimalSeparator:'.',required:true"> KW<br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_priceInput2" name="price" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true"><br/><br/>
				</div>
	    	</div>
	    	<div data-options="region:'east',width:'33.3%',
	    		onOpen:function(){
	    			$(this).find('.easyui-textbox').textbox('disableValidation');
	    			$(this).find('.easyui-numberspinner').numberspinner('disableValidation');
	    			$(this).find('.easyui-combobox').combobox('disableValidation');
	    		}
	    	">
				<div style="height:100px;font-size:35px;line-height:100px;text-align:center;">Quote Ⅲ</div>
				<div>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_panelBrandInput3" name="panel_brand" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_panelSizeInput3" name="panel_size" data-options="width:'88%',label:'Panel Size:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',precision:2,decimalSeparator:'.',required:true,
						onChange:function(newValue,oldValue){
							projectMag_ready_to_quote_calc_systemsize(this);
						}"> watt<br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_panelNumberInput3" name="panel_number" data-options="width:'88%',label:'Panel Number:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',required:true,
						onChange:function(newValue,oldValue){
							projectMag_ready_to_quote_calc_systemsize(this);
						}"> pcs<br/><br/>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_inverterBrandInput3" name="inverter_brand" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-textbox" id="projectMag_ready_to_quote_inverterSizeInput3" name="inverter_size" data-options="width:'95%',label:'Inverter Size:',labelWidth:170,labelAlign:'right',required:true"><br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_systemSizeInput3" name="system_size" data-options="width:'88%',label:'System Size:',labelWidth:170,labelAlign:'right',readonly:true,min:0,groupSeparator:',',precision:3,decimalSeparator:'.',required:true"> KW<br/><br/>
					<input class="easyui-numberspinner" id="projectMag_ready_to_quote_priceInput3" name="price" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:170,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',precision:2,decimalSeparator:'.',required:true"><br/><br/>
				</div>
	    	</div>
    	</div>
    </div>
    <div data-options="region:'south',height:50" border="false" style="text-align:center;line-height:50px;">
    	<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject"'>
	        <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_ready_to_quote_submit()">Submit</a>
	        <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_ready_to_quote_cancel()">Cancel</a>
        </s:if>
    </div>
    </form>
</div>
