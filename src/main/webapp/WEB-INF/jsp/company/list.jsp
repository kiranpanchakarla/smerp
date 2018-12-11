
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
											<div class="card-header">
											<div class="row">
												<div class="col-md-2">
													<h2 class="content-header-title">Company</h2>
												</div>
												<div class="col-md-6">
												<c:forEach items="${sessionScope.umpmap}" var="ump">
										 <c:if test="${ump.key eq 'Company'}">
										 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	<c:if test="${fn:containsIgnoreCase(permissions,'create')}">
	        									<a class="btn btn-primary" href="<c:url value="/company/create"/>">Create</a>
	   										 </c:if>
	       								</c:if>     
   									 </c:forEach>
													 
												</div>
												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">Home</a></li>
														<li class="breadcrumb-item"><a href="#">Administration</a></li>
														<li class="breadcrumb-item active">Company</li>
													</ol>
												</div>
											</div>

										</div>
											<div class="card-body collapse in">
												<div class="card-block card-dashboard">
													 
													<div class="table-responsive">
														<table id="example" class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
															style="width: 100%">
															<thead>
																<tr>
																    <th>S.no</th>
																	<th>Name</th>
																	<th>GSTIN</th>
																	<th>City</th>
																	<th>Website</th>
																	<th>Created</th>
																	<th>Modified</th>
																	<th>Actions</th>
																</tr>
															</thead>
															<tbody>
															<c:forEach items="${companyList}" var="companyList">
																<tr>
																<td><c:set var="count" value="${count + 1}" scope="page" />
                                                                     <c:out value="${count}" />  </td>
																	<td>${companyList.name}</td>
																	<td>${companyList.gstinVat}</td>
																	<td>${companyList.city}</td>
																	<td>${companyList.webSite}</td>
																	<td>${companyList.createdAt}</td>
																	<td>${companyList.updatedAt}</td>
																	<td >
																	<c:forEach items="${sessionScope.umpmap}" var="ump">
																		 <c:if test="${ump.key eq 'Company'}">
																		 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
																		<c:if test="${fn:containsIgnoreCase(permissions,'update')}"> 
																	<a class ="btn btn-edit" href="<c:url value="/company/getInfo?companyId=${companyList.id}"/>"><i class="icon-edit left"></i></a>  
																	 </c:if>
									   								<c:if test="${fn:containsIgnoreCase(permissions,'delete')}"> 
																	<a  class ="btn btn-delete" href="#"  onclick="deleteById('<c:out value="${companyList.id}"/>','/company/delete')"><i class="icon-bin left"></i></a>
															    	 </c:if> 
									   								<c:if test="${fn:containsIgnoreCase(permissions,'view')}">
															    	<a  class ="btn btn-view" href="<c:url value="/company/view?companyId=${companyList.id}"/>"><i class="icon-eye3 left"></i></a>
																	 </c:if> 
									       								</c:if>     
   															    	 </c:forEach>
																	</td>
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

