<%@ attribute name="label" required="true"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="value" required="true"%>
<%@ attribute name="clear" required="true"%>
<%@ attribute name="dialog" required="true"%>


<div class="form-group col-md-10">
	<label class="col-md-12 control-lable" for="${name}">${label}</label>
	<div class="col-md-12">
		<div>
			<div style="float: left; width: calc(100% - 121px); margin-right: 5px;">
				<input type="text" name=${name}" class="form-control input-sm"
					placeholder="Select a Product Sub Group" disabled="disabled" autocomplete="off" 
					value="${value} " />
			</div>

			<div style="float: left">
				<div ng-controller="ComponentProductSubGroupController as componentProductSubGroup">
					<button type="button" ng-click="componentProductSubGroup.showProductSubGroupDialog($event, '${dialog}')" class="btn btn-success btn-sm">Search</button>
					<button type="button" ng-click="${clear}" class="btn btn-warning btn-sm">Clear</button>
				</div>
			</div>

			<div style="clear: both"></div>
		</div>
	</div>
</div>
