<form method="post">
	<input id="account_mag_table_usernameInput" name="username" class="easyui-textbox" data-options="width:150,label:'Username:',labelWidth:60,labelAlign:'right'">
	<input id="account_mag_table_realNameInput" name="real_name" class="easyui-textbox" data-options="width:180,label:'Name:',labelWidth:40,labelAlign:'right'">
	<select name="role" class="easyui-combobox" data-options="editable:false,required:true,width:130,label:'Role:',labelWidth:40,labelAlign:'right'">
	    <option value="sales">sales</option>
	    <option value="admin">admin</option>
	    <option value="door knocker">door knocker</option>
	</select>
	<input type="hidden" name="vid"/>
    <div style="padding:5px 0;text-align:right;padding-right:30px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="accountMagSave(this)">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="accountMagCancel(this)">Cancel</a>
    </div>
</form>
