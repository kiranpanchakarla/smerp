
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
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
    class="vertical-layout vertical-menu 2-columns">
    <c:import url="/WEB-INF/jsp/header.jsp" />
    <c:import url="/WEB-INF/jsp/sidebar.jsp" />
    <%-- <%@include file="header.jsp"%>
    <%@include file="sidebar.jsp"%> --%>
    <%-- <div class="app-content content container-fluid"
        style="margin-top: 40px;">
        <div class="content-wrapper">
            <div class="content-header row">
                <div class="col-md-6">
                    <h4>Header</h4>
                </div>
            </div>
            <div class="content-body">
                <!--/ project charts -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                 <div>token:${token}</div>
                                <input type="text"  id="tokenId"  value="${tokenId}"/> 
                                <div>data:${data}</div>
                            </div>
                            <div class="card-footer">Footer</div>
                        </div>
                    </div>
                </div>
                <!--/ project charts -->
                <br>
            </div>
        </div>
    </div> --%>
    
    <div class="app-content content container-fluid" style="margin-top: 40px;">
  <div class="content-wrapper">
    <div class="content-header row"> </div>
    <div class="content-body"><!-- stats -->
      <div class="row">
        <div class="col-xl-3 col-lg-6 col-xs-12">
          <div class="card">
            <div class="card-body">
              <div class="card-block">
                <div class="media">
                  <div class="media-body text-xs-left">
                    <h4 class="pink">5</h4>
                    <span>Purchase</span> </div>
                  <div class="media-right media-middle"> <i class="icon-bag2 pink font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-lg-6 col-xs-12">
          <div class="card">
            <div class="card-body">
              <div class="card-block">
                <div class="media">
                  <div class="media-body text-xs-left">
                    <h3 class="teal">6</h3>
                    <span>Admin</span> </div>
                  <div class="media-right media-middle"> <i class="icon-user1 teal font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-lg-6 col-xs-12">
          <div class="card">
            <div class="card-body">
              <div class="card-block">
                <div class="media">
                  <div class="media-body text-xs-left">
                    <h3 class="deep-orange">99.99 %</h3>
                    <span>Inventory</span> </div>
                  <div class="media-right media-middle"> <i class="icon-diagram deep-orange font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-lg-6 col-xs-12">
          <div class="card">
            <div class="card-body">
              <div class="card-block">
                <div class="media">
                  <div class="media-body text-xs-left">
                  <h3 class="cyan">3</h3>
                    <span>Settings</span> </div>
                  <div class="media-right media-middle"> <i class="icon-ios-help-outline cyan font-large-2 float-xs-right"></i> </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--/ stats --> 
      <!--/ project charts -->
      <div class="row">
        <div class="col-xl-8 col-lg-12">
          <div class="card">
            <div class="card-body">
              <ul class="list-inline text-xs-center pt-2 m-0">
                <li class="mr-1">
                  <h6><i class="icon-circle warning"></i> <span class="grey darken-1">Remaining</span></h6>
                </li>
                <li class="mr-1">
                  <h6><i class="icon-circle success"></i> <span class="grey darken-1">Completed</span></h6>
                </li>
              </ul>
              <div class="chartjs height-250">
                <canvas id="line-stacked-area" height="250"></canvas>
              </div>
            </div>
            <div class="card-footer">
              <div class="row">
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Total Projects</span>
                  <h2 class="block font-weight-normal">10</h2>
                  <progress class="progress progress-xs mt-2 progress-success" value="70" max="100"></progress>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Total Task</span>
                  <h2 class="block font-weight-normal">20</h2>
                  <progress class="progress progress-xs mt-2 progress-success" value="40" max="100"></progress>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Completed Task</span>
                  <h2 class="block font-weight-normal">40</h2>
                  <progress class="progress progress-xs mt-2 progress-success" value="60" max="100"></progress>
                </div>
                <div class="col-xs-3 text-xs-center"> <span class="text-muted">Total Revenue</span>
                  <h2 class="block font-weight-normal">0</h2>
                  <progress class="progress progress-xs mt-2 progress-success" value="90" max="100"></progress>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-4 col-lg-12">
          <div class="card card-inverse bg-info">
            <div class="card-body">
              <div class="position-relative">
                <div class="chart-title position-absolute mt-2 ml-2 white">
                  <h1 class="display-4">100%</h1>
                  <span>Employees Satisfied</span> </div>
                <canvas id="emp-satisfaction" class="height-400 block"></canvas>
                <div class="chart-stats position-absolute position-bottom-0 position-right-0 mb-2 mr-3 white"> <a href="#" class="btn bg-info bg-darken-3 mr-1 white">Statistics <i class="icon-stats-bars"></i></a> for the last year. </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--/ project charts --> 
      <!-- Recent invoice with Statistics -->
      <div class="row match-height">
        <div class="col-xl-4 col-lg-12">
          <div class="card">
            <div class="card-body">
              <div class="media">
                <div class="p-2 text-xs-center bg-deep-orange media-left media-middle"> <i class="icon-user1 font-large-2 white"></i> </div>
                <div class="p-2 media-body">
                  <h5 class="deep-orange">Global Settings</h5>
                  <progress class="progress progress-sm progress-deep-orange mt-1 mb-0" value="45" max="100"></progress>
                </div>
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-body">
              <div class="media">
                <div class="p-2 text-xs-center bg-cyan media-left media-middle"> <i class="icon-camera7 font-large-2 white"></i> </div>
                <div class="p-2 media-body">
                  <h5>New Products</h5>
                </div>
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-body">
              <div class="media">
                <div class="p-2 media-body text-xs-left">
                  <h5>New Users</h5>
                </div>
                <div class="p-2 text-xs-center bg-teal media-right media-middle"> <i class="icon-user1 font-large-2 white"></i> </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-8 col-lg-12">
          <div class="card">
            <div class="card-header">
              <h4 class="card-title">Recent Invoices</h4>
              
            </div>
            <div class="card-body">
              <div class="card-block">
                <p>Total paid invoices 0, unpaid 0. <span class="float-xs-right"><a href="#">Invoice Summary <i class="icon-arrow-right2"></i></a></span></p>
              </div>
              <div class="table-responsive">
                <table class="table table-hover mb-0">
                  <thead>
                    <tr>
                      <th>Invoice#</th>
                      <th>Customer Name</th>
                      <th>Status</th>
                      <th>Due</th>
                      <th>Amount</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="text-truncate"><a href="#">INV-001001</a></td>
                      <td class="text-truncate">Elizabeth W.</td>
                      <td class="text-truncate"><span class="tag tag-default tag-success">Paid</span></td>
                      <td class="text-truncate">10/05/2016</td>
                      <td class="text-truncate">$ 1200.00</td>
                    </tr>
                    <tr>
                      <td class="text-truncate"><a href="#">INV-001012</a></td>
                      <td class="text-truncate">Andrew D.</td>
                      <td class="text-truncate"><span class="tag tag-default tag-success">Paid</span></td>
                      <td class="text-truncate">20/07/2016</td>
                      <td class="text-truncate">$ 152.00</td>
                    </tr>
                    <tr>
                      <td class="text-truncate"><a href="#">INV-001401</a></td>
                      <td class="text-truncate">Megan S.</td>
                      <td class="text-truncate"><span class="tag tag-default tag-success">Paid</span></td>
                      <td class="text-truncate">16/11/2016</td>
                      <td class="text-truncate">$ 1450.00</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr>
    </div>
  </div>
</div>
    <footer class="footer footer-static footer-light navbar-border">
        <div class="row">
            
            <div class="col-md-12">
                <div class="footer_logo">
                    <img <c:url value="images/favicon-32.png"/>">
                    <%-- <link href="<c:url value="images/favicon-32.png"/>" rel="shortcut" /> --%>
                </div>
            </div>
        </div>
    </footer>
    <c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
<script src="/resources/js/scripts/pages/dashboard-lite.js" type="text/javascript"></script>
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