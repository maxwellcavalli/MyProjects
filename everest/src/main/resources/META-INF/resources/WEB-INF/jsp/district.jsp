<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<h:page ngApp="myApp" ngController="DistrictController as ctrl" >
	<jsp:attribute name="header">
    </jsp:attribute>


	<jsp:attribute name="scripts">
		
		<script type="text/javascript" src="js/service/district_service.js"></script>
		<script type="text/javascript" src="js/controller/district_controller.js"></script>
	</jsp:attribute>

	<jsp:body>
	
		<h:panel title="District Registration Form">
			<h:messages controller="ctrl"/>
		
			<form ng-submit="ctrl.submit()" name="formulario" class="form-horizontal" form-focus="true">
		         <!-- <input type="hidden" ng-model="ctrl.domain.code" /> -->
			
				 <div class="row">
					<h:input model="ctrl.domain.districts_name" label="Name" name="name" form="formulario" maxlength="50" required="true" styleClass="col-md-10"/>
				</div>
				
				
				<div class="row">
					<div class="form-actions floatRight">
	         			<input type="submit"  value="{{!ctrl.domain.districts_code ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="formulario.$invalid">
						<button type="button" ng-click="ctrl.reset()" class="btn btn-danger btn-sm" ng-show="ctrl.domain.districts_code">Cancel</button>
						<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="formulario.$pristine">Reset Form</button>
					</div>
	 			</div>
			</form>
		</h:panel>
		
		<h:panel title="List of Districts">
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
								<td colspan="4">
									<span>No Records Found</span>
								</td>
							</tr>
						
							<tr ng-repeat="d in ctrl.datatable.data">
								<td data-title="ID"><span ng-bind="d.districts_code"></span></td>
								<td data-title="Name"><span ng-bind="d.districts_name"></span></td>
								<td data-title="City"><span ng-bind="d.districts_city.cities_name"></span></td>
								<td data-title="State"><span ng-bind="d.districts_city.cities_state.states_name"></span></td>
								
								<td data-title="Actions" align="center">
									<button type="button" ng-click="ctrl.edit(d.districts_code)" class="btn btn-success custom-width">Edit</button>  
	                             	<button type="button" ng-click="showConfirm($event, d.districts_code)" class="btn btn-danger custom-width">Remove</button>
								</td>
							</tr>
						</tbody>
					</table>
					 
					<h:paginator controller="ctrl"/>
				</div>
			</jsp:body>
		</h:panel>
		
		
		
		<div style="visibility: hidden">
    		<div class="md-dialog-container" id="myDialog">
	      		<md-dialog layout-padding>
	        		<h2>Pre-Rendered Dialog</h2>
	        		<p>
	          			This is a pre-rendered dialog, which means that <code>$mdDialog</code> doesn't compile its
	          			template on each opening.
	          			<br/><br/>
	          			The Dialog Element is a static element in the DOM, which is just visually hidden.<br/>
	          			Once the dialog opens, we just fetch the element from the DOM into our dialog and upon close
	          			we restore the element back into its old DOM position.
	        		</p>
	      		</md-dialog>
    		</div>
  		</div>
		
		
	</jsp:body>
</h:page>