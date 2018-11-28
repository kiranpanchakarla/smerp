
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
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>
<script
	src=<c:url value="/resources/js/scripts/datepicker/bootstrap-datepicker.min.js"/>
	type="text/javascript"></script>

<link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/datapickercss/bootstrap-datepicker.min.css"/>"
	rel="stylesheet" type="text/css" />

</head>


<!-----------    END   ---------------------------------->





<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />
	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>Role</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">




							<form:form method="POST" action="/role/save"
								enctype="multipart/form-data" modelAttribute="role"
								data-toggle="validator" role="form" class="bv-form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">

													<h4 class="card-title" id="basic-layout-icons">Role/Create</h4>
												</div>

												<div class="card-body collapse in">
													<div class="card-block">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-4 form-group">
																	<label>Name</label>
																	<form:input type="text"
																		cssClass="form-control name"
																		placeholder='Role Name' path="name"
																		required="true" />
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
																
																<div class="col-sm-4 form-group">
																	<label>Description</label>
																	<form:input type="text"
																		cssClass="form-control name"
																		placeholder='Description' path="description"
																		required="true" />
																	<div style="color: red;" id="1_errorContainer"
																		class="help-block with-errors"></div>
																</div>
																
																<div class="col-sm-4 form-group">
																		<label>Permission</label>
																		<form:select  path="permissions" cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select  Department')" oninput="setCustomValidity('')">
																			<c:forEach items="${permissionMap}" var="permissionMap">
																			<form:option value="${permissionMap.id}">${permissionMap.permssionName}</form:option>
																		   </c:forEach>
																		</form:select>
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
															</div>
															
															
															
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6 form-group">
											<input type="submit"  value="Save"
												class="btn btn-primary" />
											
										</div>
										<div class="col-sm-6 form-group">

											<a href="<c:url value="/rfq/list"/>">
												<button type="button" class="btn btn-warning mr-1">
													<i class="icon-cross2"></i> Cancel
												</button>
											</a> <a href="#" onclick="goBack()"
												class="btn btn-primary float-left"> Back </a>
										</div>
									</div>

								</section>
							</form:form>
						</div>
					</div>
					<!--/ project charts -->
					<br>
				</div>
			</div>
		</div>

	</div>


	<c:import url="/WEB-INF/jsp/footer.jsp" />


	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<!-- alertfy-->
<!-- form validations-->




</html>

