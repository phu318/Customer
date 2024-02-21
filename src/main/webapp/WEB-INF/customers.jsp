<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/customers?action=create">Add New Customer</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Customers</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Province</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="c" items="${listCustomers}">
            <tr>
                <td><c:out value="${c.id}"/></td>
                <td><c:out value="${c.name}"/></td>
                <td><c:out value="${c.email}"/></td>
                <td><c:out value="${c.address}"/></td>
                <td><c:out value="${c.phone}"/></td>
                <td><c:out value="${c.province.name}"/></td>
                <td>
                    <a href="/customers?action=edit&id=${c.id}">Edit</a>
                    <a href="/customers?action=delete&id=${c.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>