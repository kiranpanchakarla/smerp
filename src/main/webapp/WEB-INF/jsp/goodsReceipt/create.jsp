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
	href="<c:url value="/resources/css/datapickercss/bootstrap-datepicker.min.css"/>"
	rel="stylesheet" type="text/css" />

<script src=<c:url value="/resources/js/common.js"/> type="text/javascript"></script>

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
					<h4>gr</h4>
				</div>
			</div> -->
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">

							<c:url value="/gr/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form"
								class="bv-form commentForm" enctype="multipart/form-data"
								modelAttribute="gr" data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">
												<div class="col-md-6">
													<c:if test="${gr.id!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update
															Goods Receipt</h2>
														<form:input type="hidden" cssClass="form-control"
															path="id" />
													</c:if>
													
													<c:if test="${gr.id==null}">
														<h2 class="card-title" id="basic-layout-icons">Create
															New Goods Receipt</h2>
													</c:if>
													</div>
													<div class="col-md-6">
													<c:if test="${gr.id!=null}">
														 <a  class="btn btn-primary float-right"> ${gr.status} </a>
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
																		<form:input type="text"
																			cssClass="form-control emailId" readonly="true"
																			placeholder='Email Id' path="vendor.emailId" />
																	</div>
																	<div class="col-sm-4 form-group">
																		<label>Contact</label>

																		<form:select path="vendorContactDetails.id"
																			id="vendorContactDetails" cssClass="form-control"
																			oninvalid="this.setCustomValidity('Please Select State ')"
																			oninput="setCustomValidity('')">
																			<form:option value="">Select</form:option>
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
																			<form:option value="">Select</form:option>
																		</form:select>
																	</div>

																	<div class="col-sm-4 form-group rm_address">
																		<label>Ship From</label>
																		<form:select path="vendorShippingAddress.id"
																			id="vendorAddress" cssClass="form-control"
																			required="true">
																			<form:option value="">Select</form:option>
																		</form:select>
																	</div>

																	<div class="col-sm-4 form-group">
																		<label>Doc Number</label>
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
																					<label>Ref Doc No.</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Reference Document Number'
																						path="referenceDocNumber" />
																				</div>
																				<div class="col-sm-4 form-group">
																					<label>Posting Date</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Posting Date' path="postingDate"
																						autocomplete="off" required="true" />

																				</div>
																				<div class="col-sm-4 form-group">
																					<label>Doc Date</label>
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
																						autocomplete="off" path="requiredDate"
																						required="true" />

																				</div>
																				<div class="col-sm-4 form-group">&nbsp;</div>


																				<div class="row" id="radioDiv">
																					<div class="card-block" style="clear: both;">
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
																				</div>


																				<div class="card-block"
																					style="clear: both; padding: 0px 1.5rem;">
																					<div class="row" id="gr_radioDiv"
																						style="display: none">
																						<div class="col-sm-6 form-group has-feedback">
																							<label>Type</label>: <%-- ${gr.category} --%> Product
																						</div>
																					</div>
																				</div>


																			</div>
																		</div>
																	</div>

																	<div class="row"></div>

																	<input type="hidden" id="addressCount" value="0">


																	<ul class="nav nav-tabs" id="containerContainingTabs"
																		role="tablist">
																		<li class="nav-item"><a class="nav-link active"
																			id="home-tab" data-toggle="tab" href="#home"
																			role="tab" aria-controls="home" aria-selected="true">Item
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
																								<table
																									class="table table-bordered table-striped item-service-table"
																									id="itemTbl">
																									<thead>
																										<tr>
																											<!-- <th>S.No</th> -->
																											<th style="display: none;">Product Id</th>
																											<th>Product#</th>
																											<th>Description</th>
																											<th>UOM</th>
																											<th>SKU</th>
																											<th>Unit Price</th>
																											<th>Tax %</th>
																											<th>Tax Total</th>
																											<th>Total</th>
																											<th>Group</th>
																											<th>HSN</th>
																											<th>Warehouse</th>
																											<th>Quantity</th>
																											<th>Action</th>
																										</tr>
																									</thead>
																									<tbody>

																									</tbody>
																								</table>

																								<table
																									class="table table-bordered table-striped item-service-table"
																									id="serviceTbl">
																									<thead>
																										<tr>
																											<th style="display: none;">Product Id</th>
																											<th>SAC Code</th>
																											<th>Description</th>
																											<th>Quantity</th>
																											<th>Unit Price</th>
																											<th>Tax Code</th>
																											<th>Tax Total</th>
																											<th>Total</th>
																											<th>Warehouse</th>
																											<th>Action</th>
																										</tr>
																									</thead>
																									<tbody>

																									</tbody>
																								</table>





																								<!--1 multiply Dynamically Load   -->
																								<c:if test="${not empty goodsReceiptLineItems}">
																									<form:hidden path="poId" />
																									<table
																										class="table table-bordered table-striped item-service-table"
																										id="edit_item_serviceTbl">

																										<thead>
																											<tr>
																												<!-- <th>S.No</th> -->
																												<th style="display: none;">Product Id</th>
																												<c:if test="${gr.category=='Item'}">
																													<th>Product#</th>
																													<th>Description</th>
																													<th>UOM</th>
																													<th>SKU</th>
																													<th>Unit Price</th>
																													<th>Tax %</th>
																													<th>Tax Total</th>
																													<th>Total</th>
																													<th>Group</th>
																													<th>HSN Code</th>
																													<th>Warehouse</th>
																													<th>Quantity</th>
																												</c:if>

																												<c:if test="${gr.category!='Item'}">
																													<th>SAC Code</th>
																													<th>Description</th>
																													<th>Quantity</th>
																													<th>Unit Price</th>
																													<th>Tax Code</th>
																													<th>Tax Total</th>
																													<th>Total</th>
																													<th>Warehouse</th>
																												</c:if>
																												<c:if test="${gr.poId==null}">
																													<th>Action</th>
																												</c:if>

																											</tr>
																										</thead>

																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${goodsReceiptLineItems}"
																												var="listLineItems">


																												<c:if test="${gr.poId!=null}">

																													<tr class="multTot multTot${count}">
																														<td style="display: none;"><div
																																class="form-group">
																																<form:input type="hidden"
																																	path="goodsReceiptLineItems[${count}].productId"
																																	value="${listLineItems.productId}"
																																	class="form-control productId"></form:input>
																															</div> <form:hidden
																																path="goodsReceiptLineItems[${count}].id" />
																															<div class="form-group"></div></td>



																														<c:if test="${gr.category=='Item'}">
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].prodouctNumber"
																																		readonly="true"
																																		value="${listLineItems.prodouctNumber}"
																																		class="form-control prodouctNumber"></form:input>
																																</div></td>

																															<td>
																																<div class="form-group">
																																	<form:input type="text"  
																																		path="goodsReceiptLineItems[${count}].description"
																																		onkeypress="return isNumericKey(event)"
																																		value="${listLineItems.description}"
																																		class="form-control description validatePrice"></form:input>
																																</div>
																															</td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].uom"
																																		value="${listLineItems.uom}"
																																		class="form-control uom"
																																		readonly="true"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].sku"
																																		value="${listLineItems.uom}"
																																		class="form-control sku"
																																		readonly="true"></form:input>
																																</div></td>



																															<td><div class="form-group">
																																	<form:input type="text" readonly="true"
																																		path="goodsReceiptLineItems[${count}].unitPrice"
																																		onkeypress="return isNumericKey(event)"
																																		value="${listLineItems.unitPrice}"
																																		class="form-control unitPrice validatePrice"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:select
																																		class="form-control taxCode"
																																		style="width:;" readonly="true"
																																		path="goodsReceiptLineItems[${count}].taxCode">
																																		<form:option value="" label="Select" />
																																		<form:options items="${taxCodeMap}" />
																																	</form:select>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].taxTotal"
																																		value="${listLineItems.taxTotal}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control taxTotal "
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].total"
																																		value="${listLineItems.total}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control total"
																																		readonly="true"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].productGroup"
																																		value="${listLineItems.productGroup}"
																																		class="form-control productGroup"
																																		readonly="true"></form:input>
																																</div></td>
																															
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].hsn"
																																		value="${listLineItems.hsn}"
																																		class="form-control hsnVal"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:select class="form-control"
																																		style="width:;" readonly="true"
																																		path="goodsReceiptLineItems[${count}].warehouse">
																																		<form:option value="" label="Select" />
																																		<form:options items="${plantMap}" />
																																	</form:select>
																																</div></td>
																																
																															<td><img
																																src="${contextPath}/resources/images/portrait/info.png"
																																alt="See" id="output" width="15"
																																height="15"
																																style="float: left; margin-right: 10px;"
																																data-toggle="tooltip"
																																data-placement="top"
																																title="Pending Quantity   ${listLineItems.tempRequiredQuantity}" />
																																<c:choose>
																																	<c:when
																																		test="${listLineItems.requiredQuantity <= 0 && listLineItems.tempRequiredQuantity <= 0}">
																																		<div class="form-group">
																																			<form:input type="text"
																																				path="goodsReceiptLineItems[${count}].requiredQuantity"
																																				value="${listLineItems.requiredQuantity}"
																																				onkeypress="return isNumericKey(event)"
																																				class="form-control requiredQuantity validateQuantity"
																																				autocomplete="off" readonly="true"></form:input>
																																		</div>

																																	</c:when>
																																	<c:otherwise>
																																		<div class="form-group">
																																			<form:input type="text"
																																				path="goodsReceiptLineItems[${count}].requiredQuantity"
																																				onkeypress="return isNumericKey(event)"
																																				class="form-control requiredQuantity validateQuantity"
																																				autocomplete="off" required="true"></form:input>
																																		</div>

																																	</c:otherwise>
																																</c:choose> <input type="hidden"
																																value="${listLineItems.requiredQuantity}"
																																class="original_requiredQuantity">
																																<input type="hidden"
																																value="${listLineItems.tempRequiredQuantity}"
																																class="temp_requiredQuantity">

																															</td>

																															

																														</c:if>

																														<c:if test="${gr.category!='Item'}">


																															<td>
																																<div class="form-group">
																																	<form:input type="text" readonly="true"
																																		path="goodsReceiptLineItems[${count}].sacCode"
																																		onkeypress="return isNumericKey(event)"
																																		value="${listLineItems.sacCode}"
																																		class="form-control sacCode validatePrice"></form:input>
																																</div>
																															</td>

																															<td>
																																<div class="form-group">
																																	<form:input type="text" readonly="true"
																																		path="goodsReceiptLineItems[${count}].description"
																																		onkeypress="return isNumericKey(event)"
																																		value="${listLineItems.description}"
																																		class="form-control description validatePrice"></form:input>
																																</div>
																															</td>

																															<td><img
																																src="${contextPath}/resources/images/portrait/info.png"
																																alt="See" id="output" width="20"
																																height="20" data-toggle="tooltip"
																																data-placement="top"
																																title="Pending Quantity   ${listLineItems.tempRequiredQuantity}" />
																																<c:choose>
																																	<c:when
																																		test="${listLineItems.requiredQuantity <= 0 && listLineItems.tempRequiredQuantity <= 0}">
																																		<div class="form-group">
																																			<form:input type="text"
																																				path="goodsReceiptLineItems[${count}].requiredQuantity"
																																				value="${listLineItems.requiredQuantity}"
																																				onkeypress="return isNumericKey(event)"
																																				class="form-control requiredQuantity validateQuantity"
																																				autocomplete="off" readonly="true"></form:input>
																																		</div>

																																	</c:when>
																																	<c:otherwise>
																																		<div class="form-group">
																																			<form:input type="text"
																																				path="goodsReceiptLineItems[${count}].requiredQuantity"
																																				onkeypress="return isNumericKey(event)"
																																				class="form-control requiredQuantity validateQuantity"
																																				autocomplete="off" required="true"></form:input>
																																		</div>

																																	</c:otherwise>
																																</c:choose> <input type="hidden"
																																value="${listLineItems.requiredQuantity}"
																																class="original_requiredQuantity">
																																<input type="hidden"
																																value="${listLineItems.tempRequiredQuantity}"
																																class="temp_requiredQuantity">

																															</td>

																															<!--  -->
																															<td><div class="form-group">
																																	<form:input type="text" 
																																		path="goodsReceiptLineItems[${count}].unitPrice"
																																		value="${listLineItems.unitPrice}"
																																		autocomplete="off" readonly="true"
																																		class="form-control unitPrice"></form:input>
																																</div></td>


																															<td>
																																<div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].taxCode"
																																		value="${listLineItems.taxCode}"
																																		autocomplete="off" readonly="true"
																																		class="form-control taxCode"></form:input>
																																</div> <%--  <div class="form-group"><form:select class="form-control taxCode"
																															 readonly="true"
																															path="goodsReceiptLineItems[${count}].taxCode">
																															<form:option value="" label="Select" />
																															<form:options items="${taxCodeMap}" />
																														</form:select></div> --%>

																															</td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].taxTotal"
																																		value="${listLineItems.taxTotal}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control taxTotal validatePrice"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].total"
																																		value="${listLineItems.total}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control total validatePrice"
																																		readonly="true"></form:input>
																																</div></td>
																															<!--  -->

																															<td><div class="form-group">
																																	<form:select class="form-control"
																																		style="width:;" readonly="true"
																																		path="goodsReceiptLineItems[${count}].warehouse">
																																		<form:option value="" label="Select" />
																																		<form:options items="${plantMap}" />
																																	</form:select>
																																</div></td>

																														</c:if>

																													</tr>
																												</c:if>

																												<c:if test="${gr.poId==null}">
																													<tr class="multTot multTot${count}">
																														<td style="display: none;"><div
																																class="form-group">
																																<form:input type="hidden"
																																	path="goodsReceiptLineItems[${count}].productId"
																																	value="${listLineItems.productId}"
																																	class="form-control productId"></form:input>
																															</div> <form:hidden
																																path="goodsReceiptLineItems[${count}].id" />
																														</td>

																														<c:if test="${gr.category=='Item'}">
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].prodouctNumber"
																																		required="true"
																																		value="${listLineItems.prodouctNumber}"
																																		class="form-control prodouctNumber"></form:input>
																																</div></td>
																																
																																<td>
																																<div class="form-group">
																																	<form:input type="text"  
																																		path="goodsReceiptLineItems[${count}].description"
																																		onkeypress="return isNumericKey(event)" required="true"
																																		value="${listLineItems.description}"
																																		class="form-control description validatePrice"></form:input>
																																</div>
																															</td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].uom"
																																		value="${listLineItems.uom}"
																																		class="form-control uom"
																																		readonly="true"></form:input>
																																</div></td>
																																
																																<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].sku"
																																		value="${listLineItems.uom}"
																																		class="form-control sku"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text" readonly="true"
																																		path="goodsReceiptLineItems[${count}].unitPrice"
																																		onkeypress="return isNumericKey(event)"
																																		value="${listLineItems.unitPrice}"
																																		class="form-control unitPrice validatePrice"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:select
																																		class="form-control taxCode"
																																		style="width:;" required="true"
																																		path="goodsReceiptLineItems[${count}].taxCode">
																																		<form:option value="" label="Select" />
																																		<form:options items="${taxCodeMap}" />
																																	</form:select>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].taxTotal"
																																		value="${listLineItems.taxTotal}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control taxTotal "
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].total"
																																		value="${listLineItems.total}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control total"
																																		readonly="true"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].productGroup"
																																		value="${listLineItems.productGroup}"
																																		class="form-control productGroup"
																																		readonly="true"></form:input>
																																</div></td>
																															
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].hsn"
																																		value="${listLineItems.hsn}"
																																		class="form-control hsnVal"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:select class="form-control"
																																		style="width:;" required="true"
																																		path="goodsReceiptLineItems[${count}].warehouse">
																																		<form:option value="" label="Select" />
																																		<form:options items="${plantMap}" />
																																	</form:select>
																																</div></td>
																																
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].requiredQuantity"
																																		value="${listLineItems.requiredQuantity}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control requiredQuantity validatePrice"
																																		autocomplete="off" required="true"></form:input>
																																</div></td>

																															

																														</c:if>

																														<c:if test="${gr.category!='Item'}">
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].sacCode"
																																		value="${listLineItems.sacCode}"
																																		class="form-control sacCode"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].description"
																																		value="${listLineItems.description}"
																																		class="form-control description"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].requiredQuantity"
																																		value="${listLineItems.requiredQuantity}"
																																		required="true" autocomplete="off"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control requiredQuantity validatePrice"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].unitPrice"
																																		value="${listLineItems.unitPrice}" readonly="true"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control unitPrice validatePrice"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:select
																																		class="form-control taxCode"
																																		style="width:;" required="true"
																																		path="goodsReceiptLineItems[${count}].taxCode">
																																		<form:option value="" label="Select" />
																																		<form:options items="${taxCodeMap}" />
																																	</form:select>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].taxTotal"
																																		value="${listLineItems.taxTotal}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control taxTotal"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="goodsReceiptLineItems[${count}].total"
																																		value="${listLineItems.total}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control total"
																																		readonly="true"></form:input>
																																</div></td>



																															<td><div class="form-group">
																																	<form:select class="form-control"
																																		style="width:;" required="true"
																																		path="goodsReceiptLineItems[${count}].warehouse">
																																		<form:option value="" label="Select" />
																																		<form:options items="${plantMap}" />
																																	</form:select>
																																</div></td>

																														</c:if>
																														<td class="text-center"><a
																															onclick="removeData2(${count})"
																															class="tdicon remove confirm-delete"
																															data-toggle="modal"><i
																																class="icon-bin left"></i></a></td>

																													</tr>

																												</c:if>

																												<c:set var="count" value="${count + 1}"
																													scope="page" />
																											</c:forEach>


																										</tbody>
																									</table>
																									<input type="hidden" id="edit_addressCount"
																										value="${count-1}">
																								</c:if>


																							</div>
																						</div>
																						<c:if test="${gr.poId==null}">
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


																		<!--Calculation Part  -->

																		<div class="row">
																			<div class="col-sm-4">
																				<div class="create-block">
																					<div class="form-group">
																						<label>Remark</label>
																						<form:textarea type="text" cssClass="form-control camelCase"
																							placeholder='Enter your Remark'
																							autocomplete="off" path="remark" />
																					</div>
																				</div>
																			</div>
																			<div class="col-sm-4">&nbsp;</div>

																			<div class="col-sm-4">
																				<div class="form-group">
																					<label>Discount(%) :</label>
																			<c:choose>
																			<c:when test="${gr.poId!=null}">
																					<form:input type="text"
																						cssClass="form-control validatePrice"
																						id="totalDiscount" placeholder='Total  DisCount '
																						path="totalDiscount" autocomplete="off"
																						readonly="true"
																						onkeypress="return isNumericKey1(event)" />
																			</c:when>
																			<c:otherwise>
																			        <form:input type="text"
																						cssClass="form-control validatePrice"
																						id="totalDiscount" placeholder='Total  DisCount '
																						path="totalDiscount" autocomplete="off"
																						onkeypress="return isNumericKey1(event)" />
																			</c:otherwise>
																			</c:choose>	


																				</div>

																				<div class="form-group">
																					<label>Total Before Discount : </label>

																					<form:input type="text" cssClass="form-control"
																						placeholder='Total Before Dis '
																						path="totalBeforeDisAmt" autocomplete="off"
																						readonly="true" />


																				</div>
																				
																				
																				
																					<div class="form-group">
																					<label>Freight : </label>
																			<c:choose>
																			<c:when test="${gr.poId!=null}">
																					<form:input type="text"
																						cssClass="form-control validatePrice"
																						placeholder='Freight' path="freight"
																						onkeypress="return isNumericKey(event)"
																						autocomplete="off" readonly="true" />
																			</c:when>
																			<c:otherwise>
																			         <form:input type="text"
																						cssClass="form-control validatePrice"
																						placeholder='Freight' path="freight"
																						onkeypress="return isNumericKey(event)"
																						autocomplete="off" />
																			</c:otherwise>
																			</c:choose>
																				   </div>													
																		      
																																
																		  
																		    
																				

																				<div class="form-group">
																					<label>Rounding : </label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Rounding' path="amtRounding"
																						autocomplete="off" readonly="true" />
																				</div>

																				<div class="form-group">
																					<label>Tax Amount :</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Tax Amount' path="taxAmt"
																						autocomplete="off" readonly="true" />
																				</div>

																				<div class="form-group">
																					<label>Total Payment Due : </label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Total Payment Due'
																						path="totalPayment" autocomplete="off"
																						readonly="true" />
																				</div>
																			</div>
																		</div>







																		<!--Calculation Part  -->

																		<div class="text-xs-center">

																			<a href="<c:url value="/gr/list"/>"
																				class="btn btn-primary float-left"> Back </a>


																			<c:if test="${gr.status eq 'Draft' || gr.id==null }">
																				<form:button type="submit" id="draft"
																					name="statusType" value="DR" class="btn btn-draft">
																					<i class="icon-check2"></i> Draft</form:button>
																			</c:if>
																			<c:if test="${gr.id==null}">
																				<form:button type="submit" id="save"
																					name="statusType" value="SA"
																					class="btn btn-primary">
																					<i class="icon-check2"></i>Save</form:button>
																			</c:if>
																			<c:if test="${gr.id!=null}">
																				<form:button type="submit" id="update"
																					name="statusType" value="SA"
																					class="btn btn-primary ">
																					<i class="icon-check2"></i> Update</form:button>
																				<%--  <a href="<c:url value="/gr/cancelStage?id=${gr.id}"/>">
																			<button type="button" class="btn btn-warning mr-1">
																				<i class="icon-cross2"></i> Cancel
																			</button>
																		</a> --%>

																			</c:if>
																			<!-- Approve -->
																			<c:forEach items="${sessionScope.umpmap}" var="ump">
																				<c:if test="${ump.key eq 'Goods Receipt'}">
																					<c:set var="permissions" scope="session"
																						value="${ump.value}" />
																					<c:if
																						test="${fn:containsIgnoreCase(permissions,'Approve')}">
																						<form:button type="submit" id="approve"
																							name="statusType" value="APP"
																							class="btn btn-approve ">
																							<i class="icon-check2"></i>Approve</form:button>
																					</c:if>
																				</c:if>
																			</c:forEach>
																			<!-- Reject -->
																			<c:forEach items="${sessionScope.umpmap}" var="ump">
																				<c:if test="${ump.key eq 'Goods Receipt'}">
																					<c:set var="permissions" scope="session"
																						value="${ump.value}" />
																					<c:if
																						test="${fn:containsIgnoreCase(permissions,'Reject')}">
																						<c:if test="${gr.status != 'Cancelled'}">
																							<form:button type="submit" id="reject"
																								name="statusType" value="RE"
																								class="btn btn-reject ">
																								<i class="icon-cross2"></i>Reject</form:button>
																						</c:if>
																					</c:if>
																				</c:if>
																			</c:forEach>


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


var inc=0;
var edit_addressCount=0;

var poId=$('#poId').val();
if(poId!='' && poId != undefined) {
	 $("#gr_radioDiv").show();
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
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].prodouctNumber" autocomplete="off"  class="form-control prodouctNumber prodouctNumber'+inc+'" required="true" id="prodouctNumber'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td style="display:none;">'
			+'<div class="form-group">'
			+'<input type="hidden" name="goodsReceiptLineItems['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].description" required="true" autocomplete="off"  class="form-control description '+inc+'" id="uom'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].uom" class="form-control uom uom'+inc+'" id="uom'+inc+'"  readonly="true"  />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].sku" class="form-control sku sku'+inc+'" id="uom'+inc+'"  readonly="true"  />'
			+ '</div>'
			+'</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].unitPrice" autocomplete="off" onkeypress="return isNumericKey(event)" readonly="true" required="true" class="form-control validatePrice unitPrice'+inc+' unitPrice" id="unitPrice'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="goodsReceiptLineItems['+inc+'].taxCode" required="true"   class="form-control  taxCode"  id="taxCode'+inc+'" >'
			+'<option value="">Select</option>'+
			<c:forEach items="${taxCodeMap}" var="taxCodeMap">
			'<option value="${taxCodeMap.key}">${taxCodeMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].taxTotal" onkeypress="return isNumericKey(event)"  readonly="true" class="form-control  taxTotal'+inc+' taxTotal" id="taxTotal'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].total" onkeypress="return isNumericKey(event)"  readonly="true" class="form-control total'+inc+' total" id="total'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].productGroup" readonly="true" class="form-control  productGroup productGroup'+inc+'" id="productGroup'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].hsn" readonly="true" class="form-control hsnVal hsn'+inc+'" id="hsn'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="goodsReceiptLineItems['+inc+'].warehouse" required="true"   class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
			+'<option value="">select</option>'+
			<c:forEach items="${plantMap}" var="plantMap">
			'<option value="${plantMap.key}">${plantMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].requiredQuantity" autocomplete="off" onkeypress="return isNumericKey(event)"  required="true" class="form-control validatePrice requiredQuantity'+inc+' requiredQuantity" id="requiredQuantity'+inc+'"   />'
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
			+'<input type="hidden" name="goodsReceiptLineItems['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].sacCode" autocomplete="off" required="true"  class="form-control sacCode  sacCode'+inc+'" id="hsn'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].description" readonly="true" class="form-control description '+inc+'" id="description'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].requiredQuantity" autocomplete="off" required="true" onkeypress="return isNumericKey(event)"  class="form-control validatePrice requiredQuantity'+inc+' requiredQuantity" id="requiredQuantity'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].unitPrice" autocomplete="off"  onkeypress="return isNumericKey(event)"  required="true" class="form-control validatePrice unitPrice  unitPrice'+inc+'" id="unitPrice'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="goodsReceiptLineItems['+inc+'].taxCode" required="true"   class="form-control  taxCode"  id="taxCode'+inc+'" >'
			+'<option value="">Select</option>'+
			<c:forEach items="${taxCodeMap}" var="taxCodeMap">
			'<option value="${taxCodeMap.key}">${taxCodeMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].taxTotal" onkeypress="return isNumericKey(event)"  readonly="true" class="form-control  taxTotal'+inc+' taxTotal" id="taxTotal'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="goodsReceiptLineItems['+inc+'].total" onkeypress="return isNumericKey(event)"  readonly="true" class="form-control total'+inc+' total" id="total'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="goodsReceiptLineItems['+inc+'].warehouse" required="true"   class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
			+'<option value="">select</option>'+
			<c:forEach items="${plantMap}" var="plantMap">
			'<option value="${plantMap.key}">${plantMap.value}</option>'+
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
		$(document).on("focus", ".vendorname", function() {
			$(this).autocomplete({
		        source: availableTagsvendornames,
		        minLength: 0,
	            scroll: true,
		        select: function(event, ui) {
		        	var vendorname = ui.item.value;
		        	//alert(vendorname);
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
	$(document).on("focus", ".prodouctNumber", function() {
		var itemParentRow = $(this).parents(".multTot");
		//alert("itemParentRow"+itemParentRow);
		
		$(this).autocomplete({
	        source: availableTags,
	        minLength: 0,
            scroll: true,
	        select: function(event, ui) {
	        	name = ui.item.value;
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
		        	   alertify.alert("Purchase Order","You have already entered the Product Number "+$(this).val());
		        	/*  $(this).val('') */
		          ($(this).parents('tr').find('td').find('input').val(''));
		        	 ($(this).parents('tr').find('td').find('select').val('')); 
		        
		        }
        }else {
        	if($(this).val()!=""){
       
        	 alertify.alert("Purchase Order",$(this).val() +  "Product Number Does Not Exists!");  
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
             	var unitPrice=obj.productCost;
               	 $(itemParentRow).find(".unitPrice").val(unitPrice);
                	
             	 //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
             	 //	var productgroup=obj.productCategory.categoryType;
             
             		var productgroup=obj.productGroup.productName;
             		
             	 	//$('.productGroup').val(productgroup);
             	 	
             	 	 $(itemParentRow).find(".productGroup").val(productgroup);
             	 	 
             	 	 
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
                     var unitPrice=obj.productCost;
                   	 $(itemParentRow).find(".unitPrice").val(unitPrice);
                     //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
                   //  var productgroup = obj.productCategory.categoryType;
                     var productgroup=obj.productGroup.productName;
                     //$('.productGroup').val(productgroup);

                     $(itemParentRow).find(".productGroup").val(productgroup);
       
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
                                  		        	 
                                  		        	   alertify.alert("Purchase Order",  "You have already entered the SAC Code "+$(this).val());
                                  		        	 $(this).val('')
                                  		        	 ($(this).parents('tr').find('td').find('input').val(''));
                                  		        	 ($(this).parents('tr').find('td').find('select').val(''));
                                  		        }
                                  		        
                                              }else {
                                            	  alertify.alert("Purchase Order",$(this).val() +" SAC Code Does Not Exists ");
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
	setCalculationAmt(index);
	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	}else{
		$('table#itemTbl tr.multTot'+index).remove();
	}
	$("#form").validator("update");
}


function removeData1(index){
	//alert("ff"+index);
	setCalculationAmt(index);
	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	}else{
		$('table#serviceTbl tr.multTot'+index).remove();
	}
	$("#form").validator("update");
}


function removeData2(index){
	//alert("ff"+index);
	setCalculationAmt(index);
	$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	$("#form").validator("update");
}


</script>


<script type="text/javascript">


$("#items_radio").click(function() {
	//alert("item");
	 alertify.confirm("Goods Receipt",'Are you Sure Want to Change  Item ,Service will be removed ', function(){
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
		  
		  $('#totalBeforeDisAmt').val("");
		  $('#freight').val("");
		  $('#amtRounding').val("");
		  $('#taxAmt').val("");
		  $('#totalPayment').val("");
		  $('#totalDiscount').val("");
		  
		  addItem();
		 
		 
	  			 
	          }, function(){
	        	 
	        	$("#service_radio").prop('checked',true); 
	      alertify.error('Cancelled');
	   });
	
});

$("#service_radio").click(function() {
	//alert("service");
	 alertify.confirm("Goods Receipt",'Are you Sure Want to Change Service ,Items will be removed! ', function(){
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
	  
	  
	  $('#totalBeforeDisAmt').val("");
	  $('#freight').val("");
	  $('#amtRounding').val("");
	  $('#taxAmt').val("");
	  $('#totalPayment').val("");
	  $('#totalDiscount').val("");
	  
	  
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
	
	
$('form.commentForm').on('submit', function(event) {
    
    if ($('#items_radio').is(":checked") == true) {
    var rowCount = $('#itemTbl tr').length-1;
    
    if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
    	rowCount = $('#edit_item_serviceTbl tr').length-1;
		}
    
	if(rowCount == 0){
		alertify.alert("Goods Receipt","Please Select Atleast One Item");
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
 		alertify.alert("Goods Receipt","Please Select Atleast One  Service");
 		 return false;
 	}else{
 		return true;
 	}
	  }
    
});

function isNumericKey(evt)
{
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ( charCode > 31 
	&& (charCode < 48 || charCode > 57))
	return false;
	return true;
} 

function isNumericKey1(evt)
{
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ( charCode > 31 
			&& (charCode < 45 || charCode > 57))
	return false;
	return true;
}

function goBack() {
    window.history.back();
}
	
	
	


$(document).on("change", ".taxCode", function() {

	var itemParentRow = $(this).parents(".multTot");
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	var tax=  $(itemParentRow).find(".taxCode option:selected").text();
//	alert("tax--->" +tax);
	var tax_amt = getDiscount(tax);
	var totalValue = getCalculateAmt(requiredQuantity,unitPrice,tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
	
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(totalValue.toFixed(2));
	
	 var sum_total = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		sum_total += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	 
 	if(!isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
  	 $("#amtRounding").val(Math.round(sum_total));
  	 $("#totalPayment").val(Math.round(sum_total));
  	 $("#totalDiscount").val("");
  	 $("#freight").val("");
 	}
  	 
	});
	
	
	

$(document).on("keyup", ".unitPrice", function() {
	 var itemParentRow = $(this).parents(".multTot");
	
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	var tax=  $(itemParentRow).find(".taxCode option:selected").text();
//	alert("tax--->" +tax);
	var tax_amt = Number(tax) / 100;
	var totalValue = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
		if( tax !="Select") {
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(totalValue.toFixed(2));
		}
	
	
	 var sum_total = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		sum_total += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	if( tax !="Select"  && !isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
  	 $("#amtRounding").val(Math.round(sum_total));
  	 $("#totalPayment").val(Math.round(sum_total));
  	 $("#totalDiscount").val("");
  	 $("#freight").val("");
    	}
	});
	

$(document).on("keyup", ".requiredQuantity", function() {
	
	var itemParentRow = $(this).parents(".multTot");
	 
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	//alert("requiredQuantity" +requiredQuantity);
	var tax=  $(itemParentRow).find(".taxCode option:selected").text();
	if(tax=='') {
        tax=  $(itemParentRow).find(".taxCode").val();
   }
	//alert("tax--->" +tax);
	var tax_amt = Number(tax) / 100;
	var totalValue = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
		if( tax !="Select") {
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(totalValue.toFixed(2));
		}
	
	
	 var sum_total = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		sum_total += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	 
  	//alert("tax" +tax);
	///alert("sum_total" +sum_total);
	
  	if( tax !="Select"  && !isNaN(sum_total)) {
  		var totalAmt = sum_total;
  		var discount_val = $("#totalDiscount").val();
  		
  		if(discount_val<100){
  		var discount = Number(discount_val) / 100;
  		var totalPayment = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
  	    
  		if( $("#freight").val()!="") {
  			totalPayment += parseFloat($("#freight").val());
  		}
  		if(totalAmt!="") {
  		 $("#totalPayment").val(Math.round(totalPayment));
  		 $("#amtRounding").val( Math.round(totalPayment));
  		}
  		
  		}
  		
  	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
  	
  	
    	}
	});

	
	
	$('#totalDiscount').keyup(function() {
	var totalAmt = $("#totalBeforeDisAmt").val();
	var discount_val = $("#totalDiscount").val();
	
	if(discount_val<100){
	var discount = Number(discount_val) / 100;
	var totalPayment = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
    
	if( $("#freight").val()!="") {
		totalPayment += parseFloat($("#freight").val());
	}
	if(totalAmt!="") {
	 $("#totalPayment").val(Math.round(totalPayment));
	 $("#amtRounding").val( Math.round(totalPayment));
	}
	
	}else {
		 $("#totalDiscount").val("");
		alertify.alert("Goods Receipt Discount","Please Enter Valid Discount!");
		 return false;
	}
	
	});
	
	
	function getDiscount(discount_val){
		var discount = Number(discount_val) / 100;
		return discount;
	}
	
	function getCalculateAmt(requiredQuantity,unitPrice,tax_amt){
		var amt = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
		return amt;
	}



$('#freight').keyup(function() {
	
	var totalAmt = $("#totalBeforeDisAmt").val();
	var discount_val = $("#totalDiscount").val();
	var freight = $(this).val();
	
//alert("discount_val" +discount_val);
//alert("freight" +freight);
//alert("totalAmt" +totalAmt);
	if( discount_val !="" && $("#totalDiscount").val()!="") {
		var discount = Number(discount_val) / 100;
		totalAmt = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
	}
	
	if(totalAmt!="") {
	var finalValue =  Number(totalAmt) + Number(freight);
	 $("#totalPayment").val(Math.round(finalValue));
	 $("#amtRounding").val( Math.round(finalValue));
	}
});
	
	function setCalculationAmt(index){
			 var sum_total = 0;
			 var sum_tax_total = 0;
		  	 $(".total").each(function(row) {
		  		///alert("ttt-->" +isNaN($(this).val()));
		  		 if(row!=index) {
		  		 sum_total += parseFloat((isNaN($(this).val())) ? 0 : $(this).val());
		  		             }
				    });
		  	 
		  	 $(".taxTotal").each(function(row) {
		  		if(row!=index) {
		  		 sum_tax_total += parseFloat((isNaN($(this).val())) ? 0 : $(this).val());
		  	                }
		 		    });
		  	 //alert(sum_total);
		  	// alert(sum_tax_total);
		  	 
		  	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
		  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
		  	 
		var discount_val = $("#totalDiscount").val();
		var discount = Number(discount_val) / 100;
		var totalPayment = parseFloat(sum_total) - parseFloat(sum_total * discount);
	    
		if( $("#freight").val()!="") {
			totalPayment += parseFloat($("#freight").val());
		}
		if(sum_total!="") {
		 $("#totalPayment").val(Math.round(totalPayment));
		 $("#amtRounding").val( Math.round(totalPayment));
		}
	
	}
	
	$(document).on("keypress", ".validatePrice", function(e) {	
		if (this.value.length == 0 && e.which == 48 ){
			      return false;
			   }
		});
	
	$(document).on("keyup", ".validateQuantity", function(e) {	
		if (this.value.length == 0 && e.which == 48 ){
			      return false;
			   }
		
		var itemParentRow = $(this).parents(".multTot");
		 
		var original_requiredQuantity=  $(itemParentRow).find(".original_requiredQuantity").val();
		
		var change_requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
		
		var temp_requiredQuantity=  $(itemParentRow).find(".temp_requiredQuantity").val();
		
	    var remain_requiredQuantity = change_requiredQuantity - original_requiredQuantity;
		
		
		if(temp_requiredQuantity<remain_requiredQuantity){
			alertify.alert("Goods Receipt","Avaliable "+temp_requiredQuantity + ". Cannot Exceed more than the required quantity!");	
			 ($(this).parents('tr').find('td').find('.requiredQuantity').val(original_requiredQuantity));
			 return false;
		}
		
		});
	
	
	/*  $(".requiredQuantity").each(function() {
		 var itemParentRow = $(this).parents(".multTot");
		 var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
		 var temp_requiredQuantity=  $(itemParentRow).find(".temp_requiredQuantity").val();
		 if(temp_requiredQuantity<=0){
	        	 ($(this).parents('tr').find('td').find('.requiredQuantity').attr('readonly', true));
	        	 ($(this).parents('tr').find('td').find('.temp_requiredQuantity').attr('readonly', true));
			}
	 }); */
	
	
	
	</script>

<%-- <c:import url="/WEB-INF/jsp/loadJs.jsp" />  --%>

<!-- alertifyjs -->

<link
	href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
	rel="stylesheet" type="text/css" />
<script
	src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/>
	type="text/javascript"></script>

<link
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css"
	rel="Stylesheet"></link>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

</html>
