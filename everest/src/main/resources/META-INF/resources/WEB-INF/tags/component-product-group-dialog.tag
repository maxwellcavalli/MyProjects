<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="selectEvent" required="true" %>

<div style="visibility: hidden">
	<div ng-controller="ComponentProductGroupController as componentProductGroup">
	<div class="md-dialog-container" id="${name}">
		<md-dialog aria-label="City" flex="100%">
			<form ng-cloak>
				<md-toolbar>
					<div class="md-toolbar-tools">
						<h2>${title}</h2>
      					<span flex></span>
      					<md-button class="md-icon-button" ng-click="componentProductGroup.hideProductGroupDialog()">
        						<md-icon md-svg-src="image/icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
      					</md-button>
   					</div>
 				</md-toolbar>

				<md-dialog-content >
 					<div class="md-dialog-content" flex="100">
						<div>
							<input type="text" ng-model="componentProductGroup.datatable.filter" name="filter" class="input-sm" placeholder="Filter" 
								style="height: 36px; width: calc(100% - 61px); padding-top: 1px" />
							<button type="button" ng-click="componentProductGroup.fetchAll(0)" class="btn btn-primary ">Filter</button>
						</div>
								
						<div id="no-more-tables">
							<table class="col-sm-12 table-bordered table-striped table-condensed cf" style="padding-right: 0px; padding-left: 0px; ">
								<thead class="cf">
									<tr>
										<th ng-repeat="d in componentProductGroup.datatable.header" width="{{d.width}}" align="{{d.align}}">{{d.title}}</th>
									</tr>
								</thead>
						
								<tbody>
									<tr ng-show="componentProductGroup.datatable.data.length == 0"> 
										<td colspan="3">
											<span>No Records Found</span>
										</td>
									</tr>
								
									<tr ng-repeat="d in componentProductGroup.datatable.data">
										<td data-title="ID"><span ng-bind="d.product_group_id"></span></td>
										<td data-title="Name"><span ng-bind="d.product_group_name"></span></td>
										<td data-title="Actions" align="center">
											<button type="button" ng-click="${selectEvent}; componentProductGroup.hideProductGroupDialog()" class="btn btn-primary custom-width">Select</button>
										</td>
									</tr>
								</tbody>
							</table>
							<h:paginator controller="componentProductGroup"/>
						</div>
					</div>
				</md-dialog-content>

				<md-dialog-actions layout="row">
					<md-button ng-click="componentProductGroup.hideProductGroupDialog()">
						Close
					</md-button>
				</md-dialog-actions>
			</form>
		</md-dialog>
	</div>
</div>
