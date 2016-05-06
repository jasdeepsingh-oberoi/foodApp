<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body style="margin: 55px auto" align=center>

<h3>Menu List</h3>
<div id="showMenuForm">
    <table id="showMenuTable" class="pure-table pure-table-horizontal" align=center>
        <thead>
        <tr>
            <th scope="col">ID </th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Calorie</th>
            <th scope="col">Unit Price</th>
            <th scope="col">Prep Time</th>
            <th scope="col">Is deleted</th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${not empty menus}">
                <c:forEach var="item" items="${menus}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.category}</td>
                        <td>${item.calorie_count}</td>
                        <td>${item.unit_price}</td>
                        <td>${item.prep_time}</td>
                        <td class="itemDelete"><input type="submit" value="DELETE" data-itemid=${item.id} class="pure-button pure-button-primary"/></td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>

    </table>
    <form id="createNewMenu" action="/adminAddMenuForm" method = "get" style="margin: 15px auto">
        <input type="submit" id = "submitAddNewMenu" class = "pure-button pure-button-primary" value="Add New Menu" />
    </form>

</div>
<script>
    $(document).ready(function() {
        $(document).on("click", ".itemDelete input", function (e) {
            e.preventDefault();
            var itemid = $(this).data("itemid");

            $.ajax({
                type: "DELETE",
                url: "/deleteMenuItem/" + itemid,
                success: function (data) {

                }
            });
            $(this).parents("tr").remove();

        });
    })

</script>
</body>


