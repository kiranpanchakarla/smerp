
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />

<script
	src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/js/scripts/datepicker/bootstrap-datepicker.min.js"/>
	type="text/javascript"></script>

<link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/datapickercss/bootstrap-datepicker.min.css"/>"
	rel="stylesheet" type="text/css" />

</head>



<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />
	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<!-- <div class="content-header row">
				<div class="col-md-6">
					<h4>RFQ</h4>
				</div>
			</div> -->
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">




							<form:form method="POST" action="/rfq/save"
								enctype="multipart/form-data" modelAttribute="rfq"
								data-toggle="validator" role="form" class="bv-form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">

													<h2 class="card-title" id="basic-layout-icons">RFQ/Create</h2>
												</div>

												<div class="card-body collapse in">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-6 form-group">
																	<label>Name</label>
																	<form:input type="text"
																		cssClass="form-control vendorname"
																		placeholder='Vendor Name' path="vendor.name"
																		required="true" />
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
																	<form:hidden path="vendor.id" id="vendordata" />
																<div class="col-sm-6 form-group">
																	<label>Email Id</label>
																	<form:input type="text" cssClass="form-control emailId"
																		readonly="true" placeholder='Email Id'
																		path="vendor.emailId" required="true" />
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
															</div>

															<form:hidden path="id" />

															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Contact Person </label>

																	<form:select path="vendorContactDetails.id"
																		id="vendorContactDetails" cssClass="form-control"
																		required="true"
																		oninvalid="this.setCustomValidity('Please Select State ')"
																		oninput="setCustomValidity('')">
																	</form:select>
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>

																<div class="col-sm-4 form-group">
																	<label>Pay To</label>
																	<form:select path="vendorPayTypeAddress.id"
																		id="vendorPayToAddress" cssClass="form-control"
																		required="true">
																		<%-- <form:option value="Select">Select</form:option> --%>
																	</form:select>
																</div>

																<div class="col-sm-4 form-group">
																	<label>Ship From</label>
																	<form:select path="vendorShippingAddress.id"
																		id="vendorAddress" cssClass="form-control"
																		required="true">
																		<%-- <form:option value="Select">Select</form:option> --%>
																	</form:select>
																</div>

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>Document Number</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Document Number' path="docNumber"
																					readonly="true" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>

																			<div class="col-sm-6 form-group">
																				<label>Reference Document Number</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Reference Document Number'
																					path="referenceDocNumber" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>
																		</div>




																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Posting Date</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Posting Date' path="postingDate"
																					autocomplete="off" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Document Date</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Document Date' path="documentDate"
																					autocomplete="off" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>


																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>
																				<form:input type="text" cssClass="form-control"
																					id="require_date" placeholder='Required Date'
																					autocomplete="off" path="requiredDate" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>
																		</div>
																		
																		
																	</div>
																</div>
															</div>
															
															<div class="row">
																			
																			<div class="col-sm-6 form-group">
                                                                            <div class="input-group">
                                                                                <div class="inventory-list">
                                                                                <form:radiobutton cssClass="form-control"
																					 value="Item" path="category"  name="category"  id="items_radio" 
																				/>
                                                                                <span class="radio-list">Item</span>
                                                                                </div>
                                                                                <div class="inventory-list">
                                                                                <form:radiobutton cssClass="form-control"
																					 value="Services" path="category"  name="category" id="service_radio" 
																				/>	
                                                                                <span class="radio-list">Services</span></div>
                                                                                <div class="help-block with-errors"></div>
                                                                            </div>
                                                                        </div>
															</div>
																		
															<input type="hidden" id="addressCount" value="1">
															

															<ul class="nav nav-tabs" id="containerContainingTabs" role="tablist">
																<li class="nav-item"><a class="nav-link active"
																	id="home-tab" data-toggle="tab" href="#home" role="tab"
																	aria-controls="home" aria-selected="true">Item
																		Details</a></li>
																<li class="nav-item"><a class="nav-link"
																	id="profile-tab" data-toggle="tab" href="#profile"
																	role="tab" aria-controls="profile"
																	aria-selected="false">Inventory</a></li>
															</ul>

															<div class="tab-content">
																<div class="tab-pane active" id="home" role="tabpanel"
																	aria-labelledby="home-tab">
																	<div class="row">
																		<div class="col-xs-12">
																			<div class="row-in">
																				
																				<div class="table-responsive">
																					<div class="full-col-table">
																						<table class="table table-bordered table-striped"
																							id="itemTbl">
																							<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th style="display: none;">Product Id</th>
																									<th>Product Name</th>
																									<th>UOM</th>
																									<th>Quantity</th>
																									<th>Product Group</th>
																									<th>Ware house</th>
																									<th>HSN</th>
																									<th>Action</th>
																								</tr>
																							</thead>
																							<tbody >
																										<tr class="multTot multTot0">
																										
																											<td ><form:input type="text"
																													path="lineItems[0].prodouctNumber"
																													class="form-control prodouctNumber "></form:input></td>
																										
																											<td style="display: none;"><form:input
																													type="hidden" path="lineItems[0].productId"
																													class="form-control productId"></form:input></td>
																											
																											<td ><form:input type="text"
																													path="lineItems[0].uom"
																													class="form-control uom" readonly="true"></form:input></td>

																											<td><form:input type="text"
																													path="lineItems[0].requiredQuantity"
																													class="form-control"></form:input></td>
																											
																											<td ><form:input type="text"
																													path="lineItems[0].productGroup"
																													class="form-control productGroup"
																													readonly="true"></form:input></td>			
																											
																											<td><form:select class="form-control"
																													style="width:160px !important;"
																													path="lineItems[0].warehouse">
																													<form:option value="" label="Select" />
																													<form:options items="${planMap}" />
																												</form:select></td>
																												
																											<td  ><form:input type="text"
																													path="lineItems[0].hsn"
																													class="form-control hsnVal" readonly="true"></form:input>
																											</td>
																											
																									       <td class="text-center"><a  onclick="removeData(0)" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a> </td>
																										</tr>
                                                                                            </tbody>
                                                                                      </table>   
                                                                                         
                                                                                      <table class="table table-bordered table-striped"
																							id="serviceTbl">   
																							<thead>
																								<tr>
																									<th style="display: none;">Product Id</th>
																									<th>SAC</th>
																									<th>Description</th>
																									<th>Request Quantity</th>
																									<th>Ware house</th>
																									<th>Action</th>
																								</tr>
																							</thead> 
                                                                                            <tbody >
																										<tr class="multTot multTot0">
																										
																											<td style="display: none;"><form:input
																													type="hidden" path="lineItems[0].productId"
																													class="form-control productId"></form:input></td>
																											
																											<td ><form:input type="text"
																													path="lineItems[0].sacCode"
																													class="form-control sacCode"
																													></form:input></td>		
																											
																											<td ><form:input type="text"
																													path="lineItems[0].description"
																													class="form-control description"
																													readonly="true"></form:input></td>
																											

																											<td><form:input type="text"
																													path="lineItems[0].requiredQuantity"
																													class="form-control"></form:input></td>
																											
																											<td><form:select class="form-control"
																													style="width:160px !important;"
																													path="lineItems[0].warehouse">
																													<form:option value="" label="Select" />
																													<form:options items="${planMap}" />
																												</form:select></td>
																											
																									       <td class="text-center"><a  onclick="removeData1(0)" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a> </td>
																										</tr>
                                                                                            </tbody>
                                                                                        </table>    
                                                                                            
                                                                                            
                                                                                            
                                                                                            
                                                                                            
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty lineItems}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th style="display: none;">Product Id</th>
																									<c:if test="${rfq.category=='Item'}">
																									<th>Product Name</th>
																									<th>UOM</th>
																									<th>Quantity</th>
																									<th>Product Group</th>
																									<th>Ware house</th>
																									<th>HSN</th>
																									</c:if>
																									
																									<c:if test="${rfq.category!='Item'}">
																									<th>SAC</th>
																									<th>Description</th>
																									<th>Request Quantity</th>
																									<th>Ware house</th>
																									</c:if>
																									<th>Action</th>
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${lineItems}"
																												var="listLineItems">
																												
																												<tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="lineItems[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input></td>
																															
																													<c:if test="${rfq.category=='Item'}">
																													<td ><form:input type="text"
																															path="lineItems[${count}].prodouctNumber"
																															value="${listLineItems.prodouctNumber}"
																															class="form-control prodouctNumber"></form:input></td>
																													
																													<td><form:input type="text"
																															path="lineItems[${count}].uom"
																															value="${listLineItems.uom}"
																															class="form-control uom" readonly="true"></form:input></td>
																													
																													<td><form:input type="text"
																															path="lineItems[${count}].requiredQuantity"
																															value="${listLineItems.requiredQuantity}"
																															class="form-control"></form:input></td>
																															
																														<td><form:input type="text"
																															path="lineItems[${count}].productGroup"
																															value="${listLineItems.productGroup}"
																															class="form-control productGroup"
																															readonly="true"></form:input></td>
																														
																														<td><form:select class="form-control"
																															style="width:160px !important;"
																															path="lineItems[${count}].warehouse">
																															<form:option value="" label="Select" />
																															<form:options items="${planMap}" />
																														</form:select></td>
																															
																														<td><form:input type="text"
																															path="lineItems[${count}].hsn"
																															value="${listLineItems.hsn}"
																															class="form-control hsnVal"
																															readonly="true"></form:input></td>
																															
																													</c:if>
																													
																													<c:if test="${rfq.category!='Item'}">
																													<td><form:input type="text"
																															path="lineItems[${count}].hsn"
																															value="${listLineItems.sacCode}"
																															class="form-control sacCode"
																															readonly="true"></form:input></td>
																													
																													<td><form:input type="text"
																															path="lineItems[${count}].hsn"
																															value="${listLineItems.description}"
																															class="form-control description"
																															readonly="true"></form:input></td>
																															
																													<td><form:input type="text"
																															path="lineItems[${count}].requiredQuantity"
																															value="${listLineItems.requiredQuantity}"
																															class="form-control"></form:input></td>
																													
																													<td><form:select class="form-control"
																															style="width:160px !important;"
																															path="lineItems[${count}].warehouse">
																															<form:option value="" label="Select" />
																															<form:options items="${planMap}" />
																														</form:select></td>
																															
																													</c:if>
																													
																															
																													
																						                           <td class="text-center"><a  onclick="removeData2(${count})" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a> </td>
																												
																												</tr>

																												<c:set var="count" value="${count + 1}"
																													scope="page" />
																											</c:forEach>
																							
																							
																									</tbody>
																									</table>
																									<input type="hidden" id="edit_addressCount" value="${count-1}">
																										</c:if>

																						
																					</div>
																				</div>
																				<div class="add-row-block">
																					<input type="button" class="btn btn-info"
																						id="addItem" value="Add Row">
																				</div>
																			</div>
																		</div>
																	</div>
																</div>

																<div class="tab-pane" id="profile" role="tabpanel"
																	aria-labelledby="profile-tab">

																	<table class="table fixed-width-table">
																		<thead>
																			<tr>
																				<th style="vertical-align: top; !important">Shipping
																					From</th>
																				<td>
																					<div id="shippingAddressTable"></div>
																				</td>
																			</tr>
																			<tr>
																				<th style="vertical-align: top; !important">Pay
																					To</th>
																				<td>
																					<div id="payToAddressTable"></div>
																				</td>
																			</tr>
																		</thead>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="card-block"><div class="row">
										<div class="col-sm-6 form-group">
											<a href="#" onclick="goBack()"
												class="btn btn-primary"> Back </a>
												
										</div>
										<div class="col-sm-6 form-group">
                                              <form:input type="submit" path="status" value="Save"
												class="btn btn-primary" />
											<form:input type="submit" path="status" value="Draft"
												class="btn btn-primary" />
												
											<a href="<c:url value="/rfq/list"/>">
												<button type="button" class="btn btn-warning mr-1">
													<i class="icon-cross2"></i> Cancel
												</button>
											</a> 
										</div>
									</div>
                                    </div>
								</section>
							</form:form>
						</div>
					</div>
					<!--/ project charts -->
					<br>
				</div>
			</div>
		</div>

	</div>


	<c:import url="/WEB-INF/jsp/footer.jsp" />


	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<!-- alertfy-->
<!-- form validations-->


<!-----------    END   ---------------------------------->


<script type="text/javascript">

var i=1;var j=1;
var inc=0;
$(document).ready(function(){
	
	 if ($('#service_radio').is(":checked") == true) {
		 $("#serviceTbl").show();
		 $("#itemTbl").hide();
	 }else {
		 document.getElementById("items_radio").checked = true;
		 $("#serviceTbl").hide();
	      $("#itemTbl").show();
	 }
	
		if ($('#edit_addressCount').val() != undefined ) {
			 $("#serviceTbl").hide();
			 $("#itemTbl").hide();
		}
	
	var id=$('#id').val();
	
	//alert("id--->"+id);

		if(id!=''){
			var vendorName=$('.vendorname').val();
			//alert("vendorName"+vendorName);
			autocompletevendorDetails(vendorName);
			var payTypeAddressId='${vendorPayTypeAddressId}';
			var shippingAddressId='${vendorShippingAddressId}';
			
			
			//alert("payTypeAddressId--->"+payTypeAddressId);
			//alert("shippingAddressId--->"+shippingAddressId);
			vendorShippingAddress(shippingAddressId);
			vendorPayTypeAddress(payTypeAddressId);
		}
	
	
	  var date = new Date();
	  var today = new Date(date.getDate(),date.getFullYear(), date.getMonth());
	  var end = new Date(date.getFullYear(), date.getMonth(), date.getDate());
		
		
		  $('#postingDate').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				startDate: today,
				endDate: end,
				autoclose: true
				  });
			
		  $('#documentDate').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				startDate: today,
				endDate: end,
				autoclose: true
		  });
		  
		  
		  $('#require_date').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				startDate: today,
				endDate: end,
				autoclose: true
		  });

	     $('#postingDate,#documentDate','#require_date').datepicker('setDate', today);
		
		

	
	var vendorNames=[];
	var vendorNamesList=${vendorNamesList};
	var availableTagsvendornames=[];
	 $.each(vendorNamesList, function (index, value) {
		 availableTagsvendornames.push(value.toString());
	   });
	
	
		$(document).on("keypress", ".vendorname", function() {
			$(this).autocomplete({
		        source: availableTagsvendornames,
		        select: function(event, ui) {
		        	var vendorname = ui.item.value;
		        //	alert(vendorname);
		            autocompletevendorDetails(vendorname);
		       		 },
		        }); 
			});
			 
		
		//get the vendor information based on vendor name
    	function autocompletevendorDetails(vendorname){
			//alert(vendorname);
			//$("#vendorContactDetails").html("");
		//	 $("#vendorAddress").html("");
			
    	 $.ajax({
			   type: "GET",
    			data: {vendorname :vendorname}, 
    			async : false,
                url: "<c:url value="/vendor/getVendorInfo"/>", 
                success: function (response) {
                	console.log("vendor details"+response);
                	var obj =JSON.parse(response);
                	
                	$('#vendordata').val(obj.id);
                	
                	$('.emailId').val(obj.emailId);
                	var vendorcontactdetails=obj.vendorContactDetails;
                	var vendoraddress=obj.vendorAddress;
                	$.each(vendorcontactdetails, function( index, value ) {
                		//  alert( index + ": " + JSON.stringify(value) );
                		 //   alert(value.contactName)
                		    $("#vendorContactDetails").append($("<option></option>").attr("value",value.id).text(value.contactName)); 
                		});
                	
                	  $.each(vendoraddress, function( index, value ) {
              		 // alert( index + ": " + JSON.stringify(value) );
              			 if(value.payTo!=null && "shipFrom".localeCompare(value.shipFrom)){
              		  		 $("#vendorAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
              			 }else{
              				 $("#vendorPayToAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
              			 }
              		  });
                	  
                	
                  	$('#payToAddressTable').html("");
                  	vendorPayTypeAddress($('#vendorPayToAddress').val());
                  	
                	  
              		$('#shippingAddressTable').html("");
              		vendorShippingAddress($('#vendorAddress').val());
                	
               	 },
                error: function(e){
                // alert('Error: ' + e);
                 }
                });
		}
		
	 

	 var productList =[];
	 var name;
	 var prodouctNumbers= ${productList};
	 var availableTags = [];
		$.each(prodouctNumbers, function (index, value) {
		   availableTags.push(value.toString());
	   });
		// alert("length"+availableTags.length);
		 
		//$(".prodouctNumber").autocomplete({
	$(document).on("keypress", ".prodouctNumber", function() {
		var itemParentRow = $(this).parents(".multTot");
		//alert("itemParentRow"+itemParentRow);
		
		$(this).autocomplete({
	        source: availableTags,
	        select: function(event, ui) {
	        	name = ui.item.value;
	        	//alert(name);
	             autocompleteandchange(name,itemParentRow);
	       		 },
	        }); 
		});
		

	//get the product information based on product  name
    	function autocompleteandchange(name,itemParentRow){
			//alert(name);
			
    	 $.ajax({
			   type: "GET",
    			data: {name :name}, 
    			async : false,
                url: "<c:url value="/product/getProductInfo"/>", 
                success: function (response) {
                	console.log(response);
                	
                	
                	
                	var obj =JSON.parse(response);
                	
                	//var myJSON = JSON.stringify(obj);
                	
                	
                	
                	var hsndata=obj.hsnCode;
                	//alert("hsnCode"+hsndata.hsnCode);
                //	$('.hsnVal').val(hsndata.hsnCode);
                
                	$(itemParentRow).find(".prodouctNumber").val(obj.description);
                	$(itemParentRow).find(".productId").val(obj.id);
            	
                
                	 $(itemParentRow).find(".hsnVal").val(hsndata.hsnCode);
                	
                	var uom=obj.purchasingUom.uomName;
                	//alert("uom"+uom);
                	//$('.uom').val(uom);
                	
                	 $(itemParentRow).find(".uom").val(uom);
                	
             	 //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
             	 	var productgroup=obj.productCategory.categoryType;
             	 	//$('.productGroup').val(productgroup);
             	 	
             	 	 $(itemParentRow).find(".productGroup").val(productgroup);
                	
                	
               	 },
                error: function(e){
               //  alert('Error: ' + e);
                 }
                });
		}
	
    	$('#vendorAddress').on('change', function() {
    		var shippingId=$('#vendorAddress').val();
    		$('#shippingAddressTable').html("");
    		vendorShippingAddress(shippingId);
    	});
    	
    	
    	
    	function vendorShippingAddress(shippingId){
			$.ajax({
	 			   type: "GET",
	     			data: {shippingId :shippingId}, 
	     			async : false,
	                 url: "<c:url value="/vendor/getShippingAddressInfo"/>", 
	                 success: function (response) {
	                	console.log(response);
	                		var obj =JSON.parse(response);
	                 	
	                		//shippingAddress
	                		//shippingAddressTableTr
	                		var addr=obj.addressName;
	                		var street=obj.street;
	                		var city=obj.city;
	                		var zipCode=obj.zipCode;
	                		
	                		//$('#shippingAddressTable').html("");
	                		
	                		$('#shippingAddressTable').append(addr+', ');
	                		$('#shippingAddressTable').append(street+'<br/>');
	                		$('#shippingAddressTable').append(city+' - ');
	                		$('#shippingAddressTable').append(zipCode+'<br/>');
	                		
	                		if(obj.country!="" && obj.country != null && (typeof(obj.country)!= "undefined")){
	                            if(obj.country.name != "" && obj.country.name != "undefined" && (typeof(obj.country.name) != "undefined")){
	                            	var country=obj.country.name;
	                                $('#shippingAddressTable').append(country);
	                            }
	                		}
	                 	
	                	 },
	                 error: function(e){
	               //   alert('Error: ' + e);
	                  }
	                 });
		}
    	
    					// pay to address ajax call
	
                                $('#vendorPayToAddress').on('change', function() {
                            		var shippingId=$('#vendorPayToAddress').val();
                            		$('#payToAddressTable').html("");
                            		vendorPayTypeAddress(shippingId);
                            	});
    					
    					
                                function vendorPayTypeAddress(shippingId){
                        			$.ajax({
                          			   type: "GET",
                              			data: {shippingId :shippingId}, 
                              			async : false,
                                          url: "<c:url value="/vendor/getShippingAddressInfo"/>", 
                                          success: function (response) {
                                         	console.log(response);
                                         		var obj =JSON.parse(response);
                                         		var addr=obj.addressName;
                                         		var street=obj.street;
                                         		var city=obj.city;
                                         		var zipCode=obj.zipCode;
                                         		
                                         		
                                         		$('#payToAddressTable').append(addr+', ');
                                         		$('#payToAddressTable').append(street+'<br/>');
                                         		$('#payToAddressTable').append(city+' - ');
                                         		$('#payToAddressTable').append(zipCode+'<br/>');
                                         		
                                         		if(obj.country!="" && obj.country != null && (typeof(obj.country)!= "undefined")){
                                                     if(obj.country.name != "" && obj.country.name != "undefined" && (typeof(obj.country.name) != "undefined")){
                                                     	var country=obj.country.name;
                                                         $('#payToAddressTable').append(country);
                                                     }
                                         		}
                                          	
                                         	 },
                                          error: function(e){
                                        //   alert('Error: ' + e);
                                           }
                                          });
                        		}
                                
                                
                                var sacData= ${sacList};
                                var availableSacs = [];
                                   $.each(sacData, function (index, value) {
                                       availableSacs.push(value.toString());
                                  });
                                        
                                        // alert("availableSacs" +availableSacs);
                                         $(document).on("keypress", ".sacCode", function() {
                                             var itemParentRow = $(this).parents(".multTot");
                                             
                                             $(this).autocomplete({
                                                 source: availableSacs,
                                                 select: function(event, ui) {
                                                     sacCode = ui.item.value;
                                                     //alert(name);
                                                      autocompleteandchangeSacCode(sacCode,itemParentRow);
                                                         },
                                                 });
                                             });
                                             

                                         //get the product information based on product  name
                                             function autocompleteandchangeSacCode(sacCode,itemParentRow){
                                                 //alert(name);
                                                 
                                              $.ajax({
                                                    type: "GET",
                                                     data: {sacCode :sacCode},
                                                     async : false,
                                                     url: "<c:url value="/saccode/getSacInfo"/>",
                                                     success: function (response) {
                                                         console.log(response);
                                                         var obj =JSON.parse(response);
                                                         var description=obj.sacCode;
                                                            $(itemParentRow).find(".description").val(obj.description);
                                                         },
                                                     error: function(e){
                                                    //  alert('Error: ' + e);
                                                      }
                                                     });
                                             }
                                
                                
    					
                        	
	
	//alert("ready");
	
	$('#addItem').click(function() {
		
		
	
						var addressCount = $('#addressCount').val();
						var edit_addressCount = $('#edit_addressCount').val();
						if (edit_addressCount != undefined ) {
							    inc = parseInt(edit_addressCount)+1;
								$('#edit_addressCount').val(inc);
						}else {
							if(addressCount==1){
								inc =parseInt(inc)+1;
							}else {
								inc = parseInt(addressCount)  + 1;
							}
						}
						
						
						//alert(inc)
						$('#addressCount').val(inc);
						 i = inc;
						 j = inc;
	
	  if ($('#items_radio').is(":checked") == true) {
		  
	        var item_table_data = '<tr class="multTot multTot'+i+'">'
			
			+'<td>'
			+'<input type="text" name="lineItems['+i+'].prodouctNumber" class="form-control prodouctNumber prodouctNumber'+i+'" id="prodouctNumber'+i+'"   />'
			+'</td>'
			
			+'<td style="display:none;">'
			+'<input type="hidden" name="lineItems['+i+'].productId" class="form-control productId productId'+i+'" id="productId'+i+'"   />'
			+'</td>'
			
			
			+'<td>'
			+'<input type="text" name="lineItems['+i+'].uom" class="form-control uom uom'+i+'" id="uom'+i+'"   />'
			+'</td>'
			
			
			+'<td>'
			+'<input type="text" name="lineItems['+i+'].requiredQuantity" class="form-control requiredQuantity'+i+'" id="requiredQuantity'+i+'"   />'
			+'</td>'
			
			+'<td>'
			+'<input type="text" name="lineItems['+i+'].productGroup" class="form-control  productGroup productGroup'+i+'" id="productGroup'+i+'"   />'
			+'</td>'
			
			
			+ '<td>'
			+ '<select  name="lineItems['+i+'].warehouse" style="width:160px !important;" class="form-control warehouse'+i+' warehouse"  id="warehouse'+i+'" >'
			+'<option value="">select</option>'+
			<c:forEach items="${planMap}" var="planMap">
			'<option value="${planMap.key}">${planMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</td>'
			
			+'<td>'
			+'<input type="text" name="lineItems['+i+'].hsn" class="form-control hsnVal hsn'+i+'" id="hsn'+i+'"   />'
			+'</td>'
			
			+ ' <td class="text-center"><a  onclick="removeData('+i+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
			+ '</td>'
			 
			
	+'</tr>';
			if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
				$("#edit_item_serviceTbl").append(item_table_data);
			}else{
				 $("#itemTbl").append(item_table_data);
			}
		 
					
	  }else {
		 
		  var service_table_data='<tr class="multTot multTot'+j+'">'
			
			+'<td style="display:none;">'
			+'<input type="hidden" name="lineItems['+j+'].productId" class="form-control productId productId'+j+'" id="productId'+j+'"   />'
			+'</td>'
			
			+'<td>'
			+'<input type="text" name="lineItems['+j+'].sacCode" class="form-control sacCode  sacCode'+j+'" id="hsn'+j+'"   />'
			+'</td>'
			
			+'<td>'
			+'<input type="text" name="lineItems['+j+'].description" class="form-control description '+j+'" id="uom'+j+'"   />'
			+'</td>'
			
			
			+'<td>'
			+'<input type="text" name="lineItems['+j+'].requiredQuantity" class="form-control requiredQuantity'+j+'" id="requiredQuantity'+j+'"   />'
			+'</td>'
			
			+ '<td>'
			+ '<select  name="lineItems['+j+'].warehouse" style="width:160px !important;" class="form-control warehouse'+j+' warehouse"  id="warehouse'+j+'" >'
			+'<option value="">select</option>'+
			<c:forEach items="${planMap}" var="planMap">
			'<option value="${planMap.key}">${planMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</td>'
			
			+ ' <td class="text-center"><a  onclick="removeData1('+j+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
			+ '</td>'
			 
			
	+'</tr>';
		  
		  if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
				 $("#edit_addressCount").append(service_table_data);
			}else{
				 $("#serviceTbl").append(service_table_data);
			}
		  
	  }
		
		j++;
		i++;
	});
	
  });


function removeData(index){
	//alert("ff"+index);
	$('table#itemTbl tr.multTot'+index).remove();
}


function removeData1(index){
	//alert("ff"+index);
	$('table#serviceTbl tr.multTot'+index).remove();
}


function removeData2(index){
	//alert("ff"+index);
	$('table#edit_item_serviceTbl tr.multTot'+index).remove();
}


</script>


<script type="text/javascript">


$("#items_radio").click(function() {
	$("#serviceTbl").hide();
	 $("#itemTbl").show();
	 $("#edit_item_serviceTbl").hide();
	 $(this).parents().find("#serviceTbl").find(".form-control").attr("required",false).val(''); 
	 
       var edit_inc = $('#edit_addressCount').val();
       if(edit_inc!= undefined){
    	   inc = edit_inc;
       }
      for(var k=1;k<=inc;k++) {
    	  removeData1(k);
	  }
	  inc=0;
	  
	  $('#addressCount').val(1);
	  $('#edit_addressCount').val(0);
});

$("#service_radio").click(function() {
	$("#serviceTbl").show();
	 $("#itemTbl").hide();
	 $("#edit_item_serviceTbl").hide();
	 
	 $(this).parents().find("#itemTbl").find(".form-control").attr("required",false).val(''); 
      var edit_inc = $('#edit_addressCount').val();
      if(edit_inc!= undefined){
   	   inc = edit_inc;
      }
      for(var k=1;k<=inc;k++) {
    	  removeData(k);
	  }
      
	  inc=0;
	  $('#addressCount').val(1);
	  $('#edit_addressCount').val(0);
	 
});

$('#containerContainingTabs a').on('click', function(e) {
	e.preventDefault();
	$(this).tab('show');
	var theThis = $(this);
	$('#containerContainingTabs a').removeClass('active');
	theThis.addClass('active');
	});
	</script>


</html>

