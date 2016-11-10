'use strict';
 
angular.module('myApp').factory('CrudService', ['$http', '$q', '$log', function($http, $q, $log){
 
    var location = {
    	controller_url : '',
    	repository_url : ''
    }
    
    var factory = {
    	init				: init,		
        fetchAll			: fetchAll,
        create				: create,
        update				: update,
        remove				: remove,
        fetchAllByUrl		: fetchAllByUrl
    };
 
    return factory;
    
    function init (controller_url, repository_url){
    	$log.info(location);
    	
    	location.controller_url = controller_url;
    	location.repository_url = repository_url;
    	
    	$log.info(location);
    }
    
    
    /*function fetchAllService(pageNumber, pageSize, filter){
    	return $http.get(REST_REPOSITORY_URI + filter+'&size='+pageSize+'&page='+pageNumber);
    }*/
    
    function fetchAllByUrl(url, pageNumber, pageSize, filter) {
        var deferred = $q.defer();
        $http.get(url + filter+'&size='+pageSize+'&page='+pageNumber).then(
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
 
    function fetchAll(pageNumber, pageSize, filter) {
        var deferred = $q.defer();
        $http.get(location.repository_url + filter+'&size='+pageSize+'&page='+pageNumber).then(
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
 
    function create(domain) {
        var deferred = $q.defer();
        $http.post(location.controller_url, domain).then(
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
        $http.put(location.controller_url+id, domain).then(
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
        $http.delete(location.controller_url+id).then(
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