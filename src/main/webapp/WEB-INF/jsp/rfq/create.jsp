
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

<script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>

<link href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/datapickercss/bootstrap-datepicker.min.css"/>" rel="stylesheet" type="text/css" />
	
</head>

	





<!-----------    END   ---------------------------------->	
	

<script type="text/javascript">
$(document).ready(function(){
	
	var vendorNames=[];
	var vendorNamesList=${vendorNamesList};
	var availableTagsvendornames=[];
	 $.each(vendorNamesList, function (index, value) {
		 availableTagsvendornames.push(value.toString());
	   });
	
	
		$(document).on("keypress", ".vendorname", function() {
			var itemParentRow = $(this).parents(".multTot");
			$(this).autocomplete({
		        source: availableTagsvendornames,
		        select: function(event, ui) {
		        	var vendorname = ui.item.value;
		        	alert(vendorname);
		            autocompletevendorDetails(vendorname,itemParentRow);
		       		 },
		        }); 
			});
			 
		
		//get the vendor information based on vendor name
    	function autocompletevendorDetails(vendorname,itemParentRow){
			alert(vendorname);
			
    	 $.ajax({
			   type: "GET",
    			data: {vendorname :vendorname}, 
    			async : false,
                url: "<c:url value="/vendor/getVendorInfo"/>", 
                success: function (response) {
                	console.log(response);
                	var obj =JSON.parse(response);
                	
                	$('#vendordata').val(obj.id);
                	
                	$('.emailId').val(obj.emailId);
                	var vendorcontactdetails=obj.vendorContactDetails;
                	var vendoraddress=obj.vendorAddress;
                	
                	$.each(vendorcontactdetails, function( index, value ) {
                		  alert( index + ": " + JSON.stringify(value) );
                		    alert(value.contactName)
                		    $("#vendorContactDetails").append($("<option></option>").attr("value",value.id).text(value.contactName)); 
                		});
                	
                	$.each(vendoraddress, function( index, value ) {
              		  alert( index + ": " + JSON.stringify(value) );
              		   $("#vendorAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
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
                	
                	var hsndata=obj.hsnCode;
                	alert("hsnCode"+hsndata.hsnCode);
                //	$('.hsnVal').val(hsndata.hsnCode);
                	
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
	
	
	//alert("ready");
	$('#addItem').click(function() {
		var count = $('#itemTbl tr').length;
		var i = count-1;
	//	alert("i"+i);
		
		$("#itemTbl").append(
				'<tr class="multTot multTot'+i+'">'
				
						
						+ '<td>'
						+ '<select  name="lineItem['+i+'].category" style="width:160px !important;" class="form-control category'+i+' category"  id="category'+i+'" >'
						+'<option value="">select</option>'+
						<c:forEach items="${categoryMap}" var="category">
						'<option value="${category.key}">${category.value}</option>'+
						</c:forEach>
						+ '</select>'
						+ '</td>'
					
						
						+'<td>'
						+'<input type="text" name="lineItem['+i+'].productNo" class="form-control productName productNo'+i+'" id="productNo'+i+'"   />'
						+'</td>'
						
						+'<td>'
						+'<input type="text" name="lineItem['+i+'].hsnOrSac" class="form-control hsnVal hsnOrSac'+i+'" id="hsnOrSac'+i+'"   />'
						+'</td>'
						
						
						+'<td>'
						+'<input type="text" name="lineItem['+i+'].uom" class="form-control uom uom'+i+'" id="uom'+i+'"   />'
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
						+'<input type="text" name="lineItem['+i+'].quantity" class="form-control quantity'+i+'" id="quantity'+i+'"   />'
						+'</td>'
						
						+'<td>'
						+'<input type="text" name="lineItem['+i+'].productGroup" class="form-control  productGroup productGroup'+i+'" id="productGroup'+i+'"   />'
						+'</td>'
						
						
						+ '<td>'
						+ '<select  name="lineItem['+i+'].warehouse" style="width:160px !important;" class="form-control warehouse'+i+' warehouse"  id="warehouse'+i+'" >'
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
																	<form:input type="text" cssClass="form-control vendorname"
																		placeholder='Vendor Name' path="name"
																		required="true" />
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
																<div class="col-sm-6 form-group">
																	<label>Email Id</label>
																	<form:input type="text" cssClass="form-control emailId" readonly="true"
																		placeholder='Email Id' path="emailId"
																		required="true" />
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
															</div>
															
															

															<div class="row">
																<div class="col-sm-6 form-group">
																	<label>Contact Person </label>
																	
																	<form:select path="vendorContactDetails.id"  id="vendorContactDetails"
																		cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select State ')"	oninput="setCustomValidity('')">
																	</form:select>
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>

																<div class="col-sm-6 form-group">
																	<label>Ship From</label>
																	<form:select path="vendorAddress.id"  id="vendorAddress"
																		cssClass="form-control" required="true" >
																	</form:select>	
																		
																
																</div>

															</div>

															<div class="card-body collapse in">
																<div class="card-block">
																	<div class="form-body">
																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Posting Date</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Posting Date' path="postingDate" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Document Date</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Document Date' path="documentDate" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>


																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>
																				<form:input type="text" cssClass="form-control"
																					id="require_date" placeholder='Required Date'
																					path="requiredDate" />
																				<div style="color: red;"
																					class="help-block with-errors"></div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>


													

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
																								<td>
																									<form:select class="form-control"
																											style="width:160px !important;"
																											path="lineItems[0].category">
																											<form:option value="" label="Select" />
																											<form:options items="${categoryMap}" />
																										</form:select>		
																								</td>
																								<td><form:input type="text"
																											path="lineItems[0].productNo" 
																											class="form-control productName" ></form:input></td>
																								
																									<td><form:input type="text"
																											path="lineItems[0].hsnOrSac"
																											class="form-control hsnVal" readonly="true"></form:input></td>
																										
																										
																									<td><form:input type="text"
																											path="lineItems[0].uom"
																											class="form-control uom" readonly="true"></form:input></td>
																												
																									<%-- <td><form:select class="form-control uom"
																											style="width:160px !important;"
																											path="lineItems[0].uom">
																											<form:option value="" label="Select" />
																										</form:select>
																									</td>	 --%>	
																											
																									
																									<td><form:input type="text"
																											path="lineItems[0].quantity"
																											class="form-control"></form:input></td>
																										
																									<td><form:input type="text"
																											path="lineItems[0].productGroup"
																											class="form-control productGroup" readonly="true"></form:input>
																									</td>
																										<td><form:select class="form-control"
																											style="width:160px !important;"
																											path="lineItems[0].warehouse">
																											<form:option value="" label="Select" />
																											<form:options items="${planMap}" />
																										</form:select>
																									</td>
																									<td></td>
																								</tr>
																							</c:when>

																							<c:otherwise>
																							hiiiiiiiii
																							</c:otherwise>
																						</c:choose>
																					</tbody>
																				</table>
																			</div>
																		</div>
																		<br>
																		<br>
																	</div>
																</div>
															</div>



														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" id="vendordata"  name="vendorId" />
									
									<button type="submit" name="submitSave" class="btn btn-primary">
																				<i class="icon-check2"></i> Save
																			</button>
									<div>
										<a href="#" onclick="goBack()" class="btn btn-primary"
											style="float: left;"> Back</a>
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
	<footer class="footer footer-static footer-light navbar-border">
		<p class="clearfix text-muted text-sm-center mb-0 px-2">
			<span class="float-md-right d-xs-block d-md-inline-block">Copyright
				&copy; 2018 <a href="#" target="_blank"
				class="text-bold-800 grey darken-2">SMERP </a>, All rights reserved.
			</span>
		</p>
	</footer>
	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<!-- alertfy-->
<!-- form validations-->
<script>
  $(function () {
    //Date picker
	//var date = new Date();
	//var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	//var enddate  = new Date(date.getFullYear(), date.getMonth(), date.getDate()+10);
    $('#postingDate').datepicker({
      autoclose: true,
      format: 'dd/mm/yyyy',
	  orientation: 'bottom auto'
    });
	$('#documentDate').datepicker({
      autoclose: true,
      format: 'dd/mm/yyyy',
      orientation: 'bottom auto'
    }); 
	$('#require_date').datepicker({
      autoclose: true,
      format: 'dd/mm/yyyy',
      orientation: 'bottom auto'
    });
	// update with current date
	//$( '#posting_date, #doc_date','#require_date' ).datepicker( 'setDate', today );
	
	
 });
 

    
</script>
<script
	src=<c:url value="/resources/js/scripts/datepicker/bootstrap-datepicker.min.js"/>
	type="text/javascript"></script>


</html>

