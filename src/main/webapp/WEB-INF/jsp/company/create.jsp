
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
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">


	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />
	
	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>Company</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

						<div class="content-body">
							<!-- Basic form layout section start -->

							<form:form method="POST" action="/company/save"  enctype="multipart/form-data"
								modelAttribute="company" id="form-company" class="bv-form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
												<c:if test="${company.name==null}">
											<h4 class="card-title" id="basic-layout-icons">Company/Create</h4>
											   </c:if>
												
												<c:if test="${company.name!=null}">
											<h4 class="card-title" id="basic-layout-icons">Company/Update</h4>
											   </c:if>
											   
													
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>



												<div class="card-body collapse in">
													<div class="card-block">
														<div class="form-body">
															<input type="hidden" id="id" class="form-control"
																name="id" >
															<div class="row">
																<div class="col-sm-6 form-group">
																	<label>Company Name</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Company Name' path="name"
																		
																		data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#1_errorContainer" data-bv-stringlength-message="between-string-length"/>
																	<span class="scl-form-error-container" id="1_errorContainer"></span>
																		
																</div>
																
																<div class="col-sm-2 form-group">
																	<label>LOGO</label>${message}
																	 <%-- <form:input type="file" cssClass="form-control"  accept="image/*" onchange="loadFile(event)" 
																		path="logo" value="${company.logo}" /> --%>
																		
																<p>
																<input type="file"   name="file" id="file" value="${company.logo}"  onchange="loadFile(event)" style="display: none;"
			                                         data-bv-notempty="true" data-bv-notempty-message="Required"  data-bv-file="true" data-bv-file-message="Required"  data-bv-file-maxsize="2"   data-bv-container="#100_errorContainer" />

																<span class="scl-form-error-container" id="100_errorContainer"></span>
																<form:input  type="hidden" path="logo" value="${company.logo}" />
																</p>
															<p style="margin-top: -28px;"><label for="file" style="cursor: pointer;"><img src="${contextPath}/resources/images/company/cameraIcon.png"></label></p>
																</div>
																<div class="col-sm-4 form-group">
																<c:if test="${filePath==null}">
																			<p><img src="${contextPath}/resources/images/company/noImageUploaded.png" alt="See" id="output" width="100" height="70" /></p>
																<%-- <img src="<c:url value="${contextPath}"/>"/> --%>
																</c:if>
																
																<c:if test="${filePath!=null}">
																			<p><img src="${filePath}" alt="See" id="output" width="100" height="70" /></p>
																</c:if>
																	
																</div>
																
																
																
																
															</div>


															<div class="row">
																<div class="col-sm-6 form-group">
																	<label>VAT</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='VAT' path="gstinVat"
																		data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#3_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="3_errorContainer"></span>
																</div>
																
																<div class="col-sm-6 form-group">
																	<label>Company Tagline</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Company TagLine' path="companyTagLine"
																		data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#2_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="2_errorContainer"></span>
																</div>
																
																
															</div>
															
															
															<div class="row">
															<div class="col-sm-6 form-group">
																				<label>Tax ID</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter Tax ID' path="taxId"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#12_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="12_errorContainer"></span>
																			</div>
																			
																			
																			
																			<div class="col-sm-6 form-group">
																				<label>Company Registry</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter Company Registry'
																					path="companyRegisrory"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#14_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="14_errorContainer"></span>
																			</div>
															
															
															</div>
															



															<div class="row">
																
																<div class="col-sm-6 form-group">
																	<label>State Code</label>
																	<form:select path="states.id" id="selectSubcat"
																		cssClass="form-control">
																		<form:option value="0">--Select--</form:option>
																		<c:forEach items="${stateList}" var="stateList">

																			<form:option value="${stateList.id}">${stateList.name}</form:option>
																		</c:forEach>
																	</form:select>
																</div>
																
																<div class="col-sm-6 form-group">
																	<label>Pan Number</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Enter Pan Number' path="panNum"
																		data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#4_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="4_errorContainer"></span>
																</div>
																
															</div>
														
															<div class="card-header">
																<h4 class="card-title" id="basic-layout-icons">Address</h4>
																<a class="heading-elements-toggle"><i
																	class="icon-ellipsis font-medium-3"></i></a>
															</div>

															<div class="card-body collapse in">
																<div class="card-block">

																	<div class="form-body">


																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>Street-1</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='street1' path="street1"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#5_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="5_errorContainer"></span>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Phone</label>
																				<form:input type="text" cssClass="form-control numericwithoutdecimal"
																					placeholder='Enter Phone No' path="phoneNum"
																					 data-bv-notempty="true" data-bv-notempty-message="Required"  data-bv-container="#6_errorContainer" />
																		<span class="scl-form-error-container" id="6_errorContainer"></span>
																			</div>
																		</div>


																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>Street-2</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Street2' path="street2"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#7_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="7_errorContainer"></span>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Fax</label>
																				<form:input type="text" cssClass="form-control numericwithoutdecimal"
																					placeholder='Enter fax Num' path="faxNum"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#8_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="8_errorContainer"></span>
																			</div>
																		</div>


																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>City</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter City' path="city"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#9_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="9_errorContainer"></span>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Email</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter Email' path="emailId"
																				data-bv-notempty="true" data-bv-notempty-message="Required" 	data-bv-emailaddress="true"   data-bv-emailaddress-message="The value is not a valid email address"  data-bv-container="#10_errorContainer" />
																		<span class="scl-form-error-container" id="10_errorContainer"></span>
																			</div>
																		</div>

																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>State</label>
																				<form:select path="states.id" id="selectSubcat"
																					cssClass="form-control">
																					<form:option value="0">--Select--</form:option>
																					<c:forEach items="${stateList}" var="stateList">

																						<form:option value="${stateList.id}">${stateList.name}</form:option>
																					</c:forEach>
																				</form:select>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Country</label>
																				 <form:select path="country" id="selectSubcat"
																					cssClass="form-control">
																						<form:option value="${country.id}">${country.name}</form:option>
																				</form:select> 
																				
																				 <!-- <select class="form-control">
																					<option value="1">INDIA</option>  
																				</select> -->
																			</div>
																		</div>

																		<div class="row">
																			
																			
																			<div class="col-sm-6 form-group">
																				<label>ZIP Code</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter ZIP Code' path="zipCode"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#13_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="13_errorContainer"></span>
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>Website</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter Website' path="webSite"
																					data-bv-notempty="true" data-bv-notempty-message="Required" data-bv-stringlength="true" data-bv-stringlength-min="0" data-bv-stringlength-max="255" data-bv-container="#11_errorContainer" data-bv-stringlength-message="between-string-length"/>
																		<span class="scl-form-error-container" id="11_errorContainer"></span>
																			</div>
																			
																			
																			
																		</div>



																	</div>

																	<div style="text-align: center;">
																		<a href="/company/list">
																			<button type="button" class="btn btn-warning mr-1">
																				<i class="icon-cross2"></i> Cancel
																			</button>
																		</a>

																		<c:if test="${company.name!=null}">
																			<button type="submit" name="submitUpdate" class="btn btn-primary" onclick="">
																				<i class="icon-check2"></i> Update
																			</button>
																		</c:if>

																		<c:if test="${company.name==null}">
																			<button type="submit" name="submitSave" class="btn btn-primary">
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
									<div>
										<a href="#" onclick="goBack()" class="btn btn-primary"
											style="float: left;"> Back</a>
									</div>
								</section>
							</form:form>
							<br> <br>
						</div>
					</div>
					<br>
				</div>
			</div>
		</div>

		<footer class="footer footer-static footer-light navbar-border">
			<p class="clearfix text-muted text-sm-center mb-0 px-2">
				<span class="float-md-right d-xs-block d-md-inline-block">Copyright
					&copy; 2018 <a href="#" target="_blank"
					class="text-bold-800 grey darken-2">SMERP </a>, All rights
					reserved.
				</span>
			</p>
		</footer>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<c:import url="/WEB-INF/jsp/loadJs.jsp" />
		<script src="/resources/js/common-form-elements.js" type="text/javascript"></script> 
		<script src="/resources/components/bootstrap-validator/js/bootstrap-validator.bundle.js" type="text/javascript"></script> 
		<link href="/resources/components/bootstrap-validator/css/bootstrap-validator.min.css" rel="stylesheet" type="text/css" >
</body>
	<style type="text/css">
	.has-error .help-block,
	.has-error .control-label {
	  color: #b94a48;
	}
	
	.has-error .form-control {
	  border-color: #b94a48;
	  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	}
	
	.has-error .form-control:focus {
	  border-color: #953b39;
	  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #d59392;
	          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #d59392;
	}
	
	.has-error .input-group-addon {
	  color: #b94a48;
	  background-color: #f2dede;
	  border-color: #b94a48;
	}
	</style>
<script>

$(function() {
    $( "#form-company" ).submit(function( event ) {
        event.preventDefault();
        var a=$('#form-company').serialize();
        var isValid = commonBootStrapValidations($( "#form-company" ));
        if(isValid){
            //alert(isValid);
            this.submit();
        }
       });
});
var loadFile = function(event) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(event.target.files[0]);
};

		
		
	
</script>
</html>

