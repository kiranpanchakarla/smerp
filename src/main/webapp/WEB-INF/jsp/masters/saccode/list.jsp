
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
							 
							<div>
								<div class="content-body">
									<!-- Basic Tables start -->

									<div class="card">
										<div class="card-header">
										<div class="row">
												<div class="col-md-9">
													<a class="btn btn-primary" href="<c:url value="/saccode/create"/>">Create</a>
												</div>
												<div class="col-md-3">
													<ol class="breadcrumb">
											<li class="breadcrumb-item"><a href="/user/dashboard">Home</a>
											</li>
											<li class="breadcrumb-item"><a href="#">Administration</a>
											</li>
											<li class="breadcrumb-item active">SAC Code</li>
										</ol>
												</div>
											</div>
										</div>
										<div class="card-body collapse in">
											<div class="card-block card-dashboard">

												<div class="table-responsive">
													<table id="example"
														class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
														style="width: 100%">
														<thead>
															<tr>
															    <th style="text-align: center;">S.no</th>
																<th style="text-align: center;">SAC CODE</th>
																<th style="text-align: center;">Description</th>
																<th style="text-align: center;">Rate</th>
																<th style="text-align: center;">Actions</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${saccodeList}" var="saccode">
																<tr>
																    <td style="text-align: center;"><c:set var="count" value="${count + 1}" scope="page" />
                                                                     <c:out value="${count}" />  </td>
																	<td style="text-align: center;">${saccode.sacCode}</td>
																	<td style="text-align: center;">${saccode.description}</td>
																	<td style="text-align: center;">${saccode.rate}</td>
																	<td style="text-align: center;"><a class="btn btn-primary"
																		href="<c:url value="/saccode/getInfo?saccodeId=${saccode.id}"/>">
																			<i class="icon-edit left"></i>
																	</a> <a class="btn btn-warning mr-1" href="#"
																		onclick="deleteById('<c:out value="${saccode.id}"/>','/saccode/delete')"><i
																			class="icon-bin left"></i></a><a class="btn btn-primary"
																		href="<c:url value="/saccode/view?saccodeId=${saccode.id}"/>"><i
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
	
</body>
<script>
$(document).ready(function() {
	  $('#example').DataTable( {
	       
	    } );
} );
</script>

</html>

