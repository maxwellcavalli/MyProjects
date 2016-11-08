'use strict';


	 

angular.module('myApp')
	.controller('AutocompleteController',['$scope', '$timeout', '$q', '$log', 'ConfirmationDialog', 'UserService', 'HOST', 
    function($scope, $timeout, $q, $log, ConfirmationDialog, UserService, HOST) {
		
		var self 			= this;
		self.simulateQuery 	= true;
		self.isDisabled 	= false;
		
		// list of `state` value/display objects
		self.users 				= [];
		self.querySearch 		= querySearch;
		self.selectedItemChange = selectedItemChange;
		self.searchTextChange 	= searchTextChange;
		self.newState 			= newState;
		self.selectedItem 		= null;

		function newState(state) {
			alert("Sorry! You'll need to create a Constituion for "+ state + " first!");
		}
		
		$scope.showConfirm = function(ev){
			ConfirmationDialog.showDialog(ev, 
				function fnYes(){
					alert('Yes');
				},
				
				function fnNo(){
					alert('No');
				}
			);
				
		}
		

		// ******************************
		// Internal methods
		// ******************************
		/**
		 * Search for states... use $timeout to simulate
		 * remote dataservice call.
		 */
		function querySearch(query) {
			 var filter = encodeURI('%'+query+'%');
			 var deferred = $q.defer();
			
			 UserService.fetchAllUsersService(0, 50, filter).then(
	            function (response) {
	                deferred.resolve(response.data._embedded.userRepository);
	            	//self.users = response.data._embedded.userRepository;
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
		}
		
		function searchTextChange(text) {
			$log.info('Text changed to ' + text);
		}
		
		function selectedItemChange(item) {
			$log.info('Item changed to '+ JSON.stringify(item));
		}
		
		/**
		 * Build `states` list of key/value pairs
		 */
		function loadAll(query) {
			var filter = encodeURI('%'+query+'%');
			self.users = [];
			
			UserService.fetchAllUsers(0, 50, filter).then(
				function(d) {
					self.users = d._embedded.userRepository
					
					$log.info("[loadAll]");
					$log.info(self.users);
				},
				
				function (errorResponse){
					$log.info(errorResponse);
				}
    		)
		}
		
		/**
		 * Create filter function for a query string
		 */
		function createFilterFor(query) {
			var lowercaseQuery = angular.lowercase(query);
			
			return function filterFn(value) {
				$log.info("createFilterFor" + value.username.indexOf(lowercaseQuery));
				return (angular.lowercase(value.username).indexOf(lowercaseQuery) === 0);
			};
		}

	} ]);