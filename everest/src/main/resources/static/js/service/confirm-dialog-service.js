'use strict';
 
angular.module('myApp').factory('ConfirmationDialog', ['$mdDialog', function($mdDialog) {
	
	 var factory = {
		showDialog : showDialog
	 };
	 
	 return factory;
	
	function showDialog(ev, fnYes, fnNo){
	    // Appending dialog to document.body to cover sidenav in docs app
	    var confirm = $mdDialog.confirm()
	          .title('Confirmation')
	          .textContent('Do you really confirm this?')
	          .ariaLabel('Confirmation')
	          .targetEvent(ev)
	          .ok('Yes')
	          .cancel('No');

	    $mdDialog.show(confirm).then(function() {
	    	fnYes();
	    }, function() {
	    	fnNo();
	    });
	  };
	
}]);