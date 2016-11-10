<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="name" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="model" required="true" %>
<%@ attribute name="form" required="true" %>


<%@ attribute name="styleClass" required="false" %>
<%@ attribute name="maxlength" required="false" %>
<%@ attribute name="minlength" required="false" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="disabled" required="false" %>
<%@ attribute name="ngDisable" required="false" %>


<c:set var="required" value="${(empty required) ? false : required}" />
<c:set var="minlength" value="${(empty minlength) ? 0 : minlength}" />
<c:set var="maxlength" value="${(empty maxlength) ? 255 : maxlength}" />
<c:set var="styleClass" value="${(empty styleClass) ? 'col-md-12' : styleClass}" />
<c:set var="disabled" value="${(empty disabled) ? '' : 'disabled'}" />


<div class="form-group ${styleClass}">
   <label class="col-md-12 control-lable" for="${name}">${label}</label>
   <div class="col-md-12">
   		<c:choose>
			<c:when test="${required == true}">
				<input type="text" ng-model="${model}" name="${name}" class="form-control input-sm" 
      	 			placeholder="${label}" required ng-minlength="${minlength}" ng-maxlength="${maxlength}" 
      	 			maxlength="${maxlength}"
      	 			${disabled} ng-disabled="${ngDisable}"
      	 			autocomplete="off"/>
			</c:when>
			<c:otherwise>
				<input type="text" ng-model="${model}" name="${name}" class="form-control input-sm" 
      	 			placeholder="${label}" ng-minlength="${minlength}" ng-maxlength="${maxlength}"
      	 			maxlength="${maxlength}"
      	 			${disabled} ng-disabled="${ngDisable}"
      	 			autocomplete="off"/>
			</c:otherwise>
		</c:choose>	 
       	
       	<!-- <div class="has-error" ng-show="myForm.$dirty"> -->
        <div class="has-error" ng-show="${form}.$submitted || (${form}.$dirty && ${form}.${name}.$touched)">
            <div ng-show="${form}.${name}.$error.required">This is a required field</div>
            <div ng-show="${form}.${name}.$error.minlength">Minimum length required is ${minlength}</div>
            <div ng-show="${form}.${name}.$error.maxlength">Max length is ${maxlength}</div>
            <%-- <div ng-show="${form}.${name}.$invalid">This field is invalid </div> --%>
        </div>
    </div>
</div>


<%-- <md-input-container class="${styleClass}" flex=${flex}>
	<label>${label}</label>
	
	<c:choose>
		<c:when test="${required == true}">
			<input md-maxlength="${maxlength}" required name="${name}" ng-model="${model}" style="text-transform:uppercase" >
		</c:when>
		<c:otherwise>
			<input md-maxlength="${maxlength}" name="${name}" ng-model="${model}" maxlength="${maxlength}"  style="text-transform:uppercase" >
		</c:otherwise>
	</c:choose>	 
	
	<div ng-messages="${form}.${name}.$error">
		<c:if test="#{required == true}">
			<div ng-message="required">This is required.</div>
		</c:if>
		
		<c:if test="#{not empty maxlength}">
			<div ng-message="md-maxlength">The description must be less than #{maxlength} characters long.</div>
		</c:if>
	</div>
</md-input-container> --%>
