
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
<link href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>" rel="stylesheet" type="text/css" />


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

						<div class="content-wrapper">
							<div>
								<div class="content-body">
									<!-- Basic Tables start -->

									<div class="card">
										<div class="card-header" style="height: 60px;">
											<div class="row">
												<div class="col-md-2">
													<h4 class="content-header-title" style="width: 180px;">HSN
														Code View</h4>
												</div>
												<div class="col-md-6">
													<a class="btn btn-primary"
														href="<c:url value="/hsncode/getInfo?hsncodeId=${hsncodeObj.id}"/>"
														style="margin-left: 30px;">Edit</a>
												</div>

												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a href="/user/dashboard">Home</a>
														</li>
														<li class="breadcrumb-item"><a href="#">Administration</a>
														</li>
														<li class="breadcrumb-item active">HSN Code</li>
													</ol>
												</div>
											</div>
										</div>
										<div class="card-body collapse in">
											<div class="table-responsive">
												<table class="table table-bordered mb-0">

													<tr>
														<th style="width: 40%;">HSN Code</th>
														<td>${hsncodeObj.hsnCode}</td>
													</tr>

													<tr>
														<th style="width: 40%;">Description</th>
														<td>${hsncodeObj.description}</td>
													</tr>

													<tr>
														<th style="width: 40%;">Rate</th>
														<td>${hsncodeObj.rate}</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>


							<div>
								<a href="#" onclick="goBack()" class="btn btn-primary"
									style="float: left;">Back</a>
							</div>

						</div>
						<br>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer footer-static footer-light navbar-border">
		<p class="clearfix text-muted text-sm-center mb-0 px-2">
			<span class="float-md-right d-xs-block d-md-inline-block">Copyright
				&copy; 2018 <a href="#" target="_blank"
				class="text-bold-800 grey darken-2">SMERP </a>, All rights reserved.
			</span>
		</p>
	</footer>

	<c:import url="/WEB-INF/jsp/loadJs.jsp" />

</body>

</html>

