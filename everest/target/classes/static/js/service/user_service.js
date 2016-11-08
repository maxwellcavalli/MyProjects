'use strict';
 
angular.module('myApp').factory('UserService', ['$http', '$q', 'HOST', function($http, $q, HOST){
 
    var REST_SERVICE_URI 	= HOST + '/user/';
    var REST_REPOSITORY_URI = HOST + '/userRepository/search/findByUsernameLikeIgnoreCase?username='
 
    var factory = {
        fetchAllUsers			: fetchAllUsers,
        createUser				: createUser,
        updateUser				: updateUser,
        deleteUser				: deleteUser,
        fetchAllUsersService 	: fetchAllUsersService
    };
 
    return factory;
    
    function fetchAllUsersService(pageNumber, pageSize, filter){
    	return $http.get(REST_REPOSITORY_URI + filter+'&size='+pageSize+'&page='+pageNumber);
    }
 
    function fetchAllUsers(pageNumber, pageSize, filter) {
        var deferred = $q.defer();
        fetchAllUsersService(pageNumber, pageSize, filter).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        
        return deferred.promise;
    }
 
}]);