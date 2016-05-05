<!DOCTYPE html>
<html>
<head>
    <title>Winery: Your Shopping Cart</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.shop.js"></script>
</head>

<body>

<div id="site">
    <header id="masthead">
        <h1>Romantic Food <span class="tagline">Food Gallery since 1999</h1>
    </header>
    <div id="content">
        <h1>Your Shopping Cart</h1>
        <form id="shopping-cart" action="cart.html" method="post">
            <table class="shopping-cart">
                <thead>
                <tr>
                    <th scope="col">Item</th>
                    <th scope="col">Qty</th>
                    <th scope="col" colspan="2">Price</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <p id="sub-total">
                <strong>Sub Total</strong>: <span id="stotal"></span>
            </p>
            <ul id="shopping-cart-actions">
                <li>
                    <input type="submit" name="update" id="update-cart" class="btn" value="Update Cart" />
                </li>
                <li>
                    <input type="submit" name="delete" id="empty-cart" class="btn" value="Empty Cart" />
                </li>
                <li>
                    <a href="/menuHome" class="btn">Continue Shopping</a>
                </li>
                <li>
                    <a href="/checkout" class="btn">Go To Checkout</a>
                </li>
            </ul>
        </form>
    </div>



</div>

<footer id="site-info">
    Copyright &copy; Winery
</footer>

</body>
<script>
    $(function(){
        displayCart();
    });

    function displayCart() {
        if($("#shopping-cart").length) {
            var $tableCart = $("#shopping-cart").find(".shopping-cart");

            if (sessionStorage.getItem("SpringCart") == null) {
                $tableCart.find("tbody").html("");
                $("#stotal")[0].innerHTML = "$" + 0.00;
                return;
            }
            var cart = JSON.parse(sessionStorage.getItem("SpringCart"));
            var items = cart.items;


            if (items.length == 0) {
                $tableCart.find("tbody").html("");
                $("#stotal")[0].innerHTML = "$" + 0.00;
            } else {
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    var product = item.product;
                    var price = "$ " + item.price;
                    var qty = item.qty;
                    var row = "<tr><td class='pname'>" + product + "</td>" + "<td class='pqty'><input type='text' value='" + qty + "' class='qty'/></td>";
                    row += "<td class='pprice'>" + price + "</td><td class='pdelete'><a href='' data-product='" + product + "'>&times;</a></td></tr>";
                    $tableCart.find("tbody").append(row);
                }
                var total = sessionStorage.getItem("SpringTotal");
                $("#stotal")[0].innerHTML = "$" + total;
            }
        }
    }


    function _emptyCart() {
        sessionStorage.clear();
    }

    function _convertString( numStr ) {
        var num;
        if( /^[-+]?[0-9]+\.[0-9]+$/.test( numStr ) ) {
            num = parseFloat( numStr );
        } else if( /^\d+$/.test( numStr ) ) {
            num = parseInt( numStr, 10 );
        } else {
            num = Number( numStr );
        }

        if( !isNaN( num ) ) {
            return num;
        } else {
            console.warn( numStr + " cannot be converted into a number" );
            return false;
        }
    }

    function _extractPrice(element) {
        var self = this;
        var text = element.text();
        var price = text.replace("$","").replace(" ","");
        return price;
    }

    $(document).ready(function() {
        $("#shopping-cart-actions #empty-cart").on("click", function() {
            _emptyCart();
        })


        $("#update-cart").on("click", function() {
            updateCart();
        });

        function updateCart() {
            var $rows = $("#shopping-cart").find("tbody tr");
            var cart = sessionStorage.getItem("SpringCart");
            var total = sessionStorage.getItem("SpringTotal");

            var updatedTotal = 0;
            var totalQty = 0;
            var updatedCart = {};
            updatedCart.items = [];

            $rows.each(function() {
                var $row = $(this);
                var pname = $.trim($row.find(".pname").text());
                var pqty = _convertString($row.find(".pqty > .qty").val());
                var pprice = _convertString(_extractPrice($row.find(".pprice")));

                var cartObj = {
                    product: pname,
                    price: pprice,
                    qty: pqty
                };
                updatedCart.items.push(cartObj);
                var subTotal = pqty * pprice;
                updatedTotal += subTotal;
                totalQty +=pqty;
            });
            sessionStorage.setItem("SpringTotal", updatedTotal.toString());
            sessionStorage.setItem("SpringCart", JSON.stringify(updatedCart));
        }

        $(document).on("click", ".pdelete a", function(e) {
            e.preventDefault();
            var productName = $(this).data("product");
            var newItems = [];
            var cart = JSON.parse(sessionStorage.getItem("SpringCart"));
            var items = cart.items;
            for (var i = 0;i < items.length; ++i) {
                var item = items[i];
                var product = item.product;
                if (product == productName) {
                    items.splice(i, 1);
                }
            }

            newItems = items;
            var updatedCart = {};
            updatedCart.items = newItems;

            var updatedTotal = 0;
            if (newItems.length == 0) {
                updatedTotal = 0;
            } else {
                for (var j = 0; j < newItems.length; ++j) {
                    var prod = newItems[j];
                    var sub = prod.price * prod.qty;
                    updatedTotal += sub;
                }
            }
            sessionStorage.setItem("SpringTotal", updatedTotal.toString());
            sessionStorage.setItem("SpringCart", JSON.stringify(updatedCart));
            $(this).parents("tr").remove();
            $("#stotal")[0].innerHTML = "$" + sessionStorage.getItem("SpringTotal");

        });



    })




</script>

</html>