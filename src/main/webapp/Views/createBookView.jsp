<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h3>Thêm sách mới</h3>
		<p style="color: red;">${errors}</p>
		<form method="POST" action="createBook">
			<table border="0">
				<tr>
					<td>Tiêu đề </td>
					<td><input type="text" name="title" value="${book.title}"/></td>
				</tr>
				<tr>
					<td>Tác giả </td>
					<td><input type="text" name="author" value="${book.author}"/></td>
				</tr>
				<tr>
					<td>Giá tiền </td>
					<td><input type="text" name="price" value="${book.price}"/>&nbsp;&nbsp;(vnđ)</td>
				</tr>
				<tr>
					<td>Số lượng có trong kho </td>
					<td><input type="text" name="quantityInStock" value="${book.quantityInStock}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Thêm mới"/>&nbsp;&nbsp; <a href="AdminHomeServlet">Bỏ qua</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>