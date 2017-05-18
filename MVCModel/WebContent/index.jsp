<%@page import="com.zs.mvcapp.domain.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="query.do" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>address:</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>phone:</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td><input type="submit" value="query"></td>
				<td><a href="#">Add New Customer</a></td>
			</tr>
		</table>
	</form>
	
	<%
		List<Customer> customers = (List<Customer>)request.getAttribute("customers");
		if(customers != null && customers.size() > 0){
	%>
	<table border="1" cellpadding="10" cellspacing="0">
				<tr>
					<th>ID</th>
					<th>CustomerName</th>
					<th>address</th>
					<th>phone</th>
					<th>DELETE/UPDATE</th>
				</tr>
	<%		
		for(Customer customer:customers){
	%>
				<tr>
					<td><%=customer.getId() %></td>
					<td><%=customer.getName() %></td>
					<td><%=customer.getAddress() %></td>
					<td><%=customer.getPhone() %></td>
					<td>
						<a href="#">DELETE</a>
						<a href="#">UPDATE</a>
					</td>
				</tr>
				<hr>
	<% 
			} 
	%>
	</table>
	<%
		}
	%>

</body>
</html>