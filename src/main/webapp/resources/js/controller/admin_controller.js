App.controller('AdminController', function($scope,app_service,$location,$window,$http){

	var ac = this;

	ac.menu = {
			items:[]
	};

	ac.reportdata = {
			items:[]
	};
	
	ac.statReportdata = {
			items: []
	};

	ac.menuDet = {
			data : []
	};
	
	ac.menuDetailDiv = false;
	ac.showPopRep = false;
	ac.showStatRep = false;

	ac.currentPage = 0;
	ac.currentPage1 = 0;

	ac.menu_category = [{
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

	ac.report_menu_category = [{
		'id': 0,
		'name': 'Select'
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

	ac.category_mapping = {
			1 : 'Drink',
			2 : 'Appetizer',
			3 : 'Main Course',
			4 : 'Dessert'
	}
	
	ac.status_mapping = {
			0 : 'Order Placed',
			1 : 'Cancelled',
			2 : 'In Progress',
			3 : 'Completed'
	}

	ac.logout = function(){
		window.location = '/termprj/';
	}

	ac.category_unmapping = {
			'Drink' : 1,
			'Appetizer' : 2,
			'Main Course' : 3,
			'Dessert' : 4
	}
	ac.dataAdded = false;
	ac.initFunction = function(){

		URI = '/showAllMenus';
		app_service.getRequest(URI).then(
				function(res){
					console.log(res);

					for(var i=0; i<res.data.length;i++){
						ac.menu.items[i] = res.data[i];
					}

				},function(errResponse){
					console.log(errResponse);
				});
	};
	
	
	ac.showMenuDet = function(item){
		ac.menuDet.data = item.orderDetailsList;
		ac.menuDetailDiv = true;
	}
	
	ac.sort = function(keyname){
        ac.sortKey = keyname;   //set the sortKey to the param passed
        ac.reverse = !ac.reverse; //if true make it false and vice versa
    }

	ac.getMenuByCategory = function(category){
		console.log("in category " +category);
		URI = '/queryMenuByCategory/'+category;
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					ac.menu.items = [];
					for(var i=0; i<res.data.length;i++){
						ac.menu.items[i] = res.data[i];
					}
				},function(errResponse){
					console.log(errResponse);
				});

	}
	
	ac.getStatReport = function(){
		ac.showStatRep = false;
		ac.currentPage1 = 0;
		console.log("inside stat report");
		var startDt = ac.beginDt;
		var endDt = ac.finishDt;
		var month;
		var inputDateSt = new Date(startDt);
		var inputDateEd = new Date(endDt);
		var now = new Date();
		console.log(now.getDate());
		console.log(now.getMonth());
		console.log(1900+now.getYear());
		console.log(now.getHours() + ":" + now.getMinutes());
		console.log(now.getHours() + ":" + now.getMinutes() + ":00");
		if((now.getMonth()+1) < 9){
			nowDt = now.getMonth()+1;
			nowDt = "0"+nowDt;
		}else{
			nowDt = now.getMonth()+1;
		}
		console.log(now.getDate() +"-" + nowDt +"-" + (1900+now.getYear()) + " "+now.getHours() + ":" + now.getMinutes() + ":00");
		nowDt = now.getDate() +"-" + nowDt +"-" + (1900+now.getYear()) + " "+now.getHours() + ":" + now.getMinutes() + ":00";
		now = new Date(nowDt);
		console.log(now);
		
		if((inputDateSt.getMonth()+1) < 9){
			monthSt = inputDateSt.getMonth()+1;
			monthSt = "0"+monthSt;
		}else{
			monthSt = inputDateSt.getMonth()+1;
		}
		
		if((inputDateEd.getMonth()+1) < 9){
			monthEd = inputDateEd.getMonth()+1;
			monthEd = "0"+monthEd;
		}else{
			monthEd = inputDateEd.getMonth()+1;
		}
		startDatestring = inputDateSt.getDate()+"-"+monthSt+"-"+(1900+inputDateSt.getYear());
		endDatestring = inputDateEd.getDate()+"-"+monthEd+"-"+(1900+inputDateEd.getYear()) + " 23:59:59";
		URI = '/getStatReport/'+startDatestring+'/'+endDatestring;
		console.log(URI);
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					ac.showStatRep = true;
					ac.statReportdata.items = [];
					for(var i=0; i<res.data.length;i++){
						var status = res.data[i].status;
						console.log(res.data[i].start_time);
						console.log(res.data[i].end_time);
						console.log(new Date(res.data[i].end_time));
						ac.statReportdata.items[i] = res.data[i];
					}
					ac.reslength1 = ac.statReportdata.length;
				},function(errResponse){
					console.log(errResponse);
				});
	}

	ac.getReportByCategory = function(input_category){
		ac.showPopRep = false;
		ac.currentPage1 = 0;
		console.log(input_category.id);
		//console.log("in category " +ac.report_category);
		//var category = ac.category_unmapping[ac.report_category];
		var startDt = ac.startDt;
		var endDt = ac.endDt;
		var month;
		var inputDateSt = new Date(startDt);
		var inputDateEd = new Date(endDt);
		
		if((inputDateSt.getMonth()+1) < 9){
			monthSt = inputDateSt.getMonth()+1;
			monthSt = "0"+monthSt;
		}else{
			monthSt = inputDateSt.getMonth()+1;
		}
		
		if((inputDateEd.getMonth()+1) < 9){
			monthEd = inputDateEd.getMonth()+1;
			monthEd = "0"+monthEd;
		}else{
			monthEd = inputDateEd.getMonth()+1;
		}
		startDatestring = inputDateSt.getDate()+"-"+monthSt+"-"+(1900+inputDateSt.getYear());
		endDatestring = inputDateEd.getDate()+"-"+monthEd+"-"+(1900+inputDateEd.getYear()) + " 23:59:59";
		URI = '/getPopReportByCategory/'+input_category.id+ '/'+startDatestring+'/'+endDatestring;
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					ac.showPopRep = true;
					ac.reportdata.items = [];
					for(var i=0; i<res.data.length;i++){
						ac.reportdata.items[i] = res.data[i];
					}
					ac.reslength = res.data.length;
				},function(errResponse){
					console.log(errResponse);
				});
	}

	ac.numberOfPages=function(){
		return Math.ceil(ac.reportdata.items.length/5);   
	}
	
	ac.numberOfPages1=function(){
		return Math.ceil(ac.statReportdata.items.length/5);   
	}

		ac.remove = function(item){
			URI = '/deleteMenuItem/';
			app_service.postRequest(URI,item).then(
					function(res){
						console.log("item deleted");
						ac.getMenuByCategory(ac.category.id);
					},function(errResponse){
						console.log("could not delete");
					});
		}

		ac.addMenuItem = function(inFile) {
			var inputData = {

					"name" : ac.newName,
					"category" : ac.category_unmapping[ac.newCategory],
					"prep_time" : ac.newPrepTime,
					"calorie_count" : ac.newCalorie,
					"unit_price" : ac.newPrice,
					"is_deleted" : 0
			}

			console.log(ac.category_unmapping[ac.newCategory]);
			var inptFile = {
					"file" : ac.file
			}

			var URIData = '/addNewMenuData';
			var URIImage = '/addNewMenuImage';


			console.log("in add menu ctrl");

			app_service.postRequest(URIData,inputData).then(
					function(res){
						console.log("item added");
						console.log(res.data);
						ac.dataAdded = true;
					}
					,function(errResponse){
						console.log("could not add");
					});



		}
	});