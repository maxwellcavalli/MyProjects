<%@ attribute name="controller" required="true" %>


<nav aria-label="Page navigation" class="page-position">
	<ul class="pagination">
		<li class="{{${controller}.datatable.pages.number > 0 ? '' : 'disabled'}}" >
			<a href aria-label="Previous" ng-click="${controller}.fetchAll(${controller}.datatable.pages.number - 1)">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	    
	    <li ng-repeat="page in ${controller}.datatable.pageInformation" ng-class="page.className">
	    	<a href ng-click="${controller}.fetchAll(page.number)">
	    		<span ng-bind="page.label"></span>
	    	</a>
	    </li>
	    
	    <li class="{{${controller}.datatable.pages.number >= (${controller}.datatable.pages.totalPages - 1) ? 'disabled' : ''}}">
			<a href aria-label="Next" ng-click="${controller}.fetchAll(${controller}.datatable.pages.number + 1)">
	        	<span aria-hidden="true">&raquo;</span>
			</a>
	    </li>
	    
	    <li>
	    	<select name="mySelect" id="mySelect" style="height: 31px; margin-left: 5px"
			      ng-options="option.name for option in ${controller}.datatable.paginator.availableOptions track by option.id"
			      ng-model="${controller}.datatable.paginator.selectedOption"
			      ng-change="${controller}.fetchAll(${controller}.datatable.pages.number)"></select>
	    </li>
	</ul>
</nav>