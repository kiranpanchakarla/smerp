<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
</head>


<link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />


<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/inventorysidebar.jsp" />

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
												<div class="col-md-4">
													<h2 class="content-header-title">Inventory Goods Issue</h2>
												</div>
												<div class="col-md-4">
													<c:forEach items="${sessionScope.umpmap}" var="ump">
														<c:if test="${ump.key eq 'Inventory Goods Issue'}">
															<c:set var="permissions" scope="session"
																value="${ump.value}" />
															<c:if
																test="${fn:containsIgnoreCase(permissions,'create')}">
																<a class="btn btn-primary"
																	href="<c:url value="/invgi/create"/>">Create</a>
															</c:if>
														</c:if>
													</c:forEach>

												</div>
												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a
															href="<c:url value="/dashboard"/>">Home</a></li>
														<%-- <li class="breadcrumb-item"><a
															href="<c:url value="/purchase"/>">Purchase</a></li> --%>
														<li class="breadcrumb-item active">Inventory Goods Issue</li>
													</ol>
												</div>
											</div>

										</div>
										<c:import url="/WEB-INF/jsp/searchFilter.jsp"/>
										<div class="card-body collapse in">
											<div class="card-block card-dashboard">

												<div class="table-responsive">
													<table id="example"
														class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
														style="width: 100%">
														<thead>
															<tr>
																<th>S.No</th>
																<th>Doc#</th>
																<th>Created Date</th>
																<th>Modified Date</th>
																<th>Document Status</th>
																<th>Actions</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${list}" var="list">
																<tr>
																	<td><c:set var="count" value="${count + 1}"
																			scope="page" /> <c:out value="${count}" /></td>
																	<td>${list.docNumber}</td>
																	<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a" value="${list.createdAt}"/></td>
																	<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm a" value="${list.updatedAt}"/></td>
																	<td>${list.status}</td>
																	<%-- <td>
																	<c:if test="${list.status != 'Approved'}">
																	 <a class ="btn btn-edit" href="<c:url value="/gr/edit?id=${list.id}"/>"><i class="icon-edit left"></i></a>  
																	<a  class ="btn btn-delete" href="#"  onclick="deleteById('<c:out value="${list.id}"/>','/user/delete')"><i class="icon-bin left"></i></a>
																	</c:if> 
														    		<a class ="btn btn-view" href="<c:url value="/gr/view?id=${list.id}"/>"><i class="icon-eye3 left"></i></a> 
									                               </td>	 --%>
																	<td><c:choose>
																			<c:when test="${list.status != 'Approved'  && list.status != 'Cancelled'  && list.status != 'Rejected' && list.status != 'Goods_Return' && list.status != 'Invoiced'}">
																				<c:forEach items="${sessionScope.umpmap}" var="ump">
																					<c:if test="${ump.key eq 'Inventory Goods Issue'}">
																						<c:set var="permissions" scope="session"
																							value="${ump.value}" />
																						<c:choose>
																							<c:when
																								test="${fn:containsIgnoreCase(permissions,'update')}">
																								<a class="btn btn-edit"
																									href="<c:url value="/invgi/edit?id=${list.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i
																									class="icon-edit left"></i></a>
																							</c:when>
																							<c:otherwise>
																								<a class="btn btn-disable"><i
																									class="icon-bin left"></i></a>
																							</c:otherwise>
																						</c:choose>
																						<c:choose>
																							<c:when
																								test="${fn:containsIgnoreCase(permissions,'delete')}">
																								<a class="btn btn-delete" href="#"
																									onclick="deleteById('<c:out value="${list.id}"/>','/user/delete')" data-toggle="tooltip" data-placement="top" title="Delete"><i
																									class="icon-bin left"></i></a>
																							</c:when>
																							<c:otherwise>
																								<a class="btn btn-disable"><i
																									class="icon-bin left"></i></a>
																							</c:otherwise>
																						</c:choose>

																					</c:if>
																				</c:forEach>
																			</c:when>
																			<c:otherwise>
																				<a class="btn btn-disable"><i
																					class="icon-edit left"></i></a>
																				<a class="btn btn-disable"><i
																					class="icon-bin left"></i></a>
																			</c:otherwise>
																		</c:choose> <c:forEach items="${sessionScope.umpmap}" var="ump">
																			<c:if test="${ump.key eq 'Inventory Goods Issue'}">
																				<c:set var="permissions" scope="session"
																					value="${ump.value}" />
																				<c:if
																					test="${fn:containsIgnoreCase(permissions,'view')}">
																					<a class="btn btn-view"
																						href="<c:url value="/invgi/view?id=${list.id}"/>" data-toggle="tooltip" data-placement="top" title="View"><i
																						class="icon-eye3 left"></i></a>
																				</c:if>
																			</c:if>
																		</c:forEach></td>
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
	function getSearchFilterList() {
		var dateSelectfrom = $('#dateSelect option:selected').val();
		var formdatepicker = $('#fromDate').val();
		var todatepicker = $('#toDate').val();
		$('#searchFilterForm').attr('action', "/invgi/getSearchFilterList").submit();
	} 
	
 	function downloadExcelFile(){		
		var a = document.getElementById('exceldownload');
		var stringQuery = downloadExcelFileQuery();   /* This function from loadJs.jsp */
		//alert(stringQuery);
		
		a.href = "/invgi/exportINVGIExcel?"+stringQuery;
	}
	
		$(document).ready(function() {
			$('#example').DataTable({
				"scrollX" : true,
				"searching": false,
				"info": false,
				"dom": '<"top"i>rt<"bottom"flp><"clear">'
			});
		});
		$(document).ready(function(){
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

