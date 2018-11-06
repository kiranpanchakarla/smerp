
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

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script> -->

<script src="/resources/components/bootstrap-validator/js/jquery.min.js" type="text/javascript"></script>
<script src="/resources/components/bootstrap-validator/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/components/bootstrap-validator/js/validator.min.js" type="text/javascript"></script>

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
								modelAttribute="company"  data-toggle="validator" role="form"  class="bv-form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
												<c:if test="${company.name==null}">
											<h4 class="card-title" id="basic-layout-icons">Company/Create</h4>
											   </c:if>
												
												<c:if test="${company.name!=null}">
					<form:input type="hidden" cssClass="form-control"  path="id"  />
											<h4 class="card-title" id="basic-layout-icons">Company/Update</h4>
											   </c:if>
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>

												<div class="card-body collapse in">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-6 form-group">
																	<label>Company Name</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Company Name' path="name" onchange="isValidName('name','/company/isValidCompanyName','1_errorContainer')"
																	required="true" oninvalid="this.setCustomValidity('Please Enter Company Name')"	oninput="setCustomValidity('')"/>
																		<div style="color:red;" id="1_errorContainer"  class="help-block with-errors"></div>
																</div>
																
																<div class="col-sm-2 form-group">
																	<label>LOGO</label>
																	 <%-- <form:input type="file" cssClass="form-control"  accept="image/*" onchange="loadFile(event)" 
																		path="logo" value="${company.logo}" /> --%>
																		
																<p>
																<input type="file"   name="file" id="file" value="${company.logo}"  onchange="return fileValidation(event)" style="display: none;"
			                                                            required="true" oninvalid="this.setCustomValidity('Please Upload Image')"	oninput="setCustomValidity('')"   />

																<form:input  type="hidden" path="logo" value="${company.logo}" />
																</p>
															<p style="margin-top: -28px;"><label for="file" style="cursor: pointer;"><img src="${contextPath}/resources/images/company/cameraIcon.png"></label></p>
																<div style="color:red;" id="3_errorContainer"  class="help-block with-errors"></div>
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
																	<label>GSTIN</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='GSTIN' path="gstinVat"
																		required="true"  pattern="^[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}?$"  oninvalid="this.setCustomValidity('Please Enter Valid GSTIN Number Ex:22AABCE2207R1Z6')"	oninput="setCustomValidity('')"/>
																		<div style="color:red;"  class="help-block with-errors"></div>
																</div>
																
																<div class="col-sm-6 form-group">
																	<label>Company Tagline</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Company TagLine' path="companyTagLine"
																		required="true"  oninvalid="this.setCustomValidity('Please Enter Company TagLine')"	oninput="setCustomValidity('')" />
																		<div style="color:red;"  class="help-block with-errors"></div>
																</div>
																
																
															</div>

															<div class="row">
																
																<div class="col-sm-6 form-group">
																	<label>State Code</label>
																	<form:select path="statesCode.id" id="selectSubcat"
																		cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select State ')"	oninput="setCustomValidity('')">
																		<form:option value="">--Select--</form:option>
																		<c:forEach items="${stateList}" var="stateList">

																			<form:option value="${stateList.id}">${stateList.stateCode} | ${stateList.name}</form:option>
																		</c:forEach>
																	</form:select>
																	<div style="color:red;"  class="help-block with-errors"></div>
																</div>
																
																<div class="col-sm-6 form-group">
																	<label>Pan Number</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Enter Pan Number' path="panNum"
																		required="true" maxlength="10" oninvalid="this.setCustomValidity('Please Enter PAN Number')"	oninput="setCustomValidity('')" />
																<div style="color:red;"  class="help-block with-errors"></div>
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
																					required="true" oninvalid="this.setCustomValidity('Please Enter Street1')"	oninput="setCustomValidity('')" />
																		<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Street-2</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Street2' path="street2"
																					required="true" oninvalid="this.setCustomValidity('Please Enter Street2')"	oninput="setCustomValidity('')" />
																		<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																		</div>


																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>Phone</label>
																				<form:input type="text" cssClass="form-control numericwithoutdecimal"
																					placeholder='Enter Phone No' path="phoneNum" pattern="[0-9]+$"
																					required="true"  maxlength="10" oninvalid="this.setCustomValidity('Please Enter Phone Number')"	oninput="setCustomValidity('')"  />
																	<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Fax</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter fax Num' path="faxNum"
																					required="true" oninvalid="this.setCustomValidity('Please Enter Fax Number')"	oninput="setCustomValidity('')" />
																	<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																		</div>


																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>City</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter City' path="city"
																					required="true" oninvalid="this.setCustomValidity('Please Enter City')"	oninput="setCustomValidity('')"/>
																	<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			<div class="col-sm-6 form-group">
																				<label>Email</label>
																				<form:input type="email" cssClass="form-control"  onchange="isValidName('emailId','/company/isValidEmailId','2_errorContainer')"
																				 pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder='Enter Email' path="emailId"
																				required="true"  oninvalid="this.setCustomValidity('Please Enter Valid Email')"	oninput="setCustomValidity('')" />
																		<div style="color:red;" id="2_errorContainer"  class="help-block with-errors" ></div>
																			</div>
																		</div>

																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>State</label>
																				<form:select path="states.id" id="selectSubcat"
																					cssClass="form-control" required="true"   oninvalid="this.setCustomValidity('Please Select State')"	oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${stateList}" var="stateList">

																						<form:option value="${stateList.id}">${stateList.name}</form:option>
																					</c:forEach>
																				</form:select>
																				<div style="color:red;"  class="help-block with-errors"></div>
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
																				<form:input type="text" cssClass="form-control numericwithoutdecimal"
																					placeholder='Enter ZIP Code' path="zipCode"
																					required="true" maxlength="6"  oninvalid="this.setCustomValidity('Please ZIP Code')"	oninput="setCustomValidity('')" />
																		<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>Website</label>
																				<form:input type="text" cssClass="form-control"
																					placeholder='Enter Website' path="webSite" id="instmanageformid"
																					required="true"  oninvalid="this.setCustomValidity('Please Enter WebSite')"	oninput="setCustomValidity('')" />
																	<div style="color:red;"  class="help-block with-errors"></div>
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
		
		
		
</body>
	
<script>


/* var loadFile = function(event) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(event.target.files[0]);
}; */

function fileValidation(event){
    var fileInput = document.getElementById('file');
    var filePath = fileInput.value;
    var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    if(!allowedExtensions.exec(filePath)){
        //alert();
        alertify.alert('Please upload file having extensions .jpeg/.jpg/.png/.gif only.');
        fileInput.value = '';
        return false;
    }else{
    	var image = document.getElementById('output');
    	image.src = URL.createObjectURL(event.target.files[0]);
    }
}	



	
</script>
</html>

