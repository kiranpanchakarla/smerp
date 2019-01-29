
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<h4>po</h4>
				</div>
			</div> -->
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">




							<c:url value="/gr/savePOtoGR" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form"
								class="bv-form commentForm" enctype="multipart/form-data"
								modelAttribute="po" data-toggle="validator" role="form">
								
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">

													<h2 class="card-title" id="basic-layout-icons">Purchase Order</h2>
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>: ${po.vendor.name}
																	
																</div>
																<div class="col-sm-4 form-group">
																	<label>Email Id</label>: ${po.vendor.emailId}
																	
																</div>
                                                                <div class="col-sm-4 form-group">
																	<label>Contact</label>: ${po.vendorContactDetails.contactName}
																</div>
															</div>

															<form:hidden path="id" />

															<div class="row">
																

																<div class="col-sm-4 form-group">
																	<label>Pay To</label>: ${po.vendorPayTypeAddress.city}
																</div>

																<div class="col-sm-4 form-group">
																	<label>Ship From</label>: ${po.vendorShippingAddress.city}
																</div>
                                                                
                                                                <div class="col-sm-4 form-group">
																				<label>Document#</label>: ${po.docNumber}
																			</div>

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Ref Doc#</label>: ${po.referenceDocNumber}
																			</div>
                                                                            <div class="col-sm-4 form-group">
																				<label>Posting Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${po.postingDate}" />
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Doc Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${po.documentDate}" />
																			</div>
																		</div>




																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${po.requiredDate}" />
																			</div>
                                                                            <div class="col-sm-4 form-group">
																				<label>Type</label>: Product
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Status</label>: ${po.status}
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
																										<c:if test="${not empty purchaseOrderlineItems}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																							<tr>
																									
																									<th style="display: none;">Product Id</th>
																									<c:if test="${po.category=='Item'}">
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
																									
																									<c:if test="${po.category!='Item'}">
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
																									<th>Action</th>
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${purchaseOrderlineItems}"
																												var="listLineItems">
																												
																												  <tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="purchaseOrderlineItems[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="purchaseOrderlineItems[${count}].id"/>	
																															</td>
																													<td><c:set var="index" value="${index + 1}"
																								                  scope="page" /> <c:out value="${index}" /></td>
																															
																													<c:if test="${po.category=='Item'}">
																													<td>${listLineItems.prodouctNumber}</td>
																													<td>${listLineItems.description}</td>
																													<td>${listLineItems.uom}</td>
																													<td>${listLineItems.sku}</td>
																													
																													
																															<td>${listLineItems.unitPrice}</td>
																															<td><c:forEach var="entry"
																																items="${taxCodeMap}">
																																<c:if test="${entry.key ==listLineItems.taxCode}">
																													            ${entry.value} 																													 </c:if>
																															</c:forEach></td>
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

																												
																														<td>--</td>	
																													</c:if>
																													
																													<c:if test="${po.category!='Item'}">
																													<td>${listLineItems.sacCode}</td>
																													
																													<td>${listLineItems.description}</td>
																															
																													<td>${listLineItems.requiredQuantity}</td>
																													
																													<td>${listLineItems.unitPrice}</td>
																												<td><c:forEach var="entry"
																														items="${taxCodeMap}">
																														<c:if
																															test="${entry.key ==listLineItems.taxCode}">
																													            ${entry.value} 																													 </c:if>
																													</c:forEach></td>
																												<td>${listLineItems.taxTotal}</td>
																												<td>${listLineItems.total}</td>


																												<td><c:forEach var="entry"
																																items="${plantMap}">
																																<c:if
																																	test="${entry.key ==listLineItems.warehouse}">
																													 ${entry.value} 																													 </c:if>
																															</c:forEach></td>
																														<td>--</td>		
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

																	<table class="table fixed-width-table">
																		<thead>
																			<tr>
																				<th style="vertical-align: top; !important">Shipping
																					From</th>
																				<td>
																					<div id="shippingAddressTable">
																					
																					${po.vendorShippingAddress.addressName}<br>
																					${po.vendorShippingAddress.street}
																					${po.vendorShippingAddress.city}
																					${po.vendorShippingAddress.zipCode}<br>
																					${po.vendorShippingAddress.country.name}
																					
																					
																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th style="vertical-align: top; !important">Pay
																					To</th>
																				<td>
																					<div id="payToAddressTable">
																					
																					${po.vendorPayTypeAddress.addressName}<br>
																					${po.vendorPayTypeAddress.street}
																					${po.vendorPayTypeAddress.city}
																					${po.vendorPayTypeAddress.zipCode}<br>
																					${po.vendorPayTypeAddress.country.name}
																					
																					
																					</div>
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
									
									<!--Calculation Part  -->
									
									<div class="row">
											<div class="col-sm-4"> &nbsp;  </div>
                                           <div class="col-sm-4">&nbsp;</div>   
											
										<div class="col-sm-4">
											<div class="form-group">
											<div class="col-sm-6">
												<label>Discount(%)</label>
												</div>
												<div class="col-sm-6">
												: ${po.totalDiscount}
												</div>
											</div>

											<div class="form-group">
											<div class="col-sm-6">
												<label>Total Before Discount  </label>
												</div>
												<div class="col-sm-6">
												: ${po.totalBeforeDisAmt}
												</div>
											</div>
											<div class="form-group">
											<div class="col-sm-6">
												<label>Freight  </label> 
												</div>
												<div class="col-sm-6">: ${po.freight}</div>
											</div>

											<div class="form-group">
												<div class="col-sm-6"><label>Rounding  </label></div>
												<div class="col-sm-6">: ${po.amtRounding} </div>
											</div>

											<div class="form-group">
												<div class="col-sm-6"><label>Tax Amount </label> </div>
												<div class="col-sm-6">: ${po.taxAmt} </div>
											</div>

											<div class="form-group">
											<div class="col-sm-6">	<label>Total Payment Due  </label> </div>
											<div class="col-sm-6">: ${po.totalPayment} </div>
											</div>
										</div>
									
									</div>		
											
											
											
											
											
											
											<!--Calculation Part  -->
									
									<div class="card-block">
									<div class="row">
										<div class="col-sm-12 form-group">
											<div class="row">
												          <div class="col-sm-6 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												         
												         <div class="col-sm-4 form-group has-feedback">
												         
												           <c:forEach items="${sessionScope.umpmap}" var="ump">
										                           <c:if test="${ump.key eq 'PurchaseOrder'}">
										                           <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	                            <c:if test="${fn:containsIgnoreCase(permissions,'Convertion')}">
	        									                        <c:if test="${po.status eq 'Approved' || po.status eq 'Partially_Received'}">
																		 
																		   <c:if test="${checkStatusPoGr ==true}">
									<input type="hidden" name="poId" value="${po.id}">
																		<form:button type="button" id="convertBtn"
																			class="btn btn-primary mr-1 float-right">
																			<i></i>Convert To GR</form:button>
																			
								                           </c:if>	
																		 
																	</c:if>
	   										                           </c:if>
	       								                           </c:if>     
   									                            </c:forEach>
												         
												         
												         
												       
												         
												         </div>
												         
												          <div class="col-sm-2 form-group has-feedback"><a href="<c:url value="/po/downloadPdf?id=${po.id}"/>"  class="btn btn-primary float-right">PDF</a></div>
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

		$('#convertBtn').on(
				'click',
				function(event) {
					event.preventDefault();
					alertify.confirm('Convert PO to GR','Are you Sure, Want to Convert  PO  to GR',
							function() {
								form.submit();
							}, function() {
								alertify.error('Cancelled')
							});

				});

		function goBack() {
			window.history.back();
		}
	</script>

	
	
</body>


</html>
