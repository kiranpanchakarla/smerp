<!-- main menu-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow"> 
  <div class="main-menu-content">
    <ul class="side_main_menu">
      <li><a href="<c:url value ="/dashboard"/>"><i class="icon-android-home left"></i><span class="menu_text">DASHBOARD</span><span class="menu_text_pad">&nbsp;</span></a>
        <ul class="sub_menu">
         <%--  <li class="has_sub"><a href="<c:url value ="/users/4"/>">Users</a></li> --%>
         <%-- <li class="has_sub"><a href="<c:url value ="/user/create"/>">Users</a></li> --%>
         <%-- <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li> --%>
        </ul>
      </li>
      
       <li><a href="#"><i class="icon-android-menu left"></i><span class="menu_text">Administration</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
         
          <c:forEach items="${sessionScope.umpmap}" var="ump">
			     <c:if test="${ump.key eq 'Company'}">
					 <li class="has_sub"><a href="<c:url value ="/company/list"/>">Companies</a></li>
			 	</c:if>
			    <c:if test="${ump.key eq 'User'}">
					<li class="has_sub"><a href="<c:url value ="/user/list"/>">Users</a></li>
			 	</c:if>
			 	
			 	 <c:if test="${ump.key eq 'Vendor'}">
					<li class="has_sub"><a href="<c:url value ="/vendor/list"/>">Vendor</a></li>
			 	</c:if>
   		 
            <c:if test="${ump.key eq 'Admin Master'}">
          <li class="has_sub"><a href="<c:url value ="/department/list"/>">Departments</a></li>
          <li class="has_sub"><a href="<c:url value ="/designation/list"/>">Designations</a></li>
          </c:if>
           </c:forEach> 
        </ul>
      </li>
      
      
       <c:forEach items="${sessionScope.umpmap}" var="ump">
	    <c:if test="${ump.key eq 'Product'}">
       <li><a href="#"><i class="icon-android-expand left"></i><span class="menu_text">Inventory</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
          
					 <li class="has_sub"><a href="<c:url value ="/product/list"/>">Product</a></li>
					 <c:if test="${ump.key eq 'Admin Master'}">
					  <li class="has_sub"><a href="<c:url value ="/producttype/list"/>">Product Group</a></li>
          			 <li class="has_sub"><a href="<c:url value ="/uomcategory/list"/>">UOM Category</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/uom/list"/>">Unit Of Measure</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/productattributes/list"/>">Product Attributes</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/productattributesvalues/list"/>">Product Attribute Values</a></li>
         			  <li class="has_sub"><a href="<c:url value ="/plant/list"/>">Warehouse/Plant</a></li>
         			  </c:if>
        </ul>
      </li>
      </c:if>
   		  </c:forEach> 
      
     
       <c:forEach items="${sessionScope.umpmap}" var="ump">
	    <c:if test="${ump.key eq 'Admin Master'}">
      <li><a href="#"><i class="icon-android-settings left"></i><span class="menu_text">Settings</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
         <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li>
         <li><a href="<c:url value ="/country/list"/>">Countries</a></li>
          <li><a href="<c:url value ="/languages/list"/>">Languages</a></li>
           <li><a href="<c:url value ="/timezone/list"/>">Timezones</a></li>
            <li><a href="<c:url value ="/states/list"/>">States</a></li>
            <li><a href="<c:url value ="/hsncode/list"/>">HSN Code</a></li>
            <li><a href="<c:url value ="/saccode/list"/>">SAC Code</a></li>
        
        </ul>
      </li>
       </c:if>
   		  </c:forEach> 
      
      <li>
      
      <a href="#"><i class="icon-egg left"></i><span class="menu_text">Purchase Module</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
        <c:forEach items="${sessionScope.umpmap}" var="ump">
			     <c:if test="${ump.key eq 'Purchase Request'}">
          <li class="has_sub"><a href="<c:url value ="/purchaseReq/list"/>">Purchase Request</a></li>
          </c:if>
          </c:forEach>
          
           <c:forEach items="${sessionScope.umpmap}" var="ump">
			     <c:if test="${ump.key eq 'Convert To RFQ'}">
             <li class="has_sub"><a href="<c:url value ="/purchaseReq/approvedList"/>">Convert PR To RFQ</a></li>
           </c:if>
           </c:forEach>
          
          <c:forEach items="${sessionScope.umpmap}" var="ump">
			     <c:if test="${ump.key eq 'RFQ'}">
           <li class="has_sub"><a href="<c:url value ="/rfq/list"/>">Request Quotations</a></li>
           </c:if>
           </c:forEach>
          
           
           <c:forEach items="${sessionScope.umpmap}" var="ump">
			     <c:if test="${ump.key eq 'Convert To PO'}">
             <li class="has_sub"><a href="<c:url value ="/purchaseReq/approvedList"/>">Convert RFQ To PO</a></li>
           </c:if>
           </c:forEach>
           
            <c:forEach items="${sessionScope.umpmap}" var="ump">
			     <c:if test="${ump.key eq 'PurchaseOrder'}">
             <li class="has_sub"><a href="<c:url value ="/po/list"/>">Purchase Order</a></li>
           </c:if>
           </c:forEach>
           
           
            
           
        </ul>
      </li>
      
     <%--  <li><a href="#"><i class="icon-marquee-plus left"></i><span class="menu_text">Approval Procedures</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
          <li class="has_sub"><a href="<c:url value =""/>">Approval Stages</a></li>
           <li class="has_sub"><a href="<c:url value =""/>">Approval Templates</a></li>
        </ul>
      </li> --%>
    </ul>
  </div>
</div>
<!-- / main menu-->