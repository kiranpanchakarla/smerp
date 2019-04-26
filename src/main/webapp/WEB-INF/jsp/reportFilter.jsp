<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.security.Principal"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="filter-search-wrap">
	<form class="form" id="searchFilterForm" action="" modelAttribute="searchFilter" method="get">
		<div class="form-body">
			<div class="row">
				<div class="col-sm-8">
					<ul class="po-wrap-list">
						<li><div class="form-group">
								<!--  <label>Search By:</label> -->
								<label for="searchlabel">Search By:</label> 
								<select id="searchBy" class="form-control report-select-box" name="searchBy">
									<option value="select">select</option>
									   
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'vw_vendor_wise_po_report' || searchFilter.typeOf == 'vw_vendor_wise_inv_report'}">	<!-- this is only for Purchase Request -->
									    <option value="vendor_Code">Vendor_Code</option>
									  	<option value="vendor_Name">Vendor_Name</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
									
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'vw_product_wise_po_report' || searchFilter.typeOf == 'vw_product_wise_inv_report'}">	<!-- this is only for Purchase Request -->
									  	<option value="product_number">Product Number</option>
									  	<option value="description">Description</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
									
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'vw_plant_wise_po_report' || searchFilter.typeOf == 'vw_plant_wise_inv_report'}">	<!-- this is only for Purchase Request -->
									  	<option value="warehouse_Name">Warehouse_Name</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
							 
								</select>
							</div></li>
							
						<li><div class="form-group">
								<!--  <label>Field:</label> -->
								<input type="text" id="filedName" class="form-control" name="fieldName" autocomplete="off"
									placeholder="Field name">
							</div></li>
							
						 <%--  <li><div class="form-group">
								<!--  <label>Search By:</label> -->
								<label for="sortBy">Sort By:</label> 
								<select id="sortBy" class="form-control" name="sortBy">
								    <option value="select">select</option>
								    <c:choose>
									  <c:when test="${searchFilter.typeOf == 'vw_vendor_wise_po_report'}">	<!-- this is only for Purchase Request -->
									  	<option value="vendor_Code">Vendor_Code</option>
									  	<option value="vendor_Name">Vendor_Name</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
									
								</select>
							</div></li>  --%> 
							
					</ul>
					<ul class="po-wrap-list last-list">
						
						<!-- <li>
							<div class="form-group">
								 <label>Search By:</label>
								<label for="datelabel" class="last-list-search">Date:</label> 
								<select id="dateSelect" class="form-control" name="dateSelect">
									<option value="po_Year">Year</option>
									
								</select>
							</div>
						</li> -->

						<li>
							<div class="form-group">
							    <label>Created Date:</label>
								<!-- <label style="margin-right: 30px;">From:</label>  -->
								<input type="text" id="fromDate" name="fromDate"
									class="form-control report-select-box1" placeholder="From Date" autocomplete="off"/>
							</div>
						</li>
                       
						<li>
							<div class="form-group">
								<!-- <label class="list-search2">To: </label>  -->
								<input type="text" id="toDate" name="toDate"
									class="form-control report-select-box2" placeholder="To Date"  autocomplete="off" >
							</div>
						</li>

						<li>

							<ul>
								<li>
									<div class="form-group">
										<!-- <label>User</label> -->
										<input type="button" id="search" value="Search" onclick="getSearchFilterList()"
											class="btn btn-primary" />
									</div>
								</li>

								<li>
									<div class="form-group">
										<!-- <label>User</label> -->
										<input type="button" id="reset" value="Reset" onclick="getSearchFilterResetList()"
											class="btn btn-warning" />
									</div>
								</li>
								
							</ul>
					</ul>
				</div>
				<input type="hidden" id="isConvertedDoc" name="isConvertedDoc" value=""/>
				</form>
				
				 
							
							<div class="col-sm-4"><a href="#excelDownload"  id="exceldownload" onclick="downloadExcelFile()" class="excel-download exceldownload float-sm-right" title="Excel Report Download"   >Excel Download</a>
								</div>
						 
			</div>

</div>

