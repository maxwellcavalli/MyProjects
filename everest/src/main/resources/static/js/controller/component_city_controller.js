'use strict';
 
angular.module('myApp').controller('ComponentCityController', ['$scope', '$log', '$rootScope', '$mdDialog', 'ComponentCityService', 
	function($scope, $log, $rootScope, $mdDialog, ComponentCityService) {
	
    var self 		= this;
    self.datatable  = new Datatable();
    
    self.datatable.header.push(new DatatableHeader('ID', '10%', 'left'));
	self.datatable.header.push(new DatatableHeader('Name', '40%', 'left'));
	self.datatable.header.push(new DatatableHeader('State', '30%', 'left'));
	self.datatable.header.push(new DatatableHeader('Actions', '20%', 'center'));
    
    //public methods
    self.fetchAll 		= fetchAll;
    self.hideCityDialog	= hideCityDialog;
    self.showCityDialog = showCityDialog;
    
    function hideCityDialog() {
    	$log.info('hide');
    	$mdDialog.hide();
	}
    
    function showCityDialog(ev, dialogName) {
    	$log.info(ev);
    	
    	$mdDialog.show({
    		contentElement: '#'+dialogName,
    		parent: angular.element(document.body),
    		targetEvent: ev,
    		clickOutsideToClose: true,
    		//controllerAs: 'DistrictController',
    		bindToController: true
    	})      
    }
    
    
    function fetchAll(pageNumber){
    	$log.info('Fetching all ...');
    	
    	var pageSize = parseInt(self.datatable.paginator.selectedOption.id);
    	var filter = encodeURI('%'+self.datatable.filter+'%');
    	
    	if (pageNumber == 0 || (pageNumber >= 0 && pageNumber < self.datatable.pages.totalPages)){
    		ComponentCityService.fetchAll(pageNumber, pageSize, filter).then(
				function(d) {
					self.datatable.data 			= d.data;
					self.datatable.pages 			= d.page;
					self.datatable.pageInformation 	= [];
				
					var currentPage 	 = self.datatable.pages.number + 1;
					var totalPages 		 = self.datatable.pages.totalPages;
					var pageInformations = calculaDatatablePager(currentPage, totalPages);
					
					var x = 0;
					for (x in pageInformations){
						self.datatable.pageInformation.push(pageInformations[x]);
					}
				},
				
				function(errResponse){
					$log.error('Error while fetching ...');
					
					self.showError = true;
	                self.errorMessage = 'Error fetchting results...';
				}
    		);
    	}
    }
 
    
 
}]);