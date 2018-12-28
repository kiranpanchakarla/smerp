
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


<script
	src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/>
	type="text/javascript"></script>
<!-- <script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script> -->
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>

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
							<c:url value="/company/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}"
								enctype="multipart/form-data" modelAttribute="company"
								data-toggle="validator" role="form" class="bv-form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<c:if test="${company.name==null}">
														<h2 class="card-title" id="basic-layout-icons">Create New Company</h2>
													</c:if>

													<c:if test="${company.name!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update Company Details</h2>
													</c:if>
													
													<form:input type="hidden" cssClass="form-control" path="id" />
													<!-- 	<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a> -->
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Company Name' path="name"
																		onchange="isValidName('name','/company/isValidCompanyName','1_errorContainer','Company Name Already Exists')"
																		required="true"
																		oninvalid="this.setCustomValidity('Please Enter Company Name')"
																		oninput="setCustomValidity('')" />
																	<!-- <div  id="1_errorContainer"  class="help-block with-errors"></div>  -->
																</div>

																<div class="col-sm-4 form-group">
																	<label>Tagline</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Company TagLine' path="companyTagLine"
																		oninvalid="this.setCustomValidity('Please Enter Company TagLine')"
																		oninput="setCustomValidity('')" />
																	<!-- <div   class="help-block with-errors"></div> -->
																</div>

																<div class="col-sm-4 form-group">
																	<div class="col-sm-12 no-padding">
																		<label>Logo</label>
																		<%-- <form:input type="file" cssClass="form-control"  accept="image/*" onchange="loadFile(event)" 
																		path="logo" value="${company.logo}" /> --%>

																		<div class="logo-upload">
																			<input type="file" name="file" id="file"
																				value="${company.logo}"
																				onchange="return fileValidation(event)"
																				oninvalid="this.setCustomValidity('Please Upload Image')"
																				oninput="setCustomValidity('')" />

																			<form:input type="hidden" path="logo"
																				value="${company.logo}" />
																		</div>
																		<div class="logo-upload hidden">
																			<label for="file" style="cursor: pointer;"> <img
																				src="${contextPath}/resources/images/company/cameraIcon.png"></label>
																		</div>
																		<!-- <div  id="3_errorContainer"  class="help-block with-errors"></div> -->
																	</div>

																	<div class="col-sm-12 form-group no-margin">
																		<label class="hidden-box">&nbsp;</label>
																		<c:if test="${filePath==null}">
																			<div class="logo-show hidden">
																				<img
																					src="${contextPath}/resources/images/company/noImageUploaded.png"
																					alt="See" id="output" width="100" height="70" />
																			</div>
																			<%-- <img src="<c:url value="${contextPath}"/>"/> --%>
																		</c:if>

																		<c:if test="${filePath!=null}">
																			<div class="logo-show">
																				<img src="${filePath}" alt="See" id="output"
																					width="100" height="70" />
																			</div>
																		</c:if>

																	</div>

																</div>


															</div>


															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>GSTIN</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='GSTIN' path="gstinVat" required="true"
																		maxlength='15' minlength='15'
																		pattern="^[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}?$"
																		oninvalid="this.setCustomValidity('Please Enter Valid GSTIN Number Ex:22AABCE2207R1Z6')"
																		oninput="setCustomValidity('')" />
																	<!-- <div   class="help-block with-errors"></div> -->
																</div>

																<div class="col-sm-4 form-group">
																	<label>State Code</label>
																	<form:select path="statesCode.id" id="selectSubcat"
																		cssClass="form-control" required="true"
																		oninvalid="this.setCustomValidity('Please Select State ')"
																		oninput="setCustomValidity('')">
																		<form:option value="">--Select--</form:option>
																		<c:forEach items="${stateList}" var="stateList">

																			<form:option value="${stateList.id}">${stateList.stateCode} | ${stateList.name}</form:option>
																		</c:forEach>
																	</form:select>
																	<!-- <div   class="help-block with-errors"></div> -->
																</div>

																<div class="col-sm-4 form-group">
																	<label>Pan Number</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Pan Number' path="panNum" required="true"
																		pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}" maxlength="10"
																		minlength="10"
																		oninvalid="this.setCustomValidity('Please Enter PAN Number')"
																		oninput="setCustomValidity('')" />
																	<!-- <div   class="help-block with-errors"></div> -->
																</div>

															</div>

															<%-- <div class="row">
																
																<div class="col-sm-6 form-group">
																	<label>State Code</label>
																	<form:select path="statesCode.id" id="selectSubcat"
																		cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select State ')"	oninput="setCustomValidity('')">
																		<form:option value="">--Select--</form:option>
																		<c:forEach items="${stateList}" var="stateList">

																			<form:option value="${stateList.id}">${stateList.stateCode} | ${stateList.name}</form:option>
																		</c:forEach>
																	</form:select>
																	<div   class="help-block with-errors"></div>
																</div>
																
																<div class="col-sm-6 form-group">
																	<label>Pan Number</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Enter Pan Number' path="panNum"
																		required="true" maxlength="10" oninvalid="this.setCustomValidity('Please Enter PAN Number')"	oninput="setCustomValidity('')" />
																<div   class="help-block with-errors"></div>
																</div>
																
															</div> --%>

															<div class="card-header-in">
																<h4 class="card-title" id="basic-layout-icons">Address</h4>

															</div>

															<div class="card-body collapse in">
																<div class="card-block-in">

																	<div class="form-body">


																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Street-1</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Street1' path="street1"
																					required="true"
																					oninvalid="this.setCustomValidity('Please Enter Street1')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Street-2</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Street2' path="street2"
																					oninvalid="this.setCustomValidity('Please Enter Street2')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>

																			<div class="col-sm-4 form-group">
																				<label>City</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='City' path="city" required="true"
																					oninvalid="this.setCustomValidity('Please Enter City')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>
																		</div>



																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Mobile</label>

																				<form:input type="text"
																					cssClass="form-control "
																					placeholder='Mobile Number' path="phoneNum" autocomplete="off"
																					required="true" maxlength="13" minlength="10" onkeypress="return isNumericMobileKey(event)" 
																					oninvalid="this.setCustomValidity('Please Enter Phone Number')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Fax</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Fax Number' path="faxNum" onkeypress='return isNumberKey(event);'
																					maxlength="15" minlength="10" 
																					oninvalid="this.setCustomValidity('Please Enter Fax Number')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>

																			<div class="col-sm-4 form-group">
																				<label>Email</label>
																				<form:input type="email" cssClass="form-control"
																					onchange="isValidName('emailId','/company/isValidEmailId','2_errorContainer','Company Email Already Exists')"
																					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
																					placeholder='Email Id' path="emailId"
																					required="true"
																					oninvalid="this.setCustomValidity('Please Enter Valid Email')"
																					oninput="setCustomValidity('')" />
																				<!-- <div  id="2_errorContainer"  class="help-block with-errors" ></div> -->
																			</div>
																		</div>

																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>State</label>
																				<form:select path="states.id" id="selectSubcat"
																					cssClass="form-control" required="true"
																					oninvalid="this.setCustomValidity('Please Select State')"
																					oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${stateList}" var="stateList">

																						<form:option value="${stateList.id}">${stateList.name}</form:option>
																					</c:forEach>
																				</form:select>
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>
																			<div class="col-sm-4 form-group">
																				<label>Country</label>
																				<form:select path="country" id="selectSubcat"
																					cssClass="form-control">
																					<form:option value="${country.id}">${country.name}</form:option>
																				</form:select>

																			</div>

																			<div class="col-sm-4 form-group">
																				<label>ZIP Code</label>
																				<form:input type="text"
																					cssClass="form-control numericwithoutdecimal"
																					placeholder='ZIP Code' path="zipCode"
																					required="true" maxlength="6"
																					oninvalid="this.setCustomValidity('Please ZIP Code')"
																					oninput="setCustomValidity('')" />
																				<!-- <div   class="help-block with-errors"></div> -->
																			</div>




																		</div>

																		<div class="row">



																			<div class="col-sm-4 form-group">
																				<label>Website</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Website' path="webSite"
																					id="instmanageformid"
																					oninvalid="this.setCustomValidity('Please Enter WebSite')"
																					oninput="setCustomValidity('')" />
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																			<%-- <div class="col-sm-4 form-group">
																				<label>Landline</label>
																				<form:input type="text" cssClass="form-control numericwithoutdecimal"
																					placeholder='Landline Number' path="phoneNum" pattern="[0-9]+$"
																					required="true"    />
																	<!-- <div   class="help-block with-errors"></div> -->
																			</div> --%>

																		</div>


																	</div>

																	<div class="text-xs-center">

																		<a href="#" onclick="goBack()"
																			class="btn btn-primary float-left"> Back</a> <a
																			href="<c:url value="/company/list"/>">
																			<button type="button" class="btn btn-warning mr-1">
																				<i class="icon-cross2"></i> Cancel
																			</button>
																		</a>

																		<c:if test="${company.name!=null}">
																			<button type="submit" name="submitUpdate"
																				class="btn btn-primary" onclick="">
																				<i class="icon-check2"></i> Update
																			</button>
																		</c:if>

																		<c:if test="${company.name==null}">
																			<button type="submit" name="submitSave"
																				class="btn btn-primary">
																				<i class="icon-check2"></i> Save
																			</button>
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
									<!-- <div class="card-block">
										<a href="#" onclick="goBack()" class="btn btn-primary"
											style="float: left;"> Back</a>
									</div> -->
								</section>
							</form:form>

						</div>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/jsp/footer.jsp" />
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	<c:import url="/WEB-INF/jsp/loadJs.jsp" />



</body>

<script>
	/* var loadFile = function(event) {
	 var image = document.getElementById('output');
	 image.src = URL.createObjectURL(event.target.files[0]);
	 }; */

	var id = $('#id').val();
	
	if (id == '') {
		$("#phoneNum").val("+91");
	}

	function isNumericKey(evt)
	{
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57))
		   return false;
		
		return true;
	}
	
	
	function isNumericMobileKey(evt)
	{
		var data = $("#phoneNum").val();
		var first_pos = data.charAt(0);
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if(first_pos=="" && charCode==43)
			return true;
		if (charCode > 31 && (charCode < 48 || charCode > 57))
		   return false;
		
		return true;
	}
	
	function fileValidation(event) {
		var fileInput = document.getElementById('file');
		var filePath = fileInput.value;
		var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
		if (!allowedExtensions.exec(filePath)) {
			//alert();
			alertify.alert("Upload Image",'Please upload file having extensions .jpeg/.jpg/.png/.gif only.');
			fileInput.value = '';
			return false;
		} else {
			var image = document.getElementById('output');
			image.src = URL.createObjectURL(event.target.files[0]);
		}
	}
	/* function isNumberKey(evt) {
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    console.log(charCode);
	    if (charCode != 43 && charCode != 45 &&  charCode > 31
	        && (charCode < 48 || charCode > 57))
	        return false;

	    return true;
	} */
</script>
</html>

