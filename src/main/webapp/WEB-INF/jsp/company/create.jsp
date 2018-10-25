
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
<%@include file="../loadcss.jsp"%>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<%@include file="../header.jsp"%>

	<%@include file="../sidebar.jsp"%>

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
		
      <form:form method="POST" action="/company/create" modelAttribute="company" onsubmit="return register()" >
      <section id="basic-form-layouts">
        <div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h4 class="card-title" id="basic-layout-icons">Company/Create</h4>
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="">

												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Company Name</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Company Name' path="name" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Company Tagline</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Company TagLine' path="companyTagLine"
																			value="" />
																	</div>
																</div>


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>VAT</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='VAT' path="gstinVat" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>State Code</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter State Code' path="states" value="" />
																	</div>
																</div>



																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>LOGO</label>
																		<form:input type="file" cssClass="form-control"
																			placeholder='File' path="" value="" />
																	</div>
																</div>



															</div>

														</form>
													</div>
												</div>

											</div>
										</div>
										
										
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h4 class="card-title" id="basic-layout-icons">Address</h4>
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="">

												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Address</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='street1' path="street1" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Phone</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Phone No' path="phoneNum"
																			value="" />
																	</div>
																</div>


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Address</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Street2' path="street2" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Fax</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter fax Num' path="faxNum" value="" />
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>City</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter City' path="" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Email</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Email' path="emailId" value="" />
																	</div>
																</div>
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>State</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter State' path="" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Website</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Website' path="webSite" value="" />
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Tax ID</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Tax ID' path="gstinVat" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Country</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Country' path="" value="" />
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Currency</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Currency' path="" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Company Registry</label>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Enter Company Registry' path="states" value="" />
																	</div>
																</div>
															
															</div>
															
															
					<div class="form-actions center">
                     <a  href="/company/create"> <button type="button" class="btn btn-warning mr-1"> <i class="icon-cross2"></i> Cancel </button></a>
                      <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Save </button>
                    </div>



														</form>
													</div>
												</div>

											</div>
										</div>
										
										
										
									</div>
      </section>
     
        </form:form>
     <a class="btn btn-primary" href="/users/4">Back</a>
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

