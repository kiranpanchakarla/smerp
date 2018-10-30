
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
<%@include file="../../loadcss.jsp"%>
</head>

<%-- 
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
</head> --%>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css"> -->
<!-- <style>
.nav-tabs>li.active>a {
	background-color: #369;
	color: #fff;
}

.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus {
	background-color: #369;
	color: #fff;
}

#exTab3 .nav-pills>li>a {
	border-radius: 4px 4px 0 0;
}

#exTab3 .tab-content {
	color: white;
	background-color: #428bca;
	padding: 5px 15px;
}
</style> -->

<%-- <body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<%@include file="../header.jsp"%>

	<%@include file="../sidebar.jsp"%> --%>


<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<%@include file="../../header.jsp"%>

	<%@include file="../../sidebar.jsp"%>

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>Product</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

						<div class="content-body">
							<!-- Basic form layout section start -->

							<form:form method="POST" action="product/create"
								modelAttribute="product" onsubmit="return register()">
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
																		<input type="text" cssClass="form-control"
																			placeholder='Company Name' path="name" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Company Tagline</label>
																		<input type="text" cssClass="form-control"
																			placeholder='Company TagLine' path="companyTagLine"
																			value="" />
																	</div>
																</div>


																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>VAT</label>
																		<input type="text" cssClass="form-control"
																			placeholder='VAT' path="gstinVat" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>State Code</label>
																		<input type="text" cssClass="form-control"
																			placeholder='Enter State Code' path="states" value="" />
																	</div>
																</div>



																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>LOGO</label>
																		<input type="file" cssClass="form-control"
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
																		<input type="text" cssClass="form-control"
																			placeholder='street1' path="street1" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Phone</label>
																		<input type="text" cssClass="form-control"
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
																		<input type="text" cssClass="form-control"
																			placeholder='Enter fax Num' path="faxNum" value="" />
																	</div>
																</div>
																
																
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>City</label>
																		<input type="text" cssClass="form-control"
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
																		<input type="text" cssClass="form-control"
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
																		<input type="text" cssClass="form-control"
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
																		<input type="text" cssClass="form-control"
																			placeholder='Enter Currency' path="" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Company Registry</label>
																		<input type="text" cssClass="form-control"
																			placeholder='Enter Company Registry' path="states" value="" />
																	</div>
																</div>
															
															</div>
															
															
					<!-- <div class="form-actions center">
                     <a  href="/company/create"> <button type="button" class="btn btn-warning mr-1"> <i class="icon-cross2"></i> Cancel </button></a>
                      <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Save </button>
                    </div> -->



														</form>
													</div>
												</div>

											</div>
										</div>
										
										
										
									</div> 	


									<%-- <div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h4 class="card-title" id="basic-layout-icons">Product/Create</h4>
													<a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
												</div>
												
												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
												<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Address</label>
																		<input type="text" cssClass="form-control"
																			placeholder='street1' path="street1" value="" />
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Phone</label>
																		<input type="text" cssClass="form-control"
																			placeholder='Enter Phone No' path="phoneNum"
																			value="" />
																	</div>
																</div>
												
												
												<!-- <div class="row">
												  <div class="col-xs-12 col-sm-6>
													<label>Product Number </label> 	<input  type="number cssClass="form-control""> 
												</div>
												<div class="col-xs-12 col-sm-4">
													<label>Description <input type="text"> </label>
												</div>
												<div class="col-xs-12 col-sm-4">
													<label>Product Group <input type="text"> </label>
												</div>
												<div class="col-xs-12 col-sm-4">
													<label>UOM Group <input type="text"> </label>
												</div>
												<div class="col-xs-12 col-sm-4">
													<label>Bar Code <input type="number"> </label>
												</div>
												</div> -->
												
												<div class="row">
												<div class="col-sm-6 form-group">
												<label >Product Number </label>
												<input type="text" cssClass="form-control">
												</div>
												</div>
												
												</div>
											
											<div class="card-body collapse in">

												<ul class="nav nav-tabs">
													<li class="active"><a href="#tab1" data-toggle="tab">General Tab</a></li>
													<li class="tablinks"><a href="#tab2" data-toggle="tab">Principal
															Place</a></li>
													<li class="tablinks"><a href="#tab3" data-toggle="tab">Business
															Details</a></li>
													<!-- <li class="tablinks"><a href="#tab4" data-toggle="tab">Bank
															& Goods</a></li>
													<li class="tablinks"><a href="#tab5" data-toggle="tab">Service
															& Add Places</a></li>
													<li class="tablinks"><a href="#tab6" data-toggle="tab">Promoters</a>
													</li>
													<li class="tablinks"><a href="#tab7" data-toggle="tab">others</a>
													</li> -->
												</ul>

												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">

																<div class="tab-content">
																	<div class="tab-pane active" id="tab1">
																		<br> <span class="headerColor">General Tab</span>
																		<hr>
																		<div class="row">
																			<div class="col-xs-12 col-sm-3">
																				<div class="form-group">
																					<label> With Holding Tax Liable </label> 
																				</div>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<div class="form-group">
																					<label>Product Category</label> 
																					<br>
																					<label>Service</label>
																					<br>
																					<label>Product</label>
																					<br>
																					<label>GST</label>
																				</div>
																			</div> 
																		</div>
																		 
																		<hr>
																	 <div class="row">
																			<div class="col-xs-12 col-sm-6">
																		<div class="row">
																			<div class="col-xs-12 col-sm-4">
																				<label>Service Type</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" Class="form-control" ></input>
																			</div>
																			 
																		</div>
																		<br>
																		 <div class="row">
																			<div class="col-xs-12 col-sm-4">
																				<label>SAC</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" ></input>
																			</div>
																			 
																		</div>
																		<br>
																	 <div class="row">
																			<div class="col-xs-12 col-sm-4">
																				<label>Tax Category</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" ></input>
																			</div>
																			 
																		</div>
																		<br>
																</div>
																<div class="col-xs-12 col-sm-6">
																		<div class="row">
																			<div class="col-xs-12 col-sm-4">
																				<label>Product Type</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" ></input>
																			</div>
																			 
																		</div>
																		<br>
																		 <div class="row">
																			<div class="col-xs-12 col-sm-4">
																				<label>HSN</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" ></input>
																			</div>
																			 
																		</div>
																		<br>
																	 <div class="row">
																			<div class="col-xs-12 col-sm-4">
																				<label>Tax Category</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" ></input>
																			</div>
																			 
																		</div>
																		<br>
																</div>
																</div>
																<div>
																<h6>Serial and Batch Numbers</h6>
																	 <div class="row">
																			<div class="col-xs-12 col-sm-2">
																				<label>Managed Product By</label>
																			</div>
																			<div class="col-xs-12 col-sm-3">
																				<input type="text" ></input>
																			</div>
																			 
																		</div>
																</div>
																		<br>
																		<div class="row">
																			<div class="col-xs-12 col-sm-8"></div>
																			<div class="col-xs-12 col-sm-3">
																				<a class="btn btn-primary nextbtn">Next</a>

																				<button id="saveBtn" type="submit"
																					class="btn btn-success nextbtn">
																					<i class="icon-upload icon-white"></i> Save
																				</button>

																				<a href="/gstregistration/list"
																					class="btn btn-danger"> <i
																					class="icon-remove-circle icon-white"></i> Cancel
																				</a>
																			</div>
																		</div>

																	</div>

																	<div class="tab-pane" id="tab2">
																		<c:import
																			url="/WEB-INF/views/gstregistration/principalPlaces.jsp" />
																	</div>
																	<div class="tab-pane" id="tab3">
																		<c:import
																			url="/WEB-INF/views/gstregistration/businessDetails.jsp" />
																	</div>
																	<div class="tab-pane" id="tab4">
																		<c:import
																			url="/WEB-INF/views/gstregistration/bankAndGoods.jsp" />
																	</div>

																	<div class="tab-pane" id="tab5">
																		<c:import
																			url="/WEB-INF/views/gstregistration/serviceandAddPlaces.jsp" />
																	</div>
																	<div class="tab-pane" id="tab6">
																		<c:import
																			url="/WEB-INF/views/gstregistration/promoters.jsp" />
																	</div>
																	<div class="tab-pane" id="tab7">
																		<c:import
																			url="/WEB-INF/views/gstregistration/others.jsp" />
																	</div>
																</div>

															</div>

														</form>
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



									</div> --%>
								</section>
							</form:form>

							<a class="btn btn-primary" href="/users/4">Back</a> <br> <br>

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
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<%@include file="../../loadJs.jsp"%>
</body>



<!-- <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->
<!------ Include the above in your HEAD tag ---------->

<div class="container">
	<div class="=row">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">Dealer
					Details</a></li>
			<li class="tablinks"><a href="#tab2" data-toggle="tab">Principal
					Place</a></li>
			<li class="tablinks"><a href="#tab3" data-toggle="tab">Business
					Details</a></li>
			<li class="tablinks"><a href="#tab4" data-toggle="tab">Bank
					& Goods</a></li>
			<li class="tablinks"><a href="#tab5" data-toggle="tab">Service
					& Add Places</a></li>
			<li class="tablinks"><a href="#tab6" data-toggle="tab">Promoters</a>
			</li>
			<li class="tablinks"><a href="#tab7" data-toggle="tab">others</a>
			</li>
		</ul>
		<%-- <div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<br> <span class="headerColor">Dealer Details</span>
				<hr>
				<div class="row">
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="legalNameOfBusiness"
								path="legalNameOfBusiness">Legal Name Of Business</label>
							<input type="text" class="form-control"
								path="legalNameOfBusiness"
								name="legalNameOfBusiness"
								placeholder="Enter Legal Name Of Business" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.pan" path="dealerDetails.pan">PAN</label><span class="msg">*</span>
							<input type="text" id="paNumber" path="dealerDetails.pan"
								 class="form-control" name="dealerDetails.pan"
							     placeholder=" Enter PAN"></input>
							    <p id="panNumberError" Class="error">${panNumberError}</p>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.email" path="dealerDetails.email">Email</label><span class="msg">*</span>
							<input type="text" path="dealerDetails.email"
								 id="emailId" class="form-control" name="dealerDetails.email"
								 required="required" placeholder=" Enter EMAIL"></input>
								 <p id="emailIdError" Class="error">${emailIdError}</p>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.mobileNo" path="dealerDetails.mobileNo">Mobile No</label><span class="msg">*</span>
							<input type="text" path="dealerDetails.mobileNo"
								 id="mobileNo" class="form-control" name="dealerDetails.mobileNo"
								 required="required" placeholder=" Enter MobileNo"></input>
								  <p id="mobileNoError" Class="error">${mobileNoError}</p>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.tradeName" path="dealerDetails.tradeName">Trade Name</label>
							<input type="text" path="dealerDetails.tradeName"
								 id="tradeName" class="form-control" name="dealerDetails.tradeName"
								 required="required" placeholder=" Enter TradeName"></input>
						</div>
					</div>
				<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.constitution"
								path="dealerDetails.constitution">Constitution</label><span class="msg">*</span>
							<select class="form-control" id="constitution"
								path="dealerDetails.constitution">
								<option value="" label="Select" />
								<options items="${consitutionList}" />
							</select>
							<p id="constitutionError" Class="error">${constitutionError}</p>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label>State</label><span class="msg">*</span>
							<select class="form-control"
								 id="stateCode" path="dealerDetails.delaresAddress.state.code">
								<option value="" label="Select" />
								<options items="${stateList}" />
							</select>
							<p id="stateCodeError" class="error">${stateCodeError}</p>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.delaresAddress.district"
								path="dealerDetails.delaresAddress.district">District</label><span class="msg">*</span>
							<select class="form-control"
								id="district" path="dealerDetails.delaresAddress.district">
								<option value="" label="Select" />
								<option value="District" label="District" />
								<option value="District" label="District" />
							</select>
							<p id="districtError" class="error">${districtError}</p>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.sectorCircleWard"
								path="dealerDetails.sectorCircleWard">Sector, Circle, Ward Etc</label><span class="msg">*</span>
							<select class="form-control"
								id="sectorCircleWard"
								path="dealerDetails.sectorCircleWard">
								<option value="" label="Select" />
								<option value="sectorCircleWard1" label="sectorCircleWard1" />
							</select>
							<p id="sectorCircleWardError" class="error" >${sectorCircleWardError} </p>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.commisionarate"
								path="dealerDetails.commisionarate"> Commisionarate </label>
							<select class="form-control"
								path="dealerDetails.commisionarate">
								<option value="" label="Select" />
								<option value="ss" label="Selectss" />
							</select>
						</div>
					</div>	
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.division"
								path="dealerDetails.division"> Division </label>
							<select class="form-control" id="dealerDetails.division"
								path="dealerDetails.division">
								<option value="" label="Select" />
								<option value="division1" label="division1" />
								<option value="division2" label="division2" />
								<option value="division3" label="division3" />
								<option value="division4" label="division4" />
							</select>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.range" path="dealerDetails.range"> Range </label>
							<select class="form-control" id="dealerDetails.range"
								path="dealerDetails.range">
								<option value="" label="Select" />
								<option value="range1" label="range1" />
								<option value="range2" label="range2" />
								<option value="range3" label="range3" />
								<option value="range4" label="range4" />
							</select>
						</div>
					</div>
					<br>
				</div>
				<div class="row">
				<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label path="dealerDetails.optionForComposition">
								<b>Option For Composition</b>
							</label>
							<br>
							<radiobutton path="dealerDetails.optionForComposition"
								value="true" class="dealerDetails.optionForComposition"
								name="dealerDetails.optradio" checked="checked" />
							Yes &nbsp;&nbsp;
							<radiobutton path="dealerDetails.optionForComposition"
								class="dealerDetails.optionForComposition"
								name="dealerDetails.optradio" value="false" checked="checked" />
							No
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label for="dealerDetails.dateOfCommencementOfBusiness"
								path="dealerDetails.dateOfCommencementOfBusiness">Date Of Commencement Of Business</label>
							<input 
								path="dealerDetails.dateOfCommencementOfBusiness"
								class="form-control datePicker"
								name="dealerDetails.dateOfCommencementOfBusiness"
								placeholder="Enter Date"></input>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="form-group">
							<label
								for="dealerDetails.dateOnWhichLiabilityToPayTaxArises"
								path="dealerDetails.dateOnWhichLiabilityToPayTaxArises">Date&nbsp;On&nbsp;Which&nbsp;Liability&nbsp;To&nbsp;pay&nbsp;Tax&nbsp;Arises</label>
							<input 
								path="dealerDetails.dateOnWhichLiabilityToPayTaxArises"
								class="form-control datePicker"
								name="dealerDetails.dateOnWhichLiabilityToPayTaxArises"
								placeholder="Enter Date"></input>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<label path="dealerDetails.taxablePerson">Are you appling for a registration as a casual taxable person</label>
					</div>
					<div class="col-xs-12 col-sm-6">
						<radiobutton path="dealerDetails.taxablePerson" value="true"
							class="taxablePerson" name="optradio" checked="checked" />
						Yes &nbsp;&nbsp;
						<radiobutton path="dealerDetails.taxablePerson"
							class="taxablePerson" name="optradio" value="false" />
						No
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<label path="">If Yes</label>
					</div>
					<div class="col-xs-12 col-sm-4">
						<label path="">Turnover
               </label>
					</div>
					<div class="col-xs-12 col-sm-4">
						<label path="">Net Tax Liability</label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<label path="">Integrated GST(IGST)</label>
					</div>
					<div class="col-xs-12 col-sm-3">
						<input type="number"
							path="dealerDetails.integratedGstTurnOver" class="form-control"
							name="dealerDetails.integratedGstTurnOver"></input>
					</div>
					<div class="col-xs-12 col-sm-1"></div>
					<div class="col-xs-12 col-sm-3">
						<input type="number"
							path="dealerDetails.integratedNetTaxLiability"
							class="form-control"
							name="dealerDetails.integratedNetTaxLiability"></input>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<label path="">Central GST(CGST)</label>
					</div>
					<div class="col-xs-12 col-sm-3">
						<input type="number" path="dealerDetails.centralGstTurnOver"
							class="form-control" name="dealerDetails.centralGstTurnOver"></input>
					</div>
					<div class="col-xs-12 col-sm-1"></div>
					<div class="col-xs-12 col-sm-3">
						<input type="number"
							path="dealerDetails.centralGstTaxLiability" class="form-control"
							name="dealerDetails.centralGstTaxLiability"></input>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<label path="">State GST(SGST)</label>
					</div>
					<div class="col-xs-12 col-sm-3">
						<input type="number" path="dealerDetails.sateGstTurnOver"
							class="form-control" name="dealerDetails.sateGstTurnOver"></input>
					</div>
					<div class="col-xs-12 col-sm-1"></div>
					<div class="col-xs-12 col-sm-3">
						<input type="number" path="dealerDetails.sateGstTurnOver"
							class="form-control" name="dealerDetails.sateGstTurnOver"></input>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<label path="">Period for which registration required from </label>
					</div>
					<div class="col-xs-12 col-sm-3">
						<input  path="dealerDetails.turnOverDate"
							class="form-control datePicker" name="dealerDetails.turnOverDate"></input>
					</div>
					<div class="col-xs-12 col-sm-1">
						<label path="">To</label>
					</div>
					<div class="col-xs-12 col-sm-3">
						<input 
							path="dealerDetails.netTaxLiabilityDate" class="form-control datePicker"
							name="dealerDetails.netTaxLiabilityDate"></input>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-12 col-sm-8"></div>
					<div class="col-xs-12 col-sm-3">
						<a class="btn btn-primary nextbtn">Next</a>
						
						 <button id="saveBtn" type="submit" class="btn btn-success nextbtn">
							<i class="icon-upload icon-white"></i> Save
						</button>
						
						<a href="/gstregistration/list" class="btn btn-danger">
					<i class="icon-remove-circle icon-white"></i> Cancel
						</a>
						</div>
				</div>
				
			</div>

 			<div class="tab-pane" id="tab2">
				<c:import url="/WEB-INF/views/gstregistration/principalPlaces.jsp" />
			</div>
			<div class="tab-pane" id="tab3">
				<c:import url="/WEB-INF/views/gstregistration/businessDetails.jsp" />
			</div>
 			<div class="tab-pane" id="tab4">
				<c:import url="/WEB-INF/views/gstregistration/bankAndGoods.jsp" />
			</div>
			
			<div class="tab-pane" id="tab5">
				<c:import url="/WEB-INF/views/gstregistration/serviceandAddPlaces.jsp" />
			</div>
 			<div class="tab-pane" id="tab6">
				<c:import url="/WEB-INF/views/gstregistration/promoters.jsp" />
			</div>
				<div class="tab-pane" id="tab7">
				<c:import url="/WEB-INF/views/gstregistration/others.jsp" />
			</div>
		</div> --%>
		</form>
		</section>
	</div>
</div>


<!-- <div class="row">
    <div class="col-sm-10 col-sm-offset-1" id="logout">
        <div class="page-header">
            <h3 class="reviews">Leave your comment</h3>
            <div class="logout">
                <button class="btn btn-default btn-circle text-uppercase" type="button" onclick="$('#logout').hide(); $('#login').show()">
                    <span class="glyphicon glyphicon-off"></span> Logout                    
                </button>                
            </div>
        </div>
        <div class="comment-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#comments-logout" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Comments</h4></a></li>
                <li><a href="#add-comment" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Add comment</h4></a></li>
                <li><a href="#account-settings" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Account settings</h4></a></li>
            </ul>            
            <div class="tab-content">
                <div class="tab-pane active" id="comments-logout">                
                    <ul class="media-list">
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dancounsell/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Marco </h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                Great snippet! Thanks for sharing.
                              </p>
                              <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                              <a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse" href="#replyOne"><span class="glyphicon glyphicon-comment"></span> 2 comments</a>
                          </div>              
                        </div>
                        <div class="collapse" id="replyOne">
                            <ul class="media-list">
                                <li class="media media-replied">
                                    <a class="pull-left" href="#">
                                      <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/ManikRathee/128.jpg" alt="profile">
                                    </a>
                                    <div class="media-body">
                                      <div class="well well-lg">
                                          <h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> The Hipster</h4>
                                          <ul class="media-date text-uppercase reviews list-inline">
                                            <li class="dd">22</li>
                                            <li class="mm">09</li>
                                            <li class="aaaa">2014</li>
                                          </ul>
                                          <p class="media-comment">
                                            Nice job Maria.
                                          </p>
                                          <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                                      </div>              
                                    </div>
                                </li>
                                <li class="media media-replied" id="replied">
                                    <a class="pull-left" href="#">
                                      <img class="media-object img-circle" src="https://pbs.twimg.com/profile_images/442656111636668417/Q_9oP8iZ.jpeg" alt="profile">
                                    </a>
                                    <div class="media-body">
                                      <div class="well well-lg">
                                          <h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> Mary</h4></h4>
                                          <ul class="media-date text-uppercase reviews list-inline">
                                            <li class="dd">22</li>
                                            <li class="mm">09</li>
                                            <li class="aaaa">2014</li>
                                          </ul>
                                          <p class="media-comment">
                                            Thank you Guys!
                                          </p>
                                          <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                                      </div>              
                                    </div>
                                </li>
                            </ul>  
                        </div>
                      </li>          
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/kurafire/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Nico</h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                I'm looking for that. Thanks!
                              </p>
                              <div class="embed-responsive embed-responsive-16by9">
                                  <iframe class="embed-responsive-item" src="//www.youtube.com/embed/80lNjkcp6gI" allowfullscreen></iframe>
                              </div>
                              <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                          </div>              
                        </div>
                      </li>
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/lady_katherine/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Kriztine</h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                Yehhhh... Thanks for sharing.
                              </p>
                              <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                              <a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse" href="#replyTwo"><span class="glyphicon glyphicon-comment"></span> 1 comment</a>
                          </div>              
                        </div>
                        <div class="collapse" id="replyTwo">
                            <ul class="media-list">
                                <li class="media media-replied">
                                    <a class="pull-left" href="#">
                                      <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/jackiesaik/128.jpg" alt="profile">
                                    </a>
                                    <div class="media-body">
                                      <div class="well well-lg">
                                          <h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> Lizz</h4>
                                          <ul class="media-date text-uppercase reviews list-inline">
                                            <li class="dd">22</li>
                                            <li class="mm">09</li>
                                            <li class="aaaa">2014</li>
                                          </ul>
                                          <p class="media-comment">
                                            Classy!
                                          </p>
                                          <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                                      </div>              
                                    </div>
                                </li>
                            </ul>  
                        </div>
                      </li>
                    </ul> 
                </div>
                <div class="tab-pane" id="add-comment">
                    <form action="#" method="post" class="form-horizontal" id="commentForm" role="form"> 
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                              <textarea class="form-control" name="addComment" id="addComment" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="uploadMedia" class="col-sm-2 control-label">Upload media</label>
                            <div class="col-sm-10">                    
                                <div class="input-group">
                                  <div class="input-group-addon">http://</div>
                                  <input type="text" class="form-control" name="uploadMedia" id="uploadMedia">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-success btn-circle text-uppercase" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
                            </div>
                        </div>            
                    </form>
                </div>
                <div class="tab-pane" id="account-settings">
                    <form action="#" method="post" class="form-horizontal" id="accountSetForm" role="form">
                        <div class="form-group">
                            <label for="avatar" class="col-sm-2 control-label">Avatar</label>
                            <div class="col-sm-10">                                
                                <div class="custom-input-file">
                                    <label class="uploadPhoto">
                                        Edit
                                        <input type="file" class="change-avatar" name="avatar" id="avatar">
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                              <input type="text" class="form-control" name="name" id="name" placeholder="Vilma palma">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nickName" class="col-sm-2 control-label">Nick name</label>
                            <div class="col-sm-10">
                              <input type="text" class="form-control" name="nickName" id="nickName" placeholder="Vilma">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                              <input type="email" class="form-control" name="email" id="email" placeholder="vilma@mail.com">
                            </div>
                        </div>  
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">New password</label>
                            <div class="col-sm-10">
                              <input type="password" class="form-control" name="newPassword" id="newPassword">
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="confirmPassword" class="col-sm-2 control-label">Confirm password</label>
                            <div class="col-sm-10">
                              <input type="password" class="form-control" name="confirmPassword" id="confirmPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-primary btn-circle text-uppercase" type="submit" id="submit">Save changes</button>
                            </div>
                        </div>            
                    </form>
                </div>
            </div>
        </div>
	</div>
  </div> -->
<!-- <div class="row">
    <div class="col-sm-10 col-sm-offset-1" id="login">
        <div class="page-header">
            <h3 class="reviews">Leave your comment</h3>
            <div class="logout">
                <button class="btn btn-default btn-circle text-uppercase" type="button" onclick="$('#login').hide(); $('#logout').show()">
                    <span class="glyphicon glyphicon-off"></span> Login                    
                </button>                
            </div>
        </div>
        <div class="comment-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#comments-login" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Comments</h4></a></li>
                <li><a href="#add-comment-disabled" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Add comment</h4></a></li>
                <li><a href="#new-account" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Create an account</h4></a></li>
            </ul>            
            <div class="tab-content">
                <div class="tab-pane active" id="comments-login">                
                    <ul class="media-list">
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dancounsell/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Marco</h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                Great snippet! Thanks for sharing.
                              </p>
                              <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                              <a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse" href="#replyThree"><span class="glyphicon glyphicon-comment"></span> 2 comments</a>
                          </div>              
                        </div>
                        <div class="collapse" id="replyThree">
                            <ul class="media-list">
                                <li class="media media-replied">
                                    <a class="pull-left" href="#">
                                      <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/ManikRathee/128.jpg" alt="profile">
                                    </a>
                                    <div class="media-body">
                                      <div class="well well-lg">
                                          <h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> The Hipster</h4>
                                          <ul class="media-date text-uppercase reviews list-inline">
                                            <li class="dd">22</li>
                                            <li class="mm">09</li>
                                            <li class="aaaa">2014</li>
                                          </ul>
                                          <p class="media-comment">
                                            Nice job Maria.
                                          </p>
                                          <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                                      </div>              
                                    </div>
                                </li>
                                <li class="media media-replied" id="replied">
                                    <a class="pull-left" href="#">
                                      <img class="media-object img-circle" src="https://pbs.twimg.com/profile_images/442656111636668417/Q_9oP8iZ.jpeg" alt="profile">
                                    </a>
                                    <div class="media-body">
                                      <div class="well well-lg">
                                          <h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> Mary</h4></h4>
                                          <ul class="media-date text-uppercase reviews list-inline">
                                            <li class="dd">22</li>
                                            <li class="mm">09</li>
                                            <li class="aaaa">2014</li>
                                          </ul>
                                          <p class="media-comment">
                                            Thank you Guys!
                                          </p>
                                          <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                                      </div>              
                                    </div>
                                </li>
                            </ul>  
                        </div>
                      </li>          
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/kurafire/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Nico</h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                I'm looking for that. Thanks!
                              </p>
                              <div class="embed-responsive embed-responsive-16by9">
                                  <iframe class="embed-responsive-item" src="//www.youtube.com/embed/80lNjkcp6gI" allowfullscreen></iframe>
                              </div>
                              <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                          </div>              
                        </div>
                      </li>
                      <li class="media">
                        <a class="pull-left" href="#">
                          <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/lady_katherine/128.jpg" alt="profile">
                        </a>
                        <div class="media-body">
                          <div class="well well-lg">
                              <h4 class="media-heading text-uppercase reviews">Kriztine</h4>
                              <ul class="media-date text-uppercase reviews list-inline">
                                <li class="dd">22</li>
                                <li class="mm">09</li>
                                <li class="aaaa">2014</li>
                              </ul>
                              <p class="media-comment">
                                Yehhhh... Thanks for sharing.
                              </p>
                              <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                              <a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse" href="#replyFour"><span class="glyphicon glyphicon-comment"></span> 1 comment</a>
                          </div>              
                        </div>
                        <div class="collapse" id="replyFour">
                            <ul class="media-list">
                                <li class="media media-replied">
                                    <a class="pull-left" href="#">
                                      <img class="media-object img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/jackiesaik/128.jpg" alt="profile">
                                    </a>
                                    <div class="media-body">
                                      <div class="well well-lg">
                                          <h4 class="media-heading text-uppercase reviews"><span class="glyphicon glyphicon-share-alt"></span> Lizz</h4>
                                          <ul class="media-date text-uppercase reviews list-inline">
                                            <li class="dd">22</li>
                                            <li class="mm">09</li>
                                            <li class="aaaa">2014</li>
                                          </ul>
                                          <p class="media-comment">
                                            Classy!
                                          </p>
                                          <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
                                      </div>              
                                    </div>
                                </li>
                            </ul>  
                        </div>
                      </li>
                    </ul> 
                </div>
                <div class="tab-pane" id="add-comment-disabled">
                    <div class="alert alert-info alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert">
                        <span aria-hidden="true">×</span><span class="sr-only">Close</span>                        
                      </button>
                      <strong>Hey!</strong> If you already have an account <a href="#" class="alert-link">Login</a> now to make the comments you want. If you do not have an account yet you're welcome to <a href="#" class="alert-link"> create an account.</a>
                    </div>
                    <form action="#" method="post" class="form-horizontal" id="commentForm" role="form"> 
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Comment</label>
                            <div class="col-sm-10">
                              <textarea class="form-control" name="addComment" id="addComment" rows="5" disabled></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="uploadMedia" class="col-sm-2 control-label">Upload media</label>
                            <div class="col-sm-10">                    
                                <div class="input-group">
                                  <div class="input-group-addon">http://</div>
                                  <input type="text" class="form-control" name="uploadMedia" id="uploadMedia" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-success btn-circle text-uppercase disabled" type="submit" id="submitComment"><span class="glyphicon glyphicon-send"></span> Summit comment</button>
                            </div>
                        </div>            
                    </form>
                </div>
                <div class="tab-pane" id="new-account">
                    <form action="#" method="post" class="form-horizontal" id="commentForm" role="form">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                              <input type="text" class="form-control" name="name" id="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                              <input type="email" class="form-control" name="email" id="email" required>
                            </div>
                        </div>  
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-10">
                              <input type="password" class="form-control" name="password" id="password">
                            </div>
                        </div>                         
                        <div class="form-group">
                            <div class="checkbox">                
                                <label for="agreeTerms" class="col-sm-offset-2 col-sm-10">
                                    <input type="checkbox" name="agreeTerms" id="agreeTerms"> I agree all <a href="#">Terms & Conditions</a>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                    
                                <button class="btn btn-primary btn-circle text-uppercase" type="submit" id="submit">Create an account</button>
                            </div>
                        </div>            
                    </form>
                </div>
            </div>
        </div>
    </div>
  </div> -->
<div class="page-header text-center">
	<h3 class="reviews">
		<span class="glyphicon glyphicon-magnet"></span> Uicomments by <a
			href="https://twitter.com/maridlcrmn">maridlcrmn</a>
	</h3>
</div>
<div class="notes text-center">
	<small>Image credits: uifaces.com</small>
</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$('.nextbtn').click(function() {
			$('.nav-tabs > .active').next('li').find('a').trigger("click");
		});

		$('.prevoiusBtn').click(function() {
			$('.nav-tabs > .active').prev('li').find('a').trigger('click');
		});

	
	

	/* function createPopup() {
		$('div#createPopup').modal('show');

	}

	function createPopupForServices() {
		$('div#createPopupForAdditionalPlaces').modal('show');
	}

	function createPopupForAdditionalPlaces() {
		$('div#createPopupForAdditionalPlaces').modal('show');
	}

	function createPopupForBank() {
		$('div#createPopupForBankDetails').modal('show');
	} */
	

</script>