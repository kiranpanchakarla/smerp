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
									  <c:when test="${searchFilter.typeOf == 'vw_plant_wise_po_report' || searchFilter.typeOf == 'vw_plant_wise_inv_report'}">	<!-- this is only for Plant Wise Report -->
									  	<option value="warehouse_Name">Warehouse_Name</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'vw_inv_stock_qty_report' || searchFilter.typeOf == 'vw_inv_warehouse_report'}">	<!-- this is only for Inventory Product Report -->
									  	<option value="product_no">Product Number</option>
									  	<option value="product_description">Description</option>
									  	<option value="product_group">Product Group</option>
									  	<option value="uom">UOM</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
									
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'vw_inventory_goods_issue_report'}">	<!-- this is only for Inventory GI Report -->
									  	<option value="doc_number">DOC Number</option>
									  	<option value="doc_date">DocDate</option>
									  	<option value="product_no">Product Number</option>
									  	<option value="product_description">Description</option>
									  	<option value="product_group">Product Group</option>
									  	<option value="plant">Warehouse</option>
									  	<option value="department">Department</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
							     
								</select>
							</div></li>
							
						<li><div class="form-group">
								<!--  <label>Field:</label> -->
								<input type="text" id="filedName" class="form-control report-textbox" name="fieldName" autocomplete="off"
									placeholder="Field name">
							</div></li>
							
						 <c:if test="${searchFilter.typeOf == 'vw_inv_warehouse_report' || searchFilter.typeOf == 'vw_inventory_goods_issue_report'}">
							   
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
							     
								</c:if>
							
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
							 <c:if test="${searchFilter.typeOf != 'vw_inv_stock_qty_report' && searchFilter.typeOf != 'vw_inv_warehouse_report'  && searchFilter.typeOf != 'vw_inventory_goods_issue_report'}">
							    <label>Created Date:</label>
								<!-- <label style="margin-right: 30px;">From:</label>  -->
								<input type="text" id="fromDate" name="fromDate"
									class="form-control report-select-box1" placeholder="From Date" autocomplete="off"/>
								</c:if>
								 <c:if test="${searchFilter.typeOf == 'vw_inv_stock_qty_report'}">
								  <label for="sortBy">Sort By:</label> 
								    <select id="sortBy" class="form-control sort-report" name="sortBy">
								    <option value="select">select</option>
									<option value="Madurawada">Madurawada</option>
									<option value="Yelamanchili">Yelamanchili</option>
									
								</select>
								 </c:if>
							</div>
							
									  
						</li>
                       
						<li>
						
							<div class="form-group">
							<c:if test="${searchFilter.typeOf != 'vw_inv_stock_qty_report' && searchFilter.typeOf != 'vw_inv_warehouse_report'  && searchFilter.typeOf != 'vw_inventory_goods_issue_report'}">
								<!-- <label class="list-search2">To: </label>  -->
								<input type="text" id="toDate" name="toDate"
									class="form-control report-select-box2" placeholder="To Date"  autocomplete="off" >
							</c:if>
							</div>
							
						</li>

						<li>

							<ul>
							<c:if test="${searchFilter.typeOf != 'vw_inv_warehouse_report' && searchFilter.typeOf != 'vw_inventory_goods_issue_report'}">
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
								</c:if>
							</ul>
					</ul>
				</div>
				<input type="hidden" id="isConvertedDoc" name="isConvertedDoc" value=""/>
				</form>
				
				 
							
							<div class="col-sm-4"><a href="#excelDownload"  id="exceldownload" onclick="downloadExcelFile()" class="excel-download exceldownload float-sm-right" title="Excel Report Download"   >Excel Download</a>
								</div>
						 
			</div>

</div>

