'use strict';
 
angular.module('myApp').controller('ProductController', ['$scope', '$log', '$rootScope', 'CrudService', 'ConfirmationDialog', 'HOST', 
	function($scope, $log, $rootScope, CrudService, ConfirmationDialog, HOST) {
	
    var self 		= this;
    self.domain		= createDomain();
    self.datatable  = new Datatable();
    
    self.datatable.header.push(new DatatableHeader('ID', '5%', 'left', ''));
	self.datatable.header.push(new DatatableHeader('Name', '40%', 'left', ''));
	self.datatable.header.push(new DatatableHeader('SubGroup', '20%', 'left', ''));
	self.datatable.header.push(new DatatableHeader('Group', '20%', 'left', ''));	
	self.datatable.header.push(new DatatableHeader('Actions', '15%', 'center', 'text-align:center'));
    
    self.showError 	= false;
    self.errorMessage = ''; 
    
    //public methods
    self.submit 	= submit;
    self.edit 		= edit;
    self.remove 	= remove;
    self.reset		= reset;
    self.fetchAll 	= fetchAll;
    
    self.selectProductSubGroup 	= selectProductSubGroup;
    self.cleanProductSubGroup 	= cleanProductSubGroup;
    
    function selectProductSubGroup(d){
    	$log.info(d);
    	self.domain.product_sub_group = d;
    }
    
    function cleanProductSubGroup(){
    	self.domain.product_sub_group = null;
    }
    
    $scope.showConfirm = function(ev, code){
		ConfirmationDialog.showDialog(ev, 
			function fnYes(){
				removeDomain(code)
			},
			
			function fnNo(){}
		);
	}
    
    var REST_SERVICE_URI 	= HOST + '/product/';
    var REST_REPOSITORY_URI = HOST + '/api/productRepo/search/findByNameLikeIgnoreCase?name='
    
    CrudService.init(REST_SERVICE_URI, REST_REPOSITORY_URI);
    
    fetchAll(0);
    
    function createDomain(){
		return {
			product_id : null,
			product_name : '',
			product_sub_group: null
		};
    }
    
    function fetchAll(pageNumber){
    	$log.info('Fetching all ...');
    	
    	var pageSize = parseInt(self.datatable.paginator.selectedOption.id);
    	var filter = encodeURI('%'+self.datatable.filter+'%');
    	
    	if (pageNumber == 0 || (pageNumber >= 0 && pageNumber < self.datatable.pages.totalPages)){
    		CrudService.fetchAll(pageNumber, pageSize, filter).then(
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
    	
        CrudService.create(domain).then(
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
        CrudService.update(domain, code).then(
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
        CrudService.remove(code).then(
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
        if(self.domain.product_id===null){
            $log.info('Saving New Domain', self.domain);
            create(self.domain);
        }else{
            update(self.domain, self.domain.product_id);
            $log.info('Domain updated with code ', self.domain.product_id);
        }
    }
 
    function edit(code){
    	$log.info('code to be edited', code);
    	
        for(var i = 0; i < self.datatable.data.length; i++){
            if(self.datatable.data[i].product_id === code) {
                self.domain = angular.copy(self.datatable.data[i]);
                break;
            }
        }
        
        focusOnFirstField(angular.element('form'));
    }
 
    function remove(code){
        $log.info('code to be deleted', code);
        
        if(self.domain.product_id === code) {
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