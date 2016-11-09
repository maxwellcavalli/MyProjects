'use strict';
 
angular.module('myApp').factory('StateService', ['$http', '$q', '$log', 'HOST', function($http, $q, $log, HOST){
 
    var REST_SERVICE_URI 	= HOST + '/state/';
    var REST_REPOSITORY_URI = HOST + '/api/states/search/findByNameLikeIgnoreCase?name='
 
    var factory = {
        fetchAll			: fetchAll,
        create				: create,
        update				: update,
        remove				: remove,
        fetchAllService 	: fetchAllService
    };
 
    return factory;
    
    function fetchAllService(pageNumber, pageSize, filter){
    	return $http.get(REST_REPOSITORY_URI + filter+'&size='+pageSize+'&page='+pageNumber);
    }
 
    function fetchAll(pageNumber, pageSize, filter) {
        var deferred = $q.defer();
        fetchAllService(pageNumber, pageSize, filter).then(
            function (response) {
            	var records = response.data._embedded.states;
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
 
    function create(domain) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, domain).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function update(domain, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, domain).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function remove(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        
        return deferred.promise;
    }
 
}]);