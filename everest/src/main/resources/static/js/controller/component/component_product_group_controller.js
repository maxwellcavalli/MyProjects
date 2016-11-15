'use strict';
 
angular.module('myApp').controller('ComponentProductGroupController', ['$scope', '$log', '$rootScope', '$mdDialog', 'ComponentProductGroupService', 
	function($scope, $log, $rootScope, $mdDialog, ComponentProductGroupService) {
	
    var self 		= this;
    self.datatable  = new Datatable();
    self.datatable.header.push(new DatatableHeader('ID', '10%', 'left'));
	self.datatable.header.push(new DatatableHeader('Name', '25%', 'left'));
	self.datatable.header.push(new DatatableHeader('Actions', '20%', 'center'));
    
    //public methods
    self.fetchAll 				= fetchAll;
    self.hideProductGroupDialog	= hideProductGroupDialog;
    self.showProductGroupDialog = showProductGroupDialog;
    
    function hideProductGroupDialog() {
    	$log.info('hide');
    	$mdDialog.hide();
	}
    
    function showProductGroupDialog(ev, dialogName) {
    	$mdDialog.show({
    		contentElement: '#'+dialogName,
    		parent: angular.element(document.body),
    		targetEvent: ev,
    		clickOutsideToClose: true,
    		bindToController: true
    	})      
    }
    
    function fetchAll(pageNumber){
    	$log.info('Fetching all ...');
    	
    	var pageSize = parseInt(self.datatable.paginator.selectedOption.id);
    	var filter = encodeURI('%'+self.datatable.filter+'%');
    	
    	if (pageNumber == 0 || (pageNumber >= 0 && pageNumber < self.datatable.pages.totalPages)){
    		ComponentProductGroupService.fetchAll(pageNumber, pageSize, filter).then(
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