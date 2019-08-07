<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>

<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
 <script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
 <!-- <script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script>  -->
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>
<script src=<c:url value="/resources/js/common.js"/> type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.nav-item').click(function(){
		$(this).parent('ul').find('li').removeClass('active');
		$(this).parent('ul').find('li').find('a').removeClass('active');
		$(this).find('a').addClass('active');
	});
});


</script>

</head>

<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

						<div class="content-body">
							<!-- Basic form layout section start -->
							<c:url value="/vendor/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form"
								class="commentForm" modelAttribute="vendor"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<c:if test="${vendor.id!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update Vendor Details</h2>
													</c:if>

													<c:if test="${vendor.id==null}">
														<h2 class="card-title" id="basic-layout-icons">Create New Vendor</h2>
													</c:if>
												</div>
												<form:input type="hidden" cssClass="form-control" path="id" />

												<div class="card-body collapse in create-block">
													<div class="card-block">

														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Code</label>
																	<form:input type="text" class="form-control camelCase"
																		placeholder='Vendor Code' path="vendorCode" value=""
																		onchange="isValidName('vendorCode','/vendor/isValidVendorCode','1_errorContainer','Vendor Code Already Exists')"
																		required="true" autocomplete="off"
																		oninvalid="this.setCustomValidity('Please Enter Vendor Code')"
																		oninput="setCustomValidity('')" readonly="true"  />
																	<!--  <div  class="help-block with-errors"></div> -->
																</div>

																<div class="col-sm-4 form-group">
																	<label>Name</label>
																	<form:input type="text" class="form-control camelCase"
																		placeholder='Vendor Name' path="name" value=""
																		onchange="isValidName('name','/vendor/isValidVendorName','1_errorContainer','Vendor Name Already Exists')"
																		required="true" autocomplete="off"
																		oninvalid="this.setCustomValidity('Please Enter Vendor Name')"
																		oninput="setCustomValidity('')" />
																	<!-- <div   id="1_errorContainer"   class="help-block with-errors"></div> -->

																</div>

																<div class="col-sm-4 form-group">
																	<label>Group</label>
																	<form:input type="text" class="form-control camelCase"
																		placeholder='Group Name' path="groupName" value=""
																		oninvalid="this.setCustomValidity('Please Enter Group Name')"
																		oninput="setCustomValidity('')" />
																	<!-- <div   class="help-block with-errors"></div> -->
																</div>




															</div>


														</div>
														<form class="form">
															<div class="form-body">

																<ul class="nav nav-tabs" id="myTab" role="tablist">
																	<li class="nav-item active"><a class="nav-link"
																		id="general-tab" data-toggle="tab" href="#general"
																		role="tab" aria-controls="general"
																		aria-selected="true">General</a></li>
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
																	<div class="tab-pane active" id="general"
																		role="tabpanel" aria-labelledby="general-tab">

																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Mobile</label>
																				<form:input path="mobileNo"
																					placeholder='Mobile Number'
																					  type="text" autocomplete="off"
																					class="form-control"
																					maxlength="13" minlength="10" onkeypress='return isNumericMobileKey(event);'
																					oninvalid="this.setCustomValidity('Please Enter Mobile Number')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>
																			
																			<div class="col-sm-1 form-group"> &nbsp; </div>

																			<div class="col-sm-4 form-group">
																				<label>Fax</label>
																				<form:input path="fax" placeholder='Fax number'
																					type="text" class="form-control" maxlength="15"
																					minlength="10" onkeypress='return isNumberKey(event);'
																					oninvalid="this.setCustomValidity('Please Enter Fax Number')"
																					oninput="setCustomValidity('')" />
																				<!--  <div   class="help-block with-errors"></div> -->
																			</div>



																		</div>

																		<div class="row">
																			<div class="col-md-4 form-group">
																				<label>Email</label>
																				<form:input path="emailId" type="text"
																					class="form-control" 
																					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
																					placeholder='Email Id'
																					oninvalid="this.setCustomValidity('Please Enter Valid Email')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors" ></div> -->
																			</div>
																			
																			<div class="col-sm-1 form-group"> &nbsp; </div>

																			<div class="col-md-4 form-group">
																				<label>Business Type</label>
																				<form:select path="businessPartnerType"
																					class="form-control"
																					oninvalid="this.setCustomValidity('Please Select Business Type')"
																					oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<form:option value="Company">Company</form:option>
																					<form:option value="Private">Private</form:option>
																					<form:option value="Employee">Employee</form:option>
																				</form:select>
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>

																		</div>


																		<!--Single Contact  -->

																		<c:if test="${vendor.id==null}">

																			<div class="row address-details-block">
																				<div class="col-xs-12 col-sm-12">
																					<div class="form-group">
																						<label>Contact Details</label>
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Contact Id</label><input type="text"
																							name="vendorContactDetails[0].contactId"
																							class="form-control camelCase" placeholder='Contact Id'
																							oninvalid="this.setCustomValidity('Please Enter Contact Id')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div>  -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Title</label> <select
																							name="vendorContactDetails[0].title" id="title0"
																							class="form-control " onchange="titleValidation(0)"  
																							oninvalid="this.setCustomValidity('Please Select Valid Title')"
																							oninput="setCustomValidity('')">
																							<option value="" selected disabled>Select</option>
																							<option value="Mr">Mr</option>
																							<option value="Ms">Ms</option>
																						</select>
																						<!--  <div   class="help-block with-errors" ></div> -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Name</label><input type="text"
																							class="form-control camelCase" placeholder='Contact Name'
																							name="vendorContactDetails[0].contactName"
																							
																							oninvalid="this.setCustomValidity('Please Enter Contact Name')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div>  -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Address</label><input type="text"
																							class="form-control camelCase" placeholder='Address'
																							name="vendorContactDetails[0].address"
																							oninvalid="this.setCustomValidity('Please Enter Address')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div>  -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Mobile</label><input type="text"
																							class="form-control" maxlength="13" value="+91"
																							minlength="10" onkeypress='return isNumberKey(event);' placeholder='Mobile Number'
																							name="vendorContactDetails[0].mobileNo"
																							id="vendorContactDetailsMobileNo"
																							oninvalid="this.setCustomValidity('Please Enter Mobile')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div>  -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Fax</label><input type="text"
																							class="form-control" maxlength="15"
																					minlength="10" onkeypress='return isNumberKey(event);' placeholder='Fax Number'
																							name="vendorContactDetails[0].fax"
																							oninvalid="this.setCustomValidity('Please Enter Fax')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div>  -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Email</label><input type="text"
																							class="form-control" placeholder='Email Id'
																							name="vendorContactDetails[0].email"
																							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
																							placeholder='Enter Email'  
																							oninvalid="this.setCustomValidity('Please Enter Valid Email')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors" ></div> -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Gender</label> <select class="form-control"
																							name="vendorContactDetails[0].gender"
																							 id="gender0" onchange="genderValidation(0)"
																							oninvalid="this.setCustomValidity('Please Enter Gender')"
																							oninput="setCustomValidity('')">
																							<option value="" selected disabled>Select</option>
																							<option value="Male">Male</option>
																							<option value="Female">Female</option>
																						</select>
																						<!-- <div   class="help-block with-errors" ></div> -->
																					</div>
																				</div>

																				<!-- <div class="col-xs-12 col-sm-3"><div class="form-group"><label>&nbsp;&nbsp;&nbsp; </label><br><a class="remove btn btn-danger">Remove</a></div></div>
												 -->
																			</div>

																		</c:if>
																		<!--// Single Contact  -->

																		<div class="optionBox">



																			<!--Dynamic Load data  -->

																			<c:if test="${not empty vendor.vendorContactDetails}">
																				<%
																					int count = 0;
																				%>
																				<c:forEach items="${vendor.vendorContactDetails}"
																					var="listContactDetails">

																					<div class="row address-details-block">
																						<div class="col-xs-12 col-sm-12">
																							<div class="form-group">
																								<label>Add Contact Details</label>
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Contact Id</label><input type="text"
																									value="${listContactDetails.contactId}"
																									name="vendorContactDetails[<%= count %>].contactId"
																									class="form-control camelCase" placeholder='Contact Id'
																									oninvalid="this.setCustomValidity('Please Enter Contact Id')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->

																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Title</label> <select
																									class="form-control"
																									name="vendorContactDetails[<%=count%>].title"
																									oninvalid="this.setCustomValidity('Please Select Title')"
																									oninput="setCustomValidity('')">
																									<option value="" selected disabled>Select</option>
																									<c:forTokens items="Mr,Ms" delims=","
																										var="titleName">
																										<c:choose>
																											<c:when
																												test="${titleName == listContactDetails.title}">
																												<option value="${titleName}" selected><c:out
																														value="${titleName}" /></option>
																											</c:when>
																											<c:otherwise>
																												<option value="${titleName}"><c:out
																														value="${titleName}" /></option>
																											</c:otherwise>
																										</c:choose>
																									</c:forTokens>
																								</select>
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Name</label><input type="text"
																									class="form-control camelCase"
																									value="${listContactDetails.contactName}"
																									name="vendorContactDetails[<%= count %>].contactName"
																									placeholder='Contact Name' required="true"
																									oninvalid="this.setCustomValidity('Please Contact Name')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Address</label><input type="text"
																									class="form-control camelCase"
																									value="${listContactDetails.address}"
																									name="vendorContactDetails[<%= count %>].address"
																									placeholder='Address'  
																									oninvalid="this.setCustomValidity('Please Enter Address')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Mobile</label><input type="text"
																									class="form-control" maxlength="13"
																									minlength="10" onkeypress='return isNumberKey(event);'
																									value="${listContactDetails.mobileNo}"
																									name="vendorContactDetails[<%= count %>].mobileNo"
																									 
																									placeholder='Mobile Number'  
																									oninvalid="this.setCustomValidity('Please Mobile Number')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Fax</label><input type="text"
																									class="form-control"
																									value="${listContactDetails.fax}"
																									name="vendorContactDetails[<%= count %>].fax"
																									maxlength="15" minlength="10" 
																					                onkeypress='return isNumberKey(event);'
																									placeholder='Fax Number'  
																									oninvalid="this.setCustomValidity('Please Enter FAX')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Email</label><input type="text"
																									class="form-control"
																									value="${listContactDetails.email}"
																									name="vendorContactDetails[<%= count %>].email"
																									placeholder='Email Id'  
																									oninvalid="this.setCustomValidity('Please Enter Email Id')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Gender</label> <select
																									class="form-control"
																									value="${listContactDetails.gender}"
																									name="vendorContactDetails[<%= count %>].gender"
																									oninvalid="this.setCustomValidity('Please Select Gender')"
																									oninput="setCustomValidity('')">
																									<option value="" selected disabled>Select</option>
																									<c:forTokens items="Male,Female" delims=","
																										var="genderName">
																										<c:choose>
																											<c:when
																												test="${genderName == listContactDetails.gender}">
																												<option value="${genderName}" selected><c:out
																														value="${genderName}" /></option>
																											</c:when>
																											<c:otherwise>
																												<option value="${genderName}"><c:out
																														value="${genderName}" /></option>
																											</c:otherwise>
																										</c:choose>
																									</c:forTokens>
																								</select>
																								<!-- <div   class="help-block with-errors"></div> -->

																							</div>
																						</div>

																						<!-- <div class="col-xs-12 col-sm-4">
												<div class="form-group">
												
												<a class="remove btn btn-danger">Remove</a>
												</div>
												</div> -->
																					</div>


																					<%
																						count = count + 1;
																					%>
																				</c:forEach>

																				<input type="hidden" id="addressCount"
																					value="<%=count%>">
																				<input type="hidden" id="addressIndexCount"
																					value="<%=count%>">

																			</c:if>
																			<input type="hidden" id="indexValue">
																			<!--//Dynamic Load data  -->

																			<div class="block">
																				<p Class="error">
																					<a class="add btn btn-success flat btn-responsive"><i
																						class="fa fa-plus btni_left"></i>Add Contact
																						Persons</a>
																				</p>
																			</div>
																		</div>

																		<!-- Add multiple  -->

																	</div>
																	<div class="tab-pane" id="profile" role="tabpanel"
																		aria-labelledby="profile-tab">


																		<!--Single Address  -->
																		<c:if test="${vendor.id==null}">
                                                                           <c:set var="count" value="0" scope="page" />
																			<div class="row address-details-block1${count}">

																				<div>
																					<div class="col-md-12">
																						<div class="col-md-7">
																							<div class="form-group">
																								<label>Vendor Address Details</label>
																							</div>
																						</div>
																						<div class="col-md-3">

																							<label class="checkbox-inline"><input
																								type="checkbox" checked="checked"
																								name="vendorAddress[${count}].payTo" id="payToAdd0"
																								value="payTo">Pay To</label> &nbsp; &nbsp;
																							&nbsp; <label class="checkbox-inline"><input
																								type="checkbox" checked="checked" name="vendorAddress[${count}].shipFrom"
																								id="shipFromAdd0" value="shipFrom">Ship
																								From</label>
																						</div>
																					</div>
																				</div>


																				<div class="col-xs-12 col-sm-4">
																				<div class="form-group">
																						<label>Address-1</label> <input type="text"
																							name="vendorAddress[${count}].addressName"
																							class="form-control Address-1 camelCase" placeholder='Address-1'
																							required="true"
																							oninvalid="this.setCustomValidity('Please Enter Address')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div> -->
																					</div>
																					
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Address-2</label> <input type="text"
																							name="vendorAddress[${count}].addressId"
																							class="form-control camelCase" placeholder='Address-2'
																							oninvalid="this.setCustomValidity('Please Enter Address Id')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div> -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Street/PO Box</label> <input type="text"
																							name="vendorAddress[${count}].street"
																							class="form-control camelCase" placeholder='Street/PO Box'
																							oninvalid="this.setCustomValidity('Please Enter Street')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div> -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>City</label> <input type="text"
																							name="vendorAddress[${count}].city" class="form-control camelCase"
																							placeholder='City'  required="true"
																							oninvalid="this.setCustomValidity('Please Enter City')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div> -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Zipcode</label> <input type="text"
																							name="vendorAddress[${count}].zipCode"
																							class="form-control"
																							onkeypress='return isZipcodeKey(event);'
																							maxlength='6' minlength='6'
																							placeholder='Zip Code'
																							oninvalid="this.setCustomValidity('Please Enter Zip Code')"
																							oninput="setCustomValidity('')" />
																						<!-- <div   class="help-block with-errors"></div> -->
																					</div>
																				</div>

																				<!-- <div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Street#</label> <input type="text"
																							name="vendorAddress[0].streetNo"
																							class="form-control camelCase" placeholder='Street Number'
																							oninvalid="this.setCustomValidity('Please Enter Street Number')"
																							oninput="setCustomValidity('')" />
																						<div   class="help-block with-errors"></div>
																					</div>
																				</div> -->
																				
																				 <div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>State</label> <select
																							name="vendorAddress[${count}].states.id"  required="true"
																							class="form-control">
																							<option value="">--Select--</option>
																							<c:forEach items="${stateList}" var="statesList">
																						    <%-- <option value="${states.id}">${states.name}</option> --%>
																						    <c:choose>
																							<c:when test="${vendor.vendorAddress!=null}">
																							<option value="${vendorAddress[0].states.name}">${vendorAddress[0].states.name}</option>
																							</c:when>
																							 <c:otherwise>
																								<option value="${statesList.id}">${statesList.name}</option>
																							</c:otherwise> 
																							</c:choose>
																							</c:forEach>
																						</select>
																					</div>
																				</div> 

																				  <div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>GSTIN </label> <input type="text"
																							name="vendorAddress[${count}].gstin"
																							class="form-control" placeholder='GSTIN'
																							maxlength='15' minlength='15'
																							pattern="^[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}?$"
																							oninvalid="this.setCustomValidity('Please Enter GST IN')"
																							oninput="setCustomValidity('')" />
																						 
																					</div>
																				</div> 
																				

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>GST type</label> <select
																							name="vendorAddress[${count}].gstinType"
																							class="form-control"  
																							oninvalid="this.setCustomValidity('Please Enter GST Type')"
																							oninput="setCustomValidity('')">
																							<option value="" selected disabled>select</option>
																							<option value="Regular">Regular</option>
																							<option value="Excisible">Excisible</option>
																							<option value="Regular">UIN Agency or
																								Embasyy</option>
																							<option value="Excisible">Composition
																								Levy</option>
																							<option value="Regular">Non-Resident
																								Taxable person</option>
																							<option value="Excisible">Casual Taxable
																								Person</option>
																						</select>
																						<!-- <div   class="help-block with-errors"></div> -->
																					</div>
																				</div>

																				<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>Country</label> <select
																							name="vendorAddress[${count}].country"
																							class="form-control">
																							<option value="${country.id}">${country.name}</option>
																						</select>
																					</div>
																				</div>

																			</div>
																		</c:if>
																		<c:set var="count" value="${count + 1}"
																													scope="page" />
																		<!--// Single Address  -->
                                                                       
																		<div class="optionBox1">


																			<!--1 multiply Dynamically Load   -->
																			<c:if test="${not empty vendor.vendorAddress}">
																				<%
																					int count1 = 0;
																				%>
																				<c:forEach items="${vendor.vendorAddress}"
																					var="listAddressDetails">
																					<div class="row address-details-block1">
																						<div >
																							<div class="col-md-12">
																								<div class="col-md-7">
																									<div class="form-group">
																										<label>Vendor Address Details</label>
																									</div>
																								</div>
																								<div class="col-md-3">

																									<label class="checkbox-inline"> <input
																										type="checkbox" id="payToAdd<%=count1 %>"
																										name="vendorAddress[<%=count1 %>].payTo"
																										<c:if test="${listAddressDetails.payTo!=null}">
												 						checked
												 						 </c:if>
																										value="payTo">Pay To
																									</label> &nbsp; &nbsp; &nbsp; <label
																										class="checkbox-inline"><input
																										type="checkbox" id="shipFromAdd<%=count1 %>"
																										name="vendorAddress[<%=count1 %>].shipFrom"
																										<c:if test="${listAddressDetails.shipFrom!=null}">
												 						checked
												 						 </c:if>
																										value="shipFrom">Ship From</label>
																								</div>
																							</div>
																						</div>
																						
																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Address-1</label> <input type="text"
																									value="${listAddressDetails.addressName}" id="address-1"
																									name="vendorAddress[<%=count1 %>].addressName"
																									class="form-control Address-1 camelCase" placeholder='Address-1'
																									required="true"
																									oninvalid="this.setCustomValidity('Please Enter Address Name')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Address-2</label> <input type="text"
																									value="${listAddressDetails.addressId}"
																									name="vendorAddress[<%=count1 %>].addressId"
																									class="form-control camelCase" placeholder='Address-2'
																									oninvalid="this.setCustomValidity('Please Enter Address Id')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Street/PO Box</label> <input type="text"
																									value="${listAddressDetails.street}"
																									name="vendorAddress[<%=count1 %>].street"
																									class="form-control camelCase"
																									placeholder='Street/PO Box'
																									oninvalid="this.setCustomValidity('Please Enter Street')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>City</label> <input type="text"
																									value="${listAddressDetails.city}"
																									name="vendorAddress[<%=count1 %>].city"
																									class="form-control" placeholder='City' required="true"
																									oninvalid="this.setCustomValidity('Please Enter City')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Zipcode</label> <input type="text"
																									value="${listAddressDetails.zipCode}"
																									name="vendorAddress[<%=count1 %>].zipCode"
																									class="form-control" onkeypress='return isZipcodeKey(event);'
																									placeholder='Zipcode'  
																									oninvalid="this.setCustomValidity('Please Enter Zip Code')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<%-- <div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Street#</label> <input type="text"
																									value="${listAddressDetails.streetNo}"
																									name="vendorAddress[<%=count1 %>].streetNo"
																									class="form-control"
																									placeholder='Street Number'
																									oninvalid="this.setCustomValidity('Please Enter Street Number')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div> --%>
																						
																						<div class="col-xs-12 col-sm-4">
																					<div class="form-group">
																						<label>State</label> <select
																							name="vendorAddress[<%=count1%>].states.id" required="true"
																							class="form-control">
																							
																							  <option value="">Select</option> 
																						   <c:forEach items="${stateMap}" var="statesList">
																						    <c:choose>
																							<c:when test="${statesList.key == listAddressDetails.states.id}">
																							<option value="${statesList.key}" selected >${statesList.value}</option>
																							</c:when>
																							 <c:otherwise>
																								<option value="${statesList.key}">${statesList.value}</option>
																							</c:otherwise> 
																							</c:choose>
																							</c:forEach>
																							 
																						</select>
																					</div>
																				</div> 

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>GSTIN </label> <input type="text"
																									value="${listAddressDetails.gstin}"
																									name="vendorAddress[<%=count1 %>].gstin"
																									class="form-control" placeholder='GSTIN'
																									required="true" maxlength='15' minlength='15'
																									pattern="^[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}?$"
																									oninvalid="this.setCustomValidity('Please Enter GST IN')"
																									oninput="setCustomValidity('')" />
																								<!-- <div   class="help-block with-errors"></div> -->
																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>GST type</label> <select
																									class="form-control"
																									name="vendorAddress[<%=count1%>].gstinType"
																									required="true"
																									oninvalid="this.setCustomValidity('Please Enter GST Type')"
																									oninput="setCustomValidity('')">
																									<option value="" selected disabled>Select</option>
																									<c:forTokens
																										items="Regular,Excisible,Regular,Excisible,UIN Agency or Embasyy,Composition Levy,Non-Resident Taxable person,Casual Taxable Person"
																										delims="," var="gstType">
																										<c:choose>
																											<c:when
																												test="${gstType == listAddressDetails.gstinType}">
																												<option value="${gstType}" selected><c:out
																														value="${gstType}" /></option>
																											</c:when>
																											<c:otherwise>
																												<option value="${gstType}"><c:out
																														value="${gstType}" /></option>
																											</c:otherwise>
																										</c:choose>
																									</c:forTokens>
																								</select>
																								<!-- <div   class="help-block with-errors"></div> -->

																							</div>
																						</div>

																						<div class="col-xs-12 col-sm-4">
																							<div class="form-group">
																								<label>Country</label> <select
																									name="vendorAddress[<%=count1%>].country"
																									class="form-control">
																									<option value="${country.id}">${country.name}</option>
																								</select>
																							</div>
																						</div>

																					</div>



																					<%
																						count1 = count1 + 1;
																					%>
																				</c:forEach>

																				<input type="hidden" id="addressCount1"
																					value="<%=count1%>">
																				<input type="hidden" id="addressIndexCount1"
																					value="<%=count1%>">

																			</c:if>
																			<input type="hidden" id="indexValue1">

																			<div class="block1">
																				<!--1 // multiply Dynamically Load   -->

																				<p Class="error">
																					<a class="add1 btn btn-success flat btn-responsive"><i
																						class="fa fa-plus btni_left"></i>Add Address</a>
																				</p>
																			</div>
																		</div>
																		<!--Pay - Shipping  -->

																	</div>
																	<div class="tab-pane" id="messages" role="tabpanel"
																		aria-labelledby="messages-tab">
																		<!--Payment  -->

																		<div class="row">

																			<div class="col-sm-4 form-group">
																				<label>Payment</label>
																				<form:input path="paymentTerms"
																					placeholder='Payment Terms' type="text"
																					class="form-control camelCase"  
																					oninvalid="this.setCustomValidity('Please Enter Payment Terms')"
																					oninput="setCustomValidity('')" />
																				<!--  <div   class="help-block with-errors"></div> -->
																			</div>

																			<div class="col-sm-4 form-group">
																				<label>Credit Limit</label>
																				<form:input path="creditLimit"
																					placeholder='Credit Limit' type="text"
																					class="form-control"
																					onkeypress="return isNumericKey(event)"
																					oninvalid="this.setCustomValidity('Please Enter Credit Limit')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>

																			<div class="col-sm-4 form-group">
																				<label>Commitment</label>
																				<form:input path="commitmentLimit"
																					placeholder='Commitment Limit' type="text"
																					class="form-control"
																					onkeypress="return isNumericKey(event)"
																					oninvalid="this.setCustomValidity('Please Enter Commitment Limit')"
																					oninput="setCustomValidity('')" />
																				<!--  <div   class="help-block with-errors"></div> -->
																			</div>
																		</div>



																		<div class="row">

																			<div class="col-sm-4 form-group">
																				<label>Bank Country</label>
																				<form:select path="bankCountry" id="selectSubcat"
																					cssClass="form-control">
																					<form:option value="${country.id}">${country.name}</form:option>
																				</form:select>

																			</div>

																			<div class="col-sm-4 form-group">
																				<label>Bank Name</label>
																				<form:input path="bankName"
																					placeholder='Bank AccountName' type="text"
																					class="form-control camelCase"  
																					oninvalid="this.setCustomValidity('Please Enter Bank Name')"
																					oninput="setCustomValidity('')" />
																				<!--  <div   class="help-block with-errors"></div> -->
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>IFSC Code</label>
																				<form:input path="bankCode" placeholder='IFSC Code'
																					pattern="[a-zA-Z]{4}[0-9]{7}" type="text"
																					class="form-control"  maxlength="11"
																					minlength="11"
																					oninvalid="this.setCustomValidity('Please Enter Bank Code')"
																					oninput="setCustomValidity('')" />
																				<!--   <div   class="help-block with-errors"></div> -->
																			</div>


																		</div>

																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Branch</label>
																				<form:input path="branch" placeholder='Branch Name'
																					type="text" class="form-control camelCase"  
																					oninvalid="this.setCustomValidity('Please Enter Branch Name')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Account#</label>
																				<form:input path="accountId" minlength='9'
																					maxlength='18' placeholder='Account Number'
																					type="text" class="form-control"
																					onkeypress="return isNumericKey(event)"
																					oninvalid="this.setCustomValidity('Please Enter Account Id')"
																					oninput="setCustomValidity('')" />
																				<!--  <div   class="help-block with-errors"></div> -->
																			</div>

																			<div class="col-sm-4 form-group">
																				<label>Account Name</label>
																				<form:input path="bankAccountName"
																					placeholder='Account Holder Name' type="text"
																					class="form-control camelCase"  
																					oninvalid="this.setCustomValidity('Please Enter Bank Account Name')"
																					oninput="setCustomValidity('')" />
																				<!--  <div   class="help-block with-errors"></div> -->
																			</div>



																		</div>



																		<!--Payment  -->
																	</div>
																</div>



															</div>
														</form>
														<br>
														<div class="text-xs-center">

															<a href="#" onclick="goBack()"
																class="btn btn-primary float-left"> Back</a> <a
																href="<c:url value ="/vendor/list"/>">
																<button type="button" class="btn btn-warning mr-1">
																	<i class="icon-cross2"></i> Cancel
																</button>
															</a>

															<c:if test="${vendor.id==null}">
																<button type="submit" class="btn btn-primary">
																	<i class="icon-check2"></i> Save
																</button>
															</c:if>


															<c:if test="${vendor.id!=null}">
																<button type="submit" class="btn btn-primary">
																	<i class="icon-check2"></i> Update
																</button>
															</c:if>

														</div>


													</div>
												</div>

											</div>

										</div>
									</div>
								</section>

							</form:form>

						</div>
						<!--  <a class="btn btn-primary" href="/users/4">Back</a> -->
						<br> <br>

						<!-- // Basic form layout section end -->
					</div>

				</div>
				<!--/ project charts -->
				<br>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/jsp/footer.jsp" />
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	<%--  <c:import url="/WEB-INF/jsp/loadJs.jsp" />  --%>
</body>
<script type="text/javascript">

	$('.add')
			.click(
					function() {
						var a = $('#addressIndexCount').val();
						var addressCount = $('#addressCount').val();
						var index = $('#indexValue').val();
						var inc=1;
						//alert("a"+a);
						//alert("addressCount"+addressCount);
						//alert("index"+index);
						if (a != undefined ) {
							if (parseInt(a) != parseInt(addressCount)) {
								inc = parseInt(a) ;
								$('#addressIndexCount').val(inc+1);
							} else {
								inc = parseInt(a);
								$('#addressIndexCount').val(parseInt(inc) + 1);
							}
						} else {
							//alert("index-->"+inc);
							if (index == "" || index == null || index == NaN) {
								inc = 1;
								$('#indexValue').val("1");
							} else {
								inc = parseInt(index) + 1;
								$('#indexValue').val(inc);
							}
						}

						//alert("imc-->"+inc);
						$('.block:last')
								.before('<div class="row address-details-block" >'
												+ '<div class="col-xs-12 col-sm-12">'
												+ '<div class="form-group"><label>Contact Details</label></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Contact Id</label><input type="text" placeholder="Contact Id" name="vendorContactDetails['+inc+'].contactId" class="form-control camelCase"    oninvalid="this.setCustomValidity(\'Please Enter Contact Id\')" oninput="setCustomValidity(\'\')" > </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Title</label><select name="vendorContactDetails['+inc+'].title" id="title'+inc+'"  onchange="titleValidation('+inc+')"  class="form-control"   oninvalid="this.setCustomValidity(\'Please Select Title\')" oninput="setCustomValidity(\'\')" >'
												+ '<option value="" selected disabled >select</option><option value="Mr">Mr</option><option value="Ms">Ms</option></select> <div   class="help-block with-errors" ></div></div></div>'
												
												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Name</label><input type="text" placeholder="Contact Name" name="vendorContactDetails['+inc+'].contactName" class="form-control camelCase"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Contact Name\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Address</label><input type="text" placeholder="Address" name="vendorContactDetails['+inc+'].address" class="form-control camelCase"  oninvalid="this.setCustomValidity(\'Please Enter Address\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Mobile</label><input type="text" maxlength="13" minlength="10" onkeypress="return isNumberKey(event);" placeholder="Mobile Number" value="+91"  name="vendorContactDetails['+inc+'].mobileNo" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Mobile Number\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Fax</label><input type="text" maxlength="15" minlength="10" onkeypress="return isNumberKey(event);" placeholder="Fax Number" name="vendorContactDetails['+inc+'].fax" class="form-control"   oninvalid="this.setCustomValidity(\'Please Enter  Fax Number\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Email</label><input type="text" placeholder="Email Id" name="vendorContactDetails['+inc+'].email" class="form-control"    oninvalid="this.setCustomValidity(\'Please Enter Email \')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Gender</label><select class="form-control" name="vendorContactDetails['+inc+'].gender"  id="gender'+inc+'"   onchange="genderValidation('+inc+')"  oninvalid="this.setCustomValidity(\'Please Select Gender\')" oninput="setCustomValidity(\'\')"    >'
												+ '<option value="" selected disabled >select</option>'
												+ '<option value="Male">Male</option>'
												+ '<option value="Female">Female</option>'
												+ '</select></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><a class="remove btn btn-danger float-right">Remove</a></div></div></div>');
					
						$("#form").validator("update");
					});

	$('.optionBox').on('click', '.remove', function() {
		$(this).parents(".address-details-block").remove();
		$("#form").validator("update");
	});
	
</script>



<script type="text/javascript">
var inc1=1;
	$('.add1')
			.click(
					function() {
						var a = $('#addressIndexCount1').val();
						var addressCount = $('#addressCount1').val();
						var index = $('#indexValue1').val();
						
						if (a != undefined ) {
							if (parseInt(a) != parseInt(addressCount)) {
								inc1 = parseInt(a) ;
								$('#addressIndexCount1').val(inc1+1);
							} else {
								inc1 = parseInt(a);
								$('#addressIndexCount1').val(parseInt(inc1) + 1);
							}
						} else {
							//alert("index-->"+inc1);
							if (index == "" || index == null || index == NaN) {
								inc1 = 1;
								$('#indexValue1').val("1");
							} else {
								inc1 = parseInt(index) + 1;
								$('#indexValue1').val(inc1);
							}
						}

						$('.block1:last')
								.before('<div class="row address-details-block1" >'
												+ '<div ><div class="col-md-12"><div class="col-md-7"><div class="form-group">'
												+ '<label>VendorAddress Details</label></div></div><div class="col-md-3">  '
												
												+ '<label class="checkbox-inline"><input type="checkbox" id="payToAdd'+inc1+'"  name="vendorAddress['+inc1+'].payTo"  value="payTo">Pay To</label>'
												+ '&nbsp; &nbsp; &nbsp; <label class="checkbox-inline"><input type="checkbox" id="shipFromAdd'+inc1+'"  checked="checked" name="vendorAddress['+inc1+'].shipFrom" value="shipFrom">Ship From</label>'
												
												+ '</div><div class="col-md-2"> <a class="remove1 btn btn-danger">Remove</a></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Address-1</label>'
												+ '<input type="text" placeholder="Address-1" name="vendorAddress['+inc1+'].addressName" id="address-1['+inc1+']" class="form-control Address-1 Address-1 '+inc1+'camelCase"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Address\')" oninput="setCustomValidity(\'\')" >  </div></div>'
																											
												+'<div class="col-xs-12 col-sm-4"><div class="form-group">'
												+ '<label>Address-2</label><input type="text" placeholder="Address-2" name="vendorAddress['+inc1+'].addressId" class="form-control camelCase"     oninvalid="this.setCustomValidity(\'Please Enter Address Id\')" oninput="setCustomValidity(\'\')" >  </div></div>'
												
												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Street/PO Box</label>'
												+ '<input type="text" placeholder="Street/PO Box" name="vendorAddress['+inc1+'].street" class="form-control camelCase"    oninvalid="this.setCustomValidity(\'Please Enter Street\')" oninput="setCustomValidity(\'\')" > </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>City</label>'
												+ '<input type="text" placeholder="City" name="vendorAddress['+inc1+'].city" class="form-control camelCase"  required="true"   oninvalid="this.setCustomValidity(\'Please Enter City\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Zipcode</label>'
												+ '<input type="text" placeholder="Zip Code" name="vendorAddress['+inc1+'].zipCode" maxlength="6" minlength="6" class="form-control" onkeypress="return isZipcodeKey(event);"  oninvalid="this.setCustomValidity(\'Please Enter Zip Code\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												/* +'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Street#</label>'
												+ '<input type="text" placeholder="Street Number" name="vendorAddress['+inc1+'].streetNo" class="form-control camelCase"   oninvalid="this.setCustomValidity(\'Please Enter Street Number\')" oninput="setCustomValidity(\'\')" >  </div></div>' */
												
												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>State</label>'
												+ '<select name="vendorAddress['+inc1+'].states.id" required="true" class="form-control">'
												+ '<option value="">--Select--</option>' + <c:forEach items="${stateList}" var="states"> '<option value="${states.id}">${states.name}</option>'+ </c:forEach> '</select> </div></div>'
												
												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>GSTIN </label>'
												+ '<input type="text" placeholder="GSTIN" name="vendorAddress['+inc1+'].gstin" class="form-control" maxlength="15" minlength="15" pattern="^[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}?$"  oninvalid="this.setCustomValidity(\'Please Enter GST IN\')" oninput="setCustomValidity(\'\')" >  </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>GST type</label>'
												+ '<select name="vendorAddress['+inc1+'].gstinType" class="form-control"  oninvalid="this.setCustomValidity(\'Please Select GST type\')" oninput="setCustomValidity(\'\')"   >'
												+ '<option selected disabled value="">select</option><option value="Regular">Regular</option>'
												+ '<option value="Excisible">Excisible</option>'
												+ '<option value="Regular">UIN Agency or Embasyy</option>'
												+ '<option value="Excisible">Composition Levy</option>'
												+ '<option value="Regular">Non-Resident Taxable person</option>'
												+ '<option value="Excisible">Casual Taxable Person</option></select></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Country</label>'
												+ '<select name="vendorAddress['+inc1+'].country" class="form-control">'
												+ '<option value="${country.id}">${country.name}</option></select></div></div></div>'

												+'</div></div>');
						 
						$("#form").validator("update");
					});

	$('.optionBox1').on('click', '.remove1', function() {
		$(this).parents(".address-details-block1").remove();
		$("#form").validator("update");
	});
	
	
	
	  $('form.commentForm').on('submit', function(event) {
		var pay_flag=0;
		var ship_flag=0;
		//alert(inc1);
		  for(var k=0;k<=inc1;k++) {
			if($('#payToAdd'+k).prop('checked') == true){
				pay_flag++;
			}
			if($('#shipFromAdd'+k).prop('checked') == true){
				ship_flag++
			}
		 }
		// alert("pay_flag-->"+pay_flag + "ship_flag"+ship_flag);
		  if(pay_flag!=0 && ship_flag!=0){
			 return true;
		  }else {
			  alertify.alert("Vendor",'Please Add Atleast One PayTo And One ShipFrom Address');
			 return false;
		  } 
		 
  });   
 	
	
	
	function isValidName(nameId,url,displayId,msg){
	      
	      
	      var parts = url.split('/');
	      
	      var answer = parts[parts.length - 1];
	    /*   alert(answer); */
	      
	      var dataString  ="name="+$('#'+nameId).val();
	      //alert(dataString); 
	      $.ajax({
	             type:"GET",
	            /*  url: url, */
	              url: answer,
	             data : dataString,
	             success: function(result){
	                 if(result==true){
	                     alertify.success(msg);
	                     $('#'+nameId).val('');
	                     $('#'+displayId).html(msg);
	                 }else {
	                     $('#'+displayId).html('');
	                 }
	            }});
	      
	 }
	function isNumericKey(evt)
	{
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 
		&& (charCode < 48 || charCode > 57))
		return false;
		return true;
	}  
	
	  function goBack() {
	      window.history.back();
	  }
	 
	   function isNumberKey(evt) {
		    var charCode = (evt.which) ? evt.which : event.keyCode;
		    console.log(charCode);
		    if (charCode > 31 && (charCode < 48 || charCode > 57))
				   return false;

		    return true;
		} 
	  
		var id = $('#id').val();
		if (id == '') {
			$("#mobileNo").val("+91");
		}
	  function isNumericMobileKey(evt)
		{
			var data = $("#mobileNo").val();
			var first_pos = data.charAt(0);
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if(first_pos=="" && charCode==43)
				return true;
			if (charCode > 31 && (charCode < 48 || charCode > 57))
			   return false;
			
			return true;
		}
	
	  function isZipcodeKey(evt) {
		    var charCode = (evt.which) ? evt.which : event.keyCode;
		    console.log(charCode);
		    if (charCode < 48 || charCode > 57)
		        return false;

		    return true;
		}
	
	 
  	  
  	  function titleValidation(index) {
  		 var val = $("#title"+index).val();
  		 if(val=='Mr') {
  		$("#gender"+index+" option[value='Male']").attr('selected', true);
  	    $("#gender"+index+" option[value='Female']").attr('selected', false);
  		}else {
  	    $("#gender"+index+" option[value='Female']").attr('selected', true);
  	  $("#gender"+index+" option[value='Male']").attr('selected', false);
  		}
  	  }
	 
  	 function genderValidation(index) {
  		 var val = $("#gender"+index).val();
  		 if(val=='Male') {
  		$("#title"+index+" option[value='Mr']").attr('selected', true);
  		 $("#title"+index+" option[value='Ms']").attr('selected', false);
  		}else {
  	    $("#title"+index+" option[value='Ms']").attr('selected', true);
  	    $("#title"+index+" option[value='Mr']").attr('selected', false);
  		}
  	  }
	
</script>



<!-- alertifyjs -->
<link
	href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
	rel="stylesheet" type="text/css" />
<script
	src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/>
	type="text/javascript"></script>
	
<!-- alertfy-->

<c:import url="/WEB-INF/jsp/loadJs.jsp" /> 
<script>
$(document).ready(function(){
	$.noConflict();
});
</script>
</html>