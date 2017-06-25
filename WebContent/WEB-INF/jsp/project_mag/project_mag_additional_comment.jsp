<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" fit="true">
    <div data-options="region:'center'">
    	<div id="#project_mag_additional_comment_toolbar" style="padding:5px;height:auto">
    		<a id="project_mag_additional_comment_toolbar_addNewBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="projectMag_add_new_additional_comment('<s:property value="#request.project_id"/>')">New Comment</a>
    	</div>
    	<table id="project_mag_additional_comment_table" class="easyui-datagrid"
		        data-options="singleSelect:true,fitColumns:true,nowrap:false,toolbar:'#project_mag_additional_comment_toolbar',
		        	rowStyler: function(index,row){
						return 'height:70px;'; // return inline style
					}
		        ">
			<thead>
		        <tr>
		            <th data-options="field:'comment_date',width:100,align:'center'">Date</th>
		            <th data-options="field:'comment_person',width:140,align:'center'">Contact Person</th>
		            <th data-options="field:'comment',width:240,align:'center'">Comment</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<s:iterator value="#request.additional_comments" id="record">
		    		<tr>
		    			<td><s:property value="#record.comment_date"/></td>
		    			<td><s:property value="#record.comment_person"/></td>
		    			<td><s:property value="#record.comment"/></td>
		    		</tr>
				</s:iterator>
		    </tbody>
		</table>
    </div>
</div>
