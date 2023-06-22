<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>
<link rel="stylesheet" href="./css/list.css">
<link rel="stylesheet" href="./css/common.css">
</head>
<body>
	<div class="logout">
		<a href="./logout">ログアウト</a>
	</div>
	<h1>社員一覧</h1>
	<div class="signup">
		<input type="button" onclick="location.href='./detail'" value="新規登録">
	</div>
	<table border="1">
		<tr>
			<th>No</th>
			<th>会社</th>
			<th>事業部</th>
			<th>氏名</th>
			<th>氏名（ひらがな）</th>
			<th>年齢</th>
			<th>担当管理営業</th>
			<th>入社日</th>
			<th>稼働状況</th>
			<th>詳細</th>
			<th>削除</th>
		</tr>
		<c:forEach var="employee" items="${empId}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${employee.abbreviation}</td>
				<td>${employee.department}</td>
				<td>${employee.name}</td>
				<td>${employee.nameHiragana}</td>
				<td>${employee.age}</td>
				<td>${employee.businessManager}</td>
				<td>${employee.hireDate}</td>
				<td>${employee.commissioningStatus}</td>
				<td><a href="./detail?empId=${employee.employeeId}">詳細</a></td>
				<td><a href="./delete?empId=${employee.employeeId}"
					onclick="return confirm('[${employee.abbreviation}][${employee.name}]を削除しますか？')">削除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>