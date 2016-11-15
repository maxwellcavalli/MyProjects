<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="selectEvent" required="true" %>

<div style="visibility: hidden">
	<div ng-controller="ComponentProductSubGroupController as componentProductSubGroup">
	<div class="md-dialog-container" id="${name}">
		<md-dialog aria-label="City" flex="100%">
			<form ng-cloak>
				<md-toolbar>
					<div class="md-toolbar-tools">
						<h2>${title}</h2>
      					<span flex></span>
      					<md-button class="md-icon-button" ng-click="componentProductSubGroup.hideProductSubGroupDialog()">
        					<md-icon md-svg-src="image/icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
      					</md-button>
   					</div>
 				</md-toolbar>

				<md-dialog-content >
 					<div class="md-dialog-content" flex="100">
 						<div class="row">
							<div class="col-md-5">
								<input type="text" ng-model="componentProductSubGroup.filter.subGroup" name="filterSubGroup" class="input-sm col-md-12" placeholder="Sub Group" 
										style="height: 36px;" />
							</div>
							
							<div class="col-md-5">
								<input type="text" ng-model="componentProductSubGroup.filter.group" name="filterGroup" class="input-sm col-md-12" placeholder="Group" 
										style="height: 36px;" />
							</div>
							
							<div class="col-md-2">
								<button type="button" ng-click="componentProductSubGroup.fetchAll(0)" class="btn btn-primary col-md-12">Filter</button>
							</div>
						</div>
								
						<div id="no-more-tables">
							<table class="col-sm-12 table-bordered table-striped table-condensed cf" style="padding-right: 0px; padding-left: 0px; ">
								<thead class="cf">
									<tr>
										<th ng-repeat="d in componentProductSubGroup.datatable.header" width="{{d.width}}" align="{{d.align}}">{{d.title}}</th>
									</tr>
								</thead>
						
								<tbody>
									<tr ng-show="componentProductSubGroup.datatable.data.length == 0"> 
										<td colspan="4">
											<span>No Records Found</span>
										</td>
									</tr>
								
									<tr ng-repeat="d in componentProductSubGroup.datatable.data">
										<td data-title="ID"><span ng-bind="d.product_sub_group_id"></span></td>
										<td data-title="SubGroup"><span ng-bind="d.product_sub_group_name"></span></td>
										<td data-title="Group"><span ng-bind="d.product_sub_group_group.product_group_name"></span></td>
										<td data-title="Actions" align="center">
											<button type="button" ng-click="${selectEvent}; componentProductSubGroup.hideProductSubGroupDialog()" class="btn btn-primary custom-width">Select</button>
										</td>
									</tr>
								</tbody>
							</table>
							<h:paginator controller="componentProductSubGroup"/>
						</div>
					</div>
				</md-dialog-content>

				<md-dialog-actions layout="row">
					<md-button ng-click="componentProductSubGroup.hideProductSubGroupDialog()">
						Close
					</md-button>
				</md-dialog-actions>
			</form>
		</md-dialog>
	</div>
</div>
