App.controller('DashboardController', function($scope,app_service,$location,$window){


	var dc = this;

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
						console.log(res.data[i].category);
						console.log(res.data[i].name);
					}

				},function(errResponse){
					console.log(errResponse);
				});
	};

	dc.addToCart = function(item,qty){
		console.log("addToCart");
		console.log(item);

		var cartJson = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyJson = JSON.parse($window.sessionStorage.getItem("qty"));
		if(cartJson != null){
			for(var i=0;i<cartJson.length;i++){
				console.log("inside for");
				console.log(cartJson[i]);
				console.log(qtyJson[i]);
				if(cartJson[i] != null && cartJson[i].id == item.id){
					delete cartJson[i];
					delete qtyJson[i];
					delete dc.cart.items[i];
					delete dc.cart.qty[i];
				}
			}
		}


		dc.cart.items.push(item);
		dc.cart.qty.push(qty);
		$window.sessionStorage.setItem("cart", JSON.stringify(dc.cart.items))
		$window.sessionStorage.setItem("qty", JSON.stringify(dc.cart.qty))
		console.log("out of for");
		console.log($window.sessionStorage.getItem("cart"));
		console.log($window.sessionStorage.getItem("qty"));

	}
	dc.flag = false;
	dc.checkout=function(){
		dc.flag = true;
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
	dc.placeOrder = function(hope){
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
				"email" : email
		}
		var cartJsonFinal = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyJsonFinal = JSON.parse($window.sessionStorage.getItem("qty"));
		if(cartJsonFinal != null){			
			for(var i=0;i<cartJsonFinal.length;i++){
				if(cartJsonFinal[i] != null){
					orderItem.items.push(cartJsonFinal[i]);
					orderItem.quantity.push(qtyJsonFinal[i]);
				}
			}
		}
		
		console.log(orderItem);
		console.log("above from controller");
		var URI = "/addOrder";
		app_service.postRequest(URI,orderItem).then(
				function(res){
					console.log(res);
					console.log(res.data.start_time);
					if(res.data == "" || res.data == null){
						dc.displayErrMsg = true;
					}else{
						dc.displayTime = true;
						dc.dispTime = res.data.end_time;
						dc.showCancelButton = true;
					}
				},function(errResponse){
					if(errResponse.status == 403){
						dc.displayTime = false;
						dc.showErrorMsg =true;
						dc.showReviseButton = true;
						dc.dispTime = errResponse.data.end_time;
					}
					console.log(errResponse);
				});

	}
	
	dc.checkout = function(){
		
		dc.finalCart = {
				"name" : [],
				"qty" : [],
				"price" : [],
				"id" : []
		};
		
		var cartJsonFinal = JSON.parse($window.sessionStorage.getItem("cart"));
		var qtyJsonFinal = JSON.parse($window.sessionStorage.getItem("qty"));
		if(cartJsonFinal != null){			
			for(var i=0;i<cartJsonFinal.length;i++){
				if(cartJsonFinal[i] != null){
					dc.finalCart.name.push(cartJsonFinal[i].name);
					dc.finalCart.price.push(cartJsonFinal[i].unit_price);
					dc.finalCart.id.push(cartJsonFinal[i].id);
					dc.finalCart.qty.push(qtyJsonFinal[i]);
				}
			}
		}
	}
	
	dc.removeFromCart = function(item){
		
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