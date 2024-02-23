<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<div align="center">
		<h3>Thông tin chi tiết cuốn sách</h3>
		<p style="color: red">${errors }</p>
		<c:if test="${not empty book }">
			<form id="detailBookForm" action="cartBook/addToCart" method="post">
				<input type="hidden" name="bookId" value="${book.bookId }">
				<table style="width: 50%" border="1">
					<tr>
						<td width="25%">Tiêu đề</td>
						<td>${book.title}</td>
					</tr>
					<tr>
						<td>Tác giả</td>
						<td>${book.author}</td>
					</tr>
					<tr>
						<td>Giá tiền</td>
						<td><fmt:formatNumber type="number" value="${book.price }" /><sup>đ</sup></td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="text-align: justify;text-justify: inter-word;margin: 5px;">
								<img alt="Book Image" src="${book.imagePath}" style="float: left;margin-right: 5px;" width="150">
								${book.detail}
							</div>
						</td>
					</tr>
				</table>

				<div style="margin-top: 20px">
					<img alt="minus-icon" src="img/icons-minus.png"
						onclick="minusValue('quantity');" width="20"> <input
						type="text" value="1" size="2" style="line-height: 20px;"
						id="quantity" name="quantityPurchased"
						onkeyup="validateValue(this, ${book.quantityInStock});"> <img
						alt="add-icon" src="img/icons-add.png"
						onclick="plusValue('quantity',${book.quantityInStock})" width="20"
						height="20"> &nbsp;&nbsp;&nbsp;
					<c:if test="${not empty loginedUser }">
						<button
							onclick="checkQuantityAndSubmit('quantity',${book.bookId},${bookquantityInStock })">Thêm
							vào giỏ hàng</button>
					</c:if>
					
					<c:if test="${empty loginedUser }">
						<button
							type="button" onclick="alert('Bạn cần đăng nhập!')">Thêm
							vào giỏ hàng</button>
					</c:if>
					&nbsp;&nbsp;&nbsp;
					<a href="ClientHomeServlet">Tiếp tục xem sách</a>
				</div>
			</form>
		</c:if>
	</div>
</body>
</html>