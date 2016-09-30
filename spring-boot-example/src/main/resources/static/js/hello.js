angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider
		.when('/', {
			templateUrl : 'home.html',
			controller : 'home',
			controllerAs: 'controller'
		})
		.when('/login', {
			templateUrl : 'login.html',
			controller : 'navigation',
			controllerAs: 'controller'
		})
		.when('/signup', {
			templateUrl : 'signup.html',
			controller : 'signup',
			controllerAs: 'controller'
		})  
		.otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

	function($scope, $rootScope, $routeParams, $http, $location, $route) {
	
	 	$scope.$on("$locationChangeSuccess", function handleLocationChangeEvent( event ) {
                 console.log( "Location Change:", $location.path() );
             
	 	});
	 	
         $scope.$on("$routeChangeSuccess",
             function handleRouteChangeEvent( event ) {
                 $('[autofocus]').focus();  
            
         });
		
		var self = this;

		self.tab = function(route) {
			return $route.current && route === $route.current.controller;
		};

		var authenticate = function(credentials, callback) {

			var headers = credentials ? {
				authorization : "Basic " + btoa(credentials.username + ":"+ credentials.password)
			} : {};

			$http.get('user', {
				headers : headers
			}).then(function(response) {
				if (response.data.name) {
					$rootScope.authenticated = true;
					loadPermissions($http, $rootScope);
				} else {
					$rootScope.authenticated = false;
					
					$rootScope.nav   = null;
					$rootScope.links = null;
				}
				callback && callback($rootScope.authenticated);
			}, function() {
				$rootScope.authenticated = false;
				
				$rootScope.nav   = null;
				$rootScope.links = null;
				
				callback && callback(false);
			});

		}

		authenticate();

		self.credentials = {};
		self.loginFailed = false;
		
		self.login = function() {
			authenticate(self.credentials, function(authenticated) {
				if (authenticated) {
					console.log("Login succeeded")
					$location.path("/");
					self.loginFailed = false;
					$rootScope.authenticated = true;
					
					console.log('loading permissions');
					
					loadPermissions($http, $rootScope);
				} else {
					console.log("Login failed")
					$location.path("/login");
					self.loginFailed = true;
					$rootScope.authenticated = false;
				}
			})
		};

		self.logout = function() {
			$http.post('logout', {}).finally(function() {
				console.log('logout');
				$rootScope.authenticated = false;
				$rootScope.nav   = null;
				$rootScope.links = null;
				$location.path("/");
			});
		};
		
		var loadPermissions = function($http, $rootScope){
			$http.get('../data/nav.json').success(function(data) {
				$rootScope.nav   = data;
				$rootScope.links = data.links;
			    
			    console.log($rootScope.links);
			});
		};

	}).controller('home', function($http) {
		var self = this;
		
		$http.get('/resource/').then(function(response) {
			self.greeting = response.data;
		})
	}).controller('signup', function($http, $scope, $location) {
			var self = this;
			var urlBase="";
			$scope.validationsFailed = false;
			$scope.registerOk = false;
			
			$scope.register = function register() {
				$('.validationError').each(function(){
					$(this).removeClass('validationError');
				});
				
				$http.post(urlBase + '/registerUser', {
		             username: $scope.username,
		             password: $scope.password,
		             email: $scope.email
		         }).success(function(data, status, headers) {
					 alert("User added");
					 
					 $scope.validationsFailed = false;
					 $scope.registerOk = true;
					 
					 $location.path("/login");
					 console.log(data);
		         }).error(function(data, status){
		        	 $scope.validationsFailed = true;
		        	 $scope.registerOk = false;
		        	 
		        	 $scope.errorMessage = null;
		
		        	 $scope.errors = null;
		        	 
		        	 if (data.exception == 'org.springframework.web.bind.MethodArgumentNotValidException'){
		        		 $scope.errors = data.errors;
		        		 
		        		 var firstElement = null;
		        		 $(data.errors).each(function (){
		        			 var element = angular.element('#'+this.field);
		        			 
		        			 if (firstElement == null){
		        				 firstElement = element;
		        			 }
		        			 
		        			 element.addClass('validationError');
		        		 });
		        		 
		        		 if (firstElement != null){
		        			 firstElement.focus();
		        		 }
		        	 } else {
		        		 $scope.errorMessage = data.message;
		        	 }
		         })
			}
	})
;
