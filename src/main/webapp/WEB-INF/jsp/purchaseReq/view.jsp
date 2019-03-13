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
<%--  <c:import url="/WEB-INF/jsp/loadcss.jsp" /> --%>
<style>
/* .table thead tr th {
	text-align: center !important;
} */
</style>
</head>

<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/purchasesidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-body">
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">
							<!-- Basic form layout section start -->
							<c:url value="/rfq/savePRtoRFQ" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form" 
								class="commentForm" modelAttribute="purchaseRequest"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
												 <h2 class="card-title" id="basic-layout-icons">Purchase Request</h2> 
												</div>
												<form:hidden path="id" />
												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Doc#</label>: ${purchaseRequest.docNumber}

																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Email Id</label>:
																		${purchaseRequest.user.userEmail}
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Requester Name</label>:
																		${purchaseRequest.referenceUser.firstname}${' '}${purchaseRequest.referenceUser.lastname}
																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Posting Date</label>:
																		<fmt:formatDate pattern="dd/MM/yyyy"
																			value="${purchaseRequest.postingDate}" />
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Plant</label>:
																		${purchaseRequest.user.plant.plantName}
																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Doc Date</label>:
																		<fmt:formatDate pattern="dd/MM/yyyy"
																			value="${purchaseRequest.documentDate}" />
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Status</label>: ${purchaseRequest.status}
																	</div>
																	
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Required Date</label>:
																		<fmt:formatDate pattern="dd/MM/yyyy"
																			value="${purchaseRequest.requiredDate}" />
																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Remarks</label>: ${purchaseRequest.remarks}
																	</div>
																</div>
																 
																

																<!--  -->
																<input type="hidden" id="addressCount" value="0">

																<div class="table-responsive">
																	<div class="full-col-table">
																		<table class="table table-bordered "
																			id="edit_item_serviceTbl">
																			<c:if test="${purchaseRequest.type=='Item'}">
																				<thead>
																					<tr>
																						<!-- <th>S.No</th> -->
																						<th style="display: none;">Product Id</th>
																						<th>S.no</th>
																						<th>Product#</th>
																						<th>Description</th>
																						<th>UOM</th>
																						<th>SKU</th>
																						<th>Group</th>
																						<th>HSN Code</th>
																						<th>Warehouse</th>
																						<th>Quantity</th>
																						
																				</thead>

																				<c:forEach
																					items="${purchaseRequest.purchaseRequestLists}"
																					var="listpurchaseRequestLists">
																					<tbody>
																						<td><c:set var="count" value="${count + 1}"
																								scope="page" /> <c:out value="${count}" /></td>
																						<td>${listpurchaseRequestLists.prodouctNumber}</td>
																						<td>${listpurchaseRequestLists.description}</td>
																						<td>${listpurchaseRequestLists.uom}</td>
																						<td>${listpurchaseRequestLists.sku}</td>
																						<td>${listpurchaseRequestLists.productGroup}</td>
																						<td>${listpurchaseRequestLists.hsn}</td>
																						<td><c:forEach var="entry"
																								items="${plantMap}">
																								<c:if
																									test="${entry.key ==listpurchaseRequestLists.warehouse}">
																													 ${entry.value} 																													 </c:if>
																							</c:forEach></td>
																							<td>${listpurchaseRequestLists.requiredQuantity}</td>
																						
																					</tbody>
																				</c:forEach>

																			</c:if>
																			<c:if test="${purchaseRequest.type !='Item'}">
																				<thead>
																					<tr>
																						<th>S.No</th>
																						<th>SAC Code</th>
																						<th>Description</th>
																						<th>Quantity</th>
																						<th>Warehouse</th>
																					</tr>
																				</thead>

																				<c:forEach
																					items="${purchaseRequest.purchaseRequestLists}"
																					var="listpurchaseRequestLists">
																					<tbody>
																						<td><c:set var="count" value="${count + 1}"
																								scope="page" /> <c:out value="${count}" /></td>
																						<td>${listpurchaseRequestLists.sacCode}</td>
																						<td>${listpurchaseRequestLists.description}</td>
																						<td>${listpurchaseRequestLists.requiredQuantity}</td>
																						<td><c:forEach var="entry"
																								items="${plantMap}">
																								<c:if
																									test="${entry.key ==listpurchaseRequestLists.warehouse}">
																													 ${entry.value} 																													 </c:if>
																							</c:forEach></td>
																					</tbody>
																				</c:forEach>

																			</c:if>

																		</table>

																	</div>
																</div>
																

																<!--  -->

															</div>

														</form>

														<div>
															<br></br>
															 
													<div class="row">
												      <div class="col-sm-6 form-group has-feedback"><a href="#" onclick="goBack()" class="btn btn-primary float-left">Back</a></div>
												     <div class="col-sm-2 form-group has-feedback" style="visibility: hidden;"> &nbsp;</div>
										              <div class="col-sm-2 form-group has-feedback">
																<c:forEach items="${sessionScope.umpmap}" var="ump">
										                           <c:if test="${ump.key eq 'Purchase Request'}">
										                           <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	                            <c:if test="${fn:containsIgnoreCase(permissions,'Convertion')}">
	        									                        <c:if test="${purchaseRequest.status == 'Approved'}">
																	    <input type="hidden" name="purchaseId" 	value="${purchaseRequest.id}">
															            <form:button type="button" id="convertBtn" class="btn btn-primary mr-1 float-right">
																        <i></i>Convert PR to RFQ</form:button>
																       </c:if>
	   										                           </c:if>
	       								                           </c:if>     
   									                            </c:forEach>
																</div>
														 <div class="col-sm-2 form-group has-feedback"><a href="<c:url value="/purchaseReq/downloadPdf?purchaseReqId=${purchaseRequest.id}"/>"  class="btn btn-primary pdfdownload float-right">PDF</a></div>		
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
						<br> <br>
					</div>
				</div>
				<!--/ project charts -->
				<br>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
	<c:import url="/WEB-INF/jsp/footer.jsp" />
	
	<script type="text/javascript">
		function goBack() {
			window.history.back();
		}
		
		$('#convertBtn').on('click', function(event) {
			event.preventDefault();
			  alertify.confirm('Are you Sure, Want To Convert PR to RFQ', function(){
				  
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
			  }, function(){
				  setTimeout($.unblockUI, 1000);
		          alertify.error('Cancelled')
		       });
		 
		});
		
		
	</script>
	<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>
</body>

</html>