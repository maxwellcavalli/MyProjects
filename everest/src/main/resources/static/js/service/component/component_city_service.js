'use strict';
 
angular.module('myApp').factory('ComponentCityService', ['$http', '$q', '$log', '$mdDialog', 'HOST', function($http, $q, $log, $mdDialog, HOST){
 
    var REST_REPOSITORY_URI = HOST + '/api/cityRepo/search/findByNameLikeIgnoreCase?name='
 
    var factory = {
    	fetchAll 		: fetchAll
    };
    
    return factory;
    
    function fetchAll(pageNumber, pageSize, filter) {
        var deferred = $q.defer();
        $http.get(REST_REPOSITORY_URI + filter+'&size='+pageSize+'&page='+pageNumber).then(
            function (response) {
            	var records = response.data._embedded.d;
            	var retorno = {data:[], page:null }
            	
				var x = 0;
				for (x in records){
					var s = records[x].content;
					var obj = JSON.parse(s);
					
					retorno.data.push(obj);
				}
            	
				retorno.page = response.data.page;
				
                deferred.resolve(retorno);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);