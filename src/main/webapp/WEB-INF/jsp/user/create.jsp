
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
</head>

 <script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>

<body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />
	<div class="app-content content container-fluid" style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>USER</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">
						<div class="content-body">
							<!-- Basic form layout section start -->
							<form:form method="POST" action="/user/save" enctype="multipart/form-data" modelAttribute="user" data-toggle="validator" role="form">
								<section id="basic-form-layouts">
									<div class="row match-height">
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<c:if test="${user.userId!=null}">
														<h4 class="card-title" id="basic-layout-icons">Update</h4>
														<form:input type="hidden" cssClass="form-control" path="userId" />
													</c:if>
													<c:if test="${user.userId==null}">
														<h4 class="card-title" id="basic-layout-icons">Create</h4>
													</c:if>	
												</div>
												<input type="hidden" id="designationValue" class="form-control" name="id" value="${user.desigination.desigination}">
												<input type="hidden" id="designationKey" class="form-control" name="id" value="${user.desigination.id}">
												<div class="card-body collapse in">
													<div class="card-block">
														<form class="form">
															<div class="form-body">
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>First Name</label>
																		<form:input type="text" cssClass="form-control" placeholder='First Name' path="firstname" required="true" oninvalid="this.setCustomValidity('Please Enter First Name')" oninput="setCustomValidity('')" />
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Last Name</label>
																		<form:input type="text" cssClass="form-control" placeholder='Lat Name' path="lastname" required="true" oninvalid="this.setCustomValidity('Please Enter Last Name')" oninput="setCustomValidity('')" />
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>User Name</label>
																		<form:input type="text" cssClass="form-control" required="true" onchange="isValidName('username','/user/isValidUserName','1_userName','User Name Already Exists')" placeholder='User Name' path="username" oninvalid="this.setCustomValidity('Please Enter User Name')" oninput="setCustomValidity('')" />	<span style="color: red" class="scl-form-error-container" id="1_userName"></span>
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																	<div class="col-sm-2 form-group">
																		<label>Image</label>
																		<%-- <form:input type="file" cssClass="form-control" accept="image/*" onchange="loadFile(event)" path="logo" value="${company.logo}" />--%>
																		<p>
																			<input type="file" name="file" id="file" value="${user.image}" onchange="return fileValidation(event)" style="display: none;" required="true" oninvalid="this.setCustomValidity('Please Upload Image')" oninput="setCustomValidity('')" />
																			<form:input type="hidden" path="image" value="${user.image}" />
																		</p>
																		<p style="margin-top: -28px;">
																			<label for="file" style="cursor: pointer;">
																				<img src="${contextPath}/resources/images/company/cameraIcon.png">
																			</label>
																		</p>
																		<div style="color:red;" id="3_errorContainer" class="help-block with-errors"></div>
																	</div>
																	<div class="col-sm-4 form-group">
																		<c:if test="${filePath==null}">
																			<p>
																				<img src="${contextPath}/resources/images/company/noImageUploaded.png" alt="See" id="output" width="100" height="70" />
																			</p>
																			<%-- <img src="<c:url value=" ${contextPath} "/>"/>--%></c:if>
																		<c:if test="${filePath!=null}">
																			<p>
																				<img src="${filePath}" alt="See" id="output" width="100" height="70" />
																			</p>
																		</c:if>
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Email</label>
																		<form:input type="text" cssClass="form-control" placeholder='Email Id' path="userEmail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" value="" required="true" oninvalid="this.setCustomValidity('Please Enter Email Id')" oninput="setCustomValidity('')" />
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Mobile Number</label>
																		<form:input type="text" cssClass="form-control  numericwithoutdecimal" placeholder='Mobile Number' path="mobileNo" maxlength="10" pattern="[0-9]+$" value="" required="true" oninvalid="this.setCustomValidity('Please Enter Mobile No')" oninput="setCustomValidity('')" />
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Department</label>
																		<form:select id="department" path="department.id" cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select  Department')" oninput="setCustomValidity('')">
																			<form:option value="">Select</form:option>
																			<form:options items="${department}"></form:options>
																		</form:select>
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Designation</label>
																		<form:select id="desigination" path="desigination.id" cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select  Desigination')" oninput="setCustomValidity('')">
																			<c:if test="${user.username==null}">
																				<form:option value="">Select</form:option>
																			</c:if>
																		</form:select>
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-sm-6 form-group">
																		<label>Plant</label>
																		<form:select id="plant" path="plant.id" cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select  Plant')" oninput="setCustomValidity('')">
																			<form:option value="">Select</form:option>
																			<c:forEach items="${plantList}" var="plantList">
																				<form:option value="${plantList.id}">${plantList.plantName}</form:option>
																			</c:forEach>
																		</form:select>
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																	<div class="col-sm-6 form-group">
																		<label>Roles</label>
																		<form:select path="rolesDt" cssClass="form-control" required="true" oninvalid="this.setCustomValidity('Please Select  Role')" oninput="setCustomValidity('')">
																				<c:if test="${user.userId==null}">
																			 <form:option value="">Select</form:option>
																			</c:if>
																				<form:options items="${rolesList}"></form:options>
																		</form:select>
																		<div style="color:red;" class="help-block with-errors"></div>
																	</div>
																</div>
															</div>
															<div class="form-actions right">
																<a href="<c:url value="/user/list"/>">
																	<button type="button" class="btn btn-warning mr-1">	<i class="icon-cross2"></i> Cancel</button>
																</a>
																<c:if test="${user.username!=null}">
																	<button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Update</button>
																</c:if>
																<c:if test="${user.username==null}">
																	<button type="submit" class="btn btn-primary">	<i class="icon-check2"></i> Save</button>
																</c:if>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</section>
							</form:form> <a href="#" onclick="goBack()" class="btn btn-primary"> Back</a> 
							<!-- 	<a class="btn btn-primary" href="#">Back</a> <br> <br> -->
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
		<p class="clearfix text-muted text-sm-center mb-0 px-2">	<span class="float-md-right d-xs-block d-md-inline-block">Copyright
					&copy; 2018 <a href="#" target="_blank"
					class="text-bold-800 grey darken-2">SMERP </a>, All rights
					reserved.
				</span>
		</p>
	</footer>
	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<script>
$(document).ready(function() {
 

	
	if (typeof $("#userId").val() != 'undefined'){
		designationLoadForUpdate();
		
		$("#username").prop("readonly",true);
	}
	
	
	
 
   $("#department").change(function(){
	  // alert("The text has been changed."+$("#department").val());
	   designationLoad(); 
	});
   
   function designationLoad(){
	  // alert("hiii");
	   $("#desigination").html('<option>Select</option>');
	   $.ajax({
           type : "GET",
           url : "<c:url value="/user/getdeginations/"/>?id="+$("#department").val(),
           success : function(response) {
        	//   console.log(response);
    		//	alert("response"+JSON.stringify(response));
               if (response == undefined) {
                //  alert("response");
                   return false;
               } else {
            	   $.each(response, function(key, value) {
            		// alert(key+"  "+value);
            			$("#desigination").append($("<option></option>").attr("value",key).text(value)); 
            		});
               }
           },
           error : function(e) {
               alert("error" + JSON.stringify(e))

           }
       });
	   
   }
	
   
 function designationLoadForUpdate(){
	   var id=$('#designationValue').val();
		// alert(id);
	   $.ajax({
           type : "GET",
           url : "<c:url value="/user/getdeginations/"/>?id="+$("#department").val(),
           success : function(response) {
        	   console.log(response);
       //	alert("response"+JSON.stringify(response));
               if (response == undefined) {
                  // alert("response");
                   return false;
               } else {
            	   $("#desigination").append($("<option></option>").attr("value",$('#designationKey').val()).text($('#designationValue').val())); 
            	   $.each(response, function(key, value) {
            		   if(key!=$('#designationKey').val()){
            			   $("#desigination").append($("<option></option>").attr("value",key).text(value)); 
            		   }
            		});
            	
               }
           },
           error : function(e) {
               alert("error" + JSON.stringify(e))

           }
       }); 
	   
   }
});

            		   var loadFile = function(event) {
            				var image = document.getElementById('output');
            				image.src = URL.createObjectURL(event.target.files[0]);
            			};
            			
            			
            			 $("#uomCategoryId").change(function(){
            	         	    var id=$('#uomCategoryId').val();
            	         		// alert(id);
            	         		 $("#purchasingUom").html('<option>Select</option>');
            	         		 $("#packingUom").html('<option>Select</option>');
            	         		 $("#inventoryUom").html('<option>Select</option>');
            	         	   $.ajax({
            	                    type : "GET",
            	                    url : "<c:url value="/inventory/getUOMList/"/>?id="+$('#uomCategoryId').val(),
            	                    success : function(response) {
            	                 	   console.log(response);
            	                //	alert("response"+JSON.stringify(response));
            	                        if (response == undefined) {
            	                           // alert("response");
            	                            return false;
            	                        } else {
            	                     	  // $("#purchasingUom").append($("<option></option>").attr("value",$('#designationKey').val()).text($('#designationValue').val())); 
            	                     	   $.each(response, function(key, value) {
            	                     		  // if(key!=$('#designationKey').val()){
            	                     			   $("#purchasingUom").append($("<option></option>").attr("value",key).text(value)); 
            	                     			   $("#packingUom").append($("<option></option>").attr("value",key).text(value)); 
            	                     			   $("#inventoryUom").append($("<option></option>").attr("value",key).text(value)); 
            	                     			   
            	                     		 //  }
            	                     		});
            	                        }
            	                    },
            	                    error : function(e) {
            	                        alert("error" + JSON.stringify(e))

            	                    }
            	                });  
            	         	   
            	            }); 			
            			
            			 
  	                     			  $("#lastname").change(function(){
  	                     				 var firstName=$(firstname).val();
  	                     				  var lastName=$(lastname).val();
  	                     				  var firstSplit = firstName.split(" ");
  	                     				  var lastSplit =  lastName.split(" ");
  	                     				  var res1 = firstSplit[0].concat(lastSplit[0]); 
  	                     				  document.getElementById("username").value = res1;
  	                     				 var status= isValidName('username','/user/isValidUserName','1_userName','User Name Already Exit');
  	                     				// alert(status);
  	                     	            }); 
            	                     			  
            	                     			  
	                     			 function fileValidation(event){
	                     			    var fileInput = document.getElementById('file');
	                     			    var filePath = fileInput.value;
	                     			    var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
	                     			    if(!allowedExtensions.exec(filePath)){
	                     			        //alert();
	                     			        alertify.alert('Please upload file having extensions .jpeg/.jpg/.png/.gif only.');
	                     			        fileInput.value = '';
	                     			        return false;
	                     			    }else{
	                     			    	var image = document.getElementById('output');
	                     			    	image.src = URL.createObjectURL(event.target.files[0]);
	                     			    }
	                     			}     			  
            			 
</script>
</html>

