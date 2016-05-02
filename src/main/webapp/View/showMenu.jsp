<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 3/22/16
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>Summary</title>
</head>
<body>
<h3>Added Item</h3>
<h3>${msg}</h3>

<c:if test="${not empty menuList}">
    <c:forEach var="item1" items="${menuList}">
        <table>
            <tr>
                <td>ID :</td>
                <td>${item1.id}</td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>${item1.category}</td>
            </tr>
            <tr>
                <td>Name: </td>
                <td>${item1.name}</td>
            </tr>

            <tr>
                <td>Image: </td>
                <td>${item1.image_path}</td>
            </tr>

            <tr>
                <td>Unit Price: </td>
                <td>${item1.unit_price}</td>
            </tr>
            <tr>
                <td>Calorie: </td>
                <td>${item1.calorie_count}</td>
            </tr>

            <tr>
                <td>Prep Time: </td>
                <td>${item1.prep_time}</td>
            </tr>

            <tr>
                <td>Is deleted: </td>
                <td>${item1.is_deleted}</td>
            </tr>
        </table>
    </c:forEach>
</c:if>
</body>
</html>
