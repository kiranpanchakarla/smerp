
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
<!--  <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>    
 --> <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>

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

							<c:url value="/saccode/save" var="createUrl" />
							<form:form  method="POST" action="${createUrl}" 
								modelAttribute="saccode" onsubmit="return register()"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height" >

										<div class="col-md-12" >
											<div class="card">
												<div class="card-header">
													<div class="row">
														<div class="col-md-10">
															<c:if test="${saccode.id==null}">
														<h2 class="card-title" id="basic-layout-icons">Create New SAC Code</h2>
													</c:if>

													<c:if test="${saccode.id!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update SAC Code Details</h2>
													</c:if>
														</div>
														<div class="col-md-1">
															<!-- <a class="btn btn-primary" href="/hsncode/list">Back</a> -->
														</div>
													</div>

												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="${saccode.id}">

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																<div class="col-sm-6 form-group">
																	<label for="timesheetinput1">SAC Code</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='SAC Code' path="sacCode"
																		 required="true" onchange="isValidName('sacCode','/saccode/isValidSACCode','1_errorContainer','SAC Code Already Exists')"
																		oninvalid="this.setCustomValidity('Enter SAC Code')" maxlength="10" minlength="6" onkeypress='return isNumberKey(event);'
																		oninput="setCustomValidity('')" />
																	 
																</div>

																<div class="col-sm-6 form-group">
																	<label for="timesheetinput1">Description</label>
																	<form:input type="text" cssClass="form-control camelCase"
																		placeholder='Description' path="description"
																		 required="true"
																		oninvalid="this.setCustomValidity('Enter Description')"
																		oninput="setCustomValidity('')" />
																	 
																</div>

																<div class="col-sm-6 form-group">
																	<label for="timesheetinput1">Rate</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Rate' path="rate"
																		 required="true"
																		oninvalid="this.setCustomValidity('Enter Rate')"
																		oninput="setCustomValidity('')" />
																	 
																</div>
															</div>

															</div>
															<br>
															<div class="text-xs-center">
																	
																	<a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a>
																<a href="<c:url value="/saccode/list"/>">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a> <input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<c:if test="${saccodeObj.sacCode!=null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Update
																	</button>
																</c:if>

																<c:if test="${saccodeObj.sacCode==null}">
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
								</section>
							</form:form>

							<!-- // Basic form layout section end -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/jsp/footer.jsp" />
	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<script>
	$(document).ready(function() {
		/*  console.log("hiiiii");
		var x=$('#tokenId').val();
		console.log("token id"+x);
		document.cookie="tokenId=" + x;
		
		console.log("document cookie"+document.cookie);   */
	});
	
	function isNumberKey(evt) {
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    console.log(charCode);
	    if ((charCode < 48 || charCode > 57))
	        return false;

	    return true;
	}
</script>
</html>

