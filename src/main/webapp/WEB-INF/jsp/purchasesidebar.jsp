<!-- main menu-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow"> 
  <div class="main-menu-content">
    <ul class="side_main_menu">
      <li><a href="<c:url value ="/dashboard"/>"><i class="icon-android-home left"></i><span class="menu_text">Administration</span><span class="menu_text_pad">&nbsp;</span></a>
        <ul class="sub_menu">
         <%--  <li class="has_sub"><a href="<c:url value ="/users/4"/>">Users</a></li> --%>
         <%-- <li class="has_sub"><a href="<c:url value ="/user/create"/>">Users</a></li> --%>
         <%-- <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li> --%>
        </ul>
      </li>
      
       <li><a href="<c:url value ="/purchaseReq/list"/>"><i class="icon-android-menu left"></i><span class="menu_text">Purchase Request</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <%-- <ul class="sub_menu">
          <li class="has_sub"><a href="<c:url value ="/company/list"/>">Companies</a></li>
         <li class="has_sub"><a href="<c:url value ="/user/list"/>">Users</a></li>
          <li class="has_sub"><a href="<c:url value ="/vendor/create"/>">Vendor</a></li>
        </ul> --%>
      </li>
       <li><a href="<c:url value ="/rfq/list"/>"><i class="icon-android-expand left"></i><span class="menu_text">Request for Quotation</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <%-- <ul class="sub_menu">
          <li class="has_sub"><a href="<c:url value ="/product/productList"/>">Product</a></li>
          <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Product Type</a></li>
          <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Product UOM Category</a></li>
          <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Unit Of Measure</a></li>
          <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Product Attributes</a></li>
          <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Product Attribute Values</a></li>
         <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Warehouse</a></li>
         <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Inventory Adjustment</a></li>
        </ul> --%>
      </li>
      
      
     <!--  <li><a href="#"><i class="icon-android-star left"></i><span class="menu_text">Purchase Orders</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a> -->
        <%-- <ul class="sub_menu">
         <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li>
         <li><a href="<c:url value ="/country/list"/>">Countries</a></li>
          <li><a href="<c:url value ="/languages/list"/>">Languages</a></li>
           <li><a href="<c:url value ="/timezone/list"/>">Timezones</a></li>
            <li><a href="<c:url value ="/states/list"/>">States</a></li>
            <li><a href="<c:url value ="/hsncode/list"/>">HSN Code</a></li>
            <li><a href="<c:url value ="/saccode/list"/>">SAC Code</a></li>
         <!--  <li class="has_sub"><a>item 1<i class="icon-ios-arrow-right right"></i></a>
            <ul class="super_sub">
              <li><a>Sub item 1</a></li>
              <li><a>Sub item 1</a></li>
              <li><a>Sub item 1</a></li>
              <li class="has_sub"><a>Sub item 1<i class="icon-ios-arrow-right right"></i></a>
                <ul class="super_sub">
                  <li><a>Super Sub item 1</a></li>
                  <li class="has_sub"><a>Super Sub item 1<i class="icon-ios-arrow-right right"></i></a>
                    <ul class="super_sub">
                      <li><a>Super Sub item 1</a></li>
                    </ul>
                  </li>
                  <li><a>Super Sub item 1</a></li>
                </ul>
              </li>
            </ul>
          </li> -->
        </ul> --%>
      </li>
       <!-- <li><a href="#"><i class="icon-android-share left"></i><span class="menu_text">Goods Received</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a></li>
        <li><a href="#"><i class="icon-android-upload left"></i><span class="menu_text">Purchase Order Invoice</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a></li>
         <li><a href="#"><i class="icon-android-upload left"></i><span class="menu_text">Direct Account Invoice</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a></li>
          <li><a href="#"><i class="icon-android-sync left"></i><span class="menu_text">Reports</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a></li> -->
    </ul>
  </div>
</div>
<!-- / main menu-->