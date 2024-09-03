<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Pager</h1>
	<table border="1">
	<thead>
			<tr>
				<td>Num</td>
				<td>Title</td>
				<td>Write</td>
				<td>Date</td>
			</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.boardNum}</td>
				<td>${vo.boardTitle}</td>
				<td>${vo.boardWriter}</td>
				<td>${vo.createDate}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<a href="./add">Add</a>
	
	
</body>
</html>