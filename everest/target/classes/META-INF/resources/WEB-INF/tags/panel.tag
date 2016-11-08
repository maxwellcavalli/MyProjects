<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<%@attribute name="controls" fragment="true" %>
<%@ attribute name="title" required="true" %>


<div class="panel panel-default">
	<!-- Default panel contents -->
 	<div class="panel-heading" style="padding: 10px 0px;">
 		<div class="col-md-7" >
  			<span class="lead">${title}</span>
 		</div>
 	
 		<div class="col-md-5" >
 			<jsp:invoke fragment="controls"/>
 		</div>
 	
 		<div style="clear:both"> </div>
 	</div>
 	
 	<div class="formcontainer">
 		<jsp:doBody></jsp:doBody>
 	</div>
</div>
