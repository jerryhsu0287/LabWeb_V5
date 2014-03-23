<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="" />

<title>Product</title>
<script type="text/javascript">
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
	}
</script>
</head>
<body>

	<h3>Welcome, ${param.custid}</h3>

	<h3>Product Table</h3>

	<form action="<c:url value='/pages/product.controller' />" method="get">
		<table>
			<tr>
				<td>ID :</td>
				<td><input type="text" name="id" value="${param.id}"></td>
				<td style="color: red">${errors.id}</td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name" value="${param.name}"></td>
				<td style="color: red">${errors.name}</td>
			</tr>

			<tr>
				<td>Price :</td>
				<td><input type="text" name="price" value="${param.price}"></td>
				<td style="color: red">${errors.price}</td>
			</tr>
			<tr>
				<td>Make :</td>
				<td><input type="text" name="make" value="${param.make}"></td>
				<td style="color: red">${errors.make}</td>
			</tr>
			<tr>
				<td>Expire :</td>
				<td><input type="text" name="expire" value="${param.expire}"></td>
				<td style="color: red">${errors.expire}</td>
			</tr>
			<tr>
				<td><input type="submit" name="prodaction" value="Insert">
					<input type="submit" name="prodaction" value="Update"></td>
				<td><input type="submit" name="prodaction" value="Delete">
					<input type="submit" name="prodaction" value="Select"> <input
					type="button" value="Clear" onclick="clearForm()"></td>
			</tr>
		</table>
	</form>
	<dir style="color: red">${delete}</dir>
	<dir style="color: red">${errors.result}</dir>

	<c:if test="${not empty result}">

		<table>
			<tr>
				<td>ID:</td>
				<td>${result.id}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>${result.name}</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td>${result.price}</td>
			</tr>
			<tr>
				<td>Make:</td>
				<td>${result.make}</td>
			</tr>
			<tr>
				<td>Expire:</td>
				<td>${result.expire}</td>
			</tr>
		</table>
	</c:if>
</body>
</html>