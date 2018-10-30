
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
<c:import url="../loadcss.jsp" />
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="../header.jsp" />

     <c:import url="../sidebar.jsp" />
	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>Company</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
				<div class="large-12 columns">
							
				<div class="content-body"><!-- Basic form layout section start -->
		
      <form:form method="POST" action="/company/save" modelAttribute="company"  >
      <section id="basic-form-layouts">
        <div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h4 class="card-title" id="basic-layout-icons">Company/Create</h4>
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>

												

												<div class="card-body collapse in">
													<div class="card-block">
													
															<div class="form-body">

 													<input type="hidden" id="id" class="form-control"  name="id" value="${company.id}">
 
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Company Name</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Company Name' path="name"  value="${company.name}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Company Tagline</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Company TagLine' path="companyTagLine"
																			value="${company.companyTagLine}" />
																	</div>
																</div>


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>VAT</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='VAT' path="gstinVat" value="${company.gstinVat}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>State Code</label>
																		<form:select path="states" id="selectSubcat" cssClass="form-control">
																	   
																	<c:forEach  items="${stateList}" var="stateList">
																	   
																	   <form:option value="${stateList.id}">${stateList.name}</form:option>
																	 </c:forEach>  
																	   
																	   </form:select>
																	</div>
																</div>



																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>LOGO</label>
																		<form:input type="file" cssClass="form-control"
																			placeholder='File' path="logo" value="${company.id}" />
																	</div>
																</div>


											<!-- <div class="col-md-12">
											<div class="card"> -->
												<div class="card-header">
													<h4 class="card-title" id="basic-layout-icons">Address</h4>
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>

												

												<div class="card-body collapse in">
													<div class="card-block">
														
															<div class="form-body">


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Address</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='street1' path="street1" value="${company.street1}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Phone</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Phone No' path="phoneNum"
																			value="${company.phoneNum}" />
																	</div>
																</div>


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Address</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Street2' path="street2" value="${company.street2}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Fax</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter fax Num' path="faxNum" value="${company.faxNum}" />
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>City</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter City' path="city" value="${company.city}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Email</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Email' path="emailId" value="${company.emailId}" />
																	</div>
																</div>
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>State</label>
																		<form:select path="states" id="selectSubcat" cssClass="form-control">
																	   
																	<c:forEach  items="${stateList}" var="stateList">
																	   
																	   <form:option value="${stateList.id}">${stateList.name}</form:option>
																	 </c:forEach>  
																	   
																	   </form:select>
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Website</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Website' path="webSite" value="${company.webSite}" />
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Tax ID</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Tax ID' path="taxId" value="${company.taxId}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Country</label>
																	   <form:select path="country" id="selectSubcat" cssClass="form-control">
																	   
																	<c:forEach  items="${countryList}" var="countryList">
																	   
																	   <form:option value="${countryList.id}">${countryList.name}</form:option>
																	 </c:forEach>  
																	   
																	   </form:select>
																			
																			
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>ZIP Code</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter ZIP Code' path="zipCode" value="${company.zipCode}" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Company Registry</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Company Registry' path="companyRegisrory" value="${company.companyRegisrory}" />
																	</div>
																</div>
															
															</div>
															
															
					
												<div  style="text-align: center;">
								                     <a  href="/company/list"> <button type="button" class="btn btn-warning mr-1"> <i class="icon-cross2"></i> Cancel </button></a>
									
									 <c:if test = "${company.name!=null}">  
                      <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Update </button>
                      </c:if>
                      
                       <c:if test = "${company.name==null}">  
                      <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Save </button>
                      </c:if>
									
						 </div>		


														
													</div>
												</div>

											<!-- </div>
										</div> -->







															</div>

														
													</div>
												</div>

										
										
										
										
										
									</div>	</div>
										
									</div>
										
									
                    <div>
                       <a href="#" onclick="goBack()"
					class="btn btn-primary" style=" float: left;"> Back</a> 
                   </div>
      </section>
     
     
     
     
        </form:form>
     
      <br>
       
      <br>
      
      <!-- // Basic form layout section end --> 
    </div>
    
				
					<%-- <div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-body">
								 <div>token:${token}</div>
								<input type="text"  id="tokenId"  value="${tokenId}"/> 
								<div>data:${data}</div>
							</div>
							<div class="card-footer">Footer</div>
						</div>
					</div> --%>
				</div>
				<!--/ project charts -->
				<br>
			</div>
		</div>
	</div>

		<footer class="footer footer-static footer-light navbar-border">
  <p class="clearfix text-muted text-sm-center mb-0 px-2"><span class="float-md-right d-xs-block d-md-inline-block">Copyright  &copy; 2018 <a href="#" target="_blank" class="text-bold-800 grey darken-2">SMERP </a>, All rights reserved. </span></p>
</footer>
	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<c:import url="../loadJs.jsp" />
	
	
</body>
<script>
$(document).ready(function(){
	/*  console.log("hiiiii");
	var x=$('#tokenId').val();
	console.log("token id"+x);
	document.cookie="tokenId=" + x;
	
	console.log("document cookie"+document.cookie);   */
});



</script>
</html>

