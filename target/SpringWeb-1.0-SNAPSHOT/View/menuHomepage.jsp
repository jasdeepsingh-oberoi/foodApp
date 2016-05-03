<!DOCTYPE html>
<html>
<head>
    <title>Winery</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.shop.js"></script>
</head>

<body>

<div id="site">
    <header id="masthead">
        <h1>Spring Food System <span class="tagline">Food Order System</h1>
    </header>
    <div id="content">

        <form action = "" method = 'post' width = '200px'>
        Please select a type:
            <select id = 'category' >
                <option value = '1' >Drink</option>
                <option value = '2' >Appetizer</option>
                <option value = '3' >Main course</option>
                <option value = '4' >Desert</option>
                <option value = '0'> All</option>
            </select>
       </form>


        <div id="returnresult">
            <table id="products" style="width: 100%">
                <ul>

                </ul>

            </table>
        </div>









        <%--<div id="products">--%>
            <%--<ul>--%>
                <%--<li>--%>
                    <%--<div class="product-image">--%>
                        <%--<img src="images/wine1.jpg" alt="" />--%>
                    <%--</div>--%>
                    <!--<div class="product-description" data-name="Wine #1" data-price="5">-->
                        <!--<h3 class="product-name">Wine #1</h3>-->
                        <!--<p class="product-price">&euro; 5</p>-->
                        <!--<form class="add-to-cart" action="cart.html" method="post">-->
                            <!--<div>-->
                                <!--<label for="qty-1">Quantity</label>-->
                                <!--<input type="text" name="qty-1" id="qty-1" class="qty" value="1" />-->
                            <!--</div>-->
                            <!--<p><input type="submit" value="Add to cart" class="btn" /></p>-->
                        <!--</form>-->
                    <!--</div>-->
                <!--</li>-->
            <!--</ul>-->
        <!--</div>-->


    </div>
</div>

<script>
    $('#category').change(function(){
        $("#returnresult #products").empty();
        var action = "/queryMenuByCategory/";
        var category = $("#category").val();
        $.ajax({
            type: "GET",
            url: action + category,
            success: function (data) {

                var valdata =JSON.stringify(data);
                var objData = JSON.parse(valdata);
                $.each(objData, function(index, item) {
                    var eachrow =
                        "<li>"
                        + "<div class='product-image'>"
                        +   "<img src= '" + item.image_path + "' alt=''/>"
                        + "</div>"
                        + "<div class='product-description' data-name='' data-price=''>"
                        +   "<h3 class='product-name'>" + item.name + "</h3>"
                        +   "<p class='product-price'>$" + item.unit_price +"</p>"
                        +   "<form class='add-to-cart' action='cart.html' method=post'>"
                        +       "<div>"
                        +           "<label for='qty-1'>Quantity</label>"
                        +           "<input type='text' name='qty-1' id='qty-1' class='qty' value='1' />"
                        +       "</div>"
                        +       '<p><input type="submit" value="Add to cart" class="btn" /></p>'
                        +    "</form>"
                        + "</div>"
                        +"</li>";

//                   var eachrow = "<tr>"
//                           + "<td>" + item.id + "</td>"
//                           + "<td>" + item.name + "</td>"
//                           + "<td>" + item.image_path + "</td>"
//                           + "<td>" + item.unit_price + "</td>"
//                           + "<td>" + item.calorie_count + "</td>"
//                           + "</tr>";

                    console.log(eachrow);
                $("#returnresult #products").append(eachrow);
                });
            },
            error: function(e)
            {
                alert('Error: ' + e);
            }
        });



    })
</script>


</body>
<footer id="site-info">
    Copyright &copy; Spring Corp
</footer>
</html>