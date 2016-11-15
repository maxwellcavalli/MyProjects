<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<h:page ngApp="myApp" ngController="CustomerController as ctrl" >
	<jsp:attribute name="header">
    </jsp:attribute>

	<jsp:attribute name="scripts">
		<script type="text/javascript" src="js/service/crud_service.js"></script>
		<script type="text/javascript" src="js/controller/customer_controller.js"></script>
		
		<script type="text/javascript" src="js/service/component_district_service.js"></script>
		<script type="text/javascript" src="js/controller/component_district_controller.js"></script>
	</jsp:attribute>

	<jsp:body>
		<h:panel title="Customer Registration Form">
			<h:messages controller="ctrl"/>
		
			<form ng-submit="ctrl.submit()" name="formulario" class="form-horizontal" form-focus="true">
				<div class="row">
					<h:input model="ctrl.domain.customer_name" label="Name" name="name" form="formulario" maxlength="50" required="true" styleClass="col-md-10"/>
				</div>
				
				<div class="row">
					<h:component-district clear="ctrl.cleanDistrict()" name="district" label="District" dialog="districtDialog" 
						value="{{ctrl.domain.customer_district.districts_name}}  - {{ctrl.domain.customer_district.districts_city.cities_name}} - {{ctrl.domain.customer_district.districts_city.cities_state.states_name}}"/>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12" ng-show="ctrl.domain.customer_id">
					   <div class="col-md-12">
					   		
							<md-tabs md-dynamic-height md-border-bottom>
			      				<md-tab label="Contacts">
			        				<md-content class="md-padding">
			        					<div class="row">
			        						<h:select model="ctrl.customer_contacts.customer_contact_type" label="Type" name="customerType" form="formulario" 
												ngOptions="c for c in ctrl.contactType" styleClass="col-md-4"/>
												
											<h:input model="ctrl.customer_contacts.customer_contact_name" label="Description" name="description" form="formulario" 
												maxlength="50" styleClass="col-md-6"/>
			        						
		        							<div class="form-group col-md-2 padding-top-20" >
		        								 <div class="col-md-12">
		        								 	<button type="button" ng-click="ctrl.addContact()" class="btn btn-primary btn-sm glyphicon glyphicon-ok" 
		        								 		title="{{!ctrl.customer_contacts.inserted ? 'Add Contact' : 'Update Contact'}} "></button>
		        								 	<button type="button" ng-click="ctrl.resetContact()" class="btn btn-danger btn-sm glyphicon glyphicon-remove"
		        								 		 ng-show="ctrl.customer_contacts.inserted" title="Cancel"></button>
												</div>
											</div>	
			        					</div>
			        					
			        					<div class="row">
			        						<div id="no-more-tables">
			
												<table class="col-sm-12 table-bordered table-striped table-condensed cf" style="padding-right: 0px; padding-left: 0px; ">
													<thead class="cf">
														<tr>
															<th width="30%" align="left">Type</th>
															<th width="60%" align="left">Description</th>
															<th width="10%" align="center">Actions</th>
														</tr>
													</thead>
													
													<tbody>
														<tr ng-show="ctrl.domain.customer_contacts.length == 0"> 
															<td colspan="3">
																<span>No Records Found</span>
															</td>
														</tr>
													
														<tr ng-repeat="d in ctrl.domain.customer_contacts">
															<td data-title="Type"><span ng-bind="d.customer_contact_type"></span></td>
															<td data-title="Description"><span ng-bind="d.customer_contact_name"></span></td>
															
															<td data-title="Actions" align="center">
																<button type="button" ng-click="ctrl.editContact(d)" class="btn btn-success glyphicon glyphicon-pencil" title="Edit"></button>  
								                             	<button type="button" ng-click="ctrl.removeContact(d)" class="btn btn-danger glyphicon glyphicon-trash" title="Remove"></button>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
			        					</div>
			        					
			        				</md-content>
			        			</md-tab>
			        		</md-tabs>
			        	</div>
			        </div>
				</div>
				
				<div class="row">
					<div class="form-actions floatRight">
	         			<input type="submit"  value="{{!ctrl.domain.customer_id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="formulario.$invalid">
						<button type="button" ng-click="ctrl.reset()" class="btn btn-danger btn-sm" ng-show="ctrl.domain.customer_id">Cancel</button>
						<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="formulario.$pristine">Reset Form</button>
					</div>
	 			</div>
	 			
			</form>
		</h:panel>
		
		<h:panel title="List of Customers">
			<jsp:attribute name="controls">
				<input type="text" ng-model="ctrl.datatable.filter" name="filter" class="input-sm" placeholder="Filter" 
					style="height: 36px; width: calc(100% - 61px); padding-top: 1px" />
				<button type="button" ng-click="ctrl.fetchAll(0)" class="btn btn-primary ">Filter</button>
    		</jsp:attribute>
    		
    		<jsp:body>
				<div id="no-more-tables">
				
					<table class="col-sm-12 table-bordered table-striped table-condensed cf" style="padding-right: 0px; padding-left: 0px; ">
						<thead class="cf">
							<tr>
								<th ng-repeat="d in ctrl.datatable.header" width="{{d.width}}" align="{{d.align}}">{{d.title}}</th>
							</tr>
						</thead>
						
						<tbody>
							<tr ng-show="ctrl.datatable.data.length == 0"> 
								<td colspan="3">
									<span>No Records Found</span>
								</td>
							</tr>
						
							<tr ng-repeat="d in ctrl.datatable.data">
								<td data-title="ID"><span ng-bind="d.customer_id"></span></td>
								<td data-title="Name"><span ng-bind="d.customer_name"></span></td>
								
								<td data-title="Actions" align="center">
									<button type="button" ng-click="ctrl.edit(d.customer_id)" class="btn btn-success custom-width">Edit</button>  
	                             	<button type="button" ng-click="showConfirm($event, d.customer_id)" class="btn btn-danger custom-width">Remove</button>
								</td>
							</tr>
						</tbody>
					</table>
					 
					<h:paginator controller="ctrl"/>
				</div>
			</jsp:body>
		</h:panel>
		
		<h:component-district-dialog name="districtDialog" title="District" selectEvent="ctrl.selectDistrict(d)"/>
	
	</jsp:body>
</h:page>
