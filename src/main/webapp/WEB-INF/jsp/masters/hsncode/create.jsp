
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
<script src="/resources/components/bootstrap-validator/js/jquery.min.js" type="text/javascript"></script>
<script src="/resources/components/bootstrap-validator/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/components/bootstrap-validator/js/validator.min.js" type="text/javascript"></script>
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

							<form:form method="POST" action="/hsncode/save"
								modelAttribute="hsncode" onsubmit="return register()" data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height" style="background: white;">

										<div class="col-md-6" style="margin-left: 250px;">
											<div class="card">
												<div class="card-header">
													<div class="row">
														<div class="col-md-10">
															<h4 class="card-title" id="basic-layout-icons">HSN
																Code/Create</h4>
														</div>
														<div class="col-md-1">
															<!-- <a class="btn btn-primary" href="/hsncode/list">Back</a> -->
														</div>
													</div>

												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="${hsncodeObj.id}">

												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="form-group">
																	<label for="timesheetinput1">HSN Code</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='HSN Code' path="hsnCode"
																		value="${hsncodeObj.hsnCode}" required="true" oninvalid="this.setCustomValidity('Enter HSN Code')"    oninput="setCustomValidity('')"/>
																     <div style="color:red;"  class="help-block with-errors"></div>
																</div>

																<div class="form-group">
																	<label for="timesheetinput1">Description</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Description' path="description"
																		value="${hsncodeObj.description}" required="true" oninvalid="this.setCustomValidity('Enter Description')"    oninput="setCustomValidity('')"/>
																         <div style="color:red;"  class="help-block with-errors"></div>
																</div>

																<div class="form-group">
																	<label for="timesheetinput1">Rate</label>
																	<form:input type="text" cssClass="form-control"
																		placeholder='Rate' path="rate"
																		value="${hsncodeObj.rate}" required="true" oninvalid="this.setCustomValidity('Enter Rate')"    oninput="setCustomValidity('')" />
																         <div style="color:red;"  class="help-block with-errors"></div>
																</div>
															</div>

															<div  style="text-align: center;">
																<a class="btn btn-primary" href="/hsncode/list">Back</a>
																<a href="/hsncode/list">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a> <input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<c:if test="${hsncodeObj.hsnCode!=null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Update
																	</button>
																</c:if>

																<c:if test="${hsncodeObj.hsnCode==null}">
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
</script>
</html>

