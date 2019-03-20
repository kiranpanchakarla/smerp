
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- <script src=<c:url value="/resources/js/scripts/pages/dashboard-lite.js"/> type="text/javascript"></script> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
    class="vertical-layout vertical-menu 2-columns">
    <c:import url="/WEB-INF/jsp/header.jsp" />
    <c:import url="/WEB-INF/jsp/sidebar.jsp" />
    
    <div class="app-content content container-fluid" style="margin-top: 40px;">
  <div class="content-wrapper">
    <div class="content-header row"> </div>
    <div class="content-body"><!-- stats -->
      <div class="row">
      <div class="col-xl-3 col-lg-6 col-xs-12">
          <div class="card dashboard-card">
          <a href="<c:url value="/company/list"/>">
            <div class="card-body">
              <div class="card-block color-box1">
                <div class="media">
                  <div class="media-body text-xs-left">
                    
                    <h3>${companyListCount}</h3>
                    <span>Company</span> </div>
                  <div class="media-right media-middle"> <i class="icon-building-o pink font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
              </a>
            </div>
            
          </div>
        </div>
        <div >
        <div class="col-xl-3 col-lg-6 col-xs-12">
         <a href="<c:url value="/product/list"/>">
          <div class="card dashboard-card">
            <div class="card-body">
              <div class="card-block color-box2">
                <div class="media">
                  <div class="media-body text-xs-left">
                    <h3>${productsCount}</h3>
                    <span>Products</span> </div>
                  <div class="media-right media-middle"> <i class="icon-shop pink font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
            </div>
          </div>
          </a>
        </div>
      </div>
        
        <div class="col-xl-3 col-lg-6 col-xs-12" >
        <a href="<c:url value="/user/list"/>">
          <div class="card dashboard-card">
            <div class="card-body">
              <div class="card-block color-box3">
                <div class="media">
                  <div class="media-body text-xs-left">
                    <h3>${userListCount}</h3>
                    
                    <span>Users</span> </div>
                  <div class="media-right media-middle"> <i class="icon-user1 teal font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
            </div>
          </div>
            </a>
        </div>
      
       
        <div class="col-xl-3 col-lg-6 col-xs-12">
       <a href="<c:url value="/vendor/list"/>">
          <div class="card dashboard-card">
            <div class="card-body">
              <div class="card-block color-box4">
                <div class="media">
                  <div class="media-body text-xs-left">
                    <h3>${vendorListCount}</h3>
                    <span>Vendors</span> </div>
                  <div class="media-right media-middle"> <i class="icon-diagram deep-orange font-large-2 float-xs-right"></i> </div>
                </div>
               
              </div>
            </div>
          </div>
          </a>
        </div>
        
     
      
      <div class="row match-height">
        <div class="col-sm-12">
         
       <%-- <div class="col-sm-3">
        <div class="card">
            <div class="card-body product-box1">
              <div class="media">
                <div class="p-2 media-body">
                <c:forEach items="${sessionScope.umpmap}" var="ump">
										 <c:if test="${ump.key eq 'Company'}">
										 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	<c:if test="${fn:containsIgnoreCase(permissions,'create')}">
										 	     <div class="p-2 text-xs-center product-bg-box media-left media-middle" style="float:left;"> <i class="icon-cube font-large-2 white"></i> </div>
	        									 <h3 style="margin: 5% 0% 0 25%;"><a href="<c:url value="/company/create"/>">New Company</a></h3>
	   										 </c:if>
	       								</c:if>     
   									 </c:forEach>
               
                </div>
              </div>
            </div>
          </div>
        </div>  --%> 
        
        
         <div class="col-sm-4">
        <div class="card">
            <div class="card-body product-box2">
              <div class="media">
               
                <div class="p-2 media-body">
                  <c:forEach items="${sessionScope.umpmap}" var="ump">
										 <c:if test="${ump.key eq 'Product'}">
										 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	<c:if test="${fn:containsIgnoreCase(permissions,'create')}">
				                                  <div class="p-2 text-xs-center product-bg-box media-left media-middle" style="float:left;"> <i class="icon-product-hunt font-large-2 white"></i> </div>
	        									  <h3 style="margin: 5% 0% 0 25%;"><a href="<c:url value="/product/create"/>">New Product</a></h3>
	   										 </c:if>
	       								</c:if>     
   									 </c:forEach>
                
                </div>
              </div>
            </div>
          </div>
        </div>
        
        
        
        <div class="col-sm-4">
        <div class="card">
            <div class="card-body product-box3">
              <div class="media">
                <div class="p-2 media-body">
                 <c:forEach items="${sessionScope.umpmap}" var="ump">
										 <c:if test="${ump.key eq 'User'}">
										 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	<c:if test="${fn:containsIgnoreCase(permissions,'create')}">
				                                  <div class="p-2 text-xs-center product-bg-box media-left media-middle" style="float:left;"> <i class="icon-user1 font-large-2 white"></i> </div>
	        									  <h3 style="margin: 5% 0% 0 25%;"><a href="<c:url value="/user/create"/>">New User</a></h3>
	   										 </c:if>
	       								</c:if>     
   									 </c:forEach>
                
                </div>
              </div>
            </div>
          </div>
        </div>
        
        
        <div class="col-sm-4">
        <div class="card">
            <div class="card-body product-box4">
              <div class="media">
                <div class="p-2 media-body">
                 <c:forEach items="${sessionScope.umpmap}" var="ump">
										 <c:if test="${ump.key eq 'Vendor'}">
										 <c:set var = "permissions" scope = "session" value = "${ump.value}"/>
										 	<c:if test="${fn:containsIgnoreCase(permissions,'create')}">
				                                 <div class="p-2 text-xs-center product-bg-box media-left media-middle" style="float:left;"> <i class="icon-user1 font-large-2 white"></i> </div>
				                                 <h3 style="margin: 5% 0% 0 25%;"><a href="<c:url value="/vendor/create"/>">New Vendor</a></h3>
				                                 
				                                 
	   										 </c:if>
	       								</c:if>     
   									 </c:forEach>
                
                </div>
              </div>
            </div>
          </div>
        </div>
         
        </div>
      
        </div>
        
      <%--   <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
              <div class="card-block">
                <h4 class="card-title" style="text-align: center;"><a href="<c:url value="/purchaseReq/list"/>">Purchase Request</a></h4>
              </div>
              <ul class="list-group list-group-flush">
                 <a id="example" onchange="setStatus('Open')" href="<c:url value="/purchaseReq/list?purchaseReqStatus=Open"/>">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-primary float-xs-right">${dashboardCount.open}</span> Open </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${dashboardCount.draft}</span> Draft </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-warning float-xs-right">${dashboardCount.cancelled}</span>Cancelled </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${dashboardCount.approved}</span> Approved</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${dashboardCount.rejected}</span> Rejected </li>
                 <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${dashboardCount.convertedToRFQ}</span>Converted to RFQ</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-total float-xs-right">${dashboardCount.total}</span> Total Records </li>
              </ul>
            </div>
          </div>
         </div> --%>
       <%--  <div class="col-md-6">
        <a href="<c:url value="/purchaseReq/list"/>">
          <div class="card">
          
            <div class="card-block color-box5">
            <h2 class="text-center">Purchase Request</h2>
              <div class="row text-xs-center">
                
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Open Stage</span>
                  <h2 class="block font-weight-normal">${dashboardCount.open}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Draft Stage</span>
                  <h2 class="block font-weight-normal">${dashboardCount.draft}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Cancelled</span>
                  <h2 class="block font-weight-normal">${dashboardCount.cancelled}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Approved</span>
                  <h2 class="block font-weight-normal">${dashboardCount.approved}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Rejected</span>
                  <h2 class="block font-weight-normal">${dashboardCount.rejected}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Converted to RFQ</span>
                  <h2 class="block font-weight-normal">${dashboardCount.convertedToRFQ}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Total Records</span>
                  <h2 class="block font-weight-normal">${dashboardCount.total}</h2>
                </div>
              </div>
            </div>
          </div>
          </a>
        </div> --%>
         <%-- <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
              <div class="card-block">
                <h4 class="card-title" style="text-align: center;"><a href="<c:url value="/rfq/list"/>">Request For Quotation</a></h4>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-primary float-xs-right">${rfqCount.open}</span> Open </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${rfqCount.draft}</span> Draft </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-warning float-xs-right">${rfqCount.cancelled}</span>Cancelled </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${rfqCount.approved}</span> Approved</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${rfqCount.rejected}</span> Rejected </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${rfqCount.convertedToRFQ}</span>Converted to RFQ</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${rfqCount.convertedToPO}</span>Converted to PO</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-total float-xs-right">${rfqCount.total}</span> Total Records </li>
              </ul>
            </div>
          </div>
         </div> --%>
         <%-- <div class="col-md-6">
         <a href="<c:url value="/rfq/list"/>">
          <div class="card">
          
            <div class="card-block color-box5">
            <h2 class="text-center">Request for Quotation</h2>
             <div class="row text-xs-center">
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Open Stage</span>
                  <h2 class="block font-weight-normal">${rfqCount.open}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Draft Stage</span>
                  <h2 class="block font-weight-normal">${rfqCount.draft}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Cancelled</span>
                  <h2 class="block font-weight-normal">${rfqCount.cancelled}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Approved</span>
                  <h2 class="block font-weight-normal">${rfqCount.approved}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Rejected</span>
                  <h2 class="block font-weight-normal">${rfqCount.rejected}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Converted to RFQ</span>
                  <h2 class="block font-weight-normal">${rfqCount.convertedToRFQ}</h2>
                </div>
                 <div class="col-xs-3 text-xs-center"> <span class="text-muted">Converted to PO</span>
                  <h2 class="block font-weight-normal">${rfqCount.convertedToPO}</h2>
                </div>
                <div class="col-xs-3  text-xs-center"> <span class="text-muted">Total Records</span>
                  <h2 class="block font-weight-normal">${rfqCount.total}</h2>
                </div>
              </div>
            </div>
          </div>
          </a>
        </div>  --%>
       <%-- <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
              <div class="card-block">
                <h4 class="card-title" style="text-align: center;"><a href="<c:url value="/po/list"/>">Purchase Order</a></h4>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-primary float-xs-right">${poCount.open}</span> Open </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${poCount.draft}</span> Draft </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-warning float-xs-right">${poCount.cancelled}</span>Cancelled </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${poCount.approved}</span> Approved</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${poCount.rejected}</span> Rejected </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${poCount.completed}</span> Completed </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${poCount.partiallyReceived}</span> PartiallyReceived </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-total float-xs-right">${poCount.total}</span> Total Records </li>
              </ul>
            </div>
          </div>
         </div> --%>
       <%-- <div class="col-md-6">
         <a href="<c:url value="/rfq/list"/>">
          <div class="card">
          
            <div class="card-block color-box5">
            <h2 class="text-center">Purchase Order</h2>
             <div class="row text-xs-center">
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Open Stage</span>
                  <h2 class="block font-weight-normal">${poCount.open}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Draft Stage</span>
                  <h2 class="block font-weight-normal">${poCount.draft}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Approved</span>
                  <h2 class="block font-weight-normal">${poCount.approved}</h2>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Rejected</span>
                  <h2 class="block font-weight-normal">${poCount.rejected}</h2>
                </div>
                
                <div class="col-xs-3  text-xs-center"> <span class="text-muted">Total Records</span>
                  <h2 class="block font-weight-normal">${poCount.total}</h2>
                </div>
              </div>
            </div>
          </div>
          </a>
        </div>  --%>
        
        <%-- <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
              <div class="card-block">
                <h4 class="card-title" style="text-align: center;"><a href="<c:url value="/gr/list"/>">Goods Receipt</a></h4>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-primary float-xs-right">${grCount.open}</span> Open </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${grCount.draft}</span> Draft </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-warning float-xs-right">${grCount.cancelled}</span>Cancelled </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-success float-xs-right">${grCount.approved}</span> Approved</li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${grCount.rejected}</span> Rejected </li>
                 <li class="list-group-item"> <span class="tag tag-default tag-pill bg-danger float-xs-right">${grCount.completed}</span> Completed </li>
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-total float-xs-right">${grCount.total}</span> Total Records </li>
              </ul>
            </div>
          </div>
         </div> --%>
         
            
          
         <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
            
              <ul class="list-group list-group-flush">
                <h4 class="card-title" style="text-align: center; padding-top: 10px;"><a href="">Purchase Request</a></h4>
               <c:forEach var="docCount" items="${count}">
               <c:if test="${docCount.status != 'ConvertedToPO' && docCount.status != 'Goods_Return' && docCount.status != 'Invoiced' && docCount.status != 'Partially_Received'}">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${docCount.prCount}</span> ${docCount.status} </li>
                </c:if>
                </c:forEach>
                
              </ul>
             
            </div>
          </div>
         </div>
         
         <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
           
              <ul class="list-group list-group-flush">
              <h4 class="card-title" style="text-align: center; padding-top: 10px;"><a href="">Request for Quotation</a></h4>
                
               <c:forEach var="docCount" items="${count}">
               <c:if test="${docCount.status != 'ConvertedToRFQ' && docCount.status != 'Goods_Return' && docCount.status != 'Invoiced' && docCount.status != 'Partially_Received' }">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${docCount.rfqCount}</span> ${docCount.status} </li>
                </c:if>
                </c:forEach>
              </ul>
             
            </div>
          </div>
         </div>  
         
         <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
            
              <ul class="list-group list-group-flush">
               <h4 class="card-title" style="text-align: center; padding-top: 10px;"><a href="">Purchase Order</a></h4>
               <c:forEach var="docCount" items="${count}">
                <c:if test="${docCount.status != 'ConvertedToRFQ' && docCount.status != 'ConvertedToPO' && docCount.status != 'Goods_Return' && docCount.status != 'Invoiced'}">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${docCount.poCount}</span> ${docCount.status} </li>
                </c:if>
                </c:forEach>
              </ul>
             
            </div>
          </div>
         </div>
         
         <div class="col-xl-3 col-lg-6 col-xs-12">
         <div class="card new_card_style" style="height: 440px;">
            <div class="card-body">
            
              <ul class="list-group list-group-flush">
               <h4 class="card-title" style="text-align: center; padding-top: 10px;"><a href="">Goods Receipt</a></h4>
               <c:forEach var="docCount" items="${count}">
               <c:if test="${docCount.status != 'ConvertedToRFQ' && docCount.status != 'ConvertedToPO' && docCount.status != 'Partially_Received' }">
                <li class="list-group-item"> <span class="tag tag-default tag-pill bg-info float-xs-right">${docCount.grCount}</span> ${docCount.status} </li>
                </c:if>
                </c:forEach>
              </ul>
             
            </div>
          </div>
         </div>
         
        
       
         
      </div><br><br>
      
      <!-- <hr> -->
    </div>
  </div>
</div>
    <c:import url="/WEB-INF/jsp/footer.jsp" />
    <c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<script src=<c:url value="/resources/js/scripts/pages/dashboard-lite.js"/> type="text/javascript"></script>
<!-- <script src="/resources/js/scripts/pages/dashboard-lite.js" type="text/javascript"></script> -->
<script>

$(document).ready(function(){
    /*  console.log("hiiiii");
    var x=$('#tokenId').val();
    console.log("token id"+x);
    document.cookie="tokenId=" + x;
    
    console.log("document cookie"+document.cookie);   */
});


	
	
</script>
</html>