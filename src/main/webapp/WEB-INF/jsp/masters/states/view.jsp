
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
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">


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
							<%-- <div class="content-header row">
								<div class="content-header-left col-md-6 col-xs-12 mb-1">
									<h2 class="content-header-title">States</h2>
									<a class="btn btn-primary"
										href="<c:url value="/states/getInfo?statesId=${statesObj.id}"/>">Edit</a>
								</div>
								<div
									class="content-header-right breadcrumbs-right breadcrumbs-top col-md-6 col-xs-12">
									<div class="breadcrumb-wrapper col-xs-12">
										<ol class="breadcrumb">
											<li class="breadcrumb-item"><a href="/user/dashboard">Home</a>
											</li>
											<li class="breadcrumb-item"><a href="#">Administration</a>
											</li>
											<li class="breadcrumb-item active">States</li>
										</ol>
									</div>
								</div>
							</div> --%>
							<div>
								<div class="content-body">
									<!-- Basic Tables start -->

									<div class="card">
										<div class="card-header" style="height: 60px;">
											<div class="row">
												<div class="col-md-2">
													<h4 class="content-header-title" style="width: 180px;">States View</h4>
												</div>
												<div class="col-md-6">
													<a class="btn btn-primary"
														href="<c:url value="/states/getInfo?statesId=${statesObj.id}"/>"
														style="margin-left: 30px;">Edit</a>
												</div>

												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a href="/user/dashboard">Home</a>
														</li>
														<li class="breadcrumb-item"><a href="#">Administration</a>
														</li>
														<li class="breadcrumb-item active">States</li>
													</ol>
												</div>
											</div>
										</div>
												<div class="card-body collapse in">
													<div class="table-responsive">
														<table class="table table-bordered mb-0">

															<tr>
																<th style="width: 40%;">Code</th>
																<td>${statesObj.code}</td>
															</tr>

															<tr>
																<th style="width: 40%;">Name</th>
																<td>${statesObj.name}</td>
															</tr>
															
															<tr>
																<th style="width: 40%;">Country</th>
																<td>${statesObj.country.name}</td>
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

