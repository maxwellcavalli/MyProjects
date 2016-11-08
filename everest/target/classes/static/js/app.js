var myApp = angular.module('myApp', ['ngMaterial', 'ngMessages']);

angular.module('myApp').directive('formFocus', function($timeout, $log) {
	return {
		scope : {
			focus : '@formFocus'
		},
		link : function(scope, element) {
			scope.$watch('focus', function(value) {
				 $log.info('formFocus');
				
				if (value === "true") {
					$timeout(function() {
						focusOnFirstField(element);
					});
				}
			});
		}
	};
});






(function(angular) {
	'use strict';

	function ifLoading($http) {
		return {
			restrict : 'A',
			link : function(scope, elem) {
				scope.isLoading = isLoading;

				scope.$watch(scope.isLoading, toggleElement);

				function toggleElement(loading) {
					console.log(loading);
					
					if (loading) {
						elem.show();
						console.log('show');
					} else {
						elem.hide();
						console.log('hide');
					}
				}

				function isLoading() {
					return $http.pendingRequests.length > 0;
				}
			}
		};
	}

	ifLoading.$inject = [ '$http' ];

	angular.module('myApp').directive('ifLoading', ifLoading);
}(angular));



function focusOnFirstField(parentElement){
	window.setTimeout(function(){
		var inputs = parentElement.find('input'),
		count = inputs.length;

		for (var i = 0; i < count; i++) {
			 var o = $(inputs[i]);
			 
			 if (o.attr('disabled') == undefined){
				 if (o.attr('type') != 'hidden'){
					 inputs[i].focus();
					 break;
				 }
			 }
		}	
	}, 300);
	
}

function calculaDatatablePager(currentPage, totalPages){
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
		
		if ((currentPage + qtd) <= totalPages){
			positions.push(currentPage + qtd);
		}
		
		if (positions.length >= 5){
			break;
		}
		qtd++;
	}
	
	positions.sort();
	
	var pageInformation = [];
	var x = 0;
	
	for (x in positions){
		var className 	= (positions[x] - 1) == (currentPage - 1) ? 'active' : '';
		var label 		= parseInt(positions[x]);
		var pageData 	= {number:(positions[x] -1), label: label, className:className};
		
		pageInformation.push(pageData)
	}
	
	return pageInformation;
}

function Datatable(){
	return datatable = {
			pageInformation:[],
			paginator: {
		    		availableOptions:[
		    		                  {id: '5',  name: '5'},
		    		                  {id: '10', name: '10'},
		    		                  {id: '15', name: '15'},
		    		                  {id: '30', name: '30'},
		    		                  {id: '50', name: '50'}
		    		                 ],
		    	   selectedOption: {id: '5', name: '5'}
			},
			pages:[],
			filter:'',
			data:[],
			header:[]
	}
}

function DatatableHeader(pTitle, pWidth, pAlign, pName){
	return header = {
			title: pTitle,
			width: pWidth,
			align: pAlign,
			name: pName
	}
}


	

