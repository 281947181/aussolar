var markers = [];
var current_marker = null;
var map;
//Sets the map on all markers in the array.
function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}
// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setMapOnAll(null);
}
// Shows any markers currently in the array.
function showMarkers() {
  setMapOnAll(map);
}
// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
  clearMarkers();
  markers = [];
}
$.extend($.fn.validatebox.defaults.rules, { 
	equalTo: {
		validator:function(value,param){
			return $(param[0]).val() == value;
		},
		message:'Passwords are not the same!'
	}
});
function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}
$(function(){
	project_mag_init();
	var role = $("#hidden_roleInput").val();
	if( role != "admin" ){
		$('#accountMagBtn').linkbutton("disable");
//		$('#showMapBtn').linkbutton("disable");
	}
//	$.getJSON("http://localhost:8080/aussolar/projectMag_queryInstalledProjectFromWP.action?jsoncallback=?")
//	.done(function( data ) {
//		alert(data.latlng.length);
//	});
	$.getScript("resources/js/sha.js");
	$.getScript("https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js");
	$.getScript("https://maps.googleapis.com/maps/api/js?key=AIzaSyDTwj5RaQzi___RzieB9vxR6L0VYkIxL-E&language=en");
	
});
function searchProject(){
	$("#project_mag_main_table").datagrid('load',{
		project_status: $('#project_mag_main_table_toolbar_form_statusCombo').combobox('getValue'),
		address: $('#project_mag_main_table_toolbar_form_addressInput').textbox('getValue'),
		customer_name: $('#project_mag_main_table_toolbar_form_customerNameInput').textbox('getValue'),
		customer_phone: $('#project_mag_main_table_toolbar_form_customerPhoneInput').textbox('getValue'),
		postcode: $('#project_mag_main_table_toolbar_form_postcodeInput').textbox('getValue'),
		subburb: $('#project_mag_main_table_toolbar_form_subburbInput').textbox('getValue'),
		asign_to: $('#project_mag_main_table_toolbar_form_asignToCombo').combobox('getValue'),
		installer: $('#project_mag_main_table_toolbar_form_installerCombo').combobox('getValue'),
		door_knocker: $('#project_mag_main_table_toolbar_form_doorKnockerCombo').combobox('getValue'),
		panel_brand: $('#project_mag_main_table_toolbar_form_panelNameCombo').combobox('getValue'),
		inverter_brand: $('#project_mag_main_table_toolbar_form_inverterNameCombo').combobox('getValue'),
		create_from: $('#project_mag_main_table_toolbar_form_createDateFromDatebox').datebox('getValue'),
		create_to: $('#project_mag_main_table_toolbar_form_createDateToDatebox').datebox('getValue'),
		asign_from: $('#project_mag_main_table_toolbar_form_asignDateFromDatebox').datebox('getValue'),
		asign_date_to: $('#project_mag_main_table_toolbar_form_asignDateToDatebox').datebox('getValue'),
		contract_from: $('#project_mag_main_table_toolbar_form_contractDateFromDatebox').datebox('getValue'),
		contract_to: $('#project_mag_main_table_toolbar_form_contractDateToDatebox').datebox('getValue'),
		approved_from: $('#project_mag_main_table_toolbar_form_approvedDateFromDatebox').datebox('getValue'),
		approved_to: $('#project_mag_main_table_toolbar_form_approvedDateToDatebox').datebox('getValue'),
		install_from: $('#project_mag_main_table_toolbar_form_installDateFromDatebox').datebox('getValue'),
		install_to: $('#project_mag_main_table_toolbar_form_installDateToDatebox').datebox('getValue')
	});
	
}
function project_mag_init(){
	var contentHeight = 330;
	var buttonHeight = 60;
	$("#project_mag_main_table").datagrid({
		rowStyler: function(index, row){
			if( index%2 == 1 ){
				return 'background-color:#ebeb9b;height:400px;';
			}
			else
				return 'height:400px;';
		},
		columns:[[
			{field:'vid',hidden:true},
			{field:'id',title:'ID',width:'15%',
				formatter: function(value,row,index){
					var role = $('#hidden_roleInput').val();
					var returnValue = "";
					returnValue += '<div style="height:'+contentHeight+'px;width:100%">';
					if(row.status >= 6)
						returnValue += '<input id="installDownloadBatCheckBox" type="checkbox" name="vid" value="'+row.vid+'"/> Download Install Report<br/><br/>';
					returnValue += row.project_id + "<br/><br/>";
					returnValue += "Leads From: " + row.leads_from + "<br/>";
					if( "Door to door" == row.leads_from )
						returnValue += "Door Knocker: " + row.leads_knocker + "<br/>";
					if( row.asign_to ){
						returnValue += "Asign to: "+ row.asign_to + "<br/>";
						returnValue += "Asign date: "+ row.asign_date.substring(0,10) + "<br/><br/>";
					}
					
					if( row.status <= 3 )
						returnValue += "<div style=\"text-align:center;width:95%;background-color:#f3715c;color:black;\">Status<br/>" + row.project_status.toUpperCase()+"</div><br/>";
					else if( row.status <= 7 )
						returnValue += "<div style=\"text-align:center;width:95%;background-color:blue;color:white;\">Status<br/>" + row.project_status.toUpperCase()+"</div><br/>";
					else
						returnValue += "<div style=\"text-align:center;width:95%;background-color:#ADFF2F;color:black;\">Status<br/>" + row.project_status.toUpperCase()+"</div><br/>";
					
					if( row.wp_status != "null" && role == 'admin' ){
						if( row.commission_status == 'unpaid' )
							returnValue += "<div style=\"width:95%;height:30px;line-height:30px;text-align:center;background-color:#f3715c;color:black;\">Commission Unpaid&nbsp;" 
								+'<a id="commissionPayBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_commissionPay('+row.vid+',\''+row.project_id+'\')" data-options="height:25">PAY</a>'
								+"</div><br/>";
						else if( row.commission_status == 'paid' )
							returnValue += "<div style=\"width:95%;height:30px;line-height:30px;text-align:center;background-color:#ADFF2F;color:black;\">Commission Paid: "+row.commission_value+"</div><br/>";
					}
					returnValue += '</div>';
					returnValue += '<div style="height:'+buttonHeight+'px;width:100%">';
					if( role == 'admin' && row.status >= 0 && row.status <= 7 )
						returnValue += '<a id="cancelProjectBtn'+row.vid+'" href="javascript:void(0)" iconCls="icon-cancel" onclick="projectMag_cancelProject('+row.vid+',\''+row.project_id+'\')">Cancel Project</a>';
					else if( role == 'admin' && row.status == -1 )
						returnValue += '<a id="deleteProjectBtn'+row.vid+'" href="javascript:void(0)" iconCls="icon-cancel" onclick="projectMag_deleteProject('+row.vid+')">Delete Project</a>';
					returnValue += '</div>';
					return returnValue;
				}
		    },
		    {field:'leads_info',title:'Leads Info',width:'14%',
				formatter: function(value,row,index){
					var role = $('#hidden_roleInput').val();
					var returnValue = "";
					returnValue += '<div style="height:'+contentHeight+'px;width:100%">';
						
//					if( row.modified )
//						returnValue += "<span style=\"color:red;\">Modification to be comfirmed.</span><br/><br/>";
					
					returnValue += "Customer: "+ row.customer_name + "<br/>";
					returnValue += "Phone: "+ row.customer_phone + "<br/>";
					returnValue += "Address: "+ row.customer_address + "<br/>";
					returnValue += "Suburb: "+ row.customer_subburb + "<br/>";
					returnValue += "Postcode: "+ row.customer_postcode + "<br/>";
					returnValue += '<div style="font-weight:bolder;background-color:orange;">Leads Type: '+ row.leads_type + '</div>';
					returnValue += '<div style="font-weight:bolder;background-color:orange;overflow-y:scroll;max-height:130px;">Comment: '+ row.leads_comment + '</div>';
					
					if( row.contact_times ){
						returnValue += "------------------------<br/>";
						returnValue += "Contact " + row.contact_times + " times already.<br/>";
						returnValue += "Lastest contact: " + row.latest_contact.substring(0,10) + "<br/><br/>";
					}
					if( row.customer_address_lat == "null" )
						returnValue += '<span style="color:red;">Leads Info needs to be checked and edited!</span>';
					
					returnValue += '</div>';
					returnValue += '<div style="height:'+buttonHeight+'px;width:100%">';
					if( role == 'admin' ){
						returnValue += '<a id="editLeadsBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_edit_leads_createWindow('+row.vid+')">Details</a>&nbsp;';
						if( row.status == 0 )
							returnValue += '<a id="asignBtn'+row.vid+'" href="javascript:void(0)" onclick="makeAsignToWindow('+row.vid+',\''+row.customer_name+'\',\''+row.project_id+'\')">Asign</a>';
						else if( row.status == 1 ){
							returnValue += '<a id="contactBtn'+row.vid+'" href="javascript:void(0)" onclick="makeContactWindow('+row.vid+',\''+row.project_id+'\')">Contact</a>&nbsp;';
							returnValue += '<a id="rejectLeadsBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_rejectLeads('+row.vid+',\''+row.project_id+'\')">Reject</a>';
							if( row.contact_times )
								returnValue += '<br/><a id="readyToQuoteBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_readyToQuoteConfirm('+row.vid+')">Ready to Quote</a>';
						}
					}
					else if( role == 'sales' ){
						returnValue += '<a id="editLeadsBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_edit_leads_createWindow('+row.vid+')">Details</a>&nbsp;';
						if( row.status == 1 ){
							returnValue += '<a id="contactBtn'+row.vid+'" href="javascript:void(0)" onclick="makeContactWindow('+row.vid+',\''+row.project_id+'\')">Contact</a>&nbsp;';
							returnValue += '<a id="rejectLeadsBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_rejectLeads('+row.vid+',\''+row.project_id+'\')">Reject</a>';
							if( row.contact_times )
								returnValue += '<br/><a id="readyToQuoteBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_readyToQuoteConfirm('+row.vid+')">Ready to Quote</a>';
						}
					}
					returnValue += '</div>';
					return returnValue;
				}
		    },
		    {field:'quote_info',title:'Quote Info',width:'12%',
				formatter: function(value,row,index){
					var role = $('#hidden_roleInput').val();
					var returnValue = "";
					returnValue += '<div style="height:'+contentHeight+'px;width:100%">';
					if( row.status >= 2 ){
						for(var i = 0; i < row.quote_system_size.length; i++){
							returnValue += "System Size " + (i+1) + ": " + row.quote_system_size[i] + " KW<br/>";
							returnValue += "Price " + (i+1) + ": $" + row.quote_price[i] + "<br/>";
						}
						returnValue += "Quote Date: " + row.quoted_date.substring(0,10) + "<br/>";
						returnValue += "Quote Person: " + row.quoted_person + "<br/>";
						returnValue += '<div style="font-weight:bolder;background-color:orange;overflow-y:scroll;max-height:'+
							(row.quote_system_size.length==1?250:(row.quote_system_size.length==2?200:150))+'px;">Comment: ' + row.quote_to_confirm_comment + '</div>';
					}
					
					returnValue += '</div>';
					returnValue += '<div style="height:'+buttonHeight+'px;width:100%">';
					if( row.status >= 2 ){
						if( role != 'door knocker' ){
							returnValue += '<a id="quoteDetailBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_quote_detail('+row.vid+')">Show Detail</a> ';
							returnValue += '<a id="downloadQuotationBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_download_quotation('+row.vid+')">Download</a>';
						}
					}
					returnValue += '</div>';
					return returnValue;
				}
		    },
		    {field:'contract_info',title:'Contract Info',width:'23%',
				formatter: function(value,row,index){
					var role = $('#hidden_roleInput').val();
					var returnValue = "";
					returnValue += '<div style="height:'+(contentHeight+30)+'px;width:100%">';
					if( row.status >= 3 ){
						returnValue += 'Panel Brand & Model: '+row.quote_to_confirm_panel_brand+'<br/>';
						returnValue += 'Panel Size: '+row.quote_to_confirm_panel_size+' watt, Number: '+row.quote_to_confirm_panel_number+' pcs<br/>';
						returnValue += 'Inverter Brand: '+row.quote_to_confirm_inverter_brand+'<br/>';
						returnValue += 'Inverter Size: '+row.quote_to_confirm_inverter_size+', ';
						returnValue += 'System Size: '+row.quote_to_confirm_system_size+' KW<br/>';
						returnValue += 'Price: $'+row.quote_to_confirm_price+', Payment: '+ row.quote_to_confirm_payment +'<br/>';
						returnValue += '------------------------<br/>';
						returnValue += '<div style="float:left;margin-top:1px;width:98%;height:20px;line-height:20px;font-weight:bolder;background-color:orange;text-align:center;">Confirm Date: '+(row.confirm_date=='null'?'':(row.confirm_date.substring(0,10)))+'</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_quote_info_confirm?'ADFF2F':'f3715c')+'">Quote Info</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_contract_info_confirm?'ADFF2F':'f3715c')+'">Contract Info</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_electric_bill_info_confirm?'ADFF2F':'f3715c')+'">Electric Bill Info</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_meter_box_info_confirm?'ADFF2F':'f3715c')+'">Meter Box Info</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_nearmap_info_confirm?'ADFF2F':'f3715c')+'">Nearmap & Ref Photos</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_install_comment_confirm?'ADFF2F':'f3715c')+'">Install Comment</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:32%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_deposit_confirm?'ADFF2F':'f3715c')+'">Deposit</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:32%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_certegy_confirm?'ADFF2F':'f3715c')+'">Certegy</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:32%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_ASM_confirm?'ADFF2F':'f3715c')+'">ASM</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_dd_info_confirm?'ADFF2F':'f3715c')+'">DD Request Form</div>';
						returnValue += '<div style="border:1px white solid;float:left;width:49%;height:20px;line-height:20px;text-align:center;color:black;background-color:#'+(row.confirm_other_finance_confirm?'ADFF2F':'f3715c')+'">Other Finance Paperwork</div>';
						returnValue += '<div style="float:left;margin-top:5px;width:98%;background-color:orange;font-weight:bolder;overflow-Y:scroll;max-height:100px;">Comment: '+(row.confirm_comment==null?'':row.confirm_comment)+'</div>';
						
					}
					returnValue += '</div>';
					returnValue += '<div style="height:'+(buttonHeight-30)+'px;width:100%">';
					if( row.status >= 3 ){
						if( role == 'admin' )
							returnValue += '<a id="contractConfirmBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_contract_confirm('+row.vid+')">Contract Confirm</a><br/>';
					}
					returnValue += '</div>';
					return returnValue;
				}
		    },
		    {field:'install_info',title:'Connection Approval',width:'20%',
				formatter: function(value,row,index){
					var role = $('#hidden_roleInput').val();
					var returnValue = "";
					returnValue += '<div style="height:'+contentHeight+'px;width:100%;word-break:break-all;">';
					if( row.status >= 4 ){
						returnValue += "WP Status: ";
						returnValue += row.wp_status + "<br/><br/>";
						returnValue += "WP ready to apply date: " + (row.confirm_date=='null'?'':(row.confirm_date.substring(0,10))) + "<br/>"
						returnValue += "WP applied & pending date: " + (row.wp_submit_date=='null'?'':(row.wp_submit_date.substring(0,10))) + "<br/>"
						returnValue += "WP approved date: " + (row.wp_approved_date=='null'?'':(row.wp_approved_date.substring(0,10))) + "<br/><br/>"
						returnValue += "Install Arrange Date: " + (row.install_arrange_date=='null'?'':(row.install_arrange_date.substring(0,10))) + "<br/>";
						returnValue += "To Install Date: " + (row.to_install_date=='null'?'':(row.to_install_date.substring(0,10))) + "<br/>";
						returnValue += '<div style="font-weight:bolder;background-color:orange;">Install Person: ' + (row.install_person=='null'?'':(row.install_person)) + '</div>';
						returnValue += "Install Comment: " + (row.install_comment=='null'?'':(row.install_comment)) + "<br/>";
						returnValue += "Installation Finish Date: " +(row.install_date=='null'?'':(row.install_date.substring(0,10)));
					}
					
					returnValue += '</div>';
					returnValue += '<div style="height:'+buttonHeight+'px;width:100%">';
					if( row.status >= 4 ){
						if( role == 'admin' ){
							if( row.wp_status == "WP ready to apply" )
								returnValue += '<a id="readyForWPBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_ready_for_wp('+row.vid+')">WP ready to apply</a> ';
							else if( row.wp_status == "WP applied & pending" ){
								returnValue += '<a id="approvedForWPBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_approved_for_wp('+row.vid+')">WP Approved</a> ';
								returnValue += '<a id="revokeWPBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_revoke_for_wp('+row.vid+')">WP Revoke</a> ';
							}
							else if( row.wp_status == "WP approved" ){
								returnValue += '<a id="revokeWPBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_revoke_for_wp('+row.vid+')">WP Revoke</a> ';
							}
							
							if( row.status == 5 ){//to insatll
								returnValue += '<br/><a id="startInstallBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_readyToInstall('+row.vid+')">Ready to Install</a> ';
							}
							else if( row.status == 6 ){// install arranged
								returnValue += '<a id="reorderInstallBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_readyToInstall('+row.vid+')">Reorder Install</a>';
								returnValue += '<br/><a id="finishInstallBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_finish_install('+row.vid+')">Finish Install</a> ';
								returnValue += '<a id="installReportDownloadBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_download_install_info('+row.vid+')">Download Install Info</a>';
							}
							else if( row.status >= 7 ){// to finish
								returnValue += '<br/><a id="revokeInstallBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_revoke_for_install('+row.vid+')">Install Revoke</a> ';
								returnValue += '<a id="installReportDownloadBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_download_install_info('+row.vid+')">Download Install Info</a>';
							}
						}
					}
					returnValue += '</div>';
					
					
					
					return returnValue;
				}
		    },
		    {field:'ending',title:'Ending',width:'16%',
				formatter: function(value,row,index){
					var role = $("#hidden_roleInput").val();
					var returnValue = "";
					returnValue += '<div style="height:'+contentHeight+'px;width:100%;word-break:break-all;">';
					if( row.project_status == 'canceled' ){
						returnValue += "Cancel date:<br/>" + row.finish_date + "<br/><br/>"
						returnValue += "Cancel person:<br/>" + row.finish_person + "<br/><br/>"
						returnValue += "Cancel reason:<br/>" + row.finish_remark + "<br/><br/>"
					}
					else if( row.project_status == 'leads reject' ){
						returnValue += "Reject date:<br/>" + row.finish_date + "<br/><br/>"
						returnValue += "Reject person:<br/>" + row.finish_person + "<br/><br/>"
						returnValue += "Reject reason:<br/>" + row.finish_remark + "<br/><br/>"
					}
					else if( row.project_status == 'to finish' && row.wp_status == 'WP approved' ){
//						returnValue += "Installation Finish Date: " + row.install_date.substring(0,10);
						returnValue += '<div style="width:100%;height:30px;">';
						returnValue += '<div style="width:25%;float:left;height:30px;line-height:30px;text-align:center;color:black;background-color:#'+(row.stc_status!='null'?'ADFF2F':'f3715c')+'">STC</div>';
						if(row.stc_status == 'null'){
							if( role == 'admin' )
								returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;text-align:center;">'
											+'<a id="stcConfirmBtn'+row.vid+'" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_stc_confirm_submit('+row.vid+')">Confirm</a>'
											+'</div>';
						}
						else{
							returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;">';
							returnValue += row.stc_date.substring(0,10);
							if( role == 'admin' )
								returnValue += ' <a id="stcRevokeBtn'+row.vid+'" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_stc_revoke_submit('+row.vid+')">Revoke</a>'
							returnValue += '</div>';
						}
						returnValue += '</div>';
						
						returnValue += '<div style="margin-top:10px;width:100%;height:30px;">';
						returnValue += '<div style="width:25%;float:left;height:30px;line-height:30px;text-align:center;color:black;background-color:#'+(row.money_status!='null'?'ADFF2F':'f3715c')+'">Due</div>';
						if(row.money_status == 'null'){
							if( role == 'admin' )
								returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;text-align:center;">'
											+'<a id="moneyConfirmBtn'+row.vid+'" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_due_confirm_submit('+row.vid+')">Confirm</a>'
											+'</div>';
						}
						else{
							returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;">';
							returnValue += row.money_date.substring(0,10);
							if( role == 'admin' )
								returnValue += ' <a id="moneyRevokeBtn'+row.vid+'" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_due_revoke_submit('+row.vid+')">Revoke</a>'
							returnValue += '</div>';
						}
						returnValue += '</div>';
						
						returnValue += '<div style="margin-top:10px;width:100%;height:30px;">';
						returnValue += '<div style="width:25%;float:left;height:30px;line-height:30px;text-align:center;color:black;background-color:#'+(row.issue_status=='null'?'ADFF2F':'f3715c')+'">Issue</div>';
						if(row.issue_status == 'null'){
							if( role == 'admin' )
								returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;text-align:center;">'
											+'<a id="issueConfirmBtn'+row.vid+'" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_issue_confirm_submit('+row.vid+')">Issue</a>'
											+'</div>';
						}
						else{
							returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;">';
							returnValue += row.issue_date.substring(0,10);
							if( role == 'admin' )
								returnValue += ' <a id="issueRevokeBtn'+row.vid+'" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_issue_revoke_submit('+row.vid+')">Revoke</a>'
							returnValue += '</div>';
						}
						returnValue += '</div>';
						if( row.latest_additional_comment )
							returnValue += '<div style="font-weight:bolder;background-color:orange;">Additional Comment:' + row.latest_additional_comment + '</div>';
						
						if( row.money_status == 'complete' && row.stc_status == 'complete' && role == 'admin' ){
							returnValue += '<br/><input id="finishComment'+row.vid+'" class="easyui-textbox" data-options="width:\'96%\',height:100,label:\'Comment:\',labelWidth:60,labelAlign:\'right\',multiline:true"><br/><br/>';
						}
					}
					else if( row.project_status == 'complete' ){
						returnValue += '<div style="width:100%;height:30px;">';
						returnValue += '<div style="width:25%;float:left;height:30px;line-height:30px;text-align:center;color:black;background-color:#'+(row.stc_status!='null'?'ADFF2F':'f3715c')+'">STC</div>';
						returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;text-align:center;">';
						returnValue += row.stc_date.substring(0,10);
						returnValue += '</div>';
						returnValue += '</div>';
						
						returnValue += '<div style="margin-top:10px;width:100%;height:30px;">';
						returnValue += '<div style="width:25%;float:left;height:30px;line-height:30px;text-align:center;color:black;background-color:#'+(row.money_status!='null'?'ADFF2F':'f3715c')+'">Due</div>';
						returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;text-align:center;">';
						returnValue += row.money_date.substring(0,10);
						returnValue += '</div>';
						returnValue += '</div>';
						
						returnValue += '<div style="margin-top:10px;width:100%;height:30px;">';
						returnValue += '<div style="width:25%;float:left;height:30px;line-height:30px;text-align:center;color:black;background-color:#'+(row.issue_status=='null'?'ADFF2F':'f3715c')+'">Issue</div>';
						if(row.issue_status == 'null'){
							if( role == 'admin' )
								returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;text-align:center;">'
											+'<a id="issueConfirmBtn'+row.vid+'" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_issue_confirm_submit('+row.vid+')">Issue</a>'
											+'</div>';
						}
						else{
							returnValue += '<div style="width:74%;float:left;height:30px;line-height:30px;">';
							returnValue += row.issue_date.substring(0,10);
							if( role == 'admin' )
								returnValue += ' <a id="issueRevokeBtn'+row.vid+'" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_issue_revoke_submit('+row.vid+')">Revoke</a>'
							returnValue += '</div>';
						}
						returnValue += '</div>';
						
						returnValue += 'Finish Date: ' + row.finish_date.substring(0,10) + '<br/>';
						returnValue += 'Finish Person: ' + row.finish_person + '<br/>';
						returnValue += 'Comment: ' + row.finish_remark + '<br/>';
						if( row.latest_additional_comment )
							returnValue += '<div style="font-weight:bolder;background-color:orange;">Additional Comment: ' + row.latest_additional_comment + '</div>';
						
						
					}
					returnValue += '</div>';
					returnValue += '<div style="height:'+buttonHeight+'px;width:100%">';
					if( row.status >= 7 && row.wp_status == 'WP approved' ){
						if( row.money_status == 'complete' && row.stc_status == 'complete' && role == 'admin' && row.status == 7 )
							returnValue += '<a id="finishSubmitBtn'+row.vid+'" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_finish_submit('+row.vid+')">Project Complete</a><br/>'
						if( role == 'admin' )
							returnValue += '<a id="addAdditionalCommentBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_add_additional_comment('+row.vid+')">Additional Comments</a>';
					}
					returnValue += '</div>';
					return returnValue;
				}
		    }
		]],
		url: 'projectMag_queryProjectInfo.action',
		nowrap: false,
		onLoadSuccess: function(data){
			$("a[id^='cancelProjectBtn']").linkbutton();
			$("a[id^='deleteProjectBtn']").linkbutton();
			$("a[id^='asignBtn']").linkbutton();
			$("a[id^='contactBtn']").linkbutton();
			$("a[id^='readyToQuoteBtn']").linkbutton();
			$("a[id^='editLeadsBtn']").linkbutton();
			$("a[id^='quoteDetailBtn']").linkbutton();
			$("a[id^='downloadQuotationBtn']").linkbutton();
			$("a[id^='contractConfirmBtn']").linkbutton();
			$("a[id^='readyForWPBtn']").linkbutton();
			$("a[id^='approvedForWPBtn']").linkbutton();
//			$("a[id^='showMapBtn']").linkbutton();
			$("a[id^='startInstallBtn']").linkbutton();
			$("a[id^='revokeWPBtn']").linkbutton();
			$("a[id^='installReportDownloadBtn']").linkbutton();
			$("a[id^='finishInstallBtn']").linkbutton();
			$("a[id^='stcConfirmBtn']").linkbutton();
			$("a[id^='moneyConfirmBtn']").linkbutton();
			$("a[id^='commissionPayBtn']").linkbutton();
			$("a[id^='rejectLeadsBtn']").linkbutton();
			$("a[id^='finishSubmitBtn']").linkbutton();
			$("input[id^='finishComment']").textbox();
			$("a[id^='addAdditionalCommentBtn']").linkbutton();
			$("a[id^='reorderInstallBtn']").linkbutton();
			$("a[id^='revokeInstallBtn']").linkbutton();
			$("a[id^='stcRevokeBtn']").linkbutton();
			$("a[id^='moneyRevokeBtn']").linkbutton();
			$("a[id^='issueConfirmBtn']").linkbutton();
			$("a[id^='issueRevokeBtn']").linkbutton();
			
		},
//		onDblClickCell: function(index,field,value){
//			if( field == "leads_info" ){
//				projectMag_edit_leads_createWindow(index,field,value);
//			}
//		},
		onSelect: function(index,row){
			$("#project_mag_main_table").datagrid('unselectRow',index);
		},
		onClickCell: function(index,field,value){
			$("#project_mag_main_table").datagrid('unselectRow',index);
			$('#project_mag_main_table').parent('.datagrid-view').find("td.datagrid-row-selected").removeClass('datagrid-row-selected');
			$('#datagrid-row-r2-2-'+index).find('td[field=\"'+field+'\"]').addClass("datagrid-row-selected");
			$('#project_mag_main_table').datagrid('options').singleSelect=false;
		}
	});
}
function projectMag_cancelProject(vid,projectId){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm that you want to cancel the project?',
		fn: function(r){
			if (r){
				$("#cancel_form").form('reset');
				$("#cancel_form_vid").val(vid);
				$("#cancel_form_projectIdInput").textbox("setValue",projectId);
				$("#cancel_window").window("open");
			}
		}
	});
}
function projectMag_cancel_project_submit(){
	$('#cancel_form').form('submit', {
	    url: 'projectMag_cancelProject.action',
	    onSubmit: function(){
	    	$("#cancel_form_cancelReasonInput").textbox("setValue",$.trim($("#cancel_form_cancelReasonInput").textbox("getValue")));
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Project cancel SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#cancel_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_deleteProject(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to delete the job? All data will be deleted!',
		fn: function(r){
			if (r){
				$.post('projectMag_deleteProject.action',{
					vid: vid
				},function(data){
					$("#project_mag_main_table").datagrid("reload");
					$.messager.alert('Success', 'Job deleted successfully!', "info");
				});
			}
		}
	});
}
function projectMag_addNew(){
	 $('#project_mag_add_new_window').window({
		width: 830,
		height: 430,
		modal: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Create Project",
		href: 'projectMag_addNewProjectInit.action',
		onLoad:function(){
//			$('#projectMag_addNew_map').append($('#map'));
//			$('#map').css('visibility','visible').css('height','100%').css('width','100%');
//			projectMag_addNew_initMap('abc');
			projectMag_addNew_initMap();
    	}
	});
}
function projectMag_addNew_initMap() {
	map = new google.maps.Map(document.getElementById('projectMag_addNew_map'), {
		zoom : 6,
		center : {
			lat : -31.902675,
			lng : 115.886378
		}
	});
}
function projectMag_addNew_codeAddressByAddress(address) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		address: address,
		componentRestrictions : {
			country : 'AU',
			locality: $('#projectMag_addNew_form_customerSubburbInput').textbox('getValue')
//			postalCode : postcode
		}
	},
	function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if( results.length == 1 ){
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map : map,
					animation: google.maps.Animation.DROP,
					position : results[0].geometry.location
				});
				var street_number = "", route = "";
				var address_components = results[0].address_components;
				for( var i = 0; i < address_components.length; i++ ){
					switch (address_components[i].types[0]){
						case "street_number":
							street_number = address_components[i].long_name;
							break;
						case "route":
							route = address_components[i].long_name;
							break;
						case "locality":
							$('#projectMag_addNew_form_customerSubburbInput').textbox('setValue', address_components[i].long_name);
							break;
						case "administrative_area_level_1":
							$('#projectMag_addNew_form_customerStateInput').textbox('setValue', address_components[i].short_name);
							break;
						case "postal_code":
							$('#projectMag_addNew_form_customerPostcodeInput').textbox('setValue', address_components[i].long_name);
							break;
						default:
							break;
					}
				}
				$('#projectMag_addNew_form_customerAddressLatInput').val(results[0].geometry.location.lat);
				$('#projectMag_addNew_form_customerAddressLngInput').val(results[0].geometry.location.lng);
				$('#projectMag_addNew_form_customerAddressInput').textbox('setValue', street_number+" "+route);
				markers.push(marker);
			}
			else{
				$.messager.alert("ERROR", 'Too many results according to the address. Please Check!', "error");
			}
		} 
		else
			$.messager.alert("ERROR", 'Something is wrong. Please check the address and try again later.', "error");
	});
}
function projectMag_edit_leads_codeAddressByAddress(address){
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		address: address,
		componentRestrictions : {
			country : 'AU',
			locality: $('#projectMag_edit_leads_form_customerSubburbInput').textbox('getValue')
//			postalCode : postcode
		}
	},
	function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if( results.length == 1 ){
				var street_number = "", route = "";
				var address_components = results[0].address_components;
				for( var i = 0; i < address_components.length; i++ ){
					switch (address_components[i].types[0]){
						case "street_number":
							street_number = address_components[i].long_name;
							break;
						case "route":
							route = address_components[i].long_name;
							break;
						case "locality":
							$('#projectMag_edit_leads_form_customerSubburbInput').textbox('setValue', address_components[i].long_name);
							break;
						case "administrative_area_level_1":
							$('#projectMag_edit_leads_form_customerStateInput').textbox('setValue', address_components[i].short_name);
							break;
						case "postal_code":
							$('#projectMag_edit_leads_form_customerPostcodeInput').textbox('setValue', address_components[i].long_name);
							break;
						default:
							break;
					}
				}
				$('#projectMag_edit_leads_form_customerAddressLatInput').val(results[0].geometry.location.lat);
				$('#projectMag_edit_leads_form_customerAddressLngInput').val(results[0].geometry.location.lng);
				$('#projectMag_edit_leads_form_customerAddressInput').textbox('setValue', street_number+" "+route);
			}
			else{
				$.messager.alert("ERROR", 'Too many results according to the address. Please Check!', "error");
			}
		} 
		else
			$.messager.alert("ERROR", 'Something is wrong. Please check the address and try again later.', "error");
	});
}
function projectMag_ready_to_quote_codeAddressByAddress(address){
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		address: address,
		componentRestrictions : {
			country : 'AU',
			locality: $('#projectMag_ready_to_quote_customerSubburbInput').textbox('getValue')
//			postalCode : postcode
		}
	},
	function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if( results.length == 1 ){
				var street_number = "", route = "";
				var address_components = results[0].address_components;
				for( var i = 0; i < address_components.length; i++ ){
					switch (address_components[i].types[0]){
						case "street_number":
							street_number = address_components[i].long_name;
							break;
						case "route":
							route = address_components[i].long_name;
							break;
						case "locality":
							$('#projectMag_ready_to_quote_customerSubburbInput').textbox('setValue', address_components[i].long_name);
							break;
						case "administrative_area_level_1":
							$('#projectMag_ready_to_quote_customerStateInput').textbox('setValue', address_components[i].short_name);
							break;
						case "postal_code":
							$('#projectMag_ready_to_quote_customerPostcodeInput').textbox('setValue', address_components[i].long_name);
							break;
						default:
							break;
					}
				}
				$('#projectMag_ready_to_quote_customerAddressLatInput').val(results[0].geometry.location.lat);
				$('#projectMag_ready_to_quote_customerAddressLngInput').val(results[0].geometry.location.lng);
				$('#projectMag_ready_to_quote_customerAddressInput').textbox('setValue', street_number+" "+route);
			}
			else{
				$.messager.alert("ERROR", 'Too many results according to the address. Please Check!', "error");
			}
		} 
		else
			$.messager.alert("ERROR", 'Something is wrong. Please check the address and try again later.', "error");
	});
}
function projectMag_edit_quote_codeAddressByAddress(address){
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		address: address,
		componentRestrictions : {
			country : 'AU',
			locality: $('#projectMag_edit_quote_customerSubburbInput').textbox('getValue')
//			postalCode : postcode
		}
	},
	function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if( results.length == 1 ){
				var street_number = "", route = "";
				var address_components = results[0].address_components;
				for( var i = 0; i < address_components.length; i++ ){
					switch (address_components[i].types[0]){
						case "street_number":
							street_number = address_components[i].long_name;
							break;
						case "route":
							route = address_components[i].long_name;
							break;
						case "locality":
							$('#projectMag_edit_quote_customerSubburbInput').textbox('setValue', address_components[i].long_name);
							break;
						case "administrative_area_level_1":
							$('#projectMag_edit_quote_customerStateInput').textbox('setValue', address_components[i].short_name);
							break;
						case "postal_code":
							$('#projectMag_edit_quote_customerPostcodeInput').textbox('setValue', address_components[i].long_name);
							break;
						default:
							break;
					}
				}
				$('#projectMag_edit_quote_customerAddressLatInput').val(results[0].geometry.location.lat);
				$('#projectMag_edit_quote_customerAddressLngInput').val(results[0].geometry.location.lng);
				$('#projectMag_edit_quote_customerAddressInput').textbox('setValue', street_number+" "+route);
			}
			else{
				$.messager.alert("ERROR", 'Too many results according to the address. Please Check!', "error");
			}
		} 
		else
			$.messager.alert("ERROR", 'Something is wrong. Please check the address and try again later.', "error");
	});
}
function projectMag_addNew_submit(){
	$('#projectMag_addNew_form').form('submit', {
	    url: 'projectMag_addNewProjectSubmit.action',
	    onSubmit: function(){
	    	$('#projectMag_addNew_form').find('.easyui-textbox').each(function(index,element){
	    		var textbox = $(this);
	    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
	    	});
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Create new project SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#project_mag_add_new_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_addNew_cancel(){
	$('#project_mag_add_new_window').window('close');
}
function makeAsignToWindow(vid,customer,projectId){
	$("#asign_to_form").form('reset');
	$("#asign_to_form_vid").val(vid);
	$("#asign_to_form_projectIdInput").textbox("setValue",projectId);
	$("#asign_to_form_customer").textbox("setValue",customer);
	$("#asign_to_window").window("open");
}
function projectMag_asignTo_submit(){
	$('#asign_to_form').form('submit', {
	    url: 'projectMag_asignToSubmit.action',
	    onSubmit: function(){
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Project asigned SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#asign_to_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_asignTo_cancel(){
	$('#asign_to_window').window('close');
}
function makeContactWindow(vid,projectId){
	$("#contact_form").form('reset');
	$("#contact_form_vid").val(vid);
	$("#contact_form_projectIdInput").textbox("setValue",projectId);
	$("#contact_window").window("open");
}
function projectMag_contact_submit(){
	$('#contact_form').form('submit', {
	    url: 'projectMag_contactSubmit.action',
	    onSubmit: function(){
	    	$("#contact_form_contactCommentInput").textbox("setValue",$.trim($("#contact_form_contactCommentInput").textbox("getValue")));
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Project contact SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#contact_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_contact_cancel(){
	$('#contact_window').window('close');
}
function projectMag_rejectLeads(vid,projectId){
	$("#rejectLeads_form").form('reset');
	$("#rejectLeads_form_vid").val(vid);
	$("#rejectLeads_form_projectIdInput").textbox("setValue",projectId);
	$("#rejectLeads_window").window("open");
}
function projectMag_rejectLeads_submit(){
	$('#rejectLeads_form').form('submit', {
	    url: 'projectMag_rejectLeadsSubmit.action',
	    onSubmit: function(){
	    	$("#rejectLeads_form_finishRemarkInput").textbox("setValue",$.trim($("#rejectLeads_form_finishRemarkInput").textbox("getValue")));
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Reject Leads SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#rejectLeads_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_rejectLeads_cancel(){
	$('#rejectLeads_window').window('close');
}
function projectMag_edit_leads_createWindow(vid){
	$('#project_mag_edit_leads_window').window({
		width: 830,
		height: 440,
		modal: true,
		draggable: true,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Edit Leads Info",
		href: 'projectMag_editLeadsInfoInit.action',
		queryParams:{
			vid: vid
		}
	});
}
function projectMag_edit_leads_submit(){
	$('#projectMag_edit_leads_form').form('submit', {
	    url: 'projectMag_editLeadsInfoSubmit.action',
	    onSubmit: function(){
	    	$('#projectMag_edit_leads_form').find('.easyui-textbox').each(function(index,element){
	    		var textbox = $(this);
	    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
	    	});
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Modification submit SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
						$('#project_mag_edit_leads_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	        else{
	        	$.messager.alert({
	        		title: 'Info',
	        		msg: 'Nothing has been modified!',
					icon: 'info'
	        	});
	        }
	    }
	});
}
function projectMag_edit_leads_confirm(){
	$('#projectMag_edit_leads_form').form('submit', {
	    url: 'projectMag_editLeadsInfoConfirm.action',
	    onSubmit: function(){
//	    	$("#projectMag_edit_leads_form_customerNameInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerNameInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_customerPhoneInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerPhoneInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_customerAddressInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerAddressInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_customerSubburbInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerSubburbInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_customerPostcodeInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerPostcodeInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_customerStateInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerStateInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_customerEmailInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_customerEmailInput").textbox("getValue")));
//	    	$("#projectMag_edit_leads_form_leadsCommentInput").textbox("setValue",$.trim($("#projectMag_edit_leads_form_leadsCommentInput").textbox("getValue")));
	    	$('#projectMag_edit_leads_form').find('.easyui-textbox').each(function(index,element){
	    		var textbox = $(this);
	    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
	    	});
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Modification confirm SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
						$('#project_mag_edit_leads_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	        else{
	        	$.messager.alert({
	        		title: 'Info',
	        		msg: 'Nothing has been modified!',
					icon: 'info'
	        	});
	        }
	    }
	});
}
function projectMag_edit_leads_cancel(){
	$('#project_mag_edit_leads_window').window('close');
}
function projectMag_readyToQuote_isChanged(newValue,oldValue,input){
	if( newValue != oldValue ){
		var label = $(input).textbox('options').label;
		var changedField = $('#projectMag_ready_to_quote_changedHidden').val();
		if( changedField.indexOf(label) < 0 ){//修改未登记
			var id = $(input).attr('id');
			var changedFieldID = $('#projectMag_ready_to_quote_changedFieldIdHidden').val();
			$('#projectMag_ready_to_quote_changedHidden').val((changedField==""?"":(changedField+";"))+label.substring(0,label.length-1));
			$('#projectMag_ready_to_quote_changedFieldIdHidden').val((changedFieldID==""?"":(changedFieldID+";"))+id);
		}
		
	}
}
function projectMag_readyToQuoteConfirm(vid){
	$('#project_mag_ready_to_quote_window').window({
		width: 900,
		height: 550,
		modal: true,
		maximized: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Quote",
		href: 'projectMag_readyToQuote.action',
		queryParams:{
			vid: vid
		}
	});
}
function projectMag_ready_to_quote_submit(){
	if( $('#projectMag_ready_to_quote_changedHidden').val() != "" ){
		$.messager.confirm({
			title: 'Confirm the change',
			msg: $('#projectMag_ready_to_quote_changedHidden').val().replace(';','、')+' changed. Do you confirm these changes? <br/>The operation will generate a change record of the leads info.',
			fn: function(r){
				if (!r){
					var changedFieldArray = $('#projectMag_ready_to_quote_changedFieldIdHidden').val().split(';');
					for( var i = 0; i < changedFieldArray.length; i++ )
						$('#'+changedFieldArray[i]).textbox('reset');
					$('#projectMag_ready_to_quote_changedHidden').val('');
					$('#projectMag_ready_to_quote_changedFieldIdHidden').val('');
				}
				$('#projectMag_ready_to_quote_form').form('submit', {
				    url: 'projectMag_readyToQuoteSubmit.action',
				    onSubmit: function(){
				    	$('#projectMag_ready_to_quote_form').find('.easyui-textbox').each(function(index,element){
				    		var textbox = $(this);
				    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
				    	});
				    	$(".easyui-numberspinner[id^='projectMag_ready_to_quote_priceInput']").each(function(index,element){
				    		var panel = $(this).parent();
				    		if($(this).numberspinner('getValue')==""){
				    			panel.find('.easyui-textbox').textbox('disableValidation');
				    			panel.find('.easyui-numberspinner').numberspinner('disableValidation');
				    			panel.find('.easyui-combobox').combobox('disableValidation');
				    		}
				    		else{
				    			panel.find('.easyui-textbox').textbox('enableValidation');
				    			panel.find('.easyui-numberspinner').numberspinner('enableValidation');
				    			panel.find('.easyui-combobox').combobox('enableValidation');
				    		}
				    	});
				    	return $(this).form('validate');
			        },
				    success:function(data){
				    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
				        if (result.success){
				        	$.messager.alert({
				        		title: 'Success',
				        		msg: 'Quote info submit SUCCESSFULLY!',
								icon: 'info',
								fn: function(){
									$('#project_mag_ready_to_quote_window').window('close');
					        		$("#project_mag_main_table").datagrid("reload");
					        	}
				        	});
				        }
				    }
				});
			}
		});
	}
	else{
		$('#projectMag_ready_to_quote_form').form('submit', {
		    url: 'projectMag_readyToQuoteSubmit.action',
		    onSubmit: function(){
		    	$('#projectMag_ready_to_quote_form').find('.easyui-textbox').each(function(index,element){
		    		var textbox = $(this);
		    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
		    	});
		    	$(".easyui-numberspinner[id^='projectMag_ready_to_quote_priceInput']").each(function(index,element){
		    		var panel = $(this).parent();
		    		if($(this).numberspinner('getValue')==""){
		    			panel.find('.easyui-textbox').textbox('disableValidation');
		    			panel.find('.easyui-numberspinner').numberspinner('disableValidation');
		    			panel.find('.easyui-combobox').combobox('disableValidation');
		    		}
		    		else{
		    			panel.find('.easyui-textbox').textbox('enableValidation');
		    			panel.find('.easyui-numberspinner').numberspinner('enableValidation');
		    			panel.find('.easyui-combobox').combobox('enableValidation');
		    		}
		    	});
		    	return $(this).form('validate');
	        },
		    success:function(data){
		    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
		        if (result.success){
		        	$.messager.alert({
		        		title: 'Success',
		        		msg: 'Quote info submit SUCCESSFULLY!',
						icon: 'info',
						fn: function(){
							$('#project_mag_ready_to_quote_window').window('close');
			        		$("#project_mag_main_table").datagrid("reload");
			        	}
		        	});
		        }
		    }
		});
	}
}
function projectMag_ready_to_quote_cancel(){
	$('#project_mag_ready_to_quote_window').window('close');
}
function projectMag_ready_to_quote_calc_systemsize(target){
	var idNum = $(target).attr('id').substr(-1);
	var size = $('#projectMag_ready_to_quote_panelSizeInput'+idNum).numberspinner('getValue');
	var number = $('#projectMag_ready_to_quote_panelNumberInput'+idNum).numberspinner('getValue');
	if( "" != number && "" != size ){
		$('#projectMag_ready_to_quote_systemSizeInput'+idNum).numberspinner('setValue',(size*number/1000.00).toFixed(3));
	}
}
function projectMag_quote_detail(vid){
	$('#project_mag_quote_detail_window').window({
		width: 900,
		height: 550,
		modal: true,
		maximized: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Quote",
		href: 'projectMag_showQuoteDetail.action',
		queryParams:{
			vid: vid
		}
	});
}
function projectMag_editQuote_isChanged(newValue,oldValue,input){
	if( newValue != oldValue ){
		var label = $(input).textbox('options').label;
		var changedField = $('#projectMag_edit_quote_changedHidden').val();
		if( changedField.indexOf(label) < 0 ){//修改未登记
			var id = $(input).attr('id');
			var changedFieldID = $('#projectMag_edit_quote_changedFieldIdHidden').val();
			$('#projectMag_edit_quote_changedHidden').val((changedField==""?"":(changedField+";"))+label.substring(0,label.length-1));
			$('#projectMag_edit_quote_changedFieldIdHidden').val((changedFieldID==""?"":(changedFieldID+";"))+id);
		}
		
	}
}
function projectMag_edit_quote_calc_systemsize(target){
	var idNum = $(target).attr('id').substr(-1);
	var size = $('#projectMag_edit_quote_panelSizeInput'+idNum).numberspinner('getValue');
	var number = $('#projectMag_edit_quote_panelNumberInput'+idNum).numberspinner('getValue');
	if( "" != number && "" != size ){
		$('#projectMag_edit_quote_systemSizeInput'+idNum).numberspinner('setValue',(size*number/1000.00).toFixed(3));
	}
}
function projectMag_edit_quote_submit(){
	if( $('#projectMag_edit_quote_changedHidden').val() != "" ){
		$.messager.confirm({
			title: 'Confirm the change',
			msg: 'Basic Info has been changed. Do you confirm these changes? <br/>The operation will generate a change record of the leads info.',
			fn: function(r){
				if (!r){
					var changedFieldArray = $('#projectMag_edit_quote_changedFieldIdHidden').val().split(';');
					for( var i = 0; i < changedFieldArray.length; i++ )
						$('#'+changedFieldArray[i]).textbox('reset');
					$('#projectMag_edit_quote_changedHidden').val('');
					$('#projectMag_edit_quote_changedFieldIdHidden').val('');
				}
				$('#projectMag_edit_quote_form').form('submit', {
				    url: 'projectMag_editQuoteSubmit.action',
				    onSubmit: function(){
				    	$('#projectMag_edit_quote_form').find('.easyui-textbox').each(function(index,element){
				    		var textbox = $(this);
				    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
				    	});
				    	$(".easyui-numberspinner[id^='projectMag_edit_quote_priceInput']").each(function(index,element){
				    		var panel = $(this).parent();
				    		if($(this).numberspinner('getValue')==""){
				    			panel.find('.easyui-textbox').textbox('disableValidation');
				    			panel.find('.easyui-numberspinner').numberspinner('disableValidation');
				    			panel.find('.easyui-combobox').combobox('disableValidation');
				    		}
				    		else{
				    			panel.find('.easyui-textbox').textbox('enableValidation');
				    			panel.find('.easyui-numberspinner').numberspinner('enableValidation');
				    			panel.find('.easyui-combobox').combobox('enableValidation');
				    		}
				    	});
				    	return $(this).form('validate');
			        },
				    success:function(data){
				    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
				        if (result.success){
				        	$.messager.alert({
				        		title: 'Success',
				        		msg: 'Quote info edit SUCCESSFULLY!',
								icon: 'info',
								fn: function(){
									$('#project_mag_quote_detail_window').window('close');
					        		$("#project_mag_main_table").datagrid("reload");
					        	}
				        	});
				        }
				    }
				});
			}
		});
	}
	else{
		$('#projectMag_edit_quote_form').form('submit', {
		    url: 'projectMag_editQuoteSubmit.action',
		    onSubmit: function(){
		    	$('#projectMag_edit_quote_form').find('.easyui-textbox').each(function(index,element){
		    		var textbox = $(this);
		    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
		    	});
		    	$(".easyui-numberspinner[id^='projectMag_edit_quote_priceInput']").each(function(index,element){
		    		var panel = $(this).parent();
		    		if($(this).numberspinner('getValue')==""){
		    			panel.find('.easyui-textbox').textbox('disableValidation');
		    			panel.find('.easyui-numberspinner').numberspinner('disableValidation');
		    			panel.find('.easyui-combobox').combobox('disableValidation');
		    		}
		    		else{
		    			panel.find('.easyui-textbox').textbox('enableValidation');
		    			panel.find('.easyui-numberspinner').numberspinner('enableValidation');
		    			panel.find('.easyui-combobox').combobox('enableValidation');
		    		}
		    	});
		    	return $(this).form('validate');
	        },
		    success:function(data){
		    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
		        if (result.success){
		        	$.messager.alert({
		        		title: 'Success',
		        		msg: 'Quote info edit SUCCESSFULLY!',
						icon: 'info',
						fn: function(){
							$('#project_mag_quote_detail_window').window('close');
			        		$("#project_mag_main_table").datagrid("reload");
			        	}
		        	});
		        }
		    }
		});
	}
}
function projectMag_edit_quote_cancel(){
	$('#project_mag_quote_detail_window').window('close');
}
function projectMag_edit_select_button_change(target,checked){
	if(checked){
		var form = $('#projectMag_edit_quote_form');
		form.find('.easyui-switchbutton[id!="'+$(target).attr('id')+'"]').switchbutton('uncheck').parent().parent().removeClass('datagrid-row-selected');
		$(target).parent().parent().addClass('datagrid-row-selected');
	}
	else{
		$(target).switchbutton('uncheck').parent().parent().removeClass('datagrid-row-selected');
	}
}
function projectMag_download_quotation(vid){
	var form = document.getElementById('empty_form');
	form.innerHTML = "";
	$(form).attr('action','projectMag_downloadQuotation.action');
	$(form).html('<input type="hidden" name="vid" value="'+vid+'"/>');
	form.submit();
}
function projectMag_ready_to_sign(){
	var panel = $('#projectMag_edit_quote_form').find('.datagrid-row-selected');
	if( panel.length > 0 ){
		if( panel.find('[id^="projectMag_edit_quote_priceInput"]').numberspinner('getValue') != '' ){
			if( $('#projectMag_edit_quote_changedHidden').val() != "" ){
				$.messager.confirm({
					title: 'Confirm the change',
					msg: 'Basic Info has been changed. Do you confirm these changes? <br/>The operation will generate a change record of the leads info.',
					fn: function(r){
						if (!r){
							var changedFieldArray = $('#projectMag_edit_quote_changedFieldIdHidden').val().split(';');
							for( var i = 0; i < changedFieldArray.length; i++ )
								$('#'+changedFieldArray[i]).textbox('reset');
							$('#projectMag_edit_quote_changedHidden').val('');
							$('#projectMag_edit_quote_changedFieldIdHidden').val('');
						}
						$('#projectMag_edit_quote_form').form('submit', {
						    url: 'projectMag_editQuoteSubmit.action',
						    onSubmit: function(){
						    	$('#projectMag_edit_quote_form').find('.easyui-textbox').each(function(index,element){
						    		var textbox = $(this);
						    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
						    	});
						    	$(".easyui-numberspinner[id^='projectMag_edit_quote_priceInput']").each(function(index,element){
						    		var panel = $(this).parent();
						    		if($(this).numberspinner('getValue')==""){
						    			panel.find('.easyui-textbox').textbox('disableValidation');
						    			panel.find('.easyui-numberspinner').numberspinner('disableValidation');
						    			panel.find('.easyui-combobox').combobox('disableValidation');
						    		}
						    		else{
						    			panel.find('.easyui-textbox').textbox('enableValidation');
						    			panel.find('.easyui-numberspinner').numberspinner('enableValidation');
						    			panel.find('.easyui-combobox').combobox('enableValidation');
						    		}
						    	});
						    	return $(this).form('validate');
					        },
						    success:function(data){
						    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
						    	var vidsArray = result.vids.reverse();
						    	var pricesInput = new Array();
						    	$('[id^="projectMag_edit_quote_priceInput"]').each(function(index,element){
						    		if( !($(this).numberspinner('options').disabled) ){
						    			$(this).parent().find('input[id^="projectMag_edit_quote_quoteRecordVid"]').val(vidsArray.pop());
						    		}
						    	});
						    	$.post('projectMag_readyToSign.action',{
						    		vid: $('#project_vid').val(),
						    		quote_record_vid: $('#projectMag_edit_quote_form').find('.datagrid-row-selected').find('input[id^="projectMag_edit_quote_quoteRecordVid"]').val()
						    	},function(data){
									var result = eval('(' + data + ')');
									$.messager.alert("Success", 'Quote plan submit successfully! The admin will confirm soon!', "info");
									$('#project_mag_quote_detail_window').window('close');
					        		$("#project_mag_main_table").datagrid("reload");
								});
						    }
						});
					}
				});
			}
			else{
				$('#projectMag_edit_quote_form').form('submit', {
				    url: 'projectMag_editQuoteSubmit.action',
				    onSubmit: function(){
				    	$('#projectMag_edit_quote_form').find('.easyui-textbox').each(function(index,element){
				    		var textbox = $(this);
				    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
				    	});
				    	$(".easyui-numberspinner[id^='projectMag_edit_quote_priceInput']").each(function(index,element){
				    		var panel = $(this).parent();
				    		if($(this).numberspinner('getValue')==""){
				    			panel.find('.easyui-textbox').textbox('disableValidation');
				    			panel.find('.easyui-numberspinner').numberspinner('disableValidation');
				    			panel.find('.easyui-combobox').combobox('disableValidation');
				    		}
				    		else{
				    			panel.find('.easyui-textbox').textbox('enableValidation');
				    			panel.find('.easyui-numberspinner').numberspinner('enableValidation');
				    			panel.find('.easyui-combobox').combobox('enableValidation');
				    		}
				    	});
				    	return $(this).form('validate');
			        },
			        success:function(data){
				    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
				    	var vidsArray = result.vids.reverse();
				    	var pricesInput = new Array();
				    	$('[id^="projectMag_edit_quote_priceInput"]').each(function(index,element){
				    		if( !($(this).numberspinner('options').disabled) ){
				    			$(this).parent().find('input[id^="projectMag_edit_quote_quoteRecordVid"]').val(vidsArray.pop());
				    		}
				    	});
				    	$.post('projectMag_readyToSign.action',{
				    		vid: $('#project_vid').val(),
				    		quote_record_vid: $('#projectMag_edit_quote_form').find('.datagrid-row-selected').find('input[id^="projectMag_edit_quote_quoteRecordVid"]').val()
				    	},function(data){
							var result = eval('(' + data + ')');
							$.messager.alert("Success", 'Quote plan submit successfully! The admin will confirm soon!', "info");
							$('#project_mag_quote_detail_window').window('close');
			        		$("#project_mag_main_table").datagrid("reload");
						});
				    }
				});
			}
		}
		else{
			$.messager.alert('ERROR','The quote needs a price!','error');
			panel.find('[id^="projectMag_edit_quote_selectBtn"]').switchbutton('uncheck');
		}
	}
	else{
		$.messager.alert('Choose a plan','No plan has been chosen!','error');
	}
}
function projectMag_contract_confirm(vid){
	$('#project_mag_quote_confirm_window').window({
		width: 900,
		height: 600,
		modal: true,
//		maximized: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Quote",
		href: 'projectMag_quoteConfirmInit.action',
		queryParams:{
			vid: vid
		},
		onBeforeClose:function(){
			$("#project_mag_main_table").datagrid("reload");
		}
	});
}
function projectMag_contract_confirm_submit(vid,type,button){
	$.post('projectMag_contractConfirmSubmit.action',{
		vid:vid,
		type:type
	},function(data){
		var result = eval('(' + data + ')');
		if(result.success){
			var td = $(button).parent();
			td.prev('td').css('background-color','#ADFF2F');
			td.html('<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="projectMag_contract_confirm_revoke(\''+vid+'\','+type+',this)">Revoke Confirm</a>')
			td.find('.easyui-linkbutton').linkbutton();
		}
	});
}
function projectMag_contract_confirm_comment_submit(vid,button){
	$.post('projectMag_contractConfirmCommentSubmit.action',{
		vid:vid,
		comment:$('#projectMag_contract_confirm_adminCommentInput').textbox('getValue')
	},function(data){
		var result = eval('(' + data + ')');
		if(result.success){
			$.messager.alert('Success', 'Admin comment submit successfully!', "info");
		}
	});
}
function projectMag_contract_confirm_revoke(vid,type,button){
	$.post('projectMag_contractConfirmRevoke.action',{
		vid:vid,
		type:type
	},function(data){
		var result = eval('(' + data + ')');
		if(result.success){
			var td = $(button).parent();
			td.prev('td').css('background-color','#f3715c');
			td.html('<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="projectMag_contract_confirm_submit(\''+vid+'\','+type+',this)">Confirm</a>')
			td.find('.easyui-linkbutton').linkbutton();
		}
//		$.messager.alert("Success", result.msg, "info");
	});
}
function projectMag_ready_for_wp(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to submit for WP?',
		fn: function(r){
			if (r){
				$.post('projectMag_readyForWP.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_approved_for_wp(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm that WP has been approved?',
		fn: function(r){
			if (r){
				$.post('projectMag_approvedForWP.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_revoke_for_wp(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to revoke?',
		fn: function(r){
			if (r){
				$.post('projectMag_revokeForWP.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}

function projectMag_readyToInstall(vid){
	$('#project_mag_ready_to_install_window').window({
		width: 430,
		height: 450,
		modal: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Ready to install",
		href: 'projectMag_readyToInstallInit.action',
		queryParams:{
			vid: vid
		},
//		onLoad:function(){
//			$('#projectMag_addNew_map').append($('#map'));
//			$('#map').css('visibility','visible').css('height','100%').css('width','100%');
//			projectMag_addNew_initMap('abc');
//			projectMag_addNew_initMap();
//    	}
	});
}
function projectMag_ready_to_install_submit(){
	$('#projectMag_ready_to_install_form').form('submit', {
	    url: 'projectMag_readyToInstallSubmit.action',
	    onSubmit: function(){
	    	$('#projectMag_ready_to_install_form').find('.easyui-textbox').each(function(index,element){
	    		var textbox = $(this);
	    		textbox.textbox('setValue',$.trim(textbox.textbox('getValue')));
	    	});
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Installation arranged SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#project_mag_ready_to_install_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_revoke_for_install(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to revoke finish install?',
		fn: function(r){
			if (r){
				$.post('projectMag_revokeForInstall.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
//function projectMag_edit_quote_cancel(){
//	$('#project_mag_ready_to_install_window').window('close');
//}
function projectMag_finish_install(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to finish the installation?',
		fn: function(r){
			if (r){
				$.post('projectMag_finishInstall.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
//function projectMag_install_show_map(){
function searchProjectAndShowInMap(){
	$('#project_mag_install_show_map_window').window({
		width: 900,
		height: 500,
		modal: true,
		maximized: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Project Info",
		href: 'projectMag_installShowMapInit.action',
		onLoad: function(){
			$('#project_mag_to_install_table').datagrid({
				columns:[[
					{field:'vid',hidden:true},
				    {field:'customer_info',title:'Customer Info',width:'35%',
						formatter: function(value,row,index){
							var returnValue = "";
							var role = $("#hidden_roleInput").val();
							returnValue += 'Project ID: ' + row.project_id + "<br/><br/>";
							returnValue += 'Customer Name: ' + row.customer_name + "<br/>";
							returnValue += 'Suburb: ' + row.customer_subburb + "<br/>";
							returnValue += 'Address: ' + row.customer_address + "<br/>";
							returnValue += 'Roof Type: ' + (row.customer_roof_type?row.customer_roof_type:"") + "<br/>";
							returnValue += 'Phase Type: ' + (row.customer_phase_type?row.customer_phase_type:"") + "<br/>";
							return returnValue;
						}
				    },
				    {field:'project_info',title:'Project Info',width:'35%',
						formatter: function(value,row,index){
							var returnValue = "";
							var role = $("#hidden_roleInput").val();
							
							returnValue += 'Panel Brand & Model: '+(row.quote_to_confirm_panel_brand?row.quote_to_confirm_panel_brand:'')+'<br/>';
							returnValue += 'Panel Size: '+(row.quote_to_confirm_panel_size?row.quote_to_confirm_panel_size:'')+' watt<br/>';
							returnValue += 'Number: '+(row.quote_to_confirm_panel_number?row.quote_to_confirm_panel_number:'')+' pcs<br/>';
							returnValue += 'Inverter Brand: '+(row.quote_to_confirm_inverter_brand?row.quote_to_confirm_inverter_brand:'')+'<br/>';
							returnValue += 'Inverter Size: '+(row.quote_to_confirm_inverter_size?row.quote_to_confirm_inverter_size:'')+'<br/>';
							returnValue += 'System Size: '+(row.quote_to_confirm_system_size?row.quote_to_confirm_system_size:'')+' KW<br/>';
							returnValue += 'Price: $'+(row.quote_to_confirm_price?row.quote_to_confirm_price:'')+'<br/>';
							returnValue += 'Deposit: $'+(row.quote_to_confirm_deposit?row.quote_to_confirm_deposit:'')+'<br/>';
							returnValue += 'Payment: '+ (row.quote_to_confirm_payment?row.quote_to_confirm_payment:'')+'<br/>';
							returnValue += 'Comment: '+ (row.quote_to_confirm_comment?row.quote_to_confirm_comment:'')+'';
							return returnValue;
						}
				    },
				    {field:'install',title:'Install',width:'30%',
						formatter: function(value,row,index){
							var returnValue = "";
							var role = $("#hidden_roleInput").val();
							returnValue += "WP Status:<br/>";
							returnValue += row.wp_status=='null'?"":row.wp_status + "<br/><br/>";
//							if( role == 'admin' ){
//								returnValue += '<a id="readyToInstallBtn'+row.vid+'" href="javascript:void(0)" onclick="projectMag_readyToInstall('+row.vid+')">Ready to Install</a>';
//							}
							return returnValue;
						}
				    }
				]],
//				url:'projectMag_queryProjectInfoToInstall.action',
				url: 'projectMag_queryProjectInfo.action',
				queryParams:{
					project_status: $('#project_mag_main_table_toolbar_form_statusCombo').combobox('getValue'),
					address: $('#project_mag_main_table_toolbar_form_addressInput').textbox('getValue'),
					customer_name: $('#project_mag_main_table_toolbar_form_customerNameInput').textbox('getValue'),
					customer_phone: $('#project_mag_main_table_toolbar_form_customerPhoneInput').textbox('getValue'),
					postcode: $('#project_mag_main_table_toolbar_form_postcodeInput').textbox('getValue'),
					subburb: $('#project_mag_main_table_toolbar_form_subburbInput').textbox('getValue'),
					asign_to: $('#project_mag_main_table_toolbar_form_asignToCombo').combobox('getValue'),
					installer: $('#project_mag_main_table_toolbar_form_installerCombo').combobox('getValue'),
					door_knocker: $('#project_mag_main_table_toolbar_form_doorKnockerCombo').combobox('getValue'),
					panel_brand: $('#project_mag_main_table_toolbar_form_panelNameCombo').combobox('getValue'),
					inverter_brand: $('#project_mag_main_table_toolbar_form_inverterNameCombo').combobox('getValue'),
					create_from: $('#project_mag_main_table_toolbar_form_createDateFromDatebox').datebox('getValue'),
					create_to: $('#project_mag_main_table_toolbar_form_createDateToDatebox').datebox('getValue'),
					asign_from: $('#project_mag_main_table_toolbar_form_asignDateFromDatebox').datebox('getValue'),
					asign_date_to: $('#project_mag_main_table_toolbar_form_asignDateToDatebox').datebox('getValue'),
					contract_from: $('#project_mag_main_table_toolbar_form_contractDateFromDatebox').datebox('getValue'),
					contract_to: $('#project_mag_main_table_toolbar_form_contractDateToDatebox').datebox('getValue'),
					approved_from: $('#project_mag_main_table_toolbar_form_approvedDateFromDatebox').datebox('getValue'),
					approved_to: $('#project_mag_main_table_toolbar_form_approvedDateToDatebox').datebox('getValue'),
					install_from: $('#project_mag_main_table_toolbar_form_installDateFromDatebox').datebox('getValue'),
					install_to: $('#project_mag_main_table_toolbar_form_installDateToDatebox').datebox('getValue')
				},
				rowStyler: function(index,row){
					return 'height:250px;';
				},
				onLoadSuccess: function(data){
//					$("a[id^='readyToInstallBtn']").linkbutton();
					current_marker = null;
					map = new google.maps.Map(document.getElementById('project_mag_to_install_map'), {
						zoom : 5,
						center : {
							lat : -31.902675,
							lng : 115.886378
						},
						zoomControl: true,
						mapTypeControl: true,
						scaleControl: true,
						streetViewControl: false,
						rotateControl: true
					});
					markers = data.rows.map(function(row, i) {
						var lat = parseFloat(row.customer_address_lat);
						var lng = parseFloat(row.customer_address_lng);
						var latLng = new google.maps.LatLng(lat,lng);
						var marker = new google.maps.Marker({
							position: latLng,
							icon: 'resources/images/yellow_marker.png',
							id: i,
							animation: google.maps.Animation.DROP,
							map: map
						});
						marker.addListener('click', function() {
							$('#project_mag_to_install_table').datagrid('selectRow',this.id);
						});
						return marker;
			        });
//					var markerCluster = new MarkerClusterer(map, markers,
//			            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
					
				},
				onSelect: function(index, row){
					if(current_marker){
						current_marker.setAnimation(null);
						current_marker.setIcon('resources/images/yellow_marker.png');
					}
					markers[index].setIcon('resources/images/blue_marker.png');
					markers[index].setAnimation(google.maps.Animation.BOUNCE);
					current_marker = markers[index];
				}
			});
//			$('#project_mag_to_install_table').datagrid({})
//			$("#project_mag_to_install_table").datagrid('load',{
//				project_status: $('#project_mag_main_table_toolbar_form_statusCombo').combobox('getValue'),
//				address: $('#project_mag_main_table_toolbar_form_addressInput').textbox('getValue'),
//				customer_name: $('#project_mag_main_table_toolbar_form_customerNameInput').textbox('getValue'),
//				customer_phone: $('#project_mag_main_table_toolbar_form_customerPhoneInput').textbox('getValue'),
//				postcode: $('#project_mag_main_table_toolbar_form_postcodeInput').textbox('getValue'),
//				subburb: $('#project_mag_main_table_toolbar_form_subburbInput').textbox('getValue'),
//				asign_to: $('#project_mag_main_table_toolbar_form_asignToCombo').combobox('getValue'),
//				installer: $('#project_mag_main_table_toolbar_form_installerCombo').combobox('getValue'),
//				door_knocker: $('#project_mag_main_table_toolbar_form_doorKnockerCombo').combobox('getValue'),
//				panel_brand: $('#project_mag_main_table_toolbar_form_panelNameCombo').combobox('getValue'),
//				inverter_brand: $('#project_mag_main_table_toolbar_form_inverterNameCombo').combobox('getValue'),
//				create_from: $('#project_mag_main_table_toolbar_form_createDateFromDatebox').datebox('getValue'),
//				create_to: $('#project_mag_main_table_toolbar_form_createDateToDatebox').datebox('getValue'),
//				asign_from: $('#project_mag_main_table_toolbar_form_asignDateFromDatebox').datebox('getValue'),
//				asign_date_to: $('#project_mag_main_table_toolbar_form_asignDateToDatebox').datebox('getValue'),
//				contract_from: $('#project_mag_main_table_toolbar_form_contractDateFromDatebox').datebox('getValue'),
//				contract_to: $('#project_mag_main_table_toolbar_form_contractDateToDatebox').datebox('getValue'),
//				approved_from: $('#project_mag_main_table_toolbar_form_approvedDateFromDatebox').datebox('getValue'),
//				approved_to: $('#project_mag_main_table_toolbar_form_approvedDateToDatebox').datebox('getValue'),
//				install_from: $('#project_mag_main_table_toolbar_form_installDateFromDatebox').datebox('getValue'),
//				install_to: $('#project_mag_main_table_toolbar_form_installDateToDatebox').datebox('getValue')
//			});
		}
	});
}
function searchProjectForMap(){
	$('#project_mag_to_install_table').datagrid('load',{
		project_status:$('#project_mag_to_install_table_toolbar_statusCombo').combobox('getValue')
	});
}
function projectMag_download_install_info(vid){
	var form = document.getElementById('empty_form');
	form.innerHTML = "";
	$(form).attr('action','projectMag_downloadInstallInfo.action');
	$(form).html('<input type="hidden" name="vid" value="'+vid+'"/>');
	form.submit();
}
function projectMag_commissionPay(vid,projectId){
	$("#commission_form").form('reset');
	$("#commission_form_vid").val(vid);
	$("#commission_form_projectIdInput").textbox("setValue",projectId);
	$("#commission_window").window("open");
}
function projectMag_commission_submit(){
	$('#commission_form').form('submit', {
	    url: 'projectMag_commissionSubmit.action',
	    onSubmit: function(){
	    	$("#commission_form_commissionValueInput").numberspinner("setValue",$.trim($("#commission_form_commissionValueInput").numberspinner("getValue")));
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Commission paid SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#commission_window').window('close');
		        		$("#project_mag_main_table").datagrid("reload");
		        	}
	        	});
	        }
	    }
	});
}
function projectMag_commission_cancel(){
	$('#commission_window').window('close');
}
function projectMag_installDownloadBat(){
	var checkbox = $('input[id="installDownloadBatCheckBox"]:checked');
	if( checkbox.length > 0 ){
		var form = document.getElementById('empty_form');
		form.innerHTML = "";
		$(form).attr('action','projectMag_installDownloadBat.action');
		$(form).append($('input[id="installDownloadBatCheckBox"]').clone());
		form.submit();
	}
	else
		$.messager.alert("ERROR", 'At least one project should be selected!', "error");
	
}
function projectMag_stc_confirm_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm that STC is complete?',
		fn: function(r){
			if (r){
				$.post('projectMag_stcConfirmSubmit.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_stc_revoke_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to revoke STC?',
		fn: function(r){
			if (r){
				$.post('projectMag_revokeForSTC.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_due_revoke_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to revoke Due?',
		fn: function(r){
			if (r){
				$.post('projectMag_revokeForDue.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_due_confirm_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm that payment due is complete?',
		fn: function(r){
			if (r){
				$.post('projectMag_dueConfirmSubmit.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_issue_revoke_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to revoke Issue?',
		fn: function(r){
			if (r){
				$.post('projectMag_revokeForIssue.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_issue_confirm_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm to Issue?',
		fn: function(r){
			if (r){
				$.post('projectMag_issueConfirmSubmit.action',{
					vid:vid
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_finish_submit(vid){
	$.messager.confirm({
		title: 'Confirm',
		msg: 'Do you confirm the project is totally complete?',
		fn: function(r){
			if (r){
				$.post('projectMag_projectCompleteSubmit.action',{
					vid:vid,
					finish_remark: $('#finishComment'+vid).textbox('getValue')
				},function(data){
					var result = eval('(' + data + ')');
					if(result.success){
						$.messager.alert("Project Complete!", 'This job has been completed!!', "info");
						$("#project_mag_main_table").datagrid("reload");
					}
				});
			}
		}
	});
}
function projectMag_add_additional_comment(vid){
	$('#project_mag_additional_comment_window').window({
		width: 830,
		height: 440,
		modal: true,
		draggable: true,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: "Additional Comments",
		href: 'projectMag_additionalCommentInit.action',
		queryParams:{
			vid: vid
		}
	});
}
function projectMag_add_new_additional_comment(project_id){
	$("#additional_comment_form").form('reset');
	$("#additional_comment_form_projectIdInput").textbox("setValue",project_id);
	$("#additional_comment_window").window("open");
}
function projectMag_add_new_additional_comment_submit(){
	$('#additional_comment_form').form('submit', {
	    url: 'projectMag_additionalCommentSubmit.action',
	    onSubmit: function(){
	    	$("#additional_comment_form_commentInput").textbox("setValue",$.trim($("#additional_comment_form_commentInput").textbox("getValue")));
            return $(this).form('validate');
        },
	    success:function(data){
	    	var result = eval('(' + data + ')');  // change the JSON string to javascript object
	        if (result.success){
	        	$.messager.alert({
	        		title: 'Success',
	        		msg: 'Project contact SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#additional_comment_window').window('close');
		        		$("#project_mag_additional_comment_table").datagrid('insertRow',{
		        			index: 0,
		        			row: result.row
		        		});
		        	}
	        	});
	        }
	    }
	});
}
function changePwd(){
	$('#change_pwd_window').window('open');
}
function changePasswordSubmit(){
	$("#change_pwd_form").form('submit', {
	    url:'accountMag_changePasswordSubmit.action',
	    onSubmit: function(){
	    	if($(this).form('validate')){
	    		$('#hidden_old_password').val(calcHash($('#old_password').textbox('getValue')).toUpperCase());
	    		$('#hidden_new_password').val(calcHash($('#new_password').textbox('getValue')).toUpperCase());
	    		return true;
	    	}
	    	else
	    		return false;
	    },
	    success:function(data){
	    	var result = eval('(' + data + ')');
	    	if(result.success){
	    		$.messager.alert({
	        		title: 'Success',
	        		msg: 'Change password SUCCESSFULLY!',
					icon: 'info',
					fn: function(){
		        		$('#change_pwd_window').window('close');
		        	}
	        	});
	    	}
	    	else{
	    		$.messager.alert({
	        		title: 'Fail',
	        		msg: 'Fail to change password! ' + result.msg,
					icon: 'error',
					fn: function(){
		        		$('#change_pwd_window').window('close');
		        	}
	        	});
	    	}
	    }
	});
}
function accountMagInit(){
	$('#account_mag_window').window({
		width: 640,
		height: 480,
		modal: true,
		draggable: false,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		title: 'Account Managem',
		href: 'accountMag_accountMagInit.action',
		onLoad:function(){
			accountMagWindowInit();
	    }
	});
}
function accountMagSave(target){
	var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
    var index = parseInt(tr.attr('datagrid-row-index'));
    var row = $('#account_mag_table').datagrid('getRows')[index];
    var url = row.isNewRecord ? 'accountMag_saveUserInfo.action' : 'accountMag_updateUserInfo.action';
    $('#account_mag_table').datagrid('getRowDetail',index).find('form').form('submit',{
        url: url,
        onSubmit: function(){
        	$("#account_mag_table_usernameInput").textbox("setValue",$.trim($("#account_mag_table_usernameInput").textbox("getValue")));
        	$("#account_mag_table_realNameInput").textbox("setValue",$.trim($("#account_mag_table_realNameInput").textbox("getValue")));
            return $(this).form('validate');
        },
        success: function(data){
            data = eval('('+data+')');
            if(data.success){
            	data.userInfo.isNewRecord = false;
            	$('#account_mag_table').datagrid('collapseRow',index);
                $('#account_mag_table').datagrid('updateRow',{
                    index: index,
                    row: data.userInfo
                });
            }
            else{
            	$.messager.alert("Error", data.msg, "error");
            }
        }
    });
}
function accountMagCancel(target){
	var tr = $(target).closest('.datagrid-row-detail').closest('tr').prev();
    var index = parseInt(tr.attr('datagrid-row-index'));
    var row = $('#account_mag_table').datagrid('getRows')[index];
    if (row.isNewRecord){
        $('#account_mag_table').datagrid('deleteRow',index);
    } else {
        $('#account_mag_table').datagrid('collapseRow',index);
    }
}
function accountMagWindowInit(){
	$("#account_mag_table").datagrid({
		url: 'accountMag_queryUserInfo.action',
		view: detailview,
	    detailFormatter:function(index,row){
		    return '<div class="ddv"></div>';
		},
		onExpandRow: function(index,row){
		    var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
		    ddv.panel({
		        border:false,
		        href:'accountMag_generateUserInfoEditor.action',
				onLoad: function(){
		            $('#account_mag_table').datagrid('fixDetailRowHeight',index);
		            $('#account_mag_table').datagrid('selectRow',index);
		            $('#account_mag_table').datagrid('getRowDetail',index).find('form').form('load',row);
		        }
		    });
		    $('#account_mag_table').datagrid('fixDetailRowHeight',index);
		}
	});
}
function accountMagResetPwd(){
	var row = $('#account_mag_table').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','Are you sure you want to reset password for this user?',function(r){
			if (r){
				var index = $('#account_mag_table').datagrid('getRowIndex',row);
				$.post('accountMag_resetPassword.action',{vid:row.vid},function(data){
					var result = eval('(' + data + ')');
					$.messager.alert("Success", result.msg, "info");
				});
			}
		});
	}
}
function accountMagAddNew(){
	$('#account_mag_table').datagrid('appendRow',{isNewRecord:true});
	var index = $('#account_mag_table').datagrid('getRows').length - 1;
	$('#account_mag_table').datagrid('expandRow', index);
	$('#account_mag_table').datagrid('selectRow', index);
}
function accountMagRemove(){
	var row = $('#account_mag_table').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','Are you sure you want to remove this user?',function(r){
			if (r){
				var index = $('#account_mag_table').datagrid('getRowIndex',row);
				$.post('accountMag_removeUserInfo.action',{vid:row.vid},function(){
					$('#account_mag_table').datagrid('deleteRow',index);
				});
			}
		});
	}
}
function accountMagSearch(){
    $('#account_mag_table').datagrid('load',{
    	name: $('#account_mag_nameInput').val(),
        role: $('#account_mag_roleCombobox').val()
    });
}
function logout(){
	$('#logoutForm').submit();
}
function calcHash(input) {
	try {
		var hashObj = new jsSHA("SHA-256", "TEXT", {
			numRounds : 1
		});
		hashObj.update(input);
		return hashObj.getHash("HEX");
	} catch (e) {
		hashOutput.value = e.message;
	}
}