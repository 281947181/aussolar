<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<title>test</title>
</head>
<body>
	<form id="form">
		name: <input name="customer_name" value="0"/><br/>
		phone: <input name="customer_phone" value="1"/><br/>
		address: <input name="customer_address" value="2"/><br/>
		email: <input name="customer_email" value="3"/><br/>
		subburb: <input name="customer_subburb" value="4"/><br/>
		postcode: <input name="customer_postcode" value="1"/><br/>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$.getJSON( 'http://192.168.99.100:8080/aussolar/projectMag_addNewProjectFromWP.action?jsoncallback=?', $('#form').serialize())
	    .done(function( data ) {
	    	if( data.success )
	    		alert("success");
	    	else
	    		alert("faile");
	    });
	});
</script>
</html>