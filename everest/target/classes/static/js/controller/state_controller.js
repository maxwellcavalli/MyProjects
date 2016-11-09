'use strict';
 
angular.module('myApp').controller('StateController', ['$scope', '$log', '$rootScope', 'StateService', 'ConfirmationDialog', 
	function($scope, $log, $rootScope, StateService, ConfirmationDialog) {
	
    var self 		= this;
    self.domain		= createDomain();
    self.datatable  = new Datatable();
    
    self.datatable.header.push(new DatatableHeader('ID', '10%', 'left'));
	self.datatable.header.push(new DatatableHeader('Code', '10%', 'left'));
	self.datatable.header.push(new DatatableHeader('Name', '60%', 'left'));
	self.datatable.header.push(new DatatableHeader('Actions', '20%', 'center'));
    
    self.showError 	= false;
    self.errorMessage = ''; 
    
    //public methods
    self.submit 	= submit;
    self.edit 		= edit;
    self.remove 	= remove;
    self.reset		= reset;
    self.fetchAll 	= fetchAll;
    
    $scope.showConfirm = function(ev, code){
		ConfirmationDialog.showDialog(ev, 
			function fnYes(){
				removeDomain(code)
			},
			
			function fnNo(){}
		);
	}
 
    fetchAll(0);
    
    function createDomain(){
		return {
			states_code : null,
			states_abreviation : '',
			states_name : ''
		};
    }
    
    function fetchAll(pageNumber){
    	$log.info('Fetching all ...');
    	
    	var pageSize = parseInt(self.datatable.paginator.selectedOption.id);
    	var filter = encodeURI('%'+self.datatable.filter+'%');
    	
    	if (pageNumber == 0 || (pageNumber >= 0 && pageNumber < self.datatable.pages.totalPages)){
    		StateService.fetchAll(pageNumber, pageSize, filter).then(
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
 
    function create(domain){
    	$log.info('Creating...');
    	
        StateService.create(domain).then(
        	function (response) {	
        		reset();
        		
        		$log.info('After Create...');
        		fetchAll(0);
        	},
            function(errResponse){
                $log.error('Error while creating ...');
                
                self.showError 		= true;
                self.errorMessage 	= errResponse.data.errorMessage;
            }
        );
    }
 
    function update(domain, code){
    	$log.info('Updating...');
        StateService.update(domain, code).then(
        	function (response) {
        		reset();
        		
            	$log.info('After Update...');
            	fetchAll(0);
            },
            function(errResponse){
                $log.error('Error while updating ...');
                
                self.showError 		= true;
                self.errorMessage 	= errResponse.data.errorMessage;
            }
        );
    }
 
    function removeDomain(code){
        StateService.remove(code).then(
        	function (response) {
        		reset();
        		
        		$log.info('After Remove...');
        		fetchAll(0);
        	},
            function(errResponse){
                $log.error('Error while deleting ...');
                
                self.showError 		= true;
                self.errorMessage 	= errResponse.data.errorMessage;
            }
        );
    }
 
    function submit() {
        if(self.domain.states_code===null){
            $log.info('Saving New Domain', self.domain);
            create(self.domain);
        }else{
            update(self.domain, self.domain.states_code);
            $log.info('Domain updated with code ', self.domain.states_code);
        }
    }
 
    function edit(code){
    	$log.info('code to be edited', code);
    	
        for(var i = 0; i < self.datatable.data.length; i++){
            if(self.datatable.data[i].states_code === code) {
                self.domain = angular.copy(self.datatable.data[i]);
                break;
            }
        }
        
        focusOnFirstField(angular.element('form'));
    }
 
    function remove(code){
        $log.info('code to be deleted', code);
        
        if(self.domain.states_code === code) {
            reset();
        }
        
        removeDomain(code);
    }
 
    function reset(){
    	self.domain	= createDomain();
    	
    	$scope.formulario.$setPristine(); //reset Form
    	$scope.formulario.$setValidity();
    	
    	$scope.formulario.$rollbackViewValue();  
    	
    	self.showError 		= false;
        self.errorMessage 	= '';
        
        focusOnFirstField(angular.element('form'));
    }
 
}]);