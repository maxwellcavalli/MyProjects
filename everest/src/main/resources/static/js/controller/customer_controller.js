'use strict';
 
angular.module('myApp').controller('CustomerController', 
		['$scope', '$log', '$rootScope', '$mdDialog', 'CrudService', 'ConfirmationDialog', 'HOST', 
	function($scope, $log, $rootScope, $mdDialog, CrudService, ConfirmationDialog, HOST) {
	
    var self 		= this;
    self.domain		= createDomain();
    self.datatable  = new Datatable();
    
    self.datatable.header.push(new DatatableHeader('ID', '10%', 'left'));
	self.datatable.header.push(new DatatableHeader('Name', '70%', 'left'));
	self.datatable.header.push(new DatatableHeader('Actions', '20%', 'center'));
    
    self.showError 		= false;
    self.errorMessage 	= '';
    
    //public methods
    self.submit 		= submit;
    self.edit 			= edit;
    self.remove 		= remove;
    self.reset			= reset;
    self.fetchAll 		= fetchAll;
    self.selectDistrict = selectDistrict;
    self.cleanDistrict 	= cleanDistrict;
    
    self.addContact		= addContact;
    self.resetContact 	= resetContact;
    self.editContact 	= editContact;
    self.removeContact 	= removeContact;
    
    self.contactType = ['TELEPHONE', 'CELLPHONE', 'FAX', 'EMAIL'];   
    
    self.customer_contacts = createContact(); 
    
    function removeContact(d){
    	var x = 0;
    	var index = -1;
    	for (x in self.domain.customer_contacts){
    		if (self.domain.customer_contacts[x].internal_id == d.internal_id){
    			index = x;
    			break;
    		}
    	}  
    	
    	if (index > -1){
    		self.domain.customer_contacts.splice(index, 1);
    	}
    	
    	$log.info(self.domain.customer_contacts);
    }
    
    function createContact(){
    	return {
    		customer_contact_id : null,
    		customer_contact_name: '',
    		customer_contact_type:null,
    		inserted: false,
    		internal_id: null
    	}
    }
    
    function editContact(d){
    	self.customer_contacts = angular.copy(d);
    }
    
    function addContact(){
    	self.showError 		= false;
    	self.errorMessage 	= '';
    	
    	if (self.customer_contacts.customer_contact_type == null){
    		 self.errorMessage 	= 'Contacts - Type Invalid <br />';
    		 self.showError = true;
    	}
    	
    	if (self.customer_contacts.customer_contact_name == null || self.customer_contacts.customer_contact_name == ''){
    		self.errorMessage 	= self.errorMessage + 'Contacts - Description Invalid <br>';
    		self.showError = true;
    	}
    	
    	if (!self.showError){
	    	if (self.customer_contacts.inserted){
	    		var x = 0;
	    		var index = -1;
	    		for (x in self.domain.customer_contacts){
	    			if (self.domain.customer_contacts[x].internal_id == self.customer_contacts.internal_id){
	    				index = x;
	    				break;
	    			}
	    		}
	    		
	    		if (index > -1){
	    			self.domain.customer_contacts[index] = self.customer_contacts;
	    		}
	    		
	    	} else {
	    		self.customer_contacts.inserted = true;
	    		self.customer_contacts.internal_id = self.domain.customer_contacts.length + 1;
	    		
	    		self.domain.customer_contacts.push(self.customer_contacts);
	    	}
	    	
	    	$log.info(self.domain.customer_contacts);
	    	resetContact();
    	}
    }
    
    function resetContact(){
    	self.customer_contacts = createContact();
    	$('#customerType').focus();
    }
    
    function selectDistrict(d){
    	$log.info(d);
    	self.domain.customer_district = d;
    }
    
    function cleanDistrict(){
    	self.domain.customer_district = null;
    }
      
    var REST_SERVICE_URI 	= HOST + '/customer/';
    var REST_REPOSITORY_URI = HOST + '/api/customerRepo/search/findByNameLikeIgnoreCase?name='
    
    CrudService.init(REST_SERVICE_URI, REST_REPOSITORY_URI);
    
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
    		customer_id:null, 
    		customer_name:'', 
    		customer_district:{
    			customer_id:null,
    			districts_name:''
    		}, 
    		customer_contacts:[]
    	}
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
    	$log.info(domain);
    	
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
    	$log.info('Removing...');
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
        if(self.domain.customer_id===null){
            $log.info('Saving New Domain', self.domain);
            create(self.domain);
        }else{
            update(self.domain, self.domain.customer_id);
            $log.info('Domain updated with code ', self.domain.customer_id);
        }
    }
 
    function edit(code){
    	$log.info('code to be edited', code);
    	
        for(var i = 0; i < self.datatable.data.length; i++){
            if(self.datatable.data[i].customer_id === code) {
                self.domain = angular.copy(self.datatable.data[i]);
                break;
            }
        }
        
        $log.info(self.domain);
        
        var x = 0;
		for (x in self.domain.customer_contacts){
			self.domain.customer_contacts[x].internal_id = x;
			self.domain.customer_contacts[x].inserted = true;
		}
        
        focusOnFirstField(angular.element('form'));
    }
 
    function remove(code){
        $log.info('code to be deleted', code);
        
        if(self.domain.customer_id === code) {
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