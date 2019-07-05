
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
												<div class="col-md-8">
													<h2 class="content-header-title">Purchase Order - Product Wise Annual Report</h2>
												</div>
												 
												<div class="col-md-4">
													<ol class="breadcrumb">
														<li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">Home</a></li>
														<li class="breadcrumb-item"><a href="#">Report</a>
														</li>
														<li class="breadcrumb-item active">Product Wise</li>
													</ol>
												</div>
											</div>

										</div>
										<c:import url="/WEB-INF/jsp/annualFilter.jsp" />
										<div class="card-body collapse in">
											<div class="card-block card-dashboard">

												<div class="row">
													<table id="example"
														class="display nowrap table table_padding_custom table-hover table-striped table-bordered"
														style="width: 100%">
														
														<thead>
															<tr>
														     	 
																<th style="width: 50px;">S.No</th>
																<th>Product Number</th>
																<th>Description</th>
																<th>Total Quantity</th>
																<th>Total Amount</th>
															 
																 
															</tr>
														</thead>
														
														<tbody>
														 
															 
															<c:forEach items="${vendorList}" var="product">
																<tr>
																	<td style="width: 50px;"><c:set var="count" value="${count + 1}"
																			scope="page" /> <c:out value="${count}" /></td>
																	<td>${product.productName}</td>
																	<td>${product.description}</td>
																	<td><fmt:formatNumber type="number" pattern="###0.00" value="${product.totalQty}" /></td> 
																	<td><fmt:formatNumber type="number" pattern="###0.00" value="${product.totalAmount}" /></td> 
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
	/* $('#exceldownload').hide(); */
	
	  $('#example').removeAttr('width').DataTable( {
		  "scrollX": true,
		  "scrollY": true,
		  "searching": false,
			"info": false,
			"dom": '<"top"i>rt<"bottom"flp><"clear">',
			 
	    } );
	  $('[data-toggle="tooltip"]').tooltip();  
	    
	    
} );
 
$(document).ready(function() {
    
	$("#fromDate1").datepicker({
		dateFormat : 'dd/mm/yy'
		}).datepicker("option", {
			setDate : "0",
			minDate : '-3y +1d',
			maxDate:'+10y -1d',
			changeYear: true,
	        changeMonth:true,
			onSelect: function(selectedDate) {
	          	 var date = $(this).datepicker("getDate");
	               date.setDate(date.getDate());
	               $("#todatepicker").datepicker("setDate", date);
	               $("#toDate").datepicker( "option", "minDate", selectedDate );
	               date.setDate(date.getDate() + 10);
	          }
		});

		$("#toDate1").datepicker({
			dateFormat : 'dd/mm/yy'
		}).datepicker("option", {
			setDate : "0",
			minDate : '-3y +1d',
			maxDate:'+10y -1d',
			changeYear: true,
	        changeMonth:true
		});
		
	}); 
 

function getSearchFilterList() {
	$('#exceldownload').show();
	
	var dateSelectfrom = $('#dateSelect option:selected').val();
	var formdatepicker = $('#fromDate').val();
	var todatepicker = $('#toDate').val(); 
	$('#searchFilterForm').attr('action',"<c:url value='/annualProductReport/getSearchFilterPOList'/>").submit();
	
} 


function getSearchFilterResetList() {
	 
	$('#searchFilterForm').attr('action',"<c:url value='/annualProductReport/polist'/>").submit();
} 

function downloadExcelFile(){	
	var a = document.getElementById('exceldownload');
	var stringQuery = downloadExcelFileQuery();   /* This function from loadJs.jsp */
	//alert(stringQuery);
	a.href = "<c:url value='/annualProductReport/exportPOExcel?id=vw_product_wise_po_report?'/>"+stringQuery;
} 
</script>
<script src=<c:url value="/resources/js/scripts/dataTables/buttons.html5.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/dataTables/dataTables.buttons.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/dataTables/jquery.dataTables.min.js"/> type="text/javascript"></script> 
<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>
</body>
</html>

