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
								<select id="searchBy" class="form-control" name="searchBy">
									<option value="select">select</option>
									<option value="documentNo">Document No</option>
									<option value="status">Status</option>
									<c:choose>
									  <c:when test="${searchFilter.typeOf != 'InventoryGoodsTransfer'}">	<!-- this is only for Purchase Request -->
									  	<option value="plant">Plant Name</option>
									  </c:when>
									  <c:otherwise>
									   	
									  </c:otherwise>
									</c:choose>
									
									
							<c:choose>
								<c:when test="${searchFilter.typeOf != 'InventoryGoodsReceipt' && searchFilter.typeOf != 'InventoryGoodsIssue' && searchFilter.typeOf != 'InventoryGoodsTransfer'}">	
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'PurchaseRequest'}">	<!-- this is only for Purchase Request -->
									  	<option value="userName">User Name</option>
									  </c:when>
									  <c:otherwise>
									   	<option value="vendorName">Vendor Name</option>
									  </c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									  <!--  <option value="field">Field Name</option> -->
								</c:otherwise>
							</c:choose>
								</select>
							</div></li>
						<li><div class="form-group full-group">
								<!--  <label>Field:</label> -->
								<input type="text" id="filedName" class="form-control" name="fieldName" autocomplete="off"
									placeholder="Field name">
							</div></li>
						<li><div class="form-group">
								<!--  <label>Search By:</label> -->
								<label for="sortBy">Sort By:</label> 
								<select id="sortBy" class="form-control" name="sortBy">
								    <option value="select">select</option>
									<option value="createdDate" selected>Created Date</option>
									<option value="documentNo">Document No</option>
									<option value="updatedDate">Modified Date</option>
									<option value="status">Status</option>
							<c:choose>
								<c:when test="${searchFilter.typeOf != 'InventoryGoodsReceipt' && searchFilter.typeOf != 'InventoryGoodsIssue' && searchFilter.typeOf != 'InventoryGoodsTransfer'}">	
									
									<c:choose>
									  <c:when test="${searchFilter.typeOf == 'PurchaseRequest'}">	<!-- this is only for Purchase Request -->
									  	<option value="userName">User Name</option>
									  </c:when>
									  <c:otherwise>
									   	<option value="vendorName">Vendor Name</option>
									  </c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									  <!--  <option value="field">Field Name</option> -->
								</c:otherwise>
							</c:choose>
									
								</select>
							</div></li>
					</ul>
					<ul class="po-wrap-list last-list">
						<li>
							<div class="form-group">
								<!--  <label>Search By:</label> -->
								<label for="datelabel" class="last-list-search">Date:</label> 
								<select id="dateSelect" class="form-control" name="dateSelect">
									<option value="select">select</option>
									<option value="createdDate">Created Date</option>
									<option value="updatedDate">Modified Date</option>
								</select>
							</div>
						</li>

						<li>
							<div class="form-group">
								<label>From:</label> 
								<input type="text" id="fromDate" name="fromDate"
									class="form-control list-search1" placeholder="From Date" autocomplete="off"/>
							</div>
						</li>

						<li>
							<div class="form-group">
								<label class="list-search2">To: </label> 
								<input type="text" id="toDate" name="toDate"
									class="form-control list-search3" placeholder="To Date" autocomplete="off">
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
										<input type="reset" id="reset" value="Reset"
											class="btn btn-warning" />
									</div>
								</li>
								
							</ul>
					</ul>
				</div>
				<input type="hidden" id="isConvertedDoc" name="isConvertedDoc" value=""/>
				</form>
				
				<c:choose>
					<c:when test="${searchFilter.typeOf != 'InventoryGoodsReceipt' && searchFilter.typeOf != 'InventoryGoodsIssue' && searchFilter.typeOf != 'InventoryGoodsTransfer'}">	
						
					<c:choose>
						<c:when test="${searchFilter.isConvertedDoc == 'true'}">
							
						</c:when>
						<c:otherwise>
							
							<div class="col-sm-4"><a href="#"  id="exceldownload" onclick="downloadExcelFile()" class="excel-download exceldownload float-sm-right" title="Excel Report Download">Excel Download</a>
								</div>
						</c:otherwise>
					</c:choose>
				</c:when>
					<c:otherwise>
									  <!--  <option value="field">Field Name</option> -->
					</c:otherwise>
			</c:choose>
			</div>

</div>

