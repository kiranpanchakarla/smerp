
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
<script src="/resources/components/bootstrap-validator/js/jquery.min.js"
	type="text/javascript"></script>
<script
	src="/resources/components/bootstrap-validator/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="/resources/components/bootstrap-validator/js/validator.min.js"
	type="text/javascript"></script>
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

							<form:form method="POST" action="/currency/save"
								modelAttribute="currency" onsubmit="return register()"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-6" style="margin-left: 250px;">
											<div class="card">
												<div class="card-header">
													<h4 class="card-title" id="basic-layout-icons">Currency/Create</h4>
													<!-- <a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a> -->
												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="${currencyObj.id}">

												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="form-group">
																	<label for="timesheetinput1">Currency Name</label>
																	<div>

																		<form:input type="text" cssClass="form-control"
																			placeholder='Currency Name' path="name"
																			value="${currencyObj.name}" required="true"
																			oninvalid="this.setCustomValidity('Please Enter Currency Name')"
																			oninput="setCustomValidity('')" />
																		<div style="color: red;"
																			class="help-block with-errors"></div>
																		<div class="form-control-position"></div>
																	</div>
																</div>
																<div class="form-group">
																	<label for="timesheetinput2">Currency
																		Description</label>
																	<div>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Currency Description' path="description"
																			value="${currencyObj.description}" required="true"
																			oninvalid="this.setCustomValidity('Please Enter Description')"
																			oninput="setCustomValidity('')" />
																		<div style="color: red;"
																			class="help-block with-errors"></div>
																		<div class="form-control-position"></div>
																	</div>
																</div>
															</div>
															<div class="form-actions center">
																<a class="btn btn-primary" href="/currency/list">Back</a>
																<a href="/currency/list">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a> <input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<c:if test="${currency.name!=null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Update
																	</button>
																</c:if>

																<c:if test="${currency.name==null}">
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

		<footer class="footer footer-static footer-light navbar-border">
			<p class="clearfix text-muted text-sm-center mb-0 px-2">
				<span class="float-md-right d-xs-block d-md-inline-block">Copyright
					&copy; 2018 <a href="#" target="_blank"
					class="text-bold-800 grey darken-2">SMERP </a>, All rights
					reserved.
				</span>
			</p>
		</footer>

		<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>

</html>

