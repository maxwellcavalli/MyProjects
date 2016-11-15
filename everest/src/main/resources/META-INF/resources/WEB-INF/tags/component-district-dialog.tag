<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="selectEvent" required="true" %>

<div style="visibility: hidden">
	<div ng-controller="ComponentDistrictController as componentDistrict">
	<div class="md-dialog-container" id="${name}">
		<md-dialog aria-label="City" flex="100%">
			<form ng-cloak>
				<md-toolbar>
					<div class="md-toolbar-tools">
						<h2>${title}</h2>
      					<span flex></span>
      					<md-button class="md-icon-button" ng-click="componentDistrict.hideDistrictDialog()">
        						<md-icon md-svg-src="image/icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
      					</md-button>
   					</div>
 				</md-toolbar>

				<md-dialog-content >
 					<div class="md-dialog-content" flex="100%">
 						<div class="row">
							<div class="col-md-5">
								<input type="text" ng-model="componentDistrict.filter.city" name="filterCity" class="input-sm col-md-12" placeholder="City" 
										style="height: 36px;" />
							</div>
							
							<div class="col-md-5">
								<input type="text" ng-model="componentDistrict.filter.district" name="filterDistrict" class="input-sm col-md-12" placeholder="District" 
										style="height: 36px;" />
							</div>
							
							<div class="col-md-2">
								<button type="button" ng-click="componentDistrict.fetchAll(0)" class="btn btn-primary col-md-12">Filter</button>
							</div>
						</div>
						
								
						<div id="no-more-tables">
							<table class="col-sm-12 table-bordered table-striped table-condensed cf" style="padding-right: 0px; padding-left: 0px; ">
								<thead class="cf">
									<tr>
										<th ng-repeat="d in componentDistrict.datatable.header" width="{{d.width}}" align="{{d.align}}">{{d.title}}</th>
									</tr>
								</thead>
						
								<tbody>
									<tr ng-show="componentDistrict.datatable.data.length == 0"> 
										<td colspan="5">
											<span>No Records Found</span>
										</td>
									</tr>
								
									<tr ng-repeat="d in componentDistrict.datatable.data">
									
										<td data-title="ID"><span ng-bind="d.districts_id"></span></td>
										<td data-title="Name"><span ng-bind="d.districts_name"></span></td>
										<td data-title="City"><span ng-bind="d.districts_city.cities_name"></span></td>
										<td data-title="State"><span ng-bind="d.districts_city.cities_state.states_name"></span></td>
										<td data-title="Actions" align="center">
											<button type="button" ng-click="${selectEvent}; componentDistrict.hideDistrictDialog()" class="btn btn-primary custom-width">Select</button>
										</td>
									</tr>
								</tbody>
							</table>
							<h:paginator controller="componentDistrict"/>
						</div>
					</div>
				</md-dialog-content>

				<md-dialog-actions layout="row">
					<md-button ng-click="componentDistrict.hideDistrictDialog()">
						Close
					</md-button>
				</md-dialog-actions>
			</form>
		</md-dialog>
	</div>
</div>
