<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Social Network</title>
</head>
<body>
<c:forEach var="user" items="${users}">
    <table>
        <tr>
            <th>id</th>
            <th>email</th>
            <th>role.id</th>
            <th>role.name</th>
            <th>isLocked</th>
            <th>time registration</th>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.role.id}</td>
            <td>${user.role.name}</td>
            <td>${user.locked}</td>
            <td>${user.timeRegistration}</td>
        </tr>
    </table>
</c:forEach>
</body>
</html>