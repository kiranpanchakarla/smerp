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
<!-- <script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script> -->
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
	$('.nav-item').click(function(){
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
							<c:url value="/vendor/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}"
								class="commentForm" modelAttribute="vendor"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h2 class="card-title" id="basic-layout-icons">Vendor</h2>
												</div>
												<form:hidden path="id" />
												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																	<div class="col-sm-3 form-group has-feedback">
																		<label>Code</label>: ${vendor.vendorCode}

																	</div>
																	<div class="col-sm-3 form-group has-feedback">
																		<label>Name</label>: ${vendor.name}
																	</div>
																	<div class="col-sm-3 form-group has-feedback">
																		<label>Group</label>: ${vendor.groupName}
																	</div>
																	
																	<div class="col-sm-3 form-group has-feedback">
																		<label>Payment</label>: ${totalAmt}
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
																		aria-selected="false">Address</a></li>
																	<li class="nav-item"><a class="nav-link"
																		id="messages-tab" data-toggle="tab" href="#messages"
																		role="tab" aria-controls="messages"
																		aria-selected="false">Payment</a></li>
																</ul>
																<div class="tab-content">
																	<div class="tab-pane active" id="home" role="tabpanel"
																		aria-labelledby="home-tab">

																		<div class="row">
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Mobile</label>: ${vendor.mobileNo}
																			</div>
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Fax</label>: ${vendor.fax}
																			</div>
																		</div>

																		<div class="row">
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Email</label>: ${vendor.emailId}
																			</div>
																			<div class="col-sm-4 form-group has-feedback">
																				<label>Business Type</label>:
																				${vendor.businessPartnerType}
																			</div>
																		</div>
																		<hr>
																		<div class="form-group">
																						<label>Contact Details</label>
																					</div>

                                                                     <c:forEach items="${vendor.vendorContactDetails}" var="listContactDetails">
																				<div class="optionBox1">
																				
																			
																			 <div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Contact ID</label>:
																					${listContactDetails.contactId}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Title</label>:
																					${listContactDetails.title}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Name</label>:
																					${listContactDetails.contactName}
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Address</label>:
																					${listContactDetails.address}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Mobile</label>:
																					${listContactDetails.mobileNo}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Fax</label>:
																					${listContactDetails.fax}
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Email</label>:
																					${listContactDetails.email}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Gender</label>:
																					${listContactDetails.gender}
																				</div>
																				<br> <br> 
																			</div>
																			</div>
																			 <hr>
																				</c:forEach>
 
																			<input type="hidden" id="indexValue1">	

																	</div>

																	<!--  -->

																	
																	<div class="tab-pane " id="profile" role="tabpanel"
																			aria-labelledby="profile-tab">
                                                                          	<!--1 multiply Dynamically Load   -->
																			 
																				<c:forEach items="${vendor.vendorAddress}" var="listAddressDetails">
																				<div class="optionBox1">
																				
																			
																			 <div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Address ID</label>:
																					${listAddressDetails.addressId}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Address</label>:
																					${listAddressDetails.addressName}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Street/PO Box</label>:
																					${listAddressDetails.street}
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>City</label>:
																					${listAddressDetails.city}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Zipcode</label>:
																					${listAddressDetails.zipCode}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>States</label>:
																					${listAddressDetails.states.name}
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-sm-4 form-group has-feedback">
																					<label>GSTIN</label>:
																					${listAddressDetails.gstin}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>GST type</label>:
																					${listAddressDetails.gstinType}
																				</div>
																				<div class="col-sm-4 form-group has-feedback">
																					<label>Country</label>:
																					${listAddressDetails.country.name}
																				</div>
																			</div>
																			<hr>
																			</div>
																			 <br>   
																				</c:forEach>
 
																			<input type="hidden" id="indexValue1">	


																</div>
																	<!--  -->
																	
																	

																<div class="tab-pane" id="messages" role="tabpanel"
																	aria-labelledby="messages-tab">
																	<div class="row">
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Payment</label>: ${vendor.paymentTerms}
																		</div>
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Credit Limit</label>: ${vendor.creditLimit}
																		</div>
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Commitment</label>:
																			${vendor.commitmentLimit}
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Bank Country</label>:
																			${vendor.bankCountry.name}
																		</div>
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Bank Name</label>: ${vendor.bankName}
																		</div>
																		<div class="col-sm-4 form-group has-feedback">
																			<label>IFSC Code</label>: ${vendor.bankCode}
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Branch</label>: ${vendor.branch}
																		</div>
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Account#</label>: ${vendor.accountId}
																		</div>
																		<div class="col-sm-4 form-group has-feedback">
																			<label>Name</label>:
																			${vendor.bankAccountName}
																		</div>
																	</div>
																	<hr>
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
	<c:import url="/WEB-INF/jsp/loadJs.jsp" /> 
	<script type="text/javascript">
		function goBack() {
			window.history.back();
		}
	</script>
</body>
</html>