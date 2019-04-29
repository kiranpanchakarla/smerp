
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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
												<div class="col-md-5">
													<h2 class="content-header-title">Invoice - Vendor Wise Report</h2>
												</div>
												<div class="col-md-3">
												 
												</div>
												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">Home</a></li>
														<li class="breadcrumb-item"><a href="#">Report</a>
														</li>
														<li class="breadcrumb-item active">Vendor Wise</li>
													</ol>
												</div>
											</div>

										</div>
										<c:import url="/WEB-INF/jsp/reportFilter.jsp" />
										<div class="card-body collapse in">
											<div class="card-block card-dashboard">

												<div class="row">
													<table id="example"
														class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
														style="width: 100%">
														
														<thead>
															<tr>
																<th style="width: 50px;">S.No</th>
																<th style="width: 100px;">Vendor Code</th>
																<th style="width: 100px;">Vendor Name</th>
																
																<c:forEach items="${searchListMonths}" var="month"> 
																<th style="text-align: right;">${month}-Amt</th>
																</c:forEach>  
																 
																 
															</tr>
														</thead>
														
														<tbody>
														 
															
															 <c:forEach items="${vendorList}" var="vendor">  
																<tr>
																	<td> <c:set var="count" value="${count + 1}" scope="page" /> <c:out value="${count}" /></td>
																	<td>${vendor.vendorCode}</td>
																	<td>${vendor.vendorName}</td> 
																	  <c:forEach items="${searchListMonths}" var="month">   
																	 <c:if test="${month eq 'JAN'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.JAN}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'FEB'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.FEB}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'MAR'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.MAR}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'APR'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.APR}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'MAY'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.MAY}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'JUN'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.JUN}"/></td>
																	</c:if>
																	  <c:if test="${month == 'JUL'}">  
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.JUL}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'AUG'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.AUG}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'SEP'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.SEP}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'OCT'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.OCT}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'NOV'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.NOV}"/></td>
																	 </c:if>
																	  <c:if test="${month == 'DEC'}"> 
																	 <td style="text-align: right;"><fmt:formatNumber type="number" pattern = "###0.00" value="${vendor.DEC}"/></td>
																	</c:if>
																	  
																	  </c:forEach>    
																	 
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
		  "scrollX": true,
		  "scrollY": true,
		  "searching": false,
			"info": false,
			"dom": '<"top"i>rt<"bottom"flp><"clear">'
	    } );
} );
 
 
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();  
    //$('.btn-edit').tooltip('open');
});

function getSearchFilterList() {
	var dateSelectfrom = $('#dateSelect option:selected').val();
	var formdatepicker = $('#fromDate').val();
	var todatepicker = $('#toDate').val(); 
	$('#searchFilterForm').attr('action',"<c:url value='/vendorReport/getSearchFilterINVList'/>").submit();
} 


function getSearchFilterResetList() {
	 
	$('#searchFilterForm').attr('action',"<c:url value='/vendorReport/invlist'/>").submit();
} 

function downloadExcelFile(){		
	var a = document.getElementById('exceldownload');
	var stringQuery = downloadExcelFileQuery();   /* This function from loadJs.jsp */
	//alert(stringQuery);
	
	a.href = "<c:url value='/vendorReport/exportPOExcel?id=vw_vendor_wise_inv_report?'/>"+stringQuery;
} 
</script>
<script src=<c:url value="/resources/js/scripts/dataTables/buttons.html5.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/dataTables/dataTables.buttons.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/dataTables/jquery.dataTables.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>
</body>
</html>

