<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <!DOCTYPE>
    <html>

    <head>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
                    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
                    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                    <title>SMERP</title>
                  <c:import url="/WEB-INF/jsp/loadcss.jsp" />
                  
 <script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>  

    </head>

    <body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
       	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

                <div class="app-content content container-fluid" style="margin-top: 40px;">
                    <div class="content-wrapper">
                        <div class="content-body">
                            <!--/ project charts -->
                            <div class="row">
                                <div class="large-12 columns">

                                    <div class="content-body">
                                        <!-- Basic form layout section start -->

                                        <form:form method="POST" action="/vendor/save"  id="form" class="commentForm" modelAttribute="vendor" data-toggle="validator" role="form" >
                                            <section id="basic-form-layouts">
                                                <div class="row match-height">

                                                    <div class="col-md-12">
                                                        <div class="card">
                                                            <div class="card-header">
                                                             <c:if test = "${vendor.id!=null}">  
												   <h2 class="card-title" id="basic-layout-icons">Vendor/Update</h2>
     					 									<form:input type="hidden" cssClass="form-control"  path="id"  />
								 				 </c:if>
								 				 
								 				  <c:if test = "${vendor.id==null}">  
												   <h2 class="card-title" id="basic-layout-icons">Vendor/Create</h2>
								 				 </c:if>
                                                            </div>


                                                            <div class="card-body collapse in create-block">
                                                                <div class="card-block">
                                                                   
                                                                        <div class="form-body">
                                                                            <div class="row">
                                                                                <div class="col-sm-4 form-group">
                                                                                    <label>Vendor Code</label>
                                                                                    <form:input type="text" class="form-control" placeholder='vendorCode' path="vendorCode" value=""
                                                                                    required="true" oninvalid="this.setCustomValidity('Please Enter Vendor Code')"	oninput="setCustomValidity('')"/>
                                                                               <div style="color:red;" class="help-block with-errors"></div>
                                                                                </div>
                                                                               
                                                                               <div class="col-sm-4 form-group">
                                                                                    <label>Vendor Name</label>
                                                                                    <form:input type="text" class="form-control" placeholder='name' path="name" value=""  onchange="isValidName('name','/vendor/isValidVendorName','1_errorContainer','Vendor Name Already Exists')"
                                                                                     required="true" oninvalid="this.setCustomValidity('Please Enter Vendor Name')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  id="1_errorContainer"   class="help-block with-errors"></div>
                                                                                     
                                                                                </div>
                                                                               
                                                                                <div class="col-sm-4 form-group">
                                                                                    <label>Group</label>
                                                                                     <form:input type="text" class="form-control" placeholder='groupName' path="groupName" value="" 
                                                                                      required="true" oninvalid="this.setCustomValidity('Please Enter Group Name')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
                                                                                </div>
                                                                               
                                                                               
                                                                               
                                                                               
                                                                            </div>
                                                                             
                                                                            
                                                                        </div>
                                                                        <form class="form">
																			<div class="form-body">
                                                                        
                                                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
																<li class="nav-item"><a class="nav-link active" id="general-tab" data-toggle="tab" href="#general" role="tab" aria-controls="general" aria-selected="true">General</a>
																</li>
																<li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Address</a>
																</li>
																<li class="nav-item"><a class="nav-link" id="messages-tab" data-toggle="tab" href="#messages" role="tab" aria-controls="messages" aria-selected="false">Payment</a>
																</li>
															</ul>
																<div class="tab-content">
																	<div class="tab-pane active" id="general" role="tabpanel"
																		aria-labelledby="general-tab">

																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>Mobile</label>
																				<form:input path="mobileNo" placeholder='mobileNo' onkeypress="return isNumericKey(event)"
																					type="text" class="form-control numericwithoutdecimal" 
																					 required="true"  maxlength="10"  oninvalid="this.setCustomValidity('Please Enter Mobile Number')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>Fax</label>
																				<form:input path="fax" placeholder='fax' type="text"
																					class="form-control" 
																					 required="true" oninvalid="this.setCustomValidity('Please Enter Fax Number')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			
																			
																		</div>
																		 
																		<div class="row">
																			<div class="col-md-6 form-group">
																				<label>Email</label>
																				<form:input path="emailId" 
																					type="text" class="form-control"  
																				 pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder='Enter Email'
																				required="true"  oninvalid="this.setCustomValidity('Please Enter Valid Email')"	oninput="setCustomValidity('')" />
																	<div style="color:red;"  class="help-block with-errors" ></div>
																			</div>
																			
																			<div class="col-md-6 form-group">
																				<label>Business Type</label>
																				<form:select path="businessPartnerType"
																					class="form-control"  required="true"   oninvalid="this.setCustomValidity('Please Select Business Type')"	oninput="setCustomValidity('')" >
																					<form:option value="">--Select--</form:option>
																					<form:option value="Company">Company</form:option>
																					<form:option value="Private">Private</form:option>
																					<form:option value="Employee">Employee</form:option>
																				</form:select>
																				<div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																		</div>
																		 

																		<!--Single Contact  -->
																		
																		<c:if test="${vendor.id==null}">
											  
																		<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">
												 <div class="col-xs-12 col-sm-12">
												 <div class="form-group"><label>Add Contact Details</label></div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Contact ID</label><input type="text" name="vendorContactDetails[0].contactId" class="form-control" 
												 	required="true" oninvalid="this.setCustomValidity('Please Enter Contact Id')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
												 </div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Title</label>
												 <select name="vendorContactDetails[0].title" class="form-control" required="true"  oninvalid="this.setCustomValidity('Please Select Valid Title')"	oninput="setCustomValidity('')" >
												 <option value="" selected disabled >select</option>
												 <option value="Mr">Mr</option>
												 <option value="Ms">Ms</option>
												 </select>
												 <div style="color:red;"  class="help-block with-errors" ></div>
												 </div></div>
												
												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Contact Name</label><input type="text" class="form-control" name="vendorContactDetails[0].contactName" 
												  	required="true" oninvalid="this.setCustomValidity('Please Enter Contact Name')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
												  </div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Address</label><input type="text" class="form-control" name="vendorContactDetails[0].address" 
													required="true" oninvalid="this.setCustomValidity('Please Enter Address')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
												  </div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Mobile</label><input type="text" class="form-control" maxlength="10" name="vendorContactDetails[0].mobileNo"  onkeypress="return isNumericKey(event)"
												 	required="true" oninvalid="this.setCustomValidity('Please Enter Mobile')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
												 	</div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Fax</label><input type="text" class="form-control" name="vendorContactDetails[0].fax"
													required="true" oninvalid="this.setCustomValidity('Please Enter Fax')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
												 </div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Email</label><input type="text" class="form-control" name="vendorContactDetails[0].email 
												  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder='Enter Email'
																				required="true"  oninvalid="this.setCustomValidity('Please Enter Valid Email')"	oninput="setCustomValidity('')" />
																	<div style="color:red;"  class="help-block with-errors" ></div></div></div>

												<div class="col-xs-12 col-sm-4">
												 <div class="form-group"><label>Gender</label>
												 <select class="form-control" name="vendorContactDetails[0].gender" required="true"  oninvalid="this.setCustomValidity('Please Enter Gender')"	oninput="setCustomValidity('')" >
												 <option value="" selected disabled >select</option>
												 <option value="Male">Male</option>
												 <option value="Female">Female</option>
												 </select>
												 <div style="color:red;"  class="help-block with-errors" ></div>
												 </div></div>

												<!-- <div class="col-xs-12 col-sm-3"><div class="form-group"><label>&nbsp;&nbsp;&nbsp; </label><br><a class="remove btn btn-danger">Remove</a></div></div>
												 -->
												</div>
																		
																		 </c:if>
																		<!--// Single Contact  -->

																		<div class="optionBox">
																			
																				
																				
																				<!--Dynamic Load data  -->

																		<c:if
																			test="${not empty vendor.vendorContactDetails}">
																			<%
																				int count = 0;
																			%>
																			<c:forEach items="${vendor.vendorContactDetails}"
																				var="listContactDetails">
																				
																				<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">
												<div class="col-xs-12 col-sm-12">
												<div class="form-group"><label>Add Contact Details</label></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Contact ID</label><input type="text" value="${listContactDetails.contactId}" name="vendorContactDetails[<%= count %>].contactId" class="form-control"
												required="true" oninvalid="this.setCustomValidity('Please Enter Contact Id')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
												
												</div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Title</label>
												
												
												<select class="form-control"  name="vendorContactDetails[<%= count %>].title" required="true" oninvalid="this.setCustomValidity('Please Select Title')"	oninput="setCustomValidity('')">
												<option value="" selected disabled>Select</option>
											<c:forTokens items = "Mr,Ms" delims = "," var = "titleName">
											  <c:choose>  
											    <c:when test="${titleName == listContactDetails.title}"> 
											       <option value="${titleName}" selected ><c:out value = "${titleName}"/></option> 
											    </c:when>  
											    <c:otherwise>  
											       <option value="${titleName}"><c:out value = "${titleName}"/></option>
											    </c:otherwise>   
        									 </c:choose>  
     										</c:forTokens>
									           </select>
									           <div style="color:red;"  class="help-block with-errors"></div>
												</div></div>
												
												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Contact Name</label><input type="text" class="form-control" value="${listContactDetails.contactName}" name="vendorContactDetails[<%= count %>].contactName" 
													required="true" oninvalid="this.setCustomValidity('Please Contact Name')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Address</label><input type="text" class="form-control" value="${listContactDetails.address}" name="vendorContactDetails[<%= count %>].address"	
												required="true" oninvalid="this.setCustomValidity('Please Enter Address')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Mobile</label><input type="text" class="form-control" maxlength="10"  value="${listContactDetails.mobileNo}"  name="vendorContactDetails[<%= count %>].mobileNo" onkeypress="return isNumericKey(event)"	
												required="true" oninvalid="this.setCustomValidity('Please Mobile Number')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Fax</label><input type="text" class="form-control" value="${listContactDetails.fax}" name="vendorContactDetails[<%= count %>].fax"	
												required="true" oninvalid="this.setCustomValidity('Please Enter FAX')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Email</label><input type="text" class="form-control" value="${listContactDetails.email}"  name="vendorContactDetails[<%= count %>].email" 
														required="true" oninvalid="this.setCustomValidity('Please Enter Email Id')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div> 
														</div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Gender</label>
												
									<select class="form-control" value="${listContactDetails.gender}" name="vendorContactDetails[<%= count %>].gender" 
									required="true" oninvalid="this.setCustomValidity('Please Select Gender')"	oninput="setCustomValidity('')">
												<option value="" selected disabled>Select</option>
											<c:forTokens items = "Male,Female" delims = "," var = "genderName">
											  <c:choose>  
											    <c:when test="${genderName == listContactDetails.gender}"> 
											       <option value="${genderName}" selected ><c:out value = "${genderName}"/></option> 
											    </c:when>  
											    <c:otherwise>  
											       <option value="${genderName}"><c:out value = "${genderName}"/></option>
											    </c:otherwise>   
        									 </c:choose>  
     										</c:forTokens>
									</select>		
												<div style="color:red;"  class="help-block with-errors"></div>
												
												</div></div>

												<!-- <div class="col-xs-12 col-sm-3">
												<div class="form-group">
												<label>&nbsp;&nbsp;&nbsp; </label><br>
												<a class="remove btn btn-danger">Remove</a>
												</div>
												</div> -->
												                               </div>
																				
																				
																				<%
																					count = count + 1;
																				%>
																			</c:forEach>

																			<input type="hidden" id="addressCount"
																				value="<%=count%>">
																			<input type="hidden" id="addressIndexCount"
																				value="<%=count%>">

																		</c:if>
																		<input type="hidden" id="indexValue">
																		<!--//Dynamic Load data  -->
																		
																		   <div class="block">
																				<p Class="error">
																					<a class="add btn btn-success flat btn-responsive"><i
																						class="fa fa-plus btni_left"></i>Add Contact
																						Persons</a>
																				</p>
																			</div>
																		</div>

																		<!-- Add multiple  -->

																	</div>
																	<div class="tab-pane" id="profile" role="tabpanel"
																		aria-labelledby="profile-tab">
																		
																		
																		<!--Single Address  -->
																		<c:if test="${vendor.id==null}">
											  
																		<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">
																		 <div class="col-md-12"><div class="row"><div class="col-md-6"><div class="form-group">
																		 <label>Vendor Address Details</label></div></div><div class="col-md-3">  
												
												 						<label class="checkbox-inline"><input type="checkbox" checked="checked" name="vendorAddress[0].payTo"  value="payTo" >Pay To</label>
												 					     &nbsp; &nbsp; &nbsp; 
												 						<label class="checkbox-inline"><input type="checkbox" name="vendorAddress[0].shipFrom" value="shipFrom">Ship From</label>
																		</div></div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address ID</label>
																		<input type="text" name="vendorAddress[0].addressId"  class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Address Id')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address</label>
																		<input type="text" name="vendorAddress[0].addressName" class="form-control"  
																		required="true" oninvalid="this.setCustomValidity('Please Enter Address')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street/ PO Box</label>
																		<input type="text" name="vendorAddress[0].street" class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Street')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>City</label>
																		<input type="text" name="vendorAddress[0].city" class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter City')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Zip code</label>
																		<input type="text" name="vendorAddress[0].zipCode" class="form-control"  onkeypress="return isNumericKey(event)"
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Zip Code')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street No</label>
																		<input type="text" name="vendorAddress[0].streetNo" class="form-control"  
																		required="true" oninvalid="this.setCustomValidity('Please Enter Street Number')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST IN </label>
																		<input type="text" name="vendorAddress[0].gstin" class="form-control"  
																		required="true" oninvalid="this.setCustomValidity('Please Enter GST IN')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST type</label>
																		<select name="vendorAddress[0].gstinType" class="form-control"
																		required="true" oninvalid="this.setCustomValidity('Please Enter GST Type')"	oninput="setCustomValidity('')">
																		<option value="" selected disabled>select</option>
																		<option value="Regular">Regular</option>
																		<option value="Excisible">Excisible</option>
																		<option value="Regular">UIN Agency or Embasyy</option>
																		<option value="Excisible">Composition Levy</option>
																		<option value="Regular">Non-Resident Taxable person</option>
																		<option value="Excisible">Casual Taxable Person</option>
																		</select>
																		<div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Country</label>
																		<select name="vendorAddress[0].country" class="form-control">
																		<option value="${country.id}">${country.name}</option>
																		</select>
																		</div>
																		</div>
																		
																		</div>
																		 </c:if>
																		<!--// Single Address  -->
																		
																		<div class="optionBox1">
																			
																				
																				<!--1 multiply Dynamically Load   -->
																					<c:if
																			test="${not empty vendor.vendorAddress}">
																			<%
																				int count1 = 0;
																			%>
																			<c:forEach items="${vendor.vendorAddress}"
																				var="listAddressDetails">
																				<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">
																		 <div class="col-md-12"><div class="row"><div class="col-md-6"><div class="form-group">
																		 <label>Add Vendor Address Details</label></div></div><div class="col-md-3">  
												
												 						<label class="checkbox-inline">
												 						<input type="checkbox" name="vendorAddress[<%=count1 %>].payTo"  
												 						<c:if test="${listAddressDetails.payTo!=null}">
												 						checked
												 						 </c:if>
												 						value="payTo">Pay To</label>
												 					     &nbsp; &nbsp; &nbsp; 
												 						<label class="checkbox-inline"><input type="checkbox" name="vendorAddress[<%=count1 %>].shipFrom" 
												 						<c:if test="${listAddressDetails.shipFrom!=null}">
												 						checked
												 						 </c:if>
												 						value="shipFrom">Ship From</label>
																		</div></div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address ID</label>
																		<input type="text"  value="${listAddressDetails.addressId}" name="vendorAddress[<%=count1 %>].addressId" class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Address Id')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address Name</label>
																		<input type="text" value="${listAddressDetails.addressName}" name="vendorAddress[<%=count1 %>].addressName" class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Address Name')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street/ PO Box</label>
																		<input type="text" value="${listAddressDetails.street}" name="vendorAddress[<%=count1 %>].street" class="form-control"  
																		required="true" oninvalid="this.setCustomValidity('Please Enter Street')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>City</label>
																		<input type="text" value="${listAddressDetails.city}" name="vendorAddress[<%=count1 %>].city" class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter City')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Zip code</label>
																		<input type="text" value="${listAddressDetails.zipCode}" name="vendorAddress[<%=count1 %>].zipCode" class="form-control"  onkeypress="return isNumericKey(event)"
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Zip Code')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street Number</label>
																		<input type="text" value="${listAddressDetails.streetNo}" name="vendorAddress[<%=count1 %>].streetNo" class="form-control" 
																		 required="true" oninvalid="this.setCustomValidity('Please Enter Street Number')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST IN </label>
																		<input type="text" value="${listAddressDetails.gstin}" name="vendorAddress[<%=count1 %>].gstin" class="form-control"  
																		required="true" oninvalid="this.setCustomValidity('Please Enter GST IN')"	oninput="setCustomValidity('')"/><div style="color:red;"  class="help-block with-errors"></div>
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST type</label>
																		
																		<select class="form-control"  name="vendorAddress[<%=count1 %>].gstinType" 	required="true" oninvalid="this.setCustomValidity('Please Enter GST Type')"	oninput="setCustomValidity('')">
																		<option value="" selected disabled>Select</option>
																	<c:forTokens items = "Regular,Excisible,Regular,Excisible,UIN Agency or Embasyy,Composition Levy,Non-Resident Taxable person,Casual Taxable Person" delims = "," var = "gstType">
																	  <c:choose>  
																	     <c:when test="${gstType == listAddressDetails.gstinType}"> 
																	       <option value="${gstType}" selected ><c:out value = "${gstType}"/></option> 
																	    </c:when>  
																	    <c:otherwise>  
																	       <option value="${gstType}"><c:out value = "${gstType}"/></option>
																	    </c:otherwise>   
						        									 </c:choose>  
						     										</c:forTokens>
															              </select>	
																		<div style="color:red;"  class="help-block with-errors"></div>
																		
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Country</label>
																		<select name="vendorAddress[<%=count1 %>].country" class="form-control">
																		<option value="${country.id}">${country.name}</option>
																		</select>
																		</div>
																		</div>
																		
																		</div>
																				
																				
																				
																					<%
																					count1 = count1 + 1;
																				%>
																			</c:forEach>

																			<input type="hidden" id="addressCount1"
																				value="<%=count1%>">
																			<input type="hidden" id="addressIndexCount1"
																				value="<%=count1%>">

																		</c:if>
																		<input type="hidden" id="indexValue1">
																				
																				<div class="block1">
																				<!--1 // multiply Dynamically Load   -->
																				
																				<p Class="error">
																					<a class="add1 btn btn-success flat btn-responsive"><i
																						class="fa fa-plus btni_left"></i>Add Address</a>
																				</p>
																			   </div>
																		</div>
																		<!--Pay - Shipping  -->
																		
																		</div>
																	<div class="tab-pane" id="messages" role="tabpanel"
																		aria-labelledby="messages-tab">
																		<!--Payment  -->
																		
																		<div class="row">
																			
																			<div class="col-sm-4 form-group">
																				<label>Payment Terms</label>
																				<form:input path="paymentTerms" placeholder='paymentTerms'
																					type="text" class="form-control" 
																					required="true" oninvalid="this.setCustomValidity('Please Enter Payment Terms')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>Credit Limit</label>
																				<form:input path="creditLimit" placeholder='creditLimit' type="text"
																					class="form-control"  onkeypress="return isNumericKey(event)"
																					required="true" oninvalid="this.setCustomValidity('Please Enter Credit Limit')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>Commitment Limit</label>
																				<form:input path="commitmentLimit" placeholder='paymentTerms'
																					type="text" class="form-control"  onkeypress="return isNumericKey(event)"
																					required="true" oninvalid="this.setCustomValidity('Please Enter Commitment Limit')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																		</div>
																		
																		
																		
																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>Bank Country</label>
																				 <form:select path="bankCountry" id="selectSubcat"
																					cssClass="form-control">
																						<form:option value="${country.id}">${country.name}</form:option>
																				</form:select> 
																				
																		      </div>
																		      
																		      <div class="col-sm-6 form-group">
																				<label>Bank Name</label>
																				<form:input path="bankName" placeholder='bankAccountName' type="text"
																					class="form-control" 
																					required="true" oninvalid="this.setCustomValidity('Please Enter Bank Name')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																		
																		
																		</div>
																		
																		
																		
																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>Bank Code</label>
																				<form:input path="bankCode" placeholder='bankCode' type="text"
																					class="form-control" 
																					 required="true" oninvalid="this.setCustomValidity('Please Enter Bank Code')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>Account Id</label>
																				<form:input path="accountId" placeholder='accountId' type="text"
																					class="form-control" onkeypress="return isNumericKey(event)"
																					 required="true" oninvalid="this.setCustomValidity('Please Enter Account Id')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																		
																		</div>
																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>Bank Account Name</label>
																				<form:input path="bankAccountName" placeholder='bankAccountName' type="text"
																					class="form-control"
																					 required="true" oninvalid="this.setCustomValidity('Please Enter Fax Bank Account Name')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>Branch Name</label>
																				<form:input path="branch" placeholder='branch' type="text"
																					class="form-control" 
																					 required="true" oninvalid="this.setCustomValidity('Please Enter Branch Name')"	oninput="setCustomValidity('')"/>
                                                                                    <div style="color:red;"  class="help-block with-errors"></div>
																			</div>
																			
																		</div>
																		
																		
																		
																		<!--Payment  -->
																		</div>
																</div>



															</div>
                    									</form>
                    									<br>
                   										<div class="text-xs-center">
																	
																	<a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a>
																<a href="<c:url value ="/vendor/list"/>">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a>

 																 <c:if test = "${vendor.id==null}">  
																<button type="submit" class="btn btn-primary">
																	<i class="icon-check2"></i> Save
																</button>
																  </c:if> 


															  <c:if test = "${vendor.id!=null}">  
                  													   <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Update </button>
                   										   </c:if> 

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

                 <footer class="footer footer-static footer-light navbar-border">
			<p class="clearfix text-muted text-sm-center mb-0 px-2">
				<span class="float-md-right d-xs-block d-md-inline-block">Copyright
					&copy; 2018 <a href="#" target="_blank"
					class="text-bold-800 grey darken-2">SMERP </a>, All rights
					reserved.
				</span>
			</p>
		</footer>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              	<%--  <c:import url="/WEB-INF/jsp/loadJs.jsp" />  --%>
    </body>
<script type="text/javascript">
	$('.add')
			.click(
					function() {
						var a = $('#addressIndexCount').val();
						var addressCount = $('#addressCount').val();
						var index = $('#indexValue').val();
						var inc=1;
						//alert("a"+a);
						//alert("addressCount"+addressCount);
						//alert("index"+index);
						if (a != undefined ) {
							if (parseInt(a) != parseInt(addressCount)) {
								inc = parseInt(a) ;
								$('#addressIndexCount').val(inc+1);
							} else {
								inc = parseInt(a);
								$('#addressIndexCount').val(parseInt(inc) + 1);
							}
						} else {
							//alert("index-->"+inc);
							if (index == "" || index == null || index == NaN) {
								inc = 1;
								$('#indexValue').val("1");
							} else {
								inc = parseInt(index) + 1;
								$('#indexValue').val(inc);
							}
						}

						//alert("imc-->"+inc);
						$('.block:last')
								.before('<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">'
												+ '<div class="col-xs-12 col-sm-12">'
												+ '<div class="form-group"><label>Add Contact Details</label></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Contact ID</label><input type="text" name="vendorContactDetails['+inc+'].contactId" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Contact Id\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Title</label><select name="vendorContactDetails['+inc+'].title" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Select Title\')" oninput="setCustomValidity(\'\')" >'
												+ '<option value="" selected disabled >select</option><option value="Mr">Mr</option><option value="Ms">Ms</option></select> <div style="color:red;"  class="help-block with-errors" ></div></div></div>'
												
												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Contact Name</label><input type="text"  name="vendorContactDetails['+inc+'].contactName" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Contact Name\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Address</label><input type="text"  name="vendorContactDetails['+inc+'].address" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Address\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Mobile</label><input type="text" maxlength="10"  name="vendorContactDetails['+inc+'].mobileNo" onkeypress="return isNumericKey(event)" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Mobile Number\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Fax</label><input type="text"  name="vendorContactDetails['+inc+'].fax" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter  Fax Number\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Email</label><input type="text"  name="vendorContactDetails['+inc+'].email" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Email \')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Gender</label><select class="form-control" name="vendorContactDetails['+inc+'].gender"   required="true"  oninvalid="this.setCustomValidity(\'Please Select Gender\')" oninput="setCustomValidity(\'\')"    >'
												+ '<option value="" selected disabled >select</option>'
												+ '<option value="Male">Male</option>'
												+ '<option value="Female">Female</option>'
												+ '</select><div style="color:red;"  class="help-block with-errors" ></div></div></div>'

												+'<div class="col-xs-12 col-sm-3"><div class="form-group"><label>&nbsp;&nbsp;&nbsp; </label><br><a class="remove btn btn-danger">Remove</a></div></div></div>');
					
						$("#form").validator("update");
					});

	$('.optionBox').on('click', '.remove', function() {
		$(this).parents(".address-details-block").remove();
		$("#form").validator("update");
	});
	
</script>



<script type="text/javascript">
	$('.add1')
			.click(
					function() {
						var a = $('#addressIndexCount1').val();
						var addressCount = $('#addressCount1').val();
						var index = $('#indexValue1').val();
						var inc=1;
						if (a != undefined ) {
							if (parseInt(a) != parseInt(addressCount)) {
								inc = parseInt(a) ;
								$('#addressIndexCount1').val(inc+1);
							} else {
								inc = parseInt(a);
								$('#addressIndexCount1').val(parseInt(inc) + 1);
							}
						} else {
							//alert("index-->"+inc);
							if (index == "" || index == null || index == NaN) {
								inc = 1;
								$('#indexValue1').val("1");
							} else {
								inc = parseInt(index) + 1;
								$('#indexValue1').val(inc);
							}
						}

						$('.block1:last')
								.before('<div class="row address-details-block1" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">'
												+ '<div class="col-md-12"><div class="row"><div class="col-md-6"><div class="form-group">'
												+ '<label>Add vendorAddress Details</label></div></div><div class="col-md-3">  '
												
												+ '<label class="checkbox-inline"><input type="checkbox"  name="vendorAddress['+inc+'].payTo"  value="payTo">Pay To</label>'
												+ '&nbsp; &nbsp; &nbsp; <label class="checkbox-inline"><input type="checkbox" checked="checked" name="vendorAddress['+inc+'].shipFrom" value="shipFrom">Ship From</label>'
												
												+ '</div><div class="col-md-3"> <a class="remove1 btn btn-danger">Remove</a></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group">'
												+ '<label>Address ID</label><input type="text" name="vendorAddress['+inc+'].addressId" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Address Id\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Address Name</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].addressName" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Address\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'
												
												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Street/ PO Box</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].street" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Street\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>City</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].city" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter City\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Zip code</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].zipCode" class="form-control" onkeypress="return isNumericKey(event)"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Zip Code\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Street No</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].streetNo" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter Street Number\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>GST IN </label>'
												+ '<input type="text" name="vendorAddress['+inc+'].gstin" class="form-control"  required="true"  oninvalid="this.setCustomValidity(\'Please Enter GST IN\')" oninput="setCustomValidity(\'\')" > <div style="color:red;"  class="help-block with-errors" ></div> </div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>GST type</label>'
												+ '<select name="vendorAddress['+inc+'].gstinType" class="form-control" required="true"  oninvalid="this.setCustomValidity(\'Please Select GST type\')" oninput="setCustomValidity(\'\')"   >'
												+ '<option selected disabled value="">select</option><option value="Regular">Regular</option>'
												+ '<option value="Excisible">Excisible</option>'
												+ '<option value="Regular">UIN Agency or Embasyy</option>'
												+ '<option value="Excisible">Composition Levy</option>'
												+ '<option value="Regular">Non-Resident Taxable person</option>'
												+ '<option value="Excisible">Casual Taxable Person</option></select><div style="color:red;"  class="help-block with-errors" ></div></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Country</label>'
												+ '<select name="vendorAddress['+inc+'].country" class="form-control">'
												+ '<option value="${country.id}">${country.name}</option></select></div></div></div>'

												+'</div></div>');
						$("#form").validator("update");
					});

	$('.optionBox1').on('click', '.remove1', function() {
		$(this).parents(".address-details-block1").remove();
		$("#form").validator("update");
	});
	
	
	
 	/*  $('form.commentForm').on('submit', function(event) {
 		
		return false;
	});  */
 	
	
	
	  function isValidName(nameId,url,displayId,msg){
		  var dataString  ="name="+$('#'+nameId).val();
		  $.ajax({
				 type:"GET",
				 url: url,
				 data : dataString,
				 success: function(result){
					 if(result==true){
						 alertify.success(msg);
						 $('#'+nameId).val('');
						 $('#'+displayId).html(msg);
					 }else {
						 $('#'+displayId).html(''); 
					 }
			    }});
		  
	  }
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
	 
	
	
	
	
	
</script>



<!-- alertifyjs -->
<link href="<c:url value="/resources/components/alertifyjs/css/alertify.css"/>"
    rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/components/alertifyjs/css/themes/default.css"/>"
    rel="stylesheet" type="text/css" />
<script src=<c:url value="/resources/components/alertifyjs/alertify.min.js"/> type="text/javascript"></script>  
<!-- alertfy-->




</html>