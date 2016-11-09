<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="name" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="model" required="true" %>
<%@ attribute name="form" required="true" %>
<%@ attribute name="ngOptions" required="true" %>


<%@ attribute name="styleClass" required="false" %>
<%@ attribute name="required" required="false" %>
<%@ attribute name="disabled" required="false" %>


<c:set var="required" value="${(empty required) ? false : required}" />
<c:set var="styleClass" value="${(empty styleClass) ? 'col-md-12' : styleClass}" />
<c:set var="disabled" value="${(empty disabled) ? '' : 'disabled'}" />


<div class="form-group ${styleClass}">
   <label class="col-md-12 control-lable" for="${name}">${label}</label>
   <div class="col-md-12">
   		<c:choose>
			<c:when test="${required == true}">
				<select name="${name}" id="${name}" 
			      		ng-options="${ngOptions}"
			      		ng-model="${model}" required
			      		${disabled}
			      		class="form-control input-sm">
			    	<option disabled selected value> -- select an option -- </option>
			      		
			    </select>
			</c:when>
			<c:otherwise>
				<select name="${name}" id="${name}" 
			      		ng-options="${ngOptions}"
			      		ng-model="${model}" required
			      		${disabled}
			      		class="form-control input-sm">
			      		
			      	<option disabled selected value> -- select an option -- </option>
			    </select>
			</c:otherwise>
		</c:choose>	 
       	
        <div class="has-error" ng-show="${form}.$submitted || (${form}.$dirty && ${form}.${name}.$touched)">
            <div ng-show="${form}.${name}.$error.required">This is a required field</div>
        </div>
    </div>
</div>

