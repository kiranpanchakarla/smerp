
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />


</head>
<style>
.table th, .table td {
	text-align: left;
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



							<c:url value="/po/saveRFQtoPO" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form"
								class="bv-form commentForm" enctype="multipart/form-data"
								modelAttribute="rfq" data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">
												 <h2 class="card-title" id="basic-layout-icons">Request For Quotation</h2> 
													
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>: ${rfq.vendor.name}

																</div>
																<div class="col-sm-4 form-group">
																	<label>Email Id</label>: ${rfq.vendor.emailId}

																</div>
																<div class="col-sm-4 form-group">
																	<label>Contact</label>:
																	${rfq.vendorContactDetails.contactName}
																</div>
															</div>

															<form:hidden path="id" />

															<div class="row">


																<div class="col-sm-4 form-group">
																	<label>Pay To</label>: ${rfq.vendorPayTypeAddress.city}
																</div>

																<div class="col-sm-4 form-group">
																	<label>Ship From</label>:
																	${rfq.vendorShippingAddress.city}
																</div>
																<div class="col-sm-4 form-group">
																				<label>Posting Date</label>:
																				<fmt:formatDate pattern="dd/MM/yyyy"
																					value="${rfq.postingDate}" />
																			</div>

																

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		<div class="row">
																		<div class="col-sm-4 form-group">
																	<label>RFQ Doc#</label>: ${rfq.docNumber}
																</div>
																			<div class="col-sm-4 form-group">
																				<label>PR Doc#</label>:
																				${rfq.referenceDocNumber}
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>Doc Date</label>:
																				<fmt:formatDate pattern="dd/MM/yyyy"
																					value="${rfq.documentDate}" />
																			</div>
																		</div>




																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>:
																				<fmt:formatDate pattern="dd/MM/yyyy"
																					value="${rfq.requiredDate}" />
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Type</label>: Product
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Status</label>: ${rfq.status}
																			</div>
																		</div>


																	</div>
																</div>
															</div>

															<div class="row"></div>



															<ul class="nav nav-tabs" id="containerContainingTabs"
																role="tablist">
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






																						<!--1 multiply Dynamically Load   -->
																						<c:if test="${not empty lineItems}">
																							<table class="table table-bordered table-striped item-service-table"
																								id="edit_item_serviceTbl">

																								<thead>
																									<tr>
																										
																										<th style="display: none;">Product Id</th>
																										<c:if test="${rfq.category=='Item'}">
																											<th>S.no</th> 
																											<th>Product Name</th>
																											<th>Description</th>
																											<th>UOM</th>
																											<th>SKU</th>
																											<th>Quantity</th>
																											<th>Product Group</th>
																											<th>Warehouse</th>
																											<th>HSN</th>
																										</c:if>

																										<c:if test="${rfq.category!='Item'}">
																										    <th>S.No</th> 
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

																										<tr class="multTot multTot${count}">
																											<td style="display: none;"><form:input
																													type="hidden"
																													path="lineItems[${count}].productId"
																													value="${listLineItems.productId}"
																													class="form-control productId"></form:input>
																												<form:hidden path="lineItems[${count}].id" />
																											</td>
																											<td><c:set var="index" value="${index + 1}"
																								                  scope="page" /> <c:out value="${index}" /></td>

																											<c:if test="${rfq.category=='Item'}">
																												<td>${listLineItems.prodouctNumber}</td>
																												
																												<td>${listLineItems.description}</td>

																												<td>${listLineItems.uom}</td>
																												
																												<td>${listLineItems.sku}</td>

																												<td>${listLineItems.requiredQuantity}</td>

																												<td>${listLineItems.productGroup}</td>
																												<td><c:forEach var="entry"
																														items="${plantMap}">
																														<c:if
																															test="${entry.key ==listLineItems.warehouse}">
																													 ${entry.value} 																													 </c:if>
																													</c:forEach></td>
																												<td>${listLineItems.hsn}</td>

																											</c:if>

																											<c:if test="${rfq.category!='Item'}">
																												<td>${listLineItems.sacCode}</td>

																												<td>${listLineItems.description}</td>

																												<td>${listLineItems.requiredQuantity}</td>

																												<td><c:forEach var="entry"
																														items="${plantMap}">
																														<c:if
																															test="${entry.key ==listLineItems.warehouse}">
																													 ${entry.value} 																													 </c:if>
																													</c:forEach></td>
																											</c:if>
																										</tr>

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
																					<div id="shippingAddressTable">

																						${rfq.vendorShippingAddress.addressName}<br>
																						${rfq.vendorShippingAddress.street}
																						${rfq.vendorShippingAddress.city}
																						${rfq.vendorShippingAddress.zipCode}<br>
																						${rfq.vendorShippingAddress.country.name}


																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th style="vertical-align: top; !important">Pay
																					To</th>
																				<td>
																					<div id="payToAddressTable">

																						${rfq.vendorPayTypeAddress.addressName}<br>
																						${rfq.vendorPayTypeAddress.street}
																						${rfq.vendorPayTypeAddress.city}
																						${rfq.vendorPayTypeAddress.zipCode}<br>
																						${rfq.vendorPayTypeAddress.country.name}


																					</div>
																				</td>
																			</tr>
																		</thead>
																	</table>
																</div>
																
															</div>
															<div>
																	
														<div class="row">
												          <div class="col-sm-8 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												           <div class="col-sm-2 form-group has-feedback">  <c:forEach items="${sessionScope.umpmap}" var="ump">
										                           <c:if test="${ump.key eq 'RFQ'}">
										                           <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	                            <c:if test="${fn:containsIgnoreCase(permissions,'Convertion')}">
	        									                        <c:if test="${rfq.status == 'Approved'}">
																		<input type="hidden" name="rfqId" value="${rfq.id}">
																		<form:button type="button" id="convertBtn"
																			class="btn btn-primary mr-1 float-right">
																			<i></i>Convert To PO</form:button>
																	</c:if>
	   										                           </c:if>
	       								                           </c:if>     
   									                            </c:forEach></div>
												          <div class="col-sm-2 form-group has-feedback"><a href="<c:url value="/rfq/downloadPdf?id=${rfq.id}"/>"  class="btn btn-primary pdfdownload float-right">PDF</a></div>
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

	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
	<c:import url="/WEB-INF/jsp/footer.jsp" />


	<script type="text/javascript">
		$('#containerContainingTabs a').on('click', function(e) {
			e.preventDefault();
			$(this).tab('show');
			var theThis = $(this);
			$('#containerContainingTabs a').removeClass('active');
			theThis.addClass('active');
		});

		$('#convertBtn').on('click',function(event) {
					event.preventDefault();
					alertify.confirm('Are you Sure, Want to Convert RFQ to PO',
							function() {
								$.blockUI({ css: {
					                 border: 'none', 
					                 padding: '15px', 
					                 backgroundColor: '#000', 
					                 '-webkit-border-radius': '10px', 
					                 '-moz-border-radius': '10px', 
					                 opacity: .5, 
					                 color: '#fff' 
					             },
					             message: "<h3>Converting <img src=<c:url value='/resources/images/ajax-loader.gif'/> border='0' /></h3>"
					             });
								
								form.submit();
							}, function() {
								setTimeout($.unblockUI, 1000);
								alertify.error('Cancelled')
							});

				});

		function goBack() {
			window.history.back();
		}
	</script>
	<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>


</body>


</html>
