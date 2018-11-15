
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
</head>

<link href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>" rel="stylesheet" type="text/css" />

<c:import url="/WEB-INF/jsp/loadcss.jsp" />
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">


	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6"></div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

						<div class="content-wrapper">
							<div class="content-header row"></div>
							<div>
								<div class="content-body">

									<div class="card">
										<div class="card-header" style="height: 60px;">
											<div class="row">
												<div class="col-md-2">
													<h2 class="content-header-title">Country</h2>
												</div>
												<div class="col-md-6">
													<a class="btn btn-primary"
														href="<c:url value="/country/create"/>">Create</a>
												</div>
												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a href="/user/dashboard">Home</a>
														</li>
														<li class="breadcrumb-item"><a href="#">Administration</a>
														</li>
														<li class="breadcrumb-item active">Countries</li>
													</ol>
												</div>
											</div>

										</div>
										<div class="card-body collapse in">
											<div class="card-block card-dashboard">

												<div class="">
													<table id="example"
														class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
														style="width: 100%">
														<thead>
															<tr>
																<th style="text-align: center;">S.no</th>
																<th style="text-align: center;">Country Code</th>
																<th style="text-align: center;">Country name</th>
																<th style="text-align: center;">Currency</th> 
																<th style="text-align: center;">Phone Code</th>
																<th style="text-align: center;">Actions</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${countryList}" var="country">
																<tr>
																	<td style="text-align: center;"><c:set var="count"
																			value="${count + 1}" scope="page" /> <c:out
																			value="${count}" /></td>
																	<td style="text-align: center;">${country.countryCode}</td>
																	<td style="text-align: center;">${country.name}</td>
																	<td style="text-align: center;">${country.currency.name}</td> 
																	<td style="text-align: center;">${country.phoneCode}</td>
																	<td style="text-align: center;"><a
																		class="btn btn-primary"
																		href="<c:url value="/country/getInfo?countryId=${country.id}"/>">
																			<i class="icon-edit left"></i>
																	</a> | <a class="btn btn-warning mr-1" href="#"
																		onclick="deleteById('<c:out value="${country.id}"/>','/country/delete')"><i
																			class="icon-bin left"></i></a> | <a
																		class="btn btn-primary"
																		href="<c:url value="/country/view?countryId=${country.id}"/>"><i
																			class="icon-eye3 left"></i></a></td>
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
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/jsp/footer.jsp" />
	<c:import url="/WEB-INF/jsp/loadJs.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	  $('#example').DataTable( {
		  "scrollX": true
	       
	    } );
} );

</script>
<script src=<c:url value="/resources/js/scripts/dataTables/buttons.html5.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/dataTables/dataTables.buttons.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/dataTables/jquery.dataTables.min.js"/> type="text/javascript"></script> 
</body>
</html>

