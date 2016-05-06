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
    <title>Add New Item</title>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
</head>
<body>
<div style="margin:0 auto" align=center id="addNewMenu">
    <h1>Add New Menu</h1>
    <form action="/adminAddMenuItem" method = "post" enctype="multipart/form-data" class = "pure-form pure-form-aligned">
        <fieldset>

            <div class="pure-control-group" >
                <label for="category">Category </label>
                <select id = 'category' name="category">
                    <option value = '1' >Drink</option>
                    <option value = '2' >Appetizer</option>
                    <option value = '3' >Main course</option>
                    <option value = '4' >Desert</option>
                </select>
            </div>

            <div class="pure-control-group">
                <label for="name">Name</label>
                <input type="text" id ="name" name="name">
            </div>

            <div class="pure-control-group">
                <label for="calorie_count">Calorie Count </label>
                <input type="text" id ="calorie_count" name="calorie_count" >
            </div>



            <div class="pure-control-group">
                <label for="is_deleted">is deleted</label>
                <input type="text" id ="is_deleted" name="is_deleted">
            </div>


            <div class="pure-control-group">
                <label for="prep_time">Prep_time</label>
                <input type="text" id ="prep_time" name="prep_time">
            </div>

            <div class="pure-control-group">
                <label for="unit_price">Unit Price</label>
                <input type="text" id ="unit_price" name="unit_price">
            </div>

            <div class="pure-control-group">
                <label for="file">Upload Image</label>
                <input type="file" id ="file" name="file">
            </div>

            <div class="pure-controls">
                <input type="submit" class = "pure-button pure-button-primary" value="Add to Menu" />
            </div>

        </fieldset>

    </form>
</div>


</body>
</html>
