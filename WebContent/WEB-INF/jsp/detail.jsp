<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員詳細</title>
<link rel="stylesheet" href="./css/detail.css">
<link rel="stylesheet" href="./css/common.css">
</head>
<body>
	<h1>社員詳細</h1>
	<ul>
		<c:forEach var="errorMessagess" items="${errorMessage}" varStatus="status">
			<li>${errorMessagess}</li>
		</c:forEach>
	</ul>
	<form action="./detail" method="post">

		<input type="hidden" name="employeeId" value="<c:out value="${empInfoDto.employeeId}"/>">
		<table border="1">
			<tr>
				<th class="color">氏名</th>
				<td class="left"><input type="text" name="name" maxlength="20" value="<c:out value="${empInfoDto.name}"/>"></td>
			</tr>
			<tr>
				<th class="color">氏名（ひらがな）</th>
				<td class="left"><input type="text" name="nameHiragana" maxlength="20" value="<c:out value="${empInfoDto.nameHiragana}"/>"></td>
			</tr>
			<tr>
				<th class="color">生年月日</th>
				<td class="left"><input type="text" name="birthday" maxlength="10" value="<c:out value="${empInfoDto.birthday}"/>"></td>
			</tr>
			<tr>
				<th class="color">性別</th>
				<td>
					<c:forEach var="sex" items="${sex}" varStatus="status"><input type="radio" name="sex" value="${sex.code}"
						<c:if test="${sex.code == empInfoDto.sex}">checked</c:if>>${sex.name}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th class="color">メールアドレス</th>
				<td class="left"><input type="text" name="mailAddress" maxlength="50" value="<c:out value="${empInfoDto.mailAddress}"/>"></td>
			</tr>
			<tr>
				<th class="color">電話番号</th>
				<td class="left"><input type="text" name="telephoneNumber" maxlength="11" value="<c:out value="${empInfoDto.telephoneNumber}"/>"></td>
			</tr>
			<tr>
				<th class="color">所属会社</th>
				<td class="left">
					<select name="companyInfoId">
						<option value=""></option>
						<c:forEach var="company" items="${companyInfoDtoList}" varStatus="status">
							<option value="<c:out value="${company.companyId}"/>"<c:out value="${empInfoDto.companyInfoId == company.companyId ? 'selected' : ''}" />>${company.abbreviation}
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th class="color">担当管理営業</th>
				<td class="left">
					<input type="text" name="businessManager" maxlength="20" value="<c:out value="${empInfoDto.businessManager}"/>">
				</td>
			</tr>
			<tr>
				<th>事業部</th>
				<td>
					<select name="department">
						<option value=""></option>
						<c:forEach var="department" items="${department}" varStatus="status">
							<option value="${department.code}" <c:if test="${department.name == empInfoDto.department}">selected</c:if>>${department.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th class="color">稼働状況</th>
				<td>
					<c:forEach var="commissioningStatus" items="${commissioningStatus}" varStatus="status">
						<input type="radio" name="commissioningStatus" value="${commissioningStatus.code}"<c:if test="${commissioningStatus.code == empInfoDto.commissioningStatus}">checked</c:if>>${commissioningStatus.name}
					</c:forEach>
				</td>
			</tr>

			<tr>
				<th class="color">入社日</th>
				<td class="left">
					<input type="text" name="hireDate" maxlength="10" value="<c:out value="${empInfoDto.hireDate}"/>">
				</td>
			</tr>
			<tr>
				<th class="color">退職日</th>
				<td class="left">
					<input type="text" name="retireDate" maxlength="10" value="<c:out value="${empInfoDto.retireDate}"/>">
				</td>
			</tr>

			<tr>
				<th>ステータス</th>
				<td>
					<select name="stat">
						<option value=""></option>
						<c:forEach var="stat" items="${status}" varStatus="status">
							<option value="${stat.code}"
								<c:if test="${stat.name == empInfoDto.status}">selected</c:if>>${stat.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>

		<div class="button">
			<input type="submit" value="${buttonName}" onclick="return confirm('${buttonName}しますか？')" />
			<input type="button" onclick="location.href='./list'" value="戻る">
		</div>
	</form>
</body>
</html>