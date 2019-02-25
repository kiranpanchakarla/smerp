
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

	
<script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
<!--  <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>  -->   
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>    
<script src=<c:url value="/resources/js/common.js"/> type="text/javascript"></script>
	
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/loadcss.jsp" />

	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row"></div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

						<div class="content-body">
							<!-- Basic form layout section start -->

							<c:url value="/plant/save" var="createUrl" />
							<form:form  method="POST" action="${createUrl}" 
								modelAttribute="plant" onsubmit="return register()"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<c:if test="${plant.id==null}">
														<h2 class="card-title" id="basic-layout-icons">Create New Warehouse</h2>
													</c:if>

													<c:if test="${plant.id!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update Warehouse Details</h2>
													</c:if>
												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="${plant.id}">

												<div class="card-body collapse in create-block">
													<div class="card-block">
															<div class="form-body">
																<div class="row">
																<div class="col-sm-4 form-group">
																	<label for="timesheetinput1">Name</label>
																	<div>

																		<form:input type="text" cssClass="form-control camelCase"
																			placeholder='Warehouse Name' path="plantName"
																			required="true" onchange="isValidName('plantName','/currency/isValidWarehouseName','1_errorContainer','Warehouse Name Already Exists')"
																			oninvalid="this.setCustomValidity('Please Enter Warehouse Name')"
																			oninput="setCustomValidity('')" />
																		<!-- <div  
																			class="help-block with-errors"></div> -->
																		 
																	</div>
																</div>
																 
																 <div class="col-sm-4 form-group">
																	<label for="timesheetinput1">Address-1</label>
																	<div>

																		<form:input type="text" cssClass="form-control camelCase"
																			placeholder='Warehouse Address 1' path="address1"
																			required="true"
																			oninvalid="this.setCustomValidity('Please Enter Warehouse Address')"
																			oninput="setCustomValidity('')" />
																		<!-- <div  
																			class="help-block with-errors"></div> -->
																		 
																	</div>
																</div>
																
																 <div class="col-sm-4 form-group">
																	<label for="timesheetinput1">Address-2</label>
																	<div>

																		<form:input type="text" cssClass="form-control camelCase"
																			placeholder='Warehouse Address 2 ' path="address2"
																			required="true"
																			oninvalid="this.setCustomValidity('Please Enter Warehouse Address')"
																			oninput="setCustomValidity('')" />
																		<!-- <div  
																			class="help-block with-errors"></div> -->
																		 
																	</div>
																</div>
																
																<div class="col-sm-4 form-group">
																	<label for="timesheetinput1">City</label>
																	<div>

																		<form:input type="text" cssClass="form-control camelCase"
																			placeholder='Warehouse City ' path="city"
																			required="true"
																			oninvalid="this.setCustomValidity('Please Enter Warehouse Address')"
																			oninput="setCustomValidity('')" />
																		<!-- <div  
																			class="help-block with-errors"></div> -->
																		 
																	</div>
																</div>
																
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
																			</div>
																			<div class="row">
																			
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
															<br>
															<div class="text-xs-center">
																	
																	<a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a> <a href="<c:url value="/plant/list"/>">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a> <input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<c:if test="${plant.plantName!=null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Update
																	</button>
																</c:if>

																<c:if test="${plant.plantName==null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Save
																	</button>
																</c:if>


															</div>
														</form>
														 
														
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								</section>

							</form:form>

							<br> <br>

							<!-- // Basic form layout section end -->
						</div>


						<%-- <div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-body">
								 <div>token:${token}</div>
								<input type="text"  id="tokenId"  value="${tokenId}"/> 
								<div>data:${data}</div>
							</div>
							<div class="card-footer">Footer</div>
						</div>
					</div> --%>
					</div>
					<!--/ project charts -->
					<br>
				</div>
			</div>
		</div>
		</div>

		<footer class="footer footer-static footer-light navbar-border">
			<p class="clearfix text-muted text-sm-center mb-0 px-2">
				<span class="float-md-right d-xs-block d-md-inline-block">Copyright
					&copy; 2019 <a href="#" target="_blank"
					class="text-bold-800 grey darken-2">SMERP </a>, All rights
					reserved.
				</span>
			</p>
		</footer>

		<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>

</html>

