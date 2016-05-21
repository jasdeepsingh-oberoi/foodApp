App.controller('AppController', function($scope,app_service,$location,$window){
	var URI;
	$scope.signedup = false;
	$scope.alreadyexists = false;
	$scope.invalidcredentials = false;
	$scope.wrongverification = false;
	$scope.signUp = function(){

		var newUserData = {"email" : $scope.signUpUsername,
				"password" : $scope.signUpPassword};

		URI = '/signup';
		app_service.postRequest(URI,newUserData).then(
				function(res){
					if(res.status == 201){
						$scope.signedup = true;
					}
				},function(errResponse){
					if(errResponse.status == 409){
						$scope.alreadyexists = true;
						console.log(errResponse);
					}
					console.log(errResponse);
				});
		
	};

	$scope.signIn = function(){

		var newUserData = {"email" : $scope.signInUsername,
				"password" : $scope.signInPassword};

		URI = '/signin';
		app_service.postRequest(URI,newUserData).then(
				function(response){
					console.log('in success ');
					console.log(response);
					$window.localStorage.username = $scope.signInUsername;
					if(response.data.isAdmin=="0"){
						window.location = '/termprj/dashboard';
					}else if (response.data.isAdmin != null && response.data.isAdmin == "1"){
						window.location = '/termprj/adminDashboard';
					}
					
				}, 
				function(errResponse){
					console.error('Error while fetching users in controller');
					console.error(errResponse);
					console.error(errResponse.data);
					console.log(errResponse.data.is_verified);
					if(errResponse.status == 401 && errResponse.data.isVerified == "0"){
						window.location = '/termprj/verification';
						/*URI = '/verification';
						app_service.getRequest(URI).then(function(res){
							console.log(res);
						});*/
					}else{
						$scope.invalidcredentials = true;
					}
				});
	};

	$scope.verifyCode = function(){

		var newUserData = {"email" : $scope.email,
				"verification_code" : $scope.code};

		URI = '/authenticate';
		app_service.postRequest(URI,newUserData).then(
				function(res){
					console.log(res);
					if(res.status == 200){
						$window.localStorage.username = $scope.email;
						window.location = '/termprj/dashboard';
					}
				}, function(errResponse){
					$scope.wrongverification = true;
					console.log(errResponse);
				});
	};



});