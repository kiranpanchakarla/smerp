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
                                <h4>Product</h4>
                            </div>
                        </div>
                        <div class="content-body">
                            <!--/ project charts -->
                            <div class="row">
                                <div class="large-12 columns">

                                    <div class="content-body">
                                        <!-- Basic form layout section start -->

                                        <form:form method="POST" action="/inventory/save" modelAttribute="product" onsubmit="return register()">
                                            <section id="basic-form-layouts">
                                                <div class="row match-height">

                                                    <div class="col-md-12">
                                                        <div class="card">
                                                            <div class="card-header">
                                                             <c:if test = "${product.id!=null}">  
												   <h4 class="card-title" id="basic-layout-icons">Product/Update</h4>
     					 									<form:input type="hidden" cssClass="form-control"  path="id"  />
								 				 </c:if>
								 				 
								 				  <c:if test = "${product.id==null}">  
												   <h4 class="card-title" id="basic-layout-icons">Product/Create</h4>
								 				 </c:if>
                                                            
                                                             
                                                                
                                                                
                                                                <a class="heading-elements-toggle"><i
														class="icon-ellipsis font-medium-3"></i></a>
                                                            </div>

                                                            <input type="hidden" id="id" class="form-control" name="id" value="">

                                                            <div class="card-body collapse in">
                                                                <div class="card-block">
                                                                   
                                                                        <div class="form-body">
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>Product No</label>
                                                                                    <form:input type="text" class="form-control" placeholder='productNo' path="productNo" value="" />
                                                                                </div>
                                                                                <div class="col-sm-6 form-group">
                                                                                    <div style="margin-top: 38px">
                                                                                        <form:checkbox path="inventoryProduct" value="inventoryProduct" /> Inventory Product
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>Description</label>
                                                                                    <form:input type="text" class="form-control" placeholder='description' path="description" value="" />
                                                                                </div>
                                                                                <div class="col-sm-6 form-group">
                                                                                    <div style="margin-top: 38px">
                                                                                        <form:checkbox path="purchaseProduct" value="purchaseProduct" /> Purchase Product
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>Product Group</label>
                                                                                     <form:select path="productCategory.id" class="form-control">
                                                                                       <form:option value="">--Select--</form:option>
                                                                                     <c:forEach  items="${productCategoryList}" var="productCategoryList">

																	   <form:option value="${productCategoryList.id}">${productCategoryList.name}</form:option>
																	 </c:forEach>  
                                                                                                
                                                                                            </form:select>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-sm-6 form-group">
                                                                                    <label>UOM Group</label>
                                                                                    <form:select path="uomCategory.id" id="uomCategoryId" class="form-control">
                                                                                    <form:option value="">--Select--</form:option>
                                                                                     <c:forEach  items="${uomCategoryList}" var="uomCategoryList">
																					   <form:option value="${uomCategoryList.id}">${uomCategoryList.uomCategoryName}</form:option>
																					 </c:forEach>  
                                                                                    </form:select>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                                                            <li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">General</a></li>
                                                                            <li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Purchasing</a></li>
                                                                            <li class="nav-item"><a class="nav-link" id="messages-tab" data-toggle="tab" href="#messages" role="tab" aria-controls="messages" aria-selected="false">Inventory</a></li>
                                                                        </ul>
                                                                        <div class="tab-content">
                                                                            <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                                                                <br>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <!-- <label>With old Tax Liable</label> -->
                                                                                        <form:checkbox path="withOldTaxLiable" value="withOldTaxLiable" /> With Old Tax Liable
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">

                                                                                        <div class="input-group">
                                                                                            <!--  <label class="display-inline-block custom-control custom-radio ml-1" style="padding: 0px"> -->
                                                                                            <form:radiobutton class="product-category" name="productcategory" path="serviceOrProduct" value="service" /> Service
                                                                                            <form:radiobutton class="product-category" name="productcategory" path="serviceOrProduct" value="product" /> Product

                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-sm-6 form-group">
                                                                                        <form:checkbox path="gst" value="gst" id="gstcategory" /> GST
                                                                                    </div>
                                                                        <input type="hidden" id="gstValue" class="form-control"  name="gstValue" value="${product.gst}">
                                                                                </div>
                                                                                <div id="service-gst-div" style="display: none;">
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>Service Type:</label>
                                                                                            <form:input path="serviceType" type="text" class="form-control" />
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>SAC:</label>
                                                                                            <form:select path="sacCode.id" class="form-control">
                                                                                              <form:option value="">--Select--</form:option>
                                                                                                    <c:forEach  items="${sacList}" var="sacList">
																	  							 <form:option value="${sacList.id}">${sacList.sacCoe}</form:option>
																									 </c:forEach>  
                                                                                            </form:select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>Tax Category</label>
                                                                                            <form:select path="taxCategory" class="form-control">
                                                                                              <form:option value="">--Select--</form:option>
                                                                                                <form:option value="Regular ">Regular </form:option>
                                                                                                <form:option value="Nil Rated  ">Nil Rated  </form:option>
                                                                                                <form:option value="Exempt ">Exempt </form:option>
                                                                                            </form:select>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div id="product-gst-div" style="display: none;">
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>Product Type</label>
                                                                                            <form:select path="productType" class="form-control">
                                                                                              <form:option value="">--Select--</form:option>
                                                                                                <form:option value="Raw material">Raw material</form:option>
                                                                                                <form:option value="Capital Goods ">Capital Goods </form:option>
                                                                                                <form:option value="Finished Goods ">Finished Goods </form:option>
                                                                                            </form:select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>HSN:</label>
                                                                                            <form:select path="hsnCode.id" class="form-control">
                                                                                              <form:option value="">--Select--</form:option>
                                                                                               <c:forEach  items="${hsnList}" var="hsnList">

																	   <form:option value="${hsnList.id}">${hsnList.hsnCode}</form:option>
																	 </c:forEach>  
                                                                                            </form:select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>TaxCategory </label>
                                                                                            <form:select path="productTaxCategory" class="form-control">
                                                                                              <form:option value="">--Select--</form:option>
                                                                                                <form:option value="Regular">Regular</form:option>
                                                                                                <form:option value="Nil Rated">Nil Rated</form:option>
                                                                                                <form:option value="Exempt">Exempt</form:option>
                                                                                            </form:select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 form-group">
                                                                                            <label>Manage ProductBy</label>
                                                                                                <form:select path="manageProductBy" class="form-control">
                                                                                                    <form:option value="">--Select--</form:option>
                                                                                                    <form:option value="None">None</form:option>
                                                                                                    <form:option value="Serial Numbers">Serial Numbers</form:option>
                                                                                                    <form:option value="Batches">Batches</form:option>
                                                                                                </form:select>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
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
                                                                            <div class="tab-pane" id="messages" role="tabpanel" aria-labelledby="messages-tab">
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
																<a href="/inventory/productList">
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