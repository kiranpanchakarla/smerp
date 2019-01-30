<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />

          
 <script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
<!--  <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>  -->   
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>  


<script src=<c:url value="/resources/js/scripts/datepicker/bootstrap-datepicker.min.js"/> type="text/javascript"></script>
<link href="<c:url value="/resources/css/datapickercss/bootstrap-datepicker.min.css"/>" rel="stylesheet" type="text/css" />

<script src=<c:url value="/resources/js/common.js"/> type="text/javascript"></script> 	

	 <style>
        .ui-autocomplete { 
            cursor:pointer; 
            height:120px; 
            overflow-y:scroll;
        }    
    </style>

</head>
<style>
    .table td a i {
    line-height: 35px;
      }
    </style>
 

<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/purchasesidebar.jsp" />
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

							<c:url value="/rfq/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form" class="bv-form commentForm"
								enctype="multipart/form-data" modelAttribute="rfq"
								data-toggle="validator" role="form" >
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">
												<div class="col-md-6"> <c:if test="${rfq.id!=null}">
												<h2 class="card-title" id="basic-layout-icons">Update Request For Quotation</h2>
													<form:input type="hidden" cssClass="form-control" path="id" />
												</c:if>
												<c:if test="${rfq.id==null}">
												<h2 class="card-title" id="basic-layout-icons">Create New Request For Quotation</h2>
												</c:if> </div>
												<div class="col-md-6">
												 <c:if test="${rfq.id!=null}">
												 <a  class="btn btn-primary float-right"> ${rfq.status} </a>
												</c:if>
												<c:if test="${rfq.id==null}">
												</c:if>
												
												</div>
												
												
												 	
												
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
													<form class="form">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>
																	<form:input type="text"
																		cssClass="form-control vendorname camelCase"
																		placeholder='Vendor Name' path="vendor.name"
																		required="true" autocomplete="off" />
																</div>
																	<form:hidden path="vendor.id" id="vendordata" />
																<div class="col-sm-4 form-group">
																	<label>Email Id</label>
																	<form:input type="text" cssClass="form-control emailId"
																		readonly="true" placeholder='Email Id'
																		path="vendor.emailId"   />
																</div>
                                                                <div class="col-sm-4 form-group">
																	<label>Contact Person </label>

																	<form:select path="vendorContactDetails.id"
																		id="vendorContactDetails" cssClass="form-control"
																		oninvalid="this.setCustomValidity('Please Select State ')"
																		oninput="setCustomValidity('')">
																		<form:option value="" >Select</form:option>
																	</form:select>
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
															</div>

															<form:hidden path="id" />

															<div class="row">
																

																<div class="col-sm-4 form-group rm_address">
																	<label>Pay To</label>
																	<form:select path="vendorPayTypeAddress.id"
																		id="vendorPayToAddress" cssClass="form-control"
																		required="true">
																		<form:option value="" >Select</form:option>
																	</form:select>
																</div>

																<div class="col-sm-4 form-group rm_address">
																	<label>Ship From</label>
																	<form:select path="vendorShippingAddress.id"
																		id="vendorAddress" cssClass="form-control"
																		required="true">
																		<form:option value="" >Select</form:option>
																	</form:select>
																</div>
                                                                
                                                                <div class="col-sm-4 form-group">
																				<label>Document Number</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Document Number' path="docNumber"
																					readonly="true" />
																			</div>

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Reference Document Number</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Reference Document Number'
																					path="referenceDocNumber"  />
																			</div>
                                                                            <div class="col-sm-4 form-group">
																				<label>Posting Date</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Posting Date' path="postingDate"
																					autocomplete="off" required="true" />
																				
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Document Date</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Document Date' path="documentDate"
																					autocomplete="off" required="true" />
																				
																			</div>
																		</div>




																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>
																				<form:input type="text" cssClass="form-control"
																					id="require_date" placeholder='Required Date'
																					autocomplete="off" path="requiredDate" required="true" />
																			
																			</div>
                                                                            <div class="col-sm-4 form-group">
                                                                          <label>Remark</label> 
                                                                            <form:textarea type="text" cssClass="form-control camelCase"
																					 placeholder='Enter your Remark'
																					autocomplete="off" path="remark"  />
                                                                           </div>
																		</div>
																		
																		
																		<div class="row" id="radioDiv">
																			<div class="col-sm-6 form-group">
																			<div class="input-group">
                                                                               
                                                                                <%--  <div class="inventory-list">
                                                                                <form:radiobutton cssClass="form-control"
																					 value="Item" path="category"  name="category"  id="items_radio" 
																				/>
                                                                                <span class="radio-list">Item</span>
                                                                                </div>
                                                                               
                                                                                <div class="inventory-list">
                                                                                <form:radiobutton cssClass="form-control"
																					 value="Services" path="category"  name="category" id="service_radio" 
																				/>	
                                                                                <span class="radio-list">Services</span></div> --%>
                                                                                
                                                                                   <div class="inventory-list">
                                                                                    <form:radiobutton name="type" path="category"  id="items_radio"  value="Item" checked="checked" disabled="true" />
                                                                                    <span class="radio-list">Product</span>

                                                                                </div>
                                                                                <div class="inventory-list" style="display: none;" >
                                                                                    <form:radiobutton name="type" path="category" id="service_radio"  value="Service" />
                                                                                    <span class="radio-list">Service</span>
                                                                                </div> 
                                                                                <div class="help-block with-errors"></div>
                                                                            </div>
																			
																			</div>
																		</div>
																		
																		<div class="row" id="pur_radioDiv" style="display: none">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Type</label>: <%-- ${rfq.category} --%> Product
																	</div>
																       </div>
																		
																		
																	</div>
																</div>
															</div>
															
															<div class="row">
																			
																			
															</div>
																		
															<input type="hidden" id="addressCount" value="0">
															

															<ul class="nav nav-tabs" id="containerContainingTabs" role="tablist">
																<li class="nav-item active"><a class="nav-link"
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
																						<table class="table table-bordered table-striped item-service-table"
																							id="itemTbl">
																							<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th style="display: none;">Product Id</th>
																									<th>Product No.</th>
																									<th>Description</th>
																									<th>UOM</th>
																									<th>SKU</th>
																									<th>Product Group</th>
																									<th>HSN</th>
																									<th>Ware house</th>
																									<th>Quantity</th>
																									<th>Action</th>
																								</tr>
																							</thead>
																							<tbody >
																										
                                                                                            </tbody>
                                                                                      </table>   
                                                                                         
                                                                                      <table class="table table-bordered table-striped item-service-table"
																							id="serviceTbl">   
																							<thead>
																								<tr>
																									<th style="display: none;">Product Id</th>
																									<th>SAC Code</th>
																									<th>Description</th>
																									<th>Quantity</th>
																									<th>Warehouse</th>
																									<th>Action</th>
																								</tr>
																							</thead> 
                                                                                            <tbody >
																										
                                                                                            </tbody>
                                                                                        </table>    
                                                                                            
                                                                                            
                                                                                            
                                                                                            
                                                                                            
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty lineItems}">
																										<form:hidden path="purchaseReqId" />
																						<table class="table table-bordered table-striped item-service-table	"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th style="display: none;">Product Id</th>
																									<c:if test="${rfq.category=='Item'}">
																									<th>Product No.</th>
																									<th>Description</th>
																									<th>UOM</th>
																									<th>SKU</th>
																									<th>Product Group</th>
																									<th>HSN</th>
																									<th>Warehouse</th>
																									<th>Quantity</th>
																									</c:if>
																									
																									<c:if test="${rfq.category!='Item'}">
																									<th>SAC Code</th>
																									<th>Description</th>
																									<th>Quantity</th>
																									<th>Warehouse</th>
																									</c:if>
																									
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${lineItems}"
																												var="listLineItems">
																												
																												
																											 <c:if test="${rfq.purchaseReqId!=null}">
				         
				                                                                                          <tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="lineItems[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="lineItems[${count}].id"/>	
																															</td>
																															
																													<c:if test="${rfq.category=='Item'}">
																													<td>${listLineItems.prodouctNumber}</td>
																													
																													<td>${listLineItems.description}</td>
																													
																													<td>${listLineItems.uom}</td>
																													
																													<td>${listLineItems.sku}</td>
																															
																													<td>${listLineItems.productGroup}</td>

																												    <td>${listLineItems.hsn}</td>
																												
																													<td><c:forEach var="entry"
																																items="${planMap}">
																																<c:if
																																	test="${entry.key ==listLineItems.warehouse}">
																													 ${entry.value} 																													 </c:if>
																															</c:forEach></td>

																													
																														
																													<td>${listLineItems.requiredQuantity}</td>
																														
																													</c:if>
																													
																													<c:if test="${rfq.category!='Item'}">
																													<td>${listLineItems.sacCode}</td>
																													
																													<td>${listLineItems.description}</td>
																															
																													<td>${listLineItems.requiredQuantity}</td>

																														<td><c:forEach var="entry"
																																items="${planMap}">
																																<c:if
																																	test="${entry.key ==listLineItems.warehouse}">
																													 ${entry.value} 																													 </c:if>
																															</c:forEach></td>
																															
																													</c:if>
																												
																												</tr>
																											</c:if>		
																										
																										 <c:if test="${rfq.purchaseReqId==null}">
																												<tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="lineItems[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="lineItems[${count}].id"/>	
																															</td>
																															
																													<c:if test="${rfq.category=='Item'}">
																													<td ><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].prodouctNumber" required="true"
																															value="${listLineItems.prodouctNumber}"  
																															class="form-control prodouctNumber"></form:input></div></td>
																												   
																												   <td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].description" required="true"
																															value="${listLineItems.description}"
																															class="form-control description description" ></form:input></div></td>
																													
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].uom"
																															value="${listLineItems.uom}"
																															class="form-control uom" readonly="true"></form:input>
																													    <form:input type="hidden"
                                                                                                                            path="lineItems[${count}].unitPrice"  readonly="true"
                                                                                                                            value="${listLineItems.unitPrice}"
                                                                                                                            class="form-control unitPrice " required="true" ></form:input>
																															</div></td>
																													
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].sku"
																															value="${listLineItems.sku}"
																															class="form-control sku" readonly="true"></form:input></div></td>
																													
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].productGroup"
																															value="${listLineItems.productGroup}"
																															class="form-control productGroup"
																															readonly="true"></form:input></div></td>
																														
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].hsn"
																															value="${listLineItems.hsn}"
																															class="form-control hsnVal"
																															readonly="true"></form:input></div></td>
																													
																													<td><div class="form-group"><form:select class="form-control"
																															style="width:160px !important;" required="true"
																															path="lineItems[${count}].warehouse">
																															<form:option value="" label="Select" />
																															<form:options items="${planMap}" />
																														</form:select></div></td>
																															
																															
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].requiredQuantity"
																															value="${listLineItems.requiredQuantity}" onkeypress="return isNumericKey(event)"
																															class="form-control validatePrice"  required="true"></form:input></div></td>
																															
																													</c:if>
																													
																													<c:if test="${rfq.category!='Item'}">
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].sacCode" required="true"
																															value="${listLineItems.sacCode}"
																															class="form-control sacCode"
																															></form:input></div></td>
																													
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].description" required="true"
																															value="${listLineItems.description}"
																															class="form-control description description" ></form:input></div></td>
																															
																													<td><div class="form-group"><form:input type="text"
																															path="lineItems[${count}].requiredQuantity"
																															value="${listLineItems.requiredQuantity}" required="true"
																															class="form-control"></form:input></div></td>
																													
																													<td><div class="form-group"><form:select class="form-control"
																															style="width:160px !important;" required="true"
																															path="lineItems[${count}].warehouse">
																															<form:option value="" label="Select" />
																															<form:options items="${planMap}" />
																														</form:select></div></td>
																															
																													</c:if>
																												            <td class="text-center"><a  onclick="removeData2(${count})" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a> </td>
																												
																												</tr>
																												
																										  </c:if>	

																												<c:set var="count" value="${count + 1}"
																													scope="page" />
																											</c:forEach>
																							
																							
																									</tbody>
																									</table>
																									<input type="hidden" id="edit_addressCount" value="${count-1}">
																										</c:if>

																						
																					</div>
																				</div>
																				 <c:if test="${rfq.purchaseReqId==null}">
																				<div class="add-row-block">
																					<input type="button" class="btn btn-info"
																						onclick="addItem()" value="Add Row">
																						
																				</div>
																				</c:if>
																				
																				
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
											<br>				
									<div class="text-xs-center">
									
												<a href="<c:url value="/rfq/list"/>" class="btn btn-primary float-left">
																	 Back </a>
									
										
											<c:if test="${rfq.status eq 'Draft' || rfq.id==null }">
                                                                   <form:button type="submit"  id="draft" name="statusType" value="DR" class="btn btn-draft mySubButton"> <i class="icon-check2"></i> Draft</form:button> 
                                                                   </c:if>
                                                                    <c:if test="${rfq.id==null}">
                                                                    <form:button  type="submit"  id="save" name="statusType" value="SA" class="btn btn-primary mySubButton"> <i class="icon-check2"></i>Save</form:button>
                                                                    </c:if>
                                                                    <c:if test="${rfq.id!=null}">
                                                                       <form:button  type="submit" id="update" name="statusType" value="SA" class="btn btn-primary mySubButton"> <i class="icon-check2"></i> Update</form:button>
                                                                      
                                                                      <a
																			href="<c:url value="/rfq/cancelStage?id=${rfq.id}"/>">
																			<button type="button" class="btn btn-warning mr-1">
																				<i class="icon-cross2"></i> Cancel
																			</button>
																		</a>
																		
																		
                                                                      </c:if>
                                                                      <!-- Approve -->
                                                                       <c:forEach items="${sessionScope.umpmap}" var="ump">
																		 <c:if test="${ump.key eq 'RFQ'}">
																		 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
																		<c:if test="${fn:containsIgnoreCase(permissions,'Approve')}"> 
                                                                      <form:button  type="submit" id="approve" name="statusType" value="APP" class="btn btn-approve mySubButton"> <i class="icon-check2"></i>Approve</form:button>
                                                                      </c:if></c:if></c:forEach>
                                                                      <!-- Reject -->
                                                                     <c:forEach items="${sessionScope.umpmap}" var="ump">
																		 <c:if test="${ump.key eq 'RFQ'}">
																		 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
																		<c:if test="${fn:containsIgnoreCase(permissions,'Reject')}"> 
                                                                       <c:if test="${rfq.status != 'Cancelled'}">
                                                                      <form:button  type="submit" id="reject" name="statusType" value="RE" class="btn btn-reject mySubButton"> <i class="icon-cross2"></i>Reject</form:button>
                                                                    </c:if>
                                                                     </c:if></c:if></c:forEach>
                                                                     
										</div>
										</div>
														</div>
													</div>
												</div>
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



	
	
	
</body>
<!-- alertfy-->
<!-- form validations-->


<!-----------    END   ---------------------------------->


<script type="text/javascript">

var sizeplant = "${planMapSize}";
var scriptSelectPlant='';
if(sizeplant>1) {
    scriptSelectPlant ='<option value="">select</option>';
     }
var inc=0;
var edit_addressCount=0;

var purchaseReqId=$('#purchaseReqId').val();
if(purchaseReqId!='' && purchaseReqId != undefined) {
	 $("#pur_radioDiv").show();
	 $("#radioDiv").hide();
}



if ($('#service_radio').is(":checked") == true) {
	// alert("CHECKsERVICE");
		 $("#serviceTbl").show();
		 $("#itemTbl").hide();
	 }else {
		// alert("cHECKitem");
		 document.getElementById("items_radio").checked = true;
		 $("#serviceTbl").hide();
	      $("#itemTbl").show();
	 }
	
	
	 
		if ($('#edit_addressCount').val() != undefined ) {
			// alert("edit");
			 $("#serviceTbl").hide();
			 $("#itemTbl").hide();
		}

		if ($('#edit_addressCount').val() != undefined ) {
		}else{
			addItem();
		}


function addItem() {
		
	
						var addressCount = $('#addressCount').val();
						 edit_addressCount = $('#edit_addressCount').val();
						if (edit_addressCount != undefined ) {
							    inc = parseInt(edit_addressCount)+1;
								$('#edit_addressCount').val(inc);
						}else {
								inc = parseInt(addressCount) ;
						}
						
						
						//alert(inc);
						
	
	  if ($('#items_radio').is(":checked") == true) {
		  //alert("itemAppend");
	        var item_table_data = '<tr class="multTot multTot'+inc+'">'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].prodouctNumber" class="form-control prodouctNumber prodouctNumber'+inc+'" required="true" id="prodouctNumber'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td style="display:none;">'
			+'<div class="form-group">'
			+'<input type="hidden" name="lineItems['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].description" autocomplete="off" required="true" class="form-control description description'+inc+'" id="description'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].uom" class="form-control uom uom'+inc+'" id="uom'+inc+'"  readonly="true"  />'
			+'<input type="hidden" name="lineItems['+inc+'].unitPrice"  class="form-control unitPrice unitPrice'+inc+'" id="unitPrice'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].sku" class="form-control sku sku'+inc+'" id="sku'+inc+'"  readonly="true"  />'
			+ '</div>'
			+'</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].productGroup" readonly="true" class="form-control  productGroup productGroup'+inc+'" id="productGroup'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].hsn" readonly="true" class="form-control hsnVal hsn'+inc+'" id="hsn'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="lineItems['+inc+'].warehouse" required="true"  style="width:160px !important;" class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
			+scriptSelectPlant+
			<c:forEach items="${planMap}" var="planMap">
			'<option value="${planMap.key}">${planMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].requiredQuantity" onkeypress="return isNumericKey(event)"  required="true" class="form-control validatePrice requiredQuantity'+inc+'" id="requiredQuantity'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ ' <td class="text-center"><a  onclick="removeData('+inc+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
			+ '</td>'
			 
			
	+'</tr>';
			if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
				$("#edit_item_serviceTbl").append(item_table_data);
			}else{
				 $("#itemTbl").append(item_table_data);
			}
		 
					
	  }else {
		// alert("sERVICEAppend");
		  var service_table_data='<tr class="multTot multTot'+inc+'">'
			
			+'<td style="display:none;">'
			+'<div class="form-group">'
			+'<input type="hidden" name="lineItems['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].sacCode" required="true"  class="form-control sacCode  sacCode'+inc+'" id="hsn'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].description" readonly="true" class="form-control description '+inc+'" id="uom'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="lineItems['+inc+'].requiredQuantity" required="true" onkeypress="return isNumericKey(event)"  class="form-control validatePrice requiredQuantity'+inc+'" id="requiredQuantity'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="lineItems['+inc+'].warehouse" required="true"  style="width:160px !important;" class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
			+'<option value="">select</option>'+
			<c:forEach items="${planMap}" var="planMap">
			'<option value="${planMap.key}">${planMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			
			
			+ ' <td class="text-center"><a  onclick="removeData1('+inc+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
			+ '</td>'
			 
			
	+'</tr>';
		 // alert(service_table_data);
		  if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
			 // alert("edit");
			  $("#edit_item_serviceTbl").append(service_table_data);
			}else{
				 $("#serviceTbl").append(service_table_data);
			}
		  
	  }
		
	
		inc++;
		$('#addressCount').val(inc);
		$("#form").validator("update");
	}


$(document).ready(function(){
	
	
	var id=$('#id').val();
	
	//alert("id--->"+id);

	        $('#postingDate').datepicker({ 
		    	 dateFormat: 'dd/mm/yy' ,   //    dateFormat: 'MM dd, yy' 
		    }).datepicker( "option", { setDate:"0",
		            maxDate:'+3y -1d',
		            minDate:'0' } );
		    
		    $('#documentDate').datepicker({ 
		   	 dateFormat: 'dd/mm/yy' ,   //    dateFormat: 'MM dd, yy' 
		   }).datepicker( "option", { setDate:"0",
		           maxDate:'+3y -1d',
		           minDate:'0' } );
		    
		    $('#require_date').datepicker({ 
		      	 dateFormat: 'dd/mm/yy' ,   //    dateFormat: 'MM dd, yy' 
		      }).datepicker( "option", { setDate:0,
		              maxDate:'+3y -1d',
		              minDate:'0' } );
	
	
		if(id!=''){
			var vendorName=$('.vendorname').val();
			//alert("vendorName"+vendorName);
			
			if(vendorName!=''){
			autocompletevendorDetails(vendorName);
			}
			
			var payTypeAddressId='${vendorPayTypeAddressId}';
			var shippingAddressId='${vendorShippingAddressId}';
			//alert("payTypeAddressId"+payTypeAddressId);
			//alert("shippingAddressId"+shippingAddressId);
			 
		}else{
		$('#postingDate').datepicker("setDate", "0"); //"0" for current date
		$('#documentDate').datepicker("setDate", "0"); //"0" for current date
		$('#require_date').datepicker("setDate", "10"); //"after 10" for current date
		}
	
	
		 $('#postingDate').datepicker({
			  dateFormat: 'dd/mm/yy' 
				  });
			
		  $('#documentDate').datepicker({
			  dateFormat: 'dd/mm/yy' 
		  });
		  
		  
		  $('#require_date').datepicker({
			  dateFormat: 'dd/mm/yy' 
		  });
    
	
	var vendorNames=[];
	var vendorNamesList=${vendorNamesList};
	var availableTagsvendornames=[];
	 $.each(vendorNamesList, function (index, value) {
		 availableTagsvendornames.push(value.toString());
	   });
	
	// alert("push data-->" +availableTagsvendornames);
	/* 	$(document).on("keypress", ".vendorname", function() {
			$(this).autocomplete({
		        source: availableTagsvendornames,
		        select: function(event, ui) {
		        	var vendorname = ui.item.value;
		        	//alert(vendorname);
		            autocompletevendorDetails(vendorname);
		       		 },
		        }); 
			}); */
			
			$(document).on("focus", ".vendorname", function() {
				$(this).autocomplete({
			        source: availableTagsvendornames,
			        minLength: 0,
		            scroll: true, 
		            select: function(event, ui) {
			        	var vendorname = ui.item.value;
			            autocompletevendorDetails(vendorname);
			       		 },
				}).focus(function() {
		            $(this).autocomplete("search", "");
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
                	   $("#vendorContactDetails").html('');
                	$.each(vendorcontactdetails, function( index, value ) {
                		//  alert( index + ": " + JSON.stringify(value) );
                		 //   alert(value.contactName)
                		    $("#vendorContactDetails").append($("<option></option>").attr("value",value.id).text(value.contactName)); 
                		});
                	  
                	 $("#vendorAddress").html('');
                     $("#vendorPayToAddress").html('');
                   //  alert("vendoraddress-->"+vendoraddress);
                	  $.each(vendoraddress, function( index, value ) {
              		//alert( index + ": " + JSON.stringify(value) );
              		 if(value.payTo!=null &&  value.shipFrom!=null   ){
          		  		 $("#vendorAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
          		  	     $("#vendorPayToAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
          			 }else if(value.payTo!=null &&  ("payTo".localeCompare(value.payTo)==0)){
          				//alert("Payto"+value.payTo);
          				 $("#vendorPayToAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
          			 }else if(value.shipFrom!=null &&  ("shipFrom".localeCompare(value.shipFrom)==0) ){
          		  	//	alert("Shipping");
          				 $("#vendorAddress").append($("<option></option>").attr("value",value.id).text(value.city)); 
          			   } 
              			 
              		  });
                	  
                	
                  	$('#payToAddressTable').html("");
                  	vendorPayTypeAddress($('#vendorPayToAddress').val());
                  	
                	  
              		$('#shippingAddressTable').html("");
              		vendorShippingAddress($('#vendorAddress').val());
              		
              	  $('.rm_address').removeClass('has-error has-danger');
              	  $("#form").validator("update");
              	  
                	
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
	 //alert("length"+availableTags);
		 
		//$(".prodouctNumber").autocomplete({
/* 	$(document).on("keypress", ".prodouctNumber", function() {
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
		}); */
	
	$(document).on("focus", ".prodouctNumber", function() {
		var itemParentRow = $(this).parents(".multTot");
		//alert("itemParentRow"+itemParentRow);
		
		$(this).autocomplete({
	        source: availableTags,
	        minLength: 0,
            scroll: true, 
            select: function(event, ui) {
	        	var name = ui.item.value;
	        	//alert(name);
	             autocompleteandchange(name,itemParentRow);
	       		 },
			}).focus(function() {
	            $(this).autocomplete("search", "");
	        });
		});
		
	
	 $(document).on("blur", ".prodouctNumber", function() {
     	var itemParentRow = $(this).parents(".multTot");
     
     	
     	var arr=[];
     	 $(".prodouctNumber").each(function() {
     	//alert("validation-->"+	availableTags.includes($(this).val()) );
        if(availableTags.includes($(this).val()) == true) 	 {
     	
		        if ($.inArray($(this).val(), arr) == -1){
		            arr.push($(this).val());
		       	// var isDluplicate = true;
		       	
		       	//autocompleteandchange(($(this).val()),itemParentRow);
		        }else{
		        	 /* var isDluplicate = false; */
		        	   alertify.alert("Request For Quotation","You have already entered the Product Number "+$(this).val());
		        	/*  $(this).val('') */
		          ($(this).parents('tr').find('td').find('input').val(''));
		        	 ($(this).parents('tr').find('td').find('select').val('')); 
		        
		        }
        }else {
        	
        	if($(this).val()!=""){

        	 alertify.alert("Request For Quotation",$(this).val() +  " Product Number Does Not Exists!");  
        	 ($(this).parents('tr').find('td').find('input').val(''));
        	 ($(this).parents('tr').find('td').find('select').val('')); 
             }
        }
		        
		    });
     });
	

	//get the product information based on product  name
    	function autocompleteandchange(name,itemParentRow){
			//alert(name);
			//alert(itemParentRow);
    	 $.ajax({
			   type: "GET",
    			data: {name :name}, 
    			async : false,
                url: "<c:url value="/product/getProductInfo"/>", 
                success: function (response) {
                	console.log("getProductInfo-->"+response);
                	
                	//alert("--->"+response);
                	if(response!="") {
                	var obj =JSON.parse(response);
                	
                	//var myJSON = JSON.stringify(obj);
                	
                	var hsndata=0;
                	
                	if(obj.hsnCode!=null) {
                		hsndata=obj.hsnCode;
                	}
                	//alert("hsnCode"+hsndata.hsnCode);
                //	$('.hsnVal').val(hsndata.hsnCode);
                
                	//$(itemParentRow).find(".prodouctNumber").val(obj.productNo);
                	$(itemParentRow).find(".productId").val(obj.id);
            	
                	$(itemParentRow).find(".description").val(obj.description);
                	 $(itemParentRow).find(".hsnVal").val(hsndata.hsnCode);
                	
                	var uom=obj.purchasingUom.uomName;
                	
                	var sku=obj.packingUom.uomName;
                	 
                	//alert("uom"+uom);
                	//$('.uom').val(uom);
                	
                	 $(itemParentRow).find(".uom").val(uom);
                	 
                	 $(itemParentRow).find(".sku").val(sku);
                	
             	 //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
             	 //	var productgroup=obj.productCategory.categoryType;
             
             		var productgroup=obj.productGroup.productName;
             		
             	 	//$('.productGroup').val(productgroup);
             	 	
             	 	 $(itemParentRow).find(".productGroup").val(productgroup);
             	 	 $(itemParentRow).find(".unitPrice").val(obj.productCost);
             	 	 
                	}
                	
               	 },
                error: function(e){
               //  alert('Error: ' + e);
                 }
                });
		}
	
	    /*  Discription List */
    	    var descriptionList = [];
           var getDescription = ${descriptionList};
           var availabledescTags = [];
             $.each(getDescription, function(index, value) {
              availabledescTags.push(value.toString());
          });   
	
              $(document).on("focus", ".description", function() {
       			var itemParentRow = $(this).parents(".multTot");
       			//alert("itemParentRow"+itemParentRow);
       			
       			$(this).autocomplete({
       		        source: availabledescTags,
       		        minLength: 0,
       	            scroll: true, 
       	            select: function(event, ui) {
       		        	var name = ui.item.value;
       		        	//alert(name);
       		            autocompleteandchangedesc(name,itemParentRow);
       		       		 },
       				}).focus(function() {
       		            $(this).autocomplete("search", "");
       		        });
       			});   
             
              $(document).on("blur", ".description", function() {
             	var itemParentRow = $(this).parents(".multTot");
             
             	
             	var arr=[];
             	 $(".description").each(function() {
                 	 
                 	  if(availabledescTags.includes($(this).val()) == true) 	 {
     		        if ($.inArray($(this).val(), arr) == -1){
     		            arr.push($(this).val());
     		           
     		       	// var isDluplicate = true;
     		       	//autocompleteandchange(($(this).val()),itemParentRow);
     		        }else{
     		        	   var isDluplicate = false;
     		        	   alertify.alert("Duplicate Entry","You have already entered the Product Description "+($(this).val()));
     		        	 $(this).val('')
     		        	 ($(this).parents('tr').find('td').find('input').val(''));
     		        	 ($(this).parents('tr').find('td').find('select').val(''));
     		        
     		        }
                 }
                 	  else
                 	  {
                 		 if($(this).val()!=""){
                      	 alertify.alert("No Entry Found",$(this).val() +  " Product Description Does Not Exists!");  
                      	  ($(this).parents('tr').find('td').find('input').val(''));
                      	   ($(this).parents('tr').find('td').find('select').val(''));
                 		 }
                      }   
     		      
     		    });
                         	
             	
             });
          
             //get the product information based on product  description
              function autocompleteandchangedesc(name1, itemParentRow) {
                 //alert(name);

                 $.ajax({
                     type: "GET",
                     data: {name: name1},
                     async: false,
                     url: "<c:url value="/product/getProductInfoByDescription"/>", 
                     success: function(response) {
                         console.log(response);

                         var obj = JSON.parse(response);

                         //var myJSON = JSON.stringify(obj);

 	                       var hsndata=0;
 	                	
 	                	if(obj.hsnCode!=null) {
 	                		hsndata=obj.hsnCode;
 	                	}
                         //alert("hsnCode"+hsndata.hsnCode);
                         //	$('.hsnVal').val(hsndata.hsnCode);
                         $(itemParentRow).find(".hsnVal").val(hsndata.hsnCode);

                        // $(itemParentRow).find(".prodouctNumber").val(obj.description);
                         $(itemParentRow).find(".productId").val(obj.id);
                         $(itemParentRow).find(".prodouctNumber").val(obj.productNo);
                         //$(itemParentRow).find(".prodouctNumber").val(obj.description);

                         // $(itemParentRow).find(".productGroup").val(obj.producttype.name);
                         var sku=obj.packingUom.uomName;
                	
                         var uom = obj.purchasingUom.uomName;
                         //alert("uom" + uom);
                         //$('.uom').val(uom);

                         $(itemParentRow).find(".uom").val(uom);
                         $(itemParentRow).find(".sku").val(sku);
                         //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
                       //  var productgroup = obj.productCategory.categoryType;
                         var productgroup=obj.productGroup.productName;
                         //$('.productGroup').val(productgroup);

                         $(itemParentRow).find(".productGroup").val(productgroup);
                         $(itemParentRow).find(".unitPrice").val(obj.productCost);
                        
           
                     },
                     error: function(e) {
                         //  alert('Error: ' + e);
                     }
                 });
                          
             }   
       /* End of Description List  */     
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
                                             
                                         
                                         
                                         
                                         $(document).on("blur", ".sacCode", function() {
                                          	var itemParentRow = $(this).parents(".multTot");
                                          	
                                          	 //var isDluplicate = true;
                                          	//var name1 =  $("#"+itemParentRow.context.id).val(); 
                                          	//recipientsArray = enterdproducts.sort(); 
                                          	var arr=[];
                                          	 $(".sacCode").each(function() {
                                              	 // alert($.inArray($(this).val(), arr));
                                              	  if(availableSacs.includes($(this).val()) == true) 	 {
                                              	 
                                  		        if ($.inArray($(this).val(), arr) == -1){
                                  		            arr.push($(this).val());
                                  		       	// var isDluplicate = true;
                                  		       //	autocompleteandchangeSacCode(($(this).val()),itemParentRow);
                                  		        }else{
                                  		        	 
                                  		        	   alertify.alert("Request For Quotation","You have already entered the SAC Code "+$(this).val());
                                  		        	 $(this).val('')
                                  		        	 ($(this).parents('tr').find('td').find('input').val(''));
                                  		        	 ($(this).parents('tr').find('td').find('select').val(''));
                                  		        }
                                  		        
                                              }else {
                                            	  alertify.alert("Request For Quotation",$(this).val() +" SAC Code Does Not Exists ");
                               		        	 $(this).val('')
                               		        	 ($(this).parents('tr').find('td').find('input').val(''));
                               		        	 ($(this).parents('tr').find('td').find('select').val(''));  
                                              }
                                  		        
                                  		        
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
	
	
	
  });

                                                
				
		
		
function removeData(index){
	//alert("ff"+index);
	var rowCount = $('#itemTbl tr').length-2;
	if(rowCount==0){
	alertify.confirm("Request For Quotation",'You will loose your Data, Do you want to Continue?', function(){
		
		//($('table#itemTbl').parents('tr').find('td').find('input').val(''));
		$('#itemTbl input[type="text"]').val('');
		$('.warehouse').prop('selectedIndex',0);
		$("#form").validator("update");
		}, function(){
		    alertify.error('Cancelled');
		 });
		}else{
			if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
				$('table#edit_item_serviceTbl tr.multTot'+index).remove();
			}else{
				$('table#itemTbl tr.multTot'+index).remove();
			}
			
			$("#form").validator("update");
		  }
	}
	
	
function removeData1(index){
	//alert("ff"+index);
	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	}else{
		$('table#serviceTbl tr.multTot'+index).remove();
	}
	$("#form").validator("update");
}


function removeData2(index){
	//alert("ff"+index);
	var rowCount = $('#edit_item_serviceTbl tr').length-2;
	if(rowCount==0){
		alertify.alert("Request For Quotation","Can't Delete this Row");
		//($('table#itemTbl').parents('tr').find('td').find('input').val(''));
		$('#edit_item_serviceTbl input[type="text"]').val('');
		$('.warehouse').prop('selectedIndex',0);
		return false;
	}
	
	$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	$("#form").validator("update");
}


</script>


<script type="text/javascript">


$("#items_radio").click(function() {
	//alert("item");
	 alertify.confirm("Request For Quotation",'Are you Sure Want to Change  Item ,Service will be removed ', function(){
		 $("#serviceTbl").hide();
		 $("#itemTbl").show();
		 $("#edit_item_serviceTbl").hide();
		 $(this).parents().find("#serviceTbl").find(".form-control").attr("required",false).val(''); 
		 
	       var edit_inc = $('#edit_addressCount').val();
	       if(edit_inc!= undefined){
	    	   inc = edit_inc;
	       }
	      for(var k=0;k<=inc;k++) {
	    	  removeData1(k);
	    	  removeData(k);
	    	  if(edit_inc!= undefined){
	    		  removeData2(k);
	    	  }
		  }
		  inc=0;
		  
		  $('#addressCount').val(0);
		  $('#edit_addressCount').val(-1);
		  addItem();
		 
		 
	  			 
	          }, function(){
	        	 
	        	$("#service_radio").prop('checked',true); 
	      alertify.error('Cancelled');
	   });
	
});

$("#service_radio").click(function() {
	//alert("service");
	 alertify.confirm("Request For Quotation",'Are you Sure Want to Change Service ,Items will be removed! ', function(){
	$("#serviceTbl").show();
	 $("#itemTbl").hide();
	 $("#edit_item_serviceTbl").hide();
	 
	 $(this).parents().find("#itemTbl").find(".form-control").attr("required",false).val(''); 
      var edit_inc = $('#edit_addressCount').val();
      if(edit_inc!= undefined){
   	   inc = edit_inc;
      }
      for(var k=0;k<=inc;k++) {
    	  removeData(k);
    	  removeData1(k);
    	  if(edit_inc!= undefined){
    		  removeData2(k);
    	  }
	  }
      
	  inc=0;
	  $('#addressCount').val(0);
	  $('#edit_addressCount').val(-1);
	  addItem();
	  
	 }, function(){
     $("#items_radio").prop('checked',true); 
     alertify.error('Cancelled')
    });
	 
});

$('#containerContainingTabs a').on('click', function(e) {
	e.preventDefault();
	$(this).tab('show');
	var theThis = $(this);
	$('#containerContainingTabs a').removeClass('active');
	theThis.addClass('active');
	});
	
	
//$('form.commentForm').on('submit', function(event) {
$(".mySubButton").on('click', function() {
	
	if($(".mySubButton").hasClass("disabled")){
		 alertify.error('Please fill mandatory fields');
		alertify.alert("Request For Quotation","Please fill mandatory fields");
		$("#form").submit();
		 return false;
	  } else {
		 var subStatus = $(this).val();
        	if(subStatus == 'DR'){
        		alertify.message('Draft Successfully');
				return true;
			  } else if(subStatus == "SA"){
				 alertify.success('Saved Successfully');
				return true;
			  } else if(subStatus == "APP"){
				 alertify.success('Ready to Convert RFQ to PO');
				return true;
			  } else if(subStatus == "RE"){
				 alertify.warning('Document Rejected');
				 return true;
			  }  
		  }
    
    if ($('#items_radio').is(":checked") == true) {
    var rowCount = $('#itemTbl tr').length-1;
    
    if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
    	rowCount = $('#edit_item_serviceTbl tr').length-1;
		}
    
	if(rowCount == 0){
		alertify.alert("Request For Quotation","Please Select Atleast One Item");
		 return false;
	}else{
		return true;
	}
    } 
    
    if ($('#service_radio').is(":checked") == true) {
	 var rowCount1 = $('#serviceTbl tr').length-1;

	 if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		 rowCount1 = $('#edit_item_serviceTbl tr').length-1;
			} 
	 
 	if(rowCount1 == 0){
 		alertify.alert("Request For Quotation","Please Select Atleast One Service");
 		 return false;
 	}else{
 		return true;
 	}
	  }
    
});

function isNumericKey(evt)
{
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 
	&& (charCode < 48 || charCode > 57))
	return false;
	return true;
} 


	
$(document).on("keypress", ".validatePrice", function(e) {	
	if (this.value.length == 0 && e.which == 48 ){
		      return false;
		   }
	
	});

function goBack() {
    window.history.back();
}
	
	</script>

<%-- <c:import url="/WEB-INF/jsp/loadJs.jsp" />  --%>

<!-- alertifyjs -->

<link href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
    rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
    rel="stylesheet" type="text/css" />
<script src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/> type="text/javascript"></script>  



<link href="<c:url value="/resources/css/themes/jquery-ui.css"/>" rel="stylesheet" type="text/css" />
<script src=<c:url value="/resources/js/jquery-ui.js"/> type="text/javascript"></script>

<c:import url="/WEB-INF/jsp/loadJs.jsp" /> 
            
<script>
$(document).ready(function(){
	$.noConflict();
});
</script>

</html>
