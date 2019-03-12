
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

													<h2 class="card-title" id="basic-layout-icons">Inventory Goods Issue</h2>
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															

															<form:hidden path="id" />

															<div class="row">
																
                                                                
                                                                <div class="col-sm-4 form-group">
																				<label>Doc#</label>: ${gr.docNumber}
																</div>
																	 
																<div class="col-sm-4 form-group"><label>Status</label> : ${gr.status}</div>
																
																<div class="col-sm-4 form-group"><label>Remarks</label> : ${gr.remarks}</div>
																<div class="col-sm-4 form-group">
																				<label>Posting Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gr.postingDate}" />
																			</div>
																<div class="col-sm-4 form-group">
																				<label>Doc Date</label>: 
																				<fmt:formatDate pattern = "dd/MM/yyyy"  value = "${gr.documentDate}" />
																			</div>
																<div class="col-sm-4 form-group">
																				<label>Ref Doc#</label>: ${gr.referenceDocNumber}
																			</div>
																 

															</div>
																<hr>
																<div class="tab-content">
																
																	<div class="row">
																		<div class="col-xs-12">
																			<div class="row-in">
																				
																				<div class="table-responsive">
																					<div class="full-col-table">
																						
                                                                                    
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty inventoryGoodsIssueList}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																							<tr>
																									
																									<th style="display: none;">Product Id</th>
																									
																									<th>S.no</th>
																									<th>Product#</th>
																									<th>Description</th>
																											<th>Quantity</th>
																											<th>Unit Price</th>
																											<th>Tax %</th>
																											<th>Tax Total</th>
																											<th>Total</th>
																											<th>Warehouse</th>
																											<th>Department</th>
																											<th>Group</th>
																											<th>HSN</th>
																											<th>UOM</th>
																									
																									
																									
																								
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${inventoryGoodsIssueList}"
																												var="listLineItems">
																												
																												  <tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="inventoryGoodsIssueList[${count}].productId"
																															value="${listLineItems.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="inventoryGoodsIssueList[${count}].id"/>	
																															</td>
																													<td><c:set var="index" value="${index + 1}"
																								                  scope="page" /> <c:out value="${index}" /></td>
																													
																													<td>${listLineItems.productNumber}</td>
																													<td>${listLineItems.description}</td>
																													<td>${listLineItems.requiredQuantity}</td>
																													
																													 
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
																													
																													<td><c:forEach var="entry1"
																														items="${deptMap}">
																														<c:if
																															test="${entry1.key ==listLineItems.department}">
																													 ${entry1.value} 																													 </c:if>
																													</c:forEach></td>
																													<td>${listLineItems.productGroup}</td>
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
											 
											<div class="col-sm-6"><label>Discount(%) </label></div>
											<div class="col-sm-6">:	${gr.totalDiscount} </div>
											</div>

											<div class="row">
											<div class="col-sm-6">	<label>Total Invoice Amount </label> </div>
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
											<div class="col-sm-6">	<label>Total  </label></div>
											<div class="col-sm-6">: ${gr.amtRounding} </div>
											</div>
											
											<div class="row">
											<div class="col-sm-6">	<label>Rounded Off  </label></div>
											<div class="col-sm-6"> : <fmt:formatNumber type="number" maxFractionDigits="3" value="${gr.totalPayment - gr.amtRounding}"/></div>
											
											</div>
											
											<div class="row">
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
												          <div class="col-sm-3 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												         
												         <div class="col-sm-3 form-group has-feedback">
												        
									
										
									
												         
												         </div>
												         
												          <div class="col-sm-3 form-group has-feedback">
												          
												        
												          </div>
										             
												         
												         
												          <div class="col-sm-3 form-group has-feedback"><a href="<c:url value="/invgi/downloadPdf?id=${gr.id}"/>"  class="btn btn-primary pdfdownload float-right">PDF</a></div>
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
