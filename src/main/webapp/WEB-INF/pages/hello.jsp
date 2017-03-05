<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
</head>
<body>
<h1></h1>

<br>
<h3><input type="button" value="Новый пользователь" onclick="window.location.href='user_form'"/> </h3>

<br>
<br>
Поиск:
<form:form modelAttribute="searchUser" method="post" action="search">
    <table style="border: none">
        <tr>
            <td><form:label path="searchName">ФИО</form:label></td>
            <td><form:input path="searchName"/></td>
            <td></td>
            </tr>
        <tr>
            <td><form:label path="searchAge">Возраст</form:label></td>
            <td><form:input path="searchAge"/></td>
            <td><input type="submit" value="Найти"/> </td>
        </tr>
    </table>
</form:form>

<table style="border: 1px solid; width: 500px; text-align:center;border-color: darkgray">
    <thead style="background:#006A95">
    <tr>
        <th>ФИО</th>
        <th>Возраст</th>
        <th>Администратор</th>
        <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${listUser}" var="listUser">
            <c:url var="editUrl" value="get_update?id=${listUser.id}"/>
            <c:url var="delUrl" value="delete?id=${listUser.id}"/>
            <tr style="border-color: darkgray;background-color: aliceblue">
                <td width="300"><c:out value="${listUser.name}" /></td>
                <td width="100"><c:out value="${listUser.age}" /></td>
                <td ><c:out value="${listUser.isAdmin?'Да':'Нет'}" /></td>
                <td><a href="${editUrl}">edit</a> </td>
                <td><a href="${delUrl}">delete</a> </td>
            </tr>
        </c:forEach>

    </tbody>
</table>
<table style="border: 1px solid; width: 500px;;border-color: white;text-align:center">
    <tr>
        <td colspan="4">
            <a href="/hello?page=-1" >prev</a>
            <a href="/hello?page=1" >next</a>
        </td>
    </tr>
</table>

</body>
</html>