<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="dog" src="/images/cat.jpg">
	
	<spring:message code="hello"></spring:message>
	<spring:message code="hello2" text="기본값"></spring:message>
	
	
	<%-- <c:if test="${empty member}">
	</c:if> --%>
	<sec:authorize access="!isAuthenticated()">
		<h1>Login 하기 전</h1>
	</sec:authorize>
	
	<%-- <c:if test="${not empty member}">
	</c:if> --%>
	<sec:authorize access="isAuthenticated()">
		<h1>Login 성공</h1>
		<sec:authentication property="principal" var="member"/>
		
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message>
		<c:forEach items="${member.vos}" var="r">
		 <h3>${r.roleName}</h3>
		</c:forEach>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
  		<h1><a href="/qna/add">관리자 페이지</a></h1>
	</sec:authorize>
	
</body>
</html>