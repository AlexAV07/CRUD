<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Message ${mess} </h1>



<br>
<br>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody>

        <tr>
            <td><c:out value="${allUser.name}" /></td>
            <td><c:out value="${searchUser.searchName}" /></td>
            <td><c:out value="${allUser.isAdmin}" /></td>
        </tr>

    </tbody>
</table>


</body>
</html>