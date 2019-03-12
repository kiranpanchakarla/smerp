
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
					<h4>gr</h4>
				</div>
			</div> -->
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">

							<c:url value="/gre/saveGRtoGRE" var="createUrl" />
							<c:url value="/inv/saveGRtoInv" var="createInvoiceUrl" />

							<form:form method="POST" action="" id="form"
							 class="bv-form commentForm" enctype="multipart/form-data" modelAttribute="gr"
								data-toggle="validator" role="form" >
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">

													<h2 class="card-title" id="basic-layout-icons">Goods Receipt</h2>
												</div>

<c:set var="productQuantity" value="${productQuantity + 0}" scope="page" /> 

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>: ${gr.vendor.name}
																	
																</div>
																<div class="col-sm-4 form-group">
																	<label>Email Id</label>: ${gr.vendor.emailId}
																	
																</div>
                                                                <div class="col-sm-4 form-group">
																	<label>Contact</label>: ${gr.vendorContactDetails.contactName}
																</div>
															</div>

															<form:hidden path="id" />

															<div class="row">
																

																<div class="col-sm-4 form-group">
																	<label>Pay To</label>: ${gr.vendorPayTypeAddress.city}
																</div>

																<div class="col-sm-4 form-group">
																	<label>Ship From</label>: ${gr.vendorShippingAddress.city}
																</div>
																
																<div class="col-sm-4 form-group">
																				<label>Posting Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gr.postingDate}" />
																			</div>
                                                                
                                                               

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		<div class="row">
																		
																		 <div class="col-sm-4 form-group">
																				<label>Gr Doc#</label>: ${gr.docNumber}
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>PO Doc#</label>: ${gr.referenceDocNumber}
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>RFQ Doc#</label>: ${gr.poId.rfqId.docNumber}
																			</div>
                                                                            
                                                                            
                                                                            </div>
                                                                            <div class="row">
                                                                            
																			<div class="col-sm-4 form-group">
																				<label>PR Doc#</label>: ${gr.poId.rfqId.purchaseReqId.docNumber}
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Doc Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gr.documentDate}" />
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>Required Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gr.requiredDate}" />
																			</div>
																			
																		</div>




																		<div class="row">
																			
                                                                            <div class="col-sm-4 form-group">
																				<label>Status</label>: ${gr.status}
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Remarks</label>: ${gr.remark}
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
																										<c:if test="${not empty lineItemsBean}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																							<tr>
																									
																									<th style="display: none;">Product Id</th>
																									<c:if test="${gr.category=='Item'}">
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
																									
																									<c:if test="${gr.category!='Item'}">
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
																											<c:forEach items="${lineItemsBean}"
																												var="listLineItems">
																												
																												  <tr class="multTot multTot${count}">
																												<%-- <td style="display: none;"><form:input
																															type="hidden"
																															path="lineItemsBean[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="lineItemsBean[${count}].id"/>	
																															</td> --%>
																													<td><c:set var="index" value="${index + 1}"
																								                  scope="page" /> <c:out value="${index}" /></td>
																															
																													<c:if test="${gr.category=='Item'}">
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
																													 ${entry.value} 																												 </c:if>
																													</c:forEach></td>
																													
																													<td>${listLineItems.tempRequiredQuantity}
																													
																													<c:set var="productQuantity" value="${productQuantity + listLineItems.tempRequiredQuantity}" scope="page" /> 
																													</td>

																												
																														
																													</c:if>
																													
																													<c:if test="${gr.category!='Item'}">
																													<td>${listLineItems.sacCode}</td>
																													
																													<td>${listLineItems.description}</td>
																															
																													<td>${listLineItems.tempRequiredQuantity}
																													
																													<c:set var="productQuantity" value="${productQuantity + listLineItems.tempRequiredQuantity}" scope="page" /> 
																													
																													</td>
																													
																													
																													
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
																	                ${gr.vendorShippingAddress.addressName}<br>
																					${gr.vendorShippingAddress.street}
																					${gr.vendorShippingAddress.city}
																					${gr.vendorShippingAddress.zipCode}<br>
																					${gr.vendorShippingAddress.country.name}
																	</div></div>
																	
																	<div class="col-sm-4">
																	<label>Pay To </label> 
																	<div id="payToAddressTable">
																					${gr.vendorPayTypeAddress.addressName}<br>
																					${gr.vendorPayTypeAddress.street}
																					${gr.vendorPayTypeAddress.city}
																					${gr.vendorPayTypeAddress.zipCode}<br>
																					${gr.vendorPayTypeAddress.country.name}
																	</div>
																	 </div>
																	
																	<div class="col-sm-4 form-group">
																	<label>Deliver To </label> 
																	${gr.deliverTo}
																	</div>
																	</div>

																	<%-- <table class="table fixed-width-table">
																		<thead>
																			<tr>
																				<th style="vertical-align: top; !important">Shipping
																					From</th>
																				<td>
																					<div id="shippingAddressTable">
																					
																					${gr.vendorShippingAddress.addressName}<br>
																					${gr.vendorShippingAddress.street}
																					${gr.vendorShippingAddress.city}
																					${gr.vendorShippingAddress.zipCode}<br>
																					${gr.vendorShippingAddress.country.name}
																					
																					
																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th style="vertical-align: top; !important">Pay
																					To</th>
																				<td>
																					<div id="payToAddressTable">
																					
																					${gr.vendorPayTypeAddress.addressName}<br>
																					${gr.vendorPayTypeAddress.street}
																					${gr.vendorPayTypeAddress.city}
																					${gr.vendorPayTypeAddress.zipCode}<br>
																					${gr.vendorPayTypeAddress.country.name}
																					
																					
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
											<div class="col-sm-6">:	${gr.totalDiscount} </div>
											</div>

											<div class="form-group">
											<div class="col-sm-6">	<label>Total Invoice Amount </label> </div>
											<div class="col-sm-6">:	${gr.totalBeforeDisAmt} </div>
											</div>
											<div class="form-group">
												<div class="col-sm-6"><label>Freight  </label></div>
												<div class="col-sm-6">: ${gr.freight} </div>
											</div>

											<div class="form-group">
												<div class="col-sm-6"> <label>Tax Amount </label> </div>
												<div class="col-sm-6">: ${gr.taxAmt} </div>
											</div>
											
											<div class="form-group">
											<div class="col-sm-6">	<label>Total  </label></div>
											<div class="col-sm-6">: ${gr.amtRounding} </div>
											</div>
											
											<div class="form-group">
											<div class="col-sm-6">	<label>Rounded Off  </label></div>
											<div class="col-sm-6"> : <fmt:formatNumber type="number" maxFractionDigits="3" value="${gr.totalPayment - gr.amtRounding}"/></div>
											</div>

											<div class="form-group">
											<div class="col-sm-6">	<label>Total Payment Due  </label> </div>
											<div class="col-sm-6">: ${gr.totalPayment} </div>
											</div>
											
											
											
										</div>
									
									</div>		
											
											
											
											
											<!--Calculation Part  -->
									
									<div class="card-block">
									<div class="row">
										<div class="col-sm-12">
											<div class="row">
												          <div class="col-sm-6 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												         
												      <c:choose>
														<c:when test="${productQuantity !=0}">
													
												         <div class="col-sm-2 form-group has-feedback">
									
										<input type="hidden" name="greId" value="${gr.id}">
									 <c:forEach items="${sessionScope.umpmap}" var="ump">
										                           <c:if test="${ump.key eq 'Convert To GRE'}">  
										                           <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	                            <c:if test="${fn:containsIgnoreCase(permissions,'Convertion')}"> 
	        									                        <c:if test="${gr.status == 'Approved' || gr.status == 'Goods_Return'  || gr.status == 'Partially_Returned'  &&  gr.status != 'Invoiced'}">
																		
														 <c:if test="${checkStatusGr ==true}">
																		<form:button type="button" id="convertBtn" name="statusType" value="goods_return"
																			class="btn btn-primary mr-1 float-right mySubButton">
																			<i></i>Goods Return</form:button>
																			
											            </c:if>	
																	</c:if>
	   										                           </c:if>
	       								                           </c:if>     
   									                            </c:forEach>
												         
												         </div>
												         
												          <div class="col-sm-2 form-group has-feedback">
												          
												          <c:forEach items="${sessionScope.umpmap}" var="ump">
										                           <c:if test="${ump.key eq 'Convert To INV'}">
										                           <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	                            <c:if test="${fn:containsIgnoreCase(permissions,'Convertion')}">
	        									                        
	 	        									                        <c:if test="${gr.status == 'Approved' ||  gr.status == 'Partially_Returned' || gr.status == 'Goods_Return'  && gr.status != 'Invoiced'}">
														
																		<form:button type="button" id="convertBtnInvoice" name="statusType" value="in_voice"
																			class="btn btn-primary mr-1 float-right mySubButtonInv">
																			<i></i>Generate Invoice</form:button>
																	  </c:if>
	   										                           </c:if>
	       								                           </c:if>     
   									                            </c:forEach>
												          </div>
							                   <div class="col-sm-2 form-group has-feedback"><a href="<c:url value="/gr/downloadPdf?id=${gr.id}"/>"  class="btn btn-primary pdfdownload float-right">PDF</a></div>
												        </c:when>
												       <c:otherwise>
												       
												       <div class="col-sm-2 form-group has-feedback" style="visibility: hidden;"> &nbsp;</div>
												       <div class="col-sm-2 form-group has-feedback" style="visibility: hidden;"> &nbsp;</div>
												       <div class="col-sm-2 form-group has-feedback"><a href="<c:url value="/gr/downloadPdf?id=${gr.id}"/>"  class="btn btn-primary pdfdownload float-right">PDF</a></div>
												       </c:otherwise> 
												      </c:choose>
												         
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
	
$(".mySubButton").on('click', function() {
			alertify.confirm('Goods Return','Are you Sure, Want to Goods Return!',
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
				    $('#form').attr('action', "${createUrl}").submit();
					}, function() {
						setTimeout($.unblockUI, 1000);
						alertify.error('Cancelled')
					});

		});
		
		
		
	$(".mySubButtonInv").on('click', function() {
			alertify.confirm('Invoice','Are you Sure, Want to Generate Invoice!',
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
				
				    $('#form').attr('action', "${createInvoiceUrl}").submit();
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
