<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<%@attribute name="header" fragment="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>

<%@ attribute name="ngApp" required="true" %>
<%@ attribute name="ngController" required="true" %>


<html>
	<head>  
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  
	    <title>Everest</title>  
	    <style>
	    </style>
	    
	    <!-- jquery dependencies -->
		<script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
	    
	    <!-- bootstrap dependencies -->
	    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
		<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="webjars/angular-material/1.1.1//angular-material.min.css">
	    <link rel="stylesheet" href="css/app.css">
	    
	    <jsp:invoke fragment="head"/>
	</head>

  	 <body ng-app="${ngApp}" class="ng-cloak" style="margin:0; padding:0px; ">
  	 	
  	 	<div class="loadind-flex-container" if-loading>
  			<div>
  				<div class="loader"></div>
  			</div>
		</div>
  	 
  	 
	    <div id="pageheader">
	      	<jsp:invoke fragment="header"/>
	    </div>
	    
	    <div id="body">
	    	<div class="generic-container" ng-controller="${ngController}">
	   			<jsp:doBody/>
	      	</div>
	    </div>
	    
	    <div id="pagefooter" style="text-align: right; padding-right: 15px;">
	      	<p id="copyright">Copyright 2016, Everest Inc.</p>
	    </div>
	    
    	 <!-- begin angular dependencies -->	       
      	<script type="text/javascript" src="webjars/angularjs/1.5.8/angular.min.js"></script>
      	<script type="text/javascript" src="webjars/angular-animate/1.5.8/angular-animate.js"></script>
   	  	<script type="text/javascript" src="webjars/angular-aria/1.5.8/angular-aria.min.js"></script>
  	  	<script type="text/javascript" src="webjars/angular-messages/1.5.8/angular-messages.js"></script>
  	  	<script type="text/javascript" src="webjars/angular-material/1.1.1/angular-material.min.js"></script>
  	  	<!-- end angular dependencies -->
  	  
      	<script type="text/javascript" src="js/app.js"></script>
      	<script type="text/javascript" src="js/service/confirm-dialog-service.js"></script>
      	
      	<script type="text/javascript">
      		angular.module('myApp').constant('HOST', '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}');
      	</script>
	    
	    <jsp:invoke fragment="scripts"/>
	    
  	</body>
</html>
