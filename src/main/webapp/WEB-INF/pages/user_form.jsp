<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>${mess}:</h1>
<form:form modelAttribute="userAttr" method="POST" action="${action}">
    <table>
        <tr>
            <td><form:label path="name">ФИО:</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="age">Возраст:</form:label></td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td><form:label path="isAdmin">Является ли администратором:</form:label></td>
            <td><form:select path="isAdmin">
                <form:option value="1" selected="${userAttr.getIsAdmin()? 'selected':''}">Да</form:option>
                <form:option value="0" selected="${!userAttr.getIsAdmin()? 'selected':''}">Нет</form:option>
            </form:select></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Записать"/>
                <input type="button" value="Отмена"  onclick="window.location.href='hello'"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>