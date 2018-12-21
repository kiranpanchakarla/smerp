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
                   <%--  <c:import url="/WEB-INF/jsp/loadcss.jsp" /> --%>
    </head>

    <script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js" /> type="text/javascript"></script>
    <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js" /> type="text/javascript"></script>
    <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js" /> type="text/javascript"></script>
<script
	src=<c:url value="/resources/js/scripts/datepicker/bootstrap-datepicker.min.js"/> type="text/javascript"></script>
    <link href="<c:url value=" /resources/css/dataTables/buttons.dataTables.min.css "/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value=" /resources/css/dataTables/jquery.dataTables.min.css "/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value=" /resources/css/datapickercss/bootstrap-datepicker.min.css "/>" rel="stylesheet" type="text/css" />
    

    <body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
        <c:import url="/WEB-INF/jsp/header.jsp" />

        <c:import url="/WEB-INF/jsp/purchasesidebar.jsp" />

        <div class="app-content content container-fluid" style="margin-top: 40px;">
            <div class="content-wrapper">
                <div class="content-body">
                    <div class="row">
                        <div class="large-12 columns">
                            <div class="content-body">
                                <!-- Basic form layout section start -->
                                <c:url value="/purchaseReq/save" var="createUrl" />
                                <form:form method="POST" action="${createUrl}" id="form" class="commentForm" modelAttribute="purchaseRequest" data-toggle="validator" role="form">
                                    <section id="basic-form-layouts">
                                        <div class="row match-height">
                                            <div class="col-md-12">
                                                <div class="card">
                                                <div class="card-header">
												<c:if test="${purchaseRequest.id!=null}">
													<h2 class="card-title" id="basic-layout-icons">Update Purchase Request Details</h2>
													<form:input type="hidden" cssClass="form-control" path="id" />
												</c:if>
												<c:if test="${purchaseRequest.id==null}">
													<h2 class="card-title" id="basic-layout-icons">Create New Purchase Request</h2>
												</c:if> 
											</div>
                                                   	<form:hidden path="id" />
                                                    <div class="card-body collapse in create-block">
                                                        <div class="card-block">
                                                            <form class="form">
                                                                <div class="form-body">
                                                                    <div class="row">
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>User</label>
                                                                            <form:input type="text" class="form-control" placeholder='User Name'   path="user.username" readonly="true" value="${user.username}"  required="true"  oninvalid="this.setCustomValidity('Please Enter user Name.')" oninput="setCustomValidity('')" />
                                                                            <form:hidden path="user.userId" class="userId" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Doc No</label>
                                                                            <form:input type="text" class="form-control" placeholder='docNumber' path="docNumber" value="" autocomplete="off" readonly="true" required="true" oninvalid="this.setCustomValidity('Please Enter Doc No.')" oninput="setCustomValidity('')" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Requester Name</label>
                                                                            <form:input type="text" class="form-control username" placeholder='Requester Name' autocomplete="off"  path="referenceUser.firstname" 
                                                                          value="${purchaseRequest.referenceUser.firstname}${' '}${purchaseRequest.referenceUser.lastname}"
                                                                            
                                                                             required="true" oninvalid="this.setCustomValidity('Please Enter Requester Name.')" oninput="setCustomValidity('')" />
                                                                              <form:hidden path="referenceUser.userId" class="referenceUserId" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Posting Date</label>
                                                                            <form:input type="text"  autocomplete="off"  class="form-control" placeholder='postingDate' required="true" path="postingDate" value="" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Plant</label>
                                                                            <form:input type="text" class="form-control plant" placeholder='Plant' path="referenceUser.plant.plantName" 
                                                                          
                                                                            value="${purchaseRequest.referenceUser.plant.plantName}" 
                                                                            
                                                                            
                                                                              readonly="true" required="true" oninvalid="this.setCustomValidity('Please Enter Plant.')" oninput="setCustomValidity('')" />
                                                                            <form:hidden path="user.plant.id" class="plantId" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Doc Date</label>
                                                                            <form:input type="text" autocomplete="off"  class="form-control" placeholder='documentDate' required="true" path="documentDate" value="" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Email- ID</label>
                                                                            <form:input type="text" class="form-control emailId" placeholder='Email- ID' path="referenceUser.userEmail" 
                                                                            
                                                                            value="${purchaseRequest.referenceUser.userEmail}"
                                                                            
                                                                              readonly="true" required="true" oninvalid="this.setCustomValidity('Please Enter Email Id.')" oninput="setCustomValidity('')" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                        <div class="col-sm-6 form-group has-feedback">
                                                                            <label>Require Date</label>
                                                                            <form:input type="text" autocomplete="off"  class="form-control" placeholder='requiredDate' required="true" path="requiredDate" value="" />
                                                                            <div style="color:red;" class="help-block with-errors"></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-6 form-group">
                                                                            <div class="input-group">
                                                                                <div class="col-sm-3 form-group">
                                                                                    <form:radiobutton name="type" path="type" id="items_radio"  value="Item" />
                                                                                    <span class="radio-list">Item</span>

                                                                                </div>
                                                                                <div class="col-sm-3 form-group">
                                                                                    <form:radiobutton name="type" path="type" id="service_radio"  value="Service" />
                                                                                    <span class="radio-list">Service</span>
                                                                                </div>
                                                                                <!--  <label class="display-inline-block custom-control custom-radio ml-1" style="padding: 0px"> -->

                                                                                <div style="color:red;" class="help-block with-errors"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <!--  -->
                                                                    <input type="hidden" id="addressCount" value="0">

																					<div class="table-responsive">
																					<div class="full-col-table">
																						<table class="table table-bordered table-striped"
																							id="itemTbl">
																							<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th style="display: none;">Product Id</th>
																									<th>Product No.</th>
																										<th>Description</th>
																									<th>UOM</th>
																									<th>Quantity</th>
																									<th>Product Group</th>
																									<th>Warehouse</th>
																									<th>HSN</th>
																									<th>Action</th>
																								</tr>
																							</thead>
																							<tbody >
																										
                                                                                            </tbody>
                                                                                      </table>   
                                                                                         
                                                                                      <table class="table table-bordered table-striped"
																							id="serviceTbl">   
																							<thead>
																								<tr>
																									<th style="display: none;">Product Id</th>
																									<th>SAC Code</th>
																									<th>Description</th>
																									<th>Request Quantity</th>
																									<th>Warehouse</th>
																									<th>Action</th>
																								</tr>
																							</thead> 
                                                                                            <tbody >
																										
                                                                                            </tbody>
                                                                                        </table>    
                                                                                            
                                                                                            
                                                                                            
                                                                                            
                                                                                            
																										<!--1 multiply Dynamically Load   -->
																										<c:if test="${not empty purchaseRequestLists}">
																						<table class="table table-bordered table-striped"
																							id="edit_item_serviceTbl">   
																										
																										<thead>
																								<tr>
																									<!-- <th>S.No</th> -->
																									<th style="display: none;">Product Id</th>
																									<c:if test="${purchaseRequest.type=='Item'}">
																									<th>Product No.</th>
																									<th>Description</th>
																									<th>UOM</th>
																									<th>Quantity</th>
																									<th>Product Group</th>
																									<th>Warehouse</th>
																									<th>HSN</th>
																									</c:if>
																									
																									<c:if test="${purchaseRequest.type!='Item'}">
																									<th>SAC Code</th>
																									<th>Description</th>
																									<th>Request Quantity</th>
																									<th>Warehouse</th>
																									</c:if>
																									<th>Action</th>
																								</tr>
																							</thead>
																										
																										<tbody>
																											<c:set var="count" value="0" scope="page" />
																											<c:forEach items="${purchaseRequestLists}"
																												var="listpurchaseRequestLists">
																												
																												<tr class="multTot multTot${count}">
																												<td style="display: none;"><form:input
																															type="hidden"
																															path="purchaseRequestLists[${count}].productId"
																															value="${listpurchaseRequestLists.productId}"
																															class="form-control productId"></form:input>
																												<form:hidden path="purchaseRequestLists[${count}].id"/>	
																															</td>
																															
																													<c:if test="${purchaseRequest.type=='Item'}">
																													<td ><form:input type="text"
																															path="purchaseRequestLists[${count}].prodouctNumber"
																															value="${listpurchaseRequestLists.prodouctNumber}" 
																															class="form-control prodouctNumber"></form:input></td>
																													
																														<td><form:input type="text"
																															path="purchaseRequestLists[${count}].description"
																															value="${listpurchaseRequestLists.description}"
																															class="form-control " readonly="true"></form:input></td>
																													<td><form:input type="text"
																															path="purchaseRequestLists[${count}].uom"
																															value="${listpurchaseRequestLists.uom}"
																															class="form-control " readonly="true"></form:input></td>
																													
																													<td><form:input type="text"
																															path="purchaseRequestLists[${count}].requiredQuantity" onkeypress="return isNumericKey(event)"
																															autocomplete="off"  value="${listpurchaseRequestLists.requiredQuantity}"
																															class="form-control validatePrice"></form:input></td>
																															
																														<td><form:input type="text"
																															path="purchaseRequestLists[${count}].productGroup"
																															value="${listpurchaseRequestLists.productGroup}"
																															class="form-control productGroup"
																															readonly="true"></form:input></td>
																														
																														<td><form:select class="form-control"
																															style="width:160px !important;"
																															path="purchaseRequestLists[${count}].warehouse">
																															<form:option value="" label="Select" />
																															<form:options items="${planMap}" />
																														</form:select></td>
																															
																														<td><form:input type="text"
																															path="purchaseRequestLists[${count}].hsn"
																															value="${listpurchaseRequestLists.hsn}"
																															class="form-control hsnVal"
																															readonly="true"></form:input></td>
																															
																													</c:if>
																													
																													<c:if test="${purchaseRequest.type!='Item'}">
																													<td><form:input type="text"
																															path="purchaseRequestLists[${count}].sacCode"
																															value="${listpurchaseRequestLists.sacCode}"
																															class="form-control sacCode"
																															></form:input></td>
																													
																													<td><form:input type="text"
																															path="purchaseRequestLists[${count}].description"
																															value="${listpurchaseRequestLists.description}"
																															class="form-control description"
																															readonly="true"></form:input></td>
																															
																													<td><form:input type="text"
																															path="purchaseRequestLists[${count}].requiredQuantity" onkeypress="return isNumericKey(event)"
																															autocomplete="off"  value="${listpurchaseRequestLists.requiredQuantity}"
																															class="form-control validatePrice"></form:input></td>
																													
																													<td><form:select class="form-control"
																															style="width:160px !important;"
																															path="purchaseRequestLists[${count}].warehouse">
																															<form:option value="" label="Select" />
																															<form:options items="${planMap}" />
																														</form:select></td>
																															
																													</c:if>
																													
																															
																													
																						                           <td class="text-center"><a  onclick="removeData2(${count})" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a> </td>
																												
																												</tr>

																												<c:set var="count" value="${count + 1}"
																													scope="page" />
																											</c:forEach>
																							
																							
																									</tbody>
																									</table>
																									<input type="hidden" id="edit_addressCount" value="${count-1}">
																										</c:if>

																						
																					</div>
																				</div>
																				<div class="row">
																				<div class="col-md-9"></div>
																				<div class="col-md-3">
																				<div class="add-row-block">
																					<input type="button" class="btn btn-info"
																						onclick="addItem()" value="Add Row">
																						
																				</div>
																				</div>
																				</div>
																				<div class="row">
																			<div class="col-sm-4 form-group">
                                                                          <label>Remark</label> 
                                                                            <form:textarea type="text" cssClass="form-control"
																					 placeholder='Enter your Remark'
																					autocomplete="off" path="remarks"  />
                                                                           </div>
																				</div>
																				
                                                                    <!--  -->
                                                                    
                                                                    
                                                                    
                                                                    

                                                                </div>
                                                                <br>
                                                                <div class="text-xs-center">

                                                                    <a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a>
                                                                    <c:if test="${purchaseRequest.status eq 'Draft Stage' || purchaseRequest.id==null }">
                                                                   <form:button type="submit"  id="draft" name="statusType" value="DR" class="btn btn-primary"> <i class="icon-check2"></i> Draft</form:button> 
                                                                   </c:if>
                                                                    <c:if test="${purchaseRequest.id==null}">
                                                                    <form:button  type="submit"  id="save" name="statusType" value="SA" class="btn btn-primary"> <i class="icon-check2"></i>Save</form:button>
                                                                    </c:if>
                                                                    <c:if test="${purchaseRequest.id!=null}">
                                                                       <form:button  type="submit" id="update" name="statusType" value="SA" class="btn btn-primary "> <i class="icon-check2"></i> Update</form:button>
                                                                        
 																		  <a
																			href="<c:url value="/purchaseReq/cancelStage?id=${purchaseRequest.id}"/>">
																			<button type="button" class="btn btn-warning mr-1">
																				<i class="icon-cross2"></i> Cancel
																			</button>
																		</a>

                                                                     <c:forEach items="${sessionScope.umpmap}" var="ump">
																		 <c:if test="${ump.key eq 'Purchase Request'}">
																		 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
																		<c:if test="${fn:containsIgnoreCase(permissions,'Reject')}"> 
                                                                      <c:if test="${purchaseRequest.status != 'Cancelled'}">
                                                                      <form:button  type="submit" id="reject" name="statusType" value="RE" class="btn btn-reject "> <i class="icon-check2"></i>Reject</form:button>
                                                                     </c:if>
                                                                     </c:if></c:if></c:forEach>
                                                                      </c:if>
                                                                      
                                                                      <c:forEach items="${sessionScope.umpmap}" var="ump">
																		 <c:if test="${ump.key eq 'Purchase Request' }">
																		 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
																		<c:if test="${fn:containsIgnoreCase(permissions,'Approve')}"> 
                                                                        <c:if test="${purchaseRequest.status != 'Cancelled'}">
                                                                        <form:button  type="submit" id="approve" name="statusType" value="APP" class="btn btn-primary mr-1 "> <i class="icon-check2"></i>Approve</form:button>
                                                                        </c:if>
                                                                      
                                                                      </c:if></c:if></c:forEach>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </section>
                                </form:form>
                            </div>
                            <!--  <a class="btn btn-primary" href="/users/4">Back</a> -->
                            <br>
                            <br>
                            <!-- // Basic form layout section end -->
                        </div>
                    </div>
                    <!--/ project charts -->
                    <br>
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/jsp/footer.jsp" />

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <%--  <%@include file="../loadJs.jsp"%> --%>
            <%-- <c:import url="/WEB-INF/jsp/loadJs.jsp" /> --%>
    </body>

    <script type="text/javascript">

    
    var recipientsArray = []; 
    
    var inc=0;
    var edit_addressCount=0;


    if ($('#service_radio').is(":checked") == true) {
    	// alert("CHECKsERVICE");
    		 $("#serviceTbl").show();
    		 $("#itemTbl").hide();
    	 }else {
    		// alert("cHECKitem");
    		 document.getElementById("items_radio").checked = true;
    		 $("#serviceTbl").hide();
    	      $("#itemTbl").show();
    	 }
    	
    	
    	 
    		if ($('#edit_addressCount').val() != undefined ) {
    			 //alert("edit");
    			 $("#serviceTbl").hide();
    			 $("#itemTbl").hide();
    		}

    		if ($('#edit_addressCount').val() != undefined ) {
    		}else{
    			addItem();
    		}


    function addItem() {
    		
    		//alert("addItem");
    	
    						var addressCount = $('#addressCount').val();
    						$('#hiddenAddressCount').val(addressCount);
    						 edit_addressCount = $('#edit_addressCount').val();
    						if (edit_addressCount != undefined ) {
    							    inc = parseInt(edit_addressCount)+1;
    								$('#edit_addressCount').val(inc);
    						}else {
    								inc = parseInt(addressCount) ;
    						}
    						
    						
    						//alert(inc);
    						
    	
    	  if ($('#items_radio').is(":checked") == true) {
    		 // alert("itemAppend");
    	        var item_table_data = '<tr class="multTot multTot'+inc+'">'
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].prodouctNumber"  autocomplete="off"  value=""  required="true"   class="form-control prodouctNumber prodouctNumber'+inc+'" id="prodouctNumber'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			+'<td style="display:none;">'
    			+'<input type="hidden" name="purchaseRequestLists['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].description" readonly="true" value="" class="form-control description '+inc+'" id="description'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].uom" readonly="true" class="form-control uom uom'+inc+'" id="uom'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].requiredQuantity"  onkeypress="return isNumericKey(event)" autocomplete="off"   required="true"  class="form-control validatePrice requiredQuantity'+inc+'" id="requiredQuantity'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].productGroup" readonly="true" class="form-control  productGroup productGroup'+inc+'" id="productGroup'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			
    			+ '<td>'
    			+'<div class="form-group1">'
    			+ '<select  name="purchaseRequestLists['+inc+'].warehouse" style="width:160px !important;" required="true"  class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
    			+'<option value="">select</option>'+
    			<c:forEach items="${planMap}" var="planMap">
    			'<option value="${planMap.key}">${planMap.value}</option>'+
    			</c:forEach>
    			+ '</select>'
    			+ '</div>'
    			+ '</td>'
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].hsn" readonly="true" class="form-control hsnVal hsn'+inc+'" id="hsn'+inc+'"   />'
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
    		 
    					
    	  }else {
    		// alert("sERVICEAppend");
    		  var service_table_data='<tr class="multTot multTot'+inc+'">'
    			
    			+'<td style="display:none;">'
    			+'<input type="hidden" name="purchaseRequestLists['+inc+'].productId" class="form-control productId productId'+inc+'" id="productId'+inc+'"   />'
    			+'</td>'
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].sacCode"  autocomplete="off"  required="true"  class="form-control sacCode  sacCode'+inc+'" id="hsn'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].description" readonly="true" class="form-control description '+inc+'" id="uom'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			
    			+'<td>'
    			+'<div class="form-group1">'
    			+'<input type="text" name="purchaseRequestLists['+inc+'].requiredQuantity" onkeypress="return isNumericKey(event)" autocomplete="off"  required="true"  class="form-control validatePrice requiredQuantity'+inc+'" id="requiredQuantity'+inc+'"   />'
    			+ '</div>'
    			+'</td>'
    			
    			+ '<td>'
    			+'<div class="form-group1">'
    			+ '<select  name="purchaseRequestLists['+inc+'].warehouse" style="width:160px !important;" required="true"  class="form-control warehouse'+inc+' warehouse"  id="warehouse'+inc+'" >'
    			+'<option value="">select</option>'+
    			<c:forEach items="${planMap}" var="planMap">
    			'<option value="${planMap.key}">${planMap.value}</option>'+
    			</c:forEach>
    			+ '</div>'
    			+ '</select>'
    			+ '</td>'
    			
    			
    			
    			+ ' <td class="text-center"><a  onclick="removeData1('+inc+')" class="tdicon remove confirm-delete" data-toggle="modal"><i class="icon-bin left"></i></a>'
    			+ '</td>'
    			 
    			
    	+'</tr>';
    		 // alert(service_table_data);
    		  if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
    			  //alert("edit");
    			  $("#edit_item_serviceTbl").append(service_table_data);
    			}else{
    				 $("#serviceTbl").append(service_table_data);
    			}
    		  
    	  }
    		
    	
    		inc++;
    		$('#addressCount').val(inc);
    		$("#form").validator("update");
    		
    	}
   
    $(document).ready(function(){
    	
    
    	
    	 $('.userId').val(${user.userId}); 
    	 $('.referenceUserId').val(${user.userId}); 
    	 
    	
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
         
         $('#requiredDate').datepicker({
                dateFormat: 'dd/mm/yy' ,   //    dateFormat: 'MM dd, yy'
           }).datepicker( "option", { setDate:0,
                   maxDate:'+3y -1d',
                   minDate:'0' } );
    	
     

         
    	
            var date = new Date();
            var today = new Date(date.getDate(), date.getFullYear(), date.getMonth());
            var end = new Date(date.getFullYear(), date.getMonth(), date.getDate());

            var id = $('#id').val();

            if (id != '') {
                var userName = $('.username').val();
                //alert("vendorName"+vendorName);
                autocompleteuserDetails(userName);

            }else{
        		$('#postingDate').datepicker("setDate", "0"); //"0" for current date
        		$('#documentDate').datepicker("setDate", "0"); //"0" for current date
        		$('#requiredDate').datepicker("setDate", "10"); //"after 10" for current date
        	}

            $('#postingDate').datepicker({
  			  dateFormat: 'dd/mm/yy' 
  				  });
  			
  		  $('#documentDate').datepicker({
  			  dateFormat: 'dd/mm/yy' 
  		  });
  		  
  		  
  		  $('#requiredDate').datepicker({
  			  dateFormat: 'dd/mm/yy' 
  		  });
            
            var userNames = [];
            var userNamesList = ${usersList};
            var availableTagsusernames = [];

            $.each(userNamesList, function(index, value) {
                if (value != null) {
                    availableTagsusernames.push(value.toString());
                }
            });

            $(document).on("keypress", ".username", function() {
                $(this).autocomplete({
                    source: availableTagsusernames,
                    select: function(event, ui) {
                        var username = ui.item.value;
                        console.log("username" + username);
                      
                        //	alert(vendorname);
                        autocompleteuserDetails(username);
                    },
                });
            });

            function autocompleteuserDetails(username) {

                $.ajax({
                    type: "GET",
                    data: {username: username},
                    async: false, 
                    url: "<c:url value="/user/getUserInfo"/>",
                    success: function(response) {
                        console.log("user details" + response);
                        if(response!='null') {
                        var obj = JSON.parse(response);
                        $('.emailId').val(obj.userEmail);
                        $('.referenceUserId').val(obj.userId); 
                        $('.plant').val(obj.plant.plantName);
                        $('.plantId').val(obj.plant.id);
                        }
                    },
                    error: function(e) {
                    }
                });
            }

            var productList = [];
            var name  = {};
            var prodouctNumbers = ${productList};
            var availableTags = [];
            $.each(prodouctNumbers, function(index, value) {
                availableTags.push(value.toString());
            });

           
        
              $(document).on("keypress", ".prodouctNumber", function() {
          		var itemParentRow = $(this).parents(".multTot");
          		//alert("itemParentRow"+itemParentRow);
          		
          		$(this).autocomplete({
          	        source: availableTags,
          	        select: function(event, ui) {
          	        	name = ui.item.value;
          	        	//alert(name);
          	             autocompleteandchange(name,itemParentRow);
          	       		 },
          	        }); 
          		});
            
           
           
            $(document).on("blur", ".prodouctNumber", function() {
            	var itemParentRow = $(this).parents(".multTot");
            
            	
            	var arr=[];
            	 $(".prodouctNumber").each(function() {
                	 // alert($.inArray($(this).val(), arr));
                	  if(availableTags.includes($(this).val()) == true) 	 {
    		        if ($.inArray($(this).val(), arr) == -1){
    		            arr.push($(this).val());
    		       	// var isDluplicate = true;
    		       	//autocompleteandchange(($(this).val()),itemParentRow);
    		        }else{
    		        	 /* var isDluplicate = false; */
    		        	   alertify.alert("You have already entered the Product Number "+($(this).val()));
    		        	 $(this).val('')
    		        	 ($(this).parents('tr').find('td').find('input').val(''));
    		        	 ($(this).parents('tr').find('td').find('select').val(''));
    		        
    		        }
                }else {
                     	 alertify.alert($(this).val() +  " Product Number Does Not Exists!");  
                     	 ($(this).parents('tr').find('td').find('input').val(''));
                     	 ($(this).parents('tr').find('td').find('select').val('')); 
                     }  
    		      
    		    });
                        	
            	
            });

            //get the product information based on product  name
            function autocompleteandchange(name1, itemParentRow) {
                //alert(name);

                $.ajax({
                    type: "GET",
                    data: {name: name1},
                    async: false,
                    url: "<c:url value="/product/getProductInfo"/>", 
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
                        $(itemParentRow).find(".hsnVal").val(obj.hsnCode.hsnCode);

                       // $(itemParentRow).find(".prodouctNumber").val(obj.description);
                        $(itemParentRow).find(".productId").val(obj.id);
                        $(itemParentRow).find(".description").val(obj.description);
                        //$(itemParentRow).find(".prodouctNumber").val(obj.description);

                        // $(itemParentRow).find(".productGroup").val(obj.producttype.name);

                        var uom = obj.purchasingUom.uomName;
                        //alert("uom" + uom);
                        //$('.uom').val(uom);

                        $(itemParentRow).find(".uom").val(uom);

                        //  $(".uom").append($("<option></option>").attr("value",uom).text(uom)); 
                      //  var productgroup = obj.productCategory.categoryType;
                        var productgroup=obj.productGroup.productName;
                        //$('.productGroup').val(productgroup);

                        $(itemParentRow).find(".productGroup").val(productgroup);

                    },
                    error: function(e) {
                        //  alert('Error: ' + e);
                    }
                });
            }
         
            

            var sacData = ${sacList};
            var availableSacs = [];
            $.each(sacData, function(index, value) {
                availableSacs.push(value.toString());
            });

          //  alert("availableSacs" + availableSacs);
            $(document).on("keypress", ".sacCode", function() {
                var itemParentRow = $(this).parents(".multTot");

                $(this).autocomplete({
                    source: availableSacs,
                    select: function(event, ui) {
                        sacCode = ui.item.value;
                        //alert(name);
                        autocompleteandchangeSacCode(sacCode, itemParentRow);
                    },
                });
            });

            //get the product information based on product  name

       

              $(document).on("blur", ".sacCode", function() {
            	var itemParentRow = $(this).parents(".multTot");
            	
            	 //var isDluplicate = true;
            	//var name1 =  $("#"+itemParentRow.context.id).val(); 
            	//recipientsArray = enterdproducts.sort(); 
            	var arr=[];
            	 $(".sacCode").each(function() {
                	 // alert($.inArray($(this).val(), arr));
                	   if(availableSacs.includes($(this).val()) == true) 	 {
    		        if ($.inArray($(this).val(), arr) == -1){
    		            arr.push($(this).val());
    		       	// var isDluplicate = true;
    		       //	autocompleteandchangeSacCode(($(this).val()),itemParentRow);
    		        }else{
    		        	 
    		        	   alertify.alert("You have already entered the SAC Code "+$(this).val());
    		        	 $(this).val('')
    		        	 ($(this).parents('tr').find('td').find('input').val(''));
    		        	 ($(this).parents('tr').find('td').find('select').val(''));
    		        }
            	 }else {
               	  alertify.alert($(this).val() +" SAC Code Does Not Exists ");
  		        	 $(this).val('')
  		        	 ($(this).parents('tr').find('td').find('input').val(''));
  		        	 ($(this).parents('tr').find('td').find('select').val(''));  
                 }
     		        
    		        
    		    });
            	 
                        	
            	
            });

            
            
      
     
        
        function autocompleteandchangeSacCode(sacCode, itemParentRow) {
            //alert(name);

            $.ajax({
                type: "GET",
                data: {sacCode: sacCode},
                async: false,
                url: "<c:url value="/saccode/getSacInfo"/>",
                success: function(response) {
                    console.log(response);

                    var obj = JSON.parse(response);

                    //var myJSON = JSON.stringify(obj);

                    var description = obj.sacCode;
                    $(itemParentRow).find(".description").val(obj.description);

                },
                error: function(e) {
                    //  alert('Error: ' + e);
                }
            });
        }
    
       
    });
        
            function removeData(index){
            	//alert("ff"+index);
            	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
            		$('table#edit_item_serviceTbl tr.multTot'+index).remove();
            		
            	}else{
            	
            		$('table#itemTbl tr.multTot'+index).remove();
            		var id = "prodouctNumber"+index;
            		/* alert(id); */
            		recipientsArray.splice($.inArray(  $("#"+id).val(''), recipientsArray),1);
            		//recipientsArray.splice($.inArray($( "#"+id).val(''), recipientsArray),1);
            	}
            	$("#form").validator("update");
            }


            function removeData1(index){
            	//alert("ff"+index);
            	if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
            		$('table#edit_item_serviceTbl tr.multTot'+index).remove();
            	}else{
            		$('table#serviceTbl tr.multTot'+index).remove();
            	}
            	$("#form").validator("update");
            }


            function removeData2(index){
            	//alert("ff"+index);
            	$('table#edit_item_serviceTbl tr.multTot'+index).remove();
            	$("#form").validator("update");
            }


            </script>


            <script type="text/javascript">




            $("#items_radio").click(function() {
            	//alert("item");
            	 alertify.confirm('Are you Sure Want to Change  Item ,Service will be removed ', function(){
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
            		  addItem();
            		 
            		  enterdproducts = [];
            	  			 
            	          }, function(){
            	        	 
            	         $("#service_radio").prop('checked',true);  
            	        	  
            	      alertify.error('Cancelled');
            	   });
            	
            });

            $("#service_radio").click(function() {
            	//alert("service");
            	 alertify.confirm('Are you Sure Want to Change Service ,Items will be removed! ', function(){
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
            	  addItem();
            	  
            	 }, function(){
                    $("#items_radio").prop('checked',true);   
                    alertify.error('Cancelled');
                });
            	 
            });
        
        
        
        $('#containerContainingTabs a').on('click', function(e) {
            e.preventDefault();
            $(this).tab('show');
            var theThis = $(this);
            $('#containerContainingTabs a').removeClass('active');
            theThis.addClass('active');
        });
        
        $('form.commentForm').on('submit', function(event) {
        	
        	
         
             /*   var arr = [];
              $(".prodouctNumber").each(function() {
            	 // alert($.inArray($(this).val(), arr));
		        if ($.inArray($(this).val(), arr) == -1){
		            arr.push($(this).val());
		        }else{
		        	//alert($(this).val() + "duplicate");
		        	($(this).parents('tr').find('td').find('input').css('border', '1px solid red'));
		        	($(this).parents('tr').find('td').find('select').css('border', '1px solid red'));
		        }
		    });
              
              
              $(".sacCode").each(function() {
             	 // alert($.inArray($(this).val(), arr));
 		        if ($.inArray($(this).val(), arr) == -1){
 		            arr.push($(this).val());
 		        }else{
 		        	//alert($(this).val() + "duplicate");
 		        	($(this).parents('tr').find('td').find('input').css('border', '1px solid red'));
 		        	($(this).parents('tr').find('td').find('select').css('border', '1px solid red'));
 		        }
 		    }); */

              
             
              
            
            
            if ($('#items_radio').is(":checked") == true) {
            var rowCount = $('#itemTbl tr').length-1;
            
            if (edit_addressCount != undefined && $('#edit_item_serviceTbl').css('display') != 'none' ) {
            	rowCount = $('#edit_item_serviceTbl tr').length-1;
  			}
            
        	if(rowCount == 0){
        		alertify.alert("Please Select Atleast One Item");
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
         		alertify.alert("Please Select Atleast One Service");
         		 return false;
         	}else{
         		return true;
         	}
        	  }
            
            
            
            
        });
        
        

        function isNumericKey(evt)
        {
        	var charCode = (evt.which) ? evt.which : evt.keyCode;
        	if (charCode != 46 && charCode > 31 
        	&& (charCode < 48 || charCode > 57)) 
        	return false;
        	return true;
        }  

        function goBack() {
            window.history.back();
        }
        
        $(document).on("keypress", ".validatePrice", function(e) {	
    		if (this.value.length == 0 && e.which == 48 ){
    			      return false;
    			   }
    		});
        
    </script>

<%-- <c:import url="/WEB-INF/jsp/loadJs.jsp" />  --%>

<!-- alertifyjs -->

<link href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
    rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
    rel="stylesheet" type="text/css" />
<script src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/> type="text/javascript"></script>  

<link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet"></link>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js" ></script>
    </html>