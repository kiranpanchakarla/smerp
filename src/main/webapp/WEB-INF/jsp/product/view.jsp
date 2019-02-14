<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>

<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
<style>
.table thead tr th {
	text-align: center !important;
}
</style>

</head>

<script
	src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-item').click(function() {
			$(this).parent('ul').find('li').removeClass('active');
			$(this).parent('ul').find('li').find('a').removeClass('active');
			$(this).find('a').addClass('active');
		});
	});
</script>

<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-body">
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">
							<!-- Basic form layout section start -->
							<c:url value="/product/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}"
								class="commentForm" modelAttribute="product"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h2 class="card-title" id="basic-layout-icons">Product
														View</h2>
												</div>
												<form:hidden path="id" />
												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Product No.</label>: ${product.productNo}

																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Description</label>: ${product.description}
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Product Group</label>:
																		${product.productCategory.name}
																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>UOM Group</label>:
																		${product.uomCategory.uomCategoryName}
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-12 form-group">
																		<div class="inventory-list">
																			<form:checkbox onclick="return false;"
																				path="inventoryProduct" id="inventoryProduct"
																				class="purchaseType" value="inventoryProduct"
																				required="true" />
																			<span class="radio-list">Inventory Product</span>
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>

																		<div class="inventory-list">
																			<form:checkbox onclick="return false;"
																				path="purchaseProduct" id="purchaseProduct"
																				class="purchaseType" value="purchaseProduct"
																				required="true" />
																			<span class="radio-list">Purchase Product</span>
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	</div>
																</div>

																<ul class="nav nav-tabs" id="myTab" role="tablist">
																	<li class="nav-item active"><a class="nav-link"
																		id="home-tab" data-toggle="tab" href="#home"
																		role="tab" aria-controls="home" aria-selected="true">General</a>
																	</li>
																	<li class="nav-item"><a class="nav-link"
																		id="profile-tab" data-toggle="tab" href="#profile"
																		role="tab" aria-controls="profile"
																		aria-selected="false">Purchasing</a></li>
																	<li class="nav-item"><a class="nav-link"
																		id="messages-tab" data-toggle="tab" href="#messages"
																		role="tab" aria-controls="messages"
																		aria-selected="false">Inventory</a></li>
																</ul>
																<div class="tab-content">
																	<div class="tab-pane active" id="home" role="tabpanel"
																		aria-labelledby="home-tab">
																		<div class="row">
																			<div class="col-sm-6 form-group has-feedback">
																				<form:checkbox path="withOldTaxLiable"
																					onclick="return false;" value="withOldTaxLiable"
																					required="true" />
																				<span class="radio-list">Withholding Tax
																					Liable</span>
																			</div>
																		</div>

																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<div class="input-group">
																					<!--  <label class="display-inline-block custom-control custom-radio ml-1" style="padding: 0px"> -->
																					<div class="inventory-list">
																						<form:radiobutton class="product-category"
																							onclick="return false;" name="productcategory"
																							path="serviceOrProduct" required="true"
																							value="service" />
																						<span class="radio-list">Service</span>
																					</div>
																					<div class="inventory-list">
																						<form:radiobutton class="product-category"
																							onclick="return false;" name="productcategory"
																							path="serviceOrProduct" required="true"
																							value="product" />
																						<span class="radio-list">Product</span>
																					</div>
																					<!-- <div  class="help-block with-errors"></div> -->
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<div class="inventory-list">
																					<form:checkbox path="gst" value="gst"
																						id="gstcategory" required="true" />
																					<span class="radio-list">GST</span>
																				</div>
																			</div>
																		</div>
																		<c:if test="${product.serviceOrProduct eq 'service'}">
																			<div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Service Type</label>: ${product.serviceType}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>SAC</label>: ${product.sacCode.id}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Tax Category</label>: ${product.taxCategory}
																				</div>
																			</div>
																		</c:if>
																		<c:if test="${product.serviceOrProduct eq 'product'}">
																			<div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Product Type</label>: ${product.productType}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>HSN</label>: ${product.hsnCode.id}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>TaxCategory</label>:
																					${product.productTaxCategory}
																				</div>

																			</div>
																		</c:if>

																		<p class="card-title">Serial and Batch Numbers</p>
																		<div class="row">
																			<div class="col-sm-6 form-group has-feedback">
																				<label>Manage Product By</label>:
																				${product.manageProductBy}
																			</div>
																		</div>
																	</div>

																	<div class="tab-pane " id="profile" role="tabpanel"
																		aria-labelledby="profile-tab">

																		<div class="row">
																			<div class="col-sm-6 form-group has-feedback">
																				<label>Preferred Vendor</label>:
																				${product.preferredVendor}
																			</div>
																			<div class="col-sm-6 form-group has-feedback">
																				<label>Purchasing UOM</label>:
																				${product.purchasingUom.uomName}
																			</div>
																			
																		</div>
																		<div class="row">
																			<div class="col-sm-6 form-group has-feedback">
																				<label>SKU</label>:
																				${product.packingUom.uomName}
																			</div>
																			<div class="col-sm-6 form-group has-feedback">
																				<label>SKU Quantity  </label>:
																				${product.qualityPerPackage}
																			</div>
																		</div>

																		<!--  -->

																	</div>

																	<div class="tab-pane" id="messages" role="tabpanel"
																		aria-labelledby="messages-tab">
																		<div class="row">
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Quantity Per Package</label>:
																				${product.inventoryUom.id}
																			</div>
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Minimum</label>: ${product.minimun}
																			</div>
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Maximum</label>: ${product.maximim}
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Valuation Method</label>:
																				${product.valuationMethod}
																			</div>
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Product Cost:</label>: ${product.productCost}
																			</div>
																		</div>

																		<c:if test="${product.id!=null}">
																			<div class="table-responsive" style="width: 60%;">
																				<div class="full-col-table">
																					<table class="table table-bordered "
																						id="edit_item_serviceTbl">

																						<tr>
																							<th>S.no</th>
																							<th>Warehouse Name</th>
																							<th>In Stock</th>
																							<th>Ordered</th>
																							<!-- <th>In Stock</th>-->
																						    <th>Available</th> 

																						</tr>
																						<c:forEach items="${productQuantity}"
																							var="product">
																							<tbody>
																								<td><c:set var="index" value="${index + 1}"
																										scope="page" /> <c:out value="${index}" /></td>
																								<td>${product.warehouse}</td>
																								<td>${product.inStock }</td>
																								<td> ${product.ordered} </td>
																					        	<td>${product.avaliableQuantity} </td> 

																							</tbody>
																						</c:forEach>
																					</table>
																				</div>
																			</div>

																		</c:if>
																	</div>



																</div>
																<!--  -->

															</div>

														</form>

														<div>
															<a href="#" onClick="goBack()" class="btn btn-primary">Back
															</a>
														</div>

													</div>
												</div>
											</div>
										</div>
									</div>

								</section>
							</form:form>
						</div>
						<br> <br>
					</div>
				</div>
				<!--/ project charts -->
				<br>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/jsp/footer.jsp" />
	<script type="text/javascript">
		function goBack() {
			window.history.back();
		}
	</script>
</body>

</html>