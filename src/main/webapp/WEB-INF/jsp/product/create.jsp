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
    

<script type="text/javascript">
$(document).ready(function(){
	$('.nav-item').click(function(){
		$(this).parent('ul').find('li').removeClass('active');
		$(this).parent('ul').find('li').find('a').removeClass('active');
		$(this).find('a').addClass('active');
	});
});


</script>

    <body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
       	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

                <div class="app-content content container-fluid" style="margin-top: 40px;">
	<div class="content-wrapper">
		<div class="content-body">
			<div class="row">
				<div class="large-12 columns">
					<div class="content-body">
						<!-- Basic form layout section start -->
						<c:url value="/product/save" var="createUrl" />
						<form:form  method="POST" action="${createUrl}" modelAttribute="product"  data-toggle="validator" role="form">
							<section id="basic-form-layouts">
								<div class="row match-height">
									<div class="col-md-12">
										<div class="card">
											<div class="card-header">
												<c:if test="${product.id!=null}">
													<h2 class="card-title" id="basic-layout-icons">Product/Update</h2>
													<form:input type="hidden" cssClass="form-control" path="id" />
												</c:if>
												<c:if test="${product.id==null}">
													<h2 class="card-title" id="basic-layout-icons">Product/Create</h2>
												</c:if> 
											</div>
											<input type="hidden" id="id" class="form-control" name="id" value="">
											<div class="card-body collapse in create-block">
												<div class="card-block">
													<form class="form">
														<div class="form-body">
															<div class="row">
																<div class="col-sm-6 form-group has-feedback">
																	<label>Product Number</label>
																	<form:input type="text" class="form-control" placeholder='Product Number' path="productNo"  onchange="isValidName('productNo','/product/isValidProductNo','1_productNo','Product Alredy Exists')" value="" required="true" oninvalid="this.setCustomValidity('Please Enter Product No.')" oninput="setCustomValidity('')" />
																	<!-- <div  id="1_productNo" class="help-block with-errors"></div> -->
																</div>
																
																<div class="col-sm-6 form-group">
																	<label>Description</label>
																	<form:input type="text" class="form-control" placeholder='Description' path="description" value="" required="true" oninvalid="this.setCustomValidity('Please Enter Description.')" oninput="setCustomValidity('')" />
																	<!-- <div  class="help-block with-errors"></div> -->
																</div>
															</div>
															
															<div class="row">
																<div class="col-sm-6 form-group">
																	<label>Product Group</label>
																	<form:select path="productGroup.id" class="form-control" required="true" oninvalid="this.setCustomValidity('Please Select Product.')" oninput="setCustomValidity('')">
																		<form:option value="">--Select--</form:option>
																		<c:forEach items="${productGroupList}" var="productGroup">
																			<form:option value="${productGroup.id}">${productGroup.productName}</form:option>
																		</c:forEach>
																	</form:select>
																	<!-- <div  class="help-block with-errors"></div> -->
																</div>
																<div class="col-sm-6 form-group">
																	<label>UOM Group</label>
																	<form:select path="uomCategory.id" id="uomCategoryId" class="form-control" required="true" oninvalid="this.setCustomValidity('Please Select UOM Group')" oninput="setCustomValidity('')">
																		<form:option value="">--Select--</form:option>
																		<c:forEach items="${uomCategoryList}" var="uomCategory">
																			<form:option value="${uomCategory.id}">${uomCategory.uomCategoryName}</form:option>
																		</c:forEach>
																	</form:select>
																	<!-- <div  class="help-block with-errors"></div> -->
																</div>
															</div>
															
															<div class="row">
																<div class="col-sm-12 form-group">
																<div class="inventory-list">
																		<form:checkbox path="inventoryProduct" id="inventoryProduct" class="purchaseType" value="inventoryProduct" required="true"  /> <span class="radio-list">Inventory Product</span>
																		<!-- <div  class="help-block with-errors"></div> -->
																	</div>
																	
																	<div class="inventory-list">
																		<form:checkbox path="purchaseProduct"  id="purchaseProduct" class="purchaseType" value="purchaseProduct" required="true"  /> <span class="radio-list">Purchase Product</span>
																		<!-- <div  class="help-block with-errors"></div> -->
																	</div>
																</div>
															</div>
															
															<ul class="nav nav-tabs" id="myTab" role="tablist">
																<li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">General</a>
																</li>
																<li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Purchasing</a>
																</li>
																<li class="nav-item"><a class="nav-link" id="messages-tab" data-toggle="tab" href="#messages" role="tab" aria-controls="messages" aria-selected="false">Inventory</a>
																</li>
															</ul>
															<div class="tab-content">
																<div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
																	
																	<div class="row">
																		<div class="col-sm-6 form-group">
																		<div class="inventory-list">
																			<!-- <label>With old Tax Liable</label> -->
																			<form:checkbox path="withOldTaxLiable" value="withOldTaxLiable" required="true" /><span class="radio-list">Withholding Tax Liable</span>
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-6 form-group">
																			<div class="input-group">
																				<!--  <label class="display-inline-block custom-control custom-radio ml-1" style="padding: 0px"> -->
																				<div class="inventory-list"><form:radiobutton class="product-category" name="productcategory" path="serviceOrProduct" required="true" value="service" /><span class="radio-list">Service</span></div>
																				<div class="inventory-list"><form:radiobutton class="product-category" name="productcategory" path="serviceOrProduct" required="true" value="product" /><span class="radio-list">Product</span></div>
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-6 form-group">
																			<div class="inventory-list"><form:checkbox path="gst" value="gst" id="gstcategory" required="true" /><span class="radio-list">GST</span>
																			<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																		</div>
																		<input type="hidden" id="gstValue" class="form-control" name="gstValue" value="${product.gst}">
																	</div>
																	<div id="service-gst-div" style="display: none;">
																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Service Type:</label>
																				<form:input path="serviceType" id="service" type="text" class="form-control"  oninvalid="this.setCustomValidity('Please Enter Service Type')" oninput="setCustomValidity('')" />
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																		
																			<div class="col-sm-4 form-group">
																				<label>SAC:</label>
																				<form:select path="sacCode.id"   class="form-control"  oninvalid="this.setCustomValidity('Please Select SAC Type')" oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${sacList}" var="sac">
																						<form:option value="${sac.id}">${sac.sacCode}</form:option>
																					</c:forEach>
																				</form:select>
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																		
																			<div class="col-sm-4 form-group">
																				<label>Tax Category</label>
																				<form:select path="taxCategory" id="taxCategory" class="form-control"  oninvalid="this.setCustomValidity('Please Select Tax Category')" oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${taxCategoryList}" var="taxCategory">
																						<form:option value="${taxCategory.key}">${taxCategory.value}</form:option>
																					</c:forEach>
																				</form:select>
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																		</div>
																	</div>
																	<div id="product-gst-div" style="display: none;">
																		<div class="row">
																			<div class="col-sm-4 form-group">
																				<label>Product Type</label>
																				<form:select id="product" path="productType" class="form-control"  oninvalid="this.setCustomValidity('Please Select Product  Type')" oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${productTypeList}" var="productType">
																					<form:option value="${productType.key}">${productType.value}</form:option>
																				</c:forEach>
																				</form:select>
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																		
																			<div class="col-sm-4 form-group">
																				<label>HSN:</label>
																				<form:select path="hsnCode.id" class="form-control" oninvalid="this.setCustomValidity('Please Select HSN Code')" oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${hsnList}" var="hsn">
																						<form:option value="${hsn.id}">${hsn.hsnCode}</form:option>
																					</c:forEach>
																				</form:select>
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																			
																			<div class="col-sm-4 form-group">
																				<label>TaxCategory</label>
																				<form:select path="productTaxCategory" class="form-control" oninvalid="this.setCustomValidity('Please Select Tax Category')" oninput="setCustomValidity('')">
																					<form:option value="">--Select--</form:option>
																					<c:forEach items="${taxCategoryList}" var="taxCategory">
																						<form:option value="${taxCategory.key}">${taxCategory.value}</form:option>
																					</c:forEach>
																				</form:select>
																				<!-- <div  class="help-block with-errors"></div> -->
																			</div>
																			
																		</div>
																		
																	</div>
																	<p class="card-title">Serial and Batch Numbers</p>
																	<div class="row">
																		<div class="col-sm-6 form-group">
																			<label>Manage ProductBy</label>
																			<form:select path="manageProductBy" class="form-control" required="true" oninvalid="this.setCustomValidity('Please Select Manage ProductBy')" oninput="setCustomValidity('')">
																				<form:option value="">--Select--</form:option>
																				<c:forEach items="${manageProductByList}" var="manageProductBy">
																					<form:option value="${manageProductBy.key}">${manageProductBy.value}</form:option>
																				</c:forEach>
																			</form:select>
																				
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	</div>
																</div>
																<div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
																	
																	<div class="row">
                                                                        <div class="col-sm-4 form-group">
                                                                            <label>Preferred Vendor</label>
                                                                            <form:input path="preferredVendor" class="form-control vendorname" required="true" oninvalid="this.setCustomValidity('Please Enter Preferred Vendor')" oninput="setCustomValidity('')" />
                                                                            <!-- <div  class="help-block with-errors"></div> -->
                                                                        </div>
																	
																		<div class="col-sm-4 form-group">
																			<label>Purchasing UOM Name: </label>
																			
																			<form:select id="purchasingUomId" path="purchasingUom.id" class="form-control" required="true" oninvalid="this.setCustomValidity('Please Select UOM')" oninput="setCustomValidity('')">
																				<form:option value="">--Select--</form:option>
																				<c:forEach items="${uomList}" var="uom">
																																								
																					<form:option value="${uom.id}">${uom.uomName}</form:option>
																				</c:forEach>
																			</form:select>
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																		<input type="hidden" id="purchasingUomValue" class="form-control" name="id" value="${product.purchasingUom.uomName}">
																		<input type="hidden" id="purchasingUomKey" class="form-control" name="id" value="${product.purchasingUom.id}">
																	
																		<div class="col-sm-4 form-group">
																			<label>Products Per Purchase Unit:</label>
																			<form:input path="produtPerPurchaseUnit" class="form-control numericwithdecimal" required="true" oninvalid="this.setCustomValidity('Please Enter Product Per Purchase Unit')" oninput="setCustomValidity('')" />
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-4 form-group">
																			<label>Packing UOM Name:</label>
																			<form:select id="packingUomId" path="packingUom.id" class="form-control" required="true" oninvalid="this.setCustomValidity('Please Select UOM')" oninput="setCustomValidity('')">
																				<form:option value="">--Select--</form:option>
																				<c:forEach items="${uomList}" var="uom">
																					<form:option value="${uom.id}">${uom.uomName}</form:option>
																				</c:forEach>
																			</form:select>
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																		<input type="hidden" id="packingUomValue" class="form-control" name="id" value="${product.packingUom.uomName}">
																		<input type="hidden" id="packingUomKey" class="form-control" name="id" value="${product.packingUom.id}">
																	
																		<div class="col-sm-4 form-group">
																			<label>Quantity Per Package:</label>
																			<form:input path="qualityPerPackage" type="text" class="form-control numericwithdecimal" required="true" oninvalid="this.setCustomValidity('Please Enter Quantity Per Package')" oninput="setCustomValidity('')" />
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	</div>
																</div>
																<div class="tab-pane" id="messages" role="tabpanel" aria-labelledby="messages-tab">
																	
																	<div class="row">
																		<div class="col-sm-4 form-group">
																			<label>Inventory UoM:</label>
																			<form:select id="inventoryUomId" path="inventoryUom.id" class="form-control" required="true" oninvalid="this.setCustomValidity('Please select UOM')" oninput="setCustomValidity('')">
																				<form:option value="">--Select--</form:option>
																				<c:forEach items="${uomList}" var="uom">
																					<form:option value="${uom.id}">${uom.uomName}</form:option>
																				</c:forEach>
																			</form:select>
																			<!-- <div  class="help-block with-errors"></div> -->
																		<input type="hidden" id="inventoryUomValue" class="form-control" name="id" value="${product.inventoryUom.uomName}">
																		<input type="hidden" id="inventoryUomKey" class="form-control" name="id" value="${product.inventoryUom.id}">
																	    </div>
																		<div class="col-sm-4 form-group">
																			<label>Minimum</label>
																			<form:input path="minimun" class="form-control numericwithdecimal" required="true" oninvalid="this.setCustomValidity('Please Enter Minimum Value')" oninput="setCustomValidity('')" />
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	
																		<div class="col-sm-4 form-group">
																			<label>Maximum</label>
																			<form:input path="maximim" class="form-control numericwithdecimal" required="true" oninvalid="this.setCustomValidity('Please Enter Maximum value')" oninput="setCustomValidity('')" />
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-sm-4 form-group">
																			<label>Valuation Method:</label>
																			<form:select path="valuationMethod" class="form-control" required="true" oninvalid="this.setCustomValidity('Please select Valuation Method')" oninput="setCustomValidity('')">
																				<form:option value="">--Select--</form:option>
																				<c:forEach items="${valuationMethodList}" var="valuationMethod">
																					<form:option value="${valuationMethod.key}">${valuationMethod.value}</form:option>
																				</c:forEach>
																			</form:select>
																		<!-- 	<div  class="help-block with-errors"></div> -->
																		</div>
																	
																		<div class="col-sm-4 form-group">
																			<label>Product Cost:</label>
																			<form:input path="productCost" class="form-control numericwithdecimal" required="true" oninvalid="this.setCustomValidity('Please Enter Product Cost')" oninput="setCustomValidity('')" />
																			<!-- <div  class="help-block with-errors"></div> -->
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<br>
														<div class="text-xs-center">
																	
																	<a href="#" onclick="goBack()" class="btn btn-primary float-left">
											                        Back</a>
															<a href="<c:url value="/product/list"/>">
																<button type="button" class="btn btn-warning mr-1">	<i class="icon-cross2"></i> Cancel</button>
															</a>
															<c:if test="${product.id==null}">
																<button type="submit" class="btn btn-primary" id="saveDetails">	<i class="icon-check2"></i> Save</button>
															</c:if>
															<c:if test="${product.id!=null}">
																<button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Update</button>
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
                <c:import url="/WEB-INF/jsp/loadJs.jsp"/>
    </body>
    <script>
        $(document).ready(function() {
        	/*  $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false).val('');
             $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false).val(''); */
        	
        	 /* $("#serviceOrProduct").click(function(){
        	        $("#serviceType").removeAttr("required");
        	    }); */
        	
        	    
        	
        	
        	if ($("#id").val()!=''){
        		uomSubCategoryLoadForUpdate();
        	}
        	
        	if (typeof $("#gstValue").val() != 'undefined'){
        		  $("#service-gst-div").hide();
                  $("#product-gst-div").hide();
                $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false);
                  $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false); 
                  if ($('#gstcategory').is(":checked") == true) {
                      var productcategory = $("input[class='product-category']:checked").val();
                      if (productcategory == 'service') {
                          $("#service-gst-div").show();
                           $(this).parents().find("#service-gst-div").find(".form-control").attr("required",true);
                          $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false).val(''); 
                      }else if(productcategory == 'product') {
                          $("#product-gst-div").show();
                           $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false).val('');
                          $(this).parents().find("#product-gst-div").find(".form-control").attr("required",true); 
                      }else{
                    	  $("#service-gst-div").hide();
                          $("#product-gst-div").hide();
                          $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false);
                          $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false); 
                      }
                  }
        	}
        	
        	if ($("#serviceOrProduct").val() != ''){
      		    var statusOfSP =$("#serviceOrProduct").val();
      		  if (statusOfSP == 'service' && $('#gstcategory').is(":checked") == true) {
      			  $("#service-gst-div").show();
                  $("#product-gst-div").hide();
                 $(this).parents().find("#service-gst-div").find(".form-control").attr("required",true);
                  $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false).val(''); 
               
                
              }
              if (statusOfSP == 'product' && $('#gstcategory').is(":checked") == true) {
                  $("#product-gst-div").show();
                  $("#service-gst-div").hide();
                   $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false).val('');
                  $(this).parents().find("#product-gst-div").find(".form-control").attr("required",true); 
              }
      		}
        	
        	
        	
        	
            $(".product-category").click(function() {
                if ($(this).val() == 'service' && $('#gstcategory').is(":checked") == true) {
                    $("#service-gst-div").show();
                    $("#product-gst-div").hide();
                     $(this).parents().find("#service-gst-div").find(".form-control").attr("required",true);
                    $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false).val(''); 
                }
                if ($(this).val() == 'product' && $('#gstcategory').is(":checked") == true) {
                    $("#product-gst-div").show();
                    $("#service-gst-div").hide();
                     $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false).val('');
                    $(this).parents().find("#product-gst-div").find(".form-control").attr("required",true); 
                   
                }
            });
            $("#gstcategory").click(function() {
                $("#service-gst-div").hide();
                $("#product-gst-div").hide();
                if ($('#gstcategory').is(":checked") == true) {
                    var productcategory = $("input[class='product-category']:checked").val();
                    if (productcategory == 'service') {
                        $("#service-gst-div").show();
                        $(this).parents().find("#service-gst-div").find(".form-control").attr("required",true);
                        $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false).val('');
                    }else if(productcategory == 'product') {
                        $("#product-gst-div").show();
                        $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false).val('');
                        $(this).parents().find("#product-gst-div").find(".form-control").attr("required",true);
                    } else {
                    	$("#service-gst-div").hide();
                        $("#product-gst-div").hide();
                        $(this).parents().find("#service-gst-div").find(".form-control").attr("required",false).val('');
                        $(this).parents().find("#product-gst-div").find(".form-control").attr("required",false).val('');
                    }
                }
            });

            
         
            
            
		if ( ($('#inventoryProduct').is(":checked") == true) && (($("#inventoryProduct").val()=="inventoryProduct"))){
			
            	 $(".purchaseType").removeAttr("required");
        		
        	}
        	
		if ( ($('#purchaseProduct').is(":checked") == true) && (($("#purchaseProduct").val()=="purchaseProduct"))){
        	
			
       		 $(".purchaseType").removeAttr("required");
   		
   	}
            
           /*  if (($("#purchaseProduct").val()=="purchaseProduct")){
            	alert("fd");
            	 $(".purchaseType").removeAttr("required");
       		
       	} */
            
            
        
            $(".purchaseType").click(function(){
            	if(($('#purchaseProduct').is(":checked") == true && ($('#inventoryProduct').is(":checked") == true))){
            	$(this).parents().find(".purchaseType").attr("required",false);
            	}else if(($('#purchaseProduct').is(":checked") == true && ($('#inventoryProduct').is(":checked") == false))){
            		 $(this).parents().find(".purchaseType").attr("required",false);
            		if($('#purchaseProduct').is(":checked").val() == "purchaseProduct"){
            			alertify.success('Please Select atleast one');
            		}
            	}else if(($('#purchaseProduct').is(":checked") == false && ($('#inventoryProduct').is(":checked") == true))){
            		 $(this).parents().find(".purchaseType").attr("required",false);
            		 if($('#inventoryProduct').is(":checked").val() == "inventoryProduct"){
             			alertify.success('Please Select atleast one');
             		}
            	}else if(($('#purchaseProduct').is(":checked") == false && ($('#inventoryProduct').is(":checked") == false))){
            		 $(this).parents().find(".purchaseType").attr("required",true);
            		 alertify.success('Please Select atleast one');
            	}
            	
            });
            
          
            
            $("#uomCategoryId").change(function(){
         	    var id=$('#uomCategoryId').val();
         		 $("#purchasingUomId").html('<option>Select</option>');
         		 $("#packingUomId").html('<option>Select</option>');
         		 $("#inventoryUomId").html('<option>Select</option>');
         	   $.ajax({
                    type : "GET",
                    url : "<c:url value="/product/getUOMList/"/>?id="+$('#uomCategoryId').val(),
                    success : function(response) {
                 	   console.log(response);
                        if (response == undefined) {
                            return false;
                        } else {
                     	   $.each(response, function(key, value) {
                     			   $("#purchasingUomId").append($("<option></option>").attr("value",key).text(value)); 
                     			   $("#packingUomId").append($("<option></option>").attr("value",key).text(value)); 
                     			   $("#inventoryUomId").append($("<option></option>").attr("value",key).text(value)); 
                     			   
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
    				 $("#purchasingUomId").html('');
            		 $("#packingUomId").html('');
            		 $("#inventoryUomId").html('');
    				   var id=$('#id').val();
    					// alert("updatePage"+id);
    				   $.ajax({
    			           type : "GET",
    			           url : "<c:url value="/product/getUOMList/"/>?id="+$("#uomCategoryId").val(),
    			           success : function(response) {
    			        	  // alert(response);
    			       //	alert("response"+JSON.stringify(response));
    			               if (response == undefined) {
    			                  // alert("response");
    			                   return false;
    			               } else {
    			            	   $("#purchasingUomId").append($("<option></option>").attr("value",$('#purchasingUomKey').val()).text($('#purchasingUomValue').val())); 
                 			   $("#packingUomId").append($("<option></option>").attr("value",$('#packingUomKey').val()).text($('#packingUomValue').val())); 
                 			   $("#inventoryUomId").append($("<option></option>").attr("value",$('#inventoryUomKey').val()).text($('#inventoryUomValue').val())); 
    			            	   
    			            	   
    			            	   $.each(response, function(key, value) {
    			            		   if(key!=$('#purchasingUomKey').val()){
    			            			  $("#purchasingUomId").append($("<option></option>").attr("value",key).text(value)); 
    			            		   }
    			            		   
    			            		  if(key!=$('#packingUomKey').val()){
    	                     			   $("#packingUomId").append($("<option></option>").attr("value",key).text(value)); 
    			            		   }
    			            		  
    			            		 if(key!=$('#inventoryUomKey').val()){
   	                     			   $("#inventoryUomId").append($("<option></option>").attr("value",key).text(value)); 
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
        
  		  $("#maximim").change(function(){
       				  if(parseInt($('#maximim').val()) <= parseInt($('#minimun').val())){
       					 $('#maximim').val('');
       					alertify.success('Enter Greater than Min Vlaue');
       				  }
       				// alert(status);
       	            }); 

  		var vendorNames=[];
        var vendorNamesList=${vendorNamesList};
        var availableTagsvendornames=[];
         $.each(vendorNamesList, function (index, value) {
             availableTagsvendornames.push(value.toString());
           });
        
        
            $(document).on("keypress", ".vendorname", function() {
                $(this).autocomplete({
                    source: availableTagsvendornames,
                    select: function(event, ui) {
                        var vendorname = ui.item.value;
                    //    alert(vendorname);
                      
                            },
                    });
                });
        
        
    </script>

    </html>