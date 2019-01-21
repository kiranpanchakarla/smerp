
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

<script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
<!--  <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>  -->   
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>  	
	
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/loadcss.jsp" />

	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row"></div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

						<div class="content-body">
							<!-- Basic form layout section start -->

							<c:url value="/department/save" var="createUrl" />
							<form:form  method="POST" action="${createUrl}" 
								modelAttribute="department" onsubmit="return register()"
								data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<c:if test="${department.id==null}">
														<h2 class="card-title" id="basic-layout-icons">Create New Department</h2>
													</c:if>

													<c:if test="${department.id!=null}">
														<h2 class="card-title" id="basic-layout-icons">Update Department Details</h2>
													</c:if>
												</div>

												<input type="hidden" id="id" class="form-control" name="id"
													value="${department.id}">

												<div class="card-body collapse in create-block">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																<div class="col-sm-4 form-group">
																		<label>Company</label>
																		<form:select id="company" path="company.id"   cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select country')" oninput="setCustomValidity('')">
																			<form:option value="">Select</form:option>
																			<c:forEach items="${companyList}" var="company">
																				<form:option value="${company.id}">${company.name}</form:option>
																			</c:forEach>
																		</form:select>
																		<!-- <div   class="help-block with-errors"></div> -->
																	</div> 
																<div class="col-sm-4 form-group">
																	<label for="timesheetinput2">Name</label>
																	<div>
																		<form:input type="text"  cssClass="form-control camelCase" 
																			placeholder='Department Name' path="name"
																			 required="true" onchange="isValidDepartmentName('name','/department/isValidDepartmentName','1_errorContainer','Department Name Already Exists in this Company')"
																			oninvalid="this.setCustomValidity('Please Enter Country Name')"
																			oninput="setCustomValidity('')"  />
																		<!-- <div  
																			class="help-block with-errors"></div>
																		  -->
																	</div>
																</div>
																
																	</div>
																	<%-- <div class="row">
																	<div class="col-sm-4 form-group">
																	<label for="timesheetinput2">Manager</label>
																	<div>
																		<form:input type="text" cssClass="form-control"
																			placeholder='Manager Name' path="phoneCode"
																			value="${departmentObj.user.username}" required="true"
																			oninvalid="this.setCustomValidity('Please Enter Phone Code')"
																			oninput="setCustomValidity('')" />
																		<div  
																			class="help-block with-errors"></div>
																		 
																	</div>
																</div>
																	</div> --%>
																
																
															</div>
															<br>
															<div class="text-xs-center">
																	
																	<a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a> <a href="<c:url value="/department/list"/>">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a> <input type="hidden" name="${_csrf.parameterName}"
																	value="${_csrf.token}" />

																<c:if test="${department.name!=null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Update
																	</button>
																</c:if>

																<c:if test="${department.name==null}">
																	<button type="submit" class="btn btn-primary">
																		<i class="icon-check2"></i> Save
																	</button>
																</c:if>


															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</section>

							</form:form>

							<br> <br>

							<!-- // Basic form layout section end -->
						</div>


						
					</div>
					<!--/ project charts -->
					<br>
				</div>
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

		<c:import url="/WEB-INF/jsp/loadJs.jsp" />
		<script type="text/javascript">
		
		$('.camelCase').keyup(function(){
			 this.value = capitalize_Words(this.value);
		});
		 
		 function capitalize_Words(str)
		 {
		  return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
		 }
		 
		 function isValidDepartmentName(nameId,url,displayId,msg){
			 
		      var parts = url.split('/');
		      var answer = parts[parts.length - 1];
		      
		      var dataString  ="name="+$('#'+nameId).val()+"&companyId="+ $('#company').val();
		      $.ajax({
		             type:"GET",
		            /*  url: url, */
		              url: answer,
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
		</script>
</body>

</html>

