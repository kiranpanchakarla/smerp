
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
   

</head>
<style>
.table th, .table td{
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
					<h4>gr</h4>
				</div>
			</div> -->
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">




							<form:form method="POST" action="/gr/save" id="form" class="bv-form commentForm"
								enctype="multipart/form-data" modelAttribute="gre"
								data-toggle="validator" role="form" >
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">

													<h2 class="card-title" id="basic-layout-icons">Goods Return</h2>
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>: ${gre.vendor.name}
																	
																</div>
																<div class="col-sm-4 form-group">
																	<label>Email Id</label>: ${gre.vendor.emailId}
																	
																</div>
                                                                <div class="col-sm-4 form-group">
																	<label>Contact</label>: ${gre.vendorContactDetails.contactName}
																</div>
															</div>

														<form:hidden path="id" />

															<div class="row">
																

																<div class="col-sm-4 form-group">
																	<label>Pay To</label>: ${gre.vendorPayTypeAddress.city}
																</div>

																<div class="col-sm-4 form-group">
																	<label>Ship From</label>: ${gre.vendorShippingAddress.city}
																</div>
                                                                
                                                               <div class="col-sm-4 form-group">
																				<label>Posting Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gre.postingDate}" />
																			</div>

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		<div class="row">
																		
																		 <div class="col-sm-4 form-group">
																				<label>GRE Doc#</label>: ${gre.docNumber}
																			</div>
																		
																			<div class="col-sm-4 form-group">
																				<label>GR Doc#</label>: ${gre.referenceDocNumber}
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>PO Doc#</label>: ${gre.grId.poId.docNumber}
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>RFQ Doc#</label>: ${gre.grId.poId.rfqId.docNumber}
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>PR Doc#</label>: ${gre.grId.poId.rfqId.purchaseReqId.docNumber}
																			</div>
                                                                            
																			<div class="col-sm-4 form-group">
																				<label>Doc Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gre.documentDate}" />
																			</div>
																		</div>




																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gre.requiredDate}" />
																			</div>
                                                                            <div class="col-sm-4 form-group">
																				<label>Type</label>: Product
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Status</label>: ${gre.status}
																			</div>
																		</div>
																		
																		
																	</div>
																</div>
															</div>
															
															<div class="row">
																			
																			
															</div>
																		
															

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
																						
                                                                                    
                                                                                            
                                                                                            
                                                                                            
                                                                                            
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty goodsReturnLineItems}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																							<tr>
																									
																									<th style="display: none;">Product Id</th>
																									<c:if test="${gre.category=='Item'}">
																									<th>S.no</th>
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
																									
																									<c:if test="${gre.category!='Item'}">
																									<th>S.No</th>
																									<th>SAC Code</th>
																									<th>Description</th>
																									<th>Quantity</th>
																									<th>Unit Price</th>
																									<th>Tax Code</th>
																									<th>Tax Total</th>
																									<th>Total</th>
																									<th>Warehouse</th>
																									</c:if>
																								
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${goodsReturnLineItems}"
																												var="listLineItems">
																												
																												  <tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="goodsReturnLineItems[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="goodsReturnLineItems[${count}].id"/>	
																															</td>
																													<td><c:set var="index" value="${index + 1}"
																								                  scope="page" /> <c:out value="${index}" /></td>
																															
																													<c:if test="${gre.category=='Item'}">
																													<td>${listLineItems.prodouctNumber}</td>
																													<td>${listLineItems.description}</td>
																													<td>${listLineItems.uom}</td>
																													<td>${listLineItems.sku}</td>
																													
																													
																															<td>${listLineItems.unitPrice}</td>
																															<td><%-- <c:forEach var="entry"
																																items="${taxCodeMap}">
																																<c:if test="${entry.value ==listLineItems.taxCode}">
																													            ${entry.key} 																													 </c:if>
																															</c:forEach> --%>
																															${listLineItems.taxDescription}
																															</td>
																															<td>${listLineItems.taxTotal}</td>
																															<td>${listLineItems.total}</td>
																													
																														<td>${listLineItems.productGroup}</td>
																														<td>${listLineItems.hsn}</td>

																												<td><c:forEach var="entry"
																														items="${plantMap}">
																														<c:if
																															test="${entry.key ==listLineItems.warehouse}">
																													 ${entry.value} 																													 </c:if>
																													</c:forEach></td>
																													
																												<td>${listLineItems.requiredQuantity}</td>

																												
																														
																													</c:if>
																													
																													<c:if test="${gre.category!='Item'}">
																													<td>${listLineItems.sacCode}</td>
																													
																													<td>${listLineItems.description}</td>
																															
																													<td>${listLineItems.requiredQuantity}</td>
																													
																													<td>${listLineItems.unitPrice}</td>
																												<td><c:forEach var="entry"
																														items="${taxCodeMap}">
																														<c:if
																															test="${entry.value ==listLineItems.taxCode}">
																													            ${entry.key} 																													 </c:if>
																													</c:forEach></td>
																												<td>${listLineItems.taxTotal}</td>
																												<td>${listLineItems.total}</td>


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
																									<input type="hidden" id="edit_addressCount" value="${count-1}">
																										</c:if>

																						
																					</div>
																				</div>
																				
																				
																				
																				
																			</div>
																		</div>
																	</div>
																</div>

																<div class="tab-pane" id="profile" role="tabpanel"
																	aria-labelledby="profile-tab">
																	
																	<div class="row">
																	<div class="col-sm-4">
																	 
																	<label>Shipping From </label>
																	<div id="shippingAddressTable" >
																	                ${gre.vendorShippingAddress.addressName}<br>
																					${gre.vendorShippingAddress.street}
																					${gre.vendorShippingAddress.city}
																					${gre.vendorShippingAddress.zipCode}<br>
																					${gre.vendorShippingAddress.country.name}
																	</div></div>
																	
																	<div class="col-sm-4">
																	<label>Pay To </label> 
																	<div id="payToAddressTable">
																					${gre.vendorPayTypeAddress.addressName}<br>
																					${gre.vendorPayTypeAddress.street}
																					${gre.vendorPayTypeAddress.city}
																					${gre.vendorPayTypeAddress.zipCode}<br>
																					${gre.vendorPayTypeAddress.country.name}
																	</div>
																	 </div>
																	
																	<div class="col-sm-4 form-group">
																	<label>Deliver To </label> 
																	${gre.deliverTo}
																	</div>
																	</div>

																	<%-- <table class="table fixed-width-table">
																		<thead>
																			<tr>
																				<th style="vertical-align: top; !important">Shipping
																					From</th>
																				<td>
																					<div id="shippingAddressTable">
																					
																					${gre.vendorShippingAddress.addressName}<br>
																					${gre.vendorShippingAddress.street}
																					${gre.vendorShippingAddress.city}
																					${gre.vendorShippingAddress.zipCode}<br>
																					${gre.vendorShippingAddress.country.name}
																					
																					
																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th style="vertical-align: top; !important">Pay
																					To</th>
																				<td>
																					<div id="payToAddressTable">
																					
																					${gre.vendorPayTypeAddress.addressName}<br>
																					${gre.vendorPayTypeAddress.street}
																					${gre.vendorPayTypeAddress.city}
																					${gre.vendorPayTypeAddress.zipCode}<br>
																					${gre.vendorPayTypeAddress.country.name}
																					
																					
																					</div>
																				</td>
																			</tr>
																		</thead>
																	</table> --%>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<!--Calculation Part  -->
									
									<div class="row">
											<div class="col-sm-4"> &nbsp;  </div>
                                           <div class="col-sm-4">&nbsp;</div>   
											
										<div class="col-sm-4">
											<div class="form-group">
												<div class="col-sm-6"><label>Discount(%) </label></div>
												<div class="col-sm-6">: ${gre.totalDiscount}</div>
											</div>

											<div class="form-group">
												<div class="col-sm-6"><label>Total Before Discount  </label></div>
												<div class="col-sm-6">: ${gre.totalBeforeDisAmt}</div>
											</div>
											<div class="form-group">
												<div class="col-sm-6"><label>Freight  </label> </div>
												<div class="col-sm-6">: ${gre.freight} </div>
											</div>

											<div class="form-group">
											<div class="col-sm-6">	<label>Tax Amount </label> </div>
											<div class="col-sm-6">: ${gre.taxAmt} </div>
											</div>
											
											<div class="form-group">
												<div class="col-sm-6"><label>Total  </label></div>
												<div class="col-sm-6"> : ${gre.amtRounding}</div>
											</div>
											
											<div class="form-group">
												<div class="col-sm-6"><label>Rounded Off  </label></div>
												<div class="col-sm-6"> : <fmt:formatNumber type="number" maxFractionDigits="3" value="${gre.totalPayment - gre.amtRounding}"/></div>
											</div>

											<div class="form-group">
												<div class="col-sm-6"><label>Total Payment Due  </label>  </div>
												<div class="col-sm-6">: ${gre.totalPayment} </div>
											</div>
											
											
										</div>
									
									</div>		
											
											
											
											
											
											
											<!--Calculation Part  -->
									
									<div class="card-block">
									<div class="row">
										<div class="col-sm-12 form-group">
											<div class="row">
												          <div class="col-sm-6 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												          <div class="col-sm-6 form-group has-feedback"><a href="<c:url value="/gre/downloadPdf?id=${gre.id}"/>"  class="btn btn-primary pdfdownload float-right">PDF</a></div>
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
	
	
	
function goBack() {
    window.history.back();
}
</script>
	
<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>	
	
</body>


</html>
