
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


<!-----------    END   ---------------------------------->


<script type="text/javascript">
$(document).ready(function(){
	
	var id=$('#id').val();
	
	//alert("id--->"+id);

		if(id!=''){
			var vendorName=$('.vendorname').val();
			alert("vendorName"+vendorName);
			autocompletevendorDetails(vendorName);
			var payTypeAddressId='${vendorPayTypeAddressId}';
			var shippingAddressId='${vendorShippingAddressId}';
			
			
			alert("payTypeAddressId--->"+payTypeAddressId);
			alert("shippingAddressId--->"+shippingAddressId);
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
			alert(vendorname);
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
              		  alert( index + ": " + JSON.stringify(value) );
              			 if(value.payTo!=null && "shipFrom".localeCompare(value.shipFrom)){
              		  		 $("#vendorAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
              			 }else{
              				 $("#vendorPayToAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
              			 }
              		});
                	
               	 },
                error: function(e){
                 alert('Error: ' + e);
                 }
                });
		}
		
	 

	 var productList =[];
	 var name;
	 var productNames= ${productList};
	 var availableTags = [];
		$.each(productNames, function (index, value) {
		   availableTags.push(value.toString());
	   });
		// alert("length"+availableTags.length);
		 
		//$(".productName").autocomplete({
	$(document).on("keypress", ".productName", function() {
		var itemParentRow = $(this).parents(".multTot");
		//alert("itemParentRow"+itemParentRow);
		
		$(this).autocomplete({
	        source: availableTags,
	        select: function(event, ui) {
	        	name = ui.item.value;
	        	alert(name);
	             autocompleteandchange(name,itemParentRow);
	       		 },
	        }); 
		});
		

	//get the product information based on product  name
    	function autocompleteandchange(name,itemParentRow){
			alert(name);
			
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
                	alert("hsnCode"+hsndata.hsnCode);
                //	$('.hsnVal').val(hsndata.hsnCode);
                
                	$(itemParentRow).find(".productName").val(obj.description);
                	$(itemParentRow).find(".productId").val(obj.id);
            	
                
                	 $(itemParentRow).find(".hsnVal").val(hsndata.hsnCode);
                	
                	var uom=obj.purchasingUom.uomName;
                	alert("uom"+uom);
                	//$('.uom').val(uom);
                	
                	 $(itemParentRow).find(".uom").val(uom);
                	
             	 //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
             	 	var productgroup=obj.productCategory.categoryType;
             	 	//$('.productGroup').val(productgroup);
             	 	
             	 	 $(itemParentRow).find(".productGroup").val(productgroup);
                	
                	
               	 },
                error: function(e){
                 alert('Error: ' + e);
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
	                  alert('Error: ' + e);
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
                                           alert('Error: ' + e);
                                           }
                                          });
                        		}
    					
                        	
	
	//alert("ready");
	$('#addItem').click(function() {
		var count = $('#itemTbl tr').length;
		var i = count-1;
	//	alert("i"+i);
		
		$("#itemTbl").append(
				'<tr class="multTot multTot'+i+'">'
				
						
						+ '<td>'
						+ '<select  name="lineItems['+i+'].category" style="width:160px !important;" class="form-control category'+i+' category"  id="category'+i+'" >'
						+'<option value="">select</option>'+
						<c:forEach items="${categoryMap}" var="category">
						'<option value="${category.key}">${category.value}</option>'+
						</c:forEach>
						+ '</select>'
						+ '</td>'
					
						
						+'<td>'
						+'<input type="text" name="lineItems['+i+'].productName" class="form-control productName productName'+i+'" id="productName'+i+'"   />'
						+'</td>'
						
						+'<td style="display:none;">'
						+'<input type="hidden" name="lineItems['+i+'].productId" class="form-control productId productId'+i+'" id="productId'+i+'"   />'
						+'</td>'
						
						
						
						+'<td>'
						+'<input type="text" name="lineItems['+i+'].hsnOrSac" class="form-control hsnVal hsnOrSac'+i+'" id="hsnOrSac'+i+'"   />'
						+'</td>'
						
						
						+'<td>'
						+'<input type="text" name="lineItems['+i+'].uom" class="form-control uom uom'+i+'" id="uom'+i+'"   />'
						+'</td>'
						
					/* 	+ '<td>'
						+ '<select  name="lineItem['+i+'].uom" style="width:160px !important;" class="form-control uom uom'+i+' uom"  id="uom'+i+'" >'
						+'<option value="">select</option>'+
						<c:forEach items="${uomList}" var="uomList">
						'<option value="${uomList.key}">${uomList.value}</option>'+
						</c:forEach>
						+ '</select>'
						+ '</td>' */
						
						+'<td>'
						+'<input type="text" name="lineItems['+i+'].quantity" class="form-control quantity'+i+'" id="quantity'+i+'"   />'
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
						
						
						
						
						+ ' <td class="text-center"><a  onclick="removeData('+i+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
						+ '</td>'
						 
						
				+'</tr>');
		i++;
	});
	
  });


function removeData(index){
	//alert("ff"+index);
	$('table#itemTbl tr.multTot'+index).remove();
}


</script>


<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />
	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>RFQ</h4>
				</div>
			</div>
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
											<div class="card">
												<div class="card-header">

													<h4 class="card-title" id="basic-layout-icons">RFQ/Create</h4>
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
																<div class="card-block">
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

															<ul class="nav nav-tabs" id="myTab" role="tablist">
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
																			<div class="row">
																				<div class="text-right"
																					style="margin: 0px 20px 10px 0px;">
																					<input type="button" class="btn btn-info"
																						id="addItem" value="Add Row">
																				</div>
																				<div class="table-responsive">
																					<div class="col-sm-12">
																						<table class="table table-bordered table-striped"
																							id="itemTbl">
																							<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th>Category</th>
																									<th style="display: none;">Product Id</th>
																									<th>Product Name</th>
																									<th>HSN or SAC</th>
																									<th>UOM</th>
																									<th>Quantity</th>
																									<th>Product Group</th>
																									<th>Ware house</th>
																									<th>Action</th>
																								</tr>
																							</thead>
																							<tbody>
																								<c:choose>
																									<c:when test="${empty rfq.id}">
																										<tr class="multTot multTot0">
																											<td><form:select class="form-control"
																													style="width:160px !important;"
																													path="lineItems[0].category">
																													<form:option value="" label="Select" />
																													<form:options items="${categoryMap}" />
																												</form:select></td>
																											<td><form:input type="text"
																													path="lineItems[0].productName"
																													class="form-control productName"></form:input></td>

																											<td style="display: none;"><form:input
																													type="hidden" path="lineItems[0].productId"
																													class="form-control productId"></form:input></td>

																											<td><form:input type="text"
																													path="lineItems[0].hsnOrSac"
																													class="form-control hsnVal" readonly="true"></form:input></td>


																											<td><form:input type="text"
																													path="lineItems[0].uom"
																													class="form-control uom" readonly="true"></form:input></td>


																											<td><form:input type="text"
																													path="lineItems[0].quantity"
																													class="form-control"></form:input></td>

																											<td><form:input type="text"
																													path="lineItems[0].productGroup"
																													class="form-control productGroup"
																													readonly="true"></form:input></td>
																											<td><form:select class="form-control"
																													style="width:160px !important;"
																													path="lineItems[0].warehouse">
																													<form:option value="" label="Select" />
																													<form:options items="${planMap}" />
																												</form:select></td>
																											<td></td>
																										</tr>
																									</c:when>

																									<c:otherwise>
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty lineItems}">

																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${lineItems}"
																												var="listLineItems">
																												<tr class="multTot multTot0">
																													<td><form:select class="form-control"
																															style="width:160px !important;"
																															path="lineItems[${count}].category">
																															<form:option value="" label="Select" />
																															<form:options items="${categoryMap}" />
																														</form:select></td>
																													<td><form:input type="text"
																															path="lineItems[${count}].productName"
																															value="${listLineItems.productName}"
																															class="form-control productName"></form:input></td>

																													<td style="display: none;"><form:input
																															type="hidden"
																															path="lineItems[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input></td>

																													<td><form:input type="text"
																															path="lineItems[${count}].hsnOrSac"
																															value="${listLineItems.hsnOrSac}"
																															class="form-control hsnVal"
																															readonly="true"></form:input></td>


																													<td><form:input type="text"
																															path="lineItems[${count}].uom"
																															value="${listLineItems.uom}"
																															class="form-control uom" readonly="true"></form:input></td>


																													<td><form:input type="text"
																															path="lineItems[${count}].quantity"
																															value="${listLineItems.quantity}"
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
																													<td></td>
																												</tr>

																												<c:set var="count" value="${count + 1}"
																													scope="page" />
																											</c:forEach>

																											<input type="hidden" id="addressCount"
																												value="${count}">
																											<input type="hidden" id="addressIndexCount"
																												value="${count}">

																										</c:if>


																									</c:otherwise>
																								</c:choose>
																							</tbody>
																						</table>
																					</div>
																				</div>
																				<br> <br>
																			</div>
																		</div>
																	</div>
																</div>

																<div class="tab-pane" id="profile" role="tabpanel"
																	aria-labelledby="profile-tab">

																	<table class="table">
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
									<input type="hidden" id="vendordata" name="vendorId" />
									<div class="row">
										<div class="col-sm-6 form-group">
											<form:input type="submit" path="status" value="Save"
												class="btn btn-primary" />
											<form:input type="submit" path="status" value="Draft"
												class="btn btn-primary" />
										</div>
										<div class="col-sm-6 form-group">

											<a href="<c:url value="/rfq/list"/>">
												<button type="button" class="btn btn-warning mr-1">
													<i class="icon-cross2"></i> Cancel
												</button>
											</a> <a href="#" onclick="goBack()"
												class="btn btn-primary float-left"> Back </a>
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




</html>

