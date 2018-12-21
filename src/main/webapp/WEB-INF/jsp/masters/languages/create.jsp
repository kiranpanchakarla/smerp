
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
<!--  <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>    
 --> <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>

</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">

	
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

							<c:url value="/languages/save" var="createUrl" />
							<form:form  method="POST" action="${createUrl}" 
								modelAttribute="languages" onsubmit="return register()" data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<div class="row">
														<div class="col-md-10">
															<c:if test="${languagesObj.id==null}">
														<h2 class="card-title" id="basic-layout-icons">Create New Language</h2>
													</c:if>

													<c:if test="${languagesObj.id!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update Language Details</h2>
													</c:if>
														</div>
														<div class="col-md-1">
													
														</div>
													</div>

													
												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="${languagesObj.id}">

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																<div class="col-sm-6 form-group">
																	<label for="timesheetinput1">Language Name</label>
																	<div>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Name' path="name" onchange="isValidName('name','/language/isValidLanguageName','1_errorContainer','Language Name Already Exists')"
																			value="${languagesObj.name}" required="true" oninvalid="this.setCustomValidity('Please Enter Language')"    oninput="setCustomValidity('')"/>
																	<div   class="help-block with-errors"></div>
																	</div>
																</div>

															</div>
															</div>
															<br>
															<div class="text-xs-center">
																	
																	<a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a> 
																<a href="<c:url value="/languages/list"/>">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a> <input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<c:if test="${languagesObj.name!=null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Update
																	</button>
																</c:if>

																<c:if test="${languagesObj.name==null}">
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
						</div>

					</div>

				</div>
				<!--/ project charts -->
				<br>
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
</script>
</html>

