App.controller('AdminController', function($scope,app_service,$location,$window,$http){

	var ac = this;

	ac.menu = {
			items:[]
	};

	ac.reportdata = {
			items:[]
	};

	
	ac.showPopRep = false;

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
		var startDt = ac.beginDt;
		var endDt = ac.finishDt;
		var month;
		var inputDateSt = new Date(startDt);
		var inputDateEd = new Date(endDt);
		
		if((inputDateSt.getMonth()+1) < 9){
			monthSt = "0"+inputDateSt.getMonth();
		}else{
			monthSt = inputDateSt.getMonth()+1;
		}
		
		if((inputDateEd.getMonth()+1) < 9){
			monthEd = "0"+inputDateEd.getMonth();
		}else{
			monthEd = inputDateEd.getMonth()+1;
		}
		startDatestring = inputDateSt.getDate()+"-"+monthSt+"-"+(1900+inputDateSt.getYear());
		endDatestring = inputDateEd.getDate()+"-"+monthEd+"-"+(1900+inputDateEd.getYear());
		URI = '/getPopReportByCategory/'+ '2/'+startDatestring+'/'+endDatestring;
		
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					ac.showPopRep = true;
					ac.reportdata.items = [];
					/*for(var i=0; i<res.data.length;i++){
						ac.reportdata.items[i] = res.data[i];
					}
					for(var i=0;i<18;i++){
						ac.reportdata.items[i] = res.data[0];
					}*/
					ac.reportdata.items[1] = res.data[0];
					ac.reportdata.items[2] = res.data[1];
					ac.reportdata.items[3] = res.data[1];
					ac.reportdata.items[4] = res.data[0];
					ac.reportdata.items[5] = res.data[0];
					ac.reportdata.items[6] = res.data[0];
					ac.reportdata.items[7] = res.data[1];
					ac.reportdata.items[8] = res.data[0];
					ac.reportdata.items[9] = res.data[0];
					ac.reportdata.items[10] = res.data[1];
					ac.reportdata.items[11] = res.data[0];
					ac.reportdata.items[12] = res.data[0];
					ac.reportdata.items[13] = res.data[1];
					ac.reportdata.items[14] = res.data[0];
					ac.reslength1 = ac.reportdata.length;
				},function(errResponse){
					console.log(errResponse);
				});
	}

	ac.getReportByCategory = function(){
		console.log("in category " +ac.report_category);
		var startDt = ac.startDt;
		var endDt = ac.endDt;
		var month;
		var inputDateSt = new Date(startDt);
		var inputDateEd = new Date(endDt);
		
		if((inputDateSt.getMonth()+1) < 9){
			monthSt = "0"+inputDateSt.getMonth();
		}else{
			monthSt = inputDateSt.getMonth()+1;
		}
		
		if((inputDateEd.getMonth()+1) < 9){
			monthEd = "0"+inputDateEd.getMonth();
		}else{
			monthEd = inputDateEd.getMonth()+1;
		}
		startDatestring = inputDateSt.getDate()+"-"+monthSt+"-"+(1900+inputDateSt.getYear());
		endDatestring = inputDateEd.getDate()+"-"+monthEd+"-"+(1900+inputDateEd.getYear());
		URI = '/getPopReportByCategory/'+2+ '/'+startDatestring+'/'+endDatestring;
		app_service.getRequest(URI).then(
				function(res){
					console.log("in success");
					console.log(res);
					ac.showPopRep = true;
					ac.reportdata.items = [];
					/*for(var i=0; i<res.data.length;i+2){
						ac.reportdata.items[i] = res.data[0];
					}*/
					ac.reportdata.items[1] = res.data[0];
					ac.reportdata.items[2] = res.data[1];
					ac.reportdata.items[3] = res.data[1];
					ac.reportdata.items[4] = res.data[0];
					ac.reportdata.items[5] = res.data[0];
					ac.reportdata.items[6] = res.data[0];
					ac.reportdata.items[7] = res.data[1];
					ac.reportdata.items[8] = res.data[0];
					ac.reportdata.items[9] = res.data[0];
					ac.reportdata.items[10] = res.data[1];
					ac.reportdata.items[11] = res.data[0];
					ac.reportdata.items[12] = res.data[0];
					ac.reportdata.items[13] = res.data[1];
					ac.reportdata.items[14] = res.data[0];
					ac.reslength = res.data.length;
				},function(errResponse){
					console.log(errResponse);
				});
	}

	ac.numberOfPages=function(){
		return Math.ceil(ac.reportdata.items.length/10);   
	}
	
	ac.numberOfPages1=function(){
		return Math.ceil(ac.reportdata.items.length/10);   
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