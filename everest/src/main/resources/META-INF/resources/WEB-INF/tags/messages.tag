<%@attribute name="controller" required="true" %>

<div data-ng-show="${controller}.showError" ng-class="{fade:doFade}" class="alert alert-danger">
	<strong>Error:</strong> <span ng-bind="${controller}.errorMessage"></span> {{}}
</div>