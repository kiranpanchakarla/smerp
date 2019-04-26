<!-- main menu-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow">
	<div class="main-menu-content">
		<ul class="side_main_menu">
			<li><a href="<c:url value ="/dashboard"/>"><i
					class="icon-android-home left"></i><span class="menu_text">Dashboard</span><span
					class="menu_text_pad">&nbsp;</span></a>
				<ul class="sub_menu">
					<%--  <li class="has_sub"><a href="<c:url value ="/users/4"/>">Users</a></li> --%>
					<%-- <li class="has_sub"><a href="<c:url value ="/user/create"/>">Users</a></li> --%>
					<%-- <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li> --%>
				</ul></li>

			<li><c:forEach items="${sessionScope.umpmap}" var="ump">
					<c:if test="${ump.key eq 'Admin Master'}">
						<a href="#"><i class="icon-android-menu left"></i><span
							class="menu_text">Administration</span><span
							class="menu_text_pad">&nbsp;</span><i
							class="icon-ios-arrow-right right"></i></a>
					</c:if>
				</c:forEach>
				<ul class="sub_menu">

					<c:forEach items="${sessionScope.umpmap}" var="ump">
						<c:if test="${ump.key eq 'Admin Master'}">
							<li class="has_sub"><a>Admin<i
									class="icon-ios-arrow-right right"></i></a>
						</c:if>
					</c:forEach>

					<ul class="super_sub">

						<c:forEach items="${sessionScope.umpmap}" var="ump">
							<c:if test="${ump.key eq 'Company'}">
								<li class="has_sub"><a
									href="<c:url value ="/company/list"/>">Companies</a></li>
							</c:if>
						</c:forEach>

						<c:forEach items="${sessionScope.umpmap}" var="ump">
							<c:if test="${ump.key eq 'Admin Master'}">
								<li class="has_sub"><a
									href="<c:url value ="/department/list"/>">Departments</a></li>
								<li class="has_sub"><a
									href="<c:url value ="/designation/list"/>">Designations</a></li>
							</c:if>
						</c:forEach>


						<c:forEach items="${sessionScope.umpmap}" var="ump">
							<c:if test="${ump.key eq 'User'}">
								<li class="has_sub"><a href="<c:url value ="/user/list"/>">Users</a></li>
							</c:if>
						</c:forEach>

						<c:forEach items="${sessionScope.umpmap}" var="ump">
							<c:if test="${ump.key eq 'Vendor'}">
								<li class="has_sub"><a
									href="<c:url value ="/vendor/list"/>">Vendors</a></li>
							</c:if>
						</c:forEach>

					</ul>

					<c:forEach items="${sessionScope.umpmap}" var="ump">
						<c:if test="${ump.key eq 'Admin Master'}">
							<li class="has_sub"><a>Inventory<i
									class="icon-ios-arrow-right right"></i></a>
						</c:if>
					</c:forEach>
					<ul class="super_sub">
						<c:forEach items="${sessionScope.umpmap}" var="ump">
							<c:if test="${ump.key eq 'Product'}">
								<li class="has_sub"><a
									href="<c:url value ="/product/list"/>">Products</a></li>
							</c:if>

						</c:forEach>


						<c:forEach items="${sessionScope.umpmap}" var="ump">

							<c:if test="${ump.key eq 'Admin Master'}">
								<%--  <li class="has_sub"><a href="<c:url value ="/productattributes/list"/>">Product Attributes</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/productattributesvalues/list"/>">Attribute Values</a></li> --%>
								<li class="has_sub"><a
									href="<c:url value ="/producttype/list"/>">Product Groups</a></li>
								<li class="has_sub"><a href="<c:url value ="/plant/list"/>">Warehouse</a></li>
								<li class="has_sub"><a href="<c:url value ="/uom/list"/>">Unit
										Of Measure</a></li>
								<li class="has_sub"><a
									href="<c:url value ="/uomcategory/list"/>">UOM Categories</a></li>

							</c:if>
						</c:forEach>
					</ul>

					<c:forEach items="${sessionScope.umpmap}" var="ump">
						<c:if test="${ump.key eq 'Admin Master'}">
							<li class="has_sub"><a>Global Settings<i
									class="icon-ios-arrow-right right"></i></a>
						</c:if>
					</c:forEach>
					<ul class="super_sub">

						<c:forEach items="${sessionScope.umpmap}" var="ump">
							<c:if test="${ump.key eq 'Admin Master'}">
								<li class="has_sub"><a
									href="<c:url value ="/currency/list"/>">Currencies</a></li>
								<li><a href="<c:url value ="/country/list"/>">Countries</a></li>
								<li><a href="<c:url value ="/hsncode/list"/>">HSN Code</a></li>
								<li><a href="<c:url value ="/languages/list"/>">Languages</a></li>
								<%-- <li><a href="<c:url value ="/saccode/list"/>">SAC Code</a></li> --%>
								<li><a href="<c:url value ="/states/list"/>">States</a></li>
								<li><a href="<c:url value ="/timezone/list"/>">Timezones</a></li>
							</c:if>
						</c:forEach>

					</ul>

				</ul></li>




			<%-- <li>
       <c:forEach items="${sessionScope.umpmap}" var="ump">
	    <c:if test="${ump.key eq 'Admin Master'}">
       <a href="#"><i class="icon-android-expand left"></i><span class="menu_text">Inventory</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
       </c:if>
			 	 </c:forEach>  
       
        <ul class="sub_menu">
        <c:forEach items="${sessionScope.umpmap}" var="ump">
                    <c:if test="${ump.key eq 'Product'}">
					 <li class="has_sub"><a href="<c:url value ="/product/list"/>">Products</a></li>
				   </c:if>	 
					
          </c:forEach> 
          
          
           <c:forEach items="${sessionScope.umpmap}" var="ump">
                     
					 <c:if test="${ump.key eq 'Admin Master'}">
					  <li class="has_sub"><a href="<c:url value ="/productattributes/list"/>">Product Attributes</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/productattributesvalues/list"/>">Product Attribute Values</a></li>
					  <li class="has_sub"><a href="<c:url value ="/producttype/list"/>">Product Groups</a></li>
          			 <li class="has_sub"><a href="<c:url value ="/plant/list"/>">Warehouse</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/uom/list"/>">Unit Of Measure</a></li>
         			 <li class="has_sub"><a href="<c:url value ="/uomcategory/list"/>">UOM Categories</a></li>
         			 
         			  </c:if>
          </c:forEach> 
          
          
        </ul>
      </li> --%>




			<%--  <c:forEach items="${sessionScope.umpmap}" var="ump">
	    <c:if test="${ump.key eq 'Admin Master'}">
      <li><a href="#"><i class="icon-android-settings left"></i><span class="menu_text">Settings</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
         <li class="has_sub"><a href="<c:url value ="/currency/list"/>">Currencies</a></li>
         <li><a href="<c:url value ="/country/list"/>">Countries</a></li>
           <li><a href="<c:url value ="/hsncode/list"/>">HSN Code</a></li>
          <li><a href="<c:url value ="/languages/list"/>">Languages</a></li>
           <li><a href="<c:url value ="/saccode/list"/>">SAC Code</a></li>
           <li><a href="<c:url value ="/states/list"/>">States</a></li>
           <li><a href="<c:url value ="/timezone/list"/>">Timezones</a></li>
        </ul>
      </li>
       </c:if>
   		  </c:forEach>  --%>

			<li><c:set var="oneTime" value="${oneTime + 1}" scope="page" />

				<c:set var="oneTimeGoods" value="${oneTimeGoods + 1}" scope="page" />


				<c:forEach items="${sessionScope.umpmap}" var="ump">
					<c:if
						test="${ump.key eq 'Purchase Request'  || ump.key eq 'Convert To RFQ' || ump.key eq 'RFQ' || ump.key eq 'Convert To PO'|| ump.key eq 'Convert To GR'|| ump.key eq 'Goods Receipt'|| ump.key eq 'Goods Return' }">
						<c:if test="${oneTime  ==1}">
							<a href="#"><i class="icon-shop left"></i><span
								class="menu_text">Purchase Module</span><span
								class="menu_text_pad">&nbsp;</span><i
								class="icon-ios-arrow-right right"></i></a>
						</c:if>
						<c:set var="oneTime" value="${oneTime + 1}" scope="page" />

					</c:if>
				</c:forEach>


				<ul class="sub_menu">
					<c:forEach items="${sessionScope.umpmap}" var="ump">
						<c:if test="${ump.key eq 'Purchase Request'}">
							<li class="has_sub"><a
								href="<c:url value ="/purchaseReq/list"/>">Purchase Request</a></li>
						</c:if>

						<c:if test="${ump.key eq 'Convert To RFQ'}">
							<li class="has_sub"><a
								href="<c:url value ="/purchaseReq/approvedList"/>">Convert
									PR To RFQ</a></li>
						</c:if>

						<c:if test="${ump.key eq 'RFQ'}">
							<li class="has_sub"><a href="<c:url value ="/rfq/list"/>">Request
									For Quotation</a></li>
						</c:if>

						<c:if test="${ump.key eq 'Convert To PO'}">
							<li class="has_sub"><a
								href="<c:url value ="/purchaseReq/approvedList"/>">Convert
									RFQ To PO</a></li>
						</c:if>

						<c:if test="${ump.key eq 'PurchaseOrder'}">
							<li class="has_sub"><a href="<c:url value ="/po/list"/>">Purchase
									Order</a></li>
						</c:if>

						<c:if test="${ump.key eq 'Convert To GR'}">
							<li class="has_sub"><a
								href="<c:url value ="/po/approvedList"/>">Convert PO To GR</a></li>
						</c:if>

						<c:if test="${ump.key eq 'Goods Receipt'}">
							<li class="has_sub"><a href="<c:url value ="/gr/list"/>">Goods
									Receipt</a></li>
						</c:if>

						<c:if
							test="${ump.key eq 'Convert To GRE' || ump.key eq 'Convert To INV' }">
							<c:if test="${oneTimeGoods  ==1}">
								<li class="has_sub"><a
									href="<c:url value ="/gr/approvedList"/>">Convert GR To
										GRE/INV</a></li>
							</c:if>
							<c:set var="oneTimeGoods" value="${oneTimeGoods + 1}"
								scope="page" />
						</c:if>

						<c:if test="${ump.key eq 'Goods Return'}">
							<li class="has_sub"><a href="<c:url value ="/gre/list"/>">Goods
									Return</a></li>
						</c:if>

						<c:if test="${ump.key eq 'Invoice'}">
							<li class="has_sub"><a href="<c:url value ="/inv/list"/>">Invoice
							</a></li>
						</c:if>

						<c:if test="${ump.key eq 'Convert To CM'}">
							<li class="has_sub"><a
								href="<c:url value ="/inv/approvedList"/>">Convert INV To CM
							</a></li>
						</c:if>


						<c:if test="${ump.key eq 'Credit Memo'}">
							<li class="has_sub"><a
								href="<c:url value ="/creditMemo/list"/>">Credit Memo </a></li>
						</c:if>

					</c:forEach>

				</ul></li>
			<li><a href="#"><i class="icon-marquee-plus left"></i><span
					class="menu_text">Inventory Module</span><span
					class="menu_text_pad">&nbsp;</span><i
					class="icon-ios-arrow-right right"></i></a> <!--  <ul class="sub_menu">
          <li class="has_sub"><a>Transactions<i class="icon-ios-arrow-right right"></i></a> -->
				<ul class="sub_menu">
					<li class="has_sub"><a href="<c:url value ="/invgr/list"/>">Goods
							Receipt</a></li>
					<li class="has_sub"><a href="<c:url value ="/invgi/list"/>">Goods
							Issue</a></li>
					<li class="has_sub"><a href="<c:url value ="/invgt/list"/>">Goods
							Transfer</a></li>
				</ul></li>
			<%--  <li class="has_sub"><a href="<c:url value =""/>">Approval Templates</a></li> --%>
			<!-- </ul>
      </li>  -->
			<%--  <li><a href="#"><i class="icon-marquee-plus left"></i><span class="menu_text">Approval Procedures</span><span class="menu_text_pad">&nbsp;</span><i class="icon-ios-arrow-right right"></i></a>
        <ul class="sub_menu">
          <li class="has_sub"><a href="<c:url value =""/>">Approval Stages</a></li>
           <li class="has_sub"><a href="<c:url value =""/>">Approval Templates</a></li>
        </ul>
      </li> --%>

			<li><a href="#"><i class="icon-file2 left"></i><span
					class="menu_text">Reports</span><span class="menu_text_pad">&nbsp;</span><i
					class="icon-ios-arrow-right right"></i></a> <!--  <ul class="sub_menu">
          <li class="has_sub"><a>Transactions<i class="icon-ios-arrow-right right"></i></a> -->
				<ul class="sub_menu">
					<li class="has_sub"><a href="<c:url value =""/>">Vendor Report
					<i class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li class="has_sub"><a
								href="<c:url value ="/vendorReport/polist"/>">Purchase Order</a></li>
							<li class="has_sub"><a
								href="<c:url value ="/vendorReport/invlist"/>">Invoice</a></li>
						</ul></li>
					 <li class="has_sub"><a
						href="<c:url value =""/>">Products Report <i class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li class="has_sub"><a
								href="<c:url value ="/productReport/polist"/>">PO</a></li>
							<li class="has_sub"><a
								href="<c:url value ="/productReport/invlist"/>">INV</a></li>
						</ul></li> 
					<li class="has_sub"><a
						href="<c:url value =""/>">Warehouse Report <i class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li class="has_sub"><a
								href="<c:url value ="/warehouseReport/polist"/>">PO</a></li>
							<li class="has_sub"><a
								href="<c:url value ="/warehouseReport/invlist"/>">INV</a></li>
						</ul></li> 
				</ul></li>

		</ul>
	</div>
</div>
<!-- / main menu-->