'use strict';
 
angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self 	= this;
    self.user	= {code:null,username:'',address:'',email:''};
    self.users	= [];
    
    self.datatable = {
    		pageInformation:[],
    		paginator: {
    	    		availableOptions:[
    	    		                  {id: '5',  name: '5'},
    	    		                  {id: '10', name: '10'},
    	    		                  {id: '15', name: '15'}
    	    		                 ],
    	    	   selectedOption: {id: '5', name: '5'}
    		},
    		pages:[],
    		filter:''
    }
    
    
    self.submit = submit;
    self.edit 	= edit;
    self.remove = remove;
    self.reset	= reset;
    self.fetchAllUsers = fetchAllUsers;
 
    fetchAllUsers(0);
    
    function fetchAllUsers(pageNumber){
    	var pageSize = parseInt(self.datatable.paginator.selectedOption.id);
    	var filter = encodeURI('%'+self.datatable.filter+'%');
    	
    	if (pageNumber == 0 || (pageNumber >= 0 && pageNumber < self.datatable.pages.totalPages)){
    		UserService.fetchAllUsers(pageNumber, pageSize, filter).then(
    				function(d) {
    					self.users = d._embedded.userRepository;
    					
    					self.datatable.pages = d.page;
    					self.datatable.pageInformation = [];
    					
    					var currentPage = self.datatable.pages.number + 1;
    					var positions = [];
    					
    					positions.push(currentPage);
    					
    					var qtd = 1;
    					while (qtd < 10 ){
    						if ((currentPage - qtd) > 0){
    							positions.push(currentPage - qtd);
    						} 
    						
    						if (positions.length >= 5){
    							break;
    						}
    						
    						if ((currentPage + qtd) <= self.datatable.pages.totalPages){
    							positions.push(currentPage + qtd);
    						}
    						
    						if (positions.length >= 5){
    							break;
    						}
    						qtd++;
    					}
    					
    					positions.sort();
    					
    					var x = 0;
    					for (x in positions){
    						var className = (positions[x] - 1) == self.datatable.pages.number ? 'active' : '';
    						var label = parseInt(positions[x]);
							var pageData = {number:(positions[x] -1), label: label, className:className};
							
							self.datatable.pageInformation.push(pageData)
    					}
    					
    				},
    				
    				function(errResponse){
    					console.error('Error while fetching Users');
    				}
    		);
    	}
    	
    }
 
    function createUser(user){
        UserService.createUser(user).then(
            fetchAllUsers(0),
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
 
    function updateUser(user, code){
        UserService.updateUser(user, code).then(
            fetchAllUsers(),
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }
 
    function deleteUser(code){
        UserService.deleteUser(code).then(
            fetchAllUsers(0),
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }
 
    function submit() {
        if(self.user.code===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.code);
            console.log('User updated with code ', self.user.code);
        }
        reset();
    }
 
    function edit(code){
        console.log('code to be edited', code);
        
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].code === code) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
 
    function remove(code){
        console.log('code to be deleted', code);
        
        if(self.user.code === code) {//clean form if the user to be deleted is shown there.
            reset();
        }
        
        deleteUser(code);
    }
 
 
    function reset(){
        self.user={code:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);