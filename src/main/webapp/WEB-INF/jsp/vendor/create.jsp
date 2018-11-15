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
                  
<script src="/resources/components/bootstrap-validator/js/jquery.min.js" type="text/javascript"></script>
<script src="/resources/components/bootstrap-validator/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/resources/components/bootstrap-validator/js/validator.min.js" type="text/javascript"></script>

    </head>

    <body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
       	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

                <div class="app-content content container-fluid" style="margin-top: 40px;">
                    <div class="content-wrapper">
                        <div class="content-header row">
                            <div class="col-md-6">
                                <h4>Vendor</h4>
                            </div>
                        </div>
                        <div class="content-body">
                            <!--/ project charts -->
                            <div class="row">
                                <div class="large-12 columns">

                                    <div class="content-body">
                                        <!-- Basic form layout section start -->

                                        <form:form method="POST" action="/vendor/save" modelAttribute="vendor" data-toggle="validator" role="form" >
                                            <section id="basic-form-layouts">
                                                <div class="row match-height">

                                                    <div class="col-md-12">
                                                        <div class="card">
                                                            <div class="card-header">
                                                             <c:if test = "${vendor.id!=null}">  
												   <h4 class="card-title" id="basic-layout-icons">Vendor/Update</h4>
     					 									<form:input type="hidden" cssClass="form-control"  path="id"  />
								 				 </c:if>
								 				 
								 				  <c:if test = "${vendor.id==null}">  
												   <h4 class="card-title" id="basic-layout-icons">Vendor/Create</h4>
								 				 </c:if>
                                                            </div>

                                                            <input type="hidden" id="id" class="form-control" name="id" value="">

                                                            <div class="card-body collapse in">
                                                                <div class="card-block">
                                                                   
                                                                        <div class="form-body">
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>Vendor Code</label>
                                                                                    <form:input type="text" class="form-control" placeholder='vendorCode' path="vendorCode" value="" />
                                                                                </div>
                                                                               
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>Description</label>
                                                                                    <form:input type="text" class="form-control" placeholder='name' path="name" value="" />
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>Group</label>
                                                                                     <form:input type="text" class="form-control" placeholder='groupName' path="groupName" value="" />
                                                                                </div>
                                                                            </div>
                                                                            
                                                                        </div>
                                                                        <form class="form">
																			<div class="form-body">
                                                                        
                                                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
																<li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">General</a>
																</li>
																<li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Address</a>
																</li>
																<li class="nav-item"><a class="nav-link" id="messages-tab" data-toggle="tab" href="#messages" role="tab" aria-controls="messages" aria-selected="false">Payment</a>
																</li>
															</ul>
																<div class="tab-content">
																	<div class="tab-pane active" id="home" role="tabpanel"
																		aria-labelledby="home-tab">

																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>Mobile No</label>
																				<form:input path="mobileNo" placeholder='mobileNo'
																					type="text" class="form-control" />
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-sm-6 form-group">
																				<label>Fax</label>
																				<form:input path="fax" placeholder='fax' type="text"
																					class="form-control" />
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-md-6 form-group">
																				<label>Email</label>
																				<form:input path="emailId" placeholder='emailId'
																					type="text" class="form-control" />
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-md-6 form-group">
																				<label>Business Type</label>
																				<form:select path="businessPartnerType"
																					class="form-control">
																					<form:option value="">--Select--</form:option>
																					<form:option value="Company">Company</form:option>
																					<form:option value="Private">Private</form:option>
																					<form:option value="Employee">Employee</form:option>
																				</form:select>
																			</div>
																		</div>

																		

																		<div class="optionBox">
																			<div class="block">
																				
																				
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
												<div class="form-group"><label>Contact ID</label><input type="text" value="${listContactDetails.contactId}" name="vendorContactDetails[<%= count %>].contactId" class="form-control"/></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Title</label><select value="${listContactDetails.title}" name="vendorContactDetails[<%= count %>].title" class="form-control">
												<option value="">select</option><option value="Mr">Mr</option><option value="Ms">Ms</option></select></div></div>
												
												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Contact Name</label><input type="text" class="form-control" value="${listContactDetails.contactName}" name="vendorContactDetails[<%= count %>].contactName" /></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Address</label><input type="text" class="form-control" value="${listContactDetails.address}" name="vendorContactDetails[<%= count %>].address"/></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Mobile</label><input type="text" class="form-control"  value="${listContactDetails.mobileNo}"  name="vendorContactDetails[<%= count %>].mobileNo"/></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Fax</label><input type="text" class="form-control" value="${listContactDetails.fax}" name="vendorContactDetails[<%= count %>].fax"/></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Email</label><input type="text" class="form-control" value="${listContactDetails.email}"  name="vendorContactDetails[<%= count %>].email" /></div></div>

												<div class="col-xs-12 col-sm-4">
												<div class="form-group"><label>Gender</label><select class="form-control" value="${listContactDetails.gender}" name="vendorContactDetails[<%= count %>].gender">
												<option value="">select</option>
												<option value="Male">Male</option>
												<option value="Female">Female</option>
												</select></div></div>

												<div class="col-xs-12 col-sm-3">
												<div class="form-group">
												<label>&nbsp;&nbsp;&nbsp; </label><br>
												<a class="remove btn btn-danger">Remove</a>
												</div>
												</div>
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
																		
																		<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">
																		<div class="col-xs-12 col-sm-12">
																		<div class="form-group"><label>Add Address Details</label>
																		<label>&nbsp;&nbsp;&nbsp; </label>
																		<label class="checkbox-inline"><input type="checkbox" name="vendorAddress[0].payType"  value="payType">payType</label>
																		<label class="checkbox-inline"><input type="checkbox" name="vendorAddress[0].shipFrom" value="shipFrom">Ship From</label>
																		
																		</div></div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address ID</label>
																		<input type="text" name="vendorAddress[0].addressId"  class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address Name</label>
																		<input type="text" name="vendorAddress[0].addressName" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street/ PO Box</label>
																		<input type="text" name="vendorAddress[0].street" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>City</label>
																		<input type="text" name="vendorAddress[0].city" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Zip code</label>
																		<input type="text" name="vendorAddress[0].zipCode" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street No</label>
																		<input type="text" name="vendorAddress[0].streetNo" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST IN </label>
																		<input type="text" name="vendorAddress[0].gstin" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST type</label>
																		<select name="vendorAddress[0].gstinType" class="form-control">
																		<option value="">select</option>
																		<option value="Regular">Regular</option>
																		<option value="Excisible">Excisible</option>
																		<option value="Regular">UIN Agency or Embasyy</option>
																		<option value="Excisible">Composition Levy</option>
																		<option value="Regular">Non-Resident Taxable person</option>
																		<option value="Excisible">Casual Taxable Person</option>
																		</select>
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
																		
																		<!--// Single Address  -->
																		
																		<div class="optionBox1">
																			<div class="block1">
																				
																				<!--1 multiply Dynamically Load   -->
																					<c:if
																			test="${not empty vendor.vendorAddress}">
																			<%
																				int count1 = 0;
																			%>
																			<c:forEach items="${vendor.vendorAddress}"
																				var="listAddressDetails">
																				<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">
																		<div class="col-xs-12 col-sm-12">
																		<div class="form-group"><label>Add Address Details</label>
																		<label>&nbsp;&nbsp;&nbsp; </label>
																		<label class="checkbox-inline"><input type="checkbox" name="vendorAddress[<%=count1 %>].payType"  value="payType">payType</label>
																		<label class="checkbox-inline"><input type="checkbox" name="vendorAddress[<%=count1 %>].shipFrom" value="shipFrom">Ship From</label>
																		
																		</div></div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address ID</label>
																		<input type="text"  value="${listAddressDetails.addressId}" name="vendorAddress[<%=count1 %>].addressId" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Address Name</label>
																		<input type="text" value="${listAddressDetails.addressName}" name="vendorAddress[<%=count1 %>].addressName" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street/ PO Box</label>
																		<input type="text" value="${listAddressDetails.street}" name="vendorAddress[<%=count1 %>].street" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>City</label>
																		<input type="text" value="${listAddressDetails.city}" name="vendorAddress[<%=count1 %>].city" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Zip code</label>
																		<input type="text" value="${listAddressDetails.zipCode}" name="vendorAddress[<%=count1 %>].zipCode" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>Street No</label>
																		<input type="text" value="${listAddressDetails.streetNo}" name="vendorAddress[<%=count1 %>].streetNo" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST IN </label>
																		<input type="text" value="${listAddressDetails.gstin}" name="vendorAddress[<%=count1 %>].gstin" class="form-control">
																		</div>
																		</div>
																		
																		<div class="col-xs-12 col-sm-4">
																		<div class="form-group"><label>GST type</label>
																		<select name="vendorAddress[<%=count1 %>].gstinType" class="form-control">
																		<option value="">select</option>
																		<option value="Regular">Regular</option>
																		<option value="Excisible">Excisible</option>
																		<option value="Regular">UIN Agency or Embasyy</option>
																		<option value="Excisible">Composition Levy</option>
																		<option value="Regular">Non-Resident Taxable person</option>
																		<option value="Excisible">Casual Taxable Person</option>
																		</select>
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
																				<label>paymentTerms</label>
																				<form:input path="paymentTerms" placeholder='paymentTerms'
																					type="text" class="form-control" />
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>creditLimit</label>
																				<form:input path="creditLimit" placeholder='creditLimit' type="text"
																					class="form-control" />
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>Commitment Limit</label>
																				<form:input path="commitmentLimit" placeholder='paymentTerms'
																					type="text" class="form-control" />
																			</div>
																		</div>
																		
																		
																		
																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>BankCountry</label>
																				 <form:select path="bankCountry" id="selectSubcat"
																					cssClass="form-control">
																						<form:option value="${country.id}">${country.name}</form:option>
																				</form:select> 
																				
																		      </div>
																		      
																		      <div class="col-sm-6 form-group">
																				<label>BankName</label>
																				<form:input path="bankName" placeholder='bankAccountName' type="text"
																					class="form-control" />
																			</div>
																			
																		
																		
																		</div>
																		
																		
																		
																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>Bank Code</label>
																				<form:input path="bankCode" placeholder='bankCode' type="text"
																					class="form-control" />
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>accountId</label>
																				<form:input path="accountId" placeholder='accountId' type="text"
																					class="form-control" />
																			</div>
																		
																		</div>
																		<div class="row">
																			
																			<div class="col-sm-6 form-group">
																				<label>Bank Account Name</label>
																				<form:input path="bankAccountName" placeholder='bankAccountName' type="text"
																					class="form-control" />
																			</div>
																			
																			<div class="col-sm-6 form-group">
																				<label>branch</label>
																				<form:input path="branch" placeholder='branch' type="text"
																					class="form-control" />
																			</div>
																			
																		</div>
																		
																		
																		
																		<!--Payment  -->
																		</div>
																</div>



															</div>
                    									</form>
                   										 <div class="form-actions right">
																<a href="<c:url value ="/inventory/productList"/>">
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
                                     <a href="#" onclick="goBack()" class="btn btn-primary"> Back</a> 
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
                    <div class="row">

                        <div class="col-md-12">
                            <div class="footer_logo">
                                <img src="/resources/images/footer_logo.png">
                            </div>
                        </div>
                    </div>
                </footer>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <%@include file="../loadJs.jsp"%>
    </body>
<script type="text/javascript">
	$('.add')
			.click(
					function() {
						var a = $('#addressIndexCount').val();
						var addressCount = $('#addressCount').val();
						var index = $('#indexValue').val();
						var inc;
						//alert("a"+a);
						//alert("addressCount"+addressCount);
						//alert("index"+index);
						if (a !== undefined) {
							if (parseInt(a) != parseInt(addressCount)) {
								inc = parseInt(a) + 1;
								$('#addressIndexCount').val(inc);

							} else {
								inc = parseInt(a);
								$('#addressIndexCount').val(parseInt(inc) + 1);
							}

						} else {
							if (index == "" || index == null || index == NaN) {
								inc = 0;
								$('#indexValue').val("0");
							} else {
								inc = parseInt(index) + 1;
								$('#indexValue').val(inc);
							}
						}

						$('.block:last')
								.before('<div class="row address-details-block" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">'
												+ '<div class="col-xs-12 col-sm-12">'
												+ '<div class="form-group"><label>Add Contact Details</label></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Contact ID</label><input type="text" name="vendorContactDetails['+inc+'].contactId" class="form-control"/></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Title</label><select name="vendorContactDetails['+inc+'].title" class="form-control">'
												+ '<option value="">select</option><option value="Mr">Mr</option><option value="Ms">Ms</option></select></div></div>'
												
												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Contact Name</label><input type="text" class="form-control" name="vendorContactDetails['+inc+'].contactName" /></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Address</label><input type="text" class="form-control" name="vendorContactDetails['+inc+'].address"/></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Mobile</label><input type="text" class="form-control" name="vendorContactDetails['+inc+'].mobileNo"/></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Fax</label><input type="text" class="form-control" name="vendorContactDetails['+inc+'].fax"/></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Email</label><input type="text" class="form-control" name="vendorContactDetails['+inc+'].email" /></div></div>'

												+'<div class="col-xs-12 col-sm-4">'
												+ '<div class="form-group"><label>Gender</label><select class="form-control" name="vendorContactDetails['+inc+'].gender">'
												+ '<option value="">select</option>'
												+ '<option value="Male">Male</option>'
												+ '<option value="Female">Female</option>'
												+ '</select></div></div>'

												+'<div class="col-xs-12 col-sm-3"><div class="form-group"><label>&nbsp;&nbsp;&nbsp; </label><br><a class="remove btn btn-danger">Remove</a></div></div></div>');
					});

	$('.optionBox').on('click', '.remove', function() {
		$(this).parents(".address-details-block").remove();
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
						if (a !== undefined) {
							if (parseInt(a) != parseInt(addressCount)) {
								inc = parseInt(a) + 1;
								$('#addressIndexCount1').val(inc);

							} else {
								inc = parseInt(a);
								$('#addressIndexCount1').val(parseInt(inc) + 1);
							}

						} else {
							if (index == "" || index == null || index == NaN) {
								$('#indexValue1').val("1");
							} else {
								inc = parseInt(index) + 1;
								$('#indexValue1').val(inc);
							}
						}

						$('.block1:last')
								.before('<div class="row address-details-block1" style="background-color:#dcdada;font:white;padding:10px;margin-bottom:10px;">'
												+ '<div class="col-xs-12 col-sm-9"><div class="form-group">'
												+ '<label>Add vendorAddress Details</label></div></div>'
												
												
												+ '<div class="col-xs-12 col-sm-3"><div class="form-group">'
												
												+ '<label>&nbsp;&nbsp;&nbsp; </label>'
												+ '<label class="checkbox-inline"><input type="checkbox" name="vendorAddress['+inc+'].payType"  value="payType">Pay Type</label>'
												+ '<label class="checkbox-inline"><input type="checkbox" name="vendorAddress['+inc+'].shipFrom" value="shipFrom">Ship From</label>'
												
												+ '<label><label>&nbsp;&nbsp;&nbsp; </label><br><a class="remove1 btn btn-danger">Remove</a></label></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group">'
												+ '<label>Address ID</label><input type="text" name="vendorAddress['+inc+'].addressId" class="form-control"></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Address Name</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].addressName" class="form-control"></div></div>'
												
												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Street/ PO Box</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].street" class="form-control"></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>City</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].city" class="form-control"></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Zip code</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].zipCode" class="form-control"></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Street No</label>'
												+ '<input type="text" name="vendorAddress['+inc+'].streetNo" class="form-control"></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>GST IN </label>'
												+ '<input type="text" name="vendorAddress['+inc+'].gstin" class="form-control"></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>GST type</label>'
												+ '<select name="vendorAddress['+inc+'].gstinType" class="form-control">'
												+ '<option value="">select</option><option value="Regular">Regular</option>'
												+ '<option value="Excisible">Excisible</option>'
												+ '<option value="Regular">UIN Agency or Embasyy</option>'
												+ '<option value="Excisible">Composition Levy</option>'
												+ '<option value="Regular">Non-Resident Taxable person</option>'
												+ '<option value="Excisible">Casual Taxable Person</option></select></div></div>'

												+'<div class="col-xs-12 col-sm-4"><div class="form-group"><label>Country</label>'
												+ '<select name="vendorAddress['+inc+'].addressId" class="form-control">'
												+ '<option value="India">India</option></select></div></div></div>'
												
												+'</div></div>');
					});

	$('.optionBox1').on('click', '.remove1', function() {
		$(this).parents(".address-details-block1").remove();
	});
</script>







</html>