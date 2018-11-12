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

    <body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
        <%@include file="../header.jsp"%>

            <%@include file="../sidebar.jsp"%>

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

                                        <form:form method="POST" action="/vendor/save" modelAttribute="vendor" onsubmit="return register()">
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
                                                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                                                            <li class="nav-item"><a class="nav-link active" id="general-tab" data-toggle="tab" href="#general" role="tab" aria-controls="general" aria-selected="true">General</a></li>
                                                                            <li class="nav-item"><a class="nav-link" id="address-tab" data-toggle="tab" href="#address" role="tab" aria-controls="address" aria-selected="false">Address</a></li>
                                                                            <li class="nav-item"><a class="nav-link" id="payment-tab" data-toggle="tab" href="#payment" role="tab" aria-controls="payment" aria-selected="false">Payment Terms</a></li>
                                                                        </ul>
                                                                        <div class="tab-content">
                                                                            <div class="tab-pane active" id="general" role="tabpanel" aria-labelledby="general-tab">
                                                                                <br>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                            <label>Movile No</label>
                                                                                            <form:input path="mobileNo" placeholder='mobileNo' type="text" class="form-control" />
                                                                                        </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                            <label>Fax</label>
                                                                                            <form:input path="fax" placeholder='fax' type="text" class="form-control" />
                                                                                        </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                <div class="col-md-6 form-group">
                                                                                <label>Email</label>
                                                                                <form:input path="emailId" placeholder='emailId' type="text" class="form-control" />
                                                                                </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                	<div class="col-md-6 form-group">
                                                                                		<label>Business Type</label>
                                                                                		<form:select path="businessPartnerType" class="form-control">
                                                                                              <form:option value="">--Select--</form:option>
                                                                                                <form:option value="Company">Company</form:option>
                                                                                                <form:option value="Private">Private</form:option>
                                                                                                <form:option value="Employee">Employee</form:option>
                                                                                            </form:select>
                                                                                	</div>
                                                                                </div>
                                                                                
                                                                                
                                                                            </div>
                                                                            <div class="tab-pane" id="address" role="tabpanel" aria-labelledby="address-tab">
                                                                                <br>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Preferred Vendor</label>
                                                                                        <form:input path="preferredVendor" class="form-control" />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Purchasing UOM Name:</label>
                                                                                        <form:select path="purchasingUom" class="form-control">
                                                                                            <form:option value="">--Select--</form:option>
                                                                                          <c:forEach  items="${uomList}" var="uomList">

																	   <form:option value="${uomList.id}">${uomList.uomName}</form:option>
																	 </c:forEach>  

                                                                                        </form:select>
                                                                                    </div>
                                                                                    
                                                                                    <input type="hidden" id="purchasingUomValue" class="form-control"  name="id" value="${product.purchasingUom.uomName}">
     																				<input type="hidden" id="purchasingUomKey" class="form-control"  name="id" value="${product.purchasingUom.id}">
                                                                                
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Products Per Purchase Unit:</label>
                                                                                        <form:input path="produtPerPurchaseUnit" class="form-control" />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Packing UOM Name:</label>
                                                                                        <form:select path="packingUom" class="form-control">
                                                                                          <form:option value="">--Select--</form:option>
                                                                                           <c:forEach  items="${uomList}" var="uomList">

																	   <form:option value="${uomList.id}">${uomList.uomName}</form:option>
																	 </c:forEach>  

                                                                                        </form:select>
                                                                                    </div>
                                                                                      <input type="hidden" id="packingUomValue" class="form-control"  name="id" value="${product.packingUom.uomName}">
     																				<input type="hidden" id="packingUomKey" class="form-control"  name="id" value="${product.packingUom.id}">
                                                                                    
                                                                                </div>

                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Quantity Per Package:</label>
                                                                                        <form:input path="qualityPerPackage" type="text" class="form-control" />
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="tab-pane" id="payment" role="tabpanel" aria-labelledby="payment-tab">
                                                                                <br>

                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Inventory UoM:</label>
                                                                                        <form:select path="inventoryUom" class="form-control">
                                                                                         <form:option value="">--Select--</form:option>
                                                                                            <c:forEach  items="${uomList}" var="uomList">

																	   <form:option value="${uomList.id}">${uomList.uomName}</form:option>
																	 </c:forEach>  


                                                                                        </form:select>
                                                                                    </div>
                                                                                     <input type="hidden" id="inventoryUomValue" class="form-control"  name="id" value="${product.inventoryUom.uomName}">
     																				<input type="hidden" id="inventoryUomKey" class="form-control"  name="id" value="${product.inventoryUom.id}">
                                                                                </div>

                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Minimum</label>
                                                                                        <form:input path="minimun" class="form-control numericwithdecimal" />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Maximum</label>
                                                                                        <form:input path="maximim" class="form-control numericwithdecimal" />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label> Valuation Method:</label>
                                                                                        <form:select path="valuationMethod" class="form-control">
                                                                                            <form:option value="">--Select--</form:option>
                                                                                            <form:option value="Standard">Standard</form:option>
                                                                                            <form:option value="Moving Average">Moving Average</form:option>

                                                                                        </form:select>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <label>Product Cost:</label>
                                                                                        <form:input path="productCost" class="form-control numericwithdecimal" />
                                                                                    </div>
                                                                                </div>

                                                                            </div>

                                                                        </div>
                                                                </div>
                    
                   										 <div class="form-actions right">
																<a href="<c:url value ="/inventory/productList"/>">
																	<button type="button" class="btn btn-warning mr-1">
																		<i class="icon-cross2"></i> Cancel
																	</button>
																</a>

 																 <c:if test = "${product.id==null}">  
																<button type="submit" class="btn btn-primary">
																	<i class="icon-check2"></i> Save
																</button>
																  </c:if> 


															  <c:if test = "${product.id!=null}">  
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
    <script>
        $(document).ready(function() {
        	
        	if ($("#id").val()!=''){
        		uomSubCategoryLoadForUpdate();
        	}
        	
        	if (typeof $("#gstValue").val() != 'undefined'){
        		  $("#service-gst-div").hide();
                  $("#product-gst-div").hide();
                  if ($('#gstcategory').is(":checked") == true) {
                      var productcategory = $("input[class='product-category']:checked").val();
                      if (productcategory == 'service') {
                          $("#service-gst-div").show();
                      } else {
                          $("#product-gst-div").show();
                      }
                  }
        	}
        	
        	if ($("#serviceOrProduct").val() != ''){
      		    var statusOfSP =$("#serviceOrProduct").val();
        		
      		  if (statusOfSP == 'service' && $('#gstcategory').is(":checked") == true) {
                  $("#service-gst-div").show();
                  $("#product-gst-div").hide();
              }
              if (statusOfSP == 'product' && $('#gstcategory').is(":checked") == true) {
                  $("#product-gst-div").show();
                  $("#service-gst-div").hide();
              }
      	}
        	
        	
        	
        	
            $(".product-category").click(function() {
                if ($(this).val() == 'service' && $('#gstcategory').is(":checked") == true) {
                    $("#service-gst-div").show();
                    $("#product-gst-div").hide();
                }
                if ($(this).val() == 'product' && $('#gstcategory').is(":checked") == true) {
                    $("#product-gst-div").show();
                    $("#service-gst-div").hide();
                }
            });
            $("#gstcategory").click(function() {
                $("#service-gst-div").hide();
                $("#product-gst-div").hide();
                if ($('#gstcategory').is(":checked") == true) {
                    var productcategory = $("input[class='product-category']:checked").val();
                    if (productcategory == 'service') {
                        $("#service-gst-div").show();
                    } else {
                        $("#product-gst-div").show();
                    }
                }
            });

            
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
            
            
            
                     			  function uomSubCategoryLoadForUpdate(){
                     				 $("#purchasingUom").html('');
                             		 $("#packingUom").html('');
                             		 $("#inventoryUom").html('');
                     				   var id=$('#id').val();
                     					// alert("updatePage"+id);
                     				   $.ajax({
                     			           type : "GET",
                     			           url : "<c:url value="/inventory/getUOMList/"/>?id="+$("#uomCategoryId").val(),
                     			           success : function(response) {
                     			        	  // alert(response);
                     			       //	alert("response"+JSON.stringify(response));
                     			               if (response == undefined) {
                     			                  // alert("response");
                     			                   return false;
                     			               } else {
                     			            	   $("#purchasingUom").append($("<option></option>").attr("value",$('#purchasingUomKey').val()).text($('#purchasingUomValue').val())); 
            	                     			   $("#packingUom").append($("<option></option>").attr("value",$('#packingUomKey').val()).text($('#packingUomValue').val())); 
            	                     			   $("#inventoryUom").append($("<option></option>").attr("value",$('#inventoryUomKey').val()).text($('#inventoryUomValue').val())); 
                     			            	   
                     			            	   
                     			            	   $.each(response, function(key, value) {
                     			            		   if(key!=$('#purchasingUomKey').val()){
                     			            			  $("#purchasingUom").append($("<option></option>").attr("value",key).text(value)); 
                     			            		   }
                     			            		   
                     			            		  if(key!=$('#packingUomKey').val()){
                     	                     			   $("#packingUom").append($("<option></option>").attr("value",key).text(value)); 
                     			            		   }
                     			            		  
                     			            		 if(key!=$('#inventoryUomKey').val()){
                    	                     			   $("#inventoryUom").append($("<option></option>").attr("value",key).text(value)); 
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
        
        
        
        
    </script>

    </html>