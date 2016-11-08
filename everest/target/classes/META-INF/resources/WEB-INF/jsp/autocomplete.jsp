<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>


<html>
	<head>  
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  
	    <title>AngularJS $http Example</title>  
	    <style>
			.inputdemoErrors .inputErrorsApp {
			  min-height: 48px; }
			
			.inputdemoErrors md-input-container > p {
			  font-size: 0.8em;
			  text-align: left;
			  width: 100%; }
	     </style>
	    
	    <!-- jquery dependencies -->
		<script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
	    
	    <!-- bootstrap dependencies -->
	    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
		<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="webjars/angular-material/1.1.1//angular-material.min.css">
		
	    <link rel="stylesheet" href="css/app.css">
	</head>
	
  <body ng-app="myApp" class="ng-cloak">
  
  	<div ng-controller="AutocompleteController as ctrl" layout="column" ng-cloak class="md-inline-form">
  		
	  <md-content class="md-padding">
	    <form ng-submit="$event.preventDefault()">
	      <p>Use <code>md-autocomplete</code> to search for matches from local or remote data sources.</p>
	      <md-autocomplete
	          ng-disabled="ctrl.isDisabled"
	          md-no-cache="ctrl.noCache"
	          md-selected-item="ctrl.selectedItem"
	          md-search-text-change="ctrl.searchTextChange(ctrl.searchText)"
	          md-search-text="ctrl.searchText"
	          md-selected-item-change="ctrl.selectedItemChange(item)"
	          md-items="item in ctrl.querySearch(ctrl.searchText)"
	          md-item-text="item.username"
	          md-min-length="0"
	          placeholder="Select a User">
	          
	        <md-item-template>
	          <span md-highlight-text="ctrl.searchText" md-highlight-flags="^i">{{item.username}}</span>
	        </md-item-template>
	        
	        <md-not-found>
	          No states matching "{{ctrl.searchText}}" were found.
	          <a ng-click="ctrl.newState(ctrl.searchText)">Create a new one!</a>
	        </md-not-found>
	        
	      </md-autocomplete>
	      <br/>
	      
	      <md-checkbox ng-model="ctrl.simulateQuery">Simulate query for results?</md-checkbox>
	      <md-checkbox ng-model="ctrl.noCache">Disable caching of queries?</md-checkbox>
	      <md-checkbox ng-model="ctrl.isDisabled">Disable the input?</md-checkbox>
	      <p>By default, <code>md-autocomplete</code> will cache results when performing a query.  After the initial call is performed, it will use the cached results to eliminate unnecessary server requests or lookup logic. This can be disabled above.</p>
	    </form>
	  </md-content>
	  
	  
	  <md-content md-theme="docs-dark" layout-gt-sm="row" layout-padding>
	    <div>
	      <md-input-container>
	        <label>Title</label>
	        <input ng-model="user.title">
	      </md-input-container>
	
	      <md-input-container>
	        <label>Email</label>
	        <input ng-model="user.email" type="email">
	      </md-input-container>
	    </div>
	  </md-content>
	  
	  <md-content layout-padding>
    <form name="projectForm">

      <md-input-container class="md-block">
        <label>Description</label>
        <input md-maxlength="30" required md-no-asterisk name="description" ng-model="project.description">
        <div ng-messages="projectForm.description.$error">
          <div ng-message="required">This is required.</div>
          <div ng-message="md-maxlength">The description must be less than 30 characters long.</div>
        </div>
      </md-input-container>

      <div layout="row">
        <md-input-container flex="50">
          <label>Client Name</label>
          <input required name="clientName" ng-model="project.clientName">
          <div ng-messages="projectForm.clientName.$error">
            <div ng-message="required">This is required.</div>
          </div>
        </md-input-container>

        <md-input-container flex="50">
          <label>Project Type</label>
          <md-select name="type" ng-model="project.type" required>
            <md-option value="app">Application</md-option>
            <md-option value="web">Website</md-option>
          </md-select>
        </md-input-container>
      </div>

      <md-input-container class="md-block">
        <label>Client Email</label>
        <input required type="email" name="clientEmail" ng-model="project.clientEmail"
               minlength="10" maxlength="100" ng-pattern="/^.+@.+\..+$/" />

        <div ng-messages="projectForm.clientEmail.$error" role="alert">
          <div ng-message-exp="['required', 'minlength', 'maxlength', 'pattern']">
            Your email must be between 10 and 100 characters long and look like an e-mail address.
          </div>
        </div>
      </md-input-container>

      <md-input-container class="md-block">
        <label>Hourly Rate (USD)</label>
        <input required type="number" step="any" name="rate" ng-model="project.rate" min="800"
               max="4999" ng-pattern="/^1234$/" />

        <div ng-messages="projectForm.rate.$error" multiple md-auto-hide="false">
          <div ng-message="required">
            You've got to charge something! You can't just <b>give away</b> a Missile Defense
            System.
          </div>

          <div ng-message="min">
            You should charge at least $800 an hour. This job is a big deal... if you mess up,
            everyone dies!
          </div>

          <div ng-message="pattern">
            You should charge exactly $1,234.
          </div>

          <div ng-message="max">
            {{projectForm.rate.$viewValue | currency:"$":0}} an hour? That's a little ridiculous. I
            doubt even Bill Clinton could afford that.
          </div>
        </div>
      </md-input-container>

      <div>
        <md-button type="submit">Submit</md-button>
      </div>

      <p style="font-size:.8em; width: 100%; text-align: center;">
        Make sure to include <a href="https://docs.angularjs.org/api/ngMessages" target="_blank">ngMessages</a> module when using ng-message markup.
      </p>
    </form>
  </md-content>


	<div class="dialog-demo-content" layout="row" layout-wrap
		layout-margin layout-align="center">
		<md-button class="md-primary md-raised" ng-click="showAlert($event)">
		Alert Dialog </md-button>
		<md-button class="md-primary md-raised"
			ng-click="showConfirm($event)"> Confirm Dialog </md-button>
	</div>
	
	

	</div>
	

	
  

	  <!-- begin angular dependencies -->	       
      <script type="text/javascript" src="webjars/angularjs/1.5.8/angular.min.js"></script>
      <script type="text/javascript" src="webjars/angular-animate/1.5.8/angular-animate.js"></script>
   	  <script type="text/javascript" src="webjars/angular-aria/1.5.8/angular-aria.min.js"></script>
  	  <script type="text/javascript" src="webjars/angular-messages/1.5.8/angular-messages.js"></script>
  	  <script type="text/javascript" src="webjars/angular-material/1.1.1/angular-material.min.js"></script>
  	  
  	  <!-- end angular dependencies -->
  	  
      <script type="text/javascript" src="js/app.js"></script>
      
      <script type="text/javascript">
      	angular.module('myApp').constant('HOST', '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}');
      </script>
      
      <script type="text/javascript" src="js/service/user_service.js"></script>
      <script type="text/javascript" src="js/controller/autocomplete_controller.js"></script>
      
     
      
     
      
      
  </body>
</html>