<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table id="account_mag_table" class="easyui-datagrid" style="width:100%;height:100%;"
        toolbar="#account_mag_toolbar" singleSelect="true" 
        fitColumns="true" pagination="true" autoRowHeight="false" multiSort="true" 
        data-options="
        	rowStyler: function(index,row){
				return 'height:40px;'; // return inline style
			}
        ">
	<thead>
        <tr>
            <th data-options="field:'vid',hidden:true"></th>
            <th data-options="field:'username',width:'20%',sortable:true,align:'center'">Username</th>
            <th data-options="field:'real_name',width:'40%',sortable:true,align:'center'">Name</th>
            <th data-options="field:'role',width:'20%',sortable:true,align:'center'">Role</th>
        </tr>
    </thead>
</table>
<div id="account_mag_toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a id="account_mag_addNewBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="accountMagAddNew()">Add user</a>
        <a id="account_mag_removeBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="accountMagRemove()">Remove</a>
        <a id="account_mag_resetPwdBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-lock" onclick="accountMagResetPwd()">Reset Password</a>
    </div>
    <div>
		<input id="account_mag_nameInput" class="easyui-textbox" data-options="iconCls:'icon-search',width:300,label:'Username/Name:',labelWidth:100,labelAlign:'right'">
       	<select id="account_mag_roleCombobox" class="easyui-combobox" data-options="editable:false,width:150,label:'Role:',labelWidth:50,labelAlign:'right'" style="width:200px;">
			<option value="all">all</option>
			<option value="sales">sales</option>
			<option value="admin">admin</option>
			<option value="door knocker">door knocker</option>
		</select>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="accountMagSearch()">Search</a>
    </div>
</div>
