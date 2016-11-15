'use strict';
 
angular.module('myApp').factory('ComponentProductSubGroupService', ['$http', '$q', '$log', '$mdDialog', 'HOST', function($http, $q, $log, $mdDialog, HOST){
 
    var REST_REPOSITORY_URI = HOST + '/api/productSubGroupRepo/search/findByNameAndGroupLikeIgnoreCase'
 
    var factory = {
    	fetchAll : fetchAll
    };
    
    return factory;
    
    function fetchAll(pageNumber, pageSize, subGrupo, grupo) {
        var deferred = $q.defer();
        $http.get(REST_REPOSITORY_URI + '?name='+subGrupo+'&group='+grupo+'&size='+pageSize+'&page='+pageNumber).then(
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