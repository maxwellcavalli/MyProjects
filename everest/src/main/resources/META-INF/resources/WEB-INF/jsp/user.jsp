<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>


<html>
	<head>  
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  
	    <title>AngularJS $http Example</title>  
	    <style> </style>
	    
	    <!-- jquery dependencies -->
		<script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
	    
	    <!-- bootstrap dependencies -->
	    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
		<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		
	    <link rel="stylesheet" href="css/app.css">
	</head>
	
  <body ng-app="myApp" class="ng-cloak">
  	<h:response name="teste" greeting="teste"/>
  		
      <div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.user.code" />
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="uname">Name</label>
                              <div class="col-md-10">
                                  <input type="text" ng-model="ctrl.user.username" name="uname" class="username form-control input-sm" 
                                  	placeholder="Enter your name" required ng-minlength="3" focus="true"/>
                                  	
                                  <!-- <div class="has-error" ng-show="myForm.$dirty"> -->
                                  <div class="has-error" ng-show="myForm.$submitted || (myForm.$dirty && myForm.uname.$touched)">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="address">Address</label>
                              <div class="col-md-10">
                                  <input type="text" ng-model="ctrl.user.address" name="address" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="email">Email</label>
                              <div class="col-md-10">
                                  <input type="email" ng-model="ctrl.user.email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                                  <div class="has-error" ng-show="myForm.$submitted || (myForm.$dirty && myForm.email.$touched)">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.user.code ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-danger btn-sm" ng-show="ctrl.user.code">Cancel</button>
                              
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading">
              	<div class="col-md-7" >
	              	<span class="lead">List of Users </span>
              	</div>
              	
              	<div class="col-md-5" >
              		<input type="text" ng-model="ctrl.datatable.filter" name="filter" class="input-sm" placeholder="Filter" 
              			style="height: 36px; width: calc(100% - 61px); padding-top: 1px" />
              		<button type="button" ng-click="ctrl.fetchAllUsers(0)" class="btn btn-primary ">Filter</button>
              	</div>
              	
              	<div style="clear:both"> </div>
              </div>
              	
              	
              <div class="tablecontainer">
              
              	 <div id="no-more-tables">
                  <!-- <table class="table table-hover table-striped table-reflow"> -->
                   <table class="col-sm-12 table-bordered table-striped table-condensed cf">
                      <thead class="cf">
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Address</th>
                              <th>Email</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.users">
                              <td data-title="ID"><span ng-bind="u.code"></span></td>
                              <td data-title="Name"><span ng-bind="u.username"></span></td>
                              <td data-title="Address"><span ng-bind="u.address"></span></td>
                              <td data-title="Email"><span ng-bind="u.email"></span></td>
                              <td data-title="Actions" align="center">
                             	<button type="button" ng-click="ctrl.edit(u.code)" class="btn btn-success custom-width">Edit</button>  
                             	<button type="button" ng-click="ctrl.remove(u.code)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table> 
                  
                  <nav aria-label="Page navigation" class="page-position">
					  <ul class="pagination">
					    <li class="{{ctrl.datatable.pages.number > 0 ? '' : 'disabled'}}" >
					      <a href aria-label="Previous" ng-click="ctrl.fetchAllUsers(ctrl.datatable.pages.number - 1)">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    
					    <li ng-repeat="page in ctrl.datatable.pageInformation" ng-class="page.className">
					    	<a href ng-click="ctrl.fetchAllUsers(page.number)">
					    		<span ng-bind="page.label"></span>
					    	</a>
					    </li>
					    
					    <li class="{{ctrl.datatable.pages.number >= (ctrl.datatable.pages.totalPages - 1) ? 'disabled' : ''}}">
					      <a href aria-label="Next" ng-click="ctrl.fetchAllUsers(ctrl.datatable.pages.number + 1)">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    
					    <li>
					    	<select name="mySelect" id="mySelect" style="height: 31px; margin-left: 5px"
							      ng-options="option.name for option in ctrl.datatable.paginator.availableOptions track by option.id"
							      ng-model="ctrl.datatable.paginator.selectedOption"
							      ng-change="ctrl.fetchAllUsers(ctrl.datatable.pages.number)"></select>
					    </li>
					  </ul>
					</nav>	
                 </div>
              </div>
          </div>
      	  
      </div>

	  <!-- begin angular dependencies -->	       
      <script type="text/javascript" src="webjars/angularjs/1.5.8/angular.min.js"></script>
      <script type="text/javascript" src="webjars/angular-animate/1.5.8/angular-animate.js"></script>
   	  <script type="text/javascript" src="webjars/angular-aria/1.5.8/angular-aria.min.js"></script>
  	  <script type="text/javascript" src="webjars/angular-messages/1.5.8/angular-messages.js"></script>
  	  <!-- end angular dependencies -->
      
      <script type="text/javascript" src="js/app.js"></script>
      <script type="text/javascript" src="js/service/user_service.js"></script>
      <script type="text/javascript" src="js/controller/user_controller.js"></script>
      
  </body>
</html>