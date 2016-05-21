App.factory('app_service', function($http){
	var app_service ={};
	
	/** GET call for searching groups and people available in our system **/
	
	app_service.postRequest = function(URI,newUserData) {
		return $http({
			method : 'POST',
			url : '/termprj'+URI,
			data : newUserData
		}).then(function(resp) {
			return resp;
		});
	};
	
	app_service.getRequest = function(URI) {
		return $http({
			method : 'GET',
			url : '/termprj'+URI
		}).then(function(resp) {			
			return resp;
		});
	};
	
	
	return app_service;
});