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
<%-- <link rel="stylesheet" type="text/css" href="<c:url value="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css"/>"> --%>


<link
	href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>"
	rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css"> -->


<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">

       <div class="content-body">
                <div class="row">
                    <div class="large-12 columns">
                        <div class="content-body">
                            <!-- Basic form layout section start -->
                            <c:url value="/company/save" var="createUrl" />
                            <form:form method="POST"  action="${createUrl}" class="commentForm" modelAttribute="company" data-toggle="validator" role="form">
                                <section id="basic-form-layouts">
                                    <div class="row match-height">
                                        <div class="col-md-12">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h2 class="card-title" id="basic-layout-icons">Company</h2>
                                                </div>
                                                
                                                <div class="card-body collapse in create-block">
                                                    <div class="card-block">
                                                        <form class="form">
                                                            <div class="form-body">
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Company Name</label>: ${company.name}

                                                                    </div>
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Company logo</label>: <img src="${filePath}" alt="Upload Image" id="output" width="100" height="70"> 
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Company Tagline</label>: ${company.companyTagLine}
                                                                    </div>
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Email</label>: ${company.emailId}
                                                                    </div>
                                                                </div>
                                                                 
                                                                 <div class="row">
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>State</label>: ${company.states.name}
                                                                    </div>
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Phone</label>: ${company.phoneNum}
                                                                    </div>
                                                                </div>
                                                                
                                                                <div class="row">
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>City</label>: ${company.city}
                                                                    </div>
                                                                     
                                                                </div>
                                                                
                                                                <%-- <div class="row">
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Plant</label>: ${user.plant}
                                                                    </div>
                                                                    <div class="col-sm-6 form-group has-feedback">
                                                                        <label>Role</label>: ${}
                                                                    </div>
                                                                </div> --%>

                                                                
                                                                
                                                                <!--  -->

                                                            </div>

                                                        </form>

                                                        <div>
                                                            <a href="#" onClick="goBack()" class="btn btn-primary">Back </a>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </section>
                            </form:form>
                        </div>
                        <br>
                        <br>
                    </div>
                </div>
                <!--/ project charts -->
                <br>
            </div>
			
		</div>
	</div>
	<footer class="footer footer-static footer-light navbar-border">
		<p class="clearfix text-muted text-sm-center mb-0 px-2">
			<span class="float-md-right d-xs-block d-md-inline-block">Copyright
				&copy; 2019 <a href="#" target="_blank"
				class="text-bold-800 grey darken-2">SMERP </a>, All rights reserved.
			</span>
		</p>
	</footer>

	<c:import url="/WEB-INF/jsp/loadJs.jsp" />

</body>

</html>

