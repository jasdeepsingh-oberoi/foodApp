App.controller('DashboardController', function($scope,app_service,$location,$window){
	
	
	var dc = this;
	
/*	$window.sessionStorage.setItem("cart",[]);
	$window.sessionStorage.setItem("qty",[]);*/
	
	dc.menu = {
			items:[]
	};

	dc.status = {
			items:[]
	};

	dc.cart = {
			items: [],
			qty : []
	};

	dc.showCancelButton = false;
	dc.showReviseButton = false;
	dc.displayErrMsg = false;
	dc.showUnavailable = false;
	dc.menu_category = [{
		'id': 0,
		'name': 'All'
	},{
		'id' : 1,
		'name' : 'Drink'
	},{
		'id' : 2,
		'name' : 'Appetizer'
	},{
		'id' : 3,
		'name' : 'Main course'
	},{
		'id' : 4,
		'name' : 'Dessert'
	}];
	dc.displayTime = false;
	dc.showErrorMsg = false;

	dc.status_category = [{
		'id': 0,
		'name': 'Order Placed'
	},{
		'id' : 1,
		'name' : 'Cancelled'
	},{
		'id' : 2,
		'name' : 'In Progress'
	},{
		'id' : 3,
		'name' : 'Completed'
	}];

	/*dc.myDate = new Date();
	dc.minDate = new Date(
			dc.myDate.getFullYear(),
			dc.myDate.getMonth() - 2,
			dc.myDate.getDate());
	dc.maxDate = new Date(
			dc.myDate.getFullYear(),
			dc.myDate.getMonth() + 2,
			dc.myDate.getDate());*/

	dc.initFunction = function(){
		URI = '/showAllMenus';
		app_service.getRequest(URI).then(
				function(res){
					console.log(res);
					for(var i=0; i<res.data.length;i++){
						dc.menu.items[i] = res.data[i];
					}
					console.log(dc.menu.items);
				},function(errResponse){
					console.log(errResponse);
				});
	};

	dc.addedlabel = false;

	dc.addToCart = function(item,qty){
		console.log("addToCart");
		console.log(item);
		dc.addedlabel = true;
		var cartJson = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyJson = JSON.parse($window.sessionStorage.getItem("qty"));
		if(!cartJson){
			cartJson = [];
			qtyJson = [];
		}
		if(cartJson != null && cartJson.length != 0){
			for(var i=0;i<cartJson.length;i++){
				if(cartJson[i] != null && cartJson[i].id == item.id){
					cartJson.splice(i,1);
					qtyJson.splice(i,1);
					putValuesSession(cartJson, qtyJson,item,qty);
					/*dc.cart.items.splice(i,1);
					dc.cart.qty.splice(i,1);*/
					break;
				}else{
					if(i===cartJson.length-1){
						putValuesSession(cartJson, qtyJson,item,qty);
					}
				}
			}
			console.log(dc.cart);
		}else{
			putValuesSession(cartJson, qtyJson,item,qty);
		}
	}

	function putValuesSession(cartJson,qtyJson,item,qty){
		cartJson.push(item);
		qtyJson.push(qty);
		dc.cart.items.push(item);
		dc.cart.qty.push(qty);
		$window.sessionStorage.removeItem("cart");
		$window.sessionStorage.setItem("cart", JSON.stringify(cartJson));
		$window.sessionStorage.removeItem("qty");
		$window.sessionStorage.setItem("qty", JSON.stringify(qtyJson));
	}
	dc.checkAdded = function(id){
		if($window.sessionStorage.getItem("cart") != null){
			var item = JSON.parse($window.sessionStorage.getItem("cart")).filter(function (obj){
				return obj.id == id;
			})[0];
		}
		if(item){
			return true;
		}else{
			return false;
		};
	}

	dc.logout = function(){
		window.location = '/termprj/';
	}

	dc.getMenuByCategory = function(category){
		console.log("in category " +category);
		URI = '/queryMenuByCategory/'+category;
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					dc.menu.items = [];
					for(var i=0; i<res.data.length;i++){
						dc.menu.items[i] = res.data[i];
						console.log(res.data[i].category);
						console.log(res.data[i].name);
					}


				},function(errResponse){
					console.log(errResponse);
				});

	}
	dc.userinputfortime = 'asap';
	dc.showtime = false;
	dc.showdate = false;
	$scope.$watch('ctrl.userinputfortime',function(value){
		if(dc.userinputfortime == 'at'){
			dc.showtime = true;		
			dc.showdate = true;
		}else if(dc.userinputfortime == 'asap'){
			dc.showtime = false;	
			dc.showdate = false;
		}else if(dc.userinputfortime == 'on'){
			dc.showtime = false;	
			dc.showdate = true;
		}
		console.log(dc.userinputfortime);
	});

	dc.itemNotAvailable=[];


	dc.checkOutButton=function(){
		if($window.sessionStorage.getItem("cart") != null){
			return (JSON.parse($window.sessionStorage.getItem("cart")).length>0);			
		}
	};
	
	dc.confirmationNeeded = [];
	
	
	dc.placeOrder = function(){

		var onlytime;
		var datestring;
		console.log(dc.userinputfortime);
		console.log(dc.pickuptime);
		var onlyDt = dc.inputDt;
		if(dc.userinputfortime == 'asap'){
			var now = new Date();
			console.log(now.getHours()+1 + ":" + now.getMinutes());
			onlytime = now.getHours()+1 + ":" + now.getMinutes();
			console.log(onlytime);
			datestring = "01-01-1970";
		}else if(dc.userinputfortime == 'at'){
			onlytime = dc.pickuptime;
			console.log(onlytime);
			var inputDate = new Date(onlyDt);
			datestring = inputDate.getDate()+"-0"+(inputDate.getMonth()+1)+"-"+(1900+inputDate.getYear());
		}else if(dc.userinputfortime == 'on'){
			onlytime = "06:00";
			var inputDate = new Date(onlyDt);
			datestring = inputDate.getDate()+"-0"+(inputDate.getMonth()+1)+"-"+(1900+inputDate.getYear());
		}


		console.log("time");
		var email = $window.localStorage.username;	
		var datetime = datestring+' '+onlytime+':00';
		console.log(datestring)
		console.log(datestring+' '+onlytime+':00');
		//console.log(ctrl.inputDt)
		var orderItem = {
				"items" : [],
				"quantity" : [],
				"pickuptime" : datetime,
				"email" : email,
				"totalprice" : 0
		}
		var tPrice = 0;
		var cartJsonFinal = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyJsonFinal = JSON.parse($window.sessionStorage.getItem("qty"));
		if(cartJsonFinal != null){			
			for(var i=0;i<cartJsonFinal.length;i++){
				if(cartJsonFinal[i] != null){
					orderItem.items.push(cartJsonFinal[i]);
					orderItem.quantity.push(qtyJsonFinal[i]);
					console.log(cartJsonFinal[i]);
					tPrice += cartJsonFinal[i].unit_price * qtyJsonFinal[i];
				}
			}
			orderItem.totalprice = tPrice;
			console.log(orderItem.totalprice);
		}

		console.log(orderItem);
		console.log("above from controller");
		var URI = "/addOrder";
		app_service.postRequest(URI,orderItem).then(
				function(res){
					console.log(res);
					console.log(res.data.start_time);
					if(res.data == "" || res.data == null){
						$window.sessionStorage.removeItem("cart");
						$window.sessionStorage.removeItem("qty");
						dc.displayErrMsg = true;
					}else{
						dc.displayTime = true;
						dc.dispTime = res.data.end_time;
						$window.sessionStorage.removeItem("cart");
						$window.sessionStorage.removeItem("qty");
						dc.showCancelButton = true;
					}
				},function(errResponse){
					console.log(errResponse);
					if(errResponse.status == 404){
						console.log(errResponse);
						for(var i=0;i<errResponse.data.length;i++){
							dc.itemNotAvailable.push(errResponse.data[i]);
						}
						dc.showUnavailable = true;
					}
					if(errResponse.status == 403){
						console.log(errResponse.data);
						dc.confirmationNeeded = errResponse.data;
						dc.displayTime = false;
						dc.showErrorMsg =true;
						dc.showConfirmButton = true;
						dc.showReviseButton = true;
						dc.showCancelButton = true;
						dc.dispTime = errResponse.data.end_time;
					}
					console.log(errResponse);
				});
	}

	dc.confirmOrder = function(){
		console.log(dc.confirmationNeeded);
		var obj = dc.confirmationNeeded;
		
		var URI = '/confirmOrder';
		app_service.postRequest(URI,obj).then(
				function(res){
					console.log("in confirm order res");
					console.log(res.data);
					dc.showErrorMsg =false;
					dc.showConfirmButton = false;
					dc.showReviseButton = false;
					dc.showCancelButton = true;
					dc.displayTime = true;
					dc.dispTime = res.data.end_time;
					$window.sessionStorage.removeItem("cart");
					$window.sessionStorage.removeItem("qty");
				},function(errResponse){
					console.log("in confirm order err res");
					console.log(errResponse.data);
				});
	};
	
	
	dc.checkout = function(){
		dc.finalCart = {
				items : []
		};
		dc.totalPrice = 0;
		var cartJsonFinal = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyJsonFinal = JSON.parse($window.sessionStorage.getItem("qty"));
		if(cartJsonFinal != null){
			for(var i=0;i<cartJsonFinal.length;i++){
				if(cartJsonFinal[i] != null){
					var obj = {						
							"name" :cartJsonFinal[i].name,
							"price" : cartJsonFinal[i].unit_price,
							"id" : cartJsonFinal[i].id,
							"qty" : qtyJsonFinal[i]
					};
				}
				dc.finalCart.items.push(obj);
				dc.totalPrice += cartJsonFinal[i].unit_price * qtyJsonFinal[i];
			}
			console.log("dc.finalCart");
			console.log(dc.finalCart);
		}
	}

	dc.afterEditCart = {
			"items" : [],
			"qty" : []
	};
	
	dc.cancelOrder = function(){
		$window.sessionStorage.removeItem("cart");
		$window.sessionStorage.removeItem("qty");
		window.location = '/termprj/dashboard';
	}

	dc.removeFromCart = function(id){
		console.log("remove from cart");
		var cartRemoveJson = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyRemoveJson = JSON.parse($window.sessionStorage.getItem("qty"));
		if(cartRemoveJson != null){
			for(var i=0;i<cartRemoveJson.length;i++){
				if(cartRemoveJson[i].id == id){
					console.log("inside if");
					cartRemoveJson.splice(i,1);
					qtyRemoveJson.splice(i,1);
					break;
				}		
			}
		}
		$window.sessionStorage.removeItem("cart");
		$window.sessionStorage.setItem("cart", JSON.stringify(cartRemoveJson));
		$window.sessionStorage.removeItem("qty");
		$window.sessionStorage.setItem("qty", JSON.stringify(qtyRemoveJson));
		console.log($window.sessionStorage.getItem("cart"));
		console.log($window.sessionStorage.getItem("qty"));
		dc.checkout();
	}

	dc.getHistByStatus = function(status){
		console.log("in status " +status);
		var email = $window.localStorage.username;
		URI = '/showUserHistoryByStatus/'+email+'/'+status;
		console.log(URI);
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					dc.status.items = [];
					for(var i=0; i<res.data.length;i++){
						dc.status.items[i] = res.data[i];
					}
				},function(errResponse){
					console.log(errResponse);
				});

	}


});