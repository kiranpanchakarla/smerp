<!-- main menu-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow"> 
  <div class="main-menu-content">
    <ul class="side_main_menu">
      <li><a href="<c:url value ="/dashboard"/>"><i class="icon-android-home left"></i><span class="menu_text">Dashboard</span><span class="menu_text_pad">&nbsp;</span></a>
        <ul class="sub_menu">
         <%--  <li class="has_sub"><a href="<c:url value ="/users/4"/>">Users</a></li> --%>
         <%-- <li class="has_sub"><a href="<c:url value ="/user/create"/>">Users</a></li> --%>
         <%-- <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li> --%>
        </ul>
          <c:set var="oneTimeGoods" value="${oneTimeGoods + 1}" scope="page" /> 
      </li>
			<c:forEach items="${sessionScope.umpmap}" var="ump">
				<c:if test="${ump.key eq 'Purchase Request'}">
					<li><a href="<c:url value ="/purchaseReq/list"/>"><i
							class="icon-shuffle left"></i><span class="menu_text">Purchase
								Request</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a></li>
				</c:if>

				<c:if test="${ump.key eq 'Convert To RFQ'}">
					<li><a href="<c:url value ="/purchaseReq/approvedList"/>"><i
							class="icon-cubes left left"></i><span class="menu_text">Convert
								PR To RFQ</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>

				<c:if test="${ump.key eq 'RFQ'}">
					<li><a href="<c:url value ="/rfq/list"/>"><i
							class="icon-record left"></i><span class="menu_text">Request
								for Quotation</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>

				<c:if test="${ump.key eq 'Convert To PO'}">
					<li><a href="<c:url value ="/rfq/approvedList"/>"><i
							class="icon-cubes left"></i><span class="menu_text">Convert
								RFQ To PO</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>

				<c:if test="${ump.key eq 'PurchaseOrder'}">
					<li><a href="<c:url value ="/po/list"/>"><i
							class="icon-cube left left"></i><span class="menu_text">Purchase
								Order</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>

				<c:if test="${ump.key eq 'Convert To GR'}">
					<li><a href="<c:url value ="/po/approvedList"/>"><i
							class="icon-cubes left left"></i><span class="menu_text">Convert
								PO To GR</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>

				<c:if test="${ump.key eq 'Goods Receipt'}">
					<li><a href="<c:url value ="/gr/list"/>"><i
							class="icon-android-cart left left"></i><span class="menu_text">Goods
								Receipt</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>

                 <c:if test="${ump.key eq 'Convert To GRE'  || ump.key eq 'Convert To INV' }">
                   <c:if test="${oneTimeGoods  ==1}">
					<li><a href="<c:url value ="/gr/approvedList"/>"><i
							class="icon-cubes left left"></i><span class="menu_text">Convert GR To GRE/INV</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
							</li>
					</c:if>
					  <c:set var="oneTimeGoods" value="${oneTimeGoods + 1}" scope="page" /> 
				</c:if>




				<c:if test="${ump.key eq 'Goods Return'}">
					<li><a href="<c:url value ="/gre/list"/>"><i
							class="icon-arrow-return-left left left"></i><span class="menu_text">Goods
								Return</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>
				
				<c:if test="${ump.key eq 'Invoice'}">
					<li><a href="<c:url value ="/inv/list"/>"><i
							class="icon-moneybag left left"></i><span class="menu_text">Invoice</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>
				
				<c:if test="${ump.key eq 'Convert To CM'}">
					<li><a href="<c:url value ="/inv/approvedList"/>"><i
							class="icon-arrow-return-left left left"></i><span class="menu_text">Convert INV To CM</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>
				
				<c:if test="${ump.key eq 'Credit Memo'}">
					<li><a href="<c:url value ="/creditMemo/list"/>"><i
							class="icon-ei-credit-card left left"></i><span class="menu_text">Credit Memo</span><span class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
				</c:if>
				
			</c:forEach>





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