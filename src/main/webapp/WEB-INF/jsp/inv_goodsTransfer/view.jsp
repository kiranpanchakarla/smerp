
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
	<c:import url="/WEB-INF/jsp/inventorysidebar.jsp" />
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

							<%-- <c:url value="/gre/saveGRtoGRE" var="createUrl" />
							<c:url value="/inv/saveGRtoInv" var="createInvoiceUrl" /> --%>

							<form:form method="POST" action="" id="form"
							 class="bv-form commentForm" enctype="multipart/form-data" modelAttribute="gr"
								data-toggle="validator" role="form" >
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">

													<h2 class="card-title" id="basic-layout-icons">Inventory Goods Transfer</h2>
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															

															<form:hidden path="id" />
															
															<div class="row">
																
																<div class="col-sm-6 form-group">
																                <label>Business Partner</label>: ${gr.businessPartner}
																					 
																				</div>


																	<div class="col-sm-6 form-group">
																		<label>Doc Number</label>: ${gr.docNumber}
																		 
																	</div>
																	
																</div>
																
																<div class="row">
																
																<div class="col-sm-6 form-group">
																                <label>Name</label>: ${gr.user}
																					 
																				</div>


																	<div class="col-sm-6 form-group">
																		<label>Status</label>: ${gr.status}
																		 
																	</div>
																	
																</div>
																
																<div class="row">
																
																<div class="col-sm-6 form-group">
																                <label>Posting Date</label>: ${gr.postingDate}
																					 
																				</div>


																	<div class="col-sm-6 form-group">
																		<label>Doc Date</label>: ${gr.documentDate}
																		 
																	</div>
																	
																</div>
																
																<div class="row">
																
																<div class="col-sm-6 form-group">
																                <label>Ship to</label>: <c:forEach var="entry"
																														items="${plantMap}">
																														<c:if
																															test="${entry.key ==gr.toWarehouse}">
																													 ${entry.value} 																													 </c:if>
																													</c:forEach>  
																					 
																				</div>
 
																	
																</div>

															 

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">
																	<div class="form-body">

																		


																		
																		
																		
																	</div>
																</div>
															</div>
															
															<div class="row">
																			
																			
															</div>
																		
														

															<div class="tab-content">
																
																	<div class="row">
																		<div class="col-xs-12">
																			<div class="row-in">
																				
																				<div class="table-responsive">
																					<div class="full-col-table">
																						
                                                                                    
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty inventoryGoodsTransferList}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																							<tr>
																									
																									<th style="display: none;">Product Id</th>
																									
																									<th>S.no</th>
																									<th>Product#</th>
																									<th>Description</th>
																									<th>Ship From</th>
																									<th>Ship To</th>
																									<th>Quantity</th>
																									<th>Unit Price</th>
																									<th>Tax %</th>
																									<th>Tax Total</th>
																									<th>Total</th>
																									<th>UOM</th>
																									<th>HSN Code</th>
																									
																									
																									
																								
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${inventoryGoodsTransferList}"
																												var="listLineItems">
																												
																												  <tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="inventoryGoodsTransferList[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="inventoryGoodsTransferList[${count}].id"/>	
																															</td>
																													<td><c:set var="index" value="${index + 1}"
																								                  scope="page" /> <c:out value="${index}" /></td>
																													
																													<td>${listLineItems.productNumber}</td>
																													<td>${listLineItems.description}</td>
																														<td><c:forEach var="entry"
																														items="${plantMap}">
																														<c:if
																															test="${entry.key ==listLineItems.fromWarehouse}">
																													 ${entry.value} 																													 </c:if>
																													</c:forEach></td>
																														<td><c:forEach var="entry"
																														items="${plantMap}">
																														<c:if
																															test="${entry.key ==listLineItems.toWarehouse}">
																													 ${entry.value} 																													 </c:if>
																													</c:forEach></td>
																													
																													 
																													<td>${listLineItems.requiredQuantity}</td>
																													
																															 <td>${listLineItems.unitPrice}</td>
																															<td><c:forEach var="entry"
																																items="${taxCodeMap}">
																																<c:if test="${entry.key ==listLineItems.taxCode}">
																													            ${entry.value} 																													 </c:if>
																															</c:forEach></td>
																															<td>${listLineItems.taxTotal}</td>
																															<td>${listLineItems.total}</td>
																													
																														<td>${listLineItems.uom}</td>
																														<td>${listLineItems.hsn}</td>
 
																													
																												
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
											
										<div class="col-sm-4 create-po-wrap">
										
											<div class="row">
											<div class="col-sm-6 form-group">
											<label>Discount(%) </label></div>
											<div class="col-sm-6">:	${gr.totalDiscount} </div>
											</div>

											<div class="row">
											<div class="col-sm-6">	<label>Total Before Discount </label> </div>
											<div class="col-sm-6">:	${gr.totalBeforeDisAmt} </div>
											</div>
											
											<div class="row">
												<div class="col-sm-6"><label>Freight  </label></div>
												<div class="col-sm-6">: ${gr.freight} </div>
											</div>

											<div class="row">
												<div class="col-sm-6"> <label>Tax Amount </label> </div>
												<div class="col-sm-6">: ${gr.taxAmt} </div>
											</div>

											<div class="row">
											<div class="col-sm-6">	<label>Total Payment Due  </label> </div>
											<div class="col-sm-6">: ${gr.totalPayment} </div>
											</div>
											
											<div class="row">
											<div class="col-sm-6">	<label>Rounding  </label></div>
											<div class="col-sm-6">: ${gr.amtRounding} </div>
											</div>
											
										</div>
									
									</div>		
											
											
											
											
											
											
											<!--Calculation Part  -->
									
									<div class="card-block">
									<div class="row">
										<div class="col-sm-12">
											<div class="row">
												          <div class="col-sm-3 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												         
												         <div class="col-sm-3 form-group has-feedback">
												        
									
										
									
												         
												         </div>
												         
												          <div class="col-sm-3 form-group has-feedback">
												          
												        
												          </div>
										             
												         
												         
												          <div class="col-sm-3 form-group has-feedback"><a href="<c:url value="/invgt/downloadPdf?id=${gr.id}"/>"  class="btn btn-primary float-right">PDF</a></div>
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
				    $('#form').attr('action', "${createUrl}").submit();
					}, function() {
						alertify.error('Cancelled')
					});

		});
		
		
		
$(".mySubButtonInv").on('click', function() {
			alertify.confirm('Invoice','Are you Sure, Want to Generate Invoice!',
					function() {
				    $('#form').attr('action', "${createInvoiceUrl}").submit();
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