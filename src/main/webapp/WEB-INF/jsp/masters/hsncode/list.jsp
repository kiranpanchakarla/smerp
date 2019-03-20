
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
</head>
<link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />


<c:import url="/WEB-INF/jsp/loadcss.jsp" />
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">


	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-body">
				<div class="card">
										<div class="card-header">
										<div class="row">
										<div class="col-md-2">
										<h3 class="content-header-title">HSN Code</h3>
										</div>
											<div class="col-md-6">

													<c:forEach items="${sessionScope.umpmap}" var="ump">
												<c:if test="${ump.key eq 'Admin Master'}">
														<c:set var="permissions" scope="session"
																		value="${ump.value}" />
														<c:if test="${fn:containsIgnoreCase(permissions,'create')}">
													<a class="btn btn-primary"
													href="<c:url value="/hsncode/create"/>">Create </a>													    </c:if>
												</c:if>
												</c:forEach>
											</div>
											<div class="col-md-4" Style="text-align: right;">

												<ol class="breadcrumb">
													<li class="breadcrumb-item"><a
														href="<c:url value="/dashboard"/>">Home</a></li>
													<li class="breadcrumb-item"><a href="#">Administration</a>
													</li>
													<li class="breadcrumb-item active">HSN Code</li>
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
															<th>S.No</th>
															<th>HSN Code</th>
															<th>Description</th>
															<th>Rate</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${hsncodeList}" var="hsncode">
															<tr>
																<td><c:set var="count" value="${count + 1}"
																		scope="page" /> <c:out value="${count}" /></td>
																<td>${hsncode.hsnCode}</td>
																<td>${hsncode.description}</td>
																<td>${hsncode.rate}</td>
																<td >   
																			<c:forEach items="${sessionScope.umpmap}" var="ump">
												<c:if test="${ump.key eq 'Admin Master'}">
												<c:set var="permissions" scope="session" value="${ump.value}" />
														<c:if test="${fn:containsIgnoreCase(permissions,'update')}">
																	<a class="btn btn-edit"
																	href="<c:url value="/hsncode/getInfo?hsncodeId=${hsncode.id}"/>"
																	data-toggle="tooltip" data-placement="right"
																	title="Edit"> <i class="icon-edit left"></i>
																</a>
														</c:if>		
														
														<c:if test="${fn:containsIgnoreCase(permissions,'delete')}">
																	 <a class="btn btn-delete mr-1" href="#"
																	onclick="deleteById('<c:out value="${hsncode.id}"/>','/hsncode/delete')"
																	data-toggle="tooltip" data-placement="right"
																	title="Delete"><i class="icon-bin left"></i></a>
														</c:if>				
															
														<c:if test="${fn:containsIgnoreCase(permissions,'view')}">				
																			 <a
																	class="btn btn-view"
																	href="<c:url value="/hsncode/view?hsncodeId=${hsncode.id}"/>"
																	data-toggle="tooltip" data-placement="right"
																	title="View"><i class="icon-eye3 left"></i></a>
														</c:if>		
																			
																	</c:if>
													</c:forEach>  </td>

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
			$('#example').DataTable({
				"scrollX" : true
			});
		});
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
			//$('.btn-edit').tooltip('open');
		});
	</script>

	<script
		src=<c:url value="/resources/js/scripts/dataTables/buttons.html5.min.js"/>
		type="text/javascript"></script>
	<script
		src=<c:url value="/resources/js/scripts/dataTables/dataTables.buttons.min.js"/>
		type="text/javascript"></script>
	<script
		src=<c:url value="/resources/js/scripts/dataTables/jquery.dataTables.min.js"/>
		type="text/javascript"></script>
		<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>
</body>
</html>

