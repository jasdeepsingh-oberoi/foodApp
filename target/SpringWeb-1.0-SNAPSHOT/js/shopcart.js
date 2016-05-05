(function( $ ) {
	function _addToCart( values ) {
		var cart = sessionStorage.getItem("SpringCart");
		var cartObject = JSON.parse( cart );
		var cartCopy = cartObject;
		var items = cartCopy.items;
		items.push( values );

		sessionStorage.setItem("SpringCart", JSON.stringify( cartCopy ) );
		console.log(sessionStorage.getItem("SpringCart"));
	}

	function _convertString( numStr ) {
		console.log("price fetched: " + numStr);
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


	function createCart() {
		console.log("in create cart");
		if (sessionStorage.getItem("SpringCart") == null) {
			var cart = {};
			cart.items = [];
			sessionStorage.setItem("SpringCart",JSON.stringify(cart));
			sessionStorage.setItem("SpringTotal", "0");
		}
	}

	function addToCart() {
			createCart();
			console.log("in click add to cart");
			var $form = $(this);
			var $product = $form.parent();
			var price = _convertString($product.data("price"));
			var name = $product.data("name");
			var qty = parseInt($form.find("#qty-1").val());
			var subTotal = price * qty;
			var pastTotal = _convertString(sessionStorage.getItem("SpringTotal"));
			var total = subTotal + pastTotal;
			sessionStorage.setItem("SpringTotal", total);
			_addToCart({
				product: name,
				price: price,
				qty: qty
			});
	}

	function menuSelection() {
		$("#productlist").hide();
		$("#products").empty();
		var action = "/queryMenuByCategory/";
		var category = $("#category").val();
		$.ajax({
			type: "GET",
			url: action + category,
			success: function (data) {
				var valdata = JSON.stringify(data);
				var objData = JSON.parse(valdata);
				$.each(objData, function (index, item) {
					var eachrow =
							"<li>"
							+ "<div class='product-image'>"
							+ '<img src=" ' + item.image_path + '" alt=""/>'
							+ "</div>"
							+ "<div class='product-description' " + "data-name=" + item.name + " data-price=" + item.unit_price + ">"
							+ "<h3 class='product-name'>" + item.name + "</h3>"
							+ "<p class='product-price'>$" + item.unit_price + "</p>"
							+ '<form class="add-to-cart" action="/cart" method="post">'
							+ "<div>"
							+ "<label for='qty-1'>Quantity</label>"
							+ "<input type='text' name='qty-1' id='qty-1' class='qty' value='1' />"
							+ "</div>"
							+ '<p><input type="submit" value="Add to cart" class="btn" /></p>'
							+ "</form>"
							+ "</div>"
							+ "</li>";
					console.log(eachrow);
					$("#products").append(eachrow);
				});
			},
			error: function (e) {
				alert('Error: ' + e);
			}
		});
	}








})( jQuery );
