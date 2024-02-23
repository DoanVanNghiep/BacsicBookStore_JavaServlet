<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div style="background: #888888; height: 75px;padding: 15px;">
		<div style="float: left;">
			<h2>Website cửa hàng bán sách với JSP/SERVLET</h2>
		</div>
		<div style="float: right;text-align: right;">
			<c:if test="${not empty loginedUser}">
				Xin chào <b>${loginedUser.fullName}</b>
				|
				<a href="${pageContext.request.contextPath}">Thông tin</a>
				|
				<a href="${pageContext.request.contextPath}">Đăng xuất</a>
			</c:if>
			<br><br>
			Tìm sách&nbsp;<input name="sreach" onchange="activeAsLink('${pageContext.request.contextPath}/ClientHomeServlet?keyword=' + this.value)"/>
			
		</div>
		
	</div>
	<script src="${pageContext.request.contextPath}/js/bookstore_script.js"></script>
</body>
</html>