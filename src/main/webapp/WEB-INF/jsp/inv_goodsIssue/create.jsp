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


<script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/>
	type="text/javascript"></script>
<!-- <script
	src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/>
	type="text/javascript"></script> -->
<script
	src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/>
	type="text/javascript"></script>


<script
	src=<c:url value="/resources/js/scripts/datepicker/bootstrap-datepicker.min.js"/>
	type="text/javascript"></script>
<link
	href="<c:url value="/resources/css/datapickercss/bootstrap-datepicker.min.css"/>"
	rel="stylesheet" type="text/css" />

<script src=<c:url value="/resources/js/common.js"/>
	type="text/javascript"></script>

</head>
<style>
.table td a i {
	line-height: 35px;
}
</style>


<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/inventorysidebar.jsp" />
	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<!-- <div class="content-header row">
				<div class="col-md-6">
					<h4>gr</h4>
				</div>
			</div> -->
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">

							<c:url value="/invgi/save" var="createUrl" />
							<form:form method="POST" action="${createUrl}" id="form"
								class="bv-form commentForm" enctype="multipart/form-data"
								modelAttribute="gr" data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card-box">
												<div class="card-header">
													<div class="col-md-6">
														<c:if test="${gr.id!=null}">
															<h2 class="card-title" id="basic-layout-icons">Update
																Inventory Goods Issue</h2>
															<form:input type="hidden" cssClass="form-control"
																path="id" />
														</c:if>

														<c:if test="${gr.id==null}">
															<h2 class="card-title" id="basic-layout-icons">Create
																Inventory New Goods Issue</h2>
														</c:if>
													</div>
													<div class="col-md-6">
														<c:if test="${gr.id!=null}">
															<a class="btn btn-primary float-right"> ${gr.status}
															</a>
														</c:if>
													</div>
												</div>

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																 

																<form:hidden path="id" />

															<div class="row">

																	<div class="col-sm-6 form-group">
																		<label>Doc#</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Document Number' path="docNumber"
																			readonly="true" />
																	</div>
																	
																	<div class="col-sm-6 form-group">
																                <label>Ref Doc No.</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Reference Doc Number'
																						path="referenceDocNumber" />
																				</div>
																	
																</div>
																
																<div class="row">
																				 
																                <div class="col-sm-6 form-group">
																					<label>Posting Date</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Posting Date' path="postingDate"
																						autocomplete="off" required="true" />

																				 </div>
																				  
																                <div class="col-sm-6 form-group">
																					<label>Doc Date</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Document Date' path="documentDate"
																						autocomplete="off" required="true" />

																				</div> 
																				 
																			</div>
																			</div>

																<div class="card-body collapse in create-block">
																	 	<input type="hidden" id="addressCount" value="0">

																	<div class="tab-content">
																		
																			<div class="row">
																				<div class="col-xs-12">
																					<div class="row-in">

																						<div class="table-responsive">
																							<div class="full-col-table">
																								<table
																									class="table table-bordered table-striped item-service-table"
																									id="itemTbl">
																									<thead>
																										<tr>
																											<!-- <th>S.No</th> -->
																											<th style="display: none;">Product Id</th>
																											<th>Product#</th>
																											<th>Description</th>
																											<th>Warehouse</th>
																											<th>Quantity</th>
																											<th>Unit Price</th>
																											<th>Tax %</th>
																											<th>Tax Total</th>
																											<th>Total</th>
																											<th>Department</th>
																											<th>Group</th>
																											<th>UOM</th>
																											<th>HSN Code</th>
																											
																											<th>Action</th>
																										</tr>
																									</thead>
																									<tbody>

																									</tbody>
																								</table>
  
																								<!--1 multiply Dynamically Load   -->
																							 	<c:if test="${not empty inventoryGoodsIssueList}"> 
																									 <form:hidden path="id" /> 
																									<table
																										class="table table-bordered table-striped item-service-table"
																										id="edit_item_serviceTbl">

																										<thead>
																											<tr>
																												<!-- <th>S.No</th> -->
																												<th style="display: none;">Product Id</th>
																												 
																											<th>Product#</th>
																											<th>Description</th>
																											<th>Warehouse</th>
																											<th>Quantity</th>
																											<th>Unit Price</th>
																											<th>Tax %</th>
																											<th>Tax Total</th>
																											<th>Total</th>
																											<th>Department</th>
																											<th>Product Group</th>
																											<th>HSN Code</th>
																											<th>UOM</th>
																											<th>Action</th>
																												 

																											</tr>
																										</thead>

																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${inventoryGoodsIssueList}"
																												var="listLineItems">
  
																													<tr class="multTot multTot${count}">
																														<td style="display: none;"><div
																																class="form-group">
																																<form:input type="hidden"
																																	path="inventoryGoodsIssueList[${count}].productId"
																																	value="${listLineItems.productId}"
																																	class="form-control productId"></form:input>
																															</div> <form:hidden
																																path="inventoryGoodsIssueList[${count}].id" />
																														</td>
 
																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].productNumber"
																																		required="true"
																																		value="${listLineItems.productNumber}"
																																		class="form-control productNumber"></form:input>
																																</div></td>

																															<td>
																																<div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].description"
																																		onkeypress="return isNumericKey(event)"
																																		required="true"
																																		value="${listLineItems.description}"
																																		class="form-control description validatePrice"></form:input>
																																</div>
																															</td>

																															<td><div class="form-group">
																																	<form:select class="form-control warehouse"
																																		  required="true"
																																		path="inventoryGoodsIssueList[${count}].warehouse">
																																		<form:option value="" label="Select" />
																																		<form:options items="${plantMap}" />
																																	</form:select>
																																</div></td>


																															<td class="gr-main">
			                                                                                                               <img src="${contextPath}/resources/images/portrait/info.png" alt="See" id="output"
			                                                                                                                width="15" height="15" class="quality-alert" data-toggle="tooltip" data-placement="top" 
			                                                                                                                title="InStockQuantity ${listLineItems.tempRequiredQuantity}"/>
			                                                                                                                <div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].requiredQuantity"
																																		value="${listLineItems.requiredQuantity}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control requiredQuantity validatePrice validateQuantity"
																																		autocomplete="off" required="true"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text" readonly="true"
																																		path="inventoryGoodsIssueList[${count}].unitPrice"
																																		onkeypress="return isNumericKey(event)"
																																		value="${listLineItems.unitPrice}"
																																		class="form-control unitPrice validatePrice"></form:input>
																																</div></td>


																															<td><div class="form-group">
																																	<%-- <form:select
																																		class="form-control taxCode"
																																		 required="true"
																																		path="inventoryGoodsIssueList[${count}].taxCode">
																																		<form:option value="" label="Select" />
																																		<form:options items="${taxCodeMap}" />
																																	</form:select> --%>
																																	 <select class="form-control taxCode" required="true" style="width: 100%;"
																															name="inventoryGoodsIssueList[${count}].taxCode" >
																															<option  value="" >Select</option>	
																														<c:forEach var="taxCodeMap" items="${taxCodeMap}">
																														
																													  <c:choose>
																													<c:when
																														test="${taxCodeMap.key == listLineItems.taxDescription}">
																													<option  value="${taxCodeMap.value}" selected>${taxCodeMap.key}</option>
																													</c:when>
																													<c:otherwise>
																													<option value="${taxCodeMap.value}">${taxCodeMap.key}</option>
																													</c:otherwise>
																														</c:choose>
																														</c:forEach>
																														</select> 
																														<input type="hidden" name="inventoryGoodsIssueList[${count}].taxDescription"  class="taxDescription"  value="${listLineItems.taxDescription}"  />
																														
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].taxTotal"
																																		value="${listLineItems.taxTotal}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control taxTotal "
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].total"
																																		value="${listLineItems.total}"
																																		onkeypress="return isNumericKey(event)"
																																		class="form-control total"
																																		readonly="true"></form:input>
																																</div></td>
																																
																																
																														
																																
																																<td><div class="form-group">
																																	<form:select class="form-control"
																																		  required="true"
																																		path="inventoryGoodsIssueList[${count}].department">
																																		<form:option value="" label="Select" />
																																		<form:options items="${deptMap}" />
																																	</form:select>
																																</div></td>


																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].productGroup"
																																		value="${listLineItems.productGroup}"
																																		class="form-control productGroup"
																																		readonly="true"></form:input>
																																</div></td>
																																
																																<td><div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].uom"
																																		value="${listLineItems.uom}"
																																		class="form-control uom"
																																		readonly="true"></form:input>
																																</div></td>

																															<td><div class="form-group">
																																	<form:input type="text"
																																		path="inventoryGoodsIssueList[${count}].hsn"
																																		value="${listLineItems.hsn}"
																																		class="form-control hsnVal"
																																		readonly="true"></form:input>
																																</div></td>


 
																														<td class="text-center"><a
																															onclick="removeData2(${count})"
																															class="tdicon remove confirm-delete"
																															data-toggle="modal"><i
																																class="icon-bin left"></i></a></td>

																													</tr>

																												<c:set var="count" value="${count + 1}"
																													scope="page" />
																											</c:forEach>
                                                                                                     

																										</tbody>
																									</table>
																									<input type="hidden" id="edit_addressCount"
																										value="${count-1}">
																								 </c:if>


																							</div>
																						</div>
																						 
																							<div class="add-row-block">
																								<input type="button" class="btn btn-info"
																									onclick="addItem()" value="Add Row">

																							</div>
																						 


																					</div>
																				</div>
																			</div>
																		</div>
 
																		<br>


																		<!--Calculation Part  -->

																		<div class="row">
																			<div class="col-sm-4">
																				<div class="create-block">
																					<div class="form-group">
																						<label>Remark</label>
																						<form:input type="text"
																							cssClass="form-control camelCase"
																							placeholder='Enter your Remark'
																							autocomplete="off" path="remarks" />
																					</div>
																				</div>
																			</div>
																			<div class="col-sm-4">&nbsp;</div>

																			<div class="col-sm-4 create-po-wrap">
																				<div class="row">
																                <div class="col-sm-12 form-group">
																					<label>Discount(%)</label>
																					 
																							<form:input type="text"
																								cssClass="form-control validatePrice"
																								id="totalDiscount"
																								placeholder='Total Discount '
																								path="totalDiscount" autocomplete="off"
																								onkeypress="return isNumericKey1(event)" />
																				 


																				</div></div>

																				<div class="row">
																                <div class="col-sm-12 form-group">
																					<label>Total Invoice Amount</label>

																					<form:input type="text" cssClass="form-control"
																						placeholder='Total Before Discount '
																						path="totalBeforeDisAmt" autocomplete="off"
																						readonly="true" />


																				</div></div>
 
																				<div class="row">
																                <div class="col-sm-12 form-group">
																					<label>Freight</label>
																					 
																							<form:input type="text"
																								cssClass="form-control validatePrice"
																								placeholder='Freight' path="freight"
																								onkeypress="return isNumericKey1(event)"
																								autocomplete="off" />
																						 
																				</div></div>


																				<div class="row">
																                <div class="col-sm-12 form-group">
																					<label>Tax Amount</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Tax Amount' path="taxAmt"
																						autocomplete="off" readonly="true" />
																				</div></div>
																				
																				<div class="row">
																                <div class="col-sm-12 form-group">
																					<label>Total</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Total' path="amtRounding"
																						autocomplete="off" readonly="true" />
																				</div></div>
																				
																				<div class="row">
																						<div class="col-sm-12 form-group">
																 						<label>Rounded Off</label> 
																 						<form:input type="text" cssClass="form-control"
																							placeholder='Rounded Off' path="roundedOff"
																							autocomplete="off" readonly="true" />  
																						</div></div>

																				<div class="row">
																                <div class="col-sm-12 form-group">
																					<label>Total Payment Due</label>
																					<form:input type="text" cssClass="form-control"
																						placeholder='Total Payment Due'
																						path="totalPayment" autocomplete="off"
																						readonly="true" />
																				</div></div>
																				
																				
																			</div>
																		</div>







																		<!--Calculation Part  -->

																		<div class="text-xs-center">

																			<a href="<c:url value="/invgi/list"/>"
																				class="btn btn-primary float-left"> Back </a>


																			<c:if test="${gr.status eq 'Draft' || gr.id==null }">
																				<form:button type="submit" id="draft"
																					name="statusType" value="DR"
																					class="btn btn-draft mySubButton">
																					<i class="icon-check2"></i> Draft</form:button>
																			</c:if>
																			<c:if test="${gr.id==null}">
																				<form:button type="submit" id="save"
																					name="statusType" value="SA"
																					class="btn btn-primary mySubButton">
																					<i class="icon-check2"></i>Save</form:button>
																			</c:if>
																			<c:if test="${gr.id!=null}">
																				<form:button type="submit" id="update"
																					name="statusType" value="SA"
																					class="btn btn-primary mySubButton">
																					<i class="icon-check2"></i> Update</form:button>
																				<%--  <a href="<c:url value="/gr/cancelStage?id=${gr.id}"/>">
																			<button type="button" class="btn btn-warning mr-1">
																				<i class="icon-cross2"></i> Cancel
																			</button>
																		</a> --%>

																			</c:if>
																			<!-- Approve -->
																			<c:forEach items="${sessionScope.umpmap}" var="ump">
																				<c:if test="${ump.key eq 'Inventory Goods Issue'}">
																					<c:set var="permissions" scope="session"
																						value="${ump.value}" />
																					<c:if
																						test="${fn:containsIgnoreCase(permissions,'Approve')}">
																						<form:button type="submit" id="approve"
																							name="statusType" value="APP"
																							class="btn btn-approve mySubButton">
																							<i class="icon-check2"></i>Approve</form:button>
																					</c:if>
																				</c:if>
																			</c:forEach>
																			<!-- Reject -->
																			<c:forEach items="${sessionScope.umpmap}" var="ump">
																				<c:if test="${ump.key eq 'Inventory Goods Issue'}">
																					<c:set var="permissions" scope="session"
																						value="${ump.value}" />
																					<c:if
																						test="${fn:containsIgnoreCase(permissions,'Reject')}">
																						<c:if test="${gr.status != 'Cancelled'}">
																							<form:button type="submit" id="reject"
																								name="statusType" value="RE"
																								class="btn btn-reject mySubButton">
																								<i class="icon-cross2"></i>Reject</form:button>
																						</c:if>
																					</c:if>
																				</c:if>
																			</c:forEach>


																		</div>
																	</div>
																</div>
															</div>
													</div>
												</div>
											</div>
										</div>
								</section>
							</form:form>

						</div>
					</div>
					<!--/ project charts -->
					<br>
				</div>
			</div>
		</div>
	</div>



	<c:import url="/WEB-INF/jsp/footer.jsp" />






</body>
<!-- alertfy-->
<!-- form validations-->


<!-----------    END   ---------------------------------->


<script type="text/javascript">

var sizeplant = "${plantMapSize}";
var scriptSelectPlant='';
if(sizeplant>1) {
    scriptSelectPlant ='<option value="">select</option>';
     }
var sizeplant = "${departmentListSize}";
var scriptSelectDept='';
if(sizeplant>1) {
    scriptSelectDept ='<option value="">select</option>';
     } 
     
var inc=0;
var edit_addressCount=0;

$("#itemTbl").show();
	
	 
		if ($('#edit_addressCount').val() != undefined ) {
			// alert("edit");
			 $("#serviceTbl").hide();
			 $("#itemTbl").hide();
		}

		if ($('#edit_addressCount').val() != undefined ) {
		}else{
			addItem();
		}


function addItem() {
		
	
						var addressCount = $('#addressCount').val();
						 edit_addressCount = $('#edit_addressCount').val();
						if (edit_addressCount != undefined ) {
							    inc = parseInt(edit_addressCount)+1;
								$('#edit_addressCount').val(inc);
						}else {
								inc = parseInt(addressCount) ;
						}
						
						
						//alert(inc);
						
	    
		 
	        var item_table_data = '<tr class="multTot multTot'+inc+'">'
	        
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].productNumber" autocomplete="off"  class="form-control productNumber productNumber'+inc+'" required="true" id="productNumber'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td style="display:none;">'
			+'<div class="form-group">'
			+'<input type="hidden" name="inventoryGoodsIssueList['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].description" required="true" autocomplete="off"  class="form-control description '+inc+'" id="description'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="inventoryGoodsIssueList['+inc+'].warehouse" required="true"   class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
			+ scriptSelectPlant +
			<c:forEach items="${plantMap}" var="plantMap">
			'<option value="${plantMap.key}">${plantMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			+ '<td class="gr-main">'
			+'<img src="${contextPath}/resources/images/portrait/info.png" alt="See" id="output" width="15" height="15" class="quality-alert" data-toggle="tooltip" data-placement="top" title="InStockQunatity" />'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].requiredQuantity" autocomplete="off" maxlength="5" onkeypress="return isNumericKey(event)"  required="true" class="form-control validatePrice validateQuantity requiredQuantity'+inc+' requiredQuantity" id="requiredQuantity'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].unitPrice" autocomplete="off" onkeypress="return isNumericKey(event)" readonly="true" required="true" class="form-control validatePrice unitPrice'+inc+' unitPrice" id="unitPrice'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="inventoryGoodsIssueList['+inc+'].taxCode" required="true"   class="form-control taxCode" style="width: 100%;" id="taxCode'+inc+'" >'
			+'<option value="">Select</option>'+
			<c:forEach items="${taxCodeMap}" var="taxCodeMap">
			'<option value="${taxCodeMap.value}">${taxCodeMap.key}</option>'+
			</c:forEach>
			+ '</select>'
			+'<input type="hidden" name="inventoryGoodsIssueList['+inc+'].taxDescription"  class="taxDescription"    />'
			+ '</div>'
			+ '</td>'
			
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].taxTotal" onkeypress="return isNumericKey(event)"  readonly="true" class="form-control  taxTotal'+inc+' taxTotal" id="taxTotal'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].total" onkeypress="return isNumericKey(event)"  readonly="true" class="form-control total'+inc+' total" id="total'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			
			+ '<td>'
			+'<div class="form-group">'
			+ '<select  name="inventoryGoodsIssueList['+inc+'].department" required="true" class="form-control department'+inc+' department"  id="department'+inc+'" >'
			+ scriptSelectDept +
			<c:forEach items="${deptMap}" var="deptMap">
			'<option value="${deptMap.key}">${deptMap.value}</option>'+
			</c:forEach>
			+ '</select>'
			+ '</div>'
			+ '</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].productGroup" readonly="true" class="form-control  productGroup productGroup'+inc+'" id="productGroup'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].uom" class="form-control uom uom'+inc+'" id="uom'+inc+'"  readonly="true"  />'
			+ '</div>'
			+'</td>'
			
			+'<td>'
			+'<div class="form-group">'
			+'<input type="text" name="inventoryGoodsIssueList['+inc+'].hsn" readonly="true" class="form-control hsnVal hsn'+inc+'" id="hsn'+inc+'"   />'
			+ '</div>'
			+'</td>'
			
			+ ' <td class="text-center"><a  onclick="removeData('+inc+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
			+ '</td>'
			 
			
	+'</tr>';
	
	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		$("#edit_item_serviceTbl").append(item_table_data);
	}else{
		 $("#itemTbl").append(item_table_data);
	}
		inc++;
		$('#addressCount').val(inc);
		$("#form").validator("update");
	}



$(document).ready(function(){
	
	
	var id=$('#id').val();
	
	//alert("id--->"+id);

	        $('#postingDate').datepicker({ 
		    	 dateFormat: 'dd/mm/yy' ,   //    dateFormat: 'MM dd, yy' 
		    }).datepicker( "option", { setDate:"0",
		            maxDate:'+3y -1d',
		            minDate:'0' } );
		    
		    $('#documentDate').datepicker({ 
		   	 dateFormat: 'dd/mm/yy' ,   //    dateFormat: 'MM dd, yy' 
		   }).datepicker( "option", { setDate:"0",
		           maxDate:'+3y -1d',
		           minDate:'0' } );
		    
		    
	
	
		if(id!=''){
			
			 
		}else{
		$('#postingDate').datepicker("setDate", "0"); //"0" for current date
		$('#documentDate').datepicker("setDate", "0"); //"0" for current date
		$('#require_date').datepicker("setDate", "10"); //"after 10" for current date
		}
	
	
		 $('#postingDate').datepicker({
			  dateFormat: 'dd/mm/yy' 
				  });
			
		  $('#documentDate').datepicker({
			  dateFormat: 'dd/mm/yy' 
		  });
		  
		

	 var productList =[];
	 var name;
	 var productNumbers= ${productList};
	 var availableTags = [];
		$.each(productNumbers, function (index, value) {
		   availableTags.push(value.toString());
	   });
	 //alert("length"+availableTags);
		 
		//$(".productNumber").autocomplete({
	$(document).on("focus", ".productNumber", function() {
		var itemParentRow = $(this).parents(".multTot");
		//alert("itemParentRow"+itemParentRow);
		
		$(this).autocomplete({
	        source: availableTags,
	        minLength: 0,
            scroll: true,
	        select: function(event, ui) {
	        	name = ui.item.value;
	        	//alert(name);
	             autocompleteandchange(name,itemParentRow);
	       		 },
	        }).focus(function() {
	            $(this).autocomplete("search", "");
	        }); 
		});
		
	
	 $(document).on("blur", ".productNumber", function() {
     	var itemParentRow = $(this).parents(".multTot");
     
     	var  selcProdouctNo=   $(itemParentRow).find(".prodouctNumber").val();
     	var arr=[];
     	 $(".productNumber").each(function() {
     	//alert("validation-->"+	availableTags.includes($(this).val()) );
        if(availableTags.includes($(this).val()) == true) 	 {
     	
		        if ($.inArray($(this).val(), arr) == -1){
		            arr.push($(this).val());
		            if($(this).val() == selcProdouctNo) {
  				         autocompleteandchange(selcProdouctNo,itemParentRow);
  				       }
		        }else{
		        	 /* var isDluplicate = false; */
		        	   alertify.alert("Purchase Order","You have already entered the Product Number "+$(this).val());
		        	/*  $(this).val('') */
		          ($(this).parents('tr').find('td').find('input').val(''));
		          ($(this).parents('tr').find('td').find('.warehouse').prop('selectedIndex',0));
		        
		        }
        }else {
        	if($(this).val()!=""){
       
        	 alertify.alert("Purchase Order",$(this).val() +  "Product Number Does Not Exists!");  
        	 ($(this).parents('tr').find('td').find('input').val(''));
        	 ($(this).parents('tr').find('td').find('select').val('')); 
            }
        }
		        
		    });
     });
	

	//get the product information based on product  name
    	function autocompleteandchange(name,itemParentRow){
			//alert(name);
			//alert(itemParentRow);
    	 $.ajax({
			   type: "GET",
    			data: {name :name}, 
    			async : false,
                url: "<c:url value="/product/getProductInfo"/>", 
                success: function (response) {
                	console.log("getProductInfo-->"+response);
                	
                	//alert("--->"+response);
                	if(response!="") {
                	var obj =JSON.parse(response);
                	
                	//var myJSON = JSON.stringify(obj);
                	
                	var hsndata=0;
                	
                	if(obj.hsnCode!=null) {
                		hsndata=obj.hsnCode;
                	}
                	//alert("hsnCode"+hsndata.hsnCode);
                //	$('.hsnVal').val(hsndata.hsnCode);
                
                	//$(itemParentRow).find(".productNumber").val(obj.productNo);
                	$(itemParentRow).find(".productId").val(obj.id);
            	
                
                	$(itemParentRow).find(".description").val(obj.description);
               	 $(itemParentRow).find(".hsnVal").val(hsndata.hsnCode);
               	
               	var uom=obj.purchasingUom.uomName;
               	var sku=obj.packingUom.uomName;
               
               	//alert("uom"+uom);
               	//$('.uom').val(uom);
               	
               	 $(itemParentRow).find(".uom").val(uom);
               	 
               	 $(itemParentRow).find(".sku").val(sku);
             	var unitPrice=obj.productCost;
               	 $(itemParentRow).find(".unitPrice").val(unitPrice);
                	
             	 //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
             	 //	var productgroup=obj.productCategory.categoryType;
             
             		var productgroup=obj.productGroup.productName;
             		
             	 	//$('.productGroup').val(productgroup);
             	 	
             	 	 $(itemParentRow).find(".productGroup").val(productgroup);
             	 	 $(itemParentRow).find('.description').blur();
             	 	 
                	}
                	
               	 },
                error: function(e){
               //  alert('Error: ' + e);
                 }
                });
		}
	
    	/*  Discription List */
	    var descriptionList = [];
       var getDescription = ${descriptionList};
       var availabledescTags = [];
         $.each(getDescription, function(index, value) {
          availabledescTags.push(value.toString());
      });   

          $(document).on("focus", ".description", function() {
   			var itemParentRow = $(this).parents(".multTot");
   			//alert("itemParentRow"+itemParentRow);
   			
   			$(this).autocomplete({
   		        source: availabledescTags,
   		        minLength: 0,
   	            scroll: true, 
   	            select: function(event, ui) {
   		        	var name = ui.item.value;
   		        	//alert(name);
   		            autocompleteandchangedesc(name,itemParentRow);
   		         
   		       		 },
   				}).focus(function() {
   		            $(this).autocomplete("search", "");
   		        });
   			});   
         
          $(document).on("blur", ".description", function() {
         	var itemParentRow = $(this).parents(".multTot");
         
         	var  selcDescription=   $(itemParentRow).find(".description").val();
         	var arr=[];
         	 $(".description").each(function() {
             	 
             	  if(availabledescTags.includes($(this).val()) == true) 	 {
 		        if ($.inArray($(this).val(), arr) == -1){
 		            arr.push($(this).val());
 		           if($(this).val() == selcDescription) {
		            	autocompleteandchangedesc(selcDescription,itemParentRow);
	  		       }
 		        }else{
 		        	   var isDluplicate = false;
 		        	   alertify.alert("Duplicate Entry","You have already entered the Product Description "+($(this).val()));
 		        	 $(this).val('')
 		        	 ($(this).parents('tr').find('td').find('input').val(''));
 		        	 ($(this).parents('tr').find('td').find('.warehouse').prop('selectedIndex',0));
 		        
 		        }
             }
             	  else
             	  {
             		 if($(this).val()!=""){
                  	 alertify.alert("No Entry Found",$(this).val() +  " Product Description Does Not Exists!");  
                  	  ($(this).parents('tr').find('td').find('input').val(''));
                  	   ($(this).parents('tr').find('td').find('select').val(''));
             		 }
                  }   
 		      
 		    });
                     	
         	
         });
      
         //get the product information based on product  description
          function autocompleteandchangedesc(name1, itemParentRow) {
             //alert(name);

             $.ajax({
                 type: "GET",
                 data: {name: name1},
                 async: false,
                 url: "<c:url value="/product/getProductInfoByDescription"/>", 
                 success: function(response) {
                     console.log(response);

                     var obj = JSON.parse(response);

                     //var myJSON = JSON.stringify(obj);

	                       var hsndata=0;
	                	
	                	if(obj.hsnCode!=null) {
	                		hsndata=obj.hsnCode;
	                	}
                     //alert("hsnCode"+hsndata.hsnCode);
                     //	$('.hsnVal').val(hsndata.hsnCode);
                     $(itemParentRow).find(".hsnVal").val(hsndata.hsnCode);

                    // $(itemParentRow).find(".productNumber").val(obj.description);
                     $(itemParentRow).find(".productId").val(obj.id);
                     $(itemParentRow).find(".productNumber").val(obj.productNo);
                     //$(itemParentRow).find(".productNumber").val(obj.description);

                     // $(itemParentRow).find(".productGroup").val(obj.producttype.name);
                     var sku=obj.packingUom.uomName;
            	
                     var uom = obj.purchasingUom.uomName;
                     //alert("uom" + uom);
                     //$('.uom').val(uom);

                     $(itemParentRow).find(".uom").val(uom);
                     $(itemParentRow).find(".sku").val(sku);
                     var unitPrice=obj.productCost;
                   	 $(itemParentRow).find(".unitPrice").val(unitPrice);
                     //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
                   //  var productgroup = obj.productCategory.categoryType;
                     var productgroup=obj.productGroup.productName;
                     //$('.productGroup').val(productgroup);

                     $(itemParentRow).find(".productGroup").val(productgroup);
                     $(itemParentRow).find('.productNumber').blur();
       
                 },
                 error: function(e) {
                     //  alert('Error: ' + e);
                 }
             });
                      
         }   
   /* End of Description List  */ 
	
 
	
	
  });

                                                
				
		
		
function removeData(index){
	//alert("ff"+index);
setCalculationAmt(index);
	
	var rowCount = $('#itemTbl tr').length-2;
	if(rowCount==0){
		$('#itemTbl input[type="text"]').val('');
		$('.warehouse').prop('selectedIndex',0);
		$('.taxCode').prop('selectedIndex',0);
		return false;
	}
		if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
			$('table#edit_item_serviceTbl tr.multTot'+index).remove();
		}else{
			$('table#itemTbl tr.multTot'+index).remove();
		}
		$("#form").validator("update");
}


function removeData1(index){
	//alert("ff"+index);
	setCalculationAmt(index);
	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	}else{
		$('table#serviceTbl tr.multTot'+index).remove();
	}
	$("#form").validator("update");
}


function removeData2(index){
	//alert("ff"+index);
	setCalculationAmt(index);
	var rowCount = $('#edit_item_serviceTbl tr').length-2;
	if(rowCount==0){
		$('#edit_item_serviceTbl input[type="text"]').val('');
		$('.warehouse').prop('selectedIndex',0);
		$('.taxCode').prop('selectedIndex',0);
		$('.department').prop('selectedIndex',0);
		return false;
	}
	
	$('table#edit_item_serviceTbl tr.multTot'+index).remove();
	$("#form").validator("update");
	
	
}


</script>


<script type="text/javascript">


$("#items_radio").click(function() {
	//alert("item");
	 alertify.confirm("Inventory Goods Issue",'Are you Sure Want to Change  Item ,Service will be removed ', function(){
		 $("#serviceTbl").hide();
		 $("#itemTbl").show();
		 $("#edit_item_serviceTbl").hide();
		 $(this).parents().find("#serviceTbl").find(".form-control").attr("required",false).val(''); 
		 
	       var edit_inc = $('#edit_addressCount').val();
	       if(edit_inc!= undefined){
	    	   inc = edit_inc;
	       }
	      for(var k=0;k<=inc;k++) {
	    	  removeData1(k);
	    	  removeData(k);
	    	  if(edit_inc!= undefined){
	    		  removeData2(k);
	    	  }
		  }
		  inc=0;
		  
		  $('#addressCount').val(0);
		  $('#edit_addressCount').val(-1);
		  
		  $('#totalBeforeDisAmt').val("");
		  $('#freight').val("");
		  $('#amtRounding').val("");
		  $('#taxAmt').val("");
		  $('#totalPayment').val("");
		  $('#totalDiscount').val("");
		  
		  addItem();
		 
		 
	  			 
	          }, function(){
	        	 
	        	$("#service_radio").prop('checked',true); 
	      alertify.error('Cancelled');
	   });
	
});

$("#service_radio").click(function() {
	//alert("service");
	 alertify.confirm("Inventory Goods Issue",'Are you Sure Want to Change Service ,Items will be removed! ', function(){
	$("#serviceTbl").show();
	 $("#itemTbl").hide();
	 $("#edit_item_serviceTbl").hide();
	 
	 $(this).parents().find("#itemTbl").find(".form-control").attr("required",false).val(''); 
      var edit_inc = $('#edit_addressCount').val();
      if(edit_inc!= undefined){
   	   inc = edit_inc;
      }
      for(var k=0;k<=inc;k++) {
    	  removeData(k);
    	  removeData1(k);
    	  if(edit_inc!= undefined){
    		  removeData2(k);
    	  }
	  }
      
	  inc=0;
	  $('#addressCount').val(0);
	  $('#edit_addressCount').val(-1);
	  
	  
	  $('#totalBeforeDisAmt').val("");
	  $('#freight').val("");
	  $('#amtRounding').val("");
	  $('#taxAmt').val("");
	  $('#totalPayment').val("");
	  $('#totalDiscount').val("");
	  
	  
	  addItem();
	  
	 }, function(){
     $("#items_radio").prop('checked',true); 
     alertify.error('Cancelled')
    });
	 
});

$('#containerContainingTabs a').on('click', function(e) {
	e.preventDefault();
	$(this).tab('show');
	var theThis = $(this);
	$('#containerContainingTabs a').removeClass('active');
	theThis.addClass('active');
	});
	
	
//$('form.commentForm').on('submit', function(event) {
  $(".mySubButton").on('click', function() {
	
	if($(".mySubButton").hasClass("disabled")){
		 alertify.error('Please fill mandatory fields');
		alertify.alert("Request For Quotation","Please fill mandatory fields");
		$("#form").submit();
		 return false;
	  } else {
		  if(pendingQuantityValidate()){
		  var subStatus = $(this).val();
			
		  if(subStatus == "RE"){
			  var flag = true;
		  }else{
			  var flag = false;
		  
			  var rQuantityList = [];
			  $(".requiredQuantity").each(function() {
				  var itemParentRow = $(this).parents(".multTot");
				  var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
				  rQuantityList.push(requiredQuantity);
				});
		  
			  for ( var i = 0; i < rQuantityList.length; i++ ) {
			  	var itemq = rQuantityList[i];
			  	if(itemq > 0){
			  		flag = true;
			  		break;
			  	}else{
			  		flag = false;
			  	}
			  }
		  }
		  
		
		  if(flag == true){
	        	if(subStatus == 'DR'){
	        		alertify.message('Draft Successfully');
					return true;
				  } else if(subStatus == "SA"){
					 alertify.success('Saved Successfully');
					return true;
				  } else if(subStatus == "APP"){
					 alertify.success('Approved Return');
					return true;
				  } else if(subStatus == "RE"){
					 alertify.warning('Document Rejected');
					 return true;
				  }  
			  
		   } else{
			  alertify.alert("Inventory Goods Issue","Quantity Required For Atleast One Product");
				return false;
		  	} 
		  }else {
			  alertify.alert("Inventory Goods Issue","Can't Exceed more than the required quantity!");
			  return false;
		  }
		 }
	
	
    if ($('#items_radio').is(":checked") == true) {
    var rowCount = $('#itemTbl tr').length-1;
    
    if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
    	rowCount = $('#edit_item_serviceTbl tr').length-1;
		}
    
	if(rowCount == 0){
		alertify.alert("Inventory Goods Issue","Please Select Atleast One Item");
		 return false;
	}else{
		return true;
	}
    } 
    
    if ($('#service_radio').is(":checked") == true) {
	 var rowCount1 = $('#serviceTbl tr').length-1;

	 if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
		 rowCount1 = $('#edit_item_serviceTbl tr').length-1;
			} 
	 
 	if(rowCount1 == 0){
 		alertify.alert("Inventory Goods Issue","Please Select Atleast One  Service");
 		 return false;
 	}else{
 		return true;
 	}
	  }
    
});

function isNumericKey(evt)
{
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ( charCode > 31 
	&& (charCode < 48 || charCode > 57))
	return false;
	return true;
} 

function isNumericKey1(evt)
{
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ( charCode > 31 
			&& (charCode < 45 || charCode > 57))
	return false;
	return true;
}

function goBack() {
    window.history.back();
}
	
	
	
	
$(document).on("change", ".warehouse", function() {
	var itemParentRow = $(this).parents(".multTot");
	var warehouse=  $(itemParentRow).find(".warehouse option:selected").val();
	var productNo=  $(itemParentRow).find(".productNumber").val();
	setInfoInStockQuantity(productNo,warehouse,itemParentRow);
});

$(document).on("blur", ".productNumber", function() {	
	var itemParentRow = $(this).parents(".multTot");
	var warehouse=  $(itemParentRow).find(".warehouse option:selected").val();
	var productNo=  $(itemParentRow).find(".productNumber").val();
	setInfoInStockQuantity(productNo,warehouse,itemParentRow);
});

$(document).on("blur", ".description", function() {	
	var itemParentRow = $(this).parents(".multTot");
	var warehouse=  $(itemParentRow).find(".warehouse option:selected").val();
	var productNo=  $(itemParentRow).find(".productNumber").val();
	setInfoInStockQuantity(productNo,warehouse,itemParentRow);
});


function setInfoInStockQuantity(productNo,warehouse,itemParentRow){
	
	 $.ajax({
		   type: "GET",
			data: {productNo:productNo,warehouse :warehouse}, 
			async : false,
          url: "<c:url value="/invgi/getInStock"/>", 
          success: function (response) {
        	  $(itemParentRow).find(".quality-alert").attr('title', "InStockQunatity "+response);
          },
          error: function(e){
          // alert('Error: ' + e);
           }
          });
}
	
	

/* 
 $(document).on("change", ".taxCode", function() {

	var itemParentRow = $(this).parents(".multTot");
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	var tax=  $(itemParentRow).find(".taxCode option:selected").val();

	var taxDescription=  $(itemParentRow).find(".taxCode option:selected").text();
		$(itemParentRow).find(".taxDescription").val(taxDescription);
	////alert("unitPrice--->" +unitPrice);
//	alert("tax--->" +tax);
	var tax_amt = getDiscount(tax);
	var totalValue = getCalculateAmt(requiredQuantity,unitPrice,tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
	
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(totalValue.toFixed(2));
	
	 var sum_total = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		sum_total += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	 
 	if(!isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
  	 $("#amtRounding").val(sum_total.toFixed(2));
  	 $("#totalPayment").val(Math.round(sum_total));
  	 $("#totalDiscount").val("");
  	 $("#freight").val("");
 	}
  	 
	}); 
	
	 */
	

/* $(document).on("keyup", ".unitPrice", function() {
	 var itemParentRow = $(this).parents(".multTot");
	
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	alert("unitPrice--->" +unitPrice);
	var tax=  $(itemParentRow).find(".taxCode option:selected").text();
//	alert("tax--->" +tax);
	var tax_amt = Number(tax) / 100;
	var totalValue = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
	//alert("tax_amt" +tax_amt);
	//alert("totalValue" +totalValue);
		if( tax !="Select") {
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(totalValue.toFixed(2));
		}
	
	
	 var sum_total = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		sum_total += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	if( tax !="Select"  && !isNaN(sum_total)) {
	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
  	 $("#amtRounding").val(Math.round(sum_total));
  	 $("#totalPayment").val(Math.round(sum_total));
  	 $("#totalDiscount").val("");
  	 $("#freight").val("");
    	}
	}); */
	
/* 
$(document).on("keyup", ".requiredQuantity", function() {
	
	var itemParentRow = $(this).parents(".multTot");
	 
	var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
	var unitPrice=  $(itemParentRow).find(".unitPrice").val();
	//alert("requiredQuantity" +requiredQuantity);
	var tax=  $(itemParentRow).find(".taxCode option:selected").val();
	if(tax=='') {
        tax=  $(itemParentRow).find(".taxCode").val();
   }
	//alert("tax--->" +tax);
	var tax_amt = Number(tax) / 100;
	 
	var totalValue = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
	// alert("tax_amt" +tax_amt);
	// alert("totalValue" +totalValue);
		if( tax !="Select") {
	 $(itemParentRow).find(".taxTotal").val(parseFloat((requiredQuantity * unitPrice) * tax_amt).toFixed(2));
	 $(itemParentRow).find(".total").val(totalValue.toFixed(2));
		}
	
	
	 var sum_total = 0;
	 var sum_tax_total = 0;
  	 $(".total").each(function() {
  		sum_total += parseFloat($(this).val());
		    });
  	 
  	 $(".taxTotal").each(function() {
  		sum_tax_total += parseFloat($(this).val());
 		    });
  	 
  	 
  	//alert("tax" +tax);
	///alert("sum_total" +sum_total);
	
  	if( tax !="Select"  && !isNaN(sum_total)) {
  		var totalAmt = sum_total;
  		var discount_val = $("#totalDiscount").val();
  		
  		if(discount_val<100){
  		var discount = Number(discount_val) / 100;
  		var totalPayment = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
  	    
  		if( $("#freight").val()!="") {
  			totalPayment += parseFloat($("#freight").val());
  		}
  		if(totalAmt!="") {
  		 $("#totalPayment").val(Math.round(totalPayment));
  		 $("#amtRounding").val(totalPayment.toFixed(2));
  		}
  		
  		}
  		
  	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
  	
  	
    	}
	});

	
	
	$('#totalDiscount').keyup(function() {
	var totalAmt = $("#totalBeforeDisAmt").val();
	var discount_val = $("#totalDiscount").val();
	
	if(discount_val<100){
	var discount = Number(discount_val) / 100;
	var totalPayment = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
    
	if( $("#freight").val()!="") {
		totalPayment += parseFloat($("#freight").val());
	}
	if(totalAmt!="") {
	 $("#totalPayment").val(Math.round(totalPayment));
	 $("#amtRounding").val(totalPayment.toFixed(2));
	 $("#roundedOff").val(parseFloat(Math.round(totalPayment) - totalPayment).toFixed(2));
	}
	
	}else {
		 $("#totalDiscount").val("");
		alertify.alert("Inventory Goods Issue Discount","Please Enter Valid Discount!");
		 return false;
	}
	
	});
	
	
	function getDiscount(discount_val){
		var discount = Number(discount_val) / 100;
		return discount;
	}
	
	function getCalculateAmt(requiredQuantity,unitPrice,tax_amt){
		var amt = parseFloat(requiredQuantity * unitPrice) + parseFloat(requiredQuantity * unitPrice * tax_amt);
		return amt;
	}



$('#freight').keyup(function() {
	
	var totalAmt = $("#totalBeforeDisAmt").val();
	var discount_val = $("#totalDiscount").val();
	var freight = $(this).val();
	
//alert("discount_val" +discount_val);
//alert("freight" +freight);
//alert("totalAmt" +totalAmt);
	if( discount_val !="" && $("#totalDiscount").val()!="") {
		var discount = Number(discount_val) / 100;
		totalAmt = parseFloat(totalAmt) - parseFloat(totalAmt * discount);
	}
	
	if(totalAmt!="") {
	var finalValue =  Number(totalAmt) + Number(freight);
	 $("#totalPayment").val(Math.round(finalValue));
	 $("#amtRounding").val(finalValue.toFixed(2));
	 $("#roundedOff").val(parseFloat(Math.round(finalValue) - finalValue).toFixed(2));
	}
});
	
	function setCalculationAmt(index){
			 var sum_total = 0;
			 var sum_tax_total = 0;
		  	 $(".total").each(function(row) {
		  		///alert("ttt-->" +isNaN($(this).val()));
		  		 if(row!=index) {
		  		 sum_total += parseFloat((isNaN($(this).val())) ? 0 : $(this).val());
		  		             }
				    });
		  	 
		  	 $(".taxTotal").each(function(row) {
		  		if(row!=index) {
		  		 sum_tax_total += parseFloat((isNaN($(this).val())) ? 0 : $(this).val());
		  	                }
		 		    });
		  	 //alert(sum_total);
		  	// alert(sum_tax_total);
		  	 
		  	 $("#taxAmt").val(parseFloat(sum_tax_total).toFixed(2));
		  	 $("#totalBeforeDisAmt").val(parseFloat(sum_total).toFixed(2));
		  	 
		var discount_val = $("#totalDiscount").val();
		var discount = Number(discount_val) / 100;
		var totalPayment = parseFloat(sum_total) - parseFloat(sum_total * discount);
	    
		if( $("#freight").val()!="") {
			totalPayment += parseFloat($("#freight").val());
		}
		if(sum_total!="") {
		 $("#totalPayment").val(Math.round(totalPayment));
		 $("#amtRounding").val(totalPayment.toFixed(2));
		}
	
	}
	
	$(document).on("keypress", ".validatePrice", function(e) {	
		if (this.value.length == 0 && e.which == 48 ){
			      return false;
			   }
		}); */
	
	$(document).on("keyup", ".requiredQuantity", function(e) {	
		
		
		var itemParentRow = $(this).parents(".multTot");
		 
		var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
		var ajaxQuantity=  $(itemParentRow).find(".quality-alert").attr('title');
		
		ajaxQuantity =ajaxQuantity.substring(ajaxQuantity.lastIndexOf(" ") + 1, ajaxQuantity.length);
		
		if(Number(requiredQuantity)>Number(ajaxQuantity)){
			alertify.alert("Inventory Goods Issue","Avaliable "+ajaxQuantity + ". Cannot Exceed more than the required quantity!");	
			$(itemParentRow).find(".requiredQuantity").val("");
			return false;
		}
		
		});
		
		
			function pendingQuantityValidate(){
				var flag = false;
				$(".requiredQuantity").each(function() {
					  var itemParentRow = $(this).parents(".multTot");
					  var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
					  var temp_requiredQuantity= 0;
					  var ajaxQuantity=  $(itemParentRow).find(".quality-alert").attr('title');
						
					  temp_requiredQuantity =ajaxQuantity.substring(ajaxQuantity.lastIndexOf(" ") + 1, ajaxQuantity.length);
					  
					  if(Number(temp_requiredQuantity)<Number(requiredQuantity)){
							alertify.error("Avaliable "+temp_requiredQuantity + ". Can't Exceed more than the required quantity!");	
							 ($(this).parents('tr').find('td').find('.requiredQuantity').val(""));
							 ($(this).parents('tr').find('td').find('.requiredQuantity').focus());
							 flag = true;
						}
					});
				if(flag==true){
					return false;
				}else{
					return true;
				}
				
			}
	
	
	/*  $(".requiredQuantity").each(function() {
		 var itemParentRow = $(this).parents(".multTot");
		 var requiredQuantity=  $(itemParentRow).find(".requiredQuantity").val();
		 var temp_requiredQuantity=  $(itemParentRow).find(".temp_requiredQuantity").val();
		 if(temp_requiredQuantity<=0){
	        	 ($(this).parents('tr').find('td').find('.requiredQuantity').attr('readonly', true));
	        	 ($(this).parents('tr').find('td').find('.temp_requiredQuantity').attr('readonly', true));
			}
	 }); */
	
	
	
	</script>
	<script src=<c:url value="/resources/js/calculation.js"/> type="text/javascript"></script>

<%-- <c:import url="/WEB-INF/jsp/loadJs.jsp" />  --%>

<!-- alertifyjs -->

<link
	href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
	rel="stylesheet" type="text/css" />
<script
	src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/>
	type="text/javascript"></script>

<!-- <link
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css"
	rel="Stylesheet"></link>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> -->

<link href="<c:url value="/resources/css/themes/jquery-ui.css"/>" rel="stylesheet" type="text/css" />
<script src=<c:url value="/resources/js/jquery-ui.js"/> type="text/javascript"></script>
<script src=<c:url value="/resources/js/scripts/ui-blocker/jquery.blockUI.js"/> type="text/javascript"></script>

<c:import url="/WEB-INF/jsp/loadJs.jsp" /> 
            
<script>
$(document).ready(function(){
	$.noConflict();
});
</script>

</html>
