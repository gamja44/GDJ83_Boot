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
	<h3>Detail Page</h3>
	<h3>${vo.boardTitle}</h3>
	<h3>${vo.boardWriter}</h3>
	<h3>${vo.boardContents}</h3>
	
	<%-- <c:forEach items="${vo.ar}" var="f">
		<img src="/files/${board}/${f.fileName}">
		<a href="./fileDown?fileNum=${f.fileNum}">${f.oriName}</a>
		<h3>${f.fileName}</h3>
	</c:forEach> --%>
	
	<c:forEach items="${vo.ar}" var="f">
		<img src="/files/${board}/${f.fileName}">
		<a href="./fileDown?fileNum=${f.fileNum}">${f.oriName}</a>
	</c:forEach>
</body>
</html>