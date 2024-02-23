<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Website cửa hàng sách</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<div align="center">
		<table border="1">
			<tr>
				<th>Tiêu đề</th>
				<th>Tác giả</th>
				<th>Giá tiền</th>
				<th>Thao tác</th>
			</tr>
			<c:forEach items ="${bookList}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${book.price}"></fmt:formatNumber></td>
					<td>
						<a href="detailBook?bookId=${book.bookId}"> xem chi tiết</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- Không có hoạt động tìm kiếm -->
		<c:if test="${empty keyword}">
			<div style="margin-top: 5px;">
				<c:if test="${currentPage gt 1 }">
					<a href="ClientHomeServlet?page=${currentPage - 1}">Previuos</a> &nbsp;
				</c:if>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i }">
						&nbsp;${i}&nbsp;
					</c:when>
					<c:otherwise>
						&nbsp; <a href="ClientHomeServlet?page=${i}">${i}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${currentPage lt noOfPages}">
				&nbsp; <a href="ClientHomeServlet?page=${currentPage + 1}">Next</a>
			</c:if>
			</div>
		</c:if>
		<!-- Có hoạt động tìm kiếm -->
		<c:if test="${not empty keyword}">
			<div style="margin-top: 5px;">
				<c:if test="${currentPage gt 1 }">
					<a href="ClientHomeServlet?page=${currentPage - 1}&keyword=${keyword}">Previous</a> &nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPage}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
						&nbsp; <a href="ClientHomeServlet?page=${i}&keyword=${keyword}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages}">
					&nbsp; <a href="ClientHomeServlet?page=${currentPage + 1}&keyword=${keyword}"></a>
				</c:if>
				
			</div>
		</c:if>
	</div>
</body>
</html>