
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
	<c:import url="../header.jsp" />

	<c:import url="../sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					 
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

							<div class="content-wrapper">
								<div class="content-header row">
									<div class="content-header-left col-md-6 col-xs-12 mb-1">
										<h2 class="content-header-title">Product</h2>
										<a class="btn btn-primary" href="/inventory/create">Create</a>
									</div>
									<div
										class="content-header-right breadcrumbs-right breadcrumbs-top col-md-6 col-xs-12">
										<div class="breadcrumb-wrapper col-xs-12">
											<ol class="breadcrumb">
												<li class="breadcrumb-item"><a href="/user/dashboard">Home</a>
												</li>
												<li class="breadcrumb-item"><a href="#">Inventory</a>
												</li>
												<li class="breadcrumb-item active">Product</li>
											</ol>
										</div>
									</div>
								</div>
								<div>
									<div class="content-body">
										<!-- Basic Tables start -->

										<div class="card">
											<div class="card-header">
												<h4 class="card-title">Product List</h4>
												<a class="heading-elements-toggle"><i
													class="icon-ellipsis font-medium-3"></i></a>
											</div>
											<div class="card-body collapse in">
												<div class="card-block card-dashboard">
													 
													<div class="table-responsive">
														<table id="example" class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
															style="width: 100%">
															<thead>
																<tr>
																	<th>Product Name</th>
																	<th>product Group</th>
																	<th>Uom Group</th>
																	<th>Description</th>
																	<th>Actions</th>
																	
																</tr>
															</thead>
															<tbody>
															<c:forEach items="${productList}" var="productList">
																<tr>
																	<td>${productList.productNo}</td>
																	<td>${productList.productCategory.name}</td>
																	<td>${productList.uomCategory.uomCategoryName}</td>
																	<td>${productList.description}</td>
																	<td style="width:14%">
																	 <a class ="btn btn-primary" href="<c:url value="/inventory/getInfo?productId=${productList.id}"/>"><i class="icon-edit left"></i></a> | 
																	<a  class ="btn btn-warning" href="#"  onclick="deleteProductId('<c:out value="${productList.id}"/>')"><i class="icon-bin left"></i></a></td>
																</tr>
																</c:forEach>
															</tbody>
														
														</table>
													</div>
												</div>
											</div>
										</div>
										
									</div>
									<br>
								</div>
							</div>
					</div>
					</div>
</div></div></div>
				<footer class="footer footer-static footer-light navbar-border">
  <p class="clearfix text-muted text-sm-center mb-0 px-2"><span class="float-md-right d-xs-block d-md-inline-block">Copyright  &copy; 2018 <a href="#" target="_blank" class="text-bold-800 grey darken-2">SMERP </a>, All rights reserved. </span></p>
</footer>

<c:import url="../loadJs.jsp" />
</body>
	
</html>

