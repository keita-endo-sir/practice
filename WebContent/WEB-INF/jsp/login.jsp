<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="./css/login.css">
<link rel="stylesheet" href="./css/common.css">
</head>
<body>
	<h1>社員管理システム</h1>
	<span>${message}</span>
	<form action="./login" method="post">
		<div class="login-body">
			ログインID<input class="box" type="text" name="loginId" maxlength="20"><br>
			パスワード<input class="box" type="password" name="password"
				maxlength="20"><br>
		</div>
		<div class="login-submit">
			<input class="button" type="submit" value="ログイン">
		</div>
	</form>
</body>
</html>