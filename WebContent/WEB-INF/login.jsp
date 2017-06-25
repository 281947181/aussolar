<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ausuntech Solar Login</title>
<script src="resources/js/sha.js" type="text/javascript"></script>
</head>
<body>
	<h2>Login</h2>
	<form method="post" action="accountMag_login.action"
		onsubmit="return check()">
		<p>${errorMsg}</p>
		<p>
			<label>username：</label> <input id="username" name="username" size="20" value=""/>
		</p>
		<p>
			<label>password：</label> <input id="password" type="password" size="20" value=""/>
			<input id="hiddenpwd" type="hidden" name="password" />
		</p>
		<p>
			<input type="submit" value="Login" />
		</p>
	</form>
</body>
<script type="text/javascript">
	function check() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if (username == null || username == "") {
			alert("Username required!");
			return false;
		}
		if (password == null || password == "") {
			alert("Password required!");
			return false;
		}
		document.getElementById("hiddenpwd").value = calcHash(password).toUpperCase();
		return true;
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
</script>
</html>