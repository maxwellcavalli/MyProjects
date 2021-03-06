<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<h:page ngApp="myApp" ngController="ProductGroupController as ctrl" >
	<jsp:attribute name="header">
    </jsp:attribute>


	<jsp:attribute name="scripts">
		<script type="text/javascript" src="js/service/crud_service.js"></script>
		<script type="text/javascript" src="js/controller/product_group_controller.js"></script>
	</jsp:attribute>
	<jsp:body>
	
		<h:panel title="Product Group Registration Form">
			<h:messages controller="ctrl"/>
		
			<form ng-submit="ctrl.submit()" name="formulario" class="form-horizontal" form-focus="true">
			
				 <div class="row">
					<h:input model="ctrl.domain.product_group_name" label="Name" name="name" form="formulario" maxlength="50" required="true" styleClass="col-md-10"/>
				</div>
				
				<div class="row">
					<div class="form-actions floatRight">
	         			<input type="submit"  value="{{!ctrl.domain.product_group_id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="formulario.$invalid">
						<button type="button" ng-click="ctrl.reset()" class="btn btn-danger btn-sm" ng-show="ctrl.domain.product_group_id">Cancel</button>
						<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="formulario.$pristine">Reset Form</button>
					</div>
	 			</div>
			</form>
		</h:panel>
		
		
		<h:panel title="List of Product Groups">
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
								<th ng-repeat="d in ctrl.datatable.header" width="{{d.width}}" align="{{d.align}}" style="{{d.style}}">{{d.title}}</th>
							</tr>
						</thead>
						
						<tbody>
							<tr ng-show="ctrl.datatable.data.length == 0"> 
								<td colspan="3">
									<span>No Records Found</span>
								</td>
							</tr>
						
							<tr ng-repeat="d in ctrl.datatable.data">
								<td data-title="ID"><span ng-bind="d.product_group_id"></span></td>
								<td data-title="Name"><span ng-bind="d.product_group_name"></span></td>
								<td data-title="Actions" align="center">
									<button type="button" ng-click="ctrl.edit(d.product_group_id)" class="btn btn-success custom-width">Edit</button>  
	                             	<button type="button" ng-click="showConfirm($event, d.product_group_id)" class="btn btn-danger custom-width">Remove</button>
								</td>
							</tr>
						</tbody>
					</table>
					 
					<h:paginator controller="ctrl"/>
				</div>
			</jsp:body>
		</h:panel>
	</jsp:body>
</h:page>
