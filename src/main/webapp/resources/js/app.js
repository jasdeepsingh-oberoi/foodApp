var App = angular.module('myApp',['ngRoute','jkuri.timepicker'])
.config(['$locationProvider', '$routeProvider', function($locationProvider,$routeProvider) {
	  $locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
	  
	  
  $routeProvider
  .when('/verification', {
    templateUrl: 'termprj/verificationPage',
    //controller: 'LoginCtrl'
  })
 .otherwise({
    redirectTo: '/'
  });
}]);

App.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});
