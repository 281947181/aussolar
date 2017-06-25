<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" fit="true">
	<input type="hidden" id="project_vid" name="vid" value="${projectInfo.vid}"/>
	<div data-options="region:'north',height:120,title:'Basic Info ( Project ID: ${projectInfo.project_id} )',collapsible:false">
		<table style="width:100%;">
			<tr>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerNameInput" data-options="width:'90%',label:'Customer Name:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_name}'">
				</td>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerPhoneInput" data-options="width:'90%',label:'Customer Phone:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_phone}'">
				</td>
				<td style="width:33.3%;">
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerAddressInput" data-options="width:'90%',label:'Address:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_address}'">
				</td>
			</tr>
			<tr>
				<td>
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerSubburbInput" data-options="width:'90%',label:'Suburb:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_subburb}'">
				</td>
				<td>
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerPostcodeInput" data-options="width:'90%',label:'Postcode:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_postcode}'">
				</td>
				<td>
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerStateInput" data-options="width:'90%',label:'State:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_state}'">
				</td>
			</tr>
			<tr>
				<td>
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerRoofTypeInput" data-options="width:'90%',label:'Roof Type:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_roof_type}'">
				</td>
				<td>
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerStoreyInput" data-options="width:'90%',label:'Storey:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_storey}'">
				</td>
				<td>
					<input class="easyui-textbox" id="projectMag_contract_confirm_customerPhaseTypeInput" data-options="width:'90%',label:'Phase Type:',labelWidth:100,labelAlign:'right',readonly:true,value:'${projectInfo.customer_phase_type}'">
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'west',width:'50%',title:'Quote Info',collapsible:false">
		<br/>
		<input type="hidden" id="projectMag_contract_confirm_quoteRecordVid" value="${quoteRecord.vid}"/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_panelBrandInput" data-options="width:'95%',label:'Solar Panel Brand & Model:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.panel_brand}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_panelSizeInput" data-options="width:'88%',label:'Panel Size:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.panel_size}'"> watt<br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_panelNumberInput" data-options="width:'88%',label:'Panel Number:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.panel_number}'"> pcs<br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_inverterBrandInput" data-options="width:'95%',label:'Inverter Brand & Model:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.inverter_brand}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_inverterSizeInput" data-options="width:'95%',label:'Inverter Size:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.inverter_size}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_systemSizeInput" data-options="width:'88%',label:'System Size:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.system_size}'"> KW<br/>
		<input class="easyui-numberspinner" id="projectMag_contract_confirm_priceInput" data-options="width:'95%',label:'Price Inc.GST:',labelWidth:200,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',readonly:true,value:'${quoteRecord.price}'"><br/>
		<input class="easyui-numberspinner" id="projectMag_contract_confirm_depositInput" data-options="width:'95%',label:'Deposit Amount:',labelWidth:200,labelAlign:'right',min:0,groupSeparator:',',prefix:'$',readonly:true,value:'${quoteRecord.deposit}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_paymentInput" data-options="width:'95%',label:'Payment:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.payment}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_ABNInput" data-options="width:'95%',label:'ABN:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.ABN}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_businessTypeInput" data-options="width:'95%',label:'Business Type:',labelWidth:200,labelAlign:'right',readonly:true,value:'${quoteRecord.business_type}'"><br/>
		<input class="easyui-textbox" id="projectMag_contract_confirm_installCommentInput" data-options="width:'95%',height:90,label:'Install Comment:',labelWidth:200,labelAlign:'right',readonly:true,multiline:true,value:'${quoteRecord.install_comment}'">
	</div>
	<div data-options="region:'center',title:'Confirm Quote & Paperwork Info',collapsible:false">
		<table style="width:100%;height:85%;">
			<tr>
				<s:if test='#request.contractConfirmInfo.quote_info_confirm!="confirm"'>
					<td style="width:70%;border-bottom: 1px dotted black;background-color:#f3715c">**1. Confirm the quote info on the left.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',1,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="width:70%;border-bottom: 1px dotted black;background-color:#ADFF2F">**1. Confirm the quote info on the left.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',1,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.contract_info_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">**2. Confirm the contract info.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',2,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">**2. Confirm the contract info.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',2,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.fee_info_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">**3. Confirm the electric bill info.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',3,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">**3. Confirm the electric bill info.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',3,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.phase_info_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">**4. Confirm the meter box info.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',4,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">**4. Confirm the meter box info.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',4,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.nearmap_info_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">**5. Confirm nearmap & ref photos.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',5,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">**5. Confirm nearmap & ref photos.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',5,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.comment_info_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">&nbsp;&nbsp;6. Confirm the install comment.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',6,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">&nbsp;&nbsp;6. Confirm the install comment.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',6,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.deposit_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">&nbsp;&nbsp;7. Confirm the deposit.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',7,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">&nbsp;&nbsp;7. Confirm the deposit.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',7,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.dd_info_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">&nbsp;&nbsp;8. Confirm the DD request form.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',8,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">&nbsp;&nbsp;8. Confirm the DD request form.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',8,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.certegy_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">&nbsp;&nbsp;9. Confirm the certegy.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',9,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">&nbsp;&nbsp;9. Confirm the certegy.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',9,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.asm_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">&nbsp;&nbsp;10. Confirm the ASM.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',10,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">&nbsp;&nbsp;10. Confirm the ASM.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',10,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
			<tr>
				<s:if test='#request.contractConfirmInfo.other_finance_confirm!="confirm"'>
					<td style="border-bottom: 1px dotted black;background-color:#f3715c">&nbsp;&nbsp;11. Confirm other finance paperwork.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit('${projectInfo.vid}',11,this)">Confirm</a>
						</s:if>
					</td>
				</s:if>
				<s:else>
					<td style="border-bottom: 1px dotted black;background-color:#ADFF2F">&nbsp;&nbsp;11. Confirm other finance paperwork.</td>
					<td style="border-bottom: 1px dotted black;">
						<s:if test='#request.projectInfo.project_status!="canceled"&&#request.projectInfo.project_status!="leads reject" && (#request.projectInfo.wp_status==null||#request.projectInfo.wp_status=="ready for WP")'>
							<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke('${projectInfo.vid}',11,this)">Revoke Confirm</a>
						</s:if>
					</td>
				</s:else>
			</tr>
		</table>
		<input class="easyui-textbox" id="projectMag_contract_confirm_adminCommentInput" data-options="width:'83%',height:'15%',label:'Admin Comment:',labelWidth:110,labelAlign:'right',multiline:true,value:'${request.contractConfirmInfo.admin_comment}'">
		<a id="btn" href="javascript:void(0)" class="easyui-linkbutton" onclick="projectMag_contract_confirm_comment_submit('${request.contractConfirmInfo.vid}',this)">Submit</a>
	</div>
</div>
