'use strict';
 
angular.module('myApp').controller('ComponentProductSubGroupController', ['$scope', '$log', '$rootScope', '$mdDialog', 'ComponentProductSubGroupService', 
	function($scope, $log, $rootScope, $mdDialog, ComponentProductSubGroupService) {
	
    var self 		= this;
    self.datatable  = new Datatable();
    self.datatable.header.push(new DatatableHeader('ID', '5%', 'left'));
	self.datatable.header.push(new DatatableHeader('SubGroup', '45%', 'left'));
	self.datatable.header.push(new DatatableHeader('Group', '40%', 'left'));
	self.datatable.header.push(new DatatableHeader('Actions', '10%', 'center'));
    
    //public methods
    self.fetchAll 					= fetchAll;
    self.hideProductSubGroupDialog	= hideProductSubGroupDialog;
    self.showProductSubGroupDialog 	= showProductSubGroupDialog;
    
    
    self.filter	= {
    		group : '',
    		subGroup : ''
    }
    
    function hideProductSubGroupDialog() {
    	$log.info('hide');
    	$mdDialog.hide();
	}
    
    function showProductSubGroupDialog(ev, dialogName) {
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
    	var group = encodeURI('%'+self.filter.group+'%');
    	var subGroup = encodeURI('%'+self.filter.subGroup+'%');
    	
    	if (pageNumber == 0 || (pageNumber >= 0 && pageNumber < self.datatable.pages.totalPages)){
    		ComponentProductSubGroupService.fetchAll(pageNumber, pageSize, subGroup, group).then(
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