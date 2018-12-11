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
<%--  <c:import url="/WEB-INF/jsp/loadcss.jsp" /> --%>
<style>
.table thead tr th {
	text-align: center !important;
}
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
							<form:form method="GET" action="/purchaseReq/view"
								class="commentForm" modelAttribute="purchaseRequest"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h2 class="card-title" id="basic-layout-icons">Purchase
														Request/View</h2>
												</div>
												<form:hidden path="id" />
												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>User</label>: ${purchaseRequest.user.username}

																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Doc No</label>: ${purchaseRequest.docNumber}
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Requester Name</label>:
																		${purchaseRequest.user.firstname}${' '}${purchaseRequest.user.lastname}
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
																		<label>Email- ID</label>:
																		${purchaseRequest.user.userEmail}
																	</div>
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Require Date</label>:
																		<fmt:formatDate pattern="dd/MM/yyyy"
																			value="${purchaseRequest.requiredDate}" />
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Type</label>: ${purchaseRequest.type}

																	</div>
																	
																	<div class="col-sm-6 form-group has-feedback">
																		<label>Remark</label>:${purchaseRequest.remarks}
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
																						<th>S.No</th>
																						<th>Product Name</th>
																						<th>Description</th>
																						<th>UOM</th>
																						<th>Quantity</th>
																						<th>Product Group</th>
																						<th>Ware house</th>
																						<th>HSN</th>
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
																						<td>${listpurchaseRequestLists.requiredQuantity}</td>
																						<td>${listpurchaseRequestLists.productGroup}</td>
																						<td><c:forEach var="entry"
																								items="${plantMap}">
																								<c:if
																									test="${entry.key ==listpurchaseRequestLists.warehouse}">
																													 ${entry.value} 																													 </c:if>
																							</c:forEach></td>
																						<td>${listpurchaseRequestLists.hsn}</td>
																					</tbody>
																				</c:forEach>

																			</c:if>
																			<c:if test="${purchaseRequest.type !='Item'}">
																				<thead>
																					<tr>
																						<th>S.No</th>
																						<th>SAC</th>
																						<th>Description</th>
																						<th>Request Quantity</th>
																						<th>Ware house</th>
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
															<a href="#" onclick="goBack()"
																class="btn btn-primary float-left">Back</a>
																
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